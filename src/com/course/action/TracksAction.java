package com.course.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CourseDao;
import com.course.dao.LearningTracksDao;
import com.course.dao.PageDao;
import com.hankcs.hanlp.HanLP;
import com.learning.entity.Course;
import com.learning.entity.CourseAnaly;
import com.learning.entity.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TracksAction extends ActionSupport{
	
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
		int videoid = Integer.parseInt(request.getParameter("videoid"));
		//int videoid = Integer.parseInt(request.getParameter("videoid"));
		int currenttime = Integer.parseInt(request.getParameter("time"));
		System.out.println("the time is "+currenttime);
		LearningTracksDao.addlt(videoid, userid);
		
		
		
				
		return SUCCESS;
	}
	
	
	public String addtracks() throws Exception{
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		int videoid = Integer.parseInt(request.getParameter("videoid"));
		int playtime = Integer.parseInt(request.getParameter("time"));
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
		LearningTracksDao.updatelt(videoid,playtime,userid);
		
		
		System.out.println("接收到的参数"+videoid+playtime);
		
		
		return "pauseadd";
	}
	
	
	
	public String startadd(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
	
		
		int videoid = Integer.parseInt(request.getParameter("videoid"));
		LearningTracksDao.startaddlt(videoid, userid);
		
		//HanLP.extractKeyword("",5);
		return "startaddlt";
		
	}
	
	public String gettracks(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
		List list = new ArrayList<CourseAnaly>();
		
		list = LearningTracksDao.showUserLt(userid);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
		
		request.setAttribute("trackslist", list);
		context.put("trackslist", list);
		
		return "getok";
	}
	
	
}
