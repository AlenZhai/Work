package com.livechain.pid.rest.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.pid.rest.model.GetlockpersonsIn;
import com.livechain.pid.rest.model.GetlockpersonsOut;
import com.livechain.mybatis.dao.CredentialsMapper;
import com.livechain.mybatis.dao.OrgCodesMapper;
import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.dao.PhonesMapper;
import com.livechain.mybatis.model.Credentials;
import com.livechain.mybatis.model.CredentialsExample;
import com.livechain.mybatis.model.OrgCodesExample;
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.PhonesExample;
//获取注销个人信息列表
@Component
public class GetlockpersonsServiceImpl {
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
    public GetlockpersonsOut getlockpersonslist(GetlockpersonsIn in){
    	GetlockpersonsOut out=new GetlockpersonsOut();
    	out.setTitle("获取注销个人信息列表");
    	ConverUtils.handleCallBack(out,in.getCallback());
    	//判断pid是否为空
    	if(in.getStart()!=null&&!in.getStart().equals("")){
    		//判定接收是否有数据
    		 if(in.getPagesize()!=null&&!in.getPagesize().equals("")){
    			//判断选择的信息是否为空 且是有否有值
    		 	  if(in.getPname()==null||in.getPname().equals("")){
    				   in.setPname(null);
    		 	     }
    		 	  if(in.getGender()==null||in.getGender().equals("")){
    				  in.setGender(null);
    			     }
    		 	  if(in.getBirthday()==null||in.getBirthday().equals("")){
    				  in.setBirthday(null);
    			     }
    		 	  
    		 	  if(in.getIdcard()==null||in.getIdcard().equals("")){
    		 		  in.setIdcard(null);
    		 	    }
    		 	  if(in.getCard()==null||in.getCard().equals("")){
    		 		  in.setCard(null);
    		 	    } 
    		 	//把size和pagesize封装在RowBounds里面
    				RowBounds rowBounds=null;
    				if(Integer.valueOf(in.getPagesize())>0)
    				{
    					rowBounds=new RowBounds(Integer.valueOf(in.getStart()),Integer.valueOf(in.getPagesize()));
    				}
    				else
    				{
    					rowBounds=new RowBounds();
    				}
    				try{
    				  List<Person> list=dao.getPersonslock(rowBounds,in);
    				  Person person =null;
    				  if(list!=null&&list.size()>0){
    				  //通过循环  拿出List里面的PID
    				for(int i = 0;i<list.size();i++){
    				    person=list.get(i);
    				    String province =person.getAddrprovince()==null?"":person.getAddrprovince();
    				    String city =person.getAddrcity()==null?"":person.getAddrcity();
    				    String county =person.getAddrcounty()==null?"":person.getAddrcounty();
    				    String town =person.getAddrtown()==null?"":person.getAddrtown();
    				    String village =person.getAddrvillage()==null?"":person.getAddrvillage();
    				    String houseid =person.getAddrhouseid()==null?"":person.getAddrhouseid();
    				    person.setAddr(province+city+county+town+village+houseid);
    				   //证件example里面赋值
    				    CredentialsExample cexample=new CredentialsExample();
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
    					//根据得到的List里面的PID 查询先关联的三个表
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
    					  }
    					 out.setCount(dao.getPersonslockcount(in));
    					 out.setDatalist(list);
    					 out.setRet("0");
    					 out.setMsg("获取注销个人信息列表成功");
    					 return out;
    				   }else{
    					  out.setRet("0");
    					  out.setMsg("无对应的数据");
    				   }
    				   }catch(Exception e){
    		  		          e.printStackTrace();
    						  out.setRet("401");
    						  out.setMsg("数据库异常");
    						  return out;
    		  			}
    		 	     }else
    		          {
    		          out.setRet("101");
    				  out.setMsg("需要传入用户必要的数据");
    			     }

    				  }else
    		            {
    		              out.setRet("101");
    					  out.setMsg("需要传入用户必要的数据");
    				     }
    					  return out;
    		           }
    		 	        
    		       }
