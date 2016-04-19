package no7.dmu;

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

		String url1="http://www.dmu.ac.uk/study/courses/undergraduate-courses/undergraduate-courses.aspx?courselisting1_AtoZLetter=All&courselisting1_List_GoToPage=";
		
		String url2="http://www.dmu.ac.uk/Study/Courses/Postgraduate-courses/Postgraduate-courses.aspx?courselisting1_AtoZLetter=All&courselisting1_List_GoToPage=";
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		int count=1;
		for(int i=1;i<=7;i++)
		{
			int index=i;
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url2+index);
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			
	       ArrayList<String> list=new ArrayList<String>();
		    Parser	parser=Parser.createParser(htmls, "utf-8");
		    TagNameFilter ProfessionNameFilter=new TagNameFilter("tr");
	   	    
	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	   	    //System.out.println(nodes4.size());
	   	    for(int j=0;j<nodes4.size();j++)
	   	    {
	   	    	
	   	    	htmls=nodes4.elementAt(j).toHtml();
	   	    	
	   	    	//url
	   	    	String title="",url="";
	   	    	parser=Parser.createParser(htmls, "utf-8");
		        AndFilter uFilter=new AndFilter(new TagNameFilter("td"),
		        		new HasAttributeFilter("class","sys_col-one"));
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
		        		new HasAttributeFilter("class","sys_col-two"));
		        NodeList nodes22 = parser.extractAllNodesThatMatch(tFilter);
		        if(nodes22.size()>0)
		        {
		        	type=html2Str(nodes22.elementAt(0).toHtml()).trim();
		        	
		    		
		        }
		        if(!url.equals("")){
		        	System.out.println("{\""+count+"\",\"http://www.dmu.ac.uk"
	   	    				+url+"\",\""+title+"\",\""+type+"\",\"0\"},");
		        count++;
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
		
		public static String[][] UnData={
			{"1","http://www.dmu.ac.uk/study/courses/undergraduate-courses/accounting-and-business-management-ba-degree/accounting-and-business-management-ba-hons.aspx","Accounting and Business Management BA (Hons)","BA (Hons)","0"},
			{"2","http://www.dmu.ac.uk/study/courses/undergraduate-courses/accounting-and-economics-ba-degree/accounting-and-economics-ba-hons.aspx","Accounting and Economics BA (Hons)","BA (Hons)","0"},
			{"3","http://www.dmu.ac.uk/study/courses/undergraduate-courses/accounting-and-finance-maccfin-degree/accounting-and-finance-maccfin.aspx","Accounting and Finance (Integrated Masters)","MAccFin","0"},
			{"4","http://www.dmu.ac.uk/study/courses/undergraduate-courses/accounting-and-finance-ba-degree/accounting-and-finance-ba-hons.aspx","Accounting and Finance BA (Hons)","BA (Hons)","0"},
			{"5","http://www.dmu.ac.uk/study/courses/undergraduate-courses/advertising-and-marketing-communications-ba-degree/advertising-and-marketing-communications-ba-hons.aspx","Advertising and Marketing Communications BA (Hons)","BA (Hons)","0"},
			{"6","http://www.dmu.ac.uk/study/courses/undergraduate-courses/animation-ba-degree/animation-ba-degree.aspx","Animation BA (Hons)","BA (Hons)","0"},
			{"7","http://www.dmu.ac.uk/study/courses/undergraduate-courses/architectural-technology-bsc-degree/architectural-technology-bsc-degree.aspx","Architectural Technology BSc (Hons)","BSc (Hons)","0"},
			{"8","http://www.dmu.ac.uk/study/courses/undergraduate-courses/architecture-ba-degree/architecture-ba-degree.aspx","Architecture BA (Hons)","BA (Hons)","0"},
			{"9","http://www.dmu.ac.uk/study/courses/undergraduate-courses/art-and-design-foundation-studies-btec-diploma/art-and-design-foundation-studies-btec-diploma.aspx","Art and Design (Foundation Studies) BTEC Diploma","BTEC Diploma","0"},
			{"10","http://www.dmu.ac.uk/study/courses/undergraduate-courses/arts-festivals-management-joint-honours-ba-degree/arts-and-festivals-management-joint-honours-ba-degree.aspx","Arts and Festivals Management (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"11","http://www.dmu.ac.uk/study/courses/undergraduate-courses/arts-and-festivals-management-ba-degree/arts-and-festivals-management-ba-degree.aspx","Arts and Festivals Management BA (Hons)","BA (Hons)","0"},
			{"12","http://www.dmu.ac.uk/study/courses/undergraduate-courses/audio-recording-technology-ba-degree/audio-recording-technology-ba-degree.aspx","Audio and Recording Technology BSc (Hons)","BSc (Hons)","0"},
			{"13","http://www.dmu.ac.uk/study/courses/undergraduate-courses/biomedical-science-bsc-hons-degree/biomedical-science-bsc-hons.aspx","Biomedical Science  BSc (Hons)","BSc (Hons)","0"},
			{"14","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-administration-and-management-top-up/business-administration-and-management-ba-top-up.aspx","Business Administration and Management (Year 3 Top-up Only) BA (Hons)","BA (Hons)","0"},
			{"15","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-administration-and-management-ba-degree/business-administration-and-management-ba-hons.aspx","Business Administration and Management BA (Hons)","BA (Hons)","0"},
			{"16","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-and-globalisation-ba-degree/business-and-globalisation-ba-hons.aspx","Business and Globalisation BA (Hons)","BA (Hons)","0"},
			{"17","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-and-management-ba-degree/business-and-management-ba-hons.aspx","Business and Management BA (Hons)","BA (Hons)","0"},
			{"18","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-and-management-mbus-degree/business-and-management-mbus.aspx","Business and Management MBus","MBus","0"},
			{"19","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-and-marketing-ba-degree/business-and-marketing-ba-hons.aspx","Business and Marketing BA (Hons)","BA (Hons)","0"},
			{"20","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-entrepreneurship-and-innovation-ba-degree/business-entrepreneurship-and-innovation-ba-hons.aspx","Business Entrepreneurship and Innovation BA (Hons)","BA (Hons)","0"},
			{"21","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-information-systems-bsc-degree/business-information-systems-bsc-degree.aspx","Business Information Systems BSc (Hons)","BSc (Hons)","0"},
			{"22","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-law-llb-degree/business-law-llb-hons.aspx","Business Law LLB (Hons)","LLB (Hons)","0"},
			{"23","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-management-and-economics-ba-degree/business-management-and-economics-ba-hons.aspx","Business Management and Economics BA (Hons)","BA (Hons)","0"},
			{"24","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-management-and-finance-ba-degree/business-management-and-finance-ba-hons.aspx","Business Management and Finance BA (Hons)","BA (Hons)","0"},
			{"25","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-management-and-hrm-ba-degree/business-management-and-hrm-ba-hons.aspx","Business Management and Human Resource Management BA (Hons)","BA (Hons)","0"},
			{"26","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-management-and-law-ba-degree/business-management-and-law-ba-hons.aspx","Business Management and Law BA  (Hons)","BA (Hons)","0"},
			{"27","http://www.dmu.ac.uk/study/courses/undergraduate-courses/business-studies-bsc-degree/business-studies-bsc-hons.aspx","Business Studies (Economics/Strategy/Finance) BSc (Hons)","BSc (Hons)","0"},
			{"28","http://www.dmu.ac.uk/study/courses/undergraduate-courses/children-families-and-community-health-fda-degree/children-families-and-community-health.aspx","Children Families and Community Health (incorporating the UCPD in Children, Families and Community Health)","Foundation Degree (FdA)","0"},
			{"29","http://www.dmu.ac.uk/study/courses/undergraduate-courses/clinical-midwifery-bsc-hons-degree/clinical-midwifery-bsc-hons.aspx","Clinical Midwifery BSc Hons","BSc (Hons)","0"},
			{"30","http://www.dmu.ac.uk/study/courses/undergraduate-courses/communication-arts-ba-degree/communication-arts-ba-degree.aspx","Communication Arts BA (Hons)","BSc (Hons)","0"},
			{"31","http://www.dmu.ac.uk/study/courses/undergraduate-courses/computer-games-programming-bsc-degree/computer-games-programming-bsc-degree.aspx","Computer Games Programming BSc (Hons)","BSc (Hons)","0"},
			{"32","http://www.dmu.ac.uk/study/courses/undergraduate-courses/computer-science-bsc-degree/computer-science-bsc-degree.aspx","Computer Science BSc (Hons)","BSc (Hons)","0"},
			{"33","http://www.dmu.ac.uk/study/courses/undergraduate-courses/computer-security-bsc-degree/computer-security-bsc-hons-degree.aspx","Computer Security BSc (Hons)","BSc (Hons)","0"},
			{"34","http://www.dmu.ac.uk/study/courses/undergraduate-courses/computing-bsc/computing-bsc-degree.aspx","Computing BSc (Hons)","BSc (Hons)","0"},
			{"35","http://www.dmu.ac.uk/study/courses/undergraduate-courses/computing-for-business-bsc-degree/computing-for-business-bsc-degree.aspx","Computing for Business BSc (Hons)","BSc (Hons)","0"},
			{"36","http://www.dmu.ac.uk/study/courses/undergraduate-courses/computing-hnd/computing-hnd.aspx","Computing HND","HND","0"},
			{"37","http://www.dmu.ac.uk/study/courses/undergraduate-courses/contour-fashion-ba-degree/contour-fashion-ba-degree.aspx","Contour Fashion BA (Hons)","BA (Hons)","0"},
			{"38","http://www.dmu.ac.uk/study/courses/undergraduate-courses/creative-sound-technology-foundation-degree/creative-sound-technology-fdsc-foundation-degree.aspx","Creative Sound Technology FdSc","BSc (Hons)","0"},
			{"39","http://www.dmu.ac.uk/study/courses/undergraduate-courses/creative-writing-joint-honours-ba-degree/creative-writing-joint-honours-ba-degree.aspx","Creative Writing (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"40","http://www.dmu.ac.uk/study/courses/undergraduate-courses/criminal-investigation-policing-ba-hons-degree/criminal-investigation-with-policing-studies-ba-hons.aspx","Criminal Investigation with Policing Studies BA (Hons)","BA (Hons)","0"},
			{"41","http://www.dmu.ac.uk/study/courses/undergraduate-courses/criminology-ba-hons-degree/criminology-ba-hons.aspx","Criminology BA (Hons)","BA (Hons)","0"},
			{"42","http://www.dmu.ac.uk/study/courses/undergraduate-courses/criminology-criminal-justice-psychology-ba-hons-degree/criminology-with-psychology-ba-hons.aspx","Criminology with Psychology BA (Hons)","BA (Hons)","0"},
			{"43","http://www.dmu.ac.uk/study/courses/undergraduate-courses/criminology-criminal-justice-policing-topup-degree/criminology-policing-top-up-programme-ba-hons.aspx","Criminology: Policing Top Up Programme BA (Hons)","BA (Hons)","0"},
			{"44","http://www.dmu.ac.uk/study/courses/undergraduate-courses/dance-joint-honours-ba-degree/dance-joint-honours-ba-degree.aspx","Dance (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"45","http://www.dmu.ac.uk/study/courses/undergraduate-courses/dance-ba-degree/dance-ba-degree.aspx","Dance BA (Hons)","BA (Hons)","0"},
			{"46","http://www.dmu.ac.uk/study/courses/undergraduate-courses/design-crafts-ba-degree/design-crafts-ba-degree.aspx","Design Crafts BA (Hons)","BA (Hons)","0"},
			{"47","http://www.dmu.ac.uk/study/courses/undergraduate-courses/design-products-mdes-degree/design-products-mdes-degree.aspx","Design Products MDes (Hons)","MDes (Hons)","0"},
			{"48","http://www.dmu.ac.uk/study/courses/undergraduate-courses/digital-design-ba-degree/digital-design-ba-degree.aspx","Digital Design BA (Hons)","BA (Hons)","0"},
			{"49","http://www.dmu.ac.uk/study/courses/undergraduate-courses/drama-studies-joint-honours-ba-degree/drama-studies-joint-honours-ba-degree.aspx","Drama Studies (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"50","http://www.dmu.ac.uk/study/courses/undergraduate-courses/drama-studies-ba-degree/drama-studies-ba-degree.aspx","Drama Studies BA (Hons)","BA (Hons)","0"},
			{"51","http://www.dmu.ac.uk/study/courses/undergraduate-courses/economics-and-finance-bsc-degree/economics-and-finance-bsc-hons.aspx","Economics and Finance BSc (Hons)","BSc (Hons)","0"},
			{"52","http://www.dmu.ac.uk/study/courses/undergraduate-courses/economics-and-international-relations-ba-degree/economics-and-international-relations-ba-hons.aspx","Economics and International Relations BA (Hons)","BA (Hons)","0"},
			{"53","http://www.dmu.ac.uk/study/courses/undergraduate-courses/economics-and-politics-ba-degree/economics-and-politics-ba-hons.aspx","Economics and Politics BA (Hons)","BA (Hons)","0"},
			{"54","http://www.dmu.ac.uk/study/courses/undergraduate-courses/economics-ba-degree/economics-ba-hons.aspx","Economics BA (Hons)","BA (Hons)","0"},
			{"55","http://www.dmu.ac.uk/study/courses/undergraduate-courses/education-studies-ba-hons-degree/education-studies-ba-hons.aspx","Education Studies BA Hons","BA (Hons)","0"},
			{"56","http://www.dmu.ac.uk/study/courses/undergraduate-courses/education-studies-with-french-ba-hons-degree/education-studies-with-french-ba-hons.aspx","Education Studies with French BA (Hons)","BA (Hons)","0"},
			{"57","http://www.dmu.ac.uk/study/courses/undergraduate-courses/education-studies-with-mandarin-ba-hons-degree/education-studies-with-mandarin-ba-hons.aspx","Education Studies with Mandarin BA (Hons)","BA (Hons)","0"},
			{"58","http://www.dmu.ac.uk/study/courses/undergraduate-courses/education-studies-with-psychology-ba-hons-degree/education-studies-with-psychology-ba-hons.aspx","Education Studies with Psychology BA (Hons)","BSc (Hons)","0"},
			{"59","http://www.dmu.ac.uk/study/courses/undergraduate-courses/electrical-and-electronic-engineering/electrical-and-electronic-engineering-beng-meng-degree.aspx","Electronic Engineering (Electrical and Electronic Engineering) BEng (Hons)","BEng (Hons)","0"},
			{"60","http://www.dmu.ac.uk/study/courses/undergraduate-courses/electronic-engineering-meng-degree/electronic-engineering-meng-degree.aspx","Electronic Engineering (Integrated master's) MEng (Hons)","MEng","0"},
			{"61","http://www.dmu.ac.uk/study/courses/undergraduate-courses/electronic-engineering/electronic-engineering-beng-degree.aspx","Electronic Engineering BEng (Hons)","BEng (Hons)","0"},
			{"62","http://www.dmu.ac.uk/study/courses/undergraduate-courses/engineering-year-zero/engineering-year-zero-foundation.aspx","Engineering Year Zero","Foundation","0"},
			{"63","http://www.dmu.ac.uk/study/courses/undergraduate-courses/english-joint-honours-ba-degree/english-joint-honours-ba-degree.aspx","English (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"64","http://www.dmu.ac.uk/study/courses/undergraduate-courses/english-ba-degree/english-ba-degree.aspx","English BA (Hons)","BA (Hons)","0"},
			{"65","http://www.dmu.ac.uk/study/courses/undergraduate-courses/english-language-joint-honours-ba-degree/english-language-joint-honours-ba-degree.aspx","English Language (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"66","http://www.dmu.ac.uk/study/courses/undergraduate-courses/english-language-with-languages-ba-degree/english-language-with-languages-ba-degree.aspx","English Language with Languages BA (Hons)","BA (Hons)","0"},
			{"67","http://www.dmu.ac.uk/study/courses/undergraduate-courses/english-language-with-tesol-ba-degree/english-language-with-tesol-ba-degree.aspx","English Language with TESOL BA (Hons)","BA (Hons)","0"},
			{"68","http://www.dmu.ac.uk/study/courses/undergraduate-courses/english-with-languages-ba-degree/english-with-languages-ba-degree.aspx","English with Languages BA (Hons)","BA (Hons)","0"},
			{"69","http://www.dmu.ac.uk/study/courses/undergraduate-courses/fashion-buying-with-design-ba-degree/fashion-buying-with-design-ba-degree.aspx","Fashion Buying with Design BA (Hons)","BA (Hons)","0"},
			{"70","http://www.dmu.ac.uk/study/courses/undergraduate-courses/fashion-buying-with-marketing-ba-degree/fashion-buying-with-marketing-ba-degree.aspx","Fashion Buying with Marketing BA (Hons)","BA (Hons)","0"},
			{"71","http://www.dmu.ac.uk/study/courses/undergraduate-courses/fashion-design-ba-degree/fashion-design-ba-degree.aspx","Fashion Design BA (Hons)","BA (Hons)","0"},
			{"72","http://www.dmu.ac.uk/study/courses/undergraduate-courses/fashion-textiles-and-accessories-degree/fashion-textiles-and-accessories-ba-degree.aspx","Fashion Textiles and Accessories BA (Hons)","BA (Hons)","0"},
			{"73","http://www.dmu.ac.uk/study/courses/undergraduate-courses/film-studies-ba-degree/film-studies-ba-degree.aspx","Film Studies BA (Hons)","BA (Hons)","0"},
			{"74","http://www.dmu.ac.uk/study/courses/undergraduate-courses/film-studies-ba-degree/film-studies-joint-ba-degree.aspx","Film Studies BA (Joint Honours)","BA (Hons)","0"},
			{"75","http://www.dmu.ac.uk/study/courses/undergraduate-courses/film-studies-with-languages/film-studies-with-languages.aspx","Film Studies with Languages","BA (Hons)","0"},
			{"76","http://www.dmu.ac.uk/study/courses/undergraduate-courses/fine-art-ba-degree/fine-art-ba-degree.aspx","Fine Art BA (Hons)","BA (Hons)","0"},
			{"77","http://www.dmu.ac.uk/study/courses/undergraduate-courses/footwear-design-ba-degree/footwear-design-ba-degree.aspx","Footwear Design BA (Hons)","BA (Hons)","0"},
			{"78","http://www.dmu.ac.uk/study/courses/undergraduate-courses/footwear-fda-foundation-degree/footwear-fda-foundation-degree.aspx","Footwear Foundation Degree (FdA)","Foundation Degree (FdA)","0"},
			{"79","http://www.dmu.ac.uk/study/courses/undergraduate-courses/forensic-computing-bsc-degree/forensic-computing-bsc-degree.aspx","Forensic Computing BSc (Hons)","BSc (Hons)","0"},
			{"80","http://www.dmu.ac.uk/study/courses/undergraduate-courses/forensic-road-collision-investigation-foundation-degree-(fdsc).aspx","Forensic Road Collision Investigation","Foundation Degree (FdSc)","0"},
			{"81","http://www.dmu.ac.uk/study/courses/undergraduate-courses/forensic-science-bsc-hons-degree/forensic-science-bsc-hons.aspx","Forensic Science BSc (Hons)","BSc (Hons)","0"},
			{"82","http://www.dmu.ac.uk/study/courses/undergraduate-courses/computing-foundation/foundation-year-in-computing.aspx","Foundation Year in Computing","Foundation","0"},
			{"83","http://www.dmu.ac.uk/study/courses/undergraduate-courses/furniture-design-ba-degree/furniture-design-ba-degree.aspx","Furniture Design BA (Hons)","BA (Hons)","0"},
			{"84","http://www.dmu.ac.uk/study/courses/undergraduate-courses/game-art-design-ba-degree/game-art-ba-degree.aspx","Game Art BA (Hons)","BA (Hons)","0"},
			{"85","http://www.dmu.ac.uk/study/courses/undergraduate-courses/global-finance-bsc-degree/global-finance-bsc-hons.aspx","Global Finance BSc (Hons)","BSc (Hons)","0"},
			{"86","http://www.dmu.ac.uk/study/courses/undergraduate-courses/global-leadership-and-management-bsc-degree/global-leadership-and-management-bsc-hons.aspx","Global Leadership and Management BSc (Hons)","BSc (Hons)","0"},
			{"87","http://www.dmu.ac.uk/study/courses/undergraduate-courses/graphic-design-illustration-ba-degree/graphic-design-illustration-ba-degree.aspx","Graphic Design (Illustration) BA (Hons)","BA (Hons)","0"},
			{"88","http://www.dmu.ac.uk/study/courses/undergraduate-courses/graphic-design-interactive-ba-degree/graphic-design-interactive-ba-degree.aspx","Graphic Design (Interactive) BA (Hons)","BA (Hons)","0"},
			{"89","http://www.dmu.ac.uk/study/courses/undergraduate-courses/graphic-design-and-e-media-foundation-degree/graphic-design-and-e-media-foundation-degree.aspx","Graphic Design and e-Media Foundation Degree (FdA)","BA (Hons)","0"},
			{"90","http://www.dmu.ac.uk/study/courses/undergraduate-courses/graphic-design-degree/graphic-design-ba-degree.aspx","Graphic Design BA (Hons)","BA (Hons)","0"},
			{"91","http://www.dmu.ac.uk/study/courses/undergraduate-courses/health-professional-practice-bsc-hons-bsc/health-professional-practice-bsc-hons-bsc.aspx","Health Professional Practice BSc (Hons) / BSc","BSc (Hons) / BSc","0"},
			{"92","http://www.dmu.ac.uk/study/courses/undergraduate-courses/health-studies-direct-entry-level-6-professional/health-studies-direct-entry-level-6-professional.aspx","Health Studies (Direct entry Level 6) Professional","Professional","0"},
			{"93","http://www.dmu.ac.uk/study/courses/undergraduate-courses/health-studies-ba-hons-degree/health-studies-ba-hons.aspx","Health Studies BA (Hons)","BA (Hons)","0"},
			{"94","http://www.dmu.ac.uk/study/courses/undergraduate-courses/healthcare-science-audiology-bsc-hons-degree/healthcare-science-audiology-bsc-hons.aspx","Healthcare Science Audiology BSc (Hons)","BSc (Hons)","0"},
			{"95","http://www.dmu.ac.uk/study/courses/undergraduate-courses/history-joint-honours-ba-degree/history-joint-honours-ba-degree.aspx","History (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"96","http://www.dmu.ac.uk/study/courses/undergraduate-courses/history-ba-degree/history-ba-degree.aspx","History BA (Hons)","BA (Hons)","0"},
			{"97","http://www.dmu.ac.uk/study/courses/undergraduate-courses/history-with-languages-ba-degree/history-with-languages-ba-degree.aspx","History with Languages BA (Hons)","BA (Hons)","0"},
			{"98","http://www.dmu.ac.uk/study/courses/undergraduate-courses/human-communication-non-commissioned-bsc-hons-degree/human-communication-speech-and-language-therapy-non-commissioned-bsc-hons.aspx","Human Communication - Speech and Language Therapy Non-Commissioned BSc (Hons)","BSc (Hons)","0"},
			{"99","http://www.dmu.ac.uk/study/courses/undergraduate-courses/human-communication-nhs-commissioned-bsc-hons-degree/human-communication-speech-and-language-therapy-nhs-commissioned-bsc-hons.aspx","Human Communication Speech and Language Therapy NHS Commissioned BSc (Hons)","BSc (Hons)","0"},
			{"100","http://www.dmu.ac.uk/study/courses/undergraduate-courses/human-resource-management-ba-degree/human-resource-management-ba-hons.aspx","Human Resource Management BA (Hons)","BA (Hons)","0"},
			{"101","http://www.dmu.ac.uk/study/courses/undergraduate-courses/information-and-communication-technology-bsc-degre/information-and-communication-technology-bsc-degree.aspx","Information and Communication Technology BSc (Hons)","BSc (Hons)","0"},
			{"102","http://www.dmu.ac.uk/study/courses/undergraduate-courses/intelligent-systems-bsc-degree/intelligent-systems-bsc-degree.aspx","Intelligent Systems BSc (Hons)","BSc (Hons)","0"},
			{"103","http://www.dmu.ac.uk/study/courses/undergraduate-courses/intelligent-systems-mcomp-degree/intelligent-systems-mcomp-degree.aspx","Intelligent Systems MComp","BSc/MComp (Hons)","0"},
			{"104","http://www.dmu.ac.uk/study/courses/undergraduate-courses/interior-design-ba-degree/interior-design-ba-degree.aspx","Interior Design BA (Hons)","BA (Hons)","0"},
			{"105","http://www.dmu.ac.uk/study/courses/undergraduate-courses/interior-design-mdes-degree/interior-design-mdes-degree.aspx","Interior Design MDes (Hons)","MDes (Hons)","0"},
			{"106","http://www.dmu.ac.uk/study/courses/undergraduate-courses/international-business-ba-degree/international-business-ba-hons.aspx","International Business BA (Hons)","BA (Hons)","0"},
			{"107","http://www.dmu.ac.uk/study/courses/undergraduate-courses/international-marketing-and-business-ba-degree/international-marketing-and-business-ba-hons.aspx","International Marketing and Business BA (Hons)","BA (Hons)","0"},
			{"108","http://www.dmu.ac.uk/study/courses/undergraduate-courses/international-relations-joint-ba-degree/international-relations-joint-honours-ba-hons.aspx","International Relations (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"109","http://www.dmu.ac.uk/study/courses/undergraduate-courses/international-relations-ba-degree/international-relations-ba-hons.aspx","International Relations BA (Hons)","BA (Hons)","0"},
			{"110","http://www.dmu.ac.uk/study/courses/undergraduate-courses/journalism-ba-degree/journalism-joint-ba-degree.aspx","Journalism (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"111","http://www.dmu.ac.uk/study/courses/undergraduate-courses/journalism-ba-degree/journalism-ba-degree.aspx","Journalism (NCTJ accredited) BA (Hons)","BA (Hons)","0"},
			{"112","http://www.dmu.ac.uk/study/courses/undergraduate-courses/law-and-criminal-justice-llb-degree/law-and-criminal-justice-llb-hons.aspx","Law and Criminal Justice LLB (Hons)","LLB (Hons)","0"},
			{"113","http://www.dmu.ac.uk/study/courses/undergraduate-courses/law-and-economics-ba-degree/law-and-economics-ba-hons.aspx","Law and Economics BA (Hons)","BA (Hons)","0"},
			{"114","http://www.dmu.ac.uk/study/courses/undergraduate-courses/law-llb-degree/law-llb-hons.aspx","Law LLB (Hons)","LLB (Hons)","0"},
			{"115","http://www.dmu.ac.uk/study/courses/undergraduate-courses/law-human-rights-and-social-justice-llb-degree/law-human-rights-and-social-justice-llb-hons.aspx","Law, Human Rights and Social Justice LLB (Hons)","LLB (Hons)","0"},
			{"116","http://www.dmu.ac.uk/study/courses/undergraduate-courses/learning-beyond-registration-professional/learning-beyond-registration-professional.aspx","Learning Beyond Registration Professional","BSc (Hons)","0"},
			{"117","http://www.dmu.ac.uk/study/courses/undergraduate-courses/marketing-ba-degree/marketing-ba-hons.aspx","Marketing BA (Hons)","BA (Hons)","0"},
			{"118","http://www.dmu.ac.uk/study/courses/undergraduate-courses/mathematics-bsc-degree/mathematics-bsc-degree.aspx","Mathematics BSc (Hons)","BSc (Hons)","0"},
			{"119","http://www.dmu.ac.uk/study/courses/undergraduate-courses/mechanical-engineering-beng-degree/mechanical-engineering-beng-degree.aspx","Mechanical Engineering BEng (Hons)","BEng (Hons)","0"},
			{"120","http://www.dmu.ac.uk/study/courses/undergraduate-courses/mechanical-engineering-meng-degree/mechanical-engineering-meng-degree.aspx","Mechanical Engineering MEng (Hons)","MEng","0"},
			{"121","http://www.dmu.ac.uk/study/courses/undergraduate-courses/mechatronics-meng-degree/mechatronics-meng-degree.aspx","Mechatronics (Integrated master's) MEng","MEng","0"},
			{"122","http://www.dmu.ac.uk/study/courses/undergraduate-courses/mechatronics-beng-degree/mechatronics-beng-degree.aspx","Mechatronics BEng (Hons)","BEng (Hons)","0"},
			{"123","http://www.dmu.ac.uk/study/courses/undergraduate-courses/media-and-communication-ba-degree/media-joint-ba-degree.aspx","Media (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"124","http://www.dmu.ac.uk/study/courses/undergraduate-courses/media-and-communication-ba-degree/media-and-communication-ba-degree.aspx","Media and Communication BA (Hons)","BA (Hons)","0"},
			{"125","http://www.dmu.ac.uk/study/courses/undergraduate-courses/media-and-communication-with-languages-ba-degree/media-and-communication-with-languages-ba-degree.aspx","Media and Communication with Languages BA (Hons)","BA (Hons)","0"},
			{"126","http://www.dmu.ac.uk/study/courses/undergraduate-courses/media-production-bsc-degree/media-production-bsc-degree.aspx","Media Production BSc (Hons)","BSc (Hons)","0"},
			{"127","http://www.dmu.ac.uk/study/courses/undergraduate-courses/medical-science-b-med-sci-hons-degree/medical-science-b-med-sci-hons.aspx","Medical Science B Med Sci (Hons)","B Med Sci (Hons)","0"},
			{"128","http://www.dmu.ac.uk/study/courses/undergraduate-courses/midwifery-pre-registration-bsc-hons-degree/midwifery-pre-registration-midwifery-bsc-hons.aspx","Midwifery Pre-Registration Midwifery BSc (Hons)","BSc (Hons)","0"},
			{"129","http://www.dmu.ac.uk/study/courses/undergraduate-courses/music-technology-and-innovation-ba-hons/music-technology-and-innovation-ba-degree.aspx","Music Technology and Innovation BA (Hons)","BA (Hons)","0"},
			{"130","http://www.dmu.ac.uk/study/courses/undergraduate-courses/music-technology-bsc-degree/music-technology-bsc-degree.aspx","Music Technology BSc (Hons)","BSc (Hons)","0"},
			{"131","http://www.dmu.ac.uk/study/courses/undergraduate-courses/music-technology-and-performance-ba-hons/music-technology-and-performance-ba-degree.aspx","Music, Technology and Performance BA (Hons)","BA (Hons)","0"},
			{"132","http://www.dmu.ac.uk/study/courses/undergraduate-courses/non-medical-prescribing-bsc-degree/non-medical-prescribing-bsc.aspx","Non-Medical Prescribing BSc","BSc","0"},
			{"133","http://www.dmu.ac.uk/study/courses/undergraduate-courses/nursing-with-registration-adult-nursing-degree/nursing-with-registration-adult-nursing-bsc-hons.aspx","Nursing with Registration (Adult Nursing) BSc (Hons)","BSc (Hons)","0"},
			{"134","http://www.dmu.ac.uk/study/courses/undergraduate-courses/nursing-with-registration-child-nursing-degree/nursing-with-registration-child-nursing-bsc-hons.aspx","Nursing with Registration (Child Nursing) BSc (Hons)","BSc (Hons)","0"},
			{"135","http://www.dmu.ac.uk/study/courses/undergraduate-courses/nursing-with-registration-learning-disability-degree/nursing-with-registration-learning-disability-bsc-hons.aspx","Nursing with Registration (Learning Disability) BSc (Hons)","BSc (Hons)","0"},
			{"136","http://www.dmu.ac.uk/study/courses/undergraduate-courses/nursing-with-registration-mental-health-nursing-degree/nursing-with-registration-mental-health-nursing-bsc-hons.aspx","Nursing with Registration (Mental Health Nursing) BSc (Hons)","BSc (Hons)","0"},
			{"137","http://www.dmu.ac.uk/study/courses/undergraduate-courses/performing-arts-ba-degree/performing-arts-ba-degree.aspx","Performing Arts BA (Hons)","BA (Hons)","0"},
			{"138","http://www.dmu.ac.uk/study/courses/undergraduate-courses/pharmaceutical-cosmetic-science-bsc-hons-degree/pharmaceutical-and-cosmetic-science-bsc-hons.aspx","Pharmaceutical and Cosmetic Science BSc (Hons)","BSc (Hons)","0"},
			{"139","http://www.dmu.ac.uk/study/courses/undergraduate-courses/pharmacy-mpharm-hons-degree/pharmacy-mpharm-hons.aspx","Pharmacy MPharm (Hons)","MPharm (Hons)","0"},
			{"140","http://www.dmu.ac.uk/study/courses/undergraduate-courses/photography-and-video-ba-degree/photography-and-video-ba-degree.aspx","Photography and Video BA (Hons)","BA (Hons)","0"},
			{"141","http://www.dmu.ac.uk/study/courses/undergraduate-courses/photography-and-video-fda-foundation-degree/photography-and-video-fda-foundation-degree.aspx","Photography and Video Foundation Degree (FdA)","Foundation Degree (FdA)","0"},
			{"142","http://www.dmu.ac.uk/study/courses/undergraduate-courses/physics-bsc-degree/physics-bsc-mphys-degree.aspx","Physics BSc/MPhys (Hons)","BSc/MPhys (Hons)","0"},
			{"143","http://www.dmu.ac.uk/study/courses/undergraduate-courses/policing-ba-hons-degree/policing-ba-hons.aspx","Policing BA (Hons)","BSc (Hons)","0"},
			{"144","http://www.dmu.ac.uk/study/courses/undergraduate-courses/politics-joint-ba-degree/politics-joint-honours-ba-hons.aspx","Politics (Joint Honours) BA (Hons)","BA (Hons)","0"},
			{"145","http://www.dmu.ac.uk/study/courses/undergraduate-courses/politics-ba-degree/politics-ba-hons.aspx","Politics BA (Hons)","BA (Hons)","0"},
			{"146","http://www.dmu.ac.uk/study/courses/undergraduate-courses/practice-education-programme-bsc-degree/practice-education-programme-bsc-pg-cert.aspx","Practice Education Programme BSc PG Cert","BSc (Hons)","0"},
			{"147","http://www.dmu.ac.uk/study/courses/undergraduate-courses/practice-nursing-bsc-degree/practice-nursing-bsc.aspx","Practice Nursing BSc","BSc","0"},
			{"148","http://www.dmu.ac.uk/study/courses/undergraduate-courses/preparation-for-social-work-professional/preparation-for-social-work-professional.aspx","Preparation for Social Work Professional","Professional","0"},
			{"149","http://www.dmu.ac.uk/study/courses/undergraduate-courses/product-and-furniture-design-ba-degree/product-and-furniture-design-ba-degree.aspx","Product and Furniture Design BA (Hons)","BA (Hons)","0"},
			{"150","http://www.dmu.ac.uk/study/courses/undergraduate-courses/product-design-ba-degree/product-design-ba-degree.aspx","Product Design BA (Hons)","BA (Hons)","0"},
			{"151","http://www.dmu.ac.uk/study/courses/undergraduate-courses/product-design-bsc-degree/product-design-bsc-degree.aspx","Product Design BSc (Hons)","BSc (Hons)","0"},
			{"152","http://www.dmu.ac.uk/study/courses/undergraduate-courses/professional-studies-in-forensic-road-collision-investigation-.aspx","Professional Studies in Forensic Road Collision Investigation","BSc (Hons)","0"},
			{"153","http://www.dmu.ac.uk/study/courses/undergraduate-courses/psychology-bsc-hons-degree/psychology-bsc-hons.aspx","Psychology BSc (Hons)","BSc (Hons)","0"},
			{"154","http://www.dmu.ac.uk/study/courses/undergraduate-courses/psychology-with-criminology-bsc-hons-degree/psychology-with-criminology-bsc-hons.aspx","Psychology with Criminology BSc (Hons)","BSc (Hons)","0"},
			{"155","http://www.dmu.ac.uk/study/courses/undergraduate-courses/psychology-with-education-studies-bsc-hons-degree/psychology-with-education-studies-bsc-hons.aspx","Psychology with Education Studies BSc (Hons)","BSc (Hons)","0"},
			{"156","http://www.dmu.ac.uk/study/courses/undergraduate-courses/psychology-with-health-studies-bsc-hons-degree/psychology-with-health-studies-bsc-hons.aspx","Psychology with Health Studies BSc (Hons)","BSc (Hons)","0"},
			{"157","http://www.dmu.ac.uk/study/courses/undergraduate-courses/public-administration-and-management-ba-degree/public-administration-and-management-ba-hons.aspx","Public Administration and Management BA (Hons)","BA (Hons)","0"},
			{"158","http://www.dmu.ac.uk/study/courses/undergraduate-courses/public-and-community-health-direct-entry-year-3-degree/public-and-community-health-bsc-hons.aspx","Public and Community Health BSc (Hons)","BSc (Hons)","0"},
			{"159","http://www.dmu.ac.uk/study/courses/undergraduate-courses/social-work-ba-hons-degree/social-work-ba-hons.aspx","Social Work BA (Hons)","BA (Hons)","0"},
			{"160","http://www.dmu.ac.uk/study/courses/undergraduate-courses/software-engineering-bsc-degree/software-engineering-bsc-degree.aspx","Software Engineering BSc (Hons)","BSc (Hons)","0"},
			{"161","http://www.dmu.ac.uk/study/courses/undergraduate-courses/specialist-community-public-health-nursing-degree/specialist-community-public-health-nursing-with-nmc-registration.aspx","Specialist Community Public Health Nursing with NMC Registration BSc (Hons)","BSc (Hons)","0"},
			{"162","http://www.dmu.ac.uk/study/courses/undergraduate-courses/textile-design-ba-degree/textile-design-ba-degree.aspx","Textile Design BA (Hons)","BA (Hons)","0"},
			{"163","http://www.dmu.ac.uk/study/courses/undergraduate-courses/transition-to-preregistration-nursing-professional/transition-to-pre-registration-nursing-professional.aspx","Transition to Pre Registration Nursing Professional","Professional","0"},
			{"164","http://www.dmu.ac.uk/study/courses/undergraduate-courses/work-with-communities-and-young-people-fda-degree/working-with-communities-and-young-people-fda-degree.aspx","Working-with-Communities-and-Young-People-FDA-Degree","Foundation Degree (FdA)","0"},
			{"165","http://www.dmu.ac.uk/study/courses/undergraduate-courses/youth-work-community-development-ba-hons-degree/youth-work-community-development-ba-hons.aspx","Youth Work Community Development BA (Hons)","BSc (Hons)","0"},

		};
		public static String[][] PostData={
			{"1","http://www.dmu.ac.uk/study/courses/postgraduate-courses/accounting-and-finance-msc-degree/accounting-and-finance-msc.aspx","Accounting and Finance MSc","MSc","0"},
			{"2","http://www.dmu.ac.uk/study/courses/postgraduate-courses/accounting-and-finance-distance-learning/accounting-and-finance-msc-fast-track-distance-learning-msc.aspx","Accounting and Finance MSc (fast track-distance learning)","MSc","0"},
			{"3","http://www.dmu.ac.uk/study/courses/postgraduate-courses/advanced-biomedical-science-mscpg-dippg-cert/advanced-biomedical-science.aspx","Advanced Biomedical Science","MSc/PG Dip/PG Cert","0"},
			{"4","http://www.dmu.ac.uk/study/courses/postgraduate-courses/advanced-practice-in-urgent-primary-care-pg-dip/advanced-practice-in-urgent-primary-care.aspx","Advanced Practice in Urgent Primary Care","PG Dip","0"},
			{"5","http://www.dmu.ac.uk/study/courses/postgraduate-courses/advertising-public-relations-management-msc-degree/advertising-and-public-relations-management-msc.aspx","Advertising and Public Relations Management MSc","MSc","0"},
			{"6","http://www.dmu.ac.uk/study/courses/postgraduate-courses/architectural-design-ma-degree/architectural-design-ma-degree.aspx","Architectural Design MA","MA","0"},
			{"7","http://www.dmu.ac.uk/study/courses/postgraduate-courses/architectural-practice-postgraduate-diploma/architectural-practice-postgraduate-diploma.aspx","Architectural Practice Postgraduate Diploma","PG Dip","0"},
			{"8","http://www.dmu.ac.uk/study/courses/postgraduate-courses/architecture-and-sustainability-msc-degree/architecture-and-sustainability-msc-degree.aspx","Architecture and Sustainability MSc","MSc","0"},
			{"9","http://www.dmu.ac.uk/study/courses/postgraduate-courses/architecture-march-degree/architecture-march-degree.aspx","Architecture MArch","MArch","0"},
			{"10","http://www.dmu.ac.uk/study/courses/postgraduate-courses/architecture-pedr/architecture-professional-experience-development-record.aspx","Architecture Professional Experience Development Record (PEDR)","Professional","0"},
			{"11","http://www.dmu.ac.uk/study/courses/postgraduate-courses/architecture-research-mphil-phd-degree/architecture-research-mphil-phd-degree.aspx","Architecture research degree MPhil/PhD","MPhil/PhD","0"},
			{"12","http://www.dmu.ac.uk/study/courses/postgraduate-courses/arts-ma-degree/arts-ma-degree.aspx","Arts MA","MA","0"},
			{"13","http://www.dmu.ac.uk/study/courses/postgraduate-courses/arts-research-mphil-phd-degree/arts-research-mphil-phd-degree.aspx","Arts research degree MPhil/PhD","MPhil/PhD","0"},
			{"14","http://www.dmu.ac.uk/study/courses/postgraduate-courses/bespoke-footwear-biomechanics-ma-degree/bespoke-footwear-biomechanics-ma-degree.aspx","Bespoke Footwear Biomechanics MA","MA","0"},
			{"15","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-economics-and-business-finance-msc-degree/business-economics-and-business-finance-msc.aspx","Business Economics and Business Finance MSc","MSc","0"},
			{"16","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-economics-and-international-relations-msc/business-economics-and-international-relations-msc.aspx","Business Economics and International Relations MSc","MSc","0"},
			{"17","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-economics-and-marketing-msc-degree/business-economics-and-marketing-msc.aspx","Business Economics and Marketing MSc","MSc","0"},
			{"18","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-economics-and-risk-management-msc-degree/business-economics-and-risk-management-msc.aspx","Business Economics and Risk Management MSc","MSc","0"},
			{"19","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-intelligence-systems-and-data-mining/business-intelligence-systems-and-data-mining-msc-degree.aspx","Business Intelligence Systems and Data Mining MSc/PG Dip/PG Cert","MA","0"},
			{"20","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-law-international-business-law-llm-dl/business-law-international-business-law-llm-distance-learning.aspx","Business Law / International Business Law LLM (distance learning)","LLM","0"},
			{"21","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-law-law-llm-degree/business-law-law-llm.aspx","Business Law/Law LLM","LLM","0"},
			{"22","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-management-and-sport.aspx","Business Management and Sport","MSc","0"},
			{"23","http://www.dmu.ac.uk/study/courses/postgraduate-courses/business-management-and-the-creative-industries.aspx","Business Management and the Creative Industries","MSc","0"},
			{"24","http://www.dmu.ac.uk/study/courses/postgraduate-courses/clinical-pharmacy-mscpg-dippg-cert/clinical-pharmacy.aspx","Clinical Pharmacy","MSc/PG Dip/PG Cert","0"},
			{"25","http://www.dmu.ac.uk/study/courses/postgraduate-courses/computing/computing-msc-degree.aspx","Computing MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"26","http://www.dmu.ac.uk/study/courses/postgraduate-courses/creative-technologies/creative-technologies-ma-msc-degree.aspx","Creative Technologies","MA/MSc","0"},
			{"27","http://www.dmu.ac.uk/study/courses/postgraduate-courses/cultural-events-management-msc-degree/cultural-events-management-msc-degree.aspx","Cultural Events Management MSc","MA","0"},
			{"28","http://www.dmu.ac.uk/study/courses/postgraduate-courses/cyber-security/cyber-security-msc-degree.aspx","Cyber Security MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"29","http://www.dmu.ac.uk/study/courses/postgraduate-courses/cyber-technology/cyber-technology.aspx","Cyber Technology","MSc","0"},
			{"30","http://www.dmu.ac.uk/study/courses/postgraduate-courses/cyber-technology/cyber-technology-msc-degree.aspx","Cyber Technology MSc","MSc/PG Dip/PG Cert","0"},
			{"31","http://www.dmu.ac.uk/study/courses/postgraduate-courses/design-innovation-ma-msc-degree/design-innovation-ma-msc-degree.aspx","Design Innovation MA/MSc","MA/MSc","0"},
			{"32","http://www.dmu.ac.uk/study/courses/postgraduate-courses/design-ma-degree/design-ma-degree.aspx","Design MA","MA","0"},
			{"33","http://www.dmu.ac.uk/study/courses/postgraduate-courses/design-management-and-entrepreneurship-ma-degree/design-management-and-entrepreneurship-ma-degree.aspx","Design Management and Entrepreneurship MA","MA","0"},
			{"34","http://www.dmu.ac.uk/study/courses/postgraduate-courses/design-research-mphil-phd-degree/design-research-mphil-phd-degree.aspx","Design research degree MPhil/PhD","MPhil/PhD","0"},
			{"35","http://www.dmu.ac.uk/study/courses/postgraduate-courses/digital-arts-ma-degree/digital-arts-ma-degree.aspx","Digital Arts MA","MA","0"},
			{"36","http://www.dmu.ac.uk/study/courses/postgraduate-courses/digital-design-ma-degree/digital-design-ma-degree.aspx","Digital Design MA","MA","0"},
			{"37","http://www.dmu.ac.uk/study/courses/postgraduate-courses/diplomacy-and-world-order-ma-degree/diplomacy-and-world-order-ma.aspx","Diplomacy and World Order MA","MA","0"},
			{"38","http://www.dmu.ac.uk/study/courses/postgraduate-courses/education-practice-mapg-dippg-cert/education-practice.aspx","Education Practice","MA/PG Dip/PG Cert","0"},
			{"39","http://www.dmu.ac.uk/study/courses/postgraduate-courses/electronic-engineering/electronic-engineering-msc-degree.aspx","Electronic Engineering MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"40","http://www.dmu.ac.uk/study/courses/postgraduate-courses/energy-and-sustainable-building-design/engineering-and-sustainable-building-design-msc-degree.aspx","Energy and Sustainable Building Design MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"41","http://www.dmu.ac.uk/study/courses/postgraduate-courses/energy-and-sustainable-development/energy-and-sustainable-development-msc-degree.aspx","Energy and Sustainable Development MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"42","http://www.dmu.ac.uk/study/courses/postgraduate-courses/engineering-management/engineering-management-msc-degree.aspx","Engineering Management MSc","MSc","0"},
			{"43","http://www.dmu.ac.uk/study/courses/postgraduate-courses/english-language-teaching-ma-degree/english-language-teaching-ma-degree.aspx","English Language Teaching MA","MA","0"},
			{"44","http://www.dmu.ac.uk/study/courses/postgraduate-courses/fashion-and-textiles-ma-degree/fashion-and-textiles-ma-degree.aspx","Fashion and Textiles MA","MA","0"},
			{"45","http://www.dmu.ac.uk/study/courses/postgraduate-courses/fashion-management-with-marketing-ma-degree/fashion-management-with-marketing-ma-degree.aspx","Fashion Management with Marketing MA","MA","0"},
			{"46","http://www.dmu.ac.uk/study/courses/postgraduate-courses/finance-and-investment-msc-degree/finance-and-investment-msc.aspx","Finance and Investment MSc","MSc","0"},
			{"47","http://www.dmu.ac.uk/study/courses/postgraduate-courses/fine-art-ma-degree/fine-art-ma-degree.aspx","Fine Art MA","MA","0"},
			{"48","http://www.dmu.ac.uk/study/courses/postgraduate-courses/food-law-llm-degree/food-law-llm-distance-learning.aspx","Food Law LLM (distance learning)","LLM","0"},
			{"49","http://www.dmu.ac.uk/study/courses/postgraduate-courses/forensic-accounting-msc-degree/forensic-accounting-msc.aspx","Forensic Accounting MSc","MSc","0"},
			{"50","http://www.dmu.ac.uk/study/courses/postgraduate-courses/fc4p/forensic-computing-for-practitioners-professional-qualification.aspx","Forensic Computing for Practitioners","Professional","0"},
			{"51","http://www.dmu.ac.uk/study/courses/postgraduate-courses/global-banking-and-finance.aspx","Global Banking and Finance","MSc","0"},
			{"52","http://www.dmu.ac.uk/study/courses/postgraduate-courses/global-financial-management-msc-degree/global-financial-management-msc.aspx","Global Financial Management MSc","MSc","0"},
			{"53","http://www.dmu.ac.uk/study/courses/postgraduate-courses/global-investment-and-risk.aspx","Global Investment and Risk","MSc","0"},
			{"54","http://www.dmu.ac.uk/study/courses/postgraduate-courses/global-media/global-media-ma-degree.aspx","Global Media","MA/MSc","0"},
			{"55","http://www.dmu.ac.uk/study/courses/postgraduate-courses/health-and-community-development-studies-mapg-dip/health-and-community-development-studies.aspx","Health and Community Development Studies","MA/PG Dip/PG Cert","0"},
			{"56","http://www.dmu.ac.uk/study/courses/postgraduate-courses/health-psychology-mscpg-dippg-cert/health-psychology.aspx","Health Psychology","MSc/PG Dip/PG Cert","0"},
			{"57","http://www.dmu.ac.uk/study/courses/postgraduate-courses/housing-studies-msc-degree/housing-studies-msc.aspx","Housing Studies MSc","MSc","0"},
			{"58","http://www.dmu.ac.uk/study/courses/postgraduate-courses/human-resource-management-ma-pg-dip-full-time/human-resource-management-ma-pg-dip-full-time.aspx","Human Resource Management MA/PG Dip (full time)","MA/PG Dip","0"},
			{"59","http://www.dmu.ac.uk/study/courses/postgraduate-courses/human-resource-management-ma-pg-dip-part-time/human-resource-management-ma-pg-dip-part-time.aspx","Human Resource Management MA/PG Dip (part-time)","MA/PG Dip","0"},
			{"60","http://www.dmu.ac.uk/study/courses/postgraduate-courses/humanities-ma-degree/humanities-ma-degree.aspx","Humanities MA","MA","0"},
			{"61","http://www.dmu.ac.uk/study/courses/postgraduate-courses/humanities-research-mphil-phd-degree/humanities-research-mphil-phd-degree.aspx","Humanities research degree MPhil/PhD","MPhil/PhD","0"},
			{"62","http://www.dmu.ac.uk/study/courses/postgraduate-courses/independent-study-leicester-media-school/independent-study-leicester-media-school-ma-degree.aspx","Independent Study: Leicester Media School MA/PG Dip/PG Cert","MA/MSc","0"},
			{"63","http://www.dmu.ac.uk/study/courses/postgraduate-courses/information-systems-management/information-systems-management-msc-degree.aspx","Information Systems Management MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"64","http://www.dmu.ac.uk/study/courses/postgraduate-courses/intelligent-systems-and-robotics/intelligent-systems-and-robotics-msc-degree.aspx","Intelligent Systems and Robotics MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"65","http://www.dmu.ac.uk/study/courses/postgraduate-courses/intelligent-systems/intelligent-systems-msc-degree.aspx","Intelligent Systems MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"66","http://www.dmu.ac.uk/study/courses/postgraduate-courses/intercultural-business-communications-mscpg-dip/intercultural-business-communications.aspx","Intercultural Business Communications","MSc/PG Dip/PG Cert","0"},
			{"67","http://www.dmu.ac.uk/study/courses/postgraduate-courses/interior-design-ma-degree/interior-design-ma-degree.aspx","Interior Design MA","MA","0"},
			{"68","http://www.dmu.ac.uk/study/courses/postgraduate-courses/intermediate-dysphagia-msc/intermediate-dysphagia.aspx","Intermediate Dysphagia","MSc","0"},
			{"69","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-business-and-csr-msc-degree/international-business-and-corporate-social-responsibility-msc.aspx","International Business and Corporate Social Responsibility MSc","MSc","0"},
			{"70","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-business-entrepreneurship-msc-degree/international-business-and-entrepreneurship-msc.aspx","International Business and Entrepreneurship MSc","MSc","0"},
			{"71","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-business-and-finance-msc-degree/international-business-and-finance-msc.aspx","International Business and Finance MSc","MSc","0"},
			{"72","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-business-and-hrm-msc-degree/international-business-and-human-resource-management-msc.aspx","International Business and Human Resource Management MSc","MSc","0"},
			{"73","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-business-and-management-msc-degree/international-business-and-management-msc.aspx","International Business and Management MSc","MSc","0"},
			{"74","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-business-and-marketing-msc-degree/international-business-and-marketing-msc.aspx","International Business and Marketing MSc","MSc","0"},
			{"75","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-business-law-llm-degree/international-business-law-llm.aspx","International Business Law LLM","LLM","0"},
			{"76","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-human-rights-law-llm-degree/international-human-rights-law-llm-distance-learning.aspx","International Human Rights Law LLM (distance learning)","LLM","0"},
			{"77","http://www.dmu.ac.uk/study/courses/postgraduate-courses/international-relations-ma-degree/international-relations-ma.aspx","International Relations MA","MA","0"},
			{"78","http://www.dmu.ac.uk/study/courses/postgraduate-courses/investigative-journalism/investigative-journalism-ma-degree.aspx","Investigative Journalism MA","MA","0"},
			{"79","http://www.dmu.ac.uk/study/courses/postgraduate-courses/journalism-masters/journalism-ma-degree.aspx","Journalism MA","MA/PG Dip/PG Cert","0"},
			{"80","http://www.dmu.ac.uk/study/courses/postgraduate-courses/graduate-diploma-in-law-gdl-cpe-full-time/law-graduate-diploma-gdl-cpe-graduate-diploma-full-time.aspx","Law Graduate Diploma GDL/CPE Graduate Diploma (full-time)","Graduate Diploma","0"},
			{"81","http://www.dmu.ac.uk/study/courses/postgraduate-courses/graduate-diploma-in-law-gdl-cpe-part-time/law-graduate-diploma-gdl-cpe-graduate-diploma-part-time.aspx","Law Graduate Diploma GDL/CPE Graduate Diploma (part-time)","Graduate Diploma","0"},
			{"82","http://www.dmu.ac.uk/study/courses/postgraduate-courses/legal-practice-course-lpc-full-time/legal-practice-course-full-time.aspx","Legal Practice Course (full-time)","PG Dip","0"},
			{"83","http://www.dmu.ac.uk/study/courses/postgraduate-courses/legal-practice-course-lpc-part-time/legal-practice-course-part-time.aspx","Legal Practice Course (part-time)","PG Dip","0"},
			{"84","http://www.dmu.ac.uk/study/courses/postgraduate-courses/legal-practice-llm-degree/legal-practice-course-llm-distance-learning.aspx","Legal Practice LLM (distance learning)","LLM","0"},
			{"85","http://www.dmu.ac.uk/study/courses/postgraduate-courses/management-law-and-humanities-of-sport-ma-degree/management-law-and-humanities-of-sport-ma-degree.aspx","Management, Law and Humanities of Sport MA","MA","0"},
			{"86","http://www.dmu.ac.uk/study/courses/postgraduate-courses/marketing-management-msc-degree/marketing-management-msc.aspx","Marketing Management MSc","MSc","0"},
			{"87","http://www.dmu.ac.uk/study/courses/postgraduate-courses/master-of-business-administration-global-mba/master-of-business-administration-mba-global.aspx","Master of Business Administration MBA (Global)","MBA","0"},
			{"88","http://www.dmu.ac.uk/study/courses/postgraduate-courses/master-of-business-administration-mba-part-time/master-of-business-administration-mba-part-time.aspx","Master of Business Administration MBA (part-time)","MBA","0"},
			{"89","http://www.dmu.ac.uk/study/courses/postgraduate-courses/master-of-business-administration-lawyers-mba/master-of-business-administration-mba-lawyers-distance-learning.aspx","Master of Business Administration MBA for Lawyers (distance learning)","MBA","0"},
			{"90","http://www.dmu.ac.uk/study/courses/postgraduate-courses/masters-by-research-mamsc/masters-by-research.aspx","Masters by Research","MA/MSc","0"},
			{"91","http://www.dmu.ac.uk/study/courses/postgraduate-courses/masters-in-research-applied-health-studies-mres/masters-in-research-applied-health-studies.aspx","Masters in Research Applied Health Studies","MSc/PG Dip/PG Cert","0"},
			{"92","http://www.dmu.ac.uk/study/courses/postgraduate-courses/mechanical-engineering/mechanical-engineering-msc-degree.aspx","Mechanical Engineering MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"93","http://www.dmu.ac.uk/study/courses/postgraduate-courses/mechatronics/mechatronics-msc-degree.aspx","Mechatronics MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"94","http://www.dmu.ac.uk/study/courses/postgraduate-courses/medical-education-mapg-dip/medical-education.aspx","Medical Education","MA/PG Dip","0"},
			{"95","http://www.dmu.ac.uk/study/courses/postgraduate-courses/medical-law-and-ethics-llm-degree/medical-law-and-ethics-llm-distance-learning.aspx","Medical Law and Ethics LLM (distance learning)","LLM","0"},
			{"96","http://www.dmu.ac.uk/study/courses/postgraduate-courses/medical-leadership-and-advanced-professional-skill/medical-leadership-and-advanced-professional-skills.aspx","Medical Leadership and Advanced Professional Skills","MSc","0"},
			{"97","http://www.dmu.ac.uk/study/courses/postgraduate-courses/non-medical-prescribing-pg-cert/non-medical-prescribing.aspx","Non-Medical Prescribing","PG Cert","0"},
			{"98","http://www.dmu.ac.uk/study/courses/postgraduate-courses/nursing-mscpg-dippg-cert/nursing.aspx","Nursing","MSc/PG Dip/PG Cert","0"},
			{"99","http://www.dmu.ac.uk/study/courses/postgraduate-courses/nursing-specialist-district-nursing-with-nmc-spq/nursing-specialist-district-nursing-with-nmc-recordable-spq.aspx","Nursing (Specialist District Nursing) with NMC Recordable SPQ","MSc/PG Dip/PG Cert","0"},
			{"100","http://www.dmu.ac.uk/study/courses/postgraduate-courses/nursing-midwifery-council-recordable-teacher-qual/nursing-midwifery-council-recordable-teacher-qualification-pg-cert.aspx","Nursing Midwifery Council Recordable Teacher Qualification PG Cert","PG Cert","0"},
			{"101","http://www.dmu.ac.uk/study/courses/postgraduate-courses/nursing-specialist-practice-with-nmc-recordable/nursing-specialist-practice-with-nmc-recordable.aspx","Nursing Specialist Practice with NMC recordable Specialist Practitioner Qualification","MSc/PG Dip/PG Cert","0"},
			{"102","http://www.dmu.ac.uk/study/courses/postgraduate-courses/palliative-care-mscpg-dippg-cert/palliative-care.aspx","Palliative Care","MSc/PG Dip/PG Cert","0"},
			{"103","http://www.dmu.ac.uk/study/courses/postgraduate-courses/performance-practices-ma-degree/performance-practices-ma-degree.aspx","Performance Practices MA","MA","0"},
			{"104","http://www.dmu.ac.uk/study/courses/postgraduate-courses/performance-practices-mfa-degree/performance-practices-mfa-degree.aspx","Performance Practices MFA","MFA","0"},
			{"105","http://www.dmu.ac.uk/study/courses/postgraduate-courses/pharmaceutical-biotechnology-mscpg-dippg-cert/pharmaceutical-biotechnology.aspx","Pharmaceutical Biotechnology","MSc/PG Dip/PG Cert","0"},
			{"106","http://www.dmu.ac.uk/study/courses/postgraduate-courses/pharmaceutical-quality-by-design-mscpg-dippg-cer/pharmaceutical-quality-by-design.aspx","Pharmaceutical Quality by Design","MSc/PG Dip/PG Cert","0"},
			{"107","http://www.dmu.ac.uk/study/courses/postgraduate-courses/photographic-history-ma-degree/photographic-history-ma-degree.aspx","Photographic History MA","MA","0"},
			{"108","http://www.dmu.ac.uk/study/courses/postgraduate-courses/photography-ma-degree/photography-ma-degree.aspx","Photography MA","MA","0"},
			{"109","http://www.dmu.ac.uk/study/courses/postgraduate-courses/politics-ma-degree/politics-ma.aspx","Politics MA","MA","0"},
			{"110","http://www.dmu.ac.uk/study/courses/postgraduate-courses/practice-certificate-in-independent-prescribing/practice-certificate-in-independent-prescribing-for-pharmacists.aspx","Practice Certificate in Independent Prescribing for Pharmacists","PG Cert","0"},
			{"111","http://www.dmu.ac.uk/study/courses/postgraduate-courses/practice-education-programme-bsc-pg-cert/practice-education-programme.aspx","Practice Education Programme","PG Cert","0"},
			{"112","http://www.dmu.ac.uk/study/courses/postgraduate-courses/practice-nursing-pg-cert/practice-nursing.aspx","Practice Nursing","PG Cert","0"},
			{"113","http://www.dmu.ac.uk/study/courses/postgraduate-courses/product-design-ma-degree/product-design-ma-degree.aspx","Product Design MA","MA","0"},
			{"114","http://www.dmu.ac.uk/study/courses/postgraduate-courses/association-chartered-certified-accountants-acca/association-of-chartered-certified-accountants-course-acca.aspx","Professional Association of Chartered Certified Accountants (ACCA)","ACCA","0"},
			{"115","http://www.dmu.ac.uk/study/courses/postgraduate-courses/professional-practice-in-digital-and-forensics/professional-practice-in-digital-and-forensics-and-security.aspx","Professional Practice in Digital Forensics and Security MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"116","http://www.dmu.ac.uk/study/courses/postgraduate-courses/project-management-msc-degree/project-management-msc.aspx","Project Management MSc","MSc","0"},
			{"117","http://www.dmu.ac.uk/study/courses/postgraduate-courses/psychological-well-being.aspx","Psychological Well-being","MSc/PG Dip/PG Cert","0"},
			{"118","http://www.dmu.ac.uk/study/courses/postgraduate-courses/psychological-well-being-mscpg-dippg-cert/psychological-well-being.aspx","Psychological Well-being","MSc/PG Dip/PG Cert","0"},
			{"119","http://www.dmu.ac.uk/study/courses/postgraduate-courses/quality-by-design-for-the-pharmaceutical-industry/quality-by-design-for-the-pharmaceutical-industry.aspx","Quality by Design for the Pharmaceutical Industry","MSc/PG Dip/PG Cert with professional qualification","0"},
			{"120","http://www.dmu.ac.uk/study/courses/postgraduate-courses/risk-management-msc-degree/risk-management-msc.aspx","Risk Management MSc","MSc","0"},
			{"121","http://www.dmu.ac.uk/study/courses/postgraduate-courses/risk-management-msc-distance-learning-degree/risk-management-msc-distance-learning.aspx","Risk Management MSc (distance learning)","MSc","0"},
			{"122","http://www.dmu.ac.uk/study/courses/postgraduate-courses/social-work.aspx","Social Work","MA","0"},
			{"123","http://www.dmu.ac.uk/study/courses/postgraduate-courses/software-engineering/software-engineering-msc-degree.aspx","Software Engineering MSc/PG Dip/PG Cert","MSc/PG Dip/PG Cert","0"},
			{"124","http://www.dmu.ac.uk/study/courses/postgraduate-courses/specialist-community-public-health-nursing-mscpg/specialist-community-public-health-nursing.aspx","Specialist Community Public Health Nursing","MSc/PG Dip/PG Cert","0"},
			{"125","http://www.dmu.ac.uk/study/courses/postgraduate-courses/sports-history-and-culture-ma-degree/sports-history-and-culture-ma-degree.aspx","Sports History and Culture MA","MA","0"},
			{"126","http://www.dmu.ac.uk/study/courses/postgraduate-courses/sports-law-llm-degree-distance-learning/sports-law-llm-distance-learning.aspx","Sports Law LLM (distance learning)","LLM","0"},
			{"127","http://www.dmu.ac.uk/study/courses/postgraduate-courses/sports-law-pg-cert-degree/sports-law-pg-cert.aspx","Sports Law PG Cert","PG Cert","0"},
			{"128","http://www.dmu.ac.uk/study/courses/postgraduate-courses/strategic-and-digital-marketing-msc-degree/strategic-and-digital-marketing-msc.aspx","Strategic and Digital Marketing MSc","MSc","0"},
			{"129","http://www.dmu.ac.uk/study/courses/postgraduate-courses/television-scriptwriting/television-scriptwriting-ma-degree.aspx","Television Scriptwriting MA","MA/MSc","0"},
			{"130","http://www.dmu.ac.uk/study/courses/postgraduate-courses/youth-and-community-development-studies-mapg-dip/youth-and-community-development-studies.aspx","Youth and Community Development Studies","MA/PG Dip/PG Cert","0"},
			{"131","http://www.dmu.ac.uk/study/courses/postgraduate-courses/youth-work-and-community-development-pq-mapg-dip/youth-work-and-community-development.aspx","Youth Work and Community Development","MA/PG Dip with professional qualification","0"},
			{"132","http://www.dmu.ac.uk/study/courses/postgraduate-courses/youth-work-health-and-community-development-pq-m/youth-work-health-and-community-development.aspx","Youth Work, Health and Community Development","MA/PG Dip with professional qualification","0"},

		};
	

}
