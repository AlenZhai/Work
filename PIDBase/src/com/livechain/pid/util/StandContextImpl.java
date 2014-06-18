package com.livechain.pid.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

public class StandContextImpl implements StandContext{
	private  ServletContext servletContext=null;
	private  ApplicationContext context=null;
	
	public StandContextImpl()
	{
		this.context=SpringContextUtil.getContext();
		this.servletContext=SpringServletContextUtil.getServletCtext();
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	@Override
	public ApplicationContext getContext() {
		// TODO Auto-generated method stub
		return context;
	}
	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return servletContext;
	}
	
	
}
