package com.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.news.dao.impl.NewsDAO;
import com.news.entity.News;
import com.news.entity.NewsPage;

/**
 * 新闻业务处理层
 * @author ASUS
 *
 */
public class NewsService{
    private	NewsDAO newsdao =  new NewsDAO();
		
    //添加新闻
	public boolean addNews(News news){
		//封装时间，按照指定格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		news.setCreatedate(time);
		//调用层的添加方法
		boolean flag = newsdao.addNews(news);
		return flag;
	}
	
	//更新新闻
	public boolean updateNews(News news){
		//封装时间，按照指定格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		news.setCreatedate(time);
		//调用层的更新方法
		boolean flag = newsdao.updateNews(news);
		return flag;
	}
	
	//删除新闻
	public boolean deleteNews(int ntid){
		boolean flag = newsdao.deleteNews(ntid);
		return flag;
	}
	
	//获取新闻总数
	public int getNewsCount(){
		int totalCount =  newsdao.getCountNews();		
		return totalCount;
	}
	
	//获取新闻列表
	public List<News> getNewsByAll(){		
		return newsdao.getNewsByAll();		
	}
	
	//获取新闻最新的5条记录
	public List<News> getNews(int ntid){
		return newsdao.getNewsByNtid(ntid);
	}
	
	//处理分页，NewsPage为类
	public List<News> getCurrenPageNews( NewsPage npage ){
		//设置开始页，公式一般是当前页数减1再乘以一页的总记录数
		int start = (npage.getCurrPageNo() -1)*npage.getPageSize();
		//设置一页显示多少条记录
		int pageCount = npage.getPageSize();
		//调用dao中的分页方法
		List<News> list = newsdao.getNewsByPage(start, pageCount);
		return list;
	}
}
