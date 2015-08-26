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
		
		BufferedReader rr=new BufferedReader(new FileReader("./file/belfast-postgraduate.txt"));
		int i = 1;
		int j=0;
		String url="";
		while((url = rr.readLine()) != null)
		{
			
				System.out.println(i+":"+url);
				//url=rr.readLine();
				HashMap<String,String> data=BelfastGetDetails(url);
				for(j=0;j<13;j++)
					{
						label = new Label(j, i, data.get(Keys[j]));
						sheet.addCell(label);
					}
			
			i++;
			//if(i>20)
				//break;
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
	    TagNameFilter ProfessionNameFilter=new TagNameFilter("h1");
        NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
        if(nodes2.size()>=2)
        {
        	Node node=(Node)nodes2.elementAt(1);
    	    System.out.println("Title:"+html2Str(node.toHtml()));
    	    System.out.println("Type:"+GetType(html2Str(node.toHtml())));
    	    result.put("Title",html2Str(node.toHtml()));
    	    result.put("Type",GetType(html2Str(node.toHtml())));
        }
	    
	  //**************************get Structure**********************<div class="module inactive" id="mobile_module">
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter StructureFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","mobile_module"));
        NodeList nodes6 = parser.extractAllNodesThatMatch(StructureFilter);
        //String Structure="";
        if(nodes6.size()>0)
        {
        	for(int i=0;i<nodes6.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes6.elementAt(i);
    	    	String structure=(html2Str(node.toHtml().replace("<br />", "\r\n").replace("</strong>", "").replace("<strong>", "").replace("</", "\r\n</").replace("\t"," ").replace("&amp;"," ")).replace("\r\n\r\n", "\r\n"));
                structure=HTMLFilter(structure);
                System.out.println(structure);
        	    result.put("Structure",structure.trim());
    	    	break;
    	    }
        }
        
        
        
        
      //**************************get overview***********************//<div class="module active" id="email_module">
        parser=Parser.createParser(htmls, "utf-8");
	    AndFilter OverviewFilter=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","email_module"));
        NodeList nodes5 = parser.extractAllNodesThatMatch(OverviewFilter);
        String Overview="";
        if(nodes5.size()>0)
        {
        	for(int i=0;i<nodes5.size();i++)
    	    {
    	    	
    	    	Node node=(Node)nodes5.elementAt(i);
    	    	Overview=html2Str(node.toHtml()).trim();
    	    	//result.put("School",school);
    	    	break;
    	    }
        }
        
        
      //**************************get school,institute,entry requirement,length*************************
        FileOutputStream o=new FileOutputStream(new File("d:/BELFAST/temp.txt"));
		o.write(Overview.getBytes());
		o.close();
		BufferedReader fis = new BufferedReader(new FileReader("d:/BELFAST/temp.txt"));
		String title="";
		String text="";
		String line="";
		String Contact="",Entry="",Duration="",International="";
		int index=0;
		//String Paras="Aim;Contact details;Entrance Requirements;Additional Information for International Students;Duration;Number of places";
		//String[] Ps=Paras.split(";");
		while((line=fis.readLine())!=null)
		{
			line=line.replace("\t", " ");
			//System.out.println(line);
			if(line.equals("Aim")||line.equals("Contact details")||line.equals("Entrance Requirements")||line.equals("Additional Information for International Students")||line.equals("Duration")||line.equals("Number of places"))
			{
				if(index!=0)
				{
					//System.out.println();
					//System.out.println(title+":\n"+text);//锟斤拷写锟�
					if(title.equals("Contact details"))
					{
						Contact=text;
						System.out.println(title+":\n"+text);
					}
					else if(title.equals("Entrance Requirements"))
					{
						Entry=text;
						result.put("Academic Entry Requirement",Entry);
					}
					else if(title.equals("Additional Information for International Students"))
					{
						International=text;
					}
					else if(title.equals("Duration"))
					{
						Duration=text;
					}
					
				}
				title=line;
				text="";
				index++;
				
			}
			else
			{
				if(!text.equals(" "))
				text+=line+"\n";
			}
		}
		
		
		//******************************School***********************
		String[] Contacts=Contact.split("\n");
		//Boolean ha
		String[] SchoolStart={"School of","Institute of","Queen's University","College of"};
		for(int i=0;i<Contacts.length;i++)
		{
			//if(Contacts[i].startsWith("School of")||Contacts[i].startsWith("Institute of"))
			if(Contacts[i].contains("Tel"))
			{
				String school=Contacts[i].split("Tel")[0];
				for(int j=0;j<SchoolStart.length;j++)
				{
					if(school.contains(SchoolStart[j]))
					{
						if(!school.startsWith(SchoolStart[j]))
						{
							String[] temp=school.split(SchoolStart[j]);
							school=SchoolStart[j]+temp[temp.length-1];
						}
					}
				}
				
				
				result.put("School",school);
			}
		}
		
		//******************************Duration***********************
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
        result.put("Level", "Postgraduate");
        
        return result;
	}
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	public static String GetType(String input)//BA BEng Bsc Msc MEng 
	{
		String types="MSc;MEng;Double MA;Joint MA;MA;MArich;MBA;PG;Pg;EdD;MEd;Postgraduate Diploma;Postgraduate Certificate;Doctorate;Graduate Certificate;LLM;GradDip;MTh;MRes";
		
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
		    //input = input.trim().replaceAll("脗", "");
		    //input = input.trim().replaceAll(" \n", "");
		    //input = input.trim().replaceAll(" \r\n", "");
		    return input;
		}

}
