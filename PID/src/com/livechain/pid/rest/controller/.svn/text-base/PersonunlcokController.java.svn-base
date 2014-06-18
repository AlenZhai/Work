package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.PersonunlockIn;
import com.livechain.pid.rest.model.PersonunlockOut;
import com.livechain.pid.rest.service.PersonunlockServiceImpl;

@Controller
public class PersonunlcokController {
//恢复个人信息
	@Autowired
	public PersonunlockServiceImpl personunlockservice;
	@RequestMapping(method=RequestMethod.GET, value="/personunlock/")
	public @ResponseBody PersonunlockOut personunlock(
		   @RequestParam(value="callback",required=false) String callback,
		   @RequestParam(value="pid",required=false) String pid
		) 
	{
		PersonunlockIn in = new PersonunlockIn();
		in.setCallback(callback);
		in.setPid(pid);
		return personunlockservice.personunlock(in);
	}
	
}
