
<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%
	/* request.setCharacterEncoding("utf-8");
	String opr = request.getParameter("opr");
	NewsDao newsDao=new NewsDaoImpl();
	TopicsDao topicDao=new TopicsDaoImpl();
	if(opr.equals("list")){//编辑新闻时对新闻的查找
		List<News> list=newsDao.getAllnews();	
		session.setAttribute("list",list);
		response.sendRedirect("../newspages/admin.jsp");
	}else if(opr.equals("listTitle")){//首次进入首页面
		List<News> list1 = newsDao.getAllnewsByTName("国内");
		List<News> list2 = newsDao.getAllnewsByTName("国际");
		List<News> list3 = newsDao.getAllnewsByTName("娱乐");
		List<Topic> list4 = topicDao.getAllTopics();
		List<News> list5 = newsDao.getAllnews();
		session.setAttribute("list1",list1);//左侧国内新闻
		session.setAttribute("list2",list2);//左侧国际新闻
		session.setAttribute("list3",list3);//左侧娱乐新闻
		session.setAttribute("list4",list4);//所有的主题
		session.setAttribute("list5",list5);//中间的所有新闻
		response.sendRedirect("../index.jsp");			
	}else if(opr.equals("topicNew")){//中间的某主题下的新闻		
		String Tid=request.getParameter("tid");
		List<News> list5 = newsDao.getAllnewsByTID(Integer.valueOf(Tid));
		session.setAttribute("list5",list5);//中间的某主题下的新闻
		response.sendRedirect("../index.jsp");	
	} */
%>

