package lancaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class LancasterUnData {
	
public static String FILE_PATH="d:\\LANCASTER";
	public static void main(String[] args) throws Exception {
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
		
		BufferedReader rr=new BufferedReader(new FileReader("./file/LancasterUn.txt"));
		int i = 1;
		int j=0;
		String url="";
		while((url = rr.readLine()) != null)
		{
			//if(i>10)
			{
				System.out.println(i+":"+url);
				//url=rr.readLine();
				HashMap<String,String> data=LancasterGetDetails(url.split(";"));
				
				for(j=0;j<13;j++)
					{
						label = new Label(j, i, data.get(Keys[j]));
						sheet.addCell(label);
					}
			}
			
			i++;
		}
		rr.close();
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
			}
	}

		
		
       
		
	}
	
	public static HashMap<String,String> LancasterGetDetails(String[] url) throws Exception
	{
		HashMap<String,String> result=new HashMap<String,String>();
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
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
	    
	  //**************************get duration**********************
        parser=Parser.createParser(htmls, "utf-8");
        TagNameFilter DurationFilter=new TagNameFilter("li");
        NodeList nodes9 = parser.extractAllNodesThatMatch(DurationFilter);
        //String Structure="";
        if(nodes9.size()>0)
        {
        	for(int i=0;i<nodes9.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes9.elementAt(i);
    	    	String Duration=html2Str(node.toHtml());
    	    	if(Duration.contains("Duration: "))
    	    	{
    	    		System.out.println("Duration:"+Duration);
            		if(Duration.contains("1 Year"))
            		{
            			result.put("Length (months)","12");
            		}
            		else if(Duration.contains("2 Year"))
            		{
            			result.put("Length (months)","24");
            		}
            		else if(Duration.contains("3 Year"))
            		{
            			result.put("Length (months)","36");
            		}
            		else if(Duration.contains("4 Year"))
            		{
            			result.put("Length (months)","48");
            		}
            		else if(Duration.contains("1")&&Duration.contains("Year"))
            		{
            			result.put("Length (months)","12");
            		}
            		else if(Duration.contains("2")&&Duration.contains("Year"))
            		{
            			result.put("Length (months)","24");
            		}
            		else if(Duration.contains("3")&&Duration.contains("Year"))
            		{
            			result.put("Length (months)","36");
            		}
            		else if(Duration.contains("4")&&Duration.contains("Year"))
            		{
            			result.put("Length (months)","48");
            		}
    	    		break;
    	    	}
    	    	
    	    }
        }
        
      //**************************get school*************************
	    
        			parser=Parser.createParser(htmls, "utf-8");
        			AndFilter SFilter=new AndFilter(new TagNameFilter("a"),
        	                new HasAttributeFilter("title","Department website URL"));
        	        NodeList nodes0 = parser.extractAllNodesThatMatch(SFilter);
        	        if(nodes0.size()>0)
        	        {
        	        	String school=html2Str(nodes0.elementAt(0).toHtml());
        	        	result.put("School",school);
        	        	System.out.println("School:"+school);
        	        	
        	        }
    	    
        
        
      //**************************get entry**********************
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter EntryFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","details wrapper-course-entry-criteria hidden"));
        NodeList nodes4 = parser.extractAllNodesThatMatch(EntryFilter);
        //String Structure="";
        if(nodes4.size()>0)
        {
        	for(int i=0;i<nodes4.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes4.elementAt(i);
    	    	String entry=(html2Str(node.toHtml())).replace("\r", "");
    	    	entry=entry.replace("\n", " ");
    	    	//entry=entry.replace("<br>", " ");
    	    	entry=HTMLFilter(entry);
                System.out.println(entry);
                result.put("Academic Entry Requirement",entry);
                ArrayList<String> list = new ArrayList<String>();
                if(entry.contains("7.0"))
                {
                	list.add("7.0");
                }
                if(entry.contains("6.5"))
                {
                	list.add("6.5");
                }
                if(entry.contains("6.0"))
                {
                	list.add("6.0");
                }
                if(entry.contains("5.5"))
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
        
     
		
		 //**************************get structure**********************
		parser=Parser.createParser(htmls, "utf-8");
	    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","details wrapper-course-modules hidden"));
        NodeList nodes5 = parser.extractAllNodesThatMatch(StructureFilter);
        //String Structure="";
        if(nodes5.size()>0)
        {
        	for(int i=0;i<nodes5.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes5.elementAt(i);
    	    	//System.out.println("Structure:\n");
    	    	//System.out.println(node.toHtml().replaceAll("(<table([^>]*?)>)(.*?)(</table>)",""));
                String structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
                structure=HTMLFilter(structure);
                //System.out.println(structure);
                
        	    result.put("Structure",structure.trim());
    	    	break;
    	    }
        }
        result.put("Level", "Undergraduate");
		result.put("Scholarship", "");
		
		result.put("Title",url[1]);
	    result.put("Type",url[2]);
	    BufferedReader rr=new BufferedReader(new FileReader("./file/LancasterUnFee.txt"));
		String feeline="";
		while((feeline = rr.readLine()) != null)
		{
			String[] parts=feeline.split(";");
			if(parts[0].equals(url[1])&&parts[1].equals(url[2]))
			{
				result.put("Tuition Fee", parts[2].replace(",", "").trim());
			}
		}
		rr.close();
		httpclient.close();
        return result;
	}//...
	
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
