package com.course.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CollectionDao;
import com.course.dao.UserDao;
import com.course.dao.VideoCommentDao;

import com.learning.entity.VideoComment;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.UserBean;

public class UserAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String,Object> session = null;
		session = context.getSession();
		//session.put("userid", 1);
		
		//User user = new User();
		
//		User user = new User();
//		user.setId(6);
//		user.setDepartment("技术部");
//		user.setMailbox("123456789@qq.com");
//		user.setNickname("小明");
//		user.setPassword("970802");
//		user.setUsername("张明");
//		user.setPosition("董事长");
		
		
		//User user = session.get("user");
		int userid = (Integer)session.get("user_id");
		
		UserBean user = UserDao.getuserByid(userid);
		//System.out.println(id);
		context.put("user", user);
		
				
		return SUCCESS;
	}
	
	public String changemessage(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String nickname = request.getParameter("newname");
		String depart = request.getParameter("newdep");
		String position = request.getParameter("newpos");
		
		Map<String,Object> session = null;
		session = context.getSession();
		//session.put("user_id", 1);
		
		int userid = (Integer)session.get("user_id");
		
		
		UserDao.changeuser(userid, nickname, depart, position);
		
		ServletActionContext.getRequest().getSession().setAttribute("user_nickname",nickname);// 保存昵称
		
		return "changeok";
	}
	
	
}
