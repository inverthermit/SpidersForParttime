package no6.chicheester;

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

	//
	/*
	 Un:http://www.chi.ac.uk/search/course-search-results?search_api_views_fulltext=&field_department=All&page=1&f[0]=field_year%3A2017&f[1]=field_course_type%3AUndergraduate
	 page:0-16
	 
	 Post:http://www.chi.ac.uk/search/course-search-results?search_api_views_fulltext=&field_department=All&page=5&f[0]=field_year%3A2017&f[1]=field_course_type%3APostgraduate
	 page:0-5
	 */

	public static String url1="http://www.chi.ac.uk/search/course-search-results?search_api_views_fulltext=&field_department=All&page=";
	public static String url2="&f[0]=field_year%3A2017&f[1]=field_course_type%3AUndergraduate";
	public static String url3="http://www.chi.ac.uk/search/course-search-results?search_api_views_fulltext=&field_department=All&page=";
	public static String url4="&f[0]=field_year%3A2017&f[1]=field_course_type%3APostgraduate";
	public static void main(String[] args) throws Exception{
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		int count=1;
		for(int i=0;i<=16;i++)
		{
			int index=i;
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url3+index+url4);
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			Parser	parser=Parser.createParser(htmls, "utf-8");
	   	    AndFilter dFilter=new AndFilter(new TagNameFilter("h2"),
	                   new HasAttributeFilter("class","field-content"));
	   	    NodeList nodes3=parser.extractAllNodesThatMatch(dFilter);
	   	    for(int k=0;k<nodes3.size();k++)
	   	    {
	   	    	htmls=nodes3.elementAt(k).toHtml();
	   	    	parser=Parser.createParser(htmls, "utf-8");
		   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
		                   new HasAttributeFilter("href"));
		   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
		   	    for(int j=0;j<nodes4.size();j++)
		   	    {
		   	    	LinkTag link=(LinkTag)nodes4.elementAt(j);
		   	    	//if(link.getAttribute("href").contains("http://www.ulster.ac.uk/"))
		   	    	{//.replaceAll("<span[\\s\\S]*/span>","")
		   	    		String temp=link.toHtml();
		   	    		
		   	    		System.out.println("{\""+count+"\",\"http://www.chi.ac.uk/"+link.getAttribute("href")+"\",\""+html2Str(temp).replace("\r\n", "").trim()+"\",\"0\"},");
		                count++;
		   	    	}
		   	    	
		   	    }
	   	    }
	   	    	
	   	    
		    
		}
		//System.out.println("DONE.");
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
		
		public static String[][] UnData={
			{"1","http://www.chi.ac.uk//ba-hons-dance","BA (Hons) Dance","0"},
			{"2","http://www.chi.ac.uk//ba-hons-digital-film-production-and-screenwriting","BA (Hons) Digital Film Production and Screenwriting","0"},
			{"3","http://www.chi.ac.uk//ba-hons-early-childhood-studies-top-degree","BA (Hons) Early Childhood Studies Top-up Degree","0"},
			{"4","http://www.chi.ac.uk//ba-hons-early-childhood-studies","BA (Hons) Early Childhood Studies","0"},
			{"5","http://www.chi.ac.uk//ba-hons-english-literature-and-drama-studies-0","BA (Hons) English Literature and Drama Studies","0"},
			{"6","http://www.chi.ac.uk//ba-hons-english-literature-and-language","BA (Hons) English Literature and Language","0"},
			{"7","http://www.chi.ac.uk//ba-hons-english-literature","BA (Hons) English Literature","0"},
			{"8","http://www.chi.ac.uk//ba-hons-accounting-and-finance","BA (Hons) Accounting and Finance","0"},
			{"9","http://www.chi.ac.uk//ba-hons-music","BA (Hons) Music","0"},
			{"10","http://www.chi.ac.uk//bsc-hons-sport-and-exercise-psychology","BSc (Hons) Sport and Exercise Psychology","0"},
			{"11","http://www.chi.ac.uk//bsc-hons-sport-and-exercise-science","BSc (Hons) Sport and Exercise Science","0"},
			{"12","http://www.chi.ac.uk//ba-hons-musical-theatre-triple-threat","BA (Hons) Musical Theatre (Triple Threat)","0"},
			{"13","http://www.chi.ac.uk//ba-hons-sport-business-and-management","BA (Hons) Sport Business and Management","0"},
			{"14","http://www.chi.ac.uk//bsc-hons-sport-science-and-coaching","BSc (Hons) Sport Science and Coaching","0"},
			{"15","http://www.chi.ac.uk//ba-hons-sport-development-and-coaching","BA (Hons) Sport Development and Coaching","0"},
			{"16","http://www.chi.ac.uk//ba-hons-sport-studies","BA (Hons) Sport Studies","0"},
			{"17","http://www.chi.ac.uk//bsc-hons-sports-therapy","BSc (Hons) Sports Therapy","0"},
			{"18","http://www.chi.ac.uk//ba-hons-physical-education-and-sports-coaching","BA (Hons) Physical Education and Sports Coaching","0"},
			{"19","http://www.chi.ac.uk//bsc-hons-politics","BSc (Hons) Politics","0"},
			{"20","http://www.chi.ac.uk//ba-hons-social-work","BA (Hons) Social Work","0"},
			{"21","http://www.chi.ac.uk//ba-hons-event-management","BA (Hons) Event Management","0"},
			{"22","http://www.chi.ac.uk//ba-hons-outdoor-and-adventure-education","BA (Hons) Outdoor and Adventure Education","0"},
			{"23","http://www.chi.ac.uk//ba-hons-fine-art","BA (Hons) Fine Art","0"},
			{"24","http://www.chi.ac.uk//ba-hons-creative-writing-and-english","BA (Hons) Creative Writing and English","0"},
			{"25","http://www.chi.ac.uk//ba-hons-film-and-television-studies","BA (Hons) Film and Television Studies","0"},
			{"26","http://www.chi.ac.uk//ba-hons-business-studies","BA (Hons) Business Studies","0"},
			{"27","http://www.chi.ac.uk//bsc-hons-football-coaching-and-performance","BSc (Hons) Football Coaching and Performance","0"},
			{"28","http://www.chi.ac.uk//bsc-hons-software-development-business","BSc (Hons) Software Development for Business","0"},
			{"29","http://www.chi.ac.uk//ba-hons-humanistic-counselling","BA (Hons) Humanistic Counselling","0"},
			{"30","http://www.chi.ac.uk//ba-hons-human-resource-management","BA (Hons) Human Resource Management","0"},
			{"31","http://www.chi.ac.uk//ba-hons-media","BA (Hons) Media","0"},
			{"32","http://www.chi.ac.uk//ba-hons-marketing","BA (Hons) Marketing","0"},
			{"33","http://www.chi.ac.uk//fdsc-community-sport-coaching","FdSc Community Sport Coaching","0"},
			{"34","http://www.chi.ac.uk//bsc-hons-community-sport-coaching-top","BSc (Hons) Community Sport Coaching (Top Up)","0"},
			{"35","http://www.chi.ac.uk//ba-hons-theatre","BA (Hons) Theatre","0"},
			{"36","http://www.chi.ac.uk//ba-hons-children-and-families","BA (Hons) Children and Families","0"},
			{"37","http://www.chi.ac.uk//ba-hons-adult-social-care","BA (Hons) Adult Social Care","0"},
			{"38","http://www.chi.ac.uk//bmus-hons-musical-theatre","BMus (Hons) Musical Theatre","0"},
			{"39","http://www.chi.ac.uk//ba-hons-physical-education-primary-years","BA (Hons) Physical Education in the Primary Years","0"},
			{"40","http://www.chi.ac.uk//ba-hons-physical-education-secondary-years","BA (Hons) Physical Education in the Secondary Years","0"},
			{"41","http://www.chi.ac.uk//ba-hons-primary-teaching-subject-specialism-0","BA (Hons) Primary Teaching with subject specialism","0"},
			{"42","http://www.chi.ac.uk//ba-hons-history","BA (Hons) History","0"},
			{"43","http://www.chi.ac.uk//ba-hons-charity-development","BA (Hons) Charity Development","0"},
			{"44","http://www.chi.ac.uk//bsc-hons-community-sport-coaching","BSc (Hons) Community Sport Coaching","0"},
			{"45","http://www.chi.ac.uk//ba-hons-medieval-and-early-modern-history","BA (Hons) Medieval and Early Modern History","0"},
			{"46","http://www.chi.ac.uk//commercial-music-platform-one-isle-wight","Commercial Music (Platform One, Isle of Wight)","0"},
			{"47","http://www.chi.ac.uk//fda-early-years","FdA Early Years","0"},
			{"48","http://www.chi.ac.uk//ba-hons-education-and-early-childhood","BA (Hons) Education and Early Childhood","0"},
			{"49","http://www.chi.ac.uk//ba-hons-education-special-needs-and-disability","BA (Hons) Education, Special Needs and Disability","0"},
			{"50","http://www.chi.ac.uk//ba-hons-english-and-ies-0","BA (Hons) English and IES","0"},
			{"51","http://www.chi.ac.uk//ba-hons-music-community-music","BA (Hons) Music with Community Music","0"},
			{"52","http://www.chi.ac.uk//ba-hons-music-instrumentalvocal-teaching","BA (Hons) Music with Instrumental/Vocal Teaching","0"},
			{"53","http://www.chi.ac.uk//ba-hons-music-international-english-studies","BA (Hons) Music with International English Studies","0"},
			{"54","http://www.chi.ac.uk//ba-hons-music-music-marketing-and-administration","BA (Hons) Music with Music, Marketing and Administration","0"},
			{"55","http://www.chi.ac.uk//ba-hons-musical-theatre-and-arts-development","BA (Hons) Musical Theatre and Arts Development","0"},
			{"56","http://www.chi.ac.uk//ba-hons-painting-and-drawing","BA (Hons) Painting and Drawing","0"},
			{"57","http://www.chi.ac.uk//bmus-hons-performance","BMus (Hons) Performance","0"},
			{"58","http://www.chi.ac.uk//fda-teaching-and-learning-support","FdA Teaching and Learning Support","0"},
			{"59","http://www.chi.ac.uk//bmus-hons-vocal-performance","BMus (Hons) Vocal Performance","0"},
			{"60","http://www.chi.ac.uk//ba-hons-politics-and-contemporary-history","BA (Hons) Politics and Contemporary History","0"},
			{"61","http://www.chi.ac.uk//bmus-hons-vocal-teaching","BMus (Hons) Vocal Teaching","0"},
			{"62","http://www.chi.ac.uk//ba-hons-primary-teaching-early-years-qts","BA (Hons) Primary Teaching with Early Years with QTS","0"},
			{"63","http://www.chi.ac.uk//ba-hons-music-musical-theatre","BA (Hons) Music with Musical Theatre","0"},
			{"64","http://www.chi.ac.uk//ba-hons-event-management-and-sustainable-tourism-management","BA (Hons) Event Management and Sustainable Tourism Management","0"},
			{"65","http://www.chi.ac.uk//ba-hons-business-studies-and-finance","BA (Hons) Business Studies and Finance","0"},
			{"66","http://www.chi.ac.uk//ba-hons-business-studies-and-economics","BA (Hons) Business Studies and Economics","0"},
			{"67","http://www.chi.ac.uk//ba-hons-event-management-and-finance","BA (Hons) Event Management and Finance","0"},
			{"68","http://www.chi.ac.uk//ba-hons-business-studies-and-event-management","BA (Hons) Business Studies and Event Management","0"},
			{"69","http://www.chi.ac.uk//ba-hons-event-management-and-human-resource-management","BA (Hons) Event Management and Human Resource Management","0"},
			{"70","http://www.chi.ac.uk//ba-hons-business-studies-and-human-resource-management","BA (Hons) Business Studies and Human Resource Management","0"},
			{"71","http://www.chi.ac.uk//ba-hons-arts-development-and-digital-film-production-and-screenwriting","BA (Hons) Arts Development and Digital Film Production and Screenwriting","0"},
			{"72","http://www.chi.ac.uk//ba-hons-event-management-and-it-management-business","BA (Hons) Event Management and IT Management for Business","0"},
			{"73","http://www.chi.ac.uk//ba-hons-business-studies-and-it-management","BA (Hons) Business Studies and IT Management","0"},
			{"74","http://www.chi.ac.uk//ba-hons-event-management-international-english","BA (Hons) Event Management with International English","0"},
			{"75","http://www.chi.ac.uk//ba-hons-business-studies-and-marketing","BA (Hons) Business Studies and Marketing","0"},
			{"76","http://www.chi.ac.uk//ba-hons-fine-art-and-international-english-studies","BA (Hons) Fine Art and International English Studies","0"},
			{"77","http://www.chi.ac.uk//ba-hons-fine-art-printmaking","BA (Hons) Fine Art with Printmaking","0"},
			{"78","http://www.chi.ac.uk//ba-hons-fine-art-sculpture","BA (Hons) Fine Art with Sculpture","0"},
			{"79","http://www.chi.ac.uk//ba-hons-fine-art-textiles","BA (Hons) Fine Art with Textiles","0"},
			{"80","http://www.chi.ac.uk//ba-hons-accounting-and-finance-and-international-english-studies","BA (Hons) Accounting and Finance and International English Studies","0"},
			{"81","http://www.chi.ac.uk//ba-hons-finance-and-economics","BA (Hons) Finance and Economics","0"},
			{"82","http://www.chi.ac.uk//fdsc-football-coaching-and-performance","FdSc Football Coaching and Performance","0"},
			{"83","http://www.chi.ac.uk//ba-hons-business-studies-international-english-studies","BA (Hons) Business Studies with International English Studies","0"},
			{"84","http://www.chi.ac.uk//ba-hons-business-studies-and-international-english-studies","BA (Hons) Business Studies and International English Studies","0"},
			{"85","http://www.chi.ac.uk//ba-hons-human-resource-management-and-finance","BA (Hons) Human Resource Management and Finance","0"},
			{"86","http://www.chi.ac.uk//ba-hons-human-resource-management-and-international-english","BA (Hons) Human Resource Management and International English","0"},
			{"87","http://www.chi.ac.uk//bsc-hons-mathematics-and-teaching-key-stages-2-and-3","BSc (Hons) Mathematics and Teaching for Key Stages 2 and 3","0"},
			{"88","http://www.chi.ac.uk//ba-hons-human-resource-management-international-english","BA (Hons) Human Resource Management with International English","0"},
			{"89","http://www.chi.ac.uk//bsc-hons-it-management-business","BSc (Hons) IT Management for Business","0"},
			{"90","http://www.chi.ac.uk//bsc-hons-mathematics-and-mathematical-learning","BSc (Hons) Mathematics and Mathematical Learning","0"},
			{"91","http://www.chi.ac.uk//ba-hons-it-management-business-and-human-resource-management","BA (Hons) IT Management for Business and Human Resource Management","0"},
			{"92","http://www.chi.ac.uk//ba-hons-it-management-business-and-international-english-studies","BA (Hons) IT Management for Business and International English Studies","0"},
			{"93","http://www.chi.ac.uk//ba-hons-marketing-and-event-management","BA (Hons) Marketing and Event Management","0"},
			{"94","http://www.chi.ac.uk//ba-hons-it-management-business-international-english-studies","BA (Hons) IT Management for Business with International English Studies","0"},
			{"95","http://www.chi.ac.uk//bmus-hons-instrumental-teaching","BMus (Hons) Instrumental Teaching","0"},
			{"96","http://www.chi.ac.uk//ba-hons-international-english-studies-and-media","BA (Hons) International English Studies and Media","0"},
			{"97","http://www.chi.ac.uk//ba-hons-marketing-and-it-management-business","BA (Hons) Marketing and IT Management for Business","0"},
			{"98","http://www.chi.ac.uk//bmus-hons-jazz-performance","BMus (Hons) Jazz Performance","0"},
			{"99","http://www.chi.ac.uk//ba-hons-marketing-and-human-resource-management","BA (Hons) Marketing and Human Resource Management","0"},
			{"100","http://www.chi.ac.uk//ba-hons-marketing-and-finance","BA (Hons) Marketing and Finance","0"},
			{"101","http://www.chi.ac.uk//fda-graphic-design","(FdA) Graphic Design","0"},
			{"102","http://www.chi.ac.uk//computing-and-software-development-hnd","Computing and Software Development HND","0"},
			{"103","http://www.chi.ac.uk//ba-hons-commercial-music-top","BA (Hons) Commercial Music (Top Up)","0"},
			{"104","http://www.chi.ac.uk//ba-hons-media-and-international-english-studies","BA (Hons) Media and International English Studies","0"},
			{"105","http://www.chi.ac.uk//ba-hons-creative-writing-and-theology-and-religious-studies","BA (Hons) Creative Writing and Theology and Religious Studies","0"},
			{"106","http://www.chi.ac.uk//ba-hons-event-management-and-international-english","BA (Hons) Event Management and International English","0"},
			{"107","http://www.chi.ac.uk//ba-hons-marketing-and-international-english-studies","BA (Hons) Marketing and International English Studies","0"},
			{"108","http://www.chi.ac.uk//ba-hons-marketing-international-english-studies","BA (Hons) Marketing with International English Studies","0"},
			{"109","http://www.chi.ac.uk//ba-hons-music-and-musical-theatre","BA (Hons) Music and Musical Theatre","0"},
			{"110","http://www.chi.ac.uk//ba-hons-charity-development-and-music-performance","BA (Hons) Charity Development and Music Performance","0"},
			{"111","http://www.chi.ac.uk//ba-hons-international-english-studies-and-music","BA (Hons) International English Studies and Music","0"},
			{"112","http://www.chi.ac.uk//ba-hons-fine-art-international-english-studies","BA (Hons) Fine Art with International English Studies","0"},
			{"113","http://www.chi.ac.uk//ba-hons-history-and-international-english","BA (Hons) History and International English","0"},
			{"114","http://www.chi.ac.uk//ba-hons-history-international-english-studies","BA (Hons) History with International English Studies","0"},
			{"115","http://www.chi.ac.uk//ba-hons-history-and-music","BA (Hons) History and Music","0"},
			{"116","http://www.chi.ac.uk//ba-hons-history-and-theology-religious-studies","BA (Hons) History and Theology &amp; Religious Studies","0"},
			{"117","http://www.chi.ac.uk//ba-hons-history-theology-and-religious-studies","BA (Hons) History with Theology and Religious Studies","0"},
			{"118","http://www.chi.ac.uk//ba-hons-theology-and-religious-studies-history","BA (Hons) Theology and Religious Studies with History","0"},
			{"119","http://www.chi.ac.uk//ba-hons-theology-and-religious-studies-and-international-english-studies","BA (Hons) Theology and Religious Studies and International English Studies","0"},
			{"120","http://www.chi.ac.uk//ba-hons-theology-and-religious-studies-international-english-studies","BA (Hons) Theology and Religious Studies with International English Studies","0"},
			{"121","http://www.chi.ac.uk//msci-advanced-applied-psychology","MSci Advanced Applied Psychology","0"},
			{"122","http://www.chi.ac.uk//bmus-hons-orchestral-performance","BMus (Hons) Orchestral Performance","0"},
			{"123","http://www.chi.ac.uk//bmus-hons-choral-directing","BMus (Hons) Choral Directing","0"},
			{"124","http://www.chi.ac.uk//ba-hons-sports-media","BA (Hons) Sports Media","0"},
			{"125","http://www.chi.ac.uk//ba-hons-modern-history","BA (Hons) Modern History","0"},
			{"126","http://www.chi.ac.uk//ba-hons-business-english-international-top-route","BA (Hons) Business English (International Top Up Route)","0"},
			{"127","http://www.chi.ac.uk//ba-hons-finance-and-accounting-international-top-route","BA (Hons) Finance and Accounting (International Top Up Route)","0"},
			{"128","http://www.chi.ac.uk//bsc-hons-sustainable-tourism-management","BSc (Hons) Sustainable Tourism Management","0"},
			{"129","http://www.chi.ac.uk//ba-hons-creative-writing-and-history","BA (Hons) Creative Writing and History","0"},
			{"130","http://www.chi.ac.uk//ba-hons-creative-writing-and-media","BA (Hons) Creative Writing and Media","0"},
			{"131","http://www.chi.ac.uk//ba-hons-business-and-management-day-release-degree","BA (Hons) Business and Management (Day Release Degree)","0"},
			{"132","http://www.chi.ac.uk//ba-hons-acting","BA (Hons) Acting","0"},
			{"133","http://www.chi.ac.uk//bsc-hons-digital-marketing","BSC (Hons) Digital Marketing","0"},
			{"134","http://www.chi.ac.uk//ba-hons-english-literature-language-and-media","BA (Hons) English Literature &amp; Language and Media","0"},
			{"135","http://www.chi.ac.uk//ba-hons-creative-writing-and-philosophy-ethics","BA (Hons) Creative Writing and Philosophy &amp; Ethics","0"},
			{"136","http://www.chi.ac.uk//ba-hons-english-literature-drama-studies-and-politics","BA (Hons) English Literature &amp; Drama Studies and Politics","0"},
			{"137","http://www.chi.ac.uk//ba-hons-english-literature-and-drama-studies-and-media","BA (Hons) English Literature and Drama Studies and Media","0"},
			{"138","http://www.chi.ac.uk//ba-hons-english-literature-and-drama-studies-and-history","BA (Hons) English Literature and Drama Studies and History","0"},
			{"139","http://www.chi.ac.uk//ba-hons-english-literature-and-language-and-history","BA (Hons) English Literature and Language and History","0"},
			{"140","http://www.chi.ac.uk//ba-hons-english-literature-and-language-and-politics","BA (Hons) English Literature and Language and Politics","0"},
			{"141","http://www.chi.ac.uk//ba-hons-english-literature-and-history","BA (Hons) English Literature and History","0"},
			{"142","http://www.chi.ac.uk//ba-hons-english-literature-and-media","BA (Hons) English Literature and Media","0"},
			{"143","http://www.chi.ac.uk//ba-hons-english-literature-and-politics","BA (Hons) English Literature and Politics","0"},
			{"144","http://www.chi.ac.uk//ba-hons-english-literature-and-philosophy-and-ethics","BA (Hons) English Literature and Philosophy and Ethics","0"},
			{"145","http://www.chi.ac.uk//ba-hons-history-and-philosophy-and-ethics","BA (Hons) History and Philosophy and Ethics","0"},
			{"146","http://www.chi.ac.uk//ba-hons-politics-and-theology-religious-studies","BA (Hons) Politics and Theology &amp; Religious Studies","0"},
			{"147","http://www.chi.ac.uk//ba-hons-politics-and-philosophy-and-ethics","BA (Hons) Politics and Philosophy and Ethics","0"},
			{"148","http://www.chi.ac.uk//fdn-management","Fdn Management","0"},
			{"149","http://www.chi.ac.uk//english-literature-theology-religious-studies","English Literature &amp; Theology Religious Studies","0"},
			{"150","http://www.chi.ac.uk//ba-hons-english-literature-theology-religious-studies","BA (Hons) English Literature &amp; Theology Religious Studies","0"},
			{"151","http://www.chi.ac.uk//ba-hons-business-and-management-international-top-route","BA (Hons) Business and Management (International Top Up Route)","0"},
			{"152","http://www.chi.ac.uk//ba-hons-education","BA (Hons) Education","0"},
			{"153","http://www.chi.ac.uk//ba-hons-screenwriting","BA (Hons) Screenwriting","0"},
			{"154","http://www.chi.ac.uk//bsc-hons-creative-and-digital-media","BSc (Hons) Creative and Digital Media","0"},
			{"155","http://www.chi.ac.uk//bsc-hons-marketing-and-sustainable-tourism-management","BSc (Hons) Marketing and Sustainable Tourism Management","0"},
			{"156","http://www.chi.ac.uk//bsc-hons-sustainable-tourism-management-international-english-studies","BSc (Hons) Sustainable Tourism Management with International English Studies","0"},
			{"157","http://www.chi.ac.uk//ba-hons-music-performance-and-acting-film","BA (Hons) Music Performance and Acting for Film","0"},
			{"158","http://www.chi.ac.uk//ba-hons-digital-film-production-and-acting-film","BA (Hons) Digital Film Production and Acting for Film","0"},
			{"159","http://www.chi.ac.uk//ba-hons-film-and-television-studies-acting-film","BA (Hons) Film and Television Studies &amp; Acting for Film","0"},
			{"160","http://www.chi.ac.uk//ba-hons-media-and-acting-film","BA (Hons) Media and Acting for Film","0"},
			{"161","http://www.chi.ac.uk//ba-hons-musical-theatre-and-acting-film","BA (Hons) Musical Theatre and Acting for Film","0"},
			{"162","http://www.chi.ac.uk//ba-hons-screenwriting-and-acting-film","BA (Hons) Screenwriting and Acting for Film","0"},
			{"163","http://www.chi.ac.uk//ba-hons-international-english-studies-0","BA (Hons) International English Studies","0"},
			{"164","http://www.chi.ac.uk//ba-hons-creative-writing","BA (Hons) Creative Writing","0"},
			{"165","http://www.chi.ac.uk//ba-hons-drama-and-theatre","BA (Hons) Drama and Theatre","0"},
			{"166","http://www.chi.ac.uk//ba-hons-music-performance","BA (Hons) Music Performance","0"},
			{"167","http://www.chi.ac.uk//ba-hons-religion-ethics-and-society","BA (Hons) Religion, Ethics and Society","0"},
			{"168","http://www.chi.ac.uk//early-years-teacher-status-eyts","Early Years Teacher Status EYTS","0"}
		};

		public static String[][] PostData={
			{"1","http://www.chi.ac.uk//pgce-primary-teaching","PGCE Primary Teaching","0"},
			{"2","http://www.chi.ac.uk//pgce-secondary-education","PGCE Secondary Education","0"},
			{"3","http://www.chi.ac.uk//ma-choreography-and-professional-practices","MA Choreography and Professional Practices","0"},
			{"4","http://www.chi.ac.uk//contemporary-dance-postgraduate-diploma","Contemporary Dance Postgraduate Diploma","0"},
			{"5","http://www.chi.ac.uk//ma-creative-writing-0","MA Creative Writing","0"},
			{"6","http://www.chi.ac.uk//ma-cultural-history","MA Cultural History","0"},
			{"7","http://www.chi.ac.uk//ma-early-years-professional-practices","MA Early Years Professional Practices","0"},
			{"8","http://www.chi.ac.uk//msc-sport-and-exercise-biomechanics","MSc Sport and Exercise Biomechanics","0"},
			{"9","http://www.chi.ac.uk//msc-sport-and-exercise-physiology","MSc Sport and Exercise Physiology","0"},
			{"10","http://www.chi.ac.uk//msc-sport-and-exercise-therapy","MSc Sport and Exercise Therapy","0"},
			{"11","http://www.chi.ac.uk//msc-sports-performance-analysis","MSc Sports Performance Analysis","0"},
			{"12","http://www.chi.ac.uk//ma-performance-theatre-or-theatre-collectives","MA Performance: Theatre or Theatre Collectives","0"},
			{"13","http://www.chi.ac.uk//ma-teaching-english-speakers-other-languages-tesol","MA Teaching English to Speakers of Other Languages TESOL","0"},
			{"14","http://www.chi.ac.uk//mfa-theatre-performance-making-san-francisco","MFA Theatre - Performance Making (San Francisco)","0"},
			{"15","http://www.chi.ac.uk//ma-pg-dip-pg-certificate-practice-education","MA, PG Dip, PG Certificate Practice Education","0"},
			{"16","http://www.chi.ac.uk//ma-social-work","MA Social Work","0"},
			{"17","http://www.chi.ac.uk//ma-performance-dance-mapdance","MA Performance: Dance (mapdance)","0"},
			{"18","http://www.chi.ac.uk//msc-sport-and-exercise-psychology-bps-stage-1","MSc Sport and Exercise Psychology (BPS Stage 1)","0"},
			{"19","http://www.chi.ac.uk//msc-psychology-sport-and-exercise","MSc Psychology of Sport and Exercise","0"},
			{"20","http://www.chi.ac.uk//ma-fine-art","MA Fine Art","0"},
			{"21","http://www.chi.ac.uk//msc-sports-coaching-science","MSc Sports Coaching Science","0"},
			{"22","http://www.chi.ac.uk//msc-strength-and-conditioning","MSc Strength and Conditioning","0"},
			{"23","http://www.chi.ac.uk//ma-music-performance","MA Music Performance","0"},
			{"24","http://www.chi.ac.uk//ma-education-ma-ed","MA Education (MA Ed)","0"},
			{"25","http://www.chi.ac.uk//advanced-professional-practice-leadership-and-management-health-and-social-care-pathway","Advanced Professional Practice: Leadership and Management (Health and Social Care) Pathway","0"},
			{"26","http://www.chi.ac.uk//leadership-and-management-ma","Leadership and Management MA","0"},
			{"27","http://www.chi.ac.uk//subject-knowledge-enhancement-mathematics-ske","Subject Knowledge Enhancement in Mathematics (SKE)","0"},
			{"28","http://www.chi.ac.uk//national-award-senco","National Award for SENCO","0"},
			{"29","http://www.chi.ac.uk//pgce-primary-modern-languages","PGCE Primary: Modern Languages","0"},
			{"30","http://www.chi.ac.uk//advanced-professional-practice","Advanced Professional Practice","0"},
			{"31","http://www.chi.ac.uk//professional-media-composition-ma","Professional Media Composition MA","0"},
			{"32","http://www.chi.ac.uk//ma-orchestration-film-games-and-television","MA Orchestration for Film, Games and Television","0"},
			{"33","http://www.chi.ac.uk//postgraduate-certificate-strategic-leadership","Postgraduate Certificate in Strategic Leadership","0"},
			{"34","http://www.chi.ac.uk//pgce-secondary-dance","PGCE Secondary Dance","0"},
			{"35","http://www.chi.ac.uk//teaching-english-young-learners","Teaching English to Young Learners","0"},
			{"36","http://www.chi.ac.uk//teaching-english-secondary-schools","Teaching English in Secondary Schools","0"},
			{"37","http://www.chi.ac.uk//language-development-english-teachers","Language Development for English Teachers","0"},
			{"38","http://www.chi.ac.uk//pgce-secondary-english","PGCE Secondary English","0"},
			{"39","http://www.chi.ac.uk//pgce-secondary-history","PGCE Secondary History","0"},
			{"40","http://www.chi.ac.uk//pgce-secondary-mathematics","PGCE Secondary Mathematics","0"},
			{"41","http://www.chi.ac.uk//pgce-secondary-modern-foreign-languages","PGCE Secondary Modern Foreign Languages","0"},
			{"42","http://www.chi.ac.uk//pgce-secondary-physical-education","PGCE Secondary Physical Education","0"},
			{"43","http://www.chi.ac.uk//pgce-secondary-religious-studies","PGCE Secondary Religious Studies","0"},
			{"44","http://www.chi.ac.uk//pgce-secondary-science","PGCE Secondary Science","0"},
			{"45","http://www.chi.ac.uk//ma-choreography-independent-research-0","MA Choreography by Independent Research","0"},
			{"46","http://www.chi.ac.uk//ma-somatic-practices-independent-research","MA Somatic Practices by Independent Research","0"},
			{"47","http://www.chi.ac.uk//ma-schools-chaplaincy","MA Schools Chaplaincy","0"},
			{"48","http://www.chi.ac.uk//ma-christian-ministry","MA Christian Ministry","0"},
			{"49","http://www.chi.ac.uk//ma-public-theology","MA Public Theology","0"},
			{"50","http://www.chi.ac.uk//advanced-performance","Advanced Performance","0"},
			{"51","http://www.chi.ac.uk//msci-sport-and-exercise-science","MSci Sport and Exercise Science","0"},
			{"52","http://www.chi.ac.uk//postgraduate-certificate-professional-practice-workplace-learning-development-pgcipp-wpld","Postgraduate Certificate in Professional Practice: Workplace Learning Development (PGCiPP: WPLD)","0"}
		};
}
