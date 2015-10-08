package royal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import royal.getURL;

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
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class RoyalUnData {

	//div class="sys_contentbox sys_contentbox-closed"
	//Structure: contains <h2 class="sys_contentbox-hdr" style="cursor: pointer;">What you'll learn</h2>
	//Entry: contains <h2 class="sys_contentbox-hdr" style="cursor: pointer;">Entry requirements</h2>
	//IELTS: in Entry,tr
	//Duration: div id="tabbedareaA" Three years Four years
	//School:String School="<title>Royal Holloway | Accounting &amp; Finance (BSc) | School of Management home</title>";
	//System.out.println(School.split(" \\| ")[School.split(" \\| ").length-1].replace(" home", ""));
	
	
	public static String FILE_PATH="d:\\ROYAL";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WriteToExcel();

	}
	public static void WriteToExcel()
	{
		File outputFile = new File(FILE_PATH + "\\" + "gen_data.xls");
		OutputStream os = null;
		WritableWorkbook book=null;
		WritableSheet sheet=null;
		try {
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			os = new FileOutputStream(outputFile);
		Label label;
		String[] Keys={"School","Level","Title","Type","Application Fee","Tuition Fee",
				"Academic Entry Requirement","IELTS Average Requirement",
				"IELTS Lowest Requirement","Structure","Length (months)","Month of Entry",
				"Scholarship"};
		book = Workbook.createWorkbook(os);
		sheet = book.createSheet("sheet1", 0);
		label = new Label(0, 0, "School");
		sheet.addCell(label);
		label = new Label(1, 0, "Level");
		sheet.addCell(label);
		label = new Label(2, 0, "Title");
		sheet.addCell(label);
		label = new Label(3, 0, "Type");
		sheet.addCell(label);
		label = new Label(4, 0, "Application Fee");
		sheet.addCell(label);
		label = new Label(5, 0, "Tuition Fee");
		sheet.addCell(label);
		label = new Label(6, 0, "Academic Entry Requirement");
		sheet.addCell(label);
		label = new Label(7, 0, "IELTS Average Requirement");
		sheet.addCell(label);
		label = new Label(8, 0, "IELTS Lowest Requirement");
		sheet.addCell(label);
		label = new Label(9, 0, "Structure");
		sheet.addCell(label);
		label = new Label(10, 0, "Length (months)");
		sheet.addCell(label);
		label = new Label(11, 0, "Month of Entry");
		sheet.addCell(label);
		label = new Label(12, 0, "Scholarship");
		sheet.addCell(label);
		
		
		int i = 1;
		int j=0;
		String url="";
		for(;i<getURL.UnData.length+1;i++)
		{
			if(i>=239)
			{
				System.out.println(i+":"+getURL.UnData[i-1][0]);
					HashMap<String,String> data=RoyalGetDetails(getURL.UnData[i-1]);
					for(j=0;j<13;j++)
						{
							label = new Label(j, i, data.get(Keys[j]));
							sheet.addCell(label);
						}
				
			}
				
				
			
			
			if(i>243)
				break;
		}
		
		
       
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}finally{
			try {
				book.write();
				book.close();
				os.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
	}
	
	public static HashMap<String,String> RoyalGetDetails(String[] url) throws Exception
	{
		HashMap<String,String> result=new HashMap<String,String>();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		HttpGet httpGet = new HttpGet(url[0]); 
		HttpResponse response = httpclient.execute(httpGet);  
		HttpEntity entity = response.getEntity();
		
		String htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity).replace("\t", " ");
		    //System.out.println(htmls);
		    
		     
		}
		System.out.println("Got reply!");
		//htmls=HTMLFilter(htmls);
		Parser parser=null;
	    HtmlPage page=new HtmlPage(parser); 
	    if(htmls.contains("September")||htmls.contains("september"))
	    {
	    	result.put("Month of Entry", "9");
	    	
	    }
	    else if(htmls.contains("October")||htmls.contains("october"))
	    {
	    	result.put("Month of Entry", "10");
	    }
	    else
	    {
	    	result.put("Month of Entry", "");
	    }
	    
	    
        
      //**************************get school*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    TagNameFilter SchoolFilter=new TagNameFilter("title");
        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
        //System.out.println(nodes3.size());
        if(nodes3.size()>0)
        {
        		Node node=(Node)nodes3.elementAt(0);
        		
        	    String school=html2Str(node.toHtml().split(" \\| ")[node.toHtml().split(" \\| ").length-1].replace(" home", ""));
        	    result.put("School",school);
        	    System.out.println("School:"+school);
    	    
    	    
        	
        }


        //**************************get duration**********************
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter DurationFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","tabbedareaA"));
        NodeList nodes4 = parser.extractAllNodesThatMatch(DurationFilter);
        //String Structure="";
        if(nodes4.size()>0)
        {
        	for(int i=0;i<nodes4.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes4.elementAt(i);
    	    	String Duration=node.toHtml();
    	        if(Duration.contains("One year"))
    			{
    				result.put("Length (months)","12");
    			}
    			else if(Duration.contains("Two year"))
    			{
    				result.put("Length (months)","24");
    			}
    			else if(Duration.contains("Three year"))
    			{
    				result.put("Length (months)","36");
    			}
    			else if(Duration.contains("Four year"))
    			{
    				result.put("Length (months)","48");
    			}
    			else if(Duration.contains("Five year"))
    			{
    				result.put("Length (months)","60");
    			}
    			else if(Duration.contains("One")&&Duration.contains("year"))
    			{
    				result.put("Length (months)","12");
    			}
    			else if(Duration.contains("Two")&&Duration.contains("year"))
    			{
    				result.put("Length (months)","24");
    			}
    			else if(Duration.contains("Three")&&Duration.contains("year"))
    			{
    				result.put("Length (months)","36");
    			}
    			else if(Duration.contains("Four")&&Duration.contains("year"))
    			{
    				result.put("Length (months)","48");
    			}
    	    	break;
    	    }
        }
        
        
		 //**************************get structure & entry**********************
		parser=Parser.createParser(htmls, "utf-8");
	    AndFilter Filter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","sys_contentbox"));
        NodeList nodes5 = parser.extractAllNodesThatMatch(Filter);
        //String Structure="";
        if(nodes5.size()>0)
        {
        	for(int i=0;i<nodes5.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes5.elementAt(i);
    	    	if(node.toHtml().contains("<h2 class=\"sys_contentbox-hdr\">What you'll learn</h2>"))
    	    	{
    	    		String structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
                    structure=HTMLFilter(structure);
            	    result.put("Structure",structure.trim());
    	    	}
    	    	else if(node.toHtml().contains("<h2 class=\"sys_contentbox-hdr\">Entry requirements</h2>"))
    	    	{
    	    		
                    //*******************IELTS*********************
                    parser=Parser.createParser(node.toHtml(),"utf-8");
                    TagNameFilter IELTSFilter=new TagNameFilter("table");
                    NodeList nodes13 = parser.extractAllNodesThatMatch(IELTSFilter);
                    for(int j=0;j<nodes13.size();j++)
            	    {
                    Node tablenode=(Node)nodes13.elementAt(j);
                    String entry=(html2Str(tablenode.toHtml())).replace("\r", "");
        	    	entry=entry.replace("\n", " ");
        	    	//entry=entry.replace("<br>", " ");
        	    	entry=HTMLFilter(entry);
                    //System.out.println(entry);
                    result.put("Academic Entry Requirement",entry);
        	    	if(tablenode.toHtml().contains("English language"))
        	    	{
        	    		 String International=tablenode.toHtml();
        	        		ArrayList<String> list = new ArrayList<String>();
        	        		if(International.contains("7.5"))
        	                {
        	                	list.add("7.5");
        	                }
        	        		if(International.contains("7.0"))
        	                {
        	                	list.add("7.0");
        	                }
        	                if(International.contains("6.5"))
        	                {
        	                	list.add("6.5");
        	                }
        	                if(International.contains("6.0"))
        	                {
        	                	list.add("6.0");
        	                }
        	                if(International.contains("5.5"))
        	                {
        	                	list.add("5.5");
        	                }
        	                if(list.size()==1)
        	                {
        	                	result.put("IELTS Average Requirement", list.get(0));
        	                	result.put("IELTS Lowest Requirement", list.get(0));
        	                }
        	                else if(list.size()>=2)
        	                {
        	                	result.put("IELTS Average Requirement", list.get(0));
        	                	result.put("IELTS Lowest Requirement", list.get(1));
        	                }
        	                else
        	                {
        	                	result.put("IELTS Average Requirement","6.0");
        	                    
        	            	    result.put("IELTS Lowest Requirement", "5.5");
        	                }
                	    break;
        	    	}
            	    }
                    
    	    	}
                
    	    }
        }
        result.put("Level", "Undergraduate");
        //result.put("IELTS Average Requirement", "");
		//result.put("IELTS Lowest Requirement", "");
		result.put("Scholarship", "");
		
		result.put("Title",url[1]);
	    result.put("Type",url[2]);
	    //result.put("Length (months)",url[3]);
		httpclient.close();
        return result;
	}//...
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	public static String GetType(String input)//BA BEng Bsc Msc MEng 
	{
		String types="BA;BEng;BSc;BDS;BN;BVSc;MOSci;MESci;MEcol;MPhys;MMath;MMarBiol;MBChB;MChem;MSc;MEng;Double MA;Joint MA;MA;MArich;MBA;PG;Pg;EdD;MEd;Postgraduate Diploma;Postgraduate Certificate;Doctorate;Graduate Certificate;LLM;LLB;GradDip;MTh;MRes";
		
		String[] array=types.split(";");
		for(int i=0;i<array.length;i++)
		{
			if(input.contains(array[i]))
			{
				return array[i];
			}
		}
		//String result=array[array.length-1].replace(")", "");
		return "";
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
