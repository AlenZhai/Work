package com.livechain.pid.rest.model;

import java.util.List;

import org.spring.converter.CallBack;

import com.livechain.mybatis.model.Person;

public class GetsimilaritbypidOut implements CallBack {
	
		private	List<Person> datalist;
		private	String ret;
		private	String title;
		private	String callback;
	public List<Person> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<Person> list) {
		this.datalist = list;
	}
	@Override
	public String getCallback() {
		return callback;
	}
	@Override
	public void setCallback(String callback) {
		this.callback = callback;
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
	private	String msg;
}

