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
import com.livechain.pid.model.Weight;
import com.livechain.pid.rest.model.WeightconfigchangeIn;
import com.livechain.pid.rest.model.WeightconfigchangeOut;
import com.livechain.pid.util.SystemConfig;
import com.livechain.pid.weight.WeightLoaderImpl;
import com.livechain.pid.weight.WeightManger;
import com.livechain.pid.weight.WeightMangerImpl;

//修改权重比配置
@Component 
public class WeightconfigchangeServiceImpl {
	private WeightManger weightManger;
	
	  public WeightManger getWeightManger() {
			return weightManger;
		}
	 @Resource
		public void setWeightManger(WeightManger weightManger) {
			this.weightManger = weightManger;
		}
	private final static Log log4j=LogFactory.getLog(WeightMangerImpl.class);
    @SuppressWarnings("unchecked")
	public void loader(Weight weight,Object properties) throws IOException, DocumentException {
		//获取路径XML
		Map<String,Object> proes=(Map<String,Object>)properties;
    	URL url = WeightLoaderImpl.class.getClassLoader().getResource(proes.get("weightpath").toString());
    	log4j.info("loading xml file:"+url.getFile());
    	File weightfile=new File(url.getFile());
    	XMLWriter writer = null;// 声明写XML的对象
         OutputFormat format = OutputFormat.createPrettyPrint();
         format.setEncoding("UTF-8");// 设置XML文件的编码格式
    	//写入XML
    	Document document=DocumentHelper.createDocument();
    	Element rootE=document.addElement("weights");
    	Float min=weight.getMin();
    	Float max=weight.getMax();
    	//判断上下限是否为空
    	if(null == min || min.equals(0) || null==max || max.equals(0))
  	    {
  	    	min=new Float(30);
  	    	max=new Float(70);
  		}  
    	//添加MAX和MIN
    	rootE.addAttribute("min",min.toString());    	
    	rootE.addAttribute("max",max.toString());
    	//添加pname
	  	//添加 field层的节点
   	    Element pname= rootE.addElement("field");
   	    //判断name是否为空  不为空添加进去
   	    if(weight.getPname()!=null)
   	    {
   	    if(weight.getPname().getName()!=null)
   	      {
	  	    pname.addAttribute("name", weight.getPname().getName());
   	      }
   	    //判断comment是否为空
	  	if(weight.getPname().getComment()!=null)
	  	  {
	  		pname.addAttribute("comment", weight.getPname().getComment());
	  	  }
	  	//判断weight是否为空  weight>0的时候是 有weight值的
	  	 if(weight.getPname().getWeight("weight")>0)
	  	  {
	  		//把值放入进去
	  	  pname.addAttribute("weight", weight.getPname().getWeight("weight")+"");
	  	  }else
	  	   {
	  	 //weight的值小于0的时候  会有下一个元素的  判断type的大小
	  		if(weight.getPname().getTypes().size()>1)
	  			for(String key:weight.getPname().getTypes().keySet())
	  			{
	  				//添加节点weight
	  				Element w=pname.addElement("weight");
	  				w.addAttribute("type", key);
			  		w.addAttribute("value", weight.getPname().getType(key).getWeight()+"");
			  		w.addAttribute("unique", weight.getPname().getType(key).isUnique()+"");
			  		w.addAttribute("comment", weight.getPname().getType(key).getComment());
	  			}
	  	   }
   	    }
	  	 //性别的相关添加
	  	 Element gender= rootE.addElement("field");
	  	 if(weight.getGender()!=null)
	  	 {
	  	   if(weight.getGender().getName()!=null)
	  	      {
	  	        gender.addAttribute("name",weight.getGender().getName());
	  	      }
		  	if(weight.getGender().getComment()!=null)
		  	  {
		  		gender.addAttribute("comment",weight.getGender().getComment());
		  	  }
		  	 if(weight.getGender().getWeight("weight")>0)
		  	  {
		  	  gender.addAttribute("weight", weight.getGender().getWeight("weight")+"");
		  	  }else
		  	   {
		  		if(weight.getGender().getTypes().size()>1)
		  			for(String key:weight.getGender().getTypes().keySet())
		  			{
		  				Element w=gender.addElement("weight");
		  				w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getGender().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getGender().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getGender().getType(key).getComment());
		  			}
		  	   }
	  	 }
		 //出生日期相关添加
	  	 Element birthday= rootE.addElement("field");
	  	 if(weight.getBirthday()!=null)
	  	 {
	  	   if(weight.getBirthday().getName()!=null)
	  	     {
	  	       birthday.addAttribute("name",weight.getBirthday().getName());
	  	     }
		  	if(weight.getBirthday().getComment()!=null)
		  	  {
		  		birthday.addAttribute("comment",weight.getBirthday().getComment());
		  	  }
		  	 if(weight.getBirthday().getWeight("weight")>0)
		  	  {
		  		birthday.addAttribute("weight", weight.getBirthday().getWeight("weight")+"");
		  	  }else
		  	   {
		  		if(weight.getBirthday().getTypes().size()>1)
		  			for(String key:weight.getBirthday().getTypes().keySet())
		  			{
		  				Element w=birthday.addElement("weight");
		  				w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getBirthday().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getBirthday().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getBirthday().getType(key).getComment());
		  			}
		  	   }
	  	    }
		 
			 
		 //电子邮件相关添加
		  	 Element email= rootE.addElement("field");
		  	 if(weight.getEmail()!=null)
		  	 {
		  	 if(weight.getEmail().getName()!=null)
		  	   {
		  	    email.addAttribute("name",weight.getEmail().getName());
		  	   }
		  	 if(weight.getEmail().getComment()!=null)
			  	  {
			  		email.addAttribute("comment",weight.getEmail().getComment());
			  	  }
			  	 if(weight.getEmail().getWeight("weight")>0)
			  	  {
			  		email.addAttribute("weight", weight.getEmail().getWeight("weight")+"");
			  	  }else
			  	   {
			  		if(weight.getEmail().getTypes().size()>1)
			  			for(String key:weight.getEmail().getTypes().keySet())
			  			{
			  				Element w=email.addElement("weight");
			  				w.addAttribute("type", key);
					  		w.addAttribute("value", weight.getEmail().getType(key).getWeight()+"");
					  		w.addAttribute("unique", weight.getEmail().getType(key).isUnique()+"");
					  		w.addAttribute("comment", weight.getEmail().getType(key).getComment());
			  			}
			  	   }
		  	   }
		//昵称相关添加
			 Element nickname= rootE.addElement("field");
			 if(weight.getNickname()!=null)
			 {
			 if(weight.getNickname().getName()!=null)
		  	   {
				nickname.addAttribute("name",weight.getNickname().getName());
		  	   }
		      if(weight.getNickname().getComment()!=null)
				 {
		    	  nickname.addAttribute("comment",weight.getNickname().getComment());
				 }
			 if(weight.getNickname().getWeight("weight")>0)
				 {
				 nickname.addAttribute("weight", weight.getNickname().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getNickname().getTypes().size()>1)
				   for(String key:weight.getNickname().getTypes().keySet())
				  	{
				  		Element w=nickname.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getNickname().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getNickname().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getNickname().getType(key).getComment());
				  	}
				  } 
			 }
		//户籍类型代码
			 Element registeretype= rootE.addElement("field");
			 if(weight.getRegisteretype()!=null)
			 {
			 if(weight.getRegisteretype().getName()!=null)
		  	   {
				 registeretype.addAttribute("name",weight.getRegisteretype().getName());
		  	   }
		      if(weight.getRegisteretype().getComment()!=null)
				 {
		    	  registeretype.addAttribute("comment",weight.getRegisteretype().getComment());
				 }
			 if(weight.getRegisteretype().getWeight("weight")>0)
				 {
				 registeretype.addAttribute("weight", weight.getRegisteretype().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getRegisteretype().getTypes().size()>1)
				   for(String key:weight.getRegisteretype().getTypes().keySet())
				  	{
				  		Element w=registeretype.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getRegisteretype().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getRegisteretype().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getRegisteretype().getType(key).getComment());
				  	}
				  }
			 }
		//地址-省
			 Element addrprovince= rootE.addElement("field");
			 if(weight.getAddrprovince()!=null)
			 {
			 if(weight.getAddrprovince().getName()!=null)
		  	   {
				 addrprovince.addAttribute("name",weight.getAddrprovince().getName());
		  	   }
		      if(weight.getAddrprovince().getComment()!=null)
				 {
		    	  addrprovince.addAttribute("comment",weight.getAddrprovince().getComment());
				 }
			 if(weight.getAddrprovince().getWeight("weight")>0)
				 {
				 addrprovince.addAttribute("weight", weight.getAddrprovince().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getAddrprovince().getTypes().size()>1)
				   for(String key:weight.getAddrprovince().getTypes().keySet())
				  	{
				  		Element w=addrprovince.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getAddrprovince().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getAddrprovince().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getAddrprovince().getType(key).getComment());
				  	}
				  }
			 }
			//地址-市
			 Element addrcity= rootE.addElement("field");
			 if(weight.getAddrcity()!=null)
			 {
			 if(weight.getAddrcity().getName()!=null)
		  	   {
				 addrcity.addAttribute("name",weight.getAddrcity().getName());
		  	   }
		      if(weight.getAddrcity().getComment()!=null)
				 {
		    	  addrcity.addAttribute("comment",weight.getAddrcity().getComment());
				 }
			 if(weight.getAddrcity().getWeight("weight")>0)
				 {
				 addrcity.addAttribute("weight", weight.getAddrcity().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getAddrcity().getTypes().size()>1)
				   for(String key:weight.getAddrcity().getTypes().keySet())
				  	{
				  		Element w=addrcity.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getAddrcity().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getAddrcity().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getAddrcity().getType(key).getComment());
				  	}
				  }  
			 }
		//地址-县
			 Element addrcounty= rootE.addElement("field");
			 if(weight.getAddrcounty()!=null)
			 {
			 if(weight.getAddrcounty().getName()!=null)
		  	   {
				 addrcounty.addAttribute("name",weight.getAddrcounty().getName());
		  	   }
		      if(weight.getAddrcounty().getComment()!=null)
				 {
		    	  addrcounty.addAttribute("comment",weight.getAddrcounty().getComment());
				 }
			 if(weight.getAddrcounty().getWeight("weight")>0)
				 {
				 addrcounty.addAttribute("weight", weight.getAddrcounty().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getAddrcounty().getTypes().size()>1)
				   for(String key:weight.getAddrcounty().getTypes().keySet())
				  	{
				  		Element w=addrcounty.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getAddrcounty().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getAddrcounty().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getAddrcounty().getType(key).getComment());
				  	}
				  } 
			 }
		//地址-乡
			 Element addrtown= rootE.addElement("field");
			 if(weight.getAddrtown()!=null)
			 {
			 if(weight.getAddrtown().getName()!=null)
		  	   {
				 addrtown.addAttribute("name",weight.getAddrtown().getName());
		  	   }
		      if(weight.getAddrtown().getComment()!=null)
				 {
		    	  addrtown.addAttribute("comment",weight.getAddrtown().getComment());
				 }
			 if(weight.getAddrtown().getWeight("weight")>0)
				 {
				 addrtown.addAttribute("weight", weight.getAddrtown().getWeight("weight")+"");
				 }else{
				  if(weight.getAddrtown().getTypes().size()>1)
				   for(String key:weight.getAddrtown().getTypes().keySet())
				  	{
				  		Element w=addrtown.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getAddrtown().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getAddrtown().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getAddrtown().getType(key).getComment());
				  	}
				  }  
			 }
		//地址-村
			 Element addrvillage= rootE.addElement("field");
			 if(weight.getAddrvillage()!=null)
			 {
			 if(weight.getAddrvillage().getName()!=null)
		  	   {
				 addrvillage.addAttribute("name",weight.getAddrvillage().getName());
		  	   }
			 //判断comment是否为空
		      if(weight.getAddrvillage().getComment()!=null)
				 {
		    	  addrvillage.addAttribute("comment",weight.getAddrvillage().getComment());
				 }
			 if(weight.getAddrvillage().getWeight("weight")>0)
				 {
				 addrvillage.addAttribute("weight", weight.getAddrvillage().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getAddrvillage().getTypes().size()>1)
				   for(String key:weight.getAddrvillage().getTypes().keySet())
				  	{
				  		Element w=addrvillage.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getAddrvillage().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getAddrvillage().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getAddrvillage().getType(key).getComment());
				  	}
				  }   
			 }
		//地址-门牌号码
			 Element addrhouseid= rootE.addElement("field");
			 if(weight.getAddrhouseid()!=null)
			 {
			 if(weight.getAddrhouseid().getName()!=null)
		  	   {
				 addrhouseid.addAttribute("name",weight.getAddrhouseid().getName());
		  	   }
		      if(weight.getAddrhouseid().getComment()!=null)
				 {
		    	  addrhouseid.addAttribute("comment",weight.getAddrhouseid().getComment());
				 }
			 if(weight.getAddrhouseid().getWeight("weight")>0)
				 {
				 addrhouseid.addAttribute("weight", weight.getAddrhouseid().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getAddrhouseid().getTypes().size()>1)
				   for(String key:weight.getAddrhouseid().getTypes().keySet())
				  	{
				  		Element w=addrhouseid.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getAddrhouseid().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getAddrhouseid().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getAddrhouseid().getType(key).getComment());
				  	}
				  } 
			 }
		
		//国籍
			 Element nationality= rootE.addElement("field");
			 if(weight.getNationality()!=null)
			 {
			 if(weight.getNationality().getName()!=null)
		  	   {
				 nationality.addAttribute("name",weight.getNationality().getName());
		  	   }
		      if(weight.getNationality().getComment()!=null)
				 {
		    	  nationality.addAttribute("comment",weight.getNationality().getComment());
				 }
			 if(weight.getNationality().getWeight("weight")>0)
				 {
				 nationality.addAttribute("weight", weight.getNationality().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getNationality().getTypes().size()>1)
				   for(String key:weight.getNationality().getTypes().keySet())
				  	{
				  		Element w=nationality.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getNationality().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getNationality().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getNationality().getType(key).getComment());
				  	}
				  } 
			 }
		//婚姻状况代码
			 Element marriedstatus= rootE.addElement("field");
			 if(weight.getMarriedstatus()!=null)
			 {
			 if(weight.getMarriedstatus().getName()!=null)
		  	   {
				 marriedstatus.addAttribute("name",weight.getMarriedstatus().getName());
		  	   }
		      if(weight.getMarriedstatus().getComment()!=null)
				 {
		    	  marriedstatus.addAttribute("comment",weight.getMarriedstatus().getComment());
				 }
			 if(weight.getMarriedstatus().getWeight("weight")>0)
				 {
				 marriedstatus.addAttribute("weight", weight.getMarriedstatus().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getMarriedstatus().getTypes().size()>1)
				   for(String key:weight.getMarriedstatus().getTypes().keySet())
				  	{
				  		Element w=marriedstatus.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getMarriedstatus().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getMarriedstatus().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getMarriedstatus().getType(key).getComment());
				  	}
				  } 
			 }
		//民族
			 Element nation= rootE.addElement("field");
			 if(weight.getNation()!=null)
			 {
			 if(weight.getNation().getName()!=null)
		  	   {
				 nation.addAttribute("name",weight.getNation().getName());
		  	   }
		      if(weight.getNation().getComment()!=null)
				 {
		    	  nation.addAttribute("comment",weight.getNation().getComment());
				 }
			 if(weight.getNation().getWeight("weight")>0)
				 {
				 nation.addAttribute("weight", weight.getNation().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getNation().getTypes().size()>1)
				   for(String key:weight.getNation().getTypes().keySet())
				  	{
				  		Element w=nation.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getNation().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getNation().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getNation().getType(key).getComment());
				  	}
				  }  
			 }
		//年龄
			 Element age= rootE.addElement("field");
			 if(weight.getAge()!=null)
			 {
			 if(weight.getAge().getName()!=null)
		  	   {
				 age.addAttribute("name",weight.getAge().getName());
		  	   }
		      if(weight.getAge().getComment()!=null)
				 {
		    	  age.addAttribute("comment",weight.getAge().getComment());
				 }
			 if(weight.getAge().getWeight("weight")>0)
				 {
				 age.addAttribute("weight", weight.getAge().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getAge().getTypes().size()>1)
				   for(String key:weight.getAge().getTypes().keySet())
				  	{
				  		Element w=age.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getAge().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getAge().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getAge().getType(key).getComment());
				  	}
				  } 
			 }
		//ABO血型
			 Element booldtype= rootE.addElement("field");
			 if(weight.getBooldtype()!=null)
			 {
			 if(weight.getBooldtype().getName()!=null)
		  	   {
				 booldtype.addAttribute("name",weight.getBooldtype().getName());
		  	   }
		      if(weight.getBooldtype().getComment()!=null)
				 {
		    	  booldtype.addAttribute("comment",weight.getBooldtype().getComment());
				 }
			 if(weight.getBooldtype().getWeight("weight")>0)
				 {
				 booldtype.addAttribute("weight", weight.getBooldtype().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getBooldtype().getTypes().size()>1)
				   for(String key:weight.getBooldtype().getTypes().keySet())
				  	{
				  		Element w=booldtype.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getBooldtype().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getBooldtype().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getBooldtype().getType(key).getComment());
				  	}
				  }  
			 }
		//RH血型
			 Element rh= rootE.addElement("field");
			 if(weight.getRh()!=null)
			 {
			 if(weight.getRh().getName()!=null)
		  	   {
				 rh.addAttribute("name",weight.getRh().getName());
		  	   }
		      if(weight.getRh().getComment()!=null)
				 {
		    	  rh.addAttribute("comment",weight.getRh().getComment());
				 }
			 if(weight.getRh().getWeight("weight")>0)
				 {
				 rh.addAttribute("weight", weight.getRh().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getRh().getTypes().size()>1)
				   for(String key:weight.getRh().getTypes().keySet())
				  	{
				  		Element w=rh.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getRh().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getRh().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getRh().getType(key).getComment());
				  	}
				  } 
			 }
		//参加工作日期
			 Element workdate= rootE.addElement("field");
			 if(weight.getWorkdate()!=null)
			 {
			 if(weight.getWorkdate().getName()!=null)
		  	   {
				 workdate.addAttribute("name",weight.getWorkdate().getName());
		  	   }
		      if(weight.getWorkdate().getComment()!=null)
				 {
		    	  workdate.addAttribute("comment",weight.getWorkdate().getComment());
				 }
			 if(weight.getWorkdate().getWeight("weight")>0)
				 {
				 workdate.addAttribute("weight", weight.getRh().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getWorkdate().getTypes().size()>1)
				   for(String key:weight.getWorkdate().getTypes().keySet())
				  	{
				  		Element w=rh.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getWorkdate().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getWorkdate().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getWorkdate().getType(key).getComment());
				  	}
				  }
			 }
			//职业类别代码
			 Element jobtypecode= rootE.addElement("field");
			 if(weight.getJobtypecode()!=null)
			 {
			 if(weight.getJobtypecode().getName()!=null)
		  	   {
				 jobtypecode.addAttribute("name",weight.getJobtypecode().getName());
		  	   }
		      if(weight.getJobtypecode().getComment()!=null)
				 {
		    	  jobtypecode.addAttribute("comment",weight.getJobtypecode().getComment());
				 }
			 if(weight.getJobtypecode().getWeight("weight")>0)
				 {
				 jobtypecode.addAttribute("weight", weight.getJobtypecode().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getJobtypecode().getTypes().size()>1)
				   for(String key:weight.getJobtypecode().getTypes().keySet())
				  	{
				  		Element w=jobtypecode.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getJobtypecode().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getJobtypecode().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getJobtypecode().getType(key).getComment());
				  	}
				  }
			 }
		//学历代码
			 Element educationcode= rootE.addElement("field");
			 if(weight.getEducationcode()!=null)
			 {
			 if(weight.getEducationcode().getName()!=null)
		  	   {
				 educationcode.addAttribute("name",weight.getEducationcode().getName());
		  	   }
		      if(weight.getEducationcode().getComment()!=null)
				 {
		    	  educationcode.addAttribute("comment",weight.getEducationcode().getComment());
				 }
			 if(weight.getEducationcode().getWeight("weight")>0)
				 {
				 educationcode.addAttribute("weight", weight.getEducationcode().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getEducationcode().getTypes().size()>1)
				   for(String key:weight.getEducationcode().getTypes().keySet())
				  	{
				  		Element w=educationcode.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getEducationcode().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getEducationcode().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getEducationcode().getType(key).getComment());
				  	}
				  }
			 }
		//学位代码
			 Element degreecode= rootE.addElement("field");
			 if(weight.getDegreecode()!=null)
			 {
			 if(weight.getDegreecode().getName()!=null)
		  	   {
				 degreecode.addAttribute("name",weight.getDegreecode().getName());
		  	   }
		      if(weight.getDegreecode().getComment()!=null)
				 {
		    	  degreecode.addAttribute("comment",weight.getDegreecode().getComment());
				 }
			 if(weight.getDegreecode().getWeight("weight")>0)
				 {
				 degreecode.addAttribute("weight", weight.getDegreecode().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getDegreecode().getTypes().size()>1)
				   for(String key:weight.getDegreecode().getTypes().keySet())
				  	{
				  		Element w=degreecode.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getDegreecode().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getDegreecode().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getDegreecode().getType(key).getComment());
				  	}
				  }
			 }
	//机构代码
			 Element orgcodes= rootE.addElement("field");
			 if(weight.getOrgcodes()!=null)
			 {
			 if(weight.getOrgcodes().getName()!=null)
		  	   {
				 orgcodes.addAttribute("name",weight.getOrgcodes().getName());
		  	   }
		      if(weight.getOrgcodes().getComment()!=null)
				 {
		    	  orgcodes.addAttribute("comment",weight.getOrgcodes().getComment());
				 }
			 if(weight.getOrgcodes().getWeight("weight")>0)
				 {
				 orgcodes.addAttribute("weight", weight.getOrgcodes().getWeight("weight")+"");
				 }else
				 {
				  if(weight.getOrgcodes().getTypes().size()>1)
				   for(String key:weight.getOrgcodes().getTypes().keySet())
				  	{
				  		Element w=orgcodes.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getOrgcodes().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getOrgcodes().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getOrgcodes().getType(key).getComment());
				  	}
				  }
			 }
			 
			//电话
			 Element phones= rootE.addElement("field");
			 if(weight.getPhones()!=null)
			 {
			 if(weight.getPhones().getName()!=null)
		  	   {
				 phones.addAttribute("name",weight.getPhones().getName());
		  	   }
		      if(weight.getPhones().getComment()!=null)
				 {
		    	  phones.addAttribute("comment",weight.getPhones().getComment());
				 }
			 if(weight.getPhones().getWeight("weight")>0)
				 {
				   phones.addAttribute("weight", weight.getPhones().getWeight("weight")+"");
				 }else
				 {				  
				   for(String key:weight.getPhones().getTypes().keySet())
				  	{
				  		Element w=phones.addElement("weight");
				  		w.addAttribute("type", key);
				  		w.addAttribute("value", weight.getPhones().getType(key).getWeight()+"");
				  		w.addAttribute("unique", weight.getPhones().getType(key).isUnique()+"");
				  		w.addAttribute("comment", weight.getPhones().getType(key).getComment());
				  	}
				  }  
			 }
			 
			 
			//证件相关添加
			  	Element credentials= rootE.addElement("field");
			  	if(weight.getCredentials()!=null)
			  	{
			  	if(weight.getCredentials().getName()!=null)
			  	  {
			  	    credentials.addAttribute("name",weight.getCredentials().getName());
			  	   }
			    if(weight.getCredentials().getComment()!=null)
				  {
			    	credentials.addAttribute("comment",weight.getCredentials().getComment());
				  }
				 if(weight.getCredentials().getWeight("weight")>0)
					{ 
					 credentials.addAttribute("weight", weight.getCredentials().getWeight("weight")+"");
					}else
					{
						//循环type
					 if(!weight.getCredentials().getTypes().containsKey("weight"))
					  for(String key:weight.getCredentials().getTypes().keySet())
					  {
					  	Element w=credentials.addElement("weight");
					  	w.addAttribute("type", key);
					  	w.addAttribute("value", String.valueOf(weight.getCredentials().getType(key).getWeight()));
					  	w.addAttribute("comment", weight.getCredentials().getType(key).getComment());
					  	w.addAttribute("unique", String.valueOf(weight.getCredentials().getType(key).isUnique()));
					  }					
					 }   
			  	}
			 
			 OutputFormat outFmt = new OutputFormat("\t", true);  
			 outFmt.setEncoding("UTF-8"); 
			 writer = new XMLWriter(new FileOutputStream(weightfile) ,outFmt);
             writer.write(document);
             writer.close();
   		 }
	public WeightconfigchangeOut weightconfigchange(WeightconfigchangeIn in) throws DocumentException{
		WeightconfigchangeOut out=new WeightconfigchangeOut();
		ConverUtils.handleCallBack(out,in.getCallback());
		in.getWeight();
		try {
			  this.loader(in.getWeight(),SystemConfig.getConfig());
			  weightManger.refresh();
		    } catch (IOException e) {
			  e.printStackTrace();
		    }
		    out.setTitle("修改权重比配置");
		    out.setMsg("修改权重比配置成功");
		    out.setRet("0");  
		    
		    return out;
	}
}
