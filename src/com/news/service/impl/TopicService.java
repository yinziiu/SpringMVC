package com.news.service.impl;

import java.util.List;
import com.news.dao.impl.TopicDAO;
import com.news.entity.Topic;

/**
 * 新闻主页业务处理层
 * @author ASUS
 *
 */
public class TopicService{
	
	private TopicDAO topicDAO = new TopicDAO();
	
	//添加主题
	public boolean addTopic(String tname){
		boolean flag = topicDAO.addTopic(tname);
		return flag;
	}
	
	//获取主题列表
	public List<Topic> getAllTopic(){
		return topicDAO.getAllTopic();
	}
	
	//根据tid查询主题
	public Topic queryTopicById(int tid){
		return topicDAO.queryTopicById(tid);
	}
	
	//根据tid更新主题
	public int  updateTopic(String topicName, String tid){
		return topicDAO.updateTopic(topicName, tid);
	}
	
	//根据tid删除主题
	public boolean  deleteTopic(int tid){
		boolean flag = topicDAO.deleteTopic(tid);
		return flag;
	}

	
}
