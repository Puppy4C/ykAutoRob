package cn.xgg.robbe.main;


import cn.xgg.robbe.core.TbRobbe;
import cn.xgg.robbe.core.TbRobbeStrategyBuilder;
import cn.xgg.robbe.interfaces.Robbe;


public class Test {
	

	private static final String USERNAME = "your username";
	private static final String PASSWORD = "your password";

	public static void main(String[] args) {
		
		
		Robbe robbe = new TbRobbe(USERNAME, PASSWORD, TbRobbeStrategyBuilder.newTbRobbeStrategy()
				.setMinPrice(30F)
				.setMaxPrice(400F)
				.setRobbeTimeField("00:00", "23:59")
				.setUpdateInterval(1)
				.setMaxRobbeNum(1)
				.setDuration(3000L)
				.setAdvanceTime(1000L )
				.setOnlyTmall(true)
				.build()
				);
		robbe.start();
	}

}
