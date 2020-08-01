package cn.xgg.httptools;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;


public class BuildForGet implements RequestBuild {
	private HttpGet httpGet = new HttpGet();
	
	private Builder custom = RequestConfig.custom();
	
	private StringBuffer cookies = new StringBuffer("");
	

	
	public BuildForGet() {
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
		setTimeout(10000);
	}


	/**
	 * 添加请求头
	 * @param name
	 * @param value
	 * @return
	 */
	public RequestBuild addHeader(String name, String value){
		httpGet.addHeader(name, value);
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
		httpGet.addHeader("User-Agent", userAgent);
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
	
	@Override
	public RequestResult excute(String url,  String charset){
		return excute(url, null, charset);
	}
	
	@Override
	public RequestResult excute(String url){
		return excute(url, null,"utf-8");
	}

	@Override
	public RequestResult excute(String url, Object params) {
		return excute(url, params, "utf-8");
	}


	@SuppressWarnings("unchecked")
	@Override
	public RequestResult excute(String url, Object params, String charset) {
		CloseableHttpClient httpClient = Requests.getHttpClient();
		httpGet.setConfig(custom.build());
		httpGet.addHeader("Cookie", cookies.toString());
		HttpEntity entity = null;
		HttpResponse response = null;
		try {
			if(params != null){
				Map<String,Object> mapParams = null; 
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				if(params instanceof Map){
					mapParams = (Map<String, Object>) params;
				}else{
					String json = JSON.toJSONString(params);
					mapParams = (Map<String, Object>) JSON.parse(json);
				}
				for(Entry<String,Object> entry : mapParams.entrySet()){
					BasicNameValuePair nameValuePair = new BasicNameValuePair(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
					parameters.add(nameValuePair);
				}
				URIBuilder uriBuilder = new URIBuilder(url);
				uriBuilder.setParameters(parameters);
				httpGet.setURI(uriBuilder.build());
			}else{
				httpGet.setURI(URI.create(url));
			}
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			return new RequestResult(entity, response, charset);
		} catch (Exception e) {
			return null;
		}
	}
	

}
