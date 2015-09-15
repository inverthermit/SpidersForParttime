package birmingham;

import java.io.File;
import java.io.FileInputStream;

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
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class getURL {

	/**
	 * @param args
	 */
	public static String url="http://www.birmingham.ac.uk/students/courses/index.aspx?SearchFilter=Undergraduate&CourseComplete_searchlisting_goto=";
	public static void main(String[] args) throws Exception{
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		for(int i=1;i<=35;i++)
		{
			int index=i;
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url+index); 
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			Parser parser=Parser.createParser(htmls, "utf-8");
		    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("table"),
	                new HasAttributeFilter("class","sys_uob-listing"));
	        NodeList nodes1 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
	        parser=Parser.createParser(nodes1.elementAt(0).toHtml(), "utf-8");
	        TagNameFilter TF=new TagNameFilter("tr");
	        NodeList nodes2 = parser.extractAllNodesThatMatch(TF);
	        //System.out.println("Size:"+nodes2.size());
	        if(nodes2.size()>0)
	        {
	        	for(int j=1;j<nodes2.size();j++)
	        	{
	        		
	        		//***************get links;name;type;time
	        		Node node=(Node)nodes2.elementAt(j);
	        		//System.out.println(node.toHtml());
	        		AndFilter linkFilter=new AndFilter(new TagNameFilter("a"),
	                        new HasAttributeFilter("title"));
	        		parser=Parser.createParser(node.toHtml(), "utf-8");
	                NodeList nodes3 = parser.extractAllNodesThatMatch(linkFilter);
	                //System.out.println(nodes3.size());
	                Node aNode=nodes3.elementAt(0);
	                //System.out.println(aNode.toHtml());
	                LinkTag l=(LinkTag)aNode;
	                System.out.print("{\"http://www.birmingham.ac.uk"+l.getAttribute("href")+"\",\""+html2Str(aNode.toHtml().replace(" <br />", "\",\"")).trim()+"\",\"");
	                //html2Str(aNode.toHtml().replace("<br />", ";"));
	                
	                
	               //***************get duration
	                
	                String Duration=html2Str(node.toHtml());
	                if(Duration.contains("1 year"))
	        		{
	        			System.out.print(12);
	        		}
	        		else if(Duration.contains("2 year"))
	        		{
	        			System.out.print(24);
	        		}
	        		else if(Duration.contains("3 year"))
	        		{
	        			System.out.print(36);
	        		}
	        		else if(Duration.contains("4 year"))
	        		{
	        			System.out.print(48);
	        		}
	        		else if(Duration.contains("5 year"))
	        		{
	        			System.out.print(60);
	        		}
	        		else if(Duration.contains("1")&&Duration.contains("year"))
	        		{
	        			System.out.print(12);
	        		}
	        		else if(Duration.contains("2")&&Duration.contains("year"))
	        		{
	        			System.out.print(24);
	        		}
	        		else if(Duration.contains("3")&&Duration.contains("year"))
	        		{
	        			System.out.print(36);
	        		}
	        		else if(Duration.contains("4")&&Duration.contains("year"))
	        		{
	        			System.out.print(48);
	        		}
	        		else if(Duration.contains("5")&&Duration.contains("year"))
	        		{
	        			System.out.print(60);
	        		}
	                System.out.println("\"},");
	                
	        	}
	        	
	    	    
	        }
		}
		System.out.println("DONE.");
		
		
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

		public static String[][] unData={
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/accounting-finance.aspx","Accounting and Finance BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/dasa/african.aspx","African Studies BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/dasa/african-anthropology.aspx","African Studies with Anthropology BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/dasa/african-development.aspx","African Studies with Development BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/american-and-canadian-and-history.aspx","American and Canadian Studies and History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/acs/american-canadian-studies.aspx","American and Canadian Studies BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/acs/american-canadian-abroad.aspx","American and Canadian Studies with year abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/history/history-ancient-medieval.aspx","Ancient and Medieval History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/caha/ancient-history.aspx","Ancient History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/anthropology-and-african-studies.aspx","Anthropology and African Studies BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/anthropology-and-classical-literature-and-civilisation.aspx","Anthropology and Classical Literature and Civilisation BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/anthropology-and-history.aspx","Anthropology and History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/anthropology-and-political-science.aspx","Anthropology and Political Science BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/sportex/app-golf-mgt-studies.aspx","Applied Golf Management Studies BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/archaeology-and-ancient-history-and-history.aspx","Archaeology & Ancient History and History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/caha/arch-ancient-hist.aspx","Archaeology and Ancient History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/caha/arch-anthropology.aspx","Archaeology and Anthropology BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/artificial-intelligence-computer-science.aspx","Artificial Intelligence and Computer Science BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/artificial-intelligence-computer-science-industry.aspx","Artificial Intelligence and Computer Science BSc with a year in industry","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/edu/autism-spectrum.aspx","Autism Spectrum (Webautism) University Certificate","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/american-and-canadian-and-english.aspx","BA American and Canadian Studies and English","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/policy-politics-economics.aspx","BA Policy, Politics and Economics","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science-social-policy.aspx","BA Political Science and Social Policy","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry-genetics.aspx","Biochemistry (Genetics) BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry.aspx","Biochemistry BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry-msci.aspx","Biochemistry MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry-international-year.aspx","Biochemistry with an International Year BSc (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry-biotechnology.aspx","Biochemistry with Biotechnology BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry-molecular-cell.aspx","Biochemistry with Molecular Cell Biology BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry-placement.aspx","Biochemistry with Professional Placement MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biochemistry-europe.aspx","Biochemistry with Study in Continental Europe BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-biotechnology.aspx","Biological Sciences (Biotechnology) BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-environmental-biology.aspx","Biological Sciences (Environmental Biology) BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-genetics.aspx","Biological Sciences (Genetics) BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-microbiology.aspx","Biological Sciences (Microbiology) BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-plant.aspx","Biological Sciences (Plant Biology) BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-zoology.aspx","Biological Sciences (Zoology) BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences.aspx","Biological Sciences BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-msci.aspx","Biological Sciences MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-international-year.aspx","Biological Sciences with an International Year BSc (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-placement.aspx","Biological Sciences with Professional Placement MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biological-sciences-europe.aspx","Biological Sciences with Study in Continental Europe BSc (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/BiomedicalEthicsandLaw.aspx","Biomedical Ethics and Law",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/biomedical-materials-sci.aspx","Biomedical Materials Science BMedSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/biomedical-science.aspx","Biomedical Science BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/biosciences-business-management.aspx","Biosciences with Business Management BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/bus-mgnt-year-in-industry.aspx","Business Management (Year in Industry) BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/business-management.aspx","Business Management BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/business-mgmt-comms.aspx","Business Management with Communications BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/business-management-with-marketing.aspx","Business Management with Marketing BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/cert-english-law.aspx","Certificate in English Law","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-energy-beng.aspx","Chemical and Energy Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-energy-meng.aspx","Chemical and Energy Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-energy-industry-beng.aspx","Chemical and Energy Engineering with Industrial Study BEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-energy-industrial-meng.aspx","Chemical and Energy Engineering with Industrial Study MEng","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-international-meng.aspx","Chemical Engineering (International Study) MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-beng.aspx","Chemical Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-meng.aspx","Chemical Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-business-beng.aspx","Chemical Engineering with Business Management BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-business-meng.aspx","Chemical Engineering with Business Management MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-business-industrial-beng.aspx","Chemical Engineering with Business Management with Industrial Study BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-business-industrial-meng.aspx","Chemical Engineering with Business Managementwith Industrial Study MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/fd/chemical-engineering-foundation.aspx","Chemical Engineering with Foundation Year","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-industry-beng.aspx","Chemical Engineering with Industrial Study BEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-industry-meng.aspx","Chemical Engineering with Industrial Study MEng","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemical-engineering/chemical-engineering-industrial-international-meng.aspx","Chemical Engineering with International and Industrial Study MEng","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-bsc.aspx","Chemistry BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-msci.aspx","Chemistry MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-language-bsc.aspx","Chemistry with a Modern Language BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-language-msci.aspx","Chemistry with a Modern Language MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-business-bsc.aspx","Chemistry with Business Management BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-business-msci.aspx","Chemistry with Business Management MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/fd/chemistry-foundation.aspx","Chemistry with Foundation Year","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-industrial.aspx","Chemistry with Industrial Experience MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-pharmacology-bsc.aspx","Chemistry with Pharmacology BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-pharmacology-msci.aspx","Chemistry with Pharmacology MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/chemistry/chemistry-study-abroad-msci.aspx","Chemistry with Study Abroad MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-energy-beng.aspx","Civil and Energy Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-energy-meng.aspx","Civil and Energy Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-railway-beng.aspx","Civil and Railway Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-railway-meng.aspx","Civil and Railway Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-beng.aspx","Civil Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-meng.aspx","Civil Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-business-beng.aspx","Civil Engineering with Business Management BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-business-meng.aspx","Civil Engineering with Business Management MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-industry.aspx","Civil Engineering with Industrial Experience MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/civil-engineering/civil-engineering-international-meng.aspx","Civil Engineering with International Study MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/classical-literature-and-civilisation-and-philosophy.aspx","Classical Literature & Civilisation and Philosophy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/caha/classical-lit-civ.aspx","Classical Literature and Civilisation BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/caha/classics.aspx","Classics BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/ClinicalScienceBMedSc-IntercalatedDegree.aspx","Clinical Science BMedSc - Intercalated Degree","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/CommunicableDiseasesinInternationalHealth.aspx","Communicable Diseases in International Health",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science.aspx","Computer Science BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-industry.aspx","Computer Science BSc with a year in industry","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-msci.aspx","Computer Science MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-industry-msci.aspx","Computer Science with an Industrial Year MSci","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-business.aspx","Computer Science with Business Management BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-business-industry.aspx","Computer Science with Business Management BSc with a year in industry","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-international-bsc.aspx","Computer Science with Study Abroad BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-international-msci.aspx","Computer Science with Study Abroad MSci","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-software-engineering.aspx","Computer Science/Software Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/computer-science/computer-science-software-engineering-industry.aspx","Computer Science/Software Engineering MEng with a year in industry","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/ContemporaryMedicalStatistics.aspx","Contemporary Medical Statistics",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/dental-hygiene-therapy.aspx","Dental Hygiene and Therapy BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/dental-surgery.aspx","Dental Surgery BDS","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/DissertationProject.aspx","Dissertation Project",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/drama-and-english.aspx","Drama and English BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/drama/drama-theatre.aspx","Drama and Theatre Arts BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/economics-and-political-science-bsc.aspx","Economics and Political Science BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/economics-bsc.aspx","Economics BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/economics-german.aspx","Economics with German BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/economics-italian.aspx","Economics with Italian BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/economics-japanese.aspx","Economics with Japanese BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/economics-portuguese.aspx","Economics with Portuguese BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/economics-spanish.aspx","Economics with Spanish BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/edu/education-history.aspx","Education and History BA (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/edu/education-sociology.aspx","Education and Sociology BA (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/edu/ba-education.aspx","Education BA (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electrical-energy-engineering-beng.aspx","Electrical and Energy Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electrical-energy-engineering-meng.aspx","Electrical and Energy Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electrical-energy-engineering-industry-beng.aspx","Electrical and Energy Engineering with Industrial Year BEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electrical-energy-engineering-industry-meng.aspx","Electrical and Energy Engineering with Industrial Year MEng","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electrical-railway-engineering-beng.aspx","Electrical and Railway Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electrical-railway-engineering-meng.aspx","Electrical and Railway Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-electrical-engineering.aspx","Electronic and Electrical Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-electrical-engineering-meng.aspx","Electronic and Electrical Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-electrical-engineering-industry-beng.aspx","Electronic and Electrical Engineering with Industrial Year BEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-electrical-engineering-industry-meng.aspx","Electronic and Electrical Engineering with Industrial Year MEng","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-engineering-business-beng.aspx","Electronic Engineering with Business Management BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-engineering-business-meng.aspx","Electronic Engineering with Business Management MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-engineering-business-industry-beng.aspx","Electronic Engineering with Business Management with Industrial Year BEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/eese/electronic-engineering-business-industry-meng.aspx","Electronic Engineering with Business Management with Industrial Year MEng","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/fd/engineering-physical-sciences-foundation.aspx","Engineering and Physical Sciences Foundation Year","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/english-and-classical-literature-and-civilisation.aspx","English and Classical Literature & Civilisation BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/english/english-creative.aspx","English and Creative Writing BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/english-and-history.aspx","English and History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/english-and-philosophy.aspx","English and Philosophy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/english/english.aspx","English BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/english/english-language-and-literature.aspx","English Language and Literature BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/english/english-lang.aspx","English Language BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/english-literature-and-history-of-art.aspx","English Literature and History of Art BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-geology.aspx","Environmental Geology BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-geology-msci.aspx","Environmental Geology MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-geology-international.aspx","Environmental Geology with an International Year MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-science.aspx","Environmental Science BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-science-msci.aspx","Environmental Science MSci (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-science-placement.aspx","Environmental Science with Professional Placement Abroad (Australasia) BSc (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-science-abroad.aspx","Environmental Science with Year Abroad BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/EthicsinResearch.aspx","Ethics in Research",""},
			{"http://www.birmingham.ac.uk/undergraduate/bfa/pathways/ss-business-and-law-Jan-prg.aspx","Foundation Pathways  - Social Sciences, Business and Law, Jan programme","12"},
			{"http://www.birmingham.ac.uk/International/foundation-academy/pathways/ss-business-and-law-Jan-prg.aspx","Foundation Pathways  - Social Sciences, Business and Law, Jan programme","12"},
			{"http://www.birmingham.ac.uk/undergraduate/bfa/pathways/bfa-arts-ss-business-and-law.aspx","Foundation Pathways - Arts, Social Sciences, Business and Law","12"},
			{"http://www.birmingham.ac.uk/International/foundation-academy/pathways/bfa-arts-ss-business-and-law.aspx","Foundation Pathways - Arts, Social Sciences, Business and Law","12"},
			{"http://www.birmingham.ac.uk/undergraduate/bfa/pathways/engineering-and-physical-sciences-pathway.aspx","Foundation Pathways - Engineering and Physical Sciences Pathway","12"},
			{"http://www.birmingham.ac.uk/International/foundation-academy/pathways/engineering-and-physical-sciences-pathway.aspx","Foundation Pathways - Engineering and Physical Sciences Pathway","12"},
			{"http://www.birmingham.ac.uk/undergraduate/bfa/pathways/medical-life-geosciences-pathway.aspx","Foundation Pathways - Medical, Life and Geo Sciences Pathway","12"},
			{"http://www.birmingham.ac.uk/International/foundation-academy/pathways/medical-life-geosciences-pathway.aspx","Foundation Pathways - Medical, Life and Geo Sciences Pathway","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/french-studies-and-geography.aspx","French Studies and Geography BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/french-studies-and-history.aspx","French Studies and History BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/french-studies-and-mathematics.aspx","French Studies and Mathematics BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/geography-economy-joint.aspx","Geography and Economics Joint Honours BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/geography-and-german-studies.aspx","Geography and German Studies BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/geography-and-history.aspx","Geography and History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-urban-joint.aspx","Geography and Urban and Regional Planning BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-ba.aspx","Geography BA (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-bsc.aspx","Geography BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-msci.aspx","Geography MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-msci-international.aspx","Geography with International Year MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-ba-abroad.aspx","Geography with Year Abroad BA (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-bsc-abroad.aspx","Geography with Year Abroad BSc (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-geography.aspx","Geology and Physical Geography BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-physical-geography-msci.aspx","Geology and Physical Geography MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geology.aspx","Geology BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-msci.aspx","Geology MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-international.aspx","Geology with an International Year MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/german-studies-and-history.aspx","German Studies and History BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/HealthcareEthicsandLaw-IntercalatedDegree.aspx","Healthcare Ethics and Law BMedSc - Intercalated Degree","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/hispanic-studies-and-history.aspx","Hispanic Studies and History BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/history-and-history-of-art.aspx","History and History of Art BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/history-and-philosophy.aspx","History and Philosophy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/history-and-political-science.aspx","History and Political Science BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/history-and-russian-studies.aspx","History and Russian Studies BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/history-and-theology.aspx","History and Theology and Religion BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/history/history.aspx","History BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/histart/history-art.aspx","History of Art BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/HistoryofMedicineBMedSc-IntercalatedDegree.aspx","History of Medicine BMedSc - Intercalated Degree","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/HistoryofOccupationalHealthandMedicine.aspx","History of Occupational Health and Medicine",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/human-biology.aspx","Human Biology BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/human-biology-msci.aspx","Human Biology MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/human-biology-international-year.aspx","Human Biology with an International Year BSc (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/human-biology-placement.aspx","Human Biology with Professional Placement MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/psychology/human-neuroscience-bsc.aspx","Human Neuroscience BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/international-business.aspx","International Business BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/international-business-comms.aspx","International Business with Communications BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/international-business-lang.aspx","International Business with Language BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/InternationalHealthBMedSc-IntercalatedDegree.aspx","International Health BMedSc - Intercalated Degree","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/InternationalHealthResearchDesign.aspx","International Health Research Design",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/InternationalHealthResearchProject.aspx","International Health Research Project",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/InternationalHealthcareOrganisationModule.aspx","International Healthcare Organisation Module",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations.aspx","International Relations BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-economics.aspx","International Relations with Economics BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-economics-year-abroad.aspx","International Relations with Economics with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-french.aspx","International Relations with French BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-german.aspx","International Relations with German BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-political-science.aspx","International Relations with Political Science BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-political-science-year-abroad.aspx","International Relations with Political Science with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-spanish.aspx","International Relations with Spanish BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/international-relations-year-abroad.aspx","International Relations with Year Abroad BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/kpmg-school-leavers-programme-accounting-finance.aspx","KPMG School Leavers' Programme - BSc Accountancy","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/law-llb.aspx","Law LLB","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/law-business.aspx","Law with Business Studies LLB","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/law-criminology.aspx","Law with Criminology LLB","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/law-french.aspx","Law with French Law LLB","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/law-german.aspx","Law with German Law LLB","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/arts-science.aspx","Liberal Arts & Sciences BA / BSc / MArt / MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/llb-graduates.aspx","LLB for Graduates","24"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/law/international-law-globalisation.aspx","LLB International Law and Globalisation","36"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/materials-engineering-meng.aspx","Materials Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/materials-engineering-meng.aspx","Materials Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/materials-engineering-industrial-meng.aspx","Materials Engineering with Industrial Experience MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/materials-engineering-industrial-meng.aspx","Materials Engineering with Industrial Experience MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/materials-science-energy-beng.aspx","Materials Science and Energy Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/materials-science-energy-beng.aspx","Materials Science and Energy Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/materials-science-energy-meng.aspx","Materials Science and Energy Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/materials-science-energy-meng.aspx","Materials Science and Energy Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/materials-science-engineering-business-beng.aspx","Materials Science and Engineering with Business Management BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/materials-science-engineering-business-beng.aspx","Materials Science and Engineering with Business Management BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/materials-science-engineering-business-meng.aspx","Materials Science and Engineering with Business Management MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/materials-science-engineering-business-meng.aspx","Materials Science and Engineering with Business Management MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/materials-science-technology.aspx","Materials Science and Technology BEng","36"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/materials-science-technology.aspx","Materials Science and Technology BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/maths-econ-stats-bsc.aspx","Mathematical Economics and Statistics BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/maths-computer-science.aspx","Mathematics and Computer Science BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/maths-computer-science-msci.aspx","Mathematics and Computer Science MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/maths-computer-science-industrial.aspx","Mathematics and Computer Science with Industrial Year BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/maths-computer-science-industrial-msci.aspx","Mathematics and Computer Science with Industrial Year MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/mathematics-and-music.aspx","Mathematics and Music BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/mathematics-and-philosophy.aspx","Mathematics and Philosophy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/mathematics.aspx","Mathematics BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/mathematics-MSci.aspx","Mathematics MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/mathematics-business.aspx","Mathematics with Business Management BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/mathematics-business-msci.aspx","Mathematics with Business Management MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/maths/mathematics-europe.aspx","Mathematics with Study in Continental Europe BSc","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/mechanical-materials-engineering-beng.aspx","Mechanical and Materials Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/mechanical-materials-engineering-beng.aspx","Mechanical and Materials Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/mechanical-materials-engineering-meng.aspx","Mechanical and Materials Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/mechanical-materials-engineering-meng.aspx","Mechanical and Materials Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/mechanical-engineering/mechanical-engineering-automotive-beng.aspx","Mechanical Engineering (Automotive) BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/mechanical-engineering/mechanical-engineering-automotive-meng.aspx","Mechanical Engineering (Automotive) MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/mechanical-engineering/mechanical-engineering-beng.aspx","Mechanical Engineering BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/mechanical-engineering/mechanical-engineering-meng.aspx","Mechanical Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/mechanical-engineering/mechanical-engineering-industrial-meng.aspx","Mechanical Engineering with Industrial Year MEng","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/biosciences/medical-biochemistry.aspx","Medical Biochemistry BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/medical-science-2014.aspx","Medical Science BMedSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/medical-sciences-BMedSc-IntercalatedDegree.aspx","Medical Sciences BMedSc - Intercalated Degree","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/MedicineandSociety,1750-1950.aspx","Medicine and Society, 1750 - 1950",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/medicine.aspx","Medicine and Surgery MBChB","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/medicine-grad-entry.aspx","Medicine and Surgery MBChB Graduate Entry Course","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/mental-health-and-deafness.aspx","Mental Health and Deafness Cert/Advanced Cert",""},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/metallurgy-beng.aspx","Metallurgy BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/metallurgy-beng.aspx","Metallurgy BEng","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/lang/modern-languages-ou-pathway.aspx","Modern Languages (University of Birmingham with The Open University pathway) BA","60"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/modern-languages-and-english.aspx","Modern Languages and English BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/modern-languages-and-history-of-art.aspx","Modern Languages and History of Art BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/modern-languages-and-music.aspx","Modern Languages and Music BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/lang/modern-languages.aspx","Modern Languages BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/lang/mod-lang-business.aspx","Modern Languages with Business Management BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/money-banking-finance-bsc.aspx","Money, Banking and Finance BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/money-banking-finance-german.aspx","Money, Banking and Finance with German BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/money-banking-finance-italian.aspx","Money, Banking and Finance with Italian BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/money-banking-finance-portuguese.aspx","Money, Banking and Finance with Portuguese BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/econ/money-banking-finance-spanish.aspx","Money, Banking and Finance with Spanish BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/music/music.aspx","Music BMus","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/NeurobiologyofMentalIllness.aspx","Neurobiology of Mental Illness",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/NeurobiologyofPsychologicalProcesses.aspx","Neurobiology of Psychological Processes",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/NonCommunicableDiseasesinInternationalHealth.aspx","Non Communicable Diseases in International Health",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/nuclear-engineering-meng.aspx","Nuclear Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/nuclear-engineering-meng.aspx","Nuclear Engineering MEng","48"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/nuclear-science-materials-bsc.aspx","Nuclear Science and Materials BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/nuclear-science-materials-bsc.aspx","Nuclear Science and Materials BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/nursing.aspx","Nursing BNurs","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/palaeobiology-environment-bsc.aspx","Palaeobiology and Palaeoenvironments BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/palaeobiology-palaeoenvironments-msci.aspx","Palaeobiology and Palaeoenvironments MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/pharmacy-4-year.aspx","Pharmacy MPharm (4 year)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/philosophy-and-sociology.aspx","Philosophy and Sociology BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/phil/philosophy.aspx","Philosophy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/phil/philosophy-religion-ethics.aspx","Philosophy, Religion and Ethics BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-international-bsc.aspx","Physics (International Study) BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-international-msci.aspx","Physics (International Study) MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-astrophysics-international-bsc.aspx","Physics and Astrophysics (International Study) BSc","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-astrophysics-bsc.aspx","Physics and Astrophysics BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-astrophysics-msci.aspx","Physics and Astrophysics MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-bsc.aspx","Physics BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-msci.aspx","Physics MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-particle-cosmology-bsc.aspx","Physics with Particle Physics and Cosmology BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/physics-particle-cosmology-msci.aspx","Physics with Particle Physics and Cosmology MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/sportex/physiotherapy.aspx","Physiotherapy BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/planning-economics.aspx","Planning and Economics BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/planning-social-policy.aspx","Planning and Social Policy BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/policy-politics-economics-year-abroad.aspx","Policy, Politics and Economics with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-economy.aspx","Political Economy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-economy-year-abroad.aspx","Political Economy with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science-philosophy.aspx","Political Science and Philosophy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science-philosophy-year-abroad.aspx","Political Science and Philosophy with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science-social-policy-year-abroad.aspx","Political Science and Social Policy with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science-sociology.aspx","Political Science and Sociology BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science-sociology-year-abroad.aspx","Political Science and Sociology with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science.aspx","Political Science BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/political-science-year-abroad.aspx","Political Science with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/thr/politics-religion-philosophy.aspx","Politics, Religion and Philosophy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/sportex/professional-golf-studies.aspx","Professional Golf Studies FdSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/PsychiatryPsychopathologyintheArts.aspx","Psychiatry & Psychopathology in the Arts",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/PsychologicalMedicineBMedSc-IntercalatedDegree.aspx","Psychological Medicine BMedSc - Intercalated Degree","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/PsychologicalMedicineResearchDesign.aspx","Psychological Medicine Research Design",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/PsychologicalMedicineResearchProject.aspx","Psychological Medicine Research Project",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/psychology/psychology-practice.aspx","Psychology and Psychological Practice MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/psychology/psychology-research.aspx","Psychology and Psychological Research MSci (Hons)","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/psychology/psychology.aspx","Psychology BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/PublicHealthPopulationScienceBMedSc-IntercalatedDegree.aspx","Public Health & Population Sciences BMedSc - Intercalated Degree","12"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/QuantitativeandQualitativeResearchMethods.aspx","Quantitative and Qualitative Research Methods",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/ResearchDesign-PublicHealthandPopulation.aspx","Research Design",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/ResearchMethodologiesintheHistoryofMedicine.aspx","Research Methodologies in the History of Medicine",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/ResearchMethodsinBioethics.aspx","Research Methods in Bioethics",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/ResearchProject.aspx","Research Project",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/ResearchProject-PublicHealthandPopulationSciences.aspx","Research Project",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/jointhonours/russian-studies-and-international-relations.aspx","Russian Studies and International Relations BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-policy-and-criminology.aspx","Social Policy and Criminology BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-policy-sociology.aspx","Social Policy and Sociology BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-policy-ba.aspx","Social Policy BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-policy-year-abroad-ba.aspx","Social Policy with Year Abroad BA","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-policy-policing-community-justice.aspx","Social Policy: Crime, Policing and Community Justice BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-policy-health-social-care.aspx","Social Policy: Health and Social Care BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-policy-housing.aspx","Social Policy: Housing and Communities BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/social-policy/social-work.aspx","Social Work BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/govsoc/sociology.aspx","Sociology BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/gees/spatial-planning-business.aspx","Spatial Planning and Business Management BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/sportex/sport-exercise-sciences.aspx","Sport and Exercise Sciences BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/sportex/sport-pe-coaching.aspx","Sport, Physical Education and Coaching Science BSc (Hons)","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/metallurgy-materials/sports-materials-science.aspx","Sports and Materials Science Joint Honours BSc","36"},
			{"http://www.birmingham.ac.uk/schools/metallurgy-materials/undergraduate-courses/courses/sports-materials-science.aspx","Sports and Materials Science Joint Honours BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/TheHistoryofMedicalInstitutions,1700-1950.aspx","The History of Medical Institutions, 1700-1950",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/med/Modules-intercalateddegreesPOSH/TheHistoryofMedicalSpecialisation,1700-1950.aspx","The History of Medical Specialisation, 1700-1950",""},
			{"http://www.birmingham.ac.uk/undergraduate/courses/thr/theology.aspx","Theology and Religion BA","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/theoretical-physics-applied-maths.aspx","Theoretical Physics and Applied Mathematics BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/theoretical-physics-applied-maths-msci.aspx","Theoretical Physics and Applied Mathematics MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/theoretical-physics-bsc.aspx","Theoretical Physics BSc","36"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/physics/theoretical-physics-msci.aspx","Theoretical Physics MSci","48"},
			{"http://www.birmingham.ac.uk/undergraduate/courses/business/undergraduates-degrees-in-singapore.aspx","Undergraduate degrees in Singapore","24"}
		};

}
