package cn.xgg.robbe.core;


public class TbRobbeStrategyTemp {
	private TbRobbeStrategy robbeStrategy = new TbRobbeStrategy();
	

	

	/**
	 * 设置接单的最大价格，超过此价格的商品将被忽略<BR>
	 * 默认值:999999F
	 * @param maxPrice
	 * @return
	 */
	public TbRobbeStrategyTemp setMaxPrice(Float maxPrice) {
		robbeStrategy.setMaxPrice(maxPrice);
		return this;
	}

	
	/**
	 * 设置token
	 * 
	 * @param maxPrice
	 * @return
	 */
	public TbRobbeStrategyTemp setToken(String token) {
		robbeStrategy.setToken(token);
		return this;
	}

	

	/**
	 * 设置接单的最小价格，小于此价格的商品将被忽略<BR>
	 * 默认值:0
	 * @param minPrice
	 * @return
	 */
	public TbRobbeStrategyTemp setMinPrice(Float minPrice) {
		robbeStrategy.setMinPrice(minPrice);
		return this;
	}

	

	/**
	 * 设置接单的最大佣金，超过此值的任务将会被忽略<BR>
	 * 默认值:999999F
	 * @param maxCommission
	 * @return
	 */
	public TbRobbeStrategyTemp setMaxCommission(Float maxCommission) {
		robbeStrategy.setMaxCommission(maxCommission);
		return this;
	}

	

	/**
	 * 设置接单的最小佣金，小于此值的任务将会被忽略<BR>
	 * 默认值:0
	 * @param minCommission
	 * @return
	 */
	public TbRobbeStrategyTemp setMinCommission(Float minCommission) {
		robbeStrategy.setMinCommission(minCommission);
		return this;
	}

	
	/**
	 * 设置最大抢单数，当抢单成功的数量达到此设置的数量时，所有抢单任务会结束<BR>
	 * 默认值:999999
	 * @param maxRobbeNum
	 * @return
	 */
	public TbRobbeStrategyTemp setMaxRobbeNum(Integer maxRobbeNum) {
		robbeStrategy.setMaxRobbeNum(maxRobbeNum);
		return this;
	}
	
	/**
	 * 设置提前抢单的时间，单位毫秒  为防止远程服务器时间与本地不一致，提前抢单能增大抢单成功率。<BR>
	 * 默认值:10秒
	 * @param advanceTime
	 * @return
	 */
	public TbRobbeStrategyTemp setAdvanceTime(Long advanceTime) {
		robbeStrategy.setAdvanceTime(advanceTime);
		return this;
	}
	
	
	/**
	 * 是否只抢天猫任务
	 */
	
	public TbRobbeStrategyTemp setOnlyTmall(boolean onlyTmall) {
		robbeStrategy.setOnlyTmall(onlyTmall);
		return this;
	}
	

	/**
	 * 设置抢单持续的时间，单位毫秒 <BR>
	 * 当开始抢单时，程序会一直尝试抢单，直到抢单成功或者达到此设置的时间<BR>
	 * 默认值:20秒
	 * @param duration
	 * @return
	 */
	public TbRobbeStrategyTemp setDuration(Long duration) {
		robbeStrategy.setDuration(duration);
		return this;
	}

	
	/**
	 * 设置任务列表的更新间隔 单位：小时<BR>
	 * 默认值:7小时
	 * @param updateInterval
	 * @return
	 */
	public TbRobbeStrategyTemp setUpdateInterval(Integer updateInterval) {
		robbeStrategy.setUpdateInterval(updateInterval);
		return this;
	}
	
	/**
	 * 设置抢单的时段
	 * @param start 开始时间  默认值:00:00
	 * @param end   结束时间  默认值:23:59
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
