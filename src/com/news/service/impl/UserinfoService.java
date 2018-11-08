package com.news.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.news.dao.impl.UserinfoDAO;
import com.news.entity.User;
/**
 * 用户业务处理层
 * @author ASUS
 *
 */
public class UserinfoService {
	UserinfoDAO userinfodao  = new UserinfoDAO();
	
	public int FindUser(String uname, String upwd) throws SQLException{
		return userinfodao.FindUser(uname, upwd);
	}
	
	public List<User> FindUser1(String uname, String upwd){
		return userinfodao.FindUser1(uname, upwd);
	}
	
}
