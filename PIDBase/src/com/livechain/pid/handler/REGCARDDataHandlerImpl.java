package com.livechain.pid.handler;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.livechain.pid.exception.NoDataException;
import com.livechain.pid.exception.StandException;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.StandContext;

/**
 * 注册卡信息
 * 1.注册 卡信息时一定要提供PID
 * 2.根据PID把卡号注册到对应人的自然信息中
 * @author alenzhai 2013-06-01
 *
 */
public class REGCARDDataHandlerImpl extends CommonDataHandler {
	private static final Logger logger = LoggerFactory.getLogger(REGCARDDataHandlerImpl.class);

	@Override
	public void handlerReq(JSONObject json, JSONObject response,
			StandContext extParams) {
		// TODO Auto-generated method stub
		JSONObject persons=new JSONObject();
        this.queryInSolr(json, persons, response);
        	if(persons.length()>0)
        	{    
        		logger.debug("get person from solr :"+persons);
        		JSONObject p=null;
        		for(Object key:persons.keySet())
        		{
        			p=persons.getJSONObject(key.toString());
        		}
        		
        		if(null!=p)
        		{        			
        				  JSONArray arr=json.getJSONArray(PropertiesUtil.CREDENTIALS);
        				  JSONArray jarr=p.getJSONArray(PropertiesUtil.CREDENTIALS);
        				  if(p.keySet().contains(PropertiesUtil.CREDENTIALS))
        				  {
            				  for(int i=0;i<arr.length();i++)
            	        		 {
            	        			 JSONObject cre=arr.getJSONObject(i);
            	        			 if(cre.keySet().contains("type")&&cre.keySet().contains("number"))
            	        			 {  
            	        				 boolean has=false;
            	        				 for(int j=0;j<jarr.length();j++)
            	        				 {
            	        					 JSONObject jcre=jarr.getJSONObject(j);
            	        					 if(jcre.keySet().contains("type")&&cre.keySet().contains("number"))
            	        					 {
            	        						 if(jcre.get("type").equals(cre.get("type"))&&jcre.get("number").equals(cre.get("number")))
            	        						 {
            	        							 String card=cre.getString(PropertiesUtil.NUMBER)+"_"+cre.getString(PropertiesUtil.TYPE);
            	        							 response.put(card, "exist");
            	        							 has=true;
            	        							 break;
            	        						 }
            	        					 }
            	        				 }
            	        				 if(!has)
            	        				 {
            	        					 jarr.put(cre);
                	        				 //老数据中没有 则加入老数据中
                	        				 String card=cre.getString(PropertiesUtil.NUMBER)+"_"+cre.getString(PropertiesUtil.TYPE);
                	        				 response.put(card, "added");
            	        				 }  	        				 
            	        			 }
            	        		 }
        				  }        				  	
        			if(p.keySet().contains("score"))
        			{
        				p.remove("score");
        			}
        			if(isMerged(p))
        			{
        			  this.updateByPid(p.getString(PropertiesUtil.SIMILARITY), PropertiesUtil.CREDENTIALS, jarr);
        			}else{
        				this.updateByPid(json.getString(PropertiesUtil.PID), PropertiesUtil.CREDENTIALS, jarr);
        			}
        			
        			json.put("flag", "3");
        			//System.out.println(flag);
        			//response.put("update", true);
        		}
        	// this.createNewPerson(json, response,false);
        	 //return;
        	}
        	else {
        		StandException exception=new NoDataException();
        		logger.error(exceptionManager.getCode(exception)+""+exceptionManager.getMsg(exception));
        		response.put("error", this.exceptionManager.getCode(exception));
        	} 
	}
	

}
