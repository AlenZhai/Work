package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.PersonlockIn;
import com.livechain.pid.rest.model.PersonlockOut;
import com.livechain.pid.rest.service.PersonlockServiceImpl;

//注销个人消息
@Controller
public class PersonlockController {
	@Autowired
	 public PersonlockServiceImpl personlockservice;
	 @RequestMapping(method=RequestMethod.GET, value="/personlock/")
	 public @ResponseBody PersonlockOut personlock(
		   @RequestParam(value="callback",required=false) String callback,
		   @RequestParam(value="pid",required=false) String pid
		) 
	{
		PersonlockIn personlockIn = new PersonlockIn();
		personlockIn.setCallback(callback);
		personlockIn.setPid(pid);
		return personlockservice.personlock(personlockIn);
	}
}
