package no7.Glasgow;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class getURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub

		String url1="http://www.gcu.ac.uk/study/postgraduate/courses/search/?cp=";
		String url2="#CourseList_131225";
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		int count=1;
		for(int i=0;i<=22;i++)
		{
			int index=i;
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url1+index+url2);
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			
	       ArrayList<String> list=new ArrayList<String>();
		    Parser	parser=Parser.createParser(htmls, "utf-8");
	   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("tr"),
	                   new HasAttributeFilter("class","CourseListItemHead"));
	   	    
	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	   	    //System.out.println(nodes4.size());
	   	    for(int j=0;j<nodes4.size();j++)
	   	    {
	   	    	
	   	    	htmls=nodes4.elementAt(j).toHtml();
	   	    	
	   	    	//url
	   	    	String title="",url="";
	   	    	parser=Parser.createParser(htmls, "utf-8");
		        AndFilter uFilter=new AndFilter(new TagNameFilter("td"),
		        		new HasAttributeFilter("class","CourseListItemHeadTitle"));
		        NodeList nodes11 = parser.extractAllNodesThatMatch(uFilter);
		        if(nodes11.size()>0)
		        {
		        	title=html2Str(nodes11.elementAt(0).toHtml()).trim();
		        	parser=Parser.createParser(htmls, "utf-8");
			        AndFilter aFilter=new AndFilter(new TagNameFilter("a"),
			        		new HasAttributeFilter("href"));
			        NodeList nodes00 = parser.extractAllNodesThatMatch(aFilter);
			        if(nodes00.size()>0)
			        {
			        	LinkTag link=(LinkTag)nodes00.elementAt(0);
			   	    	//if(!link.getAttribute("href").equals("")&&!html2Str(link.toHtml()).contains("View this course"))
			   	    	{
			   	    		if(!list.contains(link.getAttribute("href")))
			   	    		{
			   	    			url=(link.getAttribute("href"));
			   	    			
			   	    		}
			   	    		
			   	    	}
			        }
			        
		        }
		        
		        //type
		        String type="";
	   	    	parser=Parser.createParser(htmls, "utf-8");
		        AndFilter tFilter=new AndFilter(new TagNameFilter("td"),
		        		new HasAttributeFilter("class","CourseListItemHeadAward"));
		        NodeList nodes22 = parser.extractAllNodesThatMatch(tFilter);
		        if(nodes22.size()>0)
		        {
		        	type=html2Str(nodes22.elementAt(0).toHtml()).trim();
		        	
		    		
		        }
		        
		        //start
		        String start="";
	   	    	parser=Parser.createParser(htmls, "utf-8");
		        AndFilter SFilter=new AndFilter(new TagNameFilter("td"),
		        		new HasAttributeFilter("class","CourseListItemHeadStartDate"));
		        NodeList nodes33 = parser.extractAllNodesThatMatch(SFilter);
		        if(nodes33.size()>0)
		        {
		        	start=html2Str(nodes33.elementAt(0).toHtml()).trim();
		        	
		    		
		        }
		        //length
		        String length="";
	   	    	parser=Parser.createParser(htmls, "utf-8");
		        AndFilter lFilter=new AndFilter(new TagNameFilter("td"),
		        		new HasAttributeFilter("class","CourseListItemHeadDuration"));
		        NodeList nodes44 = parser.extractAllNodesThatMatch(lFilter);
		        if(nodes44.size()>0)
		        {
		        	length=html2Str(nodes44.elementAt(0).toHtml()).trim();
		        	
		    		
		        }
	   	    	
	   	    	/*LinkTag link=(LinkTag)nodes4.elementAt(j);
	   	    	//if(!link.getAttribute("href").equals("")&&!html2Str(link.toHtml()).contains("View this course"))
	   	    	{
	   	    		if(!list.contains(link.getAttribute("href")))
	   	    		{
	   	    			list.add(link.getAttribute("href"));
	   	    			System.out.println(HTMLFilter(html2Str(link.toHtml()))
	   	   	    				.replace("\r\n", "").split(" – ").length);
	   	    			System.out.println("{\""+count+"\",\"www.brighton.ac.uk"
	   	   	    				+link.getAttribute("href")+"\",\""+HTMLFilter(html2Str(link.toHtml()))
	   	   	    				.replace("\r\n", "")+"\",\"0\"},");
	   	                count++;
	   	    		}
	   	    		
	   	    	}*/
		        System.out.println("{\""+count+"\",\"http://www.gcu.ac.uk"
	   	    				+url+"\",\""+title+"\",\""+type+"\",\""+start+"\",\""+length+"\",\"0\"},");
		        count++;
	   	    }
		}
		}
		public static String html2Str(String html) { 
			return html.replaceAll("<[^>]+>", "");
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
		public static String[][] UnData1={
			{"1","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02941-1PTA-1617/Clinical_Physiology","BSc (Hons) 3D Animation and Visualisation","BSc (Hons)","12 September 2016","4 Years","0"},
			};
		
		public static String[][] UnData={
			{"1","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02961-1FTA-1617/3D_Animation_and_Visualisation","BSc (Hons) 3D Animation and Visualisation","BSc (Hons)","12 September 2016","4 Years","0"},
			{"2","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02708-1FTA-1617/Accountancy","BA/BA (Hons) Accountancy","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"3","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02651-1FTA-1617/Applied_Psychology","BSc (Hons) Applied Psychology","BSc (Hons)","12 September 2016","4 Years","0"},
			{"4","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02842-1PTA-1617/Applied_Psychology_(Pt)","BSc (Hons) Applied Psychology (Pt)","BSc (Hons)","12 September 2016","2 - 7 Years","0"},
			{"5","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02897-1FTA-1617/Audio_Systems_Engineering","BSc/BSc (Hons) Audio Systems Engineering","BSc/BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"6","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02896-3FTA-1617/Audio_Technology_(3rd_Year_Entry)","BSc/BSc (Hons) Audio Technology (3rd Year Entry)","BSc/BSc (Hons)","12 September 2016","1 - 2 Years","0"},
			{"7","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02477-1FTA-1617/Bachelor_Of_Laws_(Fast_Track_LLB)","LLB Bachelor Of Laws (Fast Track LLB)","LLB","12 September 2016","2 Years","0"},
			{"8","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02711-1FTA-1617/Bachelor_of_Laws_(LLB)","LLB Bachelor of Laws (LLB)","LLB","12 September 2016","4 Years","0"},
			{"9","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02749-1FTA-1617/Bachelor_of_Laws_with_Risk_(LLB)","LLB Bachelor of Laws with Risk (LLB)","LLB","12 September 2016","4 Years","0"},
			{"10","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00227-1FTA-1617/Biomedical_Science/Applied_Biomedical_Science","BSc (Hons) Biomedical Science/Applied Biomedical Science","BSc (Hons)","12 September 2016","4 Years","0"},
			{"11","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00138-1PTA-1617/Biomedical_Science/Applied_Biomedical_Science_(Pt)","BSc (Hons) Biomedical Science/Applied Biomedical Science (Pt)","BSc (Hons)","12 September 2016","4 - 7 Years","0"},
			{"12","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00096-2PTA-1617/Building_Services_Engineering_(Year_2_and_3_entry)","BEng/BEng (Hons) Building Services Engineering (Year 2 and 3 entry)","BEng/BEng (Hons)","12 September 2016","4 - 5 Years","0"},
			{"13","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02307-2FTA-1617/Building_Services_Engineering_(Year_2_and_3_entry)","BEng/BEng (Hons) Building Services Engineering (Year 2 and 3 entry)","BEng/BEng (Hons)","12 September 2016","2 - 3 Years","0"},
			{"14","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00152-1PTA-1617/Building_Surveying","BSc/BSc (Hons) Building Surveying","BSc/BSc (Hons)","12 September 2016","4 - 5 Years","0"},
			{"15","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00231-1FTA-1617/Building_Surveying","BSc (Hons) Building Surveying","BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"16","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02693-1FTA-1617/Business_Management","BA/BA (Hons) Business Management","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"17","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00295-1FTA-1617/Cell_and_Molecular_Biology","BSc (Hons) Cell and Molecular Biology","BSc (Hons)","12 September 2016","4 Years","0"},
			{"18","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02941-1PTA-1617/Clinical_Physiology","BSc (Hons) Clinical Physiology","BSc (Hons)","12 September 2016","4 Years","0"},
			{"19","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02850-1FTA-1617/Computer_Games_(Art_and_Animation)","BSc (Hons) Computer Games (Art and Animation)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"20","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P01628-1FTA-1617/Computer_Games_(Design)","BSc (Hons) Computer Games (Design)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"21","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02769-3FTA-1617/Computer_Games_(Indie_Development)","BSc (Hons) Computer Games (Indie Development)","BSc (Hons)","12 September 2016","2 Years","0"},
			{"22","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00265-1FTA-1617/Computer_Games_(Software_Development)","BSc (Hons) Computer Games (Software Development)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"23","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02359-1FTA-1617/Computer-Aided_Mechanical_Engineering","BEng/BEng (Hons) Computer-Aided Mechanical Engineering","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"24","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02445-1FTA-1617/Computer-Aided_Mechanical_Engineering","MEng Computer-Aided Mechanical Engineering","MEng","12 September 2016","5 Years","0"},
			{"25","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02454-1PTA-1617/Computer-Aided_Mechanical_Engineering","BEng/BEng (Hons) Computer-Aided Mechanical Engineering","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"26","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/T-EBE-003/Computer-Aided_Mechanical_Engineering","MEng Computer-Aided Mechanical Engineering","MEng","12 September 2016","6 Years","0"},
			{"27","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02768-1FTA-1617/Computing","BSc (Hons) Computing","BSc (Hons)","12 September 2016","4 Years","0"},
			{"28","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00139-1PTA-1617/Construction_Management","BSc/BSc (Hons) Construction Management","BSc/BSc (Hons)","12 September 2016","3 - 5 Years","0"},
			{"29","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00242-1FTA-1617/Construction_Management","BSc/BSc (Hons) Construction Management","BSc/BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"30","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P01471-1PTA-1617/Counselling_Skills_Certificate_(September_Start)","CPD Counselling Skills Certificate (September Start)","CPD","September 2016","1 Year","0"},
			{"31","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02276-1FTA-1617/Cyber_Security_&_Networks","BSc/BSc (Hons) Cyber Security & Networks","BSc/BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"32","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P01640-1FTA-1617/Diagnostic_Imaging","BSc (Hons) Diagnostic Imaging","BSc (Hons)","12 September 2016","4 Years","0"},
			{"33","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02274-1FTA-1617/Digital_Security,_Forensics_and_Ethical_Hacking","BEng (Hons) Digital Security, Forensics and Ethical Hacking","BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"34","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02744-1FTA-1617/Economics_and_Finance","BA/BA (Hons) Economics and Finance","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"35","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02742-1FTA-1617/Economics_and_Law","BA/BA (Hons) Economics and Law","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"36","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02743-1FTA-1617/Economics_and_Risk","BA/BA (Hons) Economics and Risk","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"37","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00109-1FTA-1617/Electrical_Power_Engineering","BEng/BEng (Hons) Electrical Power Engineering","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"38","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02447-1FTA-1617/Electrical_Power_Engineering","MEng Electrical Power Engineering","MEng","12 September 2016","5 Years","0"},
			{"39","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02486-1PTA-1617/Electrical_Power_Engineering","BEng/BEng (Hons) Electrical Power Engineering","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"40","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02866-1FTA-1617/Electrical_and_Electronic_Engineering","BEng (Hons) Electrical and Electronic Engineering","BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"41","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02868-1FTA-1617/Electrical_and_Electronic_Engineering","MEng Electrical and Electronic Engineering","MEng","12 September 2016","5 Years","0"},
			{"42","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02536-1PTA-1617/Electrical,_Electronic_and_Energy_Engineering","BEng/BEng (Hons) Electrical, Electronic and Energy Engineering","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"43","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02517-3FTA-1617/Electrical,_Electronic_and_Energy_Engineering_(3rd_year_entry)","MEng/BEng (Hons) Electrical, Electronic and Energy Engineering (3rd year entry)","MEng/BEng (Hons)","12 September 2016","2 - 3 Years","0"},
			{"44","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/T-INTO-002/English_for_University_Study_(June)","Certificate English for University Study (June)","Certificate","13 June 2016","10 Weeks","0"},
			{"45","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/T-INTO-003/English_for_University_Study_(March)","Certificate English for University Study (March)","Certificate","21 March 2016","10 Weeks","0"},
			{"46","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00156-1PTA-1617/Environmental_Civil_Engineering","BSc (Hons) Environmental Civil Engineering","BSc (Hons)","12 September 2016","4 - 5 Years","0"},
			{"47","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00237-1FTA-1617/Environmental_Civil_Engineering","BSc (Hons) Environmental Civil Engineering","BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"48","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02314-1FTA-1617/Environmental_Management","BSc (Hons) Environmental Management","BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"49","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00535-1PTA-1617/Fashion_Brand_Retailing_(One-module_programme_for_School_Pupils)","n/a Fashion Brand Retailing (One-module programme for School Pupils)","n/a","23 August 2016","28 Weeks","0"},
			{"50","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02709-1FTA-1617/Finance,_Investment_and_Risk","BA/BA (Hons) Finance, Investment and Risk","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"51","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02924-1FTA-1617/Fire_Risk_Engineering","BEng (Hons) Fire Risk Engineering","BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"52","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02925-2PTA-1617/Fire_Risk_Engineering","BEng/BEng (Hons) Fire Risk Engineering","BEng/BEng (Hons)","12 September 2016","4 - 5 Years","0"},
			{"53","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00262-1FTA-1617/Food_Bioscience","BSc (Hons) Food Bioscience","BSc (Hons)","12 September 2016","4 Years","0"},
			{"54","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00260-1FTA-1617/Forensic_Investigation","BSc (Hons) Forensic Investigation","BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"55","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02800 -3PTA-1617/Health_and_Safety_Management","BSc Health and Safety Management","BSc","12 September 2016","2 Years","0"},
			{"56","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02767-1PTA-1617/Health,_Safety_and_Environmental_Management","BSc (Hons) Health, Safety and Environmental Management","BSc (Hons)","12 September 2016","2 Years","0"},
			{"57","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00268-1FTA-1617/Human_Nutrition_with_Dietetics","BSc (Hons) Human Nutrition with Dietetics","BSc (Hons)","12 September 2016","4 Years","0"},
			{"58","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00280-1FTA-1617/IT_Management_for_Business","BSc (Hons) IT Management for Business","BSc (Hons)","12 September 2016","3 - 5 Years","0"},
			{"59","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02319-3FTA-1617/Interior_Design_(3rd_Year_Entry)","BA (Hons) Interior Design (3rd Year Entry)","BA (Hons)","12 September 2016","1 - 2 Years","0"},
			{"60","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02696-1FTA-1617/International_Business","BA/BA (Hons) International Business","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"61","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02612-3FTA-1617/International_Business_and_Finance_(3rd_year_entry)","BA/BA (Hons) International Business and Finance (3rd year entry)","BA/BA (Hons)","12 September 2016","2 Years","0"},
			{"62","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02613-3FTA-1617/International_Business_and_Hospitality_Management_(3rd_year_entry)","BA/BA (Hons) International Business and Hospitality Management (3rd year entry)","BA/BA (Hons)","12 September 2016","2 Years","0"},
			{"63","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02697-3FTA-1617/International_Business_and_Human_Resource_Management_(3rd_year_entry)","BA/BA (Hons) International Business and Human Resource Management (3rd year entry)","BA/BA (Hons)","12 September 2016","2 Years","0"},
			{"64","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02702-3FTA-1617/International_Business_and_Tourism_Management_(3rd_year_entry)","BA/BA (Hons) International Business and Tourism Management (3rd year entry)","BA/BA (Hons)","12 September 2016","2 Years","0"},
			{"65","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02700-1FTA-1617/International_Business_with_Language","BA/BA (Hons) International Business with Language","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"66","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02601-1FTA-1617/International_Events_Management","BA (Hons) International Events Management","BA (Hons)","12 September 2016","4 Years","0"},
			{"67","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02694-1FTA-1617/International_Fashion_Branding","BA/BA (Hons) International Fashion Branding","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"68","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02602-1FTA-1617/International_Fashion_Business","BA/BA (Hons) International Fashion Business","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"69","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02611-1FTA-1617/International_Marketing","BA/BA (Hons) International Marketing","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"70","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02703-3FTA-1617/International_Retail_Management_(3rd_year_entry)","BA/BA (Hons) International Retail Management (3rd year entry)","BA/BA (Hons)","12 September 2016","2 Years","0"},
			{"71","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02596-1FTA-1617/International_Sports_Management","BA (Hons) International Sports Management","BA (Hons)","12 September 2016","2 Years","0"},
			{"72","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02600-3FTA-1617/International_Supply_Chain_Management_(3rd_year_entry)","BA/BA (Hons) International Supply Chain Management (3rd year entry)","BA/BA (Hons)","12 September 2016","2 Years","0"},
			{"73","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02446-1FTA-1617/Mechanical_Electronic_Systems_Engineering","MEng Mechanical Electronic Systems Engineering","MEng","12 September 2016","5 Years","0"},
			{"74","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02483-1FTA-1617/Mechanical_Electronic_Systems_Engineering","BEng/BEng (Hons) Mechanical Electronic Systems Engineering","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"75","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02500-1PTA-1617/Mechanical_Electronic_Systems_Engineering","BEng/BEng (Hons) Mechanical Electronic Systems Engineering","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"76","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02448-3FTA-1617/Mechanical_and_Power_Plant_Systems","MEng Mechanical and Power Plant Systems","MEng","12 September 2016","5 Years","0"},
			{"77","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02455-3PTA-1617/Mechanical_and_Power_Plant_Systems","BEng/BEng (Hons) Mechanical and Power Plant Systems","BEng/BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"78","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02481-1FTA-1617/Mechanical_and_Power_Plant_Systems","BEng (Hons) Mechanical and Power Plant Systems","BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"79","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02705-1FTA-1617/Media_and_Communication","BA/BA (Hons) Media and Communication","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"80","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00322-1FTA-1617/Microbiology","BSc (Hons) Microbiology","BSc (Hons)","12 September 2016","4 Years","0"},
			{"81","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02706-1FTA-1617/Multimedia_Journalism","BA/BA (Hons) Multimedia Journalism","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"82","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02272-1FTA-1617/Networked_Systems_Engineering","BEng (Hons) Networked Systems Engineering","BEng (Hons)","12 September 2016","3 - 4 Years","0"},
			{"83","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02876-1FTA-1617/Nursing_Studies_(Adult)","BSc Nursing Studies (Adult)","BSc","12 September 2016","3 Years","0"},
			{"84","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02880-1FTA-1617/Nursing_Studies_(Adult)","BSc (Hons) Nursing Studies (Adult)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"85","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02877-1FTA-1617/Nursing_Studies_(Child)","BSc Nursing Studies (Child)","BSc","12 September 2016","3 Years","0"},
			{"86","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02881-1FTA-1617/Nursing_Studies_(Child)","BSc (Hons) Nursing Studies (Child)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"87","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02884-1FTA-1617/Nursing_Studies_(Dual_Registration_Learning_Disability/Child)","BSc (Hons) Nursing Studies (Dual Registration Learning Disability/Child)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"88","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02878-1FTA-1617/Nursing_Studies_(Learning_Disability)","BSc Nursing Studies (Learning Disability)","BSc","12 September 2016","3 Years","0"},
			{"89","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02882-1FTA-1617/Nursing_Studies_(Learning_Disability)","BSc (Hons) Nursing Studies (Learning Disability)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"90","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02879-1FTA-1617/Nursing_Studies_(Mental_Health)","BSc Nursing Studies (Mental Health)","BSc","12 September 2016","3 Years","0"},
			{"91","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02883-1FTA-1617/Nursing_Studies_(Mental_Health)","BSc (Hons) Nursing Studies (Mental Health)","BSc (Hons)","12 September 2016","4 Years","0"},
			{"92","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00307-1FTA-1617/Occupational_Therapy","BSc (Hons) Occupational Therapy","BSc (Hons)","12 September 2016","4 Years","0"},
			{"93","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02837-1FTA-1617/Operating_Department_Practice","BSc Operating Department Practice","BSc","12 September 2016","3 Years","0"},
			{"94","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00304-1FTA-1617/Ophthalmic_Dispensing","BSc Ophthalmic Dispensing","BSc","12 September 2016","3 Years","0"},
			{"95","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00305-1FTA-1617/Optometry","BSc (Hons) Optometry","BSc (Hons)","12 September 2016","4 Years","0"},
			{"96","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02679-1FTA-1617/Oral_Health_Science","BSc Oral Health Science","BSc","12 September 2016","3 Years","0"},
			{"97","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02367-1FTA-1617/Orthoptics","BSc (Hons) Orthoptics","BSc (Hons)","12 September 2016","4 Years","0"},
			{"98","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00340-1FTA-1617/Pharmacology","BSc (Hons) Pharmacology","BSc (Hons)","12 September 2016","4 Years","0"},
			{"99","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00310-1FTA-1617/Physiotherapy","BSc (Hons) Physiotherapy","BSc (Hons)","12 September 2016","4 Years","0"},
			{"100","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00308-1FTA-1617/Podiatry","BSc (Hons) Podiatry","BSc (Hons)","12 September 2016","4 Years","0"},
			{"101","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00205-1PTA-1617/Property_Management_and_Valuation","BSc (Hons) Property Management and Valuation","BSc (Hons)","12 September 2016","5 Years","0"},
			{"102","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00317-1FTA-1617/Property_Management_and_Valuation","BSc (Hons) Property Management and Valuation","BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"103","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00206-1PTA-1617/Quantity_Surveying","BSc (Hons) Quantity Surveying","BSc (Hons)","12 September 2016","3 - 5 Years","0"},
			{"104","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00318-1FTA-1617/Quantity_Surveying","BSc (Hons) Quantity Surveying","BSc (Hons)","12 September 2016","3 - 4 Years","0"},
			{"105","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P01641-1FTA-1617/Radiotherapy_and_Oncology","BSc (Hons) Radiotherapy and Oncology","BSc (Hons)","12 September 2016","4 Years","0"},
			{"106","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02934-3DLA-1617/Railway_Operations_Management_(by_Learning_Contract)","BSc Railway Operations Management (by Learning Contract)","BSc","TBC","2 Years","0"},
			{"107","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/T-HLS-004/Responding_to_Domestic_Abuse","Module Responding to Domestic Abuse","Module","TBC","2 Days","0"},
			{"108","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02710-1FTA-1617/Risk_Management","BA/BA (Hons) Risk Management","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"109","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02751-1FTA-1617/Risk_and_Law","BA/BA (Hons) Risk and Law","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"110","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02704-1FTA-1617/Social_Sciences","BA/BA (Hons) Social Sciences","BA/BA (Hons)","12 September 2016","4 Years","0"},
			{"111","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02707-1FTA-1617/Social_Sciences_and_Media_(3rd_year_entry)","BA/BA (Hons) Social Sciences and Media (3rd year entry)","BA/BA (Hons)","12 September 2016","2 Years","0"},
			{"112","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P00088-1FTA-1617/Social_Work","BSc (Hons) Social Work","BSc (Hons)","12 September 2016","4 Years","0"},
			{"113","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/P02655-1FTA-1617/Software_Development_for_Business","BSc (Hons) Software Development for Business","BSc (Hons)","12 September 2016","3 - 5 Years","0"},
			{"114","http://www.gcu.ac.uk/study/undergraduate/courses/details/index.php/T-GSBS-001/Successful_Franchising:_Theory_and_Practice","CPD (20 SCOTCAT credits at level 9) Successful Franchising: Theory and Practice","CPD (20 SCOTCAT credits at level 9)","TBC","15 Weeks","0"}
		};
		public static String[][] PostData={
			{"1","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00722-1FTA-1617/3D_Design_for_Virtual_Environments","MA/PgD 3D Design for Virtual Environments","MA/PgD","12 September 2016","1 Year","0"},
			{"2","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01711-1PTA-1617/3D_Design_for_Virtual_Environments","MA/PgD 3D Design for Virtual Environments","MA/PgD","12 September 2016","2 Years","0"},
			{"3","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02571-1FTA-1617/Accounting_and_Finance","MSc Accounting and Finance","MSc","12 September 2016","1 Year","0"},
			{"4","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02856-1FTAB-1617/Accounting_and_Finance_(Islamic_Finance)","MSc Accounting and Finance (Islamic Finance)","MSc","16 January 2017","1 - 2 Years","0"},
			{"5","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02865-1PTA-1617/Accounting_and_Finance_(Islamic_Finance)_Pt","MSc Accounting and Finance (Islamic Finance) Pt","MSc","12 September 2016","2 - 3 Years","0"},
			{"6","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02571-1FTAB-1617/Accounting_and_Finance_(Jan)","MSc Accounting and Finance (Jan)","MSc","16 January 2017","16 Months","0"},
			{"7","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02686-1FTA-1617/Accounting_and_Finance_Fast-track","MSc Accounting and Finance Fast-track","MSc","12 September 2016","6 Months","0"},
			{"8","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00865-1PTA-1617/Advanced_Computer_Networking","MSc/PgD Advanced Computer Networking","MSc/PgD","12 September 2016","2 Years","0"},
			{"9","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01020-1FTA-1617/Advanced_Computer_Networking","MSc/PgD Advanced Computer Networking","MSc/PgD","12 September 2016","12 Months","0"},
			{"10","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00865-1PTAB-1617/Advanced_Computer_Networking_(January)","MSc Advanced Computer Networking (January)","MSc","16 January 2017","2 Years","0"},
			{"11","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01020-1FTAB-1617/Advanced_Computer_Networking_(January)","MSc/PgD Advanced Computer Networking (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"12","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02765-1FTA-1617/Advanced_Internetwork_Engineering","MSc Advanced Internetwork Engineering","MSc","12 September 2016","12 Months","0"},
			{"13","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02839-1FTA-1617/Advanced_Practice_in_District_Nursing_with_Specialist_Practitioner_Qualification","PgD Advanced Practice in District Nursing with Specialist Practitioner Qualification","PgD","12 September 2016","1 Year","0"},
			{"14","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02839-1PTA-1617/Advanced_Practice_in_District_Nursing_with_Specialist_Practitioner_Qualification_(Pt)","PgD Advanced Practice in District Nursing with Specialist Practitioner Qualification (Pt)","PgD","12 September 2016","2 Years","0"},
			{"15","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00725-1FTA-1617/Applied_Instrumentation_&_Control_(Distance_Learning)","MSc/PgD Applied Instrumentation & Control (Distance Learning)","MSc/PgD","12 September 2016","2 - 5 Years","0"},
			{"16","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00725-1DLAB-1617/Applied_Instrumentation_&_Control_(January)","MSc Applied Instrumentation & Control (January)","MSc","16 January 2017","2 - 5 Years","0"},
			{"17","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00927-1FTA-1617/Applied_Instrumentation_and_Control","MSc/PgD Applied Instrumentation and Control","MSc/PgD","12 September 2016","1 Year","0"},
			{"18","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00927-1FTAB-1617/Applied_Instrumentation_and_Control_(January)","MSc/PgD Applied Instrumentation and Control (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"19","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02555-1FTA-1617/Applied_Instrumentation_and_Control_(Oil_&_Gas)","MSc/PgD Applied Instrumentation and Control (Oil & Gas)","MSc/PgD","12 September 2016","1 Year","0"},
			{"20","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02555-1FTAB-1617/Applied_Instrumentation_and_Control_(Oil_&_Gas)_January","MSc/PgD Applied Instrumentation and Control (Oil & Gas) January","MSc/PgD","16 January 2017","16 Months","0"},
			{"21","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/T-GSBS-004/Association_of_Chartered_Certified_Accountants_(ACCA)","n/a Association of Chartered Certified Accountants (ACCA)","n/a","August 2016","3 Years","0"},
			{"22","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02860-1FTA-1617 /Big_Data_Technologies","MSc Big Data Technologies","MSc","12 September 2016","12 Months","0"},
			{"23","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02870-1PTA-1617 /Big_Data_Technologies_(Pt)","MSc Big Data Technologies (Pt)","MSc","12 September 2016","2 Years","0"},
			{"24","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02860-1FTAB-1617 /Big_Data_Technologies_Jan","MSc Big Data Technologies Jan","MSc","16 January 2017","15 Months","0"},
			{"25","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02870-1PTAB-1617 /Big_Data_Technologies_Jan_(Pt)","MSc Big Data Technologies Jan (Pt)","MSc","16 January 2017","2 Years","0"},
			{"26","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02170-1PTA-1617/Biomedical_Science_(Pt)","MSc Biomedical Science (Pt)","MSc","12 September 2016","2 Years","0"},
			{"27","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00934-1FTA-1617/Biomolecular_and_Biomedical_Sciences","MSc Biomolecular and Biomedical Sciences","MSc","12 September 2016","1 Year","0"},
			{"28","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00934-1FTAB-1617/Biomolecular_and_Biomedical_Sciences_(Jan)","MSc Biomolecular and Biomedical Sciences (Jan)","MSc","16 January 2017","1 Year","0"},
			{"29","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00781-1PTA-1617/Building_Services_Engineering","MSc/PgD Building Services Engineering","MSc/PgD","12 September 2016","24 - 30 Months","0"},
			{"30","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00931-1FTA-1617/Building_Services_Engineering","MSc/PgD Building Services Engineering","MSc/PgD","12 September 2016","13 Months","0"},
			{"31","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00781-1PTAB-1617/Building_Services_Engineering_(January)","MSc Building Services Engineering (January)","MSc","16 January 2017","2 Years","0"},
			{"32","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00931-1FTAB-1617/Building_Services_Engineering_(January)","MSc/PgD Building Services Engineering (January)","MSc/PgD","16 January 2017","15 Months","0"},
			{"33","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02799-1FTAB-1617/Business_Resilience_and_Crisis_Management","MSc Business Resilience and Crisis Management","MSc","16 January 2017","16 Months","0"},
			{"34","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00379-1DLA-1617/Captive_Insurance_Management","Certificate Captive Insurance Management","Certificate","TBC","1 Year","0"},
			{"35","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02288-1PTA-1617/Citizenship_and_Human_Rights","MSc Citizenship and Human Rights","MSc","12 September 2016","2 - 3 Years","0"},
			{"36","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02846-1FTA-1617/Climate_Justice","MSc Climate Justice","MSc","12 September 2016","1 Year","0"},
			{"37","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02847-1PTA-1617/Climate_Justice","MSc Climate Justice","MSc","12 September 2016","2 Years","0"},
			{"38","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02846-1FTAB-1617/Climate_Justice_(January)","MSc Climate Justice (January)","MSc","16 January 2017","1 Year","0"},
			{"39","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02847-1PTAB-1617/Climate_Justice_(January)","MSc Climate Justice (January)","MSc","16 January 2017","1 Year","0"},
			{"40","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02247-1FTA-1617/Clinical_Microbiology","MSc Clinical Microbiology","MSc","12 September 2016","1 Year","0"},
			{"41","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02247-1FTAB-1617/Clinical_Microbiology_(Jan)","MSc Clinical Microbiology (Jan)","MSc","16 January 2017","1 Year","0"},
			{"42","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02758-1PTA-1617/Clinical_Microbiology_(Pt)","MSc Clinical Microbiology (Pt)","MSc","12 September 2016","2 Years","0"},
			{"43","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00943-1FTA-1617/Clinical_Nutrition_and_Health","MSc Clinical Nutrition and Health","MSc","12 September 2016","1 Year","0"},
			{"44","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02366-1FTA-1617/Clinical_Ophthalmology_and_Vision_Research","MSc Clinical Ophthalmology and Vision Research","MSc","12 September 2016","1 Year","0"},
			{"45","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02371-1FTA-1617/Clinical_Ophthalmology_and_Vision_Research_(Diabetes)","MSc Clinical Ophthalmology and Vision Research (Diabetes)","MSc","12 September 2016","1 Year","0"},
			{"46","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02369-1PTA-1617/Clinical_Ophthalmology_and_Vision_Research_(Therapeutics)","MSc Clinical Ophthalmology and Vision Research (Therapeutics)","MSc","12 September 2016","2 Years","0"},
			{"47","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02501-1FTA-1617/Computer_Science","MSc/PgD Computer Science","MSc/PgD","12 September 2016","1 Year","0"},
			{"48","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02505-1PTA-1617/Computer_Science","MSc/PgD Computer Science","MSc/PgD","12 September 2016","3 Years","0"},
			{"49","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02501-1FTAB-1617/Computer_Science_(January)","MSc/PgD Computer Science (January)","MSc/PgD","16 January 2017","15 Months","0"},
			{"50","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02505-1PTAB-1617/Computer_Science_(January)","MSc/PgD Computer Science (January)","MSc/PgD","16 January 2017","1 - 2 Years","0"},
			{"51","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00792-1PTA-1617/Construction_Management","MSc/PgD Construction Management","MSc/PgD","12 September 2016","2 Years","0"},
			{"52","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00944-1FTA-1617/Construction_Management","MSc/PgD Construction Management","MSc/PgD","12 September 2016","13 Months","0"},
			{"53","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02252-1FTL-1617/Construction_Management","MSc/PgD Construction Management","MSc/PgD","12 September 2016","1 Year","0"},
			{"54","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02740-1PTL-1617/Construction_Management","MSc/PgD Construction Management","MSc/PgD","12 September 2016","2 - 3 Years","0"},
			{"55","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00729-1DLA-1617/Construction_Management_(Distance_Learning)","MSc/PgD Construction Management (Distance Learning)","MSc/PgD","12 September 2016","2 Years","0"},
			{"56","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02740-1PTLB-1617/Construction_Management_(Jan_-_PT)","MSc/PgD Construction Management (Jan - PT)","MSc/PgD","16 January 2017","2 - 3 Years","0"},
			{"57","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02252-1FTLB-1617/Construction_Management_(Jan)","MSc/PgD Construction Management (Jan)","MSc/PgD","16 January 2017","16 Months","0"},
			{"58","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00792-1PTAB-1617/Construction_Management_(January)","MSc Construction Management (January)","MSc","16 January 2017","2 Years","0"},
			{"59","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00944-1FTAB-1617/Construction_Management_(January)","MSc/PgD Construction Management (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"60","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00682-1FTA-1617/Counselling_Psychology","DPsych Counselling Psychology","DPsych","12 September 2016","3 Years","0"},
			{"61","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00688-1FTA-1617/Counselling_Psychology","DPsych Counselling Psychology","DPsych","12 September 2016","4 - 7 Years","0"},
			{"62","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02567-1FTA-1617/Creative_&_Cultural_Business","MA  Creative & Cultural Business","MA","12 September 2016","1 Year","0"},
			{"63","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02281-1PTAB-1617/DBA","DBA DBA","DBA","16 January 2017","4 - 5 Years","0"},
			{"64","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02282-1FTAB-1617/DMan","DMan DMan","DMan","16 January 2017","4 - 5 Years","0"},
			{"65","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/MMC122347 -1FTA-1617/Diabetes_Care_-_A_Multiprofessional_Approach","PEC Diabetes Care - A Multiprofessional Approach","PEC","20 September 2016","6 Days","0"},
			{"66","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02638-1FTA-1617/Diabetes_Care_and_Management","MSc Diabetes Care and Management","MSc","12 September 2016","16 Months","0"},
			{"67","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02638-1FTAB-1617/Diabetes_Care_and_Management_(Jan)","MSc Diabetes Care and Management (Jan)","MSc","16 January 2017","16 Months","0"},
			{"68","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02663-1PTA-1617/Diabetes_Care_and_Management_(Pt)","MSc Diabetes Care and Management (Pt)","MSc","12 September 2016","3 Years","0"},
			{"69","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02406-1FTA-1617/Diagnostic_Imaging","MSc Diagnostic Imaging","MSc","12 September 2016","1 - 2 Years","0"},
			{"70","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01208-1FTA-1617/Dietetics_(pre-registration)","PgD Dietetics (pre-registration)","PgD","12 September 2016","20 Months","0"},
			{"71","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02905-1FTL-1617/Digital_Fashion_Strategy","MSc Digital Fashion Strategy","MSc","12 September 2016","1 Year","0"},
			{"72","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02956-1PTL-1617/Digital_Fashion_Strategy","MSc Digital Fashion Strategy","MSc","12 September 2016","2 - 3 Years","0"},
			{"73","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02857-1FTA-1617/Digital_Health","MSc Digital Health","MSc","12 September 2016","1 Year","0"},
			{"74","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02858-1PTA-1617/Digital_Health","MSc Digital Health","MSc","12 September 2016","2 - 5 Years","0"},
			{"75","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02858-1PTAB-1617/Digital_Health_(Part-time_January)","MSc Digital Health (Part-time January)","MSc","16 January 2017","2 - 3 Years","0"},
			{"76","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02841-1FTA-1617/Education_in_Health_and_Social_Care_(Teaching_qualification)","PgC Education in Health and Social Care (Teaching qualification)","PgC","12 September 2016","1 Year","0"},
			{"77","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02840-1PTA-1617/Education_in_Health_and_Social_Care_(Teaching_qualification)_(Pt)","PgC Education in Health and Social Care (Teaching qualification) (Pt)","PgC","12 September 2016","2 - 3 Years","0"},
			{"78","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02519-1FTA-1617/Electrical_and_Electronic_Engineering","MSc Electrical and Electronic Engineering","MSc","12 September 2016","1 Year","0"},
			{"79","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02535-1PTA-1617/Electrical_and_Electronic_Engineering","MSc Electrical and Electronic Engineering","MSc","12 September 2016","2 Years","0"},
			{"80","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02519-1FTAB-1617/Electrical_and_Electronic_Engineering_(January)","MSc Electrical and Electronic Engineering (January)","MSc","16 January 2017","16 Months","0"},
			{"81","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02535-1PTAB-1617/Electrical_and_Electronic_Engineering_(January)","MSc Electrical and Electronic Engineering (January)","MSc","16 January 2017","2 Years","0"},
			{"82","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00810-1PTA-1617/Energy_&_Environmental_Management","MSc/PgD Energy & Environmental Management","MSc/PgD","12 September 2016","2 Years","0"},
			{"83","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00961-1FTA-1617/Energy_&_Environmental_Management","MSc/PgD Energy & Environmental Management","MSc/PgD","12 September 2016","12 Months","0"},
			{"84","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00810-1PTAB-1617/Energy_&_Environmental_Management_(January)","MSc Energy & Environmental Management (January)","MSc","16 January 2017","2 Years","0"},
			{"85","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00961-1FTAB-1617/Energy_&_Environmental_Management_(January)","MSc/PgD Energy & Environmental Management (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"86","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02553-1FTA-1617/Energy_&_Environmental_Management_(Oil_&_Gas)","MSc/PgD Energy & Environmental Management (Oil & Gas)","MSc/PgD","12 September 2016","12 Months","0"},
			{"87","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02553-1FTAB-1617/Energy_&_Environmental_Management_(Oil_&_Gas)_January","MSc/PgD Energy & Environmental Management (Oil & Gas) January","MSc/PgD","16 January 2017","16 Months","0"},
			{"88","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02587-1PTAB-1617/Energy_&_Environmental_Management_(Oil_and_Gas)_January","MSc Energy & Environmental Management (Oil and Gas) January","MSc","16 January 2017","2 Years","0"},
			{"89","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02921-1FTA-1617/Energy_&_Environmental_Management_(Waste)","MSc/PgD Energy & Environmental Management (Waste)","MSc/PgD","12 September 2016","12 Months","0"},
			{"90","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02921-1FTAB-1617/Energy_&_Environmental_Management_(Waste)_January","MSc/PgD Energy & Environmental Management (Waste) January","MSc/PgD","16 January 2017","16 Months","0"},
			{"91","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02922-1PTAB-1617/Energy_&_Environmental_Management_(Waste)_January_Pt","MSc Energy & Environmental Management (Waste) January Pt","MSc","16 January 2017","2 Years","0"},
			{"92","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02922-1FTA-1617/Energy_&_Environmental_Management_(Waste)_Pt","MSc/PgD Energy & Environmental Management (Waste) Pt","MSc/PgD","12 September 2016","2 Years","0"},
			{"93","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02906-1FTL-1617/Fashion_Business_Creation","MSc Fashion Business Creation","MSc","12 September 2016","1 Year","0"},
			{"94","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02957-1PTL-1617/Fashion_Business_Creation","MSc Fashion Business Creation","MSc","12 September 2016","2 - 3 Years","0"},
			{"95","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00968-1FTA-1617/Food_Bioscience","MSc Food Bioscience","MSc","12 September 2016","1 Year","0"},
			{"96","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00974-1FTA-1617/Forensic_Psychology","MSc Forensic Psychology","MSc","12 September 2016","1 Year","0"},
			{"97","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02904-1FTL-1617/Global_Marketing","MSc Global Marketing","MSc","12 September 2016","1 Year","0"},
			{"98","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02955-1PTL-1617/Global_Marketing","MSc Global Marketing","MSc","12 September 2016","2 - 3 Years","0"},
			{"99","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00981-1FTA-1617/Health_History","MSc Health History","MSc","September 2016","1 Year","0"},
			{"100","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02549-1FTA-1617/Health_and_Social_Care","MSc Health and Social Care","MSc","12 September 2016","1 Year","0"},
			{"101","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02466-1PTA-1617/Health_and_Social_Care_(Pt)","MSc Health and Social Care (Pt)","MSc","12 September 2016","3 Years","0"},
			{"102","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00422-1FTA-1617/Human_Resource_Management_(HRM)_(Ft)","MSc Human Resource Management (HRM) (Ft)","MSc","12 September 2016","1 Year","0"},
			{"103","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00422-1FTAB-1617/Human_Resource_Management_(HRM)_(Jan_start)","MSc Human Resource Management (HRM) (Jan start)","MSc","16 January 2017","16 Months","0"},
			{"104","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00853-1PTA-1617/Human_Resource_Management_(HRM)_(Pt)","MSc Human Resource Management (HRM) (Pt)","MSc","12 September 2016","24 Months","0"},
			{"105","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00853-1PTAB-1617/Human_Resource_Management_(HRM)_(Pt)_(Jan_start)","MSc Human Resource Management (HRM) (Pt) (Jan start)","MSc","16 January 2017","2 - 3 Years","0"},
			{"106","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02499-1FTA-1617/Information_Technology","MSc/PgD Information Technology","MSc/PgD","12 September 2016","1 Year","0"},
			{"107","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02504-1PTA-1617/Information_Technology","MSc/PgD Information Technology","MSc/PgD","12 September 2016","3 Years","0"},
			{"108","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02499-1FTAB-1617/Information_Technology_(January)","MSc/PgD Information Technology (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"109","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02504-1PTAB-1617/Information_Technology_(January)","MSc Information Technology (January)","MSc","16 January 2017","2 Years","0"},
			{"110","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02777-1FTA-1617/Information_Technology_(Oil_&_Gas)","MSc Information Technology (Oil & Gas)","MSc","12 September 2016","1 Year","0"},
			{"111","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02777-1FTAB-1617/Information_Technology_(Oil_&_Gas)_January","MSc Information Technology (Oil & Gas) January","MSc","16 January 2017","16 Months","0"},
			{"112","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02575-1FTA-1617/International_Banking,_Finance_and_Risk_Management","MSc International Banking, Finance and Risk Management","MSc","12 September 2016","1 Year","0"},
			{"113","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02578-1FTL-1617/International_Banking,_Finance_and_Risk_Management","MSc International Banking, Finance and Risk Management","MSc","12 September 2016","1 Year","0"},
			{"114","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02625-1PTL-1617/International_Banking,_Finance_and_Risk_Management","MSc International Banking, Finance and Risk Management","MSc","12 September 2016","2 - 3 Years","0"},
			{"115","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02575-1FTAB-1617/International_Banking,_Finance_and_Risk_Management_(Jan)","MSc International Banking, Finance and Risk Management (Jan)","MSc","16 January 2017","12 - 15 Months","0"},
			{"116","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02577-1FTA-1617/International_Business_Management","MSc International Business Management","MSc","12 September 2016","1 Year","0"},
			{"117","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02577-1FTAB-1617/International_Business_Management_(Jan)","MSc International Business Management (Jan)","MSc","16 January 2017","16 Months","0"},
			{"118","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02580-1FTA-1617/International_Economics_and_Finance","MSc International Economics and Finance","MSc","12 September 2016","1 Year","0"},
			{"119","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02568-1FTA-1617/International_Events_Management","MSc International Events Management","MSc","12 September 2016","1 Year","0"},
			{"120","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02562-1FTA-1617/International_Fashion_Marketing","MSc International Fashion Marketing","MSc","12 September 2016","1 Year","0"},
			{"121","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02563-1FTL-1617/International_Fashion_Marketing","MSc International Fashion Marketing","MSc","12 September 2016","1 Year","0"},
			{"122","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01677-1FTA-1617/International_Human_Resource_Management","MSc International Human Resource Management","MSc","12 September 2016","1 Year","0"},
			{"123","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01677-1FTAB-1617/International_Human_Resource_Management_(HRM)_(Jan_start)","MSc International Human Resource Management (HRM) (Jan start)","MSc","16 January 2017","16 Months","0"},
			{"124","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02901-1FTL-1617/International_Management_and_Business_Development","MSc International Management and Business Development","MSc","12 September 2016","1 Year","0"},
			{"125","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02953-1PTL-1617/International_Management_and_Business_Development","MSc International Management and Business Development","MSc","12 September 2016","2 - 3 Years","0"},
			{"126","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02558-1FTA-1617/International_Marketing","MSc International Marketing","MSc","12 September 2016","1 Year","0"},
			{"127","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02556-1FTA-1617/International_Operations_and_Supply_Chain_Management","MSc International Operations and Supply Chain Management","MSc","12 September 2016","1 Year","0"},
			{"128","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00740-1DLA-1617/International_Project_Management","MSc/PgD International Project Management","MSc/PgD","12 September 2016","2 - 5 Years","0"},
			{"129","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00839-1PTA-1617/International_Project_Management","MSc/PgD International Project Management","MSc/PgD","12 September 2016","2 Years","0"},
			{"130","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00992-1FTA-1617/International_Project_Management","MSc/PgD International Project Management","MSc/PgD","12 September 2016","1 Year","0"},
			{"131","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02101-1FTL-1617/International_Project_Management","MSc International Project Management","MSc","12 September 2016","1 Year","0"},
			{"132","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02101-1FTLB-1617/International_Project_Management_(Jan)","MSc International Project Management (Jan)","MSc","16 January 2017","16 Months","0"},
			{"133","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00839-1PTAB-1617/International_Project_Management_(January)","MSc International Project Management (January)","MSc","16 January 2017","2 Years","0"},
			{"134","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00992-1FTAB-1617/International_Project_Management_(January)","MSc/PgD International Project Management (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"135","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02552-1FTA-1617/International_Project_Management_(Oil_&_Gas)","MSc/PgD International Project Management (Oil & Gas)","MSc/PgD","12 September 2016","1 Year","0"},
			{"136","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02552-1FTAB-1617/International_Project_Management_(Oil_&_Gas)_January","MSc/PgD International Project Management (Oil & Gas) January","MSc/PgD","16 January 2017","16 Months","0"},
			{"137","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02588-1PTAB-1617/International_Project_Management_(Oil_and_Gas)_January","MSc International Project Management (Oil and Gas) January","MSc","16 January 2017","2 Years","0"},
			{"138","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02569-1FTA-1617/International_Tourism_Management","MSc International Tourism Management","MSc","12 September 2016","1 Year","0"},
			{"139","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02678-1PTA-1617/International_Tourism_Management_(Pt)","MSc International Tourism Management  (Pt)","MSc","12 September 2016","2 Years","0"},
			{"140","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/T-WBE-001/Leadership_and_Management_of_the_Public_Sector_(by_Learning_Contract)","MSc Leadership and Management of the Public Sector (by Learning Contract)","MSc","12 September 2016","1 - 3 Years","0"},
			{"141","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02927-1FTA-1617/Life_Sciences","MRes Life Sciences","MRes","12 September 2016","1 Year","0"},
			{"142","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02104-1FTL-1617/Luxury_Brand_Management","MBA Luxury Brand Management","MBA","12 September 2016","1 Year","0"},
			{"143","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02952-1PTL-1617/Luxury_Brand_Management","MBA Luxury Brand Management","MBA","12 September 2016","2 - 3 Years","0"},
			{"144","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02902-1FTL-1617/Luxury_Brand_Marketing","MSc Luxury Brand Marketing","MSc","12 September 2016","1 Year","0"},
			{"145","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02954-1PTL-1617/Luxury_Brand_Marketing","MSc Luxury Brand Marketing","MSc","12 September 2016","2 - 3 Years","0"},
			{"146","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00855-1PTA-1617/Maintenance_Management","MSc/PgD Maintenance Management","MSc/PgD","12 September 2016","2 Years","0"},
			{"147","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01009-1FTA-1617/Maintenance_Management","MSc/PgD Maintenance Management","MSc/PgD","12 September 2016","13 Months","0"},
			{"148","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00855-1PTAB-1617/Maintenance_Management_(January)","MSc Maintenance Management (January)","MSc","16 January 2017","2 Years","0"},
			{"149","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01009-1FTAB-1617/Maintenance_Management_(January)","MSc/PgD Maintenance Management (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"150","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02554-1FTA-1617/Maintenance_Management_(Oil_&_Gas)","MSc/PgD Maintenance Management (Oil & Gas)","MSc/PgD","12 September 2016","12 Months","0"},
			{"151","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02554-1FTAB-1617/Maintenance_Management_(Oil_&_Gas)_January","MSc/PgD Maintenance Management (Oil & Gas) January","MSc/PgD","16 January 2017","15 Months","0"},
			{"152","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02687-1FTA-1617/Management","MSc Management","MSc","12 September 2016","1 Year","0"},
			{"153","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02687-1FTAB-1617/Management_(Jan)","MSc Management (Jan)","MSc","16 January 2017","16 Months","0"},
			{"154","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00851-1PTA-1617/Mechanical_Engineering_with_Options_in_Design_or_Manufacture","MSc/PgD Mechanical Engineering with Options in Design or Manufacture","MSc/PgD","12 September 2016","2 Years","0"},
			{"155","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01005-1FTA-1617/Mechanical_Engineering_with_Options_in_Design_or_Manufacture","MSc/PgD Mechanical Engineering with Options in Design or Manufacture","MSc/PgD","12 September 2016","1 Year","0"},
			{"156","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00851-1PTAB-1617/Mechanical_Engineering_with_Options_in_Design_or_Manufacture_(January)","MSc/PgD Mechanical Engineering with Options in Design or Manufacture (January)","MSc/PgD","16 January 2017","1 - 2 Years","0"},
			{"157","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01005-1FTAB-1617/Mechanical_Engineering_with_Options_in_Design_or_Manufacture_(January)","MSc/PgD Mechanical Engineering with Options in Design or Manufacture (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"158","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01014-1PTA-1617/Medical_Ultrasound_(Pt)","MSc Medical Ultrasound (Pt)","MSc","12 September 2016","1 - 6 Years","0"},
			{"159","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01014-1PTAB-1617/Medical_Ultrasound_(Pt)_(Jan)","MSc Medical Ultrasound (Pt) (Jan)","MSc","16 January 2017","1 - 6 Years","0"},
			{"160","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00720-1FTA-1617/Multimedia_Journalism","MA  Multimedia Journalism","MA","12 September 2016","1 Year","0"},
			{"161","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01017-1FTA-1617/Network_Security","MSc/PgD Network Security","MSc/PgD","12 September 2016","1 Year","0"},
			{"162","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02239-1PTA-1617/Network_Security","MSc/PgD Network Security","MSc/PgD","12 September 2016","2 Years","0"},
			{"163","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01017-1FTAB-1617/Network_Security_(January)","MSc/PgD Network Security (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"164","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02239-1PTAB-1617/Network_Security_(January)","MSc Network Security (January)","MSc","16 January 2017","2 Years","0"},
			{"165","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02885-1FTAB-1617/Nursing_Studies_Adult_(Pre-registration)","MSc Nursing Studies Adult (Pre-registration)","MSc","16 January 2017","2 - 3 Years","0"},
			{"166","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02886-1FTA-1617/Nursing:_Advancing_Professional_Practice","MSc Nursing: Advancing Professional Practice","MSc","12 September 2016","1 Year","0"},
			{"167","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02886-1FTAB-1617/Nursing:_Advancing_Professional_Practice_(Jan)","MSc Nursing: Advancing Professional Practice (Jan)","MSc","16 January 2017","1 Year","0"},
			{"168","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02888-1DLA-1617/Nursing:_Advancing_Professional_Practice_(Online)","MSc Nursing: Advancing Professional Practice (Online)","MSc","12 September 2016","1 - 3 Years","0"},
			{"169","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02888-1DLAB-1617/Nursing:_Advancing_Professional_Practice_(Online)_(Jan)","MSc Nursing: Advancing Professional Practice (Online) (Jan)","MSc","16 January 2017","1 - 3 Years","0"},
			{"170","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02887-1PTA-1617/Nursing:_Advancing_Professional_Practice_(Pt)","MSc Nursing: Advancing Professional Practice (Pt)","MSc","12 September 2016","2 - 3 Years","0"},
			{"171","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02887-1PTAB-1617/Nursing:_Advancing_Professional_Practice_(Pt)_(Jan)","MSc Nursing: Advancing Professional Practice (Pt) (Jan)","MSc","16 January 2017","2 - 3 Years","0"},
			{"172","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01027-1FTAB-1617/Occupational_Therapy_(pre-registration)_(Jan)","MSc Occupational Therapy (pre-registration) (Jan)","MSc","16 January 2017","2 Years","0"},
			{"173","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01033-1FTA-1617/Pharmacology","MSc Pharmacology","MSc","12 September 2016","1 Year","0"},
			{"174","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01033-1FTAB-1617/Pharmacology_(Jan)","MSc Pharmacology (Jan)","MSc","16 January 2017","1 Year","0"},
			{"175","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02388-1FTA-1617/Physiotherapy","MSc Physiotherapy","MSc","12 September 2016","1 Year","0"},
			{"176","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02389-1PTA-1617/Physiotherapy_(Pt)","MSc Physiotherapy (Pt)","MSc","12 September 2016","3 - 6 Years","0"},
			{"177","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02389-1PTAB-1617/Physiotherapy_(Pt)_(Jan)","MSc Physiotherapy (Pt) (Jan)","MSc","16 January 2017","3 - 6 Years","0"},
			{"178","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01658-1FTAB-1617/Physiotherapy_(pre-registration)","MSc Physiotherapy (pre-registration)","MSc","23 January 2017","2 Years","0"},
			{"179","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/T-GRAD-001-1PTAB-1617/Professional_Doctorate_(Built_Environment)","Professional  Doctorate Professional Doctorate (Built Environment)","Professional  Doctorate","16 January 2017","4 - 5 Years","0"},
			{"180","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00685-1FTAB-1617/Professional_Doctorate_(Health,_Social_Care_and_Nursing)","Professional  Doctorate Professional Doctorate (Health, Social Care and Nursing)","Professional  Doctorate","16 January 2017","4 - 5 Years","0"},
			{"181","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02095-1PTAB-1617/Professional_Doctorate_(Justice_Welfare_and_Policy)","Professional  Doctorate Professional Doctorate (Justice Welfare and Policy)","Professional  Doctorate","16 January 2017","4 - 5 Years","0"},
			{"182","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02637-1FTAB-1617/Professional_Doctorate_(Public_Policy_and_Management)","Professional  Doctorate Professional Doctorate (Public Policy and Management)","Professional  Doctorate","16 January 2017","4 - 5 Years","0"},
			{"183","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02305-1FTAB-1617/Professional_Engineering_Doctorate","Professional  Doctorate Professional Engineering Doctorate","Professional  Doctorate","16 January 2017","4 - 5 Years","0"},
			{"184","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01630-1FTA-1617/Psychology_(Conversion)","Graduate Diploma Psychology (Conversion)","Graduate Diploma","12 September 2016","1 Year","0"},
			{"185","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01636-1PTA-1617/Psychology_(Conversion)_(Pt)","Graduate Diploma Psychology (Conversion) (Pt)","Graduate Diploma","12 September 2016","1 - 7 Years","0"},
			{"186","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02293-1FTL-1617/Public_Health","MSc Public Health","MSc","12 September 2016","1 Year","0"},
			{"187","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/T-HLS-010/Public_Health","MSc Public Health","MSc","12 September 2016","1 Year","0"},
			{"188","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02292-1DLAB-1617/Public_Health_(Distance_Learning)","MSc Public Health (Distance Learning)","MSc","16 January 2017","2 - 5 Years","0"},
			{"189","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02291-1FTAB-1617/Public_Health_(Jan)","MSc Public Health (Jan)","MSc","16 January 2017","16 Months","0"},
			{"190","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02294-1PTA-1617/Public_Health_(Online)","MSc Public Health (Online)","MSc","12 September 2016","2 - 5 Years","0"},
			{"191","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02294-1PTL-1617/Public_Health_(PT)","MSc Public Health (PT)","MSc","12 September 2016","2 - 3 Years","0"},
			{"192","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02100-1FTA-1617/Quantity_Surveying","MSc/PgD Quantity Surveying","MSc/PgD","12 September 2016","1 Year","0"},
			{"193","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02161-1PTA-1617/Quantity_Surveying","MSc/PgD Quantity Surveying","MSc/PgD","12 September 2016","2 Years","0"},
			{"194","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02162-1DLA-1617/Quantity_Surveying_(Distance_Learning)","MSc/PgD Quantity Surveying (Distance Learning)","MSc/PgD","12 September 2016","5 Years","0"},
			{"195","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02100-1FTAB-1617/Quantity_Surveying_(January)","MSc/PgD Quantity Surveying (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"196","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02161-1PTAB-1617/Quantity_Surveying_(January)","MSc Quantity Surveying (January)","MSc","16 January 2017","2 Years","0"},
			{"197","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02462-1PTA-1617/Research_Methods_(Pt)","MSc Research Methods (Pt)","MSc","October 2016","2 Years","0"},
			{"198","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02280-1PTL-1617 /Risk_Management","MSc Risk Management","MSc","12 September 2016","2 - 3 Years","0"},
			{"199","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02573-1FTA-1617/Risk_Management","MSc Risk Management","MSc","12 September 2016","1 Year","0"},
			{"200","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02574-1FTL-1617/Risk_Management","MSc Risk Management","MSc","12 September 2016","1 Year","0"},
			{"201","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02649-1DLA-1617/Risk_Management_(Distance_Learning)","MSc Risk Management (Distance Learning)","MSc","12 September 2016","2 - 3 Years","0"},
			{"202","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02649-1DLAB-1617/Risk_Management_(Distance_Learning)_(Jan_start)","MSc Risk Management (Distance Learning) (Jan start)","MSc","16 January 2017","2 - 3 Years","0"},
			{"203","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02573-1FTAB-1617/Risk_Management_(Jan_start)","MSc Risk Management (Jan start)","MSc","16 January 2017","16 Months","0"},
			{"204","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/T-GSBS-002/Risk_Management_(Oil_&_Gas)_(Distance_Learning)","MSc Risk Management (Oil & Gas) (Distance Learning)","MSc","12 September 2016","2 - 3 Years","0"},
			{"205","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/T-GSBS-003/Risk_Management_(Oil_&_Gas)_(Pt)","MSc Risk Management (Oil & Gas) (Pt)","MSc","12 September 2016","2 - 3 Years","0"},
			{"206","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02853-1FTAB-1617/Risk_Management_(Oil_and_Gas)_January","MSc Risk Management (Oil and Gas) January","MSc","16 January 2017","16 Months","0"},
			{"207","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02671-1PTA-1617/Risk_Management_(Pt)","MSc Risk Management (Pt)","MSc","12 September 2016","2 - 3 Years","0"},
			{"208","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02763-1FTA-1617/Social_Business_and_Microfinance","MSc Social Business and Microfinance","MSc","12 September 2016","1 Year","0"},
			{"209","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01056-1FTA-1617/Social_Work_(professional_qualification_route)","MSc Social Work (professional qualification route)","MSc","12 September 2016","2 Years","0"},
			{"210","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01233-1FTA-1617/Specialist_Community_Public_Health_Nursing_(Health_Visiting)","PgD Specialist Community Public Health Nursing (Health Visiting)","PgD","12 September 2016","1 Year","0"},
			{"211","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02939-1PTA-1617/Specialist_Community_Public_Health_Nursing_(Health_Visiting)_(Pt)","PgD Specialist Community Public Health Nursing (Health Visiting) (Pt)","PgD","12 September 2016","2 Years","0"},
			{"212","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02844-1FTA-1617/Sustainable_Urban_Environments","MSc Sustainable Urban Environments","MSc","12 September 2016","1 Year","0"},
			{"213","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02845-1PTA-1617/Sustainable_Urban_Environments","MSc Sustainable Urban Environments","MSc","12 September 2016","2 Years","0"},
			{"214","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02844-1FTAB-1617/Sustainable_Urban_Environments_(January)","MSc Sustainable Urban Environments (January)","MSc","16 January 2017","16 Months","0"},
			{"215","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02845-1PTAB-1617/Sustainable_Urban_Environments_(January)","MSc Sustainable Urban Environments (January)","MSc","16 January 2017","2 Years","0"},
			{"216","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02623-1FTA-1617/Telecommunications_Engineering","MSc/PgD Telecommunications Engineering","MSc/PgD","12 September 2016","12 Months","0"},
			{"217","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02624-1PTA-1617/Telecommunications_Engineering","MSc Telecommunications Engineering","MSc","12 September 2016","2 Years","0"},
			{"218","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02623-1FTAB-1617/Telecommunications_Engineering_(January)","MSc/PgD Telecommunications Engineering (January)","MSc/PgD","16 January 2017","16 Months","0"},
			{"219","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02624-1PTAB-1617/Telecommunications_Engineering_(January)","MSc Telecommunications Engineering (January)","MSc","16 January 2017","2 Years","0"},
			{"220","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01634-1FTA-1617/Television_Fiction_Writing","MA  Television Fiction Writing","MA","12 September 2016","1 Year","0"},
			{"221","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P01635-1PTA-1617/Television_Fiction_Writing_(Pt)","MA  Television Fiction Writing (Pt)","MA","12 September 2016","2 - 3 Years","0"},
			{"222","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P00905-1PTA-1617/Theory_of_Podiatric_Surgery_(Pt)","MSc Theory of  Podiatric Surgery (Pt)","MSc","12 September 2016","3 - 5 Years","0"},
			{"223","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/T-HLS-005/Therapeutic_Prescribing_for_Optometrists","Module Therapeutic Prescribing for Optometrists","Module","23 April 2016","10 Months","0"},
			{"224","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02848-1FTA-1617/Water_Resource_Engineering_and_Management","MSc Water Resource Engineering and Management","MSc","12 September 2016","1 Year","0"},
			{"225","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02849-1PTA-1617/Water_Resource_Engineering_and_Management","MSc Water Resource Engineering and Management","MSc","12 September 2016","2 Years","0"},
			{"226","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02848-1FTAB-1617/Water_Resource_Engineering_and_Management_(January)","MSc Water Resource Engineering and Management (January)","MSc","16 January 2017","2 Years","0"},
			{"227","http://www.gcu.ac.uk/study/postgraduate/courses/details/index.php/P02849-1PTAB-1617/Water_Resource_Engineering_and_Management_(January)","MSc Water Resource Engineering and Management (January)","MSc","16 January 2017","1 - 2 Years","0"},

		};
	

}
