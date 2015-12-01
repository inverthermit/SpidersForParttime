package kent;

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

public class KentPostData {

	/**
	 * @param args
	 */
	/*
	
	
	 */
	public static int MAX_THREAD=60;
	public static String[][] Data=kent.getURL.PostData;
	public static String FILE_PATH="d:\\KENT";
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
							HashMap<String,String> DataMap=KentGetDetails(data);
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
	
	

	public static HashMap<String,String> KentGetDetails(String[] url)
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
			    
			  //**************************get school && length**********************
			    parser=Parser.createParser(htmls, "utf-8");
			    AndFilter SchoolFilter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("class","key-facts"));
		        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
		        if(nodes3.size()>0)
		        {
		        	
		        	parser=Parser.createParser(nodes3.elementAt(0).toHtml(), "utf-8");
		        	TagNameFilter liFilter=new TagNameFilter("li");
			        NodeList nodes2 = parser.extractAllNodesThatMatch(liFilter);
			        for(int j=0;j<nodes2.size();j++)
			        {//<strong>Schools:</strong>
			        	if(nodes2.elementAt(j).toHtml().contains("<strong>School:</strong>")||
			        			nodes2.elementAt(j).toHtml().contains("<strong>Schools:</strong>")
			        			)
			        	{
			        		String school=nodes2.elementAt(j).toHtml().replace("<strong>School:</strong>","").replace("<strong>Schools:</strong>", "");
			        		school=html2Str(school).replace("\n", " ").trim();
				        	System.out.println("School:"+school);
				        	result.put("School",school);
				        	
			        	}
			        	else if(nodes2.elementAt(j).toHtml().contains("<strong>Duration:</strong>"))
			        	{
			        		String length=nodes2.elementAt(j).toHtml().replace("<strong>Duration:</strong>","");
			        		length=html2Str(length).trim();
			        		result.put("Length (months)",length);
			        	}
		        	
			        }
		        }
		        
		      //**********************************get fee**********************
		        //div id fees-tables
		        parser=Parser.createParser(htmls, "utf-8");
			    AndFilter FeeFilter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("id","fees-tables"));
		        NodeList nodes9 = parser.extractAllNodesThatMatch(FeeFilter);
		        
		        if(nodes9.size()>0)
		        {
		        	for(int i=0;i<nodes9.size();i++)
		    	    {
		    	    	
		    	    	Node node=(Node)nodes9.elementAt(i);
		    	    	Pattern p = Pattern.compile("&pound;[0-9]+");
				    	Matcher m = p.matcher(node.toHtml().replace(",", ""));
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


		    	    	break;
		    	    }
		        }
		        
		    	
		        
		      
			  	
				 //**************************get structure**********************
				parser=Parser.createParser(htmls.replace("<section", "<div").replace("</section", "</div"), "utf-8");
			    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
		                new HasAttributeFilter("id","structure"));
		        NodeList nodes5 = parser.extractAllNodesThatMatch(StructureFilter);
		        String structure="";
		        if(nodes5.size()>0)
		        {
		        	for(int i=0;i<nodes5.size();i++)
		    	    {
		    	    	
		    	    	Node node=(Node)nodes5.elementAt(i);
		    	    	//System.out.println(node.toHtml());
		    	    	//System.out.println("Structure:\n");
		    	    	//System.out.println(node.toHtml().replaceAll("(<table([^>]*?)>)(.*?)(</table>)",""));
		                structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
		                structure=HTMLFilter(structure)+" ";
		                //System.out.println(structure);
		                FileOutputStream o=new FileOutputStream(new File(FILE_PATH+"/structure/"+url[0]+"structure.txt"));
		        		o.write(structure.getBytes());
		        		o.close();
		        	    result.put("Structure",url[0]+"structure.txt");
		    	    	break;
		    	    }
		        }
		        if(structure.equals(""))
		        {
		        	parser=Parser.createParser(htmls.replace("<section", "<div").replace("</section", "</div"), "utf-8");
				    StructureFilter=new AndFilter(new TagNameFilter("div"),
			                new HasAttributeFilter("id","overview"));
			        nodes5 = parser.extractAllNodesThatMatch(StructureFilter);
			        if(nodes5.size()>0)
			        {
			        	for(int i=0;i<nodes5.size();i++)
			    	    {
			    	    	
			    	    	Node node=(Node)nodes5.elementAt(i);
			    	    	//System.out.println(node.toHtml());
			    	    	//System.out.println("Structure:\n");
			    	    	//System.out.println(node.toHtml().replaceAll("(<table([^>]*?)>)(.*?)(</table>)",""));
			                structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
			                structure=HTMLFilter(structure)+" ";
			                //System.out.println(structure);
			                FileOutputStream o=new FileOutputStream(new File(FILE_PATH+"/structure/"+url[0]+"structure.txt"));
			        		o.write(structure.getBytes());
			        		o.close();
			        	    result.put("Structure",url[0]+"structure.txt");
			    	    	break;
			    	    }
			        }
		        }

				

		        //**************************get entry**********************

		          parser=Parser.createParser(htmls.replace("<section", "<div").replace("</section", "</div"), "utf-8");
		          String entryAll="";
		      	    AndFilter EntryFilter2=new AndFilter(new TagNameFilter("div"),
		                      new HasAttributeFilter("id","entry-requirements"));
		              NodeList nodesE = parser.extractAllNodesThatMatch(EntryFilter2);
		              //String Structure="";
		              if(nodesE.size()>0)
		              {
		              	for(int i=0;i<nodesE.size();i++)
		          	    {
		          	    	
		          	    	Node node=(Node)nodesE.elementAt(i);
		          	    	//System.out.println(node.toHtml());
		          	    	entryAll=(html2Str(node.toHtml().replace(">", "> "))).replace("\r", "");
		          	    	entryAll=entryAll.replace("\n", " ");
		          	    	//entry=entry.replace("<br>", " ");
		          	    	entryAll=HTMLFilter(entryAll);
		          	    	FileOutputStream o=new FileOutputStream(new File(FILE_PATH+"/entry/"+url[0]+"entry.txt"));
			        		o.write(entryAll.getBytes());
			        		o.close();
			          result.put("Academic Entry Requirement",url[0]+"entry.txt");
		          	    	break;
		          	    }
		              }
		          
		              
		          //****************IELTS
		          /*
		          Undergraduate (NQF6) degree	6.5 IELTS (with a minimum of 6.0 in Reading and Writing and 5.5 in Speaking and Listening)	62 including 60 in each subtest	176 (with a minimum of 169 in Reading and Writing and 162 in Speaking and Listening)
		          School of Social Policy, Sociology and Social Research BA Social Work	7.0 IELTS (with a minimum of 6.5 in each component)	68 including 65 in each subject	185 (with a minimum of 176 in each component)
		          */
		          
		          
		          //*********************IELTS********************
		          //http://www.kent.ac.uk/ems/eng-lang-reqs/ielts.html
		          /*	result.put("IELTS Average Requirement","6.5");
		              
		      	    result.put("IELTS Lowest Requirement", "6.0");*/
		          
		          
		          
		  		
		        
		        
		        //finance/
		        
		        
		        
		        
		        result.put("Level", "Postgraduate");
				result.put("Scholarship", "");
				result.put("Title",url[2]);
			    result.put("Type",url[3]);
			   
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
