package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.RefreshvalidateconfigOut;

import com.livechain.pid.rest.service.RefreshvalidateconfigServiceImpl;


//刷新内存中数据校验配置
@Controller
public class RefreshvalidateconfigController {
	@Autowired
	public RefreshvalidateconfigServiceImpl refreshvalidateconfigservice;
	@RequestMapping(method=RequestMethod.GET, value="/refreshvalidateconfig/")
	public @ResponseBody RefreshvalidateconfigOut refreshvalidateconfig(
	    ) 
	  {
		return refreshvalidateconfigservice.refreshvalidateconfig();
	}
}
