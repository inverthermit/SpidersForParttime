package imperial;

import java.io.File;
import java.io.FileInputStream;

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
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
FileInputStream fis=new FileInputStream(new File("./imperialUn.html"));//鏂板缓涓�涓狥ileInputStream瀵硅薄
        
        byte[] b=new byte[fis.available()];//鏂板缓涓�涓瓧鑺傛暟缁�
        fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
       fis.close();
        String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
       //System.out.println(htmls);
        int count=1;
	    Parser	parser=Parser.createParser(htmls, "utf-8");
	    AndFilter ListFilter=new AndFilter(new TagNameFilter("li"),
                new HasAttributeFilter("class","course"));
	    NodeList nodes0=parser.extractAllNodesThatMatch(ListFilter);
	    for(int x=0;x<nodes0.size();x++)
   	    {
	    	String section=nodes0.elementAt(x).toHtml();
	    	parser=Parser.createParser(section, "utf-8");
   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
                   new HasAttributeFilter("href"));
   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
   	 
   	    for(int i=0;i<nodes4.size();i++)
   	    {
   	    	LinkTag link=(LinkTag)nodes4.elementAt(i);
   	    	String get1=link.getAttribute("href");
   	    	parser=Parser.createParser(section, "utf-8");
   	    	TagNameFilter InformationFilter= new TagNameFilter("div");
   	    	NodeList nodes=parser.extractAllNodesThatMatch(InformationFilter);
   	    	String school="";
   	    	String length="";
   	    	String type="";
   	    	
   	    	//System.out.println(nodes.size());
   	    	for(int j=0;j<nodes.size();j++)
   	    	{
   	    		Node node=nodes.elementAt(j);
   	    		String content=node.toHtml();
   	    		if(content.contains("class=\"type dept\""))
   	    		{
   	    			school=html2Str(content).trim();
   	    		}
   	    		else if(content.contains("Duration:"))
   	    		//else if(content.contains("Mode of study:"))
   	    		{
   	    			length=html2Str(content.replace("Duration:", "")).trim();
   	    			//length=html2Str(content.replace("Mode of study:", "")).trim();
   	    		}
   	    		else if(content.contains("Degree:"))
   	    		//else if(content.contains("Qualification/s:"))
   	    		{
   	    			type=html2Str(content.replace("Degree:", "")).trim();
   	    			//type=html2Str(content.replace("Qualification/s:", "")).trim();
   	    		}
   	    	}
   	    	
   	    	if(get1.startsWith("/study/ug/courses"))
   	    	{

   	   	        System.out.println("{\""+count+"\",\"http://www.ic.ac.uk"+get1+"\",\""+link.getAttribute("title")+"\",\""+type+"\",\""+school+"\",\""+length.replace("1yr", "12").replace("2yrs", "24").replace("3yrs", "36").replace("4yrs", "48").replace("5yrs", "60")+"\",\"0\"},");
   	    	    count++;
   	    	}
   	    }
   	    }
   	    
	    
	}
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	
	public static String[][] UnData={
		{"1","http://www.ic.ac.uk/study/ug/courses/aeronautics-department/aeronautical-engineering/","Aeronautical Engineering","MEng","Aeronautics","48","0"},
		{"2","http://www.ic.ac.uk/study/ug/courses/aeronautics-department/aeronautical-engineering-year-abroad/","Aeronautical Engineering with a Year Abroad","MEng","Aeronautics","48","0"},
		{"3","http://www.ic.ac.uk/study/ug/courses/aeronautics-department/aeronautical-engineering-year-industry/","Aeronautical Engineering with a Year in Industry","MEng","Aeronautics","60","0"},
		{"4","http://www.ic.ac.uk/study/ug/courses/aeronautics-department/aeronautics-spacecraft-engineering/","Aeronautics with Spacecraft Engineering","MEng","Aeronautics","48","0"},
		{"5","http://www.ic.ac.uk/study/ug/courses/materials-department/materials/","Aerospace Materials","MEng","Materials","48","0"},
		{"6","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry/","Biochemistry","BSc","Life Sciences","36","0"},
		{"7","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry/","Biochemistry with a Year in Industry/Research","BSc","Life Sciences","48","0"},
		{"8","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry-languages/","Biochemistry with French for Science","BSc","Life Sciences","48","0"},
		{"9","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology-languages/","Biochemistry with German for Science","BSc","Life Sciences","48","0"},
		{"10","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry/","Biochemistry with Management","BSc","Life Sciences","36","0"},
		{"11","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry/","Biochemistry with Management","BSc","Life Sciences","48","0"},
		{"12","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry/","Biochemistry with Management and a Year in Industry/Research","BSc","Life Sciences","48","0"},
		{"13","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry/","Biochemistry with Management with a Year in Industry/Research","BSc","Life Sciences","60","0"},
		{"14","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry/","Biochemistry with Research Abroad","BSc","Life Sciences","48","0"},
		{"15","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biochemistry-languages/","Biochemistry with Spanish for Science","BSc","Life Sciences","48","0"},
		{"16","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-management/","Biological Sciences","BSc","Life Sciences","36","0"},
		{"17","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-year-industry/","Biological Sciences with a Year in Industry/Research","BSc","Life Sciences","48","0"},
		{"18","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-languages/","Biological Sciences with French for Science","BSc","Life Sciences","48","0"},
		{"19","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-languages/","Biological Sciences with German for Science","BSc","Life Sciences","48","0"},
		{"20","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-management/","Biological Sciences with Management","BSc","Life Sciences","36","0"},
		{"21","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-management/","Biological Sciences with Management","BSc","Life Sciences","48","0"},
		{"22","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-year-industry/","Biological Sciences with Management and a Year in Industry/Research","BSc","Life Sciences","48","0"},
		{"23","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-year-industry/","Biological Sciences with Management with a Year in Industry/Research","BSc","Life Sciences","60","0"},
		{"24","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-year-industry/","Biological Sciences with Research Abroad","BSc","Life Sciences","48","0"},
		{"25","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-languages/","Biological Sciences with Spanish for Science","BSc","Life Sciences","48","0"},
		{"26","http://www.ic.ac.uk/study/ug/courses/materials-department/materials/","Biomaterials and Tissue Engineering","MEng","Materials","48","0"},
		{"27","http://www.ic.ac.uk/study/ug/courses/bioengineering-department/biomedical-engineering-meng/","Biomedical Engineering","MEng","Bioengineering","48","0"},
		{"28","http://www.ic.ac.uk/study/ug/courses/bioengineering-department/biomedical-engineering-year-industry/","Biomedical Engineering with a Year in Industry","MEng","Bioengineering","60","0"},
		{"29","http://www.ic.ac.uk/study/ug/courses/school-of-medicine/biomedical-science/","Biomedical Science","BSc","School of Medicine","36","0"},
		{"30","http://www.ic.ac.uk/study/ug/courses/school-of-medicine/biomedical-science/","Biomedical Science with Management","BSc","School of Medicine","48","0"},
		{"31","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology/","Biotechnology","BSc","Life Sciences","36","0"},
		{"32","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology/","Biotechnology with a Year in Industry/Research","BSc","Life Sciences","48","0"},
		{"33","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology-languages/","Biotechnology with French for Science","BSc","Life Sciences","48","0"},
		{"34","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology-languages/","Biotechnology with German for Science","BSc","Life Sciences","48","0"},
		{"35","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology/","Biotechnology with Management","BSc","Life Sciences","48","0"},
		{"36","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology/","Biotechnology with Management with a Year in Industry/Research","BSc","Life Sciences","60","0"},
		{"37","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology/","Biotechnology with Research Abroad","BSc","Life Sciences","48","0"},
		{"38","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biotechnology-languages/","Biotechnology with Spanish for Science","BSc","Life Sciences","48","0"},
		{"39","http://www.ic.ac.uk/study/ug/courses/chemical-engineering-department/chemical-engineering/","Chemical Engineering","MEng","Chemical Engineering","48","0"},
		{"40","http://www.ic.ac.uk/study/ug/courses/chemical-engineering-department/chemical-engineering-year-abroad/","Chemical Engineering with a Year Abroad","MEng","Chemical Engineering","48","0"},
		{"41","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-management/","Chemical Sciences and Management","BSc","Chemistry","36","0"},
		{"42","http://www.ic.ac.uk/study/ug/courses/chemical-engineering-department/chemical-nuclear-engineering/","Chemical with Nuclear Engineering","MEng","Chemical Engineering","48","0"},
		{"43","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry/","Chemistry","BSc","Chemistry","36","0"},
		{"44","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry/","Chemistry","MSci","Chemistry","48","0"},
		{"45","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry/","Chemistry with a Year in Industry","MSci","Chemistry","60","0"},
		{"46","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-languages/","Chemistry with French for Science","MSci","Chemistry","48","0"},
		{"47","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-languages/","Chemistry with German for Science","MSci","Chemistry","48","0"},
		{"48","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-management/","Chemistry with Management","BSc","Chemistry","48","0"},
		{"49","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-management/","Chemistry with Management and a Year in Industry","BSc","Chemistry","60","0"},
		{"50","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-medicinal/","Chemistry with Medicinal Chemistry","MSci","Chemistry","48","0"},
		{"51","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-medicinal/","Chemistry with Medicinal Chemistry and a Year in Industry","MSci","Chemistry","60","0"},
		{"52","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-molecular-physics/","Chemistry with Molecular Physics","MSci","Chemistry","48","0"},
		{"53","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-molecular-physics/","Chemistry with Molecular Physics and a Year in Industry","MSci","Chemistry","60","0"},
		{"54","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-year-abroad/","Chemistry with Research Abroad","MSci","Chemistry","48","0"},
		{"55","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-year-abroad/","Chemistry with Research Abroad and a Year in Industry","MSci","Chemistry","60","0"},
		{"56","http://www.ic.ac.uk/study/ug/courses/chemistry-department/chemistry-languages/","Chemistry with Spanish for Science","MSci","Chemistry","48","0"},
		{"57","http://www.ic.ac.uk/study/ug/courses/civil-environmental-engineering-department/civil-engineering/","Civil Engineering","MEng","Civil and Environmental Engineering","48","0"},
		{"58","http://www.ic.ac.uk/study/ug/courses/civil-environmental-engineering-department/civil-engineering-year-abroad/","Civil Engineering with a Year Abroad","MEng","Civil and Environmental Engineering","48","0"},
		{"59","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing","BEng","Computing","36","0"},
		{"60","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing (Artificial Intelligence)","MEng","Computing","48","0"},
		{"61","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing (Computation in Biology and Medicine)","MEng","Computing","48","0"},
		{"62","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing (Computational Management)","MEng","Computing","48","0"},
		{"63","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing (Games, Vision and Interaction)","MEng","Computing","48","0"},
		{"64","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing (International Programme of Study)","MEng","Computing","48","0"},
		{"65","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing","MEng","Computing","48","0"},
		{"66","http://www.ic.ac.uk/study/ug/courses/computing-department/computing/","Computing (Software Engineering)","MEng","Computing","48","0"},
		{"67","http://www.ic.ac.uk/study/ug/courses/school-design-engineering/design-engineering/","Design Engineering","MEng","Dyson School of Design Engineering","48","0"},
		{"68","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-ecology-environmental/","Ecology and Environmental Biology","BSc","Life Sciences","36","0"},
		{"69","http://www.ic.ac.uk/study/ug/courses/electrical-engineering-department/electrical-electronic-engineering/","Electrical and Electronic Engineering","BEng","Electrical and Electronic Engineering","36","0"},
		{"70","http://www.ic.ac.uk/study/ug/courses/electrical-engineering-department/electrical-electronic-engineering/","Electrical and Electronic Engineering","MEng","Electrical and Electronic Engineering","48","0"},
		{"71","http://www.ic.ac.uk/study/ug/courses/electrical-engineering-department/electrical-electronic-engineering/","Electrical and Electronic Engineering with a Year Abroad","MEng","Electrical and Electronic Engineering","48","0"},
		{"72","http://www.ic.ac.uk/study/ug/courses/electrical-engineering-department/electrical-electronic-engineering/","Electrical and Electronic Engineering with Management","MEng","Electrical and Electronic Engineering","48","0"},
		{"73","http://www.ic.ac.uk/study/ug/courses/electrical-engineering-department/electronic-information-engineering/","Electrical and Information Engineering","BEng","Electrical and Electronic Engineering","36","0"},
		{"74","http://www.ic.ac.uk/study/ug/courses/electrical-engineering-department/electronic-information-engineering/","Electrical and Information Engineering","MEng","Electrical and Electronic Engineering","48","0"},
		{"75","http://www.ic.ac.uk/study/ug/courses/electrical-engineering-department/electronic-information-engineering/","Electrical and Information Engineering with a Year Abroad","MEng","Electrical and Electronic Engineering","48","0"},
		{"76","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geology/","Geology","BSc","Earth Science and Engineering","36","0"},
		{"77","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geology/","Geology","MSci","Earth Science and Engineering","48","0"},
		{"78","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geology/","Geology and Geophysics","MSci","Earth Science and Engineering","48","0"},
		{"79","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geology/","Geology with a Year Abroad","MSci","Earth Science and Engineering","48","0"},
		{"80","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geophysics/","Geophysics","BSc","Earth Science and Engineering","36","0"},
		{"81","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geophysics/","Geophysics","MSci","Earth Science and Engineering","48","0"},
		{"82","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geophysics/","Geophysics with a Year Abroad","MSci","Earth Science and Engineering","48","0"},
		{"83","http://www.ic.ac.uk/study/ug/courses/materials-department/materials/","Materials Science and Engineering","MEng","Materials","48","0"},
		{"84","http://www.ic.ac.uk/study/ug/courses/materials-department/materials/","Materials Science and Engineering","BEng","Materials","36","0"},
		{"85","http://www.ic.ac.uk/study/ug/courses/materials-department/materials/","Materials with Management","BEng","Materials","36","0"},
		{"86","http://www.ic.ac.uk/study/ug/courses/materials-department/materials/","Materials with Nuclear Engineering","MEng","Materials","48","0"},
		{"87","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics","BSc","Mathematics","36","0"},
		{"88","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics","MSci","Mathematics","48","0"},
		{"89","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics (Pure Mathematics)","BSc","Mathematics","36","0"},
		{"90","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics and Computer Science ","BEng","Mathematics","36","0"},
		{"91","http://www.ic.ac.uk/study/ug/courses/computing-department/mathematics-computer-science/","Mathematics and Computer Science","MEng","Computing","48","0"},
		{"92","http://www.ic.ac.uk/study/ug/courses/computing-department/mathematics-computer-science/","Mathematics and Computer Science (Computational Statistics)","MEng","Computing","48","0"},
		{"93","http://www.ic.ac.uk/study/ug/courses/computing-department/mathematics-computer-science/","Mathematics and Computer Science (Pure Maths and Computational Logic)","MEng","Computing","48","0"},
		{"94","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics with a Year in Europe","MSci","Mathematics","48","0"},
		{"95","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics with Applied Mathematics/Mathematical Physics","BSc","Mathematics","36","0"},
		{"96","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics with Education","BSc","Mathematics","36","0"},
		{"97","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics with Education","MSci","Mathematics","48","0"},
		{"98","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics with Mathematical Computation","BSc","Mathematics","36","0"},
		{"99","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics with Statistics","BSc","Mathematics","36","0"},
		{"100","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics with Statistics for Finance","BSc","Mathematics","36","0"},
		{"101","http://www.ic.ac.uk/study/ug/courses/mathematics-department/mathematics/","Mathematics, Optimisation and Statistics","BSc","Mathematics","36","0"},
		{"102","http://www.ic.ac.uk/study/ug/courses/mechanical-engineering-department/mechanical-engineering/","Mechanical Engineering","MEng","Mechanical Engineering","48","0"},
		{"103","http://www.ic.ac.uk/study/ug/courses/mechanical-engineering-department/mechanical-engineering-year-abroad/","Mechanical Engineering with a Year Abroad","MEng","Mechanical Engineering","48","0"},
		{"104","http://www.ic.ac.uk/study/ug/courses/mechanical-engineering-department/mechanical-nuclear-engineering/","Mechanical with Nuclear Engineering","MEng","Mechanical Engineering","48","0"},
		{"105","http://www.ic.ac.uk/study/ug/courses/school-of-medicine/medicine-phd/","Medicine","MBBS/PhD","School of Medicine","8/9yrs","0"},
		{"106","http://www.ic.ac.uk/study/ug/courses/school-of-medicine/medicine/","Medicine","MBBS/BSc","School of Medicine","6yrs","0"},
		{"107","http://www.ic.ac.uk/study/ug/courses/school-of-medicine/medicine-graduate-entry/","Graduate Medicine","MBBS","School of Medicine","60","0"},
		{"108","http://www.ic.ac.uk/study/ug/courses/school-of-medicine/medicine-singapore/","Medicine (Lee Kong Chian School of Medicine, Singapore)","MBBS","School of Medicine","60","0"},
		{"109","http://www.ic.ac.uk/study/ug/courses/life-sciences-department/biology-ecology-environmental/","Microbiology","BSc","Life Sciences","36","0"},
		{"110","http://www.ic.ac.uk/study/ug/courses/earth-science-department/geology/","Petroleum Geoscience","MSci","Earth Science and Engineering","48","0"},
		{"111","http://www.ic.ac.uk/study/ug/courses/physics-department/physics/","Physics","MSci","Physics","48","0"},
		{"112","http://www.ic.ac.uk/study/ug/courses/physics-department/physics/","Physics","BSc","Physics","36","0"},
		{"113","http://www.ic.ac.uk/study/ug/courses/physics-department/physics-music/","Physics and Music Performance","BSc","Physics","48","0"},
		{"114","http://www.ic.ac.uk/study/ug/courses/physics-department/physics-year-europe/","Physics with a Year in Europe","MSci","Physics","48","0"},
		{"115","http://www.ic.ac.uk/study/ug/courses/physics-department/physics-science-education/","Physics with Science Education","MSci","Physics","48","0"},
		{"116","http://www.ic.ac.uk/study/ug/courses/physics-department/physics-science-education/","Physics with Science Education","BSc","Physics","36","0"},
		{"117","http://www.ic.ac.uk/study/ug/courses/physics-department/physics-theoretical/","Physics with Theoretical Physics","BSc","Physics","36","0"},
		{"118","http://www.ic.ac.uk/study/ug/courses/physics-department/physics-theoretical/","Physics with Theoretical Physics","MSci","Physics","48","0"}
	};
	public static String[][] PostData={
		{"1","http://www.ic.ac.uk/study/pg/aeronautics/advanced-aeronautical-engineering/","Advanced Aeronautical Engineering","MSc","Aeronautics","1Y FT","0"},
		{"2","http://www.ic.ac.uk/study/pg/chemical-engineering/advanced-chemical-engineering/","Advanced Chemical Engineering","MSc","Chemical Engineering","1Y FT","0"},
		{"3","http://www.ic.ac.uk/study/pg/chemical-engineering/biotechnology/","Advanced Chemical Engineering with Biotechnology","MSc","Chemical Engineering","1Y FT","0"},
		{"4","http://www.ic.ac.uk/study/pg/chemical-engineering/process-systems-engineering/","Advanced Chemical Engineering with Process Systems Engineering","MSc","Chemical Engineering","1Y FT","0"},
		{"5","http://www.ic.ac.uk/study/pg/chemical-engineering/structured-product-engineering/","Advanced Chemical Engineering with Structured Product Engineering","MSc","Chemical Engineering","1Y FT","0"},
		{"6","http://www.ic.ac.uk/study/pg/aeronautics/computational-methods/","Advanced Computational Methods for Aeronautics, Flow Management and Fluid-Structure Interaction","MSc","Aeronautics","1Y FT / 2Y PT","0"},
		{"7","http://www.ic.ac.uk/study/pg/computing/advanced-computing/","Advanced Computing","MSc","Computing","1Y FT","0"},
		{"8","http://www.ic.ac.uk/study/pg/computing/advanced-computing-mres/","Advanced Computing","MRes","Computing","1Y FT","0"},
		{"9","http://www.ic.ac.uk/study/pg/materials/advanced-materials/","Advanced Materials Science and Engineering","MSc","Materials","1Y FT","0"},
		{"10","http://www.ic.ac.uk/study/pg/mechanical-engineering/advanced-mechanical/","Advanced Mechanical Engineering","MSc","Mechanical Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"11","http://www.ic.ac.uk/study/pg/materials/nuclear-engineering/","Advanced Nuclear Engineering","MSc","Materials","1Y FT","0"},
		{"12","http://www.ic.ac.uk/study/pg/medicine/allergy/","Allergy","MSc","Medicine","2Y PT / 3Y PT","0"},
		{"13","http://www.ic.ac.uk/study/pg/medicine/allergy/","Allergy","PG Cert","Medicine","9 months PT","0"},
		{"14","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Anaesthetics, Pain Medicine and Intensive Care","MRes","Surgery and Cancer","1Y FT","0"},
		{"15","http://www.ic.ac.uk/study/pg/electrical-engineering/analogue-digital-circuit/","Analogue and Digital Integrated Circuit Design","MSc","Electrical and Electronic Engineering","1Y FT","0"},
		{"16","http://www.ic.ac.uk/study/pg/life-sciences/biosciences-biotechnology/","Applied Biosciences and Biotechnology","MSc","Life Sciences","1Y FT","0"},
		{"17","http://www.ic.ac.uk/study/pg/mathematics/applied-mathematics/","Applied Mathematics","MSc","Mathematics","1Y FT / 2Y PT","0"},
		{"18","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Bacterial Pathogenisis and Infection","MRes","Surgery and Cancer","1Y FT","0"},
		{"19","http://www.ic.ac.uk/study/pg/bioengineering/bioengineering-mres/","Bioengineering","MRes","Bioengineering","1Y FT","0"},
		{"20","http://www.ic.ac.uk/study/pg/chemistry/bioimaging-sciences/","Bioimaging Sciences","MRes","Chemistry","1Y FT","0"},
		{"21","http://www.ic.ac.uk/study/pg/life-sciences/bioinformatics/","Bioinformatics and Theoretical Systems Biology","MSc","Life Sciences","1Y FT","0"},
		{"22","http://www.ic.ac.uk/study/pg/bioengineering/biomedical-engineering/","Biomedical Engineering","MSc","Bioengineering","1Y FT","0"},
		{"23","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Biomedical Research","MRes","Surgery and Cancer","1Y FT","0"},
		{"24","http://www.ic.ac.uk/study/pg/life-sciences/biosystematics/","Biosystematics","MRes","Life Sciences","1Y FT","0"},
		{"25","http://www.ic.ac.uk/study/pg/business-school/business-analytics/","Business Analytics","MSc","Imperial College Business School","1Y FT","0"},
		{"26","http://www.ic.ac.uk/study/pg/surgery-and-cancer/cancer-biology/","Cancer Biology","MRes","Surgery and Cancer","1Y FT","0"},
		{"27","http://www.ic.ac.uk/study/pg/nhli/cardiorespiratory-nursing/","Cardiorespiratory Nursing","MSc","National Heart and Lung Institute","2Y PT","0"},
		{"28","http://www.ic.ac.uk/study/pg/chemistry/catalysis/","Catalysis: Chemistry and Engineering","MRes","Chemistry","1Y FT","0"},
		{"29","http://www.ic.ac.uk/study/pg/chemistry/chemical-biology/","Chemical Biology: Multi-Disciplinary Physical Scientists for Next-Generation Biological, Biomedical and Pharmaceutical Research and Development","MRes","Chemistry","1Y FT","0"},
		{"30","http://www.ic.ac.uk/study/pg/business-school/climate-change-management-finance/","Climate Change, Management &amp; Finance","MSc","Imperial College Business School","1Y FT","0"},
		{"31","http://www.ic.ac.uk/study/pg/medicine/clinical-research/","Clinical Research","MRes","Medicine","1Y FT / 2Y PT","0"},
		{"32","http://www.ic.ac.uk/study/pg/medicine/clinical-research/","Clinical Research Design and Management","MRes","Medicine","1Y FT / 2Y PT","0"},
		{"33","http://www.ic.ac.uk/study/pg/electrical-engineering/communications-signal-processing/","Communications and Signal Processing","MSc","Electrical and Electronic Engineering","1Y FT","0"},
		{"34","http://www.ic.ac.uk/study/pg/aeronautics/composites/","Composites: the Science, Technology and Engineering Application of Advanced Composites","MSc","Aeronautics","1Y FT / 2Y PT","0"},
		{"35","http://www.ic.ac.uk/study/pg/life-sciences/computational-methods-ecology-evolution-mres/","Computational Methods in Ecology and Evolution","MRes","Life Sciences","1Y FT","0"},
		{"36","http://www.ic.ac.uk/study/pg/life-sciences/computational-methods-ecology-evolution/","Computational Methods in Ecology and Evolution","MSc","Life Sciences","1Y FT","0"},
		{"37","http://www.ic.ac.uk/study/pg/computing/artificial-intelligence/","Computing (Artificial Intelligence)","MSc","Computing","1Y FT","0"},
		{"38","http://www.ic.ac.uk/study/pg/computing/computational-management-science/","Computing (Computational Management Science)","MSc","Computing","1Y FT","0"},
		{"39","http://www.ic.ac.uk/study/pg/computing/machine-learning/","Computing (Machine Learning)","MSc","Computing","1Y FT","0"},
		{"40","http://www.ic.ac.uk/study/pg/computing/secure-software-systems/","Computing (Secure Software Systems)","MSc","Computing","1Y FT","0"},
		{"41","http://www.ic.ac.uk/study/pg/computing/software-engineering/","Computing (Software Engineering)","MSc","Computing","1Y FT","0"},
		{"42","http://www.ic.ac.uk/study/pg/computing/visual-informational-processing/","Computing (Visual Information Processing)","MSc","Computing","1Y FT","0"},
		{"43","http://www.ic.ac.uk/study/pg/computing/computing-science/","Computing Science","MSc","Computing","1Y FT","0"},
		{"44","http://www.ic.ac.uk/study/pg/civil-engineering/concrete-structures/","Concrete Structures","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"45","http://www.ic.ac.uk/study/pg/civil-engineering/concrete-structures/","Concrete Structures and Business Management","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"46","http://www.ic.ac.uk/study/pg/civil-engineering/concrete-structures/","Concrete Structures and Sustainable Development","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"47","http://www.ic.ac.uk/study/pg/life-sciences/conservation-science/","Conservation Science","MSc","Life Sciences","1Y FT","0"},
		{"48","http://www.ic.ac.uk/study/pg/electrical-engineering/control-systems/","Control Systems","MSc","Electrical and Electronic Engineering","1Y FT","0"},
		{"49","http://www.ic.ac.uk/study/pg/physics/controlled-quantum-dynamics/","Controlled Quantum Dynamics","MRes","Physics","1Y FT","0"},
		{"50","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Data Science","MRes","Surgery and Cancer","1Y FT","0"},
		{"51","http://www.ic.ac.uk/study/pg/medicine/diabetes-obesity/","Diabetes and Obesity","MRes","Medicine","1Y FT / 2Y PT","0"},
		{"52","http://www.ic.ac.uk/study/pg/chemistry/drug-discovery-development/","Drug Discovery and Development: Multidisciplinary Science for Next Generation Therapeutics","MRes","Chemistry","1Y FT","0"},
		{"53","http://www.ic.ac.uk/study/pg/civil-engineering/earthquake-engineering/","Earthquake Engineering","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"54","http://www.ic.ac.uk/study/pg/life-sciences/ecological-applications/","Ecological Applications","MSc","Life Sciences","1Y FT","0"},
		{"55","http://www.ic.ac.uk/study/pg/life-sciences/ecology-evolution-conservation/","Ecology, Evolution and Conservation","MSc","Life Sciences","1Y FT / 2Y PT / 3Y PT","0"},
		{"56","http://www.ic.ac.uk/study/pg/life-sciences/ecology-evolution-conservation-research/","Ecology, Evolution and Conservation Research","MRes","Life Sciences","1Y FT / 2Y PT / 3Y PT","0"},
		{"57","http://www.ic.ac.uk/study/pg/business-school/economics-strategy-business/","Economics and Strategy for Business","MSc","Imperial College Business School","1Y FT","0"},
		{"58","http://www.ic.ac.uk/study/pg/life-sciences/ecosystems/","Ecosystem and Environmental Change (formerly titled Grand Challenges in Ecosystems and the Environment)","MRes","Life Sciences","1Y FT / 2Y PT","0"},
		{"59","http://www.ic.ac.uk/study/pg/civil-engineering/environmental-engineering/","Environmental Engineering","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"60","http://www.ic.ac.uk/study/pg/civil-engineering/environmental-engineering/","Environmental Engineering and Business Management","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"61","http://www.ic.ac.uk/study/pg/civil-engineering/environmental-engineering/","Environmental Engineering and Sustainable Development","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"62","http://www.ic.ac.uk/study/pg/environmental-policy/environmental-technology/","Environmental Technology","MSc","Centre for Environmental Policy","1Y FT / 2Y PT","0"},
		{"63","http://www.ic.ac.uk/study/pg/public-health/epidemiology/","Epidemiology","MSc","School of Public Health","1Y FT","0"},
		{"64","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Epidemiology, Evolution and Control of Infectious Diseases","MSc","Surgery and Cancer","1Y FT","0"},
		{"65","http://www.ic.ac.uk/study/pg/medicine/experimental-neuroscience/","Experimental Neuroscience","MRes","Medicine","1Y FT","0"},
		{"66","http://www.ic.ac.uk/study/pg/business-school/finance/","Finance","MSc","Imperial College Business School","1Y FT","0"},
		{"67","http://www.ic.ac.uk/study/pg/business-school/finance-accounting/","Finance and Accounting","MSc","Imperial College Business School","1Y FT","0"},
		{"68","http://www.ic.ac.uk/study/pg/aeronautics/fluid-dynamics-across-scales-mres/","Fluid Dynamics Across Scales","MRes","Aeronautics","1Y FT","0"},
		{"69","http://www.ic.ac.uk/study/pg/electrical-engineering/future-power-networks/","Future Power Networks","MSc","Electrical and Electronic Engineering","1Y FT","0"},
		{"70","http://www.ic.ac.uk/study/pg/civil-engineering/general-structural-engineering/","General Structural Engineering","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"71","http://www.ic.ac.uk/study/pg/nhli/genes-drugs-stem-cells/","Genes, Drugs and Stem Cells - Novel Therapies (formerly titled Advanced Therapeutic Strategies)","PG Cert","National Heart and Lung Institute","3 months FT","0"},
		{"72","http://www.ic.ac.uk/study/pg/nhli/genes-drugs-stem-cells/","Genes, Drugs and Stem Cells - Novel Therapies (formerly titled Advanced Therapeutic Strategies)","MSc","National Heart and Lung Institute","1 Y FT","0"},
		{"73","http://www.ic.ac.uk/study/pg/nhli/genomic-medicine-msc/","Genomic Medicine","MSc","National Heart and Lung Institute","1Y FT / 2Y PT","0"},
		{"74","http://www.ic.ac.uk/study/pg/nhli/genomic-medicine-certificate/","Genomic Medicine","PG Cert","National Heart and Lung Institute","4 months FT / 1Y PT","0"},
		{"75","http://www.ic.ac.uk/study/pg/nhli/genomic-medicine-diploma/","Genomic Medicine","PG Dip","National Heart and Lung Institute","8 months FT / 2Y PT","0"},
		{"76","http://www.ic.ac.uk/study/pg/design-engineering/global-innovation-design/","Global Innovation Design","MA/MSc","Dyson School of Design Engineering","2Y FT","0"},
		{"77","http://www.ic.ac.uk/study/pg/chemistry/green-chemistry/","Green Chemistry, Energy and the Environment","MRes","Chemistry","1Y FT","0"},
		{"78","http://www.ic.ac.uk/study/pg/surgery-and-cancer/health-policy/","Health Policy","MSc","Surgery and Cancer","2Y PT","0"},
		{"79","http://www.ic.ac.uk/study/pg/public-health/human-molecular-genetics/","Human Molecular Genetics","MSc","School of Public Health","1Y FT","0"},
		{"80","http://www.ic.ac.uk/study/pg/medicine/human-nutrition/","Human Nutrition","MRes","Medicine","1Y FT / 2Y PT","0"},
		{"81","http://www.ic.ac.uk/study/pg/civil-engineering/hydrology/","Hydrology and Business Management","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"82","http://www.ic.ac.uk/study/pg/civil-engineering/hydrology/","Hydrology and Sustainable Development","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"83","http://www.ic.ac.uk/study/pg/civil-engineering/hydrology/","Hydrology and Water Resources Management","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"84","http://www.ic.ac.uk/study/pg/medicine/immunology/","Immunology","MSc","Medicine","1Y FT","0"},
		{"85","http://www.ic.ac.uk/study/pg/design-engineering/innovation-design-engineering/","Innovation Design Engineering","MA/MSc","Dyson School of Design Engineering","2Y FT","0"},
		{"86","http://www.ic.ac.uk/study/pg/business-school/innovation-entrepreneurship-management/","Innovation, Entrepreneurship and Management","MSc","Imperial College Business School","1Y FT","0"},
		{"87","http://www.ic.ac.uk/study/pg/business-school/international-health-management/","International Health Management","MSc","Imperial College Business School","1Y FT","0"},
		{"88","http://www.ic.ac.uk/study/pg/business-school/investment-wealth-management/","Investment and Wealth Management","MSc","Imperial College Business School","1Y FT","0"},
		{"89","http://www.ic.ac.uk/study/pg/business-school/management/","Management","MSc","Imperial College Business School","1Y FT","0"},
		{"90","http://www.ic.ac.uk/study/pg/mathematics/mathematics-finance/","Mathematics and Finance","MSc","Mathematics","1Y FT / 2Y PT","0"},
		{"91","http://www.ic.ac.uk/study/pg/business-school/mba/","MBA","MBA","Imperial College Business School","1Y FT","0"},
		{"92","http://www.ic.ac.uk/study/pg/business-school/executive-mba/","MBA (Executive)","MBA","Imperial College Business School","23 months part time","0"},
		{"93","http://www.ic.ac.uk/study/pg/business-school/global-mba/","MBA (Global Online)","MBA","Imperial College Business School","2Y PT","0"},
		{"94","http://www.ic.ac.uk/study/pg/business-school/weekend-mba/","MBA (Weekend)","MBA","Imperial College Business School","21 months part time","0"},
		{"95","http://www.ic.ac.uk/study/pg/bioengineering/medical-device-design/","Medical Device Design and Entrepreneurship","MRes","Bioengineering","1Y FT","0"},
		{"96","http://www.ic.ac.uk/study/pg/surgery-and-cancer/medical-robotics/","Medical Robotics and Image-Guided Intervention","MRes","Surgery and Cancer","1Y FT","0"},
		{"97","http://www.ic.ac.uk/study/pg/nhli/medical-ultrasound/","Medical Ultrasound","MSc","National Heart and Lung Institute","1Y FT / 2Y PT","0"},
		{"98","http://www.ic.ac.uk/study/pg/earth-science/metals-energy-finance/","Metals and Energy Finance ","MSc","Earth Science and Engineering","1Y FT","0"},
		{"99","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Microbiome in Health and Disease","MRes","Surgery and Cancer","1Y FT","0"},
		{"100","http://www.ic.ac.uk/study/pg/life-sciences/molecular-cellular-biosciences/","Molecular and Cellular Biosciences","MRes","Life Sciences","1Y FT","0"},
		{"101","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Molecular Basis of Human Disease","MRes","Surgery and Cancer","1Y FT","0"},
		{"102","http://www.ic.ac.uk/study/pg/medicine/molecular-biology/","Molecular Biology and Pathology of Viruses","MSc","Medicine","1Y FT","0"},
		{"103","http://www.ic.ac.uk/study/pg/medicine/molecular-medicine/","Molecular Medicine","MSc","Medicine","1Y FT","0"},
		{"104","http://www.ic.ac.uk/study/pg/life-sciences/molecular-plant-microbial-sciences/","Molecular Plant and Microbial Sciences","MRes","Life Sciences","1Y FT","0"},
		{"105","http://www.ic.ac.uk/study/pg/chemistry/nanomaterials/","Nanomaterials","MRes","Chemistry","1Y FT","0"},
		{"106","http://www.ic.ac.uk/study/pg/bioengineering/neurotechnology-mres/","Neurotechnology","MRes","Bioengineering","1Y FT","0"},
		{"107","http://www.ic.ac.uk/study/pg/physics/optics-photonics/","Optics and Photonics","MSc","Physics","1Y FT / 2Y PT","0"},
		{"108","http://www.ic.ac.uk/study/pg/medicine/paediatrics-child-health/","Paediatrics and Child Health","PG Cert","Medicine","9 months PT","0"},
		{"109","http://www.ic.ac.uk/study/pg/medicine/paediatrics-child-health/","Paediatrics and Child Health","PG Dip","Medicine","2Y PT","0"},
		{"110","http://www.ic.ac.uk/study/pg/medicine/paediatrics-child-health/","Paediatrics and Child Health","MSc","Medicine","2Y PT / 3Y PT","0"},
		{"111","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Personalised Healthcare","MRes","Surgery and Cancer","1Y FT","0"},
		{"112","http://www.ic.ac.uk/study/pg/earth-science/petroleum-engineering/","Petroleum Engineering","MSc","Earth Science and Engineering","1Y FT","0"},
		{"113","http://www.ic.ac.uk/study/pg/earth-science/petroleum-geoscience/","Petroleum Geoscience","MSc","Earth Science and Engineering","1Y FT","0"},
		{"114","http://www.ic.ac.uk/study/pg/physics/photonics/","Photonics","MRes","Physics","1Y FT","0"},
		{"115","http://www.ic.ac.uk/study/pg/physics/physics/","Physics","MSc","Physics","1Y FT","0"},
		{"116","http://www.ic.ac.uk/study/pg/physics/physics-extended-research/","Physics with Extended Research","MSc","Physics","2Y FT","0"},
		{"117","http://www.ic.ac.uk/study/pg/physics/physics-nanophotonics/","Physics with Nanophotonics","MSc","Physics","1Y FT","0"},
		{"118","http://www.ic.ac.uk/study/pg/physics/shock-physics/","Physics with Shock Physics","MSc","Physics","1Y FT","0"},
		{"119","http://www.ic.ac.uk/study/pg/chemistry/plant-chemical-biology/","Plant Chemical Biology: Multidisciplinary Research for Next Generation Agri-Sciences","MRes","Chemistry","1Y FT / 2Y PT","0"},
		{"120","http://www.ic.ac.uk/study/pg/physics/plastic-electronic-materials/","Plastic Electronic Materials","MRes","Physics","1Y FT","0"},
		{"121","http://www.ic.ac.uk/study/pg/nhli/preventive-cardiology-certificate/","Preventive Cardiology","PG Cert","National Heart and Lung Institute","9 months PT","0"},
		{"122","http://www.ic.ac.uk/study/pg/nhli/preventive-cardiology-diploma/","Preventive Cardiology","PG Dip","National Heart and Lung Institute","2Y PT","0"},
		{"123","http://www.ic.ac.uk/study/pg/nhli/preventive-cardiology/","Preventive Cardiology","MSc","National Heart and Lung Institute","1Y FT / 2Y PT / 3Y PT","0"},
		{"124","http://www.ic.ac.uk/study/pg/chemical-engineering/process-automation/","Process Automation, Instrumentation and Control ","MSc","Chemical Engineering","2Y - 5Y PT","0"},
		{"125","http://www.ic.ac.uk/study/pg/chemical-engineering/process-automation/","Process Automation, Instrumentation and Control ","PG Dip","Chemical Engineering","1Y - 3Y PT","0"},
		{"126","http://www.ic.ac.uk/study/pg/chemical-engineering/process-automation/","Process Automation, Instrumentation and Control ","PG Cert","Chemical Engineering","6 months - 2Y PT","0"},
		{"127","http://www.ic.ac.uk/study/pg/public-health/public-health-masters/","Public Health","MPH","School of Public Health","1Y FT","0"},
		{"128","http://www.ic.ac.uk/study/pg/mathematics/pure-mathematics/","Pure Mathematics","MSc","Mathematics","1Y FT / 2Y PT","0"},
		{"129","http://www.ic.ac.uk/study/pg/physics/quantum-fields-fundamental-forces/","Quantum Fields and Fundamental Forces","MSc","Physics","1Y FT / 2Y PT","0"},
		{"130","http://www.ic.ac.uk/study/pg/surgery-and-cancer/reproductive-developmental-biology/","Reproductive and Developmental Biology","MSc","Surgery and Cancer","1Y FT","0"},
		{"131","http://www.ic.ac.uk/study/pg/surgery-and-cancer/reproductive-developmental-biology-certificate/","Reproductive and Developmental Biology","PG Cert","Surgery and Cancer","6 months FT","0"},
		{"132","http://www.ic.ac.uk/study/pg/surgery-and-cancer/biomedical-research/","Respiratory and Cardiovascular Science","MRes","Surgery and Cancer","1Y FT","0"},
		{"133","http://www.ic.ac.uk/study/pg/business-school/risk-management-financial-engineering/","Risk Management and Financial Engineering","MSc","Imperial College Business School","1Y FT","0"},
		{"134","http://www.ic.ac.uk/study/pg/science-communication/science-communication/","Science Communication","MSc","Science Communication Unit","1Y FT / 2Y PT","0"},
		{"135","http://www.ic.ac.uk/study/pg/science-communication/science-media-production/","Science Media Production","MSc","Science Communication Unit","1Y FT","0"},
		{"136","http://www.ic.ac.uk/study/pg/civil-engineering/soil-mechanics/","Soil Mechanics","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"137","http://www.ic.ac.uk/study/pg/civil-engineering/soil-mechanics/","Soil Mechanics and Business Management","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"138","http://www.ic.ac.uk/study/pg/civil-engineering/soil-mechanics/","Soil Mechanics and Engineering Seismology","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"139","http://www.ic.ac.uk/study/pg/civil-engineering/soil-mechanics/","Soil Mechanics and Environmental Geotechnics","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"140","http://www.ic.ac.uk/study/pg/civil-engineering/soil-mechanics/","Soil Mechanics and Sustainable Development","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"141","http://www.ic.ac.uk/study/pg/mathematics/statistics/","Statistics","MSc","Mathematics","1Y FT","0"},
		{"142","http://www.ic.ac.uk/study/pg/mathematics/stochastic-analysis/","Stochastic Analysis","MRes","Mathematics","1Y FT","0"},
		{"143","http://www.ic.ac.uk/study/pg/business-school/strategic-marketing/","Strategic Marketing","MSc","Imperial College Business School","1Y FT","0"},
		{"144","http://www.ic.ac.uk/study/pg/life-sciences/structural-molecular-biology/","Structural Molecular Biology","MRes","Life Sciences","1Y FT","0"},
		{"145","http://www.ic.ac.uk/study/pg/civil-engineering/structural-steel-design/","Structural Steel Design","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"146","http://www.ic.ac.uk/study/pg/civil-engineering/structural-steel-design/","Structural Steel Design and Business Management","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"147","http://www.ic.ac.uk/study/pg/civil-engineering/structural-steel-design/","Structural Steel Design and Sustainable Development","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"148","http://www.ic.ac.uk/study/pg/surgery-and-cancer/surgical-education/","Surgical Education","MEd","Surgery and Cancer","1Y FT / 2Y PT","0"},
		{"149","http://www.ic.ac.uk/study/pg/surgery-and-cancer/surgical-innovation/","Surgical Innovation","PG Dip","Surgery and Cancer","16 months PT","0"},
		{"150","http://www.ic.ac.uk/study/pg/surgery-and-cancer/surgical-innovation/","Surgical Innovation","MSc","Surgery and Cancer","2Y PT","0"},
		{"151","http://www.ic.ac.uk/study/pg/surgery-and-cancer/surgical-innovation/","Surgical Innovation","PG Cert","Surgery and Cancer","8 months PT","0"},
		{"152","http://www.ic.ac.uk/study/pg/surgery-and-cancer/surgical-science/","Surgical Science","MSc","Surgery and Cancer","1Y FT","0"},
		{"153","http://www.ic.ac.uk/study/pg/mechanical-engineering/sustainable-energy-futures/","Sustainable Energy Futures","MSc","Mechanical Engineering","1Y FT","0"},
		{"154","http://www.ic.ac.uk/study/pg/life-sciences/systems-synthetic-biology-mres/","Systems and Synthetic Biology","MRes","Life Sciences","1Y FT","0"},
		{"155","http://www.ic.ac.uk/study/pg/civil-engineering/systems-engineering-innovation/","Systems Engineering and Innovation","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT","0"},
		{"156","http://www.ic.ac.uk/study/pg/life-sciences/taxonomy-biodiversity/","Taxonomy and Biodiversity","MSc","Life Sciences","1Y FT / 2Y PT / 3Y PT","0"},
		{"157","http://www.ic.ac.uk/study/pg/physics/theory-simulation-materials/","Theory and Simulation of Materials","MSc","Physics","1Y FT","0"},
		{"158","http://www.ic.ac.uk/study/pg/medicine/translational-medicine/","Translational Medicine","MRes","Medicine","1Y FT / 2Y PT / 3Y PT","0"},
		{"159","http://www.ic.ac.uk/study/pg/civil-engineering/transport/","Transport","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"160","http://www.ic.ac.uk/study/pg/civil-engineering/transport/","Transport and Business Management","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"161","http://www.ic.ac.uk/study/pg/civil-engineering/transport/","Transport and Sustainable Development","MSc","Civil and Environmental Engineering","1Y FT / 2Y PT / 3Y PT","0"},
		{"162","http://www.ic.ac.uk/study/pg/life-sciences/tropical-forest-ecology/","Tropical Forest Ecology ","MRes","Life Sciences","1Y FT / 2Y PT","0"}
	};
}
