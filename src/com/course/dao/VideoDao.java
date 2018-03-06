package com.course.dao;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Course;
import com.learning.entity.LearningTracks;
import com.learning.entity.Problems;
import com.learning.entity.Video;

public class VideoDao {
	
	public static List showallVideo(int courseid){
		Session session = HibernateSessionFactory.getSession();
		List<Video> list ;
		try {
			session.beginTransaction();
		
			Criteria criteria = session.createCriteria(Video.class).add(Restrictions.eq("belongto", courseid));
			
			list = (List<Video>) criteria.list();
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
	
	public static List showallVideobyChapters(int courseid){
		Session session = HibernateSessionFactory.getSession();
		List<Video> list ;
		List listall = new ArrayList() ;
		try {
			session.beginTransaction();
		
//			Criteria criteria = session.createCriteria(Video.class).add(Restrictions.eq("belongto", courseid));
//			criteria.setProjection(Projections.groupProperty("chapter"));
//	
//			list = criteria.list();
//			
//			Video chapternum;
//			for(int i =0;i<list.size();i++){
//				chapternum =   list.get(i);
//				criteria = session.createCriteria(Video.class).add(Restrictions.eq("chapter", chapternum)).add(Restrictions.eq("belongto", courseid));
//				List list1 = criteria.list();
//				listall.add((Video) list1);
//			}
			String hql = "select count(chapter) from Video v where v.belongto =? group by chapter ";
			Query q = session.createQuery(hql);
			q.setInteger(0, courseid);
			List chapterlist = q.list();
			
			for(int i =0;i<chapterlist.size();i++){
				//System.out.println("i+1="+(i+1));
				hql = "from Video v where v.belongto = ? and  v.chapter = ?";
				q = session.createQuery(hql);
				q.setInteger(1, (i+1));
				q.setInteger(0, courseid);
				list = q.list();
				listall.add(list);
			}
			
			session.getTransaction().commit();
		
			return listall;
	
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public static boolean saveVideo(Video video){
		boolean bool = false;
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(video);
			session.getTransaction().commit();
			bool = true;
			return bool;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return bool;
		
	}
	

	//获取某个课程视频的总时长
	 public static void getallvideotime(String coursevideopth){
		  File source = new File(coursevideopth);
		  Encoder encoder = new Encoder();
		  File[] file = source.listFiles();
		  long sum =0;
		  for (File file2 : file) {
              try {
                 MultimediaInfo m = encoder.getInfo(file2);
                 long ls = m.getDuration()/1000;//ls是获取到的秒数
                 sum += ls;
              } catch (Exception e) {
                 e.printStackTrace();
	          }
		   }
		   double sum1 = (double)sum;
		   double sum2 =sum1/3600;// 转换成为了小时
		   
		   int min = (int) (sum1/60);
		   int sec = (int) (sum1%60);
		   System.out.println("该视频时长为"+min+"分钟"+sec+"秒");
		   
		   System.out.println(sum1);
		   System.out.println(sum2);
	}
	 
	 //获得某个视频的总时长
	 public static double getvideotime(String filepath){
		 File file = new File(filepath);
		 Encoder encoder = new Encoder();
		 long sum = 0;
		 try {
              MultimediaInfo m = encoder.getInfo(file);
              long ls = m.getDuration()/1000;//ls是获取到的秒数
              sum += ls;
           } catch (Exception e) {
              e.printStackTrace();
	      }
		 double sum1 = (double)sum;
		 System.out.println("时长为"+sum1);
		 return sum1;
		 
	 }
	
	 
	 //上传视频
	 public static void uploadvideo(){
		 
	 }
	 
	 
	/* public static Video getfirst(int courseid){
		 
	 }*/

	 public static Video getvideo(int id) {
			// TODO Auto-generated method stub
			
			Session session = HibernateSessionFactory.getSession();
			  
			try {
				session.beginTransaction();
				
				Video video=(Video)session.get(Video.class, id);
				session.getTransaction().commit();
				return video;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
			
			} finally {
				session.close();
			}
			return null;
		}
	 	
	 
	 
	 
	
}
