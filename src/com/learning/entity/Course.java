package com.learning.entity;

import java.util.List;

public class Course {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLearnnum() {
		return learnnum;
	}
	public void setLearnnum(int learnnum) {
		this.learnnum = learnnum;
	}
	public String getTimelength() {
		return timelength;
	}
	public void setTimelength(String timelength) {
		this.timelength = timelength;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	private int learnnum;
	private String timelength;
	private String coursename;
	private String courseType;
	private String coursedesc;
	private String coursefile;
	public String getCoursefile() {
		return coursefile;
	}
	public void setCoursefile(String coursefile) {
		this.coursefile = coursefile;
	}
	public String getCoursedesc() {
		return coursedesc;
	}
	public void setCoursedesc(String coursedesc) {
		this.coursedesc = coursedesc;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", learnnum=" + learnnum + ", timelength=" + timelength + ", coursename="
				+ coursename + ", courseType=" + courseType + ", coursedesc=" + coursedesc + ", coursefile="
				+ coursefile + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursename == null) ? 0 : coursename.hashCode());
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (coursename == null) {
			if (other.coursename != null)
				return false;
		} else if (!coursename.equals(other.coursename))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	private String dificulty;
	public String getDificulty() {
		return dificulty;
	}
	public void setDificulty(String dificulty) {
		this.dificulty = dificulty;
	}
	
	
	private String department;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	private String position;
	
	
}
