package com.learning.entity;

public class Support {
	
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
	public int getVideocommentid() {
		return videocommentid;
	}
	public void setVideocommentid(int videocommentid) {
		this.videocommentid = videocommentid;
	}
	public int getIfsupport() {
		return ifsupport;
	}
	public void setIfsupport(int ifsupport) {
		this.ifsupport = ifsupport;
	}
	private int videocommentid;
	//�����ж��Ƿ����ޣ���������Ϊ1���ٴε�����Ϊ0
	private int ifsupport;
	@Override
	public String toString() {
		return "Support [userid=" + userid + ", videocommentid=" + videocommentid + ", ifsupport=" + ifsupport + "]";
	}
	
	
	
}
