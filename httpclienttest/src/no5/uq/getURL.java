package no5.uq;

import java.io.*;
import java.io.FileInputStream;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import java.util.*;
public class getURL
{

    public static void main(String[] args) throws Exception
    {
        // TODO Auto-generated method stub
    	//getUnData();
    	//getPostData();
    	 int [] data = {1,2,3,4,5,6,7,8,9};
         int [] newData;
         newData = Arrays.copyOfRange(data, 2, 7);
         for(int i:newData)
             System.out.print(i+" ");

    }
    public static String[][] getPostData()
    {
    	String[][] result=null;
    	try{

            FileInputStream fis=new FileInputStream(new File("./uqPost.html"));
            byte[] b=new byte[fis.available()];//鏂板缓涓�涓瓧鑺傛暟缁�
            fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
            fis.close();
            String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
            String tempTitle="";
            String tempTitleURL="";
            //System.out.println(htmls);
            int count=1;
            Parser	parser=Parser.createParser(htmls, "utf-8");
            TagNameFilter ListFilter=new TagNameFilter("tr");
            NodeList nodes0=parser.extractAllNodesThatMatch(ListFilter);
            BufferedWriter bw=new BufferedWriter(new FileWriter("D://Australia-Unis/UQ/Post.txt"));
            result=new String[nodes0.size()][7];
            for(int x=0; x<nodes0.size(); x++)
            {
            	//-----------------------title,title url---------------------------
                String section=nodes0.elementAt(x).toHtml();
                parser=Parser.createParser(section, "utf-8");
                AndFilter TitleFilter=new AndFilter(new TagNameFilter("td"),
                new HasAttributeFilter("class","title"));
                NodeList nodes4=parser.extractAllNodesThatMatch(TitleFilter);
                if(nodes4.size()>0)
                {
                    parser=Parser.createParser(nodes4.elementAt(0).toHtml(), "utf-8");
                    AndFilter TitleURLFilter=new AndFilter(new TagNameFilter("a"),
                    new HasAttributeFilter("href"));
                    NodeList nodes1=parser.extractAllNodesThatMatch(TitleURLFilter);
                    if(nodes1.size()>0)
                    {
                        LinkTag link=(LinkTag)nodes1.elementAt(0);
                        String get1=link.getAttribute("href");
                        parser=Parser.createParser(section, "utf-8");
                        TagNameFilter InformationFilter= new TagNameFilter("div");
                        NodeList nodes=parser.extractAllNodesThatMatch(InformationFilter);
                        String plain=html2Str(link.toHtml()).replace("\n","").trim();
                        if(!plain.equals(""))
                        {
                            tempTitle=plain;
                            tempTitleURL=get1;
                        }
                    }
                }
                //-----------------------plan,plan url---------------------------
                String plan="";
                String planurl="";
                parser=Parser.createParser(section, "utf-8");
                AndFilter PlanFilter=new AndFilter(new TagNameFilter("td"),
                new HasAttributeFilter("class","plan"));
                NodeList nodes3=parser.extractAllNodesThatMatch(PlanFilter);
                if(nodes3.size()>0)
                {
                    parser=Parser.createParser(nodes3.elementAt(0).toHtml(), "utf-8");
                    AndFilter TitleURLFilter=new AndFilter(new TagNameFilter("a"),
                    new HasAttributeFilter("href"));
                    NodeList nodes1=parser.extractAllNodesThatMatch(TitleURLFilter);
                    if(nodes1.size()>0)
                    {
                        LinkTag link=(LinkTag)nodes1.elementAt(0);
                        String get1=link.getAttribute("href");
                        parser=Parser.createParser(section, "utf-8");
                        TagNameFilter InformationFilter= new TagNameFilter("div");
                        NodeList nodes=parser.extractAllNodesThatMatch(InformationFilter);
                        String plain=html2Str(link.toHtml()).replace("\n","").trim();
                        if(!plain.equals(""))
                        {
                        	plan=plain;
                        	planurl=get1;
                        }
                    }
                }
                
              //-----------------------type---------------------------
                String type="";
                parser=Parser.createParser(section, "utf-8");
                AndFilter TypeFilter=new AndFilter(new TagNameFilter("td"),
                new HasAttributeFilter("class","type"));
                NodeList nodes2=parser.extractAllNodesThatMatch(TypeFilter);
                if(nodes2.size()>0)
                {
                	String plain=html2Str(nodes2.elementAt(0).toHtml()).replace("\n","").trim();
                	type=plain;
                }


                //System.out.println("{\""+count+"\",\""+tempTitle+"\",\"http://www.uq.edu.au"+tempTitleURL+"\",\""+plan+"\",\"http://www.uq.edu.au"+planurl+"\",\""+type+"\",\"0\"},");

                String line=("{\""+count+"\",\""+tempTitle+"\",\"http://www.uq.edu.au"+tempTitleURL+"\",\""+plan+"\",\"http://www.uq.edu.au"+planurl+"\",\""+type+"\",\"0\"},");
                String[] arr={""+count,tempTitle,"http://www.uq.edu.au"+tempTitleURL,plan,"http://www.uq.edu.au"+planurl,type,"0"};
                result[x]=arr;
                bw.write(org.apache.commons.lang3.StringUtils.join(result[x])+"\r\n");
                count++;
            }
            bw.close();
            //return result;
    	}
    	catch(Exception ee)
    	{
    		ee.printStackTrace();
    	}

    	return result;
    }
    public static String[][] getUnData()
    {
    	String[][] result=null;

    	try{
    		FileInputStream fis=new FileInputStream(new File("./uqUn.html"));
            byte[] b=new byte[fis.available()];//鏂板缓涓�涓瓧鑺傛暟缁�
            fis.read(b);//灏嗘枃浠朵腑鐨勫唴瀹硅鍙栧埌瀛楄妭鏁扮粍涓�
            fis.close();
            String htmls=new String(b);//鍐嶅皢瀛楄妭鏁扮粍涓殑鍐呭杞寲鎴愬瓧绗︿覆褰㈠紡杈撳嚭
            String tempTitle="";
            String tempTitleURL="";
            //System.out.println(htmls);
            int count=1;
            Parser	parser=Parser.createParser(htmls, "utf-8");
            TagNameFilter ListFilter=new TagNameFilter("tr");
            NodeList nodes0=parser.extractAllNodesThatMatch(ListFilter);
            BufferedWriter bw=new BufferedWriter(new FileWriter("D://Australia-Unis/UQ/Un.txt"));
            result=new String[nodes0.size()][7];
            for(int x=0; x<nodes0.size(); x++)
            {
            	//-----------------------title,title url---------------------------
                String section=nodes0.elementAt(x).toHtml();
                parser=Parser.createParser(section, "utf-8");
                AndFilter TitleFilter=new AndFilter(new TagNameFilter("td"),
                new HasAttributeFilter("class","title"));
                NodeList nodes4=parser.extractAllNodesThatMatch(TitleFilter);
                if(nodes4.size()>0)
                {
                    parser=Parser.createParser(nodes4.elementAt(0).toHtml(), "utf-8");
                    AndFilter TitleURLFilter=new AndFilter(new TagNameFilter("a"),
                    new HasAttributeFilter("href"));
                    NodeList nodes1=parser.extractAllNodesThatMatch(TitleURLFilter);
                    if(nodes1.size()>0)
                    {
                        LinkTag link=(LinkTag)nodes1.elementAt(0);
                        String get1=link.getAttribute("href");
                        parser=Parser.createParser(section, "utf-8");
                        TagNameFilter InformationFilter= new TagNameFilter("div");
                        NodeList nodes=parser.extractAllNodesThatMatch(InformationFilter);
                        String plain=html2Str(link.toHtml()).replace("\n","").trim();
                        if(!plain.equals(""))
                        {
                            tempTitle=plain;
                            tempTitleURL=get1;
                        }
                    }
                }
                //-----------------------plan,plan url---------------------------
                String plan="";
                String planurl="";
                parser=Parser.createParser(section, "utf-8");
                AndFilter PlanFilter=new AndFilter(new TagNameFilter("td"),
                new HasAttributeFilter("class","plan"));
                NodeList nodes3=parser.extractAllNodesThatMatch(PlanFilter);
                if(nodes3.size()>0)
                {
                    parser=Parser.createParser(nodes3.elementAt(0).toHtml(), "utf-8");
                    AndFilter TitleURLFilter=new AndFilter(new TagNameFilter("a"),
                    new HasAttributeFilter("href"));
                    NodeList nodes1=parser.extractAllNodesThatMatch(TitleURLFilter);
                    if(nodes1.size()>0)
                    {
                        LinkTag link=(LinkTag)nodes1.elementAt(0);
                        String get1=link.getAttribute("href");
                        parser=Parser.createParser(section, "utf-8");
                        TagNameFilter InformationFilter= new TagNameFilter("div");
                        NodeList nodes=parser.extractAllNodesThatMatch(InformationFilter);
                        String plain=html2Str(link.toHtml()).replace("\n","").trim();
                        if(!plain.equals(""))
                        {
                        	plan=plain;
                        	planurl=get1;
                        }
                    }
                }
                
              //-----------------------type---------------------------
                String type="";
                parser=Parser.createParser(section, "utf-8");
                AndFilter TypeFilter=new AndFilter(new TagNameFilter("td"),
                new HasAttributeFilter("class","type"));
                NodeList nodes2=parser.extractAllNodesThatMatch(TypeFilter);
                if(nodes2.size()>0)
                {
                	String plain=html2Str(nodes2.elementAt(0).toHtml()).replace("\n","").trim();
                	type=plain;
                }


                //System.out.println("{\""+count+"\",\""+tempTitle+"\",\"http://www.uq.edu.au"+tempTitleURL+"\",\""+plan+"\",\"http://www.uq.edu.au"+planurl+"\",\""+type+"\",\"0\"},");

                String line=("{\""+count+"\",\""+tempTitle+"\",\"http://www.uq.edu.au"+tempTitleURL+"\",\""+plan+"\",\"http://www.uq.edu.au"+planurl+"\",\""+type+"\",\"0\"},");
                String[] arr={""+count,tempTitle,"http://www.uq.edu.au"+tempTitleURL,plan,"http://www.uq.edu.au"+planurl,type,"0"};
                result[x]=arr;
                bw.write(org.apache.commons.lang3.StringUtils.join(result[x])+"\r\n");
                count++;
            }
            bw.close();
            //return result;
    	}
    	catch(Exception ee)
    	{
    		ee.printStackTrace();
    	}
    	return Arrays.copyOfRange(result, 1300, result.length);
    	//return result;
    }
    public static String html2Str(String html)
    {
        return html.replaceAll("<[^>]+>", "");
    }
   
}
