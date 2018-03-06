package com.learning.entity;

public class CourseAnaly {


	public String getCoursetype() {
		return coursetype;
	}
	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public String getFinishprecent() {
		return finishprecent;
	}
	public void setFinishprecent(String finishprecent) {
		this.finishprecent = finishprecent;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	private String coursetype;
	private String coursename;
	private String lastdate;
	private String finishprecent;
	private String filepath;
	private String lastchapter;
	public String getLastchapter() {
		return lastchapter;
	}
	public void setLastchapter(String lastchapter) {
		this.lastchapter = lastchapter;
	}
	@Override
	public String toString() {
		return "CourseAnaly [coursetype=" + coursetype + ", coursename=" + coursename + ", lastdate=" + lastdate
				+ ", finishprecent=" + finishprecent + ", filepath=" + filepath + ", lastchapter=" + lastchapter + "]";
	}
	private int videoid;
	
	
	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	private int courseid;
	
	
	
}
