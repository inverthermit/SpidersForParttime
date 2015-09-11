import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;


public class getTiebaFirstPageUrls {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		HttpGet httpGet = new HttpGet("http://tieba.baidu.com/f?kw=%E5%93%88%E5%B0%94%E6%BB%A8%E5%B7%A5%E4%B8%9A%E5%A4%A7%E5%AD%A6&ie=utf-8");
		HttpResponse response = httpclient.execute(httpGet); 
		HttpEntity entity = response.getEntity();  
		if (entity != null) { 
		    String htmls=EntityUtils.toString(entity);
		    //System.out.println(htmls);
		    getMainPages(htmls);
		}   
		httpclient.close();
	}
	public static void getMainPages(String htmls)throws Exception
	{
		Parser parser=Parser.createParser(htmls, "GBK");
	    HtmlPage page=new HtmlPage(parser);
	    try{
	    	parser.visitAllNodesWith(page);
	    }
	    catch(Exception ee)
	    {
	    	System.out.println(ee);
	    }
	    //filter
	    NodeList nodelist=page.getBody();
	    NodeFilter filter=new TagNameFilter("a");
	    nodelist=nodelist.extractAllNodesThatMatch(filter,true);
	    String keywords="/p/";
	    for(int i=0;i<nodelist.size();i++)
	    {
	    	
	    	LinkTag link=(LinkTag)nodelist.elementAt(i);
	    	String get1=link.getAttribute("href");
	    	//System.out.println(get1);
	    	if(get1!=null&&get1.length()>keywords.length())
	    	{
	        	if(get1.substring(0, 3).equals(keywords))
	        	{
	        		if(!(get1.equals("/p/4014659268")||get1.equals("/p/4028406592")||get1.equals("/p/3277209778")||get1.equals("/p/4016997349")))
	        		System.out.println("http://tieba.baidu.com"+get1);
	        		//break;
	        	}
	    	}
	    	
	    	
	    	
	    }
	}

}
