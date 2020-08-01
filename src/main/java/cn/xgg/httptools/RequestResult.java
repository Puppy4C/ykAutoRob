package cn.xgg.httptools;


import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;


public class RequestResult {
	
	private HttpEntity entity;
	
	private HttpResponse response;
	
	
	private String content;
	
	public RequestResult(HttpEntity entity, HttpResponse response, String charset) {
		this.entity = entity;
		this.response = response;
		try {
			content = EntityUtils.toString(entity,charset);
		} catch (Exception e) {
			content = null;
		}
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return content;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getMap(){
		return (Map<String, Object>) JSON.parse(content);
	}
	
	

}
