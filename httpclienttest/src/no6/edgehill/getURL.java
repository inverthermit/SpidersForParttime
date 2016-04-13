package no6.edgehill;

import java.io.File;
import java.io.FileInputStream;

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

		FileInputStream fis=new FileInputStream(new File("./edgehillAll.html"));
		byte[] b=new byte[fis.available()];
        fis.read(b);
       fis.close();
        String htmls=new String(b);
       int count=1;
	    Parser	parser=Parser.createParser(htmls, "utf-8");
   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
                   new HasAttributeFilter("href"));
   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
   	    for(int i=0;i<nodes4.size();i++)
   	    {
   	    	LinkTag link=(LinkTag)nodes4.elementAt(i);
   	    	if(!link.getAttribute("href").contains("#"))
   	    	{
   	    		System.out.println("{\""+count+"\",\""+link.getAttribute("href")+"\",\""+html2Str(link.toHtml().replaceAll("<span[\\s\\S]*/span>",""))+"\",\"0\"},");
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
	
	public static String[][] AllData={
		{"1","https://www.edgehill.ac.uk/courses/accountancy/","Accountancy - BSc (Hons)","0"},
		{"2","https://www.edgehill.ac.uk/courses/accounting-finance-and-management/","Accounting, Finance and Management - MSc","0"},
		{"3","https://www.edgehill.ac.uk/courses/advanced-fertility-practice/","Advanced Fertility Practice - MSc","0"},
		{"4","https://www.edgehill.ac.uk/courses/advanced-practice/","Advanced Practice - MSc","0"},
		{"5","https://www.edgehill.ac.uk/courses/advertising/","Advertising - BA (Hons)","0"},
		{"6","https://www.edgehill.ac.uk/courses/animation/","Animation - BA (Hons)","0"},
		{"7","https://www.edgehill.ac.uk/courses/applied-clinical-nutrition/","Applied Clinical Nutrition - MSc","0"},
		{"8","https://www.edgehill.ac.uk/courses/behaviour-analysis-and-intervention/","Behaviour Analysis and Intervention - BSc (Hons)","0"},
		{"9","https://www.edgehill.ac.uk/courses/biology-mres/","Biology - MRes","0"},
		{"10","https://www.edgehill.ac.uk/courses/biology/","Biology - BSc (Hons)","0"},
		{"11","https://www.edgehill.ac.uk/courses/biology-ske/","Biology - Subject Knowledge Enhancement","0"},
		{"12","https://www.edgehill.ac.uk/courses/master-of-business-administration/","Business Administration - MBA","0"},
		{"13","https://www.edgehill.ac.uk/courses/master-of-business-administration-finance/","Business Administration (Finance) - MBA","0"},
		{"14","https://www.edgehill.ac.uk/courses/master-of-business-administration-hrm/","Business Administration (Human Resource Management) - MBA","0"},
		{"15","https://www.edgehill.ac.uk/courses/master-of-business-administration-information-technology/","Business Administration (Information Technology) - MBA","0"},
		{"16","https://www.edgehill.ac.uk/courses/master-of-business-administration-marketing/","Business Administration (Marketing) - MBA","0"},
		{"17","https://www.edgehill.ac.uk/courses/business-and-economics/","Business and Economics - BSc (Hons)","0"},
		{"18","https://www.edgehill.ac.uk/courses/business-and-management/","Business and Management - BSc (Hons)","0"},
		{"19","https://www.edgehill.ac.uk/courses/business-and-management-fda/","Business and Management - FdA","0"},
		{"20","https://www.edgehill.ac.uk/courses/business-and-management-enterprise/","Business and Management (Enterprise) - FdA","0"},
		{"21","https://www.edgehill.ac.uk/courses/business-and-management-part-time/","Business and Management (Part Time) - BSc (Hons)","0"},
		{"22","https://www.edgehill.ac.uk/courses/business-and-management-with-accounting-and-finance/","Business and Management with Accounting and Finance - BSc (Hons)","0"},
		{"23","https://www.edgehill.ac.uk/courses/business-and-management-with-human-resource-management/","Business and Management with Human Resource Management - BSc (Hons)","0"},
		{"24","https://www.edgehill.ac.uk/courses/business-and-management-with-leisure-and-tourism/","Business and Management with Leisure and Tourism - BSc (Hons)","0"},
		{"25","https://www.edgehill.ac.uk/courses/business-and-management-with-marketing/","Business and Management with Marketing - BSc (Hons)","0"},
		{"26","https://www.edgehill.ac.uk/courses/business-innovation-and-enterprise/","Business Innovation and Enterprise - BSc (Hons)","0"},
		{"27","https://www.edgehill.ac.uk/courses/cardiothoracic-practice/","Cardiothoracic Practice - BSc (Hons)","0"},
		{"28","https://www.edgehill.ac.uk/courses/chemistry/","Chemistry - Subject Knowledge Enhancement","0"},
		{"29","https://www.edgehill.ac.uk/courses/child-health-and-wellbeing/","Child Health and Wellbeing - BSc (Hons)","0"},
		{"30","https://www.edgehill.ac.uk/courses/childhood-youth-studies-and-criminology/","Childhood &amp; Youth Studies and Criminology - BA (Hons)","0"},
		{"31","https://www.edgehill.ac.uk/courses/childhood-youth-studies-and-sociology/","Childhood &amp; Youth Studies and Sociology - BA (Hons)","0"},
		{"32","https://www.edgehill.ac.uk/courses/childhood-and-youth-mres/","Childhood and Youth - MRes","0"},
		{"33","https://www.edgehill.ac.uk/courses/childhood-and-youth-studies/","Childhood and Youth Studies - BA (Hons)","0"},
		{"34","https://www.edgehill.ac.uk/courses/children-and-young-people-s-learning-and-development/","Children and Young People’s Learning and Development - BA (Hons)","0"},
		{"35","https://www.edgehill.ac.uk/courses/clinical-and-health-research/","Clinical and Health Research - MRes","0"},
		{"36","https://www.edgehill.ac.uk/courses/clinical-and-professional-cardiothoracic-care/","Clinical and Professional Cardiothoracic Care - University Advanced Certificate","0"},
		{"37","https://www.edgehill.ac.uk/courses/clinical-and-professional-palliative-practice/","Clinical and Professional Child and Younger Person Palliative and End of Life Care Practice - BSc (Hons)","0"},
		{"38","https://www.edgehill.ac.uk/courses/clinical-and-professional-midwifery-practice/","Clinical and Professional Midwifery Practice - BSc (Hons)","0"},
		{"39","https://www.edgehill.ac.uk/courses/clinical-and-professional-paramedic-practice/","Clinical and Professional Paramedic Practice - BSc (Hons)","0"},
		{"40","https://www.edgehill.ac.uk/courses/clinical-and-professional-perioperative-practice/","Clinical and Professional Perioperative Practice - BSc (Hons)","0"},
		{"41","https://www.edgehill.ac.uk/courses/clinical-and-professional-practice/","Clinical and Professional Practice - BSc (Hons)","0"},
		{"42","https://www.edgehill.ac.uk/courses/clinical-education/","Clinical Education - MA","0"},
		{"43","https://www.edgehill.ac.uk/courses/clinical-reproductive-medicine/","Clinical Reproductive Medicine - MSc","0"},
		{"44","https://www.edgehill.ac.uk/courses/coach-education-mres/","Coach Education - MRes","0"},
		{"45","https://www.edgehill.ac.uk/courses/cognitive-behavioural-psychotherapy/","Cognitive Behavioural Psychotherapy - MSc","0"},
		{"46","https://www.edgehill.ac.uk/courses/complementary-therapies-bsc/","Complementary Therapies - BSc (Hons)","0"},
		{"47","https://www.edgehill.ac.uk/courses/computer-science/","Computer Science - Subject Knowledge Enhancement","0"},
		{"48","https://www.edgehill.ac.uk/courses/computer-security-and-networks/","Computer Security and Networks - MComp (Hons)","0"},
		{"49","https://www.edgehill.ac.uk/courses/computing-mres/","Computing - MRes","0"},
		{"50","https://www.edgehill.ac.uk/courses/computing-mcomp/","Computing - MComp (Hons)","0"},
		{"51","https://www.edgehill.ac.uk/courses/computing/","Computing - BSc (Hons)","0"},
		{"52","https://www.edgehill.ac.uk/courses/computing-msc/","Computing - MSc","0"},
		{"53","https://www.edgehill.ac.uk/courses/computing-application-development/","Computing (Application Development) - BSc (Hons)","0"},
		{"54","https://www.edgehill.ac.uk/courses/computing-games-programming/","Computing (Games Programming) - BSc (Hons)","0"},
		{"55","https://www.edgehill.ac.uk/courses/computing-information-systems/","Computing (Information Systems) - BSc (Hons)","0"},
		{"56","https://www.edgehill.ac.uk/courses/computing-networking-security-and-forensics/","Computing (Networking, Security and Forensics) - BSc (Hons)","0"},
		{"57","https://www.edgehill.ac.uk/courses/computing-part-time/","Computing (Part Time) - BSc (Hons)","0"},
		{"58","https://www.edgehill.ac.uk/courses/computing-systems-and-software/","Computing (Systems and Software) - BSc (Hons)","0"},
		{"59","https://www.edgehill.ac.uk/courses/conservation-management/","Conservation Management - MSc","0"},
		{"60","https://www.edgehill.ac.uk/courses/counselling-ba/","Counselling - BA (Hons)","0"},
		{"61","https://www.edgehill.ac.uk/courses/counselling-and-psychotherapy/","Counselling and Psychotherapy - BA (Hons)","0"},
		{"62","https://www.edgehill.ac.uk/courses/creative-and-cultural-education/","Creative and Cultural Education - PGCert","0"},
		{"63","https://www.edgehill.ac.uk/courses/creative-writing/","Creative Writing - BA (Hons)","0"},
		{"64","https://www.edgehill.ac.uk/courses/creative-writing-ma/","Creative Writing - MA","0"},
		{"65","https://www.edgehill.ac.uk/courses/creative-writing-and-drama/","Creative Writing and Drama - BA (Hons)","0"},
		{"66","https://www.edgehill.ac.uk/courses/creative-writing-and-english-literature/","Creative Writing and English Literature - BA (Hons)","0"},
		{"67","https://www.edgehill.ac.uk/courses/creative-writing-and-film-studies/","Creative Writing and Film Studies - BA (Hons)","0"},
		{"68","https://www.edgehill.ac.uk/courses/criminology/","Criminology - MRes","0"},
		{"69","https://www.edgehill.ac.uk/courses/criminology-ba/","Criminology - BA (Hons)","0"},
		{"70","https://www.edgehill.ac.uk/courses/criminology-and-law/","Criminology and Law - BA (Hons)","0"},
		{"71","https://www.edgehill.ac.uk/courses/criminology-and-psychology/","Criminology and Psychology - BA (Hons)","0"},
		{"72","https://www.edgehill.ac.uk/courses/criminology-and-sociology/","Criminology and Sociology - BA (Hons)","0"},
		{"73","https://www.edgehill.ac.uk/courses/critical-management-research/","Critical Management Research - MRes","0"},
		{"74","https://www.edgehill.ac.uk/courses/critical-screen-practice/","Critical Screen Practice - MA","0"},
		{"75","https://www.edgehill.ac.uk/courses/cyber-security/","Cyber Security - MSc","0"},
		{"76","https://www.edgehill.ac.uk/courses/dance/","Dance - BA (Hons)","0"},
		{"77","https://www.edgehill.ac.uk/courses/dance-and-drama/","Dance and Drama - BA (Hons)","0"},
		{"78","https://www.edgehill.ac.uk/courses/dental-implantology/","Dental Implantology - MSc","0"},
		{"79","https://www.edgehill.ac.uk/courses/drama/","Drama - BA (Hons)","0"},
		{"80","https://www.edgehill.ac.uk/courses/drama-and-english-literature/","Drama and English Literature - BA (Hons)","0"},
		{"81","https://www.edgehill.ac.uk/courses/drama-and-film-studies/","Drama and Film Studies - BA (Hons)","0"},
		{"82","https://www.edgehill.ac.uk/courses/early-childhood-studies/","Early Childhood Studies - BA (Hons)","0"},
		{"83","https://www.edgehill.ac.uk/courses/early-childhood-studies-and-sociology/","Early Childhood Studies and Sociology - BA (Hons)","0"},
		{"84","https://www.edgehill.ac.uk/courses/early-mathematics-intervention/","Early Mathematics Intervention - PGCert / MA","0"},
		{"85","https://www.edgehill.ac.uk/courses/early-years-education/","Early Years Education - BA (Hons)","0"},
		{"86","https://www.edgehill.ac.uk/courses/early-years-education-with-qts/","Early Years Education with QTS - BA (Hons)","0"},
		{"87","https://www.edgehill.ac.uk/courses/pgce-early-years/","Early Years Education with QTS - PGCE","0"},
		{"88","https://www.edgehill.ac.uk/courses/early-years-leadership-fda/","Early Years Leadership - FdA","0"},
		{"89","https://www.edgehill.ac.uk/courses/early-years-leadership/","Early Years Leadership - BA (Hons)","0"},
		{"90","https://www.edgehill.ac.uk/courses/early-years-practice-ba/","Early Years Practice - BA (Hons)","0"},
		{"91","https://www.edgehill.ac.uk/courses/early-years-practice/","Early Years Practice - FdA","0"},
		{"92","https://www.edgehill.ac.uk/courses/early-years-teacher-status/","Early Years Teacher Status - Early Years Initial Teacher Training (EYITT)","0"},
		{"93","https://www.edgehill.ac.uk/courses/ecology/","Ecology - BSc (Hons)","0"},
		{"94","https://www.edgehill.ac.uk/courses/education/","Education - MA","0"},
		{"95","https://www.edgehill.ac.uk/courses/educational-psychology/","Educational Psychology - BSc (Hons)","0"},
		{"96","https://www.edgehill.ac.uk/courses/english/","English - BA (Hons)","0"},
		{"97","https://www.edgehill.ac.uk/courses/english-ma/","English - MA","0"},
		{"98","https://www.edgehill.ac.uk/courses/english-and-film-studies/","English and Film Studies - BA (Hons)","0"},
		{"99","https://www.edgehill.ac.uk/courses/pre-sessional-english/","English Courses - Pre-Sessional","0"},
		{"100","https://www.edgehill.ac.uk/courses/english-language/","English Language - BA (Hons)","0"},
		{"101","https://www.edgehill.ac.uk/courses/english-language-mres/","English Language - MRes","0"},
		{"102","https://www.edgehill.ac.uk/courses/english-language-with-creative-writing/","English Language with Creative Writing - BA (Hons)","0"},
		{"103","https://www.edgehill.ac.uk/courses/english-literature-mres/","English Literature - MRes","0"},
		{"104","https://www.edgehill.ac.uk/courses/english-literature/","English Literature - BA (Hons)","0"},
		{"105","https://www.edgehill.ac.uk/courses/english-literature-and-history/","English Literature and History - BA (Hons)","0"},
		{"106","https://www.edgehill.ac.uk/courses/english-literature-with-creative-writing/","English Literature with Creative Writing - BA (Hons)","0"},
		{"107","https://www.edgehill.ac.uk/courses/english-with-creative-writing/","English with Creative Writing - BA (Hons)","0"},
		{"108","https://www.edgehill.ac.uk/courses/environmental-science/","Environmental Science - BSc (Hons)","0"},
		{"109","https://www.edgehill.ac.uk/courses/family-and-community-studies/","Family and Community Studies - BSc (Hons)","0"},
		{"110","https://www.edgehill.ac.uk/courses/fastrack-access-to-higher-education/","Fastrack: Access to Higher Education - University Foundation Certificate","0"},
		{"111","https://www.edgehill.ac.uk/courses/fastrack-access-to-primary-initial-teacher-training/","Fastrack: Access to Primary Initial Teacher Training in Higher Education - University Foundation Certificate","0"},
		{"112","https://www.edgehill.ac.uk/courses/fastrack-access-to-secondary-initial-teacher-training/","Fastrack: Access to Secondary Initial Teacher Training in Higher Education - University Foundation Certificate","0"},
		{"113","https://www.edgehill.ac.uk/courses/film-and-media/","Film and Media - MA","0"},
		{"114","https://www.edgehill.ac.uk/courses/film-and-television-production/","Film and Television Production - BA (Hons)","0"},
		{"115","https://www.edgehill.ac.uk/courses/film-studies-mres/","Film Studies - MRes","0"},
		{"116","https://www.edgehill.ac.uk/courses/film-studies/","Film Studies - BA (Hons)","0"},
		{"117","https://www.edgehill.ac.uk/courses/film-studies-with-film-production/","Film Studies with Film Production - BA (Hons)","0"},
		{"118","https://www.edgehill.ac.uk/courses/football-rehabilitation/","Football Rehabilitation - MSc","0"},
		{"119","https://www.edgehill.ac.uk/courses/uhd-further-education-and-training/","Further Education and Training - University Higher Diploma","0"},
		{"120","https://www.edgehill.ac.uk/courses/further-education-and-training-pgce/","Further Education and Training - PGCE","0"},
		{"121","https://www.edgehill.ac.uk/courses/genetics/","Genetics - BSc (Hons)","0"},
		{"122","https://www.edgehill.ac.uk/courses/geography-ba/","Geography - BA (Hons)","0"},
		{"123","https://www.edgehill.ac.uk/courses/geography-bsc/","Geography - BSc (Hons)","0"},
		{"124","https://www.edgehill.ac.uk/courses/geography-ske/","Geography - Subject Knowledge Enhancement","0"},
		{"125","https://www.edgehill.ac.uk/courses/geology-with-physical-geography/","Geology with Physical Geography - BSc (Hons)","0"},
		{"126","https://www.edgehill.ac.uk/courses/health-and-social-wellbeing/","Health and Social Wellbeing - BA (Hons)","0"},
		{"127","https://www.edgehill.ac.uk/courses/health-science/","Health Science - BSc (Hons)","0"},
		{"128","https://www.edgehill.ac.uk/courses/history-mres/","History - MRes","0"},
		{"129","https://www.edgehill.ac.uk/courses/history/","History - BA (Hons)","0"},
		{"130","https://www.edgehill.ac.uk/courses/history-and-culture/","History and Culture - MA","0"},
		{"131","https://www.edgehill.ac.uk/courses/history-with-politics/","History with Politics - BA (Hons)","0"},
		{"132","https://www.edgehill.ac.uk/courses/human-biology/","Human Biology - BSc (Hons)","0"},
		{"133","https://www.edgehill.ac.uk/courses/human-geography/","Human Geography - BA (Hons)","0"},
		{"134","https://www.edgehill.ac.uk/courses/human-geography-with-information-technology/","Human Geography with Information Technology - BA (Hons)","0"},
		{"135","https://www.edgehill.ac.uk/courses/human-geography-with-management/","Human Geography with Management - BA (Hons)","0"},
		{"136","https://www.edgehill.ac.uk/courses/humanities-mres/","Humanities - MRes","0"},
		{"137","https://www.edgehill.ac.uk/courses/information-security-and-it-management/","Information Security and IT Management - MSc","0"},
		{"138","https://www.edgehill.ac.uk/courses/information-technology-mobile-computing/","Information Technology (Mobile Computing) - FdSc","0"},
		{"139","https://www.edgehill.ac.uk/courses/information-technology-management-for-business/","Information Technology Management for Business - BSc (Hons)","0"},
		{"140","https://www.edgehill.ac.uk/courses/integrated-children-and-young-people-s-practice/","Integrated Children and Young People’s Practice - BSc (Hons)","0"},
		{"141","https://www.edgehill.ac.uk/courses/international-business/","International Business - BSc (Hons)","0"},
		{"142","https://www.edgehill.ac.uk/courses/international-business-and-commercial-law/","International Business and Commercial Law - LLM","0"},
		{"143","https://www.edgehill.ac.uk/courses/international-foundation-programme/","International Foundation Programme - IFP","0"},
		{"144","https://www.edgehill.ac.uk/courses/international-justice-and-human-rights-law/","International Justice and Human Rights Law - LLM","0"},
		{"145","https://www.edgehill.ac.uk/courses/international-midwifery-studies/","International Midwifery Studies - MSc","0"},
		{"146","https://www.edgehill.ac.uk/courses/international-midwifery/","International Midwifery Studies - BSc (Hons)","0"},
		{"147","https://www.edgehill.ac.uk/courses/law/","Law - LLB (Hons)","0"},
		{"148","https://www.edgehill.ac.uk/courses/law-part-time/","Law (Part Time) - LLB (Hons)","0"},
		{"149","https://www.edgehill.ac.uk/courses/law-with-criminology/","Law with Criminology - LLB (Hons)","0"},
		{"150","https://www.edgehill.ac.uk/courses/law-with-criminology-part-time/","Law with Criminology (Part Time) - LLB (Hons)","0"},
		{"151","https://www.edgehill.ac.uk/courses/law-with-politics/","Law with Politics - LLB (Hons)","0"},
		{"152","https://www.edgehill.ac.uk/courses/law-with-politics-part-time/","Law with Politics (Part Time) - LLB (Hons)","0"},
		{"153","https://www.edgehill.ac.uk/courses/leadership-and-management-development/","Leadership and Management Development - MSc","0"},
		{"154","https://www.edgehill.ac.uk/courses/leadership-development/","Leadership Development - MSc","0"},
		{"155","https://www.edgehill.ac.uk/courses/making-performance/","Making Performance - MA","0"},
		{"156","https://www.edgehill.ac.uk/courses/management-and-entrepreneurship/","Management and Entrepreneurship - MA","0"},
		{"157","https://www.edgehill.ac.uk/courses/marketing/","Marketing - BSc (Hons)","0"},
		{"158","https://www.edgehill.ac.uk/courses/marketing-communications-and-branding/","Marketing Communications and Branding - MA","0"},
		{"159","https://www.edgehill.ac.uk/courses/marketing-with-advertising/","Marketing with Advertising - BSc (Hons)","0"},
		{"160","https://www.edgehill.ac.uk/courses/marketing-with-digital-communications/","Marketing with Digital Communications - BSc (Hons)","0"},
		{"161","https://www.edgehill.ac.uk/courses/marketing-with-public-relations/","Marketing with Public Relations - BSc (Hons)","0"},
		{"162","https://www.edgehill.ac.uk/courses/mathematics/","Mathematics - Subject Knowledge Enhancement","0"},
		{"163","https://www.edgehill.ac.uk/courses/media-mres/","Media - MRes","0"},
		{"164","https://www.edgehill.ac.uk/courses/media-management/","Media Management - MSc","0"},
		{"165","https://www.edgehill.ac.uk/courses/media-film-and-television/","Media, Film and Television - BA (Hons)","0"},
		{"166","https://www.edgehill.ac.uk/courses/media-music-and-sound/","Media, Music and Sound - BA (Hons)","0"},
		{"167","https://www.edgehill.ac.uk/courses/medical-leadership/","Medical Leadership - PGCert","0"},
		{"168","https://www.edgehill.ac.uk/courses/mental-health-law-and-ethics/","Mental Health Law and Ethics - PGCert","0"},
		{"169","https://www.edgehill.ac.uk/courses/midwifery/","Midwifery - BSc (Hons)","0"},
		{"170","https://www.edgehill.ac.uk/courses/motion-graphics/","Motion Graphics - BA (Hons)","0"},
		{"171","https://www.edgehill.ac.uk/courses/music/","Music - BA (Hons)","0"},
		{"172","https://www.edgehill.ac.uk/courses/music-production/","Music Production - BA (Hons)","0"},
		{"173","https://www.edgehill.ac.uk/courses/musical-theatre/","Musical Theatre - BA (Hons)","0"},
		{"174","https://www.edgehill.ac.uk/courses/nursing-adult/","Nursing (Adult) - BSc (Hons)","0"},
		{"175","https://www.edgehill.ac.uk/courses/nursing-children/","Nursing (Children’s) - BSc (Hons)","0"},
		{"176","https://www.edgehill.ac.uk/courses/nursing-learning-disabilities/","Nursing (Learning Disabilities) - BSc (Hons)","0"},
		{"177","https://www.edgehill.ac.uk/courses/nursing-mental-health/","Nursing (Mental Health) - BSc (Hons)","0"},
		{"178","https://www.edgehill.ac.uk/courses/nutrition-and-health/","Nutrition and Health - BSc (Hons)","0"},
		{"179","https://www.edgehill.ac.uk/courses/operating-department-practice-bsc/","Operating Department Practice - BSc (Hons)","0"},
		{"180","https://www.edgehill.ac.uk/courses/paramedic-practice/","Paramedic Practice - DipHE","0"},
		{"181","https://www.edgehill.ac.uk/courses/performing-arts/","Performing Arts - BA (Hons)","0"},
		{"182","https://www.edgehill.ac.uk/courses/physical-education-and-school-sport-mres/","Physical Education and School Sport - MRes","0"},
		{"183","https://www.edgehill.ac.uk/courses/physical-education-and-school-sport/","Physical Education and School Sport - BA (Hons)","0"},
		{"184","https://www.edgehill.ac.uk/courses/physical-geography/","Physical Geography - BSc (Hons)","0"},
		{"185","https://www.edgehill.ac.uk/courses/physical-geography-mres/","Physical Geography - MRes","0"},
		{"186","https://www.edgehill.ac.uk/courses/physical-geography-and-geology/","Physical Geography and Geology - BSc (Hons)","0"},
		{"187","https://www.edgehill.ac.uk/courses/physical-geography-with-biology/","Physical Geography with Biology - BSc (Hons)","0"},
		{"188","https://www.edgehill.ac.uk/courses/physical-geography-with-geological-science/","Physical Geography with Geological Science - BSc (Hons)","0"},
		{"189","https://www.edgehill.ac.uk/courses/physical-geography-with-information-technology/","Physical Geography with Information Technology - BSc (Hons)","0"},
		{"190","https://www.edgehill.ac.uk/courses/physical-geography-with-management/","Physical Geography with Management - BSc (Hons)","0"},
		{"191","https://www.edgehill.ac.uk/courses/physics/","Physics - Subject Knowledge Enhancement","0"},
		{"192","https://www.edgehill.ac.uk/courses/policing/","Policing - BA (Hons)","0"},
		{"193","https://www.edgehill.ac.uk/courses/popular-culture/","Popular Culture - MA","0"},
		{"194","https://www.edgehill.ac.uk/courses/popular-music/","Popular Music - BA (Hons)","0"},
		{"195","https://www.edgehill.ac.uk/courses/pre-masters-programme/","Pre-Masters Programme - Graduate Diploma","0"},
		{"196","https://www.edgehill.ac.uk/courses/primary-education-with-qts/","Primary Education with QTS - BA (Hons)","0"},
		{"197","https://www.edgehill.ac.uk/courses/pgce-primary/","Primary Education with QTS - PGCE","0"},
		{"198","https://www.edgehill.ac.uk/courses/primary-education-with-qts-school-based-route/","Primary Education with QTS (School Based Route) - BA (Hons)","0"},
		{"199","https://www.edgehill.ac.uk/courses/pgce-primary-mathematics-specialist/","Primary Mathematics Specialist with QTS - PGCE","0"},
		{"200","https://www.edgehill.ac.uk/courses/primary-physical-education-specialist/","Primary Physical Education (PE) Specialist with QTS - PGCE","0"},
		{"201","https://www.edgehill.ac.uk/courses/professional-clinical-practice/","Professional Clinical Practice - MSc","0"},
		{"202","https://www.edgehill.ac.uk/courses/professional-development-ba/","Professional Development - BA (Hons)","0"},
		{"203","https://www.edgehill.ac.uk/courses/professional-development/","Professional Development - FdA","0"},
		{"204","https://www.edgehill.ac.uk/courses/psychology-msc/","Psychology - MSc","0"},
		{"205","https://www.edgehill.ac.uk/courses/psychology/","Psychology - BSc (Hons)","0"},
		{"206","https://www.edgehill.ac.uk/courses/psychology-and-criminology/","Psychology and Criminology - BSc (Hons)","0"},
		{"207","https://www.edgehill.ac.uk/courses/psychosocial-analysis-of-offending-behaviour/","Psychosocial Analysis of Offending Behaviour - BSc (Hons)","0"},
		{"208","https://www.edgehill.ac.uk/courses/public-health-nutrition/","Public Health Nutrition - MSc","0"},
		{"209","https://www.edgehill.ac.uk/courses/public-relations/","Public Relations - BA (Hons)","0"},
		{"210","https://www.edgehill.ac.uk/courses/public-relations-with-politics/","Public Relations with Politics - BA (Hons)","0"},
		{"211","https://www.edgehill.ac.uk/courses/research/","Research - LLM","0"},
		{"212","https://www.edgehill.ac.uk/courses/pgce-secondary-business-education/","Secondary Business Education (Age Phase 14-19) with QTS - PGCE","0"},
		{"213","https://www.edgehill.ac.uk/courses/pgce-secondary-computer-science-and-information-technology/","Secondary Computer Science and Information Technology Education (Age Phase 11-16) with QTS - PGCE","0"},
		{"214","https://www.edgehill.ac.uk/courses/secondary-computer-science-and-information-technology-education-with-qts/","Secondary Computer Science and Information Technology Education with QTS - BSc (Hons)","0"},
		{"215","https://www.edgehill.ac.uk/courses/pgce-secondary-design-and-technology/","Secondary Design and Technology (Age Phase 11-16) with QTS - PGCE","0"},
		{"216","https://www.edgehill.ac.uk/courses/secondary-design-and-technology-education-with-qts/","Secondary Design and Technology Education with QTS - BSc (Hons)","0"},
		{"217","https://www.edgehill.ac.uk/courses/pgce-secondary-english/","Secondary English (Age Phase 11-16) with QTS - PGCE","0"},
		{"218","https://www.edgehill.ac.uk/courses/secondary-english-education-with-qts/","Secondary English Education with QTS - BA (Hons)","0"},
		{"219","https://www.edgehill.ac.uk/courses/pgce-secondary-geography/","Secondary Geography (Age Phase 11-16) with QTS - PGCE","0"},
		{"220","https://www.edgehill.ac.uk/courses/pgce-secondary-history/","Secondary History (Age Phase 11-16) with QTS - PGCE","0"},
		{"221","https://www.edgehill.ac.uk/courses/pgce-secondary-mathematics/","Secondary Mathematics (Age Phase 11-16) with QTS - PGCE","0"},
		{"222","https://www.edgehill.ac.uk/courses/secondary-mathematics-education-with-qts/","Secondary Mathematics Education with QTS - BSc (Hons)","0"},
		{"223","https://www.edgehill.ac.uk/courses/secondary-modern-foreign-languages-education-with-qts/","Secondary Modern Foreign Languages Education with QTS - BA (Hons)","0"},
		{"224","https://www.edgehill.ac.uk/courses/pgce-secondary-modern-languages/","Secondary Modern Languages (Age Phase 11-16) with QTS - PGCE","0"},
		{"225","https://www.edgehill.ac.uk/courses/pgce-secondary-music/","Secondary Music (Age Phase 11-16) with QTS - PGCE","0"},
		{"226","https://www.edgehill.ac.uk/courses/pgce-secondary-physical-education/","Secondary Physical Education (Age Phase 11-16) with QTS - PGCE","0"},
		{"227","https://www.edgehill.ac.uk/courses/pgce-secondary-religious-education/","Secondary Religious Education (Age Phase 11-16) with QTS - PGCE","0"},
		{"228","https://www.edgehill.ac.uk/courses/secondary-religious-education-with-qts/","Secondary Religious Education with QTS - BA (Hons)","0"},
		{"229","https://www.edgehill.ac.uk/courses/pgce-secondary-science-biology/","Secondary Science (Biology) (Age Phase 11-16) with QTS - PGCE","0"},
		{"230","https://www.edgehill.ac.uk/courses/secondary-science-biology-education-with-qts/","Secondary Science (Biology) Education with QTS - BSc (Hons)","0"},
		{"231","https://www.edgehill.ac.uk/courses/pgce-secondary-science-chemistry/","Secondary Science (Chemistry) (Age Phase 11-16) with QTS - PGCE","0"},
		{"232","https://www.edgehill.ac.uk/courses/secondary-science-chemistry-education-with-qts/","Secondary Science (Chemistry) Education with QTS - BSc (Hons)","0"},
		{"233","https://www.edgehill.ac.uk/courses/pgce-secondary-science-physics/","Secondary Science (Physics) (Age Phase 11-16) with QTS - PGCE","0"},
		{"234","https://www.edgehill.ac.uk/courses/secondary-science-physics-education-with-qts/","Secondary Science (Physics) Education with QTS - BSc (Hons)","0"},
		{"235","https://www.edgehill.ac.uk/courses/simulation-and-clinical-learning/","Simulation and Clinical Learning - PGCert","0"},
		{"236","https://www.edgehill.ac.uk/courses/social-work/","Social Work - BA (Hons)","0"},
		{"237","https://www.edgehill.ac.uk/courses/social-work-ma/","Social Work - MA","0"},
		{"238","https://www.edgehill.ac.uk/courses/sociology/","Sociology - BA (Hons)","0"},
		{"239","https://www.edgehill.ac.uk/courses/sociology-mres/","Sociology - MRes","0"},
		{"240","https://www.edgehill.ac.uk/courses/software-application-development/","Software Application Development - MComp (Hons)","0"},
		{"241","https://www.edgehill.ac.uk/courses/special-educational-needs-coordination/","Special Educational Needs Coordination - National Award","0"},
		{"242","https://www.edgehill.ac.uk/courses/specialist-primary-mathematics-practice/","Specialist Primary Mathematics Practice - PGCert / MA","0"},
		{"243","https://www.edgehill.ac.uk/courses/spld-dyslexia/","SpLD (Dyslexia) with AMBDA/ATS - PGCert","0"},
		{"244","https://www.edgehill.ac.uk/courses/sport-and-exercise-psychology/","Sport and Exercise Psychology - BSc (Hons)","0"},
		{"245","https://www.edgehill.ac.uk/courses/sport-and-exercise-science/","Sport and Exercise Science - BSc (Hons)","0"},
		{"246","https://www.edgehill.ac.uk/courses/sport-and-exercise-science-mres/","Sport and Exercise Science - MRes","0"},
		{"247","https://www.edgehill.ac.uk/courses/sport-physical-activity-and-mental-health/","Sport, Physical Activity and Mental Health - MSc","0"},
		{"248","https://www.edgehill.ac.uk/courses/sports-coaching-and-development/","Sports Coaching and Development - BA (Hons)","0"},
		{"249","https://www.edgehill.ac.uk/courses/sports-coaching-and-development-msci/","Sports Coaching and Development - MSci","0"},
		{"250","https://www.edgehill.ac.uk/courses/sports-development-mres/","Sports Development - MRes","0"},
		{"251","https://www.edgehill.ac.uk/courses/sports-development-and-management/","Sports Development and Management - BA (Hons)","0"},
		{"252","https://www.edgehill.ac.uk/courses/sports-management-and-coaching/","Sports Management and Coaching - BA (Hons)","0"},
		{"253","https://www.edgehill.ac.uk/courses/sports-studies-mres/","Sports Studies - MRes","0"},
		{"254","https://www.edgehill.ac.uk/courses/sports-therapy-mres/","Sports Therapy - MRes","0"},
		{"255","https://www.edgehill.ac.uk/courses/sports-therapy/","Sports Therapy - BSc (Hons)","0"},
		{"256","https://www.edgehill.ac.uk/courses/stop-motion-animation/","Stop-Motion Animation - BA (Hons)","0"},
		{"257","https://www.edgehill.ac.uk/courses/supporting-practice-in-special-educational-needs/","Supporting Practice in Special Educational Needs - Certificate","0"},
		{"258","https://www.edgehill.ac.uk/courses/supporting-pre-hospital-care/","Supporting Pre-Hospital Care - CertHE","0"},
		{"259","https://www.edgehill.ac.uk/courses/master-of-surgery/","Surgery - MCh","0"},
		{"260","https://www.edgehill.ac.uk/courses/surgical-care-practice/","Surgical Care Practice - MSc","0"},
		{"261","https://www.edgehill.ac.uk/courses/teaching-and-learning-in-clinical-practice/","Teaching and Learning in Clinical Practice - PGCert","0"},
		{"262","https://www.edgehill.ac.uk/courses/teaching-in-the-lifelong-learning-sector-ba/","Teaching in the Lifelong Learning Sector - BA (Hons)","0"},
		{"263","https://www.edgehill.ac.uk/courses/teaching-in-the-lifelong-learning-sector/","Teaching in the Lifelong Learning Sector - FdA","0"},
		{"264","https://www.edgehill.ac.uk/courses/teaching-learning-and-child-development/","Teaching, Learning and Child Development - BA (Hons)","0"},
		{"265","https://www.edgehill.ac.uk/courses/teaching-learning-and-mentoring-practice-part-time/","Teaching, Learning and Mentoring Practice (Part Time) - BA (Hons)","0"},
		{"266","https://www.edgehill.ac.uk/courses/television-production-management/","Television Production Management - BA (Hons)","0"},
		{"267","https://www.edgehill.ac.uk/courses/tesol/","TESOL (Teaching English to Speakers of Other Languages) - MA","0"},
		{"268","https://www.edgehill.ac.uk/courses/theatre-design/","Theatre Design - BA (Hons)","0"},
		{"269","https://www.edgehill.ac.uk/courses/web-design-and-development/","Web Design and Development - BSc (Hons)","0"},
		{"270","https://www.edgehill.ac.uk/courses/web-design-and-development-mcomp/","Web Design and Development - MComp (Hons)","0"},
		{"271","https://www.edgehill.ac.uk/courses/workplace-based-postgraduate-medical-education/","Workplace-based Postgraduate Medical Education - PGCert","0"}
	};

}

