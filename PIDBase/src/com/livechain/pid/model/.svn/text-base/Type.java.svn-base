package com.livechain.pid.model;

public class Type {
	public Type()
	{
		
	}
	public Type(String name,String comment,float weight,String regx,boolean unique)
	{
		this.name=name;
		this.comment=comment;
		this.weight=weight;
		this.regx=regx;
		this.unique=unique;
	}
	public Type(String name,String comment,float weight)
	{
	  this(name,comment,weight,null,false);
	}	
	private String name;
	private String comment;
	private float weight;
	private String regx;	
	public String getRegx() {
		return regx;
	}
	public void setRegx(String regx) {
		this.regx = regx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public boolean isUnique() {
		return unique;
	}
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	public boolean hasRegx()
	{
		return this.regx!=null&&!this.regx.equals("");
	}
	private boolean unique;

}
