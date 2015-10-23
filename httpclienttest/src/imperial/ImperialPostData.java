package imperial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.*;

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

public class ImperialPostData {

	/**
	 * @param args
	 */
	
	public static int MAX_THREAD=60;
	public static String[][] Data=imperial.getURL.PostData;
	public static String FILE_PATH="d:\\IMPERIAL";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WriteToExcel();

	}
	public static WritableSheet sheet=null;
	public static void WriteToExcel()
	{
		File outputFile = new File(FILE_PATH + "\\" + "Postgen_data.xls");
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
							HashMap<String,String> DataMap=ImperialGetDetails(data);
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
	
	
	
	public static HashMap<String,String> ImperialGetDetails(String[] url)
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
			  //IELTS:Standard	Higher  standard College requirement
				//6.5 overall (minimum 6.0 in all elements)	 7.0 overall (minimum 6.5 in all elements)
				//Entry: div class item   h3 class item-header contains Entry requirements
				//Structure:  div class item   h3 class item-header contains Structure
				
				 //**************************get structure & entry**********************
				parser=Parser.createParser(htmls, "utf-8");
			    AndFilter Filter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("class","item"));
		        NodeList nodes5 = parser.extractAllNodesThatMatch(Filter);
		        //String Structure="";
		        if(nodes5.size()>0)
		        {
		        	for(int i=0;i<nodes5.size();i++)
		    	    {
		    	    	
		    	    	Node node=(Node)nodes5.elementAt(i);
		    	    	if(node.toHtml().contains("<h3 class=\"item-header\">Structure</h3>"))
		    	    	{
		    	    		String structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
		                    structure=HTMLFilter(structure);
		            	    result.put("Structure",structure.trim());
		    	    	}
		    	    	else if(node.toHtml().contains("<h3 class=\"item-header\">Entry requirements</h3>"))
		    	    	{
		    	    		Node tablenode=node;
		                    String entry=(html2Str(tablenode.toHtml())).replace("\r", "");
		        	    	entry=entry.replace("\n", " ");
		        	    	//entry=entry.replace("<br>", " ");
		        	    	entry=HTMLFilter(entry);
		                    //System.out.println(entry);
		                    result.put("Academic Entry Requirement",entry);
		    	    		
		                    //*******************IELTS*********************
		                    
		                    
		        	    	if(entry.contains("standard College requirement"))
		        	    	{
		        	    		result.put("IELTS Average Requirement", "6.5");
		        	            result.put("IELTS Lowest Requirement", "6.0");
		        	    	}
		        	    	else if(entry.contains("higher College requirement"))
		        	    	{
		        	    		result.put("IELTS Average Requirement", "7.0");
		        	            result.put("IELTS Lowest Requirement","6.5");
		        	    	}
		                    
		    	    	}
		                
		    	    }
		        }
		        result.put("Level", "Postgraduate");
		        //result.put("IELTS Average Requirement", "");
				//result.put("IELTS Lowest Requirement", "");
				result.put("Scholarship", "");
				//fee website http://www3.imperial.ac.uk/studentfinance/2015-16tuitionfees
				result.put("Title",url[2]);
			    result.put("Type",url[3]);
			    result.put("School", url[4]);
			    
			    //Length
			    int[] LenNum={3,4,6,8,16,21,23};
			    int[] YearNum={1,2,3,4};
			    int LenFlag=0;
			    for(int lencount=0;lencount<LenNum.length;lencount++)
			    {
			    	if(url[5].contains(LenNum[lencount]+" month"))
			    	{
			    		result.put("Length (months)",LenNum[lencount]+"");
			    		LenFlag=1;
			    		break;
			    	}
			    }
			    if(LenFlag==0)
			    {
			    	for(int lencount=0;lencount<YearNum.length;lencount++)
				    {
				    	if(url[5].contains(YearNum[lencount]+"Y"))
				    	{
				    		result.put("Length (months)",YearNum[lencount]*12+"");
				    		LenFlag=1;
				    		break;
				    	}
				    }
			    }
			    if(LenFlag==0)
			    {
			    	result.put("Length (months)",url[5]);
			    }
			    
				httpclient.close();
		        return result;
			}
			catch(Exception ee)
			{
				System.out.println("Retrying...");
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