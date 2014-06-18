package com.livechain.pid.rest.model;

import com.livechain.pid.model.Weight;

//修改权重比配置--入参
public class WeightconfigchangeIn {
	private Weight weight;//权重比数据对象
    private String callback;//调用客户端类型
	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}
}
