package com.genpact.crawler.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.genpact.crawler.service.ClbusService;
import com.genpact.crawler.service.ProductService;

@Controller
public class CrawlerController {
	@Autowired private ClbusService clbusService;
	@Autowired private ProductService productService;
	
	@RequestMapping(value="product",method=RequestMethod.GET)
	public String product(){
		return "product/index";
	}
	
	@RequestMapping(value="product",method=RequestMethod.POST)
	public void product(String url,HashMap<String, Boolean> map ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean flag = productService.crawler(url, request);
		map.put("flag", flag);
		response.getWriter().write(JSON.toJSONString(map));
	}
	
	@RequestMapping(value="getProductContext",method=RequestMethod.POST)
	public  void getProductContext(HttpServletResponse response,HttpSession session) throws IOException{
		String url = session.getServletContext().getRealPath("/downloads/product.txt");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(JSON.toJSONString(productService.getContent(url)));
	}
	
	
	
	@RequestMapping(value="clbus",method=RequestMethod.GET)
	public String clbus(){
		return "clbus/index";
	}
	
	@RequestMapping(value="clbus",method=RequestMethod.POST)
	public void clbus(String url,HashMap<String, Boolean> map ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean flag = clbusService.crawler(url, request);
		map.put("flag", flag);
		response.getWriter().write(JSON.toJSONString(map));
	}
	
	@RequestMapping(value="getClbusContext",method=RequestMethod.POST)
	public  void getClbusContext(HttpSession session,HttpServletResponse response) throws IOException{
		String url = session.getServletContext().getRealPath("/downloads/clbus.txt");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(JSON.toJSONString(productService.getContent(url)));
	}
	
}
