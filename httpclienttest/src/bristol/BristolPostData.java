package bristol;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import bristol.getURL;

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

public class BristolPostData {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		//<div id="course-structure" class="module">
		//id=typical-offer
		//A 7.5 7  B D 7.0 7 C 6.5 6.5    E 6.5 6 F 6 6 Profile Foundation 6.0 5.5
		//Undergraduate fee:Arts courses 15200  Science courses 18300  Clinical courses 33900
		//Postgraduate fee using EL pattern
		Pattern p = Pattern.compile("[0-9]+,[0-9]+");
		Matcher m = p.matcher("<dl><dt>UK/EU</dt><dd>£4,145</dd><dt>Overseas</dt><dd>£14,200</dd></dl>");
		ArrayList<Integer> money=new ArrayList<Integer>();
		while (m.find()) {
			money.add(Integer.parseInt(m.group().replace(",", "")));
		}
		int max=0;
		for(int w=0;w<money.size();w++)
		{
			if(money.get(w)>max)
			{
				max=money.get(w);
			}
		}
		if(max!=0)
		System.out.println(max);
		
	}*/
	public static String FILE_PATH="d:\\BRISTOL";
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
		for(;i<getURL.PostData.length+1;i++)
		{
			//if(i>=22)
			{
				System.out.println(i+":"+getURL.PostData[i-1][0]);
					HashMap<String,String> data=MaryGetDetails(getURL.PostData[i-1]);
					for(j=0;j<13;j++)
						{
							label = new Label(j, i, data.get(Keys[j]));
							sheet.addCell(label);
						}
				
			}
				
				
			
			/*
			if(i>10)
				break;*/
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
	
	public static HashMap<String,String> MaryGetDetails(String[] url) throws Exception
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
	    
	  //**************************get length*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    TagNameFilter LengthFilter=new TagNameFilter("tr");
        NodeList nodes0 = parser.extractAllNodesThatMatch(LengthFilter);
        //System.out.println(nodes3.size());
        if(nodes0.size()>0)
        {
        	for(int i=0;i<nodes0.size();i++)
    	    {
        		Node node=(Node)nodes0.elementAt(i);
        		String tr=html2Str(node.toHtml());
        		if(tr.contains("Programme length")||tr.contains("Course length"))//Course length
        		{
        			
        			//Four years
        			//seven years
        			//One year
        			// Three to four years
        			//two years
        			String Duration=tr;
        			if(Duration.contains("One year")||Duration.contains("one year"))
            		{
            			result.put("Length (months)","12");
            		}
            		else if(Duration.contains("Two year")||Duration.contains("two year"))
            		{
            			result.put("Length (months)","24");
            		}
            		else if(Duration.contains("Three year")||Duration.contains("three year"))
            		{
            			result.put("Length (months)","36");
            		}
            		else if(Duration.contains("Four year")||Duration.contains("four year"))
            		{
            			result.put("Length (months)","48");
            		}
            		else if((Duration.contains("One")||Duration.contains("one"))&&Duration.contains("year"))
            		{
            			result.put("Length (months)","12");
            		}
            		else if((Duration.contains("Two")||Duration.contains("two"))&&Duration.contains("year"))
            		{
            			result.put("Length (months)","24");
            		}
            		else if((Duration.contains("Three")||Duration.contains("three"))&&Duration.contains("year"))
            		{
            			result.put("Length (months)","36");
            		}
            		else if((Duration.contains("Four")||Duration.contains("four"))&&Duration.contains("year"))
            		{
            			result.put("Length (months)","48");
            		}
            		break;
        		}
    	    
    	    }
        	
        }
        
      //**************************get school*************************
	    //{"36","http://www.bristol.ac.uk/study/undergraduate/2016/accounting-finance/bsc-accounting-finance/","Accounting and Finance","BSc"},
        String[] urlSplit=url[0].split("/");	        	
	    String school=urlSplit[urlSplit.length-2];
	    char[] cs=school.toCharArray();
        cs[0]-=32;
        school=String.valueOf(cs);
	    result.put("School",school);
        	        	System.out.println("School:"+school);
    	  
      //**************************get fees**********************
        //div id=fees
      parser=Parser.createParser(htmls, "utf-8");
      AndFilter FeeFilter=new AndFilter(new TagNameFilter("div"),
              new HasAttributeFilter("id","fees"));
      NodeList nodes3 = parser.extractAllNodesThatMatch(FeeFilter);
      if(nodes3.size()>0)
      {
    	  Pattern p = Pattern.compile("[0-9]+,[0-9]+");
  		Matcher m = p.matcher(nodes3.elementAt(0).toHtml());
  		ArrayList<Integer> money=new ArrayList<Integer>();
  		while (m.find()) {
  			money.add(Integer.parseInt(m.group().replace(",", "")));
  		}
  		int max=0;
  		for(int w=0;w<money.size();w++)
  		{
  			if(money.get(w)>max)
  			{
  				max=money.get(w);
  			}
  		}
  		if(max!=0)
  		{
  			System.out.println(max); 
  			result.put("Tuition Fee", ""+max);
  		}
  		    	
        	            	
      }
        
      //**************************get entry**********************
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter EntryFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","entry-requirements"));
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
                //System.out.println(entry);
                result.put("Academic Entry Requirement",entry);
                
                //****************IELTS
              //A 7.5 7  B D 7.0 7 C 6.5 6.5    E 6.5 6 F 6 6 Profile Foundation 6.0 5.5
                
                if(entry.contains("Profile A"))
                {

                    result.put("IELTS Average Requirement", "7.5");
            		result.put("IELTS Lowest Requirement", "7");
                }
                else if(entry.contains("Profile B")||entry.contains("Profile D"))
                {

                    result.put("IELTS Average Requirement", "7");
            		result.put("IELTS Lowest Requirement", "7");
                }
                else if(entry.contains("Profile C"))
                {

                    result.put("IELTS Average Requirement", "6.5");
            		result.put("IELTS Lowest Requirement", "6.5");
                }
                else if(entry.contains("Profile E"))
                {

                    result.put("IELTS Average Requirement", "6.5");
            		result.put("IELTS Lowest Requirement", "6");
                }
                else if(entry.contains("Profile F"))
                {

                    result.put("IELTS Average Requirement", "6");
            		result.put("IELTS Lowest Requirement", "6");
                }
                else if (entry.contains("Profile Foundation"))
                {

                    result.put("IELTS Average Requirement", "6");
            		result.put("IELTS Lowest Requirement", "5.5");
                }
                else
                {
                	result.put("IELTS Average Requirement", "6");
            		result.put("IELTS Lowest Requirement", "5.5");
                }
    	    	break;
    	    }
        }
        
     
		
		 //**************************get structure**********************
		parser=Parser.createParser(htmls, "utf-8");
	    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","programme-structure"));
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
        else//pgr-overview
        {
        	parser=Parser.createParser(htmls, "utf-8");
    	    AndFilter OverviewFilter=new AndFilter(new TagNameFilter("div"),
                    new HasAttributeFilter("id","pgr-overview"));
            nodes5 = parser.extractAllNodesThatMatch(OverviewFilter);
            for(int i=0;i<nodes5.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes5.elementAt(i);
                String structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
                structure=HTMLFilter(structure);                
        	    result.put("Structure",structure.trim());
    	    	break;
    	    }
        }
        result.put("Level", "Postgraduate");
		result.put("Scholarship", "");
		
		result.put("Title",url[1]);
	    result.put("Type",url[2]);
	    //result.put("Length (months)",url[0]);
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
