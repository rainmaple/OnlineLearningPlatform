package com.learning.entity;

public class Category {

	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	private String percent;
	@Override
	public String toString() {
		return "Category [type=" + type + ", percent=" + percent + "]";
	}
	
}
