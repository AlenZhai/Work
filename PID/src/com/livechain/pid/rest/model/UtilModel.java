package com.livechain.pid.rest.model;

import java.util.Date;

public class UtilModel {
  private String type;
  private String number;
  private Date createDate;
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
}
