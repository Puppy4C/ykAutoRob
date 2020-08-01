package cn.xgg.robbe.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.xgg.httptools.RequestResult;
import cn.xgg.httptools.Requests;
import cn.xgg.robbe.config.RobbeConfig;
import cn.xgg.robbe.pojo.UserInfo;

import com.alibaba.fastjson.JSON;

public class RobbeSupport {
	
	private  String auth = "eyJBVVRIT1JJVFkiOiI0Vk1GMm9vYm8vOD0iLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyMDM3OCIsImV4cCI6MTU4Njc0NTY5OH0.xkE43YEOFWQpcccXBEzJxzLBV6Ui7eF2veSeDcaWt9S5uAHAjVOPuAkE-eh--l0ePKY26UroSOL_XXRfWuVDRQ";
	private Logger logger = Logger.getLogger(RobbeSupport.class);
	
	@SuppressWarnings("unchecked")
	protected UserInfo login(String username, String password, TbRobbeStrategy strategy){
		Map<String,Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);
		params.put("type", 1);
		//params.put("imei", "867561040334461,867561040436415");
		params.put("imei", "861989049856257,861989049856265");
		//params.put("imei", "861989049856265,861989049856257");
		RequestResult result = Requests.doPost(RobbeConfig.LOGIN_URL, params);
		if(result != null || strategy.getToken()!=null){
			Map<String,Object> resultMap = (Map<String, Object>) JSON.parse(result.toString());
			if(String.valueOf(resultMap.get("success")).equals("true") || strategy.getToken()!=null ){
				UserInfo userInfo = new UserInfo();
				String token = null;
				String userid = null;
				if(strategy.getToken()!=null) {
					token = strategy.getToken();
				}else{
					//获取token与userid
					token = ((Map<String, Object>)resultMap.get("data")).get("token").toString();
					userid = ((Map<String, Object>)resultMap.get("data")).get("userid").toString();
				};
				//获取更多信息
				//String token = auth;
					RequestResult moreInfo = Requests.newPost().addHeader("Authorization", token)
									  .setUserAgent(RobbeConfig.USER_AGENT)
									  .excute(RobbeConfig.INFO_URL);
					Map<String,Object> resultMap2 = (Map<String, Object>) JSON.parse(moreInfo.toString());
					if(String.valueOf(resultMap2.get("success")).equals("true")){
						Map<String,Object> dataMap = (Map<String, Object>) resultMap2.get("data");
						Map<String,Object> userMap = (Map<String, Object>) dataMap.get("user");
						Map<String,Object> userIdcardMap = (Map<String, Object>) dataMap.get("userIdcard");
						List<Map<String,Object>> accounts = (List<Map<String, Object>>) dataMap.get("bindAccounts");
						for(Map<String,Object> account : accounts){
							if(String.valueOf(account.get("platformcode")).equals("tb")){
								int tbAccountId = Integer.parseInt(String.valueOf(account.get("id")));
								String mobile = String.valueOf(userMap.get("mobile"));
								String name = String.valueOf(userIdcardMap.get("realname"));
								userInfo.setUserid(userid);
								userInfo.setToken(token);
								userInfo.setMobile(mobile);
								userInfo.setName(name);
								userInfo.setTbAccountId(tbAccountId);
								logger.info("用户登录成功:"+userInfo);
								return userInfo;
							}
						}
						if(userInfo.getTbAccountId()==0){
							logger.info("用户未绑定淘宝账户，无法进行抢单！");
						}
					}else{
						logger.info("获取用户信息失败："+moreInfo);
					}
				}
			else{
				logger.info("登录失败:"+result);
			}
		}else{
			logger.info("请求URL:"+RobbeConfig.LOGIN_URL+"失败！");
		}
		
		return null;
	}

}
