package com.livechain.pid.rest.model;

//注销个人信息-入参
public class PersonlockIn {
	private String pid;//个人全局索引
	private String callback;//调用客户端类型
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	
}

