package com.news.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		//获去Url
		String uri = httpRequest.getRequestURI();
		System.out.println(uri);
		
		String userName = (String) session.getAttribute("uname");
		String upwd = (String) session.getAttribute("upwd");
		System.out.println(userName);
		
		//判断是否在当前页
		if(uri.indexOf("/index.jsp")>-1){
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		// 判断如果没有取到员工信息,就跳转到登陆页面
        if (userName == null || "".equals(userName)) {
            // 跳转到登陆页面
            httpResponse.sendRedirect("/News_86/index.jsp");
        	//request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
        }
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
