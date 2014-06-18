package com.livechain.pid.rest.model;

import java.util.List;

import org.spring.converter.CallBack;

import com.livechain.mybatis.model.Person;

//获取注销个人信息列表出参
public class GetlockpersonsOut implements CallBack{
	private List<Person> datalist;//返回数据集
	private int count;//数据总条数
	private String ret;//返回状态代码
	private String title;//标题
	private String msg;//消息
	private String callback;
	@Override
	public String getCallback() {
		return  callback;
	}
	@Override
	public void setCallback(String callback) {
		this.callback=callback;
	}
	public List<Person> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<Person> datalist) {
		this.datalist = datalist;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
