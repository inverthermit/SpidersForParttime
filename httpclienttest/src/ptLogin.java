import java.net.URLDecoder;
import java.util.*;
import java.io.*;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;


public class ptLogin {

	/**
	 * @param args
	 */
	public static String FILEPATH="d://torrents/";
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("username", "ladimeya"));
		formparams.add(new BasicNameValuePair("password", "ladimeyaladimeya"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		HttpPost httppost = new HttpPost("http://pt.hit.edu.cn/takelogin.php");
		httppost.setEntity(entity);
		HttpResponse response1=httpclient.execute(httppost); 
		if(!response1.getEntity().toString().contains("ladimeya"))
		{
			System.out.println("Login failed.");
			httpclient.close();
			return;
		}
		
		//http://pt.hit.edu.cn/torrents.php?cat=402
		HttpGet httpGet2 = new HttpGet("http://pt.hit.edu.cn/torrents.php?cat=402");
		HttpResponse response2 = httpclient.execute(httpGet2); 
		HttpEntity entity2 = response2.getEntity(); 
		String htmls="";
		if (entity2 != null) { 
		    htmls=EntityUtils.toString(entity2);
		    //System.out.println(htmls);
		}   
		else
		{
			System.out.println("Get list failed!");
			httpclient.close();
			return;
		}
		
		

		HttpGet httpGet = new HttpGet("http://pt.hit.edu.cn/download.php?id=47250"); 
		HttpResponse response = httpclient.execute(httpGet);  
		HttpEntity entity1 = response.getEntity();  
		for(int i=0;i<response.getAllHeaders().length;i++)
		{
			Header h=response.getAllHeaders()[i];
			if(h.getName().equals("Content-Disposition"))
			{
				//System.out.println(i);
				//System.out.println(URLDecoder.decode(h.getValue(), "UTF-8"));
				String fileName = URLDecoder.decode(h.getValue().substring(h.getValue().indexOf("filename=")+9),"UTF-8");
				System.out.println(fileName);
				InputStream is = entity1.getContent();  
	            File file = new File(FILEPATH+fileName);  
	            file.getParentFile().mkdirs();  
	            FileOutputStream fileout = new FileOutputStream(file);  


	            byte[] buffer=new byte[1024];
	            int ch = 0;  
	            while ((ch = is.read(buffer)) != -1) {  
	                fileout.write(buffer,0,ch);  
	            }  
	            is.close();  
	            fileout.flush();  
	            fileout.close();   
				break;
			}
			
			
		}
		
		
		System.out.println("Got reply!");
		//htmls=HTMLFilter(htmls);
		httpclient.close();
	}
	
	public static ArrayList<String> getList(String htmls) throws Exception{
		ArrayList<String> result=new ArrayList<String>();
		Parser	parser=Parser.createParser(htmls, "utf-8");
   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
                   new HasAttributeFilter("target","_blank"));
   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
   	    for(int i=0;i<nodes4.size();i++)
   	    {
   	    	LinkTag link=(LinkTag)nodes4.elementAt(i);
   	    	if(!link.getAttribute("href").equals("#"))
   	    	{
   	    		//System.out.println("{\""+count+"\",\"https://handbook.unimelb.edu.au"+link.getAttribute("href")+"\",\""+html2Str(link.toHtml().replaceAll("<span[\\s\\S]*/span>",""))+"\",\"0\"},");
                //count++;
   	    	}
   	    	
   	    }
		return result;
	}

}
