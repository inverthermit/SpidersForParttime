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
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class InputMaster {
	static int index = 2;// ��1��ʼ

	/**
	 * @param args
	 */
	static final String FILE_PATH = "d://ASTON";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		System.setProperty("webdriver.firefox.bin",
				"D:\\Mozilla Firefox\\firefox.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.myoffer.cn/account/login/");

		driver.manage().window().maximize();
		new WebDriverWait(driver, 10, 500).until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver driver) {
				return driver.findElement(By.id("username")).isDisplayed();
			}
		});

		driver.findElement(By.id("username")).sendKeys("l1111j@live.com");
		driver.findElement(By.id("password")).sendKeys("13392426025");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		waitForLoad(driver);

		System.out.println("login");

		File inputFile = new File(FILE_PATH + "\\"
				+ "gen_dataMaster.xls");
		InputStream os = null;
		Workbook book = null;
		Sheet sheet = null;
		try {
			if (!inputFile.exists()) {
				inputFile.createNewFile();
			}
			os = new FileInputStream(inputFile);

			book = Workbook.getWorkbook(os);
			sheet = book.getSheet("Sheet2");
		} catch (Exception qe) {
			qe.printStackTrace();
		}


		int j = 0;
		for (int i = 1; i <= 141; i++) {
			j = 0;

			driver.get("http://www.myoffer.cn/external/course");
			new WebDriverWait(driver, 120, 500).until(new Predicate<WebDriver>() {
				@Override
				public boolean apply(WebDriver driver) {
					return driver
							.findElement(
									By.xpath("//li[@ng-repeat='university in universities']"))
							.isDisplayed();
				}
			});

			driver.findElement(By.xpath("//div[@class='panel-heading']"))
					.findElements(By.tagName("a")).get(index).click();
			
			WebElement e;

			driver.findElement(By.id("inputSchool")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));

			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputSchool\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputLevel")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));

			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputLevel\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputTitle")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));

			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputTitle\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputType")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));

			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputType\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputApplication")).sendKeys("");

			/*
			 * ((JavascriptExecutor) driver) .executeScript(
			 * "document.getElementById(\"inputApplication\").value=\"\"");
			 */

			j++;
			driver.findElement(By.id("inputTuition")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));
			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputTuition\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputAcademic")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));
			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputAcademic\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputIELTSAvg")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));
			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputIELTSAvg\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputIELTSLow")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));
			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputIELTSLow\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			String structure = sheet.getCell(j++, i).getContents();
			// �Ȱ�structure����浽һ���ļ�������Ҷ�ȡ��

			FileOutputStream o = new FileOutputStream(new File(
					"d:/soastest.txt"));
			o.write(structure.getBytes());
			o.close();
			BufferedReader fis = new BufferedReader(new FileReader(
					"d:/soastest.txt"));
			String title = "";
			String text = "";
			String line = "";
			int index = 0;
			while ((line = fis.readLine()) != null) {
				line = line.replace("\t", " ");
				// System.out.println(line);
				if (line.equals("Year 1") || line.equals("Year 2")
						|| line.equals("Year 3") || line.equals("Year 4")||line.equals("Final Year")) {
					if (index != 0) {
						// System.out.println(title+":\n"+text);//��д�?
						driver.findElement(By.id("inputStructureCategory"))
								.sendKeys(title);
						/*
						 * ((JavascriptExecutor) driver) .executeScript(
						 * "document.getElementById(\"inputStructureCategory\").value=\""
						 * + title + "\"");
						 */
						driver.findElement(By.id("inputStructureSummary"))
								.sendKeys(text);
						/*
						 * ((JavascriptExecutor) driver) .executeScript(
						 * "document.getElementById(\"inputStructureSummary\").value=\""
						 * + text + "\""); driver.findElement(
						 * By.xpath("//button[@ng-click='addCategory()']"))
						 * .click();
						 */
					}
					title = line;
					index++;

				} else {
					text += line + "\n";
				}
			}
			// System.out.println(title+":\n"+text);//��д���һ���?
			driver.findElement(By.id("inputStructureCategory")).sendKeys(title);
			/*
			 * ((JavascriptExecutor) driver) .executeScript(
			 * "document.getElementById(\"inputStructureCategory\").value=\"" +
			 * title + "\"");
			 */
			driver.findElement(By.id("inputStructureSummary")).sendKeys(text);
			/*
			 * ((JavascriptExecutor) driver) .executeScript(
			 * "document.getElementById(\"inputStructureSummary\").value=\"" +
			 * text + "\"");
			 */
			driver.findElement(By.xpath("//button[@ng-click='addCategory()']"))
					.click();
			fis.close();

			driver.findElement(By.id("inputLength")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));
			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputLength\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */
			driver.findElement(By.id("inputMonths")).sendKeys(
					sheet.getCell(j++, i).getContents().replace("\n", " "));
			/*
			 * ((JavascriptExecutor) driver)
			 * .executeScript("document.getElementById(\"inputMonths\").value=\""
			 * + sheet.getCell(j++, i).getContents() .replace("\n", " ") +
			 * "\"");
			 */

			driver.findElement(By.id("inputScholarshipName")).sendKeys("Aston PG50 Masters Scholarships");
			driver.findElement(By.id("inputScholarshipValue")).sendKeys("10000");
			driver.findElement(By.xpath("//button[@ng-click='addScholarship()']")).click();//Aston PG50 Masters Scholarships:10000


			driver.findElement(By.id("btnSubmit")).click();
			System.out.println(i+" done");
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
