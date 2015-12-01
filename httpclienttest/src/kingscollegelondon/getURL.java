package kingscollegelondon;

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

	//public static String url="http://www.kcl.ac.uk/prospectus/undergraduate/search/alpha/";
	public static String url="http://www.kcl.ac.uk/prospectus/graduate/search/alpha/";
	public static void main(String[] args) throws Exception{
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		int count=1;
		for(int i=0;i<=26;i++)
		//for(int i=0;i<=1;i++)
		{
			try{
				while(true)
				{
					int index=i;
					//System.out.println(index);
					HttpGet httpGet = new HttpGet(url+(char)('a'+index)); 
					//System.out.println(url+(char)('a'+index));
					HttpResponse response = httpclient.execute(httpGet);  
					HttpEntity entity = response.getEntity();
					String htmls=null;
					if (entity != null) { 
					    htmls=EntityUtils.toString(entity).replace("\t", " ");
					    //System.out.println("Got reply.");
					}
					Parser parser=Parser.createParser(htmls, "utf-8");
				    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("table"),
			                new HasAttributeFilter("class","view_table"));
			        NodeList nodes1 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
			        //System.out.println(nodes1.elementAt(0).toHtml());
			        if(nodes1.size()>0)
			        {
			        	for(int k=0;k<nodes1.size();k++)
			        	{
			        		parser=Parser.createParser(nodes1.elementAt(k).toHtml(), "utf-8");
					        AndFilter TF=new AndFilter(new TagNameFilter("tr"),
					                new HasAttributeFilter("class","pgt_table_content"));//pgt_table_content ug_table_content
					        NodeList nodes2 = parser.extractAllNodesThatMatch(TF);
					        //System.out.println("Size:"+nodes2.size());
					        if(nodes2.size()>0)
					        {
					        	for(int j=0;j<nodes2.size();j++)
					        	{
					        		
					        		//***************get links;name;type;time
					        		Node node=(Node)nodes2.elementAt(j);
					        		parser=Parser.createParser(node.toHtml(), "utf-8");
							        AndFilter TD=new AndFilter(new TagNameFilter("td"),
							                new HasAttributeFilter("class","pgt_table_content"));
							        NodeList nodes4 = parser.extractAllNodesThatMatch(TD);
					        		//System.out.println(node.toHtml());
					        		AndFilter linkFilter=new AndFilter(new TagNameFilter("a"),
					                        new HasAttributeFilter("href"));
					        		parser=Parser.createParser(node.toHtml(), "utf-8");
					                NodeList nodes3 = parser.extractAllNodesThatMatch(linkFilter);
					                //System.out.println(nodes3.size());
					                Node aNode=nodes3.elementAt(0);
					                //System.out.println(aNode.toHtml());
					                LinkTag l=(LinkTag)aNode;
					                String title=HTMLFilter(html2Str(aNode.toHtml().replace(" <br />", "\",\""))).trim();
					                System.out.println("{\""+count+"\",\"http://www.kcl.ac.uk/"+l.getAttribute("href")+"\",\""+title+"\",\""+html2Str(nodes4.elementAt(2).toHtml())+"\",\"0\"},");
					                //html2Str(aNode.toHtml().replace("<br />", ";"));
					                count++;
					                
					        	}
					        }
					        
			        	}
			        }
			        break;
				}
				
			}
			catch(Exception ee)
			{
				System.out.println("Retry");
			}
		}
		System.out.println("DONE.");
		
		
	}
	public static String GetType(String input)//BA BEng Bsc Msc MEng 
	{
		String types="BA;BEng;BSc;BDS;BN;BVSc;MOSci;MESci;MEcol;MPhys;MMath;MMarBiol;MBChB;MChem;MSc;MEng;Double MA;Joint MA;MA;MArich;MBA;PG;Pg;EdD;MEd;Postgraduate Diploma;Postgraduate Certificate;Doctorate;Graduate Certificate;LLM;LLB;GradDip;MTh;MRes";
		
		String[] array=types.split(";");
		for(int i=0;i<array.length;i++)
		{
			if(input.contains(array[i]))
			{
				return array[i];
			}
		}
		//String result=array[array.length-1].replace(")", "");
		return "";
	}
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	public static String[][] UnData={
		{"1","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/human-sciences/alpha/A/header_search/","Anatomy, Developmental & Human Biology","BSc","0"},
		{"2","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/ancient-history/alpha/A/header_search/","Ancient History","BA","0"},
		{"3","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/biochemistry-msci/alpha/B/header_search/","Biochemistry","MSci","0"},
		{"4","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/biochemistry/alpha/B/header_search/","Biochemistry","BSc","0"},
		{"5","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/biomedical-engineering-meng/alpha/B/header_search/","Biomedical Engineering","MEng","0"},
		{"6","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/biomedical-engineering/alpha/B/header_search/","Biomedical Engineering","BEng","0"},
		{"7","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/biomedical-science/alpha/B/header_search/","Biomedical Science","BSc","0"},
		{"8","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/business-management/alpha/B/header_search/","Business Management","BSc","0"},
		{"9","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/chemistry-bsc/alpha/C/header_search/","Chemistry","BSc","0"},
		{"10","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/chemistry-msci/alpha/C/header_search/","Chemistry"," MSci","0"},
		{"11","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/chemistry-biomedicine-msci/alpha/C/header_search/","Chemistry with Biomedicine","MSci","0"},
		{"12","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/chemistry-biomedicine-bsc/alpha/C/header_search/","Chemistry with Biomedicine","BSc","0"},
		{"13","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/classical-and-modern-greek-studies/alpha/C/header_search/","Classical and Modern Greek Studies","BA","0"},
		{"14","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/classical-archaeology/alpha/C/header_search/","Classical Archaeology","BA","0"},
		{"15","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/classical-studies/alpha/C/header_search/","Classical Studies","BA","0"},
		{"16","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/classical-studies-and-comparative-literature/alpha/C/header_search/","Classical Studies & Comparative Literature","BA","0"},
		{"17","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/classical-studies-and-french/alpha/C/header_search/","Classical Studies & French with a year abroad","BA","0"},
		{"18","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/classical-studies-with-english/alpha/C/header_search/","Classical Studies with English","BA","0"},
		{"19","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/classics-greek-and-latin/alpha/C/header_search/","Classics (Greek & Latin)","BA","0"},
		{"20","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/comparative-literature/alpha/C/header_search/","Comparative Literature","BA","0"},
		{"21","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/comparative-literature-with-film-studies/alpha/C/header_search/","Comparative Literature with Film Studies","BA","0"},
		{"22","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-msci/alpha/C/header_search/","Computer Science","MSci","0"},
		{"23","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science/alpha/C/header_search/","Computer Science","BSc","0"},
		{"24","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-with-a-year-abroad/alpha/C/header_search/","Computer Science with a year abroad","BSc","0"},
		{"25","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-with-a-year-in-industry/alpha/C/header_search/","Computer Science with a year in industry","BSc","0"},
		{"26","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-with-intelligent-systems/alpha/C/header_search/","Computer Science with Intelligent Systems","BSc","0"},
		{"27","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-with-management/alpha/C/header_search/","Computer Science with Management","BSc","0"},
		{"28","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-with-management-with-a-year-abroad/alpha/C/header_search/","Computer Science with Management and a year abroad","BSc","0"},
		{"29","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-with-management-with-a-year-in-industry/alpha/C/header_search/","Computer Science with Management and a year in industry","BSc","0"},
		{"30","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/computer-science-with-robotics/alpha/C/header_search/","Computer Science with Robotics","BSc","0"},
		{"31","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/dentistry/alpha/D/header_search/","Dentistry","BDS","0"},
		{"32","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/enhanced-support-dentistry/alpha/D/header_search/","Dentistry (Enhanced Support Programme)","BDS","0"},
		{"33","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/dentistry-entry-programme-for-medical-graduates/alpha/D/header_search/","Dentistry Entry Programme for Medical Graduates","BDS","0"},
		{"34","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/dentistry-graduate-professional-entry-programme/alpha/D/header_search/","Dentistry Graduate/Professional Entry Programme","BDS","0"},
		{"35","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/human-sciences/alpha/D/header_search/","Developmental & Human Biology","BSc","0"},
		{"36","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/digital-culture/alpha/D/header_search/","Digital Culture","BA","0"},
		{"37","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/economics-and-management-bsc/alpha/E/header_search/","Economics & Management","BSc","0"},
		{"38","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/political-economy/alpha/E/header_search/","Economics (BA/BSc Politics of the International Economy)","BA/BSc","0"},
		{"39","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/electronic-and-information-engineering-beng/alpha/E/header_search/","Electronic & Information Engineering","BEng","0"},
		{"40","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/electronic-and-information-engineering-meng/alpha/E/header_search/","Electronic & Information Engineering","MEng","0"},
		{"41","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/electronic-engineering-beng/alpha/E/header_search/","Electronic Engineering","BEng","0"},
		{"42","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/electronic-engineering-meng/alpha/E/header_search/","Electronic Engineering","MEng","0"},
		{"43","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/electronic-engineering-with-management-beng/alpha/E/header_search/","Electronic Engineering with Management","BEng","0"},
		{"44","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/electronic-engineering-with-management-meng/alpha/E/header_search/","Electronic Engineering with Management","MEng","0"},
		{"45","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english/alpha/E/header_search/","English","BA","0"},
		{"46","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-language-and-linguistics/alpha/E/header_search/","English Language & Linguistics","BA","0"},
		{"47","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-and-american-law/alpha/E/header_search/","English Law & American Law","LLB and JD","0"},
		{"48","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-and-french-law/alpha/E/header_search/","English Law & French Law","LLB and Maitrise en droit (French equivalent of LLB)","0"},
		{"49","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-and-german-law/alpha/E/header_search/","English Law & German Law","LLB and MLLP or Certificate in Rechtswissenschaften","0"},
		{"50","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-with-australian-law/alpha/E/header_search/","English Law with Australian Law","LLB","0"},
		{"51","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-with-film-studies/alpha/E/header_search/","English with Film Studies","BA","0"},
		{"52","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/enhanced-support-dentistry/alpha/E/header_search/","Enhanced Support Dentistry Programme","BDS","0"},
		{"53","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/european-politics/alpha/E/header_search/","European Politics","BA","0"},
		{"54","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/european-studies-french/alpha/E/header_search/","European Studies (French pathway) with a year abroad","BA","0"},
		{"55","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/european-studies-german/alpha/E/header_search/","European Studies (German pathway) with a year abroad","BA","0"},
		{"56","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/european-studies-spanish/alpha/E/header_search/","European Studies (Spanish pathway) with a year abroad","BA","0"},
		{"57","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/emdp/alpha/E/header_search/","Extended Medical Degree Programme","MBBS","0"},
		{"58","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/film-studies/alpha/F/header_search/","Film Studies","BA","0"},
		{"59","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/international-foundation-programme-in-humanities-and-social-sciences/alpha/F/header_search/","Foundation Programme","Diploma","0"},
		{"60","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-and-german-with-a-year-abroad/alpha/F/header_search/","French & German with a year abroad","BA","0"},
		{"61","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-and-history/alpha/F/header_search/","French & History with a year abroad","BA","0"},
		{"62","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-and-management-with-a-year-abroad/alpha/F/header_search/","French & Management with a year abroad","BA","0"},
		{"63","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-and-philosophy/alpha/F/header_search/","French & Philosophy with a year abroad","BA","0"},
		{"64","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-four-year/alpha/F/header_search/","French (four year) with a year abroad","BA","0"},
		{"65","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-three-year/alpha/F/header_search/","French (three year)","BA","0"},
		{"66","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-and-spanish/alpha/F/header_search/","French and Spanish with a year abroad","BA","0"},
		{"67","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-with-english/alpha/F/header_search/","French with English with a year abroad","BA","0"},
		{"68","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/french-with-film-studies/alpha/F/header_search/","French with Film Studies with a year abroad","BA","0"},
		{"69","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/geography-bsc/alpha/G/header_search/","Geography","BSc","0"},
		{"70","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/geography/alpha/G/header_search/","Geography","BA","0"},
		{"71","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-and-history/alpha/G/header_search/","German & History with a year abroad","BA","0"},
		{"72","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-and-music/alpha/G/header_search/","German & Music with a year abroad","BA","0"},
		{"73","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-and-philosophy/alpha/G/header_search/","German & Philosophy with a year abroad","BA","0"},
		{"74","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-and-portuguese/alpha/G/header_search/","German & Portuguese with a year abroad","BA","0"},
		{"75","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-and-management-with-a-year-abroad/alpha/G/header_search/","German and Management with a year abroad","BA","0"},
		{"76","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-and-spanish/alpha/G/header_search/","German and Spanish with a year abroad"," BA","0"},
		{"77","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german/alpha/G/header_search/","German with a year abroad","BA","0"},
		{"78","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-with-english/alpha/G/header_search/","German with English with a year abroad","BA","0"},
		{"79","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-with-film-studies/alpha/G/header_search/","German with Film Studies with a year abroad","BA","0"},
		{"80","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/global-health-and-social-medicine-bsc/alpha/G/header_search/","Global Health & Social Medicine","BSc","0"},
		{"81","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/global-health-and-social-medicine-ba/alpha/G/header_search/","Global Health & Social Medicine","BA","0"},
		{"82","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/greek-with-english/alpha/G/header_search/","Greek with English","BA","0"},
		{"83","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/history/alpha/H/header_search/","History","BA","0"},
		{"84","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/history-and-international-relations/alpha/H/header_search/","History and International Relations","BA","0"},
		{"85","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/human-physiology/alpha/H/header_search/","Human Physiology","MSci","0"},
		{"86","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/human-sciences/alpha/H/header_search/","Human Sciences","BSc","0"},
		{"87","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/international-development/alpha/I/header_search/","International Development","BA","0"},
		{"88","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/international-foundation-programme-in-humanities-and-social-sciences/alpha/I/header_search/","International Foundation Programme in Humanities & Social Sciences","Diploma","0"},
		{"89","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/international-management/alpha/I/header_search/","International Management","BSc","0"},
		{"90","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/international-relations/alpha/I/header_search/","International Relations","BA","0"},
		{"91","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/international-science-foundation-programme/alpha/I/header_search/","International Science Foundation Programme","Diploma","0"},
		{"92","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/law/alpha/L/header_search/","Law","LLB","0"},
		{"93","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-and-american-law/alpha/L/header_search/","Law (English Law & American Law)","LLB and JD","0"},
		{"94","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-and-french-law/alpha/L/header_search/","Law (English Law & French Law)","LLB and Maitrise en droit (French equivalent of LLB)","0"},
		{"95","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-and-german-law/alpha/L/header_search/","Law (English Law & German Law)","LLB and MLLP or Certificate in Rechtswissenschaften","0"},
		{"96","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/english-law-with-australian-law/alpha/L/header_search/","Law (English Law with Australian Law)","LLB","0"},
		{"97","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/politics-philosophy-and-law/alpha/L/header_search/","Law (Politics, Philosophy &)","LLB","0"},
		{"98","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/law-with-american-legal-studies/alpha/L/header_search/","Law with American Legal Studies","LLB","0"},
		{"99","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/law-with-european-legal-studies/alpha/L/header_search/","Law with European Legal Studies","LLB","0"},
		{"100","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/law-with-transnational-legal-studies/alpha/L/header_search/","Law with Transnational Legal Studies","LLB","0"},
		{"101","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/liberal-arts/alpha/L/header_search/","Liberal Arts","BA","0"},
		{"102","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/german-and-management-with-a-year-abroad/alpha/M/header_search/","Management & German","BA","0"},
		{"103","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/spanish-and-management-with-a-year-abroad/alpha/M/header_search/","Management & Spanish","BA","0"},
		{"104","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/mathematics/alpha/M/header_search/","Mathematics","BSc","0"},
		{"105","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/mathematics-msci/alpha/M/header_search/","Mathematics","MSci","0"},
		{"106","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/mathematics-and-philosophy/alpha/M/header_search/","Mathematics & Philosophy","BA","0"},
		{"107","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/mathematics-and-physics-msci/alpha/M/header_search/","Mathematics & Physics","MSci","0"},
		{"108","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/mathematics-and-physics/alpha/M/header_search/","Mathematics & Physics","BSc","0"},
		{"109","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/mathematics-with-management-and-finance/alpha/M/header_search/","Mathematics with Management & Finance","BSc","0"},
		{"110","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/mathematics-with-statistics/alpha/M/header_search/","Mathematics with Statistics","BSc","0"},
		{"111","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/medicine/alpha/M/header_search/","Medicine","MBBS","0"},
		{"112","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physiology/alpha/M/header_search/","Medical Physiology","BSc","0"},
		{"113","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/emdp/alpha/M/header_search/","Medicine Extended Degree Programme","MBBS","0"},
		{"114","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/medicine-graduate-professional-entry-programme/alpha/M/header_search/","Medicine Graduate/Professional Entry Programme","MBBS","0"},
		{"115","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/medicine-maxfax-entry-programme/alpha/M/header_search/","Medicine Maxfax Entry Programme","MBBS","0"},
		{"116","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/global-health-and-social-medicine-bsc/alpha/M/header_search/","Medicine, Health & Society","BSc","0"},
		{"117","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/midwifery-studies-with-registration/alpha/M/header_search/","Midwifery Studies with Registration","BSc","0"},
		{"118","http://www.kcl.ac.uk/prospectus/graduate/structure/name/midwifery-with-registration-graduate-entry/alpha/M/header_search/","Midwifery with registration (graduate entry)","PGDip","0"},
		{"119","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/molecular-genetics/alpha/M/header_search/","Molecular Genetics","BSc","0"},
		{"120","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/pharmacology-and-molecular-genetics/alpha/M/header_search/","Molecular Genetics & Pharmacology","BSc","0"},
		{"121","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/music/alpha/M/header_search/","Music","BMus","0"},
		{"122","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/neuroscience-msci/alpha/N/header_search/","Neuroscience","MSci","0"},
		{"123","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/neuroscience/alpha/N/header_search/","Neuroscience","BSc","0"},
		{"124","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/nursing-studies/alpha/N/header_search/","Nursing Studies (for qualified healthcare professionals)","BSc","0"},
		{"125","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/nursing-studies-with-registration-adult-nursing/alpha/N/header_search/","Nursing Studies with Registration - Adult nursing","BSc","0"},
		{"126","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/nursing-studies-with-registration-child-nursing-bsc/alpha/N/header_search/","Nursing Studies with Registration - Children's nursing","BSc","0"},
		{"127","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/nursing-studies-with-registration-mental-health-nursing/alpha/N/header_search/","Nursing Studies with Registration - Mental Health nursing","BSc","0"},
		{"128","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-with-registration-graduate-entry/alpha/N/header_search/","Nursing with registration (graduate entry)","PG Dip","0"},
		{"129","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-adult-pg-dip/alpha/N/header_search/","Nursing with registration - adult (graduate entry)","PG Dip","0"},
		{"130","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-child-pg-dip/alpha/N/header_search/","Nursing with registration - child (graduate entry)","PG Dip","0"},
		{"131","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-mental-health-pg-dip/alpha/N/header_search/","Nursing with registration - mental health (graduate entry)","PG Dip","0"},
		{"132","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/nutrition/alpha/N/header_search/","Nutrition","BSc","0"},
		{"133","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/nutrition-and-dietetics/alpha/N/header_search/","Nutrition & Dietetics","BSc","0"},
		{"134","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/pharmacology-msci/alpha/P/header_search/","Pharmacology","MSci","0"},
		{"135","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/pharmacology/alpha/P/header_search/","Pharmacology","BSc","0"},
		{"136","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/pharmacology-and-molecular-genetics/alpha/P/header_search/","Pharmacology & Molecular Genetics","BSc","0"},
		{"137","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/pharmacy/alpha/P/header_search/","Pharmacy","MPharm","0"},
		{"138","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/philosophy/alpha/P/header_search/","Philosophy","BA","0"},
		{"139","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/philosophy-and-spanish/alpha/P/header_search/","Philosophy & Spanish with a year abroad","BA","0"},
		{"140","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/philosophy-politics-and-economics/alpha/P/header_search/","Philosophy, Politics and Economics","BA","0"},
		{"141","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics-msci/alpha/P/header_search/","Physics","MSci","0"},
		{"142","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics/alpha/P/header_search/","Physics","BSc","0"},
		{"143","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics-and-philosophy/alpha/P/header_search/","Physics & Philosophy","BSc","0"},
		{"144","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics-and-philosophy-with-a-year-abroad/alpha/P/header_search/","Physics & Philosophy with a year abroad","BSc","0"},
		{"145","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics-with-a-year-abroad/alpha/P/header_search/","Physics with a year abroad","BSc","0"},
		{"146","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics-with-medical-applications/alpha/P/header_search/","Physics with Medical Applications","BSc","0"},
		{"147","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics-with-theoretical-physics-msci/alpha/P/header_search/","Physics with Theoretical Physics","MSci","0"},
		{"148","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physics-with-theoretical-physics/alpha/P/header_search/","Physics with Theoretical Physics","BSc","0"},
		{"149","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/human-physiology/alpha/P/header_search/","Physiology","MSci","0"},
		{"150","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/physiotherapy/alpha/P/header_search/","Physiotherapy","BSc","0"},
		{"151","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/political-economy/alpha/P/header_search/","Politics (BA/BSc Political Economy)","BA/BSc","0"},
		{"152","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/politics/alpha/P/header_search/","Politics (BA International Politics)","BA","0"},
		{"153","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/european-politics/alpha/P/header_search/","Politics (European)","BA","0"},
		{"154","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/war-studies/alpha/P/header_search/","Politics (War Studies)","BA","0"},
		{"155","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/politics-philosophy-and-law/alpha/P/header_search/","Politics, Philosophy & Law","LLB","0"},
		{"156","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/portuguese-and-french-with-a-year-abroad/alpha/P/header_search/","Portuguese & French with a year abroad","BA","0"},
		{"157","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/psychology/alpha/P/header_search/","Psychology","BSc","0"},
		{"158","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/religion-philosophy-and-ethics/alpha/R/header_search/","Religion, Philosophy & Ethics","BA","0"},
		{"159","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/religion-politics-and-society/alpha/R/header_search/","Religion, Politics & Society","BA","0"},
		{"160","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/robotics-and-intelligent-systems/alpha/R/header_search/","Robotics & Intelligent Systems","MSci","0"},
		{"161","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/international-science-foundation-programme/alpha/S/header_search/","Science Foundation Programme","Diploma","0"},
		{"162","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/spanish-latin-american-studies/alpha/S/header_search/","Spanish & Latin American Studies with a year abroad","BA","0"},
		{"163","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/spanish-and-portuguese-with-a-year-abroad/alpha/S/header_search/","Spanish & Portuguese with a year abroad","BA","0"},
		{"164","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/spanish-and-management-with-a-year-abroad/alpha/S/header_search/","Spanish and Management with a year abroad","BA","0"},
		{"165","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/spanish-with-english/alpha/S/header_search/","Spanish with English with a year abroad","BA","0"},
		{"166","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/spanish-with-film-studies/alpha/S/header_search/","Spanish with Film Studies with a year abroad","BA","0"},
		{"167","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/theology/alpha/T/header_search/","Theology","BA","0"},
		{"168","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/war-studies/alpha/W/header_search/","War Studies","BA","0"},
		{"169","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/war-studies-and-history/alpha/W/header_search/","War Studies & History","BA","0"},
		{"170","http://www.kcl.ac.uk/prospectus/undergraduate/structure/name/war-studies-and-philosophy/alpha/W/header_search/","War Studies & Philosophy","BA","0"}
	};
	public static String[][] PostData={
		{"1","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-studies-and-academic-english/alpha/A/header_search/","Academic English & War Studies","Grad Dip","0"},
		{"2","http://www.kcl.ac.uk/prospectus/graduate/structure/name/academic-practice-he/alpha/A/header_search/","Academic Practice in Higher Education","PG Cert","0"},
		{"3","http://www.kcl.ac.uk/prospectus/graduate/structure/name/academic-practice-in-higher-education/alpha/A/header_search/","Academic Practice in Higher Education","MA/PG Dip","0"},
		{"4","http://www.kcl.ac.uk/prospectus/graduate/structure/name/accounting-accountability-and-financial-management/alpha/A/header_search/","Accounting, Accountability & Financial Management","MSc","0"},
		{"5","http://www.kcl.ac.uk/prospectus/graduate/structure/name/addictions/alpha/A/header_search/","Addiction Studies","MSc/PG Cert","0"},
		{"6","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced--obr-neuromusculo-skeletal-cbr--physiotherapy/alpha/A/header_search/","Advanced (Neuromusculoskeletal) Physiotherapy","MSc","0"},
		{"7","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-clinical-healthcare/alpha/A/header_search/","Advanced Clinical Healthcare","MSc","0"},
		{"8","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-computing/alpha/A/header_search/","Advanced Computing","MSc","0"},
		{"9","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-computing-with-management-msc/alpha/A/header_search/","Advanced Computing with Management","MSc","0"},
		{"10","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-minimum-intervention-dentistry/alpha/A/header_search/","Advanced Minimum Intervention Dentistry","MSc","0"},
		{"11","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-musical-studies/alpha/A/header_search/","Advanced Musical Studies","PG Cert","0"},
		{"12","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-paediatrics/alpha/A/header_search/","Advanced Paediatrics","MSc","0"},
		{"13","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice/alpha/A/header_search/","Advanced Practice","MSc/PG Dip/PG Cert","0"},
		{"14","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice--obr-district-nursing-cbr-/alpha/A/header_search/","Advanced Practice (District Nursing)","MSc","0"},
		{"15","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice--obr-leadership-cbr-/alpha/A/header_search/","Advanced Practice (Leadership)","MSc/PG Dip/PG Cert","0"},
		{"16","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice--obr-midwifery-cbr-/alpha/A/header_search/","Advanced Practice (Midwifery)","MSc/PG Dip/PG Cert","0"},
		{"17","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice-specialist-community/alpha/A/header_search/","Advanced Practice (Specialist Community Public Health Nursing/ Health Visiting/School Nursing)","MSc/PG Dip","0"},
		{"18","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-software-engineering/alpha/A/header_search/","Advanced Software Engineering","MSc","0"},
		{"19","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-software-engineering-with-management-msc/alpha/A/header_search/","Advanced Software Engineering with Management","MSc","0"},
		{"20","http://www.kcl.ac.uk/prospectus/graduate/structure/name/aesthetic-dentistry/alpha/A/header_search/","Aesthetic Dentistry","MSc","0"},
		{"21","http://www.kcl.ac.uk/prospectus/graduate/structure/name/affective-disorders/alpha/A/header_search/","Affective Disorders","MSc","0"},
		{"22","http://www.kcl.ac.uk/prospectus/graduate/structure/name/ageing-in-society/alpha/A/header_search/","Ageing & Society","MA/MSc/PG Dip/PG Cert","0"},
		{"23","http://www.kcl.ac.uk/prospectus/graduate/structure/name/air-power-in-the-modern-world/alpha/A/header_search/","Air Power in the Modern World","MA/PG Dip","0"},
		{"24","http://www.kcl.ac.uk/prospectus/graduate/structure/name/analytical-science-for-industry/alpha/A/header_search/","Analytical Science for Industry","MSc","0"},
		{"25","http://www.kcl.ac.uk/prospectus/graduate/structure/name/analytical-toxicology/alpha/A/header_search/","Analytical Toxicology","MSc","0"},
		{"26","http://www.kcl.ac.uk/prospectus/graduate/structure/name/ancient-history/alpha/A/header_search/","Ancient History","MA","0"},
		{"27","http://www.kcl.ac.uk/prospectus/graduate/structure/name/applied-linguistics-and-english-language-teaching/alpha/A/header_search/","Applied Linguistics and English Language Teaching","MA","0"},
		{"28","http://www.kcl.ac.uk/prospectus/graduate/structure/name/aquatic-resource-management/alpha/A/header_search/","Aquatic Resource Management","MSc","0"},
		{"29","http://www.kcl.ac.uk/prospectus/graduate/structure/name/classical-art-and-archaeology/alpha/A/header_search/","Archaeology, Classical Art","MA","0"},
		{"30","http://www.kcl.ac.uk/prospectus/graduate/structure/name/arts-and-cultural-management-ma/alpha/A/header_search/","Arts & Cultural Management","MA","0"},
		{"31","http://www.kcl.ac.uk/prospectus/graduate/structure/name/asian-and-european-affairs/alpha/A/header_search/","Asian & European Affairs, Double Masters","MA","0"},
		{"32","http://www.kcl.ac.uk/prospectus/graduate/structure/name/banking-and-finance/alpha/B/header_search/","Banking and Finance","MSc","0"},
		{"33","http://www.kcl.ac.uk/prospectus/graduate/structure/name/biblical-studies/alpha/B/header_search/","Biblical Studies, with pathways (Language and Literature; Theology)","MA","0"},
		{"34","http://www.kcl.ac.uk/prospectus/graduate/structure/name/research-biobanking/alpha/B/header_search/","Biobanking Research","MSc","0"},
		{"35","http://www.kcl.ac.uk/prospectus/graduate/structure/name/bioethics-and-society/alpha/B/header_search/","Bioethics & Society","MA, PG Diploma, PG Certificate","0"},
		{"36","http://www.kcl.ac.uk/prospectus/graduate/structure/name/biomedical-and-molecular-sciences-research/alpha/B/header_search/","Biomedical & Molecular Sciences Research","MSc/MRes","0"},
		{"37","http://www.kcl.ac.uk/prospectus/graduate/structure/name/biopharmaceuticals/alpha/B/header_search/","Biopharmaceuticals","MSc","0"},
		{"38","http://www.kcl.ac.uk/prospectus/graduate/structure/name/brazil-in-global-perspective/alpha/B/header_search/","Brazil in Global Perspective","MSc","0"},
		{"39","http://www.kcl.ac.uk/prospectus/graduate/structure/name/engineering-with-management/alpha/B/header_search/","Business Management, Engineering","MSc","0"},
		{"40","http://www.kcl.ac.uk/prospectus/graduate/structure/name/late-antique-and-byzantine-studies/alpha/B/header_search/","Byzantine & Late Antique Studies","MA","0"},
		{"41","http://www.kcl.ac.uk/prospectus/graduate/structure/name/late-antique-and-byzantine-studies-grad-dip/alpha/B/header_search/","Byzantine & Late Antique Studies","Grad Dip","0"},
		{"42","http://www.kcl.ac.uk/prospectus/graduate/structure/name/translational-cancer-medicine/alpha/C/header_search/","Cancer Medicine (Translational)","MRes","0"},
		{"43","http://www.kcl.ac.uk/prospectus/graduate/structure/name/cardiovascular-research/alpha/C/header_search/","Cardiovascular Research","MSc","0"},
		{"44","http://www.kcl.ac.uk/prospectus/graduate/structure/name/cbt-informed-amp-carer-supportive-practice-in-psychosis/alpha/C/header_search/","CBT Informed & Carer Supportive Practice in Psychosis","Grad Cert/Grad Dip","0"},
		{"45","http://www.kcl.ac.uk/prospectus/graduate/structure/name/cellular-therapy-from-bench-to-market/alpha/C/header_search/","Cellular Therapy from Bench to Market","MSc","0"},
		{"46","http://www.kcl.ac.uk/prospectus/graduate/structure/name/child-and-adolescent-mental-health/alpha/C/header_search/","Child & Adolescent Mental Health","MSc","0"},
		{"47","http://www.kcl.ac.uk/prospectus/graduate/structure/name/child-studies/alpha/C/header_search/","Child Studies","MA","0"},
		{"48","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-child-studies/alpha/C/header_search/","Child Studies (International)","MA","0"},
		{"49","http://www.kcl.ac.uk/prospectus/graduate/structure/name/china-and-globalisation/alpha/C/header_search/","China & Globalisation","MSc","0"},
		{"50","http://www.kcl.ac.uk/prospectus/graduate/structure/name/christianity-and-the-arts/alpha/C/header_search/","Christianity & the Arts","MA","0"},
		{"51","http://www.kcl.ac.uk/prospectus/graduate/structure/name/film-studies/alpha/C/header_search/","Cinema Cultures (former title)","MA","0"},
		{"52","http://www.kcl.ac.uk/prospectus/graduate/structure/name/sustainable-cities/alpha/C/header_search/","Cities, Sustainable","MA/MSc","0"},
		{"53","http://www.kcl.ac.uk/prospectus/graduate/structure/name/classical-art-and-archaeology/alpha/C/header_search/","Classical Art & Archaeology","MA","0"},
		{"54","http://www.kcl.ac.uk/prospectus/graduate/structure/name/classical-studies/alpha/C/header_search/","Classical Studies","Grad Dip","0"},
		{"55","http://www.kcl.ac.uk/prospectus/graduate/structure/name/classical-world-and-its-reception-/alpha/C/header_search/","Classical World & Its Reception, The","MA","0"},
		{"56","http://www.kcl.ac.uk/prospectus/graduate/structure/name/classics/alpha/C/header_search/","Classics","MA","0"},
		{"57","http://www.kcl.ac.uk/prospectus/graduate/structure/name/climate-change-environment-science-and-policy/alpha/C/header_search/","Climate Change: Environment, Science and Policy","MSc","0"},
		{"58","http://www.kcl.ac.uk/prospectus/graduate/structure/name/climate-change-history-culture-society/alpha/C/header_search/","Climate Change: History, Culture, Society","MA","0"},
		{"59","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-dermatology/alpha/C/header_search/","Clinical Dermatology","MSc","0"},
		{"60","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-education/alpha/C/header_search/","Clinical Education","MA/PG Dip/PG Cert","0"},
		{"61","http://www.kcl.ac.uk/prospectus/graduate/structure/name/forensic-mental-health/alpha/C/header_search/","Clinical Forensic Psychology","MSc/ PG Dip","0"},
		{"62","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-neurodevelopmental-sciences/alpha/C/header_search/","Clinical Neurodevelopmental Sciences","MSc","0"},
		{"63","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-neuroscience/alpha/C/header_search/","Clinical Neuroscience","MSc","0"},
		{"64","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-nursing/alpha/C/header_search/","Clinical Nursing","MSc","0"},
		{"65","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-pharmacology/alpha/C/header_search/","Clinical Pharmacology","MSc/PG Dip/PG Cert","0"},
		{"66","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-research/alpha/C/header_search/","Clinical Research","MRes","0"},
		{"67","http://www.kcl.ac.uk/prospectus/graduate/structure/name/cognitive-behavioural-therapies/alpha/C/header_search/","Cognitive Behavioural Therapies","PG Dip","0"},
		{"68","http://www.kcl.ac.uk/prospectus/graduate/structure/name/cognitive-behavioural-therapy-for-psychosis/alpha/C/header_search/","Cognitive Behavioural Therapy for Psychosis","PG Dip/PG Cert","0"},
		{"69","http://www.kcl.ac.uk/prospectus/graduate/structure/name/comparative-literature/alpha/C/header_search/","Comparative Literature","MA","0"},
		{"70","http://www.kcl.ac.uk/prospectus/graduate/structure/name/competition-law/alpha/C/header_search/","Competition Law","LLM","0"},
		{"71","http://www.kcl.ac.uk/prospectus/graduate/structure/name/economics-for-competition-law/alpha/C/header_search/","Competition Law, Economics","MA, PG Dip","0"},
		{"72","http://www.kcl.ac.uk/prospectus/graduate/structure/name/eu-competition-law/alpha/C/header_search/","Competition Law, EU","MA, PG Dip","0"},
		{"73","http://www.kcl.ac.uk/prospectus/graduate/structure/name/complex-systems-modelling/alpha/C/header_search/","Complex Systems Modelling - From Biomedical and Natural to Economic and Social Sciences","MSc","0"},
		{"74","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computer-systems-engineering-with-management-msc/alpha/C/header_search/","Computer Systems Engineering with Management","MSc","0"},
		{"75","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computing-and-internet-systems/alpha/C/header_search/","Computing & Internet Systems","MSc","0"},
		{"76","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computing-and-security/alpha/C/header_search/","Computing & Security","MSc","0"},
		{"77","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computing-in-education/alpha/C/header_search/","Computing in Education","MA","0"},
		{"78","http://www.kcl.ac.uk/prospectus/graduate/structure/name/digital-humanities/alpha/C/header_search/","Computing in the Humanities","MA","0"},
		{"79","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-computing/alpha/C/header_search/","Computing, Advanced","MSc","0"},
		{"80","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computing,-it-law-and-management/alpha/C/header_search/","Computing, IT Law & Management","MSc","0"},
		{"81","http://www.kcl.ac.uk/prospectus/graduate/structure/name/conflict-resolution-in-divided-societies/alpha/C/header_search/","Conflict Resolution in Divided Societies","MA","0"},
		{"82","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-conflict-studies/alpha/C/header_search/","Conflict Studies, International","MA","0"},
		{"83","http://www.kcl.ac.uk/prospectus/graduate/structure/name/conflict,-security-and-development/alpha/C/header_search/","Conflict, Security & Development","MA","0"},
		{"84","http://www.kcl.ac.uk/prospectus/graduate/structure/name/conscious-sedation-for-dentistry/alpha/C/header_search/","Conscious Sedation for Dentistry","PG Dip","0"},
		{"85","http://www.kcl.ac.uk/prospectus/graduate/structure/name/construction-law-and-dispute-resolution/alpha/C/header_search/","Construction Law & Dispute Resolution","MSc","0"},
		{"86","http://www.kcl.ac.uk/prospectus/graduate/structure/name/global-ethics-amp-human-values/alpha/C/header_search/","Contemporary Global Ethics & Human Values","MA","0"},
		{"87","http://www.kcl.ac.uk/prospectus/graduate/structure/name/contemporary-british-history/alpha/C/header_search/","Contemporary History","MA","0"},
		{"88","http://www.kcl.ac.uk/prospectus/graduate/structure/name/contemporary-india/alpha/C/header_search/","Contemporary India","MA","0"},
		{"89","http://www.kcl.ac.uk/prospectus/graduate/structure/name/contemporary-india--obr-research-cbr-/alpha/C/header_search/","Contemporary India (Research)","MRes","0"},
		{"90","http://www.kcl.ac.uk/prospectus/graduate/structure/name/contemporary-literature-culture-and-theory/alpha/C/header_search/","Contemporary Literature, Culture & Theory","MA","0"},
		{"91","http://www.kcl.ac.uk/prospectus/graduate/structure/name/uk-eu-and-us-copyright-law/alpha/C/header_search/","Copyright Law (UK, EU & US)","MA, PG Dip","0"},
		{"92","http://www.kcl.ac.uk/prospectus/graduate/structure/name/forensic-science/alpha/C/header_search/","Criminalistics (US)","MSc, MRes, PG Dip, PG Cert","0"},
		{"93","http://www.kcl.ac.uk/prospectus/graduate/structure/name/critical-methodologies/alpha/C/header_search/","Critical Methodologies","MA","0"},
		{"94","http://www.kcl.ac.uk/prospectus/graduate/structure/name/cultural-and-creative-industries/alpha/C/header_search/","Cultural & Creative Industries","MA","0"},
		{"95","http://www.kcl.ac.uk/prospectus/graduate/structure/name/data-science/alpha/D/header_search/","Data Science","MSc","0"},
		{"96","http://www.kcl.ac.uk/prospectus/graduate/structure/name/dental-public-health/alpha/D/header_search/","Dental Public Health","MSc","0"},
		{"97","http://www.kcl.ac.uk/prospectus/graduate/structure/name/conscious-sedation-for-dentistry/alpha/D/header_search/","Dentistry, Conscious Sedation for","PG Dip","0"},
		{"98","http://www.kcl.ac.uk/prospectus/graduate/structure/name/special-care-dentistry/alpha/D/header_search/","Dentistry, Sedation & Special Care","MSc","0"},
		{"99","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-dermatology/alpha/D/header_search/","Dermatology, Clinical","MSc","0"},
		{"100","http://www.kcl.ac.uk/prospectus/graduate/structure/name/dietetics/alpha/D/header_search/","Dietetics","MSc, PG Dip","0"},
		{"101","http://www.kcl.ac.uk/prospectus/graduate/structure/name/digital-asset-management/alpha/D/header_search/","Digital Asset & Media Management","MA","0"},
		{"102","http://www.kcl.ac.uk/prospectus/graduate/structure/name/digital-culture-and-technology/alpha/D/header_search/","Digital Culture & Society","MA","0"},
		{"103","http://www.kcl.ac.uk/prospectus/graduate/structure/name/digital-curation/alpha/D/header_search/","Digital Curation","MA","0"},
		{"104","http://www.kcl.ac.uk/prospectus/graduate/structure/name/digital-humanities/alpha/D/header_search/","Digital Humanities","MA","0"},
		{"105","http://www.kcl.ac.uk/prospectus/graduate/structure/name/disasters,-adaptation-and-development/alpha/D/header_search/","Disasters, Adaptation & Development","MA/MSc","0"},
		{"106","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice--obr-district-nursing-cbr-/alpha/D/header_search/","District Nursing (Advanced Practice)","MSc","0"},
		{"107","http://www.kcl.ac.uk/prospectus/graduate/structure/name/asian-and-european-affairs/alpha/D/header_search/","Double Masters in Asian & European Affairs","MA","0"},
		{"108","http://www.kcl.ac.uk/prospectus/graduate/structure/name/drug-development-science/alpha/D/header_search/","Drug Development Science","MSc/PG Dip/PG Cert","0"},
		{"109","http://www.kcl.ac.uk/prospectus/graduate/structure/name/drug-discovery-skills/alpha/D/header_search/","Drug Discovery Skills","MSc","0"},
		{"110","http://www.kcl.ac.uk/prospectus/graduate/structure/name/early-intervention-in-psychosis/alpha/E/header_search/","Early Intervention in Psychosis","MSc","0"},
		{"111","http://www.kcl.ac.uk/prospectus/graduate/structure/name/early-modern-english-literature-text-and-transmission/alpha/E/header_search/","Early Modern English Literature: Text & Transmission","MA","0"},
		{"112","http://www.kcl.ac.uk/prospectus/graduate/structure/name/early-modern-history/alpha/E/header_search/","Early Modern History","MA","0"},
		{"113","http://www.kcl.ac.uk/prospectus/graduate/structure/name/economics-for-competition-law/alpha/E/header_search/","Economics for Competition Law, Law","MA, PG Dip","0"},
		{"114","http://www.kcl.ac.uk/prospectus/graduate/structure/name/education-and-professional-studies/alpha/E/header_search/","Education & Professional Studies","MA","0"},
		{"115","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce/alpha/E/header_search/","Education (PGCE)","PGCE","0"},
		{"116","http://www.kcl.ac.uk/prospectus/graduate/structure/name/education-in-arts-and-cultural-settings/alpha/E/header_search/","Education in Arts & Cultural Settings","MA","0"},
		{"117","http://www.kcl.ac.uk/prospectus/graduate/structure/name/education-management/alpha/E/header_search/","Education Management","MA","0"},
		{"118","http://www.kcl.ac.uk/prospectus/graduate/structure/name/education,-policy-and-society/alpha/E/header_search/","Education, Policy & Society","MA","0"},
		{"119","http://www.kcl.ac.uk/prospectus/graduate/structure/name/eighteenth-century-studies/alpha/E/header_search/","Eighteenth-Century Studies","MA","0"},
		{"120","http://www.kcl.ac.uk/prospectus/graduate/structure/name/electronic-engineering-with-management/alpha/E/header_search/","Electronic Engineering with Management","MSc","0"},
		{"121","http://www.kcl.ac.uk/prospectus/graduate/structure/name/emerging-economies-and-inclusive-development/alpha/E/header_search/","Emerging Economies and Inclusive Development","MSc","0"},
		{"122","http://www.kcl.ac.uk/prospectus/graduate/structure/name/emerging-economies-and-international-development/alpha/E/header_search/","Emerging Economies and International Development","MSc","0"},
		{"123","http://www.kcl.ac.uk/prospectus/graduate/structure/name/school-direct-qts/alpha/E/header_search/","Employment-based School Direct Training","QTS","0"},
		{"124","http://www.kcl.ac.uk/prospectus/graduate/structure/name/employment-based-school-direct-training-qts-only/alpha/E/header_search/","Employment-based School Direct Training (QTS only)","TBC","0"},
		{"125","http://www.kcl.ac.uk/prospectus/graduate/structure/name/palliative-care/alpha/E/header_search/","End of Life Care","MSc/PG Dip/PG Cert","0"},
		{"126","http://www.kcl.ac.uk/prospectus/graduate/structure/name/endodontics-pg-dip/alpha/E/header_search/","Endodontics","PG Dip","0"},
		{"127","http://www.kcl.ac.uk/prospectus/graduate/structure/name/endodontics/alpha/E/header_search/","Endodontics (Distance Learning)","MSc","0"},
		{"128","http://www.kcl.ac.uk/prospectus/graduate/structure/name/endodontology/alpha/E/header_search/","Endodontology","MClinDent","0"},
		{"129","http://www.kcl.ac.uk/prospectus/graduate/structure/name/engineering-with-management/alpha/E/header_search/","Engineering with Management","MSc","0"},
		{"130","http://www.kcl.ac.uk/prospectus/graduate/structure/name/english-in-education/alpha/E/header_search/","English in Education","MA","0"},
		{"131","http://www.kcl.ac.uk/prospectus/graduate/structure/name/english-1850-present/alpha/E/header_search/","English: 1850-Present","MA","0"},
		{"132","http://www.kcl.ac.uk/prospectus/graduate/structure/name/environment-and-development/alpha/E/header_search/","Environment & Development","MA/MSc","0"},
		{"133","http://www.kcl.ac.uk/prospectus/graduate/structure/name/environment,-politics-and-globalisation/alpha/E/header_search/","Environment, Politics & Globalisation","MA/MSc","0"},
		{"134","http://www.kcl.ac.uk/prospectus/graduate/structure/name/environmental-monitoring,-modelling-and-management/alpha/E/header_search/","Environmental Monitoring, Modelling & Management","MSc","0"},
		{"135","http://www.kcl.ac.uk/prospectus/graduate/structure/name/eu-competition-law/alpha/E/header_search/","EU Competition Law","MA, PG Dip","0"},
		{"136","http://www.kcl.ac.uk/prospectus/graduate/structure/name/uk-eu-and-us-copyright-law/alpha/E/header_search/","EU Copyright Law","MA, PG Dip","0"},
		{"137","http://www.kcl.ac.uk/prospectus/graduate/structure/name/european-union-law/alpha/E/header_search/","European Union Law","MA, PG Dip","0"},
		{"138","http://www.kcl.ac.uk/prospectus/graduate/structure/name/eurasian-political-economy-amp-energy/alpha/E/header_search/","Eurasian Political Economy & Energy","MSc","0"},
		{"139","http://www.kcl.ac.uk/prospectus/graduate/structure/name/european-history/alpha/E/header_search/","European History","MA","0"},
		{"140","http://www.kcl.ac.uk/prospectus/graduate/structure/name/european-law/alpha/E/header_search/","European Law","LLM","0"},
		{"141","http://www.kcl.ac.uk/prospectus/graduate/structure/name/european-political-economy/alpha/E/header_search/","European Political Economy","MA","0"},
		{"142","http://www.kcl.ac.uk/prospectus/graduate/structure/name/european-studies/alpha/E/header_search/","European Studies","MA","0"},
		{"143","http://www.kcl.ac.uk/prospectus/graduate/structure/name/family-therapy/alpha/F/header_search/","Family Therapy","MSc","0"},
		{"144","http://www.kcl.ac.uk/prospectus/graduate/structure/name/family-therapy-grad-cert/alpha/F/header_search/","Family Therapy","Grad Cert","0"},
		{"145","http://www.kcl.ac.uk/prospectus/graduate/structure/name/film-studies/alpha/F/header_search/","Film Studies (Film & Philosophy pathway available)","MA","0"},
		{"146","http://www.kcl.ac.uk/prospectus/graduate/structure/name/financial-mathematics/alpha/F/header_search/","Financial Mathematics","MSc","0"},
		{"147","http://www.kcl.ac.uk/prospectus/graduate/structure/name/fixed-and-removable-prosthodontics/alpha/F/header_search/","Fixed & Removable Prosthodontics","MClinDent","0"},
		{"148","http://www.kcl.ac.uk/prospectus/graduate/structure/name/forensic-mental-health/alpha/F/header_search/","Forensic Mental Health","MSc/ PG Dip","0"},
		{"149","http://www.kcl.ac.uk/prospectus/graduate/structure/name/forensic-science/alpha/F/header_search/","Forensic Science","MSc, MRes, PG Dip, PG Cert","0"},
		{"150","http://www.kcl.ac.uk/prospectus/graduate/structure/name/french-literature-and-culture/alpha/F/header_search/","French Literature & Culture","MA","0"},
		{"151","http://www.kcl.ac.uk/prospectus/graduate/structure/name/genes-environment-and-development/alpha/G/header_search/","Genes, Environment & Development","MSc","0"},
		{"152","http://www.kcl.ac.uk/prospectus/graduate/structure/name/genomic-medicine/alpha/G/header_search/","Genomic Medicine","MSc/PG Dip/PG Cert","0"},
		{"153","http://www.kcl.ac.uk/prospectus/graduate/structure/name/geography/alpha/G/header_search/","Geography","MA/MSc","0"},
		{"154","http://www.kcl.ac.uk/prospectus/graduate/structure/name/geopolitics,-territory-and-security/alpha/G/header_search/","Geopolitics, Territory & Security","MA","0"},
		{"155","http://www.kcl.ac.uk/prospectus/graduate/structure/name/german-and-comparative-literature-mres/alpha/G/header_search/","German & Comparative Literature","MRes","0"},
		{"156","http://www.kcl.ac.uk/prospectus/graduate/structure/name/gerontology/alpha/G/header_search/","Gerontology","MSc/PG Dip/PG Cert","0"},
		{"157","http://www.kcl.ac.uk/prospectus/graduate/structure/name/global-ethics-amp-human-values/alpha/G/header_search/","Global Ethics & Human Values","MA","0"},
		{"158","http://www.kcl.ac.uk/prospectus/graduate/structure/name/global-health/alpha/G/header_search/","Global Health","MSc","0"},
		{"159","http://www.kcl.ac.uk/prospectus/graduate/structure/name/global-health-social/alpha/G/header_search/","Global Health & Social Justice","MSc, PGDip, PGCert","0"},
		{"160","http://www.kcl.ac.uk/prospectus/graduate/structure/name/global-mental-health/alpha/G/header_search/","Global Mental Health","MSc","0"},
		{"161","http://www.kcl.ac.uk/prospectus/graduate/structure/name/south-asia-amp-global-security/alpha/G/header_search/","Global Security (South Asia and)","MA","0"},
		{"162","http://www.kcl.ac.uk/prospectus/graduate/structure/name/classical-studies/alpha/G/header_search/","Graduate Diploma Classical Studies","Grad Dip","0"},
		{"163","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-studies-and-academic-english/alpha/G/header_search/","Graduate Diploma in International Studies & Academic English","Grad Dip","0"},
		{"164","http://www.kcl.ac.uk/prospectus/graduate/structure/name/space-physiology-and-health/alpha/H/header_search/","Health & Space Physiology","MSc","0"},
		{"165","http://www.kcl.ac.uk/prospectus/graduate/structure/name/health-psychology/alpha/H/header_search/","Health Psychology","MSc","0"},
		{"166","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice-specialist-community/alpha/H/header_search/","Health Visiting","MSc/PG Dip","0"},
		{"167","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice--obr-leadership-cbr-/alpha/H/header_search/","Healthcare Leadership","MSc/PG Dip/PG Cert","0"},
		{"168","http://www.kcl.ac.uk/prospectus/graduate/structure/name/history/alpha/H/header_search/","History","MRes","0"},
		{"169","http://www.kcl.ac.uk/prospectus/graduate/structure/name/politics-contemporary-history/alpha/H/header_search/","History (Politics & Contemporary)","MA","0"},
		{"170","http://www.kcl.ac.uk/prospectus/graduate/structure/name/history-of-philosophy/alpha/H/header_search/","History of Philosophy","MA","0"},
		{"171","http://www.kcl.ac.uk/prospectus/graduate/structure/name/history-of-war/alpha/H/header_search/","History of War","MA","0"},
		{"172","http://www.kcl.ac.uk/prospectus/graduate/structure/name/contemporary-british-history/alpha/H/header_search/","History, Contemporary British","MA","0"},
		{"173","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medieval-history/alpha/H/header_search/","History, Medieval","MA","0"},
		{"174","http://www.kcl.ac.uk/prospectus/graduate/structure/name/world-history-and-cultures/alpha/H/header_search/","History, World & Cultures","MA","0"},
		{"175","http://www.kcl.ac.uk/prospectus/graduate/structure/name/early-modern-history/alpha/H/header_search/","History: Early Modern","MA","0"},
		{"176","http://www.kcl.ac.uk/prospectus/graduate/structure/name/modern-history/alpha/H/header_search/","History: Modern","MA","0"},
		{"177","http://www.kcl.ac.uk/prospectus/graduate/structure/name/human-and-applied-physiology/alpha/H/header_search/","Human & Applied Physiology","MSc","0"},
		{"178","http://www.kcl.ac.uk/prospectus/graduate/structure/name/human-resource-management-and-organisational-analysis/alpha/H/header_search/","Human Resource Management & Organisational Analysis","MSc","0"},
		{"179","http://www.kcl.ac.uk/prospectus/graduate/structure/name/digital-humanities/alpha/H/header_search/","Humanities (Digital)","MA","0"},
		{"180","http://www.kcl.ac.uk/prospectus/graduate/structure/name/immunology/alpha/I/header_search/","Immunology","MSc","0"},
		{"181","http://www.kcl.ac.uk/prospectus/graduate/structure/name/implementation-and-improvement-science/alpha/I/header_search/","Implementation and Improvement Science","MSc","0"},
		{"182","http://www.kcl.ac.uk/prospectus/graduate/structure/name/emerging-economies-and-inclusive-development/alpha/I/header_search/","Inclusive Development and Emerging Economies","MSc","0"},
		{"183","http://www.kcl.ac.uk/prospectus/graduate/structure/name/contemporary-india/alpha/I/header_search/","Indian Studies","MA","0"},
		{"184","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computing,-it-law-and-management/alpha/I/header_search/","IT Law, Management & Computing","MSc","0"},
		{"185","http://www.kcl.ac.uk/prospectus/graduate/structure/name/intellectual-property-and-information-law/alpha/I/header_search/","Intellectual Property & Information Law","LLM","0"},
		{"186","http://www.kcl.ac.uk/prospectus/graduate/structure/name/intelligence-and-international-security/alpha/I/header_search/","Intelligence & International Security","MA","0"},
		{"187","http://www.kcl.ac.uk/prospectus/graduate/structure/name/intelligent-systems/alpha/I/header_search/","Intelligent Systems","MSc","0"},
		{"188","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-business-law/alpha/I/header_search/","International Business Law","LLM","0"},
		{"189","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-child-studies/alpha/I/header_search/","International Child Studies","MA","0"},
		{"190","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-conflict-studies/alpha/I/header_search/","International Conflict Studies","MA","0"},
		{"191","http://www.kcl.ac.uk/prospectus/graduate/structure/name/emerging-economies-and-international-development/alpha/I/header_search/","International Development and Emerging Economies","MSc","0"},
		{"192","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-dispute-resolution/alpha/I/header_search/","International Dispute Resolution","LLM","0"},
		{"193","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-financial-law/alpha/I/header_search/","International Financial Law","LLM","0"},
		{"194","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-management/alpha/I/header_search/","International Management","MSc","0"},
		{"195","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-marketing/alpha/I/header_search/","International Marketing","MSc","0"},
		{"196","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-peace-and-security/alpha/I/header_search/","International Peace & Security","MA","0"},
		{"197","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-political-economy/alpha/I/header_search/","International Political Economy","MA","0"},
		{"198","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-general-pre-masters-programme/alpha/I/header_search/","International Pre-Master's Programme","Diploma","0"},
		{"199","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-programme-in-addiction-studies/alpha/I/header_search/","International Programme in Addiction Studies","MSc/PG Cert/PG Dip","0"},
		{"200","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-relations/alpha/I/header_search/","International Relations","MA","0"},
		{"201","http://www.kcl.ac.uk/prospectus/graduate/structure/name/ir-war/alpha/I/header_search/","International Relations & Contemporary War","MA/PG Dip","0"},
		{"202","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-studies-and-academic-english/alpha/I/header_search/","International Studies & Academic English","Grad Dip","0"},
		{"203","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-tax-llm/alpha/I/header_search/","International Tax","LLM","0"},
		{"204","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computing-and-internet-systems/alpha/I/header_search/","Internet Systems & Computing","MSc","0"},
		{"205","http://www.kcl.ac.uk/prospectus/graduate/structure/name/jewish-studies/alpha/J/header_search/","Jewish Studies","MA","0"},
		{"206","http://www.kcl.ac.uk/prospectus/graduate/structure/name/language-and-cultural-diversity/alpha/L/header_search/","Language & Cultural Diversity","MA","0"},
		{"207","http://www.kcl.ac.uk/prospectus/graduate/structure/name/late-antique-and-byzantine-studies-grad-dip/alpha/L/header_search/","Late Antique & Byzantine Studies","Grad Dip","0"},
		{"208","http://www.kcl.ac.uk/prospectus/graduate/structure/name/late-antique-and-byzantine-studies/alpha/L/header_search/","Late Antique & Byzantine Studies","MA","0"},
		{"209","http://www.kcl.ac.uk/prospectus/graduate/structure/name/latin-american-development/alpha/L/header_search/","Latin American Development","MSc","0"},
		{"210","http://www.kcl.ac.uk/prospectus/graduate/structure/name/master-of-laws/alpha/L/header_search/","LLM","LLM","0"},
		{"211","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-ethics-and-law/alpha/L/header_search/","Law & Medical Ethics","MA","0"},
		{"212","http://www.kcl.ac.uk/prospectus/graduate/structure/name/economics-for-competition-law/alpha/L/header_search/","Law - Economics for Competition Law","MA, PG Dip","0"},
		{"213","http://www.kcl.ac.uk/prospectus/graduate/structure/name/eu-competition-law/alpha/L/header_search/","Law - EU Competition Law","MA, PG Dip","0"},
		{"214","http://www.kcl.ac.uk/prospectus/graduate/structure/name/uk-eu-and-us-copyright-law/alpha/L/header_search/","Law, Copyright","MA, PG Dip","0"},
		{"215","http://www.kcl.ac.uk/prospectus/graduate/structure/name/european-union-law/alpha/L/header_search/","Law, EU","MA, PG Dip","0"},
		{"216","http://www.kcl.ac.uk/prospectus/graduate/structure/name/leadership-development/alpha/L/header_search/","Leadership & Development","MSc","0"},
		{"217","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice--obr-leadership-cbr-/alpha/L/header_search/","Leadership (Advanced Practice)","MSc/PG Dip/PG Cert","0"},
		{"218","http://www.kcl.ac.uk/prospectus/graduate/structure/name/competition-law/alpha/L/header_search/","LLM Competition Law","LLM","0"},
		{"219","http://www.kcl.ac.uk/prospectus/graduate/structure/name/european-law/alpha/L/header_search/","LLM in European Law","LLM","0"},
		{"220","http://www.kcl.ac.uk/prospectus/graduate/structure/name/intellectual-property-and-information-law/alpha/L/header_search/","LLM in Intellectual Property Law","LLM","0"},
		{"221","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-business-law/alpha/L/header_search/","LLM in International Business Law","LLM","0"},
		{"222","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-financial-law/alpha/L/header_search/","LLM in International Financial Law","LLM","0"},
		{"223","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-tax-llm/alpha/L/header_search/","LLM in Tax Law","LLM","0"},
		{"224","http://www.kcl.ac.uk/prospectus/graduate/structure/name/transnational-law/alpha/L/header_search/","LLM in Transnational Law","LLM","0"},
		{"225","http://www.kcl.ac.uk/prospectus/graduate/structure/name/public-policy/alpha/M/header_search/","Management & Public Services Policy","MA","0"},
		{"226","http://www.kcl.ac.uk/prospectus/graduate/structure/name/computing,-it-law-and-management/alpha/M/header_search/","Management, Computing & IT Law","MSc","0"},
		{"227","http://www.kcl.ac.uk/prospectus/graduate/structure/name/human-resource-management-and-organisational-analysis/alpha/M/header_search/","Management, Human Resources","MSc","0"},
		{"228","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-management/alpha/M/header_search/","Management, International","MSc","0"},
		{"229","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-marketing/alpha/M/header_search/","Marketing (International)","MSc","0"},
		{"230","http://www.kcl.ac.uk/prospectus/graduate/structure/name/master-of-laws/alpha/M/header_search/","Master of Laws","LLM","0"},
		{"231","http://www.kcl.ac.uk/prospectus/graduate/structure/name/public-health/alpha/M/header_search/","MSc in Public Health","MPH/MSc/MPH (Primary Care)","0"},
		{"232","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mathematics-grad-dip/alpha/M/header_search/","Mathematics","Grad Dip","0"},
		{"233","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mathematics/alpha/M/header_search/","Mathematics","MSc","0"},
		{"234","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mathematics-education/alpha/M/header_search/","Mathematics Education","MA","0"},
		{"235","http://www.kcl.ac.uk/prospectus/graduate/structure/name/maxillofacial-and-craniofacial-technology/alpha/M/header_search/","Maxillofacial & Craniofacial Technology","MSc","0"},
		{"236","http://www.kcl.ac.uk/prospectus/graduate/structure/name/maxillofacial-prosthetic-rehabilitation/alpha/M/header_search/","Maxillofacial Prosthetic Rehabilitation","MSc","0"},
		{"237","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-engineering-and-physics/alpha/M/header_search/","Medical Engineering & Physics","MSc","0"},
		{"238","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-ethics-and-law/alpha/M/header_search/","Medical Ethics & Law","MA","0"},
		{"239","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-humanities/alpha/M/header_search/","Medical Humanities","MSc","0"},
		{"240","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-imaging-sciences/alpha/M/header_search/","Medical Imaging Sciences","MRes","0"},
		{"241","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-immunology/alpha/M/header_search/","Medical Immunology","MSc/PG Dip/PG Cert","0"},
		{"242","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-law/alpha/M/header_search/","Medical Law","MA","0"},
		{"243","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-ultrasound/alpha/M/header_search/","Medical Ultrasound","MSc/PG Dip/PG Cert","0"},
		{"244","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medicine-science-and-society/alpha/M/header_search/","Medicine, Health and Public Policy","MSc/PG Dip/PG Cert","0"},
		{"245","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medieval-english/alpha/M/header_search/","Medieval English","MA","0"},
		{"246","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medieval-history/alpha/M/header_search/","Medieval History","MA","0"},
		{"247","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medieval-studies/alpha/M/header_search/","Medieval Studies","MA","0"},
		{"248","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mental-health-service-and-population-research/alpha/M/header_search/","Mental Health Service & Population Research","MSc","0"},
		{"249","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mental-health-studies/alpha/M/header_search/","Mental Health Studies","MSc","0"},
		{"250","http://www.kcl.ac.uk/prospectus/graduate/structure/name/child-and-adolescent-mental-health/alpha/M/header_search/","Mental Health, Child & Adolescent","MSc","0"},
		{"251","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mental-health-ethics-and-law-msc/alpha/M/header_search/","Mental Health, Ethics and Law","MSc","0"},
		{"252","http://www.kcl.ac.uk/prospectus/graduate/structure/name/middle-eastern-studies/alpha/M/header_search/","Middle Eastern Studies","MA","0"},
		{"253","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice--obr-midwifery-cbr-/alpha/M/header_search/","Midwifery (Advanced Practice)","MSc/PG Dip/PG Cert","0"},
		{"254","http://www.kcl.ac.uk/prospectus/graduate/structure/name/midwifery-with-registration-graduate-entry/alpha/M/header_search/","Midwifery with registration (graduate entry)","PGDip","0"},
		{"255","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mobile-and-personal-communications/alpha/M/header_search/","Mobile & Personal Communications","MSc","0"},
		{"256","http://www.kcl.ac.uk/prospectus/graduate/structure/name/mobile-internet-research/alpha/M/header_search/","Mobile Internet Research","MSc","0"},
		{"257","http://www.kcl.ac.uk/prospectus/graduate/structure/name/modern-foreign-languages-education/alpha/M/header_search/","Modern Foreign Languages Education","MA","0"},
		{"258","http://www.kcl.ac.uk/prospectus/graduate/structure/name/modern-history/alpha/M/header_search/","Modern History","MA","0"},
		{"259","http://www.kcl.ac.uk/prospectus/graduate/structure/name/biomedical-and-molecular-sciences-research/alpha/M/header_search/","Molecular & Biomedical Sciences Research","MSc/MRes","0"},
		{"260","http://www.kcl.ac.uk/prospectus/graduate/structure/name/molecular-biophysics/alpha/M/header_search/","Molecular Biophysics","MRes","0"},
		{"261","http://www.kcl.ac.uk/prospectus/graduate/structure/name/music/alpha/M/header_search/","Music","MMus","0"},
		{"262","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-musical-studies/alpha/M/header_search/","Musical Studies, Advanced","PG Cert","0"},
		{"263","http://www.kcl.ac.uk/prospectus/graduate/structure/name/neuroimaging/alpha/N/header_search/","Neuroimaging","MSc","0"},
		{"264","http://www.kcl.ac.uk/prospectus/graduate/structure/name/neuroscience/alpha/N/header_search/","Neuroscience","MSc","0"},
		{"265","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-neuroscience/alpha/N/header_search/","Neuroscience, Clinical","MSc","0"},
		{"266","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nineteenth-century-studies/alpha/N/header_search/","Nineteenth-Century Studies","MA","0"},
		{"267","http://www.kcl.ac.uk/prospectus/graduate/structure/name/non-equilibrium-systems-theoretical-modelling-simulation-and-data-driven-analysis/alpha/N/header_search/","Non-Equilibrium Systems: Theoretical Modelling, Simulation and Data-Driven Analysis","TBC","0"},
		{"268","http://www.kcl.ac.uk/prospectus/graduate/structure/name/non-proliferation-and-international-security/alpha/N/header_search/","Non-Proliferation & International Security","MA","0"},
		{"269","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nuclear-medicine-science-and-practice/alpha/N/header_search/","Nuclear Medicine: Science & Practice","MSc/PG Dip/PG Cert","0"},
		{"270","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursingmsc/alpha/N/header_search/","Nursing","MSc","0"},
		{"271","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-with-registration-graduate-entry/alpha/N/header_search/","Nursing with registration (graduate entry)","PG Dip","0"},
		{"272","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-adult-pg-dip/alpha/N/header_search/","Nursing with registration - adult (graduate entry)","PG Dip","0"},
		{"273","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-child-pg-dip/alpha/N/header_search/","Nursing with registration - child (graduate entry)","PG Dip","0"},
		{"274","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nursing-mental-health-pg-dip/alpha/N/header_search/","Nursing with registration - mental health (graduate entry)","PG Dip","0"},
		{"275","http://www.kcl.ac.uk/prospectus/graduate/structure/name/nutrition/alpha/N/header_search/","Nutrition","MSc/PG Dip","0"},
		{"276","http://www.kcl.ac.uk/prospectus/graduate/structure/name/organisational-psychiatry-and-psychology/alpha/O/header_search/","Organisational Psychiatry & Psychology","MSc","0"},
		{"277","http://www.kcl.ac.uk/prospectus/graduate/structure/name/orthodontics/alpha/O/header_search/","Orthodontics","MSc","0"},
		{"278","http://www.kcl.ac.uk/prospectus/graduate/structure/name/paediatric-dentistry/alpha/P/header_search/","Paediatric Dentistry","MSc","0"},
		{"279","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-paediatrics/alpha/P/header_search/","Paediatrics (Advanced)","MSc","0"},
		{"280","http://www.kcl.ac.uk/prospectus/graduate/structure/name/palliative-care/alpha/P/header_search/","Palliative Care","MSc/PG Dip/PG Cert","0"},
		{"281","http://www.kcl.ac.uk/prospectus/graduate/structure/name/periodontology/alpha/P/header_search/","Periodontology","MClinDent","0"},
		{"282","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pharmaceutical-analysis-and-quality-control/alpha/P/header_search/","Pharmaceutical Analysis & Quality Control","MSc","0"},
		{"283","http://www.kcl.ac.uk/prospectus/graduate/structure/name/clinical-pharmacology/alpha/P/header_search/","Pharmaceutical Medicine (Clinical Pharmacology)","MSc/PG Dip/PG Cert","0"},
		{"284","http://www.kcl.ac.uk/prospectus/graduate/structure/name/drug-development-science/alpha/P/header_search/","Pharmaceutical Medicine (Drug Development Science)","MSc/PG Dip/PG Cert","0"},
		{"285","http://www.kcl.ac.uk/prospectus/graduate/structure/name/translational-medicine/alpha/P/header_search/","Pharmaceutical Medicine (Translational Medicine)","MSc/PG Dip/PG Cert","0"},
		{"286","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pharmaceutical-technology/alpha/P/header_search/","Pharmaceutical Technology","MSc","0"},
		{"287","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pharmacology/alpha/P/header_search/","Pharmacology","MSc/MRes","0"},
		{"288","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pharmacy-practice/alpha/P/header_search/","Pharmacy Practice","MSc, MSc (Prescribing), PG Dip, PG Dip (Prescribing), PG Cert","0"},
		{"289","http://www.kcl.ac.uk/prospectus/graduate/structure/name/philosophy/alpha/P/header_search/","Philosophy","MA","0"},
		{"290","http://www.kcl.ac.uk/prospectus/graduate/structure/name/film-studies/alpha/P/header_search/","Philosophy & Film Studies (pathway)","MA","0"},
		{"291","http://www.kcl.ac.uk/prospectus/graduate/structure/name/philosophy-of-medicine/alpha/P/header_search/","Philosophy of Medicine","MA","0"},
		{"292","http://www.kcl.ac.uk/prospectus/graduate/structure/name/philosophy-of-psychology/alpha/P/header_search/","Philosophy of Psychology","MA","0"},
		{"293","http://www.kcl.ac.uk/prospectus/graduate/structure/name/physics-graduate-diploma/alpha/P/header_search/","Physics","Grad Dip","0"},
		{"294","http://www.kcl.ac.uk/prospectus/graduate/structure/name/physics/alpha/P/header_search/","Physics","MSc","0"},
		{"295","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-engineering-and-physics/alpha/P/header_search/","Physics & Medical Engineering","MSc","0"},
		{"296","http://www.kcl.ac.uk/prospectus/graduate/structure/name/theoretical-physics/alpha/P/header_search/","Physics, Theoretical","MSc","0"},
		{"297","http://www.kcl.ac.uk/prospectus/graduate/structure/name/human-and-applied-physiology/alpha/P/header_search/","Physiology, Human & Applied","MSc","0"},
		{"298","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced--obr-neuromusculo-skeletal-cbr--physiotherapy/alpha/P/header_search/","Physiotherapy (Neuromusculo-skeletal)","MSc","0"},
		{"299","http://www.kcl.ac.uk/prospectus/graduate/structure/name/physiotherapy--obr-pre-registration-cbr-/alpha/P/header_search/","Physiotherapy (pre-registration)","MSc","0"},
		{"300","http://www.kcl.ac.uk/prospectus/graduate/structure/name/political-economy/alpha/P/header_search/","Political Economy","MA","0"},
		{"301","http://www.kcl.ac.uk/prospectus/graduate/structure/name/political-economy-of-emerging-markets/alpha/P/header_search/","Political Economy of Emerging Markets","MSc","0"},
		{"302","http://www.kcl.ac.uk/prospectus/graduate/structure/name/political-economy-middle-east/alpha/P/header_search/","Political Economy of the Middle East","MA","0"},
		{"303","http://www.kcl.ac.uk/prospectus/graduate/structure/name/politics-contemporary-history/alpha/P/header_search/","Politics & Contemporary History","MA","0"},
		{"304","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce/alpha/P/header_search/","Postgraduate Certificate in Education","PGCE","0"},
		{"305","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-biology/alpha/P/header_search/","Postgraduate Certificate in Education (Biology)","PGCE","0"},
		{"306","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-chemistry/alpha/P/header_search/","Postgraduate Certificate in Education (Chemistry)","PGCE","0"},
		{"307","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-computer-science-with-it/alpha/P/header_search/","Postgraduate Certificate in Education (Computer Science)","PGCE","0"},
		{"308","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-english/alpha/P/header_search/","Postgraduate Certificate in Education (English)","PGCE","0"},
		{"309","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-ict/alpha/P/header_search/","Postgraduate Certificate in Education (Information Technology and Computer Science)","PGCE","0"},
		{"310","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-latin-with-classics/alpha/P/header_search/","Postgraduate Certificate in Education (Latin with Classics)","PGCE","0"},
		{"311","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-maths/alpha/P/header_search/","Postgraduate Certificate in Education (Mathematics)","PGCE","0"},
		{"312","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-mfl/alpha/P/header_search/","Postgraduate Certificate in Education (Modern Foreign Languages)","PGCE","0"},
		{"313","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-maths-and-physics/alpha/P/header_search/","Postgraduate Certificate in Education (Physics with Mathematics)","PGCE","0"},
		{"314","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-physics/alpha/P/header_search/","Postgraduate Certificate in Education (Physics)","PGCE","0"},
		{"315","http://www.kcl.ac.uk/prospectus/graduate/structure/name/pgce-religious-education/alpha/P/header_search/","Postgraduate Certificate in Education (Religious Education)","PGCE","0"},
		{"316","http://www.kcl.ac.uk/prospectus/graduate/structure/name/school-direct-pgce/alpha/P/header_search/","Postgraduate Certificate in Education (School Direct)","PGCE","0"},
		{"317","http://www.kcl.ac.uk/prospectus/graduate/structure/name/postgraduate-certificate-in-education-school-direct-qts/alpha/P/header_search/","Postgraduate Certificate in Education (School Direct)","QTS only","0"},
		{"318","http://www.kcl.ac.uk/prospectus/graduate/structure/name/prosthodontics/alpha/P/header_search/","Prosthodontics","MClinDent","0"},
		{"319","http://www.kcl.ac.uk/prospectus/graduate/structure/name/fixed-and-removable-prosthodontics/alpha/P/header_search/","Prosthodontics, Fixed & Removable","MClinDent","0"},
		{"320","http://www.kcl.ac.uk/prospectus/graduate/structure/name/psychiatric-research/alpha/P/header_search/","Psychiatric Research","MSc","0"},
		{"321","http://www.kcl.ac.uk/prospectus/graduate/structure/name/war-and-psychiatry/alpha/P/header_search/","Psychiatry & War","MSc","0"},
		{"322","http://www.kcl.ac.uk/prospectus/graduate/structure/name/genes-environment-and-development/alpha/P/header_search/","Psychiatry:  Genes, Environment & Development","MSc","0"},
		{"323","http://www.kcl.ac.uk/prospectus/graduate/structure/name/social,-genetic-and-developmental-psychiatry/alpha/P/header_search/","Psychiatry: Social, Genetic & Developmental","MSc","0"},
		{"324","http://www.kcl.ac.uk/prospectus/graduate/structure/name/early-intervention-in-psychosis/alpha/P/header_search/","Psychosis, Early Interventions in","MSc","0"},
		{"325","http://www.kcl.ac.uk/prospectus/graduate/structure/name/public-health/alpha/P/header_search/","Public Health","MPH/MSc/MPH (Primary Care)","0"},
		{"326","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice-specialist-community/alpha/P/header_search/","Public Health Nursing","MSc/PG Dip","0"},
		{"327","http://www.kcl.ac.uk/prospectus/graduate/structure/name/dental-public-health/alpha/P/header_search/","Public Health, Dental","MSc","0"},
		{"328","http://www.kcl.ac.uk/prospectus/graduate/structure/name/public-policy/alpha/P/header_search/","Public Policy","MA","0"},
		{"329","http://www.kcl.ac.uk/prospectus/graduate/structure/name/public-policy-and-ageing/alpha/P/header_search/","Public Policy & Ageing","MA/PG Dip/PG Cert","0"},
		{"330","http://www.kcl.ac.uk/prospectus/graduate/structure/name/public-policy-and-management/alpha/P/header_search/","Public Policy and Management","MSc","0"},
		{"331","http://www.kcl.ac.uk/prospectus/graduate/structure/name/radiopharmaceutics-and-pet-radiochemistry/alpha/R/header_search/","Radiopharmaceutics & PET Radiochemistry","MSc","0"},
		{"332","http://www.kcl.ac.uk/prospectus/graduate/structure/name/regenerative-dentistry/alpha/R/header_search/","Regenerative Dentistry","MSc","0"},
		{"333","http://www.kcl.ac.uk/prospectus/graduate/structure/name/palliative-care/alpha/R/header_search/","Rehabilitation","MSc/PG Dip/PG Cert","0"},
		{"334","http://www.kcl.ac.uk/prospectus/graduate/structure/name/religion-in-contemporary-society/alpha/R/header_search/","Religion in Contemporary Society","MA","0"},
		{"335","http://www.kcl.ac.uk/prospectus/graduate/structure/name/theology-and-religious-studies/alpha/R/header_search/","Religious Studies, Theology","Grad Dip","0"},
		{"336","http://www.kcl.ac.uk/prospectus/graduate/structure/name/research-biobanking/alpha/R/header_search/","Research Biobanking","MSc","0"},
		{"337","http://www.kcl.ac.uk/prospectus/graduate/structure/name/research-methods-for-social-science-and-health/alpha/R/header_search/","Research Methods for Social Science & Health","MSc/PG Diploma/PG Certificate","0"},
		{"338","http://www.kcl.ac.uk/prospectus/graduate/structure/name/rheumatology/alpha/R/header_search/","Rheumatology","MSc","0"},
		{"339","http://www.kcl.ac.uk/prospectus/graduate/structure/name/risk-analysis/alpha/R/header_search/","Risk Analysis","MA/MSc","0"},
		{"340","http://www.kcl.ac.uk/prospectus/graduate/structure/name/robotics/alpha/R/header_search/","Robotics","MSc","0"},
		{"341","http://www.kcl.ac.uk/prospectus/graduate/structure/name/russia-in-global-systems/alpha/R/header_search/","Russia in Global Systems","MSc","0"},
		{"342","http://www.kcl.ac.uk/prospectus/graduate/structure/name/russian-policy-amp-society/alpha/R/header_search/","Russian Policy & Society","MSc","0"},
		{"343","http://www.kcl.ac.uk/prospectus/graduate/structure/name/school-direct-qts/alpha/S/header_search/","School Direct employment-based training","QTS","0"},
		{"344","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-practice-specialist-community/alpha/S/header_search/","Specialist Community Public Health Nursing/Health Visiting/School Nursing (Advanced Practice)","MSc/PG Dip","0"},
		{"345","http://www.kcl.ac.uk/prospectus/graduate/structure/name/science-and-security/alpha/S/header_search/","Science & Security","MA","0"},
		{"346","http://www.kcl.ac.uk/prospectus/graduate/structure/name/science-education/alpha/S/header_search/","Science Education","MA","0"},
		{"347","http://www.kcl.ac.uk/prospectus/graduate/structure/name/science-history/alpha/S/header_search/","Science, Technology & Medicine in History","MA","0"},
		{"348","http://www.kcl.ac.uk/prospectus/graduate/structure/name/conflict,-security-and-development/alpha/S/header_search/","Security, Conflict & Development","MA","0"},
		{"349","http://www.kcl.ac.uk/prospectus/graduate/structure/name/security-leadership/alpha/S/header_search/","Security, Leadership & Society","MSc","0"},
		{"350","http://www.kcl.ac.uk/prospectus/graduate/structure/name/conscious-sedation-for-dentistry/alpha/S/header_search/","Sedation for Dentistry","PG Dip","0"},
		{"351","http://www.kcl.ac.uk/prospectus/graduate/structure/name/shakespeare-studies/alpha/S/header_search/","Shakespeare Studies","MA","0"},
		{"352","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-studies-and-academic-english/alpha/S/header_search/","Social Science & Academic English","Grad Dip","0"},
		{"353","http://www.kcl.ac.uk/prospectus/graduate/structure/name/research-methods-for-social-science-and-health/alpha/S/header_search/","Social Science & Health (Research Methods)","MSc/PG Diploma/PG Certificate","0"},
		{"354","http://www.kcl.ac.uk/prospectus/graduate/structure/name/social,-genetic-and-developmental-psychiatry/alpha/S/header_search/","Social, Genetic & Developmental Psychiatry","MSc","0"},
		{"355","http://www.kcl.ac.uk/prospectus/graduate/structure/name/advanced-software-engineering/alpha/S/header_search/","Software Engineering (Advanced)","MSc","0"},
		{"356","http://www.kcl.ac.uk/prospectus/graduate/structure/name/south-asia-amp-global-security/alpha/S/header_search/","South Asia & Global Security","MA","0"},
		{"357","http://www.kcl.ac.uk/prospectus/graduate/structure/name/space-physiology-and-health/alpha/S/header_search/","Space Physiology & Health","MSc","0"},
		{"358","http://www.kcl.ac.uk/prospectus/graduate/structure/name/spanish-portuguese-and-latin-american-studies/alpha/S/header_search/","Spanish, Portuguese & Latin American Studies","MA","0"},
		{"359","http://www.kcl.ac.uk/prospectus/graduate/structure/name/special-care-dentistry/alpha/S/header_search/","Special Care Dentistry","MSc","0"},
		{"360","http://www.kcl.ac.uk/prospectus/graduate/structure/name/specialist-ultrasound-practice/alpha/S/header_search/","Specialist Ultrasound Practice","PG Cert","0"},
		{"361","http://www.kcl.ac.uk/prospectus/graduate/structure/name/sustainable-cities/alpha/S/header_search/","Sustainable Cities","MA/MSc","0"},
		{"362","http://www.kcl.ac.uk/prospectus/graduate/structure/name/systematic-theology/alpha/S/header_search/","Systematic Theology","MA","0"},
		{"363","http://www.kcl.ac.uk/prospectus/graduate/structure/name/tesol-ma/alpha/T/header_search/","Teaching English to Speakers of Other Languages (TESOL)","MA","0"},
		{"364","http://www.kcl.ac.uk/prospectus/graduate/structure/name/telecommunications-and-internet-technology/alpha/T/header_search/","Telecommunications & Internet Technology","MSc","0"},
		{"365","http://www.kcl.ac.uk/prospectus/graduate/structure/name/telecommunications-research/alpha/T/header_search/","Telecommunications Research","MSc","0"},
		{"366","http://www.kcl.ac.uk/prospectus/graduate/structure/name/palliative-care/alpha/T/header_search/","Terminal Care","MSc/PG Dip/PG Cert","0"},
		{"367","http://www.kcl.ac.uk/prospectus/graduate/structure/name/terrorism,-security-and-society/alpha/T/header_search/","Terrorism, Security & Society","MA","0"},
		{"368","http://www.kcl.ac.uk/prospectus/graduate/structure/name/classical-world-and-its-reception-/alpha/T/header_search/","The Classical World & its Reception","MA","0"},
		{"369","http://www.kcl.ac.uk/prospectus/graduate/structure/name/theatre-and-performance-studies/alpha/T/header_search/","Theatre & Performance Studies","MA","0"},
		{"370","http://www.kcl.ac.uk/prospectus/graduate/structure/name/theology-and-religious-studies/alpha/T/header_search/","Theology & Religious Studies","Grad Dip","0"},
		{"371","http://www.kcl.ac.uk/prospectus/graduate/structure/name/systematic-theology/alpha/T/header_search/","Theology, Systematic","MA","0"},
		{"372","http://www.kcl.ac.uk/prospectus/graduate/structure/name/theoretical-physics/alpha/T/header_search/","Theoretical Physics","MSc","0"},
		{"373","http://www.kcl.ac.uk/prospectus/graduate/structure/name/tourism,-environment-and-development/alpha/T/header_search/","Tourism, Environment & Development","MA/MSc","0"},
		{"374","http://www.kcl.ac.uk/prospectus/graduate/structure/name/translational-cancer-medicine/alpha/T/header_search/","Translational Cancer Medicine","MRes","0"},
		{"375","http://www.kcl.ac.uk/prospectus/graduate/structure/name/translational-medicine/alpha/T/header_search/","Translational Medicine","MSc/PG Dip/PG Cert","0"},
		{"376","http://www.kcl.ac.uk/prospectus/graduate/structure/name/transnational-law/alpha/T/header_search/","Transnational Law","LLM","0"},
		{"377","http://www.kcl.ac.uk/prospectus/graduate/structure/name/uk-eu-and-us-copyright-law/alpha/U/header_search/","UK, EU & US Copyright Law","MA, PG Dip","0"},
		{"378","http://www.kcl.ac.uk/prospectus/graduate/structure/name/medical-ultrasound/alpha/U/header_search/","Ultrasound, Medical","MSc/PG Dip/PG Cert","0"},
		{"379","http://www.kcl.ac.uk/prospectus/graduate/structure/name/specialist-ultrasound-practice/alpha/U/header_search/","Ultrasound, Specialist Practice","PG Cert","0"},
		{"380","http://www.kcl.ac.uk/prospectus/graduate/structure/name/vascular-ultrasound/alpha/U/header_search/","Ultrasound, Vascular","MSc/PG Dip/PG Cert","0"},
		{"381","http://www.kcl.ac.uk/prospectus/graduate/structure/name/vascular-ultrasound/alpha/V/header_search/","Vascular Ultrasound","MSc/PG Dip/PG Cert","0"},
		{"382","http://www.kcl.ac.uk/prospectus/graduate/structure/name/war-and-psychiatry/alpha/W/header_search/","War & Psychiatry","MSc","0"},
		{"383","http://www.kcl.ac.uk/prospectus/graduate/structure/name/war-in-the-modern-world--obr-by-e-learning-cbr-/alpha/W/header_search/","War in the Modern World","MA/PG Dip","0"},
		{"384","http://www.kcl.ac.uk/prospectus/graduate/structure/name/war-studies/alpha/W/header_search/","War Studies","MA","0"},
		{"385","http://www.kcl.ac.uk/prospectus/graduate/structure/name/international-studies-and-academic-english/alpha/W/header_search/","War Studies & Academic English","Grad Dip","0"},
		{"386","http://www.kcl.ac.uk/prospectus/graduate/structure/name/history-of-war/alpha/W/header_search/","War, History of","MA","0"},
		{"387","http://www.kcl.ac.uk/prospectus/graduate/structure/name/aquatic-resource-management/alpha/W/header_search/","Water Resource Management","MSc","0"},
		{"388","http://www.kcl.ac.uk/prospectus/graduate/structure/name/water-science-and-governance/alpha/W/header_search/","Water: Science & Governance","MSc","0"},
		{"389","http://www.kcl.ac.uk/prospectus/graduate/structure/name/web-intelligence/alpha/W/header_search/","Web Intelligence","MSc","0"},
		{"390","http://www.kcl.ac.uk/prospectus/graduate/structure/name/world-history-and-cultures/alpha/W/header_search/","World History & Cultures","MA","0"}
	};
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

}
