package com.livechain.pid.cache;

import java.util.LinkedList;
import java.util.Queue;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 1.管理缓存
 * 
 * @author alenzhai 2013-05-16
 * 
 */
public class ECacheManager {
	private static final Logger logger = LoggerFactory.getLogger(ECacheManager.class);
	private static Queue<DataSaveElement> jsonQueue=new LinkedList<DataSaveElement>();
	private static ECacheManager cacheMgr;
	private ECacheManager()
	{
		logger.debug("init cache .....");
	}
	/**
	 * 获取缓存管理对象
	 * @return
	 */
	public  static ECacheManager getNewInstance()
	{
		if(cacheMgr==null)
		{
			cacheMgr=new ECacheManager();
		}
		return cacheMgr;
	}
	/**
	 * 获取队列
	 * @return
	 */
	public  Queue<DataSaveElement> getQueue()
	{
		if(jsonQueue==null)
		{
			jsonQueue=new LinkedList<DataSaveElement>();
		}
		return jsonQueue;
	}
	/**
	 * 从队列头取出一个元素并移除该元素
	 * @return
	 */
	public synchronized  DataSaveElement outQueue()
	{
		return jsonQueue.poll();
	}
	/**
	 * 在队列的尾加入一个元素
	 * @param json
	 */
	public synchronized void inQueue(DataSaveElement json)
	{
		jsonQueue.offer(json);
	}
	/**
	 * 从队列头取出一个元素但不移除该元素
	 * @return
	 */
	public  DataSaveElement peek()
	{
		return jsonQueue.peek();
	}

}
