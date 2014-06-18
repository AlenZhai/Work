package com.livechain.pid.ws.init;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.livechain.pid.cache.DataSaveElement;
import com.livechain.pid.cache.ECacheManager;
import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.rest.service.SaveQueueData;
import com.livechain.pid.util.SpringContextUtil;
import com.livechain.pid.ws.PIDWebServiceImpl;


/**
 * 加载初始化信息
 * @author alenzhai 2013-05-29
 *
 */
public class SystemInit implements ServletContextListener{
	private static final Logger logger = LoggerFactory.getLogger(SystemInit.class);
	private Set<Thread> threadPool=new HashSet<Thread>();
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext context=sce.getServletContext();
		for(Thread t : threadPool)
		{
		  t.interrupt();
		  logger.info("thread "+t.getName()+" stoped");
		}
		//WeightMangerImpl manager=WeightMangerImpl.newInstance();
		//manager.init();
		//context.setAttribute("weightManager", manager);
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//System.out.println("System init");
		logger.info("System init...");
		int count=10;
		do{
		Thread thread=new Thread(new ThreadRun());
		thread.start();
		threadPool.add(thread);
		logger.debug(thread+" add to the thread pool");
		count--;
		}while(count>0);
		
		
	}
	@SuppressWarnings("unused")
	private class ThreadRun implements Runnable{
        
		@Override
		public void run() {
			
			//System.out.println("start thread save data");
			SaveQueue saveQueueData=(SaveQueue)SpringContextUtil.getContext().getBean("saveQueueData");
			logger.info(Thread.currentThread().getName()+" Thread started");
			while(true)
			{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.info("stop thread");
					return ;
				}
				saveQueueData.saveQueueData();
				
			}
		}		
	}

}
