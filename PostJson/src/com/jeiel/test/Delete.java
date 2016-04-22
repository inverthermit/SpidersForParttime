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
	private static String postUrl = "http://www.myoffer.cn/external/api/courses";
	private static int index=1;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		for(;index<=32;){//indexΪ��ҳ����ʾ��id��
			
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
	    connection.setRequestProperty("Cookie", "tencentSig=5528000512; tencentSig=5651228672; CNZZDATA1256122972=1789449401-1440137143-%7C1455843882; Hm_lvt_7b2d81bba29516af3254cc73cbff78b1=1455844584,1456215195,1456276806,1456396357; Hm_lvt_f2d08716d77a6692d1510d26ea9b72d1=1455844584,1456215195,1456276806,1456396357; connect.sid=s%3AZu0rvE8B5ekRZRE0_X1n2dZGlfZR15H7.8uQmKmSG4t%2Fq6%2FOwHY6I2ysrQrJ0vvy2MP8xDKmdroQ; __utmt_UA-72589077-1=1; RTP=/university/7380.html; RTPA=; __utma=255880599.985860591.1440983762.1461054816.1461293394.9; __utmb=255880599.6.10.1461293394; __utmc=255880599; __utmz=255880599.1440983762.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); Hm_lvt_9be5940c4751a630c3c5c971cc9d090d=1460336078,1461054816,1461293394; Hm_lpvt_9be5940c4751a630c3c5c971cc9d090d=1461293466; fromBD=false");
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
			
		    //�̶�ֵ
		    JSONObject entry=new JSONObject();
		    entry.put("target", "course");
		    entry.put("action", "remove");
		    
		    //�Զ���ֵ
		   	JSONObject value=new JSONObject();
		    value.put("university", "saos");
		    value.put("id", id);
		   	entry.put("value", value);
		    
		    out.writeBytes(entry.toString());
		    out.flush();
		    
		    //��ȡ��Ӧ

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
