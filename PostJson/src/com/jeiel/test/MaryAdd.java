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

public class MaryAdd {
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
			for(;index<=259;){
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
	    connection.setRequestProperty("Cookie", "__utma=255880599.985860591.1440983762.1440983762.1440983762.1; __utmz=255880599.1440983762.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); connect.sid=s%3AIdozs4s7UN9nU_JXTP-4INvvJmmmtMuV.21Ks8IOVjwi%2BiLDhee1U0C4GLE1yUj05PooVObvilIM; CNZZDATA1256122972=1789449401-1440137143-%7C1441764565");
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
			
		    //�̶�ֵ
		    JSONObject entry=new JSONObject();
		    entry.put("target", "course");
		    entry.put("action", "add");
		    
		    //�Զ���ֵ
		    
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
		    	//System.out.println(e.getKey()+":"+e.getValue());
		    	scholarshipList.add(scholarshipItem);
		    }

		   	if(scholarshipList.size()>0)
		   		course.put("scholarship", scholarshipList);
		   	
		   	JSONObject value=new JSONObject();
		    value.put("university", "MARY");
		    value.put("course", course);
		   	entry.put("value", value);
		   	System.out.println(entry.toString());
		    out.write(entry.toString().getBytes("utf8"));
		    out.flush();
		    
		    //��ȡ��Ӧ

		    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String lines;
		    StringBuffer sb = new StringBuffer("");
		    while ((lines = reader.readLine()) != null) {
		    	lines = new String(lines.getBytes(), "utf-8");
		    	sb.append(lines);
		    }
		    
		    System.out.println(sb.toString());
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
	public static String[] MaryPostScho={
		"Barts and The London School of Medicine and Dentistry;Canon Collins Scholarship funded by Simon and Deirdre Gaskell scholarships;100%;Commonwealth Shared Scholarships;100%;Chevening Scholarships;100%;Santander Masters Scholarship;3000",
		"Barts Cancer Institute;Canon Collins Scholarship funded by Simon and Deirdre Gaskell scholarships;100%;Commonwealth Shared Scholarships;100%;Chevening Scholarships;100%;Santander Masters Scholarship;3000",
		"Institute of Dentistry;Canon Collins Scholarship funded by Simon and Deirdre Gaskell scholarships;100%;Commonwealth Shared Scholarships;100%;Chevening Scholarships;100%;Santander Masters Scholarship;3000",

		"School of Biological and Chemical Sciences;Science and Engineering taught scholarships;1500;International Science and Engineering Excellence Awards;2500;Chevening Scholarships;1500",
		"School of Business and Management;Commonwealth Shared Scholarships;100%;Chevening Scholarships;100%",
		"School of Economics and Finance;School of Economics and Finance Postgraduate scholarships;5000",
		"School of Electronic Engineering and Computer Science;Science and Engineering taught scholarships;1500;International Science and Engineering Excellence Awards;2500;Chevening Scholarships;100%;Santander Masters Scholarship;3000",
		"School of Engineering and Materials Science;Science and Engineering taught scholarships;1500;International Science and Engineering Excellence Awards;2500",
		"School of English and Drama;Chevening Scholarships;1500",
		"School of Geography;Chevening Scholarships;1500;Worshipful Company of Water Conservators bursaries*;2500;QMUL Bursary;1000;School of Geography bursary*;1000",
		"School of History;Chevening Scholarships;1500",
		"School of Languages, Linguistics and Film;Chevening Scholarships;1500",
		"School of Law;Law scholarships;100%",
		"School of Mathematical Sciences;MSc Mathematical Finance and MSc Financial Computing Support Scheme 2015;10000;Mathematical Sciences Excellence Award;5000;International Mathematical Sciences Excellence Award;5000;International Science and Engineering Excellence Awards;2500;MSc Mathematical Finance Academic Excellence Scholarship;5000;MSc Financial Computing Academic Excellence Scholarship;5000;The School of Mathematical Sciences Alumni Scholarship;20%;Chevening Scholarships;1500",
		"School of Physics and Astronomy;Science and Engineering taught scholarships;1500;International Science and Engineering Excellence Awards;2500;MSc Mathematical Finance Academic Excellence Scholarship;10000;Chevening Scholarships;1500",
		"School of Politics and International Relations;Chevening Scholarships;1500"
	};//Institute of Dentistry

	public static String[] MaryUnScho={
		"Barts Cancer Institute;18000;QMUL Global Health International Scholarship",
		"Institute of Dentistry;18000;QMUL Global Health International Scholarship",
		"School of Biological and Chemical Sciences;5000;International Science and Engineering Undergraduate Excellence Awards ",
		"School of Economics and Finance;3000;School of Economics and Finance Undergraduate scholarships",
		"School of Electronic Engineering and Computer Science;5000;International Science and Engineering Undergraduate Excellence Awards ",
		"School of Engineering and Materials Science;5000;International Science and Engineering Undergraduate Excellence Awards ",
		"School of Mathematical Sciences;2250;International Science and Engineering Undergraduate Excellence Awards ",
		"School of Physics and Astronomy;5000;International Science and Engineering Undergraduate Excellence Awards "

	};

}
