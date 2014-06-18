package com.livechain.pid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.GetlockpersonsIn;
import com.livechain.pid.rest.model.GetlockpersonsOut;
import com.livechain.pid.rest.service.GetlockpersonsServiceImpl;


//获取注销个人信息列表
@Controller
public class GetlockpersonsController {
	@Autowired
	public GetlockpersonsServiceImpl getlockpersonsservice;
	@RequestMapping(method=RequestMethod.GET, value="/getlockpersons/")
	public @ResponseBody GetlockpersonsOut getlockpersons(
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
		GetlockpersonsIn in = new GetlockpersonsIn();
		in.setCallback(callback);
		in.setStart(start);
		in.setPagesize(pagesize);
		in.setCard(card);
		in.setGender(gender);
		in.setIdcard(idcard);
		in.setPname(pname);
		in.setBirthday(birthday);
		return getlockpersonsservice.getlockpersonslist(in);
	}
}
