package com.livechain.pid.validater;

import org.json.JSONObject;

/**
 * 根据数据校验规则校验数据
 * @author alenzhai 2013-05-16
 *
 */
public interface DataValidater {
	/**
	 * 根据规则校验并过虑数据
	 * @param validata 需要校验的数据
	 * 
	 */
   public boolean validate(JSONObject validata,String action,JSONObject response);
   /**
    * 检查数据字段个数据是否满足要求
    * 满足返回true 满足返回 false
    * @param data 需要检查的数据
    * @return
    */
   public  boolean hasData(JSONObject data,String action);
}
