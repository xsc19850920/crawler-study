package com.genpact.crawler.bean;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
@Component
public class ClbusCrawler extends WebCrawler {

private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|ico|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	private final static  Pattern URLFILTER = Pattern.compile("http://www.clbus.com/busiInfo/getBusiInfoByColumnType/1\\?pageSize=5&pageNum=\\d+");
	
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         if(FILTERS.matcher(href).matches()){
        	 return false;
         }else{
        	 if(URLFILTER.matcher(href).matches()){
        		 return true;
        	 }else{
        		 return false;
        	 }
         }
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();
         logger.info("-------------抓取 URL: " + url+"-----------------");
         String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/downloads/clbus.txt");
         if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             //String text = htmlParseData.getText();
             String html = htmlParseData.getHtml();
//             Set<WebURL> links = htmlParseData.getOutgoingUrls();
//             for (WebURL webURL : links) {
//            	 System.out.println(webURL.getURL());
//             }
             Document doc = Jsoup.parse(html);  
             Elements root = doc.select("div.center_first");
 			for (Element e : root) {
 				String time = e.select("div.date_top").html()+"-"+e.select("div.date_bottom").html();
 				String title = e.select("span.center_first_head").html();
 				String content = e.select("a").text();
 				StringBuffer sb = new StringBuffer();
 				sb.append(time + "|" + title + "|" + content+ System.getProperty("line.separator"));
 				
 				try {
 					FileUtils.writeStringToFile(new File(path), sb.toString(), true);
 				} catch (IOException e1) {
 					e1.printStackTrace();
 				}
 			}
            
             
           
             
//             System.out.println("Text length: " + text.length());
//             System.out.println("Html length: " + html.length());
//             System.out.println("Number of outgoing links: " + links.size());
         }
    }

	
     
     

}