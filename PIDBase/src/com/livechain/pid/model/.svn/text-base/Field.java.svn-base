package com.livechain.pid.model;

import java.util.HashMap;
import java.util.Map;
/**
 * 字段包括字段名和字段权重比
 * @author alenzhai 2013-05-24
 *
 */
public class Field {
	private String name;
	//指明字段设置了 unique=true field里面的
	private String comment;	
	private Map<String,Type> types;
	private String regx;	
	public Field()
	{
		types=new HashMap<String,Type>();
	}
	public String getRegx() {
		return regx;
	}
	public void setRegx(String regx) {
		this.regx = regx;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Type> getTypes() {
		return types;
	}
	public void setTypes(Map<String, Type> types) {
		this.types = types;
	}
	public Type getType(String type)
	{
		return this.types.get(type);
	}
   public void addType(String type,float weight)
   {
	   types.put(type, new Type(type,null,weight));
   }
   public void addType(String type,float weight,String comment,String regx,boolean unique)
   {
	   types.put(type, new Type(type,comment,weight,regx,unique));
   }
   public float getWeight(String type)
   {
	   return types.get(type)!=null?types.get(type).getWeight():0.0F;
   }
    public boolean hasRegx()
    {
    	return this.regx!=null&&!this.regx.equals("");
    }
	public boolean hasType(String type)
	{		
		return types.containsKey(type);
	}
	
	
}
