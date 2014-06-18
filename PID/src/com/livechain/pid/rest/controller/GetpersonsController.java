package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.GetpersonsIn;
import com.livechain.pid.rest.model.GetpersonsOut;
import com.livechain.pid.rest.service.GetpersonsServiceImpl;

//获取个人信息列表 方法
@Controller
public class GetpersonsController {
	@Autowired
	public GetpersonsServiceImpl getpersonsservice;
	@RequestMapping(method=RequestMethod.GET, value="/getpersons/")
	public @ResponseBody GetpersonsOut getpersons(
		   @RequestParam(value="callback",required=false) String callback,
		   @RequestParam(value="pagesize",required=false) String pagesize,
		   @RequestParam(value="start",required=false) String start,
		   @RequestParam(value="pname",required=false) String pname,
		   @RequestParam(value="gender",required=false) String gender,
		   @RequestParam(value="birthday",required=false) String birthday,
		   @RequestParam(value="idcard",required=false) String idcard,
		   @RequestParam(value="card",required=false) String card
		) 
	{
		GetpersonsIn in = new GetpersonsIn();
		in.setCallback(callback);
		in.setStart(start);
		in.setPagesize(pagesize);
		in.setCard(card);
		in.setGender(gender);
		in.setIdcard(idcard);
		in.setPname(pname);
		in.setBirthday(birthday);
		return getpersonsservice.getpersonslist(in);
	}
		
	
}
