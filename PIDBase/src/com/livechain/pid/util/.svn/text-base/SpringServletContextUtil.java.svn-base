package com.livechain.pid.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public  class SpringServletContextUtil implements ServletContextAware {
	private static ServletContext servletCtext;
	public static ServletContext getServletCtext() {
		return servletCtext;
	}
	public static void setServletCtext(ServletContext servletCtext) {
		SpringServletContextUtil.servletCtext = servletCtext;
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.servletCtext=arg0;
	}

}
