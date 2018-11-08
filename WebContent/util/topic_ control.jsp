
<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%	
	/* request.setCharacterEncoding("utf-8");
	String opr = request.getParameter("opr");
	TopicsDao topicsDao=new TopicsDaoImpl();
	if(opr.equals("update")){	//更新主题
		String tid = request.getParameter("tid");
		String tname = request.getParameter("tname");
		Map<String,String> topic = new HashMap<String,String>();
		topic.put("tid",tid);
		topic.put("tname",tname);
		if(topicsDao.updateTopic(topic)>0){	
		List<Topic> list4=topicsDao.getAllTopics();	
		session.setAttribute("list4",list4);	 */	
%>
			<%-- <script type="text/javascript">			    
				alert("已经成功更新主题，点击确认返回主题列表");
				location.href="topic_ control.jsp?opr=list";				
			</script>
			<%				
			}else{			
			%>
			<script type="text/javascript">
				alert("更新主题失败，点击确认返回主题列表");
				location.href="../newspages/topic_list.jsp";	
			</script>
<%		
		}
	}else if(opr.equals("list")){
		List<Topic> list4=topicsDao.getAllTopics();	
		session.setAttribute("list4",list4);
		response.sendRedirect("../newspages/topic_list.jsp");
	}else if(opr.equals("add")){//添加主题
		String tname = request.getParameter("tname");
		Topic topic=topicsDao.findTopicByName(tname);
		if(topic==null){
			topicsDao.addTopic(tname);
			List<Topic> list4=topicsDao.getAllTopics();	
			session.setAttribute("list4",list4);%>
			<script type="text/javascript">
			alert("当前主题创建成功，点击确认返回主题列表！");
			location.href="topic_ control.jsp?opr=list";	
			</script>
<%			
		}else{%>
		    <script type="text/javascript">
			alert("当前主题已存在，请输入不同的主题！");
			location.href="../newspages/topic_add.jsp";	
			</script>
<%			
		}
	}else if(opr.equals("del")){//删除主题
		String tid = request.getParameter("tid");
		NewsDao newsDao=new NewsDaoImpl();
		if(newsDao.getAllnewsByTID(Integer.valueOf(tid)).size()<=0){
			if(topicsDao.deleteTopic(tid)>0){
			List<Topic> list4=topicsDao.getAllTopics();	
			session.setAttribute("list4",list4);	
	%>
				<script type="text/javascript">
					alert("已经成功删除主题，点击确认返回原来页面");
					location.href="topic_ control.jsp?opr=list";	
				</script>
	<%		}else{%>
				<script type="text/javascript">
					alert("删除主题失败！请联系管理员查找原因！点击确认返回原来页面");
					location.href="topic_ control.jsp?opr=list";	
				</script>
	<%		}
		}else{%>
				<script type="text/javascript">
					alert("该主题下还有文章，不能删除！");
					location.href="topic_ control.jsp?opr=list";	
				</script>
	<%		}
} --%>
%>
