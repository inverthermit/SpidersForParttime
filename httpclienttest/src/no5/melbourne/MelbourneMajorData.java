package no5.melbourne;
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
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class MelbourneMajorData {

	/**
	 * @param args
	 */
	/*
	
	
	 */
	public static int MAX_THREAD=60;
	public static String[][] Data=no5.melbourne.getURL.MajorData;
	public static String FILE_PATH="d:\\Australia-Unis\\MELBOURNE";
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
				
				
			    
		        
			    //**********************************get school**********************
		        
		        
				
				
				
				Parser parser=null;
			    HtmlPage page=new HtmlPage(parser); 
			    if(htmls.contains("February")||htmls.contains("february"))
			    {
			    	result.put("Month of Entry", "2");
			    	
			    }
			    else
			    {
			    	result.put("Month of Entry", "");
			    }
			    
			    //**********************************get length**********************
				parser=Parser.createParser(htmls, "utf-8");
			    AndFilter TFilter=new AndFilter(new TagNameFilter("table"),//table class="CSCPreviewTable grey"
		                new HasAttributeFilter("class","CSCTableSummary striped"));
		        NodeList nodes3 = parser.extractAllNodesThatMatch(TFilter);
		        if(nodes3.size()>0)
		        {
		        	parser=Parser.createParser(nodes3.elementAt(0).toHtml(), "utf-8");
			        TagNameFilter SchoolFilter=new TagNameFilter("tr");
			        NodeList nodes1 = parser.extractAllNodesThatMatch(SchoolFilter);
			        String structure="";
			        if(nodes1.size()>0)
			        {
			        	for(int i=0;i<nodes1.size();i++)
			        	{
			        		String row=nodes1.elementAt(i).toHtml();
			        		if(row.contains("Duration &amp; Credit Points:</th>"))//Structure
			        		{
			        			Pattern p = Pattern.compile("[0-9]+ month");
			    		    	Matcher m = p.matcher(htmls.replace(",", ""));
			    		    	ArrayList<Integer> money=new ArrayList<Integer>();
			    		    	while (m.find()) 
			    		    	{
			    		    		String length=m.group().replace(" month", "");
			    			        result.put("Length (months)",length);
			    		    		break;
			    		    	}
			        		}
			        		
			        	}
			        }
		        }
		        
			    
		      
		        
		        
			    //**********************************get entry structure**********************
		        //038AB MC-ARTPAE
		        parser=Parser.createParser(htmls, "utf-8");
		        TagNameFilter SchoolFilter=new TagNameFilter("tr");
		        NodeList nodes1 = parser.extractAllNodesThatMatch(SchoolFilter);
		        String structure="";
		        String majors="";
		        String course="";
		        String type="";
		        if(nodes1.size()>0)
		        {
		        	for(int i=0;i<nodes1.size();i++)
		        	{
		        		String row=nodes1.elementAt(i).toHtml();
		        		if(row.contains("Subject Options:</th>"))//Structure
		        		{
		        			structure=(html2Str(row.replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
		    	    		structure=HTMLFilter(structure);
		    	    		result.put("Structure",structure);
		        		}
		        		else if(row.contains("Related Course(s):</span></th>"))
		        		{
		        			type=html2Str(row);
		        			type=type.replace("Related Course(s):", "");
		          	    	type=HTMLFilter(type);
		          	    	result.put("Type",type.trim());
		        		}
		        		else if(row.contains("Majors/<br>Minors/<br>Specialisations </th>"))
		        		{
		        			majors=(html2Str(row.replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
		        			majors=HTMLFilter(majors);
		    	    		//result.put("Structure",majors);
		        		}
		        		//Course Structure &amp; Available Subjects:</span></th>
		        		else if(row.contains("Course Structure &amp; Available Subjects:</span></th>"))
		        		{
		        			course=(html2Str(row.replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
		        			course=HTMLFilter(course);
		    	    		//result.put("Structure",majors);
		        		}
		        	}
		        	if(structure.equals("")&&(!majors.equals("")))
		        	{
		        		result.put("Structure",majors);
		        	}
		        	else if(structure.equals("")&&(majors.equals("")))
		        	{
		        		result.put("Structure",course);
		        	}
		        }
		        
			    
			    
		        
		      //**********************************get fee**********************
		        
		    	Pattern p = Pattern.compile("&pound;[0-9]+");
		    	Matcher m = p.matcher(htmls.replace(",", ""));
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
		    	 	System.out.println(max); 
		    	 	result.put("Tuition Fee", ""+max);
		    	 }
		        
		      
			  	

				

		          //****************IELTS
		        result.put("IELTS Average Requirement","6.5");
		        result.put("IELTS Lowest Requirement", "6.0");
		          
		        
		        result.put("Level", "Postgraduate");
				result.put("Scholarship", "");
                //**************************get title & type**********************
			    //Related Course(s):
	        	result.put("Title",(url[1].split("/")[url[1].split("/").length-1]+" "+url[2]).replace("%21", "").replace("%2B", "+"));
			    
			   
				httpclient.close();
		        return result;
			}
			catch(Exception ee)
			{
				System.out.println("Retrying...");
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
