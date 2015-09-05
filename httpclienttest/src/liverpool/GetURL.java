package liverpool;

import java.io.File;
import java.io.FileInputStream;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class GetURL {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

        FileInputStream fis=new FileInputStream(new File("./liverpoolPost.html"));
        
             byte[] b=new byte[fis.available()];
             fis.read(b);
            fis.close();
             String htmls=new String(b);
            Parser parser=Parser.createParser(htmls, "utf-8");
    	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("td"),
                    new HasAttributeFilter("class","course-name"));
            NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
            for(int i=0;i<nodes2.size();i++)
    	    {
            	
    	    	parser=Parser.createParser(nodes2.elementAt(i).toHtml(), "utf-8");
	    	    ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
	                    new HasAttributeFilter("href"));
	    	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
	    	    
	    	    	LinkTag link=(LinkTag)nodes4.elementAt(0);
	    	    	try{
	    	    	String get1=link.getAttribute("href");
	    	    		if(get1.startsWith("http://www.liv.ac.uk"))
	    	    	    	System.out.println(get1);
	    	    	    	else
	    	    	    		System.out.println("http://www.liv.ac.uk"+get1);
	    	    	}
	    	    	catch(Exception ee)
	    	    	{
	    	    		
	    	    	}
	    	    	
	    	    	
	    	    
    	    }

	}

}
