package com.livechain.pid.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUtil {
	public static DataHandler getHandler(String beanName,String xmlPath)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		DataHandler handler=(DataHandler)applicationContext.getBean(beanName);
		return handler;
	}
	public static JSONObject getJsonParameter(String jsonPath)
	{
		File f=new File(jsonPath);
		String json="";
		try {
			BufferedReader  s=new  BufferedReader(new FileReader(f));
			try {
				String line="";
				while((line=s.readLine())!=null)
				{
					json+=line;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONObject(json); 
	}

}
