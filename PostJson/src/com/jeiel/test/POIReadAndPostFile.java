package com.jeiel.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.*;

public class POIReadAndPostFile {
	
	public static String StructureDir="";

	public static String rootpath="D:\\KCL\\Post";
	public static String filepath="\\KCLPostFixed.xls";
	/**
	 * @param args
	 * @throws IOException 
	 * @throws Exception 
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws IOException{
		System.out.println(getStructureFromFile("structure_1.txt"));
	}
	public static List<Major> getData(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		
		InputStream is = new FileInputStream(rootpath+filepath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);//The first Sheet.
        
        List<Major> list=new ArrayList<Major>();
        Major major;
            // ѭ����Row
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
        	
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow == null) {
                continue;
            }
            major=new Major();


//0.School;1.Level;2.Title;3.Type;4.ApplicationFee;5.Tuition Fee;6.Academic Requirements;
//7.IELTS Average;8.IELTS Low;9.Structure;10.Length(month);11.Month of Entry;12.Scholarship                
            
            major.setSchool(String.valueOf(hssfRow.getCell(0)));
            String school=String.valueOf(hssfRow.getCell(0));
            major.setLevel(String.valueOf(hssfRow.getCell(1)));
            major.setTitle(String.valueOf(hssfRow.getCell(2)));
            major.setType(String.valueOf(hssfRow.getCell(3)));
            major.setApplicationFee(String.valueOf(hssfRow.getCell(4)));
            major.setTuitionFee(String.valueOf(hssfRow.getCell(5)));
            major.setAcademicRequirements(String.valueOf(getStructureFromFile(rootpath+"//entry//"+String.valueOf(hssfRow.getCell(6))).replace("\r", "").replace("\n", "")));
            major.setIELTS_Avg(String.valueOf(hssfRow.getCell(7)));
            major.setIELTS_Low(String.valueOf(hssfRow.getCell(8)));
            //major.setStructure(splitStructure(String.valueOf(hssfRow.getCell(9))));
            major.setStructure(splitStructure(getStructureFromFile(rootpath+"//structure//"+String.valueOf(hssfRow.getCell(9)))));
            major.setLength(String.valueOf(hssfRow.getCell(10)));
            major.setMonthOfEntry(String.valueOf(hssfRow.getCell(11)));
            
            LinkedHashMap<String, String> sc=new LinkedHashMap<String, String>();

            //sc.put("Alumni Loyalty Scholarship","20%");
            //sc.put("Lancaster Master��s Scholarship", "10000");
            //***********Mary Undergraduate
            /*for(int k=0;k<MaryAdd.MaryUnScho.length;k++)
            {
            	if(school.equals(MaryAdd.MaryUnScho[k].split(";")[0]))
            	{
            		sc.put(MaryAdd.MaryUnScho[k].split(";")[2], MaryAdd.MaryUnScho[k].split(";")[1]);
            	}
            }
            major.setScholarship(sc);*/
            //************Mary Postgraduate
            /*for(int k=0;k<MaryAdd.MaryPostScho.length;k++)
            {
            	//System.out.println(MaryAdd.MaryPostScho[k].split(";").length%2==1);
            	if(school.trim().equals(MaryAdd.MaryPostScho[k].split(";")[0]))
            	{
            		for(int m=1;m<MaryAdd.MaryPostScho[k].split(";").length-1;)
            		{
            			sc.put(MaryAdd.MaryPostScho[k].split(";")[m], MaryAdd.MaryPostScho[k].split(";")[m+1]);
            			//System.out.println(MaryAdd.MaryPostScho[k].split(";")[m]+":"+MaryAdd.MaryPostScho[k].split(";")[m+1]);
            			m=m+2;
            		}
            		break;
            		
            	}
            }*/
            major.setScholarship(sc);
            list.add(major);
        }
        
        hssfWorkbook.close();
        is.close();
        System.out.println("rowCount:"+list.size());
        return list;

	}
	
	public static String getStructureFromFile(String fileName) throws IOException{
		File file=new File(fileName);
		FileInputStream fis=new FileInputStream(file);
		byte[] bytes=new byte[fis.available()];
		fis.read(bytes);
		fis.close();
		return new String(bytes,"utf8");
	}
	
	public static LinkedHashMap<String,String> splitStructure(String structure) throws Exception
	{
		LinkedHashMap<String,String> result=new LinkedHashMap<String,String>();
		FileOutputStream o=new FileOutputStream(new File("d:/temp.txt"));
		o.write(structure.getBytes());
		o.close();
		BufferedReader fis = new BufferedReader(new FileReader("d:/temp.txt"));
		String title="";
		String text="";
		String line="";
		int index=0;
		while((line=fis.readLine())!=null)
		{
			/*if(line.contains("PLEASE NOTE THAT ELECTIVE MODULE AVAILABILITY IS SUBJECT TO TIMETABLING CONSTRAINTS AND THAT MODULES MAY BE SUBJECT TO CHANGE"))
			{
				break;
			}*/
			line=line.replace("\t", " ").trim();
			if(line.equals(" ")||line.equals("\r"))
				continue;
			//if(line.equals("Year 1")||line.equals("Year 2")||line.equals("Year 3")||line.equals("Year 4")||line.equals("Final Year"))//Final Year
			//if(line.equals("Programme Year One")||line.equals("Programme Year Two")||line.equals("Programme Year Three")||line.equals("Programme Year Four")||line.equals("Final Year"))//Final Year
			//if(line.contains("Year 1")||line.contains("Year 2")||line.contains("Year 3")||line.contains("Year 4")||line.contains("Year 5")||line.contains("Final Year"))//Final Year
			//if(line.equals("First year")||line.equals("Second year")||line.equals("Third year")||line.equals("Fourth year")||line.equals("Final Year"))//Final Year
			if(line.equals("Year 1")
					||line.equals("Year 2")
					||line.equals("Year 3")
					||line.equals("Year 4")
					||line.equals("Year 5")
					||line.equals("Final Year")
					//||line.contains("Your first year will provide")
					//||line.contains("In your second year, you will")
					//||line.contains("In your third year you will")
					//||line.contains("In your final year, you will")
					)			
			{
				if(index!=0)
				{
					
					result.put(title, FilterToHTML.filter(text));
					text="";
				}
				title=line;
				index++;
				
			}
			else
			{
				text+=line+"\n";
			}
		}

		result.put(title, FilterToHTML.filter(text));

		return result;
	}
	
}


