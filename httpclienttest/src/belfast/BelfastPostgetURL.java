package belfast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
public class BelfastPostgetURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//getNumber();
		for(int i=0;i<numbers.length;i++)
		{
			String result=parseHTML(getHTMLS(numbers[i]));
			if(result!="NULL")
			System.out.println(result);
		}

		
			        
	}
	public static String parseHTML(String html) throws Exception
	{
		Parser parser=Parser.createParser(html, "utf-8");//div id=levels-content
		
		AndFilter URLFilter0=new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","levels-content"));
	    NodeList nodes0=parser.extractAllNodesThatMatch(URLFilter0);
	    html=nodes0.elementAt(0).toHtml();
	    parser=Parser.createParser(html, "utf-8");
	    
	    TagNameFilter ProfessionNameFilter=new TagNameFilter("li");
        NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
        for(int i=0;i<nodes2.size();i++)
	    {
        	
	    	parser=Parser.createParser(nodes2.elementAt(i).toHtml(), "utf-8");
    	    AndFilter URLFilter=new AndFilter(new TagNameFilter("a"),
                    new HasAttributeFilter("href"));
    	    NodeList nodes4=parser.extractAllNodesThatMatch(URLFilter);
    	    
    	    	LinkTag link=(LinkTag)nodes4.elementAt(0);
    	    	String get1=link.getAttribute("href");
    	    	System.out.println(get1);
    	    	//return get1;
	    }
        return "NULL";
	}
	public static String getHTMLS(String num) throws Exception
	{
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).build();  
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		 List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();  
	        valuePairs.add(new BasicNameValuePair("sn", "1516"));  
	        valuePairs.add(new BasicNameValuePair("subjects", num));  
	        valuePairs.add(new BasicNameValuePair("button1", "Go!"));  
	        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);  
	           
	        //创建一个post请求  
	        HttpPost post = new HttpPost("http://www.qub.ac.uk/home/StudyatQueens/CourseFinder/PCF1516/PTCF1516/");  

	        //注入post数据  
	        post.setEntity(entity);  
	        HttpResponse response = httpClient.execute(post);
	        HttpEntity entity1 = response.getEntity();
			String htmls=null;
			if (entity1 != null) { 
			    htmls=EntityUtils.toString(entity1);
			    //System.out.println(htmls);
			    
			     
			}
	        httpClient.close();
	        return htmls;
	}
	public static void getNumber() throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("./belfastPost.html"));//鏂板缓涓�釜FileInputStream瀵硅薄
        
        byte[] b=new byte[fis.available()];//鏂板缓涓�釜瀛楄妭鏁扮粍
        fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
       fis.close();
        String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
       //System.out.println(htmls);
       Parser parser=Parser.createParser(htmls, "utf-8");
	    AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("option"),
               new HasAttributeFilter("value"));
       NodeList nodes2 = parser.extractAllNodesThatMatch(ProfessionNameFilter);
       for(int i=0;i<nodes2.size();i++)
	    {
       	
	    	
   	    	OptionTag link=(OptionTag)nodes2.elementAt(i);
   	    	String get1=link.getAttribute("value");
   	    	System.out.println("\""+get1+"\",");
   	    
	    }
	}
	public static String[] numbers={
			"3715",
			"3763",
			"3771",
			"3781",
			"3648",
			"3707",
			"3649",
			"3716",
			"3746",
			"3743",
			"3812",
			"3650",
			"3766",
			"3652",
			"3753",
			"3717",
			"3754",
			"3734",
			"3679",
			"3678",
			"3764",
			"3794",
			"3721",
			"3816",
			"3783",
			"3659",
			"3793",
			"3772",
			"3722",
			"3773",
			"3660",
			"3755",
			"3739",
			"3740",
			"3718",
			"3724",
			"3729",
			"3663",
			"3747",
			"3664",
			"3666",
			"3709",
			"3665",
			"3765",
			"3670",
			"3673",
			"3674",
			"3714",
			"3757",
			"3751",
			"3733",
			"3680",
			"3744",
			"3682",
			"3758",
			"3811",
			"3657",
			"3815",
			"3742",
			"3704",
			"3814",
			"3759",
			"3656",
			"3686",
			"3760",
			"3730",
			"3688",
			"3767",
			"3792",
			"3777",
			"3711",
			"3752",
			"3692",
			"3796",
			"3785",
			"3797",
			"3799",
			"3800",
			"3789",
			"3737",
			"3651",
			"3671",
			"3699",
			"3693",
			"3694",
			"3706",
			"3702",
			"3727",
			"3690",
			"3662",
			"3719",
			"3782",
			"3728",
			"3801",
			"3696",
			"3784",
			"3817",
			"3791",
			"3703",
			"3749",
			"3813",
			"3770",
			"3701",
			"3647",
			"3762",
			"3786",
			"3710",
			"3790",
			"3775"

	};

}
