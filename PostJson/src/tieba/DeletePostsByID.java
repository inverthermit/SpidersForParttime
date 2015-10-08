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

public class DeletePostsByID {
	private static String postUrl = "http://tieba.baidu.com/f/commit/post/delete";
	private static int index=1;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String tid="4033906702";
		String pid="75571153471";
		delete(postUrl,tid,pid);
	   
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
	    connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
	    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	    //connection.setRequestProperty("Referer", "http://myoffer.cn/external/course");
	    connection.setRequestProperty("Cookie", "BAIDUID=5336A00175104DE0A9D0DA94ABB14F1B:FG=1; BIDUPSID=5336A00175104DE0A9D0DA94ABB14F1B; PSTM=1438066940; TIEBA_USERTYPE=18658700446b158f2ed81674; bdshare_firstime=1438067977062; BDUSS=S11YlZic21CZXRTY0RteTJzYlhaNnVaYVlkZm5LWllrd3NhRlA1VlRzb3IydUpWQVFBQUFBJCQAAAAAAAAAAAEAAADpViUxtbnBotL-yr8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACtNu1UrTbtVe; TIEBAUID=f034d094f88b848bedf0a17e; GET_TOPIC=824530665; BDRCVFR[VjobkFsAYtR]=mbxnW11j9Dfmh7GuZR8mvqV; H_PS_PSSID=16483_1449_16988_17154_12657_12826_14430_12867_16938_17000_17004_17072_15509_11946_13932_16096_17051; frs_header_barrage_head_close=1; wise_device=0; LONGID=824530665");	    
	    connection.setRequestProperty("Connection", "keep-alive");
	    connection.setRequestProperty("Pragma", "no-cache");
	    connection.setRequestProperty("Cache-Control", "no-cache");
	    connection.connect();
	    return connection;
	}
	
	public static void delete(String postUrl,String tid,String pid) {
	    try{
	    	
	    	HttpURLConnection connection=getConnection(postUrl);
			DataOutputStream out= new DataOutputStream(connection.getOutputStream());
			
		    //固定值
		    JSONObject entry=new JSONObject();
		    entry.put("commit_fr", "pb");
		    entry.put("ie", "utf-8");
		    entry.put("tbs", "d316085121f93c631441951687");
		    entry.put("kw", "哈尔滨工业大学");
		    entry.put("fid", "35522");
		    entry.put("tid", tid);
		    entry.put("pid", pid);
		    entry.put("is_vipdel", "0");
		    entry.put("is_finf", "false");
		    
		    
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
	    }catch(Exception e){
	    	e.printStackTrace();
	    	
	    }
		
	}
	
}
