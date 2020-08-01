package cn.xgg.robbe.core;

import java.util.Calendar;
import java.util.Date;

import cn.xgg.robbe.pojo.TbTask;


public class TbRobbeStrategy {
	
	/**
	 * �ӵ�����������Ʒ�����۸�
	 */
	private Float maxPrice = 999999f;
	
	/**
	 * �ӵ�����������Ʒ����С�۸�
	 */
	private Float minPrice = 0f;
	
	/**
	 * �ӵ�����������Ʒ�����Ӷ��
	 */
	private Float maxCommission = 999999f;
	
	/**
	 * �ӵ�����������Ʒ����СӶ��
	 */
	private Float minCommission = 0f;
	
	/**
	 * ���ӵ���
	 */
	private Integer maxRobbeNum = 999999;
	
	/**
	 * ���Խ���������ʱ��(����)
	 */
	private Long duration = 1000*20L;
	
	/**
	 * ��ǰ������ʱ��
	 */
	private Long advanceTime = 10000L;
	
	/**
	 * �����б���¼��
	 */
	private Integer updateInterval = 7;
	

	/**
	 * ��������ʼʱ��
	 */
	private String startTime = "00:00";
	private String endTime = "23:59";
	
	/**
	 * ָ��Token
	 * @return
	 */
	private String token = null;
	
	/**
	 * �Ƿ�ֻ����è����
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
	 * ����������ʱ��
	 * @param start ��ʼʱ��  Ĭ��ֵ:00:00
	 * @param end   ����ʱ��  Ĭ��ֵ:23:59
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
	 * �жϼ۸��Ӷ���Լ�����ʱ���Ƿ����趨�ķ�Χ֮�ڣ�����ڣ�����false�����򷵻�true<BR>
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
