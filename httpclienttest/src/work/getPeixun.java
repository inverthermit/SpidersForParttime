package work;

import java.io.File;
import java.io.FileInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class getPeixun {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
  	    
       getIPs();
       
   	 

	}
	public static void getURL() throws Exception
	{
		// TODO Auto-generated method stub
				FileInputStream fis=new FileInputStream(new File("./file/Peixun.html"));
		        
		        byte[] b=new byte[fis.available()];
		        fis.read(b);
		       fis.close();
		        String htmls=new String(b);
		       Parser parser=Parser.createParser(htmls, "utf-8");
		       AndFilter liFilter=new AndFilter(new TagNameFilter("div"),
		               new HasAttributeFilter("class","txt-box"));
		       NodeList nodes1=parser.extractAllNodesThatMatch(liFilter);
		  	    for(int i=0;i<nodes1.size();i++)
		  	    {
		  	    	
		  	    	//p class snapshot
		  	    	parser=Parser.createParser(nodes1.elementAt(i).toHtml(), "utf-8");
		  	       AndFilter pFilter=new AndFilter(new TagNameFilter("p"),
		  	               new HasAttributeFilter("class","snapshot"));
		  	       NodeList nodes2=parser.extractAllNodesThatMatch(pFilter);
		  	       String get2="";
		  	       //System.out.println(html2Str(nodes2.elementAt(0).toHtml()).split("&nbsp").length);
		  	       if(nodes2.size()==4)
		  	       {
		  	    	 get2=html2Str(nodes2.elementAt(0).toHtml()).split("&nbsp")[3].replace(";", "");
		  	       }
		  	       else if(nodes2.size()==2)
			       {
			    	 get2=html2Str(nodes2.elementAt(0).toHtml()).split("&nbsp")[0].replace(";", "");
			       }
		  	    	   
		  	       
		  	       
		  	       
		  	    	parser=Parser.createParser(nodes1.elementAt(i).toHtml(), "utf-8");
		  	       AndFilter ProfessionNameFilter=new AndFilter(new TagNameFilter("a"),
		  	                   new HasAttributeFilter("href"));
		  	   	    NodeList nodes4=parser.extractAllNodesThatMatch(ProfessionNameFilter);
		  	   	    
		  	   	    	LinkTag link=(LinkTag)nodes4.elementAt(0);
		  	   	    	String get1=link.getAttribute("href");
		  	   	    	System.out.println(html2Str(link.toHtml())+"	http://weixin.sogou.com"+get1);
		  	   	    
		  	    }
	}
	public static String url="http://www.aizhan.com/siteall/";
	public static void getIPs() throws Exception{
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();  
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();  
		
		for(int i=0;i<URLS.length;i++)
		{
			int index=i;
			Thread.sleep(2000);
			//System.out.println(index);
			HttpGet httpGet = new HttpGet(url+URLS[index].split("/")[0]); 
			HttpResponse response = httpclient.execute(httpGet);  
			HttpEntity entity = response.getEntity();
			String htmls=null;
			if (entity != null) { 
			    htmls=EntityUtils.toString(entity).replace("\t", " ");
			}
			//System.out.println(htmls);
			Parser parser=Parser.createParser(htmls.replace("span", "form"), "utf-8");
	        AndFilter TF=new AndFilter(new TagNameFilter("form"),new HasAttributeFilter("id","baidu_ip"));
	        NodeList nodes2 = parser.extractAllNodesThatMatch(TF);
	        //System.out.println("Size:"+nodes2.size());
	        int flag=0;
	        if(nodes2.size()>0)
	        {
	        	for(int j=0;j<nodes2.size();j++)
	        	{
	        		
	        		//***************get links;name;type;time
	        		Node node=(Node)nodes2.elementAt(j);
	        		if(node.toHtml().contains("~"))
	        		{
	        			//System.out.println(node.toHtml());
	        			System.out.println(index+"	"+html2Str(node.toHtml()).replace("\n", ""));
	        			flag=1;
	        			break;
	        		}
	        		
	                
	                
	        	}
	        	
	    	    
	        }
	        if(flag==0)
	        	System.out.println(index+"	0");
		}
		//System.out.println("DONE.");
		
		
	}
	
	public static String html2Str(String html) { 
		return html.replaceAll("<[^>]+>", "");
	}
	
	
		public static String HTMLFilter(String input) {
		    if (input == null) {
		        input = "";
		        return input;
		    }
		    input = input.trim().replaceAll("&amp;", "&");
		    input = input.trim().replaceAll("&lt;", "<");
		    input = input.trim().replaceAll("&gt;", ">");
		    input = input.trim().replaceAll("    ", " ");
		    input = input.trim().replaceAll("<br>", "\n");
		    input = input.trim().replaceAll("&nbsp;", "  ");
		    input = input.trim().replaceAll("&quot;", "\"");
		    input = input.trim().replaceAll("&#39;", "'");
		    input = input.trim().replaceAll("&#92;", "\\\\");
		    input = input.trim().replaceAll("&#...;", "");
		    input = input.trim().replaceAll("&#....;", "");
		    return input;
		
 	}
		
	public static String[] URLS={
		"edu.21cn.com/xuexiao/bjjysd/",
		"www.q8963505.com/",
		"www.jzcxcy.cn/jinduoshou/",
		"www.tudou.com/programs/view/X7e4Azb2hRs/",
		"v.ku6.com/v666",
		"v.163.com/paike/V8H1BIE6U/V9ULRR5A2.html",
		"www.kaixin001.com/repaste/48619537_7019852986.html",
		"www.tudou.com/programs/view/DgfX0i8ykrk/",
		"my.tv.sohu.com/us/222698768/77750748.shtml",
		"www.waaku.com/infoview10097409.html",
		"jiaoyu.huangye88.com/xinxi/62263190.html",
		"classad.zbinfo.net/bencandy.php?fid=230&id=475671",
		"blog.10jqka.com.cn/79597833/4520552.shtml",
		"www.houxue.com/kecheng/282537/",
		"www.diglog.com/index?hot=0&tag=%E7%82%92%E8%82%A1%E5%9F%B9%E8%AE%AD",
		"changzhi.baixing.com/jinengpeixun/a287927976.html",
		"blog.9666.cn/hqk750717/1052500",
		"g67295220.100ye.com/",
		"nj.houxue.com/xuexiao/83557.html",
		"www.88mf.com/chaoguruanjian/29747.html",
		"my.tv.sohu.com/us/240619123/77627235.shtml",
		"www.edu84.com/2012/11/1/23592503/",
		"my.ku6.com/playlist/playlist?li=0&channel=4516362",
		"jiazhitouji.com/rumenzhishi/xgmydyjsddgzcgpxb_4857.html",
		"www.365zhaosheng.com/school/xyfc/570579178.shtml",
		"lzhnldk.blog.163.com/blog/static/145735141201331214839898/",
		"news.sun0769.com/dg/headnews/201510/t20151019_5915214.shtml",
		"blog.sina.com.cn/s/blog_9ec0deb90101mv08.html",
		"www.myjdzi.com/ideas/ShowArticle.asp?ArticleID=9080945",
		"blog.sina.com.cn/s/blog_48f924dc01017bff.html",
		"bbs.cnnb.com/thread-5465170-1-1.html",
		"v.dhtv.cn/201507/00019194.html",
		"www.360doc.com/content/10/1121/20/3122719_71242595.shtml",
		"www.365zhaosheng.com/course/2010/06/20100622124956576113.shtml",
		"wz.58.com/zhiyepeix/22384294730657x.shtml",
		"peixun.enorth.com.cn/xzhj_zx_49102.htm",
		"jiaoyu.huangye88.com/xinxi/60959089.html",
		"www.p5w.net/today/200908/t2488064.htm",
		"www.waaku.com/infoview9907144.html",
		"nj.studyems.com/s0031035855/news1.html",
		"kejijie.cslg.cn/Html/?868.html",
		"chongqing.qd8.com.cn/jineng/xinxi14_1438553.html",
		"v.emoney.cn/video/column/date_19_4.shtml",
		"www.sywg.com/sw/node61/node75/node85/u1ai1397.html",
		"caijing.changsha.cn/cqgs/201105/t20110526_1257946.htm",
		"v.emoney.cn/video/video_81932270.shtml",
		"guangzhou.edeng.cn/jiedaoxinxi/209165856.html",
		"stock.cngold.org/shipin/c1581322.html",
		"cn.51tie.com/qitakecheng/5388381x.html",
		"wap.thea.cn/xzhj_zx_657918.html",
		"www.qianyan.biz/sshow-35901752.html",
		"guangzhou.jinti.com/zhiyejinengpeixun/82015-13149422.htm",
		"v.youku.com/v_show/id_XNzM1MzcyOTg4.html",
		"www.wanke001.com/Course/Detail/CRS2013052400036/e6e5e1cb-2610-4623-abf7-3db53bfc832d.html?AspxAutoDetectCookieSupport=1",
		"taiyuan.qd8.com.cn/zigezhengshu/xinxi14_205812.html",
		"www.qingxuetang.com/cour/45649.html",
		"www.baiyewang.com/g67295220.html",
		"guangzhou.jinti.com/zhiyerenzhengpeixun/102015-13196678.htm",
		"wenda.tianya.cn/answer/5ffbc6957e7e054200047798d9f427f1",
		"www.iqiyi.com/w_19rsg1gjst.html",
		"g67272283.100ye.com/",
		"www.letv.com/ptv/vplay/23762550.html",
		"cn.51tie.com/qitakecheng/5388347x.html",
		"m.d.119g.com/f/3B7B1D06FC8F8415_bak.html",
		"guangzhou.ziye114.com/peixun/6650210.html",
		"blog.renren.com/share/345211519/10183016081",
		"jiangsu.3158.cn/info/20150710/n36147103379739.html",
		"finance.jrj.com.cn/2010/11/2714338660784.shtml",
		"www.letv.com/ptv/vplay/23292968.html",
		"www.taoxue114.com/SellDetail/389.html",
		"www.zbmf.com/people/16743/blog/1728363/",
		"028px.com/pxinfo/px_info.php?id=60540",
		"www.bibitie.com/guangzhou/zhiyepeixun/x239x22955317.html",
		"www.thinksaas.cn/favorite/show/4113/",
		"www.pxto.com.cn/Courses/1123475.html",
		"stock.hexun.com/2012-09-02/145389800.html",
		"sem.hrbeu.edu.cn/jingguanxueyuan/ShowArticle.asp?ArticleID=145350",
		"bbs.tiexue.net/post_2477046_1.html",
		"shanghai.edushi.com/bdt/3g/detail/536673.shtml",
		"www.mgold.cn/m/zbsc/09-8-4-84056.html",
		"mp.weixin.qq.com/s?__biz=MzI4NDAzMTYyMg==&mid=405702680&idx=1&sn=7e153c1d36a960a7ab70737ed11d613b&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MjM5NzYyMTM3Mw==&mid=401379870&idx=4&sn=9e84e758da1c5becf2ac6b19c92caae5&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MjM5MDg3MTM5OQ==&mid=401645085&idx=1&sn=a693660693ff32b0817b5fec33b84291&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MjM5NzYyMTM3Mw==&mid=401066782&idx=5&sn=339e51214c0fdba362e1fd8f99bf2ecc&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzIzMDEzOTQzOA==&mid=401505448&idx=3&sn=95cff336942cb463f9c81ea880caee17&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzA3MjA0NDY2OA==&mid=401137262&idx=1&sn=d1bef8230e338c193076a393d8fd79bd&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MjM5NTA0NzcyMg==&mid=403064533&idx=1&sn=18164840f7b4abc56b727cdb7ef1afe8&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzA3MDc3NDE2Mg==&mid=400142386&idx=2&sn=8887c487a40c72c8eff505d5de0204e6&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzAwMDA0Mzg5Mw==&mid=400023218&idx=6&sn=a6f0463129de816e164d15c58e1e4ed0&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzA5MDE0ODIzMQ==&mid=258706022&idx=7&sn=7a51ee2d3a1ea728034a6fd318f96e55&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzA5MTE1MTcxOQ==&mid=211407994&idx=4&sn=11a4470cefbec039fed50557b37cd33e&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MjM5MDg3MTM5OQ==&mid=401033481&idx=1&sn=99c47961e1f8af47fa423e2456976ca1&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MjM5NzYyMTM3Mw==&mid=400899122&idx=5&sn=4630aa908a27e6e87c6fb3d02cff0554&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzAwMDA0Mzg5Mw==&mid=208381133&idx=6&sn=fe81d2566cd68527655295e3d3d39c16&3rd=MzA3MDU4NTYzMw==&scene=6#rd",
		"mp.weixin.qq.com/s?__biz=MzA3MDc3NDE2Mg==&mid=208425360&idx=2&sn=4cc462b89e9a76f370637a805c184cab&3rd=MzA3MDU4NTYzMw==&scene=6#rd"
	};

}
