package com.course.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CollectionDao;
import com.course.dao.CourseDao;
import com.course.dao.ProblemDao;
import com.learning.entity.Problems;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CollectAction extends ActionSupport{
	
	private int flag;
	
	
	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		//userid¥”sessionªÒ»°
		int flag = CollectionDao.updatecollect(courseid, 666);
		this.flag = flag;
		System.out.println("the flag is"+flag);
				
		return SUCCESS;
	}
	
	
	public String getcollection(){
		
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List list = new ArrayList();
		list  = CourseDao.getCollection(666);
		context.put("collectionlist", list);
		
		return "collectok";
	}
	

}
