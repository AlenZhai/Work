package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.GetempiworkIn;
import com.livechain.pid.rest.model.GetempiworkOut;
import com.livechain.pid.rest.service.GetempiworkService;

/**
 * 获取EMPI注册情况
 * @author liuw
 *
 */
@Controller
public class GetempiworkController {
	@Autowired
	public GetempiworkService getempiworkservice;
	/**
	 * 
	 * @param callBack
	 * @param pagesize
	 * @param start
	 * @param startdate
	 * @param enddate
	 * @return getempiworkservice.getempiwork(gwin)
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getempiwork/")
	public @ResponseBody GetempiworkOut getempiwork(
		   @RequestParam(value="callback",required=false) String callBack,
		   @RequestParam(value="startdate",required=false) String startdate,
		   @RequestParam(value="enddate",required=false) String enddate){
			GetempiworkIn gwin = new GetempiworkIn();
			gwin.setEnddate(enddate);
			gwin.setStartdate(startdate);
			gwin.setCallback(callBack);
			
			return getempiworkservice.getempiwork(gwin);
}
}
