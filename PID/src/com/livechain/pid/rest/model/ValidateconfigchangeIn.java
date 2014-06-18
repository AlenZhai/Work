package com.livechain.pid.rest.model;

import com.livechain.pid.model.Validation;


//修改数据校验配置--入参
public class ValidateconfigchangeIn {
	private Validation validation;//数据校验数据对象
    private String callback;//调用客户端类型
	public Validation getValidation() {
		return validation;
	}
	public void setValidation(Validation validation) {
		this.validation = validation;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
    
}
