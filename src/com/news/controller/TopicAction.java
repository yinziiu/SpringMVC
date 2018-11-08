package com.news.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.news.entity.Topic;
import com.news.jdbc.util.BaseDao;
import com.news.service.impl.NewsService;
import com.news.service.impl.TopicService;

@Controller
public class TopicAction {
	
	BaseDao baseDao = new BaseDao();
	TopicService service = new TopicService();
	private NewsService NewsService = new NewsService();
	
	@RequestMapping("/add_topic")
	public String addTopic(String tname){
		System.out.println("---add_topic----"+tname);
		boolean flag = service.addTopic(tname);
		if (flag) {
			System.out.println("添加成功");
			//添加成功就转发到allTopic
			//由allTopic，决定到哪个页面
			return "forward:/allTopic";
		}
		//添加不成就跳到添加页面
		return "newspages/topic_add";
	}
	
	//更新新闻主题
	@RequestMapping("/update_topic")
	public String  updateTopic(String tname, String tid){
		System.out.println("------update_topic------");
		int topic = service.updateTopic(tname, tid);
		System.out.println(topic);
		//更新成功就转发到allTopic
		//由allTopic，决定到哪个页面
		return "forward:/allTopic";
	}
	
	//加载主题列表
	@RequestMapping("/allTopic")
	public String getAllTopic(Model model){
		System.out.println("获取所有的Topic列表");
		//调用方法，获取所有的主题列表
		List<Topic> listTopic = service.getAllTopic();
		System.out.println(listTopic);
		//放到 request 里边
		model.addAttribute("allTopic",listTopic);
		//加载到数据后，到admin页面去
		return "newspages/admin";
	} 
	
	//根据id获取主题
	@RequestMapping("/topic_id")
	public String queryTopicById(int tid,Model model){
		System.out.println("---根据id获取主题----");
		//调用方法根据tid获取到相应的主题
		Topic topic = service.queryTopicById(tid);
		//放到 request 里边
		model.addAttribute("topic", topic);
		if(topic!=null){
			//如果获取到的主题不为空，就到index页面
			return "index";
		}
		//为空，就跳到添加页面
		return "newspages/topic_add";
		
	}
	
	//删除新闻中的关联主题
	@RequestMapping("/delete_news")
	public void deleteNews(int ntid){
		System.out.println("---删除新闻---"+ntid);
		NewsService.deleteNews(ntid);
	}

	//删除主题，需要先删除新闻中的关联主题
	@RequestMapping("/delete_topic")
	public String deleteTopic(int tid){
		deleteNews(tid);
		boolean flag = service.deleteTopic(tid);
		if (flag) {
			//删除成功就转发到allTopic
			//由allTopic，决定到哪个页面
			return "forward:/allTopic";
		}
		//删除不成功就到admin页面
		return "newspages/admin";
		
		
	}
	

}
