package com.livechain.pid.rest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.spring.converter.ConverUtils;
import org.springframework.stereotype.Component;
import com.livechain.pid.model.Validation;
import com.livechain.pid.rest.model.ValidateconfigchangeIn;
import com.livechain.pid.rest.model.ValidateconfigchangeOut;
import com.livechain.pid.util.SystemConfig;
import com.livechain.pid.validater.DataValidaterImpl;
import com.livechain.pid.validater.ValidatonLoaderImpl;

//修改数据校验配置
@Component 
public class ValidateconfigchangeServiceImpl {
	private DataValidaterImpl dataValidaterImpl;
	public DataValidaterImpl getDataValidaterImpl() {
		return dataValidaterImpl;
	}

	@Resource(name = "standvalid")
	public void setDataValidaterImpl(DataValidaterImpl dataValidaterImpl) {
		this.dataValidaterImpl = dataValidaterImpl;
	}
	private final static Log log4j=LogFactory.getLog(DataValidaterImpl.class);
	@SuppressWarnings("unchecked")
	public void loader(Validation validate,Object properties) throws IOException, DocumentException {
		//获取路径
		Map<String,Object> proes=(Map<String,Object>)properties;
		URL url = ValidatonLoaderImpl.class.getClassLoader().getResource(proes.get("validaterpath").toString());
		log4j.info("loading xml file:"+url.getFile());
		File validatefile= new File(url.getFile());
		XMLWriter writer = null;// 声明写XML的对象
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");// 设置XML文件的编码格式
     	//写入XML
    	Document document=DocumentHelper.createDocument();
    	Element rootE=document.addElement("validation");
    	//添加 field层的节点
   	    Element pname= rootE.addElement("field");
   	    if(validate.getPname()!=null)
   	    {
   	    //添加姓名  判断是否为空 
   	  if(validate.getPname().getName()!=null)
	      {
   		pname.addAttribute("name", validate.getPname().getName());
	      }
	  	if(validate.getPname().getComment()!=null)
	  	  {
	  		pname.addAttribute("comment", validate.getPname().getComment());
	  	  }
	  	if(validate.getPname().getTypes()!=null)
	  	  {
	  		for(String key:validate.getPname().getTypes().keySet())
  			{
  				Element e=pname.addElement("regx");
  				e.addAttribute("type", key);
  				e.addAttribute("value", validate.getPname().getType(key).getRegx());
  				e.addAttribute("comment", validate.getPname().getType(key).getComment());
  			}
	  	  }else
	  	   {
		  	  pname.addAttribute("regx", validate.getPname().getRegx());	
	  	   }
   	    }
	  //添加性别
	    Element gender= rootE.addElement("field");
	    if(validate.getGender()!=null)
	    {
	    if(validate.getGender().getName()!=null)
	      {
	       gender.addAttribute("name", validate.getGender().getName());
	      }
	  	if(validate.getGender().getComment()!=null)
	  	  {
	  		gender.addAttribute("comment", validate.getGender().getComment());
	  	  }
	  	if(validate.getGender().getTypes()!=null)
	  	  {
	  		for(String key:validate.getGender().getTypes().keySet())
  			{
  				Element e=gender.addElement("regx");
  				e.addAttribute("type", key);
  				e.addAttribute("value", validate.getGender().getType(key).getRegx());
  				e.addAttribute("comment", validate.getGender().getType(key).getComment());
  			}	
	  	  }else
	  	   {
		   gender.addAttribute("regx", validate.getGender().getRegx());
	  	 }  
	  	}	
	  	 //添加证件
	    Element credentials= rootE.addElement("field");
	    if(validate.getCredentials()!=null)
	    {
	    if(validate.getCredentials().getName()!=null)
	      {
		    credentials.addAttribute("name", validate.getCredentials().getName());
	      }
	  	//判断comment是否为空
	  	if(validate.getCredentials().getComment()!=null)
	  	  {
	  		credentials.addAttribute("comment", validate.getCredentials().getComment());
	  	  }
	  	if(validate.getCredentials().getTypes()!=null)
	  	  {
	  	//regx的值大于1的时候  会有下一个元素的  
	  	  for(String key:validate.getCredentials().getTypes().keySet())
	  		{
	  		//添加节点regx
	  			Element e=credentials.addElement("regx");
	  			e.addAttribute("type", key);
	  			e.addAttribute("value", validate.getCredentials().getType(key).getRegx());
	  			e.addAttribute("comment", validate.getCredentials().getType(key).getComment());
	  			}
	  	  }else
	  	   {//把值放入进去
		  	credentials.addAttribute("regx", validate.getCredentials().getRegx());
	  	   }
	    }
	  	 //添加电子邮箱
	    Element email= rootE.addElement("field");
	    if(validate.getEmail()!=null)
	    {
	    if(validate.getEmail().getName()!=null)
	      {
	    	 email.addAttribute("name", validate.getEmail().getName());
	      }
	  	if(validate.getEmail().getComment()!=null)
	  	  {
	  		email.addAttribute("comment", validate.getEmail().getComment());
	  	  }
	  	if(validate.getEmail().getTypes()!=null)
	  	  {
	  		for(String key:validate.getEmail().getTypes().keySet())
  			{
  				Element e=email.addElement("regx");
  				e.addAttribute("type", key);
  				e.addAttribute("value", validate.getEmail().getType(key).getRegx());
  				e.addAttribute("comment", validate.getEmail().getType(key).getComment());
  			}
	  	  }else
	  	   {
		  		email.addAttribute("regx", validate.getEmail().getRegx());	
	  	   }
	    }
	  //是addrprovince的赋值
	   
	    if(validate.getAddrprovince()!=null)
	    {
	     Element addrprovince= rootE.addElement("field");
	    if(validate.getAddrprovince().getName()!=null)
	      {
	    	addrprovince.addAttribute("name", validate.getAddrprovince().getName());
	      }
	  	if(validate.getAddrprovince().getComment()!=null)
	  	  {
	  		addrprovince.addAttribute("comment", validate.getAddrprovince().getComment());
	  	  }
	  	if(validate.getAddrprovince().getTypes()!=null)
	  	  {
	  		for(String key:validate.getAddrprovince().getTypes().keySet())
  			{
  				Element e=addrprovince.addElement("regx");
  				e.addAttribute("type", key);
  				e.addAttribute("value", validate.getAddrprovince().getType(key).getRegx());
  				e.addAttribute("comment", validate.getAddrprovince().getType(key).getComment());
  			}
	  	  }else
	  	   {
	  		addrprovince.addAttribute("regx", validate.getAddrprovince().getRegx());	
	  	   }
	    }
	 //是addrcounty的赋值 	
	  	
	  	 if(validate.getAddrcounty()!=null)
	  	 {
	  		 Element addrcounty= rootE.addElement("field");
	  	if(validate.getAddrcounty().getName()!=null)
	      {
	  		addrcounty.addAttribute("name", validate.getAddrcounty().getName());
	      }
		  	if(validate.getAddrcounty().getComment()!=null)
		  	  {
		  		addrcounty.addAttribute("comment", validate.getAddrcounty().getComment());
		  	  }
		  	if(validate.getAddrcounty().getTypes()!=null)
		  	  {
		  		for(String key:validate.getAddrcounty().getTypes().keySet())
	  			{
	  				Element e=addrcounty.addElement("regx");
	  				e.addAttribute("type", key);
	  				e.addAttribute("value", validate.getAddrcounty().getType(key).getRegx());
	  				e.addAttribute("comment", validate.getAddrcounty().getType(key).getComment());
	  			}
		  	  }else
		  	   {
		  		addrcounty.addAttribute("regx", validate.getAddrcounty().getRegx());	
		  	   }
	  	 }
	  //是addrcity的赋值		
		 
		  if(validate.getAddrcity()!=null)
		  {
			  Element addrcity= rootE.addElement("field");
		  if(validate.getAddrcity().getName()!=null)
	      {
			addrcity.addAttribute("name", validate.getAddrcity().getName());
	      }
			if(validate.getAddrcity().getComment()!=null)
			   {
				addrcity.addAttribute("comment", validate.getAddrcity().getComment());
			   }
			if(validate.getAddrcity().getTypes()!=null)
			   {
			  	for(String key:validate.getAddrcity().getTypes().keySet())
		  	   {
		  		Element e=addrcity.addElement("regx");
		  		e.addAttribute("type", key);
		  		e.addAttribute("value", validate.getAddrcity().getType(key).getRegx());
		  		e.addAttribute("comment", validate.getAddrcity().getType(key).getComment());
		  		}
			  }else
			   {
				addrcity.addAttribute("regx", validate.getAddrcity().getRegx());	
			   } 
		  }
    //是addrtown的赋值	  	
		  
		   if(validate.getAddrtown()!=null)
		   {
			   Element addrtown= rootE.addElement("field");
		   if(validate.getAddrtown().getName()!=null)
		      {
			   addrtown.addAttribute("name", validate.getAddrtown().getName());
		      }
		    if(validate.getAddrtown().getComment()!=null)
			  {
		    	addrtown.addAttribute("comment", validate.getAddrtown().getComment());
			  }
			if(validate.getAddrtown().getTypes()!=null)
			 {
			  for(String key:validate.getAddrtown().getTypes().keySet())
			 {
			  	Element e=addrtown.addElement("regx");
			  	e.addAttribute("type", key);
			  	e.addAttribute("value", validate.getAddrtown().getType(key).getRegx());
				e.addAttribute("comment", validate.getAddrtown().getType(key).getComment());
			 }
			}else
			{
			  addrtown.addAttribute("regx", validate.getAddrtown().getRegx());	
			} 
		   }
	//是addrvillage的赋值 	
		 
		  if(validate.getAddrvillage()!=null)
		  {
			  Element addrvillage= rootE.addElement("field");
		   if(validate.getAddrvillage().getName()!=null)
	          {
			   addrvillage.addAttribute("name", validate.getAddrvillage().getName());
	          }
			if(validate.getAddrvillage().getComment()!=null)
			  {
			   addrvillage.addAttribute("comment", validate.getAddrvillage().getComment());
			  }
			if(validate.getAddrvillage().getTypes()!=null)
			  {
			   for(String key:validate.getAddrvillage().getTypes().keySet())
			  {
				Element e=addrvillage.addElement("regx");
				e.addAttribute("type", key);
				e.addAttribute("value", validate.getAddrvillage().getType(key).getRegx());
				e.addAttribute("comment", validate.getAddrvillage().getType(key).getComment());
			  }
			 }else
			  {
			   addrvillage.addAttribute("regx", validate.getAddrvillage().getRegx());	
			  }  
		  }
	//是addrhouseid的赋值	  	
		  
		  if(validate.getAddrhouseid()!=null)
		  {
			  Element addrhouseid= rootE.addElement("field");
		  if(validate.getAddrhouseid().getName()!=null)
          {
		    addrhouseid.addAttribute("name", validate.getAddrhouseid().getName());
          }
			if(validate.getAddrhouseid().getComment()!=null)
			  {
				addrhouseid.addAttribute("comment", validate.getAddrhouseid().getComment());
			  }
			if(validate.getAddrhouseid().getTypes()!=null)
			 {
			   for(String key:validate.getAddrhouseid().getTypes().keySet())
			 {
				Element e=addrhouseid.addElement("regx");
				e.addAttribute("type", key);
				e.addAttribute("value", validate.getAddrhouseid().getType(key).getRegx());
				e.addAttribute("comment", validate.getAddrhouseid().getType(key).getComment());
			 }
			}else
			{
			  addrhouseid.addAttribute("regx", validate.getAddrhouseid().getRegx());	
			}
		  }
	//是birthday的赋值		
		
		if(validate.getBirthday()!=null)
		{
			Element birthday= rootE.addElement("field");
		if(validate.getBirthday().getName()!=null)
        {
			birthday.addAttribute("name", validate.getBirthday().getName());
        }
		  if(validate.getBirthday().getComment()!=null)
			 {
			  birthday.addAttribute("comment", validate.getBirthday().getComment());
			 }
		  if(validate.getBirthday().getTypes()!=null)
			 {
			  for(String key:validate.getBirthday().getTypes().keySet())
			 {
				Element e=birthday.addElement("regx");
				e.addAttribute("type", key);
				e.addAttribute("value", validate.getBirthday().getType(key).getRegx());
				e.addAttribute("comment", validate.getBirthday().getType(key).getComment());
			}
			}else
			{
				birthday.addAttribute("regx", validate.getBirthday().getRegx());	
			}  
		}
		//是nickname的赋值
		 
		  if(validate.getNickname()!=null)
		  {
			  Element nickname= rootE.addElement("field");
		  if(validate.getNickname().getName()!=null)
	        {
			  nickname.addAttribute("name", validate.getNickname().getName());
	        }
		   if(validate.getNickname().getComment()!=null)
			 {
			   nickname.addAttribute("comment", validate.getNickname().getComment());
			 }
			if(validate.getNickname().getTypes()!=null)
			 {
			  for(String key:validate.getNickname().getTypes().keySet())
			 {
				Element e=nickname.addElement("regx");
				e.addAttribute("type", key);
				e.addAttribute("value", validate.getNickname().getType(key).getRegx());
				e.addAttribute("comment", validate.getNickname().getType(key).getComment());
			 }
			 }else
			 {
				nickname.addAttribute("regx", validate.getNickname().getRegx());	
			 }
		  }
	 //是registeretype的赋值		
		 
		 if(validate.getRegisteretype()!=null)
		 {
			 Element registeretype= rootE.addElement("field");
		 if(validate.getRegisteretype().getName()!=null)
	        {
			 registeretype.addAttribute("name", validate.getRegisteretype().getName());
	        }
			if(validate.getRegisteretype().getComment()!=null)
			  {
				registeretype.addAttribute("comment", validate.getRegisteretype().getComment());
			  }
			if(validate.getRegisteretype().getTypes()!=null)
			  {
				for(String key:validate.getRegisteretype().getTypes().keySet())
			  {
				Element e=registeretype.addElement("regx");
				e.addAttribute("type", key);
				e.addAttribute("value", validate.getRegisteretype().getType(key).getRegx());
				e.addAttribute("comment", validate.getRegisteretype().getType(key).getComment());
			  }
			}else
			  {
				registeretype.addAttribute("regx", validate.getRegisteretype().getRegx());	
			   }
		 }
	//是nationality的赋值		
		
		if(validate.getNationality()!=null)
		{
			Element nationality= rootE.addElement("field");
		  if(validate.getNationality().getName()!=null)
           {
		     nationality.addAttribute("name", validate.getNationality().getName());
           }
			if(validate.getNationality().getComment()!=null)
			   {
				nationality.addAttribute("comment", validate.getNationality().getComment());
			   }
			if(validate.getNationality().getTypes()!=null)
			   {
				for(String key:validate.getNationality().getTypes().keySet())
				 {
				   Element e=nationality.addElement("regx");
				   e.addAttribute("type", key);
				   e.addAttribute("value", validate.getNationality().getType(key).getRegx());
				   e.addAttribute("comment", validate.getNationality().getType(key).getComment());
				 }
			   }else
				 {
				   nationality.addAttribute("regx", validate.getNationality().getRegx());	
				 } 
		}
	 //是 marriedstatus的赋值		
			
			if(validate.getMarriedstatus()!=null)
			{
				Element marriedstatus= rootE.addElement("field");
			 if(validate.getMarriedstatus().getName()!=null)
	           {
				marriedstatus.addAttribute("name", validate.getMarriedstatus().getName());
	           }
			if(validate.getMarriedstatus().getComment()!=null)
			  {
				marriedstatus.addAttribute("comment", validate.getMarriedstatus().getComment());
			  }
			if(validate.getMarriedstatus().getTypes()!=null)
			 {
				for(String key:validate.getMarriedstatus().getTypes().keySet())
				  {
					 Element e=marriedstatus.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getMarriedstatus().getType(key).getRegx());
					 e.addAttribute("comment", validate.getMarriedstatus().getType(key).getComment());
				  }
				  }else
				  {
					 marriedstatus.addAttribute("regx", validate.getMarriedstatus().getRegx());	
				  } 
			}
  //是 nation的赋值		
			
			if(validate.getNation()!=null)
			{
				Element nation= rootE.addElement("field");
		   if(validate.getNation().getName()!=null)
	         {
			   nation.addAttribute("name", validate.getNation().getName());
	         }
			if(validate.getNation().getComment()!=null)
			  {
				nation.addAttribute("comment", validate.getNation().getComment());
			  }
			if(validate.getNation().getTypes()!=null)
			 {
				for(String key:validate.getNation().getTypes().keySet())
				  {
					 Element e=nation.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getNation().getType(key).getRegx());
					 e.addAttribute("comment", validate.getNation().getType(key).getComment());
				  }
				  }else
				  {
					  nation.addAttribute("regx", validate.getNation().getRegx());	
				  } 
			}
	 //是 age的赋值	
			
			if(validate.getAge()!=null)
			{
				Element age= rootE.addElement("field");
			if(validate.getAge().getName()!=null)
	         {
			   age.addAttribute("name", validate.getAge().getName());
	         }
			if(validate.getAge().getComment()!=null)
			  {
				age.addAttribute("comment", validate.getAge().getComment());
			  }
			if(validate.getAge().getTypes()!=null)
			 {
				for(String key:validate.getAge().getTypes().keySet())
				  {
					 Element e=age.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getAge().getType(key).getRegx());
					 e.addAttribute("comment", validate.getAge().getType(key).getComment());
				  }
				  }else
				  {
					  age.addAttribute("regx", validate.getAge().getRegx());	
				  }
			}
   //是 booldtype的赋值		
			
			if(validate.getBooldtype()!=null)
			{
				Element booldtype= rootE.addElement("field");
			if(validate.getBooldtype().getName()!=null)
	         {
				booldtype.addAttribute("name", validate.getBooldtype().getName());
	         }
			if(validate.getBooldtype().getComment()!=null)
			  {
				booldtype.addAttribute("comment", validate.getBooldtype().getComment());
			  }
			if(validate.getBooldtype().getTypes()!=null)
			 {
				for(String key:validate.getBooldtype().getTypes().keySet())
				  {
					 Element e=booldtype.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getBooldtype().getType(key).getRegx());
					 e.addAttribute("comment", validate.getBooldtype().getType(key).getComment());
				  }
				  }else
				  {
					  booldtype.addAttribute("regx", validate.getBooldtype().getRegx());	
				  }  
			}
   //是 rh的赋值		
			
			if(validate.getRh()!=null)
			{
				Element rh= rootE.addElement("field");
			if(validate.getRh().getName()!=null)
	         {
				rh.addAttribute("name", validate.getRh().getName());
	         }
			if(validate.getRh().getComment()!=null)
			  {
				rh.addAttribute("comment", validate.getRh().getComment());
			  }
			if(validate.getRh().getTypes()!=null)
			 {
				for(String key:validate.getRh().getTypes().keySet())
				  {
					 Element e=rh.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getRh().getType(key).getRegx());
					 e.addAttribute("comment", validate.getRh().getType(key).getComment());
				  }
				  }else
				  {
					  rh.addAttribute("regx", validate.getRh().getRegx());	
				  }  	
			}
//是workdate的赋值		
			
			if(validate.getWorkdate()!=null)
			{
				Element workdate= rootE.addElement("field");
			if(validate.getWorkdate().getName()!=null)
	         {
				workdate.addAttribute("name", validate.getWorkdate().getName());
	         }
			if(validate.getWorkdate().getComment()!=null)
			  {
				workdate.addAttribute("comment", validate.getWorkdate().getComment());
			  }
			if(validate.getWorkdate().getTypes()!=null)
			 {
				for(String key:validate.getWorkdate().getTypes().keySet())
				  {
					 Element e=workdate.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getWorkdate().getType(key).getRegx());
					 e.addAttribute("comment", validate.getWorkdate().getType(key).getComment());
				  }
				  }else
				  {
					  workdate.addAttribute("regx", validate.getWorkdate().getRegx());	
				  }
			}
 //是jobtypecode的赋值		
			
			if(validate.getJobtypecode()!=null)
			{
				Element jobtypecode= rootE.addElement("field");
			if(validate.getJobtypecode().getName()!=null)
	         {
				jobtypecode.addAttribute("name", validate.getJobtypecode().getName());
	         }
			if(validate.getJobtypecode().getComment()!=null)
			  {
				jobtypecode.addAttribute("comment", validate.getJobtypecode().getComment());
			  }
			if(validate.getJobtypecode().getTypes()!=null)
			 {
				for(String key:validate.getJobtypecode().getTypes().keySet())
				  {
					 Element e=jobtypecode.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getJobtypecode().getType(key).getRegx());
					 e.addAttribute("comment", validate.getJobtypecode().getType(key).getComment());
				  }
				  }else
				  {
					  jobtypecode.addAttribute("regx", validate.getJobtypecode().getRegx());	
				  }
			}
		//是educationcode的赋值	
			
			if(validate.getEducationcode()!=null)
			{
				Element educationcode= rootE.addElement("field");
			if(validate.getEducationcode().getName()!=null)
	         {
				educationcode.addAttribute("name", validate.getEducationcode().getName());
	         }
			if(validate.getEducationcode().getComment()!=null)
			  {
				educationcode.addAttribute("comment", validate.getEducationcode().getComment());
			  }
			if(validate.getEducationcode().getTypes()!=null)
			 {
				for(String key:validate.getEducationcode().getTypes().keySet())
				  {
					 Element e=educationcode.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getEducationcode().getType(key).getRegx());
					 e.addAttribute("comment", validate.getEducationcode().getType(key).getComment());
				  }
				  }else
				  {
					  educationcode.addAttribute("regx", validate.getEducationcode().getRegx());	
				  }  	
			}
		 //是degreecode的赋值			
			
			if(validate.getDegreecode()!=null)
			{
				Element degreecode= rootE.addElement("field");
			if(validate.getDegreecode().getName()!=null)
	         {
				degreecode.addAttribute("name", validate.getDegreecode().getName());
	         }
			if(validate.getDegreecode().getComment()!=null)
			  {
				degreecode.addAttribute("comment", validate.getDegreecode().getComment());
			  }
			if(validate.getDegreecode().getTypes()!=null)
			 {
				for(String key:validate.getDegreecode().getTypes().keySet())
				  {
					 Element e=degreecode.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getDegreecode().getType(key).getRegx());
					 e.addAttribute("comment", validate.getDegreecode().getType(key).getComment());
				  }
				  }else
				  {
					  degreecode.addAttribute("regx", validate.getDegreecode().getRegx());	
				  }  
			}
			  //是orgcodes的赋值	
			
			if(validate.getOrgcodes()!=null)
			{
				Element orgcodes= rootE.addElement("field");
			if(validate.getOrgcodes().getName()!=null)
	         {
				orgcodes.addAttribute("name", validate.getOrgcodes().getName());
	         }
			if(validate.getOrgcodes().getComment()!=null)
			  {
				orgcodes.addAttribute("comment", validate.getOrgcodes().getComment());
			  }
			if(validate.getOrgcodes().getTypes()!=null)
			 {
				for(String key:validate.getOrgcodes().getTypes().keySet())
				  {
					 Element e=orgcodes.addElement("regx");
					 e.addAttribute("type", key);
					 e.addAttribute("value", validate.getOrgcodes().getType(key).getRegx());
					 e.addAttribute("comment", validate.getOrgcodes().getType(key).getComment());
				  }
				  }else
				  {
					  orgcodes.addAttribute("regx", validate.getOrgcodes().getRegx());	
				  }  		
			}
			 //添加电话
		    Element phones= rootE.addElement("field");
		    if(validate.getPhones()!=null)
		    {
		    if(validate.getPhones().getName()!=null)
	         {
		    	 phones.addAttribute("name", validate.getPhones().getName());
	         }
		  	//判断comment是否为空
		  	if(validate.getPhones().getComment()!=null)
		  	  {
		  		phones.addAttribute("comment", validate.getPhones().getComment());
		  	  }
		  	if(validate.getPhones().getTypes()!=null)
		  	  {
		  	//regx的值大于1的时候  会有下一个元素的  
		  	  for(String key:validate.getPhones().getTypes().keySet())
		  		{
		  		//添加节点regx
		  			Element e=phones.addElement("regx");
		  			e.addAttribute("type", key);
		  			e.addAttribute("value", validate.getPhones().getType(key).getRegx());
		  			e.addAttribute("comment", validate.getPhones().getType(key).getComment());
		  			}
		  	  }else
		  	   {//把值放入进去
		  		phones.addAttribute("regx", validate.getPhones().getRegx());
		  	   }
		    }
		  	
	  		 OutputFormat outFmt = new OutputFormat("\t", true);  
			 outFmt.setEncoding("UTF-8"); 
			 writer = new XMLWriter(new FileOutputStream(validatefile) ,outFmt);
             writer.write(document);
             writer.close();
	  	   }
	  	public ValidateconfigchangeOut validateconfigchange(ValidateconfigchangeIn in) throws DocumentException{
	  		ValidateconfigchangeOut out=new ValidateconfigchangeOut();
			ConverUtils.handleCallBack(out,in.getCallback());
			in.getValidation();
			try {
				  this.loader(in.getValidation(),SystemConfig.getConfig());
				  dataValidaterImpl.refresh();
			    } catch (IOException e) {
				  e.printStackTrace();
			    }
			    out.setTitle("修改数据校验配置");
			    out.setMsg("修改数据校验配置成功");
			    out.setRet("0");  
			    return out;
		}	
	  	
	}

