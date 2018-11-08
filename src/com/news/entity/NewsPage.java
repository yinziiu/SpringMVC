package com.news.entity;

public class NewsPage {	
	private int totalCount;//新闻总记录数
	private int totalPageCount = 1; // 总页数
	private int pageSize = 7; // 页面大小，即每页显示记录数
	private int currPageNo = 1; // 当前页码
	
	//构造方法初始化当前页码
	public NewsPage(){
		currPageNo = 1;
	}
	
	//获取总页数
	public int getTotalPageCount() {
		return totalPageCount;
	}
	
	//获取新闻总记录数
	public int getTotalCount() {
		return totalCount;
	}
	//设置新闻总记录数和设置总页码
	public int setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//如果总记录数%每页显示记录数==0
		//也就是总页码数刚好等于总记录数除以每页记录数的商
		if( (totalCount % pageSize)==0 ){
			totalPageCount = totalCount / pageSize;
		}
		//如果总记录数%每页显示记录数不等于0
		//也就是最后一页不够每页定义的记录数，这样总页码需要加1
		else{
			totalPageCount = (totalCount / pageSize) +1;
		}
		return totalPageCount;
	}
	
	//一页的记录数的setter和getter方法
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//当前页码的setter和getter方法
	public int getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}

	//上一页
	public int getBackPageNo(){
		//当前页码数减1回到上一页
		this.currPageNo = this.currPageNo -1;
		return this.currPageNo;
	}
	//下一页
	public int getNextPageNo(){
		//当前页码数加1回到下一页
		this.currPageNo = this.currPageNo +1;
		return this.currPageNo;
	}
	
	
}
