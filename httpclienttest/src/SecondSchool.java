import java.io.*;

import org.htmlparser.*;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;  
import java.net.URISyntaxException;  
import java.net.URL;
import java.util.*;  
import java.util.regex.Matcher;
import java.util.regex.Pattern;
  






import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;





import org.apache.http.*;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.utils.URIUtils;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;

public class SecondSchool {
	public static String FILE_PATH="d:\\ASTON";
	public static void main(String[] args) throws Exception
	{
		//[Tuition Fee] Need to check the website and adjust.
		//AstonGetDetails("http://www.aston.ac.uk/study/undergraduate/courses/school/eas/beng-design-engineering/");
		WriteToExcel();
	}
	
	public static void WriteToExcel()
	{
		File outputFile = new File(FILE_PATH + "\\" + "gen_data.xls");
		OutputStream os = null;
		WritableWorkbook book=null;
		WritableSheet sheet=null;
		try {
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			os = new FileOutputStream(outputFile);
		Label label;
		String[] Keys={"School","Level","Title","Type","Application Fee","Tuition Fee",
				"Academic Entry Requirement","IELTS Average Requirement",
				"IELTS Lowest Requirement","Structure","Length (months)","Month of Entry",
				"Scholarship"};
		book = Workbook.createWorkbook(os);
		sheet = book.createSheet("sheet1", 0);
		label = new Label(0, 0, "School");
		sheet.addCell(label);
		label = new Label(1, 0, "Level");
		sheet.addCell(label);
		label = new Label(2, 0, "Title");
		sheet.addCell(label);
		label = new Label(3, 0, "Type");
		sheet.addCell(label);
		label = new Label(4, 0, "Application Fee");
		sheet.addCell(label);
		label = new Label(5, 0, "Tuition Fee");
		sheet.addCell(label);
		label = new Label(6, 0, "Academic Entry Requirement");
		sheet.addCell(label);
		label = new Label(7, 0, "IELTS Average Requirement");
		sheet.addCell(label);
		label = new Label(8, 0, "IELTS Lowest Requirement");
		sheet.addCell(label);
		label = new Label(9, 0, "Structure");
		sheet.addCell(label);
		label = new Label(10, 0, "Length (months)");
		sheet.addCell(label);
		label = new Label(11, 0, "Month of Entry");
		sheet.addCell(label);
		label = new Label(12, 0, "Scholarship");
		sheet.addCell(label);
        HashMap<String,String> data=AstonGetDetails("http://www.aston.ac.uk/study/undergraduate/courses/school/eas/beng-design-engineering/");
		String title = null;
		int i = 1;
		int j=0;
		for(i=1;i<2;i++)
		{
			for(j=0;j<13;j++)
			{
				label = new Label(j, i, data.get(Keys[j]));
				sheet.addCell(label);
			}
		}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}finally{
			try {
				book.write();
				book.close();
				os.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
	}
	
	public static HashMap<String,String> AstonGetDetails(String url) throws Exception
	{
		HashMap<String,String> result=new HashMap<String,String>();
		HttpClient httpclient = new DefaultHttpClient();
		//String url="http://www.aston.ac.uk/study/undergraduate/courses/school/eas/beng-design-engineering/";
		HttpGet httpGet = new HttpGet(url); 
		HttpResponse response = httpclient.execute(httpGet);  
		HttpEntity entity = response.getEntity();
		String htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity).replace("\t", " ");
		    //System.out.println(htmls);
		    
		     
		}
		Parser parser=null;
	    HtmlPage page=new HtmlPage(parser); 
	    if(htmls.contains("September")||htmls.contains("september"))
	    {
	    	result.put("Month of Entry", "9");
	    }
	    else if(htmls.contains("October")||htmls.contains("october"))
	    {
	    	result.put("Month of Entry", "10");
	    }
	    else
	    {
	    	result.put("Month of Entry", "");
	    }
	    
	    //**************************get name*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("h1"),
                new HasAttributeFilter("id","skiplinks"));//id="skiplinks"
        NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
        for(int i=0;i<nodes2.size();i++)
	    {
	    	
	    	Node node=(Node)nodes2.elementAt(i);
	    	System.out.println("Title:"+html2Str(node.toHtml()));
	    	System.out.println("Type:"+GetType(html2Str(node.toHtml())));
	    	result.put("Title",html2Str(node.toHtml()));
	    	result.put("Type",GetType(html2Str(node.toHtml())));
	    	break;
	    }
        
      //**************************get school*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter SchoolFilter=new AndFilter(new TagNameFilter("span"),
                new HasAttributeFilter("class","breadcrumb5"));
        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
        if(nodes3.size()>0)
        {
        	for(int i=0;i<nodes3.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes3.elementAt(i);
    	    	if(!html2Str(node.toHtml()).trim().equals(""))
    	    	//System.out.println(html2Str(node.toHtml()).trim());
    	    	//System.out.println(node.toHtml());
    	    	parser=Parser.createParser(node.toHtml(), "utf-8");
    	    	TagNameFilter filter=new TagNameFilter("a");
    	    	NodeList nodes4=parser.extractAllNodesThatMatch(filter);
    	    	if(nodes4.size()==1)
    	    	{
    	    		Node node4=(Node)nodes4.elementAt(0);
    	    		String school=html2Str(node4.toHtml()).trim();
    	    		System.out.println("School:"+school);
    	    		result.put("School",school);
    	    		if(school.equals("EAS"))
    	    		{
    	    			result.put("Tuition Fee", "16500");
    	    		}
    	    		else if(school.equals("LHS"))
    	    		{
    	    			result.put("Tuition Fee", "16500");
    	    		}
    	    		else if(school.equals("LSS"))
    	    		{
    	    			result.put("Tuition Fee", "13500");
    	    		}
    	    		else if(school.equals("ABS"))
    	    		{
    	    			result.put("Tuition Fee", "13500");
    	    		}
    	    		/*International students

    	    		Aston Business School £13,500
    	    		Languages and Social Sciences - £13,500
    	    		EAS programmes - £14,500 - 16,500
    	    		LHS programmes - £13,500 - £16,500

    	    		Placement Year: £2,500 for International Students*/
    	    	}
    	    	
    	    }
        }
        else
        {
        	/*
        	SchoolFilter=new AndFilter(new TagNameFilter("span"),
                    new HasAttributeFilter("class","breadcrumb4"));
            nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
            for(int i=0;i<nodes3.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes3.elementAt(i);
    	    	System.out.println(html2Str(node.toHtml()));
    	    	
    	    }
    	    */
        }
        
        
        
        
        
        
     
		
	    
	    //**************************get Titles*************************
	    parser=Parser.createParser(htmls, "utf-8");
	    AndFilter filter =
                new AndFilter(
                              new TagNameFilter("a"),
                             new HasAttributeFilter("class","panel-event"));
	    NodeList nodes = parser.extractAllNodesThatMatch(filter);        
        //i=0,Entry Requirements
        //i=1,Course Outline
        int index=0;
        int RequireIndex=-1,CourseIndex=-1;
	    for(int i=0;i<nodes.size();i++)
	    {
	    	
	    	Node node=(Node)nodes.elementAt(i);
	    	String tmp=html2Str(node.toHtml());
	    	if(!tmp.equals("Expand / Collapse"))
	    	{
	    		if(tmp.contains("Entry Requirements"))
	    			RequireIndex=index;
	    		else if(tmp.contains("Course Outline")||tmp.contains("Course outline"))
	    			CourseIndex=index;
	    		
	    		index++;
	    	}
	    	
	    	
	    }
	    
	    //**************************get entry and structure*************************
        parser=Parser.createParser(htmls, "utf-8");
	    filter =
                new AndFilter(
                              new TagNameFilter("div"),
                             new HasAttributeFilter("class","tab-body-inner"));
        nodes = parser.extractAllNodesThatMatch(filter);        
        //i=0,Entry Requirements
        //i=1,Course Outline
	    /*for(int i=0;i<nodes.size();i++)
	    {
	    	
	    	Node node=(Node)nodes.elementAt(i);
	    	if(i==0) System.out.println("Entry Requirements and Tuition Fees:");
	    	else if(i==1) System.out.println("Structure:");
	    	else break;
            System.out.println(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
	    	//System.out.println(node.toHtml());
	    	
	    }
	    System.out.println(nodes.size());*/
        Node node=(Node)nodes.elementAt(RequireIndex);
        System.out.println("Entry Requirements and Tuition Fees:\n");
        String entry=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
        System.out.println(entry);
        result.put("Academic Entry Requirement",entry);
        
        //for undergraduates
        if(entry.contains("3 years"))
        {
        	result.put("Length (months)","36");
        }
        else if(entry.contains("4 years"))
        {
        	result.put("Length (months)","48");
        }
        
        
        node=(Node)nodes.elementAt(CourseIndex);
        System.out.println("Structure:\n");
        String structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
	    System.out.println(structure);
	    result.put("Structure",structure.trim());
	    
	    result.put("Level", "Undergraduate");
	   /* 预科项目要求申请人雅思总成绩不低于5.5分，其中听说读写各单项不得低于5.0分。
	  　　工程与应用科学学院，逻辑、物流管理计算项目要求申请人雅思总成绩不得低于6.5分，其中阅读和听力部分不得低于5.5分，写作和口语部分不得低于6.0分。
	  　　阿斯顿商学院本科项目、生命与健康科学项目要求申请人雅思总成绩不得低于6.5分，其中听说读写各单项不得低于6.0分。*/
	    result.put("IELTS Average Requirement", "6.5");
		
	    result.put("IELTS Lowest Requirement", "6");
	    result.put("Scholarship", "Aston Excellence Scholarship:3000;Income-based scholarships:3000;Placement Year/Year Abroad Scholarships:1000;");
	    
        return result;
	}
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	public static String GetType(String input)//BA BEng Bsc Msc MEng 
	{
		String types="BA;BEng;Bsc;MSc;MEng;Double MA;Joint MA;MA";
		String[] array=types.split(";");
		for(int i=0;i<array.length;i++)
		{
			if(input.contains(array[i]))
			{
				return array[i];
			}
		}
		return null;
	}
	
	//Aston University Pattern
		public static void AstonGetLinks() throws Exception
		{

			FileInputStream in=new FileInputStream(new File("Undergraduate courses.html"));
			byte[] data=new byte[100000];
			in.read(data);
			
			//System.out.println(new String(data));
			String htmls=new String(data);
			Parser parser=Parser.createParser(htmls, "utf-8");
		    HtmlPage page=new HtmlPage(parser); 
		    
		    AndFilter filter =
	                new AndFilter(
	                              new TagNameFilter("div"),
	                             new HasAttributeFilter("data-href"));
	        NodeList nodes = parser.extractAllNodesThatMatch(filter);
		    //System.out.println("nodelist.size();"+nodes.size());
		    //ImageTag imageTag = null;
		    for(int i=0;i<nodes.size();i++)
		    {
		    	
		    	Node node=(Node)nodes.elementAt(i);
	              //  System.out.println( node.toHtml());            
		    	TagNode tagNode = new TagNode();//通过TagNode获得属性，只有将Node转换为TagNode才能获取某一个标签的属性
	            tagNode.setText(node.toHtml());
	            System.out.println(tagNode.getAttribute("data-href"));
		    	
		    	
		    }
			
			in.close();
		}

}
