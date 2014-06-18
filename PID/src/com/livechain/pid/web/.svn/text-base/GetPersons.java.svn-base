package com.livechain.pid.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.livechain.pid.dao.DaoSupport;
import com.livechain.pid.dao.MongoDaoSupportImpl;
import com.livechain.pid.util.SpringContextUtil;

public class GetPersons extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPersons() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration ha=request.getAttributeNames();
		Map ha1=request.getParameterMap();
		System.out.println(new Date().getTime());
		while(ha.hasMoreElements())
		{
			System.out.println(ha.nextElement());
		}
		System.out.println("=========================");
		for(Object key:ha1.keySet())
		{
			System.out.println(key+":"+request.getParameter(key.toString()));
		}
		
		response.setCharacterEncoding("UTF-8");
		 PrintWriter out = response.getWriter();
         DaoSupport dao=(MongoDaoSupportImpl)SpringContextUtil.getContext().getBean("pidDao");
		 JSONObject params=new JSONObject();
		 JSONObject rtn=new JSONObject();
		 params.put("isdel", "0");
         dao.getInfo(params, rtn);
         JSONArray persons=new JSONArray();
         for(Object key:rtn.keySet())
         {
        	 persons.put(rtn.get(key.toString()));
         }
         out.write(persons.toString());
         /*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
