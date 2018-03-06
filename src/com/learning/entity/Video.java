package com.learning.entity;

import java.util.ArrayList;

public class Video {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideofile() {
		return videofile;
	}
	public void setVideofile(String videofile) {
		this.videofile = videofile;
	}
	public int getBelongto() {
		return belongto;
	}
	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}
	private String videofile;
	private int belongto;
	private String videoname;
	public String getVideoname() {
		return videoname;
	}
	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	private double videotime;
	public double getVideotime() {
		return videotime;
	}
	public void setVideotime(double videotime) {
		this.videotime = videotime;
	}
	
	private int chapter;
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	@Override
	public String toString() {
		return "Video [id=" + id + ", videofile=" + videofile + ", belongto=" + belongto + ", videoname=" + videoname
				+ ", videotime=" + videotime + ", chapter=" + chapter + "]";
	}
	
	private int chapterde;
	public int getChapterde() {
		return chapterde;
	}
	public void setChapterde(int chapterde) {
		this.chapterde = chapterde;
	}
	
	
}
