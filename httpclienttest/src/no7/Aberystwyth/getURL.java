//http://courses.aber.ac.uk/browser/business/
package no7.Aberystwyth;

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

	public static String[] sites={"http://courses.aber.ac.uk/browser/art/",
		"http://courses.aber.ac.uk/browser/biological-sciences/",
		"http://courses.aber.ac.uk/browser/computer-science/",
		"http://courses.aber.ac.uk/browser/education/",
		"http://courses.aber.ac.uk/browser/english/",
		"http://courses.aber.ac.uk/browser/geography-environmental-and-earth-sciences/",
		"http://courses.aber.ac.uk/browser/history-and-welsh-history/",
		"http://courses.aber.ac.uk/browser/information-studies/",
		"http://courses.aber.ac.uk/browser/international-politics/",
		"http://courses.aber.ac.uk/browser/law-and-criminology/",
		"http://courses.aber.ac.uk/browser/business/",
		"http://courses.aber.ac.uk/browser/mathematics/",
		"http://courses.aber.ac.uk/browser/european-languages/",
		"http://courses.aber.ac.uk/browser/physics/",
		"http://courses.aber.ac.uk/browser/psychology/",
		"http://courses.aber.ac.uk/browser/rural-science/",
		"http://courses.aber.ac.uk/browser/sport-and-exercise-science/",
		"http://courses.aber.ac.uk/browser/theatre-film-and-television-studies/",
		"http://courses.aber.ac.uk/browser/welsh-and-celtic-studies/"};
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub

		ArrayList<String> list=new ArrayList<String>();
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		int count=1;
		for(int i=0;i<sites.length;i++)
		{
			HttpGet httpGet = new HttpGet(sites[i]);
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			
	        Parser	parser=Parser.createParser(htmls, "utf-8");
		    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("ul"),
		    		new HasAttributeFilter("class","plain-list"));
	   	    
	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	   	    //System.out.println(nodes4.size());
	   	    if(nodes4.size()==2)
	   	    {
	   	    	
	   	    	parser=Parser.createParser(nodes4.elementAt(1).toHtml(), "utf-8");
		        AndFilter aFilter=new AndFilter(new TagNameFilter("a"),
		        		new HasAttributeFilter("href"));
		        NodeList nodes00 = parser.extractAllNodesThatMatch(aFilter);
		        for(int k=0;k<nodes00.size();k++)
		        {
		        	LinkTag link=(LinkTag)nodes00.elementAt(k);
		   	    	//if(!link.getAttribute("href").equals("")&&!html2Str(link.toHtml()).contains("View this course"))
		   	    	{
		   	    		if(!list.contains(link.getAttribute("href")))
		   	    		{
		   	    			String url=(link.getAttribute("href"));
		   	    			if(!url.equals("")&&!list.contains(url)){
		   			        	System.out.println("{\""+count+"\",\"http://"
		   		   	    				+url+"\",\""+html2Str(link.toHtml())+"\",\""+GetType(html2Str(link.toHtml()))+"\",\"0\"},");
		   			        list.add(url);
		   			        	count++;
		   			        }
		   	    		}
		   	    		
		   	    	}
		        }
			        
		        
		        
		        
		        
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
		public static String[][] UnData={
			{"1","http://courses.aber.ac.uk/undergraduate/art-history-degree/","Art History (BA, 3 year)","BA","0"},
			{"2","http://courses.aber.ac.uk/undergraduate/creative-arts-degree/","Creative Arts (BA, 3 year)","BA","0"},
			{"3","http://courses.aber.ac.uk/undergraduate/fine-art-degree/","Fine Art (BA, 3 year)","BA","0"},
			{"4","http://courses.aber.ac.uk/undergraduate/art-history-and-french-degree/","Art History / French (BA, 4 year)","BA","0"},
			{"5","http://courses.aber.ac.uk/undergraduate/creative-writing-art-history-degree/","Creative Writing and Art History (BA, 3 year)","BA","0"},
			{"6","http://courses.aber.ac.uk/undergraduate/creative-writing-fine-art-degree/","Creative Writing and Fine Art (BA, 3 year)","BA","0"},
			{"7","http://courses.aber.ac.uk/undergraduate/drama-and-theatre-studies-and-fine-art-degree/","Drama and Theatre Studies / Fine Art (BA, 3 year)","BA","0"},
			{"8","http://courses.aber.ac.uk/undergraduate/education-and-fine-art-degree/","Education / Fine Art (BA, 3 year)","BA","0"},
			{"9","http://courses.aber.ac.uk/undergraduate/english-literature-and-art-history-degree/","English Literature / Art History (BA, 3 year)","BA","0"},
			{"10","http://courses.aber.ac.uk/undergraduate/film-and-television-studies-and-fine-art-degree/","Film and Television Studies / Fine Art (BA, 3 year)","BA","0"},
			{"11","http://courses.aber.ac.uk/undergraduate/fine-art-and-art-history-degree/","Fine Art / Art History (BA, 3 year)","BA","0"},
			{"12","http://courses.aber.ac.uk/undergraduate/fine-art-and-english-literature-degree/","Fine Art / English Literature (BA, 3 year)","BA","0"},
			{"13","http://courses.aber.ac.uk/undergraduate/history-and-fine-art-degree/","History / Fine Art (BA, 3 year)","BA","0"},
			{"14","http://courses.aber.ac.uk/undergraduate/irish-and-fine-art-degree/","Irish / Fine Art (BA, 4 year)","BA","0"},
			{"15","http://courses.aber.ac.uk/undergraduate/art-history-with-fine-art-degree/","Art History with Fine Art (BA, 3 year)","BA","0"},
			{"16","http://courses.aber.ac.uk/undergraduate/fine-art-with-art-history-degree/","Fine Art with Art History (BA, 3 year)","BA","0"},
			{"17","http://courses.aber.ac.uk/undergraduate/cymraeg-celfyddyd-gain/","Welsh / Fine Art (BA, 3 year)","BA","0"},
			{"18","http://courses.aber.ac.uk/undergraduate/bsc-agriculture-degree/","Agriculture (BSc, 3 year)","BSc","0"},
			{"19","http://courses.aber.ac.uk/undergraduate/agriculture-animal-science/","Agriculture with Animal Science (BSc, 3 year)","BSc","0"},
			{"20","http://courses.aber.ac.uk/undergraduate/agriculture-with-animal-science-degree/","Agriculture with Animal Science (BSc, 4 year)","BSc","0"},
			{"21","http://courses.aber.ac.uk/undergraduate/agriculture-with-business-studies-work-placement-degree/","Agriculture with Business Studies (BSc, 4 year)","BSc","0"},
			{"22","http://courses.aber.ac.uk/undergraduate/agriculture-with-countryside-management-work-placement-degree/","Agriculture with Countryside Management (BSc, 4 year)","BSc","0"},
			{"23","http://courses.aber.ac.uk/undergraduate/animal-behaviour-degree/","Animal Behaviour (BSc, 3 year)","BSc","0"},
			{"24","http://courses.aber.ac.uk/undergraduate/animal-science-degree/","Animal Science (BSc, 3 year)","BSc","0"},
			{"25","http://courses.aber.ac.uk/undergraduate/biochemistry-degree/","Biochemistry (BSc, 3 year)","BSc","0"},
			{"26","http://courses.aber.ac.uk/undergraduate/biology-degree/","Biology (BSc, 3 year)","BSc","0"},
			{"27","http://courses.aber.ac.uk/undergraduate/ecology-degree/","Ecology (BSc, 3 year)","BSc","0"},
			{"28","http://courses.aber.ac.uk/undergraduate/environmental-biosciences-degree/","Environmental Biosciences (BSc, 3 year)","BSc","0"},
			{"29","http://courses.aber.ac.uk/undergraduate/equine-science-and-veterinary-bioscience/","Equine and Veterinary Bioscience (BSc, 3 year)","BSc","0"},
			{"30","http://courses.aber.ac.uk/undergraduate/genetics-degree/","Genetics (BSc, 3 year)","BSc","0"},
			{"31","http://courses.aber.ac.uk/undergraduate/genetics-and-biochemistry-degree/","Genetics and Biochemistry (BSc, 3 year)","BSc","0"},
			{"32","http://courses.aber.ac.uk/undergraduate/life-sciences-degree/","Life Sciences (BSc, 4 year)","BSc","0"},
			{"33","http://courses.aber.ac.uk/undergraduate/marine-and-freshwater-biology/","Marine and Freshwater Biology (BSc, 3 year)","BSc","0"},
			{"34","http://courses.aber.ac.uk/undergraduate/microbiology-degree/","Microbiology (BSc, 3 year)","BSc","0"},
			{"35","http://courses.aber.ac.uk/undergraduate/microbiology-zoology-degree/","Microbiology and Zoology (BSc, 3 year)","BSc","0"},
			{"36","http://courses.aber.ac.uk/undergraduate/plant-biology-degree/","Plant Biology (BSc, 3 year)","BSc","0"},
			{"37","http://courses.aber.ac.uk/undergraduate/sport-exercise-science-degree/","Sport and Exercise Science (BSc, 3 year)","BSc","0"},
			{"38","http://courses.aber.ac.uk/undergraduate/veterinary-biosciences/","Veterinary Biosciences (BSc, 3 year)","BSc","0"},
			{"39","http://courses.aber.ac.uk/undergraduate/zoology-degree/","Zoology (BSc, 3 year)","BSc","0"},
			{"40","http://courses.aber.ac.uk/undergraduate/mbiol-biology/","Biology (MBiol, 4 year)","","0"},
			{"41","http://courses.aber.ac.uk/undergraduate/mbiol-zoology/","Zoology (MBiol, 4 year)","","0"},
			{"42","http://courses.aber.ac.uk/undergraduate/artificial-intelligence-roboticsdegree/","Artificial Intelligence and Robotics (BSc, 3 year)","BSc","0"},
			{"43","http://courses.aber.ac.uk/undergraduate/artificial-intelligence-robotics-degree-work-experience/","Artificial Intelligence and Robotics (inc Integrated Industrial and Professional Training) (BSc, 4 year)","BSc","0"},
			{"44","http://courses.aber.ac.uk/undergraduate/business-information-technology-degree/","Business Information Technology (BSc, 3 year)","BSc","0"},
			{"45","http://courses.aber.ac.uk/undergraduate/business-information-technology-(inc-integrated-industrial-and-professional-training)-degree/","Business Information Technology (inc Integrated Industrial and Professional Training) (BSc, 4 year)","BSc","0"},
			{"46","http://courses.aber.ac.uk/undergraduate/computer-graphics-vision-games-degree/","Computer Graphics, Vision and Games (BSc, 3 year)","BSc","0"},
			{"47","http://courses.aber.ac.uk/undergraduate/computer-graphics-vision-games-degree-work-experience/","Computer Graphics, Vision and Games (inc Integrated Industrial and Professional Training) (BSc, 4 year)","BSc","0"},
			{"48","http://courses.aber.ac.uk/undergraduate/computer-science-degree/","Computer Science (BSc, 3 year)","BSc","0"},
			{"49","http://courses.aber.ac.uk/undergraduate/computer-science-degree-with-industrial-year/","Computer Science (inc Integrated Industrial and Professional Training) (BSc, 4 year)","BSc","0"},
			{"50","http://courses.aber.ac.uk/undergraduate/computer-science-degree-foundation-year/","Computer Science (includes foundation year) (BSc, 4 year)","BSc","0"},
			{"51","http://courses.aber.ac.uk/undergraduate/computer-science-and-artificial-intelligence-degree/","Computer Science and Artificial Intelligence (BSc, 3 year)","BSc","0"},
			{"52","http://courses.aber.ac.uk/undergraduate/computer-science-and-artificial-intelligence-(inc-integrated-industrial-and-professional-training)-degree/","Computer Science and Artificial Intelligence (inc Integrated Industrial and Professional Training) (BSc, 4 year)","BSc","0"},
			{"53","http://courses.aber.ac.uk/undergraduate/data-science/","Data Science (BSc, 3 year)","BSc","0"},
			{"54","http://courses.aber.ac.uk/undergraduate/data-science-iy/","Data Science (inc Integrated Industrial and Professional Training) (BSc, 4 year)","BSc","0"},
			{"55","http://courses.aber.ac.uk/undergraduate/internet-computing-and-systems-administration-degree/","Internet Computing and Systems Administration (BSc, 3 year)","BSc","0"},
			{"56","http://courses.aber.ac.uk/undergraduate/internet-computing-and-systems-administration-degree-work-experience/","Internet Computing and Systems Administration (inc Integrated Industrial and Professional Training) (BSc, 4 year)","BSc","0"},
			{"57","http://courses.aber.ac.uk/undergraduate/software-engineering/","Software Engineering (inc Integrated Industrial and Professional Training) (BEng, 4 year)","BEng","0"},
			{"58","http://courses.aber.ac.uk/undergraduate/space-science-robotics-degree/","Space Science and Robotics (BSc, 3 year)","BSc","0"},
			{"59","http://courses.aber.ac.uk/undergraduate/meng-software-engineering-degree/","Software Engineering (inc Integrated Industrial and Professional Training) (MEng, 5 year)","MEng","0"},
			{"60","http://courses.aber.ac.uk/undergraduate/mphys-space-science-robotics-degree/","Space Science and Robotics (MPhys, 4 year)","MPhys","0"},
			{"61","http://courses.aber.ac.uk/undergraduate/computer-science-and-mathematics-degree/","Computer Science / Mathematics (BSc, 3 year)","BSc","0"},
			{"62","http://courses.aber.ac.uk/undergraduate/computer-science-and-physical-geography-degree/","Computer Science / Physical Geography (BSc, 3 year)","BSc","0"},
			{"63","http://courses.aber.ac.uk/undergraduate/computer-science-and-physics-degree/","Computer Science / Physics (BSc, 3 year)","BSc","0"},
			{"64","http://courses.aber.ac.uk/undergraduate/computer-science-welsh-beginners-degree/","Computer Science and Welsh (for beginners) (BSc, 4 year)","BSc","0"},
			{"65","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-with-computer-science-degree/","Accounting and Finance with Computer Science (BSc, 3 year)","BSc","0"},
			{"66","http://courses.aber.ac.uk/undergraduate/business-economics-with-computer-science-degree/","Business Economics with Computer Science (BSc, 3 year)","BSc","0"},
			{"67","http://courses.aber.ac.uk/undergraduate/business-and-management-with-computer-science-degree/","Business and Management with Computer Science (BSc, 3 year)","BSc","0"},
			{"68","http://courses.aber.ac.uk/undergraduate/childhood-studies-degree/","Childhood Studies (BA, 3 year)","BA","0"},
			{"69","http://courses.aber.ac.uk/undergraduate/education-and-international-development/","Education and International Development (BA, 3 year)","BA","0"},
			{"70","http://courses.aber.ac.uk/undergraduate/education-and-drama-theatre-studies-degree/","Education / Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"71","http://courses.aber.ac.uk/undergraduate/education-and-french-degree/","Education / French (BA, 4 year)","BA","0"},
			{"72","http://courses.aber.ac.uk/undergraduate/education-and-history-degree/","Education / History (BA, 3 year)","BA","0"},
			{"73","http://courses.aber.ac.uk/undergraduate/english-literature-and-education-degree/","English Literature / Education (BA, 3 year)","BA","0"},
			{"74","http://courses.aber.ac.uk/undergraduate/irish-and-education-degree/","Irish / Education (BA, 4 year)","BA","0"},
			{"75","http://courses.aber.ac.uk/undergraduate/mathematics-and-education-degree/","Mathematics / Education (BSc, 3 year)","BSc","0"},
			{"76","http://courses.aber.ac.uk/undergraduate/welsh-and-education-degree/","Welsh / Education (BA, 3 year)","BA","0"},
			{"77","http://courses.aber.ac.uk/undergraduate/education-with-history-degree/","Education with History (BA, 3 year)","BA","0"},
			{"78","http://courses.aber.ac.uk/undergraduate/education-with-human-geography-degree/","Education with Human Geography (BA, 3 year)","BA","0"},
			{"79","http://courses.aber.ac.uk/undergraduate/education-with-mathematics-degree/","Education with Mathematics (BA, 3 year)","BA","0"},
			{"80","http://courses.aber.ac.uk/undergraduate/education-with-spanish-degree/","Education with Spanish (BA, 4 year)","BA","0"},
			{"81","http://courses.aber.ac.uk/undergraduate/environmental-earth-studies-with-education-degree/","Environmental Earth Studies with Education (BSc, 3 year)","BSc","0"},
			{"82","http://courses.aber.ac.uk/undergraduate/history-with-education-degree/","History with Education (BA, 3 year)","BA","0"},
			{"83","http://courses.aber.ac.uk/undergraduate/human-geography-with-education-degree/","Human Geography with Education (BA, 3 year)","BA","0"},
			{"84","http://courses.aber.ac.uk/undergraduate/mathematics-with-education-degree/","Mathematics with Education (BSc, 3 year)","BSc","0"},
			{"85","http://courses.aber.ac.uk/undergraduate/physical-geography-with-education-degree/","Physical Geography with Education (BSc, 3 year)","BSc","0"},
			{"86","http://courses.aber.ac.uk/undergraduate/physics-with-education-degree/","Physics with Education (BSc, 3 year)","BSc","0"},
			{"87","http://courses.aber.ac.uk/undergraduate/spanish-with-education-degree/","Spanish with Education (BA, 4 year)","BA","0"},
			{"88","http://courses.aber.ac.uk/undergraduate/creative-writing-degree/","Creative Writing (BA, 3 year)","BA","0"},
			{"89","http://courses.aber.ac.uk/undergraduate/english-literature-degree/","English Literature (BA, 3 year)","BA","0"},
			{"90","http://courses.aber.ac.uk/undergraduate/english-creative-writing-degree/","English Literature and Creative Writing (BA, 3 year)","BA","0"},
			{"91","http://courses.aber.ac.uk/undergraduate/creative-writing-cymraeg-degree/","Creative Writing and Cymraeg (BA, 3 year)","BA","0"},
			{"92","http://courses.aber.ac.uk/undergraduate/creative-writing-drama-theatre-studies-degree/","Creative Writing and Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"93","http://courses.aber.ac.uk/undergraduate/creative-writing-film-television-studies-degree/","Creative Writing and Film and Television Studies (BA, 3 year)","BA","0"},
			{"94","http://courses.aber.ac.uk/undergraduate/creative-writing-french-degree/","Creative Writing and French (BA, 4 year)","BA","0"},
			{"95","http://courses.aber.ac.uk/undergraduate/creative-writing-german-degree/","Creative Writing and German (BA, 4 year)","BA","0"},
			{"96","http://courses.aber.ac.uk/undergraduate/creative-writing-scenography-theatre-design-degree/","Creative Writing and Scenography and Theatre Design (BA, 3 year)","BA","0"},
			{"97","http://courses.aber.ac.uk/undergraduate/creative-writing-spanish-degree/","Creative Writing and Spanish (BA, 4 year)","BA","0"},
			{"98","http://courses.aber.ac.uk/undergraduate/english-literature-and-drama-and-theatre-studies-degree/","English Literature / Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"99","http://courses.aber.ac.uk/undergraduate/film-television-studies-and-english-literature-degree/","Film and Television Studies / English Literature (BA, 3 year)","BA","0"},
			{"100","http://courses.aber.ac.uk/undergraduate/french-and-english-literature-degree/","French / English Literature (BA, 4 year)","BA","0"},
			{"101","http://courses.aber.ac.uk/undergraduate/german-and-english-literature-degree/","German / English Literature (BA, 4 year)","BA","0"},
			{"102","http://courses.aber.ac.uk/undergraduate/history-and-english-literature-degree/","History / English Literature (BA, 3 year)","BA","0"},
			{"103","http://courses.aber.ac.uk/undergraduate/human-geography-and-english-literature-degree/","Human Geography / English Literature (BA, 3 year)","BA","0"},
			{"104","http://courses.aber.ac.uk/undergraduate/international-politics-and-english-literature-degree/","International Politics / English Literature (BA, 3 year)","BA","0"},
			{"105","http://courses.aber.ac.uk/undergraduate/irish-language-and-literature-and-english-literature-degree/","Irish Language and Literature / English Literature (BA, 4 year)","BA","0"},
			{"106","http://courses.aber.ac.uk/undergraduate/spanish-and-english-literature-degree/","Spanish / English Literature (BA, 4 year)","BA","0"},
			{"107","http://courses.aber.ac.uk/undergraduate/cymraeg-english-literature/","Welsh / English Literature (BA, 3 year)","BA","0"},
			{"108","http://courses.aber.ac.uk/undergraduate/environmental-earth-science-degree/","Environmental Earth Science (BSc, 3 year)","BSc","0"},
			{"109","http://courses.aber.ac.uk/undergraduate/environmental-science-degree/","Environmental Science (BSc, 3 year)","BSc","0"},
			{"110","http://courses.aber.ac.uk/undergraduate/geography-degree/","Geography (BSc, 3 year)","BSc","0"},
			{"111","http://courses.aber.ac.uk/undergraduate/human-geography-degree/","Human Geography (BA, 3 year)","BA","0"},
			{"112","http://courses.aber.ac.uk/undergraduate/physical-geography/","Physical Geography (BSc, 3 year)","BSc","0"},
			{"113","http://courses.aber.ac.uk/undergraduate/french-and-human-geography-degree/","French / Human Geography (BA, 4 year)","BA","0"},
			{"114","http://courses.aber.ac.uk/undergraduate/human-geography-and-history-degree/","Human Geography / History (BA, 3 year)","BA","0"},
			{"115","http://courses.aber.ac.uk/undergraduate/human-geography-and-international-politics-degree/","Human Geography / International Politics (BA, 3 year)","BA","0"},
			{"116","http://courses.aber.ac.uk/undergraduate/human-geography-and-politics-degree/","Human Geography / Politics (BA, 3 year)","BA","0"},
			{"117","http://courses.aber.ac.uk/undergraduate/physical-geography-and-mathematics-degree/","Physical Geography / Mathematics (BSc, 3 year)","BSc","0"},
			{"118","http://courses.aber.ac.uk/undergraduate/economics-with-human-geography-degree/","Economics with Human Geography (BSc, 3 year)","BSc","0"},
			{"119","http://courses.aber.ac.uk/undergraduate/human-geography-with-business-and-management-degree/","Human Geography with Business and Management (BA, 3 year)","BA","0"},
			{"120","http://courses.aber.ac.uk/undergraduate/human-geography-economics-degree/","Human Geography with Economics (BA, 3 year)","BA","0"},
			{"121","http://courses.aber.ac.uk/undergraduate/human-geography-with-international-politics-degree/","Human Geography with International Politics (BA, 3 year)","BA","0"},
			{"122","http://courses.aber.ac.uk/undergraduate/mathematics-with-physical-geography-degree/","Mathematics with Physical Geography (BSc, 3 year)","BSc","0"},
			{"123","http://courses.aber.ac.uk/undergraduate/physical-geography-mathematics-degree/","Physical Geography with Mathematics (BSc, 3 year)","BSc","0"},
			{"124","http://courses.aber.ac.uk/undergraduate/physical-geography-with-statistics-degree/","Physical Geography with Statistics (BSc, 3 year)","BSc","0"},
			{"125","http://courses.aber.ac.uk/undergraduate/gradd-daearyddiaeth/","Geography (BSc, 3 year)","BSc","0"},
			{"126","http://courses.aber.ac.uk/undergraduate/daearyddiaeth-a-hanes/","Geography / History (BA, 3 year)","BA","0"},
			{"127","http://courses.aber.ac.uk/undergraduate/daearyddiaeth-a-gwleidyddiaeth-ryngwladol/","Geography / International Politics (BA, 3 year)","BA","0"},
			{"128","http://courses.aber.ac.uk/undergraduate/daearyddiaeth-a-hanes-cymru/","Geography / Welsh History (BA, 3 year)","BA","0"},
			{"129","http://courses.aber.ac.uk/undergraduate/cymraeg-a-daearyddiaeth/","Welsh / Geography (BA, 3 year)","BA","0"},
			{"130","http://courses.aber.ac.uk/undergraduate/cymraeg-gyda-daearyddiaeth/","Welsh with Geography (BA, 3 year)","BA","0"},
			{"131","http://courses.aber.ac.uk/undergraduate/european-history-degree/","European History (BA, 3 year)","BA","0"},
			{"132","http://courses.aber.ac.uk/undergraduate/history-degree/","History (BA, 3 year)","BA","0"},
			{"133","http://courses.aber.ac.uk/undergraduate/history-and-economic-and-social-history-degree/","History / Economic and Social History (BA, 3 year)","BA","0"},
			{"134","http://courses.aber.ac.uk/undergraduate/history-and-media-degree/","History and Media (BA, 3 year)","BA","0"},
			{"135","http://courses.aber.ac.uk/undergraduate/hanes-cyfryngau/","History and Media (BA, 3 year)","BA","0"},
			{"136","http://courses.aber.ac.uk/undergraduate/history-and-welsh-history-degree/","History and Welsh History (BA, 3 year)","BA","0"},
			{"137","http://courses.aber.ac.uk/undergraduate/international-politics-international-history-degree/","International Politics and International History (BA, 3 year)","BA","0"},
			{"138","http://courses.aber.ac.uk/undergraduate/international-politics-and-military-history-degree/","International Politics and Military History (BA, 3 year)","BA","0"},
			{"139","http://courses.aber.ac.uk/undergraduate/medieval-early-modern-history-degree/","Medieval and Early Modern History (BA, 3 year)","BA","0"},
			{"140","http://courses.aber.ac.uk/undergraduate/modern-and-contemporary-history-degree/","Modern and Contemporary History (BA, 3 year)","BA","0"},
			{"141","http://courses.aber.ac.uk/undergraduate/politics-and-modern-history-degree/","Politics and Modern History (BA, 3 year)","BA","0"},
			{"142","http://courses.aber.ac.uk/undergraduate/drama-and-theatre-studies-and-history-degree/","Drama and Theatre Studies / History (BA, 3 year)","BA","0"},
			{"143","http://courses.aber.ac.uk/undergraduate/french-and-history-degree/","French / History (BA, 4 year)","BA","0"},
			{"144","http://courses.aber.ac.uk/undergraduate/german-and-history-degree/","German / History (BA, 4 year)","BA","0"},
			{"145","http://courses.aber.ac.uk/undergraduate/history-and-film-and-television-studies-degree/","History / Film and Television Studies (BA, 3 year)","BA","0"},
			{"146","http://courses.aber.ac.uk/undergraduate/history-and-mathematics-degree/","History / Mathematics (BA, 3 year)","BA","0"},
			{"147","http://courses.aber.ac.uk/undergraduate/history-and-spanish-degree/","History / Spanish (BA, 4 year)","BA","0"},
			{"148","http://courses.aber.ac.uk/undergraduate/history-welsh-history/","History / Welsh History (BA, 3 year)","BA","0"},
			{"149","http://courses.aber.ac.uk/undergraduate/international-politics-and-economic-and-social-history-degree/","International Politics / Economic and Social History (BA, 3 year)","BA","0"},
			{"150","http://courses.aber.ac.uk/undergraduate/international-politics-and-history-degree/","International Politics / History (BA, 3 year)","BA","0"},
			{"151","http://courses.aber.ac.uk/undergraduate/ba-politics-and-history-degree/","Politics / History (BA, 3 year)","BA","0"},
			{"152","http://courses.aber.ac.uk/undergraduate/politics-and-welsh-history-degree/","Politics / Welsh History (BA, 3 year)","BA","0"},
			{"153","http://courses.aber.ac.uk/undergraduate/business-and-management-with-economic-and-social-history-degree/","Business and Management with Economic and Social History (BSc, 3 year)","BSc","0"},
			{"154","http://courses.aber.ac.uk/undergraduate/economics-with-economic-and-social-history-degree/","Economics with Economic and Social History (BSc, 3 year)","BSc","0"},
			{"155","http://courses.aber.ac.uk/undergraduate/gradd-hanes/","History (BA, 3 year)","BA","0"},
			{"156","http://courses.aber.ac.uk/undergraduate/hanes-a-gwleidyddiaeth-rhyngwladol/","History / International Politics (BA, 3 year)","BA","0"},
			{"157","http://courses.aber.ac.uk/undergraduate/hanes-a-gwleidyddiaeth/","History / Politics (BA, 3 year)","BA","0"},
			{"158","http://courses.aber.ac.uk/undergraduate/hanes-a-hanes-cymru/","History / Welsh History (BA, 3 year)","BA","0"},
			{"159","http://courses.aber.ac.uk/undergraduate/gwyddeleg-hanes/","Irish / History (BA, 4 year)","BA","0"},
			{"160","http://courses.aber.ac.uk/undergraduate/gwyddeleg-hanes-cymru/","Irish / Welsh History (BA, 4 year)","BA","0"},
			{"161","http://courses.aber.ac.uk/undergraduate/gradd-gwleidyddiaeth-a-hanes-modern/","Politics and Modern History (BA, 3 year)","BA","0"},
			{"162","http://courses.aber.ac.uk/undergraduate/gradd-llenyddiaeth-a-hanes-cymru/","The Literature and History of Wales (BA, 3 year)","BA","0"},
			{"163","http://courses.aber.ac.uk/undergraduate/cymraeg-hanes/","Welsh / History (BA, 3 year)","BA","0"},
			{"164","http://courses.aber.ac.uk/undergraduate/cymraeg-a-hanes-cymru/","Welsh / Welsh History (BA, 3 year)","BA","0"},
			{"165","http://courses.aber.ac.uk/undergraduate/hanes-cymru-astudiaethau-ffilm-a-theledu/","Welsh History / Film and Television Studies (BA, 3 year)","BA","0"},
			{"166","http://courses.aber.ac.uk/undergraduate/cymraeg-gyda-hanes/","Welsh with History (BA, 3 year)","BA","0"},
			{"167","http://courses.aber.ac.uk/undergraduate/cymraeg-hanes-cymru/","Welsh with Welsh History (BA, 3 year)","BA","0"},
			{"168","http://courses.aber.ac.uk/undergraduate/information-library-studies-distance-learning/","Information and Library Studies (BSc, 5 year)","BSc","0"},
			{"169","http://courses.aber.ac.uk/undergraduate/international-politics-degree/","International Politics (BA, 3 year)","BA","0"},
			{"170","http://courses.aber.ac.uk/undergraduate/international-politics-intelligence-studies-degree/","International Politics and Intelligence Studies (BA, 3 year)","BA","0"},
			{"171","http://courses.aber.ac.uk/undergraduate/international-politics-strategic-studies-degree/","International Politics and Strategic Studies (BA, 3 year)","BA","0"},
			{"172","http://courses.aber.ac.uk/undergraduate/international-politics-non-western-world/","International Politics and the Non Western World (BA, 3 year)","BA","0"},
			{"173","http://courses.aber.ac.uk/undergraduate/political-studies-degree/","Political Studies (BA, 3 year)","BA","0"},
			{"174","http://courses.aber.ac.uk/undergraduate/social-science-degree/","Social Science (BSc, 3 year)","BSc","0"},
			{"175","http://courses.aber.ac.uk/undergraduate/drama-and-theatre-studies-and-international-politics-degree/","Drama and Theatre Studies / International Politics (BA, 3 year)","BA","0"},
			{"176","http://courses.aber.ac.uk/undergraduate/french-and-international-politics-degree/","French / International Politics (BA, 4 year)","BA","0"},
			{"177","http://courses.aber.ac.uk/undergraduate/international-politics-and-film-and-television-studies-degree/","International Politics / Film and Television Studies (BA, 3 year)","BA","0"},
			{"178","http://courses.aber.ac.uk/undergraduate/international-politics-and-spanish-degree/","International Politics / Spanish (BA, 4 year)","BA","0"},
			{"179","http://courses.aber.ac.uk/undergraduate/business-and-management-with-international-politics-degree/","Business and Management with International Politics (BSc, 3 year)","BSc","0"},
			{"180","http://courses.aber.ac.uk/undergraduate/business-and-management-with-politics-degree/","Business and Management with Politics (BSc, 3 year)","BSc","0"},
			{"181","http://courses.aber.ac.uk/undergraduate/economics-with-international-politics-degree/","Economics with International Politics (BSc, 3 year)","BSc","0"},
			{"182","http://courses.aber.ac.uk/undergraduate/economics-with-politics-degree/","Economics with Politics (BSc, 3 year)","BSc","0"},
			{"183","http://courses.aber.ac.uk/undergraduate/international-politics-degree-major/","International Politics (Major) (BA, 3 year)","BA","0"},
			{"184","http://courses.aber.ac.uk/undergraduate/international-politics-with-economics-degree/","International Politics with Economics (BA, 3 year)","BA","0"},
			{"185","http://courses.aber.ac.uk/undergraduate/international-politics-with-law-degree/","International Politics with Law (BA, 3 year)","BA","0"},
			{"186","http://courses.aber.ac.uk/undergraduate/law-with-international-politics-degree/","Law with International Politics (BA, 3 year)","BA","0"},
			{"187","http://courses.aber.ac.uk/undergraduate/law-with-politics-degree/","Law with Politics (BA, 3 year)","BA","0"},
			{"188","http://courses.aber.ac.uk/undergraduate/politics-with-law-degree/","Politics with Law (BA, 3 year)","BA","0"},
			{"189","http://courses.aber.ac.uk/undergraduate/gradd-gwleidyddiaeth-ryngwladol/","International Politics (BA, 3 year)","BA","0"},
			{"190","http://courses.aber.ac.uk/undergraduate/gwleidyddiaeth-ryngwladol-a-gwleidyddiaeth-cymru/","International Politics and Welsh Politics (BA, 3 year)","BA","0"},
			{"191","http://courses.aber.ac.uk/undergraduate/gradd-gwleidyddiaeth/","Politics (BA, 3 year)","BA","0"},
			{"192","http://courses.aber.ac.uk/undergraduate/cymraeg-a-gwleidyddiaeth-ryngwladol/","Welsh / International Politics (BA, 3 year)","BA","0"},
			{"193","http://courses.aber.ac.uk/undergraduate/cymraeg-a-gwleidyddiaeth/","Welsh / Politics (BA, 3 year)","BA","0"},
			{"194","http://courses.aber.ac.uk/undergraduate/business-law-degree/","Business Law (LLB, 3 year)","LLB","0"},
			{"195","http://courses.aber.ac.uk/undergraduate/criminal-law-degree/","Criminal Law (LLB, 3 year)","LLB","0"},
			{"196","http://courses.aber.ac.uk/undergraduate/criminology-degree/","Criminology (BSc, 3 year)","BSc","0"},
			{"197","http://courses.aber.ac.uk/undergraduate/criminology-applied-psychology-degree/","Criminology with Applied Psychology (BSc, 3 year)","BSc","0"},
			{"198","http://courses.aber.ac.uk/undergraduate/european-law-degree/","European Law (LLB, 3 year)","LLB","0"},
			{"199","http://courses.aber.ac.uk/undergraduate/human-rights-degree/","Human Rights (LLB, 3 year)","LLB","0"},
			{"200","http://courses.aber.ac.uk/undergraduate/llb-law-degree-m100/","Law (LLB, 3 year)","LLB","0"},
			{"201","http://courses.aber.ac.uk/undergraduate/llb-law-degree-two-years/","Law (LLB, 2 year)","LLB","0"},
			{"202","http://courses.aber.ac.uk/undergraduate/ba-law-degree-m103/","Law (BA, 3 year)","BA","0"},
			{"203","http://courses.aber.ac.uk/undergraduate/psychology-and-criminology-degree/","Psychology / Criminology (BSc, 3 year)","BSc","0"},
			{"204","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-with-law-degree/","Accounting and Finance with Law (BSc, 3 year)","BSc","0"},
			{"205","http://courses.aber.ac.uk/undergraduate/business-and-management-with-law-degree/","Business and Management with Law (BSc, 3 year)","BSc","0"},
			{"206","http://courses.aber.ac.uk/undergraduate/economics-with-law-degree/","Economics with Law (BSc, 3 year)","BSc","0"},
			{"207","http://courses.aber.ac.uk/undergraduate/law-with-business-and-management-degree/","Law with Business and Management (BA, 3 year)","BA","0"},
			{"208","http://courses.aber.ac.uk/undergraduate/law-with-criminology-degree/","Law with Criminology (BA, 3 year)","BA","0"},
			{"209","http://courses.aber.ac.uk/undergraduate/law-with-economics-degree/","Law with Economics (BA, 3 year)","BA","0"},
			{"210","http://courses.aber.ac.uk/undergraduate/law-with-french/","Law with French (LLB, 4 year)","LLB","0"},
			{"211","http://courses.aber.ac.uk/undergraduate/law-with-french-degree/","Law with French (BA, 4 year)","BA","0"},
			{"212","http://courses.aber.ac.uk/undergraduate/law-with-german/","Law with German (LLB, 4 year)","LLB","0"},
			{"213","http://courses.aber.ac.uk/undergraduate/ba-law-with-german/","Law with German (BA, 4 year)","BA","0"},
			{"214","http://courses.aber.ac.uk/undergraduate/law-with-mathematics-degree/","Law with Mathematics (BA, 3 year)","BA","0"},
			{"215","http://courses.aber.ac.uk/undergraduate/llb-law-with-spanish-degree/","Law with Spanish (LLB, 4 year)","LLB","0"},
			{"216","http://courses.aber.ac.uk/undergraduate/law-with-spanish-degree/","Law with Spanish (BA, 4 year)","BA","0"},
			{"217","http://courses.aber.ac.uk/undergraduate/marketing-with-law-degree/","Marketing with Law (BSc, 3 year)","BSc","0"},
			{"218","http://courses.aber.ac.uk/undergraduate/y-gyfraith-gyda-chymraeg/","Law with Welsh (BA, 3 year)","BA","0"},
			{"219","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-degree/","Accounting and Finance (BSc, 3 year)","BSc","0"},
			{"220","http://courses.aber.ac.uk/undergraduate/adventure-tourism-management-degree/","Adventure Tourism Management (BSc, 3 year)","BSc","0"},
			{"221","http://courses.aber.ac.uk/undergraduate/agriculture-with-business-studies-degree/","Agriculture with Business Studies (BSc, 3 year)","BSc","0"},
			{"222","http://courses.aber.ac.uk/undergraduate/business-economics-degree/","Business Economics (BSc, 3 year)","BSc","0"},
			{"223","http://courses.aber.ac.uk/undergraduate/business-finance-degree/","Business Finance (BSc, 3 year)","BSc","0"},
			{"224","http://courses.aber.ac.uk/undergraduate/business-management-degree/","Business and Management (BSc, 3 year)","BSc","0"},
			{"225","http://courses.aber.ac.uk/undergraduate/economics-degree/","Economics (BSc, 3 year)","BSc","0"},
			{"226","http://courses.aber.ac.uk/undergraduate/marketing-degree/","Marketing (BSc, 3 year)","BSc","0"},
			{"227","http://courses.aber.ac.uk/undergraduate/tourism-management-degree/","Tourism Management (BSc, 3 year)","BSc","0"},
			{"228","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-and-economics-degree/","Accounting and Finance / Economics (BSc, 3 year)","BSc","0"},
			{"229","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-and-spanish-degree/","Accounting and Finance / Spanish (BSc, 4 year)","BSc","0"},
			{"230","http://courses.aber.ac.uk/undergraduate/economics-and-marketing-degree/","Economics / Marketing (BSc, 3 year)","BSc","0"},
			{"231","http://courses.aber.ac.uk/undergraduate/marketing-and-spanish-degree/","Marketing / Spanish (BSc, 4 year)","BSc","0"},
			{"232","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-with-french-degree/","Accounting and Finance with French (BSc, 4 year)","BSc","0"},
			{"233","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-with-management-degree/","Accounting and Finance with Management (BSc, 3 year)","BSc","0"},
			{"234","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-with-marketing-degree/","Accounting and Finance with Marketing (BSc, 3 year)","BSc","0"},
			{"235","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-with-mathematics-degree/","Accounting and Finance with Mathematics (BSc, 3 year)","BSc","0"},
			{"236","http://courses.aber.ac.uk/undergraduate/accounting-and-finance-with-statistics-degree/","Accounting and Finance with Statistics (BSc, 3 year)","BSc","0"},
			{"237","http://courses.aber.ac.uk/undergraduate/business-and-management-with-french-degree/","Business and Management with French (BSc, 4 year)","BSc","0"},
			{"238","http://courses.aber.ac.uk/undergraduate/business-and-management-with-german-degree/","Business and Management with German (BSc, 4 year)","BSc","0"},
			{"239","http://courses.aber.ac.uk/undergraduate/business-and-management-with-mathematics-degree/","Business and Management with Mathematics (BSc, 3 year)","BSc","0"},
			{"240","http://courses.aber.ac.uk/undergraduate/business-and-management-with-spanish-degree/","Business and Management with Spanish (BSc, 4 year)","BSc","0"},
			{"241","http://courses.aber.ac.uk/undergraduate/economics-with-management-degree/","Economics with Management (BSc, 3 year)","BSc","0"},
			{"242","http://courses.aber.ac.uk/undergraduate/economics-with-mathematics-degree/","Economics with Mathematics (BSc, 3 year)","BSc","0"},
			{"243","http://courses.aber.ac.uk/undergraduate/german-with-business-and-management-degree/","German with Business and Management (BA, 4 year)","BA","0"},
			{"244","http://courses.aber.ac.uk/undergraduate/marketing-with-accounting-and-finance-degree/","Marketing with Accounting and Finance (BSc, 3 year)","BSc","0"},
			{"245","http://courses.aber.ac.uk/undergraduate/marketing-with-management-degree/","Marketing with Management (BSc, 3 year)","BSc","0"},
			{"246","http://courses.aber.ac.uk/undergraduate/mathematics-with-accounting-and-finance-degree/","Mathematics with Accounting and Finance (BSc, 3 year)","BSc","0"},
			{"247","http://courses.aber.ac.uk/undergraduate/mathematics-with-business-and-management-degree/","Mathematics with Business and Management (BSc, 3 year)","BSc","0"},
			{"248","http://courses.aber.ac.uk/undergraduate/mathematics-with-economics-degree/","Mathematics with Economics (BSc, 3 year)","BSc","0"},
			{"249","http://courses.aber.ac.uk/undergraduate/Modern-languages-with-business-management/","Modern Languages with Business and Management (BA, 4 year)","BA","0"},
			{"250","http://courses.aber.ac.uk/undergraduate/physics-with-business-management-degree/","Physics with Business and Management (BSc, 3 year)","BSc","0"},
			{"251","http://courses.aber.ac.uk/undergraduate/spanish-with-marketing-degree/","Spanish with Marketing (BA, 4 year)","BA","0"},
			{"252","http://courses.aber.ac.uk/undergraduate/tourism-with-french-degree/","Tourism Management with French (BSc, 4 year)","BSc","0"},
			{"253","http://courses.aber.ac.uk/undergraduate/tourism-with-spanish-degree/","Tourism Management with Spanish (BSc, 4 year)","BSc","0"},
			{"254","http://courses.aber.ac.uk/undergraduate/business-management-with-cymraeg/","Business and Management with Welsh (BSc, 3 year)","BSc","0"},
			{"255","http://courses.aber.ac.uk/undergraduate/applied-mathematics-and-pure-mathematics-degree/","Applied Mathematics / Pure Mathematics (BSc, 3 year)","BSc","0"},
			{"256","http://courses.aber.ac.uk/undergraduate/applied-mathematics-and-statistics-degree/","Applied Mathematics / Statistics (BSc, 3 year)","BSc","0"},
			{"257","http://courses.aber.ac.uk/undergraduate/financial-mathematics-degree/","Financial Mathematics (BSc, 3 year)","BSc","0"},
			{"258","http://courses.aber.ac.uk/undergraduate/mathematical-theoretical-physics-degree/","Mathematical and Theoretical Physics (BSc, 3 year)","BSc","0"},
			{"259","http://courses.aber.ac.uk/undergraduate/mathematics-degree/","Mathematics (BSc, 3 year)","BSc","0"},
			{"260","http://courses.aber.ac.uk/undergraduate/mathematics-degree-with-foundation-year/","Mathematics (includes foundation year) (BSc, 4 year)","BSc","0"},
			{"261","http://courses.aber.ac.uk/undergraduate/pure-mathematics-and-statistics-degree/","Pure Mathematics / Statistics (BSc, 3 year)","BSc","0"},
			{"262","http://courses.aber.ac.uk/undergraduate/mmath-mathematical-theoretical-physics-degree/","Mathematical and Theoretical Physics (MMath, 4 year)","MMath","0"},
			{"263","http://courses.aber.ac.uk/undergraduate/mmath-mathematics-degree/","Mathematics (MMath, 4 year)","MMath","0"},
			{"264","http://courses.aber.ac.uk/undergraduate/film-and-television-studies-and-mathematics-degree/","Film and Television Studies / Mathematics (BA, 3 year)","BA","0"},
			{"265","http://courses.aber.ac.uk/undergraduate/french-and-mathematics-degree/","French / Mathematics (BA, 4 year)","BA","0"},
			{"266","http://courses.aber.ac.uk/undergraduate/german-and-mathematics-degree/","German / Mathematics (BA, 4 year)","BA","0"},
			{"267","http://courses.aber.ac.uk/undergraduate/mathematics-and-drama-and-theatre-studies-degree/","Mathematics / Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"268","http://courses.aber.ac.uk/undergraduate/mathematics-and-physics-degree/","Mathematics / Physics (BSc, 3 year)","BSc","0"},
			{"269","http://courses.aber.ac.uk/undergraduate/cymraeg-a-mathemateg/","Welsh / Mathematics (BA, 3 year)","BA","0"},
			{"270","http://courses.aber.ac.uk/undergraduate/european-languages-degree/","European Languages (BA, 4 year)","BA","0"},
			{"271","http://courses.aber.ac.uk/undergraduate/french-and-german-degree/","French / German (BA, 4 year)","BA","0"},
			{"272","http://courses.aber.ac.uk/undergraduate/french-and-spanish-degree/","French / Spanish (BA, 4 year)","BA","0"},
			{"273","http://courses.aber.ac.uk/undergraduate/french-with-german-degree/","French with German (BA, 4 year)","BA","0"},
			{"274","http://courses.aber.ac.uk/undergraduate/french-with-spanish-degree/","French with Spanish (BA, 4 year)","BA","0"},
			{"275","http://courses.aber.ac.uk/undergraduate/german-and-spanish-degree/","German / Spanish (BA, 4 year)","BA","0"},
			{"276","http://courses.aber.ac.uk/undergraduate/german-with-french-degree/","German with French (BA, 4 year)","BA","0"},
			{"277","http://courses.aber.ac.uk/undergraduate/german-with-spanish-degree/","German with Spanish (BA, 4 year)","BA","0"},
			{"278","http://courses.aber.ac.uk/undergraduate/liberal-arts-degree/","Liberal Arts (BA, 3 year)","BA","0"},
			{"279","http://courses.aber.ac.uk/undergraduate/modern-german-studies-degree/","Modern German Studies (BA, 4 year)","BA","0"},
			{"280","http://courses.aber.ac.uk/undergraduate/romance-languages-degree/","Romance Languages (BA, 4 year)","BA","0"},
			{"281","http://courses.aber.ac.uk/undergraduate/spanish-with-french-degree/","Spanish with French (BA, 4 year)","BA","0"},
			{"282","http://courses.aber.ac.uk/undergraduate/spanish-with-german-degree/","Spanish with German (BA, 4 year)","BA","0"},
			{"283","http://courses.aber.ac.uk/undergraduate/film-and-television-studies-and-french-degree/","Film and Television Studies / French (BA, 4 year)","BA","0"},
			{"284","http://courses.aber.ac.uk/undergraduate/film-and-television-studies-and-spanish-degree/","Film and Television Studies / Spanish (BA, 4 year)","BA","0"},
			{"285","http://courses.aber.ac.uk/undergraduate/french-and-drama-and-theatre-studies-degree/","French / Drama and Theatre Studies (BA, 4 year)","BA","0"},
			{"286","http://courses.aber.ac.uk/undergraduate/spanish-and-drama-and-theatre-studies-degree/","Spanish / Drama and Theatre Studies (BA, 4 year)","BA","0"},
			{"287","http://courses.aber.ac.uk/undergraduate/astrophysics-degree/","Astrophysics (BSc, 3 year)","BSc","0"},
			{"288","http://courses.aber.ac.uk/undergraduate/bsc-physics-degree/","Physics (BSc, 3 year)","BSc","0"},
			{"289","http://courses.aber.ac.uk/undergraduate/physics-with-planetary-space-physics-degree/","Physics with Planetary and Space Physics (BSc, 3 year)","BSc","0"},
			{"290","http://courses.aber.ac.uk/undergraduate/physics-degree-with-foundation-year/","Physics with foundation year (BSc, 4 year)","BSc","0"},
			{"291","http://courses.aber.ac.uk/undergraduate/astrophysics-masters-degree/","Astrophysics (MPhys, 4 year)","MPhys","0"},
			{"292","http://courses.aber.ac.uk/undergraduate/mphys-physics-degree/","Physics (MPhys, 4 year)","MPhys","0"},
			{"293","http://courses.aber.ac.uk/undergraduate/mphys-physics-planetary-space-physics-degree/","Physics with Planetary and Space Physics (MPhys, 4 year)","MPhys","0"},
			{"294","http://courses.aber.ac.uk/undergraduate/psychology-degree/","Psychology (BSc, 3 year)","BSc","0"},
			{"295","http://courses.aber.ac.uk/undergraduate/psychology-degree-year-in-industry/","Psychology (inc integrated year in industry) (BSc, 4 year)","BSc","0"},
			{"296","http://courses.aber.ac.uk/undergraduate/psychology-degree-year-abroad/","Psychology (inc integrated year studying abroad) (BSc, 4 year)","BSc","0"},
			{"297","http://courses.aber.ac.uk/undergraduate/agriculture-degree/","Agriculture (BSc, 4 year)","BSc","0"},
			{"298","http://courses.aber.ac.uk/undergraduate/agriculture-with-countryside-management-degree/","Agriculture with Countryside Management (BSc, 3 year)","BSc","0"},
			{"299","http://courses.aber.ac.uk/undergraduate/countryside-conservation-degree/","Countryside Conservation (BSc, 3 year)","BSc","0"},
			{"300","http://courses.aber.ac.uk/undergraduate/countryside-management-degree/","Countryside Management (BSc, 3 year)","BSc","0"},
			{"301","http://courses.aber.ac.uk/undergraduate/equine-science-degree/","Equine Science (BSc, 3 year)","BSc","0"},
			{"302","http://courses.aber.ac.uk/undergraduate/agriculture-foundation-degree/","Agriculture (FD) (FDSc, 2 year)","","0"},
			{"303","http://courses.aber.ac.uk/undergraduate/agriculture-foundation-degree-work-experience/","Agriculture (inc work placement year) (FDSc, 3 year)","","0"},
			{"304","http://courses.aber.ac.uk/undergraduate/agriculture-and-countryside-management-fd-degree/","Agriculture and Countryside Management (FD) (FDSc, 2 year)","","0"},
			{"305","http://courses.aber.ac.uk/undergraduate/agriculture-countryside-management-degree/","Agriculture and Countryside Management (inc work placement year) (FDSc, 3 year)","","0"},
			{"306","http://courses.aber.ac.uk/undergraduate/countryside-management-countryside-conservation-foundation-degree/","Countryside Management and Conservation (FD) (FDSc, 2 year)","","0"},
			{"307","http://courses.aber.ac.uk/undergraduate/countryside-management-conservation-foundation-work-placement-degree/","Countryside Management and Conservation (inc work placement year) (FDSc, 3 year)","","0"},
			{"308","http://courses.aber.ac.uk/undergraduate/equine-studies-foundation-degree/","Equine Studies (FDSc, 2 year)","","0"},
			{"309","http://courses.aber.ac.uk/undergraduate/drama-theatre-studies-degree/","Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"310","http://courses.aber.ac.uk/undergraduate/film-television-studies-degree/","Film and Television Studies (BA, 3 year)","BA","0"},
			{"311","http://courses.aber.ac.uk/undergraduate/media-and-communication-studies-degree/","Media and Communication Studies (BA, 3 year)","BA","0"},
			{"312","http://courses.aber.ac.uk/undergraduate/scenography-and-theatre-design-degree/","Scenography and Theatre Design (BA, 3 year)","BA","0"},
			{"313","http://courses.aber.ac.uk/undergraduate/film-and-television-studies-and-drama-and-theatre-studies-degree/","Film and Television Studies / Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"314","http://courses.aber.ac.uk/undergraduate/scenography-and-theatre-design-and-drama-and-theatre-studies-degree/","Scenography and Theatre Design / Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"315","http://courses.aber.ac.uk/undergraduate/scenography-and-theatre-design-and-film-and-television-studies-degree/","Scenography and Theatre Design / Film and Television Studies (BA, 3 year)","BA","0"},
			{"316","http://courses.aber.ac.uk/undergraduate/drama-astudiaethau-theatr/","Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"317","http://courses.aber.ac.uk/undergraduate/drama-astudiaethau-theatr-astudiaethau-ffilm-a-theledu/","Drama and Theatre Studies / Film and Television Studies (BA, 3 year)","BA","0"},
			{"318","http://courses.aber.ac.uk/undergraduate/gradd-astudiaethau-ffilm-a-theledu/","Film and Television Studies (BA, 3 year)","BA","0"},
			{"319","http://courses.aber.ac.uk/undergraduate/cymraeg-drama-astudiaethau-theatr/","Welsh / Drama and Theatre Studies (BA, 3 year)","BA","0"},
			{"320","http://courses.aber.ac.uk/undergraduate/cymraeg-astudiaethau-ffilm-a-theledu/","Welsh / Film and Television Studies (BA, 3 year)","BA","0"},
			{"321","http://courses.aber.ac.uk/undergraduate/celtic-studies-degree/","Celtic Studies (BA, 4 year)","BA","0"},
			{"322","http://courses.aber.ac.uk/undergraduate/gradd-cymraeg-proffesiynol/","Professional Welsh (BA, 3 year)","BA","0"},
			{"323","http://courses.aber.ac.uk/undergraduate/welsh-beginners-degree/","Welsh (BA, 4 year)","BA","0"},
			{"324","http://courses.aber.ac.uk/undergraduate/gradd-cymraeg-ar-ieithoedd-celtaidd/","Welsh and the Celtic Languages (BA, 3 year)","BA","0"},
			{"325","http://courses.aber.ac.uk/undergraduate/astudiaethau-celtaidd/","Celtic Studies (BA, 4 year)","BA","0"},
			{"326","http://courses.aber.ac.uk/undergraduate/gradd-cymraeg/","Welsh (BA, 3 year)","BA","0"},
			{"327","http://courses.aber.ac.uk/undergraduate/cymraeg-a-frangeg/","Welsh / French (BA, 4 year)","BA","0"},
			{"328","http://courses.aber.ac.uk/undergraduate/cymraeg-a-almaeneg/","Welsh / German (BA, 4 year)","BA","0"},
			{"329","http://courses.aber.ac.uk/undergraduate/cymraeg-a-sbaeneg/","Welsh / Spanish (BA, 4 year)","BA","0"},

		};
		public static String[][] PostData1={
			{"1","http://courses.aber.ac.uk/postgraduate/research-art/","Art History (MA, 1 year)","MA","0"},
			
		};
		public static String[][] PostData={
			{"1","http://courses.aber.ac.uk/postgraduate/art-history-masters/","Art History (MA, 1 year)","MA","0"},
			{"2","http://courses.aber.ac.uk/postgraduate/fine-art-and-art-history-masters/","Art and Art History (MA, 1 year)","MA","0"},
			{"3","http://courses.aber.ac.uk/postgraduate/fine-art-masters/","Fine Art (MA, 1 year)","MA","0"},
			{"4","http://courses.aber.ac.uk/postgraduate/research-art/","Art (PhD, 3 year)","","0"},
			{"5","http://courses.aber.ac.uk/postgraduate/food-and-water-security-masters/","Food and Water Security (MSc, 1 year)","MSc","0"},
			{"6","http://courses.aber.ac.uk/postgraduate/managing-the-environment-masters/","Managing the Environment (MSc, 1 year)","MSc","0"},
			{"7","http://courses.aber.ac.uk/postgraduate/statistics-for-computational-biology-masters/","Statistics for Computational Biology (MSc, 1 year)","MSc","0"},
			{"8","http://courses.aber.ac.uk/postgraduate/sustainable-and-efficient-food-production-distance-learning-masters/","Sustainable and Efficient Food Production (MSc, 5 year)","MSc","0"},
			{"9","http://courses.aber.ac.uk/postgraduate/research-ibers/","Biological Sciences (PhD, 3 year)","","0"},
			{"10","http://courses.aber.ac.uk/postgraduate/research-sport-exercise/","Sport and Exercise Science (PhD, 3 year)","","0"},
			{"11","http://courses.aber.ac.uk/postgraduate/pgce-biology-balanced-science/","Biology with Balanced Science (PGCE, 1 year)","PG","0"},
			{"12","http://courses.aber.ac.uk/postgraduate/agriculture-research-masters/","Agriculture (MRes, 5 year)","MRes","0"},
			{"13","http://courses.aber.ac.uk/postgraduate/biosciences-masters-research/","Biosciences (MRes, 1 year)","MRes","0"},
			{"14","http://courses.aber.ac.uk/postgraduate/professional-doctorate-agriculture/","Professional Doctorate in Agriculture (DAg, 5 year)","Doctorate","0"},
			{"15","http://courses.aber.ac.uk/postgraduate/tar-bioleg-gwyddoniaeth-gytbwys/","Biology with Balanced Science (PGCE, 1 year)","PG","0"},
			{"16","http://courses.aber.ac.uk/postgraduate/computer-science-software-engineering-masters/","Computer Science (Software Engineering) (MSc, 1 year)","MSc","0"},
			{"17","http://courses.aber.ac.uk/postgraduate/software-engineering-integrated-industrial-year/","Computer Science Software Engineering (inc Integrated Industrial and Professional Training) (MSc, 2 year)","MSc","0"},
			{"18","http://courses.aber.ac.uk/postgraduate/intelligent-systems-masters/","Intelligent Systems (MSc, 1 year)","MSc","0"},
			{"19","http://courses.aber.ac.uk/postgraduate/remote-sensing-and-computer-science-masters/","Remote Sensing and Computing Science (MSc, 1 year)","MSc","0"},
			{"20","http://courses.aber.ac.uk/postgraduate/computer-science-research/","Computer Science (PhD, 3 year)","","0"},
			{"21","http://courses.aber.ac.uk/postgraduate/pgce-ict-and-computer-science/"," ICT and Computer Science (PGCE, 1 year)","PG","0"},
			{"22","http://courses.aber.ac.uk/postgraduate/research-education/","Education (PhD, 3 year)","","0"},
			{"23","http://courses.aber.ac.uk/postgraduate/pgce-chemistry-balanced-science/","Chemistry with Balanced Science (PGCE, 1 year)","PG","0"},
			{"24","http://courses.aber.ac.uk/postgraduate/pgce-drama/","Drama (PGCE, 1 year)","PG","0"},
			{"25","http://courses.aber.ac.uk/postgraduate/pgce-english/","English (PGCE, 1 year)","PG","0"},
			{"26","http://courses.aber.ac.uk/postgraduate/pgce-french/","French (PGCE, 1 year)","PG","0"},
			{"27","http://courses.aber.ac.uk/postgraduate/pgce-geography/","Geography (PGCE, 1 year)","PG","0"},
			{"28","http://courses.aber.ac.uk/postgraduate/pgce-german/","German (PGCE, 1 year)","PG","0"},
			{"29","http://courses.aber.ac.uk/postgraduate/pgce-history/","History (PGCE, 1 year)","PG","0"},
			{"30","http://courses.aber.ac.uk/postgraduate/pgce-physics-balanced-science/","Physics with Balanced Science (PGCE, 1 year)","PG","0"},
			{"31","http://courses.aber.ac.uk/postgraduate/pgce-spanish/","Spanish (PGCE, 1 year)","PG","0"},
			{"32","http://courses.aber.ac.uk/postgraduate/tar-almaeneg/","Almaeneg (PGCE, 1 year)","PG","0"},
			{"33","http://courses.aber.ac.uk/postgraduate/tar-cemeg-gwyddoniaeth-gytbwys/","Cemeg gyda Gwyddoniaeth Gytbwys (PGCE, 1 year)","PG","0"},
			{"34","http://courses.aber.ac.uk/postgraduate/tar-drama/","Drama (Cyfrwng Cymraeg) (PGCE, 1 year)","PG","0"},
			{"35","http://courses.aber.ac.uk/postgraduate/tar-ffrangeg/","French (PGCE, 1 year)","PG","0"},
			{"36","http://courses.aber.ac.uk/postgraduate/tar-daearyddiaeth/","Geography (PGCE, 1 year)","PG","0"},
			{"37","http://courses.aber.ac.uk/postgraduate/tar-hanes/","History (PGCE, 1 year)","PG","0"},
			{"38","http://courses.aber.ac.uk/postgraduate/tar-ffiseg-gwyddoniaeth-gytbwys/","Physics with Balanced Science (PGCE, 1 year)","PG","0"},
			{"39","http://courses.aber.ac.uk/postgraduate/tar-saesneg/","Saesneg (PGCE, 1 year)","PG","0"},
			{"40","http://courses.aber.ac.uk/postgraduate/tar-sbaeneg/","Spanish (PGCE, 1 year)","PG","0"},
			{"41","http://courses.aber.ac.uk/postgraduate/tar-cymraeg/","Welsh (PGCE, 1 year)","PG","0"},
			{"42","http://courses.aber.ac.uk/postgraduate/creative-writing-masters/","Creative Writing (MA, 1 year)","MA","0"},
			{"43","http://courses.aber.ac.uk/postgraduate/literary-studies-masters/","Literary Studies (MA, 1 year)","MA","0"},
			{"44","http://courses.aber.ac.uk/postgraduate/literary-studies-american-studies-masters/","Literary Studies (American Studies) (MA, 1 year)","MA","0"},
			{"45","http://courses.aber.ac.uk/postgraduate/literary-studies-eighteenth-century-writing-and-romanticism-masters/","Literary Studies (Eighteenth Century Writing and Romanticism) (MA, 1 year)","MA","0"},
			{"46","http://courses.aber.ac.uk/postgraduate/literary-studies-medieval-and-renaissance-writing/","Literary Studies (Medieval and Renaissance Writing) (MA, 1 year)","MA","0"},
			{"47","http://courses.aber.ac.uk/postgraduate/literary-studies-post-modern-writing-masters/","Literary Studies (Post-modern Writing) (MA, 1 year)","MA","0"},
			{"48","http://courses.aber.ac.uk/postgraduate/literary-studies-welsh-writing-in-english-masters/","Literary Studies (Welsh Writing in English) (MA, 1 year)","MA","0"},
			{"49","http://courses.aber.ac.uk/postgraduate/literature-creative-writing-masters/","Literature and Creative Writing (MA, 1 year)","MA","0"},
			{"50","http://courses.aber.ac.uk/postgraduate/research-creative-writing/","Creative Writing (PhD, 3 year)","","0"},
			{"51","http://courses.aber.ac.uk/postgraduate/research-literature/","English (PhD, 3 year)","","0"},
			{"52","http://courses.aber.ac.uk/postgraduate/human-geography-masters/","Practising Human Geography (MA, 1 year)","MA","0"},
			{"53","http://courses.aber.ac.uk/postgraduate/regional-and-environmental-policy-masters/","Regional and Environmental Policy (MA, 1 year)","MA","0"},
			{"54","http://courses.aber.ac.uk/postgraduate/environmental-change-impact-adaptation-masters/","Environmental Change, Impact and Adaptation (MSc, 1 year)","MSc","0"},
			{"55","http://courses.aber.ac.uk/postgraduate/environmental-monitoring-and-analysis-masters/","Environmental Monitoring and Analysis (MSc, 1 year)","MSc","0"},
			{"56","http://courses.aber.ac.uk/postgraduate/glaciology-masters/","Glaciology (MSc, 1 year)","MSc","0"},
			{"57","http://courses.aber.ac.uk/postgraduate/gis-remote-sensing-masters/","Remote Sensing and GIS (MSc, 1 year)","MSc","0"},
			{"58","http://courses.aber.ac.uk/postgraduate/remote-sensing-and-planetary-science-masters/","Remote Sensing and Planetary Science (MSc, 1 year)","MSc","0"},
			{"59","http://courses.aber.ac.uk/postgraduate/remote-sensing-and-the-living-environment-masters/","Remote Sensing and the Living Environment (MSc, 1 year)","MSc","0"},
			{"60","http://courses.aber.ac.uk/postgraduate/research-iges/","Geography and Earth Sciences (Science) (PhD, 3 year)","","0"},
			{"61","http://courses.aber.ac.uk/postgraduate/eighteenth-century-Britain- masters/","Eighteenth Century Britain (MA, 1 year)","MA","0"},
			{"62","http://courses.aber.ac.uk/postgraduate/history-and-heritage-masters/","History and Heritage (MA, 1 year)","MA","0"},
			{"63","http://courses.aber.ac.uk/postgraduate/history-of-wales-masters/","History of Wales (MA, 1 year)","MA","0"},
			{"64","http://courses.aber.ac.uk/postgraduate/media-history-masters/","Media History (MA, 1 year)","MA","0"},
			{"65","http://courses.aber.ac.uk/postgraduate/medieval-british-and-european-history-masters/","Medieval Britain &amp; Europe (MA, 1 year)","MA","0"},
			{"66","http://courses.aber.ac.uk/postgraduate/modern-british-history-masters/","Modern British History (MA, 1 year)","MA","0"},
			{"67","http://courses.aber.ac.uk/postgraduate/modern-european-history-masters/","Modern European History (MA, 1 year)","MA","0"},
			{"68","http://courses.aber.ac.uk/postgraduate/modern-history-masters/","Modern History (MA, 1 year)","MA","0"},
			{"69","http://courses.aber.ac.uk/postgraduate/research-history/","History (PhD, 3 year)","","0"},
			{"70","http://courses.aber.ac.uk/postgraduate/archive-administration-masters/","Archive Administration (MA, 1 year)","MA","0"},
			{"71","http://courses.aber.ac.uk/postgraduate/archive-administration-distance-learning-masters/","Archive Administration (MA, 5 year)","MA","0"},
			{"72","http://courses.aber.ac.uk/postgraduate/information-and-library-studies-masters/","Information and Library Studies (MA, 1 year)","MA","0"},
			{"73","http://courses.aber.ac.uk/postgraduate/information-and-library-studies-distance-learning-masters/","Information and Library Studies (MA, 5 year)","MA","0"},
			{"74","http://courses.aber.ac.uk/postgraduate/digital-curation-masters/","Digital Curation (MSc, 1 year)","MSc","0"},
			{"75","http://courses.aber.ac.uk/postgraduate/digital-information-services-masters/","Digital Information services (MSc, 1 year)","MSc","0"},
			{"76","http://courses.aber.ac.uk/postgraduate/management-of-library-information-services-distance-learning-masters/","Management of Library and Information Services (MSc, 5 year)","MSc","0"},
			{"77","http://courses.aber.ac.uk/postgraduate/research-information-studies/","Librarianship (PhD, 3 year)","","0"},
			{"78","http://courses.aber.ac.uk/postgraduate/critical-international-politics-masters/","Critical International Politics (Specialist) (MA, 1 year)","MA","0"},
			{"79","http://courses.aber.ac.uk/postgraduate/intelligence-and-strategic-studies-masters/","Intelligence and Strategic Studies (Specialist) (MA, 1 year)","MA","0"},
			{"80","http://courses.aber.ac.uk/postgraduate/international-relations-masters/","International Relations (Specialist) (MA, 1 year)","MA","0"},
			{"81","http://courses.aber.ac.uk/postgraduate/postcolonial-politics-masters/","Postcolonial Politics (Specialist) (MA, 1 year)","MA","0"},
			{"82","http://courses.aber.ac.uk/postgraduate/security-studies-masters-specialist/","Security Studies (Specialist) (MA, 1 year)","MA","0"},
			{"83","http://courses.aber.ac.uk/postgraduate/critical-international-politics-research-masters/","Critical International Politics (Research) (MA, 1 year)","MA","0"},
			{"84","http://courses.aber.ac.uk/postgraduate/international-relations-masters-research/","International Relations (Research Training) (MA, 1 year)","MA","0"},
			{"85","http://courses.aber.ac.uk/postgraduate/postcolonial-politics-research-training-masters/","Postcolonial Politics (Research Training) (MA, 1 year)","MA","0"},
			{"86","http://courses.aber.ac.uk/postgraduate/security-studies-masters-research/","Security Studies (Research Training) (MA, 1 year)","MA","0"},
			{"87","http://courses.aber.ac.uk/postgraduate/politics-media-performance-masters/","Politics, Media and Performance (MA, 1 year)","MA","0"},
			{"88","http://courses.aber.ac.uk/postgraduate/international-politics-mphil/","International Politics (MPhil, 1 year)","","0"},
			{"89","http://courses.aber.ac.uk/postgraduate/research-international-politics/","International Politics (PhD, 3 year)","","0"},
			{"90","http://courses.aber.ac.uk/postgraduate/research-law-criminology/","Law (PhD, 3 year)","","0"},
			{"91","http://courses.aber.ac.uk/postgraduate/climate-change-human-rights-masters/","Climate Change and Human Rights (LLM, 1 year)","LLM","0"},
			{"92","http://courses.aber.ac.uk/postgraduate/democracy-human-security-international-law-masters/","Democracy, Human Security and International Law (LLM, 1 year)","LLM","0"},
			{"93","http://courses.aber.ac.uk/postgraduate/human-rights-development-masters/","Human Rights and Development (LLM, 1 year)","LLM","0"},
			{"94","http://courses.aber.ac.uk/postgraduate/human-rights-humanitarian-law-masters/","Human Rights and Humanitarian Law (LLM, 1 year)","LLM","0"},
			{"95","http://courses.aber.ac.uk/postgraduate/human-rights-and-humanitarian-law-distance-learning-masters/","Human Rights and Humanitarian Law (LLM, 5 year)","LLM","0"},
			{"96","http://courses.aber.ac.uk/postgraduate/information-technology-law-masters/","Information Technology Law (LLM, 1 year)","LLM","0"},
			{"97","http://courses.aber.ac.uk/postgraduate/international-commercial-law-masters/","International Commercial Law (LLM, 1 year)","LLM","0"},
			{"98","http://courses.aber.ac.uk/postgraduate/international-commercial-law-human-rights-masters/","International Commercial Law and Human Rights (LLM, 1 year)","LLM","0"},
			{"99","http://courses.aber.ac.uk/postgraduate/international-commercial-law-environment-masters/","International Commercial Law and the Environment (LLM, 1 year)","LLM","0"},
			{"100","http://courses.aber.ac.uk/postgraduate/international-law-criminology-armed-conflict-masters/","International Law and Criminology of Armed Conflict (LLM, 1 year)","LLM","0"},
			{"101","http://courses.aber.ac.uk/postgraduate/internet-commerce-law-masters/","Internet Commerce and Law (LLM, 1 year)","LLM","0"},
			{"102","http://courses.aber.ac.uk/postgraduate/law-masters/","Law (LLM, 1 year)","LLM","0"},
			{"103","http://courses.aber.ac.uk/postgraduate/rights-gender-international-law-masters/","Rights, Gender and International Law (LLM, 1 year)","LLM","0"},
			{"104","http://courses.aber.ac.uk/postgraduate/international-business-management/","International Business Management (MSc, 1 year)","MSc","0"},
			{"105","http://courses.aber.ac.uk/postgraduate/international-finance-masters/","International Finance (MSc, 1 year)","MSc","0"},
			{"106","http://courses.aber.ac.uk/postgraduate/international-finance-banking-masters/","International Finance and Banking (MSc, 1 year)","MSc","0"},
			{"107","http://courses.aber.ac.uk/postgraduate/management-masters/","Management (MSc, 1 year)","MSc","0"},
			{"108","http://courses.aber.ac.uk/postgraduate/management-finance-masters/","Management and Finance (MSc, 1 year)","MSc","0"},
			{"109","http://courses.aber.ac.uk/postgraduate/management-marketing-masters/","Management and Marketing (MSc, 1 year)","MSc","0"},
			{"110","http://courses.aber.ac.uk/postgraduate/research-management-business/","Management and Business (PhD, 3 year)","","0"},
			{"111","http://courses.aber.ac.uk/postgraduate/research-mathematics/","Mathematics (PhD, 3 year)","","0"},
			{"112","http://courses.aber.ac.uk/postgraduate/applied-translation-masters/","Applied Translation (MA, 1 year)","MA","0"},
			{"113","http://courses.aber.ac.uk/postgraduate/research-european-languages/","European Languages (PhD, 3 year)","","0"},
			{"114","http://courses.aber.ac.uk/postgraduate/research-physics/","Physics (PhD, 3 year)","","0"},
			{"115","http://courses.aber.ac.uk/postgraduate/research-psychology/","Psychology (PhD, 3 year)","","0"},
			{"116","http://courses.aber.ac.uk/postgraduate/animal-science-masters/","Animal Science (MSc, 1 year)","MSc","0"},
			{"117","http://courses.aber.ac.uk/postgraduate/equine-science-masters/","Equine Science (MSc, 1 year)","MSc","0"},
			{"118","http://courses.aber.ac.uk/postgraduate/livestock-science-masters/","Livestock Science (MSc, 1 year)","MSc","0"},
			{"119","http://courses.aber.ac.uk/postgraduate/film-studies-masters/","Film Studies (MA, 1 year)","MA","0"},
			{"120","http://courses.aber.ac.uk/postgraduate/research-tfts/","Theatre, Film and Television Studies (PhD, 3 year)","","0"},
			{"121","http://courses.aber.ac.uk/postgraduate/cyfryngau-creadigol-ymarferol/","Creative Media Practices (MA, 1 year)","MA","0"},
			{"122","http://courses.aber.ac.uk/postgraduate/irish-masters/","Irish (MA, 1 year)","MA","0"},
			{"123","http://courses.aber.ac.uk/postgraduate/medieval-welsh-literature-masters/","Medieval Welsh Literature (MA, 1 year)","MA","0"},
			{"124","http://courses.aber.ac.uk/postgraduate/research-welsh/","Cymraeg (PhD, 3 year)","","0"},
			{"125","http://courses.aber.ac.uk/postgraduate/ma-ysgrifennu-creadigol/","Creative Writing (MA, 1 year)","MA","0"},

		};
	

}
