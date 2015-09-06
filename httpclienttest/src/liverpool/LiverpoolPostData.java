package liverpool;

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

public class LiverpoolPostData {
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
public static String FILE_PATH="d:\\LIVERPOOL";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		BufferedReader rr=new BufferedReader(new FileReader("./file/liverpoolPost.txt"));
		int i = 1;
		int j=0;
		String url="";
		while((url = rr.readLine()) != null)
		{
			//if(i>=196)
			{
				System.out.println(i+":"+url);
				//url=rr.readLine();
				if(url.contains("/overview/"))
				{
					url=url.replace("overview/", "entry-requirements/");
					HashMap<String,String> data=LiverpoolGetDetails(url);
					for(j=0;j<13;j++)
						{
							label = new Label(j, i, data.get(Keys[j]));
							sheet.addCell(label);
						}
				}
			}
				
				
			
			i++;
			/*if(i>3)
				break;*/
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
	
	public static HashMap<String,String> LiverpoolGetDetails(String url) throws Exception
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
		//htmls=HTMLFilter(htmls);
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
	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("h2"),
                new HasAttributeFilter("id","main_content_title"));
        NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
        if(nodes2.size()>0)
        {
        	Node node=(Node)nodes2.elementAt(0);
    	    System.out.println("Title:"+html2Str(node.toHtml()));
    	    //System.out.println("Type:"+GetType(html2Str(node.toHtml())));
    	    result.put("Title",html2Str(node.toHtml()));
    	    String school=getSchool(html2Str(node.toHtml()));
    	    result.put("School",school);
        	System.out.println("School:"+school);
    	    //result.put("Type",GetType(html2Str(node.toHtml())));
        }
        
      
        
      //******************************Duration***********************
        parser=Parser.createParser(htmls, "utf-8");
        /*parser=Parser.createParser(htmls, "utf-8");
	    AndFilter SchoolFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","study-box"));
        NodeList nodes3 = parser.extractAllNodesThatMatch(SchoolFilter);
        
        if(nodes3.size()>=0)
        {
        	for(int i=0;i<nodes3.size();i++)
    	    {
        		Node node=(Node)nodes3.elementAt(i);
        		if(node.toPlainTextString().contains("Visit the academic department"))
        		{*/
        AndFilter DFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","course-info-description"));
        NodeList nodes7 = parser.extractAllNodesThatMatch(DFilter);
        String temp2=nodes7.elementAt(0).toHtml();
        //System.out.println(temp2);
        parser=Parser.createParser(temp2, "utf-8");
        TagNameFilter PosttypeFilter=new TagNameFilter("h3");
        NodeList nodes8 = parser.extractAllNodesThatMatch(PosttypeFilter);
        String t=nodes8.elementAt(0).toHtml();
        System.out.println("Type:"+html2Str(t));
        result.put("Type",html2Str(t));
        
        parser=Parser.createParser(temp2, "utf-8");
        TagNameFilter DurationFilter=new TagNameFilter("p");
        NodeList nodes0 = parser.extractAllNodesThatMatch(DurationFilter);
        for(int i=0;i<nodes0.size();i++)
        {
        	Node node=(Node)nodes0.elementAt(i);
        	if(html2Str(node.toHtml()).contains("Programme length:"))
        	{
        		String Duration=html2Str(node.toHtml());
        		System.out.println("Duration:"+Duration);
        		if(Duration.contains("1 year"))
        		{
        			result.put("Length (months)","12");
        		}
        		else if(Duration.contains("2 year"))
        		{
        			result.put("Length (months)","24");
        		}
        		else if(Duration.contains("3 year"))
        		{
        			result.put("Length (months)","36");
        		}
        		else if(Duration.contains("4 year"))
        		{
        			result.put("Length (months)","48");
        		}
        		else if(Duration.contains("1")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","12");
        		}
        		else if(Duration.contains("2")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","24");
        		}
        		else if(Duration.contains("3")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","36");
        		}
        		else if(Duration.contains("4")&&Duration.contains("year"))
        		{
        			result.put("Length (months)","48");
        		}
        		break;
        	}
        }
        	
        
      //**************************get entry and IELTS**********************
        parser=Parser.createParser(htmls.replace("<section", "<div").replace("</section>", "</div>"), "utf-8");
        
	    AndFilter EntryFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","subject-overview"));
        NodeList nodes4 = parser.extractAllNodesThatMatch(EntryFilter);
        //String Structure="";
        if(nodes4.size()>0)
        {
        	//System.out.println(htmls);
        	
    	    	Node node=(Node)nodes4.elementAt(0);
    	    	//System.out.println(node.toHtml());
    	    	String entry=(html2Str(node.toHtml())).replace("\r", "");
    	    	entry=entry.replace("\n", " ");
    	    	//entry=entry.replace("<br>", " ");
    	    	entry=HTMLFilter(entry);
                System.out.println(entry);
                result.put("Academic Entry Requirement",entry);
                
                String International=node.toHtml();
                //System.out.println(International);
              //******************************IELTS***********************
        		ArrayList<String> list = new ArrayList<String>();
                if(International.contains("7.0"))
                {
                	list.add("7.0");
                }
                if(International.contains("6.5"))
                {
                	list.add("6.5");
                }
                if(International.contains("6.0"))
                {
                	list.add("6.0");
                }
                if(International.contains("5.5"))
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
        }
        
     //new url
        url=url.replace("entry-requirements/", "module-details/");
        httpGet = new HttpGet(url); 
        response = httpclient.execute(httpGet);  
		entity = response.getEntity();
		//System.out.println("Got reply!");
		htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity).replace("\t", " ");
		    //System.out.println(htmls);
		}
		
		 //**************************get structure**********************
		parser=Parser.createParser(htmls.replace("<section", "<div").replace("</section>", "</div>"), "utf-8");
	    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","subject-overview"));
        NodeList nodes5 = parser.extractAllNodesThatMatch(StructureFilter);
        //String Structure="";
        if(nodes5.size()>0)
        {
        	for(int i=0;i<nodes5.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes5.elementAt(i);
    	    	System.out.println("Structure:\n");
    	    	//System.out.println(node.toHtml().replaceAll("(<table([^>]*?)>)(.*?)(</table>)",""));
                String structure=(html2Str(node.toHtml().replaceAll("(<table([^>]*?)>)(.*?)(</table>)","").replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
                structure=HTMLFilter(structure);
                System.out.println(structure);
                
        	    result.put("Structure",structure.trim());
    	    	break;
    	    }
        }
        result.put("Level", "Postgraduate");
        //result.put("IELTS Average Requirement", "6.0");
		//result.put("IELTS Lowest Requirement", "5.5");
		result.put("Scholarship", "");
        return result;
	}//...
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
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
		
		public static String getSchool(String name)
		{
			for(int i=0;i<LiverpoolGetDepartment.dep2Major.length;i++)
			{
				if(LiverpoolGetDepartment.dep2Major[i][1].contains(name))
				{
					return LiverpoolGetDepartment.dep2Major[i][0];
				}
			}
			return "";
		}

}

