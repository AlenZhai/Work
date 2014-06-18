package com.livechain.pid.rest.service;

import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.model.Person;
import com.livechain.pid.rest.model.PersonunlockIn;
import com.livechain.pid.rest.model.PersonunlockOut;
//恢复个人信息
@Component
public class PersonunlockServiceImpl {
	private PersonMapper dao;
    public PersonMapper getDao() {
		return dao;
	}
    @Autowired
	public void setDao(PersonMapper dao) {
		this.dao = dao;
	}
    public PersonunlockOut personunlock(PersonunlockIn in){
    	PersonunlockOut out=new PersonunlockOut();
    	ConverUtils.handleCallBack(out,in.getCallback());
    	out.setTitle("恢复个人信息");
    	//判断是否有数据接收
    	if(in.getPid()!=null&&!in.getPid().equals("")){
    		try{
    		Person person=dao.selectByPrimaryKey(in.getPid());
    		//System.out.println(person);
    		//判断此用户是否是存在的
    		if(person!=null){
    		  if(person.getIsdel().equals("0")){
    			   out.setRet("102");
				   out.setMsg("此用户已恢复个人信息");
				   return out;
    			}
    		  person.setIsdel("0");
    		  dao.updateByPrimaryKeySelective(person);
    		  out.setRet("0");
			  out.setMsg("正常执行");
			  return out;
    		}else{
    		   out.setRet("102");
			   out.setMsg("此用户不存在");
			   return out;
    		}
    		}catch(Exception e){
    		 e.printStackTrace();
    		}
    	  }else{
    	   out.setRet("101");
		   out.setMsg("需要传入用户必要的数据");
    	 }
		 return out;
      }
  }
