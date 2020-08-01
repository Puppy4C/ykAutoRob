package cn.xgg.robbe.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import cn.xgg.httptools.RequestResult;
import cn.xgg.httptools.Requests;
import cn.xgg.robbe.config.RobbeConfig;
import cn.xgg.robbe.interfaces.Robbe;
import cn.xgg.robbe.job.TbRobbeJob;
import cn.xgg.robbe.job.TbTaskAutoUpdateJob;
import cn.xgg.robbe.pojo.TbTask;
import cn.xgg.robbe.pojo.UserInfo;
import cn.xgg.robbe.utils.RobbeUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TbRobbe extends RobbeSupport implements Robbe {
	private Logger logger = Logger.getLogger(TbRobbe.class);
	
	
	
	private String username;
	private String password;
	private TbRobbeStrategy robbeStrategy;
	private Map<Integer,TbTask> workers = new HashMap<Integer, TbTask>();
	
	private Scheduler scheduler;
	private UserInfo userInfo;
	private Integer successNum;
	
	private boolean isStart;
	
	public TbRobbe(String username, String password, TbRobbeStrategy robbeStrategy){
		this.username = username;
		this.password = password;
		this.robbeStrategy = robbeStrategy;
	}
	
	private void init(){
		this.successNum = 0;
		this.workers.clear();
		try {
			this.scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		registryAutoUpdateJob();
		isStart=true;
		
	}


	@Override
	public void start() {
		if(isStart) return;
		userInfo = login(username, password, robbeStrategy);
		if(userInfo==null){
			logger.info("获取用户信息失败！");
			return;
		}
		init();
	}
	
	public void updateTaskList(){
		clearJob();
		this.workers.clear();
		logger.info("正在更新任务列表...");
		List<TbTask> tbTaskList = getTbTaskList();
		int taskCount = tbTaskList.size();
		logger.info("获取到总任务数:"+taskCount);
		if(taskCount==0){
			logger.info("当前系统没有任务，等待下次更新！");
		}else{
			addTask(tbTaskList);
		}
	}
	
	
	private void clearJob() {
		for(Integer key : this.workers.keySet()){
			TriggerKey triggerKey = TriggerKey.triggerKey("tbRobbeTrigger"+key, username);
			try {
				this.scheduler.pauseTrigger(triggerKey);
				this.scheduler.unscheduleJob(triggerKey);
				this.scheduler.deleteJob(JobKey.jobKey("tbRobbeJob"+key, username));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@SuppressWarnings("unchecked")
	private List<TbTask> getTbTaskList(){
		List<TbTask> taskList = new ArrayList<TbTask>();
		Map<String,Object> params = new HashMap<String, Object>();
		for(int page=1;page<=10;page++){
			params.put("page", page);
			params.put("platformid", 3);
			RequestResult result = Requests.newPost()
					.setUserAgent(RobbeConfig.USER_AGENT)
					.addHeader("Authorization", userInfo.getToken())
					.excute(RobbeConfig.TASK_LIST_URL, params);
			if(result != null){
				Map<String,Object> resultMap = result.getMap();
				if(String.valueOf(resultMap.get("success")).equalsIgnoreCase("true")){
					Map<String,Object> dataMap = (Map<String, Object>) resultMap.get("data");
					List<TbTask> list = JSONObject.parseArray(String.valueOf(dataMap.get("list")), TbTask.class);
					if(list==null || list.size()==0){
						break;
					}else{
						taskList.addAll(list);
					}
				}else{
					logger.info("获取任务列表失败："+result);
				}
			}else{
				logger.info("请求URL："+RobbeConfig.TASK_LIST_URL+"失败！");
				break;
			}
		}
		return taskList;
		
	}
	
	private void addTask(List<TbTask> taskList){
		int count = 0;
		for(TbTask task:taskList){
			if(!robbeStrategy.doFilter(task)){
				if(!workers.containsKey(task.getId()) && registryTask(task)){ 
					count++;
				}
			}
		}
		logger.info("共添加满足要求的任务个数:"+count);
		
	}
	
	private boolean registryTask(TbTask task){
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("robbe", this);
		dataMap.put("task", task);
		String cronExpression = RobbeUtils.parseCronExpression(task.getStarttime()-robbeStrategy.getAdvanceTime());
		JobDetail job = JobBuilder.newJob(TbRobbeJob.class)
				.withIdentity("tbRobbeJob"+task.getId(), username)
				.usingJobData(dataMap)
				.build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("tbRobbeTrigger"+task.getId(), username)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();
		try {
			Date startTime = new Date(task.getStarttime());
			if(startTime.after(new Date())){
				Date date = scheduler.scheduleJob(job, trigger);
				workers.put(task.getId(), task);
				logger.info("添加任务"+task.getId()+"成功，任务金额:"+task.getProductprice()+"\t执行时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
				scheduler.start();
				return true;
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	private void registryAutoUpdateJob(){
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("robbe", this);
		JobDetail job = JobBuilder.newJob(TbTaskAutoUpdateJob.class)
				.withIdentity("TbTaskAutoUpdateJob", username)
				.usingJobData(dataMap)
				.build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("TbTaskAutoUpdateTrigger", username)
				.withSchedule(SimpleScheduleBuilder.repeatHourlyForever(robbeStrategy.getUpdateInterval()))
				.startNow()
				.build();
		try {
			scheduler.scheduleJob(job,trigger);
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	



	public TbRobbeStrategy getRobbeStrategy() {
		return robbeStrategy;
	}

	public Map<Integer, TbTask> getWorkers() {
		return workers;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	@Override
	public void stop() {
		if(!isStart){
			logger.info("抢单任务未开启！");
			return;
		}
		try {
			scheduler.clear();
			scheduler.shutdown();
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("抢单任务已关闭");
		isStart = false;
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean isTmall(String id){
		Map<String,Object>params  = new HashMap<>();
		params.put("id", id);
		RequestResult result = Requests.newPost()
			.setUserAgent(RobbeConfig.USER_AGENT)
			.addHeader("Authorization",userInfo.getToken())
			.excute(RobbeConfig.TASK_DETAILS_URL,params);
		if(result !=null){
			JSONObject parseObject = JSON.parseObject(result.toString());
			Map<String,Object> data = (Map<String, Object>) parseObject.get("data");
			Map<String,String> shop = (Map<String, String>) data.get("shop");
			String name = shop.get("name");
			System.out.println("店铺名："+name);
			if(name.endsWith("舰店")||name.endsWith("卖店")||name.endsWith("营店")){
				return true;
			}
		}
		return false;
	}
	
	public boolean cancelOrder(String id){
		Map<String,Object>params  = new HashMap<>();
		params.put("id", id);
		params.put("remark", "我不想做了");
		RequestResult result = Requests.newPost()
			.setUserAgent(RobbeConfig.USER_AGENT)
			.addHeader("Authorization",userInfo.getToken())
			.excute(RobbeConfig.CANCEL_URL,params);
		if(result !=null){
			JSONObject parseObject = JSON.parseObject(result.toString());
			if(String.valueOf(parseObject.get("success")).equals("true")){
				logger.info("任务取消成功!");
				return true;
			}
		}
		logger.info("任务取消失败!");
		return false;
	}

	public Integer getSuccessNum() {
		return successNum;
	}

	
	public void addSuccess(){
		this.successNum++;
	}
	
	
	

}
