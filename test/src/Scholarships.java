import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class Scholarships {
	static final String FILE_PATH = "C:\\Users\\donghua\\Desktop\\wyl";

	public static void main(String[] args) {
		System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Users\\donghua\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriver tmpdriver = new ChromeDriver();
		driver.get("http://www.soas.ac.uk/admissions/pg/subject/");
		List<WebElement> links = driver.findElements(By.className("links"));
		List<String> urls = new ArrayList<String>();
		List<WebElement> aTags = null;
		Map<String, String> scholarships = new HashMap<String, String>();
		for (WebElement link : links) {
			aTags = link.findElements(By.tagName("a"));
			for (WebElement a : aTags) {
				urls.add(a.getAttribute("href"));
			}
		}
		System.out.println("urls prepared");

		WebElement e;
		File outputFile = new File(FILE_PATH + "\\"
				+ "gen_data_school_scholarship.xls");
		OutputStream os = null;
		WritableWorkbook book = null;
		WritableSheet sheet = null;
		try {
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			os = new FileOutputStream(outputFile);

			Label label;
			book = Workbook.createWorkbook(os);
			sheet = book.createSheet("sheet1", 0);
			label = new Label(0, 0, "专业");
			sheet.addCell(label);
			label = new Label(1, 0, "奖学金名称");
			sheet.addCell(label);

			String title = null;
			int page = 1;
			int i = 1;
			int j;
			for (String url : urls) {
				/*if (page > 5)
					break;*/
				System.out.println("page: " + page);
				driver.get(url);
				WebDriverWait wait = new WebDriverWait(driver, 60, 500);
				wait.until(new Predicate<WebDriver>() {
					@Override
					public boolean apply(WebDriver driver) {
						return driver.findElement(By.xpath("//a[@title='打印']"))
								.isDisplayed();
					}
				});
				j = 0;

				// title,type
				if (doesWebElementExist(driver,
						By.xpath("//*[@id='content']/h3[1]"))) {
					e = driver
							.findElement(By.xpath("//*[@id='content']/h3[1]"));
					title = e.getText();
					label = new Label(j, i, title);
					sheet.addCell(label);
				} else {
					label = new Label(j, i, "NULL");
					sheet.addCell(label);
				}

				j += 1;

				// scholarship
				if (doesWebElementExist(driver,
						By.xpath("//div[@class='listitem']"))) {
					List<WebElement> listDivs = driver.findElements(By
							.xpath("//div[@class='listitem']"));
					for (WebElement listDiv : listDivs) {
						e = listDiv.findElement(By.tagName("a"));

						String tmpname = e.getAttribute("innerHTML");
						String tmpurl = e.getAttribute("href");

						label = new Label(j, i, tmpname);
						System.out.println("scholarship: " + tmpname);
						sheet.addCell(label);
						i++;

						tmpdriver.get(tmpurl);
						WebDriverWait tmpwait = new WebDriverWait(tmpdriver,
								60, 500);
						tmpwait.until(new Predicate<WebDriver>() {
							@Override
							public boolean apply(WebDriver driver) {
								return driver.findElement(
										By.xpath("//a[@title='打印']"))
										.isDisplayed();
							}
						});
						e = tmpdriver.findElement(By.id("content"))
								.findElement(By.tagName("p"));
						String str = e.getAttribute("innerHTML");
						System.out.println(str);

						scholarships.put(tmpname + "?" + tmpurl, str);
					}
				} else {
					i++;
				}
				page++;
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
		} finally {
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
		driver.close();
		tmpdriver.close();
		File outputFile1 = new File(FILE_PATH + "\\"
				+ "gen_data_scholarship.xls");
		OutputStream os1 = null;
		WritableWorkbook book1 = null;
		WritableSheet sheet1 = null;

		try {
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			os1 = new FileOutputStream(outputFile1);

			Label label;
			book1 = Workbook.createWorkbook(os1);
			sheet1 = book1.createSheet("sheet", 1);
			Iterator<String> it = scholarships.keySet().iterator();
			String str;
			int i = 0;
			int j = 0;
			while (it.hasNext()) {
				j = 0;
				str = it.next();
				label = new Label(j, i, ""
						+ str.subSequence(0, str.indexOf("?")));
				sheet1.addCell(label);
				j += 1;
				label = new Label(j, i, ""
						+ str.substring(str.indexOf("?") + 1));
				sheet1.addCell(label);
				j += 1;
				label = new Label(j, i, "" + scholarships.get(str)!=null?scholarships.get(str):"null");
				sheet1.addCell(label);
				i++;

			}
			book1.write();
			book1.close();
			os1.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
