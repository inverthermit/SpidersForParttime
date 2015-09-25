package uea;

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
import uea.getURL;

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

public class UeaUnData {

	/**
	 * @param args
	 */
	    //title: div id=titleRoll
		//duration: div class=courseDuration
		//school: div class=schoolOfStudy
		//structure: div class="course-profile-year"  and all div class=unit_header  inside
		//requirement div id=requirementsTab (with IELTS)
		//fees
		/*Undergraduate (Bachelor)

		Arts & Social Sciences + Maths: £14,200
		Laboratory Based Sciences: £17,500
		MB BS (Medicine): £28,000
		School of Health Sciences: £14,000 inclusive of placement fees
		Fees are reviewed annually and may be subject to incremental increases for any subsequent years of study. 

		Postgraduate Taught (Masters)

		Arts & Social Sciences + Computing & Maths: £14,200
		Lab Based Sciences: £17,500
		School of Economics: £14,415
		Norwich Business School: £15,300
		MSc Brand Leadership: £16,500
		Full-Time MBA General: £19,500
		MSc Advanced Practitioner/MS Oncoplastic Breast Surgery/MSc Stroke Recovery: £14,400.00
		MSc Clinical Education/ MSc Mental Health: £14,400.00
		MSc Clinical Research/ MRes Health Research/ MSc Health Economics: £12,000
		MSc Physiotherapy/Occupational Therapy (pre-registration): £15,000
		Postgraduate Research (MScR/MPhil/PhD)

		Arts & Social Sciences + Mathematics: £13,550
		Laboratory Based Sciences: £16,700*/
		//Under graduate scholarship Vice Chancellor’s Prizes 50% International UG Prizes 25%
	public static String FILE_PATH="d:\\UEA";
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
		for(;i<getURL.UnUrl.length+1;i++)
		{
			//if(i>=22)
			{
				System.out.println(i+":"+getURL.UnUrl[i-1][0]);
					HashMap<String,String> data=UeaGetDetails(getURL.UnUrl[i-1]);
					for(j=0;j<13;j++)
						{
							label = new Label(j, i, data.get(Keys[j]));
							sheet.addCell(label);
						}
				
			}
				
				
			
			
			if(i>10)
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
	
	public static HashMap<String,String> UeaGetDetails(String[] url) throws Exception
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
	    
	  //title: div id=titleRoll
	  //duration: div class=courseDuration
	  		//school: div class=schoolOfStudy
	  		//structure: div class="course-profile-year"  and all div class=unit_header  inside
	  		//requirement div id=requirementsTab (with IELTS)
	   
	    //**************************get duration*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter DurationFilter=new AndFilter(new TagNameFilter("div"),
	    		new HasAttributeFilter("class","courseDuration"));
        NodeList nodes8 = parser.extractAllNodesThatMatch(DurationFilter);
        //System.out.println(nodes3.size());
        if(nodes8.size()>0)
        {
        	
        		Node node=(Node)nodes8.elementAt(0);
        	    String Duration=html2Str(node.toHtml());
        	    System.out.println(Duration);
        		if(Duration.contains("1 year"))
        		{
        			result.put("Length (months)","12");
        		}
        		else if(Duration.contains("2 year"))
        		{
        			result.put("Length (months)","24");
        		}
        		else if(Duration.contains("3 year"))
        		{
        			result.put("Length (months)","36");
        		}
        		else if(Duration.contains("4 year"))
        		{
        			result.put("Length (months)","48");
        		}
        		else if(Duration.contains("1")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","12");
        		}
        		else if(Duration.contains("2")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","24");
        		}
        		else if(Duration.contains("3")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","36");
        		}
        		else if(Duration.contains("4")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","48");
        		}

    	    
    	    
        	
        }  
	    
	    //**************************get title*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter TitleFilter=new AndFilter(new TagNameFilter("div"),
	    		new HasAttributeFilter("id","titleRoll"));
        NodeList nodes7 = parser.extractAllNodesThatMatch(TitleFilter);
        //System.out.println(nodes3.size());
        if(nodes7.size()>0)
        {
        	
        		Node node=(Node)nodes7.elementAt(0);
        	    String title=html2Str(node.toHtml());
        	    System.out.println("Title:"+title);

        		result.put("Title",title);
        	    result.put("Type",GetType(title));
    	    
    	    
        	
        }  
	    
	    
      //**************************get school*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter SchoolFilter=new AndFilter(new TagNameFilter("div"),
	    		new HasAttributeFilter("class","schoolOfStudy"));
        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
        //System.out.println(nodes3.size());
        if(nodes3.size()>0)
        {
        	
        		Node node=(Node)nodes3.elementAt(0);
        	    String school=html2Str(node.toHtml());
        	   result.put("School",school);
        	    System.out.println("School:"+school);        	
        }
        
        
      //**************************get entry**********************
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter EntryFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","requirementsTab"));
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
                //System.out.println(entry.contains("6.0"));
                result.put("Academic Entry Requirement",entry);
              //******************************IELTS***********************
                String International=entry;
        		ArrayList<String> list = new ArrayList<String>();
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
        
     
      //structure: div class="course-profile-year"  and all div class=unit_header  inside
		 //**************************get structure**********************
		parser=Parser.createParser(htmls, "utf-8");
	    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","course-profile-year"));
        NodeList nodes5 = parser.extractAllNodesThatMatch(StructureFilter);
        String Structure="";
        if(nodes5.size()>0)
        {
        	for(int i=0;i<nodes5.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes5.elementAt(i);
    	    	if(node.toHtml().contains("id=\"course-profile-year-1Tab\""))
    	    	{
    	    		Structure+="\r\nYear 1\r\n";
    	    	}
    	    	else if(node.toHtml().contains("id=\"course-profile-year-2Tab\""))
    	    	{
    	    		Structure+="\r\nYear 2\r\n";
    	    	}
    	    	else if(node.toHtml().contains("id=\"course-profile-year-3Tab\""))
    	    	{
    	    		Structure+="\r\nYear 3\r\n";
    	    	}
    	    	else if(node.toHtml().contains("id=\"course-profile-year-4Tab\""))
    	    	{
    	    		Structure+="\r\nYear 4\r\n";
    	    	}
    	    	else if(node.toHtml().contains("id=\"course-profile-year-5Tab\""))
    	    	{
    	    		Structure+="\r\nYear 5\r\n";
    	    	}
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
