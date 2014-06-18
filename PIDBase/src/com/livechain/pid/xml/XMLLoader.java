package com.livechain.pid.xml;

import java.io.IOException;

/**
 * 1.加载XML配置文件
 * 2.解析XML内容
 * @author alenzhai 2013-05-16
 *
 */
public interface XMLLoader {
   /**
    * 
    * @param result 解析结果
    */
	public void parse(Object result); 
	
	/**
     * 加载配置文件
     * @param obj
     * @param des
     * @throws IOException
     */
	public void loader(Object obj,Object des) throws IOException;
}
