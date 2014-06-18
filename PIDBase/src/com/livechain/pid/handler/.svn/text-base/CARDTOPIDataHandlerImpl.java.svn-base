package com.livechain.pid.handler;

import java.util.Map;


import org.json.JSONArray;
import org.json.JSONObject;



import com.livechain.pid.exception.NoDataException;
import com.livechain.pid.exception.TypeMatchException;
import com.livechain.pid.model.Field;
import com.livechain.pid.model.Type;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.StandContext;
import com.livechain.pid.weight.WeightManger;
/**
 * 通过卡号或身份证号获取PID
 * @author alenzhai 2013-05-30
 *通过卡号或身份证号获取PID由于PID的唯一性
 *1.要确认这个卡号或身份证号可以唯一识别一个人
 *2.若这个卡号可以唯一识别一个人那么通过卡号去查询数据
 *3.若这个卡号不可以唯一识别一个人那么不能通过此卡号获得PID
 */
public class CARDTOPIDataHandlerImpl extends CommonDataHandler {
	
	@Override
	public void handlerReq(JSONObject json, JSONObject response,
			StandContext context) {
		// TODO Auto-generated method stub
		
		Field field=weightManger.getFields().get(PropertiesUtil.CREDENTIALS);
		JSONArray creArray=json.getJSONArray(PropertiesUtil.CREDENTIALS);
		boolean isUnique=false;
		//通过号码类型判断这个卡号是否可以唯一的识别一个人
		JSONObject remove=new JSONObject();
		for(int index=0;index<creArray.length();index++)
		{
			JSONObject cre=creArray.getJSONObject(index);
			if(field.getType(cre.getString(PropertiesUtil.TYPE)).isUnique())
			{
				isUnique=true;
			}else{
				remove.put(String.valueOf(index), index);
			}
		}		
		if(isUnique)
		{  
			for(Object k:remove.keySet())
			{
			 creArray.remove(Integer.valueOf(k.toString()));
			}
			
			JSONObject	persons=new JSONObject();
				this.queryInSolr(json, persons, response);
				if(persons.length()>0)
				{
					for(Object k:persons.keySet())
					{	
						JSONObject p=persons.getJSONObject(k.toString());
						//判断记录是否被合并 若合并则返回合并到的Similarity	
						if(isMerged(p))
						{
							response.put(PropertiesUtil.PID,p.getString(PropertiesUtil.SIMILARITY));
						}else
						{
							response.put(PropertiesUtil.PID,k);
						}							
					}					
					
				}else {
				//log4j.error(this.exceptionManager.getCode(new NoDataException()));
				response.put("error", this.exceptionManager.getCode(new NoDataException()));
			}
			//}
		}else
		{
			//log4j.error(this.exceptionManager.getCode(new TypeMatchException()));
			response.put("error", this.exceptionManager.getCode(new TypeMatchException()));
		}
	}

}
