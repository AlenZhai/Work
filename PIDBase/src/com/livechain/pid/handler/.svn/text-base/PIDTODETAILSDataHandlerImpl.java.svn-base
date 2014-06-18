package com.livechain.pid.handler;


import org.json.JSONObject;


import com.livechain.pid.exception.NoDataException;
import com.livechain.pid.util.StandContext;
/**
 * 能过PID获取个人自然信息
 * @author alenzhai 2013-05-30
 *
 */
public class PIDTODETAILSDataHandlerImpl extends CommonDataHandler {
	
	
	@Override
	public void handlerReq(JSONObject json, JSONObject response,
			StandContext extParams) {
		// TODO Auto-generated method stub
		JSONObject persons=new JSONObject();
        // 根据入参 查询 数据
		//queryPersonByAnd(json,persons,response);
        //若MongoDB中不存在则查看Solr中是否存在
        //if(persons.length()<=0)
        {
        	this.queryInSolr(json, persons, response);
        }
        JSONObject p=new JSONObject();
        if(persons.length()>0)
        {
        	for(Object key:persons.keySet())
        	{
        		p=persons.getJSONObject(key.toString());
        	}
        	if(p.keySet().contains("_id"))
        	p.remove("_id");//isdel status
        	if(p.keySet().contains("isdel"))
        	p.remove("isdel");
        	if(p.keySet().contains("status"))
        	p.remove("status");
        	
        	response.put("person", p);
        }else
        {       	
        	//log4j.error(this.exceptionManager.getCode(new NoDataException()));
        	response.put("error", this.exceptionManager.getCode(new NoDataException()));
        	
        }
	}

}
