package cn.xgg.robbe.config;

import java.util.Properties;


public class RobbeConfig {
	
	public static String BASE_URL = "http://m.zhenyaole.cn";
	public static String LOGIN_URL = BASE_URL + "/login";
	public static String INFO_URL = BASE_URL + "/v1.1/person/getBasicInfo";
	public static String TASK_LIST_URL = BASE_URL + "/v1.1/task/getTaskList";
	public static String ROBBE_URL = BASE_URL + "/v1.1/order/orderTaking";
	public static String CANCEL_URL = BASE_URL + "/v1.1/order/cancelOrder";
	public static String USER_AGENT = "HLK-AL00(Android/10) (com.lsjz.zjhapp/2.4.7) Weex/0.26.0 1080x2340";
	public static String TASK_DETAILS_URL = BASE_URL + "/v1.1/order/getOrderAndTaskDetail";
	
	static{
		Properties properties =  new Properties();
		try {
			properties.load(RobbeConfig.class.getResourceAsStream("/robbe.properties"));
			String baseUrl = properties.getProperty("BASE_URL");
			if(baseUrl!=null) BASE_URL = baseUrl;
			String loginUrl = properties.getProperty("LOGIN_URL");
			if(loginUrl!=null) LOGIN_URL = BASE_URL + loginUrl;
			String infoUrl = properties.getProperty("INFO_URL");
			if(infoUrl!=null) INFO_URL = BASE_URL + infoUrl;
			
			String taskListUrl = properties.getProperty("TASK_LIST_URL");
			if(taskListUrl!=null) TASK_LIST_URL = BASE_URL + taskListUrl;
			
			String robbeUrl = properties.getProperty("ROBBE_URL");
			if(robbeUrl!=null) ROBBE_URL = BASE_URL + robbeUrl;
			
			String taskDetailsUrl = properties.getProperty("TASK_DETAILS_URL");
			if(taskDetailsUrl!=null) TASK_DETAILS_URL = BASE_URL + taskDetailsUrl;
			
			String userAgent = properties.getProperty("USER_AGENT");
			if(userAgent!=null) USER_AGENT = userAgent;
		} catch (Exception e) {
		}
	}

}
