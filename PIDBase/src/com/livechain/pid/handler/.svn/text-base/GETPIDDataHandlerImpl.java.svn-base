package com.livechain.pid.handler;

import java.util.ArrayList;

import java.util.List;


import org.json.JSONObject;

import com.livechain.pid.exception.NoDataException;
import com.livechain.pid.model.WeightResult;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.StandContext;


/**
 * 通过个人自然信息得到PID
 * @author alenzhai 2013-05-20
 *
 */
public class GETPIDDataHandlerImpl extends CommonDataHandler {
	
	@Override
	public void handlerReq(JSONObject json, JSONObject response,
			StandContext extParams) {
		// TODO Auto-generated method stub
		JSONObject persons=new JSONObject();
		//queryPerson(json,persons,response);
		//if(persons.length()<=0)
		//{
			this.queryInSolr(json, persons, response);
			if(persons.length()>0)
			{
				JSONObject p=new JSONObject();
				for(Object k:persons.keySet())
				{
					p=persons.getJSONObject(k.toString());					
				}
				//JSONObject person=	persons.getJSONObject(key.toString());
				 //判断记录是否被合并 若合并则返回合并到的Similarity				 
				 if(isMerged(p))
				 {
				   response.put(PropertiesUtil.PID,p.get(PropertiesUtil.SIMILARITY));
				 }else{
				   response.put(PropertiesUtil.PID,p.getString("PID"));
				 }				
			}
		//}
		/*List<JSONObject> srclist=new ArrayList<JSONObject>();
		WeightResult result=null;
		if(persons.length()>0)
		{
			for(Object key : persons.keySet())
		       { 
		    	   JSONObject person=(JSONObject)persons.get(key.toString());
		    	   srclist.add(person);
		       }
			result=this.weightManger.getMaxWeightObj(json, srclist);
			//add by test start
			//System.out.println("pid:"+result.getPid());
			//System.out.println("pid list:"+result.getListPid());
			//add by test end
			if(result.getPid()!=null&& !result.getPid().equals(""))
			{
			  //log4j.info("get PID"+result.getPid());
			  response.put("PID", result.getPid());
			}else
			{     persons=new JSONObject();
				this.queryInSolr(json, persons, response);
				if(persons.length()>0)
				{
					JSONObject p=new JSONObject();
					for(Object k:persons.keySet())
					{
						p=persons.getJSONObject(k.toString());
					}
					if(p.keySet().contains("PID"))
					{
						response.put("PID", p.getString("PID"));
						return;
					}
				}
				//log4j.error(this.exceptionManager.getCode(new NoDataException()));
				response.put("error", this.exceptionManager.getCode(new NoDataException()));
			}*/
		else
		{
			//log4j.error(this.exceptionManager.getCode(new NoDataException()));
			response.put("error", this.exceptionManager.getCode(new NoDataException()));
		}
	}
		
}
