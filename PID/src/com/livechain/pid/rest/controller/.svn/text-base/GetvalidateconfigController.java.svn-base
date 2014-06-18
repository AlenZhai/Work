package com.livechain.pid.rest.controller;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.GetvalidateconfigIn;
import com.livechain.pid.rest.model.GetvalidateconfigOut;
import com.livechain.pid.rest.service.GetvalidateconfigServiceImpl;

//获取数据校验配置
@Controller
public class GetvalidateconfigController {
	@Autowired
	public GetvalidateconfigServiceImpl getvalidateconfigservice;
	@RequestMapping(method=RequestMethod.GET, value="/getvalidateconfig/")
	public @ResponseBody GetvalidateconfigOut getvalidateconfig(			
		   @RequestParam(value="callback",required=false) String callback
		   ) throws DocumentException
		{
			GetvalidateconfigIn in = new GetvalidateconfigIn();
			in.setCallback(callback);
			return getvalidateconfigservice.getvalidateconfig(in);
		}
	}

