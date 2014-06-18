package com.livechain.pid.handler;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.livechain.pid.dao.MongoDaoSupportImpl;
import com.livechain.pid.exception.ExceptionManagerImpl;

public class HandlerTest {
	public static void main(String[] arg)
	{
		GETPIDDataHandlerImpl getpid=new GETPIDDataHandlerImpl();
		MongoDaoSupportImpl dao=new MongoDaoSupportImpl();
		dao.setTable("personinfo");
		getpid.setPidDao(dao);
		getpid.setExceptionManager(new ExceptionManagerImpl());
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		json.put("pname", "zhang");
		json.put("gender", "2");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
		json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		idcard.put("type", "01");
		idcard.put("number", "421857423");
		
		credentials.put(idcard);
		json.put("credentials", credentials); //证件
		getpid.handlerReq(json, rtnParams, null);
	}

	/*public static void main(String[] args)
	{
		MongoDaoSupportImpl mongo=new MongoDaoSupportImpl();
		GETPIDDataHandlerImpl regpid=new GETPIDDataHandlerImpl();
		//REGPIDDataHandlerImpl regpid=new REGPIDDataHandlerImpl();
		mongo.setTable("personinfo");
		regpid.setPidDao(mongo);
		regpid.setExceptionManager(new ExceptionManagerImpl());
		JSONObject params=new JSONObject();
		JSONObject rtn=new JSONObject();
		init(params);
	    regpid.handlerReq(params, rtn, null);
	    System.out.println("ssss:"+rtn);
	   // System.out.println("back response:"+rtn.toString());
		//System.out.println("flag:"+flag);
	}*/
	public static void initquery(JSONObject json)
	{
		
	}
	public static void init(JSONObject json)
	{
		//json.put("pname", "zhang");
		//json.put("gender", "2");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
		json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		idcard.put("type", "01");
		idcard.put("number", "421857423");
		
		credentials.put(idcard);
		json.put("credentials", credentials); //证件
		/*json.put("nickname", "alenzhai");
		json.put("registereType", "1");//户籍类型代码
		json.put("addrProvince", "辽宁省"); 
		json.put("addrCity", "大连市");
		json.put("addrcounty", "高新园区");
		json.put("addrtown", "火炬路");
		json.put("addrvillage", "2");
		json.put("addrhouseid", "32号");
		JSONArray phones=new JSONArray();
		JSONObject phone=new JSONObject();
		phone.put("type", "01");
		phone.put("number", "12548754");
		phones.put(phone);
		json.put("phones", phones);
		json.put("nationality", "1");
		json.put("marriedstatus", "1");
		json.put("nation", "1");
		json.put("age", "22");
		json.put("booldType", "2");
		json.put("rh", "2");
		Date workdate = new Date();
		workdate.setYear(1992);
		workdate.setMonth(3);
		workdate.setDate(11);
		json.put("workdate", workdate);
		json.put("jobtypecode", "2");
		json.put("educationcode", "2");
		json.put("degreecode", "2");
		json.put("orgcode", "2202120");
		json.put("table","personinfo");*/
		//json.put("PID", UUID.randomUUID().toString().replace("-", ""));
		//json.put("action", "REGPID");
	}
}
