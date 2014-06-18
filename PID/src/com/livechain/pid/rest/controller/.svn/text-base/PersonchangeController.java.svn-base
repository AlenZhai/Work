package com.livechain.pid.rest.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.mybatis.model.Credentials;
import com.livechain.mybatis.model.OrgCodes;
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.Phones;
import com.livechain.pid.rest.model.PersonchangeOut;
import com.livechain.pid.rest.service.PersonchangeService;

@Controller
public class PersonchangeController {
	@Autowired
	public PersonchangeService personchangeservice;
//////////////////////////@ResponseBody ////////////////////////
	@RequestMapping(method=RequestMethod.GET, value="/personchange/")
	 public @ResponseBody PersonchangeOut Personchange(
			 @RequestParam(value="callback",required=false) String callback,
			 @RequestParam(value="pname",required=true) String pname,
			 @RequestParam(value="gender",required=true) String gender,
			 @RequestParam(value="birthday",required=true) String birthday,
			 @RequestParam(value="email",required=false) String email,
			 @RequestParam(value="credentials",required=false) String credentials,
			 @RequestParam(value="nickname",required=false) String nickname,
			 @RequestParam(value="registeretype",required=false) String registeretype,
			 @RequestParam(value="addrprovince",required=false) String addrprovince,
			 @RequestParam(value="addrcity",required=false) String addrcity,
			 @RequestParam(value="addrcounty",required=false) String addrcounty,
			 @RequestParam(value="addrtown",required=false) String addrtown,
			 @RequestParam(value="addrvillage",required=false) String addrvillage,
			 @RequestParam(value="addrhouseid",required=false) String addrhouseid,
			 @RequestParam(value="phones",required=false) String phones,
			 @RequestParam(value="nationality",required=false) String nationality,
			 @RequestParam(value="marriedstatus",required=false) String marriedstatus,
			 @RequestParam(value="nation",required=false) String nation,
			 @RequestParam(value="age",required=false) String age,
			 @RequestParam(value="booldtype",required=false) String booldtype,
			 @RequestParam(value="rh",required=false) String rh,
			 @RequestParam(value="isdel",required=false) String isdel,
			 @RequestParam(value="idcard",required=false) String idcard,
			 @RequestParam(value="workdate",required=false) String workdate,
			 @RequestParam(value="jobtypecode",required=false) String jobtypecode,
			 @RequestParam(value="educationcode",required=false) String educationcode,
			 @RequestParam(value="degreecode",required=false) String degreecode,
			 @RequestParam(value="orgcode",required=false) String orgcode,
			 @RequestParam(value="pid",required=false) String pid ){	
		
		        Person person=new Person();		
		        person.setPid(pid);
		        person.setIsdel("0");
		       	person.setAddrcity(addrcity);
		       	person.setAddrcounty(addrcounty);
		     	person.setAddrhouseid(addrhouseid);
		       	person.setAddrtown(addrtown);
				person.setAddrprovince(addrprovince);
				person.setAddrvillage(addrvillage);			
				if(age!=null){
				person.setAge(Integer.valueOf(age));
				}
				person.setBirthday(birthday);
				person.setBooldtype(booldtype);
				List<Credentials> cre = new ArrayList<Credentials>();
				if(credentials!=null&&!credentials.equals("")){					
				String creds[] = credentials.split(",");
				//首先 定义一个 容器 List<Credentials>来容纳 证件 				
				//将所有证件 的type_num 解析 成 type 和 num
				for(int i=0;i<creds.length;i++)
				{
					Credentials c=new Credentials();
					String card[]=creds[i].split("_");
					if(2==card.length)
					{
						c.setType(card[0]);
						c.setNum(card[1]);
						cre.add(c);
					}
				}
				}
				//判断idcard不为空，把idcard赋给num.添加到Credentiale
				if(idcard!=null&&!idcard.equals("")){
					Credentials c = new Credentials();
					c.setType("01");
					c.setNum(idcard);
					cre.add(c);
				}
				//判断 容器 中是否有 数据
				if(cre.size()>0)
				{
				  person.setCredentials(cre);
				}
				
				
				person.setDegreecode(degreecode);
				person.setEducationcode(educationcode);
				person.setEmail(email);
				person.setGender(gender);
				person.setJobtypecode(jobtypecode);
				person.setMarriedstatus(marriedstatus);
				person.setNation(nation);
				person.setNationality(nationality);
				person.setNickname(nickname);
				
				if(orgcode!=null&&!orgcode.equals("")){
				List<OrgCodes> orgcode1 = new ArrayList<OrgCodes>();
				OrgCodes orgcod = new OrgCodes();
				orgcode1.add(orgcod);
				orgcod.setOrgcode(orgcode);
				person.setOrgcodes(orgcode1);
				}
				
				if(phones!=null&&!phones.equals("")){
				String phon[] = phones.split(",");
				//首先 定义一个 容器 List<Phones>来容纳 证件 
				List<Phones> pho = new ArrayList<Phones>();
				//将所有电话 的type_num 解析 成 type 和 num
				for(int i=0;i<phon.length;i++)
				{
					Phones d=new Phones();
					String card[]=phon[i].split("_");
					if(2==card.length)
					{
						d.setType(card[0]);
						d.setNum(card[1]);
						pho.add(d);
					}
				}				
				//判断 容器 中是否有 数据
				if(pho.size()>0)
				{
					person.setPhones(pho);
				}
				}
				person.setPname(pname);
				person.setRegisteretype(registeretype);
				person.setRh(rh);
				person.setWorkdate(workdate);
				person.setPid(pid);
				return personchangeservice.personchange(person, callback);
				
}
}