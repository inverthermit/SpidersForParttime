package birmingham;

import java.io.File;
import java.io.FileInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class getURL {

	/**
	 * @param args
	 */
	public static String url="http://www.birmingham.ac.uk/students/courses/index.aspx?SearchFilter=Undergraduate&CourseComplete_searchlisting_goto=";
	public static void main(String[] args) throws Exception{
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		for(int i=1;i<=35;i++)
		{
			int index=i;
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url+index); 
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			Parser parser=Parser.createParser(htmls, "utf-8");
		    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("table"),
	                new HasAttributeFilter("class","sys_uob-listing"));
	        NodeList nodes1 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
	        parser=Parser.createParser(nodes1.elementAt(0).toHtml(), "utf-8");
	        TagNameFilter TF=new TagNameFilter("tr");
	        NodeList nodes2 = parser.extractAllNodesThatMatch(TF);
	        //System.out.println("Size:"+nodes2.size());
	        if(nodes2.size()>0)
	        {
	        	for(int j=1;j<nodes2.size();j++)
	        	{
	        		
	        		//***************get links;name;type;time
	        		Node node=(Node)nodes2.elementAt(j);
	        		//System.out.println(node.toHtml());
	        		AndFilter linkFilter=new AndFilter(new TagNameFilter("a"),
	                        new HasAttributeFilter("title"));
	        		parser=Parser.createParser(node.toHtml(), "utf-8");
	                NodeList nodes3 = parser.extractAllNodesThatMatch(linkFilter);
	                //System.out.println(nodes3.size());
	                Node aNode=nodes3.elementAt(0);
	                //System.out.println(aNode.toHtml());
	                LinkTag l=(LinkTag)aNode;
	                System.out.print("{\"http://www.birmingham.ac.uk"+l.getAttribute("href")+"\",\""+html2Str(aNode.toHtml().replace(" <br />", "\",\"")).trim()+"\",\"");
	                //html2Str(aNode.toHtml().replace("<br />", ";"));
	                
	                
	               //***************get duration
	                
	                String Duration=html2Str(node.toHtml());
	                if(Duration.contains("1 year"))
	        		{
	        			System.out.print(12);
	        		}
	        		else if(Duration.contains("2 year"))
	        		{
	        			System.out.print(24);
	        		}
	        		else if(Duration.contains("3 year"))
	        		{
	        			System.out.print(36);
	        		}
	        		else if(Duration.contains("4 year"))
	        		{
	        			System.out.print(48);
	        		}
	        		else if(Duration.contains("5 year"))
	        		{
	        			System.out.print(60);
	        		}
	        		else if(Duration.contains("1")&&Duration.contains("year"))
	        		{
	        			System.out.print(12);
	        		}
	        		else if(Duration.contains("2")&&Duration.contains("year"))
	        		{
	        			System.out.print(24);
	        		}
	        		else if(Duration.contains("3")&&Duration.contains("year"))
	        		{
	        			System.out.print(36);
	        		}
	        		else if(Duration.contains("4")&&Duration.contains("year"))
	        		{
	        			System.out.print(48);
	        		}
	        		else if(Duration.contains("5")&&Duration.contains("year"))
	        		{
	        			System.out.print(60);
	        		}
	                System.out.println("\"},");
	                
	        	}
	        	
	    	    
	        }
		}
		System.out.println("DONE.");
		
		
	}
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	
	
		public static String HTMLFilter(String input) {
		    if (input == null) {
		        input = "";
		        return input;
		    }
		    input = input.trim().replaceAll("&amp;", "&");
		    input = input.trim().replaceAll("&lt;", "<");
		    input = input.trim().replaceAll("&gt;", ">");
		    input = input.trim().replaceAll("    ", " ");
		    input = input.trim().replaceAll("<br>", "\n");
		    input = input.trim().replaceAll("&nbsp;", "  ");
		    input = input.trim().replaceAll("&quot;", "\"");
		    input = input.trim().replaceAll("&#39;", "'");
		    input = input.trim().replaceAll("&#92;", "\\\\");
		    input = input.trim().replaceAll("&#...;", "");
		    input = input.trim().replaceAll("&#....;", "");
		    return input;
		}


}
