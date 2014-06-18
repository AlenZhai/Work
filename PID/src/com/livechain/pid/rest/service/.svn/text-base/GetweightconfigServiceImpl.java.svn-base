package com.livechain.pid.rest.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import com.livechain.pid.model.Weight;
import com.livechain.pid.rest.model.GetweightconfigIn;
import com.livechain.pid.rest.model.GetweightconfigOut;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.SystemConfig;
import com.livechain.pid.weight.WeightLoaderImpl;
import com.livechain.pid.weight.WeightManger;
import com.livechain.pid.weight.WeightMangerImpl;

//获取权重比配置
@Component  
public class GetweightconfigServiceImpl {
	private final static Log log4j=LogFactory.getLog(WeightMangerImpl.class);
	private Weight weight = new Weight();
	private WeightManger weightMgr;
	@SuppressWarnings("unchecked")
	public void loader(Object properties) throws IOException {
		//获取路径
		/*Map<String,Object> proes=(Map<String,Object>)properties;
    	URL url = WeightLoaderImpl.class.getClassLoader().getResource(proes.get("weightpath").toString());
    	log4j.info("loading xml file:"+url.getFile());
    	File weightfile= new File(url.getFile());
    	//读取 XML配置文件
    	Document xmlDoc=null;
    	SAXReader xmlReader = new SAXReader();
    	try {
			  xmlDoc=xmlReader.read(weightfile);
			}catch(DocumentException e) {
			  e.printStackTrace();
			}
		//获取 上下限值
		Element rootE=xmlDoc.getRootElement();
		String max=rootE.attributeValue("max");
		String min=rootE.attributeValue("min");
		//判断上下限是否为空
    	  if(null != max && null != min)
    	    {
    		 weight.setMax(Float.valueOf(max));
    		 weight.setMin(Float.valueOf(min));	
    		}
    		//加载字段 权重 取出weight.xml里的field层
    	 List<Element> nodes =rootE.selectNodes("field");
    	 //取出weight.xml里的field层里的元素
    	 for(Element e : nodes )
    	   {
    	      Field f = new Field();
    	      //Field uniquef=new Field();
    	      //取出name  判断Pname是否为空
    	      String name=e.attributeValue("name");
    	      if(!StringUtils.isEmpty(name))
    	      {
    	      f.setName(name);
    	      }
    	      String weight1 = e.attributeValue("weight");       
    	  //判断uniqueStr是否为空的
    	      String uniqueStr=e.attributeValue("unique");
    	       if(!StringUtils.isEmpty(uniqueStr))
    	       {
    	    	 f.setUnique(Boolean.valueOf(uniqueStr));//非在set在Field里面去
    	       }
    	   //判断comment是否为空的     
    	       String comment=e.attributeValue("comment");
    	       if(!StringUtils.isEmpty(comment))
    	       {
    	    	 f.setComment(comment);//非在set在Field里面去
    	       }
    	       if(!StringUtils.isEmpty(weight1)) 
    	       {
    	         //判断字段 是否指定了 unique=true    	       
    	         f.addType("weight", Float.parseFloat(weight1));    	          	
    	       }else{
    	          List<Element> type = e.selectNodes("weight");
    	          Map<String,String> uniques=new HashMap<String,String>();
  	              Map<String,Float> ty=new HashMap<String,Float>();
    	          for (Element t : type) {
    	            String typeStr = t.attributeValue("type");
    	            String typeunique = t.attributeValue("unique");
    	            float value = Float.valueOf(t.attributeValue("value"));
    	            String com=t.attributeValue("comment");
    	          //判断type是否指定了 unique=true
    	           f.addType(typeStr, value, com, null, Boolean.valueOf(typeunique));    	           
    	          }
    	           //f.setUniques(uniques);
    	           //f.setTypes(ty);
    	         }*/
    	       //对应元素放入值  判断是否为空
		       
    	       //if(f.getName()!=null&&!f.getName().equals(""))
    	       //{
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.PNAME))
    	        {
    	    	   weight.setPname(weightMgr.getFields().get(PropertiesUtil.PNAME));
    	        }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.GENDER))
   	            {
   	    	      weight.setGender(weightMgr.getFields().get(PropertiesUtil.GENDER));
   	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.BIRTHDAY))
  	            {
  	    	      weight.setBirthday(weightMgr.getFields().get(PropertiesUtil.BIRTHDAY));
  	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.CREDENTIALS))
  	            {
  	    	      weight.setCredentials(weightMgr.getFields().get(PropertiesUtil.CREDENTIALS));
  	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.EMAIL))
  	            {
  	    	      weight.setEmail(weightMgr.getFields().get(PropertiesUtil.EMAIL));
  	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.NICKNAME))
  	            {
  	    	      weight.setNickname(weightMgr.getFields().get(PropertiesUtil.NICKNAME));
  	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.REGISTERETYPE))
 	            {
 	    	      weight.setRegisteretype(weightMgr.getFields().get(PropertiesUtil.REGISTERETYPE));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.ADDRPROVINCE))
 	            {
 	    	      weight.setAddrprovince(weightMgr.getFields().get(PropertiesUtil.ADDRPROVINCE));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.ADDRCITY))
 	            {
 	    	      weight.setAddrcity(weightMgr.getFields().get(PropertiesUtil.ADDRCITY));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.ADDRCOUNTY))
 	            {
 	    	      weight.setAddrcounty(weightMgr.getFields().get(PropertiesUtil.ADDRCOUNTY));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.ADDRTOWN))
 	            {
 	    	      weight.setAddrtown(weightMgr.getFields().get(PropertiesUtil.ADDRTOWN));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.ADDRVILLAGE))
 	            {
 	    	      weight.setAddrvillage(weightMgr.getFields().get(PropertiesUtil.ADDRVILLAGE));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.ADDRHOUSEID))
 	            {
 	    	      weight.setAddrhouseid(weightMgr.getFields().get(PropertiesUtil.ADDRHOUSEID));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.PHONES))
 	            {
 	    	      weight.setPhones(weightMgr.getFields().get(PropertiesUtil.PHONES));
 	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.NATIONALITY))
	            {
	    	      weight.setNationality(weightMgr.getFields().get(PropertiesUtil.NATIONALITY));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.MARRIEDSTATUS))
	            {
	    	      weight.setMarriedstatus(weightMgr.getFields().get(PropertiesUtil.MARRIEDSTATUS));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.NATION))
	            {
	    	      weight.setNation(weightMgr.getFields().get(PropertiesUtil.NATION));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.AGE))
	            {
	    	      weight.setAge(weightMgr.getFields().get(PropertiesUtil.AGE));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.BOOLDTYPE))
	            {
	    	      weight.setBooldtype(weightMgr.getFields().get(PropertiesUtil.BOOLDTYPE));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.RH))
	            {
	    	      weight.setRh(weightMgr.getFields().get(PropertiesUtil.BOOLDTYPE));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.WORKDATE))
	            {
	    	      weight.setWorkdate(weightMgr.getFields().get(PropertiesUtil.BOOLDTYPE));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.JOBTYPECODE))
	            {
	    	      weight.setJobtypecode(weightMgr.getFields().get(PropertiesUtil.JOBTYPECODE));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.EDUCATIONCODE))
	            {
	    	      weight.setEducationcode(weightMgr.getFields().get(PropertiesUtil.EDUCATIONCODE));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.DEGREECODE))
	            {
	    	      weight.setDegreecode(weightMgr.getFields().get(PropertiesUtil.DEGREECODE));
	            }
    	       if(weightMgr.getFields().containsKey(PropertiesUtil.ORGCODE))
	            {
	    	      weight.setOrgcodes(weightMgr.getFields().get(PropertiesUtil.ORGCODE));
	            }
    	       log4j.info("weight load complete");
    	 }
    	
	public GetweightconfigOut getweightconfig(GetweightconfigIn getweightconfigIn){
		GetweightconfigOut out=new GetweightconfigOut();
		ConverUtils.handleCallBack(out,getweightconfigIn.getCallback());
		try {
			  this.loader(SystemConfig.getConfig());
		    } catch (IOException e) {
			  e.printStackTrace();
		    }
		    out.setTitle("获取权重比配置");
		    out.setMsg("获取权重比配置成功");
		    out.setRet("0");
		    out.setWeight(weight);
		    return out;
	}
			
 }