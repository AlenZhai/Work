package com.livechain.pid.rest.model;

import org.spring.converter.CallBack;

public class MergerpersonsOut implements CallBack{
	
	private String ret;
	private String title;
	private String msg;
	private String callback;
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
}
