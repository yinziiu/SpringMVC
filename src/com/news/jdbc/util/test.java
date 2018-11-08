package com.news.jdbc.util;


import java.util.Date;

import com.news.dao.impl.NewsDAO;
import com.news.entity.News;
import com.news.service.impl.NewsService;

public class test {

	public static void main(String[] args) {
		NewsDAO newsDAO = new NewsDAO();
		NewsService service = new NewsService();
		//News news = new News(2, "1111111", "22", "", "1111111111111");
		//service.addNews(news);
		System.out.println(newsDAO.getCountNews());
	}

}
