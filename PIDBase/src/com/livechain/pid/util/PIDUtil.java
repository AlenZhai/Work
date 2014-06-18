package com.livechain.pid.util;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.livechain.pid.PIDGenerate;
import com.livechain.pid.PIDGenerateImpl;

public class PIDUtil {
	private static PIDGenerate generate;
	public static void buildQueryParams(JSONObject params,JSONObject query)
	{  
		
	}
	/**
	 * 生成PID
	 * @return
	 */
	public static String generatePID()
	{ 
		if(null == generate)
		{
			generate=new PIDGenerateImpl();
		}
		return generate.getPID();
	}
	public static boolean isPID(String pid)
	{
		boolean flag=false;
		if(null == pid)
			return flag;
		if(pid.length()!=32)
			return flag;
		return true;
	}
	/**
	 * 组装 要更新的数据
	 * 当调用接口更新 个人自然数据时 或者 注册个人自然数据时 已经存在这个人时
	 * 需要将这个人的数据更新到当前状态
	 * 
	 * @param json 为新数据
	 * @param persons 为老数据
	 */
	public static void buildUpdateParams(JSONObject json ,JSONObject person)
	{
		/*for(Object key:persons.keySet())
		{*/
			//JSONObject oldJson=(JSONObject)persons.get(key.toString());
		    
		}
	/*}*/

}
