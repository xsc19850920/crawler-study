package com.genpact.crawler.service;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.genpact.crawler.bean.ProductCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Service
public class ProductService extends BaseService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	/**
	 * 方法名:crawler
	 * 描    述:TODO
	 * 返回值:boolean
	 * 参    数:@param url
	 * 参    数:@param request
	 * 参    数:@return
	 * 参    数:@throws NoSuchFieldException
	 * 参    数:@throws SecurityException
	 * 参    数:@throws InstantiationException
	 * 参    数:@throws IllegalAccessException
	 * 作    者:710009498
	 * 时    间:Apr 18, 2016 9:29:08 AM
	 * @throws Exception 
	 */
	public boolean crawler(String url, HttpServletRequest request) throws Exception{
		
		
		//FileUtils.deleteQuietly(new File(downloadPath));
    	logger.info("------------------------开始网页爬取---------------------------");
    	long beginTime = new Date().getTime();
    	
    	ServletContext servletContext = request.getSession().getServletContext();


		String crawlStorageFolder = servletContext.getRealPath("/downloads/product");
    	
		FileUtils.writeStringToFile(new File(servletContext.getRealPath("/downloads/product.txt")),"", false);
        //开启的爬虫数
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        
        config.setCrawlStorageFolder(crawlStorageFolder);
        //代理设置
        config.setProxyHost("58.2.221.9");
        config.setProxyUsername("800021189");
        config.setProxyPassword("G@28032008t");
//        config.setProxyUsername("chncorp\\ldap2digi");
//		config.setProxyPassword("Pass10word");
        config.setProxyPort(80);
        
        //设置深度
        config.setMaxDepthOfCrawling(78);
        
        //设置一共可以抓取的页数
//        config.setMaxPagesToFetch(70);
        
        //设置缓存抓取过的信息(如果true下次不会在抓取)
//        config.setResumableCrawling(false);
        //每次抓取请求等待100毫秒
        config.setPolitenessDelay(100);
        
        logger.debug("-------------运行参数:"+config.toString()+"--------------------");
        
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller  = new CrawlController(config, pageFetcher, robotstxtServer);
        controller.addSeed(url);
        controller.start(ProductCrawler.class, numberOfCrawlers);
        logger.info("------------------------结束网页爬取---------------------------");
        long endTime = new Date().getTime();
        logger.info("------------------------耗时:"+ ((endTime - beginTime)/1000) +" 秒---------------------------");
        
        return true;
	}
	
	
	
}
