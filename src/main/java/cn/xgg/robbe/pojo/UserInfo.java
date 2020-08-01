package cn.xgg.robbe.pojo;

/**
 * 封易客平台用户的基本信息
 * @author xgg
 *
 */
public class UserInfo {
	private String userid;
	private String token;
	private String name;
	private int tbAccountId;
	private String mobile;
	
	
	public UserInfo(String userid, String token, String name, int tbAccountId,
			String mobile) {
		super();
		this.userid = userid;
		this.token = token;
		this.name = name;
		this.tbAccountId = tbAccountId;
		this.mobile = mobile;
	}
	public UserInfo() {
		super();
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTbAccountId() {
		return tbAccountId;
	}
	public void setTbAccountId(int tbAccountId) {
		this.tbAccountId = tbAccountId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "UserInfo [userid=" + userid + ", token=" + token + ", name="
				+ name + ", tbAccountId=" + tbAccountId + ", mobile=" + mobile
				+ "]";
	}
	
	

}
