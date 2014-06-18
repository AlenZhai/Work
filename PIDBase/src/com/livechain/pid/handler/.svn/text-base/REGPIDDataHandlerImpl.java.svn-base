package com.livechain.pid.handler;

import java.util.ArrayList;

import java.util.List;



import org.json.JSONArray;
import org.json.JSONObject;


import com.livechain.pid.exception.NoDataException;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.StandContext;
import com.livechain.pid.model.WeightResult;

/**
 * 注册数据服务类
 * 完成数据的识别和注册
 * @author alenzhai 2013-05-24
 *
 */
public  class  REGPIDDataHandlerImpl extends CommonDataHandler {
	
    /**
     * 处理注册数据
     */
	@Override
	public void handlerReq(JSONObject json, JSONObject response, StandContext extParams)
	{
		// TODO Auto-generated method stub
		JSONObject persons=new JSONObject();
		String flag="";
        // 根据入参 查询 数据
        //queryPerson(json,persons,response);
        //int count=persons.length();
		//不存在则插入
		//if(count<1 )
//		{
			//查看solr中是否存在
			queryInSolr(json,persons,response);
			//存在 则返回
			if(persons.length()>0)
			{
				//flag 1-未插入solr 2-插入solr 3-只插入证件信息到solr
				flag ="1";
				//System.out.println(persons);
				for(Object key:persons.keySet())
				{
				 JSONObject person=	persons.getJSONObject(key.toString());
				 //判断记录是否被合并 若合并则返回合并到的Similarity
				
				 if(isMerged(person))
				 {
				   response.put(PropertiesUtil.PID,person.get(PropertiesUtil.SIMILARITY));
				 }else{
				   response.put(PropertiesUtil.PID,key);
				 }
				  //判断老数据中没有证件信息
				  boolean flageOldData=!persons.getJSONObject(key.toString()).keySet().contains(PropertiesUtil.CREDENTIALS)||persons.getJSONObject(key.toString()).get(PropertiesUtil.CREDENTIALS)==null || persons.getJSONObject(key.toString()).getJSONArray(PropertiesUtil.CREDENTIALS).length()<=0;
				  //判断新数据中有证件信息
				  boolean flageNewData=json.keySet().contains(PropertiesUtil.CREDENTIALS)&&json.getJSONArray(PropertiesUtil.CREDENTIALS).length()>0;
				  //System.out.println(flageOldData&&flageNewData);
				  if(flageOldData&&flageNewData)
				  {
					  flag ="3";
					//如果新注册数据中有证件信息而老数据中没有证件信息则把证件信息加入  
				   this.updateByPid(key.toString(), PropertiesUtil.CREDENTIALS,json.get(PropertiesUtil.CREDENTIALS));
				  }
				}
				
			}else
			{
				if(response.keySet().contains(PropertiesUtil.SIMILARITY))
				{
					//JSONArray simi=new JSONArray();
					//simi.put(response.get(PropertiesUtil.SIMILARITY));
					json.put(PropertiesUtil.SIMILARITY, response.get(PropertiesUtil.SIMILARITY));
					response.remove(PropertiesUtil.SIMILARITY);
				}
				 flag ="2";
				createNewPerson(json, response,true);
				
			}
			json.put("flag", flag);
//		}
		
        	
	}
	
	
}
