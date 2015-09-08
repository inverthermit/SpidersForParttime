package mary;


import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
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
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		for(int i=0;i<=25;i++)
		{
			int index=1+i*10;
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url+index); 
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			Parser parser=Parser.createParser(htmls, "utf-8");
		    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("li"),
	                new HasAttributeFilter("data-fb-result"));
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
	                        new HasAttributeFilter("title"));
	        		parser=Parser.createParser(node.toHtml(), "utf-8");
	                NodeList nodes3 = parser.extractAllNodesThatMatch(linkFilter);
	                Node aNode=nodes3.elementAt(0);
	                //System.out.println(aNode.toHtml());
	                LinkTag l=(LinkTag)aNode;
	                System.out.print("{\""+l.getAttribute("title")+"\",\""+html2Str(aNode.toHtml().replace(" <br />", "\",\"")).trim()+"\",\"");
	                //html2Str(aNode.toHtml().replace("<br />", ";"));
	                
	                
	               //***************get duration
	                parser=Parser.createParser(node.toHtml(), "utf-8");
	                AndFilter durationFilter=new AndFilter(new TagNameFilter("div"),
	                        new HasAttributeFilter("class","result-bottom"));
	                NodeList nodes5 = parser.extractAllNodesThatMatch(durationFilter);
	                Node dNode=nodes5.elementAt(0);
	                String Duration=html2Str(dNode.toHtml());
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
	                System.out.println("\"},");
	                
	        	}
	        	
	    	    
	        }
		}
		System.out.println("DONE.");
		
		
	}
	
	//public static String url="http://search.qmul.ac.uk/s/search.html?collection=queenmary-coursefinder-undergraduate&query=&sort=title&start_rank=";//11-211
	public static String url="http://search.qmul.ac.uk/s/search.html?collection=queenmary-coursefinder-pg&query=&sort=title&start_rank=";
	//http://search.qmul.ac.uk/s/search.html?collection=queenmary-coursefinder-pg&query=&sort=title
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
		public static String[][] postUrls={
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121367.html","(15 month) Gastroenterology","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121541.html","Accounting and Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121456.html","Accounting and Management","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/149585.html","Advanced Mechanical Engineering","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121462.html","Aerospace Engineering","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121567.html","Aesthetic Medicine","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/126519.html","Aesthetic Medicine","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/126542.html","Aesthetic Medicine","PGDip","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121436.html","Anglo-German Cultural Relations","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121395.html","Applied Linguistics for English Language Teaching","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/126325.html","Applied Linguistics for English Language Teaching","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/126327.html","Applied Linguistics for English Language Teaching","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121461.html","Aquatic Ecology by Research","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121538.html","Astronomy and Astrophysics","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121377.html","Astrophysics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121400.html","Banking","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121738.html","Banking and Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121548.html","Banking and Finance Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/148633.html","Behavioural Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121386.html","Big Data Science","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137236.html","Big Data Science with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121410.html","Bioinformatics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121525.html","Biomaterials","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121539.html","Biomedical Engineering","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121421.html","Biomedical Science (Medical Microbiology)","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121431.html","British Politics: Theory and Practice","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121782.html","Burn Care","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121526.html","Burn Care","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121424.html","Business and Management","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121399.html","Business Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159410.html","Cancer and Clinical Oncology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159407.html","Cancer and Clinical Oncology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159404.html","Cancer and Clinical Oncology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159406.html","Cancer and Clinical Oncology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159403.html","Cancer and Molecular and Cellular Biology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159409.html","Cancer and Molecular and Cellular Biology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159408.html","Cancer and Molecular and Cellular Biology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159405.html","Cancer and Molecular and Cellular Biology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121349.html","Cancer Therapeutics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121368.html","Cancer Therapeutics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121790.html","Cancer Therapeutics","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121511.html","Cancer Therapeutics","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121379.html","Chemical Research","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121771.html","Cities and Cultures","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121366.html","Cities and Cultures","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121557.html","Clinical Dermatology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121382.html","Clinical Drug Development","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121550.html","Clinical Drug Development","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/138397.html","Clinical Endocrinology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137562.html","Clinical Endocrinology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121473.html","Clinical Microbiology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121563.html","Clinical Microbiology","PGDip","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/151595.html","Clinical Research","PGCert",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/151596.html","Clinical Research","PGDip",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/151594.html","Clinical Research","MRes",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121354.html","Commercial and Corporate Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159115.html","Community Organising","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121542.html","Community Organising","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121547.html","Comparative and International Dispute Resolution","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121458.html","Comparative Literature","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121543.html","Competition Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121383.html","Computer Aided Engineering","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121785.html","Computer and Communications Law","PGCert",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121783.html","Computer and Communications Law","PGDip",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121499.html","Computer and Communications Law","LLM",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121353.html","Computer and Communications Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121428.html","Computer Science","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121374.html","Computer Science by Research","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137235.html","Computer Science with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121411.html","Computer Vision","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137239.html","Computer Vision with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121464.html","Computing and Information Systems","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137241.html","Computing and Information Systems with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/160761.html","Craniofacial Trauma Reconstruction","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/124844.html","Creative Arts and Mental Health","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/145314.html","Criminal Justice","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121446.html","Critical Care","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121515.html","Dental Materials","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121469.html","Dental Public Health","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121454.html","Dental Science for Clinical Practice","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121471.html","Dental Technology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121488.html","Digital Signal Processing","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137234.html","Digital Signal Processing with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121476.html","Documentary Practice","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121430.html","Ecological and Evolutionary Genomics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121559.html","Ecology and Evolutionary Biology","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121409.html","Ecology and Evolutionary Biology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121766.html","Economics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121453.html","Economics","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121572.html","Electronic Engineering by Research","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121554.html","Endocrinology and Diabetes","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121789.html","Endocrinology and Diabetes","PGDip","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/143556.html","Endocrinology and Diabetes (Top Up)","MSc",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121978.html","Endodontic Practice","MSc","36"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121362.html","Energy and Natural Resources Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/125384.html","English Studies: Contemporary Writing","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121413.html","English Studies: Early Modern Studies","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121455.html","English Studies: Eighteenth-Century Literature and Romanticism","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121438.html","English Studies: English Literature","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/150034.html","English Studies: Postcolonial and Global Literatures","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/132633.html","English Studies: Victorian Literature","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121440.html","English Studies: Writing in the Modern Age","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121363.html","Environmental Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121384.html","Environmental Science by Research","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121459.html","Environmental Science: Integrated Management of Freshwater","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159116.html","Environmental Science: Integrated Management of Freshwater","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121352.html","European Jewish History","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121361.html","European Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121979.html","European Public Policy","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121466.html","Experimental Oral Pathology (Oral Sciences)","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121506.html","Film Studies","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121429.html","Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/123996.html","Finance","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121512.html","Finance and Econometrics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/155455.html","Financial Computing","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/155454.html","Financial Computing (with IE)","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121371.html","Forensic Medical Sciences","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121495.html","Freshwater and Marine Ecology","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121419.html","Freshwater and Marine Ecology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121973.html","Gastroenterology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121527.html","Gastroenterology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121494.html","Gastroenterology","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/158790.html","Gastroenterology (Top-Up)","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/155815.html","Genomic Medicine","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/155742.html","Genomic Medicine","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121773.html","Geography","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121974.html","Geography","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121348.html","Geography","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121764.html","Global and Imperial History","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/143983.html","Global Development Futures","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/149526.html","Global Development Futures","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121432.html","Global Health, Law and Governance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121403.html","Global Public Health and Policy","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/125726.html","Global Shakespeare (with University of Warwick)","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121549.html","Health Care Research Methods","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121381.html","Health Care Research Methods","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121402.html","Health Systems and Global Policy","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121498.html","History","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121503.html","History of Political Thought and Intellectual History","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121360.html","Human Rights Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/122494.html","Immigration Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121370.html","Inflammation: Cellular and Vascular Aspects","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121481.html","Insurance Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121435.html","Intellectual Property Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121523.html","Intellectual Property Law","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121415.html","International Business","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/126163.html","International Business and Politics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121359.html","International Business Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121505.html","International Dispute Resolution (Arbitration)","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/138361.html","International Dispute Resolution (Arbitration) (January start)","PGDip","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121502.html","International Dispute Resolution (Mediation)","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/138360.html","International Dispute Resolution (Mediation) (January start)","PGDip","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/143462.html","International Economic Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121532.html","International Finance Law","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121553.html","International Finance Law (Term I)","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121444.html","International Financial Management","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121442.html","International Human Resource Management and Employment Relations","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121404.html","International Primary Health Care","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121423.html","International Public Policy","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/146415.html","International Public Policy","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121770.html","International Relations","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121392.html","International Relations","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121792.html","International Relations","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121522.html","International Relations","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121356.html","International Relations","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/125644.html","International Relations","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/155316.html","International Relations (Paris)","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/155314.html","International Relations (Paris)","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/155315.html","International Relations (Paris) 1 Semester","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121358.html","International Shipping Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/142363.html","International Shipping Law (Piraeus, Greece)","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121507.html","Investment and Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121407.html","Law and Economics","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121364.html","Law and Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121763.html","Law by Research (September start)","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121418.html","Laws","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121545.html","Laws","PGDip","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/126476.html","Legal Theory","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121540.html","Linguistics","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121467.html","London Studies","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121389.html","Management","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121480.html","Management and Organisational Innovation","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121443.html","Management of Intellectual Property","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121479.html","Marketing","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121534.html","Materials Research","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121983.html","Materials Research","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121417.html","Mathematical Finance","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121375.html","Mathematics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121478.html","Media and Arts Technology by Research","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121345.html","Media Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121378.html","Medical Electronics and Physics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121346.html","Medical Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121388.html","Mental Health and Law","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121520.html","Mental Health and Law","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121475.html","Mental Health: Psychological Therapies","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121472.html","Mental Health: Psychological Therapies","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121788.html","Mental Health: Psychological Therapies","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121493.html","Mental Health: Psychological Therapies","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121474.html","Mental Health: Transcultural Mental Healthcare","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121373.html","Mental Health: Transcultural Mental Healthcare","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121787.html","Mental Health: Transcultural Mental Healthcare","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121496.html","Mental Health: Transcultural Mental Healthcare","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/123713.html","Migration, Culture and Global Health Policy","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121425.html","Mobile and Wireless Networks","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121405.html","Modern and Contemporary British History","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121445.html","Molecular Pathology and Genomics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121369.html","Molecular Pathology and Genomics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/143463.html","Network Science","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121514.html","Neuroscience and Translational Medicine","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121447.html","Neuroscience and Translational Medicine","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121510.html","Neuroscience and Translational Medicine","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121460.html","Oral Biology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121448.html","Oral Medicine","MClinDent","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121477.html","Oral Surgery","MClinDent","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/149026.html","Orthodontics","DCLINDENT","36"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121544.html","Orthodontics","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/126629.html","Orthopaedic Trauma Science","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/149160.html","Paediatric Dentistry","DCLINDENT","36"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121422.html","Paris","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/149157.html","Periodontology","DCLINDENT","36"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121560.html","Physics (EuroMasters)","MSc (EuroMasters)","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121412.html","Physics: Condensed Matter Physics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121394.html","Physics: Particle Physics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121414.html","Physics: Theoretical Physics","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/138996.html","Plant and Fungal Taxonomy Diversity and Conservation","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/152199.html","Poetry","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/125762.html","Polymer Science and Nanotechnology","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/149158.html","Prosthodontics","DCLINDENT","36"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/159114.html","PT Environmental Science: Integrated Management of Freshwater¡­PGCert",""},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121401.html","Public Administration","MPA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121344.html","Public International Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/146414.html","Public Policy","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121482.html","Public Policy","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121976.html","Public Policy","MRes","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/130859.html","Reconstructive Microsurgery","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121385.html","Regenerative Medicine: Science and Application","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121950.html","Semester in London (Taught Postgraduate Associate)","PGT Assoc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121465.html","Software Engineering","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137233.html","Software Engineering with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/129308.html","Sound and Music Computing","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137238.html","Sound and Music Computing with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121536.html","Sports and Exercise Medicine - Medic","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121427.html","Sports and Exercise Medicine - Medic","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121535.html","Sports and Exercise Medicine - Physio","PGDip","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121426.html","Sports and Exercise Medicine - Physio","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121372.html","Surgical Skills and Sciences","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121463.html","Sustainable Energy Systems","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121342.html","Tax Law","LLM","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/151302.html","Telecommunication and Wireless Systems","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/151301.html","Telecommunication and Wireless Systems Management","MSc","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/151798.html","Telecommunication and Wireless Systems Management with Industrial¡­MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/151300.html","Telecommunication and Wireless Systems with Industrial Experience","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/137237.html","Telecommunication Systems with Industrial Experience","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121350.html","Theatre and Performance","MA","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121565.html","Trade Marks Law and Practice","PGCert","12"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121561.html","Trauma Sciences","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/121492.html","Trauma Sciences (Military and Austere)","MSc","24"},
			{"http://www.qmul.ac.uk/postgraduate/coursefinder/courses/145693.html","Wealth Management","MSc","12"},

		};
		public static String[][] underUrls={
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/119123.html","Accounting and Management","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79932.html","Aerospace Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79931.html","Aerospace Engineering","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79948.html","Aerospace Engineering with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79947.html","Aerospace Engineering with Industrial Experience","MEng (Hons)",""},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79993.html","Astrophysics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79992.html","Astrophysics","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79984.html","Biochemistry","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79986.html","Biochemistry","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/115826.html","Biochemistry with a Year in Industry/Research","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79995.html","Biology","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139909.html","Biomedical Engineering and Clinical Materials (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80015.html","Biomedical Sciences","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80025.html","Business Management","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79924.html","Chemistry","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79926.html","Chemistry","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/115827.html","Chemistry with a Year in Industry/Research","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80119.html","Comparative Literature","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80086.html","Comparative Literature and Film Studies","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80057.html","Comparative Literature and Linguistics","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80041.html","Computer Science","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80043.html","Computer Science","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79888.html","Computer Science and Mathematics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79935.html","Computer Science and Mathematics with Industrial Experience","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79962.html","Computer Science and Multimedia","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79928.html","Computer Science and Multimedia with Industrial Experience","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80089.html","Computer Science with Business Management","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80069.html","Computer Science with Business Management and Accounting","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79883.html","Computer Science with Business Management and Accounting with Industrial Experience","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80096.html","Computer Science with Business Management with Industrial Experience","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79923.html","Computer Science with Industrial Experience","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/121328.html","Computer Systems Engineering","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/121327.html","Computer Systems Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/131763.html","Computer Systems Engineering with Industriade l Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79921.html","Dental Hygiene and Dental Therapy","DipHE","24"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80051.html","Dental Materials","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80050.html","Dental Materials","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80094.html","Dental Materials with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80093.html","Dental Materials with Industrial Experience","MEng (Hons)",""},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80113.html","Dentistry","BDS","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/116809.html","Design, Innovation and Creative Engineering","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/116808.html","Design, Innovation and Creative Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80004.html","Drama","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80107.html","Economics","BSc (Econ) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80063.html","Economics and Finance","BSc (Econ) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79953.html","Economics and Politics","BSc (Econ) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80058.html","Economics, Finance and Management","BSc (Econ) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80060.html","Economics, Statistics and Mathematics","BSc (Econ) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79942.html","Electrical and Electronic Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79894.html","Electrical and Electronic Engineering with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79906.html","Electronic Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80106.html","Electronic Engineering and Telecommunications","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80105.html","Electronic Engineering and Telecommunications","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79881.html","Electronic Engineering and Telecommunications with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79979.html","Electronic Engineering with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/121325.html","Electronics with Music and Audio Systems","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/121326.html","Electronics with Music and Audio Systems","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/131764.html","Electronics with Music and Audio Systems with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79879.html","English","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80067.html","English and Drama","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79918.html","English and European Law","LLB (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80068.html","English and Film Studies","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79997.html","English and French","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80049.html","English and German","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80055.html","English and Hispanic Studies","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80028.html","English and History","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79990.html","English and Russian","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80078.html","English Language and Linguistics","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79887.html","English Literature and Linguistics","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79944.html","Environmental Science","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79945.html","Environmental Science","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79964.html","Environmental Science with Business Management","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139903.html","Experimental Pathology (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79981.html","Film Studies","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79967.html","Film Studies and Drama","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80064.html","Film Studies and French","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79916.html","Film Studies and German","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79919.html","Film Studies and Hispanic Studies","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79880.html","Film Studies and Russian","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/134557.html","Financial Mathematics","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80036.html","French","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79952.html","French and Comparative Literature","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79970.html","French and Drama","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80090.html","French and German","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80011.html","French and Hispanic Studies","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79917.html","French and History","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80080.html","French and Linguistics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79897.html","French and Politics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80120.html","French and Russian","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/86378.html","French Studies","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79957.html","French with Business Management","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79890.html","Genetics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80005.html","Geography","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80084.html","Geography","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80091.html","Geography with Business Management","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80020.html","German","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79871.html","German and Comparative Literature","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80034.html","German and Drama","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80056.html","German and Hispanic Studies","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80077.html","German and Linguistics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80079.html","German and Politics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79875.html","German and Russian","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80114.html","German with Business Management","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/114962.html","Global Health","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/141687.html","Global Public Health (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79899.html","Hispanic Studies","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80115.html","Hispanic Studies and Catalan Language","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79902.html","Hispanic Studies and Comparative Literature","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80009.html","Hispanic Studies and Drama","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79877.html","Hispanic Studies and Linguistics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79969.html","Hispanic Studies and Politics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80081.html","Hispanic Studies and Portuguese","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79900.html","Hispanic Studies and Russian","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80010.html","Hispanic Studies with Business Management","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79886.html","History","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79951.html","History and Comparative Literature","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79910.html","History and Film Studies","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79913.html","History and German","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79954.html","History and Politics","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80112.html","Human Geography","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139910.html","Infectious Disease and Epidemiology (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80001.html","Information and Communications Technologies","BSc (Engineering) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/131765.html","Information and Communications Technologies with Industrial Experience","BSc (Engineering) (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/142186.html","Information Technology Management for Business","BSc (Engineering) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/89609.html","Information Technology Management for Business (ITMB) with Industrial Experience","BSc (Engineering) (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80031.html","International Relations","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80122.html","Law","LLB (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79889.html","Law - Senior Status","LLB (Hons)","24"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79901.html","Law and Politics","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/119122.html","Marketing and Management","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79893.html","Materials and Design","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79956.html","Materials and Design with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79982.html","Materials Science","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80124.html","Materials Science and Engineering","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80125.html","Materials Science and Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80061.html","Materials Science and Engineering with Industrial Experience","MEng (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80062.html","Materials Science and Engineering with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80110.html","Mathematics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80108.html","Mathematics","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79914.html","Mathematics and Statistics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/151011.html","Mathematics with Actuarial Science","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79950.html","Mathematics with Business Management","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80085.html","Mathematics with Finance and Accounting","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80065.html","Mathematics with Statistics","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79965.html","Mathematics, Business Management and Finance","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80023.html","Mathematics, Statistics and Financial Economics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80017.html","Mechanical Engineering","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80019.html","Mechanical Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79977.html","Mechanical Engineering with Industrial Experience","MEng (Hons)","24"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79978.html","Mechanical Engineering with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139906.html","Medical Education (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80098.html","Medical Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80097.html","Medical Engineering","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80047.html","Medical Engineering with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80046.html","Medical Engineering with Industrial Experience","MEng (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80007.html","Medical Genetics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80000.html","Medical Materials","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79998.html","Medical Materials","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79999.html","Medical Materials","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80128.html","Medical Materials with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80127.html","Medical Materials with Industrial Experience","MEng (Hons)",""},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80040.html","Medicine (5 Year Programme)","MBBS","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/150767.html","Medicine (Malta, 5 Year Programme)","MBBS","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79972.html","Medicine - Graduate Entry Programme (4 year)","MBBS","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79949.html","Medieval History","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80013.html","Modern and Contemporary History","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139907.html","Molecular Medicine (Intercalated)","BMedSci (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139908.html","Molecular Therapeutics (Intercalated)","BMedSci (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80121.html","Multimedia and Arts Technology","BSc (Engineering) (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79934.html","Multimedia and Arts Technology with Industrial Experience","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/134555.html","Neuroscience","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139911.html","Neuroscience (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139905.html","Oral Biology (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79959.html","Pharmaceutical Chemistry","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79958.html","Pharmaceutical Chemistry","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/115825.html","Pharmaceutical Chemistry with a Year in Industry/Research","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/134556.html","Pharmacology and Innovative Therapeutics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79936.html","Physics","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79938.html","Physics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79872.html","Physics with Particle Physics","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79873.html","Physics with Particle Physics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80073.html","Politics","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/155141.html","Politics and International Relations","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80035.html","Politics with Business Management","BA (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/124036.html","Pre-Hospital Medicine (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80066.html","Psychology","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79876.html","Pure Mathematics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80088.html","Russian","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79922.html","Russian and Comparative Literature","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80095.html","Russian and Drama","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79980.html","Russian and Linguistics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79971.html","Russian and Politics","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79968.html","Russian with Business Management","BA (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79996.html","Science and Engineering Foundation Programme (Biological Sciences 4 year)","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79985.html","Science and Engineering Foundation Programme (Biology 5 year)","MSci (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79927.html","Science and Engineering Foundation Programme (Chemistry 4 year)","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79925.html","Science and Engineering Foundation Programme (Chemistry 5 year)","MSci (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80044.html","Science and Engineering Foundation Programme (Computer Science 4 year)","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80042.html","Science and Engineering Foundation Programme (Computer Science 5 year)","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79907.html","Science and Engineering Foundation Programme (Electronic Engineering 4 year)","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79905.html","Science and Engineering Foundation Programme (Electronic Engineering 5 year)","MEng (Hons)",""},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79933.html","Science and Engineering Foundation Programme (Engineering 4 year)","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80018.html","Science and Engineering Foundation Programme (Engineering 5 year)","MEng (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80126.html","Science and Engineering Foundation Programme (Materials Science 4 year)","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79983.html","Science and Engineering Foundation Programme (Materials Science 5 year)","MEng (Hons)",""},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80111.html","Science and Engineering Foundation Programme (Mathematical Sciences 4 year)","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79939.html","Science and Engineering Foundation Programme (Physics 4 year)","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79937.html","Science and Engineering Foundation Programme (Physics 5 year)","MSci (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/142367.html","Software Engineering for Business","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/142368.html","Software Engineering for Business with Industrial Experience","BSc (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/139904.html","Sports and Exercise Medicine (Intercalated)","BSC (Intercal)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80072.html","Sustainable Energy Engineering","BEng (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80071.html","Sustainable Energy Engineering","MEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80032.html","Sustainable Energy Engineering with Industrial Experience","MEng (Hons)","12"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80033.html","Sustainable Energy Engineering with Industrial Experience","BEng (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80039.html","Theoretical Physics","BSc (Hons)","36"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/80038.html","Theoretical Physics","MSci (Hons)","48"},
			{"http://www.qmul.ac.uk/undergraduate/coursefinder/courses/79929.html","Zoology","BSc (Hons)","36"},

		};
		
}
