package com.livechain.pid.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;



/**
 * 加载配置文件
 * @author alenzhai 2013-05-26
 *
 */
public class Properties {
	private static java.util.Properties pros=null;
    public Properties(Class calss,String file)
    {       	
    	URL url=calss.getClassLoader().getResource("./conf/"+file);
    	init(url);
	}  
  private void init(URL url)
  {
	  FileInputStream file=null;
		try {
			file = new FileInputStream(url.getPath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	InputStream in=new BufferedInputStream (file);
	pros=new java.util.Properties();
	try {
			pros.load(in);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  public Set<Object> getKeySet()
  {	 
	  return pros.keySet();
  }
  public String getProperty(String key)
  {  //System.out.println(pros.getProperty(key));
	  
	 return pros.getProperty(key);
  }
  public String getProperty(String key,String defaultValue)
  {
	 return pros.getProperty(key, defaultValue);
  }
}
