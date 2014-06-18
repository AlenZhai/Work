package com.livechain.pid.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.rest.model.GetsimilaritiesIn;
import com.livechain.pid.rest.model.GetsimilaritiesOut;
import com.livechain.pid.rest.service.GetsimilaritiesService;

/**
 * 获取相似人员信息
 * @author liuw
 */
@Controller
public class GetsimilaritiesController {
	@Autowired
	public GetsimilaritiesService service;
	
	/**
	 * 获取相似人员信息
	 * @param callBack
	 * @param start
	 * @param pagesize
	 * @param type
	 * @param param
	 * @return GetsimilaritiesOut
	 */
//////////////////////////@ResponseBody ////////////////////////
	@RequestMapping(method=RequestMethod.GET, value="/getsimilarities/")
	public @ResponseBody GetsimilaritiesOut getsimilarities(
		   @RequestParam(value="callback",required=false) String callBack,
		   @RequestParam(value="start",required=false) String start,
		   @RequestParam(value="pagesize",required=false) String pagesize,
		   @RequestParam(value="gender",required=false) String gender,
		   @RequestParam(value="pname",required=false) String pname,
		   @RequestParam(value="birthday",required=false) String birthday,
		   @RequestParam(value="idcard",required=false) String idcard,
		   @RequestParam(value="card",required=false) String card) {
		
			GetsimilaritiesIn gi = new GetsimilaritiesIn();
			gi.setCallback(callBack);
			gi.setBirthday(birthday);
			gi.setCard(card);
			gi.setGender(gender);
			gi.setIdcard(idcard);
			gi.setPagesize(pagesize);
			gi.setPname(pname);
			gi.setStart(start);
			
			
		return service.getsimilarities(gi);
			
	}
}