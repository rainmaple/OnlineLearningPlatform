package com.learning.entity;

public class LearningTracks {
	
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getFinishpercent() {
		return finishpercent;
	}
	public void setFinishpercent(String finishpercent) {
		this.finishpercent = finishpercent;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	private String username;
	private int courseid;
	private String finishpercent;
	@Override
	public String toString() {
		return "LerningTracks [userid=" + userid + ", username=" + username + ", courseid=" + courseid
				+ ", finishpercent=" + finishpercent + ", coursename=" + coursename + ", lastlearntime=" + lastlearntime
				+ "]";
	}
	private String coursename;
	private String lastlearntime;
	public String getLastlearntime() {
		return lastlearntime;
	}
	public void setLastlearntime(String lastlearntime) {
		this.lastlearntime = lastlearntime;
	}
	private int lastchapter;
	public int getLastchapter() {
		return lastchapter;
	}
	public void setLastchapter(int lastchapter) {
		this.lastchapter = lastchapter;
	}
	public int getLastchapterde() {
		return lastchapterde;
	}
	public void setLastchapterde(int lastchapterde) {
		this.lastchapterde = lastchapterde;
	}
	private int lastchapterde;

	private int videoid;


	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	
	
}
