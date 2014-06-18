package com.livechain.pid.rest.controller;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.livechain.pid.dao.WebDaoSupport;
import com.livechain.pid.rest.model.PersonList;
import com.livechain.pid.rest.model.UtilModel;
import com.livechain.pid.rest.model.Person;
import com.livechain.pid.util.SpringContextUtil;





@Controller
public class PeresonController {

	private Jaxb2Marshaller jaxb2Mashaller;
	private WebDaoSupport dao;
	
	public Jaxb2Marshaller getJaxb2Mashaller() {
		return jaxb2Mashaller;
	}
	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
		this.jaxb2Mashaller = jaxb2Mashaller;
	}
	
    //////////////////////////@ResponseBody ////////////////////////
	@RequestMapping(method=RequestMethod.GET, value="/person/{id}")
	public @ResponseBody Person getPerson(@PathVariable String id) {
		
		
		Person p=new Person();
		p.setPid("123456789");
		p.setPname("zhailei");
		p.setGender("1");
		p.setBirthday("1987-07-20");
		JSONObject json=new JSONObject();
		JSONObject response=new JSONObject();
		//json.put("PID", id);
		/*Person p=new Person();
		p.put("pname", "zhailei");
		p.put("gender","1");
		p.put("birthday", "1987-07-20");
		List<Map> credentials=new ArrayList<Map>();
		Map c=new HashMap();
		Map c1=new HashMap();
		c.put("type", "01");
		c.put("number", "4158754569");
		c1.put("type", "02");
		c1.put("number", "4158754569");
		credentials.add(c);
		credentials.add(c1);
		p.put("credentials", credentials);*/
		//p.setBirthday("1987-07-20");
		
		//p.setCredentials(cre);
		//System.out.println("get from p:"+p.getCredentials());
		//p.setPname("zhailei");
		//dao.getInfo(json, response);
		
		return p;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/persons")
	public @ResponseBody PersonList getPersons(
			@RequestParam("limit") String limit,
			@RequestParam("start") String start,
			@RequestParam(value="type",required=false) String type,		
			@RequestParam(value="param",required=false) String param,
			@RequestParam("page") String page) {
		/*@RequestParam("type") String type;
		@RequestParam("param") String param;*/
		System.out.println("limit:"+limit);
		System.out.println("start:"+start);
		System.out.println("page:"+page);
		
		System.out.println("type:"+type);
		System.out.println("param:"+param);
		param=urlDecoder(param);
		//DaoSupport dao=(DaoSupport)SpringContextUtil.getContext().getBean("webDao");
		WebDaoSupport dao=(WebDaoSupport)SpringContextUtil.getContext().getBean("webDao");
		
		JSONObject params=new JSONObject();
		 JSONObject rtn=new JSONObject();
		 params.put("isdel", "0");
		 if((null!=type&&type.equals("01"))||(null!=type&&type.equals("02")))
		 {			 
			 params.put("credentials.type",type);
			 params.put("credentials.number",param);
			 params.remove("isdel");
		 }
		 else if(null!=type&&null!=param&&!type.equals("")){
			 params.put(type, param);
			 params.remove("isdel");
		 }
		 params.put("limit", limit);
		 params.put("skip", start);
		 dao.getCount(params, rtn);
		 /*params.put("limit", 0);
		 params.put("skip", 0);*/
        dao.search(params, rtn);
        JSONArray persons=new JSONArray();
        PersonList plist=new PersonList();
        if(rtn.keySet().contains("datacount"))
        {
        	plist.setCount(rtn.getInt("datacount"));
        	rtn.remove("datacount");
        }
        this.JsonToEntry(rtn, plist);
        /*for(Object key:rtn.keySet())
        {
       	 persons.put(rtn.get(key.toString()));
        }*/
		
		return plist;
	}
	  //////////////////////////@Tools ////////////////////////
	/**
	 * 将JSON格式的数据转换成POJO
	 * @param params 
	 * @param plist
	 * @return
	 */
	protected boolean JsonToEntry(JSONObject params,PersonList plist)
	{   boolean flag=false;
		for(Object key:params.keySet())
	    {  Person pl=new Person();
	   	   JSONObject p= params.getJSONObject(key.toString());
	   	   pl.setPid(key.toString());
	   	   if(p.keySet().contains("pname"))
	   	   {
	   		  pl.setPname(p.getString("pname")); 
	   	   }
	   	   
	   	if(p.keySet().contains("gender"))
	   	   {
	   		  pl.setGender(p.getString("gender")); 
	   	   }
	   	if(p.keySet().contains("birthday"))
	   	   {
	   		  pl.setBirthday(p.getString("birthday")); 
	   	   }
	   	///
	   	if(p.keySet().contains("phone"))
	   	   {
	   		JSONArray phon=p.getJSONArray("phone");
	   		List<UtilModel> phone=new ArrayList<UtilModel>();
	   		for(int i=0;i<phon.length();i++)
	   		{   
	   			JSONObject po=phon.getJSONObject(i);
	   			
	   			if(po.keySet().contains("type")&&po.keySet().contains("number"))
	   			{
	   				UtilModel m=new UtilModel();
	   				m.setType(po.getString("type"));//这里加字典转换
	   				m.setNumber(po.getString("number"));
	   				if(po.keySet().contains("createdate"))
	   				{
	   					//这里加上日期
	   					//m.setCreateDate();
	   				}
	   				phone.add(m);
	   			}
	   			
	   		}
	   		 pl.setPhone(phone);
	   		 
	   	   }
	   	///处理身份证
	   	if(p.keySet().contains("credentials"))
	   	   {

	   		JSONArray phon=p.getJSONArray("credentials");
	   		List<UtilModel> credentials=new ArrayList<UtilModel>();
	   		for(int i=0;i<phon.length();i++)
	   		{   
	   			JSONObject po=phon.getJSONObject(i);
	   			
	   			if(po.keySet().contains("type")&&po.keySet().contains("number"))
	   			{
	   				String type=po.getString("type");
	   				String number=po.getString("number");
	   				UtilModel card=new UtilModel();
	   				card.setType(type);//这里加字典转换
	   				card.setNumber(number);
	   				if(po.keySet().contains("createdate"))
	   				{
	   					//这里加上日期
	   					//m.setCreateDate();
	   				}
	   				if(type.equals("01"))
	   				{
	   				 pl.setIdcard(number);
	   				}
	   				if(type.equals("02"))
	   				{
	   					pl.setCard(number);
	   				}
	   				credentials.add(card);
	   			}
	   			
	   		}
	   		 pl.setPhone(credentials);
	   		 
	   	   }
	   	if(p.keySet().contains("nickname"))
	   	   {
	   		  pl.setNickname(p.getString("nickname")); 
	   	   }
	   	if(p.keySet().contains("registeretype"))
	   	   {
	   		  pl.setRegisteretype(p.getString("registeretype")); 
	   	   }
	   	if(p.keySet().contains("addrprovince"))
	   	   {
	   		  pl.setAddrprovince(p.getString("addrprovince")); 
	   	   }
	   	if(p.keySet().contains("addrcity"))
	   	   {
	   		  pl.setAddrcity(p.getString("addrcity")); 
	   	   }
	   	if(p.keySet().contains("addrcounty"))
	   	   {
	   		  pl.setAddrcounty(p.getString("addrcounty")); 
	   	   }
	   	if(p.keySet().contains("addrtown"))
	   	   {
	   		  pl.setAddrtown(p.getString("addrtown")); 
	   	   }
	   	if(p.keySet().contains("addrvillage"))
	   	   {
	   		  pl.setAddrvillage(p.getString("addrvillage")); 
	   	   }
	   	if(p.keySet().contains("addrhouseid"))
	   	   {
	   		  pl.setAddrhouseid(p.getString("addrhouseid")); 
	   	   }
	   	if(p.keySet().contains("nationality"))
	   	   {
	   		  pl.setNationality(p.getString("nationality")); 
	   	   }
	   	if(p.keySet().contains("marriedstatus"))
	   	   {
	   		  pl.setMarriedstatus(p.getString("marriedstatus")); 
	   	   }
	   	if(p.keySet().contains("nation"))
	   	   {
	   		  pl.setNation(p.getString("nation")); 
	   	   }
	   	if(p.keySet().contains("age"))
	   	   {
	   		  pl.setAge(p.getInt("age")); 
	   	   }
	   	if(p.keySet().contains("booldType"))
	   	   {
	   		  pl.setBooldType(p.getString("booldType")); 
	   	   }
	   	if(p.keySet().contains("rh"))
	   	   {
	   		  pl.setRh(p.getString("rh")); 
	   	   }
	   	if(p.keySet().contains("workdate"))
	   	   {
	   		  pl.setWorkdate(p.getString("workdate")); 
	   	   }
	   	if(p.keySet().contains("jobtypecode"))
	   	   {
	   		  pl.setJobtypecode(p.getString("jobtypecode")); 
	   	   }
	   	if(p.keySet().contains("educationcode"))
	   	   {
	   		  pl.setEducationcode(p.getString("educationcode")); 
	   	   }
	   	if(p.keySet().contains("degreecode"))
	   	   {
	   		  pl.setDegreecode(p.getString("degreecode")); 
	   	   }
	   	if(p.keySet().contains("isdel"))
	   	   {
	   		  pl.setIsdel(p.getString("isdel")); 
	   	   }
	   	if(p.keySet().contains("status"))
	   	   {
	   		  pl.setStatus(p.getString("status")); 
	   	   }
	   /*	if(p.keySet().contains("PID"))
	   	   {
	   		  pl.setPID(p.getString("PID")); 
	   	   }*/
	   	///
	   	if(p.keySet().contains("similarity"))
	   	   {
	   		JSONArray phon=p.getJSONArray("similarity");
	   		List<String> similarity=new ArrayList<String>();
	   		for(int i=0;i<phon.length();i++)
	   		{   
	   			String simi=phon.get(i).toString();
	   			similarity.add(simi);
	   			   			
	   		}
	   		 pl.setSimilarity(similarity);
	   		 
	   	   }
	   	if(p.keySet().contains("parent"))
	   	   {
	   		  pl.setParent(p.getString("parent")); 
	   	   }
	   	plist.add(pl);
	   	flag=true;
	    }
		return flag;
	}
	protected String urlDecoder(String str)
	{  
		if(null==str)
		{
			return null;
		}
		String sdecode=null;
		try {
			sdecode = java.net.URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdecode;
	}
  
}
