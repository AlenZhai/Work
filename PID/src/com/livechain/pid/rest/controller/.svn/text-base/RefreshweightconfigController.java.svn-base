package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.livechain.pid.rest.model.RefreshweightconfigOut;
import com.livechain.pid.rest.service.RefreshweightconfigServiceImpl;


//刷新内存中的权重配置
@Controller
public class RefreshweightconfigController {
	@Autowired
	public RefreshweightconfigServiceImpl refreshweightconfigservice;
	@RequestMapping(method=RequestMethod.GET, value="/refreshweightconfig/")
	public @ResponseBody RefreshweightconfigOut refreshweightconfig(
	    ) 
	  {
		return refreshweightconfigservice.refreshweightconfig();
	}
}
