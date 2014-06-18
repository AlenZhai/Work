package com.livechain.pid.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;



import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.livechain.mybatis.dao.CredentialsMapper;
import com.livechain.mybatis.dao.OrgCodesMapper;
import com.livechain.mybatis.dao.PersonInDataMapper;
import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.dao.PhonesMapper;
import com.livechain.mybatis.model.Credentials;
import com.livechain.mybatis.model.CredentialsExample;
import com.livechain.mybatis.model.OrgCodes;
import com.livechain.mybatis.model.OrgCodesExample;
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.PersonInDataWithBLOBs;
import com.livechain.mybatis.model.Phones;
import com.livechain.mybatis.model.PhonesExample;

import com.livechain.pid.cache.DataEntity;
import com.livechain.pid.util.PropertiesUtil;

public class MSSQLDaoSupportImpl implements DaoSupport {
    private PersonMapper personDao;
    private CredentialsMapper cardsDao;
    private PhonesMapper phoneDao;
    private OrgCodesMapper orgDao;
    private PersonInDataMapper indataDao;
    
	public PersonMapper getPersonDao() {
		return personDao;
	}
	@Autowired
	public void setPersonDao(PersonMapper personDao) {
		this.personDao = personDao;
	}
   
	public CredentialsMapper getCardsDao() {
		return cardsDao;
	}
	@Autowired
	public void setCardsDao(CredentialsMapper cardsDao) {
		this.cardsDao = cardsDao;
	}

	public PhonesMapper getPhoneDao() {
		return phoneDao;
	}
	@Autowired
	public void setPhoneDao(PhonesMapper phoneDao) {
		this.phoneDao = phoneDao;
	}

	public OrgCodesMapper getOrgDao() {
		return orgDao;
	}
	@Autowired
	public void setOrgDao(OrgCodesMapper orgDao) {
		this.orgDao = orgDao;
	}

	public PersonInDataMapper getIndataDao() {
		return indataDao;
	}
	@Autowired
	public void setIndataDao(PersonInDataMapper indataDao) {
		this.indataDao = indataDao;
	}

	@Override
	public boolean delete(Object params, Object rtn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getInfo(Object params, Object rtn) {
		
	}

	@Override
	public void getInfoByAnd(Object params, Object rtn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExsit(Object params, Object rtn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		JSONObject json=(JSONObject)params;
		JSONObject otherData=(JSONObject)rtn;		
		String pid=null;
		String flag=null;
		
		if(otherData.keySet().contains("PID"))
		{
			pid=otherData.getString("PID");
			DataEntity.person.setPid(pid);			
		}
		if(json.keySet().contains("flag"))
		{
			flag=json.getString("flag");
			json.remove("flag");
		}
		
		if(pid!=null)
		{
			  Person record=null;
			  if(flag!=null&&"2".equals(flag))
			  {
				//插入基本信息
				  record=getPersonFromJson(json);
				  record.setPid(pid);
				  this.personDao.insertSelective(record);
				  /////////////////
				//插入电话信息
					if(json.keySet().contains(PropertiesUtil.PHONES)&&json.get(PropertiesUtil.PHONES)!=null)
					{
						JSONArray arr=json.getJSONArray(PropertiesUtil.PHONES);
						for(int i=0;i<arr.length();i++)
						{
							System.out.println("add a card!");
							Phones phone=DataEntity.phones;
							JSONObject car=arr.getJSONObject(i);
							 phone.setType(null);
							 phone.setNum(null);
							if(car.keySet().contains(PropertiesUtil.TYPE))
							{
								phone.setType(car.getString(PropertiesUtil.TYPE));
							}								
							if(car.keySet().contains(PropertiesUtil.NUMBER))
							{
							  phone.setNum(car.getString(PropertiesUtil.NUMBER));
							}
							phone.setPid(pid);
							phone.setRegtime(new Date());				
							///
							/*PhonesExample example=new PhonesExample();
							PhonesExample.Criteria c= example.createCriteria();
							c.andTypeEqualTo(phone.getType());
							c.andNumEqualTo(phone.getNum());
							c.andPidEqualTo(phone.getPid());
							example.or(c);*/
						//if(phoneDao.selectByExample(example).size()<=0){
							this.phoneDao.insertSelective(phone);
						//}
						}
					}
										
					if(json.keySet().contains(PropertiesUtil.ORGCODE))
					{
						OrgCodes org=DataEntity.org;
						org.setPid(pid);
						org.setOrgcode(json.getString(PropertiesUtil.ORGCODE));
						org.setUploadtime(new Date());
						OrgCodesExample example=new OrgCodesExample();
						OrgCodesExample.Criteria c=example.createCriteria();
						c.andOrgcodeEqualTo(org.getOrgcode());
						c.andPidEqualTo(org.getPid());
						example.or(c);
						//if(orgDao.selectByExample(example).size()>0){
						this.orgDao.insertSelective(org);
					//}
					}
				 }			
	          
		//插入证件信息
			boolean f=flag!=null&&("3".equals(flag)||"2".equals(flag));
		if(json.keySet().contains(PropertiesUtil.CREDENTIALS)&&json.get(PropertiesUtil.CREDENTIALS)!=null&&f)
		{
			JSONArray arr=json.getJSONArray(PropertiesUtil.CREDENTIALS);
			for(int i=0;i<arr.length();i++)
			{   //System.out.println("add a card!");
				Credentials card=DataEntity.cards;
				card.setNum(null);
				card.setType(null);
				JSONObject car=arr.getJSONObject(i);
				if(car.keySet().contains(PropertiesUtil.TYPE))
				card.setType(car.getString(PropertiesUtil.TYPE));
				if(car.keySet().contains(PropertiesUtil.NUMBER))
				card.setNum(car.getString(PropertiesUtil.NUMBER));
				
				card.setRegtime(new Date());
				card.setPid(pid);
				
				/*CredentialsExample example=new CredentialsExample();
				CredentialsExample.Criteria c= example.createCriteria();
				c.andTypeEqualTo(card.getType());
				c.andNumEqualTo(card.getNum());
				c.andPidEqualTo(card.getPid());
				example.or(c);				*/
				//if(cardsDao.selectByExample(example).size()<=0){
				this.cardsDao.insertSelective(card);			
				//	}
			}
		  }
		}
		PersonInDataWithBLOBs data=DataEntity.personindata;
		data.setAction(null);
		if(otherData.keySet().contains("action"))
		{
		 data.setAction(otherData.getString("action"));
		}
		data.setClient(null);
		if(otherData.keySet().contains("client"))
		{
		 data.setClient(otherData.getString("client"));
		}
		
		data.setCreatedate(new Date());
		data.setInparam(null);
		if(otherData.keySet().contains("inparam"));
		{
		 data.setInparam(otherData.getString("inparam"));
		}		
		data.setOutparam(null);
		if(otherData.keySet().contains("outparam"))
		{
		 data.setOutparam(otherData.getString("outparam"));
		}
		data.setPid(null);
		if(otherData.keySet().contains("PID"))
		{
		  data.setPid(otherData.getString("PID"));
		}
		this.indataDao.insertSelective(data);			
		return true;
	}

	@Override
	public boolean updateInfo(Object params, Object rtn) {
		// TODO Auto-generated method stub
		
		return false;
	}
	/**
	 * 根据权重来查询人员信息
	 * @param params 入参人员信息
	 * @param response
	 */
	private void getInfoByWeight(JSONObject params,JSONObject response)
	{
		
	}
	/**
	 * 根据唯一性证件查询人员信息
	 * @param type 证件类型
	 * @param number 证件号
	 * @param response
	 */
	private void getInfoByCredentials(String type,String number,JSONObject response)
	{
		
	}
	/**
	 * 根据PID查询人员信息
	 * @param pid 
	 * @param response
	 */
	private void getInfoByPID(String pid,JSONObject response)
	{
		
	}

	@Override
	public boolean updateByPid(String pid, String field, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	private Person getPersonFromJson(JSONObject obj)
	{
		Person p=DataEntity.person;
		//姓名
		p.setPname(null);
		if(obj.keySet().contains(PropertiesUtil.PNAME))
		{
			//obj.getString(PropertiesUtil.PNAME);
			p.setPname(obj.getString(PropertiesUtil.PNAME));
		}
		//性别
		p.setGender(null);
		if(obj.keySet().contains(PropertiesUtil.GENDER))
		{
			//obj.getString(PropertiesUtil.GENDER);
			p.setGender(obj.getString(PropertiesUtil.GENDER));
		}
		//出生日期
		p.setBirthday(null);
		if(obj.keySet().contains(PropertiesUtil.BIRTHDAY))
		{
//			obj.getString(PropertiesUtil.BIRTHDAY);
			p.setBirthday(obj.getString(PropertiesUtil.BIRTHDAY));
		}
		//年龄
		p.setAge(0);
		if(obj.keySet().contains(PropertiesUtil.AGE))
		{
//			new Integer(obj.getString(PropertiesUtil.AGE));
			p.setAge(new Integer(obj.getString(PropertiesUtil.AGE)));
		}
		//血型
		p.setBooldtype(null);
		if(obj.keySet().contains(PropertiesUtil.BOOLDTYPE))
		{
//			obj.getString(PropertiesUtil.BOOLDTYPE);
			p.setBooldtype(obj.getString(PropertiesUtil.BOOLDTYPE));
		}
		//学位代码
		p.setDegreecode(null);
		if(obj.keySet().contains(PropertiesUtil.DEGREECODE))
		{
//			obj.getString(PropertiesUtil.DEGREECODE);
			p.setDegreecode(obj.getString(PropertiesUtil.DEGREECODE));
		}
		//学历代码
		p.setEducationcode(null);
		if(obj.keySet().contains(PropertiesUtil.EDUCATIONCODE))
		{
//			obj.getString(PropertiesUtil.EDUCATIONCODE);
			p.setEducationcode(obj.getString(PropertiesUtil.EDUCATIONCODE));
		}
		//电子邮箱
		p.setEmail(null);
		if(obj.keySet().contains(PropertiesUtil.EMAIL))
		{
//			obj.getString(PropertiesUtil.EMAIL);
			p.setEmail(obj.getString(PropertiesUtil.EMAIL));
		}
		//职业类型代码
		p.setJobtypecode(null);
		if(obj.keySet().contains(PropertiesUtil.JOBTYPECODE))
		{
//			obj.getString(PropertiesUtil.JOBTYPECODE);
			p.setJobtypecode(obj.getString(PropertiesUtil.JOBTYPECODE));
		}
		//婚姻状况代码
		p.setMarriedstatus(null);
		if(obj.keySet().contains(PropertiesUtil.MARRIEDSTATUS))
		{
//			obj.getString(PropertiesUtil.MARRIEDSTATUS);
			p.setMarriedstatus(obj.getString(PropertiesUtil.MARRIEDSTATUS));
		}
		//民族代码
		p.setNation(null);
		if(obj.keySet().contains(PropertiesUtil.NATION))
		{
//			obj.getString(PropertiesUtil.NATION);
			p.setNation(obj.getString(PropertiesUtil.NATION));
		}
		//国籍代码
		p.setNationality(null);
		if(obj.keySet().contains(PropertiesUtil.NATIONALITY))
		{
//			obj.getString(PropertiesUtil.NATIONALITY);
			p.setNationality(obj.getString(PropertiesUtil.NATIONALITY));
		}
		//昵称
		p.setNickname(null);
		if(obj.keySet().contains(PropertiesUtil.NICKNAME))
		{
//			obj.getString(PropertiesUtil.NICKNAME);
			p.setNickname(obj.getString(PropertiesUtil.NICKNAME));
		}
		//昵称
		p.setWorkdate(null);
		if(obj.keySet().contains(PropertiesUtil.WORKDATE))
		{
			//obj.getString(PropertiesUtil.WORKDATE);
			p.setWorkdate(obj.getString(PropertiesUtil.WORKDATE));
		}
		//地址-省
		p.setAddrprovince(null);
		if(obj.keySet().contains(PropertiesUtil.ADDRPROVINCE))
		{
//			obj.getString(PropertiesUtil.ADDRPROVINCE);
			p.setAddrprovince(obj.getString(PropertiesUtil.ADDRPROVINCE));
		}
		//地址-市
		p.setAddrcity(null);
		if(obj.keySet().contains(PropertiesUtil.ADDRCITY))
		{
//			obj.getString(PropertiesUtil.ADDRCITY);
			p.setAddrcity(obj.getString(PropertiesUtil.ADDRCITY));
		}
		//地址-县/区
		p.setAddrcounty(null);
		if(obj.keySet().contains(PropertiesUtil.ADDRCOUNTY))
		{
//			obj.getString(PropertiesUtil.ADDRCOUNTY);
			p.setAddrcounty(obj.getString(PropertiesUtil.ADDRCOUNTY));
		}
		//地址-乡/街道
		p.setAddrtown(null);
		if(obj.keySet().contains(PropertiesUtil.ADDRTOWN))
		{
//			obj.getString(PropertiesUtil.ADDRTOWN);
			p.setAddrtown(obj.getString(PropertiesUtil.ADDRTOWN));
		}
		//地址-村/社区
		p.setAddrvillage(null);
		if(obj.keySet().contains(PropertiesUtil.ADDRVILLAGE))
		{
//			obj.getString(PropertiesUtil.ADDRVILLAGE);
			p.setAddrvillage(obj.getString(PropertiesUtil.ADDRVILLAGE));
		}
		//地址-门牌号
		p.setAddrhouseid(null);
		if(obj.keySet().contains(PropertiesUtil.ADDRHOUSEID))
		{
//			obj.getString(PropertiesUtil.ADDRHOUSEID);
			p.setAddrhouseid(obj.getString(PropertiesUtil.ADDRHOUSEID));
		}
		return p;
//		return null;
	}

}
