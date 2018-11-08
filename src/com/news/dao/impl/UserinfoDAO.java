package com.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.news.entity.User;
import com.news.jdbc.util.BaseDao;
import com.news.jdbc.util.JDBCUtil;

/**
 * 用户数据操作层
 * @author ASUS
 *
 */
public class UserinfoDAO {
	
	BaseDao baseDao = new BaseDao();
	
	public List<User> FindUser1(String uname, String upwd) {
		//编写sql语句
		String sql = "select * FROM userinfo " + 
				" WHERE user_name=? and user_pwd=? ";
		//处理参数问题
		Object[] objects = {uname,upwd};
		//根据sql语句和参数查询数据
		List<User> user = baseDao.executeQuery(sql, objects, User.class);
		//返回查询到的数据
		return user;
	}
	
	public int FindUser(String uname, String upwd) throws SQLException {
		System.out.println("==========UserinfoDAO ========= ");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int ncount = 0;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(*) ncount FROM userinfo " + 
					" WHERE user_name=? and user_pwd=? ";
			System.out.println("sql:" + sql);
			System.out.println("参数:user_name:" + uname + ",user_pwd:" + upwd);
			ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upwd);
			rs = ps.executeQuery();
			while (rs.next()) {
				ncount = rs.getInt("ncount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JDBCUtil.close(rs, ps, conn);
		}
		return ncount;
	}
	
	
	
	
}
