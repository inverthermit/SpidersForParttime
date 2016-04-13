package no6.yorksj;

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

		FileInputStream fis=new FileInputStream(new File("./yorksjPost.html"));
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
   	    	if(!link.getAttribute("href").equals("")&&!link.getAttribute("href").contains("javascript:toggle"))
   	    	{
   	    		if(!list.contains(link.getAttribute("href")))
   	    		{
   	    			list.add(link.getAttribute("href"));
   	    			System.out.println("{\""+count+"\",\""+link.getAttribute("href")+"\",\""+HTMLFilter(html2Str(link.toHtml().replaceAll("<span[\\s\\S]*/span>",""))
   	   	    				.replace("\r\n", "").replace("Top Up", ""))+"\",\"0\"},");
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
	public static String[][] more={
		{"1","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-special.aspx","Education Studies with Special Educational Needs & Inclusion","0"},
		{"2","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies--sociolo.aspx","Education Studies & Sociology*","0"},
		{"3","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/cw-and-english-language.aspx","English Language and Creative Writing BA (Hons)","0"},
		{"4","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/american-studies-and-film.aspx","Film Studies and American Studies BA (Hons)","0"},
		{"5","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/english-lit-and-film-studies.aspx","Film Studies and English Literature BA (Hons)","0"},
		{"6","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/history-and-american-studies.aspx","History and American Studies BA (Hons)","0"},
		{"7","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/english-lit-and-history.aspx","History and English Literature BA (Hons)","0"},
		
	};
	public static String[][] UnData={
		{"1","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/design.aspx","","0"},
		{"2","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/animation.aspx","Animation","0"},
		{"3","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/design.aspx","Design","0"},
		{"4","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/furniture-design-ba-hons.aspx","Furniture Design","0"},
		{"5","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/design-graphic.aspx","GraphicDesign","0"},
		{"6","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/design-product.aspx","Product Design","0"},
		{"7","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/design-interior.aspx","Interior Design","0"},
		{"8","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/fine-arts.aspx","Fine Art","0"},
		{"9","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/illustration.aspx","Illustration","0"},
		{"10","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/games-design.aspx","GamesDesign","0"},
		{"11","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/photography.aspx","Photography","0"},
		{"12","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/accounting--business-man.aspx","Accounting & BusinessManagement","0"},
		{"13","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/accounting--finance.aspx","Accounting & Finance","0"},
		{"14","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-information-technolog.aspx","Business InformationTechnology","0"},
		{"15","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management.aspx","Business Management","0"},
		{"16","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management-with-place.aspx","Business Management  (withplacement)","0"},
		{"17","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management--finance.aspx","Business Management &Finance","0"},
		{"18","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management--hr.aspx","Business Management & HumanResource Management","0"},
		{"19","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management--language.aspx","Business Management &Language","0"},
		{"20","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/bsl-and-business-management.aspx","British Sign Language & BusinessManagement","0"},
		{"21","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management--french.aspx","Business Management &French","0"},
		{"22","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management--german.aspx","Business Management &German","0"},
		{"23","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management--japanese.aspx","Business Management &Japanese","0"},
		{"24","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/business-management--spanish.aspx","Business Management &Spanish","0"},
		{"25","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/international-business-man.aspx","International BusinessManagement","0"},
		{"26","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/marketing-management.aspx","Marketing Management","0"},
		{"27","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/tourism-management.aspx","Tourism Management","0"},
		{"28","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/tourism-management--marketing.aspx","Tourism Management  &Marketing","0"},
		{"29","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/computing-for-games.aspx","Computing for Games","0"},
		{"30","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/computer-science.aspx","Computer Science","0"},
		{"31","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/software-engineering.aspx","Software Engineering","0"},
		{"32","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/biomedical-science.aspx","BiomedicalScience","0"},
		{"33","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/occupational-therapy.aspx","OccupationalTherapy","0"},
		{"34","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/physiotherapy.aspx","Physiotherapy","0"},
		{"35","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/american-studies.aspx","AmericanStudies","0"},
		{"36","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/creative-writing.aspx","Creative Writing","0"},
		{"37","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/english-literature.aspx","EnglishLiterature","0"},
		{"38","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/environmental-geography.aspx","EnvironmentalGeography","0"},
		{"39","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/geography.aspx","Geography","0"},
		{"40","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/history.aspx","History","0"},
		{"41","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/human-geography.aspx","Human Geography","0"},
		{"42","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/human-geography-with-american.aspx","Human Geography  with AmericanStudies","0"},
		{"43","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/human-geography-with-history.aspx","Human Geography  withHistory","0"},
		{"44","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/human-geography-with-media.aspx","Human Geography  with MediaStudies","0"},
		{"45","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/war-studies.aspx","War Studies","0"},
		{"46","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/english-language--linguistics.aspx","English Language &Linguistics","0"},
		{"47","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/language.aspx","Languages","0"},
		{"48","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/linguistics--tesol.aspx","Linguistics &TESOL","0"},
		{"49","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/tesol-and-mfl.aspx","TESOL & Modern Foreign Language","0"},
		{"50","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/tesol-and-french.aspx","TESOL & French","0"},
		{"51","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/tesol-and-german.aspx","TESOL & German","0"},
		{"52","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/tesol-and-japanese.aspx","TESOL & Japanese","0"},
		{"53","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/tesol-and-spanish.aspx","TESOL &   Spanish","0"},
		{"54","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/film-studies.aspx","Film Studies","0"},
		{"55","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/media.aspx","Media","0"},
		{"56","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/media-production-ba-hons.aspx","MediaProduction","0"},
		{"57","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/media-production-journalism.aspx","MediaProduction: Journalism","0"},
		{"58","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/media-production-radio.aspx","MediaProduction: Radio","0"},
		{"59","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/media-production-television.aspx","MediaProduction: Film & Television","0"},
		{"60","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/dance.aspx","Dance","0"},
		{"61","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/dance-education-community.aspx","Dance: Education &Community","0"},
		{"62","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/drama--theatre.aspx","Drama & Theatre","0"},
		{"63","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/drama-education-community.aspx","Drama: Education &Community","0"},
		{"64","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/drama-with-dance.aspx","Drama & Dance","0"},
		{"65","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/music-degrees.aspx","Music degrees","0"},
		{"66","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/music.aspx","Music","0"},
		{"67","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/music-composition.aspx","Music: Composition","0"},
		{"68","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/music-performance.aspx","Music: Performance","0"},
		{"69","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/music-education.aspx","Music: Education &Community","0"},
		{"70","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/music-production.aspx","Music Production","0"},
		{"71","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/counselling-coaching-mentoring.aspx","Counselling, Coaching &Mentoring","0"},
		{"72","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/psychology.aspx","Psychology","0"},
		{"73","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/psychology-with-counselling.aspx","Psychology  withCounselling","0"},
		{"74","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/christian-theology.aspx","Christian Theology","0"},
		{"75","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/religion-philosophy.aspx","Religion, Philosophy & Ethics","0"},
		{"76","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/religious-studies.aspx","Religious Studies","0"},
		{"77","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/theology.aspx","Theology &Religious Studies","0"},
		{"78","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/cypf.aspx","Children, Young People &Families","0"},
		{"79","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/children-young-people--famil.aspx","Children, Young People &Families  with British Sign Language","0"},
		{"80","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/criminology.aspx","Criminology","0"},
		{"81","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/early-childhood-studies.aspx","Early ChildhoodStudies","0"},
		{"82","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies.aspx","Education Studies","0"},
		{"83","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-mfl.aspx","Education Studies  withModern Foreign Language","0"},
		{"84","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-mandari.aspx","Education Studies  withMandarin","0"},
		{"85","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-japanes.aspx","Education Studies  withJapanese","0"},
		{"86","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-french.aspx","Education Studies  withFrench","0"},
		{"87","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-german.aspx","Education Studies  withGerman","0"},
		{"88","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-spanish.aspx","Education Studies  withSpanish","0"},
		{"89","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/education-studies-with-inclusi.aspx","Education Studies  withInclusion","0"},
		{"90","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/sociology.aspx","Sociology","0"},
		{"91","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/sociology-with-criminology.aspx","Sociology  withCriminology","0"},
		{"92","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/nutrition--exercise.aspx","Nutrition & Exercise forHealth","0"},
		{"93","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/pe--youth-sport.aspx","Physical Education & YouthSport","0"},
		{"94","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/sport-development.aspx","Sport Development &Coaching","0"},
		{"95","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/sports-coaching.aspx","Sports Coaching","0"},
		{"96","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/sports-science.aspx","Sports Science & InjuryRehabilitation","0"},
		{"97","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/strength--conditioning.aspx","Strength &Conditioning","0"},
		{"98","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/primary-education.aspx","PrimaryEducation","0"},
		{"99","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/primary-education--3-7.aspx","Primary Education (3 -7 years)","0"},
		{"100","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/primary-education--5-11.aspx","Primary Education (5 -11  years)","0"},
		{"101","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/what-does-subject-to-validati.aspx","subject tovalidation","0"},
		{"102","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses.aspx","browse by category","0"},
		{"103","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/american-studies-and-film.aspx","American Studies andFilm Studies BA (Hons)","0"},
		{"104","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/history-and-american-studies.aspx","American Studies and History  BA (Hons)","0"},
		{"105","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/cypf--education-studies.aspx","Children, Young People and Families and EducationStudies BA (Hons)","0"},
		{"106","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/cw-and-english-language.aspx","Creative Writing and English Language BA(Hons)","0"},
		{"107","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/cw-amd-english-literature.aspx","Creative Writing and English Literature BA(Hons)","0"},
		{"108","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/cw-and-media.aspx","Creative Writing and Media BA (Hons)","0"},
		{"109","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/es-and-english-language.aspx","Education Studies and English Language BA(Hons)","0"},
		{"110","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/es-and-english-literature.aspx","Education Studies and English Literature BA(Hons)","0"},
		{"111","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/education-studies--sociology.aspx","Education Studies and  Sociology BA(Hons)","0"},
		{"112","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/bsl-and-english-lang.aspx","EnglishLanguage and British Sign Language BA (Hons)","0"},
		{"113","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/english-lit-and-english-lang.aspx","EnglishLanguage  and English Literature BA (Hons)","0"},
		{"114","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/french-and-english-lang.aspx","EnglishLanguage and French BA (Hons)","0"},
		{"115","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/german-and-english-lang.aspx","EnglishLanguage and German BA (Hons)","0"},
		{"116","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/japanese-and-english-lang.aspx","EnglishLanguage and  Japanese BA (Hons)","0"},
		{"117","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/spanish-and-english-lang.aspx","EnglishLanguage and Spanish BA (Hons)","0"},
		{"118","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/bsl-and-english-lit.aspx","EnglishLiterature and British Sign Language BA (Hons)","0"},
		{"119","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/english-lit-and-film-studies.aspx","EnglishLiterature and Film Studies BA (Hons)","0"},
		{"120","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/english-lit-and-history.aspx","EnglishLiterature and History BA (Hons)","0"},
		{"121","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/media-and-english-lit.aspx","EnglishLiterature and Media BA (Hons)","0"},
		{"122","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/french-and-english-lit.aspx","EnglishLiterature and French BA (Hons)","0"},
		{"123","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/german-and-english-lit.aspx","EnglishLiterature and German BA (Hons)","0"},
		{"124","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/japanese-and-english-lit.aspx","EnglishLiterature and Japanese BA (Hons)","0"},
		{"125","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/spanish-and-english-lit.aspx","EnglishLiterature and Spanish BA (Hons)","0"},
		{"126","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/our-courses/film-studies.aspx","FilmStudies  (joint honours only)","0"},
		{"127","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/joint-honours-courses/film-studies-and-media.aspx","FilmStudies and Media BA (Hons)","0"},
		{"128","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/foundation-degrees/ctp.aspx","Christian Theology & Practice FoundationDegree","0"},
		{"129","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/foundation-degrees/ctpc.aspx","Christian Theology and Practice in ChaplaincyFoundation Degree","0"},
		{"130","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/foundation-degrees/supporting-learning.aspx","Supporting Learning FoundationDegree","0"},
		{"131","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/foundation-degrees/wcyp.aspx","Working with Children &Young People Foundation Degree","0"},
		{"132","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/accounting-finance-top-up.aspx","Accounting & Finance BA (Hons)","0"},
		{"133","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/business-it-top-up.aspx","Business Information Technology BA (Hons)","0"},
		{"134","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/business-management-top-up.aspx","Business Management  BA (Hons)","0"},
		{"135","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/bm-and-finance-top-up.aspx","Business Management and Finance BA (Hons)","0"},
		{"136","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/hr-management-top-up.aspx","Business Management & Human Resource Management BA(Hons)","0"},
		{"137","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/international-bm-top-up.aspx","International Business Management BA (Hons)  TopUp","0"},
		{"138","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/int-tour--hospitality-top-up.aspx","International Tourism & Hospitality Management BA (Hons)","0"},
		{"139","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/marketing-management-top-up.aspx","Marketing Management  BA (Hons)","0"},
		{"140","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/m-management-and-web-top-up.aspx","Marketing Management and Web Technologies BA (Hons)  TopUp","0"},
		{"141","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/mass-communications-top-up.aspx","MassCommunications BA (Hons)","0"},
		{"142","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/christian-theology--practice.aspx","Christian Theology  & Practice BA (Hons)","0"},
		{"143","http://www.yorksj.ac.uk/undergraduate/undergraduate-study/our-courses/top-up-degrees/christian-theology--pc.aspx","Christian Theology & Practice in Chaplaincy BA(Hons)","0"}
	};
	
	public static String[][] PostData={
		{"1","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/american-studies.aspx","American Studies - MA »","0"},
		{"2","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/applied-theatre.aspx","Applied Theatre - MA, PgDip, PgCert  »","0"},
		{"3","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/contemporary-literature.aspx","Contemporary Literature - MA »","0"},
		{"4","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/creative-writing.aspx","Creative Writing - MA »","0"},
		{"5","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/design.aspx","Design - MA »","0"},
		{"6","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/fine-arts.aspx","Fine Arts - MA, PgDip, PgCert  »","0"},
		{"7","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/international-history.aspx","International History - MA »","0"},
		{"8","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/music-composition.aspx","Music Composition -  MA, PgDip, PgCert  »","0"},
		{"9","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/arts-humanities--performance/theatre-and-performance.aspx","Theatre and Performance - MA, PgDip, PgCert  »","0"},
		{"10","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/coaching--mentoring.aspx","Coaching & Mentoring- PgCert »","0"},
		{"11","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/creative-cultural-marketing.aspx","Creative and Cultural Marketing MSc »","0"},
		{"12","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/digital-marketing.aspx","Digital Marketing MSc »","0"},
		{"13","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/mba-human-resource-management.aspx","MBA: Human Resource Management »","0"},
		{"14","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/int-business-management.aspx","International Business Management MSc »","0"},
		{"15","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/international-marketing.aspx","International Marketing MSc »","0"},
		{"16","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/leadership-and-management.aspx","Leadership & Management MSc","0"},
		{"17","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/mba.aspx","MBA »","0"},
		{"18","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/business-management--it/mba-finance.aspx","MBA:  Finance  »","0"},
		{"19","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/counselling/counselling.aspx","Counselling - PgDip »","0"},
		{"20","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/counselling/counselling--psychotherapy.aspx","Counselling & Psychotherapy Studies - MA »","0"},
		{"21","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/counselling/counsellor-supervision.aspx","Post Grad Certificate in Counsellor Supervision »","0"},
		{"22","http://www.yorkcollege.ac.uk/subject-areas/health-social-care/207-adult-learning/805-introduction-to-counselling-skills-cpcab-award-level-2.html","Introduction to Counselling Skills (CPCAD Award – Level 2)","0"},
		{"23","http://www.yorkcollege.ac.uk/subject-areas/health-social-care/207-adult-learning/941-certificate-in-counselling-skills.html","Certificate in Counselling**","0"},
		{"24","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/education--social-sciences/education-primary.aspx","Primary Education - PGCE  »","0"},
		{"25","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/education--social-sciences/education-secondary-re.aspx","Secondary Education  RE  - PGCE »","0"},
		{"26","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/education--social-sciences/ma-education.aspx","MA Education  »","0"},
		{"27","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/education--social-sciences/ma-education-early-childhood.aspx","MA Education: Early Childhood  »","0"},
		{"28","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/education--social-sciences/ma-education-mentoring.aspx","MA Education: Mentoring  »","0"},
		{"29","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/education--social-sciences/ma-education-p-ce.aspx","MA Education: Post-compulsory Education  »","0"},
		{"30","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/education--social-sciences/ma-education-research-engaged.aspx","MA Education: Researched-engaged Setting  »","0"},
		{"31","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/language--linguistics/applied-linguistics-tesol.aspx","Applied Linguistics: TESOL - MA  »","0"},
		{"32","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/language--linguistics/applied-linguistics.aspx","Applied Linguistics: Translation - MA  »","0"},
		{"33","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/language--linguistics/english-language--linguistics.aspx","English Language & Linguistics - MA, PgDip, PgCert  »","0"},
		{"34","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/enabling-activity.aspx","Enabling Activity and Participation in Dementia - MSc »","0"},
		{"35","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/health--wellbeing.aspx","Health and Wellbeing for Older People - MSc »","0"},
		{"36","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/housing.aspx","Housing and Inclusive Environments - MSc »","0"},
		{"37","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/leadership-for-ahp.aspx","Leadership for Allied Health Professionals - MSc »","0"},
		{"38","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/physiotherapy.aspx","Physiotherapy (Pre-registration)  - MSc »","0"},
		{"39","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/professional-health-and-social.aspx","Professional Health and Social Care - MSc  »","0"},
		{"40","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/promoting-health.aspx","Promoting Health in Long Term Conditions - MSc »","0"},
		{"41","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/health--life-sciences/quality-and-safety.aspx","Quality & Safety in Health and Social Care - MSc »","0"},
		{"42","http://www.yorksj.ac.uk/health--life-sciences/faculty-of-hls/cpd-and-lifelong-learning.aspx","CPD short courses","0"},
		{"43","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/psychology/applied-cognitive-psychology.aspx","Applied Cognitive Psychology - MSc »","0"},
		{"44","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/psychology/community--csp.aspx","Community & Critical Social Psychology - MSc »","0"},
		{"45","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/psychology/psychology.aspx","Psychology - MSc »","0"},
		{"46","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/psychology/pcad.aspx","Psychology of Child & Adolescent Development - MSc »","0"},
		{"47","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/sport--excercise-science/applied-sport-and-es.aspx","Applied Sport and Exercise Science - MSc »","0"},
		{"48","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/sport--excercise-science/sport-coaching.aspx","Sport Coaching - MSc »","0"},
		{"49","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/sport--excercise-science/strength-and-conditioning.aspx","Strength and Conditioning - MSc »","0"},
		{"50","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/theology--religion-studies/chaplaincy-studies-ma.aspx","MA Chaplaincy Studies  »","0"},
		{"51","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/theology--religion-studies/contemporary-religion-ma.aspx","MA Contemporary Religion  »","0"},
		{"52","http://www.yorksj.ac.uk/postgraduate/postgraduate-study/courses/theology--religion-studies/theology--ministry-ma.aspx","MA Theology & Ministry  »","0"},
	};
}

