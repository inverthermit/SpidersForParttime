package southampton;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.htmlparser.visitors.HtmlPage;

public class getURLPostT {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		//getUnECSInfo();
		// TODO Auto-generated method stub
		/*String[] url=PostTDepData;
		///undergraduate/courses/
		for(int i=0;i<url.length;i++)
		{

				
				while(true)
				{
					try{
						HashMap<String,String> result=new HashMap<String,String>();
						RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();  
						CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
						
						HttpGet httpGet = new HttpGet(url[i]); 
						HttpResponse response = httpclient.execute(httpGet);  
						HttpEntity entity = response.getEntity();
						
						String htmls=null;
						if (entity != null) { 
						    htmls=EntityUtils.toString(entity).replace("\t", " ");
						    //System.out.println(htmls);
						    if(htmls.trim().equals(""))
						    {
						    	throw new Exception("no reply");
						    }
						     
						}
						else
						{
							 throw new Exception("no reply");
						}
						
						Parser	parser=Parser.createParser(htmls, "utf-8");
						
						    	AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
						                   new HasAttributeFilter("href"));
						   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
						   	 
						   	    for(int j=0;j<nodes4.size();j++)
						   	    {
						   	    	LinkTag link=(LinkTag)nodes4.elementAt(j);
						   	    	String get1=link.getAttribute("href");
						   	    	if(get1.contains("postgraduatee/research"))
						   	    	{
						   	    		System.out.println("{\""+count+"\",\""+get1+"\",\""+HTMLFilter(html2Str(link.toHtml()))+"\",\"0\"},");
						   	    	    count++;
						   	    	}
						   	    	if(get1.contains("/postgraduate/taught_courses/")&&(!get1.contains("google"))&&(!get1.contains("twitter"))&&(!get1.contains("facebook")))
						   	    	{
						   	    		if(get1.startsWith("/"))
						   	    	System.out.println("\"http://www.southampton.ac.uk"+get1+"\",");
						   	    		else
						   	    			System.out.println("\""+get1+"\",");
						   	    	}
						   	    		
						   	    	
						   	    }
						   	    
					   	    
						
					   
						httpclient.close();
						break;
					}
					catch(Exception ee)
					{
						//System.out.println("Retrying"+" "+url[i]);
						//ee.printStackTrace();
					}
				}
			
		}*/
	    
	   /* Scanner in=new Scanner(System.in);
	    while(true)
	    {
	    	String a=in.nextLine();
	    	if(a.startsWith("/"))
	    	{
	    		System.out.println("\"http://www.southampton.ac.uk"+a+"\",");
	    	}
	    	else
	    		System.out.println("\""+a+"\",");
	    	if(!a.contains("apply.page"))
	    	{
	    		System.out.println(a);
	    	}
	    }*/
	    /*
		for(int i=0;i<PostData1.length;i++)
		{
			System.out.println("{\""+(i+1)+"\",\""+PostData1[i]+"\",\"0\"},");
		}
		*/
		getUnECSInfo();

	}
	public static void getLinks() throws Exception
	{
FileInputStream fis=new FileInputStream(new File("./southamptonPostT.html"));//鏂板缓涓�涓狥ileInputStream瀵硅薄
        
        byte[] b=new byte[fis.available()];//鏂板缓涓�涓瓧鑺傛暟缁�
        fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
       fis.close();
        String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
       //System.out.println(htmls);
        int count=1;
	    
	    	Parser parser=Parser.createParser(htmls, "utf-8");
	    	AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
	                   new HasAttributeFilter("href"));
	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	   	 
	   	    for(int j=0;j<nodes4.size();j++)
	   	    {
	   	    	LinkTag link=(LinkTag)nodes4.elementAt(j);
	   	    	String get1=link.getAttribute("href");
	   	    	//System.out.println("{\""+count+"\",\""+get1+"\",\""+HTMLFilter(html2Str(link.toHtml()))+"\",\"0\"},");
	   	    	count++;
	   	        //System.out.println("{\""+get1+"\",\""+HTMLFilter(html2Str(link.toHtml()))+"\",\"0\"},");
	   	    	System.out.println(get1);
	   	    	
	   	    }
	}
	public static void getUnECSInfo() throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("./southamptonPostECS.html"));//鏂板缓涓�涓狥ileInputStream瀵硅薄
        
        byte[] b=new byte[fis.available()];//鏂板缓涓�涓瓧鑺傛暟缁�
        fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
       fis.close();
        String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
       //System.out.println(htmls);
        int count=1;
	    
	    	Parser parser=Parser.createParser(htmls, "utf-8");
	    	AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
	                   new HasAttributeFilter("href"));
	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	   	 
	   	    for(int j=0;j<nodes4.size();j++)
	   	    {
	   	    	LinkTag link=(LinkTag)nodes4.elementAt(j);
	   	    	String get1=link.getAttribute("href");
	   	    	//System.out.println("{\""+count+"\",\""+get1+"\",\""+HTMLFilter(html2Str(link.toHtml()))+"\",\"0\"},");
	   	    	count++;
	   	        System.out.println("{\""+get1+"\",\""+HTMLFilter(html2Str(link.toHtml()))+"\",\"0\"},");
	   	    	
	   	    }
	   	    
   	    
	}
	
	public static void getDepInfo() throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("./southamptonUn.html"));//鏂板缓涓�涓狥ileInputStream瀵硅薄
        
        byte[] b=new byte[fis.available()];//鏂板缓涓�涓瓧鑺傛暟缁�
        fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
       fis.close();
        String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
       //System.out.println(htmls);
        int count=1;
	    Parser	parser=Parser.createParser(htmls, "utf-8");
	   /* AndFilter ListFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","advanced-search-name-award"));*/
	    TagNameFilter ListFilter=new TagNameFilter("li");
	    NodeList nodes0=parser.extractAllNodesThatMatch(ListFilter);
	    System.out.println(nodes0.size());
	    for(int i=0;i<nodes0.size();i++)
   	    {
	    	parser=Parser.createParser(nodes0.elementAt(i).toHtml(), "utf-8");
	    	AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
	                   new HasAttributeFilter("href"));
	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	   	 
	   	    for(int j=0;j<nodes4.size();j++)
	   	    {
	   	    	LinkTag link=(LinkTag)nodes4.elementAt(j);
	   	    	String get1=link.getAttribute("href");
	   	    	/*if(get1.contains("postgraduatee/research"))
	   	    	{
	   	    		System.out.println("{\""+count+"\",\""+get1+"\",\""+HTMLFilter(html2Str(link.toHtml()))+"\",\"0\"},");
	   	    	    count++;
	   	    	}*/
	   	    	if(get1.startsWith("/sites/"))
	   	    	System.out.println("\""+get1+"\",");
	   	    	
	   	    	break;
	   	    }
	   	    
   	    }
	}
	
	public static String[] PostTDepData={
		"http://www.southampton.ac.uk/ageing/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/biosci/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/chemistry/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/demography/postgraduate/taught_courses/msc_global_health.page",
		"http://www.southampton.ac.uk/economics/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/economics/postgraduate/taught_courses/msc-finance-and-econometrics.page",
		"http://www.southampton.ac.uk/education/postgraduate/taught_courses/courses.page",
		"http://www.southampton.ac.uk/engineering/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/audiology.page",
		"http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/emp.page",
		"http://www.southampton.ac.uk/geography/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_midwifery_studies.page",
		"http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_physiotherapy.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/research_degrees/degrees/pre_sessional.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/archaeology.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/english.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/film_studies.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/history.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/modern_languages.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/music.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/philosophy.page",
		"http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/pre_masters.page",
		"http://www.southampton.ac.uk/law/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/maths/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/medicine/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc_allergy.page",
		"http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc_public_health_pathways.page",
		"http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc-diabetes-best-practice.page",
		"http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc-genomic-medicine.page",
		"http://www.southampton.ac.uk/politics/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/psychology/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/sociology/postgraduate/taught_courses/pgdip_msc_criminology.page",
		"http://www.southampton.ac.uk/soes/postgraduate/taught_courses.page",
		"http://www.southampton.ac.uk/wsa/postgraduate/index.page",
		"http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/w100_fine_art.page",
		"http://www.ecs.soton.ac.uk/postgraduatetaught/postgraduate_taught_programmes",
		"http://www.orc.soton.ac.uk/mscof.html",
		"http://www.orc.soton.ac.uk/mscprogramme.html",
		"https://www.sbs.ac.uk/postgraduate"
	};
	
	public static String[][] PostData1={
		//{"1","http://uos-web00114-si.soton.ac.uk:1776/geography/postgraduate/taught_courses/msc_gem.page?","0"},
		{"2","http://www.soton.ac.uk/geography/postgraduate/taught_courses/msc_gis_online.page?","0"},
		{"3","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/mSc_gerontology.page?","0"},
		{"4","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/msc_gerontology_dl.page?","0"},
		{"5","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/msc_gerontology_research.page?","0"},
		{"6","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/msc_global_ageing_and_policy_dl.page?","0"},
		{"7","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/pg_cert_gerontology.page?","0"},
		{"8","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/pg_cert_gerontology_dl.page?","0"},
		{"9","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/pg_cert_global_ageing_and_policy_dl.page?","0"},
		{"10","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/pg_dip_global_ageing_and_policy_dl.page?","0"},
		{"11","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/pg_diploma_gerontology_dl.page?","0"},
		{"12","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/short_courses_in_gerontology.page?","0"},
		{"13","http://www.southampton.ac.uk/ageing/postgraduate/taught_courses/short_courses_in_gerontology_dl.page?","0"},
		{"14","http://www.southampton.ac.uk/archaeology/postgraduate/taught_courses/v400_ma_msc_maritime_archaeology.page?","0"},
		{"15","http://www.southampton.ac.uk/archaeology/postgraduate/taught_courses/v400_ma_osteoarchaeology.page?","0"},
		{"16","http://www.southampton.ac.uk/archaeology/postgraduate/taught_courses/v400_ma_palaeolithic_archaeology_and_human_origins.page?","0"},
		{"17","http://www.southampton.ac.uk/archaeology/postgraduate/taught_courses/v400_ma_social_archaeology.page?","0"},
		{"18","http://www.southampton.ac.uk/archaeology/postgraduate/taught_courses/v400_msc_archaeological_computing_gis_and_survey.page?","0"},
		{"19","http://www.southampton.ac.uk/archaeology/postgraduate/taught_courses/v400_msc_archaeological_computing_virtual_pasts.page?","0"},
		{"20","http://www.southampton.ac.uk/archaeology/postgraduate/taught_courses/v404_msc_business_and_heritage_management.page?","0"},
		{"21","http://www.southampton.ac.uk/biosci/postgraduate/taught_courses/mres-advanced-biological-sciences-degree.page","0"},
		{"22","http://www.southampton.ac.uk/biosci/postgraduate/taught_courses/wildlife_conservation.page","0"},
		{"23","http://www.southampton.ac.uk/biosci/postgraduate/taught_courses/wildlife_conservation.page?","0"},
		{"24","http://www.southampton.ac.uk/chemistry/postgraduate/taught_courses/electrochemistry.page","0"},
		{"25","http://www.southampton.ac.uk/chemistry/postgraduate/taught_courses/instrumental_analytical_chemistry.page","0"},
		{"26","http://www.southampton.ac.uk/chemistry/postgraduate/taught_courses/msc_research.page","0"},
		{"27","http://www.southampton.ac.uk/chemistry/postgraduate/taught_courses/msc-chemistry.page","0"},
		{"28","http://www.southampton.ac.uk/economics/postgraduate/taught_courses/msc_economics.page?","0"},
		{"29","http://www.southampton.ac.uk/economics/postgraduate/taught_courses/msc_economics_and_econometrics.page?","0"},
		{"30","http://www.southampton.ac.uk/economics/postgraduate/taught_courses/msc_finance_and_economics.page?","0"},
		{"31","http://www.southampton.ac.uk/economics/postgraduate/taught_courses/msc-finance-and-econometrics.page?","0"},
		{"32","http://www.southampton.ac.uk/education/postgraduate/taught_courses/courses.page?","0"},
		{"33","http://www.southampton.ac.uk/education/postgraduate/taught_courses/ma_ed_dissertation_through_flexible_study.page?","0"},
		{"34","http://www.southampton.ac.uk/education/postgraduate/taught_courses/msc_education.page?","0"},
		{"35","http://www.southampton.ac.uk/education/postgraduate/taught_courses/msc_education_management_and_leadership.page?","0"},
		{"36","http://www.southampton.ac.uk/education/postgraduate/taught_courses/msc_education_practice_and_innovation.page?","0"},
		{"37","http://www.southampton.ac.uk/education/postgraduate/taught_courses/msc_education_specific_learning_differences.page?","0"},
		{"38","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/audiology.page","0"},
		{"39","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/emp.page","0"},
		{"40","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/emp/msc_biodiversity_and_conservation.page?","0"},
		{"41","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/emp/msc_environmental_monitoring_and_assessment.page","0"},
		{"42","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/emp/msc_environmental_pollution_control.page","0"},
		{"43","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/emp/msc_integrated_environmental_studies.page","0"},
		{"44","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/emp/msc_water_resources_management.page","0"},
		{"45","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/engineering.page","0"},
		{"46","http://www.southampton.ac.uk/english/postgraduate/taught_courses/q200_ma_20th_and_21st_century_literature.page?","0"},
		{"47","http://www.southampton.ac.uk/english/postgraduate/taught_courses/q300_ma_english_literary_studies.page?","0"},
		{"48","http://www.southampton.ac.uk/english/postgraduate/taught_courses/v100_ma_eighteenth_century_studies.page?","0"},
		{"49","http://www.southampton.ac.uk/english/postgraduate/taught_courses/w800_ma_creative_writing.page?","0"},
		{"50","http://www.southampton.ac.uk/film/postgraduate/taught_courses/p300_ma_film.page?","0"},
		{"51","http://www.southampton.ac.uk/film/postgraduate/taught_courses/p300_ma_film_and_cultural_management.page?","0"},
		{"52","http://www.southampton.ac.uk/geography/postgraduate/taught_courses/msc_applied_gis_and_remote_sensing.page?","0"},
		{"53","http://www.southampton.ac.uk/geography/postgraduate/taught_courses/msc_gem.page?","0"},
		{"54","http://www.southampton.ac.uk/geography/postgraduate/taught_courses/msc_gis_online.page?","0"},
		{"55","http://www.southampton.ac.uk/geography/postgraduate/taught_courses/msc_sustainability.page","0"},
		{"56","http://www.southampton.ac.uk/geography/postgraduate/taught_courses/msc_sustainability.page?","0"},
		{"57","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/masters-with-integrated-prep-study.page?","0"},
		{"58","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/mres_clinical_research.page?","0"},
		{"59","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_children.page","0"},
		{"60","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_children.page?","0"},
		{"61","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_crit_care.page?","0"},
		{"62","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_midwifery.page?","0"},
		{"63","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_neonatal.page?","0"},
		{"64","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_practitioner.page?","0"},
		{"65","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_specialist.page?","0"},
		{"66","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_adv_clin_practice_standard.page?","0"},
		{"67","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_clinical_leadership_cpeol_care.page","0"},
		{"68","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_clinical_leadership_cpeol_care.page?","0"},
		{"69","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_health_sciences.page","0"},
		{"70","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_health_sciences.page?","0"},
		{"71","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_leadership_management.page?","0"},
		{"72","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_midwifery_studies.page?","0"},
		{"73","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_physiotherapy.page?","0"},
		{"74","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc-complex-care-in-older-people.page?","0"},
		{"75","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc-nursing-studies.page?","0"},
		{"76","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/pgdip_adult_nursing.page?","0"},
		{"77","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/pgdip_child_nursing.page?","0"},
		{"78","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/pgdip_mental_nursing.page?","0"},
		{"79","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/pgdip_specialist_community_public_health_nursing.page?","0"},
		{"80","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/postgraduate-cert-low-intensity-cbt.page?","0"},
		{"81","http://www.southampton.ac.uk/history/postgraduate/taught_courses/v300_ma_jewish_history_and_culture.page?","0"},
		{"82","http://www.southampton.ac.uk/history/postgraduate/taught_courses/v900_ma_history.page?","0"},
		{"83","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400_ma_msc_maritime_archaeology.page?","0"},
		{"84","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400_ma_osteoarchaeology.page?","0"},
		{"85","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400_ma_palaeolithic_archaeology_and_human_origins.page?","0"},
		{"86","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400_ma_rome_and_the_mediterranean.page?","0"},
		{"87","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400_ma_social_archaeology.page?","0"},
		{"88","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400_msc_archaeological_computing_gis_and_survey.page?","0"},
		{"89","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400_msc_archaeological_computing_virtual_pasts.page?","0"},
		{"90","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v400-ma-ceramic-and-lithic-analysis-for-archaeologists.page?","0"},
		{"91","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/archaeology/v404_msc_business_and_heritage_management.page?","0"},
		{"92","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/english/q200_ma_20th_and_21st_century_literature.page?","0"},
		{"93","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/english/q300_ma_english_literary_studies.page?","0"},
		{"94","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/english/v100_ma_eighteenth_century_studies.page?","0"},
		{"95","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/english/v300_ma_jewish_history_and_culture.page?","0"},
		{"96","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/english/v300_ma_medieval_and_renaissance_culture.page?","0"},
		{"97","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/english/w800_ma_creative_writing.page?","0"},
		{"98","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/film_studies/p300_ma_film.page?","0"},
		{"99","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/film_studies/p300_ma_film_and_cultural_management.page?","0"},
		{"100","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/history/v100_ma_eighteenth_century_studies.page?","0"},
		{"101","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/history/v300_ma_jewish_history_and_culture.page?","0"},
		{"102","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/history/v300_ma_medieval_and_renaissance_culture.page?","0"},
		{"103","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/history/v900_ma_history.page?","0"},
		{"104","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/q100_ma_applied_linguistics_for_language_teaching.page?","0"},
		{"105","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/q100_ma_applied_linguistics_research_methodology.page?","0"},
		{"106","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/q100_ma_language_aquisition_research.page?","0"},
		{"107","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/r900_ma_elt_tesol_studies.page?","0"},
		{"108","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/r900_ma_english_language_teaching.page?","0"},
		{"109","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/r900_ma_english_language_teaching_online.page?","0"},
		{"110","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/r900_ma_global_englishes.page?","0"},
		{"111","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/modern_languages/r900_ma_transnational_studies.page?","0"},
		{"112","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/music/v100_ma_eighteenth_century_studies.page?","0"},
		{"113","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/music/v300_ma_medieval_and_renaissance_culture.page?","0"},
		{"114","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/music/w300_mmus_music.page?","0"},
		{"115","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses.page?","0"},
		{"116","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/pre_masters.page?","0"},
		{"117","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/pre_sessional/general_postgraduate.page?","0"},
		{"118","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/our_courses/pre_sessional/wsa.page","0"},
		{"119","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/philosophy/v500_ma_aesthetics.page?","0"},
		{"120","http://www.southampton.ac.uk/humanities/postgraduate/taught_courses/taught_courses/philosophy/v500_ma_philosophy.page?","0"},
		{"121","http://www.southampton.ac.uk/law/postgraduate/taught_courses/courses/LLM_Corporate_and_commercial_law.page?","0"},
		{"122","http://www.southampton.ac.uk/law/postgraduate/taught_courses/courses/LLM_general.page?","0"},
		{"123","http://www.southampton.ac.uk/law/postgraduate/taught_courses/courses/LLM_information_technology_commerce.page?","0"},
		{"124","http://www.southampton.ac.uk/law/postgraduate/taught_courses/courses/llm_insurance_law.page?","0"},
		{"125","http://www.southampton.ac.uk/law/postgraduate/taught_courses/courses/LLM_international_business_law.page?","0"},
		{"126","http://www.southampton.ac.uk/law/postgraduate/taught_courses/courses/LLM_international_law.page?","0"},
		{"127","http://www.southampton.ac.uk/law/postgraduate/taught_courses/courses/LLM_maritime_law.page?","0"},
		{"128","http://www.southampton.ac.uk/maths/postgraduate/taught_courses/diploma_msc_in_statistics_with_applications_in_medicine.page?","0"},
		{"129","http://www.southampton.ac.uk/maths/postgraduate/taught_courses/msc_diploma_in_operational_research.page?","0"},
		{"130","http://www.southampton.ac.uk/maths/postgraduate/taught_courses/msc_diploma_in_operational_research_and_finance.page?","0"},
		{"131","http://www.southampton.ac.uk/maths/postgraduate/taught_courses/msc_in_statistics.page?","0"},
		{"132","http://www.southampton.ac.uk/maths/postgraduate/taught_courses/msc_pgdip_actuarial_science.page?","0"},
		{"133","http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc_allergy.page","0"},
		{"134","http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc_public_health_pathways.page","0"},
		{"135","http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc_public_health_pathways.page?","0"},
		{"136","http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc-diabetes-best-practice.page","0"},
		{"137","http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc-diabetes-best-practice.page#learning","0"},
		{"138","http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc-genomic-medicine.page","0"},
		{"139","http://www.southampton.ac.uk/ml/postgraduate/taught_courses/modern_languages/q100_ma_applied_linguistics_for_language_teaching.page","0"},
		{"140","http://www.southampton.ac.uk/ml/postgraduate/taught_courses/modern_languages/q100_ma_applied_linguistics_research_methodology.page","0"},
		{"141","http://www.southampton.ac.uk/ml/postgraduate/taught_courses/modern_languages/q100_ma_language_aquisition_research.page","0"},
		{"142","http://www.southampton.ac.uk/ml/postgraduate/taught_courses/modern_languages/r900_ma_english_language_teaching.page","0"},
		{"143","http://www.southampton.ac.uk/ml/postgraduate/taught_courses/modern_languages/r900_ma_transnational_studies.page","0"},
		{"144","http://www.southampton.ac.uk/music/postgraduate/taught_courses/w300_mmus_music.page?","0"},
		{"145","http://www.southampton.ac.uk/oes/postgraduate/taught_courses/mres_in_vertebrate_palaeontology.page?","0"},
		{"146","http://www.southampton.ac.uk/oes/postgraduate/taught_courses/mres_marine_geology_and_geophysics.page?","0"},
		{"147","http://www.southampton.ac.uk/oes/postgraduate/taught_courses/mres_ocean_science.page?","0"},
		{"148","http://www.southampton.ac.uk/oes/postgraduate/taught_courses/msc_engineering_in_the_coastal_environment.page?","0"},
		{"149","http://www.southampton.ac.uk/oes/postgraduate/taught_courses/msc_marine_environment_and_resources.page?","0"},
		{"150","http://www.southampton.ac.uk/oes/postgraduate/taught_courses/msc_oceanography.page?","0"},
		{"151","http://www.southampton.ac.uk/philosophy/postgraduate/taught_courses/v500_ma_aesthetics.page?","0"},
		{"152","http://www.southampton.ac.uk/philosophy/postgraduate/taught_courses/v500_ma_philosophy.page?","0"},
		{"153","http://www.southampton.ac.uk/politics/postgraduate/taught_courses/msc_global_politics.page?","0"},
		{"154","http://www.southampton.ac.uk/politics/postgraduate/taught_courses/msc_global_politics_research.page?","0"},
		{"155","http://www.southampton.ac.uk/politics/postgraduate/taught_courses/msc_governance_and_policy.page?","0"},
		{"156","http://www.southampton.ac.uk/politics/postgraduate/taught_courses/msc_governance_and_policy_research.page?","0"},
		{"157","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/msc_foundations_of_clinical_psychology.page?","0"},
		{"158","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/msc_health_psychology.page?","0"},
		{"159","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/msc_research_methods_in_psychology.page?","0"},
		{"160","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/postgraduate_certificate_in_cbt_advanced_level_practice.page?","0"},
		{"161","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/postgraduate_certificate_in_cbt_mental_health.page?","0"},
		{"162","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/postgraduate_certificate_in_cbt_theory_only.page?","0"},
		{"163","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/postgraduate_diploma_in_cbt.page?","0"},
		{"164","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/postgraduate_diploma_in_cbt_for_anxiety_and_depression.page?","0"},
		{"165","http://www.southampton.ac.uk/psychology/postgraduate/taught_courses/postgraduate-cert-low-intensity-cbt.page?","0"},
		{"166","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/audiology/hearing_aid_aptitude_distance_learning.page","0"},
		{"167","http://www.southampton.ac.uk/engineering/postgraduate/taught_courses/audiology/msc_audiology.page","0"},
		{"168","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/mres_clinical_research.page","0"},
		{"169","http://www.southampton.ac.uk/healthsciences/postgraduate/taught_courses/msc_leadership_management.page","0"},
		{"170","http://www.southampton.ac.uk/medicine/postgraduate/taught_courses/msc-genomic-medicine.page","0"},
		{"171","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/MA_advertising_design_management.page","0"},
		{"172","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_communication_design.page","0"},
		{"173","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_design_management.page","0"},
		{"174","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_fashion_design.page","0"},
		{"175","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_fashion_management.page","0"},
		{"176","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_fashion_marketing_and_branding.page","0"},
		{"177","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_games_design_and_art.page","0"},
		{"178","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_global_media_management.page","0"},
		{"179","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_luxury_brand_management.page","0"},
		{"180","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_textile_design.page","0"},
		{"181","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/w100_fine_art.page","0"},
		{"182","http://www.southampton.ac.uk/socsci/postgraduate/taught_courses/economics/msc_economics_and_econometrics.page","0"},
		{"183","http://www.southampton.ac.uk/socsci/postgraduate/taught_courses/economics/msc_finance_and_economics.page","0"},
		{"184","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/MA_advertising_design_management.page","0"},
		{"185","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_contemporary_curation.page","0"},
		{"186","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_design_management.page","0"},
		{"187","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_fashion_management.page","0"},
		{"188","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_fashion_marketing_and_branding.page","0"},
		{"189","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_global_media_management.page","0"},
		{"190","http://www.southampton.ac.uk/wsa/postgraduate/taught_courses/ma_luxury_brand_management.page","0"}
	};
	
	public static String[] PostData2={
		"http://www.ecs.soton.ac.uk/postgraduatetaught/postgraduate_taught_programmes",
		"http://www.orc.soton.ac.uk/mscof.html",
		"http://www.orc.soton.ac.uk/mscprogramme.html",
		"https://www.sbs.ac.uk/postgraduate"
	};
	
	public static String[][] PostDataECS={
		{"1","http://www.ecs.soton.ac.uk/programmes/european-masters-embedded-computing-systems-emecs","European Masters in Embedded Computing Systems (EMECS)","0"},
		{"2","http://www.ecs.soton.ac.uk/programmes/msc-artificial-intelligence","MSc Artificial Intelligence","0"},
		{"3","http://www.ecs.soton.ac.uk/programmes/msc-biodevices","MSc Biodevices","0"},
		{"4","http://www.ecs.soton.ac.uk/programmes/msc_computer_science","MSc Computer Science","0"},
		{"5","http://www.ecs.soton.ac.uk/programmes/msc-cyber-security","MSc Cyber Security","0"},
		{"6","http://www.ecs.soton.ac.uk/programmes/msc-data-science","MSc Data Science","0"},
		{"7","http://www.ecs.soton.ac.uk/programmes/msc-embedded-systems","MSc Embedded Systems","0"},
		{"8","http://www.ecs.soton.ac.uk/programmes/msc-energy-and-sustainability-electrical-power-engineering","MSc Energy and Sustainability with Electrical Power Engineering","0"},
		{"9","http://www.ecs.soton.ac.uk/programmes/msc-microelectromechanical-systems-mems","MSc MicroElectroMechanical Systems (MEMS)","0"},
		{"10","http://www.ecs.soton.ac.uk/programmes/msc-microelectronics-systems-design","MSc Microelectronics Systems Design","0"},
		{"11","http://www.ecs.soton.ac.uk/programmes/msc-nanoelectronics-and-nanotechnology","MSc Nanoelectronics and Nanotechnology","0"},
		{"12","http://www.ecs.soton.ac.uk/programmes/msc-software-engineering","MSc Software Engineering","0"},
		{"13","http://www.ecs.soton.ac.uk/programmes/msc-system-chip","MSc System on Chip","0"},
		{"14","http://www.ecs.soton.ac.uk/programmes/msc-systems-control-and-signal-processing","MSc Systems, Control and Signal Processing","0"},
		{"15","http://www.ecs.soton.ac.uk/programmes/msc-web-science","MSc Web Science","0"},
		{"16","http://www.ecs.soton.ac.uk/programmes/msc-web-technology","MSc Web Technology","0"},
		{"17","http://www.ecs.soton.ac.uk/programmes/msc-wireless-communications","MSc Wireless Communications","0"}
	};
	
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
	    input = input.trim().replaceAll("&....;", "");
	    input = input.trim().replaceAll("&.....;", "");
	    return input;
	}

}
