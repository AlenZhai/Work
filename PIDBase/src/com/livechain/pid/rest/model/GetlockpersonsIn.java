package com.livechain.pid.rest.model;

public class GetlockpersonsIn {
//获取注销个人信息列表--入参
   private String start;//开始查询记录
   private String pagesize;//单页记录数
   private String callback;//跨域问题解决
   private String pname;//姓名
   private String gender;//性别
   private String birthday;//出生年月
   private String idcard;//身份证
   private String card;//一卡通
   private String isdel="1";//注销的状态
   public String getStart() {
	return start;
   }
   public void setStart(String start) {
	this.start = start;
   }
   public String getPagesize() {
	return pagesize;
   }
   public void setPagesize(String pagesize) {
	this.pagesize = pagesize;
   }
   public String getCallback() {
	return callback;
   }
   public void setCallback(String callback) {
	this.callback = callback;
   }
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
   public String getIsdel() {
	return isdel;
   }
   public void setIsdel(String isdel) {
	this.isdel = isdel;
   }
   
 }
