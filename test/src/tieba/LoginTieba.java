package tieba;
import java.util.*;

import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
public class LoginTieba {
private static int deleteCount=0;
private static String url = "http://passport.baidu.com";
    private static String mainpageurl="http://tieba.baidu.com/f?kw=%E5%93%88%E5%B0%94%E6%BB%A8%E5%B7%A5%E4%B8%9A%E5%A4%A7%E5%AD%A6&ie=utf-8";
    public static void main(String[] args) throws Exception{
    	System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		//System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe"); 
		
		WebDriver driver = new ChromeDriver();
	driver.get(url);
	Scanner sc = new Scanner(System.in);
	String CertID = sc.nextLine();
	
	WebElement button = driver.findElement(By.xpath("//*[@id=\"TANGRAM__PSP_3__submit\"]"));
	button.click();
	Thread.sleep(2000);
	driver.get(mainpageurl);
	sc.nextLine();
	driver.get(mainpageurl);
	sc.nextLine();
	
	String tid="4033906702";
	String pid="75571153471";
	String js1="input=document.getElementById('j_head_focus_btn');input.setAttribute('style', 'nothing');";
	((JavascriptExecutor)driver).executeScript(js1);
	deletePost(driver,tid,pid);
	
	
	/*----------------------------Time related function------------------
	Date pre=new Date();
	System.out.println("pre:"+pre.getTime());
	Thread.sleep(3000);
	Date next=new Date();
	System.out.println("next:"+next.getTime());
	System.out.println(next.getTime()-pre.getTime());//3000
	System.out.println(pre.compareTo(next));//-1
	System.out.println(next.compareTo(pre));//1
	*/
	
	while(true)
	{
		Date pre=new Date();
		//Get Post ID
		LinkedHashMap<String,String> resultmap=getTidPids();
		if(resultmap.size()==0)
		{
			
			continue;
		}
		else
		{
			for(int i=0;i<resultmap.size();i++)
			{
				Iterator iter = resultmap.entrySet().iterator(); 
				while (iter.hasNext()) { 
				Map.Entry entry = (Map.Entry) iter.next(); 
				String ttid = (String)entry.getKey(); 
				String ppid = (String)entry.getValue(); 
				String js11="input=document.getElementById('j_head_focus_btn');input.setAttribute('style', 'nothing');";
				((JavascriptExecutor)driver).executeScript(js11);
				deletePost(driver,ttid,ppid);
				WebElement frame = driver.findElements(By.id("j_head_focus_btn")).get(0);
		    	System.out.println(frame.getAttribute("style"));
		    	deleteCount++;
		    	
				} 
			}
		}
		while(true)
		{
			Date next=new Date();
			if(next.getTime()-pre.getTime()>=60000)
			{
				System.out.println(deleteCount);
				break;
			}
			else
			{
				Thread.sleep(3000);
			}
		}
		
	}
    
    //driver.close();
    }
    public static void deletePost(WebDriver driver,String tid,String pid) throws Exception
    {
    	String js2="function callback(){result='';if (xml.readyState==4 && xml.status==200){var responseText = xml.responseText;console.log(responseText);var input=document.getElementById('j_head_focus_btn');input.setAttribute('style', responseText);}}";
    	String js3="var result='';var xml = new XMLHttpRequest();xml.open('POST', 'http://tieba.baidu.com/f/commit/post/delete', true);xml.onreadystatechange = callback; xml.setRequestHeader('Content-type','application/x-www-form-urlencoded'); xml.send('commit_fr=pb&ie=utf-8&tbs=d316085121f93c631441951687&kw=%E5%93%88%E5%B0%94%E6%BB%A8%E5%B7%A5%E4%B8%9A%E5%A4%A7%E5%AD%A6&fid=35522&tid="+tid+"&is_vipdel=0&pid="+pid+"&is_finf=false');";
    	((JavascriptExecutor)driver).executeScript(js2+js3);
    	Thread.sleep(2000);
    	
    }
    public static LinkedHashMap<String,String> getTidPids()
    {
    	LinkedHashMap<String,String> resultlist=new LinkedHashMap<String,String>();
    	return resultlist;
    }

}
