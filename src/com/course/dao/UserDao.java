package com.course.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Course;

import com.user.entity.UserBean;

public class UserDao {
	
	public static void changeuser(int userid,String nickname,String depart,String position){
	
		
		Session session = HibernateSessionFactory.getSession();
		
		try {
			session.beginTransaction();
			
			String hql = "update UserBean ub set ub.user_nickname = ? , ub.user_department = ?,ub.user_position=? where ub.user_id = ?";
			Query q = session.createQuery(hql);
			q.setString(0,nickname);
			q.setString(1, depart);
			q.setString(2, position);
			q.setString(3,""+userid+"");
			q.executeUpdate();
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}

	
	}
	
	
	public static UserBean getuserByid(int userid){
		Session session = HibernateSessionFactory.getSession();
		List<Course> list ;
		try {
			session.beginTransaction();
			
			UserBean user = (UserBean) session.get(UserBean.class, userid);
			
			return user;
		
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return null;

	}
	

}
