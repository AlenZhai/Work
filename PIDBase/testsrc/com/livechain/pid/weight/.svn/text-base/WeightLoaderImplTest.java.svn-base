package com.livechain.pid.weight;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.livechain.pid.model.Field;
import com.livechain.pid.util.Properties;
import com.livechain.pid.util.SystemConfig;

public class WeightLoaderImplTest {
    private static  WeightLoaderImpl weightLoader=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		weightLoader=new WeightLoaderImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLoader() {
		Properties proes=new Properties(WeightMangerImpl.class,"conf.properties");
		URL url = WeightLoaderImpl.class.getClassLoader().getResource(proes.getProperty("weightpath")); //
		//System.out.println(url);
		//System.out.println(proes.getProperty("weightpath"));
		WeightMangerImpl newInstance=new WeightMangerImpl();
		new SystemConfig().init();
		newInstance.init();
		try {
			weightLoader.loader(newInstance, proes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(Float.valueOf("70").floatValue(),newInstance.getMax());
		assertEquals(Float.valueOf("30").floatValue(),newInstance.getMin());
		assertEquals(true,newInstance.getFields().containsKey("pname"));
		assertEquals(true,newInstance.getFields().containsKey("gender"));
		assertEquals(true,newInstance.getFields().containsKey("birthday"));
		assertEquals(true,newInstance.getFields().containsKey("credentials"));
		assertEquals(true,newInstance.getFields().containsKey("booldtype"));
		assertEquals(true,newInstance.getFields().containsKey("email"));
		Field f=(Field)newInstance.getFields().get("credentials");
		assertEquals(true,f.getTypes().containsKey("idcard"));
		assertEquals(true,f.getTypes().containsKey("idcard2"));
		//fail("Not yet implemented");
	}

	@Test
	public void testParse() {
		//fail("Not yet implemented");
	}

}
