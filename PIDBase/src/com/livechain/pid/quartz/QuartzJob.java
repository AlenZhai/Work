package com.livechain.pid.quartz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.livechain.mybatis.dao.CredentialsMapper;
import com.livechain.mybatis.dao.OrgCodesMapper;
import com.livechain.mybatis.dao.PersonInDataMapper;
import com.livechain.mybatis.dao.PersonMapper;
import com.livechain.mybatis.dao.PhonesMapper;
import com.livechain.mybatis.dao.SimilarityMapper;
import com.livechain.mybatis.model.Credentials;
import com.livechain.mybatis.model.CredentialsExample;
import com.livechain.mybatis.model.OrgCodes;
import com.livechain.mybatis.model.OrgCodesExample;
import com.livechain.mybatis.model.Person;
import com.livechain.mybatis.model.PersonExample;
import com.livechain.mybatis.model.PersonInData;
import com.livechain.mybatis.model.PersonInDataExample;
import com.livechain.mybatis.model.PersonInDataWithBLOBs;
import com.livechain.mybatis.model.Phones;
import com.livechain.mybatis.model.PhonesExample;
import com.livechain.mybatis.model.SimilarityExample;
import com.livechain.mybatis.model.SimilarityKey;
import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.dao.WebDaoSupport;
import com.livechain.pid.solr.SolrIndex;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.weight.WeightManger;
/**
 * 定时调度任务
 * @author Alen
 *
 */
public class QuartzJob {
	private PersonMapper personDao;//同步个人基本信息的DAO
	private CredentialsMapper credentialsDao;//同步证件信息的DAO
	private OrgCodesMapper orgCodeDao;//同步机构代码的DAO
	private PhonesMapper phonesDao;//同步联系方式的DAO
	private SimilarityMapper similarityDao;//同步相似记录的DAO
	private WebDaoSupport mongoDao;//从MongoDB中获取数据的DAO
	private boolean syncToDb;//是否做联系数据库同步
	private boolean syncToSolr;//是否做Solr数据库同步
	private boolean changeStatus;//是否做状态更改
	private boolean realDel;////是否做删除已同步数据
	private float min=10;//证件信息下限 小于此值 在solr中放入 credentials2
	private float max=50;//证件信息上限  大于此值在solr中放入 credentials
	                  //若 max>weight>=min 此值在solr中放入 credentials1
	private WeightManger weightMgr;//
	private SolrIndex solrIndex;
	private int limit=10;
	private WebDaoSupport logDao;
	private PersonInDataMapper personInDataDao;
	private boolean turnoff;
	public boolean isTurnoff() {
		return turnoff;
	}
	public void setTurnoff(boolean turnoff) {
		this.turnoff = turnoff;
	}
	public PersonInDataMapper getPersonInDataDao() {
		return personInDataDao;
	}
	//@Autowired
	public void setPersonInDataDao(PersonInDataMapper personInDataDao) {
		this.personInDataDao = personInDataDao;
	}
	public WebDaoSupport getLogDao() {
		return logDao;
	}
	//@Resource(name="logDao")
	public void setLogDao(WebDaoSupport logDao) {
		this.logDao = logDao;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public WeightManger getWeightMgr() {
		return weightMgr;
	}
	//@Autowired
	public void setWeightMgr(WeightManger weightMgr) {
		this.weightMgr = weightMgr;
	}
	public boolean isSyncToDb() {
		return syncToDb;
	}
	public float getMin() {
		return min;
	}
	public SolrIndex getSolrIndex() {
		return solrIndex;
	}
	//@Autowired
	public void setSolrIndex(SolrIndex solrIndex) {
		this.solrIndex = solrIndex;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public void setSyncToDb(boolean syncToDb) {
		this.syncToDb = syncToDb;
	}
	public boolean isSyncToSolr() {
		return syncToSolr;
	}
	public void setSyncToSolr(boolean syncToSolr) {
		this.syncToSolr = syncToSolr;
	}
	public boolean isChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(boolean changeStatus) {
		this.changeStatus = changeStatus;
	}
	public boolean isRealDel() {
		return realDel;
	}
	public void setRealDel(boolean realDel) {
		this.realDel = realDel;
	}	
    public PersonMapper getPersonDao() {
		return personDao;
	}    
    //@Autowired
	public void setPersonDao(PersonMapper personDao) {
		this.personDao = personDao;
	}
	public CredentialsMapper getCredentialsDao() {
		return credentialsDao;
	}	
	//@Autowired
	public void setCredentialsDao(CredentialsMapper credentialsDao) {
		this.credentialsDao = credentialsDao;
	}
	public OrgCodesMapper getOrgCodeDao() {
		return orgCodeDao;
	}	
	//@Autowired
	public void setOrgCodeDao(OrgCodesMapper orgCodeDao) {
		this.orgCodeDao = orgCodeDao;
	}
	public PhonesMapper getPhonesDao() {
		return phonesDao;
	}	
	//@Autowired
	public void setPhonesDao(PhonesMapper phonesDao) {
		this.phonesDao = phonesDao;
	}
	public SimilarityMapper getSimilarityDao() {
		return similarityDao;
	}
	//@Autowired
	public void setSimilarityDao(SimilarityMapper similarityDao) {
		this.similarityDao = similarityDao;
	}
	public WebDaoSupport getMongoDao() {
		return mongoDao;
	}	
	//@Resource(name="webDao")
	public void setMongoDao(WebDaoSupport mongoDao) {
		this.mongoDao = mongoDao;
	}
	/**
	 * 1.获取status=0的记录
	 * 2.将查出来的记录同步到关系数据库
	 * 3.将同步完的记录status=1 update条件pid 和status=0
	 * 4.获取status=2的记录
	 * 5.同步骤3
	 * 6.将同步完的记录status=1 update条件pid 和status=2
	 * 7.将status=1的记录删除
	 */
	public void work()
    {
        //获取status=0的记录  
		if(this.turnoff)
		{
			System.out.println("sync is turn off");
			return ;
		}
    	System.out.println("QuartzJob working ....");    	
		JSONObject params=new JSONObject();
		JSONObject rtn=new JSONObject();

		params.put("status", "0");
		params.put("limit", this.limit);
		params.put("skip", "0");
		//从MongoDB中取出未同步（status=0）的数据
		mongoDao.search(params, rtn);
		
		System.out.println("data status=0 rtn size:"+rtn.length());
		
		try {
			this.syncAddData(rtn);
		} catch (SolrServerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();return ;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();return ;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();return ;
		}		
//////////////////////////////////////////////////////////////////////////////////////////
		//将更新的数据 同步到数据库
		JSONObject params1=new JSONObject();
		
		
		//从MongoDB中取出未同步（status=0）的数据
			
		params1.put("status", "2");
		params1.put("limit", this.limit);
		params1.put("skip", "0");
		JSONObject rtn1=new JSONObject();
		mongoDao.search(params1, rtn1);
		System.out.println("data status=2 rtn size:"+rtn1.length());
		//同步更新的数据
		try {
			this.syncAddData(rtn1);
		} catch (SolrServerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();return ;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();return ;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();return ;
		} catch(Exception e){e.printStackTrace();return;}
		
		//删除已同步（status=1）的数据 冯延臣
		if(this.realDel)
		{
			try{				
				this.delsync();
				}catch(Exception e){e.printStackTrace(); return;}
		}	
/////////////////////////////////////////////////////////////////////////////////////////////
		//同步 log 数据
		this.syncLog();
		//}catch(Exception e){e.printStackTrace();}
    }
	private void syncLog()
	{
		JSONObject params=new JSONObject();
		JSONObject rtn=new JSONObject();
		params.put("status", "0");
		params.put("limit", this.limit);
		params.put("skip", "0");
		logDao.searchlog(params, rtn);
		System.out.println("log status=0 rtn size:"+rtn.length());
		//同步 log数据 到数据库
		if(this.syncToDb)
		{
			try{
				this.syncLog(rtn);
		    }catch(Exception e){e.printStackTrace();return;}
		}
		
		//更改 log数据的状态
		if(this.changeStatus)
		{
			try{
		 this.changeStautsLog(rtn);
		}catch(Exception e){e.printStackTrace();return;}
		}
		//删除 状态为 1 的数据
		if(this.realDel)
		{
			try{
		  this.realDelLog();
			}catch(Exception e){e.printStackTrace();return;}
		}
	}
	private void syncLog(JSONObject rtn)
	{		
		
		for(Object key:rtn.keySet())
		{
			PersonInDataWithBLOBs p=new PersonInDataWithBLOBs();
			JSONObject json=rtn.getJSONObject(key.toString());
			if(json.keySet().contains("inparam"))
			{
				p.setInparam(json.getString("inparam"));
			}
			if(json.keySet().contains("outparam"))
			{
				p.setOutparam(json.getString("outparam"));
			}
			if(json.keySet().contains("createdate"))
			{
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d=null;
				try {
					d = format.parse(json.getString("createdate"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.setCreatedate(d);
			}
			if(json.keySet().contains("pid"))
			{
				p.setPid(json.getString("pid"));
			}
			if(json.keySet().contains("action"))
			{
				p.setAction(json.getString("action"));
			}
			if(json.keySet().contains("client"))
			{
				p.setClient(json.getString("client"));
			}
			p.setObjid(key.toString());
			try{
				PersonInDataExample example=new PersonInDataExample();
				PersonInDataExample.Criteria crit=example.createCriteria();
				crit.andObjidEqualTo(p.getObjid());
				example.or(crit);
				System.out.println("person in data :"+key);
				List<PersonInData> list=this.personInDataDao.selectByExample(example);
				if(null!=list&&list.size()>0)
				{
					
				}else
				{
			     this.personInDataDao.insertSelective(p);
				}
			}catch(Exception e){e.printStackTrace();return;}
		}
	}
	private void changeStautsLog(JSONObject rtn)
	{
		for(Object key:rtn.keySet())
		{
			if(rtn.get(key.toString()) instanceof JSONObject)
    		{   JSONObject condition=new JSONObject();
    		    JSONObject param=new JSONObject();
    			JSONObject json=rtn.getJSONObject(key.toString());
    			//组装更新条件
    			 condition.put("status",json.get("status") );  
    			 condition.put("_id", key); 
    			 param.put("condition", condition);
    			//改变数据状态
    	    		json.put("status", "1");    	    		
    		    boolean flag=this.logDao.updateByCondition(json, param);
    		    //System.out.println("_id:"+key);
    		    System.out.println("change stauts:"+flag);
    		}
		}
	}
	private void realDelLog()
	{
		JSONObject params=new JSONObject();
		JSONObject rtn=new JSONObject();
		params.put("status", "1");
		this.logDao.realDel(params, rtn);
	}
	public void syncAddData(JSONObject rtn) throws SolrServerException, IOException, ParseException
	{
		//try{
		//将数据同步到关系型数据库中
		System.out.println("sysnc db  =====================================");
		if(this.syncToDb)
		{
			try{
				this.sync(rtn);
				}catch(Exception e){e.printStackTrace(); return;}
						
		}
		System.out.println("sysnc solr  =====================================");
		//将数据同步到Solr中
		if(this.syncToSolr)
		{
			try{
			 this.syncToSolr(rtn);
			}catch(Exception e){e.printStackTrace();return;}
		}
		System.out.println("change status  =====================================");
		//回写同步完数据的状态
		if(this.changeStatus)
		{
			//try{
				
		    this.changeStatus(rtn);
			//}catch(Exception e){e.printStackTrace(); return;}
		}
	}
	
	/**
	 * 将 MongoDB中的数据同步到SOLR中
	 * @param rtn
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public void syncToSolr(JSONObject rtn) throws SolrServerException, IOException, ParseException
	{
		
		//SolrIndex solrIndex=new SolrIndex();
		//solrIndex.init();
		//获取各个人的信息
		for(Object key:rtn.keySet())
		{
			//将人的信息组装到doc中
			if(rtn.get(key.toString()) instanceof JSONObject)
    		{ 
				JSONObject json=rtn.getJSONObject(key.toString());
				//System.out.println("json:"+json);
				if(json.keySet().contains("_id"))
				{
					json.remove("_id");
				}
				if(json.keySet().contains(PropertiesUtil.PERSON))
				{
					json.remove(PropertiesUtil.PERSON);
				}
				SolrInputDocument doc=new SolrInputDocument();
				for(Object k:json.keySet())
				{
					if(json.get(k.toString()) instanceof JSONArray)
					{
						JSONArray array=json.getJSONArray(k.toString());
						for(int index=0;index<array.length();index++)
						{   
							
							if(k.equals(PropertiesUtil.PHONES) || k.equals(PropertiesUtil.CREDENTIALS))
							{
								JSONObject j=(JSONObject) array.get(index);
								String card=j.getString(PropertiesUtil.NUMBER)+"_"+j.getString(PropertiesUtil.TYPE);
								if(k.equals(PropertiesUtil.CREDENTIALS))
								{
									float w=0;
									if(weightMgr.getFields().get(k).getTypes().containsKey(j.get(PropertiesUtil.TYPE)))
									{
										w=weightMgr.getFields().get(k).getTypes().get(j.get(PropertiesUtil.TYPE)).getWeight();
									}else if(weightMgr.getUniquefields().get(k).getTypes().containsKey(j.get(PropertiesUtil.TYPE)))
									{
										w=max+10;
									}
									if(w<min)
									{
									 doc.addField(k.toString()+"2",card);
									}else if(w>=min&&max>w)
									{
									  doc.addField(k.toString()+"1", card);
									}else if(w>max)
									{
										doc.addField(k.toString(), card);
									}
								}else{
									doc.addField(k.toString(), card);
								}
						  }else{
							  String j=array.get(index).toString();
							  doc.addField(k.toString(), j);
						  }
						}
						
					}else
					{			
						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(k.equals(PropertiesUtil.CREATEDATE))
						{
							Date d=format.parse(json.get(k.toString()).toString()) ;
							doc.addField(k.toString(), d.getTime());
						}else
						{
							doc.addField(k.toString(), json.get(k.toString()));
						}
								
					}
					
				}
				//将数据同步到Solr中				
				solrIndex.indexedByDoc(doc);
    		}
		}
		solrIndex.commit();
	}
	/**
	 * 将已同步的数据(status=1)的数据删除
	 */
	private void delsync()
	{
		JSONObject params=new JSONObject();
		JSONObject rtn=new JSONObject();
		params.put("status", "1");
	    this.mongoDao.realDel(params, rtn);
	}
	/**
	 * 将同步完的数据状态（status=1)回写到MongoDB中
	 * @param rtn
	 */
	private void changeStatus(JSONObject rtn)
	{
		for(Object key:rtn.keySet())
		{
			if(rtn.get(key.toString()) instanceof JSONObject)
    		{   JSONObject condition=new JSONObject();
    		    JSONObject param=new JSONObject();
    		    JSONObject s=new JSONObject();
    			JSONObject json=rtn.getJSONObject(key.toString());
    			//组装更新条件
    			 condition.put("status",json.get("status") ); 
    			 condition.put("PID", json.get("PID"));
    			 //condition.put("_id", key);
    			 param.put("condition", condition);
    			//改变数据状态
    			    
    	    		s.put("status", "1");    	    		
    		    boolean flag=this.mongoDao.changeStatus(s, param);
    		   // System.out.println("_id:"+key);
    		    System.out.println("change stauts:"+flag);
    		}
		}
	}
	/**
	 * 将MongoDB中的数据同步到关系型数据库中
	 * @param rtn 从MongoDB中查出来的数据集
	 */
	private void sync(JSONObject rtn)
	{
    	for(Object key:rtn.keySet())
    	{
    		if(rtn.get(key.toString()) instanceof JSONObject)
    		{
    			JSONObject json=rtn.getJSONObject(key.toString());
    			Person p=new Person();
    			if(json.keySet().contains("pname"))
    			{
    				p.setPname(json.get("pname").toString());
    			}
    			if(json.keySet().contains("birthday"))
    			{    		
    				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
    				Date d1=null;
					try {
						d1 = sdf.parse(json.get("birthday").toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();return;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						try {
							d1=sdf1.parse(json.get("birthday").toString());
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();return;
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();return;
						}
					}
    				p.setBirthday(sdf.format(d1));
    			}
    			if(json.keySet().contains("gender"))
    			{    				
    				p.setGender(json.get("gender").toString());
    			}
    			//////////////////////////////////////////////////////////
    			if(json.keySet().contains("email"))
    			{    				
    				p.setEmail(json.get("email").toString());
    			}
    			if(json.keySet().contains("nickname"))
    			{    				
    				p.setNickname(json.get("nickname").toString());
    			}
    			if(json.keySet().contains("registeretype"))
    			{    				
    				p.setRegisteretype(json.get("registeretype").toString());
    			}
    			if(json.keySet().contains("addrprovince"))
    			{    				
    				p.setAddrprovince(json.get("addrprovince").toString());
    			}
    			if(json.keySet().contains("addrcity"))
    			{    				
    				p.setAddrcity(json.get("addrcity").toString());
    			}
    			if(json.keySet().contains("addrcounty"))
    			{    				
    				p.setAddrcounty(json.get("addrcounty").toString());
    			}
    			if(json.keySet().contains("addrtown"))
    			{    				
    				p.setAddrtown(json.get("addrtown").toString());
    			}
    			if(json.keySet().contains("addrvillage"))
    			{    				
    				p.setAddrvillage(json.get("addrvillage").toString());
    			}
    			if(json.keySet().contains("addrhouseid"))
    			{    				
    				p.setAddrhouseid(json.get("addrhouseid").toString());
    			}
    			if(json.keySet().contains("nationality"))
    			{    				
    				p.setNationality(json.get("nationality").toString());
    			}
    			if(json.keySet().contains("marriedstatus"))
    			{    				
    				p.setMarriedstatus(json.get("marriedstatus").toString());
    			}
    			if(json.keySet().contains("nation"))
    			{    				
    				p.setNation(json.get("nation").toString());
    			}
    			if(json.keySet().contains("age"))
    			{    				
    				p.setAge(Integer.valueOf(json.get("age").toString()));
    			}
    			if(json.keySet().contains("booldtype"))
    			{    				
    				p.setBooldtype(json.get("booldtype").toString());
    			}
    			if(json.keySet().contains("rh"))
    			{    				
    				p.setRh(json.get("rh").toString());
    			}
    			/////////////////////////////////////////////////////////////////////
    			if(json.keySet().contains("workdate"))
    			{    				
    				p.setWorkdate(json.get("workdate").toString());
    			}
    			if(json.keySet().contains("jobtypecode"))
    			{    				
    				p.setJobtypecode(json.get("jobtypecode").toString());
    			}
    			if(json.keySet().contains("educationcode"))
    			{    				
    				p.setEducationcode(json.get("educationcode").toString());
    			}
    			if(json.keySet().contains("degreecode"))
    			{    				
    				p.setDegreecode(json.get("degreecode").toString());
    			}
    			if(json.keySet().contains("isdel"))
    			{    				
    				p.setIsdel(json.get("isdel").toString());
    			}
    			if(json.keySet().contains("PID"))
    			{    				
    				p.setPid(json.get("PID").toString());
    			}
    			if(json.keySet().contains("parent"))
    			{    				
    				p.setParent(json.get("parent").toString());
    			}     			   				
    			p.setPerson(json.toString());
    			PersonExample example=new PersonExample();
    			PersonExample.Criteria cri=example.createCriteria();
    			cri.andPidEqualTo(p.getPid());
    			example.or(cri);
    			List<Person> tp=personDao.selectByExample(example);
    			//System.out.println(p.getPid());
    			//插入或更新 个人基本信息
    			if(null!=tp && tp.size()>0)
    			{
    				System.out.println("update person pname:"+p.getPname());
    				personDao.updateByExampleSelective(p, example);
    			}else
    			{
    				//try{
    			  System.out.println("insert person pname:"+p.getPname());
    			   personDao.insertSelective(p);
    				//}catch(Exception e){e.printStackTrace();}
    			}
    			    			
    			
    			if(json.keySet().contains("credentials"))
    			{
    				if(json.get("credentials") instanceof JSONArray)
    				{
    					//证件数组
    					//List<Credentials> creden=new ArrayList<Credentials>();
    					JSONArray credentials=json.getJSONArray("credentials");
    					for(int cindex=0;cindex<credentials.length();cindex++)
    					{
    						//证件信息
    						Credentials cred=new Credentials();
    						if(credentials.get(cindex) instanceof JSONObject )
    						{
    							JSONObject card=credentials.getJSONObject(cindex);
    							if(card.keySet().contains("type")&&card.keySet().contains("number"))
    							{
    								cred.setType(card.getString("type"));
    								cred.setNum(card.getString("number"));
    								cred.setPid(json.getString("PID"));
    							}
    							CredentialsExample example1=new CredentialsExample();
    							CredentialsExample.Criteria crit=example1.createCriteria();
    							crit.andPidEqualTo(p.getPid());
    							crit.andTypeEqualTo(cred.getType());
    							crit.andNumEqualTo(cred.getNum());
    							example1.or(crit);
    							List<Credentials> tcredentials= credentialsDao.selectByExample(example1);
    							//插入或更新 证件信息
    							if(null !=tcredentials && tcredentials.size()>0)
    							{
    								//try{
    								credentialsDao.updateByExampleSelective(cred, example1);
    								//}catch(Exception e){e.printStackTrace();}
    							}else
    							{
    								//try{
    								credentialsDao.insertSelective(cred);
    								//}catch(Exception e){e.printStackTrace();}
    							}
    						}
    					}    					
    				}    				
    			}
    			if(json.keySet().contains("phones"))
    			{
    				
    				
    				if(json.get("phones") instanceof JSONArray)
    				{
    					//证件数组
    					//List<Phones> creden=new ArrayList<Phones>();
    					JSONArray phones=json.getJSONArray("phones");
    					for(int cindex=0;cindex<phones.length();cindex++)
    					{
    						//联系方式
    						Phones cred=new Phones();
    						if(phones.get(cindex) instanceof JSONObject )
    						{
    							JSONObject card=phones.getJSONObject(cindex);
    							if(card.keySet().contains("type")&&card.keySet().contains("number"))
    							{
    								cred.setType(card.getString("type"));
    								cred.setNum(card.getString("number"));
    								cred.setPid(json.getString("PID"));
    							}
    							//creden.add(cred);
    							PhonesExample example1=new PhonesExample();
    							PhonesExample.Criteria cirt=example1.createCriteria();
    							cirt.andPidEqualTo(cred.getPid());
    							cirt.andTypeEqualTo(cred.getType());
    							cirt.andNumEqualTo(cred.getNum());
    							example1.or(cirt);
    							List<Phones> tphones=phonesDao.selectByExample(example1);
    							//插入或更新 联系方式 信息
    							if(null!=tphones && tphones.size()>0)
    							{
    								//try{
    								phonesDao.updateByExampleSelective(cred, example1);
    								// }catch(Exception e){e.printStackTrace();}
    							}else
    							{
    								//try{
    								phonesDao.insertSelective(cred);
    							   //}catch(Exception e){e.printStackTrace();}
    							}
    						}
    					}
    					
    				}
    			}
    			if(json.keySet().contains("similarity"))
    			{
    				if(json.get("similarity") instanceof JSONArray)
    				{
    					JSONArray similarity=json.getJSONArray("similarity");
    					//List<String> simil=new ArrayList<String>();
    					SimilarityKey simil=new SimilarityKey();
    					for(int cindex=0;cindex<similarity.length();cindex++)
    					{
    						System.out.println(similarity.get(cindex));
    						JSONObject s=similarity.getJSONObject(cindex);
    						simil.setSimilarity(s.getString("PID"));
    						simil.setPid(json.getString("PID"));
    						SimilarityExample example1=new SimilarityExample();
    						SimilarityExample.Criteria crit=example1.createCriteria();
    						crit.andPidEqualTo(simil.getPid());
    						crit.andSimilarityEqualTo(simil.getSimilarity());
    						example1.or(crit);
    						List<SimilarityKey> tsimi=this.similarityDao.selectByExample(example1);
    						//插入或更新 相似人员 关联信息
    						if(null!=tsimi && tsimi.size()>0)
    						{
    							//try{
    							this.similarityDao.updateByExampleSelective(simil, example1);
    						// }catch(Exception e){e.printStackTrace();}
    						}else
    						{
    							//try{
    							this.similarityDao.insertSelective(simil);
    						 //}catch(Exception e){e.printStackTrace();}
    						}
    					}
    					
    				}
    			}
    			if(json.keySet().contains("orgcodes"))
    			{
    				if(json.get("orgcodes") instanceof JSONArray)
    				{
    					JSONArray orgcodes=json.getJSONArray("orgcodes");
    					//List<String> org=new ArrayList<String>();
    					OrgCodes org=new OrgCodes();
    					for(int cindex=0;cindex<orgcodes.length();cindex++)
    					{
    						//System.out.println(orgcodes.get(cindex));
    						JSONObject o=orgcodes.getJSONObject(cindex);
    						org.setOrgcode(o.getString("orgcode"));
    						org.setPid(json.getString("PID"));
    						OrgCodesExample example1=new OrgCodesExample();
    						OrgCodesExample.Criteria crit=example1.createCriteria();
    						crit.andPidEqualTo(org.getPid());
    						crit.andOrgcodeEqualTo(org.getOrgcode());
    						example1.or(crit);
    						List<OrgCodes> torgs=this.orgCodeDao.selectByExample(example1);
    						//插入或更新 机构信息
    						if(null!=torgs && torgs.size()>0)
    						{
    							//try{
    							this.orgCodeDao.updateByExampleSelective(org, example1);
    						 //}catch(Exception e){e.printStackTrace();}
    						}else
    						{
    							//try{
    							this.orgCodeDao.insertSelective(org);
    						// }catch(Exception e){e.printStackTrace();}
    						}
    					}
    					
    				}
    			}    		
    		}    		
    	}
	}
}
