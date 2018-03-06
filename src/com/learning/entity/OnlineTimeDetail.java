package com.learning.entity;

public class OnlineTimeDetail {

	private int userid;
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
	public String getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}
	public String getOnlineday() {
		return onlineday;
	}
	public void setOnlineday(String onlineday) {
		this.onlineday = onlineday;
	}
	private String username;
	private String onlinetime;
	private String onlineday;
	
	
}
