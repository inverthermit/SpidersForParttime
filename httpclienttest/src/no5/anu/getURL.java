package no5.anu;

import java.io.File;
import java.io.FileInputStream;

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

		//getProgram(); 
		getMajor();
		
	}
	
	public static void getMajor() throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("./anuMajor.html"));
        byte[] b=new byte[fis.available()];
        fis.read(b);
       fis.close();
        String htmls=new String(b);
       int count=1;
	    Parser	parser=Parser.createParser(htmls, "utf-8");
	    TagNameFilter trf=new TagNameFilter("tr");
	    NodeList nodest=parser.extractAllNodesThatMatch(trf);
	    if(nodest.size()>0)
	    {
	    	for(int i=0;i<nodest.size();i++)
	    	{
	    		parser=Parser.createParser(nodest.elementAt(i).toHtml(), "utf-8");
	    		TagNameFilter tdf=new TagNameFilter("td");
	    	    NodeList nodesd=parser.extractAllNodesThatMatch(tdf);
	    	    //System.out.println(nodesd.size());
	    	    if(nodesd.size()==4)
	    	    {
	    	    	String url="";
	    	    	String title=HTMLFilter(html2Str(nodesd.elementAt(1).toHtml()));
	    	    	String code=HTMLFilter(html2Str(nodesd.elementAt(0).toHtml()));
	    	    	String level=HTMLFilter(html2Str(nodesd.elementAt(2).toHtml()));
	    	    	parser=Parser.createParser(nodesd.elementAt(1).toHtml(), "utf-8");
	    	    	AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
	    	                   new HasAttributeFilter("href"));
	    	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	    	   	    for(int j=0;j<nodes4.size();j++)
	    	   	    {
	    	   	    	LinkTag link=(LinkTag)nodes4.elementAt(j);
	    	   	    	if(!link.getAttribute("href").equals("#"))
	    	   	    	{
	    	   	    		 url=link.getAttribute("href");
	    	                //title=HTMLFilter(html2Str(link.toHtml()));
	    	   	    	}
	    	   	    }
	    	   	System.out.println("{\""+count+"\",\"http://programsandcourses.anu.edu.au"+url+"\",\""+title+"\",\""+level+"\",\""+code+"\",\"0\"},");
 	               
	    	   	 count++;
	    	    }
	    	}
	    }
	}
	
	public static void getProgram() throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("./anuAll.html"));
        byte[] b=new byte[fis.available()];
        fis.read(b);
       fis.close();
        String htmls=new String(b);
       int count=1;
	    Parser	parser=Parser.createParser(htmls, "utf-8");
	    TagNameFilter trf=new TagNameFilter("tr");
	    NodeList nodest=parser.extractAllNodesThatMatch(trf);
	    if(nodest.size()>0)
	    {
	    	for(int i=0;i<nodest.size();i++)
	    	{
	    		parser=Parser.createParser(nodest.elementAt(i).toHtml(), "utf-8");
	    		TagNameFilter tdf=new TagNameFilter("td");
	    	    NodeList nodesd=parser.extractAllNodesThatMatch(tdf);
	    	    //System.out.println(nodesd.size());
	    	    if(nodesd.size()==6)
	    	    {
	    	    	String url="";
	    	    	String title=HTMLFilter(html2Str(nodesd.elementAt(1).toHtml()));
	    	    	String code=HTMLFilter(html2Str(nodesd.elementAt(0).toHtml()));
	    	    	String level=HTMLFilter(html2Str(nodesd.elementAt(3).toHtml()));
	    	    	String length=HTMLFilter(html2Str(nodesd.elementAt(5).toHtml()));
	    	    	parser=Parser.createParser(nodesd.elementAt(1).toHtml(), "utf-8");
	    	    	AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
	    	                   new HasAttributeFilter("href"));
	    	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	    	   	    for(int j=0;j<nodes4.size();j++)
	    	   	    {
	    	   	    	LinkTag link=(LinkTag)nodes4.elementAt(j);
	    	   	    	if(!link.getAttribute("href").equals("#"))
	    	   	    	{
	    	   	    		 url=link.getAttribute("href");
	    	                //title=HTMLFilter(html2Str(link.toHtml()));
	    	   	    	}
	    	   	    }
	    	   	System.out.println("{\""+count+"\",\"http://programsandcourses.anu.edu.au"+url+"\",\""+title+"\",\""+level+"\",\""+code+"\",\""+length+"\",\"0\"},");
 	               
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
	public static String[][] ProgramData1={
		{"1","http://programsandcourses.anu.edu.au/2016/program/1003XEXPRS","ANU Express","Non-award","1003XEXPRS","0.5","0"},
		{"2","http://programsandcourses.anu.edu.au/2016/program/1102XPREP","ANU Preparatory Program","Non-award","1102XPREP","0.5","0"},
		{"3","http://programsandcourses.anu.edu.au/2016/program/1100XNAWD","ANU Secondary College","Non-award","1100XNAWD","","0"},
		{"4","http://programsandcourses.anu.edu.au/2016/program/1006XSRS","ANU Summer Research Scholarship Program","Non-award","1006XSRS","1","0"},
		{"5","http://programsandcourses.anu.edu.au/2016/program/SASDE","Associate Degree","Undergraduate","SASDE","2","0"},
		{"6","http://programsandcourses.anu.edu.au/2016/program/BACCT","Bachelor of Accounting","Undergraduate","BACCT","3","0"},
		{"7","http://programsandcourses.anu.edu.au/2016/program/HACCT","Bachelor of Accounting (Honours)","Undergraduate","HACCT","1","0"},
		{"8","http://programsandcourses.anu.edu.au/2016/program/BACTS","Bachelor of Actuarial Studies","Undergraduate","BACTS","3","0"},
		{"9","http://programsandcourses.anu.edu.au/2016/program/HACTS","Bachelor of Actuarial Studies (Honours)","Undergraduate","HACTS","1","0"},
		{"10","http://programsandcourses.anu.edu.au/2016/program/AACOM","Bachelor of Advanced Computing (Honours)","Undergraduate","AACOM","4","0"}
	};
	public static String[][] ProgramData={
		{"1","http://programsandcourses.anu.edu.au/2016/program/1003XEXPRS","ANU Express","Non-award","1003XEXPRS","0.5","0"},
		{"2","http://programsandcourses.anu.edu.au/2016/program/1102XPREP","ANU Preparatory Program","Non-award","1102XPREP","0.5","0"},
		{"3","http://programsandcourses.anu.edu.au/2016/program/1100XNAWD","ANU Secondary College","Non-award","1100XNAWD","","0"},
		{"4","http://programsandcourses.anu.edu.au/2016/program/1006XSRS","ANU Summer Research Scholarship Program","Non-award","1006XSRS","1","0"},
		{"5","http://programsandcourses.anu.edu.au/2016/program/SASDE","Associate Degree","Undergraduate","SASDE","2","0"},
		{"6","http://programsandcourses.anu.edu.au/2016/program/BACCT","Bachelor of Accounting","Undergraduate","BACCT","3","0"},
		{"7","http://programsandcourses.anu.edu.au/2016/program/HACCT","Bachelor of Accounting (Honours)","Undergraduate","HACCT","1","0"},
		{"8","http://programsandcourses.anu.edu.au/2016/program/BACTS","Bachelor of Actuarial Studies","Undergraduate","BACTS","3","0"},
		{"9","http://programsandcourses.anu.edu.au/2016/program/HACTS","Bachelor of Actuarial Studies (Honours)","Undergraduate","HACTS","1","0"},
		{"10","http://programsandcourses.anu.edu.au/2016/program/AACOM","Bachelor of Advanced Computing (Honours)","Undergraduate","AACOM","4","0"},
		{"11","http://programsandcourses.anu.edu.au/2016/program/AACRD","Bachelor of Advanced Computing (Research and Development) (Honours)","Undergraduate","AACRD","4","0"},
		{"12","http://programsandcourses.anu.edu.au/2016/program/BAPRC","Bachelor of Archaeological Practice","Undergraduate","BAPRC","3","0"},
		{"13","http://programsandcourses.anu.edu.au/2016/program/HAPRC","Bachelor of Archaeological Practice (Honours)","Undergraduate","HAPRC","1","0"},
		{"14","http://programsandcourses.anu.edu.au/2016/program/BAHCR","Bachelor of Art History and Curatorship","Undergraduate","BAHCR","3","0"},
		{"15","http://programsandcourses.anu.edu.au/2016/program/HAHCR","Bachelor of Art History and Curatorship (Honours)","Undergraduate","HAHCR","1","0"},
		{"16","http://programsandcourses.anu.edu.au/2016/program/BARTS","Bachelor of Arts","Undergraduate","BARTS","3","0"},
		{"17","http://programsandcourses.anu.edu.au/2016/program/HART2","Bachelor of Arts (Honours)","Undergraduate","HART2","1","0"},
		{"18","http://programsandcourses.anu.edu.au/2016/program/HARTS","Bachelor of Arts (Honours)","Undergraduate","HARTS","1","0"},
		{"19","http://programsandcourses.anu.edu.au/2016/program/BARTVMIA","Bachelor of Arts/Master of International Affairs","Multiple","BARTVMIA","4","0"},
		{"20","http://programsandcourses.anu.edu.au/2016/program/BASIA","Bachelor of Asian Studies","Undergraduate","BASIA","3","0"},
		{"21","http://programsandcourses.anu.edu.au/2016/program/HASIA","Bachelor of Asian Studies (Honours)","Undergraduate","HASIA","1","0"},
		{"22","http://programsandcourses.anu.edu.au/2016/program/BASIVMGLOB","Bachelor of Asian Studies/Master of Globalisation","Multiple","BASIVMGLOB","4","0"},
		{"23","http://programsandcourses.anu.edu.au/2016/program/BAPYA","Bachelor of Asia-Pacific Studies (Year in Asia)","Undergraduate","BAPYA","4","0"},
		{"24","http://programsandcourses.anu.edu.au/2016/program/BBIOT","Bachelor of Biotechnology","Undergraduate","BBIOT","3","0"},
		{"25","http://programsandcourses.anu.edu.au/2016/program/HBIOT","Bachelor of Biotechnology (Honours)","Undergraduate","HBIOT","1","0"},
		{"26","http://programsandcourses.anu.edu.au/2016/program/BBUSA","Bachelor of Business Administration","Undergraduate","BBUSA","3","0"},
		{"27","http://programsandcourses.anu.edu.au/2016/program/HBUSA","Bachelor of Business Administration (Honours)","Undergraduate","HBUSA","1","0"},
		{"28","http://programsandcourses.anu.edu.au/2016/program/BCLAS","Bachelor of Classical Studies","Undergraduate","BCLAS","3","0"},
		{"29","http://programsandcourses.anu.edu.au/2016/program/HCLAS","Bachelor of Classical Studies (Honours)","Undergraduate","HCLAS","1","0"},
		{"30","http://programsandcourses.anu.edu.au/2016/program/BCOMM","Bachelor of Commerce","Undergraduate","BCOMM","3","0"},
		{"31","http://programsandcourses.anu.edu.au/2016/program/HCOMM","Bachelor of Commerce (Honours)","Undergraduate","HCOMM","1","0"},
		{"32","http://programsandcourses.anu.edu.au/2016/program/BCRIM","Bachelor of Criminology","Undergraduate","BCRIM","3","0"},
		{"33","http://programsandcourses.anu.edu.au/2016/program/HCRIM","Bachelor of Criminology (Honours)","Undergraduate","HCRIM","1","0"},
		{"34","http://programsandcourses.anu.edu.au/2016/program/BDESA","Bachelor of Design Arts","Undergraduate","BDESA","3","0"},
		{"35","http://programsandcourses.anu.edu.au/2016/program/HDESA","Bachelor of Design Arts (Honours)","Undergraduate","HDESA","1","0"},
		{"36","http://programsandcourses.anu.edu.au/2016/program/BDEVS","Bachelor of Development Studies","Undergraduate","BDEVS","3","0"},
		{"37","http://programsandcourses.anu.edu.au/2016/program/HDEVS","Bachelor of Development Studies (Honours)","Undergraduate","HDEVS","1","0"},
		{"38","http://programsandcourses.anu.edu.au/2016/program/BECON","Bachelor of Economics","Undergraduate","BECON","3","0"},
		{"39","http://programsandcourses.anu.edu.au/2016/program/HECON","Bachelor of Economics (Honours)","Undergraduate","HECON","1","0"},
		{"40","http://programsandcourses.anu.edu.au/2016/program/AENGI","Bachelor of Engineering (Honours)","Undergraduate","AENGI","4","0"},
		{"41","http://programsandcourses.anu.edu.au/2016/program/AENRD","Bachelor of Engineering (Research and Development) (Honours)","Undergraduate","AENRD","4","0"},
		{"42","http://programsandcourses.anu.edu.au/2016/program/BENVS","Bachelor of Environmental Studies","Undergraduate","BENVS","3","0"},
		{"43","http://programsandcourses.anu.edu.au/2016/program/HENVS","Bachelor of Environmental Studies (Honours)","Undergraduate","HENVS","1","0"},
		{"44","http://programsandcourses.anu.edu.au/2016/program/BEURO","Bachelor of European Studies","Undergraduate","BEURO","3","0"},
		{"45","http://programsandcourses.anu.edu.au/2016/program/HEURO","Bachelor of European Studies (Honours)","Undergraduate","HEURO","1","0"},
		{"46","http://programsandcourses.anu.edu.au/2016/program/BFINN","Bachelor of Finance","Undergraduate","BFINN","3","0"},
		{"47","http://programsandcourses.anu.edu.au/2016/program/HFINN","Bachelor of Finance (Honours)","Undergraduate","HFINN","1","0"},
		{"48","http://programsandcourses.anu.edu.au/2016/program/AFEST","Bachelor of Finance, Economics and Statistics (Honours)","Undergraduate","AFEST","4","0"},
		{"49","http://programsandcourses.anu.edu.au/2016/program/BGENE","Bachelor of Genetics","Undergraduate","BGENE","3","0"},
		{"50","http://programsandcourses.anu.edu.au/2016/program/HGENE","Bachelor of Genetics (Honours)","Undergraduate","HGENE","1","0"},
		{"51","http://programsandcourses.anu.edu.au/2016/program/BIT","Bachelor of Information Technology","Undergraduate","BIT","3","0"},
		{"52","http://programsandcourses.anu.edu.au/2016/program/HIT","Bachelor of Information Technology (Honours)","Undergraduate","HIT","1","0"},
		{"53","http://programsandcourses.anu.edu.au/2016/program/BISSU","Bachelor of Interdisciplinary Studies (Sustainability)","Undergraduate","BISSU","3","0"},
		{"54","http://programsandcourses.anu.edu.au/2016/program/HISSU","Bachelor of Interdisciplinary Studies (Sustainability) (Honours)","Undergraduate","HISSU","1","0"},
		{"55","http://programsandcourses.anu.edu.au/2016/program/AISSU","Bachelor of Interdisciplinary Studies (Sustainability) Advanced (Honours)","Undergraduate","AISSU","4","0"},
		{"56","http://programsandcourses.anu.edu.au/2016/program/BINBS","Bachelor of International Business","Undergraduate","BINBS","3","0"},
		{"57","http://programsandcourses.anu.edu.au/2016/program/HINBS","Bachelor of International Business (Honours)","Undergraduate","HINBS","1","0"},
		{"58","http://programsandcourses.anu.edu.au/2016/program/BIR","Bachelor of International Relations","Undergraduate","BIR","3","0"},
		{"59","http://programsandcourses.anu.edu.au/2016/program/HIR","Bachelor of International Relations (Honours)","Undergraduate","HIR","1","0"},
		{"60","http://programsandcourses.anu.edu.au/2016/program/BINSS","Bachelor of International Security Studies","Undergraduate","BINSS","3","0"},
		{"61","http://programsandcourses.anu.edu.au/2016/program/HINSS","Bachelor of International Security Studies (Honours)","Undergraduate","HINSS","1","0"},
		{"62","http://programsandcourses.anu.edu.au/2016/program/BLANG","Bachelor of Languages","Undergraduate","BLANG","3","0"},
		{"63","http://programsandcourses.anu.edu.au/2016/program/HLANG","Bachelor of Languages (Honours)","Undergraduate","HLANG","1","0"},
		{"64","http://programsandcourses.anu.edu.au/2016/program/BLAMS","Bachelor of Latin American Studies","Undergraduate","BLAMS","3","0"},
		{"65","http://programsandcourses.anu.edu.au/2016/program/HLAMS","Bachelor of Latin American Studies (Honours)","Undergraduate","HLAMS","1","0"},
		{"66","http://programsandcourses.anu.edu.au/2016/program/ALLB","Bachelor of Laws (Honours)","Undergraduate","ALLB","4","0"},
		{"67","http://programsandcourses.anu.edu.au/2016/program/BMASC","Bachelor of Mathematical Sciences","Undergraduate","BMASC","3","0"},
		{"68","http://programsandcourses.anu.edu.au/2016/program/BMASC","Bachelor of Mathematical Sciences","Undergraduate","BMASC","5","0"},
		{"69","http://programsandcourses.anu.edu.au/2016/program/BMEDS","Bachelor of Medical Science","Undergraduate","BMEDS","3","0"},
		{"70","http://programsandcourses.anu.edu.au/2016/program/HMEDS","Bachelor of Medical Science (Honours)","Undergraduate","HMEDS","1","0"},
		{"71","http://programsandcourses.anu.edu.au/2016/program/HMDSA","Bachelor of Medical Science (Honours)","Undergraduate","HMDSA","1","0"},
		{"72","http://programsandcourses.anu.edu.au/2016/program/BMECA","Bachelor of Middle Eastern and Central Asian Studies","Undergraduate","BMECA","3","0"},
		{"73","http://programsandcourses.anu.edu.au/2016/program/HMECA","Bachelor of Middle Eastern and Central Asian Studies (Honours)","Undergraduate","HMECA","1","0"},
		{"74","http://programsandcourses.anu.edu.au/2016/program/BMUSI","Bachelor of Music","Undergraduate","BMUSI","3","0"},
		{"75","http://programsandcourses.anu.edu.au/2016/program/HMUSI","Bachelor of Music (Honours)","Undergraduate","HMUSI","1","0"},
		{"76","http://programsandcourses.anu.edu.au/2016/program/BPAST","Bachelor of Pacific Studies","Undergraduate","BPAST","3","0"},
		{"77","http://programsandcourses.anu.edu.au/2016/program/APHAR","Bachelor of Philosophy (Honours) - Arts and Social Science","Undergraduate","APHAR","4","0"},
		{"78","http://programsandcourses.anu.edu.au/2016/program/APASP","Bachelor of Philosophy (Honours) - Asia and the Pacific","Undergraduate","APASP","4","0"},
		{"79","http://programsandcourses.anu.edu.au/2016/program/APHSC","Bachelor of Philosophy (Honours) - Science","Undergraduate","APHSC","4","0"},
		{"80","http://programsandcourses.anu.edu.au/2016/program/APNAR","Bachelor of Philosophy (Honours) / Bachelor of  Arts (Honours)","Undergraduate","APNAR","4","0"},
		{"81","http://programsandcourses.anu.edu.au/2016/program/APNSC","Bachelor of Philosophy (Honours) / Bachelor of Science (Honours) - ANU as home institution","Undergraduate","APNSC","4","0"},
		{"82","http://programsandcourses.anu.edu.au/2016/program/APNSN","Bachelor of Philosophy (Honours) / Bachelor of Science (Honours) - NUS as home institution","Undergraduate","APNSN","4","0"},
		{"83","http://programsandcourses.anu.edu.au/2016/program/BPOLS","Bachelor of Policy Studies","Undergraduate","BPOLS","3","0"},
		{"84","http://programsandcourses.anu.edu.au/2016/program/HPOLS","Bachelor of Policy Studies (Honours)","Undergraduate","HPOLS","1","0"},
		{"85","http://programsandcourses.anu.edu.au/2016/program/BPLSC","Bachelor of Political Science","Undergraduate","BPLSC","3","0"},
		{"86","http://programsandcourses.anu.edu.au/2016/program/HPLSC","Bachelor of Political Science (Honours)","Undergraduate","HPLSC","1","0"},
		{"87","http://programsandcourses.anu.edu.au/2016/program/BPPE","Bachelor of Politics, Philosophy and Economics","Undergraduate","BPPE","3","0"},
		{"88","http://programsandcourses.anu.edu.au/2016/program/HPPE","Bachelor of Politics, Philosophy and Economics (Honours)","Undergraduate","HPPE","1","0"},
		{"89","http://programsandcourses.anu.edu.au/2016/program/BPPEVMJD","Bachelor of Politics, Philosophy and Economics/Juris Doctor","Multiple","BPPEVMJD","5.5","0"},
		{"90","http://programsandcourses.anu.edu.au/2016/program/APSYC","Bachelor of Psychology (Honours)","Undergraduate","APSYC","4","0"},
		{"91","http://programsandcourses.anu.edu.au/2016/program/BSC","Bachelor of Science","Undergraduate","BSC","3","0"},
		{"92","http://programsandcourses.anu.edu.au/2016/program/ASCAD","Bachelor of Science (Advanced) (Honours)","Undergraduate","ASCAD","4","0"},
		{"93","http://programsandcourses.anu.edu.au/2016/program/BSFOR","Bachelor of Science (Forest Sciences)","Undergraduate","BSFOR","3","0"},
		{"94","http://programsandcourses.anu.edu.au/2016/program/HSFOR","Bachelor of Science (Forest Sciences) (Honours)","Undergraduate","HSFOR","1","0"},
		{"95","http://programsandcourses.anu.edu.au/2016/program/HSC","Bachelor of Science (Honours)","Undergraduate","HSC","1","0"},
		{"96","http://programsandcourses.anu.edu.au/2016/program/BSPSY","Bachelor of Science (Psychology)","Undergraduate","BSPSY","3","0"},
		{"97","http://programsandcourses.anu.edu.au/2016/program/HSPSY","Bachelor of Science (Psychology) (Honours)","Undergraduate","HSPSY","1","0"},
		{"98","http://programsandcourses.anu.edu.au/2016/program/BSREM","Bachelor of Science (Resource and Environmental Management)","Undergraduate","BSREM","3","0"},
		{"99","http://programsandcourses.anu.edu.au/2016/program/HSREM","Bachelor of Science (Resource and Environmental Management) (Honours)","Undergraduate","HSREM","1","0"},
		{"100","http://programsandcourses.anu.edu.au/2016/program/ASSAE","Bachelor of Social Sciences (Honours in Actuarial Studies and Economics)","Undergraduate","ASSAE","4","0"},
		{"101","http://programsandcourses.anu.edu.au/2016/program/ASENG","Bachelor of Software Engineering (Honours)","Undergraduate","ASENG","4","0"},
		{"102","http://programsandcourses.anu.edu.au/2016/program/BSTAT","Bachelor of Statistics","Undergraduate","BSTAT","3","0"},
		{"103","http://programsandcourses.anu.edu.au/2016/program/HSTAT","Bachelor of Statistics (Honours)","Undergraduate","HSTAT","1","0"},
		{"104","http://programsandcourses.anu.edu.au/2016/program/BSTUD","Bachelor of Studies","Undergraduate","BSTUD","3","0"},
		{"105","http://programsandcourses.anu.edu.au/2016/program/BVART","Bachelor of Visual Arts","Undergraduate","BVART","3","0"},
		{"106","http://programsandcourses.anu.edu.au/2016/program/HVART","Bachelor of Visual Arts (Honours)","Undergraduate","HVART","1","0"},
		{"107","http://programsandcourses.anu.edu.au/2016/program/5097XNAWD","Cross Institutional Research","Non-award","5097XNAWD","","0"},
		{"108","http://programsandcourses.anu.edu.au/2016/program/EADST","Diploma of Advanced Studies","Undergraduate","EADST","1","0"},
		{"109","http://programsandcourses.anu.edu.au/2016/program/ECOMP","Diploma of Computing","Undergraduate","ECOMP","1","0"},
		{"110","http://programsandcourses.anu.edu.au/2016/program/ELANG","Diploma of Languages","Undergraduate","ELANG","1","0"},
		{"111","http://programsandcourses.anu.edu.au/2016/program/ELIBS","Diploma of Liberal Studies","Undergraduate","ELIBS","1","0"},
		{"112","http://programsandcourses.anu.edu.au/2016/program/9031XSJD","Doctor of Juridical Science (SJD), ANU College of Law","Research","9031XSJD","4","0"},
		{"113","http://programsandcourses.anu.edu.au/2016/program/9300XLLD","Doctor of Laws","Research","9300XLLD","","0"},
		{"114","http://programsandcourses.anu.edu.au/2016/program/9101XLITTD","Doctor of Letters","Research","9101XLITTD","","0"},
		{"115","http://programsandcourses.anu.edu.au/2016/program/9911XMD","Doctor of Medicine","Research","9911XMD","","0"},
		{"116","http://programsandcourses.anu.edu.au/2016/program/8950XMCHD","Doctor of Medicine and Surgery","Graduate","8950XMCHD","4","0"},
		{"117","http://programsandcourses.anu.edu.au/2016/program/9000XPHD","Doctor of Philosophy","Research","9000XPHD","4","0"},
		{"118","http://programsandcourses.anu.edu.au/2016/program/9064XCLPSY","Doctor of Philosophy (Clinical Psychology)","Research","9064XCLPSY","4","0"},
		{"119","http://programsandcourses.anu.edu.au/2016/program/9040XPHD","Doctor of Philosophy, ANU College of Business and Economics","Research","9040XPHD","4","0"},
		{"120","http://programsandcourses.anu.edu.au/2016/program/9070XPHD","Doctor of Philosophy, ANU College of Engineering and Computer Science","Research","9070XPHD","4","0"},
		{"121","http://programsandcourses.anu.edu.au/2016/program/9030XPHD","Doctor of Philosophy, ANU College of Law","Research","9030XPHD","4","0"},
		{"122","http://programsandcourses.anu.edu.au/2016/program/9062XPHD","Doctor of Philosophy, ANU Colleges of Science (Joint Degree ANU-NUS)","Research","9062XPHD","4","0"},
		{"123","http://programsandcourses.anu.edu.au/2016/program/9072XNTNU","Doctor of Philosophy, ANU/NTNU","Research","9072XNTNU","4","0"},
		{"124","http://programsandcourses.anu.edu.au/2016/program/9513XPHD","Doctor of Philosophy, Australian Centre on China in the World","Research","9513XPHD","4","0"},
		{"125","http://programsandcourses.anu.edu.au/2016/program/9540XPHD","Doctor of Philosophy, Crawford School of Public Policy","Research","9540XPHD","4","0"},
		{"126","http://programsandcourses.anu.edu.au/2016/program/9050XPHD","Doctor of Philosophy, Culture History and Languages","Research","9050XPHD","4","0"},
		{"127","http://programsandcourses.anu.edu.au/2016/program/9065XPHD","Doctor of Philosophy, Fenner School of Environment and Society","Research","9065XPHD","4","0"},
		{"128","http://programsandcourses.anu.edu.au/2016/program/9510XPHD","Doctor of Philosophy, International, Political and Strategic Studies","Research","9510XPHD","4","0"},
		{"129","http://programsandcourses.anu.edu.au/2016/program/9710XPHD","Doctor of Philosophy, John Curtin School of Medical Research","Research","9710XPHD","4","0"},
		{"130","http://programsandcourses.anu.edu.au/2016/program/9650XPHD","Doctor of Philosophy, Mathematical Sciences Institute","Research","9650XPHD","4","0"},
		{"131","http://programsandcourses.anu.edu.au/2016/program/9910XPHD","Doctor of Philosophy, Medical School","Research","9910XPHD","4","0"},
		{"132","http://programsandcourses.anu.edu.au/2016/program/9350XPHD","Doctor of Philosophy, National Centre for Indigenous Studies","Research","9350XPHD","4","0"},
		{"133","http://programsandcourses.anu.edu.au/2016/program/9850XPHD","Doctor of Philosophy, National Security College","Research","9850XPHD","4","0"},
		{"134","http://programsandcourses.anu.edu.au/2016/program/9723XPHD","Doctor of Philosophy, Psychology","Research","9723XPHD","4","0"},
		{"135","http://programsandcourses.anu.edu.au/2016/program/9560XPHD","Doctor of Philosophy, Regulation, Justice and Diplomacy","Research","9560XPHD","4","0"},
		{"136","http://programsandcourses.anu.edu.au/2016/program/9670XPHD","Doctor of Philosophy, Research School of Astronomy and Astrophysics","Research","9670XPHD","4","0"},
		{"137","http://programsandcourses.anu.edu.au/2016/program/9066XPHD","Doctor of Philosophy, Research School of Biology","Research","9066XPHD","4","0"},
		{"138","http://programsandcourses.anu.edu.au/2016/program/9620XPHD","Doctor of Philosophy, Research School of Chemistry","Research","9620XPHD","4","0"},
		{"139","http://programsandcourses.anu.edu.au/2016/program/9640XPHD","Doctor of Philosophy, Research School of Earth Sciences","Research","9640XPHD","4","0"},
		{"140","http://programsandcourses.anu.edu.au/2016/program/9552XPHD","Doctor of Philosophy, Research School of Humanities and the Arts","Research","9552XPHD","4","0"},
		{"141","http://programsandcourses.anu.edu.au/2016/program/9680XPHD","Doctor of Philosophy, Research School of Physics and Engineering","Research","9680XPHD","4","0"},
		{"142","http://programsandcourses.anu.edu.au/2016/program/9602XPHD","Doctor of Philosophy, Research School of Population Health","Research","9602XPHD","4","0"},
		{"143","http://programsandcourses.anu.edu.au/2016/program/9520XPHD","Doctor of Philosophy, Research School of Social Sciences","Research","9520XPHD","4","0"},
		{"144","http://programsandcourses.anu.edu.au/2016/program/9060XPHD","Doctor of Philosophy, Science","Research","9060XPHD","4","0"},
		{"145","http://programsandcourses.anu.edu.au/2016/program/9063XDPSYC","Doctor of Psychology (Clinical)","Research","9063XDPSYC","3","0"},
		{"146","http://programsandcourses.anu.edu.au/2016/program/9600XDSCI","Doctor of Science","Research","9600XDSCI","","0"},
		{"147","http://programsandcourses.anu.edu.au/2016/program/MEMPA","Executive Master of Public Administration","Graduate","MEMPA","1.5","0"},
		{"148","http://programsandcourses.anu.edu.au/2016/program/4050FDD","Flexible Double Degree","Undergraduate","4050FDD","4","0"},
		{"149","http://programsandcourses.anu.edu.au/2016/program/4750FDD","Flexible Double Degree","Undergraduate","4750FDD","5","0"},
		{"150","http://programsandcourses.anu.edu.au/2016/program/4350FDD","Flexible Double Degree – Law (Honours)","Undergraduate","4350FDD","5","0"},
		{"151","http://programsandcourses.anu.edu.au/2016/program/7050FDM","Flexible Double Masters","Graduate","7050FDM","3","0"},
		{"152","http://programsandcourses.anu.edu.au/2016/program/5036XGSPP","Global Summer Program (Postgraduate)","Non-award","5036XGSPP","","0"},
		{"153","http://programsandcourses.anu.edu.au/2016/program/1305XGSPU","Global Summer Program (Undergraduate)","Non-award","1305XGSPU","","0"},
		{"154","http://programsandcourses.anu.edu.au/2016/program/5082XCRWFD","Graduate Bridging Program (Crawford School of Public Policy)","Non-award","5082XCRWFD","","0"},
		{"155","http://programsandcourses.anu.edu.au/2016/program/5082SEMDV","Graduate Bridging Program (Environmental Management and Development)","Non-award","5082SEMDV","","0"},
		{"156","http://programsandcourses.anu.edu.au/2016/program/5082SIDEC","Graduate Bridging Program (National Centre for Development Studies)","Non-award","5082SIDEC","","0"},
		{"157","http://programsandcourses.anu.edu.au/2016/program/5082SPOGO","Graduate Bridging Program (Policy and Governance)","Non-award","5082SPOGO","","0"},
		{"158","http://programsandcourses.anu.edu.au/2016/program/6353XGCMGL","Graduate Certificate in Australian Migration Law and Practice","Graduate","6353XGCMGL","0.5","0"},
		{"159","http://programsandcourses.anu.edu.au/2016/program/6459XGCACC","Graduate Certificate of Accounting","Graduate","6459XGCACC","0.5","0"},
		{"160","http://programsandcourses.anu.edu.au/2016/program/CADAN","Graduate Certificate of Applied Data Analytics","Graduate","CADAN","0.5","0"},
		{"161","http://programsandcourses.anu.edu.au/2016/program/6619XGCAPE","Graduate Certificate of Applied Epidemiology","Graduate","6619XGCAPE","0.5","0"},
		{"162","http://programsandcourses.anu.edu.au/2016/program/CARTS","Graduate Certificate of Arts","Graduate","CARTS","0.5","0"},
		{"163","http://programsandcourses.anu.edu.au/2016/program/6462XGCBIM","Graduate Certificate of Business Information Management","Graduate","6462XGCBIM","0.5","0"},
		{"164","http://programsandcourses.anu.edu.au/2016/program/CECON","Graduate Certificate of Economics","Graduate","CECON","0.5","0"},
		{"165","http://programsandcourses.anu.edu.au/2016/program/6659XGCENV","Graduate Certificate of Environment","Graduate","6659XGCENV","0.5","0"},
		{"166","http://programsandcourses.anu.edu.au/2016/program/CFAS","Graduate Certificate of Finance and Actuarial Statistics","Graduate","CFAS","0.5","0"},
		{"167","http://programsandcourses.anu.edu.au/2016/program/CLAW","Graduate Certificate of Law","Graduate","CLAW","0.5","0"},
		{"168","http://programsandcourses.anu.edu.au/2016/program/6860XGCMGT","Graduate Certificate of Management","Graduate","6860XGCMGT","0.5","0"},
		{"169","http://programsandcourses.anu.edu.au/2016/program/6829XGCMDS","Graduate Certificate of Military and Defence Studies","Graduate","6829XGCMDS","0.5","0"},
		{"170","http://programsandcourses.anu.edu.au/2016/program/CMILL","Graduate Certificate of Military Law","Graduate","CMILL","0.5","0"},
		{"171","http://programsandcourses.anu.edu.au/2016/program/CPUBH","Graduate Certificate of Public Health","Graduate","CPUBH","0.5","0"},
		{"172","http://programsandcourses.anu.edu.au/2016/program/6192XGCPPP","Graduate Certificate of Public Policy","Graduate","6192XGCPPP","0.5","0"},
		{"173","http://programsandcourses.anu.edu.au/2016/program/CSCIE","Graduate Certificate of Science","Graduate","CSCIE","0.5","0"},
		{"174","http://programsandcourses.anu.edu.au/2016/program/CSTUD","Graduate Certificate of Studies","Graduate","CSTUD","0.5","0"},
		{"175","http://programsandcourses.anu.edu.au/2016/program/CSTOL","Graduate Certificate of Studies - Online","Graduate","CSTOL","0.5","0"},
		{"176","http://programsandcourses.anu.edu.au/2016/program/6506XGCTA","Graduate Certificate of Teaching Asia","Graduate","6506XGCTA","0.5","0"},
		{"177","http://programsandcourses.anu.edu.au/2016/program/6401XGDACT","Graduate Diploma of Accounting","Graduate","6401XGDACT","1","0"},
		{"178","http://programsandcourses.anu.edu.au/2016/program/DADAN","Graduate Diploma of Applied Data Analytics","Graduate","DADAN","1","0"},
		{"179","http://programsandcourses.anu.edu.au/2016/program/DBISY","Graduate Diploma of Business Information Systems","Graduate","DBISY","1","0"},
		{"180","http://programsandcourses.anu.edu.au/2016/program/6706XGDCP","Graduate Diploma of Computing","Graduate","6706XGDCP","1","0"},
		{"181","http://programsandcourses.anu.edu.au/2016/program/DECON","Graduate Diploma of Economics","Graduate","DECON","1","0"},
		{"182","http://programsandcourses.anu.edu.au/2016/program/DENVI","Graduate Diploma of Environment","Graduate","DENVI","1","0"},
		{"183","http://programsandcourses.anu.edu.au/2016/program/DFAS","Graduate Diploma of Finance and Actuarial Statistics","Graduate","DFAS","1","0"},
		{"184","http://programsandcourses.anu.edu.au/2016/program/DIAFF","Graduate Diploma of International Affairs","Graduate","DIAFF","1","0"},
		{"185","http://programsandcourses.anu.edu.au/2016/program/6303XGDLP","Graduate Diploma of Legal Practice","Graduate","6303XGDLP","1","0"},
		{"186","http://programsandcourses.anu.edu.au/2016/program/6828XGDMDS","Graduate Diploma of Military and Defence Studies","Graduate","6828XGDMDS","1","0"},
		{"187","http://programsandcourses.anu.edu.au/2016/program/DMILL","Graduate Diploma of Military Law","Graduate","DMILL","1","0"},
		{"188","http://programsandcourses.anu.edu.au/2016/program/DPUBH","Graduate Diploma of Public Health","Graduate","DPUBH","1","0"},
		{"189","http://programsandcourses.anu.edu.au/2016/program/DPUBP","Graduate Diploma of Public Policy","Graduate","DPUBP","1","0"},
		{"190","http://programsandcourses.anu.edu.au/2016/program/6600XGDSCI","Graduate Diploma of Science","Graduate","6600XGDSCI","1","0"},
		{"191","http://programsandcourses.anu.edu.au/2016/program/DSTUD","Graduate Diploma of Studies","Graduate","DSTUD","1","0"},
		{"192","http://programsandcourses.anu.edu.au/2016/program/5034XNAWD","Graduate Exchange Program","Non-award","5034XNAWD","","0"},
		{"193","http://programsandcourses.anu.edu.au/2016/program/5031XNAWD","Graduate Exchange Program","Non-award","5031XNAWD","","0"},
		{"194","http://programsandcourses.anu.edu.au/2016/program/5050XNAWD","Graduate Non-Award","Non-award","5050XNAWD","","0"},
		{"195","http://programsandcourses.anu.edu.au/2016/program/5110XNAWD","Graduate Non-Award (ANU College of Arts and Social Sciences)","Non-award","5110XNAWD","","0"},
		{"196","http://programsandcourses.anu.edu.au/2016/program/5140XNAWD","Graduate Non-Award (ANU College of Business & Economics)","Non-award","5140XNAWD","","0"},
		{"197","http://programsandcourses.anu.edu.au/2016/program/5130XNAWD","Graduate Non-Award (ANU College of Law)","Non-award","5130XNAWD","","0"},
		{"198","http://programsandcourses.anu.edu.au/2016/program/5160XNAWD","Graduate Non-Award (ANU Colleges of Science)","Non-award","5160XNAWD","","0"},
		{"199","http://programsandcourses.anu.edu.au/2016/program/5170XNAWD","Graduate Non-Award (College of Engineering and Computer Science)","Non-award","5170XNAWD","","0"},
		{"200","http://programsandcourses.anu.edu.au/2016/program/5061XNAWD","Graduate Non-Award (Examination)","Non-award","5061XNAWD","","0"},
		{"201","http://programsandcourses.anu.edu.au/2016/program/5132XNAWD","Graduate Non-Award (Legal Workshop)","Non-award","5132XNAWD","","0"},
		{"202","http://programsandcourses.anu.edu.au/2016/program/5058XCEPH","Graduate Non-Award (National Centre for Epidemiology and Population Health)","Non-award","5058XCEPH","","0"},
		{"203","http://programsandcourses.anu.edu.au/2016/program/5092XNAWD","Graduate Non-Award (Research)","Non-award","5092XNAWD","","0"},
		{"204","http://programsandcourses.anu.edu.au/2016/program/5150XNAWD","Graduate Non-Award (School of Culture, History and Language)","Non-award","5150XNAWD","","0"},
		{"205","http://programsandcourses.anu.edu.au/2016/program/5191XCMHS","Graduate Non-Award Cross Institutional (ANU College of Medicine Biology and Environment)","Non-award","5191XCMHS","","0"},
		{"206","http://programsandcourses.anu.edu.au/2016/program/5051XNAWD","Graduate Non-Award Cross-Institutional","Non-award","5051XNAWD","","0"},
		{"207","http://programsandcourses.anu.edu.au/2016/program/5111XARTS","Graduate Non-Award Cross-Institutional (ANU College of Arts and Social Sciences)","Non-award","5111XARTS","","0"},
		{"208","http://programsandcourses.anu.edu.au/2016/program/5151XCAP","Graduate Non-Award Cross-Institutional (ANU College of Asia and the Pacific)","Non-award","5151XCAP","","0"},
		{"209","http://programsandcourses.anu.edu.au/2016/program/5141XCBE","Graduate Non-Award Cross-Institutional (ANU College of Business and Economics)","Non-award","5141XCBE","","0"},
		{"210","http://programsandcourses.anu.edu.au/2016/program/5172XCECS","Graduate Non-Award Cross-Institutional (ANU College of Engineering and Computer Science)","Non-award","5172XCECS","","0"},
		{"211","http://programsandcourses.anu.edu.au/2016/program/5131XNAWD","Graduate Non-Award Cross-Institutional (ANU College of Law)","Non-award","5131XNAWD","","0"},
		{"212","http://programsandcourses.anu.edu.au/2016/program/5133XCOL","Graduate Non-Award Cross-Institutional (ANU College of Law)","Non-award","5133XCOL","","0"},
		{"213","http://programsandcourses.anu.edu.au/2016/program/5162XCOS","Graduate Non-Award Cross-Institutional (ANU Colleges of Science)","Non-award","5162XCOS","","0"},
		{"214","http://programsandcourses.anu.edu.au/2016/program/5095XNAWD","Graduate Non-Award Examination","Non-award","5095XNAWD","","0"},
		{"215","http://programsandcourses.anu.edu.au/2016/program/5179XNSC","Graduate Non-Award, National Security College","Non-award","5179XNSC","","0"},
		{"216","http://programsandcourses.anu.edu.au/2016/program/5030XNAWD","Graduate Study Abroad Program","Non-award","5030XNAWD","","0"},
		{"217","http://programsandcourses.anu.edu.au/2016/program/7504XMSNUS","Joint Degree Program ANU-NUS - Master of Arts (Southeast Asian Studies)","Graduate","7504XMSNUS","1","0"},
		{"218","http://programsandcourses.anu.edu.au/2016/program/5035XJOINT","Joint Degree Program National University Singapore -The Australian National University","Non-award","5035XJOINT","","0"},
		{"219","http://programsandcourses.anu.edu.au/2016/program/5035SMSEAS","Joint Degree Program National University Singapore -The Australian National University (South East Asian Studies)","Non-award","5035SMSEAS","","0"},
		{"220","http://programsandcourses.anu.edu.au/2016/program/5171XPHD","Joint Doctor of Philosophy, The Australian National University - National University Singapore","Non-award","5171XPHD","","0"},
		{"221","http://programsandcourses.anu.edu.au/2016/program/5155SMAPS","Joint Postgraduate Program  National University  Singapore - The Australian National University (Asia-Pacific Studies)","Non-award","5155SMAPS","","0"},
		{"222","http://programsandcourses.anu.edu.au/2016/program/MJD","Juris Doctor","Graduate","MJD","3","0"},
		{"223","http://programsandcourses.anu.edu.au/2016/program/MJDOL","Juris Doctor","Graduate","MJDOL","0.5","0"},
		{"224","http://programsandcourses.anu.edu.au/2016/program/7330HJD","Juris Doctor (Honours)","Graduate","7330HJD","3","0"},
		{"225","http://programsandcourses.anu.edu.au/2016/program/7414XMACCT","Master of Accounting","Graduate","7414XMACCT","2","0"},
		{"226","http://programsandcourses.anu.edu.au/2016/program/7420XMACTP","Master of Actuarial Practice","Graduate","7420XMACTP","2.5","0"},
		{"227","http://programsandcourses.anu.edu.au/2016/program/7410XMACTS","Master of Actuarial Studies","Graduate","7410XMACTS","2","0"},
		{"228","http://programsandcourses.anu.edu.au/2016/program/MANTH","Master of Anthropology","Graduate","MANTH","2","0"},
		{"229","http://programsandcourses.anu.edu.au/2016/program/VANTH","Master of Anthropology (Advanced)","Graduate","VANTH","2","0"},
		{"230","http://programsandcourses.anu.edu.au/2016/program/MAAPD","Master of Applied Anthropology and Participatory Development","Graduate","MAAPD","2","0"},
		{"231","http://programsandcourses.anu.edu.au/2016/program/MAAOL","Master of Applied Anthropology and Participatory Development - Online","Graduate","MAAOL","2","0"},
		{"232","http://programsandcourses.anu.edu.au/2016/program/VAAPD","Master of Applied Anthropology and Participatory Development (Advanced)","Graduate","VAAPD","2","0"},
		{"233","http://programsandcourses.anu.edu.au/2016/program/VAAOL","Master of Applied Anthropology and Participatory Development (Advanced) - Online","Graduate","VAAOL","2","0"},
		{"234","http://programsandcourses.anu.edu.au/2016/program/MADAN","Master of Applied Data Analytics","Graduate","MADAN","1.5","0"},
		{"235","http://programsandcourses.anu.edu.au/2016/program/MAPEC","Master of Applied Economics","Graduate","MAPEC","2","0"},
		{"236","http://programsandcourses.anu.edu.au/2016/program/7421XMAPFN","Master of Applied Finance","Graduate","7421XMAPFN","1.5","0"},
		{"237","http://programsandcourses.anu.edu.au/2016/program/MARSC","Master of Archaeological Science","Graduate","MARSC","2","0"},
		{"238","http://programsandcourses.anu.edu.au/2016/program/VARSC","Master of Archaeological Science (Advanced)","Graduate","VARSC","2","0"},
		{"239","http://programsandcourses.anu.edu.au/2016/program/MAHCS","Master of Art History and Curatorial Studies","Graduate","MAHCS","2","0"},
		{"240","http://programsandcourses.anu.edu.au/2016/program/VAHCS","Master of Art History and Curatorial Studies (Advanced)","Graduate","VAHCS","2","0"},
		{"241","http://programsandcourses.anu.edu.au/2016/program/VARTS","Master of Arts (Advanced)","Graduate","VARTS","2","0"},
		{"242","http://programsandcourses.anu.edu.au/2016/program/MAPST","Master of Asia-Pacific Studies","Graduate","MAPST","2","0"},
		{"243","http://programsandcourses.anu.edu.au/2016/program/VASTP","Master of Astronomy and Astrophysics (Advanced)","Graduate","VASTP","2","0"},
		{"244","http://programsandcourses.anu.edu.au/2016/program/MBIAN","Master of Biological Anthropology","Graduate","MBIAN","2","0"},
		{"245","http://programsandcourses.anu.edu.au/2016/program/VBIAN","Master of Biological Anthropology (Advanced)","Graduate","VBIAN","2","0"},
		{"246","http://programsandcourses.anu.edu.au/2016/program/MBIOS","Master of Biological Sciences","Graduate","MBIOS","2","0"},
		{"247","http://programsandcourses.anu.edu.au/2016/program/VBIOS","Master of Biological Sciences (Advanced)","Graduate","VBIOS","2","0"},
		{"248","http://programsandcourses.anu.edu.au/2016/program/MBIOT","Master of Biotechnology","Graduate","MBIOT","2","0"},
		{"249","http://programsandcourses.anu.edu.au/2016/program/VBIOT","Master of Biotechnology (Advanced)","Graduate","VBIOT","2","0"},
		{"250","http://programsandcourses.anu.edu.au/2016/program/7810XMBA","Master of Business Administration","Graduate","7810XMBA","2","0"},
		{"251","http://programsandcourses.anu.edu.au/2016/program/7417XMBIS","Master of Business Information Systems","Graduate","7417XMBIS","2","0"},
		{"252","http://programsandcourses.anu.edu.au/2016/program/MCLAS","Master of Classical Studies","Graduate","MCLAS","2","0"},
		{"253","http://programsandcourses.anu.edu.au/2016/program/VCLAS","Master of Classical Studies (Advanced)","Graduate","VCLAS","2","0"},
		{"254","http://programsandcourses.anu.edu.au/2016/program/MCLCH","Master of Climate Change","Graduate","MCLCH","2","0"},
		{"255","http://programsandcourses.anu.edu.au/2016/program/7601XMCPSY","Master of Clinical Psychology","Graduate","7601XMCPSY","2","0"},
		{"256","http://programsandcourses.anu.edu.au/2016/program/7412XMCOM","Master of Commerce","Graduate","7412XMCOM","2","0"},
		{"257","http://programsandcourses.anu.edu.au/2016/program/7706XMCOMP","Master of Computing","Graduate","7706XMCOMP","2","0"},
		{"258","http://programsandcourses.anu.edu.au/2016/program/VCOMP","Master of Computing (Advanced)","Graduate","VCOMP","2","0"},
		{"259","http://programsandcourses.anu.edu.au/2016/program/VCHAM","Master of Culture Health and Medicine (Advanced)","Graduate","VCHAM","2","0"},
		{"260","http://programsandcourses.anu.edu.au/2016/program/MCHAM","Master of Culture, Health and Medicine","Graduate","MCHAM","2","0"},
		{"261","http://programsandcourses.anu.edu.au/2016/program/MDIGA","Master of Digital Arts","Graduate","MDIGA","2","0"},
		{"262","http://programsandcourses.anu.edu.au/2016/program/VDIGA","Master of Digital Arts (Advanced)","Graduate","VDIGA","2","0"},
		{"263","http://programsandcourses.anu.edu.au/2016/program/MDHPC","Master of Digital Humanities and Public Culture","Graduate","MDHPC","2","0"},
		{"264","http://programsandcourses.anu.edu.au/2016/program/VDHPC","Master of Digital Humanities and Public Culture (Advanced)","Graduate","VDHPC","2","0"},
		{"265","http://programsandcourses.anu.edu.au/2016/program/MDIPL","Master of Diplomacy","Graduate","MDIPL","2","0"},
		{"266","http://programsandcourses.anu.edu.au/2016/program/VDIPL","Master of Diplomacy (Advanced)","Graduate","VDIPL","2","0"},
		{"267","http://programsandcourses.anu.edu.au/2016/program/VEASC","Master of Earth Sciences (Advanced)","Graduate","VEASC","2","0"},
		{"268","http://programsandcourses.anu.edu.au/2016/program/MECPO","Master of Economic Policy","Graduate","MECPO","2","0"},
		{"269","http://programsandcourses.anu.edu.au/2016/program/MECON","Master of Economics","Graduate","MECON","2","0"},
		{"270","http://programsandcourses.anu.edu.au/2016/program/5072XMECQ","Master of Economics Qualifying Program","Non-award","5072XMECQ","1","0"},
		{"271","http://programsandcourses.anu.edu.au/2016/program/MENCH","Master of Energy Change","Graduate","MENCH","2","0"},
		{"272","http://programsandcourses.anu.edu.au/2016/program/VENCH","Master of Energy Change (Advanced)","Graduate","VENCH","2","0"},
		{"273","http://programsandcourses.anu.edu.au/2016/program/NDSTE","Master of Engineering in Digital Systems and Telecommunications","Graduate","NDSTE","2","0"},
		{"274","http://programsandcourses.anu.edu.au/2016/program/NMECH","Master of Engineering in Mechatronics","Graduate","NMECH","2","0"},
		{"275","http://programsandcourses.anu.edu.au/2016/program/NENPH","Master of Engineering in Photonics","Graduate","NENPH","2","0"},
		{"276","http://programsandcourses.anu.edu.au/2016/program/NSETE","Master of Engineering in Solar Energy Technologies","Graduate","NSETE","2","0"},
		{"277","http://programsandcourses.anu.edu.au/2016/program/MENVI","Master of Environment","Graduate","MENVI","2","0"},
		{"278","http://programsandcourses.anu.edu.au/2016/program/VENVI","Master of Environment (Advanced)","Graduate","VENVI","2","0"},
		{"279","http://programsandcourses.anu.edu.au/2016/program/MEREC","Master of Environmental and Resource Economics","Graduate","MEREC","2","0"},
		{"280","http://programsandcourses.anu.edu.au/2016/program/MEMDV","Master of Environmental Management and Development","Graduate","MEMDV","2","0"},
		{"281","http://programsandcourses.anu.edu.au/2016/program/MEMOL","Master of Environmental Management and Development - Online","Graduate","MEMOL","2","0"},
		{"282","http://programsandcourses.anu.edu.au/2016/program/MENVS","Master of Environmental Science","Graduate","MENVS","2","0"},
		{"283","http://programsandcourses.anu.edu.au/2016/program/VENVS","Master of Environmental Science (Advanced)","Graduate","VENVS","2","0"},
		{"284","http://programsandcourses.anu.edu.au/2016/program/7418XMFIN","Master of Finance","Graduate","7418XMFIN","2","0"},
		{"285","http://programsandcourses.anu.edu.au/2016/program/MFIEC","Master of Financial Economics","Graduate","MFIEC","2","0"},
		{"286","http://programsandcourses.anu.edu.au/2016/program/MFINM","Master of Financial Management","Graduate","MFINM","1","0"},
		{"287","http://programsandcourses.anu.edu.au/2016/program/MFORE","Master of Forestry","Graduate","MFORE","2","0"},
		{"288","http://programsandcourses.anu.edu.au/2016/program/VFORE","Master of Forestry (Advanced)","Graduate","VFORE","2","0"},
		{"289","http://programsandcourses.anu.edu.au/2016/program/MLING","Master of General and Applied Linguistics","Graduate","MLING","2","0"},
		{"290","http://programsandcourses.anu.edu.au/2016/program/VLING","Master of General and Applied Linguistics (Advanced)","Graduate","VLING","2","0"},
		{"291","http://programsandcourses.anu.edu.au/2016/program/MGLOB","Master of Globalisation","Graduate","MGLOB","2","0"},
		{"292","http://programsandcourses.anu.edu.au/2016/program/VGLOB","Master of Globalisation (Advanced)","Graduate","VGLOB","2","0"},
		{"293","http://programsandcourses.anu.edu.au/2016/program/MHIST","Master of History","Graduate","MHIST","2","0"},
		{"294","http://programsandcourses.anu.edu.au/2016/program/VHIST","Master of History (Advanced)","Graduate","VHIST","2","0"},
		{"295","http://programsandcourses.anu.edu.au/2016/program/7815XMIAF","Master of International Affairs","Graduate","7815XMIAF","1","0"},
		{"296","http://programsandcourses.anu.edu.au/2016/program/MIDEC","Master of International and Development Economics","Graduate","MIDEC","2","0"},
		{"297","http://programsandcourses.anu.edu.au/2016/program/MIMGT","Master of International Management","Graduate","MIMGT","2","0"},
		{"298","http://programsandcourses.anu.edu.au/2016/program/MINTR","Master of International Relations","Graduate","MINTR","2","0"},
		{"299","http://programsandcourses.anu.edu.au/2016/program/VINTR","Master of International Relations (Advanced)","Graduate","VINTR","2","0"},
		{"300","http://programsandcourses.anu.edu.au/2016/program/MIIMW","Master of Islam in the Modern World","Graduate","MIIMW","2","0"},
		{"301","http://programsandcourses.anu.edu.au/2016/program/VIIMW","Master of Islam in the Modern World (Advanced)","Graduate","VIIMW","2","0"},
		{"302","http://programsandcourses.anu.edu.au/2016/program/MLLM","Master of Laws","Graduate","MLLM","1","0"},
		{"303","http://programsandcourses.anu.edu.au/2016/program/NLLEN","Master of Laws in Environmental Law","Graduate","NLLEN","1","0"},
		{"304","http://programsandcourses.anu.edu.au/2016/program/NLLGR","Master of Laws in Government and Regulation","Graduate","NLLGR","1","0"},
		{"305","http://programsandcourses.anu.edu.au/2016/program/NLLIL","Master of Laws in International Law","Graduate","NLLIL","1","0"},
		{"306","http://programsandcourses.anu.edu.au/2016/program/NLLSL","Master of Laws in International Security Law","Graduate","NLLSL","1","0"},
		{"307","http://programsandcourses.anu.edu.au/2016/program/NLLGD","Master of Laws in Law, Governance and Development","Graduate","NLLGD","1","0"},
		{"308","http://programsandcourses.anu.edu.au/2016/program/NLLML","Master of Laws in Migration Law","Graduate","NLLML","1","0"},
		{"309","http://programsandcourses.anu.edu.au/2016/program/MLEGP","Master of Legal Practice","Graduate","MLEGP","1.5","0"},
		{"310","http://programsandcourses.anu.edu.au/2016/program/7812XMMGNT","Master of Management","Graduate","7812XMMGNT","1","0"},
		{"311","http://programsandcourses.anu.edu.au/2016/program/7812XTSING","Master of Management ANU/Tsinghua","Graduate","7812XTSING","1","0"},
		{"312","http://programsandcourses.anu.edu.au/2016/program/VMASC","Master of Mathematical Sciences (Advanced)","Graduate","VMASC","2","0"},
		{"313","http://programsandcourses.anu.edu.au/2016/program/MMECA","Master of Middle Eastern and Central Asian Studies","Graduate","MMECA","2","0"},
		{"314","http://programsandcourses.anu.edu.au/2016/program/VMECA","Master of Middle Eastern and Central Asian Studies (Advanced)","Graduate","VMECA","2","0"},
		{"315","http://programsandcourses.anu.edu.au/2016/program/7829XMMDS","Master of Military and Defence Studies","Graduate","7829XMMDS","1.5","0"},
		{"316","http://programsandcourses.anu.edu.au/2016/program/7828XMMDSA","Master of Military and Defence Studies (Advanced)","Graduate","7828XMMDSA","2","0"},
		{"317","http://programsandcourses.anu.edu.au/2016/program/7316XMMIL","Master of Military Law","Graduate","7316XMMIL","1","0"},
		{"318","http://programsandcourses.anu.edu.au/2016/program/MMUHS","Master of Museum and Heritage Studies","Graduate","MMUHS","2","0"},
		{"319","http://programsandcourses.anu.edu.au/2016/program/VMUHS","Master of Museum and Heritage Studies (Advanced)","Graduate","VMUHS","2","0"},
		{"320","http://programsandcourses.anu.edu.au/2016/program/MMUSI","Master of Music","Graduate","MMUSI","2","0"},
		{"321","http://programsandcourses.anu.edu.au/2016/program/VMUSI","Master of Music (Advanced)","Graduate","VMUSI","2","0"},
		{"322","http://programsandcourses.anu.edu.au/2016/program/MNSPO","Master of National Security Policy","Graduate","MNSPO","2","0"},
		{"323","http://programsandcourses.anu.edu.au/2016/program/VNSPO","Master of National Security Policy (Advanced)","Graduate","VNSPO","2","0"},
		{"324","http://programsandcourses.anu.edu.au/2016/program/MNEUR","Master of Neuroscience","Graduate","MNEUR","2","0"},
		{"325","http://programsandcourses.anu.edu.au/2016/program/VNEUR","Master of Neuroscience (Advanced)","Graduate","VNEUR","2","0"},
		{"326","http://programsandcourses.anu.edu.au/2016/program/MNUCL","Master of Nuclear Science","Graduate","MNUCL","2","0"},
		{"327","http://programsandcourses.anu.edu.au/2016/program/8000XMPHIL","Master of Philosophy","Research","8000XMPHIL","2","0"},
		{"328","http://programsandcourses.anu.edu.au/2016/program/8030XMPHIL","Master of Philosophy (MPhil), ANU College of Law","Research","8030XMPHIL","2","0"},
		{"329","http://programsandcourses.anu.edu.au/2016/program/8721XMPHIL","Master of Philosophy in Applied Epidemiology","Research","8721XMPHIL","2","0"},
		{"330","http://programsandcourses.anu.edu.au/2016/program/8040XMPHIL","Master of Philosophy, ANU College of Business and Economics","Research","8040XMPHIL","2","0"},
		{"331","http://programsandcourses.anu.edu.au/2016/program/8070XMPHIL","Master of Philosophy, ANU College of Engineering and Computer Science","Research","8070XMPHIL","2","0"},
		{"332","http://programsandcourses.anu.edu.au/2016/program/8060XMPHIL","Master of Philosophy, ANU Colleges of Science","Research","8060XMPHIL","2","0"},
		{"333","http://programsandcourses.anu.edu.au/2016/program/8910XMPHIL","Master of Philosophy, ANU Medical School","Research","8910XMPHIL","2","0"},
		{"334","http://programsandcourses.anu.edu.au/2016/program/8540XMPHIL","Master of Philosophy, Crawford School of Public Policy","Research","8540XMPHIL","2","0"},
		{"335","http://programsandcourses.anu.edu.au/2016/program/8050XMPHIL","Master of Philosophy, Culture, History and Languages","Research","8050XMPHIL","2","0"},
		{"336","http://programsandcourses.anu.edu.au/2016/program/8065XMPHIL","Master of Philosophy, Fenner School of Environment and Society","Research","8065XMPHIL","2","0"},
		{"337","http://programsandcourses.anu.edu.au/2016/program/8710XMPHIL","Master of Philosophy, John Curtin School of Medical Research","Research","8710XMPHIL","2","0"},
		{"338","http://programsandcourses.anu.edu.au/2016/program/8650XMPHIL","Master of Philosophy, Mathematical Sciences Institute","Research","8650XMPHIL","2","0"},
		{"339","http://programsandcourses.anu.edu.au/2016/program/8350XMPHIL","Master of Philosophy, National Centre for Indigenous Studies","Research","8350XMPHIL","2","0"},
		{"340","http://programsandcourses.anu.edu.au/2016/program/8850XMPHIL","Master of Philosophy, National Security College","Research","8850XMPHIL","2","0"},
		{"341","http://programsandcourses.anu.edu.au/2016/program/8723XMPHIL","Master of Philosophy, Psychology","Research","8723XMPHIL","2","0"},
		{"342","http://programsandcourses.anu.edu.au/2016/program/8560XMPHIL","Master of Philosophy, Regulation, Justice and Diplomacy","Research","8560XMPHIL","2","0"},
		{"343","http://programsandcourses.anu.edu.au/2016/program/8670XMPHIL","Master of Philosophy, Research School of Astronomy and Astrophysics","Research","8670XMPHIL","2","0"},
		{"344","http://programsandcourses.anu.edu.au/2016/program/8066XMPHIL","Master of Philosophy, Research School of Biology","Research","8066XMPHIL","2","0"},
		{"345","http://programsandcourses.anu.edu.au/2016/program/8620XMPHIL","Master of Philosophy, Research School of Chemistry","Research","8620XMPHIL","2","0"},
		{"346","http://programsandcourses.anu.edu.au/2016/program/8640XMPHIL","Master of Philosophy, Research School of Earth Sciences","Research","8640XMPHIL","2","0"},
		{"347","http://programsandcourses.anu.edu.au/2016/program/8551XMPHIL","Master of Philosophy, Research School of Humanities and the Arts","Research","8551XMPHIL","2","0"},
		{"348","http://programsandcourses.anu.edu.au/2016/program/8680XMPHIL","Master of Philosophy, Research School of Physics and Engineering","Research","8680XMPHIL","2","0"},
		{"349","http://programsandcourses.anu.edu.au/2016/program/8601XMPHIL","Master of Philosophy, Research School of Population Health","Research","8601XMPHIL","2","0"},
		{"350","http://programsandcourses.anu.edu.au/2016/program/8520XMPHIL","Master of Philosophy, Research School of Social Sciences","Research","8520XMPHIL","2","0"},
		{"351","http://programsandcourses.anu.edu.au/2016/program/8510XMPHIL","Master of Philosophy, School of International, Political and Strategic Studies","Research","8510XMPHIL","2","0"},
		{"352","http://programsandcourses.anu.edu.au/2016/program/7413XMPACC","Master of Professional Accounting","Graduate","7413XMPACC","1.5","0"},
		{"353","http://programsandcourses.anu.edu.au/2016/program/MPROM","Master of Project Management","Graduate","MPROM","2","0"},
		{"354","http://programsandcourses.anu.edu.au/2016/program/MPUAD","Master of Public Administration","Graduate","MPUAD","2","0"},
		{"355","http://programsandcourses.anu.edu.au/2016/program/MPUBH","Master of Public Health","Graduate","MPUBH","2","0"},
		{"356","http://programsandcourses.anu.edu.au/2016/program/VPUBH","Master of Public Health (Advanced)","Graduate","VPUBH","2","0"},
		{"357","http://programsandcourses.anu.edu.au/2016/program/MPUBP","Master of Public Policy","Graduate","MPUBP","2","0"},
		{"358","http://programsandcourses.anu.edu.au/2016/program/NPPDP","Master of Public Policy in Development Policy","Graduate","NPPDP","2","0"},
		{"359","http://programsandcourses.anu.edu.au/2016/program/NPPEP","Master of Public Policy in Economic Policy","Graduate","NPPEP","2","0"},
		{"360","http://programsandcourses.anu.edu.au/2016/program/NPPIP","Master of Public Policy in International Policy","Graduate","NPPIP","2","0"},
		{"361","http://programsandcourses.anu.edu.au/2016/program/NPPPA","Master of Public Policy in Policy Analysis","Graduate","NPPPA","2","0"},
		{"362","http://programsandcourses.anu.edu.au/2016/program/NPPSP","Master of Public Policy in Social Policy","Graduate","NPPSP","2","0"},
		{"363","http://programsandcourses.anu.edu.au/2016/program/MSCHK","Master of Science Communication","Graduate","MSCHK","2","0"},
		{"364","http://programsandcourses.anu.edu.au/2016/program/MSCOM","Master of Science Communication","Graduate","MSCOM","2","0"},
		{"365","http://programsandcourses.anu.edu.au/2016/program/7624XMSCO","Master of Science Communication Outreach","Graduate","7624XMSCO","1","0"},
		{"366","http://programsandcourses.anu.edu.au/2016/program/MSCAU","Master of Science in Science Communication","Graduate","MSCAU","1","0"},
		{"367","http://programsandcourses.anu.edu.au/2016/program/MSRES","Master of Social Research","Graduate","MSRES","2","0"},
		{"368","http://programsandcourses.anu.edu.au/2016/program/VSRES","Master of Social Research (Advanced)","Graduate","VSRES","2","0"},
		{"369","http://programsandcourses.anu.edu.au/2016/program/MSTAT","Master of Statistics","Graduate","MSTAT","2","0"},
		{"370","http://programsandcourses.anu.edu.au/2016/program/MSTST","Master of Strategic Studies","Graduate","MSTST","2","0"},
		{"371","http://programsandcourses.anu.edu.au/2016/program/VSTST","Master of Strategic Studies (Advanced)","Graduate","VSTST","2","0"},
		{"372","http://programsandcourses.anu.edu.au/2016/program/MSTUD","Master of Studies","Graduate","MSTUD","2","0"},
		{"373","http://programsandcourses.anu.edu.au/2016/program/VSTUD","Master of Studies (Advanced)","Graduate","VSTUD","2","0"},
		{"374","http://programsandcourses.anu.edu.au/2016/program/MTRAN","Master of Translation","Graduate","MTRAN","2","0"},
		{"375","http://programsandcourses.anu.edu.au/2016/program/VTRAN","Master of Translation (Advanced)","Graduate","VTRAN","2","0"},
		{"376","http://programsandcourses.anu.edu.au/2016/program/MVISA","Master of Visual Arts","Graduate","MVISA","2","0"},
		{"377","http://programsandcourses.anu.edu.au/2016/program/VVISA","Master of Visual Arts (Advanced)","Graduate","VVISA","2","0"},
		{"378","http://programsandcourses.anu.edu.au/2016/program/1140XNAWD","Non Award (ANU College of Business and Economics)","Non-award","1140XNAWD","","0"},
		{"379","http://programsandcourses.anu.edu.au/2016/program/1195XNAWD","Non Award (Clinical Experience)","Non-award","1195XNAWD","","0"},
		{"380","http://programsandcourses.anu.edu.au/2016/program/1302XNAWD","Non Award Cross Institutional","Non-award","1302XNAWD","","0"},
		{"381","http://programsandcourses.anu.edu.au/2016/program/1141XNAWD","Non Award Cross-Institutional (ANU College of Business and Economics)","Non-award","1141XNAWD","","0"},
		{"382","http://programsandcourses.anu.edu.au/2016/program/1304XNAWD","Non Award Exchange Program","Non-award","1304XNAWD","","0"},
		{"383","http://programsandcourses.anu.edu.au/2016/program/1301XNAWD","Non Award Exchange Program (12 Months)","Non-award","1301XNAWD","1","0"},
		{"384","http://programsandcourses.anu.edu.au/2016/program/1301XNAWD6","Non Award Exchange Program (6 Months)","Non-award","1301XNAWD6","0.5","0"},
		{"385","http://programsandcourses.anu.edu.au/2016/program/1150XNAWD","Non-Award (ANU College of Asia and the Pacific)","Non-award","1150XNAWD","","0"},
		{"386","http://programsandcourses.anu.edu.au/2016/program/1170XNAWD","Non-Award (ANU College of Engineering and Computer Science)","Non-award","1170XNAWD","","0"},
		{"387","http://programsandcourses.anu.edu.au/2016/program/1160XNAWD","Non-Award (ANU Colleges of Science)","Non-award","1160XNAWD","","0"},
		{"388","http://programsandcourses.anu.edu.au/2016/program/1190XNAWD","Non-Award (ANU Medical School)","Non-award","1190XNAWD","","0"},
		{"389","http://programsandcourses.anu.edu.au/2016/program/1110XNAWD","Non-Award Arts (ANU College of Arts and Social Sciences)","Non-award","1110XNAWD","","0"},
		{"390","http://programsandcourses.anu.edu.au/2016/program/1111XNAWD","Non-Award Cross-Institutional (ANU College of Arts and Social Sciences)","Non-award","1111XNAWD","","0"},
		{"391","http://programsandcourses.anu.edu.au/2016/program/1151XNAWD","Non-Award Cross-Institutional (ANU College of Asia and the Pacific)","Non-award","1151XNAWD","","0"},
		{"392","http://programsandcourses.anu.edu.au/2016/program/1171XNAWD","Non-Award Cross-Institutional (ANU College of Engineering and Computer Science)","Non-award","1171XNAWD","","0"},
		{"393","http://programsandcourses.anu.edu.au/2016/program/1131XNAWD","Non-Award Cross-Institutional (ANU College of Law)","Non-award","1131XNAWD","","0"},
		{"394","http://programsandcourses.anu.edu.au/2016/program/1161XNAWD","Non-Award Cross-Institutional (ANU Colleges of Science)","Non-award","1161XNAWD","","0"},
		{"395","http://programsandcourses.anu.edu.au/2016/program/1005XNAWD","Non-Award Cross-Institutional National Institute of the Arts/University of Canberra","Non-award","1005XNAWD","","0"},
		{"396","http://programsandcourses.anu.edu.au/2016/program/1130XNAWD","Non-Award Law (ANU College of Law)","Non-award","1130XNAWD","","0"},
		{"397","http://programsandcourses.anu.edu.au/2016/program/1300XNAW12","Non-Award Study Abroad Program (12 Months)","Non-award","1300XNAW12","1","0"},
		{"398","http://programsandcourses.anu.edu.au/2016/program/1300XNAWD","Non-Award Study Abroad Program (6 Months)","Non-award","1300XNAWD","1","0"},
		{"399","http://programsandcourses.anu.edu.au/2016/program/1300XNAWD6","Non-Award Study Abroad Program (6 Months)","Non-award","1300XNAWD6","","0"}
	};

	public static String[][] MajorData={
		{"1","http://programsandcourses.anu.edu.au/2016/major/ACCT-MAJ","Accounting","Undergraduate","ACCT-MAJ","0"},
		{"2","http://programsandcourses.anu.edu.au/2016/major/AGRK-MAJ","Ancient Greek","Undergraduate","AGRK-MAJ","0"},
		{"3","http://programsandcourses.anu.edu.au/2016/major/ANCH-MAJ","Ancient History","Undergraduate","ANCH-MAJ","0"},
		{"4","http://programsandcourses.anu.edu.au/2016/major/ANVI-MAJ","Animation and Video Major","Undergraduate","ANVI-MAJ","0"},
		{"5","http://programsandcourses.anu.edu.au/2016/major/ANTH-MAJ","Anthropology","Undergraduate","ANTH-MAJ","0"},
		{"6","http://programsandcourses.anu.edu.au/2016/major/ARAB-MAJ","Arabic","Undergraduate","ARAB-MAJ","0"},
		{"7","http://programsandcourses.anu.edu.au/2016/major/ARCP-MAJ","Archaeological Practice","Undergraduate","ARCP-MAJ","0"},
		{"8","http://programsandcourses.anu.edu.au/2016/major/ARCH-MAJ","Archaeology","Undergraduate","ARCH-MAJ","0"},
		{"9","http://programsandcourses.anu.edu.au/2016/major/ARTH-MAJ","Art History","Undergraduate","ARTH-MAJ","0"},
		{"10","http://programsandcourses.anu.edu.au/2016/major/ARTT-MAJ","Art Theory","Undergraduate","ARTT-MAJ","0"},
		{"11","http://programsandcourses.anu.edu.au/2016/major/ASPP-MAJ","Asia-Pacific Politics","Undergraduate","ASPP-MAJ","0"},
		{"12","http://programsandcourses.anu.edu.au/2016/major/SECU-MAJ","Asia-Pacific Security Studies","Undergraduate","SECU-MAJ","0"},
		{"13","http://programsandcourses.anu.edu.au/2016/major/ACMK-MAJ","Asian Capital Markets","Undergraduate","ACMK-MAJ","0"},
		{"14","http://programsandcourses.anu.edu.au/2016/major/AHIST-MAJ","Asian History","Undergraduate","AHIST-MAJ","0"},
		{"15","http://programsandcourses.anu.edu.au/2016/major/AREL-MAJ","Asian Religions","Undergraduate","AREL-MAJ","0"},
		{"16","http://programsandcourses.anu.edu.au/2016/major/ASIA-MAJ","Asian Studies","Undergraduate","ASIA-MAJ","0"},
		{"17","http://programsandcourses.anu.edu.au/2016/major/AUIS-MAJ","Australian Indigenous Studies","Undergraduate","AUIS-MAJ","0"},
		{"18","http://programsandcourses.anu.edu.au/2016/major/BCHM-MAJ","Biochemistry","Undergraduate","BCHM-MAJ","0"},
		{"19","http://programsandcourses.anu.edu.au/2016/major/BIAN-MAJ","Biological Anthropology","Undergraduate","BIAN-MAJ","0"},
		{"20","http://programsandcourses.anu.edu.au/2016/major/BIOL-MAJ","Biology","Undergraduate","BIOL-MAJ","0"},
		{"21","http://programsandcourses.anu.edu.au/2016/major/BMSY-MAJ","Biomedical Systems","Undergraduate","BMSY-MAJ","0"},
		{"22","http://programsandcourses.anu.edu.au/2016/major/BUSN-MAJ","Business Information Systems","Undergraduate","BUSN-MAJ","0"},
		{"23","http://programsandcourses.anu.edu.au/2016/major/CPMK-MAJ","Capital Markets","Undergraduate","CPMK-MAJ","0"},
		{"24","http://programsandcourses.anu.edu.au/2016/major/CERM-MAJ","Ceramics","Undergraduate","CERM-MAJ","0"},
		{"25","http://programsandcourses.anu.edu.au/2016/major/CDAR-MAJ","Ceramics Design Arts","Undergraduate","CDAR-MAJ","0"},
		{"26","http://programsandcourses.anu.edu.au/2016/major/CHEM-MAJ","Chemistry","Undergraduate","CHEM-MAJ","0"},
		{"27","http://programsandcourses.anu.edu.au/2016/major/CHIN-MAJ","Chinese Language","Undergraduate","CHIN-MAJ","0"},
		{"28","http://programsandcourses.anu.edu.au/2016/major/CHST-MAJ","Chinese Studies","Undergraduate","CHST-MAJ","0"},
		{"29","http://programsandcourses.anu.edu.au/2016/major/CASD-MAJ","Composition, Arranging and Sound Design","Undergraduate","CASD-MAJ","0"},
		{"30","http://programsandcourses.anu.edu.au/2016/major/COMPF-MAJ","Computational Foundations","Undergraduate","COMPF-MAJ","0"},
		{"31","http://programsandcourses.anu.edu.au/2016/major/CENG-MAJ","Computer Engineering","Undergraduate","CENG-MAJ","0"},
		{"32","http://programsandcourses.anu.edu.au/2016/major/CSCI-MAJ","Computer Science","Undergraduate","CSCI-MAJ","0"},
		{"33","http://programsandcourses.anu.edu.au/2016/major/COMS-MAJ","Computer Systems","Undergraduate","COMS-MAJ","0"},
		{"34","http://programsandcourses.anu.edu.au/2016/major/COAS-MAJ","Contemporary Asian Societies","Undergraduate","COAS-MAJ","0"},
		{"35","http://programsandcourses.anu.edu.au/2016/major/EURO-MAJ","Contemporary Europe","Undergraduate","EURO-MAJ","0"},
		{"36","http://programsandcourses.anu.edu.au/2016/major/CFIM-MAJ","Corporate Finance and Investment Management","Undergraduate","CFIM-MAJ","0"},
		{"37","http://programsandcourses.anu.edu.au/2016/major/CORP-MAJ","Corporate Sustainability","Undergraduate","CORP-MAJ","0"},
		{"38","http://programsandcourses.anu.edu.au/2016/major/CRIM-MAJ","Criminology","Undergraduate","CRIM-MAJ","0"},
		{"39","http://programsandcourses.anu.edu.au/2016/major/DEST-MAJ","Development Studies","Undergraduate","DEST-MAJ","0"},
		{"40","http://programsandcourses.anu.edu.au/2016/major/DIHU-MAJ","Digital Humanities","Undergraduate","DIHU-MAJ","0"},
		{"41","http://programsandcourses.anu.edu.au/2016/major/DIGS-MAJ","Digital Systems","Undergraduate","DIGS-MAJ","0"},
		{"42","http://programsandcourses.anu.edu.au/2016/major/EART-MAJ","Earth Science","Undergraduate","EART-MAJ","0"},
		{"43","http://programsandcourses.anu.edu.au/2016/major/ECST-MAJ","Economic Studies","Undergraduate","ECST-MAJ","0"},
		{"44","http://programsandcourses.anu.edu.au/2016/major/ELTS-MAJ","Electronic Systems","Undergraduate","ELTS-MAJ","0"},
		{"45","http://programsandcourses.anu.edu.au/2016/major/ELCO-MAJ","Electronic and Communication Systems","Undergraduate","ELCO-MAJ","0"},
		{"46","http://programsandcourses.anu.edu.au/2016/major/ENGS-MAJ","Engineering Science","Undergraduate","ENGS-MAJ","0"},
		{"47","http://programsandcourses.anu.edu.au/2016/major/ENGL-MAJ","English","Undergraduate","ENGL-MAJ","0"},
		{"48","http://programsandcourses.anu.edu.au/2016/major/ENST-MAJ","Environmental Studies","Undergraduate","ENST-MAJ","0"},
		{"49","http://programsandcourses.anu.edu.au/2016/major/ENSY-MAJ","Environmental Systems","Undergraduate","ENSY-MAJ","0"},
		{"50","http://programsandcourses.anu.edu.au/2016/major/EALS-MAJ","Environmental and Landscape Sciences","Undergraduate","EALS-MAJ","0"},
		{"51","http://programsandcourses.anu.edu.au/2016/major/EHIS-MAJ","European History","Undergraduate","EHIS-MAJ","0"},
		{"52","http://programsandcourses.anu.edu.au/2016/major/FINM-MAJ","Finance","Undergraduate","FINM-MAJ","0"},
		{"53","http://programsandcourses.anu.edu.au/2016/major/FREN-MAJ","French Language and Culture","Undergraduate","FREN-MAJ","0"},
		{"54","http://programsandcourses.anu.edu.au/2016/major/FURN-MAJ","Furniture","Undergraduate","FURN-MAJ","0"},
		{"55","http://programsandcourses.anu.edu.au/2016/major/FDAR-MAJ","Furniture Design Arts","Undergraduate","FDAR-MAJ","0"},
		{"56","http://programsandcourses.anu.edu.au/2016/major/GEND-MAJ","Gender, Sexuality and Culture","Undergraduate","GEND-MAJ","0"},
		{"57","http://programsandcourses.anu.edu.au/2016/major/GEOG-MAJ","Geography","Undergraduate","GEOG-MAJ","0"},
		{"58","http://programsandcourses.anu.edu.au/2016/major/GERM-MAJ","German Language and Culture","Undergraduate","GERM-MAJ","0"},
		{"59","http://programsandcourses.anu.edu.au/2016/major/GLAS-MAJ","Glass","Undergraduate","GLAS-MAJ","0"},
		{"60","http://programsandcourses.anu.edu.au/2016/major/GDAR-MAJ","Glass Design Arts","Undergraduate","GDAR-MAJ","0"},
		{"61","http://programsandcourses.anu.edu.au/2016/major/GSDA-MAJ","Gold & Silversmithing Design Arts","Undergraduate","GSDA-MAJ","0"},
		{"62","http://programsandcourses.anu.edu.au/2016/major/GOLD-MAJ","Gold and Silversmithing","Undergraduate","GOLD-MAJ","0"},
		{"63","http://programsandcourses.anu.edu.au/2016/major/HIND-MAJ","Hindi Language","Undergraduate","HIND-MAJ","0"},
		{"64","http://programsandcourses.anu.edu.au/2016/major/HIST-MAJ","History","Undergraduate","HIST-MAJ","0"},
		{"65","http://programsandcourses.anu.edu.au/2016/major/HMRT-MAJ","Human Rights","Undergraduate","HMRT-MAJ","0"},
		{"66","http://programsandcourses.anu.edu.au/2016/major/HCCP-MAJ","Human-Centric Computing","Undergraduate","HCCP-MAJ","0"},
		{"67","http://programsandcourses.anu.edu.au/2016/major/INDN-MAJ","Indonesian Language","Undergraduate","INDN-MAJ","0"},
		{"68","http://programsandcourses.anu.edu.au/2016/major/INST-MAJ","Indonesian Studies","Undergraduate","INST-MAJ","0"},
		{"69","http://programsandcourses.anu.edu.au/2016/major/INFS-MAJ","Information Systems","Undergraduate","INFS-MAJ","0"},
		{"70","http://programsandcourses.anu.edu.au/2016/major/IICP-MAJ","Information-Intensive Computing","Undergraduate","IICP-MAJ","0"},
		{"71","http://programsandcourses.anu.edu.au/2016/major/INTL-MAJ","Intelligent Systems","Undergraduate","INTL-MAJ","0"},
		{"72","http://programsandcourses.anu.edu.au/2016/major/INSC-MAJ","Interdisciplinary Science","Undergraduate","INSC-MAJ","0"},
		{"73","http://programsandcourses.anu.edu.au/2016/major/INTB-MAJ","International Business","Undergraduate","INTB-MAJ","0"},
		{"74","http://programsandcourses.anu.edu.au/2016/major/ICOM-MAJ","International Communication","Undergraduate","ICOM-MAJ","0"},
		{"75","http://programsandcourses.anu.edu.au/2016/major/IREL-MAJ","International Relations","Undergraduate","IREL-MAJ","0"},
		{"76","http://programsandcourses.anu.edu.au/2016/major/ISST-MAJ","International Security","Undergraduate","ISST-MAJ","0"},
		{"77","http://programsandcourses.anu.edu.au/2016/major/ITAL-MAJ","Italian Language and Culture","Undergraduate","ITAL-MAJ","0"},
		{"78","http://programsandcourses.anu.edu.au/2016/major/JPNS-MAJ","Japanese Language","Undergraduate","JPNS-MAJ","0"},
		{"79","http://programsandcourses.anu.edu.au/2016/major/JPLN-MAJ","Japanese Linguistics","Undergraduate","JPLN-MAJ","0"},
		{"80","http://programsandcourses.anu.edu.au/2016/major/JPST-MAJ","Japanese Studies","Undergraduate","JPST-MAJ","0"},
		{"81","http://programsandcourses.anu.edu.au/2016/major/KORE-MAJ","Korean Language","Undergraduate","KORE-MAJ","0"},
		{"82","http://programsandcourses.anu.edu.au/2016/major/LATN-MAJ","Latin","Undergraduate","LATN-MAJ","0"},
		{"83","http://programsandcourses.anu.edu.au/2016/major/LAMS-MAJ","Latin American Studies","Undergraduate","LAMS-MAJ","0"},
		{"84","http://programsandcourses.anu.edu.au/2016/major/LING-MAJ","Linguistics","Undergraduate","LING-MAJ","0"},
		{"85","http://programsandcourses.anu.edu.au/2016/major/MGMT-MAJ","Management","Undergraduate","MGMT-MAJ","0"},
		{"86","http://programsandcourses.anu.edu.au/2016/major/MARS-MAJ","Marine Science","Undergraduate","MARS-MAJ","0"},
		{"87","http://programsandcourses.anu.edu.au/2016/major/MARK-MAJ","Marketing","Undergraduate","MARK-MAJ","0"},
		{"88","http://programsandcourses.anu.edu.au/2016/major/MECO-MAJ","Mathematical Economics","Undergraduate","MECO-MAJ","0"},
		{"89","http://programsandcourses.anu.edu.au/2016/major/MFIN-MAJ","Mathematical Finance","Undergraduate","MFIN-MAJ","0"},
		{"90","http://programsandcourses.anu.edu.au/2016/major/MMOD-MAJ","Mathematical Modelling","Undergraduate","MMOD-MAJ","0"},
		{"91","http://programsandcourses.anu.edu.au/2016/major/MATH-MAJ","Mathematics","Undergraduate","MATH-MAJ","0"},
		{"92","http://programsandcourses.anu.edu.au/2016/major/MMSY-MAJ","Mechanical and Material Systems","Undergraduate","MMSY-MAJ","0"},
		{"93","http://programsandcourses.anu.edu.au/2016/major/MTSY-MAJ","Mechatronic Systems","Undergraduate","MTSY-MAJ","0"},
		{"94","http://programsandcourses.anu.edu.au/2016/major/MECA-MAJ","Middle Eastern and Central Asian Studies","Undergraduate","MECA-MAJ","0"},
		{"95","http://programsandcourses.anu.edu.au/2016/major/MUSC-MAJ","Music","Undergraduate","MUSC-MAJ","0"},
		{"96","http://programsandcourses.anu.edu.au/2016/major/MTEC-MAJ","Music Technology","Undergraduate","MTEC-MAJ","0"},
		{"97","http://programsandcourses.anu.edu.au/2016/major/MUSY-MAJ","Musicology","Undergraduate","MUSY-MAJ","0"},
		{"98","http://programsandcourses.anu.edu.au/2016/major/NRMG-MAJ","Natural Resource Management","Undergraduate","NRMG-MAJ","0"},
		{"99","http://programsandcourses.anu.edu.au/2016/major/NAST-MAJ","Northeast Asian Studies","Undergraduate","NAST-MAJ","0"},
		{"100","http://programsandcourses.anu.edu.au/2016/major/PALG-MAJ","Pacific Languages","Undergraduate","PALG-MAJ","0"},
		{"101","http://programsandcourses.anu.edu.au/2016/major/PAST-MAJ","Pacific Studies","Undergraduate","PAST-MAJ","0"},
		{"102","http://programsandcourses.anu.edu.au/2016/major/PAIN-MAJ","Painting","Undergraduate","PAIN-MAJ","0"},
		{"103","http://programsandcourses.anu.edu.au/2016/major/PECO-MAJ","Peace and Conflict Studies","Undergraduate","PECO-MAJ","0"},
		{"104","http://programsandcourses.anu.edu.au/2016/major/PERF-MAJ","Performance","Undergraduate","PERF-MAJ","0"},
		{"105","http://programsandcourses.anu.edu.au/2016/major/PERS-MAJ","Persian","Undergraduate","PERS-MAJ","0"},
		{"106","http://programsandcourses.anu.edu.au/2016/major/PHIL-MAJ","Philosophy","Undergraduate","PHIL-MAJ","0"},
		{"107","http://programsandcourses.anu.edu.au/2016/major/PHOM-MAJ","Photomedia","Undergraduate","PHOM-MAJ","0"},
		{"108","http://programsandcourses.anu.edu.au/2016/major/PHOT-MAJ","Photonic Systems","Undergraduate","PHOT-MAJ","0"},
		{"109","http://programsandcourses.anu.edu.au/2016/major/PHYS-MAJ","Physics","Undergraduate","PHYS-MAJ","0"},
		{"110","http://programsandcourses.anu.edu.au/2016/major/POLS-MAJ","Political Science","Undergraduate","POLS-MAJ","0"},
		{"111","http://programsandcourses.anu.edu.au/2016/major/PMDR-MAJ","Printmedia and Drawing","Undergraduate","PMDR-MAJ","0"},
		{"112","http://programsandcourses.anu.edu.au/2016/major/PSYC-MAJ","Psychology","Undergraduate","PSYC-MAJ","0"},
		{"113","http://programsandcourses.anu.edu.au/2016/major/QFIN-MAJ","Quantitative Finance","Undergraduate","QFIN-MAJ","0"},
		{"114","http://programsandcourses.anu.edu.au/2016/major/RENE-MAJ","Renewable Energy Systems","Undergraduate","RENE-MAJ","0"},
		{"115","http://programsandcourses.anu.edu.au/2016/major/RSCC-MAJ","Research and Development","Undergraduate","RSCC-MAJ","0"},
		{"116","http://programsandcourses.anu.edu.au/2016/major/RSCE-MAJ","Research and Development","Undergraduate","RSCE-MAJ","0"},
		{"117","http://programsandcourses.anu.edu.au/2016/major/SANS-MAJ","Sanskrit Language","Undergraduate","SANS-MAJ","0"},
		{"118","http://programsandcourses.anu.edu.au/2016/major/SCOM-MAJ","Science Communication","Undergraduate","SCOM-MAJ","0"},
		{"119","http://programsandcourses.anu.edu.au/2016/major/SCUL-MAJ","Sculpture","Undergraduate","SCUL-MAJ","0"},
		{"120","http://programsandcourses.anu.edu.au/2016/major/SOCY-MAJ","Sociology","Undergraduate","SOCY-MAJ","0"},
		{"121","http://programsandcourses.anu.edu.au/2016/major/SOFT-MAJ","Software Development","Undergraduate","SOFT-MAJ","0"},
		{"122","http://programsandcourses.anu.edu.au/2016/major/SAST-MAJ","South Asian Studies","Undergraduate","SAST-MAJ","0"},
		{"123","http://programsandcourses.anu.edu.au/2016/major/SEAS-MAJ","Southeast Asian Studies","Undergraduate","SEAS-MAJ","0"},
		{"124","http://programsandcourses.anu.edu.au/2016/major/SPAH-MAJ","Spanish","Undergraduate","SPAH-MAJ","0"},
		{"125","http://programsandcourses.anu.edu.au/2016/major/STAT-MAJ","Statistics","Undergraduate","STAT-MAJ","0"},
		{"126","http://programsandcourses.anu.edu.au/2016/major/SUSC-MAJ","Sustainability Science","Undergraduate","SUSC-MAJ","0"},
		{"127","http://programsandcourses.anu.edu.au/2016/major/SESY-MAJ","Sustainable Energy Systems","Undergraduate","SESY-MAJ","0"},
		{"128","http://programsandcourses.anu.edu.au/2016/major/SUSY-MAJ","Sustainable Systems","Undergraduate","SUSY-MAJ","0"},
		{"129","http://programsandcourses.anu.edu.au/2016/major/TEXT-MAJ","Textiles","Undergraduate","TEXT-MAJ","0"},
		{"130","http://programsandcourses.anu.edu.au/2016/major/TDAR-MAJ","Textiles Design Arts","Undergraduate","TDAR-MAJ","0"},
		{"131","http://programsandcourses.anu.edu.au/2016/major/THAI-MAJ","Thai Language","Undergraduate","THAI-MAJ","0"},
		{"132","http://programsandcourses.anu.edu.au/2016/major/THPH-MAJ","Theoretical Physics","Undergraduate","THPH-MAJ","0"},
		{"133","http://programsandcourses.anu.edu.au/2016/major/URDU-MAJ","Urdu Language","Undergraduate","URDU-MAJ","0"},
		{"134","http://programsandcourses.anu.edu.au/2016/major/URPE-MAJ","Urdu/Persian Language","Undergraduate","URPE-MAJ","0"},
		{"135","http://programsandcourses.anu.edu.au/2016/major/VIET-MAJ","Vietnamese Language","Undergraduate","VIET-MAJ","0"},
		{"136","http://programsandcourses.anu.edu.au/2016/major/WASC-MAJ","Water Science","Undergraduate","WASC-MAJ","0"},
		{"137","http://programsandcourses.anu.edu.au/2016/major/YIAS-MAJ","Year in Asia","Undergraduate","YIAS-MAJ","0"}
	};
}
