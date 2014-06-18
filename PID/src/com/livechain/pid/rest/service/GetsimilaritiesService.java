package com.livechain.pid.rest.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.spring.converter.ConverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.mybatis.dao.CredentialsMapper;
import com.livechain.mybatis.dao.OrgCodesMapper;
import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.dao.PhonesMapper;
import com.livechain.mybatis.model.Credentials;
import com.livechain.mybatis.model.CredentialsExample;
import com.livechain.mybatis.model.OrgCodesExample;
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.PhonesExample;
import com.livechain.pid.rest.model.GetsimilaritiesIn;
import com.livechain.pid.rest.model.GetsimilaritiesOut;

@Component  
public class GetsimilaritiesService {	
	private PersonMapper pmDao;
	private CredentialsMapper credentDao;
	private PhonesMapper phonesDao;
	private OrgCodesMapper ocodesDao;

	public CredentialsMapper getCredentDao() {
		return credentDao;
	}
	@Autowired
	public void setCredentDao(CredentialsMapper credentDao) {
		this.credentDao = credentDao;
	}
	public PhonesMapper getPhonesDao() {
		return phonesDao;
	}
	@Autowired
	public void setPhonesDao(PhonesMapper phonesDao) {
		this.phonesDao = phonesDao;
	}
	public OrgCodesMapper getOcodesDao() {
		return ocodesDao;
	}
	@Autowired
	public void setOcodesDao(OrgCodesMapper ocodesDao) {
		this.ocodesDao = ocodesDao;
	}
	@Autowired
	public void setPmDao(PersonMapper pmDao) {
		this.pmDao = pmDao;
	}
	public PersonMapper getPmDao() {
		return pmDao;
	}
	/**
	 * @param gi
	 * @param callBack
	 * @return go
	 */
	public GetsimilaritiesOut getsimilarities(GetsimilaritiesIn gi){
		GetsimilaritiesOut go = new GetsimilaritiesOut();
		  go.setTitle("获取相似人员信息");	
		  ConverUtils.handleCallBack(go, gi.getCallback());
		  
		  	//判断选择的信息是否为空
			/*if(gi.getPname()!=null&&gi.getGender()!=null&&gi.getBirthday()!=null){
				//判定接收是否有数据
		 		 if(gi.getPname().equals("")&&gi.getGender().equals("")&&gi.getBirthday().equals(""));
		 		    {
		 		    	go.setRet("102");
		 		    	go.setMsg("请输入正确的数据");
		 		    }*/
				
			
				if(gi.getPname()==null||gi.getPname().equals("")){
			 		  //不为空并且赋值的时候  把当前的查询条件加进去
					gi.setPname(null);
					   //ct.andPnameEqualTo(gi.getPname());
			 	     }
			 	  if(gi.getGender()==null||gi.getGender().equals("")){
			 		  gi.setGender(null);
					  // ct.andGenderEqualTo(gi.getGender());
				     }
			 	  if(gi.getBirthday()==null||gi.getBirthday().equals("")){
			 		  gi.setBirthday(null);
					 //  ct.andBirthdayEqualTo(gi.getBirthday());
				     }
			 	  if(gi.getIdcard()==null||gi.getIdcard().equals("")){
			 		 gi.setIdcard(null);
			 	    }
			 	  if(gi.getCard()==null||gi.getCard().equals("")){
			 		 gi.setCard(null);
			 	    }
			 	 
			//把size和pagesize封装在RowBounds里面
			RowBounds rowBounds=null;
			if(Integer.valueOf(gi.getPagesize())>0)
			{
				rowBounds=new RowBounds(Integer.valueOf(gi.getStart()),Integer.valueOf(gi.getPagesize()));
			}
			else
			{
				rowBounds=new RowBounds();
			}
			try {
				List<Person> person = pmDao.getSimilarity(rowBounds, gi);
				for(int i=0;i<person.size();i++)
  			  	{	
  			  	  Person p=person.get(i);
  			  	  String province =p.getAddrprovince()==null?"":p.getAddrprovince();
  			  	  String city =p.getAddrcity()==null?"":p.getAddrcity();
  			  	  String county =p.getAddrcounty()==null?"":p.getAddrcounty();
  			  	  String town =p.getAddrtown()==null?"":p.getAddrtown();
  			  	  String village =p.getAddrvillage()==null?"":p.getAddrvillage();
  			  	  String houseid =p.getAddrhouseid()==null?"":p.getAddrhouseid();
  			  	  p.setAddr(province+city+county+town+village+houseid);
  			  	  
  			  	  CredentialsExample cexample=new CredentialsExample();
	  			  CredentialsExample.Criteria ccrit=cexample.createCriteria();
	  			  ccrit.andPidEqualTo(p.getPid());
	  			  cexample.or(ccrit);
	  			  p.setCredentials(credentDao.selectByExample(cexample));
	  			  
	  			  PhonesExample phonmple = new PhonesExample();
	  			  PhonesExample.Criteria pcrit = phonmple.createCriteria();
	  			  pcrit.andPidEqualTo(p.getPid());
	  			  phonmple.or(pcrit);
	  			  p.setPhones(phonesDao.selectByExample(phonmple));
	  			  
	  			  OrgCodesExample ocodesmple = new OrgCodesExample(); 
	  			  OrgCodesExample.Criteria ocrit = ocodesmple.createCriteria();
	  			  ocrit.andPidEqualTo(p.getPid());
	  			  ocodesmple.or(ocrit);
	  			  p.setOrgcodes(ocodesDao.selectByExample(ocodesmple));
	  			  
  			  	  for(int j=0;j<p.getCredentials().size();j++)
  			  	  {
  			  		Credentials c= p.getCredentials().get(j);
  			  		if(c.getType().equals("01"))
  			  		{
  			  			p.setIdcard(c.getNum());
  			  		}
  			  	  }
  			  	}
				
				go.setCount(pmDao.getSimilaritycount(gi));
				go.setDatalist(person);
				go.setRet("0");
				go.setMsg("正常");
			} catch (Exception e) {
				e.printStackTrace();
				go.setRet("401");
				go.setMsg("数据库异常");
	}
			

			return go;

}
}

