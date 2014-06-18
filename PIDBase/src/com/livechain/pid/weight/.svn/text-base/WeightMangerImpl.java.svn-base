package com.livechain.pid.weight;


import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;



import com.livechain.pid.model.Field;
import com.livechain.pid.model.PIDWeight;
import com.livechain.pid.model.WeightResult;

import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.SystemConfig;
import com.livechain.pid.xml.XMLLoader;
/**
 * 权重管理器
 * @author alenzhai 2013-05-26
 *
 */
public class WeightMangerImpl implements WeightManger {
    private  Map<String,Field> fields=null;//存储各字段的名字和权重
    //private  Map<String,Field> uniquefields=null;//高优先级字段
    private  float max=0F;//权重上限大于此值为同一个人
    private  float min=0F;//权重下限小于此值为不同的人
                                 //大于min 小于 max 为相似
   // private static WeightMangerImpl instance=null;//权重管理器实例 这里是单例
    private final static Log log4j=LogFactory.getLog(WeightMangerImpl.class);
    private  long lastModify=0L;
    private  XMLLoader loader=null;
    
    public WeightMangerImpl()
    {   
    	//uniquefields=new HashMap<String,Field>();
    	fields=new HashMap<String,Field>();
    	
    	
    }
    
    /**
     * 刷新 权重值
     */
    public  void refresh()
    {
    	init();
    }
    /**
     * 通过 WeightLoaderImpl
     * 加载并解析权重XML配置文件
     * 
     */
    public  void init()
    {
    	try {
    		log4j.info("init weight.xml");
    		loader.loader(this,SystemConfig.getConfig());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//加载配置文件 
    	
    	
    	
    }
    /**
     * 1.根据 data 的值 和 srclist 中元素的 匹配情况计算 权重
     * 2.返回 权重最大的 记录 且 这个权重 大于 max 返回的WeightResult 中PID 不为 null （PID保存相同记录的PID）
     * 3.返回 权重最大的 记录 且 这个权重 小于 min 返回的WeightResult 中 PID和PIDLIST 都为null
     * 4.返回 权重最大的 记录 且 这个权重 大于 min 小于 max 返回的WeightResult 中PIDLIST 不为 null （PIDLIST保存相似记录的PID）
     * 5.若同时有多个记录的权重 一样大 且都 大于 min 返回的WeightResult 中PIDLIST 不为 null（PIDLIST保存相似记录的PID）
     * 6.若同时有多个记录的权重 一样大 且都 小于 min 反回 WeightResult 中 PID和PIDLIST 都 为null
     * @param data 传入记录
     * @param srclist 根据参数查询出的可能为同一个人的记录列表
     */
	@Override
	public WeightResult getMaxWeightObj(JSONObject data, List<JSONObject> srclist) {
		// TODO Auto-generated method stub
		WeightResult result=new WeightResult();
		//JSONObject json=(JSONObject)data;
		//log4j.info("get max weight person from src list");
		//PIDWeight maxvalue=new PIDWeight();
		List<PIDWeight> pidMaxList=new ArrayList<PIDWeight>();
		List<String> pidMinList=new ArrayList<String>();	
		float maxweight=0F;
		//过虑出 >= max的数据 和 >= min 且 < max的数据
		//同时记录 最大权重值
		for(int i=0;i<srclist.size();i++)
		{   
			PIDWeight current=new PIDWeight();
			JSONObject src=srclist.get(i);
			current.setPID(((JSONObject)src).getString("PID"));
			current.setWeight(getWeight(data,src));
			if(current.getWeight()>maxweight)
			{
				maxweight=current.getWeight();

				//add by test start
				System.out.println("maxweight"+maxweight);
				
				//add by test end
			}
			if(current.getWeight()>=this.max)
			{ 
				pidMaxList.add(current);								
			}else if(current.getWeight()>this.min)
			{
				pidMinList.add(current.getPID());
			}
		}
		//判断是否存在权重值 >=max 的数据
		if(pidMaxList.size()>0)
		{  			
			//若只有一条数据 的权重值>=max 直接放入 WeightResult 中PID 并返回
			if(pidMaxList.size()==1)
			{				
				result.setPid(pidMaxList.get(0).getPID());
				return result;
			}else
			{   //若有多条数据 那么就 找出权重最大的
				List<String> pids=new ArrayList<String>();
				for(int i=0;i<pidMaxList.size();i++)
				{
					PIDWeight pid=pidMaxList.get(i);
					if(pid.getWeight()==maxweight)
					{
						pids.add(pid.getPID());
					}
				}
				//权重最大的只有一条
				if(pids.size()==1)
				{
					result.setPid(pids.get(0));
					return result;
				}else if(pids.size()>1)
				{
					result.setListPid(pids);
					return result;
				}
							
			}
		}
		//
		if(pidMinList.size()>0)
		{  
			result.setListPid(pidMinList);			
		}		
		return result;
	}
    /**
     *计算并返回 两个记录的权重
     *这个方法目前只能处理卡的多个信息 有待改进
     *@param data  传入记录
     *@param srcdata 系统中记录
     */
	@Override
	public float getWeight(JSONObject data, JSONObject srcdata) {
		// TODO Auto-generated method stub
		JSONObject json=(JSONObject)data;
		JSONObject srcJson=(JSONObject)srcdata;
		float weight=0F;
		//根据入参遍历每个字段比较
		for(Object key :json.keySet())
		{
			if(srcJson.keySet().contains(key))
			{
				//如果字段为多值时
				if(json.get(key.toString()) instanceof JSONArray)
				{
					JSONArray jsonArray=json.getJSONArray(key.toString());
					JSONArray srcArray=srcJson.getJSONArray(key.toString());
					for(int index=0;index<jsonArray.length();index++)
					{
						if(srcArray.get(index) instanceof JSONObject)
						{
						  JSONObject jo=jsonArray.getJSONObject(index);
						  JSONObject so=srcArray.getJSONObject(index);
						  if(jo.get(PropertiesUtil.TYPE).equals(so.get(PropertiesUtil.TYPE)))
						  {
							  if(jo.get(PropertiesUtil.NUMBER).equals(so.get(PropertiesUtil.NUMBER)))
							  {
								if(this.fields.get(key).getTypes().get(jo.get(PropertiesUtil.TYPE)).isUnique())
								{
									return this.max;
								}else
								{
									weight+=this.fields.get(key).getTypes().get(jo.get(PropertiesUtil.TYPE)).getWeight();
								}
							  }
						  }
						}else {continue;}
					}
				}else
				{//单值字段直接比较
					if(json.get(key.toString()).equals(srcJson.get(key.toString())))
					{
					   if(this.fields.get(key).getType(PropertiesUtil.WEIGHT).isUnique())
					   {
						  return this.max; 
					   }else
					   {
						   weight+=this.fields.get(key).getType(PropertiesUtil.WEIGHT).getWeight();						   
					   }
					}
					
				}
			}
		}
		//特殊条件判断 这里的特殊条件可以是可以唯一识别一个人的信息
		/*if(null != this.uniquefields && this.uniquefields.size()>0)
		{
			for(String key :this.uniquefields.keySet())
			{   //System.out.println("===="+key+"=====");
			    //System.out.println(this.uniquefields.get(key).getTypes());
				if(json.keySet().contains(key)&& srcJson.keySet().contains(key))
				{
					if(json.get(key) instanceof JSONArray)
					{  
						
						Field unif=this.uniquefields.get(key);
						for(String type:unif.getTypes().keySet())
						{   JSONArray array=(JSONArray)json.get(key);
						    JSONArray srcArray=(JSONArray)srcJson.get(key);
						    Object typeValue="";
						    Object srcTypeValue="";
						    //在传入参数中找有没有对应 字段
							for(int i=0;i<array.length();i++)
							{
								JSONObject subjson=(JSONObject)array.get(i);
								if(subjson.get("type").equals(type))
								{
									typeValue=subjson.get("number");
								}
							}
							//在系统数据中找有没有对应字段
							for(int i=0;i<srcArray.length();i++)
							{
								JSONObject subjson=(JSONObject)srcArray.get(i);
								if(subjson.get("type").equals(type))
								{
									srcTypeValue=subjson.get("number");
								}
							}
							
							if(!typeValue.equals("") && !srcTypeValue.equals("") && typeValue.equals(srcTypeValue))
							{
								//System.out.println("11======");
								return 100F;
							}
						}
					}else if(json.get(key).equals(srcJson.get(key)))
					{
						//System.out.println("======");
						return 100F;
					}
				}
			}
		}*/
		//外层字段循环
		//System.out.println("==================================");
		/* for(Object key:json.keySet())
		{   //如果两个对象中都有这个字段则进行比较
			//System.out.println(key);
			if(srcJson.keySet().contains(key))
			{  //System.out.println("33===="+key+"=====33"); 
				//判断该字段对象是否为 JSONArray
				if(json.get(key.toString()) instanceof JSONArray)
				{   //System.out.println("22===="+key+"=====22");  
					JSONArray jsonArray=(JSONArray)json.get(key.toString());
				      JSONArray srcJsonArray=(JSONArray)srcJson.get(key.toString());
				   //为JSONArray时进行 遍历数组比较
					for(int i=0;i<jsonArray.length();i++)
					{   
						JSONObject subObject=(JSONObject)jsonArray.get(i);
						for(int j=0;j<srcJsonArray.length();j++)
						{
						  JSONObject subsrcObject=(JSONObject)srcJsonArray.get(j);
						  if(subsrcObject.keySet().contains("type")&&subsrcObject.keySet().contains("number")&&subObject.keySet().contains("type")&&subObject.keySet().contains("number"))
						  {   //System.out.println("11===="+key+"=====11");
							  if(subsrcObject.get("type").equals(subObject.get("type"))&&subsrcObject.get("number").equals(subObject.get("number")))
							  {
								  //System.out.println(key);
								  if(fields.containsKey(key))
								  {
									  Field f=(Field)fields.get(key);
									  String type=subsrcObject.get("type").toString();
									  weight+=f.getTypes().get(type).floatValue();
									 // System.out.println("===="+key+"=====");
								  }
							  }
						  }
						  for(Object subkey:subObject.keySet())
						  {
							  if(subsrcObject.keySet().contains(subkey)&&subsrcObject.get(subkey.toString()).equals(subsrcObject.get(subkey.toString())))
							  {
								  
							  }
						  }
						}
					}
					//不为JSONArray直接比较
				}else {
					
					if(json.get(key.toString()) instanceof Date)
					{						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    Date jsonbir=null;
					    Date srcbir=null;
						try {
							srcbir = sdf.parse((String)srcJson.get(key.toString()));
							jsonbir = (Date)json.get(key.toString());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							
						}
						if(null!=srcbir&& null != jsonbir)
						{
						    if(srcbir.getYear()==jsonbir.getYear()&&srcbir.getMonth()==jsonbir.getMonth()&&srcbir.getDate()==jsonbir.getDate())
						    {   
						    	if(fields.containsKey(key))
						    	{
							    	Field f=(Field)fields.get(key);					
									weight+=f.getTypes().get("name").floatValue();
						        }
						    }
						}
					}else if(json.get(key.toString()).equals(srcJson.get(key.toString())))
				
				    { 
				    	if(fields.containsKey(key))
				    	{
							Field f=(Field)fields.get(key);					
							weight+=f.getTypes().get("name").floatValue();
				    	}
				    }
				}
			}
		}*/
		return weight;
	}
	public Map<String, Field> getFields() {
		return fields;
	}
	public void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}
	
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
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

	@Override
	public Map<String, Field> getUniquefields() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
