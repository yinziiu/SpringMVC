package com.news.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.news.entity.User;
import com.news.service.impl.UserinfoService;

@Controller
public class LoginAction {
	
	UserinfoService service = new UserinfoService();
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String Login(HttpServletRequest request,HttpServletResponse response) throws SQLException{
		HttpSession session = request.getSession();
		String uname  = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		List<User> userlist = service.FindUser1(uname, upwd);
		if (userlist.size()>0) {
			session.setAttribute("uname", uname);
			session.setAttribute("upwd", upwd);
			for(User user : userlist){
				System.out.println(user);
			}
			//登录成功转发到TopicAction执行getAllTopic方法
			//由getAllTopic觉定处理完之后，到哪个页面
			return "forward:/allTopic"; 
		}else{
			//如果没有得到请求参数，也就是登录失败后，转发到LoginFilter过滤器
			//由过滤器处理到哪个页面
			return "forward:/LoginFilter";
		}
		
	}
	
	

}
