

package com.livechain.pid.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;


public class SolrIndex {
	private final static String COMMIT="<commit/>";
	private final static String OPTIMIZE="<optimize/>";
	private final static String UPDATE="update";
	private static SolrServer solrServer =null;
	private String url="";
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public SolrIndex()
	{
		this.url="http://localhost:8082/solr/";
	}
	public SolrIndex(String url)
	{
		this.url=url;
	}
	public void init()
	{
		//try {
			solrServer = new HttpSolrServer(this.url);
		//} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	}
	public String indexedByXml(String xml)
	{   String url=this.url+UPDATE;
	     String backMsg="";
		try {
			
			 backMsg=HttpClientUtils.sendPostCommand(xml, url);
			HttpClientUtils.sendPostCommand(COMMIT, url);
			HttpClientUtils.sendPostCommand(OPTIMIZE, url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return backMsg;
		
	}
	
	public  String indexedByDoc(SolrInputDocument doc) throws SolrServerException, IOException, ParseException
	{
		if(null==solrServer)
		{
			this.init();
		}
		UpdateResponse res=solrServer.add(doc);
		
		return res.toString();
	}
	/*public void commint() throws SolrServerException, IOException
	{
		if(null==solrServer)
		{
			this.init();
		}
		//solrServer.optimize();
		//solrServer.commit();
	}*/
	public  String indexedByJson(Map<String,String> map) throws SolrServerException, IOException
	{
		if(null==solrServer)
			this.init();
		//Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		SolrInputDocument doc=new SolrInputDocument();
		Iterator keySet= map.keySet().iterator();
		while(keySet.hasNext())
		{
		  String key=keySet.next().toString();
		  System.out.println(key);
		  if(key.equals("birthday"))
		  {    // doc.addField(name, value, boost)
			  Calendar calen=Calendar.getInstance();
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
			  System.out.println(map.get(key));
			  Date date=null;
			try {
				date = format.parse(map.get(key));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				try {
					date =format2.parse(map.get(key));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			  
			  //calen.setTime(date);
			  doc.addField(key, format.format(date));
		  }else
		  {
		   doc.addField(key, map.get(key));
		  }
		}
		System.out.println(doc);
		UpdateResponse res=solrServer.add(doc);
		//solrServer.optimize();
		//solrServer.commit();
		
		
	  return res.toString();	
	}
	public String deleteAllIndex(String id) throws SolrServerException, IOException
	{
		UpdateResponse res=solrServer.deleteById(id);
		//solrServer.commit();
		return res.toString();
	}
	public boolean commit() 
	{
		try {
			solrServer.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	public boolean optimize() 
	{
		try {
			solrServer.optimize();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	   return true;
	}
	
}
