package com.livechain.pid.ws.init;

import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.livechain.pid.cache.DataSaveElement;
import com.livechain.pid.cache.ECacheManager;
import com.livechain.pid.solr.HttpClientUtils;

public class HttpSaveQueueData implements SaveQueue {
	private static final Logger logger = LoggerFactory.getLogger(HttpSaveQueueData.class);
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public synchronized void saveQueueData() {
		// TODO Auto-generated method stub
		DataSaveElement element=null;
		if((element=ECacheManager.getNewInstance().outQueue())!=null)
		{ 
			try {
				//String command="{\"pname\":\"孙喜芳\",\"flag\":\"2\",\"gender\":\"2\",\"PID\":\"1eb61961c5504b9aa8a2468d260da48a\",\"addrprovince\":\"\",\"addrcity\":\"\",\"addrcounty\":\"\",\"addrtown\":\"\",\"addrvillage\":\"黑龙江省哈尔滨市方正县伊汉通乡伊汉通村村委会\",\"addrhouseid\":\"\",\"orgcode\":\"\",\"workdate\":\"\",\"nationality\":\"\",\"registeretype\":\"\",\"educationcode\":\"\",\"booldtype\":\"\",\"rh\":\"\",\"birthday\":\"1938-06-06\",\"credentials\":[{\"number\":\"232130193806065922\",\"type\":\"01\"},{\"number\":\"232130193806065922\",\"type\":\"01\"}],\"action\":\"REGPID\",\"client\":\"AppClient\"}";
				
				logger.info(element.json.toString());
				String resultStr=HttpClientUtils.sendPostCommand(element.json.toString(), url);
				logger.info(resultStr);				
			JSONObject result=new JSONObject(resultStr);			
			
			if(result.keySet().contains("flag")&&Boolean.valueOf(result.getString("flag")))
			{				
			   logger.info("queue size:"+ECacheManager.getNewInstance().getQueue().size());
			}else {
				ECacheManager.getNewInstance().inQueue(element);
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
	}
	
	public static void main(String[] args)
	{
		new HttpSaveQueueData().saveQueueData();
	}

}
