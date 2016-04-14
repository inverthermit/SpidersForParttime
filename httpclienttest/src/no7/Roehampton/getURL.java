package no7.Roehampton;

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

		FileInputStream fis=new FileInputStream(new File("./roehamptonPost.html"));
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
   	    			System.out.println("{\""+count+"\",\"http://www.roehampton.ac.uk/"
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
	public static String[][] UnData1={
		{"1","http://www.roehampton.ac.uk//undergraduate-courses/Accounting/","Accounting","0"},
		
	};
	public static String[][] UnData={
		{"1","http://www.roehampton.ac.uk//undergraduate-courses/Accounting/","Accounting","0"},
		{"2","http://www.roehampton.ac.uk//undergraduate-courses/anthropology/index.html","Anthropology","0"},
		{"3","http://www.roehampton.ac.uk//undergraduate-courses/biological-sciences/index.html","Biological Sciences","0"},
		{"4","http://www.roehampton.ac.uk//undergraduate-courses/Integrated-Biological-Sciences/","Biological Sciences (Integrated BSc and MSc)","0"},
		{"5","http://www.roehampton.ac.uk//undergraduate-courses/biomedical-sciences/index.html","Biomedical Science","0"},
		{"6","http://www.roehampton.ac.uk//undergraduate-courses/business-management/index.html","Business Management","0"},
		{"7","http://www.roehampton.ac.uk//undergraduate-courses/business-management-economics/","Business Management and Economics","0"},
		{"8","http://www.roehampton.ac.uk//undergraduate-courses/business-management-and-economics-with-foundation-year/","Business Management and Economics Extended Degree","0"},
		{"9","http://www.roehampton.ac.uk//undergraduate-courses/business-management-entrepreneurship/","Business Management and Entrepreneurship","0"},
		{"10","http://www.roehampton.ac.uk//undergraduate-courses/business-management-entrepreneurship-with-foundation-year/","Business Management and Entrepreneurship Extended Degree","0"},
		{"11","http://www.roehampton.ac.uk//undergraduate-courses/business-management-with-foundation-year/","Business Management Extended Degree","0"},
		{"12","http://www.roehampton.ac.uk//undergraduate-courses/classical-civilisation/index.html","Classical Civilisation","0"},
		{"13","http://www.roehampton.ac.uk//undergraduate-courses/creative-writing/index.html","Creative Writing","0"},
		{"14","http://www.roehampton.ac.uk//undergraduate-courses/criminology/index.html","Criminology","0"},
		{"15","http://www.roehampton.ac.uk//undergraduate-courses/dance-studies/index.html","Dance Studies","0"},
		{"16","http://www.roehampton.ac.uk//undergraduate-courses/drama-studies/","Drama Studies","0"},
		{"17","http://www.roehampton.ac.uk//drama-theatre-and-performance/index.html","Drama, Theatre and Performance Studies","0"},
		{"18","http://www.roehampton.ac.uk//undergraduate-courses/early-childhood-studies/index.html","Early Childhood Studies","0"},
		{"19","http://www.roehampton.ac.uk//undergraduate-courses/early-childhood-studies-with-foundation-year/","Early Childhood Studies Extended Degree","0"},
		{"20","http://www.roehampton.ac.uk//undergraduate-courses/education/index.html","Education","0"},
		{"21","http://www.roehampton.ac.uk//undergraduate-courses/education-with-foundation-year/","Education Extended Degree","0"},
		{"22","http://www.roehampton.ac.uk//undergraduate-courses/english-language-and-linguistics/index.html","English Language and Linguistics","0"},
		{"23","http://www.roehampton.ac.uk//undergraduate-courses/english-literature/index.html","English Literature","0"},
		{"24","http://www.roehampton.ac.uk//undergraduate-courses/film/index.html","Film","0"},
		{"25","http://www.roehampton.ac.uk//undergraduate-courses/french/index.html","French","0"},
		{"26","http://www.roehampton.ac.uk//undergraduate-courses/French-and-Spanish/","French and Spanish","0"},
		{"27","http://www.roehampton.ac.uk//undergraduate-courses/history/index.html","History","0"},
		{"28","http://www.roehampton.ac.uk//undergraduate-courses/human-resource-management/index.html","Human Resource Management","0"},
		{"29","http://www.roehampton.ac.uk//undergraduate-courses/human-resource-management-with-foundation-year/","Human Resource Management Extended Degree","0"},
		{"30","http://www.roehampton.ac.uk//undergraduate-courses/international-business/index.html","International Business","0"},
		{"31","http://www.roehampton.ac.uk//undergraduate-courses/international-business-with-foundation-year/","International Business Extended Degree","0"},
		{"32","http://www.roehampton.ac.uk//undergraduate-courses/journalism/index.html","Journalism","0"},
		{"33","http://www.roehampton.ac.uk//undergraduate-courses/Law/","LLB (Hons) Law","0"},
		{"34","http://www.roehampton.ac.uk//undergraduate-courses/Law-and-Criminology/","LLB (Hons) Law and Criminology","0"},
		{"35","http://www.roehampton.ac.uk//undergraduate-courses/marketing/index.html","Marketing","0"},
		{"36","http://www.roehampton.ac.uk//undergraduate-courses/marketing-with-foundation-year/","Marketing Extended Degree","0"},
		{"37","http://www.roehampton.ac.uk//undergraduate-courses/mass-communications/index.html","Mass Communications","0"},
		{"38","http://www.roehampton.ac.uk//undergraduate-courses/media-culture-and-identity/","Media, Culture and Identity","0"},
		{"39","http://www.roehampton.ac.uk//undergraduate-courses/ministerial-theology/index.html","Ministerial Theology (FdA/BTh)","0"},
		{"40","http://www.roehampton.ac.uk//undergraduate-courses/nutrition-and-health/index.html","Nutrition and Health","0"},
		{"41","http://www.roehampton.ac.uk//undergraduate-courses/philosophy/index.html","Philosophy","0"},
		{"42","http://www.roehampton.ac.uk//undergraduate-courses/photography/index.html","Photography","0"},
		{"43","http://www.roehampton.ac.uk//undergraduate-courses/primary-education/index.html","Primary Education","0"},
		{"44","http://www.roehampton.ac.uk//undergraduate-courses/psychology/index.html","Psychology","0"},
		{"45","http://www.roehampton.ac.uk//undergraduate-courses/counselling-psychology/index.html","Psychology and Counselling","0"},
		{"46","http://www.roehampton.ac.uk//undergraduate-courses/retail-marketing-and-management/","Retail Marketing and Management","0"},
		{"47","http://www.roehampton.ac.uk//undergraduate-courses/retail-marketing-and-management-with-foundation-year/","Retail Marketing and Management Extended Degree","0"},
		{"48","http://www.roehampton.ac.uk//undergraduate-courses/social-anthropology/index.html","Social Anthropology","0"},
		{"49","http://www.roehampton.ac.uk//undergraduate-courses/sociology/index.html","Sociology","0"},
		{"50","http://www.roehampton.ac.uk//undergraduate-courses/spanish/index.html","Spanish","0"},
		{"51","http://www.roehampton.ac.uk//undergraduate-courses/sport-and-exercise-sciences/index.html","Sport and Exercise Sciences","0"},
		{"52","http://www.roehampton.ac.uk//undergraduate-courses/sport-coaching/","Sport Coaching","0"},
		{"53","http://www.roehampton.ac.uk//undergraduate-courses/sport-psychology/index.html","Sport Psychology","0"},
		{"54","http://www.roehampton.ac.uk//undergraduate-courses/sports-coaching-practice/index.aspx","Sports Coaching Practice *Subject to approval","0"},
		{"55","http://www.roehampton.ac.uk//undergraduate-courses/theology-and-religious-studies/index.html","Theology and Religious Studies","0"},
		{"56","http://www.roehampton.ac.uk//undergraduate-courses/Therapeutic-Psychology/","Therapeutic Psychology","0"},
		{"57","http://www.roehampton.ac.uk//undergraduate-courses/Translation/","Translation","0"},
		{"58","http://www.roehampton.ac.uk//undergraduate-courses/zoology/index.html","Zoology","0"},
		{"59","http://www.roehampton.ac.uk//undergraduate-courses/Integrated-Zoology/","Zoology (Integrated BSc and MSc)","0"}
	};
	public static String[][] PostData={
		{"1","http://www.roehampton.ac.uk//postgraduate-courses/accessibility-and-filmmaking/","Accessibility and Filmmaking","0"},
		{"2","http://www.roehampton.ac.uk//postgraduate-courses/Anthropology-of-Health/","Anthropology of Health","0"},
		{"3","http://www.roehampton.ac.uk//postgraduate-courses/applied-linguistics-and-tesol/","Applied Linguistics and TESOL","0"},
		{"4","http://www.roehampton.ac.uk//postgraduate-courses/art-psychotherapy/index.html","Art Psychotherapy","0"},
		{"5","http://www.roehampton.ac.uk//postgraduate-courses/attachment-studies/index.html","Attachment Studies","0"},
		{"6","http://www.roehampton.ac.uk//postgraduate-courses/audiovisual-translation/index.html","Audiovisual Translation","0"},
		{"7","http://www.roehampton.ac.uk//postgraduate-courses/biomechanics/index.html","Biomechanics","0"},
		{"8","http://www.roehampton.ac.uk//postgraduate-courses/childrens-literature/index.html","Children's Literature","0"},
		{"9","http://www.roehampton.ac.uk//postgraduate-courses/childrens-literature-distance-learning/index.html","Children's Literature (Distance Learning)","0"},
		{"10","http://www.roehampton.ac.uk//postgraduate-courses/dance-choreography/index.html","Choreography","0"},
		{"11","http://www.roehampton.ac.uk//postgraduate-courses/choreography-and-performance-mres/index.html","Choreography and Performance: Masters by Research","0"},
		{"12","http://www.roehampton.ac.uk//postgraduate-courses/choreomundus/index.html","Choreomundus: International Master in Dance Knowledge, Practice and Heritage","0"},
		{"13","http://www.roehampton.ac.uk//postgraduate-courses/christian-ministry/index.html","Christian Ministry","0"},
		{"14","http://www.roehampton.ac.uk//postgraduate-courses/Classical-Research/","Classical Research","0"},
		{"15","http://www.roehampton.ac.uk//postgraduate-courses/clinical-neuroscience/index.html","Clinical Neuroscience","0"},
		{"16","http://www.roehampton.ac.uk//postgraduate-courses/clinical-nutrition/index.html","Clinical Nutrition","0"},
		{"17","http://www.roehampton.ac.uk//postgraduate-courses/counselling-psychology-bps/index.html","Counselling Psychology (HCPC approved and BPS accredited)","0"},
		{"18","http://www.roehampton.ac.uk//postgraduate-courses/Creative-Writing/","Creative Writing","0"},
		{"19","http://www.roehampton.ac.uk//postgraduate-courses/creative-writing-specialist-pathway/","Creative Writing (specialist pathway)","0"},
		{"20","http://www.roehampton.ac.uk//postgraduate-courses/dance-anthropology/index.html","Dance Anthropology","0"},
		{"21","http://www.roehampton.ac.uk//postgraduate-courses/dance-movement-psychotherapy/index.html","Dance Movement Psychotherapy","0"},
		{"22","http://www.roehampton.ac.uk//postgraduate-courses/dance-philosophy-and-history/","Dance Philosophy and History","0"},
		{"23","http://www.roehampton.ac.uk//postgraduate-courses/dance-politics-and-sociology/","Dance Politics and Sociology","0"},
		{"24","http://www.roehampton.ac.uk//postgraduate-courses/dramatherapy/index.html","Dramatherapy","0"},
		{"25","http://www.roehampton.ac.uk//postgraduate-courses/early-childhood-studies/index.html","Early Childhood Studies","0"},
		{"26","http://www.roehampton.ac.uk//postgraduate-courses/education-leadership-and-management/index.html","Education Leadership and Management","0"},
		{"27","http://www.roehampton.ac.uk//postgraduate-courses/Education-Policy/","Education Policy","0"},
		{"28","http://www.roehampton.ac.uk//postgraduate-courses/Educational-Practice/","Educational Practice","0"},
		{"29","http://www.roehampton.ac.uk//postgraduate-courses/Erasmus-Mundus-Human-Rights-Policy-and-Practice/","Erasmus Mundus Human Rights Policy and Practice","0"},
		{"30","http://www.roehampton.ac.uk//postgraduate-courses/erasmus-mundus-special-and-inclusive-education/index.html","Erasmus Mundus Special and Inclusive Education","0"},
		{"31","http://www.roehampton.ac.uk//postgraduate-courses/Film-and-Screen-Cultures/","Film and Screen Cultures","0"},
		{"32","http://www.roehampton.ac.uk//postgraduate-courses/global-criminology/","Global Criminology","0"},
		{"33","http://www.roehampton.ac.uk//postgraduate-courses/law/","Graduate Diploma in Law","0"},
		{"34","http://www.roehampton.ac.uk//postgraduate-courses/health-sciences/index.html","Health Sciences","0"},
		{"35","http://www.roehampton.ac.uk//postgraduate-courses/historical-research/index.html","Historical Research","0"},
		{"36","http://www.roehampton.ac.uk//postgraduate-courses/human-rights/","Human Rights","0"},
		{"37","http://www.roehampton.ac.uk//postgraduate-courses/human-rights-and-international-relations/index.html","Human Rights and International Relations","0"},
		{"38","http://www.roehampton.ac.uk//postgraduate-courses/integrative-counselling-and-psychotherapy/index.html","Integrative Counselling and Psychotherapy","0"},
		{"39","http://www.roehampton.ac.uk//postgraduate-courses/Intercultural-Communication-in-the-Creative-Industries/","Intercultural Communication in the Creative Industries","0"},
		{"40","http://www.roehampton.ac.uk//postgraduate-courses/international-management/index.html","International Management","0"},
		{"41","http://www.roehampton.ac.uk//postgraduate-courses/international-management-with-finance/index.html","International Management with Finance","0"},
		{"42","http://www.roehampton.ac.uk//postgraduate-courses/international-management-with-human-resource-management/index.html","International Management with HRM","0"},
		{"43","http://www.roehampton.ac.uk//postgraduate-courses/international-management-with-marketing/index.html","International Management with Marketing","0"},
		{"44","http://www.roehampton.ac.uk//postgraduate-courses/Journalism/","Journalism","0"},
		{"45","http://www.roehampton.ac.uk//postgraduate-courses/londons-theatre-and-performance/","London’s Theatre and Performance: Viewing, Making, Writing","0"},
		{"46","http://www.roehampton.ac.uk//mba/index.html","MBA","0"},
		{"47","http://www.roehampton.ac.uk//postgraduate-courses/media-communication-and-culture/","Media Communication and Culture","0"},
		{"48","http://www.roehampton.ac.uk//postgraduate-courses/music-therapy/index.html","Music Therapy","0"},
		{"49","http://www.roehampton.ac.uk//postgraduate-courses/obesity-risks-and-prevention/index.html","Obesity: Risks and Prevention","0"},
		{"50","http://www.roehampton.ac.uk//postgraduate-courses/performance-and-creative-research/index.html","Performance and Creative Research","0"},
		{"51","http://www.roehampton.ac.uk//postgraduate-courses/pgce-primary/index.html","PGCE Primary","0"},
		{"52","http://www.roehampton.ac.uk//postgraduate-courses/pgce-primary-mathematics-specialist/","PGCE Primary (Mathematics Specialist)","0"},
		{"53","http://www.roehampton.ac.uk//postgraduate-courses/pgce-secondary/index.html","PGCE Secondary","0"},
		{"54","http://www.roehampton.ac.uk//postgraduate-courses/play-therapy/index.html","Play Therapy","0"},
		{"55","http://www.roehampton.ac.uk//postgraduate-courses/primate-biology-behaviour-and-conservation/index.html","Primate Biology, Behaviour and Conservation","0"},
		{"56","http://www.roehampton.ac.uk//postgraduate-courses/psychological-science/","Psychological Science","0"},
		{"57","http://www.roehampton.ac.uk//postgraduate-courses/Psychology-of-Sport-and-Exercise/","Psychology of Sport and Exercise (BPS Accredited)","0"},
		{"58","http://www.roehampton.ac.uk//postgraduate-courses/sounds-of-intent/","Sounds of Intent","0"},
		{"59","http://www.roehampton.ac.uk//postgraduate-courses/Special-Education-Needs/","Special Educational Needs (Inclusive or Psychological Perspectives)","0"},
		{"60","http://www.roehampton.ac.uk//postgraduate-courses/specialised-translation/index.html","Specialised Translation","0"},
		{"61","http://www.roehampton.ac.uk//postgraduate-courses/sport-and-exercise-physiology/index.html","Sport and Exercise Physiology","0"},
		{"62","http://www.roehampton.ac.uk//postgraduate-courses/sport-psychology/index.html","Sport and Exercise Psychology","0"},
		{"63","http://www.roehampton.ac.uk//postgraduate-courses/sport-and-exercise-science/index.html","Sport and Exercise Science","0"},
		{"64","http://www.roehampton.ac.uk//postgraduate-courses/sport-and-exercise-science-mres/index.html","Sport and Exercise Science (MRes)","0"},
		{"65","http://www.roehampton.ac.uk//postgraduate-courses/stress-and-health/index.html","Stress and Health","0"},
		{"66","http://www.roehampton.ac.uk//postgraduate-courses/Studies-in-Contemporary-Catholicism/","Studies in Contemporary Catholicism","0"},
		{"67","http://www.roehampton.ac.uk//Courses/Humanities/Courses/Theology-and-Religious-Studies-MA/","Theology and Religious Studies","0"},

	};
}

