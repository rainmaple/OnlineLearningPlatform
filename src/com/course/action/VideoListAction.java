package com.course.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CollectionDao;
import com.course.dao.CommentDao;
import com.course.dao.CourseDao;
import com.course.dao.CourseTeacherDao;
import com.course.dao.PageDao;
import com.course.dao.VideoDao;
import com.learning.entity.Course;
import com.learning.entity.CourseComment;
import com.learning.entity.CourseTeacher;
import com.learning.entity.DownloadFile;
import com.learning.entity.Page;
import com.learning.entity.Video;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VideoListAction extends ActionSupport {
	
	private List<CourseComment> commentlist;
	
	public List<CourseComment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<CourseComment> commentlist) {
		this.commentlist = commentlist;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		
		Course course = CourseDao.getCourse(courseid);
		context.put("course", course);
		
		CourseTeacher ct = CourseTeacherDao.getcourseteacherbyid(courseid);
		context.put("courseteacher", ct);
		
		List relevant = new ArrayList<Course>();
		relevant = CourseDao.getSomerelevantCourse(course.getCourseType());
		context.put("relevantcourse", relevant);
		
		System.out.println("courseid is"+courseid);
		
		List list = new ArrayList();
		list = VideoDao.showallVideobyChapters(courseid);
		//���½���Ϣ����video����
		context.put("video", list);
		request.setAttribute("video", list);
		
		//��ȡ����
		List commlist = new ArrayList<CourseComment>();
		Page page = new Page();
		page.setPagenow(1);
		page.setPagesize(2);
		
		int ifcoll = CollectionDao.getcollectflag(courseid, 666);
		context.put("ifcoll", ifcoll);
		
		page = PageDao.getCourseCommentPageinfo(page,courseid);
		commlist = CommentDao.getCommentBypage(page, courseid);
		request.setAttribute("pageinfo", page);
		request.setAttribute("courseid", courseid);
		context.put("comment", commlist);
		context.put("pageinfo",page);
		
		
		//�жϸÿγ��Ƿ��Ѿ�ѧϰ��,��û�з��ظÿγ̵ĵ�һ�µ�һ�ڵ���Ƶ
		//��ѧϰ���������ϴ�ѧϰ���ĵط�
		//ֱ�ӷ���Ҫ��ת��Ƶ�Ķ���
		HashMap hm =  (HashMap) CourseDao.returnLearnFlag(courseid, 666);
		
		if(hm!=null){
		Iterator iter = hm.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			
			System.out.println(key);
			System.out.println(val);
			request.setAttribute("videoid", val);
			context.put("videoid", val);
			request.setAttribute("flag", key);
			context.put("flag", key);
			Video video = VideoDao.getvideo((Integer)val);
			
			if(key == "��ʼѧϰ"){
				context.put("lastvideoname","����ӵ�һ�ڿ�ʼѧϰ");
			}else{
				context.put("lastvideoname", "���ϴ�ѧ��"+video.getVideoname());
			}
			
		}
		}
		
		
		String coursefile = CourseDao.getcoursefile(courseid);
		String filepath = "D:/java/.metadata/.me_tcat85/webapps/OnlineLearning/coursefile/"+coursefile;
		
		List filelist = CourseDao.getfilelist(filepath);
		request.setAttribute("filelist", filelist);
		context.put("filelist", filelist);
		
		

		
		
		return SUCCESS;
	}
	
	//����ҳ����������
	public String getCourseCommentBypage(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Page page = new Page();
		int pagenow=1;
		int pagecount;
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		
		page.setPagesize(2);
		System.out.println("�ж�"+request.getParameter("pagenow"));
		if(request.getParameter("pagenow")!=null){
		    pagenow = Integer.parseInt(request.getParameter("pagenow"));
			pagecount = Integer.parseInt(request.getParameter("pagecount"));
			System.out.println("���ݵ�pagecountΪ"+pagecount);
			page = PageDao.getCourseCommentPageinfo(page,courseid);
			page.setPagenow(pagenow);
		}else{
			page.setPagenow(1);
			page = PageDao.getpageinfo(page);
		}
		
		List list = new ArrayList<Course>();
		list =  CommentDao.getCommentBypage(page, courseid);
		request.setAttribute("pageinfo", page);
		context.put("pageinfo", page);
		this.commentlist = list;
		
		
		
		
		return "coursecomment";
	}
	
	public String  submitcomment(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		String commentcontent = request.getParameter("comment");
		
		CourseComment cc = new CourseComment();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String date = df.format(new Date());
		
		//�û���Ϣͨ��session��ȡ
		cc.setContent(commentcontent);
		cc.setCourseid(courseid);
		cc.setUserid(222);
		cc.setDate(date);
		cc.setUsername("С��");
		cc.setSupport(0);
		cc.setHeadImage("#");
		
		CommentDao.savecomment(cc);
		
		return "submitreturn";
	}
	
	
	public String PlayVideo(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//String videourl = request.getParameter("videourl");
		int courseid =Integer.parseInt(request.getParameter("courseid"));
		int videoid = Integer.parseInt(request.getParameter("videoid"));
		System.out.println("the videoid is"+videoid);
		
		List list = new ArrayList();
		if(request.getParameter("flag")!=null){
			int flag = Integer.parseInt(request.getParameter("flag"));
			if(flag==0){
				//666��ʾuserid
				CourseDao.addlearnum(courseid,666,videoid);
			}
		}else{
			CourseDao.addlearnum(courseid,666,videoid);
		}
		
		//System.out.println("the flag is"+flag);
		//list = (List) request.getAttribute("allvideo");
		
		
		
		Course course = CourseDao.getCourse(courseid);
		context.put("course", course);
		
		Video video = VideoDao.getvideo(videoid);
		context.put("video", video);
		
		list = VideoDao.showallVideobyChapters(courseid);
		//System.out.println("the list size is" +list.size());
		
//		for(int i=0;i<list.size();i++){
//			
//			List chapterlist = (List) list.get(i);
//			for(int j=0;j<list.size();j++){
//				System.out.println(chapterlist.get(j).toString());
//			}
//		}
		//System.out.println();
		
		
		context.put("videolist",list);
		
		//�����Ƿ�ѧϰ���ÿγ̣������׽���Ƶ�����ϴιۿ�����λ��
		/*if(flag=="��ʼѧϰ"){
			
			
			
		}else{
			
			
		}*/
		
		return "play";
	}
	

	
	
}
