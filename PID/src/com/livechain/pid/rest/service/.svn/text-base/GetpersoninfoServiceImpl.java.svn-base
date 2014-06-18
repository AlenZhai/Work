package com.livechain.pid.rest.service;


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
import com.livechain.pid.rest.model.GetpersoninfoIn;
import com.livechain.pid.rest.model.GetpersoninfoOut;
//获取个人信息
@Component
public class GetpersoninfoServiceImpl {
	private PersonMapper dao;
	private CredentialsMapper credentialsDao;
	private OrgCodesMapper orgcodesDao;
	private PhonesMapper phoneDao;
    public CredentialsMapper getCredentialsDao() {
		return credentialsDao;
	}
    @Autowired
	public void setCredentialsDao(CredentialsMapper credentialsDao) {
		this.credentialsDao = credentialsDao;
	}
	public OrgCodesMapper getOrgcodesDao() {
		return orgcodesDao;
	}
	@Autowired
	public void setOrgcodesDao(OrgCodesMapper orgcodesDao) {
		this.orgcodesDao = orgcodesDao;
	}
	public PhonesMapper getPhoneDao() {
		return phoneDao;
	}
	@Autowired
	public void setPhoneDao(PhonesMapper phoneDao) {
		this.phoneDao = phoneDao;
	}
	public PersonMapper getDao() {
		return dao;
	}
    @Autowired
	public void setDao(PersonMapper dao) {
		this.dao = dao;
	}
    
    public GetpersoninfoOut getpersoninfo(GetpersoninfoIn getpersoninfoIn){
  	  GetpersoninfoOut out=new GetpersoninfoOut();
  	  out.setTitle("获取个人信息");
  	  ConverUtils.handleCallBack(out, getpersoninfoIn.getCallback());
  	 
  	  //判断pid是否为空
  	   if(getpersoninfoIn.getPid()!=null){
  		//判定接收是否有数据
  		 if(getpersoninfoIn.getPid().equals("")){
  	  		  out.setRet("102");
  			  out.setMsg("请输入正确的数据");
  	  	    }else{
  		  try{
  			  Person person=dao.selectByPrimaryKey(getpersoninfoIn.getPid());
  			  //判断查询到的person对象是否为空
  			   if(person!=null){
  				   //地址加进去
  				String province =person.getAddrprovince()==null?"":person.getAddrprovince();
  			    String city =person.getAddrcity()==null?"":person.getAddrcity();
  			    String county =person.getAddrcounty()==null?"":person.getAddrcounty();
  			    String town =person.getAddrtown()==null?"":person.getAddrtown();
  			    String village =person.getAddrvillage()==null?"":person.getAddrvillage();
  			    String houseid =person.getAddrhouseid()==null?"":person.getAddrhouseid();
  			    person.setAddr(province+city+county+town+village+houseid);
  				CredentialsExample cexample=new CredentialsExample();
  				//证件example里面赋值
  			  	CredentialsExample.Criteria credentials=cexample.createCriteria();
  			    credentials.andPidEqualTo(person.getPid());
  			  	cexample.or(credentials);
  				//机构example里面赋值
  			  	OrgCodesExample oexample=new OrgCodesExample();
  			    OrgCodesExample.Criteria orgCodes=oexample.createCriteria();
  			    orgCodes.andPidEqualTo(person.getPid());
  			    oexample.or(orgCodes);
  			   //电话example里面赋值
  			  	PhonesExample pexample=new PhonesExample();
  			    PhonesExample.Criteria phones=pexample.createCriteria();
  			    phones.andPidEqualTo(person.getPid());
  			    pexample.or(phones);
  			  //不为空时 通过pid查询另外三个表的相关信息
  				person.setCredentials(credentialsDao.selectByExample(cexample));
  			    person.setOrgcodes(orgcodesDao.selectByExample(oexample));
  			    person.setPhones(phoneDao.selectByExample(pexample));
  			   if(null!=person.getCredentials())
  			  	  for(int j=0;j<person.getCredentials().size();j++)
  			  	  {
  			  		Credentials credential= person.getCredentials().get(j);
  			  		if(credential.getType().equals("01"))
  			  		{
  			  		  person.setIdcard(credential.getNum());
  			  		  break;
  			  		}
  			  	  }
  			    out.setMsg("获取个人信息成功");
	  	  	    out.setRet("0");
	  	  	    out.setPerson(person);
  			    }else{
  			    	out.setRet("102");
  					out.setMsg("此用户不存在");
  					return out;
  			    }
  		        }catch(Exception e){
  		          e.printStackTrace();
				  out.setRet("401");
				  out.setMsg("数据库异常");
				  return out;
  			  	}
	            }
  	            }else
  	            {
  	             out.setRet("101");
  	  			 out.setMsg("需要传入用户必要的数据");
  	  		    }
  	  			return out;
    		  }
	 }