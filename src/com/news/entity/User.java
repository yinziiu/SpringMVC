package com.news.entity;

public class User {
	private int uid;
	private String userName;
	private String pwd;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public User(String userName, String pwd) {
		super();
		this.userName = userName;
		this.pwd = pwd;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int uid, String userName, String pwd) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + ", pwd=" + pwd + "]";
	}
	
	
	
}
