package com.livechain.pid.rest.service;

import java.util.List;

import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.mybatis.dao.CredentialsMapper;
import com.livechain.mybatis.dao.OrgCodesMapper;
import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.dao.PhonesMapper;
import com.livechain.mybatis.model.Credentials;
import com.livechain.mybatis.model.CredentialsExample;
import com.livechain.mybatis.model.OrgCodesExample;
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.PhonesExample;
import com.livechain.pid.rest.model.GetsimilaritbypidIn;
import com.livechain.pid.rest.model.GetsimilaritbypidOut;
@Component
public class GetsimilaritbypidService {
	private PersonMapper personmapperdao;
	private CredentialsMapper credentDao;
	private PhonesMapper phonesDao;
	private OrgCodesMapper ocodesDao;

	public OrgCodesMapper getOcodesDao() {
		return ocodesDao;
	}
	@Autowired
	public void setOcodesDao(OrgCodesMapper ocodesDao) {
		this.ocodesDao = ocodesDao;
	}
	public CredentialsMapper getCredentDao() {
		return credentDao;
	}
	@Autowired
	public void setCredentDao(CredentialsMapper credentDao) {
		this.credentDao = credentDao;
	}
	public PhonesMapper getPhonesDao() {
		return phonesDao;
	}
	@Autowired
	public void setPhonesDao(PhonesMapper phonesDao) {
		this.phonesDao = phonesDao;
	}
	public PersonMapper getPersonmapperdao() {
		return personmapperdao;
	}
	@Autowired
	public void setPersonmapperdao(PersonMapper personmapperdao) {
		this.personmapperdao = personmapperdao;
	}

	
	
	public GetsimilaritbypidOut getsimilaritbypid(GetsimilaritbypidIn gin){
		GetsimilaritbypidOut gout = new GetsimilaritbypidOut();
		gout.setTitle("获取对应相似人员信息");
		
		 //判断Pid是否为空
		 if(gin.getPid()==null||gin.getPid().equals("")){
		
		    gin.setPid(null);
		 }
			//判定接收是否有数据
		 
	  		  try{
	  			  	List<Person> person =  personmapperdao.getsimilaritbypid(gin);
	  			  if(person!=null&&person.size()>0){
	  			  		
	  			  //通过循环  拿出List里面的PID
	  			  	for(int i=0;i<person.size();i++)
	  			  	{	
	  			  	  Person p =person.get(i);
	  			  
	  			  	  String province =p.getAddrprovince()==null?"":p.getAddrprovince();
	  			  	  String city =p.getAddrcity()==null?"":p.getAddrcity();
	  			  	  String county =p.getAddrcounty()==null?"":p.getAddrcounty();
	  			  	  String town =p.getAddrtown()==null?"":p.getAddrtown();
	  			  	  String village =p.getAddrvillage()==null?"":p.getAddrvillage();
	  			  	  String houseid =p.getAddrhouseid()==null?"":p.getAddrhouseid();
	  			  	  p.setAddr(province+city+county+town+village+houseid);
	  			    
	  			    //证件example里面赋值
	  			  	  CredentialsExample cexample=new CredentialsExample();
		  			  CredentialsExample.Criteria ccrit=cexample.createCriteria();
		  			  ccrit.andPidEqualTo(p.getPid());
		  			  cexample.or(ccrit);
		  			//根据得到的List里面的PID 查询先关联的表
		  			  p.setCredentials(credentDao.selectByExample(cexample));
		  			//机构example里面赋值
		  			  PhonesExample phonmple = new PhonesExample();
		  			  PhonesExample.Criteria pcrit = phonmple.createCriteria();
		  			  pcrit.andPidEqualTo(p.getPid());
		  			  phonmple.or(pcrit);
		  			//根据得到的List里面的PID 查询先关联的表
		  			  p.setPhones(phonesDao.selectByExample(phonmple));
		  			  
		  			  
		  			//电话example里面赋值
		  			  OrgCodesExample ocodesmple = new OrgCodesExample(); 
		  			  OrgCodesExample.Criteria ocrit = ocodesmple.createCriteria();
		  			  ocrit.andPidEqualTo(p.getPid());
		  			  ocodesmple.or(ocrit);
		  			//根据得到的List里面的PID 查询先关联的表
		  			  p.setOrgcodes(ocodesDao.selectByExample(ocodesmple));
		  			  
		  			  
		  			 if(p.getCredentials()!=null)
	  			  	  for(int j=0;j<p.getCredentials().size();j++)
	  			  	  {
	  			  		Credentials c= p.getCredentials().get(j);
	  			  		if(c.getType().equals("01"))
	  			  		{
	  			  			p.setIdcard(c.getNum());
	  			  		}
	  			  	  }
	  			  	}	  	  			  	
	  			  	gout.setDatalist(person);
		  			//gout.setPerson(person);
	  			  	 gout.setRet("0");
	  			  	 gout.setMsg("正常");
	  			  	}else{
	  			  		gout.setRet("102");
  					    gout.setMsg("此用户不存在");
  					    return gout;
  					    }
	  		     }catch(Exception e){
	  		    	//数据库异常
	  		        e.printStackTrace(); 		   
	  		        gout.setRet("401");
	  		        gout.setMsg("数据库异常");				
	  			  	} 		    
	  	  		/*}else{
		 		gout.setMsg("无数据");
		 		gout.setRet("101");  	  		
	  	  		}*/
		 	ConverUtils.handleCallBack(gout,gin.getCallback());
		 	return gout;
	}
	
		}