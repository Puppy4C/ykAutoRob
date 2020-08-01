package cn.xgg.robbe.pojo;

import java.util.List;

public class TbTask {
	private Integer id;
	private Integer taskid;
	private Integer taskserviceid;
	private Float reward;
	private Integer ifplatformrefund;
	private Long starttime;
	private Float productprice;
	private Float taskcommission;
	private Float addservicecommission;
	private Float sysservicemoney;
	private String keyword;
	private String extrakeyword;
	private String pic;
	private String describe;
	private String productrule;
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	private Integer ordernumber;
	private Integer status;
	private String tasktitle;
	private String sorttype;
	private String platformcode;
	private String currentTime;
	private List<String> productPicList;
	private String userlevelLabel;
	
	
	
	public TbTask() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaskid() {
		return taskid;
	}
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	public Integer getTaskserviceid() {
		return taskserviceid;
	}
	public void setTaskserviceid(Integer taskserviceid) {
		this.taskserviceid = taskserviceid;
	}
	public Float getReward() {
		return reward;
	}
	public void setReward(Float reward) {
		this.reward = reward;
	}
	public Integer getIfplatformrefund() {
		return ifplatformrefund;
	}
	public void setIfplatformrefund(Integer ifplatformrefund) {
		this.ifplatformrefund = ifplatformrefund;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Float getProductprice() {
		return productprice;
	}
	public void setProductprice(Float productprice) {
		this.productprice = productprice;
	}
	public Float getTaskcommission() {
		return taskcommission;
	}
	public void setTaskcommission(Float taskcommission) {
		this.taskcommission = taskcommission;
	}
	public Float getAddservicecommission() {
		return addservicecommission;
	}
	public void setAddservicecommission(Float addservicecommission) {
		this.addservicecommission = addservicecommission;
	}
	public Float getSysservicemoney() {
		return sysservicemoney;
	}
	public void setSysservicemoney(Float sysservicemoney) {
		this.sysservicemoney = sysservicemoney;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getExtrakeyword() {
		return extrakeyword;
	}
	public void setExtrakeyword(String extrakeyword) {
		this.extrakeyword = extrakeyword;
	}
	public Integer getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTasktitle() {
		return tasktitle;
	}
	public void setTasktitle(String tasktitle) {
		this.tasktitle = tasktitle;
	}
	public String getSorttype() {
		return sorttype;
	}
	public void setSorttype(String sorttype) {
		this.sorttype = sorttype;
	}
	public String getPlatformcode() {
		return platformcode;
	}
	public void setPlatformcode(String platformcode) {
		this.platformcode = platformcode;
	}
	
	public String getUserlevelLabel() {
		return userlevelLabel;
	}
	public void setUserlevelLabel(String userlevelLabel) {
		this.userlevelLabel = userlevelLabel;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getProductrule() {
		return productrule;
	}
	public void setProductrule(String productrule) {
		this.productrule = productrule;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public List<String> getProductPicList() {
		return productPicList;
	}
	public void setProductPicList(List<String> productPicList) {
		this.productPicList = productPicList;
	}
	@Override
	public String toString() {
		return "TbTask [id=" + id + ", taskid=" + taskid + ", taskserviceid="
				+ taskserviceid + ", reward=" + reward + ", ifplatformrefund="
				+ ifplatformrefund + ", starttime=" + starttime
				+ ", productprice=" + productprice + ", taskcommission="
				+ taskcommission + ", addservicecommission="
				+ addservicecommission + ", sysservicemoney=" + sysservicemoney
				+ ", keyword=" + keyword + ", extrakeyword=" + extrakeyword
				+ ", pic=" + pic + ", describe=" + describe + ", productrule="
				+ productrule + ", field1=" + field1 + ", field2=" + field2
				+ ", field3=" + field3 + ", field4=" + field4 + ", field5="
				+ field5 + ", ordernumber=" + ordernumber + ", status="
				+ status + ", tasktitle=" + tasktitle + ", sorttype="
				+ sorttype + ", platformcode=" + platformcode
				+ ", currentTime=" + currentTime + ", productPicList="
				+ productPicList + ", userlevelLabel=" + userlevelLabel + "]";
	}
	
	
	
	
	

}
