import java.io.*;

import org.htmlparser.*;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;  
import java.net.URISyntaxException;  
import java.net.URL;
import java.util.*;  
import java.util.regex.Matcher;
import java.util.regex.Pattern;
  






import org.apache.http.*;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.utils.URIUtils;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;
public class SecondSchool {
	
	public static void main(String[] args) throws Exception
	{
		AstonGetDetails();
	}
	
	public static void AstonGetDetails() throws Exception
	{
		HttpClient httpclient = new DefaultHttpClient();
		String url="http://www.aston.ac.uk/study/undergraduate/courses/school/aston-business-school/accounting-for-management/";
		HttpGet httpGet = new HttpGet(url); 
		HttpResponse response = httpclient.execute(httpGet);  
		HttpEntity entity = response.getEntity();
		String htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity);
		    //System.out.println(htmls);
		    
		     
		}
		Parser parser=Parser.createParser(htmls, "utf-8");
	    HtmlPage page=new HtmlPage(parser); 
	    
	    AndFilter filter =
                new AndFilter(
                              new TagNameFilter("div"),
                             new HasAttributeFilter("class","tab-body-inner"));
        NodeList nodes = parser.extractAllNodesThatMatch(filter);

        //title
        //<h1 id="skiplinks">Business, Management and Public Policy </h1>

        //i=0,Entry Requirements
        //i=1,Course Outline
	    for(int i=0;i<nodes.size();i++)
	    {
	    	
	    	Node node=(Node)nodes.elementAt(i);
            System.out.println(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
	    	//System.out.println(node.toHtml());
	    	
	    }
	    System.out.println(nodes.size());
		
		
	}
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	
	//Aston University Pattern
		public static void AstonGetLinks() throws Exception
		{

			FileInputStream in=new FileInputStream(new File("Undergraduate courses.html"));
			byte[] data=new byte[100000];
			in.read(data);
			
			//System.out.println(new String(data));
			String htmls=new String(data);
			Parser parser=Parser.createParser(htmls, "utf-8");
		    HtmlPage page=new HtmlPage(parser); 
		    
		    AndFilter filter =
	                new AndFilter(
	                              new TagNameFilter("div"),
	                             new HasAttributeFilter("data-href"));
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
		    //System.out.println("nodelist.size();"+nodes.size());
		    //ImageTag imageTag = null;
		    for(int i=0;i<nodes.size();i++)
		    {
		    	
		    	Node node=(Node)nodes.elementAt(i);
	              //  System.out.println( node.toHtml());            
		    	TagNode tagNode = new TagNode();//通过TagNode获得属性，只有将Node转换为TagNode才能获取某一个标签的属性
	            tagNode.setText(node.toHtml());
	            System.out.println(tagNode.getAttribute("data-href"));
		    	
		    	
		    }
			
			in.close();
		}

}
