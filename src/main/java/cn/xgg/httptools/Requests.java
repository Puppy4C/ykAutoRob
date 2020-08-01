package cn.xgg.httptools;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * requests httpClient工具类
 * 可以用来发送get和post请求
 * @author xgg
 *
 */
public class Requests {
	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

	
	private Requests(){
		
	}
	
	public static BuildForGet newGet(){
		return new BuildForGet();
	}
	
	public static BuildForPost newPost(){
		return new BuildForPost();
	}
	public static BuildForPostJson newPostByJson(){
		return new BuildForPostJson();
	}
	
	
	
	public static CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public static void setHttpClient(CloseableHttpClient httpClient) {
		Requests.httpClient = httpClient;
	}
	
	public static RequestResult doGet(String url){
		return newGet().excute(url);
	}
	
	public static RequestResult doPost(String url, Object params){
		return newPost().excute(url,params);
	}
	
	public static RequestResult doPostJson(String url, Object params){
		return newPostByJson().excute(url, params);
	}
	

}
