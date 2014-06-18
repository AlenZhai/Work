package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.MergerpersonsIn;
import com.livechain.pid.rest.model.MergerpersonsOut;
import com.livechain.pid.rest.service.MergerpersonsService;


/**
 * 相似人员信息合并
 * @author liuw
 */

@Controller
public class MergerpersonsController {
	@Autowired
	public MergerpersonsService mservice;
	
//////////////////////////@ResponseBody ////////////////////////
	@RequestMapping(method=RequestMethod.GET, value="/mergerpersons/")
	public @ResponseBody MergerpersonsOut mergerpersons(
		   @RequestParam(value="callback",required=false) String callBack,
		   @RequestParam(value="subpids",required=false) String subpids,
		   @RequestParam(value="pid",required=false) String pid){
			
		MergerpersonsIn mi = new MergerpersonsIn();
		mi.setPid(pid);
		mi.setSubpids(subpids);
		mi.setCallback(callBack);
			return mservice.mergerpersons(mi);
	}
	
}
