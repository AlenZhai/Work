package com.livechain.pid.rest.model;

import org.spring.converter.CallBack;

import com.livechain.pid.model.Validation;

//获取数据校验配置--出参
public class GetvalidateconfigOut implements CallBack{
	private String ret;//返回状态代码
	private String title;//标题
	private String msg;//信息
	private Validation validation;//数据校验数据对象
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
