package no5.anu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class AnuUnData {

	/*
	 <div class="body__inner w-doublewide copy">

    split("<h2 id=")  "<h2 id="+...
	Structure:<h2 id="program-requirements">Program Requirements</h2>
	Fee: <h2 id="indicative-fees">Indicative Fees</h2>
	Entry:<h2 id="admission-requirements">Admission Requirements</h2>
	ielts:6.5 6
	Length:
	<li class="degree-summary__requirements-length">
    <span class="degree-summary__requirements-heading"><i class="icon-calendar"></i>Length</span>
    <span class="tooltip-area" data-toggle="tooltip" title="2 years part-time for domestic students only">1 years full-time</span>
    </li>
	
	School:<span class="first-owner">ANU College of Business and Economics</span>
	
	
	 */
	public static int MAX_THREAD=5;
	public static String[][] Data=no5.anu.getURL.ProgramData;
	public static String FILE_PATH="d:\\Australia-Unis\\ANU";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WriteToExcel();

	}
	public static WritableSheet sheet=null;
	public static void WriteToExcel()
	{
		File outputFile = new File(FILE_PATH + "\\" + "gen_data.xls");
		OutputStream os = null;
		WritableWorkbook book=null;
		try {
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			os = new FileOutputStream(outputFile);
		Label label;
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
		/*
		HashMap<String,String> DataMap=KclGetDetails(Data[0]);
		putIntoWorkbook(DataMap,Integer.parseInt(Data[0][0]));
		*/
		
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int tIndex=0;tIndex<MAX_THREAD;tIndex++)
		{
			pool.execute(
			new Runnable(){
				public void run()
				{
					while(true)
					{
						String[] data= getUnhandledURL();
						if(data!=null)
						{
							HashMap<String,String> DataMap=SouthamptonGetDetails(data);
							putIntoWorkbook(DataMap,Integer.parseInt(data[0]));
							System.out.println(data[0]+" done.");
						}
						else{
							//System.out.println("No Unhandled");
							break;
						}
					}
					
					
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(600, TimeUnit.SECONDS);
		
		
       
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
	
	public static synchronized String[] getUnhandledURL()
	{
		for(int i=0;i<Data.length;i++)
		{
			if(Data[i][Data[0].length-1].equals("0"))
			{
				Data[i][Data[0].length-1]="1";
				return Data[i];
			}
		}
		return null;
	}
	
	public static synchronized void putIntoWorkbook(HashMap<String,String> data,int index)
	{
		String[] Keys={"School","Level","Title","Type","Application Fee","Tuition Fee",
				"Academic Entry Requirement","IELTS Average Requirement",
				"IELTS Lowest Requirement","Structure","Length (months)","Month of Entry",
				"Scholarship"};
		for(int j=0;j<13;j++)
		{
			//label = new Label(j, i, data.get(Keys[j]));
		    Label label = new Label(j, index, data.get(Keys[j]));
		    try{
		    	sheet.addCell(label);
		    }
		    catch(Exception ee)
		    {
		    	ee.printStackTrace();
		    }
			
		}
	}
	
	

	public static HashMap<String,String> SouthamptonGetDetails(String[] url)
	{
		
		while(true)
		{
			try{
				HashMap<String,String> result=new HashMap<String,String>();
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();  
				CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
				
				HttpGet httpGet = new HttpGet(url[1]); 
				HttpResponse response = httpclient.execute(httpGet);  
				HttpEntity entity = response.getEntity();
				
				String htmls=null;
				if (entity != null) { 
				    htmls=EntityUtils.toString(entity).replace("\t", " ");
				    //System.out.println(htmls);
				    
				     
				}
				System.out.println("Got reply!");
				//htmls=HTMLFilter(htmls);
				
				
			    
		        
			    //**********************************get month**********************
		        
		        
				
				
				
				Parser parser=null;
			    HtmlPage page=new HtmlPage(parser); 
			    if(htmls.contains("Feburary")||htmls.contains("feburary"))
			    {
			    	result.put("Month of Entry", "2");
			    	
			    }
			    else
			    {
			    	result.put("Month of Entry", "");
			    }
			    
			    //**********************************get length**********************
					parser=Parser.createParser(htmls.replace("span", "form"), "utf-8");
				    AndFilter TFilter=new AndFilter(new TagNameFilter("span"),//table class="CSCPreviewTable grey"
			                new HasAttributeFilter("class","tooltip-area"));
			        NodeList nodes3 = parser.extractAllNodesThatMatch(TFilter);
			        if(nodes3.size()>0)
			        {
    		    		String length=html2Str(nodes3.elementAt(0).toHtml());
    			        result.put("Length (months)",length);
			        }
			        else if(!url[6].equals(""))
			        {
			        	result.put("Length (months)",url[5]);
			        }
			    
			      //**********************************get school**********************
					parser=Parser.createParser(htmls.replace("span", "form"), "utf-8");
				    AndFilter SFilter=new AndFilter(new TagNameFilter("form"),//table class="CSCPreviewTable grey"
			                new HasAttributeFilter("class","first-owner"));
			        NodeList nodes4 = parser.extractAllNodesThatMatch(SFilter);
			        if(nodes4.size()>0)
			        {
			        	
    		    		String school=html2Str(nodes4.elementAt(0).toHtml());
    			        result.put("School",school);
    		    		
			        }
		        
			    
		      
		        
		        
			    //**********************************get entry structure**********************
		        
		        parser=Parser.createParser(htmls, "utf-8");
		        AndFilter ESFilter=new AndFilter(new TagNameFilter("div"),//table class="CSCPreviewTable grey"
		                new HasAttributeFilter("class","body__inner w-doublewide copy"));
		        NodeList nodes1 = parser.extractAllNodesThatMatch(ESFilter);
		        String structure="";
		        String majors="";
		        String fee="";
		        String entryAll="";
		        if(nodes1.size()>0)
		        {
		        	String AllContents=nodes1.toHtml();
		        	String[] SP=AllContents.split("<h2 id=");
		        	for(int i=1;i<SP.length;i++)
		        	{
		        		String row="<h2 id="+SP[i];
		        		if(row.contains("<h2 id=\"program-requirements\">Program Requirements</h2>"))//Structure
		        		{
		        			structure=(html2Str(row.replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
		    	    		structure=HTMLFilter(structure);
		    	    		result.put("Structure",structure);
		        		}
		        		else if(row.contains("<h2 id=\"admission-requirements\">Admission Requirements</h2>"))
		        		{
		        			entryAll=(html2Str(row.replace(">", "> "))).replace("\r", "");
		          	    	entryAll=entryAll.replace("\n", " ");
		          	    	entryAll=HTMLFilter(entryAll);
		          	    	result.put("Academic Entry Requirement",entryAll);
		        		}
		        		else if(row.contains("<h2 id=\"indicative-fees\">Indicative Fees</h2>"))
		        		{
		        			fee=row;
		        		}
		        	}
		        	
		        }
		        
			    
			    
		        
		      //**********************************get fee**********************
		        
		    	Pattern p = Pattern.compile("\\$[0-9]+");
		    	Matcher m = p.matcher(fee.replace(",", ""));
		    	ArrayList<Integer> money=new ArrayList<Integer>();
		    	while (m.find()) 
		    	{
		    		money.add(Integer.parseInt(m.group().replace("$", "")));
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
		        
		      
			  	

				

		          //****************IELTS
		        result.put("IELTS Average Requirement","6.5");
		        result.put("IELTS Lowest Requirement", "6.0");
		          
		        
		        
		        result.put("Scholarship", url[4]);
                //**************************get title & type**********************
			    
	        	result.put("Title",url[4]+" "+url[2]);
			    result.put("Type",url[2]);
			    result.put("Level",url[3]);
			   
				httpclient.close();
		        return result;
			}
			catch(Exception ee)
			{
				System.out.println("Retrying..."+url[0]);
				ee.printStackTrace();
			}
		}
		
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
	public static String getLastYear(String e){
		if(e.toLowerCase().contains("fifth year")||e.toLowerCase().contains("year 5")){
			return "60";
		}else if(e.toLowerCase().contains("fourth year")||e.toLowerCase().contains("year 4")){
			return "48";
		}else if(e.toLowerCase().contains("third year")||e.toLowerCase().contains("year 3")){
			return "36";
		}else if(e.toLowerCase().contains("second year")||e.toLowerCase().contains("year 2")){
			return "24";
		}
		return "";
	}
	public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
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
