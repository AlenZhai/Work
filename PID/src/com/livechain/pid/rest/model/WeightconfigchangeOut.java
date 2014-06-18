package com.livechain.pid.rest.model;

import org.spring.converter.CallBack;

//修改权重比配置--出参
public class WeightconfigchangeOut implements CallBack{
	private String ret;//返回状态代码
	private String title;//标题
	private String msg;//消息
	private String callback;
	
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
	@Override
	public String getCallback() {
		return callback;
	}
	@Override
	public void setCallback(String callback) {
		this.callback=callback;
	}
	
}
