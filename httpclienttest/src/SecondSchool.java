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
		String url="http://www.aston.ac.uk/study/undergraduate/courses/school/eas/beng-design-engineering/";
		HttpGet httpGet = new HttpGet(url); 
		HttpResponse response = httpclient.execute(httpGet);  
		HttpEntity entity = response.getEntity();
		String htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity).replace("\t", " ");
		    //System.out.println(htmls);
		    
		     
		}
		Parser parser=null;
	    HtmlPage page=new HtmlPage(parser); 
	    
	    
	    //**************************get name*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("h1"),
                new HasAttributeFilter("id","skiplinks"));//id="skiplinks"
        NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
        for(int i=0;i<nodes2.size();i++)
	    {
	    	
	    	Node node=(Node)nodes2.elementAt(i);
	    	System.out.println("Title:"+html2Str(node.toHtml()));
	    	System.out.println("Type:"+GetType(html2Str(node.toHtml())));
	    	break;
	    }
        
      //**************************get school*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter SchoolFilter=new AndFilter(new TagNameFilter("span"),
                new HasAttributeFilter("class","breadcrumb5"));
        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
        if(nodes3.size()>0)
        {
        	for(int i=0;i<nodes3.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes3.elementAt(i);
    	    	if(!html2Str(node.toHtml()).trim().equals(""))
    	    	System.out.println(html2Str(node.toHtml()).trim());
    	    	
    	    }
        }
        else
        {
        	/*
        	SchoolFilter=new AndFilter(new TagNameFilter("span"),
                    new HasAttributeFilter("class","breadcrumb4"));
            nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
            for(int i=0;i<nodes3.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes3.elementAt(i);
    	    	System.out.println(html2Str(node.toHtml()));
    	    	
    	    }
    	    */
        }
        
        
        
        
        
        
     
		
	    
	    //**************************get Titles*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter filter =
                new AndFilter(
                              new TagNameFilter("a"),
                             new HasAttributeFilter("class","panel-event"));
	    NodeList nodes = parser.extractAllNodesThatMatch(filter);        
        //i=0,Entry Requirements
        //i=1,Course Outline
        int index=0;
        int RequireIndex=-1,CourseIndex=-1;
	    for(int i=0;i<nodes.size();i++)
	    {
	    	
	    	Node node=(Node)nodes.elementAt(i);
	    	String tmp=html2Str(node.toHtml());
	    	if(!tmp.equals("Expand / Collapse"))
	    	{
	    		if(tmp.contains("Entry Requirements"))
	    			RequireIndex=index;
	    		else if(tmp.contains("Course Outline")||tmp.contains("Course outline"))
	    			CourseIndex=index;
	    		
	    		index++;
	    	}
	    	
	    	
	    }
	    
	    //**************************get entry and structure*************************
        parser=Parser.createParser(htmls, "utf-8");
	    filter =
                new AndFilter(
                              new TagNameFilter("div"),
                             new HasAttributeFilter("class","tab-body-inner"));
        nodes = parser.extractAllNodesThatMatch(filter);        
        //i=0,Entry Requirements
        //i=1,Course Outline
	    /*for(int i=0;i<nodes.size();i++)
	    {
	    	
	    	Node node=(Node)nodes.elementAt(i);
	    	if(i==0) System.out.println("Entry Requirements and Tuition Fees:");
	    	else if(i==1) System.out.println("Structure:");
	    	else break;
            System.out.println(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
	    	//System.out.println(node.toHtml());
	    	
	    }
	    System.out.println(nodes.size());*/
        Node node=(Node)nodes.elementAt(RequireIndex);
        System.out.println("Entry Requirements and Tuition Fees:\n");
        System.out.println(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
        node=(Node)nodes.elementAt(CourseIndex);
        System.out.println("Structure:\n");
        System.out.println(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
	    
	}
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	public static String GetType(String input)//BA BEng Bsc Msc MEng 
	{
		String types="BA;BEng;Bsc;MSc;MEng;Double MA;Joint MA;MA";
		String[] array=types.split(";");
		for(int i=0;i<array.length;i++)
		{
			if(input.contains(array[i]))
			{
				return array[i];
			}
		}
		return null;
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
