package com.livechain.pid.dao;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.livechain.pid.PIDGenerateImpl;

public class MongoDaoSupportImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetInfo() {
		//fail("Not yet implemented");
		MongoDaoSupportImpl dao=new MongoDaoSupportImpl();
		dao.setTable("personinfo");
		JSONObject json=new JSONObject();
		JSONObject response=new JSONObject();
		json.put("pname", "zhang");
		json.put("credentials.type", "01");
		json.put("credentials.number", "01");
		dao.getInfo(json, response);
		System.out.println(response);
		for(Object key:response.keySet())
		{
			JSONObject person=response.getJSONObject(key.toString());
			Assert.assertNotNull(person.get("birthday"));
			Assert.assertNotNull(person.get("pname"));
			
		}
		Assert.assertEquals(1,response.length());
		//Assert.assertNotNull(response.get("person"));
	}

	@Test
	public void testSaveInfo() {
		//fail("Not yet implemented");
		/*MongoDaoSupportImpl dao=new MongoDaoSupportImpl();
		dao.setTable("personinfo");
		JSONObject json=new JSONObject();
		JSONObject response=new JSONObject();
		json.put("PID", new PIDGenerateImpl().getPID());
		json.put("status","0");
		json.put("isdel", "1");
		json.put("pname", "alen");
		JSONArray credentials=new JSONArray();
		JSONObject idcard1=new JSONObject();
		idcard1.put("type", "01");
		idcard1.put("number", "123456");
		credentials.put(idcard1);
		JSONObject idcard2=new JSONObject();
		idcard2.put("type", "02");
		idcard2.put("number", "123456");
		credentials.put(idcard2);
		json.put("credentials", credentials);
		dao.saveInfo(json, response);*/
	}

	@Test
	public void testUpdateInfo() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		//fail("Not yet implemented");
	}

}
