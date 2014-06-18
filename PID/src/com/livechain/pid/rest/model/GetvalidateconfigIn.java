package com.livechain.pid.rest.model;
//获取数据校验配置--入参
public class GetvalidateconfigIn {
	private String callback;//跨域问题解决

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
}
