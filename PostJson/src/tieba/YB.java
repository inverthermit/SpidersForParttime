package tieba;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class YB {
	private static String postUrl = "http://113.31.82.196/center/maibo/qikanzuixin.asp?abc=oXxXqV9YoXyWoX&def=sQoOpOqNeRoPtRiNmOpM8OuMxNmQtPuOoOpQ&vidd=5&keyy=TYUGUIYUI&F_F=13";
	private static int index=1;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		getConnection(postUrl);
		
	   
	}
	
	public static HttpURLConnection getConnection(String postUrl) throws IOException{
		URL url = new URL(postUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoOutput(true);
	    connection.setDoInput(true);
	    connection.setRequestMethod("GET");
	    connection.setUseCaches(false);
	    connection.setInstanceFollowRedirects(true);
	    //connection.setRequestProperty("Host", "myoffer.cn");
	    //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:40.0) Gecko/20100101 Firefox/40.0");
	    connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
	    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	    //connection.setRequestProperty("Referer", "http://myoffer.cn/external/course");
	    connection.setRequestProperty("Cookie", "c=; c=; MBcheckbox=on; MBsysemail=daoliyinshi%40163%2Ecom; MBsysname=szsesxs; MBsyspermission=0; MBsyspwd=c3hzc3pzZQ%3D+%3D+; ASPSESSIONIDCSDSADBQ=GICMLAPAOEFJNOACIKJGMPLE; ASPSESSIONIDCSDTCABS=DDNLHHPAFIEJPBDKHFMDKDBE");
	    connection.setRequestProperty("Connection", "keep-alive");
	    connection.setRequestProperty("Pragma", "no-cache");
	    connection.setRequestProperty("Cache-Control", "no-cache");
	    connection.connect();
	    byte[] result=new byte[2048];
	    		connection.getInputStream().read(result);
	    System.out.println(new String(result));
	    return connection;
	}
	
	public static void delete(String postUrl,String tid,String pid) {
	    try{
	    	
	    	HttpURLConnection connection=getConnection(postUrl);
			DataOutputStream out= new DataOutputStream(connection.getOutputStream());
			
		    //�̶�ֵ
		    JSONObject entry=new JSONObject();
		    entry.put("commit_fr", "pb");
		    entry.put("ie", "utf-8");
		    entry.put("tbs", "d316085121f93c631441951687");
		    entry.put("kw", "��������ҵ��ѧ");
		    entry.put("fid", "35522");
		    entry.put("tid", tid);
		    entry.put("pid", pid);
		    entry.put("is_vipdel", "0");
		    entry.put("is_finf", "false");
		    
		    
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
	    }catch(Exception e){
	    	e.printStackTrace();
	    	
	    }
		
	}
	
}
