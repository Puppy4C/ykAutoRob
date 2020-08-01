package cn.xgg.robbe.core;

import java.util.Calendar;
import java.util.Date;

import cn.xgg.robbe.pojo.TbTask;


public class TbRobbeStrategy {
	
	/**
	 * 接单所能允许商品的最大价格
	 */
	private Float maxPrice = 999999f;
	
	/**
	 * 接单所能允许商品的最小价格
	 */
	private Float minPrice = 0f;
	
	/**
	 * 接单所能允许商品的最大佣金
	 */
	private Float maxCommission = 999999f;
	
	/**
	 * 接单所能允许商品的最小佣金
	 */
	private Float minCommission = 0f;
	
	/**
	 * 最大接单数
	 */
	private Integer maxRobbeNum = 999999;
	
	/**
	 * 尝试进行抢单的时间(毫秒)
	 */
	private Long duration = 1000*20L;
	
	/**
	 * 提前抢单的时间
	 */
	private Long advanceTime = 10000L;
	
	/**
	 * 任务列表更新间隔
	 */
	private Integer updateInterval = 7;
	

	/**
	 * 抢单的起始时段
	 */
	private String startTime = "00:00";
	private String endTime = "23:59";
	
	/**
	 * 指定Token
	 * @return
	 */
	private String token = null;
	
	/**
	 * 是否只抢天猫任务
	 * @return
	 */
	private boolean onlyTmall = false;
	
	
	
	public boolean isOnlyTmall() {
		return onlyTmall;
	}

	public void setOnlyTmall(boolean onlyTmall) {
		this.onlyTmall = onlyTmall;
	}

	public Long getAdvanceTime() {
		return advanceTime;
	}

	public void setAdvanceTime(Long advanceTime) {
		this.advanceTime = advanceTime;
	}

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public Float getMaxCommission() {
		return maxCommission;
	}

	public void setMaxCommission(Float maxCommission) {
		this.maxCommission = maxCommission;
	}

	public Float getMinCommission() {
		return minCommission;
	}

	public void setMinCommission(Float minCommission) {
		this.minCommission = minCommission;
	}

	public Integer getMaxRobbeNum() {
		return maxRobbeNum;
	}

	public void setMaxRobbeNum(Integer maxRobbeNum) {
		this.maxRobbeNum = maxRobbeNum;
	}

	
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	

	public Integer getUpdateInterval() {
		return updateInterval;
	}

	public void setUpdateInterval(Integer updateInterval) {
		this.updateInterval = updateInterval;
	}
	
	/**
	 * 设置抢单的时段
	 * @param start 开始时间  默认值:00:00
	 * @param end   结束时间  默认值:23:59
	 */
	public void setRobbeTimeField(String start, String end){
		this.startTime = start;
		this.endTime = end;
	}
	

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	/**
	 * 判断价格和佣金以及抢单时段是否在设定的范围之内，如果在，返回false，否则返回true<BR>
	 * @param task
	 * @return
	 */
	public boolean doFilter(TbTask task) {
		Float price = task.getProductprice();
		Float commission = task.getTaskcommission();
		if(price<minPrice || price>maxPrice) return true;
		if(commission<minCommission || commission>maxCommission) return true;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(task.getStarttime()));
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		String[] startSplit = startTime.split(":");
		String[] endSplit = endTime.split(":");
		int startHour = Integer.parseInt(startSplit[0]);
		int startMinute = Integer.parseInt(startSplit[1]);
		int endHour = Integer.parseInt(endSplit[0]);
		int endMinute = Integer.parseInt(endSplit[1]);
		if((hour<startHour) || ((hour==startHour)&&minute<startMinute)) return true;
		if((hour>endHour) || ((hour==endHour)&&minute>endMinute)) return true;
		return false;
		
	}

	@Override
	public String toString() {
		return "TbRobbeStrategy [maxPrice=" + maxPrice + ", minPrice="
				+ minPrice + ", maxCommission=" + maxCommission
				+ ", minCommission=" + minCommission + ", maxRobbeNum="
				+ maxRobbeNum + ", advanceTime=" + advanceTime + "]";
	}
	
	
	
	
	

}
