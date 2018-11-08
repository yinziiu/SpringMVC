package com.news.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.news.entity.Topic;
import com.news.service.impl.TopicService;

/**
 * Servlet Filter implementation class TopicFrlter
 */
@WebFilter("/TopicFrlter")
public class TopicFrlter implements Filter {
	
	TopicService service = new TopicService();

    /**
     * Default constructor. 
     */
    public TopicFrlter() {
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
		List<Topic> listTopic = service.getAllTopic();
		request.setAttribute("listTopic", listTopic);
		//request.getRequestDispatcher("newspages/admin.jsp").forward(request, response);
		System.out.println(listTopic);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
