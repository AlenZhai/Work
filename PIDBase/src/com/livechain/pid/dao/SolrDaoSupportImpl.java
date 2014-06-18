package com.livechain.pid.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.livechain.pid.model.Field;
import com.livechain.pid.solr.SolrIndex;
import com.livechain.pid.solr.SolrSearch;
import com.livechain.pid.util.PIDUtil;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.weight.WeightManger;

public class SolrDaoSupportImpl implements DaoSupport {
	
	protected SolrSearch solr;
	private SolrIndex solrIndex;
	private WeightManger weightMgr;
	public SolrSearch getSolr() {
		return solr;
	}

	public void setSolr(SolrSearch solr) {
		this.solr = solr;
	}

	public SolrIndex getSolrIndex() {
		return solrIndex;
	}

	public void setSolrIndex(SolrIndex solrIndex) {
		this.solrIndex = solrIndex;
	}

	public WeightManger getWeightMgr() {
		return weightMgr;
	}

	public void setWeightMgr(WeightManger weightMgr) {
		this.weightMgr = weightMgr;
	}

	@Override
	public boolean delete(Object params, Object rtn) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public void getInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		/**
		 * 如果入参中有PID那么就根据PID来查询
		 */
		if(p.keySet().contains(PropertiesUtil.PID)&&PIDUtil.isPID(p.getString(PropertiesUtil.PID)))
		{			
			getInfoByPID(p.getString(PropertiesUtil.PID),response);
			if(response.keySet().contains(PropertiesUtil.PERSON))
			   return ;
		}
		/**
		 * 如果入参中有类似身份证之类的唯一性证件信息
		 * 那么就根据第一个唯一性证件信息来查询
		 */
		if(p.keySet().contains(PropertiesUtil.CREDENTIALS)&&p.getJSONArray(PropertiesUtil.CREDENTIALS)!=null)
		{
			JSONArray credentials=p.getJSONArray(PropertiesUtil.CREDENTIALS);
			for(int i=0;i<credentials.length();i++)
			{ 
				JSONObject cre=credentials.getJSONObject(i);
				if(null!= cre&&!cre.getString(PropertiesUtil.TYPE).equals("")&&weightMgr.getFields().get(PropertiesUtil.CREDENTIALS).getType(cre.getString(PropertiesUtil.TYPE)).isUnique())
				{
					getInfoByCredentials(cre.getString(PropertiesUtil.TYPE),cre.getString(PropertiesUtil.NUMBER),response);
					if(response.keySet().contains(PropertiesUtil.PERSON))
					{
					  return ;
					}
				}
			}
		}
		/**
		 * 若以上信息都没有则根据信息匹配度来查询
		 */
		getInfoByWeight(p,response);
		//System.out.println(p);
		//System.out.println(response);
	}

	@Override
	public boolean isExsit(Object params, Object rtn) {
		// TODO Auto-generated method stub
		return false;
	}
   
	@Override	
	public boolean saveInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		boolean flag=true;
		JSONObject p=(JSONObject)params;
		JSONObject response=(JSONObject)rtn;
		SolrInputDocument doc=new SolrInputDocument();
		try {
			this.createSolrDoc(doc, p);
			//System.out.println(doc);
			//System.out.println(p);
			//doc.addField(PropertiesUtil.PID,PIDUtil.generatePID());
			solrIndex.indexedByDoc(doc);
			//solrIndex.commit();
			//solrIndex.optimize();
			response.put(PropertiesUtil.PID, doc.getFieldValue(PropertiesUtil.PID));
		} 
		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag=false;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag=false;
		}
		catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

	@Override
	public boolean updateInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		
		return this.saveInfo(params, rtn);
	}

	@Override
	public void getInfoByAnd(Object params, Object rtn) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 根据权重来查询人员信息
	 * @param params 入参人员信息
	 * @param response
	 */
	private void getInfoByWeight(JSONObject json,JSONObject response)
	{
		StringBuilder sb = new StringBuilder();
		//组装查询条件
		for(Object key:json.keySet())
		{
			if(!weightMgr.getFields().containsKey(key))
			{
				continue;
			}
			Field field=weightMgr.getFields().get(key);
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
						if(field.hasType(type)&&field.getType(type).isUnique())
						{
							 w=weightMgr.getMax();
							
						}else if(field.hasType(type))
						{
							w=field.getTypes().get(type).getWeight();
						}
						if(sb.length()>0)
						{
							sb.append(PropertiesUtil.OR);
						}				
						 sb.append(key).append(":").append(card).append("^").append(w);
						
					}else {
						if(field.hasType(PropertiesUtil.WEIGHT)&&field.getType(PropertiesUtil.WEIGHT).isUnique())
						{
							w=weightMgr.getMax();							
						}else if(field.hasType(PropertiesUtil.WEIGHT))
						{
							w=field.getType(PropertiesUtil.WEIGHT).getWeight();
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
				if(field.hasType(PropertiesUtil.WEIGHT)&&field.getType(PropertiesUtil.WEIGHT).isUnique())
				{
					w=weightMgr.getMax();					
				}else if(field.hasType(PropertiesUtil.WEIGHT))
				{
					w=weightMgr.getFields().get(key).getType(PropertiesUtil.WEIGHT).getWeight();
				}
				if(sb.length()>0)
				{
					sb.append(PropertiesUtil.OR);
				}		
				//System.out.println(key+"=>"+w);
				//System.out.println("unique=>"+weightMgr.getFields().get(key).getType(PropertiesUtil.WEIGHT).isUnique());
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
				//System.out.println(result.getMaxScore());
				if(weightMgr.getMax()<=result.getMaxScore())
				{
				JSONObject ps=new JSONObject();
				SolrDocument p=result.get(0);
				if(p.containsKey(PropertiesUtil.SCORE));
				{
					p.remove(PropertiesUtil.SCORE);//System.out.println(p);
				}
				//解析Solr返回数据
				this.parseSolrResult(p, ps);
				if(p.containsKey(PropertiesUtil.PID))
				{
				// persons.put(p.get(PID).toString(), ps);
				 //response.put(PID, p.get(PID));
				 response.put(PropertiesUtil.PERSON, ps);
				}else
				{
					response.put("error", "exist data no pid ");
				}
			   }else if(result.getMaxScore()<weightMgr.getMax()&& result.getMaxScore()>weightMgr.getMin())
				{
					if( result.get(0).containsKey(PropertiesUtil.PID))
					{
						//JSONArray arr=new JSONArray();
						//JSONObject simi=new JSONObject();						
						//simi.put(PropertiesUtil.PID, result.get(0).get(PropertiesUtil.PID));
						//arr.put(simi);
					  response.put(PropertiesUtil.SIMILARITY, result.get(0).get(PropertiesUtil.PID));
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
	 * 根据唯一性证件查询人员信息
	 * @param type 证件类型
	 * @param number 证件号
	 * @param response
	 */
	private void getInfoByCredentials(String type,String number,JSONObject response)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(PropertiesUtil.CREDENTIALS).append(":").append(type).append("_").append(number);
		try {
			SolrDocumentList result=solr.solrj(sb.toString());
			if(result.size()>0)
			{
				JSONObject ps=new JSONObject();
				SolrDocument p=result.get(0);
				if(p.containsKey(PropertiesUtil.SCORE));
				{
					p.remove(PropertiesUtil.SCORE);//System.out.println(p);
				}
				//解析Solr返回数据
				this.parseSolrResult(p, ps);
				response.put(PropertiesUtil.PERSON, ps);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据PID查询人员信息
	 * @param pid 
	 * @param response
	 */
	private void getInfoByPID(String pid,JSONObject response)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(PropertiesUtil.PID).append(":").append(pid);
		try {
			SolrDocumentList result=solr.solrj(sb.toString());
			if(result.size()>0)
			{
				JSONObject ps=new JSONObject();
				SolrDocument p=result.get(0);
				if(p.containsKey(PropertiesUtil.SCORE));
				{
					p.remove(PropertiesUtil.SCORE);//System.out.println(p);
				}
				//解析Solr返回数据				
				this.parseSolrResult(p, ps);
				response.put(PropertiesUtil.PERSON, ps);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 将JSONObject 转换成SolrInputDocument
	 * @param d
	 * @param p
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	private void createSolrDoc(SolrInputDocument docment,JSONObject p) throws JSONException
	{
		
			JSONObject json=p;
			//System.out.println("json:"+json);
			if(json.keySet().contains("_id"))
			{
				json.remove("_id");
			}
			if(json.keySet().contains(PropertiesUtil.PERSON))
			{
				json.remove(PropertiesUtil.PERSON);
			}
			SolrInputDocument doc=docment;
			for(Object k:json.keySet())
			{
				if(json.get(k.toString()) instanceof JSONArray)
				{
					JSONArray array=json.getJSONArray(k.toString());
					for(int index=0;index<array.length();index++)
					{   
						
						if(k.equals(PropertiesUtil.PHONES) || k.equals(PropertiesUtil.CREDENTIALS))
						{
							JSONObject j=(JSONObject) array.get(index);
							String card=j.getString(PropertiesUtil.NUMBER)+"_"+j.getString(PropertiesUtil.TYPE);
							if(k.equals(PropertiesUtil.CREDENTIALS))
							{
								Field f=weightMgr.getFields().get(k);
								String type=j.getString(PropertiesUtil.TYPE);
								if(f.getTypes().containsKey(j.get(PropertiesUtil.TYPE))&&f.getType(type).isUnique())
								{
									doc.addField(k.toString(), card);
									
								}else if(f.getTypes().containsKey(j.get(PropertiesUtil.TYPE)))
								{
									doc.addField(k.toString()+"1", card);
								}
							
							}else{
								
								doc.addField(k.toString(), card);
							}
					  }else{
						  String j=null;
						  if(array.get(index) instanceof JSONObject)
						  {
							  JSONObject arrobj=array.getJSONObject(index);
							  for(Object key:arrobj.keySet())
							  {
								  j=arrobj.getString(key.toString());
							  }
						  }	else
						  {
							  j=array.get(index).toString();
						  }
						  if(j!=null)
						  {
						   doc.addField(k.toString(), j);
						  }
					  }
					}
					
				}else
				{			
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(k.equals(PropertiesUtil.CREATEDATE))
					{
						Date d=null;
						boolean error=false;
						try {
							d = format.parse(json.get(k.toString()).toString());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							//d=new Date(Long.valueOf(json.get(k.toString()).toString()));
							error=true;
							
							//System.out.println(d.toString());
							//continue;
						}
						if(error)
						{
							doc.addField(k.toString(), json.get(k.toString()));
						}else{
							doc.addField(k.toString(), d.getTime());
						}						
						//System.out.println(k);
					}else
					{
						doc.addField(k.toString(), json.get(k.toString()));
					}
							
				}
				
			}
			//将数据同步到Solr中				
			//solrIndex.indexedByDoc(doc);
		
	}
	/**
	 * 将SolrDocument转换成JSONObject
	 * @param p
	 * @param ps
	 */
	private void parseSolrResult(SolrDocument p,JSONObject ps)
	{		
		
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
					   obj.put(PropertiesUtil.NUMBER, token.nextToken());
					   obj.put(PropertiesUtil.TYPE, token.nextToken());
					   arr.put(obj);
				   }else
				   {
					  //将 字符串 转换成 JSONObject 对象					   
					  //JSONObject vo=new JSONObject(value);				  
					  arr.put(value);
					  //ps.put(key, arr);
					  //continue;
				   }				  
			   }
			   if(key.equals("credentials1") || key.equals("credentials2"))
			   {
				   if(ps.keySet().contains(PropertiesUtil.CREDENTIALS) && ps.get(PropertiesUtil.CREDENTIALS) instanceof JSONArray)
				   {
					   for(int j=0;j<arr.length();j++)
					   {
					    ps.getJSONArray(PropertiesUtil.CREDENTIALS).put(arr.get(j));
					   }
				   }else
				   {						   
					   ps.put(PropertiesUtil.CREDENTIALS, arr);
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
	 * 按照PID更新指定字段
	 * @param pid
	 * @param field 指定字
	 * @param obj
	 * @return
	 */
	public boolean updateByPid(String pid,String field,Object obj)
	{
		//System.out.println("call update by Pid in solr Dao suppoer ");
		boolean falg=false;
		JSONObject response=new JSONObject();
		getInfoByPID(pid,response);
		if(!response.keySet().contains(PropertiesUtil.PERSON))
		{			
		  return false;
		}
		response=response.getJSONObject(PropertiesUtil.PERSON);
		//System.out.println(response);
		
			response.put(field, obj);
			JSONObject rtn=new JSONObject();
			
			falg=this.saveInfo(response, rtn);	
			System.out.println(falg);
		
		
		return falg;
	}

}
