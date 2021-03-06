package no5.uq;

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

public class UqUnData {

	//Structure: plan url .replace("plan.html","plan_display.html")
	
	//length: title url <p id=program-international-duration>
	//entry: div class=panel   contains:<h2>Entry requirements</h2>  ielts inside
	//fee: div id=cost pattern
	//faculty: <p id="program-international-faculty"> 
	//type: div id=page-head replace("\n","").trim()
	

	public static int MAX_THREAD=10;
	public static String[][] Data=getURL.getUnData();
	public static String FILE_PATH="D://Australia-Unis/UQ";
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
				
				HttpGet httpGet = new HttpGet(url[2]); 
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
			    
			    
			    
			    //**************************get school**********************
			    parser=Parser.createParser(htmls, "utf-8");
			    AndFilter SchoolFilter=new AndFilter(new TagNameFilter("p"),
		                new HasAttributeFilter("id","program-international-faculty"));
		        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
		        if(nodes3.size()>0)
		        {
		        	String school=html2Str(nodes3.elementAt(0).toHtml()).trim();
		        	//System.out.println("School:"+school);
		        	result.put("School",school);
		        }
		        
		      
		        
		      //**********************************get fee**********************
		        
		        parser=Parser.createParser(htmls, "utf-8");
			    AndFilter FeeFilter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("id","cost"));
		        NodeList nodes4 = parser.extractAllNodesThatMatch(FeeFilter);
		        //System.out.println(nodes4.size());
		        if(nodes4.size()>0)
		        {
		        	String feep=nodes4.elementAt(0).toHtml();
		        	//System.out.println(feep);
		        	Pattern p = Pattern.compile("AUD\\$ [0-9]+");
			    	Matcher m = p.matcher(feep);
			    	ArrayList<Integer> money=new ArrayList<Integer>();
			    	while (m.find()) 
			    	{
			    		String temp=m.group();
			    		//System.out.println(temp);
			    		money.add(Integer.parseInt(temp.replace("AUD$ ", "")));
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
		        
		    	
				
		        //**************************get entry**********************

		          parser=Parser.createParser(htmls, "utf-8");
		          String entryAll="";
		        	  parser=Parser.createParser(htmls, "utf-8");
		      	    AndFilter EntryFilter2=new AndFilter(new TagNameFilter("div"),
		                      new HasAttributeFilter("class","panel"));
		              NodeList nodesE = parser.extractAllNodesThatMatch(EntryFilter2);
		              //String Structure="";
		              if(nodesE.size()>0)
		              {
		              	for(int i=0;i<nodesE.size();i++)
		          	    {
		          	    	
		          	    	Node node=(Node)nodesE.elementAt(i);
		          	    	if(node.toHtml().contains("<h2>Entry requirements</h2>"))
		          	    	{
		          	    		entryAll=(html2Str(node.toHtml().replace(">", "> "))).replace("\r", "");
			          	    	entryAll=entryAll.replace("\n", " ");
			          	    	//entry=entry.replace("<br>", " ");
			          	    	entryAll=HTMLFilter(entryAll);
			          	    	break;
		          	    	}
		          	    	
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
		          
		          
		  		
		        //**************************get length**********************
		          parser=Parser.createParser(htmls, "utf-8");
		          AndFilter DurationFilter=new AndFilter(new TagNameFilter("p")
		          ,new HasAttributeFilter("id","program-international-duration"));
		          NodeList nodesF = parser.extractAllNodesThatMatch(DurationFilter);
		          //String Structure="";
		          if(nodesF.size()>0)
		          {
		          	String Duration=html2Str(nodesF.elementAt(0).toHtml()).trim();
		          	result.put("Length (months)",Duration);
		          }
		          
		        //**************************get Month**********************
				    parser=Parser.createParser(htmls, "utf-8");
				    AndFilter MonthFilter=new AndFilter(new TagNameFilter("p"),
			                new HasAttributeFilter("id","program-domestic-commencement"));
			        NodeList nodesM = parser.extractAllNodesThatMatch(MonthFilter);
			        if(nodesM.size()>0)
			        {
			        	String month=html2Str(nodesM.elementAt(0).toHtml()).trim();
			        	//System.out.println("School:"+school);
			        	if(month.contains("Semester 1"))
			        	result.put("Month of Entry","2");
			        }
		          
		        //**********************************get type**********************
		          String Type="";
		          parser=Parser.createParser(htmls, "utf-8");
		          TagNameFilter TypeFilter=new TagNameFilter("h1");
		          NodeList nodesT = parser.extractAllNodesThatMatch(TypeFilter);
		          //String Structure="";
		          if(nodesT.size()>0)
		          {
		        	  for(int i=0;i<nodesT.size();i++)
		        	  {
		        		  //System.out.println(nodesT.elementAt(i).toHtml());
		        		  if(nodesT.elementAt(i).toHtml().contains("<span id=\"program-title\">"))
		        		  {
		        			  Type=html2Str(nodesT.elementAt(i).toHtml()).replace("\n","").trim();
		        			  //System.out.println(Type);
		        			  //System.exit(0);
		        			  break;
		        		  }
		        		  
		        	  }
		          	
		          }
		        
		          
		          //program.html? 
		          String newURL=url[4];
		          if(newURL.contains("plan.html"))
		          {
		        	  newURL=newURL.replace("plan.html","plan_display.html");
		          }
		          else if(newURL.contains("program.html"))
		          {
		        	  newURL=newURL.replace("program.html","program_list.html");	
		          }
		          
		          while(true)
		          {
		        	  try{
		        		  httpGet = new HttpGet(newURL);
					  		response = httpclient.execute(httpGet);  
					  		entity = response.getEntity();
					  		
					  		htmls=null;
					  		if (entity != null) { 
					  		    htmls=EntityUtils.toString(entity).replace("\t", " ");
					  		    //System.out.println(htmls);
					  		    //System.out.println("Got entry.");
					  		     
					  		}
					  		break;
		        	  }
		        	  catch(Exception ee)
		        	  {
		        		  ee.printStackTrace();
		        	  }
		          }
		          
			  		
			  		
		          //**************************get structure**********************
					parser=Parser.createParser(htmls, "utf-8");
				    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
			                new HasAttributeFilter("id","content"));
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
			                structure=structure.split("function ()")[0];
			                structure=deleteSpaces(structure.trim());
			                if(structure.contains("Error: Page not found"))
			                System.out.println(newURL);
			        	    result.put("Structure",structure);
			        	    //System.out.println(structure);
			    	    	break;
			    	    }
			        }

					
		        
		        result.put("Level", "Undergraduate");
				result.put("Scholarship", "");
				result.put("Title",Type+"---"+url[3]);
			    result.put("Type",Type);
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
