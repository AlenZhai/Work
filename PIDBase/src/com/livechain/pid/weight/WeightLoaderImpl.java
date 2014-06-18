package com.livechain.pid.weight;

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

import wl.zs.utils.StringUtils;


import com.livechain.pid.model.Field;
import com.livechain.pid.util.Properties;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.xml.XMLLoader;
/**
 * 权重配置文件加载器
 * @author alen zhai 2013-05-26
 *
 */
public class WeightLoaderImpl implements XMLLoader {
	private final static Log log4j=LogFactory.getLog(WeightMangerImpl.class);
	@Override
	public void loader(Object obj,Object properties) throws IOException {
		// TODO Auto-generated method stub
		//Properties proes=(Properties)properties;
		Map<String,Object> proes=(Map<String,Object>)properties;
		WeightMangerImpl weightManger=(WeightMangerImpl)obj;
		 // proes=new Properties(WeightMangerImpl.class,"conf.properties");
    	URL url = WeightLoaderImpl.class.getClassLoader().getResource(proes.get("weightpath").toString());
    	log4j.info("loading xml file:"+url.getFile());
    	File weightfile= new File(url.getFile());
    	//检查文件最后修改时间
    	if(weightfile.lastModified()>weightManger.getLastModify())
    	{
    		//读取 XML配置文件
    		Document xmlDoc=null;
    		SAXReader xmlReader = new SAXReader();
    		//xmlReader.setEncoding("UTF-8");
    		try {
				xmlDoc=xmlReader.read(weightfile);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//获取 上下限值
			Element rootE=xmlDoc.getRootElement();
			String max=rootE.attributeValue("max");
			String min=rootE.attributeValue("min");
    		if(null != max && null != min)
    		{
    			//System.out.println();
    			weightManger.setMax(Float.valueOf(max).floatValue());
    			weightManger.setMin(Float.valueOf(min).floatValue());
    			
    		}
    		//加载字段 权重
    		
    		List<Element> nodes =rootE.selectNodes("field");
    		
    		 
    		//System.out.println(weightManger.getMax()+1);
    		for(Element e : nodes )
    		{

    	        Field f = new Field();
    	        //Field uniquef=new Field();
    	        f.setName(e.attributeValue("name"));
    	        String w = e.attributeValue("weight");
    	        String comment = e.attributeValue("comment");
    	        //uniquef.setName(f.getName());
    	        String uniqueStr=e.attributeValue("unique");
    	        if (!StringUtils.isEmpty(w)) {
    	          //判断字段 是否指定了 unique=true
    	       f.addType(PropertiesUtil.WEIGHT,Float.valueOf(w), comment, null, Boolean.valueOf(uniqueStr));
    	         
    	        }
    	        else {
    	          List<Element> types = e.selectNodes("weight");
    	          for (Element t : types) {
    	            String type = t.attributeValue("type");
    	            String typeunique = t.attributeValue("unique");
    	            String com = t.attributeValue("comment");
    	            float v = Float.valueOf(t.attributeValue("value")).floatValue();
    	          //判断type是否指定了 unique=true
    	            f.addType(type, v, com, null, Boolean.valueOf(typeunique));
    	          }
    	        }
    	       //只有指定了unique的字段才会被放入uniquefields
    	        //如果 是 复合字段 只有 指定了 unique的子字段才会被放入uniquefields
    	       
    	        weightManger.getFields().put(f.getName(), f);
    		}
    		//设置 最后 修改时间 为当前 文档的 最后修改时间 
    		weightManger.setLastModify(weightfile.lastModified());
    		log4j.info("weight load complete");
    	}
	}

	@Override
	public void parse(Object result) {
		// TODO Auto-generated method stub

	}

}
