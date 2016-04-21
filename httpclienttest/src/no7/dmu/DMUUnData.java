package no7.dmu;

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

public class DMUUnData {

	/*
	
	
	Type:url[3]
	
	
	Entry: <div class="row row--block course-section course-section--criteria">

	Structure: <div class="row row--block course-section course-section--structure">

	Fee: <div class="block large-4 columns overview-side"> pattern
	
	Duration: <div class="block large-4 columns overview-side"> getLastYear
	
	IELTS: in entry
	
	no school
	
	
	 */

	public static int MAX_THREAD=20;
	public static String[][] Data=getURL.UnData;
	public static String FILE_PATH="d:\\ANo7\\DMU";
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
							HashMap<String,String> DataMap=GetDetails(data);
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
	
	

	public static HashMap<String,String> GetDetails(String[] url)
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
				    htmls=EntityUtils.toString(entity).replace("\t", " ");//.replace("<meta", "<form");
				    //System.out.println(htmls);
				    
				     
				}
				//System.out.println("Got reply!");
				//htmls=HTMLFilter(htmls);
				
				
			    
		        
			    //**********************************get month**********************
		        
		        
				
				
				
				Parser parser=null;
			    HtmlPage page=new HtmlPage(parser); 
			    if(htmls.contains("September")||htmls.contains("september"))
			    {
			    	result.put("Month of Entry", "9");
			    	
			    }
			    else
			    {
			    	result.put("Month of Entry", "");
			    }
			    
			    //**********************************get Structure**********************
		        parser=Parser.createParser(htmls, "utf-8");
		        AndFilter FFilter=new AndFilter(new TagNameFilter("div"),
		        		new HasAttributeFilter("class","row row--block course-section course-section--structure"));
		        NodeList nodes6 = parser.extractAllNodesThatMatch(FFilter);
		        
		        for(int i=0;i<nodes6.size();i++)
		        {
		        	
		        		String row=html2Str(nodes6.elementAt(i).toHtml()).trim();
			    		String structure=(html2Str(row.replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
	    	    		structure=HTMLFilter(structure);
	    	    		result.put("Structure",structure);
	    	    		break;
		        	
		    		
    	    		
		        }
		        
		      //**********************************get Entry**********************
		        parser=Parser.createParser(htmls, "utf-8");
		        AndFilter EFilter=new AndFilter(new TagNameFilter("div"),
		        		new HasAttributeFilter("class","row row--block course-section course-section--criteria"));
		        NodeList nodes5 = parser.extractAllNodesThatMatch(EFilter);
		        String entryAll="";
		        for(int i=0;i<nodes5.size();i++)
		        {
		        	
		        	entryAll=(html2Str(nodes5.elementAt(i).toHtml().replace(">", "> "))).replace("\r", "");
          	    	entryAll=entryAll.replace("\n", " ");
          	    	entryAll=HTMLFilter(entryAll);
          	    	result.put("Academic Entry Requirement",entryAll);
	    	    		break;
		        	
		    		
    	    		
		        }
		        
		      //**********************************get Fee**********************
		        parser=Parser.createParser(htmls, "utf-8");
		        AndFilter FeeFilter=new AndFilter(new TagNameFilter("div"),
		        		new HasAttributeFilter("class","block large-4 columns overview-side"));
		        NodeList nodes4 = parser.extractAllNodesThatMatch(FeeFilter);
		        for(int i=0;i<nodes4.size();i++)
		        {
		        	String row=(nodes4.elementAt(i).toHtml()).trim();
		        	Pattern p = Pattern.compile("&pound;[0-9]+");
			    	Matcher m = p.matcher(row.replace(",", ""));
			    	ArrayList<Integer> money=new ArrayList<Integer>();
			    	while (m.find()) 
			    	{
			    		money.add(Integer.parseInt(m.group().replace("&pound;", "")));
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
			    	 	//System.out.println(max); 
			    	 	result.put("Tuition Fee", ""+max);
			    	 }
	    	    		break;
		        	
		    		
    	    		
		        }
		      //**********************************get Duration**********************
		        parser=Parser.createParser(htmls, "utf-8");
		        AndFilter DFilter=new AndFilter(new TagNameFilter("div"),
		        		new HasAttributeFilter("class","block large-4 columns overview-side"));
		        NodeList nodes3 = parser.extractAllNodesThatMatch(DFilter);
		        
		        for(int i=0;i<nodes3.size();i++)
		        {
		        	
		        	String length=getLastYear(nodes3.elementAt(i).toHtml()).trim();
		    		result.put("Length (months)",length);
	    	    		break;
		        	
		    		
    	    		
		        }
			    
			  
		        
		      //****************IELTS
		        String International=entryAll;
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
		         
			        
			        
		        
                //**************************get title & type**********************
			    
	        	result.put("Title",url[2]);
			    result.put("Type",url[3]);
			    result.put("Level","Undergraduate");
			   
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
		if(e.toLowerCase().contains("2 year")||e.toLowerCase().contains("two year")||e.toLowerCase().contains("second year")||e.toLowerCase().contains("year 2")){
			return "24";
		}else if(e.toLowerCase().contains("3 year")||e.toLowerCase().contains("three year")||e.toLowerCase().contains("third year")||e.toLowerCase().contains("year 3")){
			return "36";
		}
		else if(e.toLowerCase().contains("4 year")||e.toLowerCase().contains("four year")||e.toLowerCase().contains("fourth year")||e.toLowerCase().contains("year 4")){
			return "48";
		}
		else if(e.toLowerCase().contains("5 year")||e.toLowerCase().contains("five year")||e.toLowerCase().contains("fifth year")||e.toLowerCase().contains("year 5")){
			return "60";
		}
		else if(e.toLowerCase().contains("19 month")){
			return "19";
		}
		else if(e.toLowerCase().contains("18 month")){
			return "18";
		}
		else if(e.toLowerCase().contains("17 month")){
			return "17";
		}
		else if(e.toLowerCase().contains("16 month")){
			return "16";
		}
		else if(e.toLowerCase().contains("15 month")){
			return "15";
		}
		else if(e.toLowerCase().contains("14 month")){
			return "14";
		}
		else if(e.toLowerCase().contains("13 month")){
			return "13";
		}
		else if(e.toLowerCase().contains("12 month")){
			return "12";
		}
		else if(e.toLowerCase().contains("11 month")){
			return "11";
		}
		else if(e.toLowerCase().contains("10 month")){
			return "10";
		}
		else if(e.toLowerCase().contains("9 month")){
			return "9";
		}
		else if(e.toLowerCase().contains("8 month")){
			return "8";
		}
		else if(e.toLowerCase().contains("7 month")){
			return "7";
		}
		else if(e.toLowerCase().contains("6 month")){
			return "6";
		}
		else if(e.toLowerCase().contains("5 month")){
			return "5";
		}
		else if(e.toLowerCase().contains("4 month")){
			return "4";
		}
		else if(e.toLowerCase().contains("3 month")){
			return "3";
		}
		else if(e.toLowerCase().contains("2 month")){
			return "2";
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
