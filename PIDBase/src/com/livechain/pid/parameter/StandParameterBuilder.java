package com.livechain.pid.parameter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.livechain.pid.PIDGenerateImpl;
import com.livechain.pid.util.PIDUtil;
import com.livechain.pid.util.PropertiesUtil;

public class StandParameterBuilder implements ParameterBuilder {
    /**
     * 将个人自然信息组装上系统信息
     * 可以根据重写这个方法以达到组装不同的系统信息
     * @param json 个人自然信息
     */
	@Override
	public synchronized void buildAddParams(JSONObject json,boolean genpid) {
		// TODO Auto-generated method stub
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(genpid)
		{
		 json.put("PID",PIDUtil.generatePID());
		}
		json.put("status","0");
		json.put("isdel", "0");
		json.put("createdate", format.format(new Date()));
	/*	if(json.keySet().contains("orgcode"))
		{
		String orgcode=(String)json.get("orgcode");
		JSONArray orgcodes=new JSONArray();
		JSONObject orgobj=new JSONObject();
		orgobj.put("orgcode", orgcode);
		orgobj.put("uploadtime", format.format(new Date()));
		orgcodes.put(orgobj);
		json.put("orgcodes", orgcodes);
		}*/
		/*if(json.keySet().contains("credentials"))
		{
			JSONArray credentials=(JSONArray)json.get("credentials");
			for(int i=0;i<credentials.length();i++)
			{
				JSONObject tmp=credentials.getJSONObject(i);
				tmp.put("createdate", format.format(new Date()));
				credentials.put(i, tmp);
			}
		}*/
		//json.put("_id", json.get("PID"));
		/*Object indata=null;
		if(json.keySet().contains("indata"))
		{
			indata=json.get("indata");
			json.remove("indata");
		}*/
		//json.put("person",json.toString());
		/*if(null!=indata &&!indata.equals(""))
		{
			json.put("indata", indata);
		}*/
		if(json.keySet().contains("client"))
		{
			json.remove("client");
		}
		if(json.keySet().contains("action"))
		{
			json.remove("action");
		}
	}

	@Override
	public void buildDelParams(JSONObject params) {
		// TODO Auto-generated method stub
		params.put("status","2");
		params.put("isdel", "1");
	}
    /**
     * 根据入参 组装 查询条件
     * 可以根据重写这个方法以达到组装不同的查询条件
     * 这里组装的为从MongoDB中以身份证为查询条件且以MongoDB中身份证是以数组方式存储的
     * @param params 入参
     * @param query  组装后的查询条件
     */
	@Override
	public synchronized void buildQueryParams(JSONObject params, JSONObject query) {
		// TODO Auto-generated method stub
		for(Object key : params.keySet())
		{
			Object value=params.get(key.toString());
			if(value!=null)
			{
				if(value instanceof JSONArray)
				{   JSONArray array=(JSONArray)value;
					for(int i=0;i<array.length();i++)
					{
						JSONObject e=array.getJSONObject(i);
						for(Object subkey:e.keySet())
						{   Object v=e.get(subkey.toString());
							if(v!=null)
							{
								query.put(key+"."+subkey, v);
							}
						}
					}
				}else
				{
					query.put(key.toString(), value);
				}
			}
		}
		/*if(!params.keySet().contains("credentials"))
	     {
			if(params.keySet().contains("pname"))
			{
		     query.put("pname", params.get("pname"));
			}
			if(params.keySet().contains("gender"))
			{
		     query.put("gender", params.get("gender"));
			}
			if(params.keySet().contains("birthday"))
			{
		     query.put("birthday", params.get("birthday"));
			}
		   //return;
	     }
		JSONArray credentials=(JSONArray)params.get("credentials");
		for(int i=0;i<credentials.length();i++)
		{   JSONObject element=(JSONObject)credentials.get(i);
		    if("01".equals(element.get("type")))
		    {  
		    	query.put("credentials.type",element.get("type"));
		    	query.put("credentials.number",element.get("number"));
		    	break;
		    }
		}*/
	}
	/**
	 * 组装 要更新的数据
	 * 当调用接口更新 个人自然数据时 或者 注册个人自然数据时 已经存在这个人时
	 * 需要将这个人的数据更新到当前状态
	 * 可以根据重写这个方法以达到按照不同规则进行整合
	 * @param newPerson 为新数据
	 * @param oldPerson 为老数据
	 * @param updateAll 是否更新所有数据
	 * 在updateAll 为false的情况下不会更新 证件 人名 性别 出生日期
	 */
	@Override
	public synchronized void buildUpdateParams(JSONObject newPerson, JSONObject oldPerson,boolean updateAll) {
		// TODO Auto-generated method stub
		//判断是否将所有字段都更新		
			
		     //去除重复的证件
		     if(oldPerson.keySet().contains(PropertiesUtil.CREDENTIALS)&&newPerson.keySet().contains(PropertiesUtil.CREDENTIALS))
		     {
		    	 //System.out.println("running in parameter build bean");
		    	 JSONArray newcrede=newPerson.getJSONArray(PropertiesUtil.CREDENTIALS);
		    	 JSONArray oldcrede=oldPerson.getJSONArray(PropertiesUtil.CREDENTIALS);
		    	 mergCards(newcrede,oldcrede,false);	    	 
		     }else if(!oldPerson.keySet().contains(PropertiesUtil.CREDENTIALS)&&newPerson.keySet().contains(PropertiesUtil.CREDENTIALS)){
		    	 oldPerson.put(PropertiesUtil.CREDENTIALS, newPerson.get(PropertiesUtil.CREDENTIALS));
		     }
		     for(Object k:newPerson.keySet())
		     {
		    	 if(!k.equals(PropertiesUtil.CREDENTIALS))
		    	 {
		    		 oldPerson.put(k.toString(), newPerson.get(k.toString()));
		    	 }
		     }
	
 }

/**
 * 合并 证件信息/电话信息
 * @param newArray
 * @param oldArray
 * @param update 当证件存在时是否更新
 */
private void mergCards(JSONArray newArray,JSONArray oldArray,boolean update)
{
	JSONObject remove=new JSONObject();
	for(int i=0;i<newArray.length();i++)
	{    
		//boolean has=false;
		JSONObject element=newArray.getJSONObject(i);
		//判断新的元素在老的数据中有没有
		for(int j=0;j<oldArray.length();j++)
		{
			JSONObject oldelement=oldArray.getJSONObject(j);
			//若有 将老的替换成新的
			if(oldelement.get("type").equals(element.get("type"))&&oldelement.get("number").equals(element.get("number")))
			{				
				//has=true;
				//break;
			}else {
				remove.put(i+"", i);
			}
		}		
	}
	for(Object k:remove.keySet())
	{
		oldArray.put(newArray.get(remove.getInt(k.toString())));
	}
}

}
