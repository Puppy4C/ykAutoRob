package cn.xgg.robbe.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.xgg.httptools.RequestResult;
import cn.xgg.httptools.Requests;
import cn.xgg.robbe.config.RobbeConfig;
import cn.xgg.robbe.core.TbRobbe;
import cn.xgg.robbe.core.TbRobbeStrategy;
import cn.xgg.robbe.pojo.TbTask;
import cn.xgg.robbe.pojo.UserInfo;
import cn.xgg.robbe.utils.RobbeUtils;

public class TbRobbeJob implements Job {
	
	private Logger logger = Logger.getLogger(TbRobbeJob.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		boolean success = false;
		TbRobbe robbe =  (TbRobbe) context.getJobDetail().getJobDataMap().get("robbe");
		TbTask task = (TbTask) context.getJobDetail().getJobDataMap().get("task");
		Map<Integer,TbTask> workers =  robbe.getWorkers();
		UserInfo userInfo = robbe.getUserInfo();
		String orderid = null;
		TbRobbeStrategy strategy = robbe.getRobbeStrategy();
		logger.info("����:"+task.getId()+"��ʼִ��!");
		logger.info(task);
		while((task.getStarttime()+strategy.getDuration()-System.currentTimeMillis())>0){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("taskmainid", task.getId());
			params.put("bindaccountid", userInfo.getTbAccountId());
			RequestResult result = Requests.newPost()
					.setUserAgent(RobbeConfig.USER_AGENT)
					.addHeader("Authorization", userInfo.getToken())
					.excute(RobbeConfig.ROBBE_URL, params);
			logger.info(result);
			if(result!=null){
				Map<String,Object> resultMap = result.getMap();
				if(String.valueOf(resultMap.get("success")).equals("true")){
					orderid = ((Map<String, Object>)resultMap.get("data")).get("orderid").toString();;
					System.out.println(orderid);
					logger.info("�ӵ��ɹ�:"+task);
					success = true;
					break;
				}
				
			}else{
				logger.info("����URL��"+RobbeConfig.ROBBE_URL+"ʧ�ܣ�");
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		workers.remove(task.getId());
		if(!success){
			logger.info("����:"+task.getId()+"δ����");
		}else{
			if(strategy.isOnlyTmall()){
				if(robbe.isTmall(orderid)){
					logger.info("orderId:"+orderid+"����è����");
					robbe.addSuccess();
					logger.info(RobbeUtils.sendMsg(robbe.getUserInfo().getMobile()));
				}else{
					logger.info("orderId:"+orderid+"������è���񣬳���ȡ��������");
					if(!robbe.cancelOrder(orderid)){
						robbe.stop();
					}
				}
			}else{
				robbe.addSuccess();
				logger.info(RobbeUtils.sendMsg(robbe.getUserInfo().getMobile()));
			}
			
		}
		if(robbe.getSuccessNum()>=strategy.getMaxRobbeNum()){
			logger.info("������ɣ�������"+robbe.getSuccessNum()+"����");
			robbe.stop();
		}else{
			robbe.updateTaskList();
		}
		
		
		
	}

}
