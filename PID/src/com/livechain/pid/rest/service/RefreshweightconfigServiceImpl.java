package com.livechain.pid.rest.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;
//import com.livechain.pid.rest.model.RefreshweightconfigOut;
import com.livechain.pid.rest.model.RefreshweightconfigOut;
import com.livechain.pid.weight.WeightManger;

//刷新内存中的权重配置
@Component
public class RefreshweightconfigServiceImpl {

	private WeightManger weightManger;
	
  public WeightManger getWeightManger() {
		return weightManger;
	}
 @Resource
	public void setWeightManger(WeightManger weightManger) {
		this.weightManger = weightManger;
	}

  public  RefreshweightconfigOut refreshweightconfig(){

	  RefreshweightconfigOut out= new RefreshweightconfigOut();
	  out.setTitle("刷新内存中的权重配置");
	  weightManger.refresh();
	  out.setRet("0");
	  out.setMsg("正常执行");
	  return out;
	  
  }

public WeightManger getWeightMgr() {
	return weightManger;

	   
   }
@Resource(name="weightManager")
public void setWeightMgr(WeightManger weightMgr) {
	this.weightManger = weightMgr;
	
}
}
