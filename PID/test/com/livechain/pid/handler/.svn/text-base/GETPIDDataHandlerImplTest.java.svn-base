package com.livechain.pid.handler;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class GETPIDDataHandlerImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testHandlerReqJSONObjectJSONObjectStandContext() {
		//fail("Not yet implemented");
		DataHandler handler=TestUtil.getHandler("GETPID", "spring/spring-pid.xml");
		JSONObject params=TestUtil.getJsonParameter("E:\\getpid.txt");
		JSONObject rtnParams=new JSONObject();
		handler.handlerReq(params, rtnParams, null);
		System.out.println(rtnParams);
	}

}
