package com.livechain.pid.handler;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class REGCARDDataHandlerImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testHandlerReqJSONObjectJSONObjectStandContext() {
		//fail("Not yet implemented");
		DataHandler handler=TestUtil.getHandler("REGCARD", "spring/spring-pid.xml");
		JSONObject params=TestUtil.getJsonParameter("E:\\regcard.txt");
		JSONObject rtnParams=new JSONObject();
		handler.handlerReq(params, rtnParams, null);
		System.out.println(rtnParams);
	}

}
