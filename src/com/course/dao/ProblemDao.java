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
import com.learning.entity.Problems;

public class ProblemDao {

	public static List showallProblembycourseid(Page page,int courseid){
		Session session = HibernateSessionFactory.getSession();
		List<Problems> list ;
		try {
			session.beginTransaction();
		
			Criteria criteria = session.createCriteria(Problems.class).add(Restrictions.eq("fromid", courseid));
			int pagenow = page.getPagenow();
			int pagesize = page.getPagesize();
			int firstIndex = (pagenow-1)*pagesize;
			criteria.addOrder(Order.desc("submit_time"));
			criteria.setFirstResult(firstIndex);
			criteria.setMaxResults(pagesize);
			
			list = (List<Problems>) criteria.list();
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
	
	public static void attachproblemtoCourse(Problems problem){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(problem);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
	}
	
	//获得最新的几个问题
	public static List getsomeproblemsbypage(){
		Session session = HibernateSessionFactory.getSession();
		
		List<Problems> list ;
		try {
			session.beginTransaction();
			Query q=session.createQuery("from Problems order by id desc");
			list = q.setFirstResult(0).setMaxResults(10).list();
			list = q.list();
			
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
	
	
	//根据所有问题分页
	public static List page(Page page) { 
		Session session = HibernateSessionFactory.getSession();
		try 
		{ 
			List list=new ArrayList<Problems>();
			session.beginTransaction();
			int pagenow = page.getPagenow();
			int pagesize = page.getPagesize();
//			System.out.println("总记录数是"+allcount);
			String hql2 = " from Problems p order by p.submit_time desc"; 
			Query q = session.createQuery(hql2);
			int firstIndex = (pagenow-1)*pagesize;

			list = q.setFirstResult(firstIndex).setMaxResults(pagesize).list(); //取出8条 

			
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
	
	
	public static List getProblemsByname(String keywords){
			
			Session session = HibernateSessionFactory.getSession();
			try{
				session.beginTransaction();
				List list=new ArrayList<Problems>();
				
				String hql="from Problems as p where p.title like :name or p.content like :con";
				Query query = session.createQuery(hql);

			    query.setString("name", "%" + keywords + "%");
			    query.setString("con", "%" + keywords + "%");
			    
				list = query.list();
				
				session.getTransaction().commit();
				
	//			Iterator it = list.iterator();
	//			while(it.hasNext()) {
	//			  System.out.println(it.next());
	//			}
	//			System.out.println(list);
				return list;
			}catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
			
			} finally {
				session.close();
			}
			return null;
			
		}
	
	public static void addviewnum(int problemid){
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			
			Query q = session.createQuery("update Problems p set p.viewnum = p.viewnum+1 where p.id = ?");
			q.setInteger(0, problemid);
			q.executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
	
	
	public static Problems getProByid(int problemid){
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			Problems pro=(Problems)session.get(Problems.class, problemid);
			
			return pro;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	
	public static int getCommcount(int problemid){
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			String hql= "select count(*) from Comment c where c.problemid = ?";
			Query q = session.createQuery(hql);
			q.setInteger(0, problemid);
			int count = ((Long) q.iterate().next()).intValue();
			//int count = (Integer) q.uniqueResult();
			
			return count;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return 0;
	}
	
	public static List getrelevantPro(int problemid){
		Session session = HibernateSessionFactory.getSession();
		
		try{
			session.beginTransaction();
			List list = new ArrayList<Problems>();
			
			Problems pro = (Problems)session.get(Problems.class, problemid);
			String type = pro.getType();
			String[] typelist = type.split(",");
			
			for(int i=0;i<typelist.length;i++){
				String hql = "from Problems p where p.type like ?";
				Query q = session.createQuery(hql);
				q.setString(0, "%"+typelist[i]+"%");
				List somelist = q.list();
				list.addAll(somelist);
			}
			
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return null;
		
	}
	
	
}
