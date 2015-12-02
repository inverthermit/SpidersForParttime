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

public class getURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*String[] url=UnDepData;
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
						   	    	if(get1.contains("/undergraduate/courses/"))
						   	    	System.out.println("\""+get1+"\",");
						   	    	
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
	    
	    Scanner in=new Scanner(System.in);
	    while(true)
	    {
	    	String a=in.nextLine();
	    	if(a.startsWith("/"))
	    	{
	    		System.out.println("\"http://www.southampton.ac.uk"+a+"\",");
	    	}
	    	else
	    		System.out.println("\""+a+"\",");
	    }
	    

	}
	public static String[] UnDepData2={

		"http://www.ecs.soton.ac.uk/undergraduate/computer-science-and-software-engineering",//special
		"http://www.ecs.soton.ac.uk/undergraduate/electrical-engineering",
		"http://www.ecs.soton.ac.uk/undergraduate/electrical-and-electronic-engineering",
		"http://www.ecs.soton.ac.uk/undergraduate/electromechanical-engineering",
		"http://www.ecs.soton.ac.uk/undergraduate/electronic-engineering",
		"http://www.ecs.soton.ac.uk/undergraduate/information-technology-in-organisations",
		"http://www.ecs.soton.ac.uk/undergraduate/web-science",
		
		"https://www.sbs.ac.uk/undergraduate",
		"http://www.phys.soton.ac.uk/undergraduate/find_a_programme",
		
	};
	public static String[] UnDepData={
		"http://www.southampton.ac.uk/archaeology/undergraduate/courses.page",
		"http://www.southampton.ac.uk/archaeology/undergraduate/courses/v402_ba_archaeology_and_anthropology.page",
		"http://www.southampton.ac.uk/biosci/undergraduate/courses.page",
		"http://www.southampton.ac.uk/biosci/undergraduate/courses/b210_bsc_pharmacology.page",
		"http://www.southampton.ac.uk/biosci/undergraduate/courses/c300_bsc_zoology.page",
		"http://www.southampton.ac.uk/chemistry/undergraduate/courses.page",
		"http://www.southampton.ac.uk/demography/undergraduate/courses/l701_bsc_population_and_geography.page",
		"http://www.southampton.ac.uk/economics/undergraduate/courses.page",
		"http://www.southampton.ac.uk/education/undergraduate/courses.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/acoustical_engineering_list.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/aerospace_list.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/audiology_list.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/civil_engineering_list.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/environmental_sciences_list.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/foundation_year/engineering_physics_geophysics_foundation_year.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/foundation_year/science_foundation_year.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/maritime_engineering_list.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/mechanical_engineering_list.page",
		"http://www.southampton.ac.uk/english/undergraduate/courses.page",
		"http://www.southampton.ac.uk/film/undergraduate/courses.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_healthcare_management_policy_and_research.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_healthcare_science_cardio.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_midwifery.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_occupational_therapy.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_physiotherapy.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_podiatry.page",
		"http://www.southampton.ac.uk/history/undergraduate/courses.page",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/lmvo_foundation_year_law_arts_management_and_the_social_sciences_page.page",
		"http://www.southampton.ac.uk/law/undergraduate/courses.page",
		"http://www.southampton.ac.uk/malaysia/foundation_programme/index.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/electrical_and_electronical_engineering.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/mechanical_engineering.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_aeronautics_and_astronautics.page",
		"http://www.southampton.ac.uk/maths/undergraduate/courses.page",
		"http://www.southampton.ac.uk/medicine/undergraduate/courses.page",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies.page",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies.page",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies.page",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies.page",
		"http://www.southampton.ac.uk/music/undergraduate/courses.page",
		"http://www.southampton.ac.uk/natsci/find_course/msci_natural_sciences.page",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses.page",
		"http://www.southampton.ac.uk/psychology/undergraduate/courses.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/All_courses/l3l6_bsc_sociology_with_anthropology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_IR_list.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list.page",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geology_list.page",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geophysics_list.page",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/marine_biology_list.page",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography_list.page",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/ba_games_design.page",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/w190_ba_fine_art.page",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/w210_ba_graphic_arts.page",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/wj24_ba_fashion_and_textile_design.page",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/wn25_ba_fashion_marketing_management.page",


		
		
	};
	
	public static String[] UnData1={
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/audiology.page",
		"http://www.southampton.ac.uk/english/undergraduate/courses/q300_ba_english.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/q301_ba_english_with_year_abroad.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/q391_ba_english_literature_language_and_linguistics.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/q392-ba-english-literature-languages-and-linguistics.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qr31_ba_english_and_french.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qr32_ba_english_and_german.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qr34_ba_english_and_spanish.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qv31_ba_english_and_history.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qv32_ba_english_and_history_with_year_abroad.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qv36_ba_philosophy_and_english_with_year_abroad.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qw33_ba_english_and_music.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qw34_ba_english_and_music_with_year_abroad.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qw36_ba_film_and_english.page?",
		"http://www.southampton.ac.uk/english/undergraduate/courses/qw37_ba_film_and_english_with_year_abroad.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/p303_ba_film_studies.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/p304_ba_film_studies_with_year_abroad.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/qw36_ba_film_and_english.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/qw37_ba_film_and_english_with_year_abroad.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/rw16_ba_film_and_french.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/rw26_ba_film_and_german.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/rw46_ba_film_and_spanish.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/wv61_ba_film_and_history.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/wv64_ba_film_and_history_with_year_abroad.page?",
		"http://www.southampton.ac.uk/film/undergraduate/courses/wv65_ba_film_and_philosophy.page?",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/f6f8_bsc_geology_with_physical_geography.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/f7f8_bsc_oceanography_with_physical_geography.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/l701_bsc_population_and_geography.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/vl47_ba_archaeology_and_geography.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bn_nursing_adult_child.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bn_nursing_adult_mental_hlth.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bn_top_up_degree.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_clin_practice_general.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_healthcare_management_policy_and_research.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_healthcare_science_cardio.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_midwifery.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_nursing_adult.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_nursing_child.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_nursing_mental.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_occupational_therapy.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_physiotherapy.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_podiatry.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_public_health_practice.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/ba-ancient-history-3years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/ba-ancient-history-archeology-3years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/ba-ancient-history-french-4years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/ba-ancient-history-german-4years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/ba-ancient-history-history-3years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/ba-ancient-history-philosophy-3years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/ba-ancient-history-spanish-4years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/qv31_ba_english_and_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/rv11_ba_french_and_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/rv21_ba_german_and_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/rv41_ba_spanish_and_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/v100_ba_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/v101_ba_history_with_year_abroad.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/vl12_ba_modern_history_and_politics.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/vl13-ba-modern-politics-ya-4years.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/vv41_ba_archaeology_and_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/vv42_ba_archaeology_and_history_with_year_abroad.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/vv51_ba_philosophy_and_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/wv61_ba_film_and_history.page?",
		"http://www.southampton.ac.uk/history/undergraduate/courses/wv64_ba_film_and_history_with_year_abroad.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/c801-bsc-psychology-with-law.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M100_LLB_Bachelor.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M101_LLB_Accelerated.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M125_LLB_European.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M130_LLB_International.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M1M2_LLB_maritime.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/m200_llb_law_with_psychology.page?",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_advanced_materials.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_aerospace.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_automotive.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_bioengineering.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_engineering_management.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_mechatronics.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_naval_engineering.page",
		"http://www.southampton.ac.uk/malaysia/undergraduate/courses/meng_mech_eng_sustainable_energy_systems.page",
		"http://www.southampton.ac.uk/medicine/undergraduate/courses/bm4_a101.page",
		"http://www.southampton.ac.uk/medicine/undergraduate/courses/bm4_a101.page?",
		"http://www.southampton.ac.uk/medicine/undergraduate/courses/bm5_a100.page",
		"http://www.southampton.ac.uk/medicine/undergraduate/courses/bm5_a100.page?",
		"http://www.southampton.ac.uk/medicine/undergraduate/courses/bm6_a102.page",
		"http://www.southampton.ac.uk/medicine/undergraduate/courses/bm6_a102.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/1c72_mlang_french.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/1e45-mlang-languages-and-contemporary-european-studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/1t67_mlang_french_and_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/31c7_mlang_french_and_german_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/5a9v_mlang_french_linguistics_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/5f98_mlang_french_and_portuguese.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/5m2w-mlang-language-learning-integrated-masters-in-languages-4-yrs.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/5xp9_mlang_french_and_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/5xu3-mlang-language-and-society-integrated-masters-in-languages-4-yrs.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/5y87_mlang_french_and_spainish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/g1r1_bsc_maths_with_french.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/lr21_ba_politics_and_french.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/nrf1_bsc_management_sciences_and_french.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/q100_ba_language_learning.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/ql33_ba_language_and_society.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/qr31_ba_english_and_french.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/r101_ba_french_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/r120_ba_french.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/r900_ba_languages_and_contemporary_european_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/r990_ba_modern_languages.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/r9q3-ba-languages-and-contemporary-european-studies-international-only.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rr12_ba_french_and_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rr14_ba_french_and_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rr15_ba_french_and_portuguese.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rrc2_ba_french_and_german_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rrc4_ba_french_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rv11_ba_french_and_history.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rv15_ba_french_and_philosophy.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rw13_ba_french_and_music.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_french_studies/rw16_ba_film_and_french.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/1e45-mlang-languages-and-contemporary-european-studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/1r57_mlang_german_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/5b75_mlang_german_and_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/5d7h_mlang_german_linguistics_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/5m2w-mlang-language-learning-integrated-masters-in-languages-4-yrs-.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/5r24_mlang_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/5xu3-mlang-language-and-society-integrated-masters-in-languages-4yrs.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/g1r2_bsc_maths_with_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/lr22_ba_politics_and_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/nr22_bsc_management_sciences_and_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/q100_ba_language_learning.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/ql33_ba_language_and_society.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/qr32_ba_english_and_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/r201_ba_german_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/r220_ba_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/r900_ba_languages_and_contemporary_european_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/r990_ba_modern_languages.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/r9q3-ba-languages-and-contemporary-european-studies-english-international.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rr12_ba_french_and_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rr24_ba_german_and_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rrc2_ba_french_and_german_linguistsic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rrf4_ba_german_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rv21_ba_german_and_history.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rv25_ba_german_and_philosophy.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rw23_ba_german_and_music.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_german_studies/rw26_ba_film_and_german.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/1r57-mlang-german-and-spanish-linguistic-studies-4-yrs-.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/1t67-mlang-french-and-spanish-linguistic-studies-integrated-masters.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/31c7-mlang-french-and-german-linguistic-studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/5a9v-mlang-french-linguistics-studies-integrated-masters.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/5d7h-mlang-german-linguistic-studies-integrated-masters.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/5m2w_mlang_language_learning.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/5xu3_mlang_language_and_society.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/q100_ba_language_learning.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/q310_ba_applied_english_language_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/ql33_ba_language_and_society.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/r101_ba_french_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/r201_ba_german_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/r401_ba_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/rrc2_ba_french_and_german_linguistsic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/rrc4_ba_french_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_linguistic_studies/rrf4_ba_german_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/1b6s_mlang_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/1e45-mlang-languages-and-contemporary-european-studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/1g1s_mlang_spanish_and_latin_american_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/1jt6_mlang_spanish_and_portuguese.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/1r57_mlang_german_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/1w4d_mlang_the_spanish_speaking_world.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/5m2w-mlang-language-learning.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/5t2a_mlang_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/5xu3-mlang-language-and-society.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/g1r4_bsc_maths_with_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/nr24_bsc_management_sciences_and_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/q100_ba_language_learning.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/ql33_ba_language_and_society.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/qr34_ba_english_and_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/r400_ba_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/r401_ba_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/r430_ba_spanish_the_spanish_speaking_world.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/r900_ba_languages_and_contemporary_european_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/r990_ba_modern_languages.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/r9q3-ba-languages-and-contemporary-european-studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rl42_ba_politics_and_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rr14_ba_french_and_spanish.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rr15_ba_french_and_portuguese.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rr45_ba_spanish_and_portuguese_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rrc4_ba_french_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rrf4_ba_german_and_spanish_linguistic_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rtk7_ba_spanish_latin_american_studies.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/rv41_ba_spanish_and_history.page?",
		"http://www.southampton.ac.uk/ml/undergraduate/courses/modern_languages_spanish_portuguese_latin_american_studies/wr46_ba_film_and_spanish.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/g1w3_bsc_mathematics_with_music.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/hw73_bsc_acoustics_and_music.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/qw33_ba_english_and_music.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/rw13_ba_french_and_music.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/rw23_ba_german_and_music.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/vw53_philosophy_and_music.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/vw54_ba_philosophy_and_music_year_abroad.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/w300_ba_music.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/w301_ba_music_year_abroad.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/wb32_ba_music_and_management_sciences.page?",
		"http://www.southampton.ac.uk/music/undergraduate/courses/xx00-bsc-web-science-music.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/l0v0_ba_philosophy_politics_and_economics.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/qv35_ba_english_and_philosophy.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/qv36_ba_philosophy_and_english_with_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/rv15_ba_french_and_philosophy.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/rv25_ba_german_and_philosophy.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/v500_ba_philosophy.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/v501_ba_philosophy_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vg51_philosophy_and_mathematics.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vg52_ba_philosophy_and_mathematics_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vl36_ba_philosophy_and_sociology_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vl51_ba_economics_and_philosophy.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vl52_philosophy_and_politics.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vl53_philosophy_and_sociology.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vl54_ba_economics_and_philosophy_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vl54_ba_philosophy_and_politics_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vv51_ba_philosophy_and_history.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vv52_ba_philosophy_and_history_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vw53_philosophy_and_music.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/vw54_ba_philosophy_and_music_year_abroad.page?",
		"http://www.southampton.ac.uk/philosophy/undergraduate/courses/wv65_ba_film_and_philosophy.page?",
		"http://www.southampton.ac.uk/psychology/undergraduate/courses/c801-bsc-psychology-with-law.page",
		"http://www.southampton.ac.uk/psychology/undergraduate/courses/cx83_education_and_psychology.page",
		"http://www.southampton.ac.uk/psychology/undergraduate/courses/lc68_bsc_criminology_and_psychology.page",
		"http://www.southampton.ac.uk/psychology/undergraduate/courses/m200_llb_law_with_psychology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/l200_bsc-politics.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/l250_bsc-international_relations.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/l260_bsc_politics_and_international_relations.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/ll12_bsc_politics_and_economics.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/lr21_ba_politics_and_french.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/lr22_ba_politics_and_german.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/rl42_ba_politics_and_spanish_or_portuguese_and_latin_american_studies.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/vl12_ba_modern_history_and_politics.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/vl52_ba_philosophy_and_politics.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/i200_bsc_web_science_social_science.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/l300_bsc_sociology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/l3l6_bsc_sociology_with_anthropology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/l611_bsc_criminology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/lc68_bsc_criminology_and_psychology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/ll34_bsc_sociology_and_social_policy.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/ll63_bsc_sociology_and_criminology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/ll64_bsc_social_policy_and_criminology.page",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/vl53_bsc_philosophy_and_sociology.page",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geology/f600_bsc_geology.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geology/f601_msci_geology.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geology/f602_geology_with_foundation_year.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geology/f603_msci_geology_with_study_abroad.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geology/f6f8_bsc_geology_with_physical_geography.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geophysics/f640_bsc_geophysical_sciences.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geophysics/f660_msci_geophysics.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geophysics/f661_msci_geophysics_with_study_abroad.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/geophysics/f662_geophysics_with_foundation_year.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/marine_biology/7n16_biology_and_marine_biology.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/marine_biology/f703_msci_marine_biology.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/marine_biology/f704_msci_marine_biology_with_study_abroad.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/marine_biology/f705_marine_biology_with_foundation_year.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/marine_biology/f7c1_bsc_marine_biology_with_oceanography.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/38v2_msci_ocean_chemistry.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/f700_msci_oceanography.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/f701_oceanography_with_foundation_year.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/f702_msci_oceanography_with_study_abroad.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/f710_bsc_oceanography_single_honours.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/f7c1_bsc_marine_biology_with_oceanography.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/f7f8_bsc_oceanography_with_physical_geography.page?",
		"http://www.southampton.ac.uk/soes/undergraduate/courses/oceanography/f7r1_msci_oceanography_with_french.page?",
		"http://www.southampton.ac.uk/economics/undergraduate/courses/l112_bsc_economics_and_management_sciences.page",
		"http://www.southampton.ac.uk/economics/undergraduate/courses/ll12_bsc_politics_and_economics.page",
		"http://www.southampton.ac.uk/economics/undergraduate/courses/nl41_bsc_accounting_and_economics.page",
		"http://www.southampton.ac.uk/economics/undergraduate/courses/vl51_ba_economics_and_philosophy.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/environmental_sciences_list.page",
		"http://www.southampton.ac.uk/engineering/undergraduate/courses/foundation_year/engineering_physics_geophysics_foundation_year.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/f800_bsc_geography.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/f8f7_geography_with_oceanography.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/ff68_geography_with_geology.page",
		"http://www.southampton.ac.uk/geography/undergraduate/courses/l700_ba_geography.page",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bn_nursing_adult_child.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bn_nursing_adult_mental_hlth.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bn_top_up_degree.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_clin_practice_general.page?#overview",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_healthcare_management_policy_and_research.page?#entry",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_healthcare_management_policy_and_research.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_healthcare_science_cardio.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_midwifery.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_nursing_adult.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_nursing_child.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_nursing_mental.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_occupational_therapy.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_physiotherapy.page?#pathways",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_physiotherapy.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_podiatry.page?",
		"http://www.southampton.ac.uk/healthsciences/undergraduate/courses/bsc_public_health_practice.page?",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/archaeology/V400_ba_archaeology.page",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/english/q391_ba_english_literature_language_and_linguistics.page",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/film_studies/p303_ba_film_studies.page?",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/history/v100_ba_history.page",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/music/w300_ba_music.page?",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/our_courses/lmvo_foundation_year_law_arts_management_and_the_social_sciences_page.page",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/philosophy/v500_ba_philosophy.page?",
		"http://www.southampton.ac.uk/humanities/undergraduate/courses/pre_sessional/wsa.page",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M100_LLB_Bachelor.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M125_LLB_European.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M130_LLB_International.page?",
		"http://www.southampton.ac.uk/law/undergraduate/courses/M1M2_LLB_maritime.page?",
		"http://www.southampton.ac.uk/management/undergraduate/courses/bsc_marketing.page?",
		"http://www.southampton.ac.uk/management/undergraduate/courses/n400_bsc_accounting_finance.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g100_mathematics.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g103_mmath.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g120_mathematical_studies.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1c1_maths_with_biology.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1f3_maths_with_physics.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1g3_maths_with_statistics.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1g4_maths_with_computer_science.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1n3_maths_with_actuarial_science.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1nh_maths_with_finance.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1r1_maths_with_french.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1r2_maths_with_german.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1r4_maths_with_spanish.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/g1w3_maths_with_music.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/gl12_morse.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/mathematical-physics.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/mmorse.page?",
		"http://www.southampton.ac.uk/maths/undergraduate/courses/vg51_ba_philosophy_and_mathematics.page?",
		"http://www.southampton.ac.uk/my/undergraduate/courses/mech-eng-comp.page",
		"http://www.southampton.ac.uk/my/undergraduate/courses/meng_mech_eng_acoustical_engineering.page",
		"http://www.southampton.ac.uk/psychology/undergraduate/courses/c800_bsc_psychology.page?",
		"http://www.southampton.ac.uk/psychology/undergraduate/courses/lmv0_foundation_year.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/All_courses/l260_bsc_politics_and_international_relations.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/All_courses/l300_bsc_sociology.page?#entry",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/All_courses/ll34_bsc_sociology_and_social_policy.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/l200_bsc-politics.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Politics_list/l250_bsc-international_relations.page?",
		"http://www.southampton.ac.uk/socsci/undergraduate/courses/Sociology_list/l310_bsc_applied_social_sciences_general_pathway.page",
		"http://www.southampton.ac.uk/undergraduate/courses/healthcare_management_policy_research.shtml",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/ba_games_design.page",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/w190_ba_fine_art.page?",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/w210_ba_graphic_arts.page?",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/wj24_ba_fashion_and_textile_design.page?",
		"http://www.southampton.ac.uk/wsa/undergraduate/courses/wn25_ba_fashion_marketing_management.page"
	};
	
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
