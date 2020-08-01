package cn.xgg.httptools;


public interface RequestBuild {
	

	/**
	 * 添加请求头
	 * @param name
	 * @param value
	 * @return
	 */
	public RequestBuild addHeader(String name, String value);
	
	
	/**
	 * 添加Cookie
	 * @param key
	 * @param value
	 * @return
	 */
	public RequestBuild addCookie(String key, String value);
	
	/**
	 * 设置User-Agent
	 * @param userAgent
	 * @return
	 */
	public RequestBuild setUserAgent(String userAgent);
	
	/**
	 * 设置超时时间
	 * @param timeout
	 * @return
	 */
	public RequestBuild setTimeout(int timeout );
	
	/**
	 * 设置代理
	 * @param hostname
	 * @param port
	 * @return
	 */
	public RequestBuild setProxy(String hostname, int port);
	
	public RequestResult excute(String url);
	public RequestResult excute(String url,  String charset);
	
	public RequestResult excute(String url,  Object params);
	public RequestResult excute(String url, Object params, String charset);
	
	
}
