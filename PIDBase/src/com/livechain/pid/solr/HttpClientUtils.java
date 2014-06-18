package com.livechain.pid.solr;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class HttpClientUtils {

	/**
	 * Send the command to Solr using a Post
	 * 
	 * @param command
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String sendPostCommand(String command, String url) throws IOException {
		System.out.println("solr url post :"+url);
		String results = null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);

		RequestEntity re = new StringRequestEntity(command, "text/xml", "UTF-8");
		post.setRequestEntity(re);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(post);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + post.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = post.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			results = new String(responseBody);
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			post.releaseConnection();
		}
		return results;
	}

	/**
	 * Send the command to Solr using a GET
	 * 
	 * @param queryString
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String sendGetCommand(String queryString, String url) throws IOException {
		String results = null;
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		get.addRequestHeader("Content-Type", "text/html; charset=UTF-8");  
		get.setQueryString(queryString.trim());

		client.executeMethod(get);
		try {
			// Execute the method.
			int statusCode = client.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + get.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			results = new String(responseBody,"UTF-8");
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
		return results;
	}

}
