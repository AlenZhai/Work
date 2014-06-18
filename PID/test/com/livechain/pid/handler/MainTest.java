package com.livechain.pid.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-pid.xml");
		DataHandler handler=(DataHandler)applicationContext.getBean("REGPID");
		System.out.println(handler.toString());
	}

}
