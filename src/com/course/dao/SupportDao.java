package com.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.CSupport;
import com.learning.entity.Comment;
import com.learning.entity.Course;
import com.learning.entity.CourseComment;
import com.learning.entity.Support;
import com.learning.entity.VideoComment;

public class SupportDao {
	
	public static int updatesupport(int coursecommentid,int userid){
		Support sup = ifsupported(coursecommentid,userid);
		
		int flag = sup.getIfsupport();
		
		Session session = HibernateSessionFactory.getSession();
		CourseComment cc = (CourseComment) session.get(CourseComment.class, coursecommentid);
		int supportnum = cc.getSupport();
		try {
			session.beginTransaction();
			
			if(flag == 0){//如果为0说明可以加1
				
				Query q = session.createQuery("update Support su set su.ifsupport = '1' where su.videocommentid=? and su.userid = ?");
				q.setInteger(0, coursecommentid);
				q.setInteger(1,userid);
				q.executeUpdate();
				
				
//				vc.setSupport(supportnum+1);
			    q = session.createQuery("update CourseComment cc set cc.support = ? where cc.id=?");
				q.setInteger(0, supportnum+1);
				q.setInteger(1, coursecommentid);
				q.executeUpdate();
				session.getTransaction().commit();
				return supportnum+1;
				
			}else if(flag == 1){//说明取消赞
				Query q = session.createQuery("update Support su set su.ifsupport = 0 where su.videocommentid=? and su.userid = ?");
				q.setInteger(0, coursecommentid);
				q.setInteger(1,userid);
				q.executeUpdate();
				
				q = session.createQuery("update CourseComment cc set cc.support = ? where cc.id=?");
				q.setInteger(0, supportnum-1);
				q.setInteger(1, coursecommentid);
				q.executeUpdate();
				session.getTransaction().commit();
				return supportnum-1;
			}
			
			
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return 0;

	}
	
	
	
	
	//0表示之前没有点过，1说明之前点过但是取消过，2表示已经点过
	public static Support ifsupported(int videocommentid,int userid){
		
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
	
			List list = new ArrayList<Support>();
	
			Query query = session.createQuery("from Support su where su.videocommentid = ? and userid = ?");
			query.setInteger(0,videocommentid);
			query.setInteger(1, userid);
			list = query.list();
			System.out.println("符合条件的记录有"+list.size());
			
			
			if(list.size()==0){
				Support sup = new Support();
				sup.setVideocommentid(videocommentid);
				sup.setUserid(userid);
				sup.setIfsupport(0);
				session.save(sup);
				session.getTransaction().commit();
				System.out.println("保存成功");
				return sup;
			}else{
				session.getTransaction().commit();
				Support sup = (Support) list.get(0);
				
				return sup;
			}
			
		
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return null;
	}
	
	
	
public static CSupport ifsupported2(int videocommentid,int userid){
		
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
	
			List list = new ArrayList<Support>();
	
			Query query = session.createQuery("from CSupport su where su.commentid = ? and userid = ?");
			query.setInteger(0,videocommentid);
			query.setInteger(1, userid);
			list = query.list();
			System.out.println("符合条件的记录有"+list.size());
			
			
			if(list.size()==0){
				CSupport sup = new CSupport();
				sup.setCommentid(videocommentid);
				sup.setUserid(userid);
				sup.setFlag(0);
				session.save(sup);
				session.getTransaction().commit();
				System.out.println("保存成功");
				return sup;
			}else{
				session.getTransaction().commit();
				CSupport sup = (CSupport) list.get(0);
				
				return sup;
			}
			
		
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return null;
	}
	
	
	
	
	public static int updatecsupport(int commentid,int userid){
		CSupport sup = ifsupported2(commentid,userid);
		
		int flag = sup.getFlag();
		
		Session session = HibernateSessionFactory.getSession();
		Comment c = (Comment) session.get(Comment.class, commentid);
		int supportnum = c.getSupport();
		try {
			session.beginTransaction();
			
			if(flag == 0){//如果为0说明可以加1
				
				Query q = session.createQuery("update CSupport su set su.flag = '1' where su.commentid=? and su.userid = ?");
				q.setInteger(0, commentid);
				q.setInteger(1,userid);
				q.executeUpdate();
				
				
//				vc.setSupport(supportnum+1);
			    q = session.createQuery("update Comment cc set cc.support = ? where cc.id=?");
				q.setInteger(0, supportnum+1);
				q.setInteger(1, commentid);
				q.executeUpdate();
				session.getTransaction().commit();
				return supportnum+1;
				
			}else if(flag == 1){//说明取消赞
				Query q = session.createQuery("update CSupport su set su.flag = 0 where su.commentid=? and su.userid = ?");
				q.setInteger(0, commentid);
				q.setInteger(1,userid);
				q.executeUpdate();
				
				q = session.createQuery("update Comment cc set cc.support = ? where cc.id=?");
				q.setInteger(0, supportnum-1);
				q.setInteger(1, commentid);
				q.executeUpdate();
				session.getTransaction().commit();
				return supportnum-1;
			}
			
			
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return 0;

	}
	
	
	
}
