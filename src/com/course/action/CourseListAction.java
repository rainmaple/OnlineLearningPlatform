package com.course.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CourseDao;
import com.course.dao.PageDao;
import com.learning.entity.Course;
import com.learning.entity.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CourseListAction extends ActionSupport {
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;
	
	private List courselist;
	
	
	public List getCourselist() {
		return courselist;
	}
	public void setCourselist(List courselist) {
		this.courselist = courselist;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		List list = new ArrayList<Course>();
		
		
		
		
		list = CourseDao.GetCourseList();
		context.put("courselist", list);
		
		Page page = new Page();
		page.setPagesize(12);
		page.setPagenow(1);
		page = PageDao.getpageinfo(page);
		request.setAttribute("pageinfo", page);
		
		System.out.println("pageinfo is"+page.toString());
		context.put("pageinfo", page);
		list = CourseDao.page(page);
		context.put("coursepage1",list);
		
				
		return SUCCESS;
	}
	
	public String getcourselistBytype(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		List list = new ArrayList<Course>();
		list = CourseDao.getCorseByType(type);
		context.put("courseoftype", list);
		
		return 	"listbytype";
		
	}
	
	//根据页数返回课程列表
	public String getcourselistBypage(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Page page = new Page();
		int pagenow=1;
		int pagecount;
		
		page.setPagesize(12);
		System.out.println("判断"+request.getParameter("pagenow"));
		if(request.getParameter("pagenow")!=null){
		    pagenow = Integer.parseInt(request.getParameter("pagenow"));
			pagecount = Integer.parseInt(request.getParameter("pagecount"));
			System.out.println("传递的pagecount为"+pagecount);
			page = PageDao.getpageinfo(page);
			page.setPagenow(pagenow);
		}else{
			page.setPagenow(1);
			page = PageDao.getpageinfo(page);
		}
		
		
//		System.out.println(page.toString());
		
//		int pagenow = Integer.parseInt(request.getParameter("pagenow"));
//		int pagecount = Integer.parseInt(request.getParameter("pagecount"));
//		
//		page.setPagecount(pagecount);
		
		
		request.setAttribute("pageinfo", page);
		context.put("pageinfo", page);
		List list = new ArrayList<Course>();
		list = CourseDao.page(page);
		context.put("coursepage1",list);
		return "listbypage";
		
	}
	
	
	public String searchcou(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String coursekeywords = request.getParameter("keywords");
		
		Page page = new Page();
		int pagenow=1;
		int pagecount;
		
		page.setPagesize(6);
		System.out.println("判断"+request.getParameter("pagenow"));
		if(request.getParameter("pagenow")!=null){
		    pagenow = Integer.parseInt(request.getParameter("pagenow"));
			pagecount = Integer.parseInt(request.getParameter("pagecount"));
			System.out.println("传递的pagecount为"+pagecount);
			page = PageDao.getsearchpage(page,coursekeywords);
			//this.setCoursekeywords(request.getParameter("keywords"));
			page.setPagenow(pagenow);
		}else{
			page.setPagenow(1);
			page = PageDao.getsearchpage(page,coursekeywords);
		}
		
		System.out.println("the coursekeywords is"+coursekeywords);
		
		List list = new ArrayList<Course>();
		list = CourseDao.getCourseByname(page, coursekeywords);
		
		System.out.println("the list size is"+list.size());
		context.put("searchlist", list);
		context.put("pageinfo",page);
		request.setAttribute("pageinfo", page);
		
		request.setAttribute("keywords",coursekeywords);
		context.put("keywords",coursekeywords);
		
		return "searchok";
	}
	
	public String getcourlistbytype(){
		
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String difi = request.getParameter("difi");
		String type = request.getParameter("type");
		String time = request.getParameter("time");
		
		difi = difi.substring(0,difi.length()/2);
		type = type.substring(0, type.length()/2);
		time = time.substring(0,time.length()/2);
		
		//System.out.println("时间的长度是"+time.length());
		
		System.out.println(difi+type+time);
		
		Page page = new Page();
		int pagenow=1;
		int pagecount;
		
		page.setPagesize(12);
		//System.out.println("判断"+request.getParameter("pagenow"));
		if(request.getParameter("pagenow")!=null){
		    pagenow = Integer.parseInt(request.getParameter("pagenow"));
			pagecount = Integer.parseInt(request.getParameter("pagecount"));
			//System.out.println("传递的pagecount为"+pagecount);
			page = PageDao.gettypepage(page, time.trim(), type.trim(), difi.trim());
			//page = PageDao.getsearchpage(page,coursekeywords);
			//this.setCoursekeywords(request.getParameter("keywords"));
			page.setPagenow(pagenow);
		}else{
			page.setPagenow(1);
			page = PageDao.gettypepage(page,time.trim(), type.trim(), difi.trim());
			//age = PageDao.getsearchpage(page,coursekeywords);
		}
		
		//System.out.println("the coursekeywords is"+coursekeywords);
		System.out.println("page的信息是"+page.toString());
		
		
		List list = new ArrayList<Course>();
		list = CourseDao.gettypebylist(page, time, type, difi);
		System.out.println(list.toString());
		this.courselist = list;
		context.put("typelist", list);
		context.put("pageinfo",page);
		request.setAttribute("pageinfo", page);
		
	
		
		return "courselist";
	}
	

	
	
	
}
