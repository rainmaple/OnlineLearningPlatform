package com.learning.entity;

public class Comment {
	
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String commentmain;
	public String getCommentmain() {
		return commentmain;
	}
	public void setCommentmain(String commentmain) {
		this.commentmain = commentmain;
	}
	public int getProblemid() {
		return problemid;
	}
	public void setProblemid(int problemid) {
		this.problemid = problemid;
	}
	public String getResponsetime() {
		return responsetime;
	}
	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private int problemid;
	private String responsetime;
	private int userid;
	private String username;
	private int support;

	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentmain=" + commentmain + ", problemid=" + problemid + ", responsetime="
				+ responsetime + ", userid=" + userid + ", username=" + username + ", support=" + support + "]";
	}
	
	

	
	
	
}
