package com.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.news.entity.News;
import com.news.jdbc.util.BaseDao;
import com.news.jdbc.util.JDBCUtil;

/**
 * 新闻数据操作层
 * @author ASUS
 *
 */
public class NewsDAO {

	BaseDao baseDao = new BaseDao();

	/**
	 * 添加新闻
	 * 
	 * @return
	 */

	public boolean addNews(News news) {
		System.out.println(news);
		//编写sql语句
		String sql = "insert into news(ntid,title,aurthor,createdate,content) " + "values(?,?,?,?,?);";
		//处理参数
		Object[] objs = { news.getNtid(), news.getTitle(), news.getAurthor(), news.getCreatedate(), news.getContent() };
		System.out.println(objs);
		//根据sql语句和参数执行添加新闻
		boolean listNews = baseDao.executeUpdate(sql, objs);
		if (listNews) {
			System.out.println("添加成功");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取所有的新闻列表
	 * 使用JCBD的写法
	 * @return
	 */
	public List<News> getNewsByAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<News>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select * from news ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				News newsOne = new News();
				newsOne.setNid(rs.getInt("nid"));
				newsOne.setNtid(rs.getInt("ntid"));
				newsOne.setTitle(rs.getString("title"));
				newsOne.setAurthor(rs.getString("aurthor"));
				newsOne.setCreatedate(rs.getString("createdate"));
				newsOne.setContent(rs.getString("content"));
				list.add(newsOne);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据nid获取到最新的5条记录
	 * @param ntid
	 * @return
	 */
	public List<News> getNewsByNtid(int ntid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<News>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select * from news where ntid=?  order by createdate desc limit 0,5;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ntid);
			rs = ps.executeQuery();
			while (rs.next()) {
				News newsOne = new News();
				newsOne.setNid(rs.getInt("nid"));
				newsOne.setNtid(rs.getInt("ntid"));
				newsOne.setTitle(rs.getString("title"));
				newsOne.setAurthor(rs.getString("aurthor"));
				newsOne.setCreatedate(rs.getString("createdate"));
				newsOne.setContent(rs.getString("content"));
				list.add(newsOne);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据nid更新数据
	 * @param news
	 * @return
	 */
	public boolean updateNews(News news) {
		String sql = "update news set ntid=?,title=?,aurthor=?,createdate=?content=? where ntid=? ";
		Object[] objs = { news.getNtid(), news.getTitle(), news.getAurthor(), news.getCreatedate(), news.getContent() };
		boolean newslist = baseDao.executeUpdate(sql, objs);
		if (newslist) {
			System.out.println("更新成功");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据nid删除数据
	 * @param ntid
	 * @return
	 */
	public boolean deleteNews(int ntid) {
		String sql = "delete from news where ntid=? ";
		Object[] objs = { ntid };
		boolean newslist = baseDao.executeUpdate(sql, objs);
		if (newslist) {
			System.out.println("删除成功");
			return true;
		} else {
			return false;
		}
	}

	// 取新闻表 news 总数
	public int getCountNews() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nCount = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(1) from news ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				nCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, conn);
		}

		return nCount;
	}

	/**
	 * 分页
	 * 参数是开始页和一页的总数 
	 * @param start
	 * @param pageCount
	 * @return
	 */
	public List<News> getNewsByPage(int start, int pageCount) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<News>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select * from news limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, pageCount);
			rs = ps.executeQuery();
			while (rs.next()) {
				News newsOne = new News();
				newsOne.setNid(rs.getInt("nid"));
				newsOne.setNtid(rs.getInt("ntid"));
				newsOne.setTitle(rs.getString("title"));
				newsOne.setAurthor(rs.getString("aurthor"));
				newsOne.setCreatedate(rs.getString("createdate"));
				newsOne.setContent(rs.getString("content"));
				list.add(newsOne);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

}
