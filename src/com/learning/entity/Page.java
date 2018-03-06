package com.learning.entity;

import java.util.List;

public class Page {
	
	private int pagenow;
	public int getPagenow() {
		return pagenow;
	}
	public void setPagenow(int pagenow) {
		this.pagenow = pagenow;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getAllcount() {
		return allcount;
	}
	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}
	public List<Course> getCourselist() {
		return Courselist;
	}
	public void setCourselist(List<Course> courselist) {
		Courselist = courselist;
	}
	private int pagesize;
	private int allcount;
	private List<Course> Courselist;
	private int pagecount;
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	@Override
	public String toString() {
		return "Page [pagenow=" + pagenow + ", pagesize=" + pagesize + ", allcount=" + allcount + ", Courselist="
				+ Courselist + ", pagecount=" + pagecount + "]";
	}
	
	
	
	
}
