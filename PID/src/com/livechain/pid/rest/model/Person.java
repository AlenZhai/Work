package com.livechain.pid.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

@XmlRootElement(name="person")
public class Person {
	/*Map filed=new HashMap();	
	public Map getPerson() {
		return filed;
	}

	public void setPerson(Map person) {
		this.filed = person;
	}
	public void put(String key,Object value)
	{
		this.filed.put(key, value);
	}
	public void remove(String key)
	{ 		
		this.filed.remove(key);
	}
	public int size()
	{
		return this.filed.size();
	}
	public Object get(String key)
	{
		return this.filed.get(key);
	}
	public boolean containsKey(String key)
	{
		return this.filed.keySet().contains(key);
	}*/
	
    private String pname;                      //姓名
    private String gender;                     //性别
    private String birthday;                   //出生日期
    private List<UtilModel> phone;             //联系电话
    private List<UtilModel> credentials;      //证件号
    private String nickname;                  //昵称
    private String registeretype;             //户籍类型
    private String addrprovince;              //地址-省
    private String addrcity;                  //地址-市
    private String addrcounty;                 //地址-县
    private String addrtown;                  //地址-乡
    private String addrvillage;                 //地址-村
    private String addrhouseid;                 //地址-门牌号
    private String nationality;             //国籍
    private String marriedstatus;           //婚姻状况
    private String nation;                  //民族
    private int age;                     //年龄
    private String booldType;               //血型
    private String rh;                   //rh阴阳性
    private String workdate;             //开始工作日期
    private String jobtypecode;          //职业类别
    private String educationcode;        //学历
    private String degreecode;           //学位
    private String isdel;                //是否删除
    private String status;               //状态
    private String pid;                  //全局ID
    private String idcard;               //身份证
    private String card;				//一卡通
    private List<String> similarity;     //相似人的PID
    private String parent;              //合并后父PID 合并到的数据的PID
	public void setCredentials(List<UtilModel> credentials) {
		this.credentials = credentials;
	}
	public String getPname() {
		return pname;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
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

	public List<UtilModel> getPhone() {
		return phone;
	}
	public void setPhone(List<UtilModel> phone) {
		this.phone = phone;
	}

	public List<UtilModel> getCredentials() {
		return credentials;
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
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getMarriedstatus() {
		return marriedstatus;
	}
	public void setMarriedstatus(String marriedstatus) {
		this.marriedstatus = marriedstatus;
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
	public String getBooldType() {
		return booldType;
	}
	public void setBooldType(String booldType) {
		this.booldType = booldType;
	}
	public String getRh() {
		return rh;
	}
	public void setRh(String rh) {
		this.rh = rh;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
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
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<String> getSimilarity() {
		return similarity;
	}
	public void setSimilarity(List<String> similarity) {
		this.similarity = similarity;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
    
}
