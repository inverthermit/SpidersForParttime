package no5.unsw;

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
import no5.uq.getURL;

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

public class UnswPostData {

	/*
	 * div class="internalContentWrapper"
	 * split("a name=")
	 * structure <h2>Program Structure</h2>
	 * <p><strong>Typical Duration:</strong>&nbsp;5 Years&nbsp;&nbsp;
	 * 6.5 6.0
	 * Master of Psychology (Clinical, Forensic & Organisational) including combined PhD/Master 7.0 7.0
	 * Fee https://student.unsw.edu.au/fees-international
	 * 
	 * Entry Requirement是一张大表，
Chinese Gaokao:http://www.international.unsw.edu.au/media/uploads/file/2015/07/06/2016_Gaokao_TABLE.pdf
Accepted Qualifications The qualifications listed below will be considered for entry to undergraduate studies. Students are assessed on actual results achieved and not simply on their completion of the qualification. Applicants who hold one or more of the qualifications listed are not guaranteed admission. If you have completed a qualification not listed below please contact the UNSW Direct Admissions Office. See http://s3.amazonaws.com/media.international.unsw.edu.au/uploads/file/2015/08/06/2016_Undergrad_Entry_Table.pdf
*/
	public static int MAX_THREAD=60;
	public static String[][] Data=no5.unsw.getURL.PostRData;
	public static String FILE_PATH="D://Australia-Unis/UNSW";
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
		HashMap<String,String> DataMap=ManchesterGetDetails(manchester.getURL.UnData[0]);
		putIntoWorkbook(DataMap,Integer.parseInt(manchester.getURL.UnData[0][0]));
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
							HashMap<String,String> DataMap=ManchesterGetDetails(data);
							putIntoWorkbook(DataMap,Integer.parseInt(data[0]));
						}
						else{
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
	
	
	
	public static HashMap<String,String> ManchesterGetDetails(String[] url)
	{
		if(url[1].equals(""))
			return null;
		while(true)
		{
			try{
				
				HashMap<String,String> result=new HashMap<String,String>();
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();  
				CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
				
				HttpGet httpGet = new HttpGet(url[1]); 
				HttpResponse response = httpclient.execute(httpGet);  
				HttpEntity entity = response.getEntity();
				
				String htmls=null;
				if (entity != null) { 
				    htmls=EntityUtils.toString(entity).replace("\t", " ");
				    //System.out.println(htmls);
				    
				     
				}
				//System.out.println("Got reply!");
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
			    
			    
			    
		        
		    	
				
		        
		          //****************IELTS
		         
		          	result.put("IELTS Average Requirement","6.5");
		              
		      	    result.put("IELTS Lowest Requirement", "5.0");
		          
		          
		  		
		        //**************************get length**********************
		      	  Pattern p = Pattern.compile("<p><strong>Typical Duration:</strong>&nbsp;[\\s\\S]*?&nbsp;&nbsp;");
			    	Matcher m = p.matcher(htmls);
			    	if (m.find()) 
			    	{
			    		String duration=m.group().replace("<p><strong>Typical Duration:</strong>&nbsp;", "").replace("&nbsp;", "");
			    	 	//System.out.println(duration); 
			    	 	result.put("Length (months)",duration);
			    	 }
			    	 
		         
		        
		          
			  		
			  		
		          //**************************get structure**********************
			    	parser=Parser.createParser(htmls, "utf-8");
				    AndFilter tableFilter=new AndFilter(new TagNameFilter("div"),
			                new HasAttributeFilter("class","internalContentWrapper"));
			        NodeList nodes4 = parser.extractAllNodesThatMatch(tableFilter);
			        if(nodes4.size()>0)
			        {
			        	htmls=nodes4.elementAt(0).toHtml();
			        }
			    	
					String[] as=htmls.split("a name=");
			        if(as.length>0)
			        {
			        	String description="";
			        	boolean flag=false;
			        	for(int i=0;i<as.length;i++)
			    	    {
			    	    	
			    	    	String temp=as[i];
			    	    	if(temp.contains("<h2>Program Structure</h2>"))
			    	    	{
			    	    		String structure=(html2Str(temp.replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
				                structure=HTMLFilter(structure);
				                //System.out.println(structure);
				                structure=deleteSpaces(structure.trim());
				                
				        	    result.put("Structure",structure);
				    	    	//break;
			    	    	}
			    	    	else if(temp.contains("<h2>Entry Requirements</h2>")//<h2>Program Description</h2>
			    	    			||temp.contains(">Admission Requirements</")
			    	    			||temp.contains(">Entry Requirements</"))
			    	    	{
			    	    		String entryAll=html2Str((temp.replace(">", "> "))).replace("\r", "");
			          	    	entryAll=entryAll.replace("\n", " ");
			          	    	//entry=entry.replace("<br>", " ");
			          	    	entryAll=HTMLFilter(entryAll);
		                        result.put("Academic Entry Requirement",entryAll);
		                        flag=true;
			    	    	}
			    	    	else if(temp.contains("<h2>Program Description</h2>"))
			    	    	{
			    	    		String entryAll=html2Str((temp.replace(">", "> "))).replace("\r", "");
			          	    	entryAll=entryAll.replace("\n", " ");
			          	    	//entry=entry.replace("<br>", " ");
			          	    	entryAll=HTMLFilter(entryAll);
		                        description=entryAll;
			    	    	}
			    	    	
			    	    }
			        	if(flag==false)
			        	{
			        		result.put("Academic Entry Requirement","Entry Requirements "+description);
			        	}
			        }

					
		        
		        result.put("Level", "Postgraduate");
				result.put("Scholarship", "");
				result.put("Title",url[2]+"|"+url[4]);
			    result.put("Type",url[3]);
			    result.put("School",url[5]);
				httpclient.close();
				System.out.println(url[0]+" done.");
		        return result;
			}
			catch(Exception ee)
			{
				System.out.println("Retrying...");
				System.out.println(url[2]+"||"+url[4]);
				ee.printStackTrace();
			}
		}
		
	}	
    
	
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
		
		public static String deleteSpaces(String input)
		{
			return input.replaceAll("\\n[\\s]+", "\n");
		}



}

