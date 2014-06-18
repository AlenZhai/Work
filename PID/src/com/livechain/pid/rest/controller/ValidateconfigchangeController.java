package com.livechain.pid.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livechain.pid.model.Field;
import com.livechain.pid.model.Validation;
import com.livechain.pid.rest.model.ValidateconfigchangeIn;
import com.livechain.pid.rest.model.ValidateconfigchangeOut;
import com.livechain.pid.rest.service.ValidateconfigchangeServiceImpl;

//修改数据校验配置
@Controller
public class ValidateconfigchangeController {
	@Autowired
	public ValidateconfigchangeServiceImpl validateconfigchangeservice;
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET, value="/validateconfigchange/")
	public @ResponseBody ValidateconfigchangeOut validateconfigchange(
			   @RequestParam Map validatemap
			  ) throws DocumentException
			  {
		       Validation validate=new Validation();
		   //是pname的赋值
		       Field pname=new Field();
		       if(validatemap.containsKey("pname_regx_regx"))
				{				 
				  if(validatemap.get("pname_regx_regx")!=null&&!validatemap.get("pname_regx_regx").toString().trim().equals(""))
				  {				 
				  pname.setRegx(validatemap.get("pname_regx_regx").toString());
				  validate.setPname(pname);
				  if(validatemap.containsKey("pname_name"))
					{
					  if(validatemap.get("pname_name")!=null&&!validatemap.get("pname_name").toString().trim().equals(""))
					  {
					  pname.setName(validatemap.get("pname_name").toString());
					  validate.setPname(pname);
					}}
					if(validatemap.containsKey("pname_comment"))
					{
					if(validatemap.get("pname_comment")!=null&&!validatemap.get("pname_comment").toString().trim().equals(""))
					 {
					  pname.setComment(validatemap.get("pname_comment").toString());
					  validate.setPname(pname);
					}
					}
				  }
				}	
		       
		//性别gender赋值
		  Field gender=new Field();
		  if(validatemap.containsKey("gender_regx_regx"))
		  {
		    Map<String,String> regx=new HashMap<String,String>();
			
			if(validatemap.get("gender_regx_regx")!=null&&!validatemap.get("gender_regx_regx").toString().trim().equals(""))
			{
			
			gender.setRegx(validatemap.get("gender_regx_regx").toString());
			validate.setGender(gender);
			if(validatemap.containsKey("gender_name"))
			  {
				if(validatemap.get("gender_name")!=null&&!validatemap.get("gender_name").toString().trim().equals(""))
				{
				gender.setName(validatemap.get("gender_name").toString());
				validate.setGender(gender);
			  }}
			if(validatemap.containsKey("gender_comment"))
			  {
				if(validatemap.get("gender_comment")!=null&&!validatemap.get("gender_comment").toString().trim().equals(""))
				{
				 gender.setComment(validatemap.get("gender_comment").toString());
				 validate.setGender(gender);
			  }}
		  }		
		  }
				
		//email	电子邮件赋值
		 Field email=new Field();
		 if(validatemap.containsKey("email_regx_regx"))
		  {
			
			if(validatemap.get("email_regx_regx")!=null&&!validatemap.get("email_regx_regx").toString().trim().equals(""))
			{
			
			email.setRegx(validatemap.get("email_regx_regx").toString());
			validate.setEmail(email);
			if(validatemap.containsKey("email_name"))
			  {
				if(validatemap.get("email_name")!=null&&!validatemap.get("email_name").toString().trim().equals(""))
				{
				email.setName(validatemap.get("email_name").toString());
				validate.setEmail(email);
			  }}
			if(validatemap.containsKey("email_comment"))
			  {
				if(validatemap.get("email_comment")!=null&&!validatemap.get("email_comment").toString().trim().equals(""))
				{
				email.setComment(validatemap.get("email_comment").toString());
				validate.setEmail(email);
			  }}
		  }		
		  }
			
		//是addrprovince的赋值
		 Field addrprovince=new Field();
		 if(validatemap.containsKey("addrprovince_regx_regx"))
		   {
			Map<String,String> regx=new HashMap<String,String>();
			if(validatemap.get("addrprovince_regx_regx")!=null&&!validatemap.get("addrprovince_regx_regx").toString().trim().equals(""))
			{			
			addrprovince.setRegx(validatemap.get("addrprovince_regx_regx").toString());
			validate.setAddrprovince(addrprovince);
			//System.out.println(validate.getAddrprovince().getRegx());
			if(validatemap.containsKey("addrprovince_name"))
			   {
				if(validatemap.get("addrprovince_name")!=null&&!validatemap.get("addrprovince_name").toString().trim().equals(""))
				{
				 addrprovince.setName(validatemap.get("addrprovince_name").toString());
				 validate.setAddrprovince(addrprovince);
			   }}
			if(validatemap.containsKey("addrprovince_comment"))
			   {
				if(validatemap.get("addrprovince_comment")!=null&&!validatemap.get("addrprovince_comment").toString().trim().equals(""))
				{
				 addrprovince.setComment(validatemap.get("addrprovince_comment").toString());
				 validate.setAddrprovince(addrprovince);
			   }}
			}
		   }
			
		//是addrcounty的赋值
		 Field addrcounty=new Field();				
		 if(validatemap.containsKey("addrcounty_regx_regx"))
		 {  
		    
		    if(validatemap.get("addrcounty_regx_regx")!=null&&!validatemap.get("addrcounty_regx_regx").toString().trim().equals(""))
		    {
		    
		    addrcounty.setRegx(validatemap.get("addrcounty_regx_regx").toString());
			validate.setAddrcounty(addrcounty);
			if(validatemap.containsKey("addrcounty_name"))
			  {
				if(validatemap.get("addrcounty_name")!=null&&!validatemap.get("addrcounty_name").toString().trim().equals(""))
			    {
				addrcounty.setName(validatemap.get("addrcounty_name").toString());
				validate.setAddrcounty(addrcounty);
			  }}
			if(validatemap.containsKey("addrcounty_comment"))
			 {
				if(validatemap.get("addrcounty_comment")!=null&&!validatemap.get("addrcounty_comment").toString().trim().equals(""))
			    {
				addrcounty.setComment(validatemap.get("addrcounty_comment").toString());
				validate.setAddrcounty(addrcounty);
			 }}
		 }	
		 }
			
	    //是addrcity的赋值			
		 Field addrcity=new Field();		
		 if(validatemap.containsKey("addrcity_regx_regx"))
			{
			  
			  
			  if(validatemap.get("addrcity_regx_regx")!=null&&!validatemap.get("addrcity_regx_regx").toString().trim().equals(""))
			  {
			 
			  addrcity.setRegx(validatemap.get("addrcity_regx_regx").toString());
			  validate.setAddrcity(addrcity);
			  if(validatemap.containsKey("addrcity_name"))
				{
				  if(validatemap.get("addrcity_name")!=null&&!validatemap.get("addrcity_name").toString().trim().equals(""))
				  {
				  addrcity.setName(validatemap.get("addrcity_name").toString());
				  validate.setAddrcity(addrcity);
				}}
				if(validatemap.containsKey("addrcity_comment"))
				{if(validatemap.get("addrcity_comment")!=null&&!validatemap.get("addrcity_comment").toString().trim().equals(""))
				  {
				  addrcity.setComment(validatemap.get("addrcity_comment").toString());
				  validate.setAddrcity(addrcity);
				}}
			}	
			}
					
			//是addrtown的赋值
		     Field addrtown=new Field();	
		     if(validatemap.containsKey("addrtown_regx_regx"))
				{
				 
				  if(validatemap.get("addrtown_regx_regx")!=null&&!validatemap.get("addrtown_regx_regx").toString().trim().equals(""))
				  {
				  
				  addrtown.setRegx(validatemap.get("addrtown_regx_regx").toString());
				  validate.setAddrtown(addrtown);
				  if(validatemap.containsKey("addrtown_name"))
					{
					  if(validatemap.get("addrtown_name")!=null&&!validatemap.get("addrtown_regx").toString().trim().equals(""))
					  {
					  addrtown.setName(validatemap.get("addrtown_name").toString());
					  validate.setAddrtown(addrtown);
					}}
					if(validatemap.containsKey("addrtown_comment"))
					{
						if(validatemap.get("addrtown_comment")!=null&&!validatemap.get("addrtown_comment").toString().trim().equals(""))
						  {
					  addrtown.setComment(validatemap.get("addrtown_comment").toString());
					  validate.setAddrtown(addrtown);
					}}
				}		
				}
						
			//是addrvillage的赋值
				Field addrvillage=new Field();
				if(validatemap.containsKey("addrvillage_regx_regx"))
				{
				 
				 if(validatemap.get("addrvillage_regx_regx")!=null&&!validatemap.get("addrvillage_regx_regx").toString().trim().equals(""))
				 {
				 
				 addrvillage.setRegx(validatemap.get("addrvillage_regx_regx").toString());
				 validate.setAddrvillage(addrvillage);
				 if(validatemap.containsKey("addrvillage_name"))
					{
					 if(validatemap.get("addrvillage_name")!=null&&!validatemap.get("addrvillage_name").toString().trim().equals(""))
					 {
					 addrvillage.setName(validatemap.get("addrvillage_name").toString());
					 validate.setAddrvillage(addrvillage);
				   }}
				if(validatemap.containsKey("addrvillage_comment"))
					{
					if(validatemap.get("addrvillage_comment")!=null&&!validatemap.get("addrvillage_comment").toString().trim().equals(""))
					 {
					 addrvillage.setComment(validatemap.get("addrvillage_comment").toString());
					 validate.setAddrvillage(addrvillage);
					}}
				}	
				}
					
			//是addrhouseid的赋值
			 Field addrhouseid=new Field();	
			 if(validatemap.containsKey("addrhouseid_regx_regx"))
				{
				 
				 if(validatemap.get("addrhouseid_regx_regx")!=null&&!validatemap.get("addrhouseid_regx_regx").toString().trim().equals(""))
				 {
				  
				 addrhouseid.setRegx(validatemap.get("addrhouseid_regx_regx").toString());
				 validate.setAddrhouseid(addrhouseid);
				 if(validatemap.containsKey("addrhouseid_name"))
					{
					 if(validatemap.get("addrhouseid_name")!=null&&!validatemap.get("addrhouseid_name").toString().trim().equals(""))
					 {
					 addrhouseid.setName(validatemap.get("addrhouseid_name").toString());
					 validate.setAddrhouseid(addrhouseid);
				   }}
				if(validatemap.containsKey("addrhouseid_comment"))
					{
					if(validatemap.get("addrhouseid_comment")!=null&&!validatemap.get("addrhouseid_comment").toString().trim().equals(""))
					 {
					addrhouseid.setComment(validatemap.get("addrhouseid_comment").toString());
					 validate.setAddrhouseid(addrhouseid);
					}}
				}
				}
								
		//是birthday的赋值
		   Field birthday=new Field();
		   if(validatemap.containsKey("birthday_regx_regx"))
			 {
			  
			  if(validatemap.get("birthday_regx_regx")!=null&&!validatemap.get("birthday_regx_regx").toString().trim().equals(""))
			  {
			 
			  birthday.setRegx(validatemap.get("birthday_regx_regx").toString());
			  validate.setBirthday(birthday);
			  if(validatemap.containsKey("birthday_name"))
				 {
				  if(validatemap.get("birthday_name")!=null&&!validatemap.get("birthday_name").toString().trim().equals(""))
				  {
				  birthday.setName(validatemap.get("birthday_name").toString());
				  validate.setBirthday(birthday);
			     }}
			   if(validatemap.containsKey("birthday_comment"))
				 {
				   if(validatemap.get("birthday_comment")!=null&&!validatemap.get("birthday_comment").toString().trim().equals(""))
					  {
				  birthday.setComment(validatemap.get("birthday_comment").toString());
				  validate.setBirthday(birthday);
				 }}
			    }	
			  }
		  		
	//是nickname的赋值
		   Field nickname=new Field();		
		   if(validatemap.containsKey("nickname_regx_regx"))
			 {
			  
			  if(validatemap.get("nickname_regx_regx")!=null&&!validatemap.get("nickname_regx_regx").toString().trim().equals(""))
			  {
			
			  nickname.setRegx(validatemap.get("nickname_regx").toString());
			  validate.setNickname(nickname);
			  if(validatemap.containsKey("nickname_name"))
				 {
				  if(validatemap.get("nickname_name")!=null&&!validatemap.get("nickname_name").toString().trim().equals(""))
				  {
				  nickname.setName(validatemap.get("nickname_name").toString());
				  validate.setNickname(nickname);
			     }}
			   if(validatemap.containsKey("nickname_comment"))
				 {
				   if(validatemap.get("nickname_comment")!=null&&!validatemap.get("nickname_comment").toString().trim().equals(""))
					  {
				  nickname.setComment(validatemap.get("nickname_comment").toString());
				  validate.setNickname(nickname);
				 }}
			 }	
			 }
		  		
	 //是registeretype的赋值
			 Field registeretype=new Field();		
			 if(validatemap.containsKey("registeretype_regx_regx"))
			 {
			  
			  if(validatemap.get("registeretype_regx_regx")!=null&&!validatemap.get("registeretype_regx_regx").toString().trim().equals(""))
			  {
			  
			  registeretype.setRegx(validatemap.get("registeretype_regx").toString());
			  validate.setRegisteretype(registeretype);
			  if(validatemap.containsKey("registeretype_name"))
			   {
				  if(validatemap.get("registeretype_name")!=null&&!validatemap.get("registeretype_name").toString().trim().equals(""))
				  {
			    registeretype.setName(validatemap.get("registeretype_name").toString());
			    validate.setRegisteretype(registeretype);
		       }}
		   if(validatemap.containsKey("registeretype_comment"))
			  {
			   if(validatemap.get("registeretype_comment")!=null&&!validatemap.get("registeretype_comment").toString().trim().equals(""))
				  {
			   registeretype.setComment(validatemap.get("registeretype_comment").toString());
			   validate.setRegisteretype(registeretype);
			  }}
			 }		
			 }
		 	
	//是nationality的赋值
		 Field nationality=new Field();		
		 if(validatemap.containsKey("nationality_regx_regx"))
		   {
		    
		     if(validatemap.get("nationality_regx_regx")!=null&&!validatemap.get("nationality_regx_regx").toString().trim().equals(""))
		     {
		    
		     nationality.setRegx(validatemap.get("nationality_regx").toString());
		     validate.setNationality(nationality);
		     if(validatemap.containsKey("nationality_name"))
			    {
		    	 if(validatemap.get("nationality_name")!=null&&!validatemap.get("nationality_name").toString().trim().equals(""))
			     {
				 nationality.setName(validatemap.get("nationality_name").toString());
			     validate.setNationality(nationality);
		        }}
		     if(validatemap.containsKey("nationality_comment"))
			    {
		    	 if(validatemap.get("nationality_comment")!=null&&!validatemap.get("nationality_comment").toString().trim().equals(""))
			     {
		    	 nationality.setComment(validatemap.get("nationality_comment").toString());
			     validate.setNationality(nationality);
			    }}
		   }	
		   }
	    	
	 //是 marriedstatus的赋值
		 Field marriedstatus=new Field();		
		 if(validatemap.containsKey("marriedstatus_regx_regx"))
		   {
		    	    
		     if(validatemap.get("marriedstatus_regx_regx")!=null&&!validatemap.get("marriedstatus_regx_regx").toString().trim().equals(""))
		     {
		   
		     marriedstatus.setRegx(validatemap.get("marriedstatus_regx").toString());
		     validate.setMarriedstatus(marriedstatus);
		     if(validatemap.containsKey("marriedstatus_name"))
			    {
		    	 if(validatemap.get("marriedstatus_name")!=null&&!validatemap.get("marriedstatus_name").toString().trim().equals(""))
			     {
				 marriedstatus.setName(validatemap.get("marriedstatus_name").toString());
			     validate.setMarriedstatus(marriedstatus);
		        }}
		     if(validatemap.containsKey("marriedstatus_comment"))
			    {
		    	 if(validatemap.get("marriedstatus_comment")!=null&&!validatemap.get("marriedstatus_comment").toString().trim().equals(""))
			     {
		    	 marriedstatus.setComment(validatemap.get("marriedstatus_comment").toString());
			     validate.setMarriedstatus(marriedstatus);
			    }}
		   }	
		   }
	     	
	   //是 nation的赋值
		 Field nation=new Field();	
		 if(validatemap.containsKey("nation_regx_regx"))
		   {
		    	     
		     if(validatemap.get("nation_regx_regx")!=null&&!validatemap.get("nation_regx_regx").toString().trim().equals(""))
		     {
		    
		     nation.setRegx(validatemap.get("nation_regx_regx").toString());
		     validate.setNation(nation);
		     if(validatemap.containsKey("nation_name"))
			    {if(validatemap.get("nation_name")!=null&&!validatemap.get("nation_name").toString().trim().equals(""))
			     {
				 nation.setName(validatemap.get("nation_name").toString());
			     validate.setNation(nation);
		        }}
		     if(validatemap.containsKey("nation_comment"))
			    {
		    	 if(validatemap.get("nation_comment")!=null&&!validatemap.get("nation_comment").toString().trim().equals(""))
			     {
		    	 nation.setComment(validatemap.get("nation_comment").toString());
			     validate.setNation(nation);
			    }}
		   }	
		   }
	     	
	   //是 age的赋值
		 Field age=new Field();		
		 if(validatemap.containsKey("age_regx_regx"))
		   {
		     
		     if(validatemap.get("age_regx_regx")!=null&&!validatemap.get("age_regx_regx").toString().trim().equals(""))
		     {
		   
		     age.setRegx(validatemap.get("age_regx_regx").toString());
		     validate.setAge(age);
		     if(validatemap.containsKey("age_name"))
			    {
		    	 if(validatemap.get("age_name")!=null&&!validatemap.get("age_name").toString().trim().equals(""))
			     {
				 age.setName(validatemap.get("age_name").toString());
			     validate.setAge(age);
		        }}
		     if(validatemap.containsKey("age_comment"))
			    {if(validatemap.get("age_comment")!=null&&!validatemap.get("age_comment").toString().trim().equals(""))
			     {
		    	 age.setComment(validatemap.get("age_comment").toString());
			     validate.setAge(age);
			    }}
		   }	
		   }
	     	
	 //是 booldtype的赋值
		 Field booldtype=new Field();	
		 if(validatemap.containsKey("booldtype_regx_regx"))
		   {
		    
		     if(validatemap.get("booldtype_regx_regx")!=null&&!validatemap.get("booldtype_regx_regx").toString().trim().equals(""))
		     {
		    
		     booldtype.setRegx(validatemap.get("booldtype_regx_regx").toString());
		     validate.setBooldtype(booldtype);
		     if(validatemap.containsKey("booldtype_name"))
			    {
		    	 if(validatemap.get("booldtype_name")!=null&&!validatemap.get("booldtype_name").toString().trim().equals(""))
			     {
				 booldtype.setName(validatemap.get("booldtype_name").toString());
			     validate.setBooldtype(booldtype);
		        }}
		     if(validatemap.containsKey("booldtype_comment"))
			    {if(validatemap.get("booldtype_comment")!=null&&!validatemap.get("booldtype_comment").toString().trim().equals(""))
			     {
		    	 booldtype.setComment(validatemap.get("booldtype_comment").toString());
			     validate.setBooldtype(booldtype);
			    }}
		     }	
		   }
	     	
	   //是 rh的赋值
		 Field rh=new Field(); 
		 if(validatemap.containsKey("rh_regx_regx"))
		   {
		     
		     if(validatemap.get("rh_regx_regx")!=null&&!validatemap.get("rh_regx_regx").toString().trim().equals(""))
		     {
		   
		     rh.setRegx(validatemap.get("rh_regx_regx").toString());
		     validate.setRh(rh);
		     if(validatemap.containsKey("rh_name"))
			    {
		    	 if(validatemap.get("rh_name")!=null&&!validatemap.get("rh_name").toString().trim().equals(""))
			     {
				 rh.setName(validatemap.get("rh_name").toString());
			     validate.setRh(rh);
		        }}
		     if(validatemap.containsKey("rh_comment"))
			    {if(validatemap.get("rh_comment")!=null&&!validatemap.get("rh_comment").toString().trim().equals(""))
			     {
		    	 rh.setComment(validatemap.get("rh_comment").toString());
			     validate.setRh(rh);
			    }}
		   }	
		   }
	     	
	   //是workdate的赋值
			Field workdate=new Field();   
			if(validatemap.containsKey("workdate_regx_regx"))
			   {
			     Map<String,String> regx=new HashMap<String,String>();
			     if(validatemap.get("workdate_regx_regx")!=null&&!validatemap.get("workdate_regx_regx").toString().trim().equals(""))
			     {
			   
			     workdate.setRegx(validatemap.get("workdate_regx_regx").toString());
			     validate.setWorkdate(workdate);
			     if(validatemap.containsKey("workdate_name"))
				    {
			    	 if(validatemap.get("workdate_name")!=null&&!validatemap.get("workdate_name").toString().trim().equals(""))
				     {
					 workdate.setName(validatemap.get("workdate_name").toString());
				     validate.setWorkdate(workdate);
			        }}
			     if(validatemap.containsKey("workdate_comment"))
				    {
			    	 if(validatemap.get("workdate_comment")!=null&&!validatemap.get("workdate_comment").toString().trim().equals(""))
				     {
			    	 workdate.setComment(validatemap.get("workdate_comment").toString());
				     validate.setWorkdate(workdate);
				    }}
			   }	
			   }
	     	
	  //是jobtypecode的赋值
		 Field jobtypecode=new Field();	
		 if(validatemap.containsKey("jobtypecode_regx_regx"))
		   {
		     
		     if(validatemap.get("jobtypecode_regx_regx")!=null&&!validatemap.get("jobtypecode_regx_regx").toString().trim().equals(""))
		     {
		    
		     jobtypecode.setRegx(validatemap.get("jobtypecode_regx_regx").toString());
		     validate.setJobtypecode(jobtypecode);
		     if(validatemap.containsKey("jobtypecode_name"))
			    {if(validatemap.get("jobtypecode_name")!=null&&!validatemap.get("jobtypecode_name").toString().trim().equals(""))
			     {
				 jobtypecode.setName(validatemap.get("jobtypecode_name").toString());
			     validate.setJobtypecode(jobtypecode);
		        }}
		     if(validatemap.containsKey("jobtypecode_comment"))
			    {if(validatemap.get("jobtypecode_comment")!=null&&!validatemap.get("jobtypecode_comment").toString().trim().equals(""))
			     {
		    	 jobtypecode.setComment(validatemap.get("jobtypecode_comment").toString());
			     validate.setJobtypecode(jobtypecode);
			    }}
		   }
		   }
	     		
	 //是educationcode的赋值
		 Field educationcode=new Field();
		 if(validatemap.containsKey("educationcode_regx_regx"))
		   {
		     
		     if(validatemap.get("educationcode_regx_regx")!=null&&!validatemap.get("educationcode_regx_regx").toString().trim().equals(""))
		     {
		    
		     educationcode.setRegx(validatemap.get("educationcode_regx_regx").toString());
		     validate.setEducationcode(educationcode);
		     if(validatemap.containsKey("educationcode_name"))
			    {if(validatemap.get("educationcode_name")!=null&&!validatemap.get("educationcode_name").toString().trim().equals(""))
			     {
				 educationcode.setName(validatemap.get("educationcode_name").toString());
			     validate.setEducationcode(educationcode);
		        }}
		     if(validatemap.containsKey("educationcode_comment"))
			    {if(validatemap.get("educationcode_comment")!=null&&!validatemap.get("educationcode_comment").toString().trim().equals(""))
			     {
		    	 educationcode.setComment(validatemap.get("educationcode_comment").toString());
			     validate.setEducationcode(educationcode);
			    }}
		   }	
		   }
	     	
	   //是degreecode的赋值
		 Field degreecode=new Field();	     
		 if(validatemap.containsKey("degreecode_regx_regx"))
		   {
		    
		     if(validatemap.get("degreecode_regx_regx")!=null&&!validatemap.get("degreecode_regx_regx").toString().trim().equals(""))
		     {
		   
		     degreecode.setRegx(validatemap.get("degreecode_regx_regx").toString());
		     validate.setDegreecode(degreecode);
		     if(validatemap.containsKey("degreecode_name"))
			    {if(validatemap.get("degreecode_name")!=null&&!validatemap.get("degreecode_name").toString().trim().equals(""))
			     {
				 degreecode.setName(validatemap.get("degreecode_name").toString());
			     validate.setDegreecode(degreecode);
		        }}
		     if(validatemap.containsKey("degreecode_comment"))
			    {
		    	 if(validatemap.get("degreecode_comment")!=null&&!validatemap.get("degreecode_comment").toString().trim().equals(""))
			     {
		    	 degreecode.setComment(validatemap.get("degreecode_comment").toString());
			     validate.setDegreecode(degreecode);
			    }}
		   }
		   }
		 
	     	
	   //是orgcodes的赋值
		 Field orgcodes=new Field();
		 if(validatemap.containsKey("orgcodes_regx_regx"))
		   { 
			 //System.out.println("开始");
			
		     if(validatemap.get("orgcodes_regx_regx")!=null && !validatemap.get("orgcodes_regx_regx").toString().trim().equals("")){  
		     
		     orgcodes.setRegx(validatemap.get("orgcodes_regx_regx").toString());
		     validate.setOrgcodes(orgcodes);
		     //System.out.println(validatemap.get("orgcodes_regx"));
		     if(validatemap.containsKey("orgcodes_name"))
			    {
		    	 if(validatemap.get("orgcodes_name")!=null && !validatemap.get("orgcodes_name").toString().trim().equals("")){  
			    
				 orgcodes.setName(validatemap.get("orgcodes_name").toString());
			     validate.setOrgcodes(orgcodes);
		        }}
		     if(validatemap.containsKey("orgcodes_comment"))
			    {
		    	 if(validatemap.get("orgcodes_comment")!=null && !validatemap.get("orgcodes_comment").toString().trim().equals(""))
		    	 {  
		    	 orgcodes.setComment(validatemap.get("orgcodes_comment").toString());
			     validate.setOrgcodes(orgcodes);
			    }}
		   }		
		}
		 
	     
//证件credentials赋值
	    Field credentials=new Field();
	    if(validatemap.containsKey("credentials_regx_regx"))
		{
	    if(validatemap.get("credentials_regx_regx")!=null && !validatemap.get("credentials_regx_regx").toString().trim().equals(""))
	    {  
		 
		
		 credentials.setRegx(validatemap.get("credentials_regx_regx").toString());
		// validate.setCredentials(credentials);
		}}
	    if(validatemap.containsKey("credentials_name"))
		{
	   if(validatemap.get("credentials_name")!=null && !validatemap.get("credentials_name").toString().trim().equals(""))
		{ 
		 credentials.setName(validatemap.get("credentials_name").toString());
		 //validate.setCredentials(credentials);
		}}
	  if(validatemap.containsKey("credentials_comment"))
		{
		  if(validatemap.get("credentials_comment")!=null && !validatemap.get("credentials_comment").toString().trim().equals(""))
			{
		 credentials.setComment(validatemap.get("credentials_comment").toString());
		 //validate.setCredentials(credentials);
		}}
	String s1="credentials_regx_";
	String arrc[]=new String[26];
	arrc[0]="01";
	arrc[1]="02";
	arrc[2]="03";
	arrc[3]="04";
	arrc[4]="05";
	arrc[5]="06";
	arrc[6]="07";
	arrc[7]="08";
	arrc[8]="09";
	arrc[9]="10";
	arrc[10]="11";
	arrc[11]="12";
	arrc[12]="13";
	arrc[13]="14";
	arrc[14]="15";
	arrc[15]="16";
	arrc[16]="70";
	arrc[17]="71";
	arrc[18]="72";
	arrc[19]="73";
	arrc[20]="80";
	arrc[21]="81";
	arrc[22]="90";
	arrc[23]="91";
	arrc[24]="92";
	arrc[25]="99";
	//Map<String,String> regx=new HashMap<String,String>();
	for(int i=0;i<arrc.length;i++)
	{
	if(validatemap.containsKey(s1+arrc[i]))
		{ 
		if(validatemap.get(s1+arrc[i])!=null&&!validatemap.get(s1+arrc[i]).toString().trim().equals(""))
		{
	     //regx.put(,validatemap.get(s1+arrc[i]).toString());
			String comment=null;
			if(validatemap.containsKey(s1+arrc[i]+"_comment"))
			{
				comment=validatemap.get(s1+arrc[i]+"_"+"comment").toString();
			}
	       credentials.addType(arrc[i], 0f, comment, validatemap.get(s1+arrc[i]).toString(), false);
		}}
	}	
	 
	 validate.setCredentials(credentials);	
 //是phones的赋值
		Field phones=new Field(); 
		 if(validatemap.containsKey("phones_regx_regx"))
		  {
		   if(validatemap.get("phones_regx_regx")!=null && !validatemap.get("phones_regx_regx").toString().trim().equals(""))
		   {	   		   
		     phones.setRegx(validatemap.get("phones_regx_regx").toString());
		     //validate.setPhones(phones);
		    }
		   }
		 if(validatemap.containsKey("phones_name"))
		   {
			if(validatemap.get("phones_name")!=null && !validatemap.get("phones_name").toString().trim().equals(""))
			{
			phones.setName(validatemap.get("phones_name").toString());
		   // validate.setPhones(phones);
		   }}
		  if(validatemap.containsKey("phones_comment"))
		  {
		   if(validatemap.get("phones_comment")!=null && !validatemap.get("phones_comment").toString().trim().equals(""))
			{
		     phones.setComment(validatemap.get("phones_comment").toString());
		   //validate.setPhones(phones);
		  }}
		
	String s2="phones_regx_";
	String arrphone[]=new String[11];
	arrphone[0]="01";
	arrphone[1]="02";
	arrphone[2]="03";
	arrphone[3]="04";
	arrphone[4]="05";
	arrphone[5]="06";
	arrphone[6]="07";
	arrphone[7]="08";
	arrphone[8]="09";
	arrphone[9]="10";
	arrphone[10]="99";
	Map<String,String> regx1=new HashMap<String,String>();
	for(int i=0;i<arrphone.length;i++)
	{	 
//	  for ( int j=i+1;j<arrphone.length;j++){
//                 if(arrphone[i]>arrphone[j])
//                 {
//                	 t = a[i]
//                 }
//	      }
	 if(validatemap.containsKey(s2+arrphone[i]))
		 { 
		 if(validatemap.get(s2+arrphone[i])!=null&&!validatemap.get(s2+arrphone[i]).toString().trim().equals(""))
		 {
			 String comment=null;
			 if(validatemap.containsKey(s2+arrphone[i]+"_comment"));
			 {
				 comment=validatemap.get(s2+arrphone[i]+"_comment").toString();
			 }
		  phones.addType(arrphone[i], 0f, comment, validatemap.get(s2+arrphone[i]).toString(), false);
		 }
		 }
	   }
	  //phones.setRegx(regx1);
	  validate.setPhones(phones);				
					
		ValidateconfigchangeIn in = new ValidateconfigchangeIn();
		if(validatemap.containsKey("callback"))
			{
			  in.setCallback(validatemap.get("callback").toString());
			}
			 in.setValidation(validate);
		 return validateconfigchangeservice.validateconfigchange(in);
	}
}
