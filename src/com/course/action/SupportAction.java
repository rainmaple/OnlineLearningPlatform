package com.course.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CommentDao;
import com.course.dao.SupportDao;
import com.course.dao.VideoDao;
import com.learning.entity.CourseComment;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SupportAction extends ActionSupport {
	
	private int supportnum;
	
	public int getSupportnum() {
		return supportnum;
	}

	public void setSupportnum(int supportnum) {
		this.supportnum = supportnum;
	}
	private int supportnum2;
	

	public int getSupportnum2() {
		return supportnum2;
	}

	public void setSupportnum2(int supportnum2) {
		this.supportnum2 = supportnum2;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int commentid = Integer.parseInt(request.getParameter("commentid"));
		System.out.println("courseid is"+commentid);
		
		int supportnum = SupportDao.updatesupport(commentid, 127);
		this.supportnum = supportnum;
		
		context.put("supportnum",supportnum);
	
		

				
		return SUCCESS;
	}
	
	
	
	public String updatesupport2() throws Exception{
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int commentid = Integer.parseInt(request.getParameter("commentid"));
		int supportnum = SupportDao.updatecsupport(commentid, 127);
		this.supportnum2 = supportnum;
		
		return "supp2";
	}

}
