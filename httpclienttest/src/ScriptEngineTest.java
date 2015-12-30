import java.io.FileReader; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;

 import javax.script.ScriptEngine;

import javax.script.ScriptEngineManager;  

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
 /**  * Java调用并执行js文件，传递参数，并活动返回值  *   * @author Tim Luo  */ 

 	public class ScriptEngineTest {    

 		public static int MAX_THREAD=60;
 		public static int proxy_index=0;
 		public static ArrayList<String> list=null;
 		public static ArrayList<String> AvailableProxyList=new ArrayList<String>();
	public static void main(String[] args) throws Exception {   
	 
		list=getProxies();
		System.out.println(list.size());
		
		
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int tIndex=0;tIndex<MAX_THREAD;tIndex++)
		{
			pool.execute(
			new Runnable(){
				public void run()
				{
					while(true)
					{
						String data= getUnhandledProxy();
						if(data!=null)
						{
							if(availableProxy(data))
							{
								addAvailable(data);
							}
						}
						else{
							//System.out.println("No Unhandled");
							break;
						}
					}
					
					
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(600, TimeUnit.SECONDS);

		System.out.println(AvailableProxyList.size());
	}
	
	public static synchronized String getUnhandledProxy()
	{
		String result=null;
		if(proxy_index<list.size())
		{
			result=list.get(proxy_index);
			System.out.println(proxy_index+" checking...");
			proxy_index++;
		}
		return result;
	}
	
	public static synchronized void addAvailable(String data)
	{
		AvailableProxyList.add(data);
	}
	
	public static ArrayList<String> getProxies() throws Exception
	{
		
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		HttpGet httpGet = new HttpGet("http://www.site-digger.com/html/articles/20110516/proxieslist.html");
		HttpResponse response = httpclient.execute(httpGet); 
		HttpEntity entity = response.getEntity(); 
		String htmls="";
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity);
		    //System.out.println(htmls);
		}   
		httpclient.close();
		
		Pattern p = Pattern.compile("var baidu_union_id = \"[\\d\\w]*\";");
    	Matcher m = p.matcher(htmls);
    	String union_id="";
    	while (m.find()) 
    	{
    		//System.out.println(m.group());
    		union_id=m.group().replace("var baidu_union_id = \"", "").replace("\";", "");
    		//System.out.println(union_id);
    		break;
    	}
    	
    	p = Pattern.compile("decrypt\\(\"[\\s\\S]*?\"");
    	m = p.matcher(htmls);
    	ArrayList<String> proxies=new ArrayList<String>();
    	while (m.find())  
    	{
    		//System.out.println(m.group());
    		String code=m.group().replace("decrypt(\"", "").replace("\"", "");
    		proxies.add(decrypt(code,union_id));
    		
    	}
    	/*for(int i=0;i<proxies.size();i++)
    	{
    		System.out.println(proxies.get(i));
    	}*/
    	return proxies;
	}
	
	public static boolean availableProxy(String url)
	{
		boolean flag=false;
			try{
				HttpHost proxy = new HttpHost(url.split(":")[0], Integer.parseInt(url.split(":")[1]));
				DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();  
				CloseableHttpClient httpclient = HttpClients.custom()
						.setRoutePlanner(routePlanner).setDefaultRequestConfig(requestConfig).build();  
				
				//httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
				HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
				HttpResponse response = httpclient.execute(httpGet); 
				HttpEntity entity = response.getEntity();  
				if (entity != null) { 
				    String htmls=EntityUtils.toString(entity);
				    if(htmls.contains(url.split(":")[0]))
				    {
				    	flag=true;
				    }
				    //getMainPages(htmls);
				}   
				httpclient.close();
			}
			catch(Exception ee)
			{
				//ee.printStackTrace();
			}
			return flag;
		
	}
	
	
	public static String decrypt(String code,String baidu_union) throws Exception
	{
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");     
		
		String jsFileName = "./js/aes.js";   // 读取js文件   
		
		FileReader reader = new FileReader(jsFileName);   // 执行指定脚本   
		engine.eval(reader);   
		String result="";
		if(engine instanceof Invocable) {    
		Invocable invoke = (Invocable)engine;    // 调用merge方法，并传入两个参数    
		
		// c = merge(2, 3);    
		//decrypt("ifau6NhMumgrkY2s5zWPjVAWIZWMaCAuADiEgNcOTSg=")
		
		//var baidu_union_id = "ca2922c6a94211e5";
		//"var baidu_union_id = "...";"
		//result = (String)invoke.invokeFunction("decrypt", "Oh2q1x5yqG+uAeXtBoroYqzcZeuNQ7QDILpV/FmiBmY=","ca2922c6a94211e5");    
		result = (String)invoke.invokeFunction("decrypt", code,baidu_union);    
		
		//System.out.println(result);   
		}   
		
		reader.close(); 
		return result;
	}

}