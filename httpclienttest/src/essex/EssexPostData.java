package essex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

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
import org.htmlparser.visitors.HtmlPage;

import essex.getURL;

public class EssexPostData {

	/*
	 structure: div id=modules-ug
	 entry: div id=ContentMain_entryRequirementsUg   IELTS inside
	 fee: a id=ContentMain_UGFactFile_hlFeeFinderLinkOverseas
	  (<a id="ContentMain_UGFactFile_hlFeeFinderLinkOverseas" href="/fees/fee_finder.aspx?status=2&amp;level=U&amp;course=BSC+N400&amp;mode=F&amp;year=2016/17">Overseas</a>)
	 div id=ContentMain_pnlFeeSearchResults
	 EL:£12950   £[0-9]+
	 
	 http://www.sx.ac.uk/fees/fee_finder.aspx?status=2&level=U&course=BSC+N400&mode=F&year=2016/17
	 */
	public static String FILE_PATH="d:\\ESSEX";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WriteToExcel();

	}
	public static void WriteToExcel()
	{
		File outputFile = new File(FILE_PATH + "\\" + "Postgen_data.xls");
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
			if(i>=0)
			{
				System.out.println(i+":"+getURL.PostData[i-1][0]);
					HashMap<String,String> data=EssexGetDetails(getURL.PostData[i-1]);
					for(j=0;j<13;j++)
						{
							label = new Label(j, i, data.get(Keys[j]));
							sheet.addCell(label);
						}
				
			}
				
				
			
			
			/*if(i>10)
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
	
	public static HashMap<String,String> EssexGetDetails(String[] url) throws Exception
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
	    
	   
        
      //**************************get entry**********************
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter EntryFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","entry-requirements-pg"));
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
                String International=entry;
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
        
        
		
		 //**************************get structure**********************
		parser=Parser.createParser(htmls, "utf-8");
	    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","modules-pg"));
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

		 //**************************get duration**********************
        String Duration=url[2];
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
        
        /*
		 structure: div id=modules-pg
		 entry: div id=ContentMain_entryRequirementsUg   IELTS inside
		 fee: a id=ContentMain_UGFactFile_hlFeeFinderLinkOverseas
		  (<a id="ContentMain_UGFactFile_hlFeeFinderLinkOverseas" href="/fees/fee_finder.aspx?status=2&amp;level=U&amp;course=BSC+N400&amp;mode=F&amp;year=2016/17">Overseas</a>)
		 div id=ContentMain_pnlFeeSearchResults
		 EL:£12950   £[0-9]+
		 
		 http://www.sx.ac.uk/fees/fee_finder.aspx?status=2&level=U&course=BSC+N400&mode=F&year=2016/17
		 */
        //**********************************get fee**********************
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter FeeLinkFilter=new AndFilter(new TagNameFilter("a"),
                new HasAttributeFilter("id","ContentMain_PGFactFile_hlFeeFinderLinkOverseas"));
        NodeList nodes7 = parser.extractAllNodesThatMatch(FeeLinkFilter);
        //String Structure="";
        if(nodes7.size()>0)
        {
        	for(int i=0;i<nodes7.size();i++)
    	    {
    	    	
    	    	LinkTag link=(LinkTag)nodes7.elementAt(i);
    	    	String get1=link.getAttribute("href");
    	    	if(!get1.equals(""))
    	    	{
    	    		httpGet = new HttpGet(HTMLFilter("http://www.sx.ac.uk"+get1)); 
    	            response = httpclient.execute(httpGet);  
    	    		entity = response.getEntity();
    	    		htmls=null;
    	    		if (entity != null) { 
    	    		    htmls=EntityUtils.toString(entity).replace("\t", " ");
    	    		    parser=Parser.createParser(htmls, "utf-8");
    	    		    AndFilter FeeFilter=new AndFilter(new TagNameFilter("div"),
    	    	                new HasAttributeFilter("id","ContentMain_pnlFeeSearchResults"));
    	    	        NodeList nodes11 = parser.extractAllNodesThatMatch(FeeFilter);
    	    	        if(nodes11.size()>0)
    	    	        {
    	    	        	for(int d=0;d<nodes11.size();d++)
    	    	    	    {
    	    	    	    	
    	    	    	    	Node node=(Node)nodes11.elementAt(d);
    	    	    	    	Pattern p = Pattern.compile("£[0-9]+");
    	    	    	  		Matcher m = p.matcher(node.toHtml());
    	    	    	  		ArrayList<Integer> money=new ArrayList<Integer>();
    	    	    	  		while (m.find()) {
    	    	    	  			money.add(Integer.parseInt(m.group().replace("£", "")));
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
    	    	    	    	break;
    	    	    	    }
    	    	        }
    	    		}
    	    	}
    	    	
    	    	break;
    	    }
        }
        
        
        
        result.put("Level", "Postgraduate");
		result.put("Scholarship", "");
		result.put("Title",url[1]);
	    result.put("Type",url[1].split(" ")[0]);
	    //result.put("Length (months)",url[2]);
	    result.put("School",url[4]);
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
