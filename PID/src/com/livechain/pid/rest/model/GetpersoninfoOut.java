package com.livechain.pid.rest.model;

import org.spring.converter.CallBack;

import com.livechain.mybatis.model.Person;

//获取个人信息--出参
public class GetpersoninfoOut implements CallBack{
	private Person person;//对象
	private String ret;//返回状态代码
	private String title;//标题
	private String msg;//信息
	private String callback;
	@Override
	public String getCallback() {
		return callback;
	}
	@Override
	public void setCallback(String callback) {
		this.callback=callback;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
