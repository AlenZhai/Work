package com.livechain.pid.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.livechain.pid.mongodb.MongoUtil;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;


public class MongoDaoSupportImpl implements DaoSupport {
    private DB mongodb=null;//mongodb 的数据库对象
    private String table=null;//mongodb的表
   // private final static Log log4j=LogFactory.getLog(MongoDaoSupportImpl.class);
    public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public DB getMongodb() {
		return mongodb;
	}

	public void setMongodb(DB mongodb) {
		this.mongodb = mongodb;
	}

	public MongoDaoSupportImpl(){
    	mongodb=MongoUtil.getDB();
    }
	/**
	 * 从Monogdb 中查询数据 所有条件为 且关系
	 * @param params
	 * @param rtn
	 */
	public void getInfoByAnd(Object params, Object rtn)
	{
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		int skip=0;
		int limit=0;
		//组装查询条件
		Map m=new HashMap();
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
			//jsonToDBObject(p,query);
			//jsonToDBObject(p);
			p.put("isdel", "0");
		}else
		{
			response.put("error", "no parameter");
		}
		
		
		//根据查询条件从mongodb中找出数据
		DBObject query=new BasicDBObject();
		jsonToDBObject(p,query);
		//log4j.info("query param:"+query.toString());
		DBCollection coll=mongodb.getCollection(table);
		DBObject orderBy = new BasicDBObject();
		orderBy.put("_id", Integer.valueOf(1));
		
        DBCursor cur = coll.find(query).sort(orderBy).skip(skip).limit(limit);
        System.out.println(cur.count());
        //response.put("count", cur.count());
        //将查出的数据 装入 rtn 
        while (cur.hasNext()) {
        	DBObject dbo=cur.next();
        	System.out.println("dbo:"+dbo);
            response.put(dbo.get("_id").toString(), new JSONObject(dbo.get("person").toString()));

        }
	}
    /**
     * 从Monogdb 中查询数据 
     * @param params 查询条件 Json 数据格式
     * @param rtn 查询出的数据容器 Json 数据格式
     */
	@Override
	public void getInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		int skip=0;
		int limit=0;
		//组装查询条件
		Map m=new HashMap();
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
			//jsonToDBObject(p,query);
			//List querylist=jsonToMap(p);			
			//m.put("$or", querylist);
			
		}
		   m.put("isdel", "0");
		   if(p.keySet().contains("pname"))
		   {
			  // m.remove("$or");
			   m.put("pname",p.get("pname").toString());
		   }
		   if(p.keySet().contains("PID"))
		   { 
			   List keys=new ArrayList();
			   for(Object k:m.keySet())
			   {
				   keys.add(k);
			   }
			   for(int i=0;i<keys.size();i++)
			   {
				   m.remove(keys.get(i));
			   }
			   //m.remove("$or");
			   m.put("PID", p.get("PID").toString());
		   }
		
		//根据查询条件从mongodb中找出数据
		DBObject query=new BasicDBObject(m);
		//log4j.info("query param:"+query.toString());
		DBCollection coll=mongodb.getCollection(table);
		DBObject orderBy = new BasicDBObject();
		orderBy.put("_id", Integer.valueOf(1));
		
        DBCursor cur = coll.find(query).sort(orderBy).skip(skip).limit(limit);
        //response.put("count", cur.count());
        //将查出的数据 装入 rtn 
        while (cur.hasNext()) {
        	DBObject dbo=cur.next();
        	System.out.println("dbo:"+dbo);
            response.put(dbo.get("_id").toString(), new JSONObject(dbo.get("person").toString()));

        }
        //log4j.info("query result:"+response.toString());
        
	}
    /**
     * 将Json 保存支Mongodb 
     * @param params 要保存的数据
     * @param rtn 执行结果
     */
	@Override
	public boolean saveInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		
		//获取要保存的表
		
		DBCollection coll=mongodb.getCollection(table);
		//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		
		//将参数 转换成 DBObject 
		DBObject jo=new BasicDBObject();
		jsonToDBObject(p,jo);
		if(!p.keySet().contains("person"))
		{
			//System.out.println("ppppppppppppppppppppppppppppp");
			jo.put("person", p.toString());
		}
		//将PID 和整个 JSON串加入 _id 和person 字段
		/*jo.put("_id", p.get("PID"));
		jo.put("person",p.toString());*/
		
		
		//将数据保存到Mongodb中
		WriteResult result=coll.insert(jo);
		//装载执行结果
		
		response.put("count", result.getN());
		response.put("error", result.getError());
		if(result.getError()==null || result.getError().equals(""))
		{
			flag=true;
		}
		return flag;
	}
	/**
	 * 将Json转换成DBObject
	 * @param json 待转换的Json
	 * @param obj  转换后数据容器
	 */
	protected void jsonToDBObject(JSONObject json,DBObject obj)
	{
		for(Object key : json.keySet())
		{  
			//System.out.println(""+key);
			if(json.get(key.toString()) instanceof JSONArray ){
			 
			ArrayList<DBObject> list=new ArrayList<DBObject>();
			JSONArray jsonarray=json.getJSONArray(key.toString());
			for(int i=0;i<jsonarray.length();i++)
			{
				System.out.println(""+jsonarray.get(i));
				JSONObject tmp=(JSONObject)jsonarray.get(i);
				DBObject subobj=new BasicDBObject();
				
				for(Object subkey : tmp.keySet())
				{
					subobj.put(subkey.toString(), tmp.get(subkey.toString()));
				}
				list.add(subobj);
			}
			
			
			//obj.put(key.toString(), json.get(key.toString()).toString());
			
			//jsonToDBObject((JSONObject)json.get(key.toString()),tmpobj);
			
			obj.put(key.toString(), list);
		    }else{
		    	obj.put(key.toString(), json.get(key.toString()));
		    }
		}
		
	}
	/**
	 *将Json格式的数据转换成Map
	 * @param json
	 * @return
	 */
	protected List<Map<String,Object>> jsonToMap(JSONObject json)
	{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(Object key:json.keySet())
		{
			Object value=json.get(key.toString());
			if(value!=null)
			{
				Map<String,Object> m=new HashMap<String,Object>();
				m.put(key.toString(), value);
				list.add(m);
			}
		}
		return list;
	}

	@Override
	public boolean updateInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
			
		DBCollection coll=mongodb.getCollection(table);
		//将参数 转换成 DBObject 
		DBObject jo=new BasicDBObject();
		jsonToDBObject(p,jo);
		//判断rtn中是否加了更新条件 若加了
		DBObject condition=new BasicDBObject("PID",jo.get("PID"));
		if(response.keySet().contains("condition"))
		{
			jsonToDBObject(response.getJSONObject("condition"),condition);
			response.remove("condition");
		}
		if(!p.keySet().contains("person"))
		{
			jo.put("person", p.toString());
		}
		WriteResult result=coll.update(condition, jo);
		System.out.println(condition.toString());
		//判断更新是否成功		
		if(result.getN()>0)
		{
			flag=true;			
		}
		response.put("count", result.getN());
		response.put("update", true);
		return flag;
	}
    /**
     * 删除数据 
     * 这里的删除不是真正的删除 
     * 而是将isdel的值改为1
     * @param params
     */
	@Override
	public boolean delete(Object params, Object rtn) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		DBObject o= new BasicDBObject();
		
		DBCollection coll=mongodb.getCollection(table);
		WriteResult result=coll.update(new BasicDBObject("PID",p.get("PID")), o);
		if(result.getN()>0)
		{
			flag=true;
		}
		response.put("delete", flag);
		return flag;
	}

	@Override 
	public boolean isExsit(Object params, Object rtn) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		//获取要保存的表
		
		DBCollection coll=mongodb.getCollection(table);
		//组装查询条件
		DBObject query=new BasicDBObject();
		jsonToDBObject(p,query);
		//根据查询条件从mongodb中找出数据
		
	    DBCursor cur = coll.find(query);
	    if(null!= cur && cur.hasNext())
	    {
	    	flag=true;
	    }
		return flag;
	}

	@Override
	public boolean updateByPid(String pid, String field, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
