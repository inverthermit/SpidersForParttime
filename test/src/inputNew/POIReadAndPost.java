package inputNew;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class POIReadAndPost {

	public static String filepath="D:\\BELFAST\\gen_data1.xls";
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		InputStream is = new FileInputStream(filepath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);//The first Sheet.
            
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	System.out.println(rowNum);
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                ArrayList<String> datarow=new ArrayList<String>();
                // 循环列Cell
//0.School;1.Level;2.Title;3.Type;4.ApplicationFee;5.Tuition Fee;6.Academic Requirements;
//7.IELTS Average;8.IELTS Low;9.Structure;10.Length(month);11.Month of Entry;12.Scholarship                
                for(int j=0;j<13;j++)
                {
                	HSSFCell xh = hssfRow.getCell(j);
                	//System.out.println(String.valueOf(xh.getStringCellValue()));
                	//System.out.println(String.valueOf(xh));
                	datarow.add(String.valueOf(xh));
                }
                Structure(datarow.get(9));
                //System.out.println(rowNum);
                
            }
            hssfWorkbook.close();
            is.close();
        

	}
	
	public static ArrayList<String> Structure(String structure) throws Exception
	{
		ArrayList<String> result=new ArrayList<String>();
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
			line=line.replace("\t", " ").trim();
			if(line.equals(" ")||line.equals("\r"))
				continue;
			//System.out.println(line);
			if(line.equals("Year 1")||line.equals("Year 2")||line.equals("Year 3")||line.equals("Year 4")||line.equals("Final Year"))//Final Year

			{
				if(index!=0)
				{
					
					result.add(title+"#####"+text);
					
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
		result.add(title+"#####"+text);
		/*for(String a:result)
		{
			System.out.println(a);
		}*/
		return result;
	}

}


