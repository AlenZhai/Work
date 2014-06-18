package com.livechain.pid.rest.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.mybatis.dao.CredentialsMapper;
import com.livechain.mybatis.dao.OrgCodesMapper;
import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.dao.PhonesMapper;
import com.livechain.mybatis.model.Credentials;
import com.livechain.mybatis.model.CredentialsExample;
import com.livechain.mybatis.model.OrgCodes;
import com.livechain.mybatis.model.Phones;
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.PhonesExample;
import com.livechain.mybatis.model.CredentialsExample.Criteria;
import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.quartz.QuartzJob;
import com.livechain.pid.rest.model.PersonchangeOut;
@Component
public class PersonchangeService {
	
	private PersonMapper dao;
	private CredentialsMapper credentialsDao;
	private OrgCodesMapper orgcodesDao;
	private PhonesMapper phoneDao;
	private DaoSupport solrDao;
	public DaoSupport getSolrDao() {
		return solrDao;
	}
	@Resource(name="solrDao")
	public void setSolrDao(DaoSupport solrDao) {
		this.solrDao = solrDao;
	}
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
    public PersonchangeOut personchange(Person person,String callback){

    	PersonchangeOut po = new PersonchangeOut();
    	ConverUtils.handleCallBack(po , callback);	
    	
    	//判断pid是否为空
   	   if(person.getPid()!= null){
   		//判定接收是否有数据
   	  	 //  try{
      			dao.selectByPrimaryKey(person.getPid());
      			
    			  //判断查询到的person对象是否为空
    			   if(person.getPname()!=null&&person.getBirthday()!=null&&person.getGender()!=null){  
    				   //对基本信息表进行添加信息
    				   dao.updateByPrimaryKeySelective(person); 
    				  //遍历身份证，如PID与身份证都存在，进行修改，只有PID存在，进行添加。   				  
    				  for(int i=0;i<person.getCredentials().size();i++){	
    					  CredentialsExample credentialsexample = new CredentialsExample();  
    					   Credentials cs = person.getCredentials().get(i);  
    	    				  Criteria ct = credentialsexample.createCriteria();
    	    				  ct.andPidEqualTo(person.getPid());
    	    				  ct.andNumEqualTo(cs.getNum());
    	    				  ct.andTypeEqualTo(cs.getType());				  
    	    				  cs.setPid(person.getPid());   
    	    				  credentialsexample.or(ct);
    	    			   List<Credentials> clist=  credentialsDao.selectByExample(credentialsexample);    	    				
	    				   if(clist!=null && clist.size()>0){
	    					 // credentialsDao.updateByExampleSelectiveperspn(cs,credentialsexample);
	    					   
	    				   }else{
	    					  credentialsDao.insertSelective(cs);
	    				   }
    				   }
    			   
    	   				 //遍历机构，如PID与机构都存在，不变化。只有PID存在，进行添加。
    				  if(person.getOrgcodes()!=null){
//    				   OrgCodes orgcodes = person.getOrgcodes().get(0);
    				  	OrgCodes orgcodes = new OrgCodes();
//	    				   for(int j=0;j< person.getOrgcodes().size();j++){	    				   
//	    				   orgcodes.setPid(person.getPid());   				  		
    				  		if(orgcodes.getPid()!=null&&orgcodes.getOrgcode().equals(null)){
	    					   orgcodesDao.insert(orgcodes);
	    				   }	    				   
    				  } 
    				  
	    			 //遍历电话，如PID 与电话都存在，进行修改，只有PID存在，进行添加。	
	    			   
    				    
    				  if(person.getPhones()!=null){
	    			   for(int z=0;z<person.getPhones().size();z++){	
	    				  PhonesExample phonesexample = new PhonesExample();		
	    				  Phones phones = person.getPhones().get(z); 
	    				  com.livechain.mybatis.model.PhonesExample.Criteria cr = phonesexample.createCriteria(); 
	    				  cr.andPidEqualTo(person.getPid());
	    				  cr.andNumEqualTo(phones.getNum());
	    				  cr.andTypeEqualTo(phones.getType());
	    				  phones.setPid(person.getPid());
	    				  phonesexample.or(cr);
	    				List<Phones> list =   phoneDao.selectByExample(phonesexample);
	    					if(list!=null&&list.size()>0){
	    					//phoneDao.updateByPrimaryKeySelective(phones);
	    					}else{
	    					phoneDao.insertSelective(phones);
	    					   }    			  		 	    					   
	    			   			}
    				  }
	    			   JSONObject jsonperson=new JSONObject();
	    			   jsonperson.put("PID", person.getPid());
	    			   jsonperson.put("pname", person.getPname());
	    			   jsonperson.put("gender", person.getGender());
	    			   jsonperson.put("birthday", person.getBirthday());
	    			   jsonperson.put("email", person.getEmail());
	    			   jsonperson.put("nickname", person.getNickname());
	    			   jsonperson.put("registeretype", person.getRegisteretype());
	    			   jsonperson.put("addrprovince", person.getAddrprovince());
	    			   jsonperson.put("addrcity", person.getAddrcity());
	    			   jsonperson.put("addrcounty", person.getAddrcounty());
	    			   jsonperson.put("addrtown", person.getAddrtown());
	    			   jsonperson.put("addrvillage", person.getAddrvillage());
	    			   jsonperson.put("addrhouseid", person.getAddrhouseid());
	    			   jsonperson.put("nationality", person.getNationality());
	    			   jsonperson.put("marriedstatus", person.getMarriedstatus());
	    			   jsonperson.put("nation", person.getNation());
	    			   jsonperson.put("age", person.getAge());
	    			   jsonperson.put("booldtype", person.getBooldtype());
	    			   jsonperson.put("rh", person.getRh());
	    			   
	    			   if(person.getCredentials()!=null && person.getCredentials().size()>0){
	    			   JSONArray creden = new JSONArray();
	    			   for(int c = 0;c<person.getCredentials().size();c++)
	    			   {
	    				   Credentials cerd = person.getCredentials().get(c);
	    				   JSONObject cer = new JSONObject();
	    				   cer.put("type", cerd.getType());
	    				   cer.put("number", cerd.getNum());
	    				  // cer.put("PID", cerd.getPid());
	    				   creden.put(cer);
	    			   }
	    			   jsonperson.put("credentials", creden);	    			   
	    			   System.out.println(jsonperson);
	    			   }  
	    			   
	    			   
	    			   JSONArray phones =new JSONArray();
	    			  if(person.getPhones()!=null && person.getPhones().size()>0)
	    			   for(int p=0;p<person.getPhones().size();p++)
	    			   {
	    				Phones phon=  person.getPhones().get(p);
	    			   JSONObject phone=new JSONObject();
	    			   phone.put("type", phon.getType());
	    			   phone.put("number", phon.getNum());
	    			   //phone.put("PID", phon.getPid());
	    			   phones.put(phone);
	    			   }
	    			  jsonperson.put("phones", phones);
	    			  JSONObject json=new JSONObject();
	    			 // json.put(person.getPid(), jsonperson);
	    			  JSONObject rtn=new JSONObject();
	    			  solrDao.saveInfo(jsonperson, rtn);
	    			  
    				   po.setRet("101");
    				   po.setMsg("修改信息成功");			   
     				   return po;
    			
    			   }
      		  else{
      			po.setRet("102");
      			po.setMsg("此用户不存在");
				return po;
      		  }
   		  /*}catch (Exception e) {
			e.printStackTrace();
			po.setRet("404");
			po.setMsg("数据库异常");
			//throw new RuntimeException();
			  return po;
		}   */	 
   	  	    
    }	
   	   	po.setRet("102");
	 	po.setMsg("请输入正确的数据");
     	return po; 
    }
  }
