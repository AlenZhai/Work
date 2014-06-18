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
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.Phones;
import com.livechain.mybatis.model.CredentialsExample.Criteria;
import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.quartz.QuartzJob;
import com.livechain.pid.rest.model.PersonaddOut;
@Component
public class PersonaddService {
	
	private PersonMapper adddao;
	private CredentialsMapper omdao;
	private PhonesMapper pmdao;
	private OrgCodesMapper ocmdao;
	private DaoSupport solrDao;

	
	public DaoSupport getSolrDao() {
		return solrDao;
	}
	@Resource(name="solrDao")
	public void setSolrDao(DaoSupport solrDao) {
		this.solrDao = solrDao;
	}
	public OrgCodesMapper getOcmdao() {
		return ocmdao;
	}
	@Autowired
	public void setOcmdao(OrgCodesMapper ocmdao) {
		this.ocmdao = ocmdao;
	}
	public PhonesMapper getPmdao() {
		return pmdao;
	}
	@Autowired
	public void setPmdao(PhonesMapper pmdao) {
		this.pmdao = pmdao;
	}
	public CredentialsMapper getOmdao() {
		return omdao;
	}
	 @Autowired
	public void setOmdao(CredentialsMapper omdao) {
		this.omdao = omdao;
	}
	public PersonMapper getAdddao() {
		return adddao;
	}
	 @Autowired
	public void setAdddao(PersonMapper adddao) {
		this.adddao = adddao;
	}
	 public PersonaddOut personadd(Person person,String callback){	
		 PersonaddOut addout = new PersonaddOut();
		 Credentials idcard=null;
		//首先找到 身份证 （type=01)
		 	if(person.getCredentials()!=null && person.getCredentials().size()>0)
		 	{
			 	for(int i=0;i<person.getCredentials().size();i++)
			 	{
			 		idcard=person.getCredentials().get(i);
			 		//如果找到 结束循环
			 		if(idcard.getType().equals("01"))
			 		{
			 			break;
			 		}else
			 		{
			 			idcard=null;
			 		}
			 	}
		 	}else{  //返回 没有身份证信息
		 		addout.setTitle("添加人员信息");
	 			addout.setRet("105");
	 			addout.setMsg("没有身份证信息！");
	 			return addout;
		 	      }
		 	if(idcard==null)
		  	{
		 		addout.setTitle("添加人员信息");
	 			addout.setRet("105");
	 			addout.setMsg("没有身份证信息！");
	 			return addout;
		  	}
		    CredentialsExample om = new CredentialsExample();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
		 	Criteria ct = om.createCriteria();
		 	
			ct.andTypeEqualTo(idcard.getType());
		 	ct.andNumEqualTo(idcard.getNum());	
		 	om.or(ct);
		 	
		 	List<Credentials> list = omdao.selectByExample(om); 		 
	 	
		 	ConverUtils.handleCallBack(addout , callback);		 	
		 	//判断主键是否为空		 	
		 	if(person.getGender()!=null&&person.getBirthday()!=null&&person.getCredentials()!=null&&person.getPname()!=null){	
		 		addout.setRet("102");
		 		addout.setMsg("请输入数据");
		 		//添加数据
		 		if(list.isEmpty()){
		 			adddao.insertSelective(person);
		 			OrgCodes oc = new OrgCodes();
		 			Phones ph = new Phones();
		 			Credentials credentials = new Credentials();
		 			//当身份证信息不为空，进行遍历循环，判断类型不为空，号码不为空。
		 			if(person.getCredentials()!=null){
		 			for(int i=0;i<person.getCredentials().size();i++){			 				
		 			if(person.getCredentials().get(i).getType()!=null&&person.getCredentials().get(i).getNum()!=null){
		 				//将基本信息中的type,num,赋值给credentiale
		 				credentials.setType(person.getCredentials().get(i).getType());
		 				credentials.setNum(person.getCredentials().get(i).getNum());
		 				//获取基本表中PID
		 				credentials.setPid(person.getPid());
		 				//添加身份证信息
		 				omdao.insertSelective(credentials);
		 			}
		 			}
		 			}
		 			
		 			if(person.getOrgcodes()!=null){
		 				ocmdao.insertSelective(oc);
		 			}
		 			
		 			//当电话信息不为空，进行遍历循环，判断类型不为空，号码不为空。
		 			if(person.getPhones()!=null){
		 			for(int y=0;y<person.getPhones().size();y++){
		 			if(person.getPhones().get(y).getType()!=null&&person.getPhones().get(y).getNum()!=null){
		 				//将基本信息中的type,num,赋值给person
		 				ph.setNum(person.getPhones().get(y).getNum());
		 				ph.setType(person.getPhones().get(y).getType());
		 				//获取基本表中PID
		 				ph.setPid(person.getPid());
		 				//添加电话信息
		 				pmdao.insertSelective(ph);
		 			}
		 			}
		 			}
		 			//将 Java对象（POJO）组装成 JOSN 格式同步到solr
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
	    			   //System.out.println(jsonperson);
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
	    			  JSONObject rtn=new JSONObject();
	    			  solrDao.saveInfo(jsonperson, rtn);
	    			  //JSONObject json=new JSONObject();
	    			  //json.put(person.getPid(), jsonperson);
					  //System.out.println(jsonperson);
	    			 
		 			addout.setTitle("添加人员信息");
		 			addout.setRet("0");
		 			addout.setMsg("添加成功！");
				 }else{
					addout.setTitle("添加人员信息");
					addout.setRet("104");
					addout.setMsg("身份证号已存在！");
				 }
		 		} 
//}
			return addout;
}
}