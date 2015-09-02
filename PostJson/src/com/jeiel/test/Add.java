package com.jeiel.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

public class Add {
	private static String postUrl = "http://myoffer.cn/external/api/courses";

	private static int index=1;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try {
			List<Major> list=POIReadAndPost.getData(null);
			for(;index<=425;){
				add(postUrl,list.get(index-1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   
	}
	
	
	public static HttpURLConnection getConnection(String postUrl) throws Exception {
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
	    connection.setRequestProperty("Cookie", "CNZZDATA1256122972=436580706-1440482499-http%253A%252F%252Fmyoffer.cn%252F%7C1441087693; connect.sid=s%3AkmPA2lJJjDsGR4Ag60QDFLl21VbxrP7_.oEtXldNCyVcbQsodvVe%2FsCXE7X%2BEJ7zxr3pxcZgmFlQ");
	    connection.setRequestProperty("Connection", "keep-alive");
	    connection.setRequestProperty("Pragma", "no-cache");
	    connection.setRequestProperty("Cache-Control", "no-cache");
	    connection.connect();
	    return connection;
	}
	
	public static void add(String postUrl,Major major){
	    
		
		try {
			System.out.println("Add "+index);
			HttpURLConnection connection = getConnection(postUrl);
			DataOutputStream out= new DataOutputStream(connection.getOutputStream());
			
		    //固定值
		    JSONObject entry=new JSONObject();
		    entry.put("target", "course");
		    entry.put("action", "add");
		    
		    //自定义值
		    
		    JSONObject course=new JSONObject();
		    course.put("school", major.getSchool());
		    course.put("level", major.getLevel());
		    course.put("title", major.getTitle());
		    course.put("type", major.getType());
		    course.put("application", major.getApplicationFee().trim().replace(",", ""));
		    course.put("tuition", major.getTuitionFee().trim().replace(",", ""));
		    course.put("academic", major.getAcademicRequirements());
		    course.put("ielts_avg", major.getIELTS_Avg().trim());
		    course.put("ielts_low", major.getIELTS_Low().trim());

		    /*course.put("ielts_low_l", "");
		    course.put("ielts_low_s", 1);
		    course.put("ielts_low_r", 1);
		    course.put("ielts_low_w", 1);*/
		    
		    LinkedHashMap<String, String> structureMap=major.getStructure();
		    JSONObject structureItem;
		    List<JSONObject> structureList=new ArrayList<JSONObject>();
		    for(Map.Entry<String, String>e:structureMap.entrySet()){
		    	structureItem=new JSONObject();
	    		structureItem.put("category", e.getKey());
		    	structureItem.put("summary", e.getValue());
		    	structureList.add(structureItem);

		    }
		    
		    if(structureList.size()>0)
		    	course.put("structure", structureList);
		    
		    course.put("length", major.getLength());
		    course.put("month", major.getMonthOfEntry());

		    
		    JSONObject scholarshipItem;
		    List<JSONObject> scholarshipList=new ArrayList<JSONObject>();
		    for(Map.Entry<String, String>e:major.getScholarship().entrySet()){
		    	scholarshipItem=new JSONObject();
		    	scholarshipItem.put("name", e.getKey());
		    	scholarshipItem.put("value", e.getValue());
		    	scholarshipList.add(scholarshipItem);
		    }

		   	if(scholarshipList.size()>0)
		   		course.put("scholarship", scholarshipList);
		   	
		   	JSONObject value=new JSONObject();
		    value.put("university", "BELFAST");
		    value.put("course", course);
		   	entry.put("value", value);
		   	System.out.println(entry.toString());
		    out.write(entry.toString().getBytes("utf8"));
		    out.flush();
		    
		    //读取响应

		    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String lines;
		    StringBuffer sb = new StringBuffer("");
		    while ((lines = reader.readLine()) != null) {
		    	lines = new String(lines.getBytes(), "utf-8");
		    	sb.append(lines);
		    }
		    
		    
		    System.out.println("get return");
		    
		    out.close();
		    connection.disconnect();
		    reader.close();
		    System.out.println("Added");
		    index++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.out.println("Terminated at "+index);
	    	System.out.println("Restart at "+index);
		}
		
	}
	public static void write(String a) throws IOException{
		File file=new File("d://test.txt");
		if(!file.exists())file.createNewFile();
		FileOutputStream fos=new FileOutputStream(file);
		fos.write(a.getBytes());
		fos.close();
	}

}
