//http://courses.aber.ac.uk/browser/business/
package no7.Aberystwyth;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
	public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub

		String url1="http://www.dmu.ac.uk/study/courses/undergraduate-courses/undergraduate-courses.aspx?courselisting1_AtoZLetter=All&courselisting1_List_GoToPage=";
		
		String url2="http://www.dmu.ac.uk/Study/Courses/Postgraduate-courses/Postgraduate-courses.aspx?courselisting1_AtoZLetter=All&courselisting1_List_GoToPage=";
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		int count=1;
		for(int i=1;i<=7;i++)
		{
			int index=i;
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url2+index);
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			
	       ArrayList<String> list=new ArrayList<String>();
		    Parser	parser=Parser.createParser(htmls, "utf-8");
		    TagNameFilter ProfessionNameFilter=new TagNameFilter("tr");
	   	    
	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	   	    //System.out.println(nodes4.size());
	   	    for(int j=0;j<nodes4.size();j++)
	   	    {
	   	    	
	   	    	htmls=nodes4.elementAt(j).toHtml();
	   	    	
	   	    	//url
	   	    	String title="",url="";
	   	    	parser=Parser.createParser(htmls, "utf-8");
		        AndFilter uFilter=new AndFilter(new TagNameFilter("td"),
		        		new HasAttributeFilter("class","sys_col-one"));
		        NodeList nodes11 = parser.extractAllNodesThatMatch(uFilter);
		        if(nodes11.size()>0)
		        {
		        	title=html2Str(nodes11.elementAt(0).toHtml()).trim();
		        	parser=Parser.createParser(htmls, "utf-8");
			        AndFilter aFilter=new AndFilter(new TagNameFilter("a"),
			        		new HasAttributeFilter("href"));
			        NodeList nodes00 = parser.extractAllNodesThatMatch(aFilter);
			        if(nodes00.size()>0)
			        {
			        	LinkTag link=(LinkTag)nodes00.elementAt(0);
			   	    	//if(!link.getAttribute("href").equals("")&&!html2Str(link.toHtml()).contains("View this course"))
			   	    	{
			   	    		if(!list.contains(link.getAttribute("href")))
			   	    		{
			   	    			url=(link.getAttribute("href"));
			   	    			
			   	    		}
			   	    		
			   	    	}
			        }
			        
		        }
		        
		        //type
		        String type="";
	   	    	parser=Parser.createParser(htmls, "utf-8");
		        AndFilter tFilter=new AndFilter(new TagNameFilter("td"),
		        		new HasAttributeFilter("class","sys_col-two"));
		        NodeList nodes22 = parser.extractAllNodesThatMatch(tFilter);
		        if(nodes22.size()>0)
		        {
		        	type=html2Str(nodes22.elementAt(0).toHtml()).trim();
		        	
		    		
		        }
		        if(!url.equals("")){
		        	System.out.println("{\""+count+"\",\"http://www.dmu.ac.uk"
	   	    				+url+"\",\""+title+"\",\""+type+"\",\"0\"},");
		        count++;
		        }
		        
	   	    }
		}
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
		
		public static String[][] UnData={
		};
		public static String[][] PostData={
		};
	

}
