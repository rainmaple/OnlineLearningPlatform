package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class CourseComment {


	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	private int userid;
	private int courseid;
	private String username;
	private String date;
	private int support;
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHeadImage() {
		return headImage;
	}
	@Override
	public String toString() {
		return "CourseComment [id=" + id + ", userid=" + userid + ", courseid=" + courseid + ", username=" + username
				+ ", date=" + date + ", support=" + support + ", content=" + content + ", headImage=" + headImage + "]";
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	private String headImage;
	
	
}
