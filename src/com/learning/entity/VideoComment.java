package com.learning.entity;

public class VideoComment {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getSubmittime() {
		return submittime;
	}
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	private int videoid;
	private String username;
	private int userid;
	private String submittime;
	private int support;
	private String videocontent;
	public String getVideocontent() {
		return videocontent;
	}
	public void setVideocontent(String videocontent) {
		this.videocontent = videocontent;
	}
	@Override
	public String toString() {
		return "VideoComment [id=" + id + ", videoid=" + videoid + ", username=" + username + ", userid=" + userid
				+ ", submittime=" + submittime + ", support=" + support + "]";
	}
	
	
	
}
