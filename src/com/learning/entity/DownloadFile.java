package com.learning.entity;

public class DownloadFile {
	
	
	private String filename;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	private String filepath;
	@Override
	public String toString() {
		return "DownloadFile [filename=" + filename + ", filepath=" + filepath + "]";
	}
	

}
