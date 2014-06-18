package com.livechain.pid.rest.service;

import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.model.PersonExample;
import com.livechain.mybatis.model.PersonExample.Criteria;
import com.livechain.pid.rest.model.MergerpersonsIn;
import com.livechain.pid.rest.model.MergerpersonsOut;
import com.livechain.mybatis.model.Person;

@Component 
public class MergerpersonsService {
		private PersonMapper pedao;

			
		/*
		 * 相似人员信息合并
		 */
		public PersonMapper getPedao() {
			return pedao;
		}

		@Autowired
		public void setPedao(PersonMapper pedao) {
			this.pedao = pedao;
		}


		public MergerpersonsOut mergerpersons (MergerpersonsIn mi){
			MergerpersonsOut mo = new MergerpersonsOut();
			mo.setTitle("相似人员信息合并");
			ConverUtils.handleCallBack(mo,mi.getCallback());
			
			//逗号隔开子Pid
			String subpids = String.valueOf(mi.getSubpids());
			String[] subid = subpids.split(",");
			
			if(mi.getPid()!=null && subid.length>0){
				Person  pes=new Person();
				pes.setParent(mi.getPid());
				
				for(String s:subid){
					PersonExample pe = new PersonExample();	
					Criteria c1=pe.createCriteria();
					
					c1.andPidEqualTo(s);
					pe.or(c1);
					
					pedao.updateByExampleSelective(pes, pe);	
					
				}
				
				mo.setRet("0");
				mo.setMsg("合并成功");
			}
			return mo;	
		}
}
