
import org.htmlparser.*;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;  
import java.net.URISyntaxException;  
import java.net.URL;
import java.util.*;  
import java.util.regex.Matcher;
import java.util.regex.Pattern;
  
import org.apache.http.*;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.utils.URIUtils;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;
public class httptest {
	public static HttpClient httpclient = new DefaultHttpClient();
	public static int picnum=1661;
	public static void main(String[] args) throws Exception {  
 int wantnum=2000;
 for(int k=84;k<=(wantnum/20);k++)
 {
HttpGet httpGet = new HttpGet("http://tieba.baidu.com/f/like/furank?kw=%B9%FE%B6%FB%B1%F5%B9%A4%D2%B5%B4%F3%D1%A7&pn="+k);  //pn��ʾҳ��
HttpResponse response = httpclient.execute(httpGet);  
//��ȡ��Ӧͷ��Ϣ  
//String headString = null;  
//HeaderIterator headItr = response.headerIterator(null);   
//while(headItr.hasNext()){  
//    System.out.println(headItr.next().toString());  
//}  
//��ȡ����ʵ����Ϣ  
HttpEntity entity = response.getEntity();  
if (entity != null) { 
    String htmls=EntityUtils.toString(entity);
    //System.out.println(htmls);
    getMainPages(htmls);
    //����parser����
}   
}  
	
}
	public static void getPicUrl(String url) throws Exception
	{
		HttpGet httpGet = new HttpGet(url);  //pn��ʾҳ��
		HttpResponse response = httpclient.execute(httpGet);  
		//��ȡ����ʵ����Ϣ  
		HttpEntity entity = response.getEntity();
		String htmls=null;
		if (entity != null) { 
		    htmls=EntityUtils.toString(entity);
		    //System.out.println(htmls);
		    
		     
		}
		Parser parser=Parser.createParser(htmls, "GBK");
	    HtmlPage page=new HtmlPage(parser); 
	    NodeFilter filter = new TagNameFilter ("img");
        NodeList nodes = parser.extractAllNodesThatMatch(filter);
	    //System.out.println("nodelist.size();"+nodes.size());
	    ImageTag imageTag = null;
	    for(int i=0;i<nodes.size();i++)
	    {
	    	
	    	Node node=(Node)nodes.elementAt(i);
	    	if (node instanceof ImageTag) 
            {
                imageTag = (ImageTag)node;
                 
//                ���html�ı���ԭ����src����
                System.out.println( imageTag.getAttribute("src"));
                saveUrlFile(imageTag.getAttribute("src"),"D:\\pics\\"+picnum+".jpg");
                picnum++;
                break;
                
            }
	    	
	    	
	    	
	    }
	}
	//��ȡ���ӵ�ַ�ļ���byte����  
    public static byte[] getUrlFileData(String fileUrl) throws Exception  
    {  
        URL url = new URL(fileUrl);  
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  
        httpConn.connect();  
        InputStream cin = httpConn.getInputStream();  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while ((len = cin.read(buffer)) != -1) {  
            outStream.write(buffer, 0, len);  
        }  
        cin.close();  
        byte[] fileData = outStream.toByteArray();  
        outStream.close();  
        return fileData;  
    }  
	//��ȡ�����ļ���ת�浽fileDes�У�fileDes��Ҫ���ļ���׺��  
    public static void saveUrlFile(String fileUrl,String fileDes) throws Exception  
    {  
        File toFile = new File(fileDes);  
        if (toFile.exists())  
        {  
//          throw new Exception("file exist");  
            return;  
        }  
        toFile.createNewFile();  
        FileOutputStream outImgStream = new FileOutputStream(toFile);  
        outImgStream.write(getUrlFileData(fileUrl));  
        outImgStream.close();  
    } 
	public static void getMainPages(String htmls)throws Exception
	{
		Parser parser=Parser.createParser(htmls, "GBK");
	    HtmlPage page=new HtmlPage(parser);
	    try{
	    	parser.visitAllNodesWith(page);
	    }
	    catch(Exception ee)
	    {
	    	System.out.println(ee);
	    }
	    //filter
	    NodeList nodelist=page.getBody();
	    NodeFilter filter=new TagNameFilter("a");
	    nodelist=nodelist.extractAllNodesThatMatch(filter,true);
	    String keywords="/home/main/?un=";
	    for(int i=0;i<nodelist.size();i++)
	    {
	    	
	    	LinkTag link=(LinkTag)nodelist.elementAt(i);
	    	String get1=link.getAttribute("href");
	    	//System.out.println(get1);
	    	if(get1!=null&&get1.length()>keywords.length())
	    	{
	        	if(get1.substring(0, 15).equals(keywords))
	        	{
	        		getPicUrl("http://tieba.baidu.com/"+get1);
	        		//System.out.println(get1);
	        		//break;
	        	}
	    	}
	    	
	    	
	    	
	    }
	    
	    /*
	    try{
	    	File file=new File("test.html");
	    	OutputStream outputStream=new FileOutputStream(file);
	    	int bytesWritten = 0;
	    	int byteCount = 0;

	    	byte[] bytes = new byte[1024];
	    	
	    	while ((byteCount = inputStream.read(bytes)) != -1)
	    	{
	    	          outputStream.write(bytes, bytesWritten, byteCount);
	    	          System.out.println("get here");
	    	           //bytesWritten += byteCount;
	    	}
	    	
	    	inputStream.close();
	    	outputStream.close();
	    	
	    	
	    	RandomAccessFile file=new RandomAccessFile("test.html","rw");
	    	file.writeChars(htmls);
	    	file.close();
	    }
	    catch(Exception ee){
	    	System.out.println(ee);
	    }*/
	    
	    //��17�����ӿ�ʼ������
	    //System.out.println(EntityUtils.toString(entity)); 
	}
	}
