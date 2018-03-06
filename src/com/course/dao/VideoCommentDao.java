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
import com.learning.entity.Page;
import com.learning.entity.VideoComment;

public class VideoCommentDao {
	
	public static List getCommentByVideoid(int videoid){
		Session session = HibernateSessionFactory.getSession();
		List<VideoComment> list ;
		try {
			session.beginTransaction();
		
			Criteria criteria = session.createCriteria(VideoComment.class).add(Restrictions.eq("videoid", videoid));
			criteria.addOrder(Order.desc("submittime"));
			list = (List<VideoComment>) criteria.list();
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
	
	public static void attachcommenttoVideo(VideoComment comment){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(comment);

			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
	}
	
	
	public static List page(Page page,int videoid) { 
		Session session = HibernateSessionFactory.getSession();
		try 
		{ 
			List list=new ArrayList<VideoComment>();
			session.beginTransaction();
			
			int pagenow = page.getPagenow();
			int pagesize = page.getPagesize();

			String hql2 = "from VideoComment vc where vc.videoid = ? order by vc.submittime desc"; 
			Query q = session.createQuery(hql2);
			q.setInteger(0, videoid);
			int firstIndex = (pagenow-1)*pagesize;

			list = q.setFirstResult(firstIndex).setMaxResults(pagesize).list(); //È¡³ö8Ìõ 

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


