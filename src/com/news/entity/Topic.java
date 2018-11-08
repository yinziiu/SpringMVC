package com.news.entity;

public class Topic {
	private int tid;
	private String topicName;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Topic(int tid, String topicName) {
		super();
		this.tid = tid;
		this.topicName = topicName;
	}
	public Topic() {
		super();
	}
	public Topic(String topicName) {
		super();
		this.topicName = topicName;
	}
	@Override
	public String toString() {
		return "Topic [tid=" + tid + ", topicName=" + topicName + "]";
	}
	
	

}
