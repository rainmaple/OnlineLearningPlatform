package com.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Comment;
import com.learning.entity.Course;
import com.learning.entity.CourseComment;
import com.learning.entity.Page;

public class CommentDao {
	
	public static List getCommentByProblemid(int problemid){
		Session session = HibernateSessionFactory.getSession();
		List<Comment> list ;
		try {
			session.beginTransaction();
		
			Criteria criteria = session.createCriteria(Comment.class).add(Restrictions.eq("problemid", problemid));
			
			list = (List<Comment>) criteria.list();
			session.getTransaction().commit();
		
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}
	
	public static void attachcommenttoProblem(Comment comment){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(comment);

			String commentcontent = comment.getCommentmain();
			int problemid = comment.getProblemid();
			Query query = session.createQuery("update Problems p set p.firstcomment = ? where id = ?");
			query.setString(0, commentcontent);
			query.setInteger(1, problemid);
			query.executeUpdate();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
	}
	
	
	public static void savecomment(CourseComment courseComment){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(courseComment);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		
		}finally{
			session.close();
		}
	}
	
	public static List getCourseCommentBycourseid(int courseid){
		List list = new ArrayList<CourseComment>();
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
		
			Criteria criteria = session.createCriteria(CourseComment.class).add(Restrictions.eq("courseid", courseid));
			criteria.addOrder(Order.desc("date"));
			
			list = criteria.list();
			session.getTransaction().commit();
		
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		
	}
	
	
	//分页
	public static List getCommentBypage(Page page,int courseid) { 
		Session session = HibernateSessionFactory.getSession();
		try 
		{ 
			List list=new ArrayList<CourseComment>();
			session.beginTransaction();

			int pagenow = page.getPagenow();
			int pagesize = page.getPagesize();
			String hql2 = " from CourseComment cc where cc.courseid = ? order by cc.date desc"; //Course是类名 
			
			
			Query q = session.createQuery(hql2);
			q.setInteger(0, courseid);
			
			int firstIndex = (pagenow-1)*pagesize;

			list = q.setFirstResult(firstIndex).setMaxResults(pagesize).list(); 

			
		    session.getTransaction().commit();
		    
		    return list;
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		}finally{
			session.close();
		} 
		return null;
	 }
	
	
	
	
}
