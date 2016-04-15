package no7.LJMU;

import java.io.File;
import java.io.FileInputStream;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import java.util.*;
public class getURL {

	/**
	 * @param args
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		FileInputStream fis=new FileInputStream(new File("./LJMUPost.html"));
		byte[] b=new byte[fis.available()];
        fis.read(b);
       fis.close();
        String htmls=new String(b);
       int count=1;
       ArrayList<String> list=new ArrayList<String>();
	    Parser	parser=Parser.createParser(htmls, "utf-8");
   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
                   new HasAttributeFilter("href"));
   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
   	    for(int i=0;i<nodes4.size();i++)
   	    {
   	    	LinkTag link=(LinkTag)nodes4.elementAt(i);
   	    	//if(!link.getAttribute("href").equals("")&&!html2Str(link.toHtml()).contains("View this course"))
   	    	{
   	    		if(!list.contains(link.getAttribute("href")))
   	    		{
   	    			list.add(link.getAttribute("href"));
   	    			/*System.out.println(HTMLFilter(html2Str(link.toHtml()))
   	   	    				.replace("\r\n", "").split(" – ").length);*/
   	    			System.out.println("{\""+count+"\",\"https://www.ljmu.ac.uk"
   	   	    				+link.getAttribute("href")+"\",\""+HTMLFilter(html2Str(link.toHtml()))
   	   	    				.replace("\r\n", "")+"\",\"0\"},");
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
		{"1","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/accounting-and-finance","Accounting and Finance","0"},
		{"2","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/animal-behaviour","Animal Behaviour","0"},
		{"3","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/animal-behaviour-with-foundation-year","Animal Behaviour with Foundation Year","0"},
		{"4","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/applied-chemistry-bsc","Applied Chemistry","0"},
		{"5","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/applied-chemistry-mchem","Applied Chemistry","0"},
		{"6","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/applied-chemistry-bsc-with-foundation-year","Applied Chemistry with Foundation Year","0"},
		{"7","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/applied-psychology","Applied Psychology","0"},
		{"8","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/applied-sports-psychology","Applied Sport Psychology","0"},
		{"9","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/architectural-engineering-meng","Architectural Engineering","0"},
		{"10","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/architectural-engineering-beng","Architectural Engineering","0"},
		{"11","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/architectural-engineering-with-foundation-year-beng","Architectural Engineering with Foundation Year","0"},
		{"12","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/architectural-technology","Architectural Technology","0"},
		{"13","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/architecture","Architecture","0"},
		{"14","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/astrophysics","Astrophysics MPhys","0"},
		{"15","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/audio-and-music-production","Audio and Music Production","0"},
		{"16","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/biochemistry","Biochemistry","0"},
		{"17","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/biochemistry-with-foundation-year","Biochemistry with Foundation Year","0"},
		{"18","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/biology","Biology","0"},
		{"19","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/biology-with-foundation-year","Biology with Foundation Year","0"},
		{"20","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/biomedical-science","Biomedical Science","0"},
		{"21","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/biomedical-science-with-foundation-year","Biomedical Science with Foundation Year","0"},
		{"22","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/building-services-engineering-meng","Building Services Engineering","0"},
		{"23","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/building-services-engineering-beng","Building Services Engineering","0"},
		{"24","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/building-services-engineering-project-management","Building Services Engineering Project Management","0"},
		{"25","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/building-services-engineering-with-foundation-year-beng","Building Services Engineering with Foundation Year","0"},
		{"26","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/building-surveying-bsc","Building Surveying","0"},
		{"27","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/business-and-human-resource-management-part-time","Business and Human Resource Management (Part-time)","0"},
		{"28","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/business-and-public-relations","Business and Public Relations","0"},
		{"29","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/business-management","Business Management","0"},
		{"30","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/business-studies","Business Studies","0"},
		{"31","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-and-environmental-engineering-meng","Civil and Environmental Engineering","0"},
		{"32","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-and-offshore-engineering-meng","Civil and Offshore Engineering","0"},
		{"33","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-and-structural-engineering","Civil and Structural Engineering","0"},
		{"34","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-and-transportation-engineering-meng","Civil and Transportation Engineering","0"},
		{"35","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-engineering-hnc","Civil Engineering","0"},
		{"36","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-engineering-beng","Civil Engineering","0"},
		{"37","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-engineering-meng","Civil Engineering","0"},
		{"38","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-engineering-and-architecture-meng","Civil Engineering and Architecture","0"},
		{"39","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-engineering-and-construction-management-meng","Civil Engineering and Construction Management","0"},
		{"40","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/civil-engineering-with-foundation-year-beng","Civil Engineering with Foundation Year","0"},
		{"41","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-forensics-mcomp","Computer Forensics","0"},
		{"42","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-forensics","Computer Forensics","0"},
		{"43","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-forensics-with-foundation-year-bsc","Computer Forensics with Foundation Year","0"},
		{"44","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-games-development","Computer Games Development","0"},
		{"45","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-games-development-mcomp","Computer Games Development","0"},
		{"46","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-games-development-with-foundation-year-bsc","Computer Games Development with Foundation Year","0"},
		{"47","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-science-bsc","Computer Science","0"},
		{"48","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-science-mcomp","Computer Science","0"},
		{"49","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-science-with-foundation-year-bsc","Computer Science with Foundation Year","0"},
		{"50","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-security-mcomp","Computer Security","0"},
		{"51","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-security-bsc","Computer Security","0"},
		{"52","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-security-with-foundation-year-bsc","Computer Security with Foundation Year","0"},
		{"53","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-studies-mcomp","Computer Studies","0"},
		{"54","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-studies","Computer Studies","0"},
		{"55","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-studies-with-foundation-year-bsc","Computer Studies with Foundation Year","0"},
		{"56","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-technology","Computer Technology","0"},
		{"57","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-technology-meng","Computer Technology","0"},
		{"58","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/computer-technology-with-foundation-year-beng","Computer Technology with Foundation Year","0"},
		{"59","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/construction-and-property-hnc","Construction and Property","0"},
		{"60","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/construction-management","Construction Management","0"},
		{"61","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/control-and-automation-engineering-beng","Control and Automation Engineering","0"},
		{"62","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/control-and-automation-engineering-meng","Control and Automation Engineering","0"},
		{"63","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/control-and-automation-engineering-with-foundation-year-beng","Control and Automation Engineering with Foundation Year","0"},
		{"64","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/creative-writing","Creative Writing","0"},
		{"65","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/creative-writing-and-film-studies","Creative Writing and Film Studies","0"},
		{"66","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/criminal-justice","Criminal Justice","0"},
		{"67","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/criminology","Criminology","0"},
		{"68","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/criminology-and-psychology-bsc","Criminology and Psychology","0"},
		{"69","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/criminology-and-sociology","Criminology and Sociology","0"},
		{"70","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/dance-practices","Dance Practices","0"},
		{"71","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/disability-sport-coaching-and-development","Disability Sport Coaching and Development","0"},
		{"72","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/drama","Drama","0"},
		{"73","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/drama-and-creative-writing","Drama and Creative Writing","0"},
		{"74","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/drama-and-english","Drama and English","0"},
		{"75","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/early-childhood-studies","Early Childhood Studies","0"},
		{"76","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/education-studies-and-early-years","Education Studies and Early Years","0"},
		{"77","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/education-studies-with-special-and-inclusive-needs","Education Studies and Special and Inclusive Needs","0"},
		{"78","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electrical-and-electronic-engineering-beng","Electrical and Electronic Engineering","0"},
		{"79","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electrical-and-electronic-engineering-meng","Electrical and Electronic Engineering","0"},
		{"80","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electrical-and-electronic-engineering-with-foundation-year-beng","Electrical and Electronic Engineering with Foundation Year","0"},
		{"81","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electrical-power-engineering-meng","Electrical Power Engineering","0"},
		{"82","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electrical-power-engineering-beng","Electrical Power Engineering","0"},
		{"83","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electrical-power-engineering-with-foundation-year-beng","Electrical Power Engineering with Foundation Year","0"},
		{"84","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electronic-engineering-meng","Electronic Engineering","0"},
		{"85","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electronic-engineering-beng","Electronic Engineering","0"},
		{"86","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/electronic-engineering-with-foundation-year-beng","Electronic Engineering with Foundation Year","0"},
		{"87","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/english","English","0"},
		{"88","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/english-and-creative-writing","English and Creative Writing","0"},
		{"89","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/english-and-media-and-cultural-studies","English and Media and Cultural Studies","0"},
		{"90","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/environmental-health","Environmental Health","0"},
		{"91","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/events-management","Events Management","0"},
		{"92","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/facilities-management-bsc","Facilities Management","0"},
		{"93","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/fashion","Fashion","0"},
		{"94","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/film-studies","Film Studies","0"},
		{"95","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/fine-art","Fine Art","0"},
		{"96","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/food-development-and-nutrition","Food Development and Nutrition","0"},
		{"97","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/forensic-anthropology","Forensic Anthropology","0"},
		{"98","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/forensic-anthropology-with-foundation-year","Forensic Anthropology with Foundation Year","0"},
		{"99","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/forensic-psychology-and-criminal-justice","Forensic Psychology and Criminal Justice","0"},
		{"100","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/forensic-science","Forensic Science","0"},
		{"101","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/forensic-science-with-foundation-year","Forensic Science with Foundation Year","0"},
		{"102","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/geography-bsc","Geography","0"},
		{"103","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/geography-bsc-with-foundation-year","Geography with Foundation Year","0"},
		{"104","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/graphic-design-and-illustration","Graphic Design and Illustration","0"},
		{"105","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/health-and-social-care-for-families-individuals-and-communities","Health and Social Care for Individuals, Families and Communities","0"},
		{"106","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/history","History","0"},
		{"107","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/history-and-english","History and English","0"},
		{"108","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/history-of-art","History of Art","0"},
		{"109","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/human-resource-management","Human Resource Management","0"},
		{"110","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/cpd-human-resource-management","Human Resource Management - CIPD Intermediate Level Standards","0"},
		{"111","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/international-journalism","International Journalism","0"},
		{"112","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/journalism","Journalism","0"},
		{"113","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/law","Law","0"},
		{"114","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/law-and-criminal-justice","Law and Criminal Justice","0"},
		{"115","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/law-part-time","Law (part-time)","0"},
		{"116","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/learning-development-and-support","Learning, Development and Support","0"},
		{"117","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/management-transport-and-logistics","Management, Transport and Logistics","0"},
		{"118","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/manufacturing-systems-engineering-meng","Manufacturing Systems Engineering","0"},
		{"119","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/manufacturing-systems-engineering-beng","Manufacturing Systems Engineering","0"},
		{"120","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/marine-operations","Marine Operations","0"},
		{"121","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/maritime-business-and-management","Maritime Business and Management","0"},
		{"122","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/marketing","Marketing","0"},
		{"123","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mathematics","Mathematics","0"},
		{"124","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mathematics-and-education-studies","Mathematics and Education Studies","0"},
		{"125","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mathematics-with-foundation-year","Mathematics with Foundation Year","0"},
		{"126","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-and-manufacturing-engineering-beng","Mechanical and Manufacturing Engineering","0"},
		{"127","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-and-manufacturing-engineering-meng","Mechanical and Manufacturing Engineering","0"},
		{"128","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-and-manufacturing-engineering-with-foundation-year-beng","Mechanical and Manufacturing Engineering with Foundation Year","0"},
		{"129","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-and-marine-engineering-beng","Mechanical and Marine Engineering","0"},
		{"130","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-and-marine-engineering-meng","Mechanical and Marine Engineering","0"},
		{"131","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-and-marine-engineering-with-foundation-year-beng","Mechanical and Marine Engineering with Foundation Year","0"},
		{"132","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-engineering-meng","Mechanical Engineering","0"},
		{"133","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-engineering-beng","Mechanical Engineering","0"},
		{"134","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-engineering-with-foundation-year-beng","Mechanical Engineering with Foundation Year","0"},
		{"135","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-engineering-with-management-beng","Mechanical Engineering with Management","0"},
		{"136","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-engineering-with-management-meng","Mechanical Engineering with Management","0"},
		{"137","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mechanical-engineering-with-management-with-foundation-year-beng","Mechanical Engineering With Management with Foundation Year","0"},
		{"138","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/media-culture-communication","Media, Culture, Communication","0"},
		{"139","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/media-production","Media Production","0"},
		{"140","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/midwifery","Midwifery","0"},
		{"141","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/multimedia-computing-bsc","Multimedia Computing","0"},
		{"142","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/multimedia-computing-mcomp","Multimedia Computing","0"},
		{"143","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/multimedia-computing-with-foundation-year-bsc","Multimedia Computing with Foundation Year","0"},
		{"144","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/nautical-science","Nautical Science","0"},
		{"145","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/mental-health-nursing","Nursing (Mental Health)","0"},
		{"146","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/adult-nursing-bsc","Nursing with Registered Nurse Status (Adult)","0"},
		{"147","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/child-nursing-bsc","Nursing with Registered Nurse Status (Child)","0"},
		{"148","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/nutrition","Nutrition","0"},
		{"149","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/outdoor-education","Outdoor Education","0"},
		{"150","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/paramedic-practice","Paramedic Practice","0"},
		{"151","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/pharmaceutical-science","Pharmaceutical Science","0"},
		{"152","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/pharmaceutical-science-with-foundation-year","Pharmaceutical Science with Foundation Year","0"},
		{"153","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/pharmacy","Pharmacy","0"},
		{"154","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/physical-education","Physical Education","0"},
		{"155","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/physics-with-astronomy","Physics with Astronomy","0"},
		{"156","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/policing-studies-ba","Policing Studies","0"},
		{"157","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/policing-studies","Policing Studies","0"},
		{"158","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/policing-studies-and-computer-forensics","Policing Studies and Computer Forensics","0"},
		{"159","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/policing-studies-and-cybercrime","Policing Studies and Cyber-Crime","0"},
		{"160","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/policing-studies-and-evidence-based-practice","Policing Studies and Evidence-based Practice","0"},
		{"161","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/policing-studies-and-forensic-psychology","Policing Studies and Forensic Psychology","0"},
		{"162","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/policing-studies-and-forensics","Policing Studies and Forensics","0"},
		{"163","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/primary-education-with-qts","Primary Education with recommendation for Qualified Teacher Status (QTS)","0"},
		{"164","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/product-design-engineering","Product Design Engineering","0"},
		{"165","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/quantity-surveying","Quantity Surveying","0"},
		{"166","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/real-estate-management-and-business","Real Estate Management and Business","0"},
		{"167","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/science-and-football","Science and Football","0"},
		{"168","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/sociology","Sociology","0"},
		{"169","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/software-engineering","Software Engineering","0"},
		{"170","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/software-engineering-mcomp","Software Engineering","0"},
		{"171","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/software-engineering-with-foundation-year-bsc","Software Engineering with Foundation Year","0"},
		{"172","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/sport-and-exercise-science","Sport and Exercise Science","0"},
		{"173","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/sport-and-nutrition-for-health","Sport and Nutrition for Health","0"},
		{"174","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/sport-business","Sport Business","0"},
		{"175","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/sport-coaching","Sport Coaching","0"},
		{"176","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/sport-development","Sport Development","0"},
		{"177","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/tourism-and-leisure-management","Tourism and Leisure Management","0"},
		{"178","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/wildlife-conservation","Wildlife Conservation","0"},
		{"179","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/wildlife-conservation-with-foundation-year","Wildlife Conservation with Foundation Year","0"},
		{"180","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/zoology","Zoology","0"},
		{"181","https://www.ljmu.ac.uk/study/courses/undergraduates/2016/zoology-with-foundation-year","Zoology with Foundation Year","0"}
	};
	
	public static String[][] PostData={
		{"1","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-computer-studies","Advanced Computer Studies MSc","0"},
		{"2","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-educational-practice-dyslexia","Advanced Educational Practice: Dyslexia MA, PgDip, PgCert","0"},
		{"3","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-educational-practice-leadership-and-management","Advanced Educational Practice: Leadership and Management MA, PgDip, PgCert","0"},
		{"4","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-educational-practice","Advanced Educational Practice MA","0"},
		{"5","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-educational-practice-mentoring-and-coaching","Advanced Educational Practice - Mentoring and Coaching PgCert","0"},
		{"6","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-educational-practice-special-educational-needs","Advanced Educational Practice: Special Educational Needs MA, PgDip, PgCert","0"},
		{"7","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-educational-practice-teaching-and-learning","Advanced Educational Practice: Teaching and Learning MA, PgDip, PgCert","0"},
		{"8","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-healthcare-practice-clinical","Advanced Healthcare Practice (Clinical) MSc","0"},
		{"9","https://www.ljmu.ac.uk/study/courses/postgraduates/advanced-policing-studies","Advanced Policing Studies *subject to validation","0"},
		{"10","https://www.ljmu.ac.uk/study/courses/postgraduates/analytical-forensic-science-msc","Analytical Forensic Science MSc","0"},
		{"11","https://www.ljmu.ac.uk/study/courses/postgraduates/applied-facilities-management","Applied Facilities Management MSc, PGDip, PGCert, CPD","0"},
		{"12","https://www.ljmu.ac.uk/study/courses/postgraduates/architectural-engineering","Architectural Engineering MSc","0"},
		{"13","https://www.ljmu.ac.uk/study/courses/postgraduates/architecture-march","Architecture","0"},
		{"14","https://www.ljmu.ac.uk/study/courses/postgraduates/art-and-design","Art and Design MRes","0"},
		{"15","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-art-and-design","Art and Design: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"16","https://www.ljmu.ac.uk/study/courses/postgraduates/art-in-science-ma","Art in Science MA","0"},
		{"17","https://www.ljmu.ac.uk/study/courses/postgraduates/astrophysics-msc","Astrophysics (by Distance Learning) MSc","0"},
		{"18","https://www.ljmu.ac.uk/study/courses/postgraduates/astrophysics-research-institute-postgraduate-research","Astrophysics Research Institute - Postgraduate Research Opportunities","0"},
		{"19","https://www.ljmu.ac.uk/study/courses/postgraduates/bioarchaeology","Bioarchaeology MSc","0"},
		{"20","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-biology","Biology: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"21","https://www.ljmu.ac.uk/study/courses/postgraduates/centre-for-public-health-postgraduate-research","Centre for Public Health - Postgraduate Research Opportunities","0"},
		{"22","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-chemistry","Chemistry: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"23","https://www.ljmu.ac.uk/study/courses/postgraduates/cities-culture-and-creativity","Cities, Culture and Creativity MA","0"},
		{"24","https://www.ljmu.ac.uk/study/courses/postgraduates/civil-engineering-msc","Civil Engineering MSc","0"},
		{"25","https://www.ljmu.ac.uk/study/courses/postgraduates/clinical-exercise-physiology","Clinical Exercise Physiology MSc","0"},
		{"26","https://www.ljmu.ac.uk/study/courses/postgraduates/clinical-pharmacy-for-primary-and-interface-care","Clinical Pharmacy for Primary and Interface Care","0"},
		{"27","https://www.ljmu.ac.uk/study/courses/postgraduates/clinical-pharmacy","Clinical Pharmacy MSc, PgDip, PgCert","0"},
		{"28","https://www.ljmu.ac.uk/study/courses/postgraduates/commercial-building-surveying","Commercial Building Surveying MSc","0"},
		{"29","https://www.ljmu.ac.uk/study/courses/postgraduates/computer-forensics","Computer Forensics MSc","0"},
		{"30","https://www.ljmu.ac.uk/study/courses/postgraduates/computer-network-security","Computer Network Security MSc","0"},
		{"31","https://www.ljmu.ac.uk/study/courses/postgraduates/computer-science-msc","Computer Science MSc","0"},
		{"32","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-computer-science-information-technology","Computer Science: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"33","https://www.ljmu.ac.uk/study/courses/postgraduates/computing-and-information-systems","Computing and Information Systems MSc","0"},
		{"34","https://www.ljmu.ac.uk/study/courses/postgraduates/construction-project-management","Construction Project Management MSc","0"},
		{"35","https://www.ljmu.ac.uk/study/courses/postgraduates/contemporary-art","Contemporary Art","0"},
		{"36","https://www.ljmu.ac.uk/study/courses/postgraduates/counselling-and-psychotherapeutic-practice","Counselling and Psychotherapy Practice","0"},
		{"37","https://www.ljmu.ac.uk/study/courses/postgraduates/criminal-justice","Criminal Justice MA","0"},
		{"38","https://www.ljmu.ac.uk/study/courses/postgraduates/critical-social-science","Critical Social Science MRes","0"},
		{"39","https://www.ljmu.ac.uk/study/courses/postgraduates/cyber-security","Cyber Security MSc","0"},
		{"40","https://www.ljmu.ac.uk/study/courses/postgraduates/department-of-built-environment-postgraduate-research","Department of Built Environment - Postgraduate Research Opportunities","0"},
		{"41","https://www.ljmu.ac.uk/study/courses/postgraduates/department-of-civil-engineering-postgraduate-research","Department of Civil Engineering - Postgraduate Research Opportunities","0"},
		{"42","https://www.ljmu.ac.uk/study/courses/postgraduates/department-of-computer-science-postgraduate-research","Department of Computer Science - Postgraduate Research Opportunities","0"},
		{"43","https://www.ljmu.ac.uk/study/courses/postgraduates/department-of-electronics-and-electrical-engineering-postgraduate-research","Department of Electronics and Electrical Engineering - Postgraduate Research Opportunities","0"},
		{"44","https://www.ljmu.ac.uk/study/courses/postgraduates/department-of-maritime-and-mechanical-engineering-postgraduate-research","Department of Maritime and Mechanical Engineering - Postgraduate Research Opportunities","0"},
		{"45","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-design-technology","Design and Technology: Secondary PGDE with QTS","0"},
		{"46","https://www.ljmu.ac.uk/study/courses/postgraduates/digital-literacies-ma","Digital Literacies and Learning *Subject to validation","0"},
		{"47","https://www.ljmu.ac.uk/study/courses/postgraduates/digital-marketing","Digital Marketing MSc","0"},
		{"48","https://www.ljmu.ac.uk/study/courses/postgraduates/doctorate-in-business-administration","Doctorate in Business Administration DBA","0"},
		{"49","https://www.ljmu.ac.uk/study/courses/postgraduates/drone-technology-and-applications","Drone Technology and Applications MSc","0"},
		{"50","https://www.ljmu.ac.uk/study/courses/postgraduates/drug-discovery-and-design","Drug Discovery and Design MSc, PgDip, PgCert","0"},
		{"51","https://www.ljmu.ac.uk/study/courses/postgraduates/education-and-society","Education and Society MRes","0"},
		{"52","https://www.ljmu.ac.uk/study/courses/postgraduates/education-globalisation-and-social-change-ma","Education, Globalisation and Social Change MA * subject to validation","0"},
		{"53","https://www.ljmu.ac.uk/study/courses/postgraduates/education-practice","Education Practice MA","0"},
		{"54","https://www.ljmu.ac.uk/study/courses/postgraduates/electrical-power-and-control-engineering","Electrical Power and Control Engineering MSc","0"},
		{"55","https://www.ljmu.ac.uk/study/courses/postgraduates/english","English MRes","0"},
		{"56","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-english","English: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"57","https://www.ljmu.ac.uk/study/courses/postgraduates/entrepreneurship","Entrepreneurship MSc","0"},
		{"58","https://www.ljmu.ac.uk/study/courses/postgraduates/mba-executive-development","Executive Development MBA","0"},
		{"59","https://www.ljmu.ac.uk/study/courses/postgraduates/exhibition-studies-ma","Exhibition Studies MA","0"},
		{"60","https://www.ljmu.ac.uk/study/courses/postgraduates/fashion-innovation-and-realisation","Fashion Innovation and Realisation MA","0"},
		{"61","https://www.ljmu.ac.uk/study/courses/postgraduates/financial-management","Financial Management MSc","0"},
		{"62","https://www.ljmu.ac.uk/study/courses/postgraduates/fine-art","Fine Art MA","0"},
		{"63","https://www.ljmu.ac.uk/study/courses/postgraduates/forensic-anthropology","Forensic Anthropology MSc","0"},
		{"64","https://www.ljmu.ac.uk/study/courses/postgraduates/forensic-bioscience-msc","Forensic Bioscience MSc","0"},
		{"65","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-geography","Geography: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"66","https://www.ljmu.ac.uk/study/courses/postgraduates/global-crime-justice-and-security","Global Crime, Justice and Security Master of Laws (LLM) / MSc","0"},
		{"67","https://www.ljmu.ac.uk/study/courses/postgraduates/graduate-diploma-in-law-gdl","Graduate Diploma in Law (GDL) Graduate Diploma","0"},
		{"68","https://www.ljmu.ac.uk/study/courses/postgraduates/graphic-design-illustration","Graphic Design and Illustration MA","0"},
		{"69","https://www.ljmu.ac.uk/study/courses/postgraduates/health-psychology","Health Psychology MSc","0"},
		{"70","https://www.ljmu.ac.uk/study/courses/postgraduates/human-resource-management","Human Resource Management MA","0"},
		{"71","https://www.ljmu.ac.uk/study/courses/postgraduates/improving-access-to-psychological-therapies-in-primary-care","Improving Access to Psychological Therapies in Primary Care PgCert","0"},
		{"72","https://www.ljmu.ac.uk/study/courses/postgraduates/industrial-biotechnology","Industrial Biotechnology MSc, PgDip, PgCert","0"},
		{"73","https://www.ljmu.ac.uk/study/courses/postgraduates/integrated-building-information-management","Integrated Building Information Management (BIM) MSc","0"},
		{"74","https://www.ljmu.ac.uk/study/courses/postgraduates/international-and-transnational-policing","International and Transnational Policing","0"},
		{"75","https://www.ljmu.ac.uk/study/courses/postgraduates/international-approaches-to-early-childhood-education","International Approaches to Early Childhood Education","0"},
		{"76","https://www.ljmu.ac.uk/study/courses/postgraduates/international-business-and-management","International Business and Management","0"},
		{"77","https://www.ljmu.ac.uk/study/courses/postgraduates/international-business-corporate-and-finance-law","International Business Corporate and Finance Law LLM (Master of Laws)","0"},
		{"78","https://www.ljmu.ac.uk/study/courses/postgraduates/international-events-management","International Events Management MSc","0"},
		{"79","https://www.ljmu.ac.uk/study/courses/postgraduates/international-human-resource-management","International Human Resource Management MSc","0"},
		{"80","https://www.ljmu.ac.uk/study/courses/postgraduates/international-journalism","International Journalism MA","0"},
		{"81","https://www.ljmu.ac.uk/study/courses/postgraduates/international-news-journalism","International News Journalism MA","0"},
		{"82","https://www.ljmu.ac.uk/study/courses/postgraduates/international-public-health","International Public Health MSc, PgDip","0"},
		{"83","https://www.ljmu.ac.uk/study/courses/postgraduates/international-public-relations","International Public Relations *Subject to validation MSc","0"},
		{"84","https://www.ljmu.ac.uk/study/courses/postgraduates/international-tourism-management","International Tourism Management MSc","0"},
		{"85","https://www.ljmu.ac.uk/study/courses/postgraduates/international-transport-trade-and-logistics","International Transport, Trade and Logistics MSc","0"},
		{"86","https://www.ljmu.ac.uk/study/courses/postgraduates/legal-practice-course","Legal Practice Course LPC","0"},
		{"87","https://www.ljmu.ac.uk/study/courses/postgraduates/llm-legal-practice-lpc-bptc-conversion","Legal Practice LLM (LPC BPTC Conversion)","0"},
		{"88","https://www.ljmu.ac.uk/study/courses/postgraduates/liverpool-business-school-postgraduate-research","Liverpool Business School - Postgraduate Research Opportunities","0"},
		{"89","https://www.ljmu.ac.uk/study/courses/postgraduates/liverpool-school-of-art-and-design-postgraduate-research","Liverpool School of Art and Design - Postgraduate Research Opportunities","0"},
		{"90","https://www.ljmu.ac.uk/study/courses/postgraduates/liverpool-screen-school-postgraduate-research","Liverpool Screen School - Postgraduate Research Opportunities","0"},
		{"91","https://www.ljmu.ac.uk/study/courses/postgraduates/logistics-and-supply-chain-management","Logistics and Supply Chain Management","0"},
		{"92","https://www.ljmu.ac.uk/study/courses/postgraduates/ma-dance-practices","MA Dance Practices","0"},
		{"93","https://www.ljmu.ac.uk/study/courses/postgraduates/management-and-digital-business","Management and Digital Business MSc","0"},
		{"94","https://www.ljmu.ac.uk/study/courses/postgraduates/management","Management MSc","0"},
		{"95","https://www.ljmu.ac.uk/study/courses/postgraduates/manufacturing-engineering","Manufacturing Engineering MSc","0"},
		{"96","https://www.ljmu.ac.uk/study/courses/postgraduates/marine-and-offshore-engineering","Marine and Offshore Engineering MSc","0"},
		{"97","https://www.ljmu.ac.uk/study/courses/postgraduates/maritime-operations","Maritime Operations Management MSc","0"},
		{"98","https://www.ljmu.ac.uk/study/courses/postgraduates/mass-communications","Mass Communications MA","0"},
		{"99","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-mathematics","Mathematics: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"100","https://www.ljmu.ac.uk/study/courses/postgraduates/mechanical-engineering","Mechanical Engineering MSc","0"},
		{"101","https://www.ljmu.ac.uk/study/courses/postgraduates/microelectronic-system-design","Microelectronic System Design MSc","0"},
		{"102","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-modern-foreign-languages","Modern Foreign Languages: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"103","https://www.ljmu.ac.uk/study/courses/postgraduates/modern-history","Modern History MRes","0"},
		{"104","https://www.ljmu.ac.uk/study/courses/postgraduates/observational-astrophysics","Observational Astrophysics (Distance Learning) MSc","0"},
		{"105","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-performing-arts","Performing Arts (Dance): Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"106","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-performing-arts-drama","Performing Arts (Drama): Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"107","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-primary-key-stages-1-and-2-with-recommendation-for-qts","PGDE Primary Key Stage 1/2 (5-11 years) with Qualified Teacher Status (QTS)","0"},
		{"108","https://www.ljmu.ac.uk/study/courses/postgraduates/pharmaceutical-manufacture-and-quality-control","Pharmaceutical Manufacture and Quality Control MSc, PgDip, PgCert","0"},
		{"109","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-physical-education","Physical Education: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"110","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-physics","Physics: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"111","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-physics-with-mathematics","Physics with Mathematics: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"112","https://www.ljmu.ac.uk/study/courses/postgraduates/policing-and-criminal-investigation","Policing and Criminal Investigation *subject to validation","0"},
		{"113","https://www.ljmu.ac.uk/study/courses/postgraduates/policing-and-cyber-crime","Policing and Cyber-Crime *subject to validation","0"},
		{"114","https://www.ljmu.ac.uk/study/courses/postgraduates/port-management","Port Management MSc","0"},
		{"115","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-primary-foundation-key-stage-1-with-qts","Primary Foundation Stage/Key Stage 1 (3-7 years) with QTS PGDE","0"},
		{"116","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-primary-with-mathematics","Primary with Mathematics Specialism (5-11 years) with QTS PGDE","0"},
		{"117","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-primary-with-physical-education","Primary with Physical Education Specialism (5-11 years) and QTS PGDE","0"},
		{"118","https://www.ljmu.ac.uk/study/courses/postgraduates/primate-behaviour-and-conservation","Primate Behaviour and Conservation MSc","0"},
		{"119","https://www.ljmu.ac.uk/study/courses/postgraduates/professional-doctorate-in-applied-sport-and-exercise-science","Professional Doctorate in Applied Sport and Exercise Science","0"},
		{"120","https://www.ljmu.ac.uk/study/courses/postgraduates/health-psychology-dpsych","Professional Doctorate in Health Psychology DHealthPsych","0"},
		{"121","https://www.ljmu.ac.uk/study/courses/postgraduates/professional-doctorate-in-applied-sport-and-exercise-psychology","Professional Doctorate in Sport and Exercise Psychology","0"},
		{"122","https://www.ljmu.ac.uk/study/courses/postgraduates/project-management","Project Management MSc","0"},
		{"123","https://www.ljmu.ac.uk/study/courses/postgraduates/public-health-addictions","Public Health (Addictions) MSc, PgDip, PgCert","0"},
		{"124","https://www.ljmu.ac.uk/study/courses/postgraduates/public-health","Public Health MSc, PgDip, PgCert","0"},
		{"125","https://www.ljmu.ac.uk/study/courses/postgraduates/public-health-nutrition","Public Health Nutrition *Subject to validation MSc, PgDip, PgCert","0"},
		{"126","https://www.ljmu.ac.uk/study/courses/postgraduates/public-relations","Public Relations *subject to validation MSc","0"},
		{"127","https://www.ljmu.ac.uk/study/courses/postgraduates/quantity-surveying-and-commercial-management","Quantity Surveying and Commercial Management MSc","0"},
		{"128","https://www.ljmu.ac.uk/study/courses/postgraduates/commercial-property-management","Real Estate MSc","0"},
		{"129","https://www.ljmu.ac.uk/study/courses/postgraduates/pgde-religious-education","Religious Education: Secondary with Qualified Teacher Status (QTS) PGDE","0"},
		{"130","https://www.ljmu.ac.uk/study/courses/postgraduates/school-direct-initial-teacher-training","School Direct Initial Teacher Training (salaried) with QTS PgCert","0"},
		{"131","https://www.ljmu.ac.uk/study/courses/postgraduates/school-direct-initial-teacher-training-primary","School Direct Primary Initial Teacher Training with QTS","0"},
		{"132","https://www.ljmu.ac.uk/study/courses/postgraduates/school-direct-initial-teacher-training-secondary","School Direct Secondary Initial Teacher Training with QTS","0"},
		{"133","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-education-postgraduate-research","School of Education Postgraduate Research Opportunities","0"},
		{"134","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-humanities-and-social-science-postgraduate-research","School of Humanities and Social Science Postgraduate Research Opportunities","0"},
		{"135","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-law-postgraduate-research","School of Law - Postgraduate Research Opportunities","0"},
		{"136","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-natural-sciences-and-psychology-postgraduate-research","School of Natural Sciences and Psychology - Postgraduate Research Opportunities","0"},
		{"137","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-nursing-and-allied-health-postgraduate-research","School of Nursing and Allied Health - Postgraduate Research Opportunities","0"},
		{"138","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-pharmacy-and-biomolecular-sciences-postgraduate-research","School of Pharmacy and Biomolecular Sciences - Postgraduate Research Opportunities","0"},
		{"139","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-sport-and-exercise-sciences-postgraduate-research","School of Sport and Exercise Sciences - Postgraduate Research","0"},
		{"140","https://www.ljmu.ac.uk/study/courses/postgraduates/school-of-sport-studies-leisure-and-nutrition-postgraduate-research","School of Sport Studies, Leisure and Nutrition Postgraduate Research Opportunities","0"},
		{"141","https://www.ljmu.ac.uk/study/courses/postgraduates/screenwriting","Screenwriting MA","0"},
		{"142","https://www.ljmu.ac.uk/study/courses/postgraduates/social-work","Social Work MA","0"},
		{"143","https://www.ljmu.ac.uk/study/courses/postgraduates/software-engineering","Software Engineering MSc","0"},
		{"144","https://www.ljmu.ac.uk/study/courses/postgraduates/special-educational-needs","Special Educational Needs MA, PgDip, PgCert","0"},
		{"145","https://www.ljmu.ac.uk/study/courses/postgraduates/specialist-community-practitioner-community-childrens-nursing","Specialist Community Practitioner Children's Nursing PgDip/BSc (Hons)","0"},
		{"146","https://www.ljmu.ac.uk/study/courses/postgraduates/specialist-community-practitioner-district-nursing","Specialist Community Practitioner/ District Nursing BSc (Hons)/PgDip","0"},
		{"147","https://www.ljmu.ac.uk/study/courses/postgraduates/specialist-community-practitioner-general-practice-nurse","Specialist Community Practitioner General Practice Nurse BSc (Hons)/PgDip","0"},
		{"148","https://www.ljmu.ac.uk/study/courses/postgraduates/specialist-community-public-health-nurse-health-visiting-school-health-nursing","Specialist Community Public Health Nurse (Health Visiting / School Nursing) BSc (Hons)/PgDip","0"},
		{"149","https://www.ljmu.ac.uk/study/courses/postgraduates/sport-and-clinical-biomechanics","Sport and Clinical Biomechanics MSc","0"},
		{"150","https://www.ljmu.ac.uk/study/courses/postgraduates/sport-and-exercise-physiology","Sport and Exercise Physiology MSc, PgDip, PgCert","0"},
		{"151","https://www.ljmu.ac.uk/study/courses/postgraduates/sport-coaching","Sport Coaching MSc","0"},
		{"152","https://www.ljmu.ac.uk/study/courses/postgraduates/sports-nutrition","Sport Nutrition MSc","0"},
		{"153","https://www.ljmu.ac.uk/study/courses/postgraduates/sports-psychology","Sports Psychology MSc","0"},
		{"154","https://www.ljmu.ac.uk/study/courses/postgraduates/telecommunications-engineering","Telecommunications Engineering MSc","0"},
		{"155","https://www.ljmu.ac.uk/study/courses/postgraduates/urban-design","Urban Design MA","0"},
		{"156","https://www.ljmu.ac.uk/study/courses/postgraduates/virology","Virology MSc","0"},
		{"157","https://www.ljmu.ac.uk/study/courses/postgraduates/water-energy-and-the-environment","Water, Energy and the Environment MSc, PgDip, PgCert","0"},
		{"158","https://www.ljmu.ac.uk/study/courses/postgraduates/wildlife-conservation-and-uav-technology","Wildlife Conservation and UAV Technology MSc","0"},
		{"159","https://www.ljmu.ac.uk/study/courses/postgraduates/writing","Writing MA","0"}
	};
	
}

