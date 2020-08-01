package cn.xgg.robbe.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.xgg.httptools.RequestResult;
import cn.xgg.httptools.Requests;

public class RobbeUtils {
	
	private RobbeUtils (){};
	
	
	public static String parseCronExpression(long time){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		int second = calendar.get(Calendar.SECOND);
		int minute = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		return second+" "+minute+" "+hour+" "+day+" "+(month+1)+" "+"?"+" "+year;
	}
	
	public static String sendMsg(String mobile){
		String url = "http://www.gintong.com/cross/register/getVCodeForPassword.json";
		Map<String,String> params = new HashMap<>();
		params.put("mobile", mobile);
		params.put("mobileAreaCode", "86");
		params.put("type", "1");
		params.put("voice", "s");
		RequestResult result = Requests.doPostJson(url, params);
		return result.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(sendMsg("13135045229"));
	}
	
	
}
