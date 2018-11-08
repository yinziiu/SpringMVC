package com.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.news.entity.Topic;
import com.news.jdbc.util.BaseDao;
import com.news.jdbc.util.JDBCUtil;

/**
 * 新闻主题数据操作层
 * @author ASUS
 *
 */
public class TopicDAO{
	
	BaseDao baseDao = new BaseDao();
	
	/**
	 * 添加主题
	 * @param tname
	 * @return
	 */
	public boolean addTopic(String tname){
		System.out.println(tname);
		String sql = "insert into topic(topic_name) "
				+ "values(?);";
		Object [] objs = {tname};
		
		boolean listTopics = baseDao.executeUpdate(sql, objs);
		if (listTopics) {
			System.out.println("添加成功");
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除主题
	 * @param tid
	 * @return
	 */
	public boolean  deleteTopic(int tid) {
		String sql = " delete from topic where tid=? ";
		Object [] objs = {tid};
		boolean flag = baseDao.executeUpdate(sql, objs);
		if (flag) {
			System.out.println("删除成功");
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取所有的主题列表
	 * @return
	 */
	public List<Topic> getAllTopic(){
		System.out.println("=====TopicDAO:getAllTopic=====");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//创建一个主题集合
		List<Topic> topicList = new ArrayList<Topic>();

		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//编写sql语句
			String sql = "SELECT tid,topic_name FROM topic ";
			//执行sql语句
			ps = conn.prepareStatement(sql);
			//执行查询方法
			rs = ps.executeQuery();
			while (rs.next()) {
				Topic t = new Topic();
				t.setTid(rs.getInt("tid"));
				t.setTopicName(rs.getString("topic_name"));
				//把获取到的数据添加到集合中
				topicList.add(t);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtil.close(rs, ps, conn);
		}
		return topicList;

	}

	/**
	 * 根据tid查询主题
	 * @param tid
	 * @return
	 */
	public Topic queryTopicById(int tid){
		System.out.println("=====TopicDAO:queryTopicById=====");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Topic t = new Topic();

		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT tid,topic_name FROM topic where tid=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tid);
			rs = ps.executeQuery();
			while (rs.next()) {
				t.setTid(rs.getInt("tid"));
				t.setTopicName(rs.getString("topic_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JDBCUtil.close(rs, ps, conn);
		}
		return t;

	}

	/**
	 * 根据tid更新主题
	 * @param topicName
	 * @param tid
	 * @return
	 */
	public int  updateTopic(String topicName, String tid) {
		Connection conn = null;
		PreparedStatement ps = null;
		int nresult =0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = " update topic set topic_name =? where tid=? ";

			ps = conn.prepareStatement(sql);
			//设置参数
			ps.setString(1, topicName);
			ps.setInt(2, Integer.valueOf(tid) );
			nresult = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtil.close(null, ps, conn);
		}
		return nresult;
	}
}
