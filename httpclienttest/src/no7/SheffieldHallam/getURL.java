package no7.SheffieldHallam;

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

		FileInputStream fis=new FileInputStream(new File("./sheffieldhallamPost.html"));
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
   	    	if(!link.getAttribute("href").equals("")&&!html2Str(link.toHtml()).contains("Back to top"))
   	    	{
   	    		if(!list.contains(link.getAttribute("href")))
   	    		{
   	    			list.add(link.getAttribute("href"));
   	    			/*System.out.println(HTMLFilter(html2Str(link.toHtml()))
   	   	    				.replace("\r\n", "").split(" – ").length);*/
   	    			if(HTMLFilter(html2Str(link.toHtml()))
   	   	    				.replace("\r\n", "").split(" – ").length==1)
   	    			System.out.println("{\""+count+"\",\""+link.getAttribute("href")+"\",\""+HTMLFilter(html2Str(link.toHtml()))
   	   	    				.replace("\r\n", "").split(" – ")[0]+"\",\"\",\"0\"},");
   	    			else
   	    				System.out.println("{\""+count+"\",\""+link.getAttribute("href")+"\",\""+HTMLFilter(html2Str(link.toHtml()))
   	   	   	    				.replace("\r\n", "").split(" – ")[0]+"\",\""+HTMLFilter(html2Str(link.toHtml()))
   	   	    	   	    				.replace("\r\n", "").split(" – ")[1]+"\",\"0\"},");
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

	
	public static String[][] UnData={
		{"1","/prospectus/course/1146/","Accounting and Finance","BA (Honours)","0"},
		{"2","/prospectus/course/993/","Aerospace Engineering","BEng (Honours)","0"},
		{"3","/prospectus/course/994/","Aerospace Engineering","MEng","0"},
		{"4","/prospectus/course/539/","Animation","BA (Honours)","0"},
		{"5","/prospectus/course/985/","Animation","MArt","0"},
		{"6","/prospectus/course/518/","Applied Nursing (Learning Disability) and Generic Social Work","BSc (Honours)","0"},
		{"7","/prospectus/course/758/","Applied Social Science","BA (Honours)","0"},
		{"8","/prospectus/course/363/","Architectural Technology","BSc (Honours)","0"},
		{"9","/prospectus/course/652/","Architectural Technology","BSc (Honours)","0"},
		{"10","/prospectus/course/715/","Architecture","BSc (Honours)","0"},
		{"11","/prospectus/course/1324/","Art and Design","Extended Degree Programme","0"},
		{"12","/prospectus/course/344/","Association of Chartered Certified Accountants Fundamental Levels","ACCA","0"},
		{"13","/prospectus/course/501/","Automotive Engineering","BEng (Honours)","0"},
		{"14","/prospectus/course/1371/","Automotive Engineering","MEng","0"},
		{"15","/prospectus/course/1181/","Biochemistry","BSc (Honours)/MSci","0"},
		{"16","/prospectus/course/1185/","Biology","BSc (Honours)/MSci","0"},
		{"17","/prospectus/course/348/","Biomedical Science","BSc (Honours)","0"},
		{"18","/prospectus/course/1180/","Biomedical Science","BSc (Honours)/MSci","0"},
		{"19","/prospectus/course/370/","Building Surveying","BSc (Honours)","0"},
		{"20","/prospectus/course/723/","Building Surveying","BSc (Honours)","0"},
		{"21","/prospectus/course/1366/","Business Analytics","BA (Honours)","0"},
		{"22","/prospectus/course/753/","Business and Enterprise Management","BA (Honours)","0"},
		{"23","/prospectus/course/989/","Business and Finance (top up)","BA (Honours)","0"},
		{"24","/prospectus/course/949/","Business and Finance (two year top up)","BA (Honours)","0"},
		{"25","/prospectus/course/570/","Business and Financial Management","BA (Honours)","0"},
		{"26","/prospectus/course/675/","Business and Human Resource Management","BA (Honours)","0"},
		{"27","/prospectus/course/688/","Business and ICT","BSc (Honours)","0"},
		{"28","/prospectus/course/676/","Business and Marketing","BA (Honours)","0"},
		{"29","/prospectus/course/538/","Business Economics","BA (Honours)","0"},
		{"30","/prospectus/course/757/","Business Management (top up)","BA (Honours)","0"},
		{"31","/prospectus/course/988/","Business Management (two year top up)","BA (Honours)","0"},
		{"32","/prospectus/course/1268/","Business Property Management (top up)","BSc (Honours)","0"},
		{"33","/prospectus/course/670/","Business Studies","BA (Honours)","0"},
		{"34","/prospectus/course/1361/","Chemical Engineering","MEng","0"},
		{"35","/prospectus/course/1363/","Chemical Engineering","BEng (Honours)","0"},
		{"36","/prospectus/course/1184/","Chemistry","BSc (Honours)/MChem","0"},
		{"37","/prospectus/course/810/","Childhood Studies","BA (Honours)","0"},
		{"38","/prospectus/course/630/","Computer and Information Security","BSc (Honours)","0"},
		{"39","/prospectus/course/1129/","Computer Networks","BSc (Honours)","0"},
		{"40","/prospectus/course/1120/","Computer Science","BSc (Honours)","0"},
		{"41","/prospectus/course/1097/","Computer Security with Forensics","BSc (Honours)","0"},
		{"42","/prospectus/course/534/","Computer Systems Engineering","BEng (Honours)","0"},
		{"43","/prospectus/course/772/","Computing","BSc (Honours)","0"},
		{"44","/prospectus/course/103/","Computing Management (top up)","BSc (Honours)","0"},
		{"45","/prospectus/course/1345/","Construction Engineering (top up)","BSc (Honours)","0"},
		{"46","/prospectus/course/369/","Construction Project Management","BSc (Honours)","0"},
		{"47","/prospectus/course/667/","Construction Project Management","BSc (Honours)","0"},
		{"48","/prospectus/course/660/","Creative Writing","BA (Honours)","0"},
		{"49","/prospectus/course/731/","Criminology","BA (Honours)","0"},
		{"50","/prospectus/course/725/","Criminology and Psychology","BSc (Honours)","0"},
		{"51","/prospectus/course/726/","Criminology and Sociology","BA (Honours)","0"},
		{"52","/prospectus/course/1319/","Data Science","BSc (Honours)","0"},
		{"53","/prospectus/course/530/","Design and Technology with Education and Qualified Teacher Status (Three Year Route)","BSc (Honours)","0"},
		{"54","/prospectus/course/611/","Diagnostic Radiography","BSc (Honours)","0"},
		{"55","/prospectus/course/526/","Digital Media Production","BA (Honours)","0"},
		{"56","/prospectus/course/1004/","Digital Media Production","MArt","0"},
		{"57","/prospectus/course/569/","Early Childhood Studies","BA (Honours)","0"},
		{"58","/prospectus/course/100/","Early Years","Foundation Degree","0"},
		{"59","/prospectus/course/546/","Early Years and Primary Education (3-7) with QTS","BA (Honours)","0"},
		{"60","/prospectus/course/969/","Education - Early Years (top up)","BA (Honours)","0"},
		{"61","/prospectus/course/1352/","Education and Learning Support","Foundation Degree","0"},
		{"62","/prospectus/course/338/","Education and Learning Support (top up)","BA (Honours)","0"},
		{"63","/prospectus/course/531/","Education Studies","BA (Honours)","0"},
		{"64","/prospectus/course/809/","Education with Psychology and Counselling","BA (Honours)","0"},
		{"65","/prospectus/course/245/","Electrical and Electronic Engineering","BEng (Honours)","0"},
		{"66","/prospectus/course/580/","Electrical and Electronic Engineering","BEng (Honours)","0"},
		{"67","/prospectus/course/737/","Electrical and Electronic Engineering","MEng","0"},
		{"68","/prospectus/course/1105/","Electrical Engineering (top up)","BEng (Honours)","0"},
		{"69","/prospectus/course/1296/","Electrical Engineering (top up)","BEng (Honours)","0"},
		{"70","/prospectus/course/248/","Electronic Engineering","BEng (Honours)","0"},
		{"71","/prospectus/course/524/","Electronic Engineering","BEng (Honours)","0"},
		{"72","/prospectus/course/1125/","Engineering and Mathematics","Extended Degree Programme","0"},
		{"73","/prospectus/course/1159/","Engineering and Mathematics","Extended Degree Programme","0"},
		{"74","/prospectus/course/532/","English","BA (Honours)","0"},
		{"75","/prospectus/course/763/","English and History","BA (Honours)","0"},
		{"76","/prospectus/course/795/","English Language","BA (Honours)","0"},
		{"77","/prospectus/course/1002/","English Literature","BA (Honours)","0"},
		{"78","/prospectus/course/714/","Environmental Science","BSc (Honours)","0"},
		{"79","/prospectus/course/914/","Events and Leisure Management (top up)","BSc (Honours)","0"},
		{"80","/prospectus/course/918/","Events Management (top up)","BSc (Honours)","0"},
		{"81","/prospectus/course/916/","Events Management with Arts and Entertainment (top up)","BSc (Honours)","0"},
		{"82","/prospectus/course/917/","Events Management with Tourism (top up)","BSc (Honours)","0"},
		{"83","/prospectus/course/361/","Facilities Management","Certificate/Foundation Degree","0"},
		{"84","/prospectus/course/875/","Facilities Management (top up)","BA (Honours)","0"},
		{"85","/prospectus/course/773/","Fashion Design","BA (Honours)","0"},
		{"86","/prospectus/course/1323/","Fashion Management and Communication","BA (Honours)","0"},
		{"87","/prospectus/course/535/","Film and Media Production","BA (Honours)","0"},
		{"88","/prospectus/course/1006/","Film and Media Production","MArt","0"},
		{"89","/prospectus/course/764/","Film Studies","BA (Honours)","0"},
		{"90","/prospectus/course/903/","Film Studies and Screenwriting","BA (Honours)","0"},
		{"91","/prospectus/course/188/","Fine Art","BA (Honours)","0"},
		{"92","/prospectus/course/517/","Fine Art","BA (Honours)","0"},
		{"93","/prospectus/course/636/","Food and Nutrition","BSc (Honours)","0"},
		{"94","/prospectus/course/920/","Food and Nutrition (top up)","BSc (Honours)","0"},
		{"95","/prospectus/course/1247/","Food Engineering","MEng","0"},
		{"96","/prospectus/course/1343/","Food Engineering","BEng (Honours)","0"},
		{"97","/prospectus/course/1354/","Food Engineering","BEng (Honours)","0"},
		{"98","/prospectus/course/642/","Food Marketing Management","BSc (Honours)","0"},
		{"99","/prospectus/course/919/","Food Marketing Management (top up)","BSc (Honours)","0"},
		{"100","/prospectus/course/867/","Forensic Accounting","BA (Honours)","0"},
		{"101","/prospectus/course/502/","Games Design","BA (Honours)","0"},
		{"102","/prospectus/course/992/","Games Design","MArt","0"},
		{"103","/prospectus/course/720/","Games Software Development","BSc (Honours)","0"},
		{"104","/prospectus/course/800/","Games Software Development","MComp","0"},
		{"105","/prospectus/course/637/","Geography","BSc (Honours)","0"},
		{"106","/prospectus/course/694/","Graphic Design","BA (Honours)","0"},
		{"107","/prospectus/course/1241/","Graphic Design","BA (Honours)","0"},
		{"108","/prospectus/course/1252/","Health and Social Care Leadership and Management","BA (Honours)/DipHE","0"},
		{"109","/prospectus/course/521/","History","BA (Honours)","0"},
		{"110","/prospectus/course/925/","Hospitality Business Management (top up)","BSc (Honours)","0"},
		{"111","/prospectus/course/921/","Hospitality Business Management with Conference and Events (top up)","BSc (Honours)","0"},
		{"112","/prospectus/course/924/","Hospitality Business Management with Culinary Arts (top up)","BSc (Honours)","0"},
		{"113","/prospectus/course/1182/","Human Biology","BSc (Honours)/MSci","0"},
		{"114","/prospectus/course/790/","Human Geography","BA (Honours)","0"},
		{"115","/prospectus/course/1322/","Illustration","BA (Honours)","0"},
		{"116","/prospectus/course/547/","Information Technology with Business Studies","BSc (Honours)","0"},
		{"117","/prospectus/course/488/","Information Technology with Business Technologies (top up)","BSc (Honours)","0"},
		{"118","/prospectus/course/513/","Information Technology with Digital Media (top up)","BSc (Honours)","0"},
		{"119","/prospectus/course/510/","Information Technology with Networks (top up)","BSc (Honours)","0"},
		{"120","/prospectus/course/1224/","Integrated Engineering","Electrical","0"},
		{"121","/prospectus/course/1225/","Integrated Engineering","Manufacturing","0"},
		{"122","/prospectus/course/983/","Integrated Engineering","Mechanical","0"},
		{"123","/prospectus/course/679/","Interior Design","BA (Honours)","0"},
		{"124","/prospectus/course/1242/","Interior Design","BA (Honours)","0"},
		{"125","/prospectus/course/1169/","International Banking and Finance (top up)","BA (Honours)","0"},
		{"126","/prospectus/course/663/","International Business","BA (Honours)","0"},
		{"127","/prospectus/course/788/","International Business (top up)","BA (Honours)","0"},
		{"128","/prospectus/course/659/","International Business with French","BA (Honours)","0"},
		{"129","/prospectus/course/1223/","International Business with German","BA (Honours)","0"},
		{"130","/prospectus/course/1222/","International Business with Spanish","BA (Honours)","0"},
		{"131","/prospectus/course/648/","International Events Management","BSc (Honours)","0"},
		{"132","/prospectus/course/647/","International Events Management with Arts and Entertainment","BSc (Honours)","0"},
		{"133","/prospectus/course/1357/","International Events Management with Experiential Marketing","BSc (Honours)","0"},
		{"134","/prospectus/course/650/","International Events Management with Tourism","BSc (Honours)","0"},
		{"135","/prospectus/course/1073/","International Finance and Banking","BA (Honours)","0"},
		{"136","/prospectus/course/1075/","International Finance and Economics","BA (Honours)","0"},
		{"137","/prospectus/course/1074/","International Finance and Investment","BA (Honours)","0"},
		{"138","/prospectus/course/1379/","International Foundation Programme: Art, Design and Media","","0"},
		{"139","/prospectus/course/1380/","International Foundation Programme: Business, Law and Social Sciences","","0"},
		{"140","/prospectus/course/770/","International Hospitality Business Management","BSc (Honours)","0"},
		{"141","/prospectus/course/728/","International Hospitality Business Management with Conference and Events","BSc (Honours)","0"},
		{"142","/prospectus/course/643/","International Hospitality Business Management with Culinary Arts","BSc (Honours)","0"},
		{"143","/prospectus/course/736/","International Hotel and Resort Management","BSc (Honours)","0"},
		{"144","/prospectus/course/926/","International Hotel Management (top up)","BSc (Honours)","0"},
		{"145","/prospectus/course/769/","International Tourism and Hospitality Business Management","BSc (Honours)","0"},
		{"146","/prospectus/course/646/","International Tourism Management","BSc (Honours)","0"},
		{"147","/prospectus/course/727/","Jewellery and Metalwork","BA (Honours)","0"},
		{"148","/prospectus/course/1243/","Jewellery and Metalwork","BA (Honours)","0"},
		{"149","/prospectus/course/542/","Journalism","BA (Honours)","0"},
		{"150","/prospectus/course/1213/","Languages with International Business (French)","BA (Honours)","0"},
		{"151","/prospectus/course/1214/","Languages with International Business (German)","BA (Honours)","0"},
		{"152","/prospectus/course/1215/","Languages with International Business (Spanish)","BA (Honours)","0"},
		{"153","/prospectus/course/984/","Languages with TESOL (French)","BA (Honours)","0"},
		{"154","/prospectus/course/1230/","Languages with TESOL (German)","BA (Honours)","0"},
		{"155","/prospectus/course/1231/","Languages with TESOL (Spanish)","BA (Honours)","0"},
		{"156","/prospectus/course/1219/","Languages with Tourism (French)","BA (Honours)","0"},
		{"157","/prospectus/course/1220/","Languages with Tourism (German)","BA (Honours)","0"},
		{"158","/prospectus/course/1221/","Languages with Tourism (Spanish)","BA (Honours)","0"},
		{"159","/prospectus/course/624/","Law","LLB (Honours)","0"},
		{"160","/prospectus/course/598/","Law with Criminology","LLB (Honours)","0"},
		{"161","/prospectus/course/1233/","Learning English for Academic Purposes (LEAP)","","0"},
		{"162","/prospectus/course/674/","Learning Languages at Sheffield Hallam University","","0"},
		{"163","/prospectus/course/1318/","Logistics Management (top up)","BA (Honours)","0"},
		{"164","/prospectus/course/1269/","Manufacturing Engineering (top up)","BEng (Honours)","0"},
		{"165","/prospectus/course/1295/","Manufacturing Engineering (top up)","BEng (Honours)","0"},
		{"166","/prospectus/course/658/","Marketing","BA (Honours)","0"},
		{"167","/prospectus/course/576/","Marketing Communications and Advertising","BA (Honours)","0"},
		{"168","/prospectus/course/106/","Materials Engineering","FdEng","0"},
		{"169","/prospectus/course/249/","Materials Engineering","BEng (Honours)","0"},
		{"170","/prospectus/course/1162/","Materials Engineering","BEng (Honours)","0"},
		{"171","/prospectus/course/1370/","Materials Engineering","MEng","0"},
		{"172","/prospectus/course/349/","Mathematics","BSc (Honours)","0"},
		{"173","/prospectus/course/745/","Mathematics","BSc (Honours)","0"},
		{"174","/prospectus/course/549/","Mathematics with Education and Qualified Teacher Status","BSc (Honours)","0"},
		{"175","/prospectus/course/1374/","MBusiness","","0"},
		{"176","/prospectus/course/246/","Mechanical Engineering","BEng (Honours)","0"},
		{"177","/prospectus/course/252/","Mechanical Engineering","FdEng","0"},
		{"178","/prospectus/course/579/","Mechanical Engineering","BEng (Honours)","0"},
		{"179","/prospectus/course/730/","Mechanical Engineering","MEng","0"},
		{"180","/prospectus/course/765/","Media","BA (Honours)","0"},
		{"181","/prospectus/course/713/","Midwifery","BSc (Honours)","0"},
		{"182","/prospectus/course/1179/","Nursing (Adult)","BSc (Honours)","0"},
		{"183","/prospectus/course/1210/","Nursing (Child)","BSc (Honours)","0"},
		{"184","/prospectus/course/1211/","Nursing (Mental Health)","BSc (Honours)","0"},
		{"185","/prospectus/course/771/","Nutrition and Public Health","BSc (Honours)","0"},
		{"186","/prospectus/course/635/","Nutrition, Diet and Lifestyle","BSc (Honours)","0"},
		{"187","/prospectus/course/734/","Occupational Therapy","BSc (Honours)","0"},
		{"188","/prospectus/course/1058/","Occupational Therapy (practice based learning)","BSc (Honours)","0"},
		{"189","/prospectus/course/599/","Operating Department Practice","BSc (Honours)","0"},
		{"190","/prospectus/course/625/","Paramedic Practice","DipHE","0"},
		{"191","/prospectus/course/507/","Performance and Professional Practice (top up)","BA (Honours)","0"},
		{"192","/prospectus/course/909/","Performance for Stage and Screen","BA (Honours)","0"},
		{"193","/prospectus/course/792/","Photography","BA (Honours)","0"},
		{"194","/prospectus/course/1005/","Photography","MArt","0"},
		{"195","/prospectus/course/1261/","Physical Activity, Sport and Health","BSc (Honours)","0"},
		{"196","/prospectus/course/312/","Physical Education and School Sport","BSc (Honours)","0"},
		{"197","/prospectus/course/739/","Physical Education and School Sport","BSc (Honours)","0"},
		{"198","/prospectus/course/1362/","Physics","BSc (Honours)","0"},
		{"199","/prospectus/course/628/","Physiotherapy","BSc (Honours)","0"},
		{"200","/prospectus/course/1059/","Physiotherapy (practice-based learning)","BSc (Honours)","0"},
		{"201","/prospectus/course/381/","Planning and Geography","BA (Honours)","0"},
		{"202","/prospectus/course/699/","Planning and Geography","BA (Honours)","0"},
		{"203","/prospectus/course/780/","Politics","BA (Honours)","0"},
		{"204","/prospectus/course/815/","Post-16 and Further Education","CertEd","0"},
		{"205","/prospectus/course/613/","Preparatory year in Biosciences and Chemistry","Extended Degree Programme","0"},
		{"206","/prospectus/course/1232/","Pre-sessional English for Academic Purposes","","0"},
		{"207","/prospectus/course/555/","Primary Education (5-11) with QTS","BA (Honours)","0"},
		{"208","/prospectus/course/496/","Product Design","BA (Honours)","0"},
		{"209","/prospectus/course/1245/","Product Design","BA (Honours)","0"},
		{"210","/prospectus/course/677/","Product Design: Furniture","BA (Honours)","0"},
		{"211","/prospectus/course/1246/","Product Design: Furniture","BA (Honours)","0"},
		{"212","/prospectus/course/722/","Psychology","BSc (Honours)","0"},
		{"213","/prospectus/course/876/","Public Relations","BA (Honours)","0"},
		{"214","/prospectus/course/686/","Public Relations and Media","BA (Honours)","0"},
		{"215","/prospectus/course/374/","Quantity Surveying","BSc (Honours)","0"},
		{"216","/prospectus/course/711/","Quantity Surveying","BSc (Honours)","0"},
		{"217","/prospectus/course/751/","Radiotherapy and Oncology","BSc (Honours)","0"},
		{"218","/prospectus/course/441/","Railway Engineering","FdEng","0"},
		{"219","/prospectus/course/1168/","Railway Engineering","HNC","0"},
		{"220","/prospectus/course/1088/","Real Estate","BSc (Honours)","0"},
		{"221","/prospectus/course/1320/","Real Estate Management (top up)","BSc (Honours)","0"},
		{"222","/prospectus/course/550/","Science with Education and Qualified Teacher Status","BSc (Honours)","0"},
		{"223","/prospectus/course/623/","Social Work","BA (Honours)","0"},
		{"224","/prospectus/course/759/","Sociology","BA (Honours)","0"},
		{"225","/prospectus/course/494/","Software Engineering","BEng (Honours)","0"},
		{"226","/prospectus/course/690/","Software Engineering","MEng","0"},
		{"227","/prospectus/course/185/","Specialist Community Public Health Nursing","Health Visiting/School Nursing","0"},
		{"228","/prospectus/course/394/","Specialist Practice District Nursing","BSc (Honours)","0"},
		{"229","/prospectus/course/746/","Sport and Exercise Science","BSc (Honours)","0"},
		{"230","/prospectus/course/787/","Sport Business Management","BSc (Honours)","0"},
		{"231","/prospectus/course/776/","Sport Coaching","BSc (Honours)","0"},
		{"232","/prospectus/course/741/","Sport Development with Coaching","BSc (Honours)","0"},
		{"233","/prospectus/course/798/","Sport Studies","BA (Honours)","0"},
		{"234","/prospectus/course/1014/","Teaching and Learning in Early Years and Primary Education (3-7) with QTS (top up)","BA (Honours)","0"},
		{"235","/prospectus/course/1012/","Teaching and Learning in Primary Education (5-11) with QTS (top up)","BA (Honours)","0"},
		{"236","/prospectus/course/913/","Tourism and Hospitality Business Management (top up)","BSc (Honours)","0"},
		{"237","/prospectus/course/928/","Tourism Management (top up)","BSc (Honours)","0"},
		{"238","/prospectus/course/583/","University English Scheme","Short course","0"},
		{"239","/prospectus/course/498/","Working with Children, Young People and Families","Foundation Degree","0"},
		{"240","/prospectus/course/1048/","Working with Children, Young People and Families","Foundation Degree","0"},
		{"241","/prospectus/course/1302/","Working with Children, Young People and Families (top up)","BA (Honours)","0"},
		{"242","/prospectus/course/978/","Youth and Community Work","BA (Honours)","0"}
	};
	
	public static String[][] PostData={
		{"1","/prospectus/course/300/","Accounting and Finance","MSc","0"},
		{"2","/prospectus/course/1206/","Accounting and Finance","ACCA Fast Track Route","0"},
		{"3","/prospectus/course/1378/","Acute and Critical Care of the Child","PgCert","0"},
		{"4","/prospectus/course/1347/","Advanced Computer Networks","MSc","0"},
		{"5","/prospectus/course/392/","Advanced Engineering","MSc","0"},
		{"6","/prospectus/course/482/","Advanced Engineering and Management","MSc","0"},
		{"7","/prospectus/course/483/","Advanced Materials Engineering","MSc","0"},
		{"8","/prospectus/course/144/","Advanced Mechanical Engineering","MSc","0"},
		{"9","/prospectus/course/1317/","Advanced Sport Coaching Practice","PgDip","0"},
		{"10","/prospectus/course/1298/","Advancing Physiotherapy Practice","MSc","0"},
		{"11","/prospectus/course/107/","Advancing Practice (Radiotherapy and Oncology)","MSc/PgDip/PgCert","0"},
		{"12","/prospectus/course/837/","Advancing Professional Practice","MSc/PgDip/PgCert","0"},
		{"13","/prospectus/course/1083/","Analytical Chemistry","MSc/PgDip/PgCert","0"},
		{"14","/prospectus/course/961/","Animation and Digital Effects","MA","0"},
		{"15","/prospectus/course/1292/","Applied Human Rights","MA","0"},
		{"16","/prospectus/course/284/","Applied Sport and Exercise Science","MSc/PgDip/PgCert","0"},
		{"17","/prospectus/course/1356/","Approved Mental Health Professional (AMHP)","PgCert","0"},
		{"18","/prospectus/course/1259/","Architecture","MArch","0"},
		{"19","/prospectus/course/138/","Arts and Cultural Management","MA","0"},
		{"20","/prospectus/course/344/","Association of Chartered Certified Accountants Fundamental Levels","ACCA","0"},
		{"21","/prospectus/course/259/","Autism and Asperger Syndrome","PgCert","0"},
		{"22","/prospectus/course/201/","Autism Spectrum","MA/PgDip/PgCert","0"},
		{"23","/prospectus/course/1158/","Automation, Control and Robotics","MSc","0"},
		{"24","/prospectus/course/317/","Banking and Finance","MSc","0"},
		{"25","/prospectus/course/1250/","Big Data Analytics","MSc","0"},
		{"26","/prospectus/course/1111/","Biomedical Laboratory Sciences","MSc/PgDip/PgCert","0"},
		{"27","/prospectus/course/1087/","Biomedical Sciences","MSc/PgDip/PgCert","0"},
		{"28","/prospectus/course/1082/","Biotechnology","MSc/PgDip/PgCert","0"},
		{"29","/prospectus/course/1112/","Building Surveying","MSc","0"},
		{"30","/prospectus/course/371/","Built Environment","MPhil/PhD","0"},
		{"31","/prospectus/course/214/","Business","MRes","0"},
		{"32","/prospectus/course/431/","Business Administration","Doctorate","0"},
		{"33","/prospectus/course/326/","Business and English (pre-masters course)","Graduate Diploma","0"},
		{"34","/prospectus/course/330/","Business and Management Programme","PhD","0"},
		{"35","/prospectus/course/814/","Clinical Cognitive Neuroscience","MSc","0"},
		{"36","/prospectus/course/353/","Coaching and Mentoring","MSc/PgDip/PgCert","0"},
		{"37","/prospectus/course/391/","Computer and Network Engineering","MSc","0"},
		{"38","/prospectus/course/1325/","Computer Science","MSc","0"},
		{"39","/prospectus/course/1329/","Computing","MSc","0"},
		{"40","/prospectus/course/1116/","Construction Project Management","MSc/PgDip/PgCert","0"},
		{"41","/prospectus/course/172/","CPD in Design and Technology Education","","0"},
		{"42","/prospectus/course/137/","Creative Writing","MA","0"},
		{"43","/prospectus/course/1256/","Design (Fashion)","MA/MFA","0"},
		{"44","/prospectus/course/229/","Design (Graphics)","MA/MFA","0"},
		{"45","/prospectus/course/1257/","Design (Illustration)","MA/MFA","0"},
		{"46","/prospectus/course/1254/","Design (Interior)","MA/MFA","0"},
		{"47","/prospectus/course/135/","Design (Jewellery and Metalwork)","MA/MFA","0"},
		{"48","/prospectus/course/124/","Design (Packaging)","MA/MFA","0"},
		{"49","/prospectus/course/134/","Design (Product)","MA/MFA","0"},
		{"50","/prospectus/course/1051/","Design and Technology Education","CertHE","0"},
		{"51","/prospectus/course/835/","Developmental Psychology","MSc","0"},
		{"52","/prospectus/course/1348/","Diagnostic Imaging","MSc/PgDip/PgCert","0"},
		{"53","/prospectus/course/1279/","Early Childhood Education and Care (0-5) with EYTS (graduate employment route)","PGCE","0"},
		{"54","/prospectus/course/1342/","Early Childhood Education and Care (0-5) with EYTS (graduate entry route)","PGCE","0"},
		{"55","/prospectus/course/192/","Early Years and Primary Education (3-7) with QTS","PGCE","0"},
		{"56","/prospectus/course/215/","Education","Doctorate in","0"},
		{"57","/prospectus/course/1115/","Education","MA/PgDip/PgCert","0"},
		{"58","/prospectus/course/216/","Education","Research Degree","0"},
		{"59","/prospectus/course/1076/","English","MPhil/PhD","0"},
		{"60","/prospectus/course/1203/","English by Research","MA","0"},
		{"61","/prospectus/course/145/","Environmental Management","MSc/PgDip/PgCert","0"},
		{"62","/prospectus/course/1283/","Executive MBA","","0"},
		{"63","/prospectus/course/1287/","Executive MBA (Built Environment)","","0"},
		{"64","/prospectus/course/1285/","Executive MBA (Education)","","0"},
		{"65","/prospectus/course/356/","Executive MBA (Facilities Management)","","0"},
		{"66","/prospectus/course/1286/","Executive MBA (Psychology)","","0"},
		{"67","/prospectus/course/1326/","Fashion Management and Communication","MA","0"},
		{"68","/prospectus/course/1078/","Film Studies","MPhil/PhD","0"},
		{"69","/prospectus/course/1336/","Filmmaking","MA","0"},
		{"70","/prospectus/course/1085/","Finance and Investment","MSc","0"},
		{"71","/prospectus/course/347/","Financial Management","MSc","0"},
		{"72","/prospectus/course/1205/","Financial Management","CIMA Fast Track Route","0"},
		{"73","/prospectus/course/136/","Fine Art","MA/MFA","0"},
		{"74","/prospectus/course/1189/","Food Consumer Marketing and Product Development","MSc","0"},
		{"75","/prospectus/course/1321/","Food Manufacturing Engineering","MSc","0"},
		{"76","/prospectus/course/104/","Forensic Accounting","MSc","0"},
		{"77","/prospectus/course/1365/","Games Design","MA","0"},
		{"78","/prospectus/course/131/","Games Software Development","MSc","0"},
		{"79","/prospectus/course/139/","Geographical Information Systems","MSc/PgDip/PgCert","0"},
		{"80","/prospectus/course/1364/","Global Commercial Law","LLM/PgDip/PgCert","0"},
		{"81","/prospectus/course/263/","Health and Social Care Leadership","MSc/PgDip/PgCert","0"},
		{"82","/prospectus/course/166/","Health Psychology","MSc","0"},
		{"83","/prospectus/course/868/","Healthcare Education","MSc/PgDip/PgCert","0"},
		{"84","/prospectus/course/1077/","History","MPhil/PhD","0"},
		{"85","/prospectus/course/1337/","History by Research","MA","0"},
		{"86","/prospectus/course/126/","Human Resource Management","MSc/PgDip/PgCert","0"},
		{"87","/prospectus/course/315/","Human Resource Management","MSc","0"},
		{"88","/prospectus/course/396/","Industrial Management","MBA","0"},
		{"89","/prospectus/course/432/","Information Systems Security","MSc","0"},
		{"90","/prospectus/course/442/","Information Technology Management","MSc","0"},
		{"91","/prospectus/course/334/","International Business Management","MSc","0"},
		{"92","/prospectus/course/839/","International Events and Conference Management","MSc","0"},
		{"93","/prospectus/course/316/","International Hospitality and Tourism Management","MSc","0"},
		{"94","/prospectus/course/294/","International Hospitality Management","MSc","0"},
		{"95","/prospectus/course/823/","International Human Resource Management","MSc","0"},
		{"96","/prospectus/course/1289/","International Journalism","MA","0"},
		{"97","/prospectus/course/335/","International Marketing","MSc","0"},
		{"98","/prospectus/course/298/","International Tourism Management","MSc","0"},
		{"99","/prospectus/course/1290/","Journalism","MA","0"},
		{"100","/prospectus/course/228/","Learning and Teaching in Higher Education","PgCert","0"},
		{"101","/prospectus/course/1233/","Learning English for Academic Purposes (LEAP)","","0"},
		{"102","/prospectus/course/674/","Learning Languages at Sheffield Hallam University","","0"},
		{"103","/prospectus/course/467/","Logistics and Supply Chain Management","MSc","0"},
		{"104","/prospectus/course/329/","Managing Global Business","MSc","0"},
		{"105","/prospectus/course/1297/","Manual Therapy","MSc","0"},
		{"106","/prospectus/course/1028/","Masters in Law by Research","LLM","0"},
		{"107","/prospectus/course/1314/","Mathematics Education","CertHE","0"},
		{"108","/prospectus/course/1282/","MBA","","0"},
		{"109","/prospectus/course/1132/","Medical Leadership","MSc/PgDip/PgCert","0"},
		{"110","/prospectus/course/276/","Medical Ultrasound Programme","MSc/PgDip/PgCert","0"},
		{"111","/prospectus/course/1080/","Molecular and Cell Biology","MSc/PgDip/PgCert","0"},
		{"112","/prospectus/course/122/","Networking Professional (incorporating Cisco certificate training)","MSc","0"},
		{"113","/prospectus/course/1369/","Non-medical Prescribing","","0"},
		{"114","/prospectus/course/195/","Nursing (Adult)","PgDip","0"},
		{"115","/prospectus/course/1372/","Nursing (Child)","PgDip","0"},
		{"116","/prospectus/course/1373/","Nursing (Mental Health)","PgDip","0"},
		{"117","/prospectus/course/1140/","Nursing (Public Health)","MSc","0"},
		{"118","/prospectus/course/1171/","Nursing (top up)","MSc","0"},
		{"119","/prospectus/course/822/","Nutrition with Public Health Management","MSc","0"},
		{"120","/prospectus/course/1058/","Occupational Therapy (practice based learning)","BSc (Honours)","0"},
		{"121","/prospectus/course/304/","Occupational Therapy (pre-registration)","MSc","0"},
		{"122","/prospectus/course/1359/","Oil and Gas Management","MSc","0"},
		{"123","/prospectus/course/465/","Pharmaceutical Analysis","MSc/PgDip/PgCert","0"},
		{"124","/prospectus/course/1360/","Pharmacist Independent Prescribing","","0"},
		{"125","/prospectus/course/387/","Pharmacology and Biotechnology","MSc/PgDip/PgCert","0"},
		{"126","/prospectus/course/1349/","Physician Associate Studies","PgDip","0"},
		{"127","/prospectus/course/1375/","Physiotherapy (Pre Registration)","MSc","0"},
		{"128","/prospectus/course/819/","Post-16 and Further Education","PGCE","0"},
		{"129","/prospectus/course/1315/","Post-16 and Further Education (Special Educational Needs)","PGCE","0"},
		{"130","/prospectus/course/1376/","Practice and Procedure of Sports Law","LLM","0"},
		{"131","/prospectus/course/1232/","Pre-sessional English for Academic Purposes","","0"},
		{"132","/prospectus/course/1340/","Primary (5-11) PE Specialist with QTS","PGCE","0"},
		{"133","/prospectus/course/193/","Primary Education (5-11) With QTS","PGCE","0"},
		{"134","/prospectus/course/1291/","Professional Business Practice (Sales and Sales Leadership)","MSc/PgDip/PgCert/Short Course","0"},
		{"135","/prospectus/course/357/","Project Management","MSc/PgDip/PgCert","0"},
		{"136","/prospectus/course/1308/","Prostate Cancer Care","MSc/PgDip/PgCert","0"},
		{"137","/prospectus/course/419/","Psychology","MSc","0"},
		{"138","/prospectus/course/415/","Public Health","MSc","0"},
		{"139","/prospectus/course/383/","Public Relations","MA","0"},
		{"140","/prospectus/course/1235/","Quality Management","MSc/PgDip/PgCert","0"},
		{"141","/prospectus/course/1117/","Quantity Surveying","MSc/PgDip/PgCert","0"},
		{"142","/prospectus/course/273/","Radiotherapy and Oncology","MSc/PgDip/PgCert","0"},
		{"143","/prospectus/course/160/","Radiotherapy and Oncology in Practice","PgDip","0"},
		{"144","/prospectus/course/1039/","Radiotherapy Planning","MSc/PgDip/PgCert","0"},
		{"145","/prospectus/course/359/","Real Estate","MSc","0"},
		{"146","/prospectus/course/1186/","Research Degrees","Biomolecular Sciences Research Centre","0"},
		{"147","/prospectus/course/1339/","Research Degrees","Centre for Health and Social Care Research","0"},
		{"148","/prospectus/course/190/","Research Degrees","Cultural, Communication and Computing Research Institute","0"},
		{"149","/prospectus/course/840/","Research Degrees","Materials and Engineering Research Institute","0"},
		{"150","/prospectus/course/1338/","Research Degrees","Sport and Physical Activity","0"},
		{"151","/prospectus/course/1368/","Return to practice (nursing and health visiting)","","0"},
		{"152","/prospectus/course/342/","Risk Management","MSc","0"},
		{"153","/prospectus/course/204/","Secondary Business Education","PGCE","0"},
		{"154","/prospectus/course/389/","Secondary Citizenship","PGCE","0"},
		{"155","/prospectus/course/207/","Secondary Computing","PGCE","0"},
		{"156","/prospectus/course/205/","Secondary Design and Technology","PGCE","0"},
		{"157","/prospectus/course/198/","Secondary Design and Technology (Food Technology)","PGCE","0"},
		{"158","/prospectus/course/153/","Secondary Design and Technology (Textiles)","PGCE","0"},
		{"159","/prospectus/course/1273/","Secondary Geography","PGCE","0"},
		{"160","/prospectus/course/1274/","Secondary History","PGCE","0"},
		{"161","/prospectus/course/208/","Secondary Mathematics","PGCE","0"},
		{"162","/prospectus/course/209/","Secondary Modern Foreign Languages","PGCE","0"},
		{"163","/prospectus/course/237/","Secondary Physical Education","PGCE","0"},
		{"164","/prospectus/course/210/","Secondary Religious Education","PGCE","0"},
		{"165","/prospectus/course/1095/","Secondary Science (Biology)","PGCE","0"},
		{"166","/prospectus/course/1094/","Secondary Science (Chemistry)","PGCE","0"},
		{"167","/prospectus/course/1092/","Secondary Science (Physics)","PGCE","0"},
		{"168","/prospectus/course/453/","Social Sciences","MPhil/PhD","0"},
		{"169","/prospectus/course/410/","Social Sciences Programme","MRes","0"},
		{"170","/prospectus/course/470/","Social Work","MSW","0"},
		{"171","/prospectus/course/110/","Sociology, Planning and Policy","MRes","0"},
		{"172","/prospectus/course/1174/","Special Educational Needs Coordination","PgCert","0"},
		{"173","/prospectus/course/1138/","Specialist Practice District Nursing","MSc/PgDip","0"},
		{"174","/prospectus/course/960/","Sport and Exercise Psychology","MSc","0"},
		{"175","/prospectus/course/302/","Sport Business Management","MSc/PgDip/PgCert","0"},
		{"176","/prospectus/course/900/","Sports Engineering","MSc","0"},
		{"177","/prospectus/course/162/","Sports Journalism","MA","0"},
		{"178","/prospectus/course/859/","Supportive and Palliative Care","MSc/PgDip/PgCert","0"},
		{"179","/prospectus/course/395/","Teaching English for Academic Purposes","PgCert","0"},
		{"180","/prospectus/course/1300/","Teaching English to Speakers of Other Languages (TESOL)","MA/PgDip/PgCert","0"},
		{"181","/prospectus/course/1301/","Teaching English to Speakers of Other Languages (TESOL)","MEd/PgDip/PgCert","0"},
		{"182","/prospectus/course/1306/","Teaching English to Speakers of Other Languages (TESOL) English for Academic Purposes (EAP)","MEd/PgDip/PgCert","0"},
		{"183","/prospectus/course/438/","Technical Architecture","MSc/PgDip/PgCert","0"},
		{"184","/prospectus/course/970/","Telecommunication and Electronic Engineering","MSc","0"},
		{"185","/prospectus/course/121/","Total Quality Management and Organisational Excellence","MSc/PgDip/PgCert","0"},
		{"186","/prospectus/course/583/","University English Scheme","Short course","0"},
		{"187","/prospectus/course/435/","Urban Planning","MSc/PgDip/PgCert","0"},
		{"188","/prospectus/course/450/","Urban Regeneration","MSc/PgDip/PgCert","0"},
		{"189","/prospectus/course/285/","Vocational Rehabilitation","PgCert","0"},
		{"190","/prospectus/course/1367/","Wealth Management","MSc","0"},
		{"191","/prospectus/course/485/","Youth Work","Graduate Diploma","0"}
		};
}
