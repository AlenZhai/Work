package com.livechain.pid.validater;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.livechain.pid.model.Field;
import com.livechain.pid.util.Properties;

public class ValidatonLoaderImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLoader() {
		//fail("Not yet implemented");
		ValidatonLoaderImpl vali=new ValidatonLoaderImpl();
		DataValidaterImpl data=new DataValidaterImpl();
		Properties proes =new Properties(DataValidaterImpl.class,"conf.properties");
		try {
			vali.loader(data, proes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(true, data.getFields().keySet().contains("pname"));
		Assert.assertEquals(true, data.getFields().keySet().contains("credentials"));
		Field f=data.getFields().get("credentials");
		Assert.assertEquals(true, f.getTypes().containsKey("01")&&f.getType("01").getRegx()!=null);
		System.out.println(f.getType("01").getRegx());
		Map<String,Field> fields=data.getFields();
		for(String k:fields.keySet())
		{
			System.out.println(k);
		}
	    //System.out.println(data.getFields());
	}

}
