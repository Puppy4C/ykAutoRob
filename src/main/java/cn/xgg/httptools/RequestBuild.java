package cn.xgg.httptools;


public interface RequestBuild {
	

	/**
	 * �������ͷ
	 * @param name
	 * @param value
	 * @return
	 */
	public RequestBuild addHeader(String name, String value);
	
	
	/**
	 * ���Cookie
	 * @param key
	 * @param value
	 * @return
	 */
	public RequestBuild addCookie(String key, String value);
	
	/**
	 * ����User-Agent
	 * @param userAgent
	 * @return
	 */
	public RequestBuild setUserAgent(String userAgent);
	
	/**
	 * ���ó�ʱʱ��
	 * @param timeout
	 * @return
	 */
	public RequestBuild setTimeout(int timeout );
	
	/**
	 * ���ô���
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
