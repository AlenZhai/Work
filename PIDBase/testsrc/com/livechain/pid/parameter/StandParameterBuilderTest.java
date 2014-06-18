package com.livechain.pid.parameter;

import static org.junit.Assert.*;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StandParameterBuilderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testBuildAddParams() {
		//fail("Not yet implemented");
	}

	@Test
	public void testBuildDelParams() {
		//fail("Not yet implemented");
	}
	/**
     * 多卡号或身份证情况 
     * 多卡号或身份证的时候 后面的卡号会覆盖前面的值 所以在查询时 卡号只需要一个（身份证号或一卡通号）
     */
	@Test
	public void testBuildQueryParams2() {
		//fail("Not yet implemented");
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
		//=========================================================
		JSONObject query=new JSONObject();
		new StandParameterBuilder().buildQueryParams(json, query);
		System.out.println(query);
		Assert.assertEquals(true, query.keySet().contains("credentials.type"));
		Assert.assertEquals(true, query.keySet().contains("credentials.type"));
		//Assert.assertNotNull(query.get("credentials.type"));
		
	}
	/**
     * 无卡号或身份证情况 
     */
	@Test
	public void testBuildQueryParams1() {
		//fail("Not yet implemented");
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
		/*idcard1.put("type", "02");
		idcard1.put("number", "421857423");*/
		credentials.put(idcard);
		//json.put("credentials", credentials);
		//=========================================================
		JSONObject query=new JSONObject();
		new StandParameterBuilder().buildQueryParams(json, query);
		System.out.println(query);
		Assert.assertEquals(false, query.keySet().contains("credentials.type"));
		Assert.assertEquals(false, query.keySet().contains("credentials.type"));
		//Assert.assertNotNull(query.get("credentials.type"));
		
	}
    /**
     * 全参情况
     */
	@Test
	public void testBuildQueryParams() {
		//fail("Not yet implemented");
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
		/*idcard1.put("type", "02");
		idcard1.put("number", "421857423");*/
		credentials.put(idcard);
		json.put("credentials", credentials);
		//=========================================================
		JSONObject query=new JSONObject();
		new StandParameterBuilder().buildQueryParams(json, query);
		System.out.println(query);
		Assert.assertEquals(true, query.keySet().contains("credentials.type"));
		Assert.assertEquals(true, query.keySet().contains("credentials.type"));
		//Assert.assertNotNull(query.get("credentials.type"));
		
	}
   /**
    * 整合json rtnParams数据 
    * 如果json中有的数据则保持不变
    * 没有的数据用rtnParams 中的数据替换
    */
	@Test
	public void testBuildUpdateParams() {
		//fail("Not yet implemented");
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
		/*idcard1.put("type", "02");
		idcard1.put("number", "421857423");*/
		credentials.put(idcard);
		json.put("credentials", credentials);
		//=========================================================
		rtnParams.put("pname", "li");
		rtnParams.put("gender", "1");
		Date birthdayt=new Date();
		birthdayt.setYear(1987);
		birthdayt.setMonth(5);
		birthdayt.setDate(25);
		rtnParams.put("birthday", birthdayt);
		rtnParams.put("email", "zhai@sina.com");
		JSONArray credentialst=new JSONArray();
		JSONObject idcardt=new JSONObject();
		JSONObject idcardt1=new JSONObject();
		idcardt.put("type", "01");
		idcardt.put("number", "42185");
		idcardt1.put("type", "02");
		idcardt1.put("number", "1857423");
		credentials.put(idcardt);
		credentials.put(idcardt1);
		rtnParams.put("credentials", credentialst);
		new StandParameterBuilder().buildUpdateParams(json, rtnParams, true);
		assertEquals("zhang",json.get("pname"));
		assertEquals("2",json.get("gender"));
		assertEquals("zhailei@sina.com",json.get("email"));
		JSONArray cards=json.getJSONArray("credentials");
		for(int i=0;i<cards.length();i++)
		{
			JSONObject card=cards.getJSONObject(i);
			if(card.get("type").equals("01"))
			{
				assertEquals("421857423",card.get("number"));
			}
			if(card.get("type").equals("02"))
			{
				assertEquals("1857423",card.get("number"));
			}
		}
	}

}
