package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.GetpersoninfoIn;
import com.livechain.pid.rest.model.GetpersoninfoOut;
import com.livechain.pid.rest.service.GetpersoninfoServiceImpl;


//获取个人信息方法
@Controller
public class GetpersoninfoController {
	@Autowired
	public GetpersoninfoServiceImpl getpersoninfoservice;
	@RequestMapping(method=RequestMethod.GET, value="/getpersoninfo/")
	public @ResponseBody GetpersoninfoOut getpersoninfo(
		   @RequestParam(value="callback",required=false) String callback,
		   @RequestParam(value="pid",required=false) String pid
		) 
	{
		GetpersoninfoIn getpersoninfoIn = new GetpersoninfoIn();
		getpersoninfoIn.setCallback(callback);
		getpersoninfoIn.setPid(pid);
		return getpersoninfoservice.getpersoninfo(getpersoninfoIn);

	}
}
