/**
 * 
 */
package com.livechain.pid.handler;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.dao.SolrDaoSupportImpl;
import com.livechain.pid.handler.REGPIDDataHandlerImpl;
import com.livechain.pid.weight.WeightManger;
import com.livechain.pid.weight.WeightMangerImpl;

/**
 * @author Alen
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring\\spring-pid.xml")
public class REGPIDDataHandlerImplTest{

	/**
	 * @throws java.lang.Exception
	 */
	//public DaoSupport pidDao;
	//@Resource(name="REGPID")
	//DataHandler handler;
	

	/**
	 * Test method for {@link com.livechain.pid.handler.REGPIDDataHandlerImpl#handlerReq(org.json.JSONObject, org.json.JSONObject, com.livechain.pid.util.StandContext)}.
	 */
	@Test
	public void testHandlerReqJSONObjectJSONObjectStandContext() {

		DataHandler handler=TestUtil.getHandler("REGPID", "spring/spring-pid.xml");
		
		JSONObject params=TestUtil.getJsonParameter("E:\\regpid.txt");
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
