package no7.LJMU;

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

public class LJMUUnData {

	/*
	
	
	Type:<div class="m-course-options"> contains <h3>Course type</h3>
	
	School:<div class="m-course-options"> contains <h3>School</h3>
	
	Duration:<div class="m-course-options">
                            <h3>Study mode</h3> getLastYear

	Fee:    <div class="l-col-5"> contains <h3>Course fees</h3>

	
	Structure:  <div class="l-container l-container--inset-grey l-container--with-padding">
            contains <h2>What you will study on this degree</h2>

	
	Entry: <div class="l-col-8 l-padinfull l-col--right-shadow-border l-bigpad-right">
                <h2 id="require-section-title" data-id="panel-title">Entry requirements</h2>


	IELTS: Un 6.0 6.0 / in entry
	
	
	
	
	 */

	public static int MAX_THREAD=40;
	public static String[][] Data=getURL.UnData;
	public static String FILE_PATH="d:\\ANo7\\LJMU";
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
		        		new HasAttributeFilter("class","l-container l-container--inset-grey l-container--with-padding"));
		        NodeList nodes6 = parser.extractAllNodesThatMatch(FFilter);
		        
		        for(int i=0;i<nodes6.size();i++)
		        {
		        	if(nodes6.elementAt(i).toHtml().contains("<h2>What you will study on this degree</h2>"))
		        	{
		        		String row=html2Str(nodes6.elementAt(i).toHtml()).trim();
			    		String structure=(html2Str(row.replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
	    	    		structure=HTMLFilter(structure);
	    	    		result.put("Structure",structure);
	    	    		break;
		        	}
		    		
    	    		
		        }
		        
			    
			  //**********************************get Type&School&Duration**********************
				parser=Parser.createParser(htmls, "utf-8");
				AndFilter tFilter=new AndFilter(new TagNameFilter("div"),
		        		new HasAttributeFilter("class","m-course-options"));
		        NodeList nodes1 = parser.extractAllNodesThatMatch(tFilter);
		        for(int i=0;i<nodes1.size();i++)
		        {
		        	//System.out.println(nodes1.elementAt(0).toHtml());
		        	String text=nodes1.elementAt(i).toHtml();
		        	if(text.contains("<h3>Course type</h3>"))
		        		{
		        			String type=html2Str(text.replace("<h3>Course type</h3>",""))
		        					.trim().replaceAll("\n[\\s\\S]+", "");
		        			result.put("Type",type);
		        			//System.out.println(type);
		        		}
		        		else if(text.contains("<h3>School</h3>"))
		        		{
		        			String school=html2Str(text.replace("<h3>School</h3>","")).trim();
		        			result.put("School",school);
		        			//System.out.println(school);
		        		}
		        		else if(text.contains("<h3>Study mode</h3>"))
		        		{
		        			String length=getLastYear(text).trim();
				    		result.put("Length (months)",length);
				    		//System.out.println(length);
		        		}
		        	
		        }
		        
		      //**********************************get fee**********************
		        parser=Parser.createParser(htmls, "utf-8");
		        AndFilter FeeFilter=new AndFilter(new TagNameFilter("div"),
		        		new HasAttributeFilter("class","l-col-5"));
		        NodeList nodes2 = parser.extractAllNodesThatMatch(FeeFilter);
		        
		        for(int i=0;i<nodes2.size();i++)
		        {
		        	if(nodes2.elementAt(i).toHtml().contains("<h3>Course fees</h3>"))
		        	{
		        		String row=html2Str(nodes2.elementAt(0).toHtml());
		        		Pattern p = Pattern.compile("&#163;[0-9]+");
    			    	Matcher m = p.matcher(row.replace(",", ""));
    			    	ArrayList<Integer> money=new ArrayList<Integer>();
    			    	while (m.find()) 
    			    	{
    			    		money.add(Integer.parseInt(m.group().replace("&#163;", "")));
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
		        	}
		        	
		    		
		        }
	    		
		     
		         
		        //**********************************get Entry**********************
			        parser=Parser.createParser(htmls, "utf-8");
			        AndFilter SFilter=new AndFilter(new TagNameFilter("div"),
			        		new HasAttributeFilter("class","l-col-8 l-padinfull l-col--right-shadow-border l-bigpad-right"));
			        NodeList nodes3 = parser.extractAllNodesThatMatch(SFilter);
			        String entryAll="";
			        for(int i=0;i<nodes3.size();i++)
			        {
			        	if(nodes3.elementAt(i).toHtml().contains("Entry requirements</h2>"))
			        	{
			        		String row=html2Str(nodes3.elementAt(i).toHtml());
	    		    		entryAll=(html2Str(row.replace(">", "> "))).replace("\r", "");
		          	    	entryAll=entryAll.replace("\n", " ");
		          	    	entryAll=HTMLFilter(entryAll);
		          	    	result.put("Academic Entry Requirement",entryAll);
		          	    	break;
			        	}
			        	
			    		
			        }
			        
			        //****************IELTS
				       
		          	result.put("IELTS Average Requirement","6.0");
		              
		      	    result.put("IELTS Lowest Requirement", "5.5");
		          
		        
                //**************************get title & type**********************
			    
	        	result.put("Title",url[2]);
			    //result.put("Type",url[3]);
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
