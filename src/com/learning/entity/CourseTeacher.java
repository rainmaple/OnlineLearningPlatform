package com.learning.entity;

public class CourseTeacher {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getTeachersay() {
		return teachersay;
	}
	public void setTeachersay(String teachersay) {
		this.teachersay = teachersay;
	}
	private int teacherid;
	private String teachername;
	private int courseid;
	private String teachersay;
	@Override
	public String toString() {
		return "CourseTeacher [id=" + id + ", teacherid=" + teacherid + ", teachername=" + teachername + ", courseid="
				+ courseid + ", teachersay=" + teachersay + "]";
	}
	
	
	
}
