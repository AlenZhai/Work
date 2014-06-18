package com.livechain.pid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MongoDaoSupportWebImpl  extends MongoDaoSupportImpl implements WebDaoSupport {
	public void getCount(Object params, Object rtn) {
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		
		//jsonToDBObject(p,query);
		List querylist=jsonToMap(p);
		Map m=new HashMap();
		m.put("$or", querylist);
		m.put("isdel", "0");
		//根据查询条件从mongodb中找出数据
		DBObject query=new BasicDBObject(m);
		
		DBCollection coll=this.getMongodb().getCollection(this.getTable());
		//DBObject orderBy = new BasicDBObject();
		//orderBy.put("_id", Integer.valueOf(1));
		
        int cur = coll.find(query).count();//.sort(orderBy).skip(skip).limit(limit);
        response.put("datacount", cur);
	  
  }
	public boolean updateByCondition(Object params,Object rtn)
	{
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
			boolean flag=false;
		DBCollection coll=this.getMongodb().getCollection(this.getTable());
		//将参数 转换成 DBObject 
		DBObject jo=new BasicDBObject();
		jsonToDBObject(p,jo);
		//判断rtn中是否加了更新条件 若加了
		DBObject condition=new BasicDBObject();
		if(response.keySet().contains("condition"))
		{
			jsonToDBObject(response.getJSONObject("condition"),condition);
			response.remove("condition");
		}
		if(condition.containsKey("_id"))
		{
			condition.put("_id", new ObjectId(condition.get("_id").toString()));
		}
		//jo.put("_id", arg1)
		//System.out.println("condition:"+condition.toString());
		WriteResult result=coll.update(condition, jo);
		//System.out.println(condition.toString());
		//判断更新是否成功		
		if(result.getN()>0)
		{
			flag=true;			
		}
		response.put("count", result.getN());
		response.put("update", true);
		return flag;
	}
	public boolean changeStatus(Object params,Object rtn)
	{
		boolean flag=false;
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		DBObject jo=new BasicDBObject();
		Map b=new HashMap();
		if(p.keySet().contains("status"))
		{
			b.put("status", p.get("status"));
		}
		jo.put("$set",b);
		DBObject condition=new BasicDBObject();
		if(response.keySet().contains("condition"))
		{
			jsonToDBObject(response.getJSONObject("condition"),condition);
			response.remove("condition");
		}
		DBCollection coll=this.getMongodb().getCollection(this.getTable());
		WriteResult result=coll.update(condition, jo);
		//System.out.println("condition:"+condition);
		//System.out.println("jo:"+jo);
		if(null != result && result.getN()>0)
		{
			//System.out.println("N :"+result.getN());
			flag=true;
		}
		return flag;
	}
    public void searchlog(Object params, Object rtn)
    {
    	JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		int skip=0;
		int limit=0;
		if(null !=p)
		{
			if(p.keySet().contains("skip"))
			{
				skip=p.getInt("skip");
				p.remove("skip");
			}
			if(p.keySet().contains("limit"))
			{
				limit=p.getInt("limit");
				p.remove("limit");
			}			
		}
		
		DBObject query=new BasicDBObject();
		this.jsonToDBObject(p, query);
		DBCollection coll=this.getMongodb().getCollection(this.getTable());
		DBObject orderBy = new BasicDBObject();
		orderBy.put("_id", Integer.valueOf(1));
		DBCursor cur = coll.find(query).sort(orderBy).skip(skip).limit(limit);
		 while (cur.hasNext()) {
	        	DBObject dbo=cur.next();
	        	JSONObject data=new JSONObject();
	        	for(String key:dbo.keySet())
	        	{  
	        		if(key.equals("_id"))
	        		{
	        			data.put("lid", dbo.get(key));
	        		}else
	        		{
	        		 data.put(key, dbo.get(key));
	        		}
	        	}
	            response.put(dbo.get("_id").toString(),data);
	        }
		
		
    }
	@Override
	public void search(Object params, Object rtn) {
		// TODO Auto-generated method stub
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		int skip=0;
		int limit=0;
		//组装查询条件
		//Map m=new HashMap();		
		if(null !=p)
		{
			if(p.keySet().contains("skip"))
			{
				skip=p.getInt("skip");
				p.remove("skip");
			}
			if(p.keySet().contains("limit"))
			{
				limit=p.getInt("limit");
				p.remove("limit");
			}			
		}
		//根据查询条件从mongodb中找出数据
		DBObject query=new BasicDBObject();
		this.jsonToDBObject(p, query);
		//log4j.info("query param:"+query.toString());
		DBCollection coll=this.getMongodb().getCollection(this.getTable());
		DBObject orderBy = new BasicDBObject();
		orderBy.put("_id", Integer.valueOf(1));
		
        DBCursor cur = coll.find(query).sort(orderBy).skip(skip).limit(limit);
        //response.put("count", cur.count());
        //将查出的数据 装入 rtn 
        while (cur.hasNext()) {
        	DBObject dbo=cur.next();        	
            response.put(dbo.get("_id").toString(), new JSONObject(dbo.get("person").toString()));
        }
        //log4j.info("query result:"+response.toString());
        
}

	@Override
	public void realDel(Object params,Object rtn) {
		// TODO Auto-generated method stub
		
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		DBObject obj=new BasicDBObject();
		this.jsonToDBObject(p, obj);
		DBCollection coll=this.getMongodb().getCollection(this.getTable());
		WriteResult result=coll.remove(obj);
		if(result.getN()>0)
		{
			response.put("del",result.getN());			
		}
	}
}
