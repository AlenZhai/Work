package com.livechain.pid.validater;

import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataValidaterImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testRefresh() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		//fail("Not yet implemented");
	}

	@Test
	public void testHasData() {
		//fail("Not yet implemented");
	}
    /**
     * 身份证不合格
     */
	@Test
	public void testValidate() {
		//fail("Not yet implemented");
		DataValidaterImpl valider=new DataValidaterImpl();
		valider.setLoader(new ValidatonLoaderImpl());
		valider.init();
		//System.out.println(valider.getLastModify());
		valider.refresh();
		//System.out.println(valider.getLastModify());
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
		JSONObject idcard1=new JSONObject();
		idcard.put("type", "01");
		idcard.put("number", "421857423");
		idcard1.put("type", "02");
		idcard1.put("number", "421857423");
		credentials.put(idcard);
		credentials.put(idcard1);
		json.put("credentials", credentials);
		String action = "REGPID";
		Assert.assertEquals(true, valider.validate(json, action,new JSONObject()));
		JSONArray arr=json.getJSONArray("credentials");
		Assert.assertEquals(1, arr.length());
		for(int i=0;i<arr.length();i++)
		{
			if(arr.getJSONObject(i).getString("type").equals("01"))
			{
				fail("Not remove dry data");
			}
		}
		System.out.println(json);
		
	}
	 /**
     * 性别和身份证都不合格
     */
	@Test
	public void testValidate1() {
		//fail("Not yet implemented");
		DataValidaterImpl valider=new DataValidaterImpl();
		valider.setLoader(new ValidatonLoaderImpl());
		valider.init();
		//System.out.println(valider.getLastModify());
		valider.refresh();
		//System.out.println(valider.getLastModify());
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		json.put("pname", "zhang");
		json.put("gender", "3");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
		json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		JSONObject idcard1=new JSONObject();
		idcard.put("type", "01");
		idcard.put("number", "421857423");
		idcard1.put("type", "02");
		idcard1.put("number", "421857423");
		credentials.put(idcard);
		credentials.put(idcard1);
		json.put("credentials", credentials);
		String action = "REGPID";
		Assert.assertEquals(false, valider.validate(json, action,new JSONObject()));
		JSONArray arr=json.getJSONArray("credentials");
		Assert.assertEquals(1, arr.length());
		Assert.assertEquals(false, json.keySet().contains("gender"));
		for(int i=0;i<arr.length();i++)
		{
			if(arr.getJSONObject(i).getString("type").equals("01"))
			{
				fail("Not remove dry data");
			}
		}
		System.out.println(json);
		
	}
	/**
     * 数据清洗
     */
	@Test
	public void testValidate2() {
		//fail("Not yet implemented");
		DataValidaterImpl valider=new DataValidaterImpl();
		valider.setLoader(new ValidatonLoaderImpl());
		valider.init();
		//System.out.println(valider.getLastModify());
		valider.refresh();
		//System.out.println(valider.getLastModify());
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		json.put("pname", "翟   磊");
		json.put("gender", "3");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
		json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		JSONObject idcard1=new JSONObject();
		idcard.put("type", "01");
		idcard.put("number", "421857423");
		idcard1.put("type", "02");
		idcard1.put("number", "421857423");
		credentials.put(idcard);
		credentials.put(idcard1);
		json.put("credentials", credentials);
		String action = "REGPID";
		Assert.assertEquals(false, valider.validate(json, action,new JSONObject()));
		JSONArray arr=json.getJSONArray("credentials");
		Assert.assertEquals(1, arr.length());
		Assert.assertEquals(false, json.keySet().contains("gender"));
		for(int i=0;i<arr.length();i++)
		{
			if(arr.getJSONObject(i).getString("type").equals("01"))
			{
				fail("Not remove dry data");
			}
		}
		System.out.println(json);
		
	}
	/**
     * 15位身份证
     */
	@Test
	public void testValidate3() {
		//fail("Not yet implemented");
		DataValidaterImpl valider=new DataValidaterImpl();
		valider.setLoader(new ValidatonLoaderImpl());
		valider.init();
		//System.out.println(valider.getLastModify());
		valider.refresh();
		//System.out.println(valider.getLastModify());
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		json.put("pname", "翟%$#磊");
		json.put("gender", "1");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
		json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		JSONObject idcard1=new JSONObject();
		idcard.put("type", "01");
		idcard.put("number", "410727870720261");
		idcard1.put("type", "02");
		idcard1.put("number", "421857423");
		credentials.put(idcard);
		credentials.put(idcard1);
		json.put("credentials", credentials);
		String action = "REGPID";
		Assert.assertEquals(true, valider.validate(json, action,new JSONObject()));
		JSONArray arr=json.getJSONArray("credentials");
		Assert.assertEquals(2, arr.length());
		Assert.assertEquals(true, json.keySet().contains("gender"));
		
		System.out.println(json);
		
	}
}
