import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Scholarship {
	public static void main(String[] args) {
		getScholarshipMap("C:\\Users\\Jeiel\\Desktop\\wyl\\gen_data_school_scholarship.xls");
	}

	public static Map<String, List<String>> getScholarshipMap(String file) {

		try {
			Map<String, List<String>> scholarshipMap = new HashMap<String, List<String>>();

			Workbook book = Workbook.getWorkbook(new FileInputStream(new File(
					file)));
			Sheet sheet = book.getSheet("sheet1");

			String key = "";
			String value = "";
			int j = 0;
			System.out.println("begin");
			for (int i = 1; i < 445; i++) {
				List<String> values = null;
				key = sheet.getCell(j, i).getContents().length() > 0 ? sheet
						.getCell(j, i).getContents() : key;
				values = scholarshipMap.get(key) != null ? scholarshipMap
						.get(key) : new ArrayList<String>();
				value = sheet.getCell(j + 1, i).getContents() + "￥"
						+ sheet.getCell(j + 2, i).getContents();
				//System.out.println(key+" : "+value);
				values.add(value);
				scholarshipMap.put(key, values);
			}
			/*int size=0;
			for(String k:scholarshipMap.keySet()){
				size+=scholarshipMap.get(k).size();
			}
			System.out.println(size);*/
			System.out.println("end");
			return scholarshipMap;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
