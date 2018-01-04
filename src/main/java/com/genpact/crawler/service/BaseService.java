package com.genpact.crawler.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
@Component
@SuppressWarnings("all")
public class BaseService {
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	
	/**
	 * 方法名:getContent
	 * 返回值:Map<String,Object>
	 * 参    数:@param url
	 * 参    数:@return
	 * 作    者:710009498
	 * 时    间:Apr 15, 2016 5:13:03 PM
	 */
	public Map<String,Object> getContent(String url){
		Map<String,Object> map = Maps.newHashMap();
		map.put("flag", false);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(url)));
			String flag = null;
			StringBuffer sb = new StringBuffer();
			while((flag = reader.readLine()) != null){
				sb.append(flag).append("</br>");
			}
			reader.close();
			map.put("flag", true);
			map.put("content", sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
		
	}
	
	
	
	
}
