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
public class ProductCrawler extends WebCrawler  {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|ico|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	private Pattern URLFILTER = Pattern.compile("http://product.yesky.com/electronic/list\\d+.html");

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		if (FILTERS.matcher(href).matches()) {
			return false;
		} else {
			if (URLFILTER.matcher(href).matches()) {
				return true;
			} else {
				return false;
			}
		}
	}
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		logger.info("-------------" + Thread.currentThread().getName() + " 抓取 URL: " + url + "-----------------");
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/downloads/product.txt");
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			// String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			// Set<WebURL> links = htmlParseData.getOutgoingUrls();
			// for (WebURL webURL : links) {
			// System.out.println(webURL.getURL());
			// }
			Document doc = Jsoup.parse(html);

			Elements root = doc.select("div.list ");
			for (Element e : root) {
				String name = e.select("h2 a").html();
				String price = e.select("h3.orange a").html();
				String category = e.select("li").first().html().replaceAll("产品类别：", "");
				String type = e.select("li").last().html().replaceAll("产品型号：", "");
				StringBuffer sb = new StringBuffer();
				sb.append(name + "|" + price + "|" + category + "|" + type + System.getProperty("line.separator"));
				
				try {
					FileUtils.writeStringToFile(new File(path), sb.toString(), true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	

}