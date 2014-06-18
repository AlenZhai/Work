package com.livechain.pid.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载系统配置信息
 * @author alenzhai 2013-06-04
 *
 */
public class SystemConfig {
       private static Map<String,Object> config=new HashMap<String,Object>();

	public static Map<String, Object> getConfig() {
		return config;
	}

	public static void setConfig(Map<String, Object> config) {
		SystemConfig.config = config;
	}
     public Object getConfig(String key)
     {
    	return config.get(key);
     }
     public void addConfig(String key,Object value)
     {
    	 this.config.put(key, value);
     }
     public void init()
     {
    	 Properties proes=new Properties(SystemConfig.class,"conf.properties");
    	 for(Object key:proes.getKeySet())
    	{
    		 config.put(key.toString(), proes.getProperty(key.toString()));
    		
        }
     }
     public void refresh()
     {
    	 this.init();
     }
     
}
