package com.livechain.pid.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;

@XmlRootElement(name="persons")
public class PersonList {
   private List<Person> personList=new ArrayList<Person>(); 
   private int count=0;//数据总数
@XmlElement(name="person")
public List<Person> getPersonList() {
	return personList;
}
/**
 * 增加一个人的信息
 * @param e
 */
public void add(Person e)
{
 this.personList.add(e);
}
/**
 * 删除一个人的信息
 * @param index
 */
public void remove(int index)
{
 this.personList.remove(index);
}
public void setPersonList(List<Person> personList) {
	this.personList = personList;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
}
