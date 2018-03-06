package com.course.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.learning.dao.UploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class upImageAction extends ActionSupport{
	
	public String execute(){
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST); 
		 UploadService.upload(request);
		 return SUCCESS;
	}
	
	
}
