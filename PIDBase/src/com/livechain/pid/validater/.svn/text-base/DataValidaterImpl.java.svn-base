package com.livechain.pid.validater;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.livechain.pid.model.Field;
import com.livechain.pid.util.IdCardUtil;
import com.livechain.pid.util.Properties;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.SystemConfig;
import com.livechain.pid.weight.WeightManger;
import com.livechain.pid.weight.WeightMangerImpl;
import com.livechain.pid.xml.XMLLoader;
import com.sun.mail.iap.Response;

public class DataValidaterImpl implements DataValidater {
     private Map<String,Field> fields;
     private  long lastModify=0L;
     private  XMLLoader loader=null;
	 private WeightManger weightmMg;
	public WeightManger getWeightmMg() {
		return weightmMg;
	}
	public void setWeightmMg(WeightManger weightmMg) {
		this.weightmMg = weightmMg;
	}
	public long getLastModify() {
		return lastModify;
	}
	public void setLastModify(long lastModify) {
		this.lastModify = lastModify;
	}
	public XMLLoader getLoader() {
		return loader;
	}
	public void setLoader(XMLLoader loader) {
		this.loader = loader;
	}
	public Map<String, Field> getFields() {
		return fields;
	}
	public void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}
	public DataValidaterImpl(){
		fields=new HashMap<String,Field>();
		
	}
	public void refresh(){
		init();
	}
	public void init(){
		try {
			loader.loader(this, SystemConfig.getConfig());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public  boolean hasData(JSONObject json,String action) {
		
		return true;
	}
    /**
     * 对数据进行校验和清洗
     */
	@Override
	public synchronized boolean validate(JSONObject validata,String action,JSONObject response) {
		// TODO Auto-generated method stub
		boolean flag=false;
		JSONArray remove=new JSONArray();
		for(Object key:validata.keySet())
		{
			if(null==validata.get(key.toString())||validata.get(key.toString()).equals(""))
			{
				remove.put(key);
			}
		}
		for(int i=0;i<remove.length();i++)
		{
			validata.remove(remove.get(i).toString());
		}
		//判断 传入的参数据 的key 是否在 参数列表中
		Object OPID=null;
		if(validata.keySet().contains("PID"))
		{
			OPID=validata.get("PID");
			validata.remove("PID");
		}
		for(Object key:validata.keySet())
		{     
			//存在的情况
			if(!this.weightmMg.getFields().containsKey(key))
			{
				response.put("msg", "key:"+key+" is not know");
				return false;
			}else{
				//存在的情况  判断 key 下面是否有 多个 type 且多个 type 是否在参数列表中
				 if(this.weightmMg.getFields().get(key).getTypes().size()>1)
				{
					if(validata.get(key.toString()) instanceof JSONArray)
					{    
						JSONArray arr=validata.getJSONArray(key.toString());
						for(int ii=0;ii<arr.length();ii++)
						{
							if(arr.getJSONObject(ii).keySet().contains("type"))
							{
								Object k=arr.getJSONObject(ii).get("type");
								if(!this.weightmMg.getFields().get(key).getTypes().containsKey(k))
								{
									response.put("msg", "key:"+k+" in "+key+" is not know");
									return false;
								}
							}else 
							{
								response.put("msg", "key:"+key+" is not contains type");
								return false;
							}
						}
					}else
					{
						response.put("msg", "key:"+key+" type is not know");
						return false;
					}
				}
			}
		}
		if(null != OPID)
		{
			validata.put("PID", OPID);
		}
		//validata.put("indata", validata.toString());
		if(action.equals("REGPID")||action.equals("GETPID"))
		{
			return regPidValidater(validata,response);
		}
		if(action.equals("UPDATE")||action.equals("PIDTODETAILS"))
		{
			if(!validata.keySet().contains("PID"))
			{
				return false;
			}
			if(validata.keySet().contains("birthday"))
			{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
				try {
					sdf.parse(validata.get("birthday").toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					try {
						Date d=sdf1.parse(validata.get("birthday").toString());
						validata.put("birthday", sdf.format(d));
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();return false;
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();return false;
					}
				}
				
			}
			String pid=validata.get("PID").toString();
			if(pid.length()==32)
			{
				return true;
			}
		}
		if(action.equals("REGCARD"))
		{
			return validata.keySet().contains("PID")&&validata.get("PID")!=null && this.cardToxx(validata);
		}
		if(action.equals("CARDTOPID")|| action.equals("CARDTODETAIL"))
		{
			return this.cardToxx(validata);
		}
		return flag;
	}
	/**
	 * 证件字段数据格式验证
	 * @param json
	 * @return
	 */
	private boolean cardToxx(JSONObject json)
	{
		boolean flag=false;
		flag=json.keySet().contains("credentials")&& 
		     (json.get("credentials") instanceof JSONArray);
		
		JSONArray array=(JSONArray)json.get("credentials");
		for(int i=0;i<array.length();i++)
		{
			Object obj=array.get(i);
			if(obj instanceof JSONObject)
			{
				JSONObject jobj=(JSONObject)obj;
				flag=flag&&jobj.keySet().contains("type")&&jobj.keySet().contains("number");
			}else
			{
			 return false;
			}
		}
		     
		return flag;
	}
	/**
	 * 校验注册或通过基本信息获取PID的参数是否正确
	 * @param validata
	 * @param response
	 * @return
	 */
	private boolean regPidValidater(JSONObject validata,JSONObject response)
	{   
		boolean flag=false;
		Map<String,List<Integer>> remove=new HashMap<String,List<Integer>>();
		 try{
			// for(String key : fields.keySet())
			 for(Object key: validata.keySet())
			 {
				 Field f=null;
				 //if(validata.keySet().contains(key))
				 if(fields.containsKey(key))
				 {
					 f=this.fields.get(key);
				 }
				 if(f!=null){
				 Object data=validata.get(key.toString());
				 if(data instanceof JSONArray)
				 {
					 //处理数据的情况
					 JSONArray array=(JSONArray)data;
					 List<Integer> removeIndex=new ArrayList<Integer>();
					 for(int i=0;i<array.length();i++)
					 {
						 JSONObject obj=array.getJSONObject(i);
						 if(obj.keySet().contains(PropertiesUtil.TYPE)&& obj.keySet().contains(PropertiesUtil.NUMBER))
						 {   
							 if(obj.getString(PropertiesUtil.TYPE).equals("01"))
							 {
								 String number=obj.getString(PropertiesUtil.NUMBER);
								 if(number.length()==15)
								 {
									number= IdCardUtil.old2New(number);
									obj.put(PropertiesUtil.NUMBER, number);
								 }
							 }
							 String regx=f.getTypes().get(obj.getString(PropertiesUtil.TYPE)).getRegx();
							 Pattern pattern = Pattern.compile(regx);
							 Matcher matcher=pattern.matcher(obj.getString(PropertiesUtil.NUMBER));
							 if(!matcher.find())
							 {
								 removeIndex.add(i);
							 }
						 }
					 }
					 remove.put(key.toString(), removeIndex);
					 
				 }else if(key.equals("birthday")||key.equals("workdate"))
				 {
					//处理日期型数据
					try{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    Date d=sdf.parse((String) data);
					    validata.put(key.toString(), sdf.format(d));
			        }catch(ParseException e)
						{  
			        	try{
			        	  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
			        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        	  Date d2=sdf2.parse(data.toString());
			        	  validata.put(key.toString(), sdf.format(d2));
			        	  }catch(ParseException pe){
			        	    response.put("msg", key+" is not a date format 'yyyy-MM-dd'");
							remove.put(key.toString(), null);
			        	  }
						}
				 }else if(key.equals("email"))
				 {
					//处理邮箱数据
					 if(null!=f)
					 {
						String regx= f.getRegx();
						Pattern pattern = Pattern.compile(regx);
						Matcher matcher=pattern.matcher(data.toString());
						 if(!matcher.find())
						 {
							remove.put(key.toString(), null);
						 }
					 }
					
				 }else
				 {
					
					//通用数据处理
					 String str=data.toString();
					 str=str.replaceAll("[_,\\-,*,\\]\\[,\\s,<,>,\\,!,@,#,$,%,\\^,&,\\(,\\),\\+,~,`,\\.]*", "");
				     validata.put(key.toString(), str);
				     if(null != f)
				     {
					     String regx= f.getRegx();
					     if(null !=regx)
					     {
							 Pattern pattern = Pattern.compile(regx);
							 Matcher matcher=pattern.matcher(str);
								 if(!matcher.find())
								 { 							
									remove.put(key.toString(), null);
								 }
					     }
				     }
				 }
				 
				}
			 }
			 for(String k:remove.keySet())
			 {
				 if(validata.get(k) instanceof JSONArray && remove.get(k)!=null)
				 {
					 JSONArray arr=(JSONArray)validata.get(k);
					 List<Integer> list=remove.get(k);
					 for(int index=0;index<list.size();index++)
					 {
						 
						 arr.remove(list.get(index));
					 }
				 }else
				 {
				  validata.remove(k);
				 }
			 }
				  
				  if(validata.keySet().contains("pname")&& validata.keySet().contains("birthday")&& validata.keySet().contains("gender"))
			      {
			    	 flag=true;
			    	
			      }else{
			    	  flag=this.cardToxx(validata);
			    	  if(flag)
			    	  {
			    		  boolean hasunique=false;
			    		  Field creden=weightmMg.getFields().get(PropertiesUtil.CREDENTIALS);
			    		  JSONArray cards=validata.getJSONArray(PropertiesUtil.CREDENTIALS);
			    		  for(int i=0;i<cards.length();i++)
			    		  {
			    			  JSONObject c=cards.getJSONObject(i);
			    			  if(creden.hasType(c.getString(PropertiesUtil.TYPE))&&creden.getType(c.getString(PropertiesUtil.TYPE)).isUnique())
			    			  {
			    				  hasunique=true;
			    			  }
			    		  }
			    		  flag=flag&&hasunique;
			    	  }
			    	 
			      }
		   }catch(Exception e)
			{   	
			   e.printStackTrace();
				return false;
			}
		   
		return flag;
		
	}
	private boolean regPidHas(JSONObject json)
			{ boolean flag=false;
				 flag=json.keySet().contains("pname")&&
			     json.keySet().contains("gender")&&
			     json.keySet().contains("birthday")&&
			     json.keySet().contains("credentials");
			if(!flag)
			{	
				return flag;
			}
			flag=json.get("credentials") instanceof JSONArray && flag;
			if(!flag)
			{  //System.out.println("type not match  ");
				return flag;
			}
			JSONArray credentials = (JSONArray)json.get("credentials");
			boolean tmpflag=false;
			for(int i=0;i<credentials.length();i++)
			{
				JSONObject card=(JSONObject)credentials.get(i);
				if(card.keySet().contains("type")&&card.get("type").equals("01"))
				{
					tmpflag=true;
					break;
				}
			}
			flag=flag && tmpflag;
			return flag;							
	}

}
