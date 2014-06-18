package com.livechain.pid.rest.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.mybatis.dao.PersonInDataMapper;
import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.model.PersonExample;
import com.livechain.mybatis.model.PersonInDataExample;
import com.livechain.pid.rest.model.GetempiworkIn;
import com.livechain.pid.rest.model.GetempiworkOut;
import com.livechain.pid.rest.model.GetempiworkPOJO;


@Component
public class GetempiworkService {

	private PersonMapper gwdao;
	private PersonInDataMapper pidmdao;
	public PersonMapper getGwdao() {
		return gwdao;
	}
	public PersonInDataMapper getPidmdao() {
		return pidmdao;
	}
	@Autowired
	public void setPidmdao(PersonInDataMapper pidmdao) {
		this.pidmdao = pidmdao;
	}
	@Autowired
	public void setGwdao(PersonMapper gwdao) {
		this.gwdao = gwdao;
	}
	/**
	 * 
	 * @param gw
	 * @return gow
	 */
	public GetempiworkOut getempiwork(GetempiworkIn gwin){
		GetempiworkOut gwo = new GetempiworkOut();
		gwo.setTitle("获取EMPI注册情况");  
		ConverUtils.handleCallBack(gwo, gwin.getCallback());
		PersonExample example = new PersonExample();
		PersonExample.Criteria crit=example.createCriteria();
		/**
		 * 1.查看传过来的 startdate和enddate是否为空
		 * 2.不为空 将其转换成 日期
		 * 3.将日期加入查询条件
		 */
		//时间类型转换
		if(gwin.getStartdate()!=null&&gwin.getEnddate()!=null){
			try {
				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				Date startdate = formate.parse(gwin.getStartdate());
				Date enddate = formate.parse(gwin.getEnddate());
				crit.andCreatedateLessThanOrEqualTo(enddate);
				crit.andCreatedateGreaterThanOrEqualTo(startdate);
				example.or(crit);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		PersonInDataExample pide = new PersonInDataExample();
		PersonInDataExample.Criteria criteria = pide.createCriteria();
		if(gwin.getStartdate()!=null&&gwin.getEnddate()!=null){
			try {
				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				Date startdate = formate.parse(gwin.getStartdate());
				Date enddate = formate.parse(gwin.getEnddate());
				criteria.andCreatedateLessThanOrEqualTo(enddate);
				criteria.andCreatedateGreaterThanOrEqualTo(startdate);
				pide.or(criteria);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//获取注册总数 管理条数 合并条数
		  try{
			  	int person = gwdao.countByExample(example);
			  	int personindata = pidmdao.countByExample(pide);
			  	int x =personindata-person;
			  	GetempiworkPOJO getempiworkpojo = new GetempiworkPOJO();
			  	getempiworkpojo.setTotal(personindata);
			  	getempiworkpojo.setManager(person);
			  	getempiworkpojo.setMerger(x);
			  	gwo.setDatalist(getempiworkpojo);
			  	gwo.setMsg("正常");
				gwo.setRet("0");
		     }catch(Exception e){
		    	//异常
		        e.printStackTrace();
		        gwo.setRet("401");
		        gwo.setMsg("数据库异常");
			  	}		
	  			return gwo;
  		}
}