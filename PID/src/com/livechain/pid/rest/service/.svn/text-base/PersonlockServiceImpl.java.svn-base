package com.livechain.pid.rest.service;
import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.model.Person;
import com.livechain.pid.rest.model.PersonlockIn;
import com.livechain.pid.rest.model.PersonlockOut;

@Component
public class PersonlockServiceImpl {
	private PersonMapper dao;
    public PersonMapper getDao() {
		return dao;
	}
    @Autowired
	public void setDao(PersonMapper dao) {
		this.dao = dao;
	}
	public PersonlockOut personlock(PersonlockIn personlockIn){
		PersonlockOut out = new PersonlockOut();
		ConverUtils.handleCallBack(out,personlockIn.getCallback());
		out.setTitle("注销个人信息");
		//判定接收是否有数据
		if(personlockIn.getPid()!=null){
			//判断pid是否赋值了
		if(personlockIn.getPid().equals("")){
			out.setRet("102");
			out.setMsg("请输入正确的数据");
		   }else{
		try {	
			Person person=dao.selectByPrimaryKey(personlockIn.getPid());
			//判断此用户是否存在
			if(person!=null){
				if(person.getIsdel().equals("1")){
					out.setRet("102");
					out.setMsg("此用户已注销");
					return out;
				}
				person.setIsdel("1");
				dao.updateByPrimaryKeySelective(person);
				out.setRet("0");
				out.setMsg("正常执行");
			   	return out;
			}else{
				out.setRet("102");
				out.setMsg("此用户不存在");
				return out;
			}
			} catch (Exception e) {
			e.printStackTrace();
			out.setMsg("数据库异常");
		    out.setRet("401");
			}
			
	      }
		}else{
			out.setRet("101");
			out.setMsg("需要传入用户必要的数据");
		}
		   return out;
	}
  }