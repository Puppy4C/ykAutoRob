package cn.xgg.httptools;


import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import com.alibaba.fastjson.JSON;

public class BuildForPostJson implements RequestBuild {

	
	private HttpPost httpPost = new HttpPost();
	
	private Builder custom = RequestConfig.custom();
	
	private StringBuffer cookies = new StringBuffer("");
	

	
	public BuildForPostJson() {
		httpPost.addHeader("Content-Type", "application/json");
		setTimeout(10000);
	}


	/**
	 * 添加请求头
	 * @param name
	 * @param value
	 * @return
	 */
	public RequestBuild addHeader(String name, String value){
		httpPost.addHeader(name, value);
		return this;
	}
	
	
	/**
	 * 添加Cookie
	 * @param key
	 * @param value
	 * @return
	 */
	public RequestBuild addCookie(String key, String value){
		cookies.append(key+"="+value+";");
		return this;
	}
	
	/**
	 * 设置User-Agent
	 * @param userAgent
	 * @return
	 */
	public RequestBuild setUserAgent(String userAgent){
		httpPost.addHeader("User-Agent", userAgent);
		return this;
	}
	
	/**
	 * 设置超时时间
	 * @param timeout
	 * @return
	 */
	public RequestBuild setTimeout(int timeout ){
		custom = custom.setSocketTimeout(timeout).setConnectTimeout(timeout);
		return this;
	}
	
	
	/**
	 * 设置代理
	 * @param hostname
	 * @param port
	 * @return
	 */
	public RequestBuild setProxy(String hostname, int port){
		HttpHost proxy = new HttpHost(hostname, port);
		custom = custom.setProxy(proxy);
		return this;
	}
	
	public RequestResult excute(String url, Object params, String charset){
		CloseableHttpClient httpClient = Requests.getHttpClient();
		httpPost.setConfig(custom.build());
		httpPost.addHeader("Cookie", cookies.toString());
		HttpEntity entity = null;
		HttpResponse response = null;
		try {
			StringEntity en = new StringEntity(JSON.toJSONString(params),"utf-8");
			httpPost.setEntity(en);
			httpPost.setURI(URI.create(url));
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			return new RequestResult(entity, response, "utf-8");
		} catch (Exception e) {
			return new RequestResult(null, null, "utf-8");
		}
		
	}
	
	
	public RequestResult excute(String url, Object params){
		return excute(url, params,"utf-8");
	}


	@Override
	public RequestResult excute(String url, String charset) {
		return excute(url, null, charset);
	}
	
	public RequestResult excute(String url) {
		return excute(url, null, "utf-8");
	}
}
