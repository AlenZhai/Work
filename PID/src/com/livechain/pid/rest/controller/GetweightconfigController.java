package com.livechain.pid.rest.controller;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.livechain.pid.rest.model.GetweightconfigIn;
import com.livechain.pid.rest.model.GetweightconfigOut;
import com.livechain.pid.rest.service.GetweightconfigServiceImpl;


//获取权重比配置
@Controller
public class GetweightconfigController {
	@Autowired
	public GetweightconfigServiceImpl getweightconfigservice;
	@RequestMapping(method=RequestMethod.GET, value="/getweightconfig/")
	public @ResponseBody GetweightconfigOut getweightconfig(			
		   @RequestParam(value="callback",required=false) String callback
		   ) throws DocumentException
		{
			GetweightconfigIn getweightconfigIn = new GetweightconfigIn();
			getweightconfigIn.setCallback(callback);
			return getweightconfigservice.getweightconfig(getweightconfigIn);
		   		}
	}
