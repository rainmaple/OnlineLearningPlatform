package com.course.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Course;
import com.learning.entity.CourseTeacher;

public class CourseTeacherDao {
	
	
	public static CourseTeacher getcourseteacherbyid(int courseid){
		Session session = HibernateSessionFactory.getSession();
		  
		try {
			session.beginTransaction();
			Query q = session.createQuery("from CourseTeacher ct where ct.courseid=?");  
	        q.setInteger(0, courseid);  
	      
			CourseTeacher ct = (CourseTeacher) q.uniqueResult();
			session.getTransaction().commit();
			return ct;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return null;
	}

}
