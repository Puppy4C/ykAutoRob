package cn.xgg.robbe.core;


public class TbRobbeStrategyTemp {
	private TbRobbeStrategy robbeStrategy = new TbRobbeStrategy();
	

	

	/**
	 * ���ýӵ������۸񣬳����˼۸����Ʒ��������<BR>
	 * Ĭ��ֵ:999999F
	 * @param maxPrice
	 * @return
	 */
	public TbRobbeStrategyTemp setMaxPrice(Float maxPrice) {
		robbeStrategy.setMaxPrice(maxPrice);
		return this;
	}

	
	/**
	 * ����token
	 * 
	 * @param maxPrice
	 * @return
	 */
	public TbRobbeStrategyTemp setToken(String token) {
		robbeStrategy.setToken(token);
		return this;
	}

	

	/**
	 * ���ýӵ�����С�۸�С�ڴ˼۸����Ʒ��������<BR>
	 * Ĭ��ֵ:0
	 * @param minPrice
	 * @return
	 */
	public TbRobbeStrategyTemp setMinPrice(Float minPrice) {
		robbeStrategy.setMinPrice(minPrice);
		return this;
	}

	

	/**
	 * ���ýӵ������Ӷ�𣬳�����ֵ�����񽫻ᱻ����<BR>
	 * Ĭ��ֵ:999999F
	 * @param maxCommission
	 * @return
	 */
	public TbRobbeStrategyTemp setMaxCommission(Float maxCommission) {
		robbeStrategy.setMaxCommission(maxCommission);
		return this;
	}

	

	/**
	 * ���ýӵ�����СӶ��С�ڴ�ֵ�����񽫻ᱻ����<BR>
	 * Ĭ��ֵ:0
	 * @param minCommission
	 * @return
	 */
	public TbRobbeStrategyTemp setMinCommission(Float minCommission) {
		robbeStrategy.setMinCommission(minCommission);
		return this;
	}

	
	/**
	 * ����������������������ɹ��������ﵽ�����õ�����ʱ������������������<BR>
	 * Ĭ��ֵ:999999
	 * @param maxRobbeNum
	 * @return
	 */
	public TbRobbeStrategyTemp setMaxRobbeNum(Integer maxRobbeNum) {
		robbeStrategy.setMaxRobbeNum(maxRobbeNum);
		return this;
	}
	
	/**
	 * ������ǰ������ʱ�䣬��λ����  Ϊ��ֹԶ�̷�����ʱ���뱾�ز�һ�£���ǰ���������������ɹ��ʡ�<BR>
	 * Ĭ��ֵ:10��
	 * @param advanceTime
	 * @return
	 */
	public TbRobbeStrategyTemp setAdvanceTime(Long advanceTime) {
		robbeStrategy.setAdvanceTime(advanceTime);
		return this;
	}
	
	
	/**
	 * �Ƿ�ֻ����è����
	 */
	
	public TbRobbeStrategyTemp setOnlyTmall(boolean onlyTmall) {
		robbeStrategy.setOnlyTmall(onlyTmall);
		return this;
	}
	

	/**
	 * ��������������ʱ�䣬��λ���� <BR>
	 * ����ʼ����ʱ�������һֱ����������ֱ�������ɹ����ߴﵽ�����õ�ʱ��<BR>
	 * Ĭ��ֵ:20��
	 * @param duration
	 * @return
	 */
	public TbRobbeStrategyTemp setDuration(Long duration) {
		robbeStrategy.setDuration(duration);
		return this;
	}

	
	/**
	 * ���������б�ĸ��¼�� ��λ��Сʱ<BR>
	 * Ĭ��ֵ:7Сʱ
	 * @param updateInterval
	 * @return
	 */
	public TbRobbeStrategyTemp setUpdateInterval(Integer updateInterval) {
		robbeStrategy.setUpdateInterval(updateInterval);
		return this;
	}
	
	/**
	 * ����������ʱ��
	 * @param start ��ʼʱ��  Ĭ��ֵ:00:00
	 * @param end   ����ʱ��  Ĭ��ֵ:23:59
	 */
	public TbRobbeStrategyTemp setRobbeTimeField(String start, String end){
		if(start==null || end==null) return this;
		String[] startSplit = start.split(":");
		if(startSplit.length !=2) return this;
		int startHour = Integer.parseInt(startSplit[0]);
		int startMinute = Integer.parseInt(startSplit[1]);
		if(startHour<0 || startHour>23 || startMinute<0 || startMinute>59) return this;
		
		String[] endSplit = end.split(":");
		if(endSplit.length !=2) return this;
		int endHour = Integer.parseInt(endSplit[0]);
		int endMinute = Integer.parseInt(endSplit[1]);
		if(endHour<0 || endHour>23 || endMinute<0 || endMinute>59) return this;
		robbeStrategy.setRobbeTimeField(start, end);
		return this;
	}
	
	public TbRobbeStrategy build(){
		return robbeStrategy;
	}
	
	
}
