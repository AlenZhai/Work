package com.livechain.pid.handler;

import static org.junit.Assert.*;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.livechain.pid.dao.MongoDaoSupportImpl;
import com.livechain.pid.exception.ExceptionManagerImpl;

public class GETPIDDataHandlerImplTest {
     private static GETPIDDataHandlerImpl getpid=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		getpid=new GETPIDDataHandlerImpl();
		MongoDaoSupportImpl dao = new MongoDaoSupportImpl();
		dao.setTable("personinfo");
		getpid.setPidDao(dao);
		getpid.setExceptionManager(new ExceptionManagerImpl());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
    /**
     * 正常参数时
     */
	@Test
	public void testHandlerReq() {
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
		org.junit.Assert.assertNotNull(rtnParams.get("PID"));
		//assertEquals(true,);
		//fail("Not yet implemented");
	}
	/**
	 * 参数未达到 预期个数据 时
	 */
	@Test
	public void testHandlerReq1() {
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
		
		getpid.handlerReq(json, rtnParams, null);
		
		assertEquals(false,rtnParams.keySet().contains("PID"));
		assertEquals(true,rtnParams.keySet().contains("error"));
		assertNotNull(rtnParams.get("error"));
		
	}
	/**
	 * 参数为空时
	 */
	@Test
	public void testHandlerReq2() {
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		
		getpid.handlerReq(json, rtnParams, null);
		//org.junit.Assert.assertNull(rtnParams.get("PID"));
		assertEquals(false,rtnParams.keySet().contains("PID"));
		assertEquals(true,rtnParams.keySet().contains("error"));
		assertNotNull(rtnParams.get("error"));
		//fail("Not yet implemented");
	}
	/**
	 * 参数为 null 时
	 */
	@Test
	public void testHandlerReq3() {
		JSONObject json=null;
		JSONObject rtnParams =new JSONObject();
		
		getpid.handlerReq(json, rtnParams, null);
		
		assertEquals(false,rtnParams.keySet().contains("PID"));
		assertEquals(true,rtnParams.keySet().contains("error"));
		assertNotNull(rtnParams.get("error"));
		
	}
}
