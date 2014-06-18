package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.GetsimilaritbypidIn;
import com.livechain.pid.rest.model.GetsimilaritbypidOut;
import com.livechain.pid.rest.service.GetsimilaritbypidService;

/**
 * 获取对应相似人员
 * @author liuw
 */
@Controller
public class GetsimilaritbypidController {
	@Autowired
	public  GetsimilaritbypidService getsimilaritbypidservice;
	
//////////////////////////@ResponseBody ////////////////////////
	@RequestMapping(method=RequestMethod.GET, value="/getsimilaritbypid/")
	public @ResponseBody GetsimilaritbypidOut getsimilaritbypid(
			@RequestParam(value="callback",required=false) String callBack,
			@RequestParam(value="pid",required=true) String pid
			){
		GetsimilaritbypidIn gin = new GetsimilaritbypidIn();   
		gin.setPid(pid);
		gin.setCallback(callBack);
		return getsimilaritbypidservice.getsimilaritbypid(gin);
			    
}
}