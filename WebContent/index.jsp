<%@page import="com.news.service.impl.NewsService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.news.entity.News"%>
<%@page import="com.news.dao.impl.NewsDAO"%>
<%@page import="com.news.entity.Topic"%>
<%@page import="com.news.dao.impl.TopicDAO"%>
<%@page import="com.news.entity.NewsPage"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻中国</title>
<link href="${ pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	function check(){
		var login_username = document.getElementById("uname");
		var login_password = document.getElementById("upwd");
		if(login_username.value == ""){
			alert("用户名不能为空！请重新填入！");
			login_username.focus();	
			return false;
		}else if(login_password.value == ""){
			alert("密码不能为空！请重新填入！");
			login_password.focus();
			return false;
		}
		return true;
	}
	
	function focusOnLogin(){
		var login_username = document.getElementById("uname");
		login_username.focus();	
	}
</script>

<%
		List<News> list = new ArrayList();
		list = (List<News>) request.getAttribute("currNewsList");
		String currPage = request.getParameter("currPage");
		NewsPage newsPage = (NewsPage)request.getAttribute("newsPage");
		int pageCount = (int)request.getAttribute("pageCount");
		System.out.println("test.jsp打印----"+newsPage.getCurrPageNo());
		int nextPage = newsPage.getCurrPageNo()+1;
		int backPage = newsPage.getCurrPageNo()-1;
	%>
</head>
<body onload="focusOnLogin()">

<div id="header">
  <div id="top_login">
    <form action="<%=request.getContextPath() %>/login" method="post" onsubmit="return check()">
     
      <label> 登录名 </label>
      <input type="text" name="uname" value="" class="login_input" />
      <label> 密&#160;&#160;码 </label>
      <input type="password" name="upwd" value="" class="login_input" />
      <input type="submit" class="login_sub" value="登录" />
      <label id="error"> </label>
      <img src="images/friend_logo.gif" alt="Google" id="friend_logo" />
    </form>
  </div>
  <div id="nav">
    <div id="logo"> <img src="images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="images/a_b01.gif" alt="" /> </div>
    <!--mainnav end-->
  </div>
</div>
<div id="container">
  <div class="sidebar">
    <h1> <img src="images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul>
      <c:forEach items="${listNews1 }" var="n">
        <li> <a href='#'><b> ${n.title} </b></a> </li>
      </c:forEach>
      </ul>
    </div>
    <h1> <img src="images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <c:forEach items="${listNews2 }" var="n">
        <li> <a href='#'><b> ${n.title} </b></a> </li>
      </c:forEach>
    </div>
    <h1> <img src="images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <c:forEach items="${listNews5 }" var="n">
        <li> <a href='#'><b> ${n.title} </b></a> </li>
      </c:forEach>
    </div>
  </div>
  <div class="main">
    <div class="class_type"> <img src="images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
        <li id='class_month'> 
        	<c:forEach items="${listTopic }" var="t">
        		<a href='#'><b> ${t.topicName} </b></a>
        	</c:forEach>
        </li>
      </ul>
      
    </div>
    <div class="main">
    <div class="class_typ e"> <img src="images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
        <li id='class_month'> 
        	<c:forEach items="${currNewsList }" var="n">
        		 <li> <a href='#' style="font-size: 16px;"><b> ${n.title}</b></a> </li>
        		 <p style="text-indent: 2em;">${n.content }</p>
     		   	 <p style="float: right;">作者：${n.aurthor }
     		   		&nbsp;&nbsp;&nbsp;&nbsp;发布时间：${n.createdate }</p>
     		   		<br/>
     		   		<hr />
     		   		<br />
        	</c:forEach>
        </li>
        <p align="center">
        <a href="index.jsp?currPage=1">[首页]</a> &nbsp;&nbsp;
			<a href="index.jsp?currPage=<%=backPage %>">上一页</a> &nbsp;&nbsp;
			 第&nbsp;${newsPage.currPageNo }&nbsp;页
			<a href="index.jsp?currPage=<%=newsPage.getNextPageNo() %>">下一页</a> &nbsp;&nbsp;
			<a href="index.jsp?currPage=<%=pageCount %>">[尾页]</a> 
		</p>
      </ul>
      
    </div>
    <%@include file="index-elements/index_rightbar.html"%>
  </div>
</div>
  <%@include file="index-elements/index_bottom.html"%>
</body>
</html>
