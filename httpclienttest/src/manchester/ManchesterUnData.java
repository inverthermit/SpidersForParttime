package manchester;

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

public class ManchesterUnData {

	/*
	 1.School: <div id="schoolPanel"><p>This course is delivered by:</p><p id="schoolName"><a href="http://www.mace.manchester.ac.uk/">School of Mechanical, Aerospace and Civil Engineering</a></p></div>
	   Fee:replace(",",""),get pattern for money
	 2. url+"entry-requirements/"   div class="courseprofilecontent"
	    form method="post"  delete(or not)
	 
	 3.url+"course-details/"
	   div class="courseprofilecontent"
	 
	 */
	

	/**
	 * @param args
	 */
	
	public static int MAX_THREAD=60;
	public static String[][] Data=manchester.getURL.UnData;
	public static String FILE_PATH="d:\\MANCHESTER";
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
			    
			    //**************************get school**********************
			    parser=Parser.createParser(htmls, "utf-8");
			    AndFilter SchoolFilter=new AndFilter(new TagNameFilter("p"),
		                new HasAttributeFilter("id","schoolName"));
		        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
		        if(nodes3.size()>0)
		        {
		        	String school=html2Str(nodes3.toHtml()).trim();
		        	System.out.println("School:"+school);
		        	result.put("School",school);
		        }
		        
		      //**********************************get fee**********************
		        
		    	Pattern p = Pattern.compile("£[0-9]+");
		    	Matcher m = p.matcher(htmls.replace(",", ""));
		    	ArrayList<Integer> money=new ArrayList<Integer>();
		    	while (m.find()) 
		    	{
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
		        
		      
			  		
			  		httpGet = new HttpGet(url[1]+"course-details/"); 
			  		response = httpclient.execute(httpGet);  
			  		entity = response.getEntity();
			  		
			  		htmls=null;
			  		if (entity != null) { 
			  		    htmls=EntityUtils.toString(entity).replace("\t", " ");
			  		    //System.out.println(htmls);
			  		    
			  		     
			  		}
				 //**************************get structure**********************
				parser=Parser.createParser(htmls, "utf-8");
			    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("class","courseprofilecontent"));
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

				

		        httpGet = new HttpGet(url[1]+"/entry-requirements/"); 
		  		response = httpclient.execute(httpGet);  
		  		entity = response.getEntity();
		  		
		  		htmls=null;
		  		if (entity != null) { 
		  		    htmls=EntityUtils.toString(entity).replace("\t", " ");
		  		    //System.out.println(htmls);
		  		    System.out.println("Got entry.");
		  		     
		  		}
		        
		        //**************************get entry**********************

		          parser=Parser.createParser(htmls, "utf-8");
		          String entryAll="";
		        	  parser=Parser.createParser(htmls, "utf-8");
		      	    AndFilter EntryFilter2=new AndFilter(new TagNameFilter("div"),
		                      new HasAttributeFilter("class","courseprofilecontent"));
		              NodeList nodesE = parser.extractAllNodesThatMatch(EntryFilter2);
		              //String Structure="";
		              if(nodesE.size()>0)
		              {
		              	for(int i=0;i<nodesE.size();i++)
		          	    {
		          	    	
		          	    	Node node=(Node)nodesE.elementAt(i);
		          	    	entryAll=(html2Str(node.toHtml().replace(">", "> "))).replace("\r", "");
		          	    	entryAll=entryAll.replace("\n", " ");
		          	    	//entry=entry.replace("<br>", " ");
		          	    	entryAll=HTMLFilter(entryAll);
		          	    	break;
		          	    }
		              }
		          
		          result.put("Academic Entry Requirement",entryAll);
		          //****************IELTS
		          String International=entryAll;
		  		ArrayList<String> list = new ArrayList<String>();
		  		if(International.contains("7.5"))
		          {
		          	list.add("7.5");
		          }
		  		if(International.contains("7.0")||International.contains(" 7 "))
		          {
		          	list.add("7.0");
		          }
		          if(International.contains("6.5"))
		          {
		          	list.add("6.5");
		          }
		          if(International.contains("6.0")||International.contains(" 6 "))
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
		          
		          
		  		
		        
		        
		        //finance/
		        
		        
		        
		        
		        result.put("Level", "Undergraduate");
				result.put("Scholarship", "");
				result.put("Title",url[2]);
			    result.put("Type",url[3]);
			    //result.put("Length (months)",url[2]);
			    //result.put("School",url[4]);
			    if(url[1].contains("3-years"))
			    {
			    	result.put("Length (months)","36");
			    }
			    else if(url[1].contains("4-years"))
			    {
			    	result.put("Length (months)","48");
			    }
			    else
			    {
			    	result.put("Length (months)",url[1]);
			    }
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
