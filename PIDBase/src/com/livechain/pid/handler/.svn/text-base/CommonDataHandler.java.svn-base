package com.livechain.pid.handler;



import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.exception.ExceptionManager;
import com.livechain.pid.exception.ParameterNullException;
import com.livechain.pid.model.Field;
import com.livechain.pid.parameter.ParameterBuilder;
import com.livechain.pid.solr.SolrSearch;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.StandContext;
import com.livechain.pid.weight.WeightManger;

/**
 * 数据处理公共类 些类为一个虚类封装通用方法
 * @author alenzhai 2013-05-30
 *
 */
public abstract class CommonDataHandler implements DataHandler {
	protected DaoSupport pidDao=null;
	protected ExceptionManager exceptionManager=null;
	protected WeightManger weightManger=null;
	protected ParameterBuilder paraBuilder=null;
	protected SolrSearch solr;
	
	
	public SolrSearch getSolr() {
		return solr;
	}
	@Resource(name="solrSearch")
	public void setSolr(SolrSearch solr) {
		this.solr = solr;
	}
	@Override
	public abstract void handlerReq(JSONObject params, JSONObject rtnParams,StandContext extParams);
	/**
	 * 将个人信息注册到系统中并返回PID
	 * @param json 注册的个人信息
	 * @param response 保存返回信息
	 */
	protected void createNewPerson(JSONObject json,JSONObject response,boolean genpid)
	{
		//获取PID并组装到注册数据
		paraBuilder.buildAddParams(json,genpid);
		
		//将数据持久化
		
		//if(null == response.get("error") || response.get("error").equals(""))
		
		if(pidDao.saveInfo(json, response))
		{
			//response.put("PID", json.get("PID"));
			return;
		}
		
	}
	
	/**
	 * 根据 入参从MongoDB中 查询 个人自然 信息 查询条件为 or 关系
	 * @param json 入参
	 * @param persons  查询到的个人自然信息
	 * @param response 系统信息
	 */
	@Deprecated
	protected void queryPerson(JSONObject json,JSONObject persons,JSONObject response)
	{
		 //persons=new JSONObject();
		//查询是否存在注册人的个人基本信息
		JSONObject query=new JSONObject();
		paraBuilder.buildQueryParams(json, query);
		
		
		if(0>=query.length())
		{
		  response.put("error", exceptionManager.getCode(new ParameterNullException()));
		  return;
		}
		/*
		System.out.println("query:"+query);*/
		pidDao.getInfo(query, persons);		
	}
	/**
	 * 根据 入参从MongoDB中 查询 个人自然 信息 查询条件 为 and 关系
	 * @param json
	 * @param persons
	 * @param response
	 */
	@Deprecated
	protected void queryPersonByAnd(JSONObject json,JSONObject persons,JSONObject response)
	{
		JSONObject query=new JSONObject();
		paraBuilder.buildQueryParams(json, query);
		
		
		if(0>=query.length())
		{
		  response.put("error", exceptionManager.getCode(new ParameterNullException()));
		  return;
		}
		/*
		System.out.println("query:"+query);*/
		System.out.println("query parameter:"+query);
		pidDao.getInfoByAnd(query, persons);	
	}
	private void parseSolrResult(SolrDocument p,JSONObject ps)
	{		
		//这里需要修改 key 为 phones 等多值时的处理
		for(String key:p.keySet())
		{
			if(p.get(key) instanceof List )
			{			
			   List l=(List) p.get(key);
			   JSONArray arr=new JSONArray();
			   for(int i=0;i<l.size();i++)
			   {					   
				   JSONObject obj=new JSONObject();
				   String value=l.get(i).toString();  
				   StringTokenizer token = new StringTokenizer(value,"_");
				  // System.out.println("token size:"+token.countTokens());
				   if(token.countTokens()>1)
				   {
					   obj.put("number", token.nextToken());
					   obj.put("type", token.nextToken());
					   arr.put(obj);
				   }else
				   {
					  //将 字符串 转换成 JSONObject 对象
					  JSONObject vo=new JSONObject(value); 
					  arr.put(vo);
					  //ps.put(key, arr);
					  //continue;
				   }				  
			   }
			   if(key.equals("credentials1") || key.equals("credentials2"))
			   {
				   if(ps.keySet().contains("credentials") && ps.get("credentials") instanceof JSONArray)
				   {
					   for(int j=0;j<arr.length();j++)
					   {
					    ps.getJSONArray("credentials").put(arr.get(j));
					   }
				   }else
				   {						   
					   ps.put("credentials", arr);
				   }
			   }else
			   {
				   ps.put(key, arr); 
			   }
			}else
			{
				ps.put(key, p.get(key));
			}
		}
	}
	/**
	 * 按PID从Solr中查询信息
	 * @param json 入参 包涵PID
	 * @param persons 查询出的信息
	 * @param response 附加信息
	 */
	@Deprecated
	protected void queryInSolrByPid(JSONObject json,JSONObject persons,JSONObject response)
	{
		//SolrSearch solr=new SolrSearch();
		//solr.init();		
		StringBuilder sb = new StringBuilder();
		sb.append("PID").append(":").append(json.get("PID"));
		try {
			SolrDocumentList result=solr.solrj(sb.toString());
			if(result.size()>0)
			{
				JSONObject ps=new JSONObject();
				SolrDocument p=result.get(0);
				//解析Solr返回数据
				this.parseSolrResult(p, ps);
				persons.put(ps.getString("PID"), ps);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 按照唯一性字段查询信息
	 * @param json
	 * @param persons
	 * @param response
	 */
	@Deprecated
	protected void queryInSolrByUniqueCard(JSONObject json,JSONObject persons,JSONObject response)
	{
		//SolrSearch solr=new SolrSearch();
		//solr.init();	
		StringBuilder sb = new StringBuilder();
		Map<String,Field> map=this.weightManger.getUniquefields();
		//组装条件
		for(String key:map.keySet())
		{
			if(json.keySet().contains(key))
			{
				if(json.get(key) instanceof JSONArray)
				{
					JSONArray array=json.getJSONArray(key);
					for(int i=0;i<array.length();i++)
					{
						JSONObject obj=(JSONObject) array.get(i);
						if(obj.keySet().contains(PropertiesUtil.TYPE)&&map.get(key).hasType(obj.get(PropertiesUtil.TYPE).toString()))
						{
							if(obj.keySet().contains(PropertiesUtil.NUMBER))
							{ String card=obj.getString(PropertiesUtil.NUMBER)+"_"+obj.getString(PropertiesUtil.TYPE);
							  if(sb.length()>0)
							  {
								  sb.append(PropertiesUtil.OR);
							  }
							 sb.append(key).append(":").append(card).append("^").append(this.weightManger.getMax()+10);
							}
						}
					}
				}else {
					if(sb.length()>0)
					{
						sb.append(PropertiesUtil.OR);						
					}
					sb.append(key).append(":").append(json.get(key)).append("^").append(this.weightManger.getMax()+10);
				}
			}
		}//组装条件结束
		try {
			//从Solr中查询出数据
			SolrDocumentList result=solr.solrj(sb.toString());
			//解析从Solr中查询出的数据
			if(null!=result && result.size()>0)
			{
				JSONObject ps=new JSONObject();
				SolrDocument p=result.get(0);
				//解析Solr返回数据
				this.parseSolrResult(p, ps);
				if(p.containsKey(PropertiesUtil.PID))
				{
				 persons.put(p.get(PropertiesUtil.PID).toString(), ps);
				 response.put(PropertiesUtil.PID, p.get(PropertiesUtil.PID));
				}else
				{
					response.put("error", "exist data no pid ");
				}
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 按权重比从Solr中查询信息
	 * @param json
	 * @param persons
	 * @param response
	 */
	@Deprecated
	protected void queryInSolrByWeight(JSONObject json,JSONObject persons,JSONObject response)
	{
		//SolrSearch solr=new SolrSearch();
		//solr.init();		
		StringBuilder sb = new StringBuilder();
		//组装查询条件
		for(Object key:json.keySet())
		{
			if(json.get(key.toString()) instanceof JSONArray)
			{
				JSONArray array=json.getJSONArray(key.toString());
				for(int i=0;i<array.length();i++)
				{    float w=0;
					if(key.equals(PropertiesUtil.CREDENTIALS) || key.equals(PropertiesUtil.PHONES))
					{
						JSONObject obj=array.getJSONObject(i);
						String num=obj.getString(PropertiesUtil.NUMBER);
						String type=obj.getString(PropertiesUtil.TYPE);
						String card=num+"_"+type;
						if(weightManger.getFields().containsKey(key)&&weightManger.getFields().get(key).getTypes().containsKey(type))
						{
							w=weightManger.getFields().get(key).getType(type).getWeight();
						}else if(weightManger.getFields().get(key).hasType(type))
						{
							w=weightManger.getMax();
						}
						if(sb.length()>0)
						{
							sb.append(PropertiesUtil.OR);
						}				
						 sb.append(key).append(":").append(card).append("^").append(w);
						
					}else {
						if(weightManger.getFields().containsKey(key)&&weightManger.getFields().get(key).getTypes().containsKey("name"))
						{
							w=weightManger.getFields().get(key).getType(PropertiesUtil.NAME).getWeight();
						}else if(weightManger.getUniquefields().containsKey(key))
						{
							w=weightManger.getMax();
						}
						if(sb.length()>0)
						{
							sb.append(PropertiesUtil.OR);
						}				
						 sb.append(key).append(":").append(json.getString(key.toString())).append("^").append(w);
						
					}
				}
			}else
			{
				float w=0;
				if(weightManger.getFields().containsKey(key)&&weightManger.getFields().get(key).getTypes().containsKey("name"))
				{
					w=weightManger.getFields().get(key).getType(PropertiesUtil.NAME).getWeight();
				}else if(weightManger.getUniquefields().containsKey(key))
				{
					w=weightManger.getMax();
				}
				if(sb.length()>0)
				{
					sb.append(PropertiesUtil.OR);
				}				
				 sb.append(key).append(":").append(json.getString(key.toString())).append("^").append(w);
				
			}
		}//组装查询条件结束
		String 	keyword=sb.toString();
		//System.out.println("query string :"+keyword);
		try {
			//通过 client查询数据
			SolrDocumentList result=solr.solrj(keyword);
			if(null!=result&&result.size()>0)
			{
				//如果 查出的数据最大分数大于 weight设置上限 返回其PID
				if(weightManger.getMax()<result.getMaxScore())
				{
				JSONObject ps=new JSONObject();
				SolrDocument p=result.get(0);
				//解析Solr返回数据
				this.parseSolrResult(p, ps);
				if(p.containsKey(PropertiesUtil.PID))
				{
				 persons.put(p.get(PropertiesUtil.PID).toString(), ps);
				 response.put(PropertiesUtil.PID, p.get(PropertiesUtil.PID));
				}else
				{
					response.put("error", "exist data no pid ");
				}
			   }else if(result.getMaxScore()<weightManger.getMax()&& result.getMaxScore()>weightManger.getMin())
				{
					if( result.get(0).containsKey(PropertiesUtil.PID))
					{
						//JSONArray arr=new JSONArray();
						JSONObject simi=new JSONObject();						
						simi.put(PropertiesUtil.PID, result.get(0).get(PropertiesUtil.PID));
						//arr.put(simi);
					  response.put(PropertiesUtil.SIMILARITY, simi);
					}
				}
		    //如果 查出数据分数 大于下限 小于上限 判断为相似
			}else 
			{
				
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 从Solr中查询数据是否存在
	 * @param json
	 * @param persons
	 * @param response
	 */
	public void queryInSolr(JSONObject json,JSONObject persons,JSONObject response)
	{
		
		pidDao.getInfo(json, response);		
		if(response.keySet().contains(PropertiesUtil.PERSON))
		{
			JSONObject person=response.getJSONObject(PropertiesUtil.PERSON);
			if(person.keySet().contains(PropertiesUtil.PID))
			persons.put(person.getString(PropertiesUtil.PID),person);
			response.remove(PropertiesUtil.PERSON);
		}
		
	}
	/**
	 * 更新数据  
	 * @param json 新的人员信息
	 * @param person 老的人员信息
	 * @param response 系统信息
	 * @throws Exception 
	 */
	protected  void updatePerson(JSONObject json,JSONObject person,JSONObject response,boolean updateAll) throws Exception
	{
		
		//根据一定的规则将 json 和 person 的数据 整合到一起
		//具体的规则见 paraBuilder 的实现类
		if(null!=json&&null!=person)
		{
		  paraBuilder.buildUpdateParams(json,person,updateAll);
		}else{
			throw new ParameterNullException();
		}		
		if(pidDao.updateInfo(person, response))
		{
		  response.put("PID", person.get("PID"));
		}
		
		
	}
	/**
	 * 判断记录是否被合并
	 * @param p
	 * @return
	 */
	protected boolean isMerged(JSONObject p)
	{
		boolean hasSimi=p.keySet().contains(PropertiesUtil.SIMILARITY)&&p.getString(PropertiesUtil.SIMILARITY)!=null;
		boolean merged=p.keySet().contains(PropertiesUtil.STATUS)&&p.getString(PropertiesUtil.STATUS).equals("3");
	  return (hasSimi&&merged);
	}
	public boolean updateByPid(String pid,String field,Object obj)
	{
		//System.out.println("call update by Pid in common data handler");
		return pidDao.updateByPid(pid, field, obj);
	}
	public DaoSupport getPidDao() {
		return pidDao;
	}
	public void setPidDao(DaoSupport pidDao) {
		this.pidDao = pidDao;
	}
	public ExceptionManager getExceptionManager() {
		return exceptionManager;
	}
	public void setExceptionManager(ExceptionManager exceptionManager) {
		this.exceptionManager = exceptionManager;
	}
	public WeightManger getWeightManger() {
		return weightManger;
	}
	public void setWeightManger(WeightManger weightManger) {
		this.weightManger = weightManger;
	}
	public ParameterBuilder getParaBuilder() {
		return paraBuilder;
	}
	public void setParaBuilder(ParameterBuilder paraBuilder) {
		this.paraBuilder = paraBuilder;
	}
	public void handlerReq(Object params, Object rtnParams, Object extParams) {
		// TODO Auto-generated method stub
		return;
	}

}
