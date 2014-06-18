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
import com.livechain.pid.model.Weight;
import com.livechain.pid.rest.model.WeightconfigchangeIn;
import com.livechain.pid.rest.model.WeightconfigchangeOut;
import com.livechain.pid.rest.service.WeightconfigchangeServiceImpl;



//修改权重比配置
@Controller
public class WeightconfigchangeController {
	@Autowired
	public WeightconfigchangeServiceImpl weightconfigchangeservice;
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET, value="/weightconfigchange/")
	public @ResponseBody WeightconfigchangeOut weightconfigchange(

		   @RequestParam(value="callback",required=false) String callback,

		   //@RequestParam(value="callback",required=false) String callback,
		   @RequestParam Map weightmap

		   ) throws DocumentException
		{
		Weight weight=new Weight();
		if(weightmap.containsKey("max"))
		{
		if(weightmap.get("max")!=null&&!weightmap.get("max").toString().trim().equals(""))
		{	
			weight.setMax(Float.valueOf(weightmap.get("max").toString()));
		}
		}
		if(weightmap.containsKey("min"))
		{
		if(weightmap.get("min")!=null&&!weightmap.get("min").toString().trim().equals(""))
		{
			weight.setMin(Float.valueOf(weightmap.get("min").toString()));
		}
		}
		//是pname的赋值
		 Field pname=new Field();
		if(weightmap.containsKey("pname_types_weight"))
		  { 
			 //Map<String,Float> types=new HashMap<String,Float>();
			if(weightmap.get("pname_types_weight")!=null&&!weightmap.get("pname_types_weight").toString().trim().equals(""))
			{
				boolean uniuqe=false;
				if(weightmap.containsKey("pname_unique"))
				{
				 if(weightmap.get("pname_unique")!=null&&!weightmap.get("pname_unique").toString().trim().equals(""))
				  {
					 uniuqe=Boolean.valueOf(weightmap.get("pname_unique").toString());
				  //weight.setPname(pname);
				  }
				}
			  //types.put("weight",Float.valueOf(weightmap.get("pname_types_weight").toString()));
			  pname.addType("weight", Float.valueOf(weightmap.get("pname_types_weight").toString()), null, null, uniuqe);
			 // weight.setPname(pname);		
		if(weightmap.containsKey("pname_name"))
		{
		if(weightmap.get("pname_name")!=null&&!weightmap.get("pname_name").toString().trim().equals(""))
		{	
		  pname.setName(weightmap.get("pname_name").toString());
		  //weight.setPname(pname);
		}
		}
		if(weightmap.containsKey("pname_comment"))
		{
		if(weightmap.get("pname_comment")!=null&&!weightmap.get("pname_comment").toString().trim().equals(""))
		{	
		  pname.setComment(weightmap.get("pname_comment").toString());
		  //weight.setPname(pname);
		}
		}
		
	   }
	 }
		weight.setPname(pname);
		//是addrprovince的赋值
		Field addrprovince=new Field();
		if(weightmap.containsKey("addrprovince_types_weight"))
		{
		  // Map<String,Float> types=new HashMap<String,Float>();
		  if(weightmap.get("addrprovince_types_weight")!=null&&!weightmap.get("addrprovince_types_weight").toString().trim().equals(""))
		  {
			  boolean unique=false;
			  if(weightmap.containsKey("addrprovince_unique"))
				{
				if(weightmap.get("addrprovince_unique")!=null&&!weightmap.get("addrprovince_unique").toString().trim().equals(""))
				{
					unique=Boolean.valueOf(weightmap.get("addrprovince_unique").toString());
				  //weight.setAddrprovince(addrprovince);
				}
				}
		   //types.put("weight",Float.valueOf(weightmap.get("addrprovince_types_weight").toString()));
		   addrprovince.addType("weight", Float.valueOf(weightmap.get("addrprovince_types_weight").toString()), null, null, unique);
		  // weight.setAddrprovince(addrprovince);
		
		if(weightmap.containsKey("addrprovince_name"))
		{
		if(weightmap.get("addrprovince_name")!=null&&!weightmap.get("addrprovince_name").toString().trim().equals(""))
		{
		  addrprovince.setName(weightmap.get("addrprovince_name").toString());
		  //weight.setAddrprovince(addrprovince);
		}
		}
		if(weightmap.containsKey("addrprovince_comment"))
		{
		if(weightmap.get("addrprovince_comment")!=null&&!weightmap.get("addrprovince_comment").toString().trim().equals(""))
		{
		  addrprovince.setComment(weightmap.get("addrprovince_comment").toString());
		  //weight.setAddrprovince(addrprovince);
		}
		}
		
	   }
	  }		
	  weight.setAddrprovince(addrprovince);
		//是addrcounty的赋值
		 Field addrcounty=new Field();
		if(weightmap.containsKey("addrcounty_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("addrcounty_types_weight")!=null&&!weightmap.get("addrcounty_types_weight").toString().trim().equals(""))
		  {
			boolean unique=false;
			if(weightmap.containsKey("addrcounty_unique"))
			{
			if(weightmap.get("addrcounty_unique")!=null&&!weightmap.get("addrcounty_unique").toString().trim().equals(""))
			{
			   unique=Boolean.valueOf(weightmap.get("addrcounty_unique").toString());
			  //weight.setAddrcounty(addrcounty);
			}
			}
		     //types.put("weight",Float.valueOf(weightmap.get("addrcounty_types_weight").toString()));
			 addrcounty.addType("weight", Float.valueOf(weightmap.get("addrcounty_types_weight").toString()), null, null, false);
			 //weight.setAddrcounty(addrcounty);
			
		if(weightmap.containsKey("addrcounty_name"))
		{
		if(weightmap.get("addrcounty_name")!=null&&!weightmap.get("addrcounty_name").toString().trim().equals(""))
		 {
		  addrcounty.setName(weightmap.get("addrcounty_name").toString());
		  //weight.setAddrcounty(addrcounty);
		}
		}
		if(weightmap.containsKey("addrcounty_comment"))
		{
	 if(weightmap.get("addrcounty_comment")!=null&&!weightmap.get("addrcounty_comment").toString().trim().equals(""))
		{
		  addrcounty.setComment(weightmap.get("addrcounty_comment").toString());
		  //weight.setAddrcounty(addrcounty);
		}
		}
	
	 }
	}
	weight.setAddrcounty(addrcounty);
		//是addrcity的赋值
		 Field addrcity=new Field();
	 if(weightmap.containsKey("addrcity_types_weight"))
	 {
	 if(weightmap.get("addrcity_types_weight")!=null&&!weightmap.get("addrcity_types_weight").toString().trim().equals(""))
	 {
		 
		 boolean unique=false;
		 if(weightmap.containsKey("addrcity_unique"))
			{
			if(weightmap.get("addrcity_unique")!=null&&!weightmap.get("addrcity_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("addrcity_unique").toString());
			 // weight.setAddrcity(addrcity);
			}
			}
		//Map<String,Float> types=new HashMap<String,Float>();
		//types.put("weight",Float.valueOf(weightmap.get("addrcity_types_weight").toString()));
		addrcity.addType("weight", Float.valueOf(weightmap.get("addrcity_types_weight").toString()), null, null, unique);
		//weight.setAddrcity(addrcity);
			
		if(weightmap.containsKey("addrcity_name"))
		{
		if(weightmap.get("addrcity_name")!=null&&!weightmap.get("addrcity_name").toString().trim().equals(""))
		{
		  addrcity.setName(weightmap.get("addrcity_name").toString());
		  //weight.setAddrcity(addrcity);
		}
		}
		if(weightmap.containsKey("addrcity_comment"))
		{
		if(weightmap.get("addrcity_comment")!=null&&!weightmap.get("addrcity_comment").toString().trim().equals(""))
		{
		  addrcity.setComment(weightmap.get("addrcity_comment").toString());
		  //weight.setAddrcity(addrcity);
		}
		}
		
	 }
	 }
	 weight.setAddrcity(addrcity);
	 //是addrtown的赋值
		 Field addrtown=new Field();
	if(weightmap.containsKey("addrtown_types_weight"))
	 {
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("addrtown_types_weight")!=null&&!weightmap.get("addrtown_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("addrtown_unique"))
			{
			if(weightmap.get("addrtown_unique")!=null&&!weightmap.get("addrtown_unique").toString().trim().equals(""))
			{
				unique=Boolean.valueOf(weightmap.get("addrtown_unique").toString());
			  //weight.setAddrtown(addrtown);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("addrtown_types_weight").toString()));
		addrtown.addType("weight", Float.valueOf(weightmap.get("addrtown_types_weight").toString()), null, null, unique);
		//weight.setAddrtown(addrtown);
	
		if(weightmap.containsKey("addrtown_name"))
		{
		if(weightmap.get("addrtown_name")!=null&&!weightmap.get("addrtown_name").toString().trim().equals(""))
		{
		  addrtown.setName(weightmap.get("addrtown_name").toString());
		  //weight.setAddrtown(addrtown);
		}
		}
		if(weightmap.containsKey("addrtown_comment"))
		{
		if(weightmap.get("addrtown_comment")!=null&&!weightmap.get("addrtown_comment").toString().trim().equals(""))
		{
		  addrtown.setComment(weightmap.get("addrtown_comment").toString());
		  //weight.setAddrtown(addrtown);
		}
		}
	
		}
	 }weight.setAddrtown(addrtown);
		//是addrvillage的赋值
		 Field addrvillage=new Field();
	 if(weightmap.containsKey("addrvillage_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("addrvillage_types_weight")!=null&&!weightmap.get("addrvillage_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("addrvillage_unique"))
			{
		   if(weightmap.get("addrvillage_unique")!=null&&!weightmap.get("addrvillage_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("addrvillage_unique").toString());
			  //weight.setAddrvillage(addrvillage);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("addrvillage_types_weight").toString()));
	    addrvillage.addType("weight", Float.valueOf(weightmap.get("addrvillage_types_weight").toString()), null, null, unique);
		//weight.setAddrvillage(addrvillage);
		
		if(weightmap.containsKey("addrvillage_name"))
		{
		if(weightmap.get("addrvillage_name")!=null&&!weightmap.get("addrvillage_name").toString().trim().equals(""))
		{
		  addrvillage.setName(weightmap.get("addrvillage_name").toString());
		  //weight.setAddrvillage(addrvillage);
		}
		}
		if(weightmap.containsKey("addrvillage_comment"))
		{
		if(weightmap.get("addrvillage_comment")!=null&&!weightmap.get("addrvillage_comment").toString().trim().equals(""))
		{
		  addrvillage.setComment(weightmap.get("addrvillage_comment").toString());
		  //weight.setAddrvillage(addrvillage);
		}
		}
	
		}
		}
	 weight.setAddrvillage(addrvillage);
		//是addrhouseid的赋值
		 Field addrhouseid=new Field();
	if(weightmap.containsKey("addrhouseid_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("addrhouseid_types_weight")!=null&&!weightmap.get("addrhouseid_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("addrhouseid_unique"))
			{
			if(weightmap.get("addrhouseid_unique")!=null&&!weightmap.get("addrhouseid_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("addrhouseid_unique").toString());
			  //weight.setAddrhouseid(addrhouseid);
			}
		  }
		//types.put("weight",Float.valueOf(weightmap.get("addrhouseid_types_weight").toString()));
		addrhouseid.addType("weight", Float.valueOf(weightmap.get("addrhouseid_types_weight").toString()), null, null, unique);
		//weight.setAddrhouseid(addrhouseid);
			
		if(weightmap.containsKey("addrhouseid_name"))
		{
		if(weightmap.get("addrhouseid_name")!=null&&!weightmap.get("addrhouseid_name").toString().trim().equals(""))
		{
		  addrhouseid.setName(weightmap.get("addrhouseid_name").toString());
		  //weight.setAddrhouseid(addrhouseid);
		}
		}
		if(weightmap.containsKey("addrhouseid_comment"))
		{
		if(weightmap.get("addrhouseid_comment")!=null&&!weightmap.get("addrhouseid_comment").toString().trim().equals(""))
		{
		  addrhouseid.setComment(weightmap.get("addrhouseid_comment").toString());
		  //weight.setAddrhouseid(addrhouseid);
		}
		}
	
	}
	}
	weight.setAddrhouseid(addrhouseid);
		//是gender的赋值
		 Field gender=new Field();
	if(weightmap.containsKey("gender_types_weight"))
	  {
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("gender_types_weight")!=null&&!weightmap.get("gender_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("gender_unique"))
			{
			if(weightmap.get("gender_unique")!=null&&!weightmap.get("gender_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("gender_unique").toString());
			  //weight.setGender(gender);
			}
		  }
		//types.put("weight",Float.valueOf(weightmap.get("gender_types_weight").toString()));
		gender.addType("weight", Float.valueOf(weightmap.get("gender_types_weight").toString()), null, null, unique);
		//weight.setGender(gender);
			
		if(weightmap.containsKey("gender_name"))
		{
		if(weightmap.get("gender_name")!=null&&!weightmap.get("gender_name").toString().trim().equals(""))
		{
		  gender.setName(weightmap.get("gender_name").toString());
		  //weight.setGender(gender);
		}
		}
		if(weightmap.containsKey("gender_comment"))
		{
		if(weightmap.get("gender_comment")!=null&&!weightmap.get("gender_comment").toString().trim().equals(""))
		{
		  gender.setComment(weightmap.get("gender_comment").toString());
		  //weight.setGender(gender);
		}
		}
	
	  }
     }weight.setGender(gender);
		//是birthday的赋值
		 Field birthday=new Field();
   if(weightmap.containsKey("birthday_types_weight"))
	   {
		//Map<String,Float> types=new HashMap<String,Float>();	  
		if(weightmap.get("birthday_types_weight")!=null&&!weightmap.get("birthday_types_weight").toString().trim().equals(""))
		{
			 boolean unique=false;
			if(weightmap.containsKey("birthday_unique"))
			{
			if(weightmap.get("birthday_unique")!=null&&!weightmap.get("birthday_comment").toString().trim().equals(""))
			{
			 unique=Boolean.valueOf(weightmap.get("birthday_unique").toString());
			  //weight.setBirthday(birthday);
			}
		   }
		//types.put("weight",Float.valueOf(weightmap.get("birthday_types_weight").toString()));
	    birthday.addType("weight", Float.valueOf(weightmap.get("birthday_types_weight").toString()), null, null, unique);
		//weight.setBirthday(birthday);
			
		if(weightmap.containsKey("birthday_name"))
		{
		if(weightmap.get("birthday_name")!=null&&!weightmap.get("birthday_name").toString().trim().equals(""))
		{
		  birthday.setName(weightmap.get("birthday_name").toString());
		  //weight.setBirthday(birthday);
		}
		}
		if(weightmap.containsKey("birthday_comment"))
		{
		if(weightmap.get("birthday_comment")!=null&&!weightmap.get("birthday_comment").toString().trim().equals(""))
		{
		  birthday.setComment(weightmap.get("birthday_comment").toString());
		  //weight.setBirthday(birthday);
		}
		}	
	 }
   }
   weight.setBirthday(birthday);
		//是email的赋值
		 Field email=new Field();
	if(weightmap.containsKey("email_types_weight"))
		{
		 //Map<String,Float> types=new HashMap<String,Float>();
		 if(weightmap.get("email_types_weight")!=null&&!weightmap.get("email_types_weight").toString().trim().equals(""))
		{
			 boolean unique=false;
				if(weightmap.containsKey("email_unique"))
				{
				if(weightmap.get("email_unique")!=null&&!weightmap.get("email_unique").toString().trim().equals(""))
				{
			     unique=Boolean.valueOf(weightmap.get("email_unique").toString());
				  //weight.setEmail(email);
				}
				}
		 //types.put("weight",Float.valueOf(weightmap.get("email_types_weight").toString()));
		 email.addType("weight", Float.valueOf(weightmap.get("email_types_weight").toString()), null, null, unique);
		 //weight.setEmail(email);
			
		if(weightmap.containsKey("email_name"))
		{
		if(weightmap.get("email_name")!=null&&!weightmap.get("email_name").toString().trim().equals(""))
		{
		  email.setName(weightmap.get("email_name").toString());
		 // weight.setEmail(email);
		}
		}
		if(weightmap.containsKey("email_comment"))
		{
		if(weightmap.get("email_comment")!=null&&!weightmap.get("email_comment").toString().trim().equals(""))
		{
		  email.setComment(weightmap.get("email_comment").toString());
		 // weight.setEmail(email);
		}
		}
	
		}
		}
	    weight.setEmail(email);
		//是nickname的赋值
		Field nickname=new Field();
		if(weightmap.containsKey("nickname_types_weight"))
		{
		  //Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("nickname_types_weight")!=null&&!weightmap.get("nickname_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("nickname_unique"))
			{
			if(weightmap.get("nickname_unique")!=null&&!weightmap.get("nickname_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("nickname_unique").toString());
			  //weight.setNickname(nickname);
			}
			}
		  //types.put("weight",Float.valueOf(weightmap.get("nickname_types_weight").toString()));
		  nickname.addType("weight", Float.valueOf(weightmap.get("nickname_types_weight").toString()), null, null, unique);
		  //weight.setNickname(nickname);
		
		if(weightmap.containsKey("nickname_name"))
		{
		if(weightmap.get("nickname_name")!=null&&!weightmap.get("nickname_name").toString().trim().equals(""))
		{
		  nickname.setName(weightmap.get("nickname_name").toString());
		  //weight.setNickname(nickname);
		}
		}
		if(weightmap.containsKey("nickname_comment"))
		{
		if(weightmap.get("nickname_comment")!=null&&!weightmap.get("nickname_comment").toString().trim().equals(""))
		{
		  nickname.setComment(weightmap.get("nickname_comment").toString());
		  //weight.setNickname(nickname);
		}
		}
	
		}
      }weight.setNickname(nickname);
		//是registeretype的赋值
		 Field registeretype=new Field();
		if(weightmap.containsKey("registeretype_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("registeretype_types_weight")!=null&&!weightmap.get("registeretype_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("registeretype_unique"))
			{
			if(weightmap.get("registeretype_unique")!=null&&!weightmap.get("registeretype_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("registeretype_unique").toString());
			  //weight.setRegisteretype(registeretype);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("registeretype_types_weight").toString()));
	    registeretype.addType("weight", Float.valueOf(weightmap.get("registeretype_types_weight").toString()), null, null, unique);
		//weight.setRegisteretype(registeretype);
		
		if(weightmap.containsKey("registeretype_name"))
		{
		if(weightmap.get("registeretype_name")!=null&&!weightmap.get("registeretype_name").toString().trim().equals(""))
		{
		  registeretype.setName(weightmap.get("registeretype_name").toString());
		  //weight.setRegisteretype(registeretype);
		}
		}
		if(weightmap.containsKey("registeretype_comment"))
		{
		if(weightmap.get("registeretype_comment")!=null&&!weightmap.get("registeretype_comment").toString().trim().equals(""))
		{
		  registeretype.setComment(weightmap.get("registeretype_comment").toString());
		  //weight.setRegisteretype(registeretype);
		}
		}
	
		}
      }
		//是nationality的赋值
		 Field nationality=new Field();
	if(weightmap.containsKey("nationality_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("nationality_types_weight")!=null&&!weightmap.get("nationality_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("nationality_unique"))
			{
			if(weightmap.get("nationality_unique")!=null&&!weightmap.get("nationality_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("nationality_unique").toString());
			  //weight.setNationality(nationality);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("nationality_types_weight").toString()));
		nationality.addType("weight", Float.valueOf(weightmap.get("nationality_types_weight").toString()), null, null, unique);
		//weight.setNationality(nationality);
		
		if(weightmap.containsKey("nationality_name"))
		{
		if(weightmap.get("nationality_name")!=null&&!weightmap.get("nationality_name").toString().trim().equals(""))
		{
		  nationality.setName(weightmap.get("nationality_name").toString());
		 // weight.setNationality(nationality);
		}
		}
		if(weightmap.containsKey("nationality_comment"))
		{
		if(weightmap.get("nationality_comment")!=null&&!weightmap.get("nationality_comment").toString().trim().equals(""))
		{
		  nationality.setComment(weightmap.get("nationality_comment").toString());
		  //weight.setNationality(nationality);
		}
		}
		
		}
       }weight.setNationality(nationality);
		//是 marriedstatus的赋值
		 Field marriedstatus=new Field();
	 if(weightmap.containsKey("marriedstatus_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("marriedstatus_types_weight")!=null&&!weightmap.get("marriedstatus_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("marriedstatus_unique"))
			{
			if(weightmap.get("marriedstatus_unique")!=null&&!weightmap.get("marriedstatus_unique").toString().trim().equals(""))
			{
			 unique=Boolean.valueOf(weightmap.get("marriedstatus_unique").toString());
			  //weight.setMarriedstatus(marriedstatus);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("marriedstatus_types_weight").toString()));
		marriedstatus.addType("weight", Float.valueOf(weightmap.get("marriedstatus_types_weight").toString()), null, null, unique);
	   //weight.setMarriedstatus(marriedstatus);
			
		if(weightmap.containsKey("marriedstatus_name"))
		{
		if(weightmap.get("marriedstatus_name")!=null&&!weightmap.get("marriedstatus_name").toString().trim().equals(""))
		{
		  marriedstatus.setName(weightmap.get("marriedstatus_name").toString());
		  //weight.setMarriedstatus(marriedstatus);
		}
		}
		if(weightmap.containsKey("marriedstatus_comment"))
		{
		if(weightmap.get("marriedstatus_comment")!=null&&!weightmap.get("marriedstatus_comment").toString().trim().equals(""))
		{
		  marriedstatus.setComment(weightmap.get("marriedstatus_comment").toString());
		 //weight.setMarriedstatus(marriedstatus);
		}
		}
	
	 }
	}weight.setMarriedstatus(marriedstatus);
		//是 nation的赋值
		 Field nation=new Field();
		if(weightmap.containsKey("nation_types_weight"))
		  {
		   //Map<String,Float> types=new HashMap<String,Float>();
		   if(weightmap.get("nation_types_weight")!=null&&!weightmap.get("nation_types_weight").toString().trim().equals(""))
			{
			   boolean unique=false;
				if(weightmap.containsKey("nation_unique"))
				{
				if(weightmap.get("nation_unique")!=null&&!weightmap.get("nation_unique").toString().trim().equals(""))
				{
				  unique=Boolean.valueOf(weightmap.get("nation_unique").toString());
				  //weight.setNation(nation);
				}
				}
		   //types.put("weight",Float.valueOf(weightmap.get("nation_types_weight").toString()));
		   nation.addType("weight", Float.valueOf(weightmap.get("nation_types_weight").toString()), null, null, unique);
		   //weight.setNation(nation);
			
		if(weightmap.containsKey("nation_name"))
		{
		if(weightmap.get("nation_name")!=null&&!weightmap.get("nation_name").toString().trim().equals(""))
		{
		  nation.setName(weightmap.get("nation_name").toString());
		  //weight.setNation(nation);
		}
		}
		if(weightmap.containsKey("nation_comment"))
		{
		if(weightmap.get("nation_comment")!=null&&!weightmap.get("nation_comment").toString().trim().equals(""))
		{
		  nation.setComment(weightmap.get("nation_comment").toString());
		  //weight.setNation(nation);
		}
		}
	
		}
	}
		weight.setNation(nation);	
   //是 age的赋值
		 Field age=new Field();
 if(weightmap.containsKey("age_types_weight"))
    {
	  //Map<String,Float> types=new HashMap<String,Float>();
	 if(weightmap.get("age_types_weight")!=null&&!weightmap.get("age_types_weight").toString().trim().equals(""))
	{
		 boolean unique=false;
		 if(weightmap.containsKey("age_unique"))
			{
			if(weightmap.get("age_unique")!=null&&!weightmap.get("age_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("age_unique").toString());
			  //weight.setAge(age);
			}
	      }
	  //types.put("weight",Float.valueOf(weightmap.get("age_types_weight").toString()));
	  age.addType("weight", Float.valueOf(weightmap.get("age_types_weight").toString()), null, null, unique);
	  //weight.setAge(age);
			
		if(weightmap.containsKey("age_name"))
		{
		if(weightmap.get("age_name")!=null&&!weightmap.get("age_name").toString().trim().equals(""))
		{
		  age.setName(weightmap.get("age_name").toString());
		  //weight.setAge(age);
		}
		}
		if(weightmap.containsKey("age_comment"))
		{
		if(weightmap.get("age_comment")!=null&&!weightmap.get("age_comment").toString().trim().equals(""))
		{
		  age.setComment(weightmap.get("age_comment").toString());
		  //weight.setAge(age);
		}
		}
		
     }
	}weight.setAge(age);
		//是 booldtype的赋值
		 Field booldtype=new Field();
   if(weightmap.containsKey("booldtype_types_weight"))
	{
	  //Map<String,Float> types=new HashMap<String,Float>();
	  if(weightmap.get("booldtype_types_weight")!=null&&!weightmap.get("booldtype_types_weight").toString().trim().equals(""))
		{
		  boolean unique=false;
			if(weightmap.containsKey("agebooldtype_unique"))
			{
			if(weightmap.get("agebooldtype_unique")!=null&&!weightmap.get("agebooldtype_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("booldtype_unique").toString());
			  //weight.setBooldtype(booldtype);
			}
		   }
	  //types.put("weight",Float.valueOf(weightmap.get("booldtype_types_weight").toString()));
	  booldtype.addType("weight", Float.valueOf(weightmap.get("booldtype_types_weight").toString()), null, null, unique);
	  //weight.setBooldtype(booldtype);
			
		if(weightmap.containsKey("booldtype_name"))
		{
		if(weightmap.get("booldtype_name")!=null&&!weightmap.get("booldtype_name").toString().trim().equals(""))
		{
		  booldtype.setName(weightmap.get("booldtype_name").toString());
		 // weight.setBooldtype(booldtype);
		}
		}
		if(weightmap.containsKey("booldtype_comment"))
		{
		if(weightmap.get("booldtype_comment")!=null&&!weightmap.get("booldtype_comment").toString().trim().equals(""))
		{
		  booldtype.setComment(weightmap.get("booldtype_comment").toString());
		  //weight.setBooldtype(booldtype);
		}
		}
	
       }
     }weight.setBooldtype(booldtype);
		//是 rh的赋值
		 Field rh=new Field();
		if(weightmap.containsKey("rh_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("rh_types_weight")!=null&&!weightmap.get("rh_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("rh_unique"))
			{
			if(weightmap.get("rh_unique")!=null&&!weightmap.get("rh_unique").toString().trim().equals(""))
			{
			 unique=Boolean.valueOf(weightmap.get("rh_unique").toString());
			  //weight.setRh(rh);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("rh_types_weight").toString()));
		rh.addType("weight", Float.valueOf(weightmap.get("rh_types_weight").toString()), null, null, unique);
		//weight.setRh(rh);
			
		if(weightmap.containsKey("rh_name"))
		{
		if(weightmap.get("rh_name")!=null&&!weightmap.get("rh_name").toString().trim().equals(""))
		{
		  rh.setName(weightmap.get("rh_name").toString());
		  //weight.setRh(rh);
		}
		}
		if(weightmap.containsKey("rh_comment"))
		{
		if(weightmap.get("rh_comment")!=null&&!weightmap.get("rh_comment").toString().trim().equals(""))
		{
		  rh.setComment(weightmap.get("rh_comment").toString());
		  //weight.setRh(rh);
		}
		}
	
		}
        }weight.setRh(rh);
		//是workdate的赋值
		Field workdate=new Field();
		if(weightmap.containsKey("workdate_types_weight"))
		{
		  //Map<String,Float> types=new HashMap<String,Float>();
		  if(weightmap.get("workdate_types_weight")!=null&&!weightmap.get("workdate_types_weight").toString().trim().equals(""))
			{
			  boolean unique=false;
				if(weightmap.containsKey("workdate_unique"))
				{
				if(weightmap.get("workdate_unique")!=null&&!weightmap.get("workdate_unique").toString().trim().equals(""))
				{
				  unique=Boolean.valueOf(weightmap.get("workdate_unique").toString());
				  //weight.setWorkdate(workdate);
				}
				}
		  //types.put("weight",Float.valueOf(weightmap.get("workdate_types_weight").toString()));
		  workdate.addType("weight", Float.valueOf(weightmap.get("workdate_types_weight").toString()), null, null, false);
		  //weight.setWorkdate(workdate);
		
		if(weightmap.containsKey("workdate_name"))
		{
		if(weightmap.get("workdate_name")!=null&&!weightmap.get("workdate_name").toString().trim().equals(""))
		{
		  workdate.setName(weightmap.get("workdate_name").toString());
		  //weight.setWorkdate(workdate);
		}
		}
		if(weightmap.containsKey("workdate_comment"))
		{
		if(weightmap.get("workdate_comment")!=null&&!weightmap.get("workdate_comment").toString().trim().equals(""))
		{
		  workdate.setComment(weightmap.get("workdate_comment").toString());
		  //weight.setWorkdate(workdate);
		}
		}
	
		}
        }weight.setWorkdate(workdate);
		//是jobtypecode的赋值
		 Field jobtypecode=new Field();
		if(weightmap.containsKey("jobtypecode_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("jobtypecode_types_weight")!=null&&!weightmap.get("jobtypecode_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("jobtypecode_unique"))
			{
			if(weightmap.get("jobtypecode_unique")!=null&&!weightmap.get("jobtypecode_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("jobtypecode_unique").toString());
			  //weight.setJobtypecode(jobtypecode);
			}	
			}
		//types.put("weight",Float.valueOf(weightmap.get("jobtypecode_types_weight").toString()));
	    jobtypecode.addType("weight", Float.valueOf(weightmap.get("jobtypecode_types_weight").toString()), null, null, unique);
		//weight.setJobtypecode(jobtypecode);
			
		if(weightmap.containsKey("jobtypecode_name"))
		{
		if(weightmap.get("jobtypecode_name")!=null&&!weightmap.get("jobtypecode_name").toString().trim().equals(""))
		{
		  jobtypecode.setName(weightmap.get("jobtypecode_name").toString());
		  //weight.setJobtypecode(jobtypecode);
		}
		}
		if(weightmap.containsKey("jobtypecode_comment"))
		{
		if(weightmap.get("jobtypecode_comment")!=null&&!weightmap.get("jobtypecode_comment").toString().trim().equals(""))
		{
		  jobtypecode.setComment(weightmap.get("jobtypecode_comment").toString());
		  //weight.setJobtypecode(jobtypecode);
		}
		}
	
		}
	 }weight.setJobtypecode(jobtypecode);
		//是educationcode的赋值
		 Field educationcode=new Field();
	if(weightmap.containsKey("educationcode_types_weight"))
	  {
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("educationcode_types_weight")!=null&&!weightmap.get("educationcode_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("educationcode_unique"))
			{
			if(weightmap.get("educationcode_unique")!=null&&!weightmap.get("educationcode_unique").toString().trim().equals(""))
			{
			  unique=Boolean.valueOf(weightmap.get("educationcode_unique").toString());
			  //weight.setEducationcode(educationcode);
			}
		  }
		//types.put("weight",Float.valueOf(weightmap.get("educationcode_types_weight").toString()));
	    educationcode.addType("weight", Float.valueOf(weightmap.get("educationcode_types_weight").toString()), null, null, unique);
		//weight.setEducationcode(educationcode);
			
		if(weightmap.containsKey("educationcode_name"))
		{
		if(weightmap.get("educationcode_name")!=null&&!weightmap.get("educationcode_name").toString().trim().equals(""))
		{
		  educationcode.setName(weightmap.get("educationcode_name").toString());
		  //weight.setEducationcode(educationcode);
		}
		}
		if(weightmap.containsKey("educationcode_comment"))
		{
		if(weightmap.get("educationcode_comment")!=null&&!weightmap.get("educationcode_comment").toString().trim().equals(""))
		{
		  educationcode.setComment(weightmap.get("educationcode_comment").toString());
		  //weight.setEducationcode(educationcode);
		}
		}
	
	}
	}weight.setEducationcode(educationcode);
		//是degreecode的赋值
		 Field degreecode=new Field();
	 if(weightmap.containsKey("degreecode_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("degreecode_types_weight")!=null&&!weightmap.get("degreecode_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("degreecode_unique"))
			{
			if(weightmap.get("degreecode_unique")!=null&&!weightmap.get("degreecode_unique").toString().trim().equals(""))
			{
			 unique=Boolean.valueOf(weightmap.get("degreecode_unique").toString());
			 //weight.setDegreecode(degreecode);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("degreecode_types_weight").toString()));
		degreecode.addType("weight", Float.valueOf(weightmap.get("degreecode_types_weight").toString()), null, null, unique);
		//weight.setDegreecode(degreecode);
			
		if(weightmap.containsKey("degreecode_name"))
		{
		if(weightmap.get("degreecode_name")!=null&&!weightmap.get("degreecode_name").toString().trim().equals(""))
		{
		  degreecode.setName(weightmap.get("degreecode_name").toString());
		  //weight.setDegreecode(degreecode);
		}
		}
		if(weightmap.containsKey("degreecode_comment"))
		{
	   if(weightmap.get("degreecode_comment")!=null&&!weightmap.get("degreecode_comment").toString().trim().equals(""))
		{
		  degreecode.setComment(weightmap.get("degreecode_comment").toString());
		  //weight.setDegreecode(degreecode);
		}
		}
	
		}
	}weight.setDegreecode(degreecode);
		//是orgcodes的赋值
		 Field orgcodes=new Field();
	 if(weightmap.containsKey("orgcodes_types_weight"))
		{
		//Map<String,Float> types=new HashMap<String,Float>();
		if(weightmap.get("orgcodes_types_weight")!=null&&!weightmap.get("orgcodes_types_weight").toString().trim().equals(""))
		{
			boolean unique=false;
			if(weightmap.containsKey("orgcodes_unique"))
			{
			if(weightmap.get("orgcodes_unique")!=null&&!weightmap.get("orgcodes_unique").toString().trim().equals(""))
			{
			 unique=Boolean.valueOf(weightmap.get("orgcodes_unique").toString());
			  //weight.setOrgcodes(orgcodes);
			}
			}
		//types.put("weight",Float.valueOf(weightmap.get("orgcodes_types_weight").toString()));
	    orgcodes.addType("weight", Float.valueOf(weightmap.get("orgcodes_types_weight").toString()), null, null, unique);
		//weight.setOrgcodes(orgcodes);
			
		if(weightmap.containsKey("orgcodes_name"))
		{
		if(weightmap.get("orgcodes_name")!=null&&!weightmap.get("orgcodes_name").toString().trim().equals(""))
		{
		  orgcodes.setName(weightmap.get("orgcodes_name").toString());
		  //weight.setOrgcodes(orgcodes);
		}
		}
		if(weightmap.containsKey("orgcodes_comment"))
		{
		if(weightmap.get("orgcodes_comment")!=null&&!weightmap.get("orgcodes_comment").toString().trim().equals(""))
		{
		  orgcodes.setComment(weightmap.get("orgcodes_comment").toString());
		  //weight.setOrgcodes(orgcodes);
		}
		}
		
		}
      }weight.setOrgcodes(orgcodes);
		//是phones的赋值
		 Field phones=new Field();
		if(weightmap.containsKey("phones_name"))
		{
		 
		if(weightmap.get("phones_name")!=null&&!weightmap.get("phones_name").toString().trim().equals(""))
		{
		  phones.setName(weightmap.get("phones_name").toString());
		  //weight.setPhones(phones);
		}
		}
		if(weightmap.containsKey("phones_comment"))
		{
		if(weightmap.get("phones_comment")!=null&&!weightmap.get("phones_comment").toString().trim().equals(""))
		{
		  phones.setComment(weightmap.get("phones_comment").toString());
		 // weight.setPhones(phones);
		}
		}
		if(weightmap.containsKey("phones_types_weight"))
		{
		  //Map<String,Float> types=new HashMap<String,Float>();
		  if(weightmap.get("phones_types_weight")!=null&&!weightmap.get("phones_types_weight").toString().trim().equals(""))
			{
		  //types.put("weight",Float.valueOf(weightmap.get("phones_types_weight").toString()));
		  phones.addType("weight", Float.valueOf(weightmap.get("phones_types_weight").toString()), null, null, false);
		  //weight.setPhones(phones);
		}
		}
		Map<String,Float> types=new HashMap<String,Float>();
		String s2="phones_types_";
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
		for(int i=0;i<arrphone.length;i++)
		{
		  if(weightmap.containsKey(s2+arrphone[i]))
		   {
			  if(weightmap.get(s2+arrphone[i])!=null&&!weightmap.get(s2+arrphone[i]).toString().trim().equals(""))
			  {
				  String com=null;
				  if(weightmap.containsValue(s2+arrphone[i]+"_comment")&&weightmap.get(s2+arrphone[i]+"_comment")!=null)
				  {
					  com=weightmap.get(s2+arrphone[i]+"_comment").toString();
				  }
				  boolean unique=false;
				  if(weightmap.containsValue("phones_uniques_"+arrphone[i])&&weightmap.get("phones_uniques_"+arrphone[i])!=null)
				  {
					  unique=Boolean.valueOf(weightmap.get("phones_uniques_"+arrphone[i]).toString());
				  }
				  phones.addType(arrphone[i], Float.valueOf(weightmap.get(s2+arrphone[i]).toString()), com, null, unique);
			}}
		}
		  //phones.addType(arrphone[i], Float.valueOf(weightmap.get(s2+arrphone[i]).toString()), comment, regx, unique);
		  //weight.setPhones(phones);
	/*	if(weightmap.containsKey("phones_unique"))
		{
		if(weightmap.get("phones_unique")!=null&&!weightmap.get("phones_unique").toString().trim().equals(""))
		{
		  phones.setUnique(Boolean.valueOf(weightmap.get("phones_unique").toString()));
		  //weight.setPhones(phones);
		}
		}*/
		weight.setPhones(phones);
		
		 //是credentials的赋值
		Field credentials=new Field();
		if(weightmap.containsKey("credentials_name"))
		{
		if(weightmap.get("credentials_name")!=null&&!weightmap.get("credentials_name").toString().trim().equals(""))
		{
		  credentials.setName(weightmap.get("credentials_name").toString());
		  //weight.setCredentials(credentials);
		}
		}
		if(weightmap.containsKey("credentials_comment"))
		{
		if(weightmap.get("credentials_comment")!=null&&!weightmap.get("credentials_comment").toString().trim().equals(""))
		{
		  credentials.setComment(weightmap.get("credentials_comment").toString());
		  //weight.setCredentials(credentials);
		}
		}
		if(weightmap.containsKey("credentials_types_weight"))
		{
		//Map<String,Float> types1=new HashMap<String,Float>();
		if(weightmap.get("credentials_types_weight")!=null&&!weightmap.get("credentials_types_weight").toString().trim().equals(""))
		{
		//types.put("weight",Float.valueOf(weightmap.get("credentials_types_weight").toString()));
		credentials.addType("weight", Float.valueOf(weightmap.get("credentials_types_weight").toString()), null, null, false);
		//weight.setCredentials(credentials);
		}
		}
		Map<String,String> uniques=new HashMap<String,String>();
		String s1="credentials_types_";
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
		for(int i=0;i<arrc.length;i++)
		{
		if(weightmap.containsKey(s1+arrc[i]))
		{
		if(weightmap.get(s1+arrc[i])!=null&&!weightmap.get(s1+arrc[i]).toString().trim().equals(""))
			{
		      //uniques.put(arrc[i],weightmap.get(s1+arrc[i]).toString());
			String com=null;
			if(weightmap.containsKey(s1+arrc[i]+"_comment")&&weightmap.get(s1+arrc[i]+"_comment")!=null)
			{
				com=weightmap.get(s1+arrc[i]+"_comment").toString();
			}
			boolean unique=false;
			if(weightmap.containsKey("credentials_uniques_"+arrc[i])&&weightmap.get("credentials_uniques_"+arrc[i])!=null)
			{
				unique=Boolean.valueOf(weightmap.get("credentials_uniques_"+arrc[i]).toString());
			}
			credentials.addType(arrc[i], Float.valueOf(weightmap.get(s1+arrc[i]).toString()), com, null, unique);
		}}
		}
		//credentials.setUniques(uniques);
		//weight.setCredentials(credentials);
	/*	if(weightmap.containsKey("credentials_unique"))
		{
		if(weightmap.get("credentials_unique")!=null&&!weightmap.get("credentials_unique").toString().trim().equals(""))
		{
		  credentials.setUnique(Boolean.valueOf(weightmap.get("credentials_unique").toString()));
		  //weight.setCredentials(credentials);
		}
		}*/
		weight.setCredentials(credentials);		
		WeightconfigchangeIn in = new WeightconfigchangeIn();
		if(weightmap.containsKey("callback"))
		{
		 in.setCallback(weightmap.get("callback").toString());
		}
		in.setWeight(weight);
	    return weightconfigchangeservice.weightconfigchange(in);
		}
}
