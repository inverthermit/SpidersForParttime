package liverpool;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class LiverpoolGetDepartment {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		for(int j=0;j<url2Department.length;j++)
		{
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url2Department[j][0]); 
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			    
			     
			}
			Parser parser=Parser.createParser(htmls, "utf-8");
			TagNameFilter atag=new TagNameFilter("a");
	        NodeList nodes3 = parser.extractAllNodesThatMatch(atag);
	        
	        if(nodes3.size()>=0)
	        {
	        	for(int i=0;i<nodes3.size();i++)
	    	    {
	        		LinkTag link=(LinkTag)nodes3.elementAt(i);
	    	    	String get1=link.getAttribute("href");
	    	    	//System.out.println(get1);
	    	    	if((get1!=null)&&get1.startsWith("http://www.liv.ac.uk/study/postgraduate/taught/"))
	    	    	{
	    	    		System.out.println("{\""+url2Department[j][1]+"\",\""+LiverpoolPostData.html2Str(link.toHtml())+"\"},");
	    	    	}
	    	    
	    	    }
	        }
		}
		

	}
	
	public void getDepartment() throws Exception
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.liv.ac.uk/study/postgraduate/courses/taught/"); 
		HttpResponse response = httpclient.execute(httpGet);  
		HttpEntity entity = response.getEntity();
		String htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity).replace("\t", " ");
		    
		     
		}
		Parser parser=Parser.createParser(htmls, "utf-8");
		TagNameFilter atag=new TagNameFilter("a");
        NodeList nodes3 = parser.extractAllNodesThatMatch(atag);
        
        if(nodes3.size()>=0)
        {
        	for(int i=0;i<nodes3.size();i++)
    	    {
        		LinkTag link=(LinkTag)nodes3.elementAt(i);
    	    	String get1=link.getAttribute("href");
    	    	//System.out.println(get1);
    	    	if((get1!=null)&&get1.startsWith("departments/"))
    	    	{
    	    		System.out.println("{\"http://www.liv.ac.uk/study/postgraduate/courses/taught/"+get1+"\",\""+LiverpoolPostData.html2Str(link.toHtml())+"\"},");
    	    	}
    	    
    	    }
        }
	}
	public static String[][] dep2Major={
		{"Archaeology Classics and Egyptology","Ancient History MA"},
		{"Archaeology Classics and Egyptology","Archaeology MA"},
		{"Archaeology Classics and Egyptology","Archaeology MSc"},
		{"Archaeology Classics and Egyptology","Classics MA"},
		{"Archaeology Classics and Egyptology","Egyptology MA"},
		{"Archaeology Classics and Egyptology","Palaeoanthropology MSc"},
		{"Architecture","Architecture MA"},
		{"Architecture","Digital Integrated Design MSc"},
		{"Architecture","Master of Architecture MArch"},
		{"Architecture","Sustainable Environmental Design in Architecture (SEDA)  MSc"},
		{"Chemistry","Advanced Chemical Sciences  MSc"},
		{"Communication and Media","Politics and the Mass Media MA"},
		{"Computer Science","Advanced Computer Science MSc/PGDip/PGCert"},
		{"Computer Science","Advanced Computer Science with Internet Economics MSc/PGDip/PGCert"},
		{"Computer Science","Big Data and High Performance Computing MSc/PGDip/PGCert"},
		{"Computer Science","Computer Science MSc/PGDip/PGCert"},
		{"Dental Sciences","Endodontics DDSc"},
		{"Dental Sciences","Orthodontics DDSc"},
		{"Earth Sciences","Petroleum Reservoir Geoscience MSc"},
		{"Earth Sciences","Sea Level: From Coast to Global Ocean MSc"},
		{"Ecology and Marine Biology","Conservation and Resource Management MRes"},
		{"Ecology and Marine Biology","Conservation and Resource Management  MSc"},
		{"Electrical Engineering and Electronics","Energy and Power Systems MSc(Eng)"},
		{"Electrical Engineering and Electronics","Microelectronic Systems MSc (Eng)"},
		{"Electrical Engineering and Electronics","Telecommunications and Wireless Systems MSc (Eng)"},
		{"Engineering","Advanced Aerospace Engineering MSc (Eng)"},
		{"Engineering","Advanced Engineering Materials  MSc(Eng)"},
		{"Engineering","Advanced Manufacturing Systems and Technology  MSc(Eng)"},
		{"Engineering","Advanced Mechanical Engineering  MSc (Eng) "},
		{"Engineering","Biomedical Engineering MSc (Eng) "},
		{"Engineering","Civil and Environmental Engineering MSc (Eng) MSc (Eng)"},
		{"Engineering","Nuclear Power Engineering MSc "},
		{"Engineering","Product Design and ManagementMSc(Eng) "},
		{"Engineering","Risk and Uncertainty MSc (Eng) "},
		{"Engineering","Simulation in Aerospace Engineering  MSc (Eng) "},
		{"Engineering","Simulation in Aerospace Engineering  MRes "},
		{"Engineering","Sustainable Structural Engineering MSc (Eng)"},
		{"English","Applied Linguistics MA"},
		{"English","English  MA"},
		{"English","English: Directed Research MA"},
		{"English","English: Modern and Contemporary Literature MA"},
		{"English","English: Renaissance and Eighteenth-Century Literature MA"},
		{"English","English: Victorian Literature MA"},
		{"English","Reading in Practice MA"},
		{"English","Teaching English to Speakers of Other Languages (TESOL) MA"},
		{"Geography","Contemporary Human Geography (Research Methods) MA"},
		{"Geography","Environment and Climate Change MSc"},
		{"Geography","Environmental Sciences  MSc"},
		{"Geography","Geographic Data Science MSc"},
		{"Geography","Population and Health MSc"},
		{"Health Sciences","Advanced Practice in Healthcare MSc/PGDip/PGCert"},
		{"Health Sciences","Radiotherapy MSc"},
		{"Health Sciences","Radiotherapy PGDip  Postgraduate Diploma"},
		{"History","Archives and Records Management MARM"},
		{"History","Archives and Records Management (International Pathway) MARMI"},
		{"History","Cultural History  MA"},
		{"History","Eighteenth-Century Worlds  MA"},
		{"History","History  MRes"},
		{"History","International Slavery Studies  MA"},
		{"History","Medieval and Renaissance Studies  MA"},
		{"History","Twentieth-Century History  MA"},
		{"Institute of Ageing and Chronic Disease","Clinical Sciences MRes"},
		{"Institute of Ageing and Chronic Disease","Musculoskeletal Ageing  MRes"},
		{"Institute of Integrative Biology","Advanced Biological Sciences MRes"},
		{"Institute of Translational Medicine","Biomedical Sciences and Translational Medicine MRes"},
		{"Irish Studies","Irish Studies  MA/PGDip"},
		{"Irish Studies","Understanding Conflict MA"},
		{"Law","International Human Rights Law LLM/PGDip/PGCert"},
		{"Law","Law, Medicine and Healthcare LLM/PGDip/PGCert"},
		{"Law","LLM (General) LLM/PGDip/PGCert"},
		{"Life Sciences","Advanced Biological Sciences  MSc/PGDip/PGCert"},
		{"Management School","Big Data Management MSc/PGDip/PGCert MSc"},
		{"Management School","Business Administration (Football Industries) PG Cert"},
		{"Management School","Digital Business Enterprise Management MSc"},
		{"Management School","Economics MSc/PGDip/PGCert"},
		{"Management School","Entrepreneurship MSc"},
		{"Management School","Finance MSc/PGDip/PGCert"},
		{"Management School","Finance and Management  MBA"},
		{"Management School","Financial Risk Management MSc MSc"},
		{"Management School","Football Industries MBA"},
		{"Management School","Human Resource Management MSc"},
		{"Management School","International Business MSc"},
		{"Management School","Management MRes"},
		{"Management School","Management MSc"},
		{"Management School","Management and Entrepreneurship MSc"},
		{"Management School","Managing International Development MSc"},
		{"Management School","Marketing MSc"},
		{"Management School","MBA (The Liverpool MBA) on campus MBA"},
		{"Management School","Operations and Supply Chain Management MSc/PGDip"},
		{"Management School","Programme and Project Management MSc"},
		{"Management School","Thoroughbred Horseracing Industries MBA"},
		{"Mathematical Sciences","Financial Mathematics MSc/PGDip/PGCert"},
		{"Mathematical Sciences","Mathematical Sciences MSc/PGDip/PGCert"},
		{"Medicine","Medical Education PGCert/PGDip/MSc"},
		{"Medicine","Public Health MPH"},
		{"Modern Languages and Cultures","Latin American Studies (by Directed Research) MA"},
		{"Modern Languages and Cultures","Modern Languages(by Directed Research) MA"},
		{"Music","Music MMus"},
		{"Music","Music MRes"},
		{"Music","Music Industry Studies MA"},
		{"Philosophy","Art, Aesthetics and Cultural Institutions MA"},
		{"Philosophy","Philosophy MA"},
		{"Physics","Medical Physics and Clinical Engineering MSc"},
		{"Physics","Nuclear Science and Technology MSc"},
		{"Physics","Radiometrics: Instrumentation and Modelling  MSc"},
		{"Planning","Environmental Assessment and Management MSc"},
		{"Planning","Marine Planning and Management MSc"},
		{"Planning","Town and Regional Planning MA"},
		{"Planning","Town and Regional Planning MCD MCD"},
		{"Planning","Urban Regeneration and Management MSc MSc"},
		{"Politics","International Relations and Security MA"},
		{"Psychology","Doctor of Clinical Psychology DClinPsychol"},
		{"Psychology","Investigative and Forensic Psychology MSc/PGDip/PGCert"},
		{"Psychology","Research Methods in Psychology MSc/PGDip/PGCert"},
		{"Sociology, Social Policy and Criminology","Criminological Research MRes"},
		{"Sociology, Social Policy and Criminology","Social Research MRes"},
		{"Sociology, Social Policy and Criminology","Social Research Methods MA"},
		{"Veterinary Science","Bovine Reproduction DBR"},
		{"Veterinary Science","Veterinary Science MSc"},

	};
	public static String[][] url2Department={
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=archaeology-classics-and-egyptology","Archaeology Classics and Egyptology"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=architecture","Architecture"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=chemistry","Chemistry"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=communication-and-media","Communication and Media"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=computer-science","Computer Science"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=dental-sciences","Dental Sciences"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=earth-sciences","Earth Sciences"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=ecology-and-marine-biology","Ecology and Marine Biology"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=electrical-engineering-and-electronics","Electrical Engineering and Electronics"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=engineering","Engineering"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=english","English"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=geography","Geography"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=health-sciences","Health Sciences"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=history","History"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=institute-of-ageing-and-chronic-disease","Institute of Ageing and Chronic Disease"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=institute-of-integrative-biology","Institute of Integrative Biology"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=institute-of-translational-medicine","Institute of Translational Medicine"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=irish-studies","Irish Studies"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=law","Law"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=life-sciences","Life Sciences"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=management-school","Management School"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=mathematical-sciences","Mathematical Sciences"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=medicine","Medicine"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=modern-languages-and-cultures","Modern Languages and Cultures"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=music","Music"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=philosophy","Philosophy"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=physics","Physics"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=planning","Planning"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=politics","Politics"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=psychology","Psychology"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=sociology-social-policy-and-criminology","Sociology, Social Policy and Criminology"},
			{"http://www.liv.ac.uk/study/postgraduate/courses/taught/departments/index.php?department=veterinary-science","Veterinary Science"},

	};

}
