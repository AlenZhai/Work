package com.livechain.pid.handler;

import org.json.JSONObject;


import com.livechain.pid.util.StandContext;


/**
 * 对请求进行处理
 * 处理数据
 * @author alenzhai 2013-05-16
 *
 */
public interface DataHandler {
	/**
	 * 对请求数据进行处理
	 * @param params 入参
	 * @param rtnParams 出参
	 * @param extParams 附加参数
	 */
     public void handlerReq(JSONObject params,JSONObject rtnParams,StandContext extParams);
}
