package com.livechain.pid.handler;


import org.json.JSONArray;
import org.json.JSONObject;

import com.livechain.pid.exception.NoDataException;
import com.livechain.pid.exception.NotEnoughParamsException;
import com.livechain.pid.exception.ParameterNullException;
import com.livechain.pid.util.StandContext;
/**
 * 根据PID和给出的个人自然信息 更新现有的个人自然 信息
 * @author alenzhai 2013-05-30
 *
 */
public class UPDATEDataHandlerImpl extends CommonDataHandler{
	
   	@Override
	public void handlerReq(JSONObject json, JSONObject response,
			StandContext extParams) {
		// TODO Auto-generated method stub
   		JSONObject persons=new JSONObject();
        // 根据入参 查询 数据   		
        	this.queryInSolr(json, persons, response);
        	if(persons.length()>0)
        	{   
        		JSONObject p=null;
        		for(Object key:persons.keySet())
        		{
        			p=persons.getJSONObject(key.toString());
        		}
        		if(null!=p)
        		{
        			
				 try {
					updatePerson(json,p, response,true);
				} catch (ParameterNullException e) {
					// TODO Auto-generated catch block
					response.put(e.getCode(), e.getMsg());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
        		 return;     
        		}
        	}
        	response.put("error", this.exceptionManager.getCode(new NoDataException()));
        		
      
        
	}

}
