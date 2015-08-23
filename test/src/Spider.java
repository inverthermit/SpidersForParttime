import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.htmlparser.Parser;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;


public class Spider {
	static final String FILE_PATH = "C:\\Users\\donghua\\Desktop\\wyl";

	public static void main(String[] args) {
		System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Users\\donghua\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.soas.ac.uk/admissions/ug/progs/");
		List<WebElement> links = driver.findElements(By.className("links"));
		List<String> urls = new ArrayList<String>();
		List<WebElement> aTags = null;
		for (WebElement link : links) {
			aTags = link.findElements(By.tagName("a"));
			for (WebElement a : aTags) {
				urls.add(a.getAttribute("href"));
			}
		}
		System.out.println("urls prepared");
		WebElement e;

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

			String title = null;
			int i = 1;
			int j;

			for (String url : urls) {
				System.out.println(i);
				driver.get(url);
				WebDriverWait wait = new WebDriverWait(driver, 20, 500);
				wait.until(new Predicate<WebDriver>() {
					@Override
					public boolean apply(WebDriver driver) {
						return driver.findElement(By.xpath("//a[@title='¥Ú”°']"))
								.isDisplayed();
					}
				});
				j = 0;
				// school
				if (doesWebElementExist(driver,
						By.xpath("//*[@id='coursebanner']/h2"))) {
					e = driver.findElement(By
							.xpath("//*[@id='coursebanner']/h2"));
					label = new Label(j, i, e.getText());
				} else {
					label = new Label(j, i, "NULL");
				}
				sheet.addCell(label);
				j += 1;
				// level
				label = new Label(j, i, "Undergraduate");
				sheet.addCell(label);
				j += 1;
				// title,type
				if (doesWebElementExist(driver,
						By.xpath("//*[@id='content']/h3[1]"))) {
					e = driver
							.findElement(By.xpath("//*[@id='content']/h3[1]"));
					title = e.getText();

					label = new Label(j, i,
							title.substring(title.indexOf(" ") + 1));
					sheet.addCell(label);
					j += 1;
					label = new Label(j, i, title.substring(0,
							title.indexOf(" ")));
					sheet.addCell(label);

				} else {
					label = new Label(j, i, "NULL");
					sheet.addCell(label);
					j += 1;
					label = new Label(j, i, "NULL");
					sheet.addCell(label);
				}

				j += 2;

				// tuition
				label = new Label(j, i, "16090");
				sheet.addCell(label);
				j += 1;

				// Academic Entry Requirement
				if (doesWebElementExist(driver,
						By.xpath("//div[@class='entryrequirements']"))) {
					e = driver.findElement(By
							.xpath("//div[@class='entryrequirements']"));
					label = new Label(j, i, e.getText());

				} else {
					label = new Label(j, i, "NULL");
				}
				sheet.addCell(label);
				j += 1;

				// IELTS Average Requirement,IELTS Lowest Requirement
				label = new Label(j, i, "7");
				sheet.addCell(label);
				j += 1;
				label = new Label(j, i, "6.5");
				sheet.addCell(label);
				j += 1;

				// Structure
				if (doesWebElementExist(driver, By.id("programmetabs"))) {
					List<WebElement> lis = null;
					lis = driver.findElement(By.id("programmetabs"))
							.findElement(By.tagName("ul"))
							.findElements(By.tagName("li"));
					int k = 0;
					/*for (WebElement m : driver.findElement(
							By.id("programmetabs")).findElements(
							By.tagName("tabcontent"))) {
						if(m.getAttribute("InnerHTML").contains("<h4 class=\"tabblocktitle\">Structure</h4>")){
							System.out.println(m.getAttribute("InnerHTML"));
						}

					}*/
					for (WebElement li : lis) {

						if (li.findElement(By.tagName("a")).getText()
								.equals("Structure")) {
							System.out.println("k:" + k);
							System.out.println(li.findElement(By.tagName("a"))
									.getText());
							break;
						}
						k++;
					}
					e = driver.findElement(By.id("programmetabs"))
							.findElements(By.className("tabcontent")).get(k);
					label = new Label(j, i,html2Str(e.getAttribute("innerHTML")));
					//System.out.println(html2Str(e.getAttribute("innerHTML")));

				} else {
					label = new Label(j, i, "NULL");
				}
				sheet.addCell(label);
				j += 1;

				// Length (months)
				if (doesWebElementExist(driver,
						By.xpath("//*[@id='content']/h3[2]"))) {
					e = driver
							.findElement(By.xpath("//*[@id='content']/h3[2]"));
					label = new Label(j, i, e.getText());

				} else {
					label = new Label(j, i, "NULL");
				}
				sheet.addCell(label);
				j += 1;

				// Month of Entry
				label = new Label(j, i, "9");
				sheet.addCell(label);
				j += 1;

				// Scholarship
				label = new Label(j, i, "590");
				sheet.addCell(label);

				i++;
			}

			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			}
			
		}
		System.out.println("work done!");
	}

	public static boolean doesWebElementExist(WebDriver driver, By selector) {
		try {
			
			driver.findElement(selector);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
}
