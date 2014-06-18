package com.livechain.pid.rest.model;

import org.spring.converter.CallBack;

public class PersonunlockOut implements CallBack{
	//恢复个人信息---出差
	private String ret;//返回状态代码
	private String title;//标题
	private String msg;//消息
	private String callback;//跨域
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
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	
}
