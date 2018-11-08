package com.news.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/ns?useUnicode=true&characterEncoding=utf-8";
		String username = "root"; 
		String pwd = "123"; 	
		Connection conn = null; 
		
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs!=null) rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null) ps.close();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
