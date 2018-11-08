package com.news.jdbc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yi
 * @date 2016年10月22日
 */

public class BaseDao {
	/**
	 * 获得连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConn() throws Exception {

		/* -***********************-mysql数据库-*********************** */
		Class.forName("com.mysql.jdbc.Driver");
		String uri = "jdbc:mysql://localhost:3306/ns?&useUnicode=true&characterEncoding=UTF-8";
		return DriverManager.getConnection(uri, "root", "123");

		/*
		 * -***********************sqlserver2005数据库-*******************
		 * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); String
		 * uri="jdbc:sqlserver://127.0.0.1:1433;DataBaseName=master"; return
		 * DriverManager.getConnection(uri,"sa","123");
		 */
	}

	/**
	 * 关闭连接
	 * 
	 * @param rs
	 * @param sm
	 * @param conn
	 */
	public void closeAll(ResultSet rs, PreparedStatement sm, Connection conn)
			throws Exception {
		if (rs != null)
			rs.close();
		if (sm != null)
			sm.close();
		if (conn != null)
			conn.close();
	}

	/**
	 * 增加,删除,修改
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean executeUpdate(String sql, Object[] args) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement sm = null;
		try {
			conn = this.getConn();
			sm = conn.prepareStatement(sql);
			if (conn != null) {
				for (int i = 0; i < args.length; i++) {
					sm.setObject(i + 1, args[i]);
				}
			}

			if (sm.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeAll(null, sm, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 查询方法
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List executeQuery(String sql, Object[] args, Class clazz) {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try {
			conn = this.getConn();
			sm = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					sm.setObject(i + 1, args[i]);
				}
			}
			rs = sm.executeQuery();
			while (rs.next()) {
				// 通过反射得到一个对象
				list.add(this.getObj(clazz, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeAll(null, sm, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static Object getObj(Class clazz, ResultSet rs) throws Exception {
		// 所有的属性
		Field[] field = clazz.getDeclaredFields();
		Object info = clazz.newInstance();
		for (int i = 0; i < field.length; i++) {
			String name = field[i].getName();
			// 得到方法名
			name = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
			// 得到类型
			Class c = field[i].getType();
			// 得到方法
			Method method = clazz.getMethod(name, c);
			// 实现方法
			if (rs.getObject(i + 1)!=null) {
				method.invoke(info, rs.getObject(i + 1));
			}
		}
		return info;
	}

	public int getTotal(String sql){
		int total=0;
		Connection conn = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try {
			conn = this.getConn();
			sm = conn.prepareStatement(sql);
			rs = sm.executeQuery();
			while (rs.next()) {
				// 通过反射得到一个对象
				total=rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeAll(null, sm, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return total;
	}
}