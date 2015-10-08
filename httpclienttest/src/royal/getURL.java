package royal;

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
	//https://www.royalholloway.ac.uk/coursecatalogue/home.aspx?keywords=&type=undergraduate&department=&page=26&sort=0
	//1-26

	//https://www.royalholloway.ac.uk/coursecatalogue/home.aspx?keywords=&type=postgraduate+taught&department=&page=1&sort=0
	//1-9
	public static String url1="https://www.royalholloway.ac.uk/coursecatalogue/home.aspx?keywords=&type=undergraduate&department=&page=";
	public static String url2="&sort=0";
	public static String url3="https://www.royalholloway.ac.uk/coursecatalogue/home.aspx?keywords=&type=postgraduate+taught&department=&page=";
	public static void main(String[] args) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		for(int i=1;i<=9;i++)
		{
			HttpGet httpGet = new HttpGet(url3+i+url2); 
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			Parser parser=Parser.createParser(htmls, "utf-8");
		    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("div"),
	                new HasAttributeFilter("class","sys_subitem"));
	        NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
	        //System.out.println("Size:"+nodes2.size());
	        if(nodes2.size()>0)
	        {
	        	for(int j=0;j<nodes2.size();j++)
	        	{
	        		
	        		//***************get links;name;type
	        		Node node=(Node)nodes2.elementAt(j);
	        		//System.out.println(node.toHtml());
	        		AndFilter linkFilter=new AndFilter(new TagNameFilter("a"),
	                        new HasAttributeFilter("href"));
	        		parser=Parser.createParser(node.toHtml(), "utf-8");
	                NodeList nodes3 = parser.extractAllNodesThatMatch(linkFilter);
	                Node aNode=nodes3.elementAt(0);
	                //System.out.println(aNode.toHtml());
	                LinkTag l=(LinkTag)aNode;
	                String name=HTMLFilter(html2Str(aNode.toHtml().replace(" <br />", "\",\"")).trim());
	                if(name.split("\\(").length>=1)
	                System.out.println("{\"https://www.royalholloway.ac.uk"+l.getAttribute("href")+"\",\""+name+"\",\""+name.split("\\(")[name.split("\\(").length-1].split("\\)")[0]+"\"},");
	                else
	                	System.out.println("{\"https://www.royalholloway.ac.uk"+l.getAttribute("href")+"\",\""+name+"\",\"\"},");	
	                
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
		
	public static String[][] UnData={
		{"https://www.royalholloway.ac.uk/management/coursefinder/bsc-accounting-finance.aspx","Accounting & Finance (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bsc-accounting-finance-yib.aspx","Accounting & Finance (Year in Business) (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscaccountingfinanceeconomics.aspx","Accounting, Finance & Economics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baancientandmedievalhistory.aspx","Ancient and Medieval History (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baancienthistory.aspx","Ancient History (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baancienthistoryphilosophy.aspx","Ancient History and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baancienthistorywithphilosophy.aspx","Ancient History with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/psychology/coursefinder/bscappliedpsychology.aspx","Applied Psychology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/bscastrophysics.aspx","Astrophysics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/msciastrophysics.aspx","Astrophysics (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/biologicalsciences/coursefinder/bscbiochemistry.aspx","Biochemistry (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/biologicalsciences/coursefinder/bscbiology.aspx","Biology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/biologicalsciences/coursefinder/bscbiomedicalsciences.aspx","Biomedical Sciences (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscbusinessandmanagement.aspx","Business and Management (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementyearinbusiness.aspx","Business and Management (Year in Business) (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicalstudies.aspx","Classical Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicalstudiesclc.aspx","Classical Studies and Comparative Literature and Culture (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicalstudiesdrama.aspx","Classical Studies and Drama (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicalstudiesitalian.aspx","Classical Studies and Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicalstudiesphilosophy.aspx","Classical Studies and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicalstudiesspanish.aspx","Classical Studies and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicalstudieswithphilosophy.aspx","Classical Studies with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassics.aspx","Classics (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicsphilosophy.aspx","Classics and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/baclassicswithphilosophy.aspx","Classics with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculture.aspx","Comparative Literature and Culture (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturedrama.aspx","Comparative Literature and Culture and Drama (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteraturecultureenglish.aspx","Comparative Literature and Culture and English (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturefrench.aspx","Comparative Literature and Culture and French (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturegerman.aspx","Comparative Literature and Culture and German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteraturecultureitalian.aspx","Comparative Literature and Culture and Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturephilosophy.aspx","Comparative Literature and Culture and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturespanish.aspx","Comparative Literature and Culture and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureandcultureandvisualcultures.aspx","Comparative Literature and Culture and Visual Cultures (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturewithinternationalfilm.aspx","Comparative Literature and Culture with International Film (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturewithphilosophy.aspx","Comparative Literature and Culture with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bacomparativeliteratureculturewithvisualarts.aspx","Comparative Literature and Culture with Visual Arts (BA)","BA"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputerscience.aspx","Computer Science (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputerscience.aspx","Computer Science (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputerscience(yearinindustry).aspx","Computer Science (Year in Industry) (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputerscience(yearinindustry).aspx","Computer Science (Year in Industry) (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputersciencemathematics.aspx","Computer Science and Mathematics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputerscienceartificialintelligence.aspx","Computer Science Artificial Intelligence (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputerscienceartificialintelligence.aspx","Computer Science Artificial Intelligence (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputerscienceartificialintelligence(yearinindustry).aspx","Computer Science Artificial Intelligence (Year in Industry) (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputerscienceartificialintelligence(yearinindustry).aspx","Computer Science Artificial Intelligence (Year in Industry) (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputerscienceinformationsecurity.aspx","Computer Science Information Security (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputerscienceinformationsecurity.aspx","Computer Science Information Security (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputerscienceinformationsecurity(yearinindustry).aspx","Computer Science Information Security (Year in Industry) (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputerscienceinformationsecurity(yearinindustry).aspx","Computer Science Information Security (Year in Industry) (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputersciencewithmanagement.aspx","Computer Science with Management (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputingbusiness.aspx","Computing and Business (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/criminologyandsociology/coursefinder/bsccriminologyandpsychology.aspx","Criminology and Psychology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/criminologyandsociology/coursefinder/bsccriminologysociology.aspx","Criminology and Sociology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/dance(ba).aspx","Dance (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/badigitalmediacommunications.aspx","Digital Media Communications (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/bscdigitalmediacommunications.aspx","Digital Media Communications (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramacreativewriting.aspx","Drama and Creative Writing (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramadance.aspx","Drama and Dance (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramagerman.aspx","Drama and German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramaitalian.aspx","Drama and Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramamusic.aspx","Drama and Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramaphilosophy.aspx","Drama and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramaspanish.aspx","Drama and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramatheatrestudies.aspx","Drama and Theatre Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/badramawithphilosophy.aspx","Drama with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/biologicalsciences/coursefinder/bscecologyenvironment.aspx","Ecology and Environment (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomics.aspx","Economics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicsmanagement.aspx","Economics and Management (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicsmathematics.aspx","Economics and Mathematics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicswithfrench.aspx","Economics with French (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicswithgerman.aspx","Economics with German (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicswithitalian.aspx","Economics with Italian (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicswithmusic.aspx","Economics with Music (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicswithpoliticalstudies.aspx","Economics with Political Studies (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicswithspanish.aspx","Economics with Spanish (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bsceconomicspoliticsinternationalrelations.aspx","Economics, Politics and International Relations (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglish.aspx","English (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishandamericanliterature.aspx","English and American Literature (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishclassicalstudies.aspx","English and Classical Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishcreativewriting.aspx","English and Creative Writing (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishdrama.aspx","English and Drama (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishfilmstudies.aspx","English and Film Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishfrench.aspx","English and French (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishgerman.aspx","English and German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishandhistory.aspx","English and History (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishitalian.aspx","English and Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishlatin.aspx","English and Latin (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishphilosophy.aspx","English and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishspanish.aspx","English and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/english/coursefinder/baenglishwithphilosophy.aspx","English with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/bscenvironmentalgeology.aspx","Environmental Geology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/bscenvironmentalgeologywithayearinindustry.aspx","Environmental Geology with a Year in Industry (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscienvironmentalgeoscience.aspx","Environmental Geoscience (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscienvironmentalgeosciencewithayearinindustry.aspx","Environmental Geoscience with a Year in Industry (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscienvironmentalgeosciencewithaninternationalyear.aspx","Environmental Geoscience with an International Year (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/europeanstudiesandinternationalstudiesfrenchba2016.aspx","European and International Studies (French) (BA) 2016","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/europeanstudiesandinternationalstudiesgermanba2016.aspx","European and International Studies (German) (BA) 2016","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/europeanstudiesandinternationalstudiesitalianba2016.aspx","European and International Studies (Italian) (BA) 2016","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/europeanstudiesandinternationalstudiesspanishba2016.aspx","European and International Studies (Spanish) (BA) 2016","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/baeuropeanstudiesfrench.aspx","European Studies - French (BA)","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/baeuropeanstudiesgerman.aspx","European Studies - German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/baeuropeanstudiesitalian.aspx","European Studies - Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/baeuropeanstudiesspanish.aspx","European Studies - Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/europeanstudies/coursefinder/europeanstudiespolitics,societyculture(ba).aspx","European Studies: Politics, Society & Culture (BA)","BA"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/bscexperimentalphysics.aspx","Experimental Physics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/msciexperimentalphysics.aspx","Experimental Physics (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/bafilmstudies.aspx","Film Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/bafilmstudieswithphilosophy.aspx","Film Studies with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/bafilmtelevisionanddigitalproduction.aspx","Film, Television and Digital Production (BA) (formerly BA Media Arts)","formerly BA Media Arts"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bscfinancemathematics.aspx","Finance and Mathematics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/economics/coursefinder/bscfinancialbusinesseconomics.aspx","Financial and Business Economics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrench4years.aspx","French - 4 years (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchclassicalstudies.aspx","French and Classical Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchdrama.aspx","French and Drama (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchgerman.aspx","French and German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchgreek.aspx","French and Greek (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchhistory.aspx","French and History (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchitalian.aspx","French and Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchlatin.aspx","French and Latin (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchmanagement.aspx","French and Management (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchmusic.aspx","French and Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchphilosophy.aspx","French and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchspanish.aspx","French and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchandvisualcultures.aspx","French and Visual Cultures","French and Visual Cultures"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithgerman.aspx","French with German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithinternationalfilm.aspx","French with International Film (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithinternationalrelations.aspx","French with International Relations (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithitalian.aspx","French with Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithmathematics.aspx","French with Mathematics (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithmusic.aspx","French with Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithphilosophy.aspx","French with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithpoliticalstudies.aspx","French with Political Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithspanish.aspx","French with Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bafrenchwithvisualarts.aspx","French with Visual Arts (BA)","BA"},
		{"https://www.royalholloway.ac.uk/geography/coursefinder/bageography.aspx","Geography (BA)","BA"},
		{"https://www.royalholloway.ac.uk/geography/coursefinder/bscgeography.aspx","Geography (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/geography/coursefinder/bscgeographypir.aspx","Geography, Politics and International Relations (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/bscgeology.aspx","Geology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/bscgeologywithayearinindustry.aspx","Geology with a Year in Industry (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscigeoscience.aspx","Geoscience (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscigeosciencewithayearinindustry.aspx","Geoscience with a Year in Industry (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscigeosciencewithayearofinternationalstudy.aspx","Geoscience with a Year of International Study (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanclassicalstudies.aspx","German and Classical Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermangreek.aspx","German and Greek (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanhistory.aspx","German and History (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanitalian.aspx","German and Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanmanagement.aspx","German and Management (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanmusic.aspx","German and Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanphilosophy.aspx","German and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanspanish.aspx","German and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bagermanandvisualcultures.aspx","German and Visual Cultures (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/bagreek.aspx","Greek (BA)","BA"},
		{"https://www.royalholloway.ac.uk/history/coursefinder/bahistory.aspx","History (BA)","BA"},
		{"https://www.royalholloway.ac.uk/history/coursefinder/bahistoryinternationalrelations.aspx","History and International Relations (BA)","BA"},
		{"https://www.royalholloway.ac.uk/history/coursefinder/bahistorymusic.aspx","History and Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/history/coursefinder/bahistoryandphilosophy.aspx","History and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/history/coursefinder/bahistoryspanish.aspx","History and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/history/coursefinder/bahistorywithaninternationalyear.aspx","History with an International Year (BA)","BA"},
		{"https://www.royalholloway.ac.uk/geography/coursefinder/bahumangeography.aspx","Human Geography (BA)","BA"},
		{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/bainternationalrelations.aspx","International Relations (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baitalianmanagement.aspx","Italian and Management (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baitalianmusic.aspx","Italian and Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baitalianphilosophy.aspx","Italian and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baitalianspanish.aspx","Italian and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baitalianandvisualcultures.aspx","Italian and Visual Cultures (BA)","BA"},
		{"https://www.royalholloway.ac.uk/classics/coursefinder/balatin.aspx","Latin (BA)","BA"},
		{"https://www.royalholloway.ac.uk/criminologyandsociology/coursefinder/llblaw.aspx","Law (LLB)","LLB"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baliberalarts.aspx","Liberal Arts (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baliberalartswithaperiodofresidenceabroad.aspx","Liberal Arts with a Period of Residence Abroad (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baliberalartswithaninternationalyear.aspx","Liberal Arts with an International Year (BA)","BA"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bamanagementspanish.aspx","Management and Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithaccounting.aspx","Management with Accounting (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithaccountingyearinbusiness.aspx","Management with Accounting (BSc) (Year in Business)","Year in Business"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithentrepreneurship.aspx","Management with Entrepreneurship (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithentrepreneurshipyearinbusiness.aspx","Management with Entrepreneurship (BSc) (Year in Business)","Year in Business"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithhumanresources.aspx","Management with Human Resources (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithhumanresourcesyearinbusiness.aspx","Management with Human Resources (BSc) (Year in Business)","Year in Business"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithinformationsystems.aspx","Management with Information Systems (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithinformationsystemsyearinbusiness.aspx","Management with Information Systems (BSc) (Year in Business)","Year in Business"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithinternationalbusiness.aspx","Management with International Business (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithinternationalbusinessyearinbusiness.aspx","Management with International Business (BSc) (Year in Business)","Year in Business"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithmarketing.aspx","Management with Marketing (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithmarketingyearinbusiness.aspx","Management with Marketing (BSc) (Year in Business)","Year in Business"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithmathematics.aspx","Management with Mathematics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithsustainability.aspx","Management with Sustainability (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/management/coursefinder/bscmanagementwithsustainabilityyearinbusiness.aspx","Management with Sustainability (BSc) (Year in Business)","Year in Business"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicalstudies.aspx","Mathematical Studies (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematics.aspx","Mathematics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/mscimathematics.aspx","Mathematics (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicsmanagement.aspx","Mathematics and Management (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bamathematicsmusic.aspx","Mathematics and Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicsphysics.aspx","Mathematics and Physics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/mscimathematicsphysics.aspx","Mathematics and Physics (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicswithfrench.aspx","Mathematics with French (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicswithgerman.aspx","Mathematics with German (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicswithitalian.aspx","Mathematics with Italian (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicswithmanagement.aspx","Mathematics with Management (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicswithphilosophy.aspx","Mathematics with Philosophy (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicswithspanish.aspx","Mathematics with Spanish (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mathematics/coursefinder/bscmathematicswithstatistics.aspx","Mathematics with Statistics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/biologicalsciences/coursefinder/bscmedicalbiochemistry.aspx","Medical Biochemistry (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/history/coursefinder/bamodernhistorypolitics.aspx","Modern History and Politics (BA)","BA"},
		{"https://www.royalholloway.ac.uk/biologicalsciences/coursefinder/bscmolecularbiology.aspx","Molecular Biology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bamultilingualstudies.aspx","Multilingual Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bamultilingualstudieswithinternationalrelations.aspx","Multilingual Studies with International Relations (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bamultilingualstudieswithphilosophy.aspx","Multilingual Studies with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/bamultilingualstudieswithvisualarts.aspx","Multilingual Studies with Visual Arts (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bmusmusic.aspx","Music (BMus)","BMus"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicandenglish.aspx","Music and English (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicphilosophy.aspx","Music and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicwithfrench.aspx","Music with French (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicwithgerman.aspx","Music with German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicwithitalian.aspx","Music with Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicwithphilosophy.aspx","Music with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicwithpoliticalstudies.aspx","Music with Political Studies (BA)","BA"},
		{"https://www.royalholloway.ac.uk/music/coursefinder/bamusicwithspanish.aspx","Music with Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/bscpetroleumgeology.aspx","Petroleum Geology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/philosophy/coursefinder/baphilosophy.aspx","Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/geography/coursefinder/bscphysicalgeography.aspx","Physical Geography (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/geography/coursefinder/bscphysicalgeographygeology.aspx","Physical Geography and Geology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/bscphysics.aspx","Physics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/msciphysics.aspx","Physics (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/bscphysicswithmusic.aspx","Physics with Music (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/bscphysicswithparticlephysics.aspx","Physics with Particle Physics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/msciphysicswithparticlephysics.aspx","Physics with Particle Physics (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/bscphysicswithphilosophy.aspx","Physics with Philosophy (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/bapolitics.aspx","Politics (BA)","BA"},
		{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/bapoliticsinternationalrelations.aspx","Politics and International Relations (BA)","BA"},
		{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/bapoliticsandinternationalrelationsandphilosophy.aspx","Politics and International Relations and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/bapoliticswithphilosophy.aspx","Politics with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/bapoliticsphilosophyeconomics.aspx","Politics, Philosophy and Economics (PPE) (BA)","BA"},
		{"https://www.royalholloway.ac.uk/psychology/coursefinder/bscpsychology.aspx","Psychology (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/psychology/coursefinder/bscpsychologyclinicalandcognitiveneuroscience.aspx","Psychology, Clinical and Cognitive Neuroscience (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/psychology/coursefinder/bscpsychologyclinicalpsychologyandmentalhealth.aspx","Psychology, Clinical Psychology and Mental Health (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/psychology/coursefinder/bscpsychologydevelopmentanddevelopmentaldisorders.aspx","Psychology, Development and Developmental Disorders(BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputersciencesoftwareengineering.aspx","Software Engineering (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputerscience(softwareengineering).aspx","Software Engineering (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/bsccomputersciencesoftwareengineering(yearinindustry).aspx","Software Engineering (Year in Industry) (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscicomputersciencesoftwareengineering(yearinindustry).aspx","Software Engineering (Year in Industry) (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanish.aspx","Spanish (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishandmusic.aspx","Spanish and Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishphilosophy.aspx","Spanish and Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishandvisualcultures.aspx","Spanish and Visual Cultures (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithfrench.aspx","Spanish with French (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithgerman.aspx","Spanish with German (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithinternationalfilm.aspx","Spanish with International Film (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithinternationalrelations.aspx","Spanish with International Relations (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithitalian.aspx","Spanish with Italian (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithmusic.aspx","Spanish with Music (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithphilosophy.aspx","Spanish with Philosophy (BA)","BA"},
		{"https://www.royalholloway.ac.uk/mllc/coursefinder/baspanishwithvisualarts.aspx","Spanish with Visual Arts (BA)","BA"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/bsctheoreticalphysics.aspx","Theoretical Physics (BSc)","BSc"},
		{"https://www.royalholloway.ac.uk/physics/coursefinder/mscitheoreticalphysics.aspx","Theoretical Physics (MSci)","MSci"},
		{"https://www.royalholloway.ac.uk/biologicalsciences/coursefinder/bsczoology.aspx","Zoology (BSc)","BSc"}
	};
    public static String[][] PostData={
    	{"https://www.royalholloway.ac.uk/music/coursefinder/mmusadvancedmusicalstudies.aspx","Advanced Musical Studies (MMus)","MMus"},
    	{"https://www.royalholloway.ac.uk/socialwork/coursefinder/mscadvancedpractice.aspx","Advanced Practice (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/classics/coursefinder/maancienthistory.aspx","Ancient History (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/maappliedandparticipatorytheatre.aspx","Applied and Participatory Theatre (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/psychology/coursefinder/mscappliedsocialpsychology.aspx","Applied Social Psychology (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscbusinessinformationsystems.aspx","Business Information Systems (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/classics/coursefinder/maclassicalartandarchaeology.aspx","Classical Art and Archaeology (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/classics/coursefinder/maclassics.aspx","Classics (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/computerscience/coursefinder/msccomputationalfinance.aspx","Computational Finance (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/computerscience/coursefinder/msccomputationalfinance(yearinindustry).aspx","Computational Finance (Year in Industry) (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/maconsumption,cultureandmarketing.aspx","Consumption, Culture and Marketing (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/macontemporaryperformancepractices.aspx","Contemporary Performance Practices (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscpgdippoliticaltheory.aspx","Contemporary Political Theory (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/english/coursefinder/macreativewriting.aspx","Creative Writing (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/history/coursefinder/macrusaderstudies.aspx","Crusader Studies (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/geography/coursefinder/maculturalgeography.aspx","Cultural Geography (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscdatascienceandanalytics.aspx","Data Science and Analytics (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscdatascienceandanalytics(yearinindustry).aspx","Data Science and Analytics (Year in Industry) (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/madocumentarybypractice.aspx","Documentary by Practice (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/economics/coursefinder/msceconomics2yearprogramme.aspx","Economics (MSc 2-year programme)","MSc 2-year programme"},
    	{"https://www.royalholloway.ac.uk/economics/coursefinder/msceconomics.aspx","Economics (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscelections,publicopinionandparties.aspx","Elections, Public Opinion and Parties (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/english/coursefinder/maenglish.aspx","English Literature (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscentrepreneurship.aspx","Entrepreneurship (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscentrepreneurshipyib.aspx","Entrepreneurship (MSc) Year in Business","MSc"},
    	{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscenvironmentaldiagnosismanagement.aspx","Environmental Diagnosis and Management (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/philosophy/coursefinder/maeuropeanphilosophy.aspx","European Philosophy (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/economics/coursefinder/mscfinance2yearprog.aspx","Finance (MSc 2-year programme)","MSc 2-year programme"},
    	{"https://www.royalholloway.ac.uk/economics/coursefinder/mscfinance.aspx","Finance (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/geography/coursefinder/mscgeopoliticsandsecurity.aspx","Geopolitics and Security (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/history/coursefinder/mahistory.aspx","History (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/history/coursefinder/mahistoryhellenicstudies.aspx","History: Hellenic Studies (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/history/coursefinder/maholocauststudies.aspx","Holocaust Studies (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/isg/coursefinder/mscinformationsecurity.aspx","Information Security (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscinternationalaccounting(msc).aspx","International Accounting (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscihrm.aspx","International Human Resource Management (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mbainternationalmanagement.aspx","International Management (MBA)","MBA"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mbainternationalmanagementyib.aspx","International Management (MBA) Year in Business","MBA"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscinternationalmanagement.aspx","International Management (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscinternationalpublicpolicy.aspx","International Public Policy (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscpgdipinternationalrelations.aspx","International Relations (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/physics/coursefinder/mscintlsupplychainmgmt.aspx","International Supply Chain Management (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/mainternationaltelevisionindustries.aspx","International Television Industries (MA) (formerly MA International Broadcasting)","formerly MA International Broadcasting"},
    	{"https://www.royalholloway.ac.uk/history/coursefinder/malateantiquebyzantinestudies.aspx","Late Antique and Byzantine Studies (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscmachinelearning.aspx","Machine Learning (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/computerscience/coursefinder/mscmachinelearning(yearinindustry).aspx","Machine Learning (Year in Industry) (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscmanagementandorganisations.aspx","Management and Organisations (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mamarketing.aspx","Marketing (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mastersbyresearch.aspx","Masters by Research","Masters by Research"},
    	{"https://www.royalholloway.ac.uk/mathematics/coursefinder/mscmathematicsforapplications.aspx","Mathematics for Applications (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/mathematics/coursefinder/mscmathematicsofcryptographyandcommunications(msc).aspx","Mathematics of Cryptography and Communications (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscpgdipmediapowerandpublicaffairs.aspx","Media, Power and Public Affairs (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/english/coursefinder/mamedievalstudies.aspx","Medieval Studies (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/philosophy/coursefinder/mamodernphilosophy.aspx","Modern Philosophy (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/english/coursefinder/mamodernismandcontemporaryliterature.aspx","Modernism and Contemporary Literature (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/criminologyandsociology/coursefinder/mscforensicpsychology.aspx","MSc Forensic Psychology","MSc Forensic Psychology"},
    	{"https://www.royalholloway.ac.uk/computerscience/coursefinder/msctheinternetofthingsyearinindustry.aspx","MSc The Internet of Things (Year in Industry)","Year in Industry"},
    	{"https://www.royalholloway.ac.uk/music/coursefinder/pgdipmusicperformance.aspx","Music Performance (PGDip)","PGDip"},
    	{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscpetroleumgeoscience.aspx","Petroleum Geoscience (Basin Evolution or Tectonics) (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/earthsciences/coursefinder/mscpetroleumgeosciencedistancelearning.aspx","Petroleum Geoscience (By Distance Learning) (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/pgcertphysicaltheatrefordancersandactors.aspx","Physical Theatre for Dancers and Actors (PGCert)","PGCert"},
    	{"https://www.royalholloway.ac.uk/physics/coursefinder/mscphysics(euromasters).aspx","Physics (EuroMasters) (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/dramaandtheatre/coursefinder/maplaywriting.aspx","Playwriting (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/economics/coursefinder/mscpolicyeconomics.aspx","Policy Economics (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/philosophy/coursefinder/mapoliticalphilosophy.aspx","Political Philosophy (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscpolitics.aspx","Politics (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscpgdipdemocracypoliticsandgovernance.aspx","Politics and International Relations: Democracy, Politics and Governance (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscpgdipinternationalrelationstheory.aspx","Politics and International Relations: International Relations Theory (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/geography/coursefinder/mscpgdippractisingsustainabledevelopment.aspx","Practising Sustainable Development  (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/geography/coursefinder/mscpgdippsdict4d.aspx","Practising Sustainable Development (Information and Communication Technologies for Development) (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/pdis.aspx","Pre-Masters Diploma for International Students (PDIS)","PDIS"},
    	{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/maproducingfilmandtelevision.aspx","Producing Film and Television (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/management/coursefinder/mscprojectmanagementgeneralpathway.aspx","Project Management  (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/history/coursefinder/mapublichistory.aspx","Public History (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/geography/coursefinder/mscquaternaryscience.aspx","Quaternary Science (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/classics/coursefinder/mresrhetoric.aspx","Rhetoric (MRes)","MRes"},
    	{"https://www.royalholloway.ac.uk/mediaarts/coursefinder/mascreenwritingfortelevisionandfilmretreat.aspx","Screenwriting for Television and Film (Retreat) (MA) (April start)","April start"},
    	{"https://www.royalholloway.ac.uk/english/coursefinder/mashakespeare.aspx","Shakespeare (MA)","MA"},
    	{"https://www.royalholloway.ac.uk/socialwork/coursefinder/mscsocialwork.aspx","Social Work (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/geography/coursefinder/mscsustainabilityandmanagement.aspx","Sustainability and Management (MSc)","MSc"},
    	{"https://www.royalholloway.ac.uk/politicsandir/coursefinder/mscpgdiptransnationalsecuritystudies.aspx","Transnational Security Studies (MSc/PGDip)","MSc/PGDip"},
    	{"https://www.royalholloway.ac.uk/english/coursefinder/mavictorianliterature,artandculture.aspx","Victorian Literature, Art and Culture (MA)","MA"}
    };
}
