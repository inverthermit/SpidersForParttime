package lancaster;

import java.io.File;
import java.io.FileInputStream;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class getURL {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream(new File("./lancasterPost.html"));//新建一个FileInputStream对象
        
        byte[] b=new byte[fis.available()];//新建一个字节数组
        fis.read(b);//将文件中的内容读取到字节数组中
       fis.close();
        String htmls=new String(b);//再将字节数组中的内容转化成字符串形式输出
       //System.out.println(htmls);
       
	    Parser	parser=Parser.createParser(htmls, "utf-8");
   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
                   new HasAttributeFilter("href"));
   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
   	    for(int i=0;i<nodes4.size();i++)
   	    {
   	    	LinkTag link=(LinkTag)nodes4.elementAt(i);
   	    	String get1=link.getAttribute("href");
   	    	String[] content=html2Str(link.toHtml()).split(":");
   	        System.out.println("http://www.lancaster.ac.uk/study/postgraduate/postgraduate-courses/"+get1+";"+content[0].trim()+";"+content[1].trim());
   	    }
   	    	
   	    
	    
	}
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}

}
