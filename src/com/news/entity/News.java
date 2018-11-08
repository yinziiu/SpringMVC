package com.news.entity;

public class News {
	private int nid;
	private int ntid;
	private String title;
	private String aurthor;
	private String createdate;
	private String content;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getNtid() {
		return ntid;
	}
	public void setNtid(int ntid) {
		this.ntid = ntid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAurthor() {
		return aurthor;
	}
	public void setAurthor(String aurthor) {
		this.aurthor = aurthor;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public News(int nid, int ntid, String title, String aurthor,
			String createdate, String content) {
		super();
		this.nid = nid;
		this.ntid = ntid;
		this.title = title;
		this.aurthor = aurthor;
		this.createdate = createdate;
		this.content = content;
	}
	public News() {
		super();
	}
	public News(int ntid, String title, String aurthor, String createdate, String content) {
		super();
		this.ntid = ntid;
		this.title = title;
		this.aurthor = aurthor;
		this.createdate = createdate;
		this.content = content;
	}
	@Override
	public String toString() {
		return "News [nid=" + nid + ", ntid=" + ntid + ", title=" + title + ", aurthor=" + aurthor + ", createdate="
				+ createdate + ", content=" + content + "]";
	}
	
	
	
	
	
}
