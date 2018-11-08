package com.news.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.news.entity.News;
import com.news.entity.NewsPage;
import com.news.entity.Topic;
import com.news.jdbc.util.BaseDao;
import com.news.service.impl.NewsService;
import com.news.service.impl.TopicService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

/**
 * Servlet Filter implementation class IndexFilter
 */
@WebFilter("/IndexFilter")
public class IndexFilter implements Filter {

	NewsService newsservice = new NewsService();
	TopicService topicService = new TopicService();
	NewsPage npage = new NewsPage();

    /**
     * Default constructor. 
     */
    public IndexFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//获所有的新闻列表存到域里，在index.jsp进行取出显示
		List<News> listNews = newsservice.getNewsByAll();
		request.setAttribute("listNews", listNews);
		//根据主题id获取相应的新闻列表存到域里，在index.jsp进行分主题显示
		List<News> listNews1 = newsservice.getNews(1);
		request.setAttribute("listNews1", listNews1);
		List<News> listNews2 = newsservice.getNews(2);
		request.setAttribute("listNews2", listNews2);
		List<News> listNews5 = newsservice.getNews(2);
		request.setAttribute("listNews5", listNews5);
		
		//获取所有的主题列表存到域里，在index.jsp进行取出显示
		List<Topic> listTopic = topicService.getAllTopic();
		request.setAttribute("listTopic", listTopic);
		System.out.println(listNews);
		
		//获取当前页码数，为1
		String currPage = request.getParameter("currPage");
		System.out.println("收到 currPage:"+currPage);
		//判断当前页码，不为空就为当前页，为空就设置为第一页
		if(currPage!=null){
			npage.setCurrPageNo( Integer.valueOf(currPage) );
			System.out.println("==currPage!=null currPage:"+npage.getCurrPageNo());
		}else{
			npage.setCurrPageNo( 1 );
			System.out.println("==npage.setCurrPageNo( 1 ); currPage:"+npage.getCurrPageNo());
		}
		//设置每一页为7条记录
		npage.setPageSize(7);
		//获取总新闻数
		System.out.println(newsservice.getNewsCount());
		//获取总记录数
		int n = newsservice.getNewsCount();
		//s设置总记录数，也就是返回的是总页码数
		int p = npage.setTotalCount(n);
		//获取总页数
		List<News> currNewsList = new ArrayList();
		//调用方法进行分页，参数中需要当前页码，每页记录数，都封装 到npage中
		currNewsList = newsservice.getCurrenPageNews(npage);
		//把相应的数据放到域里
		request.setAttribute("currNewsList", currNewsList);
		request.setAttribute("newsPage", npage);
		request.setAttribute("pageCount", p);
		System.out.println("===转发到 index.jsp====currPage:"+npage.getCurrPageNo());
		
		//请求转发到index.jsp页面
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		chain.doFilter(request, response);
	}

	private int getTotalPageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
