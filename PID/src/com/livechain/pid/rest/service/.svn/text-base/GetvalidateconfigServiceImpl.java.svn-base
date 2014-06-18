package com.livechain.pid.rest.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.spring.converter.ConverUtils;
import org.springframework.stereotype.Component;
import wl.zs.utils.StringUtils;
import com.livechain.pid.model.Field;
import com.livechain.pid.model.Validation;
import com.livechain.pid.rest.model.GetvalidateconfigIn;
import com.livechain.pid.rest.model.GetvalidateconfigOut;
import com.livechain.pid.util.SystemConfig;
import com.livechain.pid.validater.DataValidaterImpl;
import com.livechain.pid.validater.ValidatonLoaderImpl;


//获取数据校验配置
@Component  
public class GetvalidateconfigServiceImpl {
	private final static Log log4j=LogFactory.getLog(DataValidaterImpl.class);
	private Validation validation =new Validation();
	@SuppressWarnings("unchecked")
	public void loader(Object properties) throws IOException {
		//获取路径
		Map<String,Object> proes=(Map<String,Object>)properties;
		URL url = ValidatonLoaderImpl.class.getClassLoader().getResource(proes.get("validaterpath").toString());
		log4j.info("loading xml file:"+url.getFile());
		File validatefile= new File(url.getFile());
    	//读取 XML配置文件
    	Document xmlDoc=null;
    	SAXReader xmlReader = new SAXReader();
    	try {
			  xmlDoc=xmlReader.read(validatefile);
			}catch(DocumentException e) {
			  e.printStackTrace();
			}
			///加载数据校验取出validation.xml里的field层
			Element rootE=xmlDoc.getRootElement();	
			List<Element> nodes =rootE.selectNodes("field");
	    	 //取出validation.xml里的field层里的元素
			for(Element e : nodes )
	    	   {
	    	      Field f = new Field();	    	    ;
	    	      //取出name 判断是否为空
	    	      String name=e.attributeValue("name");
	    	      if(!StringUtils.isEmpty(name))
	    	      {
	    	        f.setName(name);
	    	      }
	    	    //判断comment是否为空的     
	    	       String comment=e.attributeValue("comment");
	    	       if(!StringUtils.isEmpty(comment))
	    	       {
	    	    	 f.setComment(comment);//非空时候在set在Field里面去
	    	       }
	    	       String regx = e.attributeValue("regx");
	    	     if(!StringUtils.isEmpty(regx))
	    	       {
	    	    	 f.setRegx(regx);//非空时候set在Field里面去
	    	       }else{
	    	    	   //空的时候取出
	    	    	   List<Element> types = e.selectNodes("regx");
	    	    	   for (Element t : types) {
	       	            String type = t.attributeValue("type");
	       	            String value = t.attributeValue("value");
	       	            String com=t.attributeValue("comment");
	       	            f.addType(type, 0f, com, value, false);
	       	          }	          
	    	       }
	    	     
	    	   //对应元素放入值
	    	     if(f.getName()!=null&&!f.getName().equals(""))
	    	       {
	    	       if(f.getName().equals("pname"))
	    	        {
	    	    	   validation.setPname(f);
	    	        }
	    	       
	    	    if(f.getName().equals("gender"))
	   	            {
	    	    	   validation.setGender(f);
	   	            }
	    	     
	    	       if(f.getName().equals("credentials"))
	  	            {
	    	    	   validation.setCredentials(f);
	  	            }
	    	       if(f.getName().equals("email"))
	  	            {
	    	    	   validation.setEmail(f);
	  	            }     
	    	       if(f.getName().equals("birthday"))
	  	            {
	    	    	   validation.setBirthday(f);
	  	            }
	    	       if(f.getName().equals("nickname"))
	  	            {
	    	    	  validation.setNickname(f);
	  	            }
	    	       if(f.getName().equals("registeretype"))
	 	            {
	    	    	  validation.setRegisteretype(f);
	 	            }
	    	       if(f.getName().equals("addrprovince"))
	 	            {
	    	    	  validation.setAddrprovince(f);
	 	            }
	    	       if(f.getName().equals("addrcity"))
	 	            {
	    	    	  validation.setAddrcity(f);
	 	            }
	    	       if(f.getName().equals("addrcounty"))
	 	            {
	    	    	  validation.setAddrcounty(f);
	 	            }
	    	       if(f.getName().equals("addrtown"))
	 	            {
	    	    	  validation.setAddrtown(f);
	 	            }
	    	       if(f.getName().equals("addrvillage"))
	 	            {
	    	    	  validation.setAddrvillage(f);
	 	            }
	    	       if(f.getName().equals("addrhouseid"))
	 	            {
	    	    	 validation.setAddrhouseid(f);
	 	            }
	    	       if(f.getName().equals("phones"))
	 	            {
	    	    	 validation.setPhones(f);
	 	            }
	    	       if(f.getName().equals("nationality"))
		            {
	    	    	 validation.setNationality(f);
		            }
	    	       if(f.getName().equals("marriedstatus"))
		            {
	    	    	 validation.setMarriedstatus(f);
		            }
	    	       if(f.getName().equals("nation"))
		            {
	    	    	 validation.setNation(f);
		            }
	    	       if(f.getName().equals("age"))
		            {
	    	    	 validation.setAge(f);
		            }
	    	       if(f.getName().equals("booldtype"))
		            {
	    	    	 validation.setBooldtype(f);
		            }
	    	       if(f.getName().equals("rh"))
		            {
	    	    	 validation.setRh(f);
		            }
	    	       if(f.getName().equals("workdate"))
		            {
	    	    	 validation.setWorkdate(f);
		            }
	    	       if(f.getName().equals("jobtypecode"))
		            {
	    	    	 validation.setJobtypecode(f);
		            }
	    	       if(f.getName().equals("educationcode"))
		            {
	    	    	 validation.setEducationcode(f);
		            }
	    	       if(f.getName().equals("degreecode"))
		            {
	    	    	 validation.setDegreecode(f);
		            }
	    	       if(f.getName().equals("orgcodes"))
		            {
	    	    	 validation.setOrgcodes(f);
		            }}
			   }
			  log4j.info("validation load complete");
	       }
	
	public GetvalidateconfigOut getvalidateconfig(GetvalidateconfigIn in){
		GetvalidateconfigOut out=new GetvalidateconfigOut();
		ConverUtils.handleCallBack(out,in.getCallback());
		try {
			  this.loader(SystemConfig.getConfig());
		    } catch (IOException e) {
			  e.printStackTrace();
		    }
		    out.setTitle("获取数据校验配置");
		    out.setMsg("获取数据校验配置成功");
		    out.setRet("0");
		    out.setValidation(validation);
		    return out;
	  }
   }
