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
	public static String[][] UnData={
		{"1","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/human-sciences/alpha/A/header_search/","Anatomy, Developmental & Human Biology","BSc","0"},
		{"2","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/ancient-history/alpha/A/header_search/","Ancient History","BA","0"},
		{"3","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/biochemistry-msci/alpha/B/header_search/","Biochemistry","MSc","0"},
		{"4","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/biochemistry/alpha/B/header_search/","Biochemistry","BSc","0"},
		{"5","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/biomedical-engineering-meng/alpha/B/header_search/","Biomedical Engineering","MEng","0"},
		{"6","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/biomedical-engineering/alpha/B/header_search/","Biomedical Engineering","BEng","0"},
		{"7","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/biomedical-science/alpha/B/header_search/","Biomedical Science","BSc","0"},
		{"8","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/business-management/alpha/B/header_search/","Business Management","BSc","0"},
		{"9","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/chemistry-bsc/alpha/C/header_search/","Chemistry","BSc","0"},
		{"10","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/chemistry-msci/alpha/C/header_search/","Chemistry","MSc","0"},
		{"11","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/chemistry-biomedicine-msci/alpha/C/header_search/","Chemistry with Biomedicine","MSc","0"},
		{"12","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/chemistry-biomedicine-bsc/alpha/C/header_search/","Chemistry with Biomedicine","BSc","0"},
		{"13","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/classical-and-modern-greek-studies/alpha/C/header_search/","Classical and Modern Greek Studies","BA","0"},
		{"14","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/classical-archaeology/alpha/C/header_search/","Classical Archaeology","BA","0"},
		{"15","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/classical-studies/alpha/C/header_search/","Classical Studies","BA","0"},
		{"16","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/classical-studies-and-comparative-literature/alpha/C/header_search/","Classical Studies & Comparative Literature","BA","0"},
		{"17","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/classical-studies-and-french/alpha/C/header_search/","Classical Studies & French with a year abroad","BA","0"},
		{"18","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/classical-studies-with-english/alpha/C/header_search/","Classical Studies with English","BA","0"},
		{"19","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/classics-greek-and-latin/alpha/C/header_search/","Classics (Greek & Latin)","BA","0"},
		{"20","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/comparative-literature/alpha/C/header_search/","Comparative Literature","BA","0"},
		{"21","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/comparative-literature-with-film-studies/alpha/C/header_search/","Comparative Literature with Film Studies","BA","0"},
		{"22","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-msci/alpha/C/header_search/","Computer Science","MSc","0"},
		{"23","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science/alpha/C/header_search/","Computer Science","BSc","0"},
		{"24","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-with-a-year-abroad/alpha/C/header_search/","Computer Science with a year abroad","BSc","0"},
		{"25","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-with-a-year-in-industry/alpha/C/header_search/","Computer Science with a year in industry","BSc","0"},
		{"26","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-with-intelligent-systems/alpha/C/header_search/","Computer Science with Intelligent Systems","BSc","0"},
		{"27","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-with-management/alpha/C/header_search/","Computer Science with Management","BSc","0"},
		{"28","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-with-management-with-a-year-abroad/alpha/C/header_search/","Computer Science with Management and a year abroad","BSc","0"},
		{"29","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-with-management-with-a-year-in-industry/alpha/C/header_search/","Computer Science with Management and a year in industry","BSc","0"},
		{"30","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/computer-science-with-robotics/alpha/C/header_search/","Computer Science with Robotics","BSc","0"},
		{"31","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/dentistry/alpha/D/header_search/","Dentistry","BDS","0"},
		{"32","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/enhanced-support-dentistry/alpha/D/header_search/","Dentistry (Enhanced Support Programme)","BDS","0"},
		{"33","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/dentistry-entry-programme-for-medical-graduates/alpha/D/header_search/","Dentistry Entry Programme for Medical Graduates","BDS","0"},
		{"34","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/dentistry-graduate-professional-entry-programme/alpha/D/header_search/","Dentistry Graduate/Professional Entry Programme","BDS","0"},
		{"35","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/human-sciences/alpha/D/header_search/","Developmental & Human Biology","BSc","0"},
		{"36","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/digital-culture/alpha/D/header_search/","Digital Culture","BA","0"},
		{"37","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/economics-and-management-bsc/alpha/E/header_search/","Economics & Management","BSc","0"},
		{"38","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/political-economy/alpha/E/header_search/","Economics (BA/BSc Politics of the International Economy)","BA","0"},
		{"39","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/electronic-and-information-engineering-beng/alpha/E/header_search/","Electronic & Information Engineering","BEng","0"},
		{"40","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/electronic-and-information-engineering-meng/alpha/E/header_search/","Electronic & Information Engineering","MEng","0"},
		{"41","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/electronic-engineering-beng/alpha/E/header_search/","Electronic Engineering","BEng","0"},
		{"42","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/electronic-engineering-meng/alpha/E/header_search/","Electronic Engineering","MEng","0"},
		{"43","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/electronic-engineering-with-management-beng/alpha/E/header_search/","Electronic Engineering with Management","BEng","0"},
		{"44","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/electronic-engineering-with-management-meng/alpha/E/header_search/","Electronic Engineering with Management","MEng","0"},
		{"45","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english/alpha/E/header_search/","English","BA","0"},
		{"46","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-language-and-linguistics/alpha/E/header_search/","English Language & Linguistics","BA","0"},
		{"47","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-and-american-law/alpha/E/header_search/","English Law & American Law","LLB","0"},
		{"48","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-and-french-law/alpha/E/header_search/","English Law & French Law","LLB","0"},
		{"49","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-and-german-law/alpha/E/header_search/","English Law & German Law","LLB","0"},
		{"50","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-with-australian-law/alpha/E/header_search/","English Law with Australian Law","LLB","0"},
		{"51","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-with-film-studies/alpha/E/header_search/","English with Film Studies","BA","0"},
		{"52","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/enhanced-support-dentistry/alpha/E/header_search/","Enhanced Support Dentistry Programme","BDS","0"},
		{"53","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/european-politics/alpha/E/header_search/","European Politics","BA","0"},
		{"54","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/european-studies-french/alpha/E/header_search/","European Studies (French pathway) with a year abroad","BA","0"},
		{"55","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/european-studies-german/alpha/E/header_search/","European Studies (German pathway) with a year abroad","BA","0"},
		{"56","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/european-studies-spanish/alpha/E/header_search/","European Studies (Spanish pathway) with a year abroad","BA","0"},
		{"57","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/emdp/alpha/E/header_search/","Extended Medical Degree Programme","","0"},
		{"58","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/film-studies/alpha/F/header_search/","Film Studies","BA","0"},
		{"59","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/international-foundation-programme-in-humanities-and-social-sciences/alpha/F/header_search/","Foundation Programme","","0"},
		{"60","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-and-german-with-a-year-abroad/alpha/F/header_search/","French & German with a year abroad","BA","0"},
		{"61","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-and-history/alpha/F/header_search/","French & History with a year abroad","BA","0"},
		{"62","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-and-management-with-a-year-abroad/alpha/F/header_search/","French & Management with a year abroad","BA","0"},
		{"63","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-and-philosophy/alpha/F/header_search/","French & Philosophy with a year abroad","BA","0"},
		{"64","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-four-year/alpha/F/header_search/","French (four year) with a year abroad","BA","0"},
		{"65","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-three-year/alpha/F/header_search/","French (three year)","BA","0"},
		{"66","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-and-spanish/alpha/F/header_search/","French and Spanish with a year abroad","BA","0"},
		{"67","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-with-english/alpha/F/header_search/","French with English with a year abroad","BA","0"},
		{"68","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/french-with-film-studies/alpha/F/header_search/","French with Film Studies with a year abroad","BA","0"},
		{"69","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/geography-bsc/alpha/G/header_search/","Geography","BSc","0"},
		{"70","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/geography/alpha/G/header_search/","Geography","BA","0"},
		{"71","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-and-history/alpha/G/header_search/","German & History with a year abroad","BA","0"},
		{"72","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-and-music/alpha/G/header_search/","German & Music with a year abroad","BA","0"},
		{"73","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-and-philosophy/alpha/G/header_search/","German & Philosophy with a year abroad","BA","0"},
		{"74","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-and-portuguese/alpha/G/header_search/","German & Portuguese with a year abroad","BA","0"},
		{"75","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-and-management-with-a-year-abroad/alpha/G/header_search/","German and Management with a year abroad","BA","0"},
		{"76","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-and-spanish/alpha/G/header_search/","German and Spanish with a year abroad","BA","0"},
		{"77","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german/alpha/G/header_search/","German with a year abroad","BA","0"},
		{"78","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-with-english/alpha/G/header_search/","German with English with a year abroad","BA","0"},
		{"79","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-with-film-studies/alpha/G/header_search/","German with Film Studies with a year abroad","BA","0"},
		{"80","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/global-health-and-social-medicine-bsc/alpha/G/header_search/","Global Health & Social Medicine","BSc","0"},
		{"81","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/global-health-and-social-medicine-ba/alpha/G/header_search/","Global Health & Social Medicine","BA","0"},
		{"82","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/greek-with-english/alpha/G/header_search/","Greek with English","BA","0"},
		{"83","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/history/alpha/H/header_search/","History","BA","0"},
		{"84","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/history-and-international-relations/alpha/H/header_search/","History and International Relations","BA","0"},
		{"85","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/human-physiology/alpha/H/header_search/","Human Physiology","MSc","0"},
		{"86","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/human-sciences/alpha/H/header_search/","Human Sciences","BSc","0"},
		{"87","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/international-development/alpha/I/header_search/","International Development","BA","0"},
		{"88","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/international-foundation-programme-in-humanities-and-social-sciences/alpha/I/header_search/","International Foundation Programme in Humanities & Social Sciences","","0"},
		{"89","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/international-management/alpha/I/header_search/","International Management","BSc","0"},
		{"90","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/international-relations/alpha/I/header_search/","International Relations","BA","0"},
		{"91","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/international-science-foundation-programme/alpha/I/header_search/","International Science Foundation Programme","","0"},
		{"92","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/law/alpha/L/header_search/","Law","LLB","0"},
		{"93","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-and-american-law/alpha/L/header_search/","Law (English Law & American Law)","LLB","0"},
		{"94","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-and-french-law/alpha/L/header_search/","Law (English Law & French Law)","LLB","0"},
		{"95","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-and-german-law/alpha/L/header_search/","Law (English Law & German Law)","LLB","0"},
		{"96","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/english-law-with-australian-law/alpha/L/header_search/","Law (English Law with Australian Law)","LLB","0"},
		{"97","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/politics-philosophy-and-law/alpha/L/header_search/","Law (Politics, Philosophy &)","LLB","0"},
		{"98","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/law-with-american-legal-studies/alpha/L/header_search/","Law with American Legal Studies","LLB","0"},
		{"99","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/law-with-european-legal-studies/alpha/L/header_search/","Law with European Legal Studies","LLB","0"},
		{"100","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/law-with-transnational-legal-studies/alpha/L/header_search/","Law with Transnational Legal Studies","LLB","0"},
		{"101","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/liberal-arts/alpha/L/header_search/","Liberal Arts","BA","0"},
		{"102","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/german-and-management-with-a-year-abroad/alpha/M/header_search/","Management & German","BA","0"},
		{"103","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/spanish-and-management-with-a-year-abroad/alpha/M/header_search/","Management & Spanish","BA","0"},
		{"104","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/mathematics/alpha/M/header_search/","Mathematics","BSc","0"},
		{"105","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/mathematics-msci/alpha/M/header_search/","Mathematics","MSc","0"},
		{"106","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/mathematics-and-philosophy/alpha/M/header_search/","Mathematics & Philosophy","BA","0"},
		{"107","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/mathematics-and-physics-msci/alpha/M/header_search/","Mathematics & Physics","MSc","0"},
		{"108","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/mathematics-and-physics/alpha/M/header_search/","Mathematics & Physics","BSc","0"},
		{"109","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/mathematics-with-management-and-finance/alpha/M/header_search/","Mathematics with Management & Finance","BSc","0"},
		{"110","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/mathematics-with-statistics/alpha/M/header_search/","Mathematics with Statistics","BSc","0"},
		{"111","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/medicine/alpha/M/header_search/","Medicine","","0"},
		{"112","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physiology/alpha/M/header_search/","Medical Physiology","BSc","0"},
		{"113","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/emdp/alpha/M/header_search/","Medicine Extended Degree Programme","","0"},
		{"114","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/medicine-graduate-professional-entry-programme/alpha/M/header_search/","Medicine Graduate/Professional Entry Programme","","0"},
		{"115","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/medicine-maxfax-entry-programme/alpha/M/header_search/","Medicine Maxfax Entry Programme","","0"},
		{"116","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/global-health-and-social-medicine-bsc/alpha/M/header_search/","Medicine, Health & Society","BSc","0"},
		{"117","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/midwifery-studies-with-registration/alpha/M/header_search/","Midwifery Studies with Registration","BSc","0"},
		{"118","http://www.kcl.ac.uk//prospectus/graduate/index/name/midwifery-with-registration-graduate-entry/alpha/M/header_search/","Midwifery with registration (graduate entry)","PG","0"},
		{"119","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/molecular-genetics/alpha/M/header_search/","Molecular Genetics","BSc","0"},
		{"120","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/pharmacology-and-molecular-genetics/alpha/M/header_search/","Molecular Genetics & Pharmacology","BSc","0"},
		{"121","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/music/alpha/M/header_search/","Music","","0"},
		{"122","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/neuroscience-msci/alpha/N/header_search/","Neuroscience","MSc","0"},
		{"123","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/neuroscience/alpha/N/header_search/","Neuroscience","BSc","0"},
		{"124","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/nursing-studies/alpha/N/header_search/","Nursing Studies (for qualified healthcare professionals)","BSc","0"},
		{"125","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/nursing-studies-with-registration-adult-nursing/alpha/N/header_search/","Nursing Studies with Registration - Adult nursing","BSc","0"},
		{"126","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/nursing-studies-with-registration-child-nursing-bsc/alpha/N/header_search/","Nursing Studies with Registration - Children's nursing","BSc","0"},
		{"127","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/nursing-studies-with-registration-mental-health-nursing/alpha/N/header_search/","Nursing Studies with Registration - Mental Health nursing","BSc","0"},
		{"128","http://www.kcl.ac.uk//prospectus/graduate/index/name/nursing-with-registration-graduate-entry/alpha/N/header_search/","Nursing with registration (graduate entry)","PG","0"},
		{"129","http://www.kcl.ac.uk//prospectus/graduate/index/name/nursing-adult-pg-dip/alpha/N/header_search/","Nursing with registration - adult (graduate entry)","PG","0"},
		{"130","http://www.kcl.ac.uk//prospectus/graduate/index/name/nursing-child-pg-dip/alpha/N/header_search/","Nursing with registration - child (graduate entry)","PG","0"},
		{"131","http://www.kcl.ac.uk//prospectus/graduate/index/name/nursing-mental-health-pg-dip/alpha/N/header_search/","Nursing with registration - mental health (graduate entry)","PG","0"},
		{"132","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/nutrition/alpha/N/header_search/","Nutrition","BSc","0"},
		{"133","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/nutrition-and-dietetics/alpha/N/header_search/","Nutrition & Dietetics","BSc","0"},
		{"134","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/pharmacology-msci/alpha/P/header_search/","Pharmacology","MSc","0"},
		{"135","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/pharmacology/alpha/P/header_search/","Pharmacology","BSc","0"},
		{"136","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/pharmacology-and-molecular-genetics/alpha/P/header_search/","Pharmacology & Molecular Genetics","BSc","0"},
		{"137","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/pharmacy/alpha/P/header_search/","Pharmacy","","0"},
		{"138","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/philosophy/alpha/P/header_search/","Philosophy","BA","0"},
		{"139","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/philosophy-and-spanish/alpha/P/header_search/","Philosophy & Spanish with a year abroad","BA","0"},
		{"140","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/philosophy-politics-and-economics/alpha/P/header_search/","Philosophy, Politics and Economics","BA","0"},
		{"141","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics-msci/alpha/P/header_search/","Physics","MSc","0"},
		{"142","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics/alpha/P/header_search/","Physics","BSc","0"},
		{"143","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics-and-philosophy/alpha/P/header_search/","Physics & Philosophy","BSc","0"},
		{"144","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics-and-philosophy-with-a-year-abroad/alpha/P/header_search/","Physics & Philosophy with a year abroad","BSc","0"},
		{"145","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics-with-a-year-abroad/alpha/P/header_search/","Physics with a year abroad","BSc","0"},
		{"146","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics-with-medical-applications/alpha/P/header_search/","Physics with Medical Applications","BSc","0"},
		{"147","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics-with-theoretical-physics-msci/alpha/P/header_search/","Physics with Theoretical Physics","MSc","0"},
		{"148","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physics-with-theoretical-physics/alpha/P/header_search/","Physics with Theoretical Physics","BSc","0"},
		{"149","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/human-physiology/alpha/P/header_search/","Physiology","MSc","0"},
		{"150","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/physiotherapy/alpha/P/header_search/","Physiotherapy","BSc","0"},
		{"151","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/political-economy/alpha/P/header_search/","Politics (BA/BSc Political Economy)","BA","0"},
		{"152","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/politics/alpha/P/header_search/","Politics (BA International Politics)","BA","0"},
		{"153","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/european-politics/alpha/P/header_search/","Politics (European)","BA","0"},
		{"154","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/war-studies/alpha/P/header_search/","Politics (War Studies)","BA","0"},
		{"155","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/politics-philosophy-and-law/alpha/P/header_search/","Politics, Philosophy & Law","LLB","0"},
		{"156","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/portuguese-and-french-with-a-year-abroad/alpha/P/header_search/","Portuguese & French with a year abroad","BA","0"},
		{"157","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/psychology/alpha/P/header_search/","Psychology","BSc","0"},
		{"158","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/religion-philosophy-and-ethics/alpha/R/header_search/","Religion, Philosophy & Ethics","BA","0"},
		{"159","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/religion-politics-and-society/alpha/R/header_search/","Religion, Politics & Society","BA","0"},
		{"160","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/robotics-and-intelligent-systems/alpha/R/header_search/","Robotics & Intelligent Systems","MSc","0"},
		{"161","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/international-science-foundation-programme/alpha/S/header_search/","Science Foundation Programme","","0"},
		{"162","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/spanish-latin-american-studies/alpha/S/header_search/","Spanish & Latin American Studies with a year abroad","BA","0"},
		{"163","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/spanish-and-portuguese-with-a-year-abroad/alpha/S/header_search/","Spanish & Portuguese with a year abroad","BA","0"},
		{"164","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/spanish-and-management-with-a-year-abroad/alpha/S/header_search/","Spanish and Management with a year abroad","BA","0"},
		{"165","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/spanish-with-english/alpha/S/header_search/","Spanish with English with a year abroad","BA","0"},
		{"166","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/spanish-with-film-studies/alpha/S/header_search/","Spanish with Film Studies with a year abroad","BA","0"},
		{"167","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/theology/alpha/T/header_search/","Theology","BA","0"},
		{"168","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/war-studies/alpha/W/header_search/","War Studies","BA","0"},
		{"169","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/war-studies-and-history/alpha/W/header_search/","War Studies & History","BA","0"},
		{"170","http://www.kcl.ac.uk//prospectus/undergraduate/index/name/war-studies-and-philosophy/alpha/W/header_search/","War Studies & Philosophy","BA","0"}
	};
    public static String[][] PostData={
    	
    };
	
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
