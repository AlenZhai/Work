package com.livechain.pid.rest.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.livechain.pid.rest.model.RefreshvalidateconfigOut;
import com.livechain.pid.validater.DataValidaterImpl;

///刷新内存中数据校验配置
@Component
public class RefreshvalidateconfigServiceImpl {
	private DataValidaterImpl dataValidaterImpl;

	public DataValidaterImpl getDataValidaterImpl() {
		return dataValidaterImpl;
	}

	@Resource(name = "standvalid")
	public void setDataValidaterImpl(DataValidaterImpl dataValidaterImpl) {
		this.dataValidaterImpl = dataValidaterImpl;
	}
	/**
	 * <p>这个方法用来刷新内存中的数据校验配置</p>
	 * @return 执行结果
	 */
	public  RefreshvalidateconfigOut refreshvalidateconfig(){
		  RefreshvalidateconfigOut out= new RefreshvalidateconfigOut();
		  out.setTitle("刷新内存中的数据校验配置");
		  dataValidaterImpl.refresh();
		  out.setRet("0");
		  out.setMsg("正常执行");
		  return out;  
	   }
}
