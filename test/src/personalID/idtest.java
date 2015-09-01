package personalID;
import java.util.Scanner;

import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
public class idtest {

private static String url = "http://person.sac.net.cn/login.action";
    
    public static void main(String[] args) throws InterruptedException{
    	System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		//System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe"); 
		
		WebDriver driver = new ChromeDriver();
	driver.get(url);
	Scanner sc = new Scanner(System.in);
	String CertID = sc.nextLine();
	
	WebElement button = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/input"));
	button.click();
	/*WebElement allElements = driver.findElements(By.tagName("body")).get(0);
	System.out.println(allElements.getText());
	WebElement allElements2 = driver.findElements(By.tagName("head")).get(0);
	System.out.println(allElements2.getText());
	sc.nextLine();
	WebElement button2 = driver.findElement(By.id("tree_3_switch"));
	button2.click();
	System.out.println("a");	*/
	String SearchName="";
while(true)
{
	SearchName=sc.nextLine();
	String js1 = "function callback(){result='';if (xml.readyState==4 && xml.status==200){var responseText = xml.responseText;console.log(responseText);var input=document.getElementsByTagName('frameset')[0];input.setAttribute('frameborder', responseText);}}";
	//var xml2 = new XMLHttpRequest();xml2.open('GET', '10.', true); xml2.setRequestHeader('Content-type','application/x-www-form-urlencoded'); xml2.send(responseText);
	//function callback(){result='';if (xml.readyState==4 && xml.status==200){var responseText = xml.responseText;console.log(responseText);var input=document.getElementsByTagName("frame")[0];input.setAttribute("src", responseText);}}
	String js ="var result='';var xml = new XMLHttpRequest();xml.open('POST', 'http://person.sac.net.cn/pages/synthequery/pr-application-search!list.action', true);xml.onreadystatechange = callback; xml.setRequestHeader('Content-type','application/x-www-form-urlencoded'); xml.send('disabledSearch=false&filter_EQS_REGIONTC_ID=&filter_EQS_PA#AOI_ID=&filter_EQS_RPIH_NAME="+SearchName+"&filter_EQS_RPIH_PAPER_NO=&filter_EQS_RPIH_EMP_NO=&filter_EQS_SUI#LOGINNAME=&filter_EQS_CCHI_NUM=&filter_EQS_CHI#CERTC_ID=&page.searchFileName=dxsearch&page.sqlKey=PAGE_PR_APPLICATION&page.sqlCKey=SIZE_PR_APPLICATION&_search=false&nd=1441003853603&page.pageSize=2000&page.pageNo=1&page.orderBy=CCHI_ID&page.order=desc');";
	//String result=(String)((JavascriptExecutor)driver).executeScript(js1+js);
	((JavascriptExecutor)driver).executeScript(js1+js);
    //System.out.println("---result---"+result);
	//System.out.println("---result---");
	Thread.sleep(4000);
	WebElement frame = driver.findElements(By.tagName("frameset")).get(0);
	System.out.println(frame.getAttribute("frameborder"));
	
}
	
    
    //driver.close();
    }

}
