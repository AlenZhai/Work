package com.livechain.pid.handler;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class UPDATEDataHandlerImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testHandlerReqJSONObjectJSONObjectStandContext() {
		//fail("Not yet implemented");
		DataHandler handler=TestUtil.getHandler("UPDATE", "spring/spring-pid.xml");
		//fail("Not yet implemented");
		
		
		//System.out.println("json:"+json);
		JSONObject params=TestUtil.getJsonParameter("E:\\update.txt");
		JSONObject rtnParams=new JSONObject();
		if(params.keySet().contains("client"))
		 {
			//client=json.getString("client");
			params.remove("client");
		 }
		if(params.keySet().contains("action"))
		 {
			//action=(String)json.get("action");
			params.remove("action");
		 }
		//JSONObject params=new JSONObject();
		//System.out.println(params);
		handler.handlerReq(params, rtnParams, null);
		assertTrue(rtnParams.keySet().contains("PID"));
		System.out.println(rtnParams);
	}

}
