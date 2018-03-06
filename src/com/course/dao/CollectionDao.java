package com.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Collection;
import com.learning.entity.CourseComment;
import com.learning.entity.Support;

public class CollectionDao {
	
	public static int updatecollect(int courseid,int userid){
		Collection coll = ifcollected(courseid,userid);
		
		int flag = coll.getFlag();
		
		Session session = HibernateSessionFactory.getSession();
		//CourseComment cc = (CourseComment) session.get(CourseComment.class, courseid);
		//int supportnum = cc.getSupport();
		try {
			session.beginTransaction();
			
			if(flag == 0){//���Ϊ0˵�����Լ�1
				
				Query q = session.createQuery("update Collection cn set cn.flag = 1 where cn.courseid=? and cn.userid = ?");
				q.setInteger(0, courseid);
				q.setInteger(1,userid);
				q.executeUpdate();
				

				session.getTransaction().commit();
				return 1;
				
			}else if(flag == 1){//˵��ȡ����
				Query q = session.createQuery("update Collection cn set cn.flag = 0 where cn.courseid=? and cn.userid = ?");
				q.setInteger(0, courseid);
				q.setInteger(1,userid);
				q.executeUpdate();
				
				
				session.getTransaction().commit();
				return 0;
			}
			
			
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return 0;

	}
	
	
	
	
	//0��ʾ֮ǰû�е����1˵��֮ǰ�������ȡ������2��ʾ�Ѿ����
	public static Collection ifcollected(int courseid,int userid){
		
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
	
			List list = new ArrayList<Collection>();
	
			Query query = session.createQuery("from Collection cn where cn.courseid = ? and cn.userid = ?");
			query.setInteger(0,courseid);
			query.setInteger(1, userid);
			list = query.list();
			System.out.println("���������ļ�¼��"+list.size());
			
			
			if(list.size()==0){
				Collection coll = new Collection();
				coll.setCourseid(courseid);
				coll.setUserid(userid);
				coll.setFlag(0);
				session.save(coll);
				session.getTransaction().commit();
				System.out.println("����ɹ�");
				return coll;
			}else{
				session.getTransaction().commit();
				Collection coll = (Collection) list.get(0);
				
				return coll;
			}
			
		
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return null;
	}
	
	
	public static int getcollectflag(int courseid,int userid){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
	
			List list = new ArrayList<Collection>();
	
			Query query = session.createQuery("from Collection cn where cn.courseid = ? and cn.userid = ?");
			query.setInteger(0,courseid);
			query.setInteger(1, userid);
			list = query.list();
			
			
			
			if(list.size()==0){
				
				session.getTransaction().commit();
				
				return 0;
			}else{
				session.getTransaction().commit();
				Collection coll = (Collection) list.get(0);
				int flag = coll.getFlag();
				
				return flag;
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
