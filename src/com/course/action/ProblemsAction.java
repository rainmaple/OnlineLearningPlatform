package com.course.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CommentDao;
import com.course.dao.CourseDao;
import com.course.dao.PageDao;
import com.course.dao.ProblemDao;
import com.course.dao.UserDao;
import com.course.dao.VideoDao;
import com.learning.entity.Comment;
import com.learning.entity.Course;
import com.learning.entity.CourseComment;
import com.learning.entity.Page;
import com.learning.entity.Problems;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.UserBean;

public class ProblemsAction extends ActionSupport {
	
	
	private List prolist;
	
	private String keywords;
	
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List getProlist() {
		return prolist;
	}

	public void setProlist(List prolist) {
		this.prolist = prolist;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Problems pro = new Problems();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//String date = request.getParameter("date");
		String type = request.getParameter("type");
		Map<String,Object> session = null;
		session = context.getSession();
		
		int userid = (Integer) session.get("user_id");
		
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date = df.format(new Date());
		
		System.out.println(title+content+date+type);
		pro.setContent(content);
		pro.setFromid(1234);
		pro.setFrom_name((String)session.get("user_nickname"));
		pro.setSubmit_time(date);
		pro.setTitle(title);
		pro.setType(type);
		
		ProblemDao.attachproblemtoCourse(pro);
		
				
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
	
	//根据页数返回问题列表
	public String getprolistBypage(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Page page = new Page();
		int pagenow=1;
		int pagecount;
		
		page.setPagesize(4);
		
		System.out.println("判断"+request.getParameter("pagenow"));
		if(request.getParameter("pagenow")!=null){
		    pagenow = Integer.parseInt(request.getParameter("pagenow"));
			pagecount = Integer.parseInt(request.getParameter("pagecount"));
			System.out.println("传递的pagecount为"+pagecount);
			page.setPagecount(pagecount);
			page.setPagenow(pagenow);
		}else{
			page.setPagenow(1);
			page = PageDao.getpropageinfo(page);
		}
		
		System.out.println(page.toString());

		
		
		request.setAttribute("pageinfo", page);
		context.put("pageinfo", page);
		List list = new ArrayList<Problems>();
		list = ProblemDao.page(page);
		
//		for(int i = 0 ; i < list.size() ; i++){
//			Problems tracks = (Problems)list.get(i);
//			System.out.println(tracks.toString());
//		}
		
		context.put("problems",list);
		this.prolist = list;
		return "prolist";
		
	}
	
	

	public String firstgetProlist() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Page page = new Page();
		int pagenow=1;
		
		
		page.setPagesize(4);
		page.setPagenow(pagenow);
		page = PageDao.getpropageinfo(page);
		
		List list2 = new ArrayList<Course>();
		list2 = CourseDao.getrandomCourse();
		
		context.put("randCourse", list2);
		
		
		request.setAttribute("pageinfo", page);
		context.put("pageinfo", page);
		List list = new ArrayList<Problems>();
		list = ProblemDao.page(page);
		context.put("problems",list);
		
		
		return "firstlist";
	}
	
	
	public String showProdetailbyid(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int problemid = Integer.parseInt(request.getParameter("problemid"));
		ProblemDao.addviewnum(problemid);
		
		List relevantlist = ProblemDao.getrelevantPro(problemid);
		if(relevantlist.size()>2){
			List newrelevant = new ArrayList();
			newrelevant.add(relevantlist.get(0));
			newrelevant.add(relevantlist.get(1));
		    context.put("relevantlist",newrelevant);
		}else{
			context.put("relevantlist", relevantlist);
		}
		
		List list = new ArrayList<Comment>();
		list = CommentDao.getCommentByProblemid(problemid);
		
		Problems pr = ProblemDao.getProByid(problemid);
		int comcount = ProblemDao.getCommcount(problemid);
		
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
		request.setAttribute("pro", pr);
		
		context.put("comlist", list);
		context.put("pro", pr);
		context.put("count", comcount);
		
		return "detail";
	}
	
	
	public String submitprocom(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
		int problemid = Integer.parseInt(request.getParameter("problemid"));
		request.setAttribute("problemid", problemid);
		String comcontent = request.getParameter("comcontent");
		
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		
		Comment com = new Comment();
		com.setCommentmain(comcontent);
		com.setUserid(userid);
		
		UserBean ub = UserDao.getuserByid(userid);
		
		com.setUsername((String)session.get("user_nickname"));
		com.setProblemid(problemid);
		com.setSupport(0);
		com.setResponsetime(date);
		CommentDao.attachcommenttoProblem(com);
		
		return "submitok";
	}
	
	public String getProBykeywords(){
		//System.out.println(this.keywords);
		
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List list = new ArrayList<Problems>();
		
		list = ProblemDao.getProblemsByname(keywords);
		/*for(int i = 0;i<list.size();i++){
			Problems pro = (Problems) list.get(i);
			System.out.println(pro.toString());
		}*/
		context.put("problems", list);
		return "search";
		
	}
	
	
}
