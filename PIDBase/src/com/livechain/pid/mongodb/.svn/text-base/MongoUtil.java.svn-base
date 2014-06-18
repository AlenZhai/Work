package com.livechain.pid.mongodb;


import java.io.IOException;
import java.net.UnknownHostException;

import java.util.List;

import com.livechain.pid.util.Properties;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class MongoUtil {
	    private static Properties pros=null;
	   
	    static
	    {   	        
	    	pros=new Properties(MongoUtil.class,"mongodb.properties");
	    }
	    public static void init()
	        throws UnknownHostException, MongoException, IOException
	    {
	    	
	        if(m == null)
	        {
	           /* WebApplicationContext wac = AppContextHolder.get();
	            Context ctx = (Context)wac.getBean("staticInitContext");*/
	            String cfgServerIp =pros.getProperty("IP"); //ctx.value("server.configServerIp");
	            String cfgServerport =pros.getProperty("PORT"); //ctx.value("server.configServerPort");
	            //if(!StringUtils.isEmpty(cfgServerIp) && !StringUtils.isEmpty(cfgServerport))
	            {
	                int port = Integer.valueOf(cfgServerport).intValue();
	                m = create(cfgServerIp, port);
	                List<String> databases= m.getDatabaseNames();
	                 if(databases.size()>0)
	                 {
	                  isReady = true;
	                 // log4j.info("The database is ready");
	                 }
	                
	            }
	        }
	    }

	    private static Mongo create(String host, int port)
	        throws UnknownHostException, MongoException
	    {
	        MongoOptions mp = new MongoOptions();
	        mp.autoConnectRetry = false;
	        mp.connectionsPerHost = 100;
	        mp.threadsAllowedToBlockForConnectionMultiplier = 50;
	        mp.maxWaitTime = 10000;
	        ServerAddress sa = new ServerAddress(host, port);
	        Mongo svr = new Mongo(sa, mp);
	        return svr;
	    }

	    public static DB getDB()
	    {   if(null==m)
			try {
				init();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       String dbname = pros.getProperty("dbName");
	        return m.getDB(dbname);
	    }

	    public static boolean isReady()
	    {
	        return isReady;
	    }

	    public static void main(String args1[])
	        throws UnknownHostException, MongoException
	    {
	    	getDB();
	    }

	    private static boolean isReady=false;
	    private static Mongo m;


}
