package com.jeiel.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class Delete {
	private static String postUrl = "http://myoffer.cn/external/api/courses";
	private static int index=1;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		for(;index<=10;){//index为网页上显示的id号
			
			delete(postUrl,index);
		}
	   
	}
	
	public static HttpURLConnection getConnection(String postUrl) throws IOException{
		URL url = new URL(postUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoOutput(true);
	    connection.setDoInput(true);
	    connection.setRequestMethod("POST");
	    connection.setUseCaches(false);
	    connection.setInstanceFollowRedirects(true);
	    //connection.setRequestProperty("Host", "myoffer.cn");
	    //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:40.0) Gecko/20100101 Firefox/40.0");
	    connection.setRequestProperty("Accept", "application/json, text/plain, */*");
	    connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
	    connection.setRequestProperty("Referer", "http://myoffer.cn/external/course");
	    connection.setRequestProperty("Cookie", "__utma=255880599.985860591.1440983762.1440983762.1440983762.1; __utmz=255880599.1440983762.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); connect.sid=s%3AIdozs4s7UN9nU_JXTP-4INvvJmmmtMuV.21Ks8IOVjwi%2BiLDhee1U0C4GLE1yUj05PooVObvilIM; CNZZDATA1256122972=1789449401-1440137143-%7C1441764565");
	    connection.setRequestProperty("Connection", "keep-alive");
	    connection.setRequestProperty("Pragma", "no-cache");
	    connection.setRequestProperty("Cache-Control", "no-cache");
	    connection.connect();
	    return connection;
	}
	
	public static void delete(String postUrl,int id) {
	    try{
	    	System.out.println("Delete "+index);
	    	HttpURLConnection connection=getConnection(postUrl);
			DataOutputStream out= new DataOutputStream(connection.getOutputStream());
			
		    //固定值
		    JSONObject entry=new JSONObject();
		    entry.put("target", "course");
		    entry.put("action", "remove");
		    
		    //自定义值
		   	JSONObject value=new JSONObject();
		    value.put("university", "saos");
		    value.put("id", id);
		   	entry.put("value", value);
		    
		    out.writeBytes(entry.toString());
		    out.flush();
		    
		    //读取响应

		    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String lines;
		    StringBuffer sb = new StringBuffer("");
		    while ((lines = reader.readLine()) != null) {
		    	lines = new String(lines.getBytes(), "utf-8");
		    	sb.append(lines);
		    }
		    
		    System.out.println(sb);

		    out.close();
		    connection.disconnect();
		    reader.close();
		    System.out.println("Deleted");
		    index++;
	    }catch(Exception e){
	    	System.out.println("Terminated at "+index);
	    	System.out.println("Restart at "+index);
	    }
		
	}
	
}
