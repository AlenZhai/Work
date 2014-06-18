package com.livechain.pid.ws;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.livechain.pid.cache.DataSaveElement;
import com.livechain.pid.cache.ECacheManager;
import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.exception.ActionNullException;
import com.livechain.pid.exception.ClientNullException;
import com.livechain.pid.exception.ExceptionManager;
import com.livechain.pid.exception.NotEnoughParamsException;
import com.livechain.pid.exception.NotGoodJsonException;
import com.livechain.pid.exception.ParameterNullException;
import com.livechain.pid.exception.StandException;
import com.livechain.pid.handler.DataHandler;
import com.livechain.pid.util.PropertiesUtil;
import com.livechain.pid.util.SpringContextUtil;
import com.livechain.pid.util.SpringServletContextUtil;
import com.livechain.pid.util.StandContext;
import com.livechain.pid.util.StandContextImpl;
import com.livechain.pid.util.SystemConfig;
import com.livechain.pid.validater.DataValidater;

/**
 * WebService的处理类
 * @author alenzhai 2013-05-20
 *
 */
public class PIDWebServiceImpl implements PIDWebService {
	private static final Logger logger = LoggerFactory.getLogger(PIDWebServiceImpl.class);
    private DataHandler handler=null; //数据处理类
    private ExceptionManager exceptionManager=null;//异常管理类
	private DataValidater valider=null;//数据校验类
	private StandContext context=null;
	protected DaoSupport logDao;
	public DaoSupport getLogDao() {
		return logDao;
	}
	public void setLogDao(DaoSupport logDao) {
		this.logDao = logDao;
	}
	public StandContext getContext() {
		return context;
	}

	public void setContext(StandContext context) {
		this.context = context;
	}
//	private final static Log log4j=LogFactory.getLog(PIDWebServiceImpl.class);
	public DataValidater getValider() {
		return valider;
	}

	public void setValider(DataValidater valider) {
		this.valider = valider;
	}

	
	public DataHandler getHandler() {
		return handler;
	}

	public void setHandler(DataHandler handler) {
		this.handler = handler;
	}

	public ExceptionManager getExceptionManager() {
		return exceptionManager;
	}

	public void setExceptionManager(ExceptionManager exceptionManager) {
		this.exceptionManager = exceptionManager;
	}

	/**
	 * 1.将Json字符串转换成Json对象
	 * 2.根据action值的不同调用不同的DataHandler
	 * 3.调用DataHandler对象处理数据
	 * 4.返回处理结果
     * 
	 * @param arg 调用WebService时传过来的参数

	 */
	public String execute(String arg) {
		
		// TODO Auto-generated method stub
		JSONObject response = new JSONObject();
		//System.out.println("arg:"+arg);
		try{
		//判断传入参数是否为空
		if(null == arg)
		{
			StandException exception=new ParameterNullException();
			response.put("error", exceptionManager.getCode(exception));
			logger.error(exceptionManager.getCode(exception)+"=>"+exceptionManager.getMsg(exception));
		   return response.toString();
		}
		//判断传入参数是否是 良好的JSON格式串 
		//log4j.info("Json parse ...");		
		JSONObject json=null;
		try {
			json=strToJson(arg);
			logger.debug("create a JSON from string");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			StandException exception=new NotGoodJsonException();
			logger.error("the parameter["+arg+"] is "+exceptionManager.getMsg(exception));
			response.put("error", exceptionManager.getCode(exception));
		   return response.toString();
		}
		String client=null;
		String action=null;
		//log4j.info("Json parse complete");
		if(json.keySet().contains("client"))
		 {
			client=json.getString("client");
			json.remove("client");
		 }
		if(json.keySet().contains("action"))
		 {
			action=(String)json.get("action");
			json.remove("action");
		 }
		if(null==client || client.equals(""))
		{
			logger.error("the parameter Client  is null");
			response.put("error", exceptionManager.getCode(new ClientNullException()));
		    return response.toString();
		}
		if(null == action || action.equals(""))
		{
			StandException exception=new ActionNullException();
			logger.error(exceptionManager.getCode(exception)+"=>"+exceptionManager.getMsg(exception));
		  response.put("error", exceptionManager.getCode(exception));
		  return response.toString();
		}
		
		if(!valider.validate(json, action,response))
		{
			if(!client.equals("AppClient"))
			{
				if(json.keySet().contains("orgcode"))
				{
				  response.put("PID", json.getString("orgcode"));
				}else
				{
					if(SystemConfig.getConfig().containsKey("finalPID"))
					{
						response.put("PID",SystemConfig.getConfig().get("finalPID"));
					}
				}
				return response.toString();
			}
			StandException exception=new NotEnoughParamsException();
			logger.error(exceptionManager.getCode(exception)+"=>"+exceptionManager.getMsg(exception));
			response.put("error", exceptionManager.getCode(exception));
			return response.toString();
		}
		/*if(!valider.validate(json, action,response))
		{   
			response.put("error", exceptionManager.getCode(new NotEnoughParamsException()));
			return response.toString();
		}*/
		//根据 action 获取对应的DataHandler处理类
		handler=(DataHandler)SpringContextUtil.getContext().getBean(action);
		
		//log4j.info("get bean complete");
		if(null != handler && handler instanceof DataHandler)
		{    
		    //log4j.info("begid handle data");
			
			
			// log4j.info("handle data use:"+handler);
			try{
			handler.handlerReq(json, response,context);
			}catch(Exception e){e.printStackTrace();}
		}
		/**
		 * 将入参和人员信息存入数据库
		 */
		
		//new Thread().start();
		JSONObject params =new JSONObject();
		//JSONObject params=new JSONObject();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("createdate", format.format(new Date()));
		params.put("inparam", arg);
		params.put("outparam", response.toString());
		if(response.keySet().contains("PID"))
		{
			params.put("PID", response.get("PID"));
		}
		if(null !=action)
		 {			
			params.put("action", action);
		 }
		if(null!=client)
		{
			params.put("client", client);
		}
		//params.put("status", "0");
		//logDao.saveInfo(json, params);
		DataSaveElement element=new DataSaveElement();
		element.json=json;
		element.otherjson=params;
		//System.out.println(params);
		//System.out.println(json);
		if(action.equals("REGPID")||action.equals("REGCARD"))
		 ECacheManager.getNewInstance().inQueue(element);
		//System.out.println("pid web service queue size:"+ECacheManager.getNewInstance().getQueue().size());
		}catch(Exception e){e.printStackTrace();}
		//log4j.info("resquest complete");
		return response.toString();
		
	}
	/**
	 * 将Json字符串转换成Json对象
	 * @param jsonStr //入参 json字符串
	 * @return   返回Json对象
	 * @throws JSONException
	 */
	private JSONObject strToJson(String jsonStr) throws JSONException
	{   
		JSONObject json=null;
		try{		
		json=new JSONObject(jsonStr);
		 return json;
	    }catch(Exception e)
	    {
	       throw new JSONException(jsonStr);
	    }
	}
	

}
