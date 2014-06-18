package com.livechain.pid.solr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.update.SolrIndexWriter;

public class SolrSearch {
	private static SolrServer solrServer =null;
	private static final String SELECT="select";
	private String url="";
	public SolrSearch()
	{
		this.url="http://localhost:8082/solr/";
	}
	public SolrSearch(String url)
	{
		this.url=url;
	}
	public void init()
	{
		//try {
			solrServer = new HttpSolrServer(this.url);
		//} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	public SolrDocumentList solrjByPage(String keyword,long start,long rows) throws SolrServerException
	{
		Map map=new HashMap(); 
	    map.put("q", keyword);
	    map.put("fl", "*,score");
	    map.put("start", start+"");
	    map.put("rows", rows+"");
	   
	    map.put("sort", "score desc");
	    SolrParams  query =new MapSolrParams(map);
        if(null==solrServer)
        	this.init();
	    QueryResponse response = solrServer.query(query);
	    SolrDocumentList list = response.getResults();
	    
	    
    	return list;
	}
	public SolrDocumentList solrj(String keyword) throws SolrServerException{
		StringBuilder builder = new StringBuilder();
		
	    // http://localhost:8088/solr/select?"fl=*,score&start=0&rows=10&hl=true&hl.fl=*&q=keyword
	    //SolrQuery query = new SolrQuery();
	    //query.setHighlight(true).setHighlightSnippets(1); //set other params as needed
	    Map map=new HashMap(); 
	    map.put("q", keyword);
	    map.put("fl", "*,score");
	    map.put("start", "0");
	    map.put("rows", "10");
	   // query.set("hl.fl", "name");
	    //query.set("hl.simple.pre", "<font color='red'>");
	   // query.set("hl.simple.post", "</font>");
	    map.put("sort", "score desc");
	    SolrParams  query =new MapSolrParams(map);
        if(null==solrServer)
        	this.init();
	    QueryResponse response = solrServer.query(query);
	    SolrDocumentList list = response.getResults();
	    
	    /*for(SolrDocument solrdoc:list)
	    {
	    	Iterator<String> docIt=solrdoc.getFieldNames().iterator();
	    	builder.append("doc:\n");
	    	while(docIt.hasNext())
	    	{   String filedName=docIt.next().toString();
	    		builder.append(filedName+":"+solrdoc.getFieldValue(filedName)+"\n\r");
	    	}
	    	
	    }*/
    	//Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
    	return list;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String searchByUrl(String keyword) throws IOException
	{
		String url=this.url+SELECT;
		String command = "fl=*,score&start=0&rows=10&hl=true&hl.fl=*&q="+URLEncoder.encode(keyword, "UTF-8");  
		String result = HttpClientUtils.sendGetCommand(command, url);  
	    //result = escape(result);  
		return result;
	}

	
}
