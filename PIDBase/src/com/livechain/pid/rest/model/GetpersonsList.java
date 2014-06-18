package com.livechain.pid.rest.model;

import java.util.List;

//获取个人信息列表 ---元素数据结构
public class GetpersonsList {
	private String pname;//姓名
	private String gender;//性别代码
	private String birthday;//出生日期
	private String email;//电子邮件地址
	private List<Number> credentials;//身份证件号码
	private String nickname;//昵称
	private String registeretype;//户籍类型代码
	private String addrprovince;//地址-省
	private String addrcity;//地址-市
	private String addrcounty;//地址-县
	private String addrtown;//地址-乡
	private String addrvillage;//地址-村
	private String addrhouseid;//地址-门牌号码
	private List<Number> phone;//电话号码
	private String nationality;//国籍
	private String marriedSts;//婚姻状况代码
	private String nation;//民族
	private int  age;//年龄
	private String booldtype;//ABO血型
	private String rh;//RH血型
	private String workdt;//参加工作日期
	private String jobtypecode;//职业类别代码
	private String educationcode;//学历代码
	private String degreecode;//学位代码
	private List orgcode;//机构代码
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Number> getCredentials() {
		return credentials;
	}
	public void setCredentials(List<Number> credentials) {
		this.credentials = credentials;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegisteretype() {
		return registeretype;
	}
	public void setRegisteretype(String registeretype) {
		this.registeretype = registeretype;
	}
	public String getAddrprovince() {
		return addrprovince;
	}
	public void setAddrprovince(String addrprovince) {
		this.addrprovince = addrprovince;
	}
	public String getAddrcity() {
		return addrcity;
	}
	public void setAddrcity(String addrcity) {
		this.addrcity = addrcity;
	}
	public String getAddrcounty() {
		return addrcounty;
	}
	public void setAddrcounty(String addrcounty) {
		this.addrcounty = addrcounty;
	}
	public String getAddrtown() {
		return addrtown;
	}
	public void setAddrtown(String addrtown) {
		this.addrtown = addrtown;
	}
	public String getAddrvillage() {
		return addrvillage;
	}
	public void setAddrvillage(String addrvillage) {
		this.addrvillage = addrvillage;
	}
	public String getAddrhouseid() {
		return addrhouseid;
	}
	public void setAddrhouseid(String addrhouseid) {
		this.addrhouseid = addrhouseid;
	}
	public List<Number> getPhone() {
		return phone;
	}
	public void setPhone(List<Number> phone) {
		this.phone = phone;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getMarriedSts() {
		return marriedSts;
	}
	public void setMarriedSts(String marriedSts) {
		this.marriedSts = marriedSts;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBooldtype() {
		return booldtype;
	}
	public void setBooldtype(String booldtype) {
		this.booldtype = booldtype;
	}
	public String getRh() {
		return rh;
	}
	public void setRh(String rh) {
		this.rh = rh;
	}
	public String getWorkdt() {
		return workdt;
	}
	public void setWorkdt(String workdt) {
		this.workdt = workdt;
	}
	public String getJobtypecode() {
		return jobtypecode;
	}
	public void setJobtypecode(String jobtypecode) {
		this.jobtypecode = jobtypecode;
	}
	public String getEducationcode() {
		return educationcode;
	}
	public void setEducationcode(String educationcode) {
		this.educationcode = educationcode;
	}
	public String getDegreecode() {
		return degreecode;
	}
	public void setDegreecode(String degreecode) {
		this.degreecode = degreecode;
	}
	public List getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(List orgcode) {
		this.orgcode = orgcode;
	}
	
	
}
