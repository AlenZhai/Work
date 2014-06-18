package com.livechain.pid.rest.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livechain.pid.cache.DataSaveElement;
import com.livechain.pid.cache.ECacheManager;
import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.ws.init.HttpSaveQueueData;
import com.livechain.pid.ws.init.SaveQueue;

//@Component
public class SaveQueueData implements SaveQueue {
   private DaoSupport dao;
   private static final Logger logger = LoggerFactory.getLogger(SaveQueueData.class);
public DaoSupport getDao() {
	return dao;
}
//@Resource(name="logDao")
public void setDao(DaoSupport dao) {
	this.dao = dao;
}
public  void saveQueueData()
{
	while(ECacheManager.getNewInstance().peek()!=null)
	{ 
		System.out.println("save data");
		DataSaveElement element=ECacheManager.getNewInstance().peek();
		if(dao.saveInfo(element.json, element.otherjson))
		{			 
			ECacheManager.getNewInstance().outQueue();		
		}
	}
}
}
