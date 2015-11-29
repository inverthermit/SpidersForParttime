package hw;

import java.io.File;
import java.io.FileInputStream;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class getURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
FileInputStream fis=new FileInputStream(new File("./hwUn.html"));//鏂板缓涓�涓狥ileInputStream瀵硅薄
        
        byte[] b=new byte[fis.available()];//鏂板缓涓�涓瓧鑺傛暟缁�
        fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
       fis.close();
        String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
       //System.out.println(htmls);
        int count=1;
	    Parser	parser=Parser.createParser(htmls, "utf-8");
	    TagNameFilter ListFilter=new TagNameFilter("li");
	    NodeList nodes0=parser.extractAllNodesThatMatch(ListFilter);
	    for(int x=0;x<nodes0.size();x++)
   	    {
	    	String section=nodes0.elementAt(x).toHtml();
	    	parser=Parser.createParser(section, "utf-8");
   	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
                   new HasAttributeFilter("href"));
   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
   	 
   	    for(int i=0;i<nodes4.size();i++)
   	    {
   	    	LinkTag link=(LinkTag)nodes4.elementAt(i);
   	    	String get1=link.getAttribute("href");
   	    	parser=Parser.createParser(section.replace("<strong", "<div").replace("</strong", "</div"), "utf-8");
   	    	TagNameFilter InformationFilter= new TagNameFilter("div");
   	    	NodeList nodes=parser.extractAllNodesThatMatch(InformationFilter);
   	    	String school="";
   	    	String length="";
   	    	String type="";
   	    	
   	    	//System.out.println(nodes.size());
   	    	for(int j=0;j<nodes.size();j++)
   	    	{
   	    		Node node=nodes.elementAt(j);
   	    		String content=node.toHtml();
   	    		if(content.contains("class=\"type dept\""))
   	    		{
   	    			school=html2Str(content).trim();
   	    		}
   	    	}
   	    	
   	    	if(get1.startsWith("/study/ug/courses"))
   	    	{

   	   	        System.out.println("{\""+count+"\",\"http://www.ic.ac.uk"+get1+"\",\""+link.getAttribute("title")+"\",\""+type+"\",\""+school+"\",\""+length.replace("1yr", "12").replace("2yrs", "24").replace("3yrs", "36").replace("4yrs", "48").replace("5yrs", "60")+"\",\"0\"},");
   	    	    count++;
   	    	}
   	    }
   	    }
   	    
	    
	}
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	
}
