package com.news.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.news.entity.News;
import com.news.entity.NewsPage;
import com.news.service.impl.NewsService;

/**
 * 新闻控制器
 * @author ASUS
 *
 */
@Controller
public class NewsAction {
	
	private NewsService NewsService = new NewsService();

	@RequestMapping("/add_news")
	public String addNews(News news){
		System.out.println("---添加新闻---"+news);
		
		boolean flag = NewsService.addNews(news);
		if (flag) {
			System.out.println("添加成功");
			//添加成功就转发到allNews
			//由allNews，决定到哪个页面
			return "forward:/allNews";
		}
		//添加不成就到添加页面
		return "newspages/news_add";
	}
	
	//获取所有的新闻列表
	@RequestMapping("/allNews")
	public String getNewsByAll(Model model){
		//调用方法获取列表
		List<News> listNews = NewsService.getNewsByAll();
		//把获取到的列表放到 request 里边
		model.addAttribute("listNews", listNews);
		System.out.println(listNews);
		//去到index页面
		return "index";
	}
	
	//更新新闻
	@RequestMapping("/update_news")
	public String updateNews(News news){
		System.out.println("---更新新闻---"+news);
		boolean flag = NewsService.updateNews(news);
		if (flag) {
			System.out.println("更新成功");
			//更新成功就转发到allNews
			//由allNews，决定到哪个页面
			return "forward:/allNews";
		}
		//不成功就到新闻列表
		return "newspages/topic_list";
	}

}
