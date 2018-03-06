package com.course.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Course;
import com.learning.entity.Page;
import com.opensymphony.xwork2.ActionContext;
import com.user.entity.UserBean;

public class PageDao {
	
	//获取所有课程的信息
	public static Page getpageinfo(Page page){
		Session session = HibernateSessionFactory.getSession();
		  
		try {
			session.beginTransaction();
			String hql = "select count(*) from Course as course";
			Query q0 = session.createQuery(hql);
			int count = ((Long) q0.iterate().next()).intValue();
			page.setAllcount(count);
			session.getTransaction().commit();
			
			int pagesize = page.getPagesize();
			int pagecount;
			if(count%pagesize==0){
				pagecount = count/pagesize;
			}else{
				pagecount = count/pagesize + 1;
			}
			page.setPagecount(pagecount);
			System.out.println("pagecount为"+pagecount);
			return page;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
		
	        } finally {
	        	session.close();
	        }
		return null;
	
	}
	
	
	//获取某一类型课程的信息
	public static Page getPageOfType(Page page,List list){
		int count = list.size();
		page.setAllcount(count);
		int pagesize = page.getPagesize();
		int pagecount;
		if(count%pagesize==0){
			pagecount = count/pagesize;
		}else{
			pagecount = count/pagesize + 1;
		}
		page.setPagecount(pagecount);
		
		return page;
	}
	
	//评论的页面信息
	public static Page getpageinfo(String table){
		Session session = HibernateSessionFactory.getSession();
		Page page = new Page();
		try {
			session.beginTransaction();
			String hql = "select count(*) from ? as course";
			Query q0 = session.createQuery(hql);
			q0.setString(0, table);
			int count = ((Long) q0.iterate().next()).intValue();
			page.setAllcount(count);
			session.getTransaction().commit();
			page.setPagesize(10);
			
			int pagesize = page.getPagesize();
			int pagecount;
			if(count%pagesize==0){
				pagecount = count/pagesize;
			}else{
				pagecount = count/pagesize + 1;
			}
			page.setPagecount(pagecount);
			
			return page;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
		
	        } finally {
	        	session.close();
	        }
		return null;
	
	}
	
	public static Page getCourseCommentPageinfo(Page page,int courseid){
		
		Session session = HibernateSessionFactory.getSession();
		
		try{
			session.beginTransaction();
			String hql = "select count(*) from CourseComment as cc where cc.courseid = ?";
			Query q0 = session.createQuery(hql);
			q0.setInteger(0,courseid);
			int count = ((Long) q0.iterate().next()).intValue();
			page.setAllcount(count);
			session.getTransaction().commit();
			
			int pagesize = page.getPagesize();
			int pagecount;
			if(count%pagesize==0){
				pagecount = count/pagesize;
			}else{
				pagecount = count/pagesize + 1;
			}
			page.setPagecount(pagecount);
			System.out.println("pagecount为"+pagecount);
			return page;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
		
	}
	
	
	//获取所有课程的信息
	public static Page getpropageinfo(Page page){
		Session session = HibernateSessionFactory.getSession();
		  
		try {
			session.beginTransaction();
			String hql = "select count(*) from Problems as pro";
			Query q0 = session.createQuery(hql);
			int count = ((Long) q0.iterate().next()).intValue();
			page.setAllcount(count);
			session.getTransaction().commit();
			
			int pagesize = page.getPagesize();
			int pagecount;
			if(count%pagesize==0){
				pagecount = count/pagesize;
			}else{
				pagecount = count/pagesize + 1;
			}
			page.setPagecount(pagecount);
	
			return page;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
		
	        } finally {
	        	session.close();
	        }
		return null;
	
	}
	
	
	
	public static Page getsearchpage(Page page,String keywords){
		
		ActionContext context=ActionContext.getContext();
		Map<String,Object> session2 = null;
		session2 = context.getSession();
		int userid = (Integer) session2.get("user_id");
		
		Session session = HibernateSessionFactory.getSession();
		  
		try {
			session.beginTransaction();
			String hql = "select count(*) from Course as course where (course.coursename like :likename or course.coursedesc like :likedesc) and (course.department like :likedepartment or course.department = null) and (course.position like :likeposition or course.position = null)";
			Query q0 = session.createQuery(hql);
			
			UserBean ub = (UserBean) session.get(UserBean.class, userid);
			String department = ub.getUser_department();
			String position = ub.getUser_position();
			
			
			q0.setString("likename","%"+keywords+"%");
			q0.setString("likedesc","%"+keywords+"%");
			q0.setString("likedepartment", "%"+department+"%");
			q0.setString("likeposition", "%"+position+"%");
			
			int count = ((Long) q0.iterate().next()).intValue();
			page.setAllcount(count);
			session.getTransaction().commit();
			
			int pagesize = page.getPagesize();
			int pagecount;
			if(count%pagesize==0){
				pagecount = count/pagesize;
			}else{
				pagecount = count/pagesize + 1;
			}
			page.setPagecount(pagecount);
	
			return page;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
		
	        } finally {
	        	session.close();
	        }
		return null;
		
	}
	
	
	public static Page gettypepage(Page page,String timelength,String type,String dificulty){
		Session session = HibernateSessionFactory.getSession();
		//System.out.println("接收到的时间参数是"+timelength);
		String min = "";
		String max = "";
		if(timelength.equals("0-2min")){
			min = "0";
			max = "120";
			
		}else if(timelength.equals("2-4min")){
			min = "120";
			max = "240";
		}else if(timelength.equals("4-6min")){
			min = "240";
			max = "360";
		}else if(timelength.equals("6-8min")){
			min = "360";
			max = "480";
		}else if(timelength.equals("8-10min")){
			min = "480";
			max = "600";
		}else{
			min = "600";
			max = "10000000000";
		}
		
		//System.out.println("min + max = "+min+"+"+max);
		  
		try {
			session.beginTransaction();
//			String timehql = "course.timelength > ? and course.timelength < ?";
//			String difihql = "and course.dificulty = ?";
//			String typehql = "and course.courseType = ?";
//			String hql = "";
//			Query q = session.createQuery(hql);
//			if(timelength!=""&&dificulty!=""&&type!=""){
//				hql = "from Course as course where course.timelength > ? and course.timelength < ? and course.dificulty = ? and course.courseType = ?";
//				q = session.createQuery(hql);
//				q.setString(0, min);
//				q.setString(1, max);
//				q.setString(2,dificulty);
//				q.setString(3, type);
//			}
//			else if(timelength==""){
//				hql = "from Course as course where course.dificulty = ? and course.courseType = ?";
//				q = session.createQuery(hql);
//				q.setString(0, dificulty);
//				q.setString(1, type);
//			}
//			else if(dificulty==""){
//				hql = "from Course as course where course.timelength > ? and course.timelength < ? and course.courseType = ?";
//				q = session.createQuery(hql);
//				q.setString(0, min);
//				q.setString(1, max);
//				q.setString(2,type);
//				
//			}
//			else if(type==""){
//				hql = "from Course as course where course.timelength > ? and course.timelength < ? and course.dificulty = ?";
//				q = session.createQuery(hql);
//				q.setString(0, min);
//				q.setString(1, max);
//				q.setString(2,dificulty);
//			}
			
			String hql = "from Course as course where course.timelength > ? and course.timelength < ? and course.dificulty = ? and course.courseType = ?";
			Query q = session.createQuery(hql);
			q.setString(0, min);
			q.setString(1, max);
			q.setString(2,dificulty);
			q.setString(3, type);
			
			
			
//			q.setFirstResult(page.getPagenow());
//			q.setMaxResults(page.getPagesize());
			
			
			List list = new ArrayList<Course>();
			list = q.list();
			
			int count = list.size();
			
			//page.setCourselist(list);
			page.setAllcount(count);
			session.getTransaction().commit();
			
			int pagesize = page.getPagesize();
			int pagecount;
			if(count%pagesize==0){
				pagecount = count/pagesize;
			}else{
				pagecount = count/pagesize + 1;
			}
			page.setPagecount(pagecount);
	
			return page;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
		
	        } finally {
	        	session.close();
	        }
		return null;
	}
	
	
}

