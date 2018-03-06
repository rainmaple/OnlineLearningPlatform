package com.learning.entity;

import java.util.Date;

public class Problems {
	
	private int problem_id;
	private String title;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(int problem_id) {
		this.problem_id = problem_id;
	}
	public int getFromid() {
		return fromid;
	}
	public void setFromid(int fromid) {
		this.fromid = fromid;
	}
	public String getFrom_name() {
		return from_name;
	}
	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}
	public String getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}
	private int fromid;
	private String from_name;
	private String submit_time;
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Problems [problem_id=" + problem_id + ", fromid=" + fromid + ", from_name=" + from_name
				+ ", submit_time=" + submit_time + ", content=" + content + "]";
	}
	private String firstcomment;
	public String getFirstcomment() {
		return firstcomment;
	}
	public void setFirstcomment(String firstcomment) {
		this.firstcomment = firstcomment;
	}
	
	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	private String firstreplyuser;

	public String getFirstreplyuser() {
		return firstreplyuser;
	}

	public void setFirstreplyuser(String firstreplyuser) {
		this.firstreplyuser = firstreplyuser;
	}
	
	private String firstreplytime;

	public String getFirstreplytime() {
		return firstreplytime;
	}

	public void setFirstreplytime(String firstreplytime) {
		this.firstreplytime = firstreplytime;
	}
	
	private int viewnum;

	public int getViewnum() {
		return viewnum;
	}

	public void setViewnum(int viewnum) {
		this.viewnum = viewnum;
	}
	
	
}
