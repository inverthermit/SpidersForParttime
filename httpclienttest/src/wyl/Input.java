import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class Input {
	static int index = 1;// ��1��ʼ

	/**
	 * @param args
	 */
	static final String FILE_PATH = "C:\\Users\\Jeiel\\Desktop\\wyl";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe"); 
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.myoffer.cn/account/login/");
		
		driver.manage().window().maximize();
		new WebDriverWait(driver, 10, 500).until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver driver) {
				return driver.findElement(By.id("username"))
						.isDisplayed();
			}
		});
		
	
		driver.findElement(By.id("username")).sendKeys("l1111j@live.com");
		driver.findElement(By.id("password")).sendKeys("13392426025");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		waitForLoad(driver);
		
		System.out.println("login");
		
		
		
		
		File inputFile = new File(FILE_PATH + "\\" + "gen_data_soas.xls");
		InputStream os = null;
		Workbook book=null;
		Sheet sheet=null;
		try {
			if (!inputFile.exists()) {
				inputFile.createNewFile();
			}
			os = new FileInputStream(inputFile);

			

			book = Workbook.getWorkbook(os);
			sheet = book.getSheet("sheet1");
		}catch(Exception qe){
			qe.printStackTrace();
		}

		
		
		
		int j=0;
		for(int i=1;i<=5;i++){
			j=0;
			
			driver.get("http://www.myoffer.cn/external/course");
			new WebDriverWait(driver, 10, 500).until(new Predicate<WebDriver>() {
				@Override
				public boolean apply(WebDriver driver) {
					return driver.findElement(By.xpath("//li[@ng-repeat='university in universities']"))
							.isDisplayed();
				}
			});
			
			
			driver.findElement(By.xpath("//div[@class='panel-heading']")).findElements(By.tagName("a")).get(index-1).click();
			
			WebElement e;
			driver.findElement(By.id("inputSchool")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputLevel")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputTitle")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputType")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputApplication")).sendKeys("");
			j++;
			driver.findElement(By.id("inputTuition")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputAcademic")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputIELTSAvg")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputIELTSLow")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			/*driver.findElement(By.xpath("//input[@placeholder='Lowest of Listening']")).sendKeys("1");
			driver.findElement(By.xpath("//input[@placeholder='Lowest of Speaking']")).sendKeys("1");
			driver.findElement(By.xpath("//input[@placeholder='Lowest of Reading']")).sendKeys("1");
			driver.findElement(By.xpath("//input[@placeholder='Lowest of Writing']")).sendKeys("1");*/
			String structure=sheet.getCell(j++, i).getContents();
			//�Ȱ�structure����浽һ���ļ�������Ҷ�ȡ��
			
			FileOutputStream o=new FileOutputStream(new File("d:/soastest.txt"));
			o.write(structure.getBytes());
			o.close();
			BufferedReader fis = new BufferedReader(new FileReader("d:/soastest.txt"));
			String title="";
			String text="";
			String line="";
			int index=0;
			while((line=fis.readLine())!=null)
			{
				line=line.replace("\t", " ");
				//System.out.println(line);
				if(line.equals("Year 1")||line.equals("Year 2")||line.equals("Year 3")||line.equals("Year 4"))
				{
					if(index!=0)
					{
						//System.out.println(title+":\n"+text);//��д�?
						driver.findElement(By.id("inputStructureCategory")).sendKeys(title);
						driver.findElement(By.id("inputStructureSummary")).sendKeys(text);
						driver.findElement(By.xpath("//button[@ng-click='addCategory()']")).click();
					}
					title=line;
					index++;
					
				}
				else
				{
					text+=line+"\n";
				}
			}
			//System.out.println(title+":\n"+text);//��д���һ���?
			driver.findElement(By.id("inputStructureCategory")).sendKeys(title);
			driver.findElement(By.id("inputStructureSummary")).sendKeys(text);
			driver.findElement(By.xpath("//button[@ng-click='addCategory()']")).click();
			fis.close();
			
//			driver.findElement(By.id("inputStructureCategory")).sendKeys("1");
//			driver.findElement(By.id("inputStructureSummary")).sendKeys("1");
//			driver.findElement(By.xpath("//button[@ng-click='addCategory()']")).click();
			
			driver.findElement(By.id("inputLength")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputMonths")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			driver.findElement(By.id("inputScholarshipName")).sendKeys("Undergraduate Research Awards");
			driver.findElement(By.id("inputScholarshipValue")).sendKeys(sheet.getCell(j++, i).getContents().replace("\n", " "));
			
			
			driver.findElement(By.xpath("//button[@ng-click='addScholarship()']")).click();
			
			driver.findElement(By.id("btnSubmit")).click();
		}
		
		
		
		
		
		System.out.println("Success");
		
		
	}

	public static void waitForLoad(WebDriver driver) {
		new WebDriverWait(driver, 10, 500).until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver driver) {
				return driver.findElement(
						By.xpath("//div[@class='account-name']")).isDisplayed();
			}
		});
	}

}
