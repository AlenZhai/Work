package com.livechain.pid.weight;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.livechain.pid.model.Field;
import com.livechain.pid.model.WeightResult;

public class WeightMangerImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testNewInstance() {
		WeightMangerImpl manger=new WeightMangerImpl();
		assertNotNull(manger);
		//fail("Not yet implemented");
	}

	@Test
	public void testRefresh() {
		/*WeightMangerImpl manger1=new WeightMangerImpl();
		//WeightMangerImpl manger2=WeightMangerImpl.newInstance();
		
		assertEquals(true,manger1==manger2);*/
		//fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		WeightMangerImpl manger=new WeightMangerImpl();
		manger.setLoader(new WeightLoaderImpl());
		manger.init();
		assertNotNull(manger.getLastModify());
		assertNotNull(manger.getFields());
		assertEquals(Float.valueOf("70").floatValue(),manger.getMax());
		assertEquals(Float.valueOf("30").floatValue(),manger.getMin());
		assertEquals(true,manger.getFields().containsKey("pname"));
		assertEquals(true,manger.getFields().containsKey("gender"));
		assertEquals(true,manger.getFields().containsKey("birthday"));
		assertEquals(true,manger.getFields().containsKey("credentials"));
		assertEquals(true,manger.getFields().containsKey("booldtype"));
		assertEquals(true,manger.getFields().containsKey("email"));
		Field f=(Field)manger.getFields().get("credentials");
		Field unif=manger.getUniquefields().get("credentials");
		assertEquals(false,f.getTypes().containsKey("01"));
		assertEquals(false,f.getTypes().containsKey("02"));
		assertEquals(true,unif.getTypes().containsKey("01"));
		long last=manger.getLastModify();
		manger.refresh();
		long per=manger.getLastModify();
		//System.out.println(f.getTypes());
		assertEquals(true,last==per);
		//fail("Not yet implemented");
	}
	@Test
	public void testGetMaxWeightObj3() {
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		//json.put("pname", "zhang");
		//json.put("gender", "2");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
		//json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcard.put("type", "02");
		idcard.put("number", "421857423");
		credentials.put(idcard);
		json.put("credentials", credentials); //证件
		/*json.put("email", "zhailei@sina.com");
		json.put("nickname", "alen");
		json.put("addrprovince", "1");
		json.put("addrcity", "1");
		json.put("addrcounty", "1");
		json.put("addrtown", "1");
		json.put("addrvillage", "1");
		json.put("addrhouseid", "1");
		JSONObject phone=new JSONObject();
		JSONObject phone1=new JSONObject();
		JSONArray phones=new JSONArray();
		
		phone.put("type", "01");
		phone.put("number", "1346528457");
		phone1.put("type", "02");
		phone1.put("number", "1346528457");
		phones.put(phone);
		phones.put(phone1);
		json.put("phones", phones);
		json.put("nationality", "1");
		json.put("marriedstatus", "1");
		json.put("nation", "1");
		json.put("age", "1");
		json.put("booldtype", "1");
		json.put("rh", "1");
		Date workdate=new Date();
		workdate.setYear(1999);
		workdate.setMonth(5);
		workdate.setDate(25);
		json.put("workdate", workdate);
		json.put("jobtypecode", "1");
		json.put("educationcode", "1");
		json.put("degreecode", "1");
		json.put("orgcodes", "1");*/
		//================================================================
		JSONObject json1=new JSONObject();
		//JSONObject rtnParams =new JSONObject();
		json1.put("pname", "zhang");
		json1.put("gender", "2");
		Date birthdayt=new Date();
		birthdayt.setYear(1987);
		birthdayt.setMonth(5);
		birthdayt.setDate(25);
		json1.put("birthday", birthdayt);
		json1.put("email", "zhailei@sina.com");
		JSONArray credentialst=new JSONArray();
		JSONObject idcardt=new JSONObject();
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcardt.put("type", "02");
		idcardt.put("number", "421857423");
		credentialst.put(idcardt);
		json1.put("credentials", credentialst); //证件
		json1.put("email", "zhailei@sina.com");
		json1.put("nickname", "alen");
		json1.put("addrprovince", "1");
		json1.put("addrcity", "1");
		json1.put("addrcounty", "1");
		json1.put("addrtown", "1");
		json1.put("addrvillage", "1");
		json1.put("addrhouseid", "1");
		JSONObject phonet=new JSONObject();
		JSONObject phonet1=new JSONObject();
		JSONArray phonest=new JSONArray();
		
		phonet.put("type", "01");
		phonet.put("number", "1346528457");
		phonet1.put("type", "02");
		phonet1.put("number", "1346528457");
		phonest.put(phonet);
		phonest.put(phonet1);
		json1.put("phones", phonest);
		json1.put("nationality", "1");
		json1.put("marriedstatus", "1");
		json1.put("nation", "1");
		json1.put("age", "1");
		json1.put("booldtype", "1");
		json1.put("rh", "1");
		Date workdatet=new Date();
		workdatet.setYear(1999);
		workdatet.setMonth(5);
		workdatet.setDate(25);
		json1.put("workdate", workdatet);
		json1.put("jobtypecode", "1");
		json1.put("educationcode", "1");
		json1.put("degreecode", "1");
		json1.put("orgcodes", "1");
		json1.put("PID", "123456789");
		WeightMangerImpl manger=new WeightMangerImpl();
		manger.setLoader(new WeightLoaderImpl());
		manger.init();
		List srclist=new ArrayList();
		srclist.add(json1);
		//srclist.add(json1);
		assertEquals(100F,manger.getWeight(json, json1));
		WeightResult result=(WeightResult)manger.getMaxWeightObj(json, srclist);
		assertEquals(null,result.getListPid());
		assertEquals("123456789",result.getPid());
		//assertEquals(0,result.getListPid().size());
		//fail("Not yet implemented");
	}
	@Test
	public void testGetMaxWeightObj2() {
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		//json.put("pname", "zhang");
		//json.put("gender", "2");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
	/*	json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcard.put("type", "02");
		idcard.put("number", "421857423");
		credentials.put(idcard);
		//json.put("credentials", credentials); //证件
		json.put("email", "zhailei@sina.com");
		json.put("nickname", "alen");
		json.put("addrprovince", "1");
		json.put("addrcity", "1");
		json.put("addrcounty", "1");
		json.put("addrtown", "1");
		json.put("addrvillage", "1");
		json.put("addrhouseid", "1");
		JSONObject phone=new JSONObject();
		JSONObject phone1=new JSONObject();
		JSONArray phones=new JSONArray();
		
		phone.put("type", "01");
		phone.put("number", "1346528457");
		phone1.put("type", "02");
		phone1.put("number", "1346528457");
		phones.put(phone);
		phones.put(phone1);
		json.put("phones", phones);
		json.put("nationality", "1");
		json.put("marriedstatus", "1");
		json.put("nation", "1");
		json.put("age", "1");
		json.put("booldtype", "1");
		json.put("rh", "1");
		Date workdate=new Date();
		workdate.setYear(1999);
		workdate.setMonth(5);
		workdate.setDate(25);
		json.put("workdate", workdate);
		json.put("jobtypecode", "1");
		json.put("educationcode", "1");
		json.put("degreecode", "1");
		json.put("orgcodes", "1");*/
		//================================================================
		JSONObject json1=new JSONObject();
		//JSONObject rtnParams =new JSONObject();
		json1.put("pname", "zhang");
		json1.put("gender", "2");
		Date birthdayt=new Date();
		birthdayt.setYear(1987);
		birthdayt.setMonth(5);
		birthdayt.setDate(25);
		json1.put("birthday", birthdayt);
		json1.put("email", "zhailei@sina.com");
		JSONArray credentialst=new JSONArray();
		JSONObject idcardt=new JSONObject();
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcardt.put("type", "02");
		idcardt.put("number", "421857423");
		credentialst.put(idcardt);
		//json1.put("credentials", credentialst); //证件
		json1.put("email", "zhailei@sina.com");
		json1.put("nickname", "alen");
		json1.put("addrprovince", "1");
		json1.put("addrcity", "1");
		json1.put("addrcounty", "1");
		json1.put("addrtown", "1");
		json1.put("addrvillage", "1");
		json1.put("addrhouseid", "1");
		JSONObject phonet=new JSONObject();
		JSONObject phonet1=new JSONObject();
		JSONArray phonest=new JSONArray();
		
		phonet.put("type", "01");
		phonet.put("number", "1346528457");
		phonet1.put("type", "02");
		phonet1.put("number", "1346528457");
		phonest.put(phonet);
		phonest.put(phonet1);
		json1.put("phones", phonest);
		json1.put("nationality", "1");
		json1.put("marriedstatus", "1");
		json1.put("nation", "1");
		json1.put("age", "1");
		json1.put("booldtype", "1");
		json1.put("rh", "1");
		Date workdatet=new Date();
		workdatet.setYear(1999);
		workdatet.setMonth(5);
		workdatet.setDate(25);
		json1.put("workdate", workdatet);
		json1.put("jobtypecode", "1");
		json1.put("educationcode", "1");
		json1.put("degreecode", "1");
		json1.put("orgcodes", "1");
		json1.put("PID", "123456789");
		WeightMangerImpl manger=new WeightMangerImpl();
		manger.setLoader(new WeightLoaderImpl());
		manger.init();
		List srclist=new ArrayList();
		srclist.add(json1);
		//srclist.add(json1);
		assertEquals(30F,manger.getWeight(json, json));
		WeightResult result=(WeightResult)manger.getMaxWeightObj(json, srclist);
		assertEquals(null,result.getListPid());
		assertEquals(null,result.getPid());
		//assertEquals(0,result.getListPid().size());
		//fail("Not yet implemented");
	}
	@Test
	public void testGetMaxWeightObj1() {
		JSONObject json=new JSONObject();
		JSONObject rtnParams =new JSONObject();
		json.put("pname", "zhang");
		json.put("gender", "2");
		Date birthday=new Date();
		birthday.setYear(1987);
		birthday.setMonth(5);
		birthday.setDate(25);
		json.put("birthday", birthday);
	/*	json.put("email", "zhailei@sina.com");
		JSONArray credentials=new JSONArray();
		JSONObject idcard=new JSONObject();
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcard.put("type", "02");
		idcard.put("number", "421857423");
		credentials.put(idcard);
		//json.put("credentials", credentials); //证件
		json.put("email", "zhailei@sina.com");
		json.put("nickname", "alen");
		json.put("addrprovince", "1");
		json.put("addrcity", "1");
		json.put("addrcounty", "1");
		json.put("addrtown", "1");
		json.put("addrvillage", "1");
		json.put("addrhouseid", "1");
		JSONObject phone=new JSONObject();
		JSONObject phone1=new JSONObject();
		JSONArray phones=new JSONArray();
		
		phone.put("type", "01");
		phone.put("number", "1346528457");
		phone1.put("type", "02");
		phone1.put("number", "1346528457");
		phones.put(phone);
		phones.put(phone1);
		json.put("phones", phones);
		json.put("nationality", "1");
		json.put("marriedstatus", "1");
		json.put("nation", "1");
		json.put("age", "1");
		json.put("booldtype", "1");
		json.put("rh", "1");
		Date workdate=new Date();
		workdate.setYear(1999);
		workdate.setMonth(5);
		workdate.setDate(25);
		json.put("workdate", workdate);
		json.put("jobtypecode", "1");
		json.put("educationcode", "1");
		json.put("degreecode", "1");
		json.put("orgcodes", "1");*/
		//================================================================
		JSONObject json1=new JSONObject();
		//JSONObject rtnParams =new JSONObject();
		json1.put("pname", "zhang");
		json1.put("gender", "2");
		Date birthdayt=new Date();
		birthdayt.setYear(1987);
		birthdayt.setMonth(5);
		birthdayt.setDate(25);
		json1.put("birthday", birthdayt);
		json1.put("email", "zhailei@sina.com");
		JSONArray credentialst=new JSONArray();
		JSONObject idcardt=new JSONObject();
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcardt.put("type", "02");
		idcardt.put("number", "421857423");
		credentialst.put(idcardt);
		//json1.put("credentials", credentialst); //证件
		json1.put("email", "zhailei@sina.com");
		json1.put("nickname", "alen");
		json1.put("addrprovince", "1");
		json1.put("addrcity", "1");
		json1.put("addrcounty", "1");
		json1.put("addrtown", "1");
		json1.put("addrvillage", "1");
		json1.put("addrhouseid", "1");
		JSONObject phonet=new JSONObject();
		JSONObject phonet1=new JSONObject();
		JSONArray phonest=new JSONArray();
		
		phonet.put("type", "01");
		phonet.put("number", "1346528457");
		phonet1.put("type", "02");
		phonet1.put("number", "1346528457");
		phonest.put(phonet);
		phonest.put(phonet1);
		json1.put("phones", phonest);
		json1.put("nationality", "1");
		json1.put("marriedstatus", "1");
		json1.put("nation", "1");
		json1.put("age", "1");
		json1.put("booldtype", "1");
		json1.put("rh", "1");
		Date workdatet=new Date();
		workdatet.setYear(1999);
		workdatet.setMonth(5);
		workdatet.setDate(25);
		json1.put("workdate", workdatet);
		json1.put("jobtypecode", "1");
		json1.put("educationcode", "1");
		json1.put("degreecode", "1");
		json1.put("orgcodes", "1");
		json1.put("PID", "123456789");
		WeightMangerImpl manger=new WeightMangerImpl();
		manger.setLoader(new WeightLoaderImpl());
		manger.init();
		List srclist=new ArrayList();
		srclist.add(json1);
		//srclist.add(json1);
		assertEquals(80F,manger.getWeight(json, json));
		WeightResult result=(WeightResult)manger.getMaxWeightObj(json, srclist);
		assertEquals(null,result.getListPid());
		assertNotNull(result.getPid());
		//assertEquals(0,result.getListPid().size());
		//fail("Not yet implemented");
	}
	@Test
	public void testGetMaxWeightObj() {
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
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcard.put("type", "02");
		idcard.put("number", "421857423");
		credentials.put(idcard);
		//json.put("credentials", credentials); //证件
		json.put("email", "zhailei@sina.com");
		json.put("nickname", "alen");
		json.put("addrprovince", "1");
		json.put("addrcity", "1");
		json.put("addrcounty", "1");
		json.put("addrtown", "1");
		json.put("addrvillage", "1");
		json.put("addrhouseid", "1");
		JSONObject phone=new JSONObject();
		JSONObject phone1=new JSONObject();
		JSONArray phones=new JSONArray();
		
		phone.put("type", "01");
		phone.put("number", "1346528457");
		phone1.put("type", "02");
		phone1.put("number", "1346528457");
		phones.put(phone);
		phones.put(phone1);
		json.put("phones", phones);
		json.put("nationality", "1");
		json.put("marriedstatus", "1");
		json.put("nation", "1");
		json.put("age", "1");
		json.put("booldtype", "1");
		json.put("rh", "1");
		Date workdate=new Date();
		workdate.setYear(1999);
		workdate.setMonth(5);
		workdate.setDate(25);
		json.put("workdate", workdate);
		json.put("jobtypecode", "1");
		json.put("educationcode", "1");
		json.put("degreecode", "1");
		json.put("orgcodes", "1");
		//================================================================
		JSONObject json1=new JSONObject();
		//JSONObject rtnParams =new JSONObject();
		json1.put("pname", "zhang");
		json1.put("gender", "2");
		Date birthdayt=new Date();
		birthdayt.setYear(1987);
		birthdayt.setMonth(5);
		birthdayt.setDate(25);
		json1.put("birthday", birthdayt);
		json1.put("email", "zhailei@sina.com");
		JSONArray credentialst=new JSONArray();
		JSONObject idcardt=new JSONObject();
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcardt.put("type", "02");
		idcardt.put("number", "421857423");
		credentialst.put(idcard);
		//json1.put("credentials", credentialst); //证件
		json1.put("email", "zhailei@sina.com");
		json1.put("nickname", "alen");
		json1.put("addrprovince", "1");
		json1.put("addrcity", "1");
		json1.put("addrcounty", "1");
		json1.put("addrtown", "1");
		json1.put("addrvillage", "1");
		json1.put("addrhouseid", "1");
		JSONObject phonet=new JSONObject();
		JSONObject phonet1=new JSONObject();
		JSONArray phonest=new JSONArray();
		
		phonet.put("type", "01");
		phonet.put("number", "1346528457");
		phonet1.put("type", "02");
		phonet1.put("number", "1346528457");
		phonest.put(phonet);
		phonest.put(phonet1);
		json1.put("phones", phonest);
		json1.put("nationality", "1");
		json1.put("marriedstatus", "1");
		json1.put("nation", "1");
		json1.put("age", "1");
		json1.put("booldtype", "1");
		json1.put("rh", "1");
		Date workdatet=new Date();
		workdatet.setYear(1999);
		workdatet.setMonth(5);
		workdatet.setDate(25);
		json1.put("workdate", workdatet);
		json1.put("jobtypecode", "1");
		json1.put("educationcode", "1");
		json1.put("degreecode", "1");
		json1.put("orgcodes", "1");
		json1.put("PID", "123456789");
		WeightMangerImpl manger=new WeightMangerImpl();
		manger.setLoader(new WeightLoaderImpl());
		manger.init();
		List srclist=new ArrayList();
		srclist.add(json1);
		srclist.add(json1);
		assertEquals(225F,manger.getWeight(json, json));
		WeightResult result=(WeightResult)manger.getMaxWeightObj(json, srclist);
		assertEquals(null,result.getPid());
		assertNotNull(result.getListPid());
		assertEquals(2,result.getListPid().size());
		//fail("Not yet implemented");
	}

	@Test
	public void testGetWeight() {
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
		//idcard.put("type", "01");
		//idcard.put("number", "421857423");
		idcard.put("type", "02");
		idcard.put("number", "421857423");
		credentials.put(idcard);
		//json.put("credentials", credentials); //证件
		WeightMangerImpl manger=new WeightMangerImpl();
		manger.setLoader(new WeightLoaderImpl());
		manger.init();
		assertEquals(1,manger.getUniquefields().size());
		assertEquals(90F,manger.getWeight(json, json));
		//org.junit.Assert.assertNotNull(rtnParams.get("PID"));
		//fail("Not yet implemented");
	}

}
