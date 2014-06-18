package com.livechain.pid.validater;

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

import wl.zs.utils.StringUtils;

import com.livechain.pid.model.Field;
import com.livechain.pid.util.Properties;
import com.livechain.pid.weight.WeightLoaderImpl;
import com.livechain.pid.weight.WeightMangerImpl;
import com.livechain.pid.xml.XMLLoader;

/**
 * 数据校验配置文件加载
 * 解析器
 * @author alenzhai 2013-05-16
 *
 */
public class ValidatonLoaderImpl implements XMLLoader {
	private final static Log log4j=LogFactory.getLog(WeightMangerImpl.class);
	@Override
	public void parse(Object result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loader(Object obj, Object properties) throws IOException {
		// TODO Auto-generated method stub
		Map<String,Object> proes=(Map<String,Object>)properties;
		DataValidaterImpl validater=(DataValidaterImpl)obj;
		 // proes=new Properties(WeightMangerImpl.class,"conf.properties");
    	URL url = WeightLoaderImpl.class.getClassLoader().getResource(proes.get("validaterpath").toString());
    	log4j.info("loading xml file:"+url.getFile());
    	
    	File validationfile= new File(url.getFile());
    	if(validationfile.lastModified()>validater.getLastModify())
    	{
    		//读取 XML配置文件
    		Document xmlDoc=null;
    		SAXReader xmlReader = new SAXReader();
    		try {
				xmlDoc=xmlReader.read(validationfile);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//获取 根元素
			Element rootE=xmlDoc.getRootElement();
			
    		
    		//加载字段 校验规则
    		
    		List<Element> nodes =rootE.selectNodes("field");
    		
    		//System.out.println(weightManger.getMax()+1);
    		for(Element e : nodes )
    		{

    	        Field f = new Field();
    	        
    	        f.setName(e.attributeValue("name"));
    	        String r = e.attributeValue("regx");
    	       
    	        if (!StringUtils.isEmpty(r)) {
    	          //判断字段 是否指定了 regx
    	         
    	        	  f.setRegx(r);
    	          
    	        }
    	        else {
    	          List<Element> types = e.selectNodes("regx");
    	          for (Element t : types) {
    	            String type = t.attributeValue("type");
    	           
    	            String v = t.attributeValue("value");
    	            String com=t.attributeValue("comment");
    	          //判断type是否指定了 regx
    	            
    	            	f.addType(type, 0f, com, v, false);
    	            
    	          }
    	        }
    	        validater.getFields().put(f.getName(), f);
    		}
    		//设置 最后 修改时间 为当前 文档的 最后修改时间 
    		validater.setLastModify(validationfile.lastModified());
    		log4j.info("validater load complete");
    	}
    	}

}
