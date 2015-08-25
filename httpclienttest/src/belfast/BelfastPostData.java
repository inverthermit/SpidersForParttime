package belfast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class BelfastPostData {

	public static String[] types={"BEng Honours",
			"MEng Honours",
			"Foundation Degree (FdEng)",
			"BSc Honours",
			"BSc (Econ) Joint Honours",
			"BA Honours",
			"MSci Honours",
			"BA Single Honours",
			"BA Joint Honours",
			"BSW Honours",
			"BSc Single Honours",
			"BSc Joint Honours",
			"Divinity",
			"Theology",
			"LLB Honours",
			"BSc (Econ) Single Honours"};
	public static String FILE_PATH="d:\\BELFAST";
	public static void main(String[] args) throws Exception
	{
		//[Tuition Fee] Need to check the website and adjust.
		//BelfastGetDetails("http://www.qub.ac.uk/home/StudyatQueens/CourseFinder/UG/Chemistry/F100/");
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
		
		BufferedReader rr=new BufferedReader(new FileReader("./file/belfastUn.txt"));
		int i = 1;
		int j=0;
		String url="";
		while((url = rr.readLine()) != null)
		{
			if(i>=294)
			{
				System.out.println(i+":"+url);
				//url=rr.readLine();
				HashMap<String,String> data=BelfastGetDetails(url);
				for(j=0;j<13;j++)
					{
						label = new Label(j, i, data.get(Keys[j]));
						sheet.addCell(label);
					}
			}
			
			i++;
		}
		rr.close();
		
       
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
	
	public static HashMap<String,String> BelfastGetDetails(String url) throws Exception
	{
		HashMap<String,String> result=new HashMap<String,String>();
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url); 
		HttpResponse response = httpclient.execute(httpGet);  
		HttpEntity entity = response.getEntity();
		//System.out.println("Got reply!");
		String htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity).replace("\t", " ");
		    //System.out.println(htmls);
		    
		     
		}
		htmls=HTMLFilter(htmls);
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
	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","course-title"));//id="skiplinks"
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
                new HasAttributeFilter("id","school-name"));
        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
        if(nodes3.size()>0)
        {
        	for(int i=0;i<nodes3.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes3.elementAt(i);
    	    	String school=html2Str(node.toHtml()).trim();
    	    	result.put("School",school);
    	    	break;
    	    	/*if(!html2Str(node.toHtml()).trim().equals(""))
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
    	    		International students

    	    		Aston Business School 拢13,500
    	    		Languages and Social Sciences - 拢13,500
    	    		EAS programmes - 拢14,500 - 16,500
    	    		LHS programmes - 拢13,500 - 拢16,500

    	    		Placement Year: 拢2,500 for International Students
    	    	}*/
    	    	
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
        
        
        
        
        
        
     
		
	    
	    
	    
	    
	    
	    //**************************get entry and structure*************************
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter filter =
                new AndFilter(
                              new TagNameFilter("div"),
                             new HasAttributeFilter("id","entrance"));
        NodeList nodes = parser.extractAllNodesThatMatch(filter);
        Node node=null;
        String entry="";
        	node=(Node)nodes.elementAt(0);
            System.out.println("Entry Requirements and Tuition Fees:\n");
            entry=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
            entry=HTMLFilter(entry);
            System.out.println(entry);
            result.put("Academic Entry Requirement",entry);
            
            ArrayList<String> list = new ArrayList<String>();
            if(entry.contains("7.0"))
            {
            	list.add("7.0");
            }
            if(entry.contains("6.5"))
            {
            	list.add("6.5");
            }
            if(entry.contains("6.0"))
            {
            	list.add("6.0");
            }
            if(entry.contains("5.5"))
            {
            	list.add("5.5");
            }
            if(list.size()==1)
            {
            	result.put("IELTS Average Requirement", list.get(0));
            	result.put("IELTS Lowest Requirement", list.get(0));
            }
            else if(list.size()>=2)
            {
            	result.put("IELTS Average Requirement", list.get(0));
            	result.put("IELTS Lowest Requirement", list.get(1));
            }
            else
            {
            	result.put("IELTS Average Requirement","6.0");
                
        	    result.put("IELTS Lowest Requirement", "5.5");
            }
            
            parser=Parser.createParser(htmls, "utf-8");
    	    filter =
                    new AndFilter(
                                  new TagNameFilter("div"),
                                 new HasAttributeFilter("id","course-info"));
            nodes = parser.extractAllNodesThatMatch(filter);
            node=(Node)nodes.elementAt(0);
            System.out.println("Structure:\n");
            String structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
            structure=HTMLFilter(structure);
            System.out.println(structure);
    	    result.put("Structure",structure.trim());
        
        
        
        
        //for undergraduates
        if(entry.contains("3 years")||entry.contains("3 year")||entry.contains("3 yrs"))
        {
        	result.put("Length (months)","36");
        }
        else if(entry.contains("4 years")||entry.contains("4 year")||entry.contains("4 yrs"))
        {
        	result.put("Length (months)","48");
        }
        
        
	    
	    result.put("Level", "Undergraduate");
	    
	    //result.put("Scholarship", "Aston Excellence Scholarship:3000;Income-based scholarships:3000;Placement Year/Year Abroad Scholarships:1000;");
	    
        return result;
	}
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	public static String GetType(String input)//BA BEng Bsc Msc MEng 
	{
		//String types="BA;BEng;BSc;MSc;MEng;Double MA;Joint MA;MA";
		
		String[] array=types;
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
		    	TagNode tagNode = new TagNode();//閫氳繃TagNode鑾峰緱灞炴�锛屽彧鏈夊皢Node杞崲涓篢agNode鎵嶈兘鑾峰彇鏌愪竴涓爣绛剧殑灞炴�
	            tagNode.setText(node.toHtml());
	            System.out.println(tagNode.getAttribute("data-href"));
		    	
		    	
		    }
			
			in.close();
		}
		public static String HTMLFilter(String input) {
		    // 浣垮け鍘荤敤澶勭殑鏍囩浠庢柊鏈変綔鐢�
		    if (input == null) {
		        input = "";
		        return input;
		    }
		    input = input.trim().replaceAll("&amp;", "&");
		    input = input.trim().replaceAll("&lt;", "<");
		    input = input.trim().replaceAll("&gt;", ">");
		    input = input.trim().replaceAll("    ", " ");
		    input = input.trim().replaceAll("\n", "\r\n");
		    input = input.trim().replaceAll("<br>", "\n");
		    input = input.trim().replaceAll("&nbsp;", "  ");
		    input = input.trim().replaceAll("&quot;", "\"");
		        input = input.trim().replaceAll("&#39;", "'");
		    input = input.trim().replaceAll("&#92;", "\\\\");
		    input = input.trim().replaceAll("脗", "");
		    input = input.trim().replaceAll(" \n", "");
		    input = input.trim().replaceAll(" \r\n", "");
		    return input;
		}

}
