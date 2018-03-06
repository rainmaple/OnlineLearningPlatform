package com.course.dao;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.data.util.DataPoint;
import com.data.util.RegressionLine;
import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Category;
import com.learning.entity.Course;
import com.learning.entity.CourseAnaly;
import com.learning.entity.LearningTracks;
import com.learning.entity.Video;

public class LearningTracksDao {
	
	public static List gethistoryByuserid(int userid){
		Session session = HibernateSessionFactory.getSession();
		List<LearningTracks> list ;
		try {
			session.beginTransaction();
		
			Criteria criteria = session.createCriteria(LearningTracks.class).add(Restrictions.eq("userid", userid));
			criteria.addOrder(Order.desc("lastlearntime"));
			
			list = (List<LearningTracks>) criteria.list();
			
			session.getTransaction().commit();
		
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return null;
	} 
	
	
	public static void saveLt(LearningTracks lt){
		Session session = HibernateSessionFactory.getSession();
		
		try {
			session.beginTransaction();
		
			
			session.save(lt);
			
			
			session.getTransaction().commit();
		
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			
		} finally {
			
		}
	
	}
	
	
	public static void addlt(int videoid,int userid){
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			
			
			
			String hql = "from Video v where v.id = ?";
			Query q = session.createQuery(hql);
			q.setInteger(0, videoid);
			
			Video video = (Video) q.uniqueResult();
			//session.getTransaction().commit();
			int courseid = video.getBelongto();
			Course course=(Course)session.get(Course.class, courseid);
			//session.getTransaction().commit();
			
			
			String coursename = course.getCoursename();
			int chapter = video.getChapter();
			int chapterde = video.getChapterde();
			
			hql = "from LearningTracks lt where lt.userid = ? and lt.courseid = ? and lt.lastchapter = ? and lt.lastchapterde = ?";
			q = session.createQuery(hql);
			q.setInteger(0, userid);
			q.setInteger(1, courseid);
			q.setInteger(2, chapter);
			q.setInteger(3, chapterde);
			
			List list = q.list();
			//session.getTransaction().commit();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
			String date = df.format(new Date());
			
			if(list.size()==0){
				LearningTracks lt = new LearningTracks();
				lt.setCourseid(courseid);
				lt.setCoursename(coursename);
				lt.setFinishpercent("100%");
				lt.setLastchapter(chapter);
				lt.setLastchapterde(chapterde);
				
				lt.setLastlearntime(date);
				lt.setUserid(userid);
				lt.setUsername("假装有这个名字");
				session.save(lt);
				//session.getTransaction().commit();
				
			}else{
				hql = "update LearningTracks lt set lt.finishpercent = '100%' where lt.userid = ? and lt.courseid = ? and lt.lastchapter = ? and lt.lastchapterde = ?";
				q = session.createQuery(hql);
				q.setInteger(0, userid);
				q.setInteger(1, courseid);
				q.setInteger(2, chapter);
				q.setInteger(3, chapterde);
				
				q.executeUpdate();
				//session.getTransaction().commit();
			}
			
			
			session.getTransaction().commit();
			
		}catch (Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
	
	
	
	public static void startaddlt(int videoid,int userid){
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			
			
			
			String hql = "from Video v where v.id = ?";
			Query q = session.createQuery(hql);
			q.setInteger(0, videoid);
			
			Video video = (Video) q.uniqueResult();
			//session.getTransaction().commit();
			int courseid = video.getBelongto();
			Course course=(Course)session.get(Course.class, courseid);
			//session.getTransaction().commit();
			
			
			String coursename = course.getCoursename();
			int chapter = video.getChapter();
			int chapterde = video.getChapterde();
			
			hql = "from LearningTracks lt where lt.userid = ? and lt.courseid = ? and lt.lastchapter = ? and lt.lastchapterde = ?";
			q = session.createQuery(hql);
			q.setInteger(0, userid);
			q.setInteger(1, courseid);
			q.setInteger(2, chapter);
			q.setInteger(3, chapterde);
			
			List list = q.list();
			//session.getTransaction().commit();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
			String date = df.format(new Date());
//			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
//			String date2 = df.format(new Date());	
			
			if(list.size()==0){
				LearningTracks lt = new LearningTracks();
				lt.setCourseid(courseid);
				lt.setCoursename(coursename);
				lt.setFinishpercent("0%");
				lt.setLastchapter(chapter);
				lt.setLastchapterde(chapterde);
				
				lt.setLastlearntime(date);
				lt.setUserid(userid);
				lt.setUsername("假装有这个名字");
				session.save(lt);
				//session.getTransaction().commit();
				
			}
				
//				LearningTracks lt = (LearningTracks) list.get(0);
//				String percent = lt.getFinishpercent();
//				if(percent=="0%")
//				
//				hql = "update LearningTracks lt set lt.finishpercent = '100%' where lt.userid = ? and lt.courseid = ? and lt.lastchapter = ? and lt.lastchapterde = ?";
//				q = session.createQuery(hql);
//				q.setInteger(0, userid);
//				q.setInteger(1, courseid);
//				q.setInteger(2, chapter);
//				q.setInteger(3, chapterde);
//				
//				q.executeUpdate();
				//session.getTransaction().commit();
		
			
			
			session.getTransaction().commit();
			
		}catch (Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
	
	
	
	public static void updatelt(int videoid,int time,int userid){
		 
		 Session session = HibernateSessionFactory.getSession();
		  
			try {
				session.beginTransaction();
				
				Video video=(Video)session.get(Video.class, videoid);
				int totaltime = (int) video.getVideotime();
				//计算得到完成进度
				float percentage = (float) ((double)time/(double)totaltime)*100;
				NumberFormat nFormat=NumberFormat.getNumberInstance(); 
			    nFormat.setMaximumFractionDigits(0);//设置小数点后面位数为
			    String percentage2 = nFormat.format(percentage);
			    
			    
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
				String date = df.format(new Date());
			    
				String hql= "update LearningTracks lt set lt.finishpercent = ? , lt.lastlearntime = ? where lt.videoid =? and lt.userid = ?";
				Query q = session.createQuery(hql);
				q.setString(0, percentage2);
				q.setString(1, date);
				q.setInteger(2, videoid);
				q.setInteger(3, userid);
				q.executeUpdate();
				//LearningTracks lt = (LearningTracks) q.uniqueResult();
				//List list = q.list();
				/*if(list.size()==0){
					
				}*/
				
				
				
				session.getTransaction().commit();
				
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
			
			} finally {
				session.close();
			}
			
		 
		 
	 }
	
	
	
	public static List showUserLt(int userid){
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
			String hql = "select courseid  from LearningTracks lt where lt.userid =? group by courseid";
			Query q = session.createQuery(hql);
			q.setInteger(0, userid);
			List chapterlist = q.list();
			
//			for(int i =0;i<chapterlist.size();i++){
//				//System.out.println("i+1="+(i+1));
//				hql = "from Video v where v.belongto = ? and  v.chapter = ?";
//				q = session.createQuery(hql);
//				q.setInteger(1, (i+1));
//				q.setInteger(0, courseid);
//				list = q.list();
//				listall.add(list);
//			}
			
			List percentlist = new ArrayList();
			List analylist = new ArrayList<CourseAnaly>();
			long allpercent = 0;
			int finishpercent = 0;
			int videoid;
			
			for(int i=0;i<chapterlist.size();i++){
				hql = "select lt.finishpercent from LearningTracks lt where lt.userid = ? and lt.courseid = ?";
				q = session.createQuery(hql);
				q.setInteger(0, userid);
				q.setInteger(1, (Integer)chapterlist.get(i));
				percentlist = q.list();
				
				for(int j =0;j<percentlist.size();j++){
					finishpercent = finishpercent + Integer.parseInt((String) percentlist.get(j));
				}
				
				hql = "select count(v.id) from Video v where v.belongto = ?";
				q = session.createQuery(hql);
				q.setInteger(0, (Integer)chapterlist.get(i));
				System.out.println(q.uniqueResult());
			    allpercent =((Long)q.uniqueResult())*100;
			    
			    Course course=(Course)session.get(Course.class, (Integer)chapterlist.get(i));
			    
			    hql = "from LearningTracks lt where lt.courseid = ? and lt.userid =? order by lt.lastlearntime";
			    q = session.createQuery(hql);
			    q.setInteger(0, (Integer)chapterlist.get(i));
			    q.setInteger(1, userid);
			    q.setFirstResult(0);
			    q.setMaxResults(1);
			    LearningTracks lt = (LearningTracks) q.uniqueResult();
			    
			    Video video=(Video)session.get(Video.class, lt.getVideoid());
			    
			    NumberFormat nFormat=NumberFormat.getNumberInstance(); 
			    nFormat.setMaximumFractionDigits(0);//设置小数点后面位数为
			    //System.out.println(nFormat.format(3.1415));
				float p = (float) ((double)finishpercent/(double)allpercent)*100;
				String s = nFormat.format(p);
			    
				CourseAnaly ca = new CourseAnaly();
				ca.setCoursename(course.getCoursename());
				ca.setCoursetype(course.getCourseType());
				ca.setFilepath(course.getCoursefile());
				ca.setFinishprecent(s);
				
				ca.setLastchapter(video.getVideoname());
				ca.setLastdate(lt.getLastlearntime());
				ca.setCourseid((Integer)chapterlist.get(i));
				ca.setVideoid(video.getId());
				analylist.add(ca);
			    
				finishpercent = 0;
			}
			
			
			session.getTransaction().commit();
		
			return analylist;
	
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	
	
	public static Map getTimeAna(int userid){
		
		
		Session session = HibernateSessionFactory.getSession();
		try{
			
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
//			String date = df.format(new Date());
			
			
			Map map = new HashMap();
			for(int i=0;i<5;i++ ){
				Date dNow = new Date();   //当前时间
				Date dBefore = new Date();
	
				Calendar calendar = Calendar.getInstance(); //得到日历
				calendar.setTime(dNow);//把当前时间赋给日历
				calendar.add(Calendar.DAY_OF_MONTH, -i);  //设置为前一天
				dBefore = calendar.getTime();   //得到前一天的时间
	
	
				SimpleDateFormat sdf=new SimpleDateFormat("MM-dd"); //设置时间格式
				String defaultStartDate = sdf.format(dBefore); //格式化前一天
				//String defaultEndDate = sdf.format(dNow); //格式化当前时间
				
				
				String hql = "from LearningTracks lt where lt.lastlearntime like ? and lt.userid = ?";
				Query q = session.createQuery(hql);
				q.setString(0, "%"+defaultStartDate+"%");
				q.setInteger(1, userid);
				
				List list  = new ArrayList<LearningTracks>();
				list = q.list();
				float time = 0;
			
				for(int j = 0;j<list.size();j++){
					LearningTracks lt = (LearningTracks) list.get(j);
					int videoid = lt.getVideoid();
					int percent = Integer.parseInt(lt.getFinishpercent());
					Video video = (Video) session.get(Video.class, videoid);
					
					
				    
					float p = (float) ((float) ((double)percent*(double)video.getVideotime())/(double)100);
					//System.out.println(nFormat.format(p));
					time = time + p;
					
					
				}
				NumberFormat nFormat=NumberFormat.getNumberInstance(); 
			    nFormat.setMaximumFractionDigits(0);//设置小数点后面位数为
				float min = (float) ((double)time/(double)60);
				String mins = nFormat.format(min);
				map.put(defaultStartDate, mins);
				
				
				
				
			}
			
			session.beginTransaction();
			
			
			return map;
			
		}catch(Exception e){
			
		}finally{
			session.close();
		}
		return null;
		
		
	}
	
	
	
	public static List getcate(int userid){
		Session session = HibernateSessionFactory.getSession();
		
		try{
			
			session.beginTransaction();
			Date dNow = new Date();   //当前时间
			Date dBefore = new Date();

			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -7);  //设置为前一天
			dBefore = calendar.getTime();   //得到前一天的时间


			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
			String defaultStartDate = sdf.format(dBefore); //格式化前一周
			String defaultEndDate = sdf.format(dNow); //格式化当前时间
			
			
			//获取最近一周的总进度
			String hql0 = "select sum(lt.finishpercent) from LearningTracks lt where lt.lastlearntime < ? and lt.lastlearntime > ? and lt.userid = ?";
			Query q = session.createQuery(hql0);
			q.setString(0, defaultEndDate);
			q.setString(1, defaultStartDate);
			q.setInteger(2, userid);
			int allpercent = Integer.parseInt((String)q.uniqueResult()); 
			//System.out.println("the aa is"+allpercent);
			
			String hql = "select lt.courseid from LearningTracks lt where lt.lastlearntime < ? and lt.lastlearntime > ? and lt.userid = ? group by lt.courseid";
			q = session.createQuery(hql);
			q.setString(0, defaultEndDate);
			q.setString(1, defaultStartDate);
			q.setInteger(2, userid);
			
			List list = new ArrayList();
			list = q.list();
			
			List percentlist = new ArrayList();
			List analylist = new ArrayList<CourseAnaly>();
			
			int finishpercent = 0;
			int videoid;
			String type="";
			int temp = 0;
			List catalist = new ArrayList<Category>();
			
			
			
			for(int i=0;i<list.size();i++){
				//System.out.println("课程的id分别是"+(Integer)list.get(i));
				hql = "select sum(lt.finishpercent) from LearningTracks lt where lt.userid = ? and lt.courseid = ? and lt.lastlearntime < ? and lt.lastlearntime > ?";
				q = session.createQuery(hql);
				q.setInteger(0, userid);
				q.setInteger(1, (Integer)list.get(i));
				q.setString(2, defaultEndDate);
				q.setString(3, defaultStartDate);
				percentlist = q.list();
				finishpercent = Integer.parseInt((String)q.uniqueResult()); 
				
				//System.out.println("每个课程的完成情况"+finishpercent);
				Course course = (Course) session.get(Course.class,(Integer)list.get(i));
				
				
//				for(int j =0;j<percentlist.size();j++){
//					finishpercent = finishpercent + Integer.parseInt((String) percentlist.get(j));
//				}
				
				
				if(!type.equals(course.getCourseType())){
					Category cate = new Category();
					//temp = finishpercent;
					NumberFormat nFormat=NumberFormat.getNumberInstance(); 
				    nFormat.setMaximumFractionDigits(0);//设置小数点后面位数为
					float cataper = (float) ((double)temp/(double)allpercent)*100;
					String catapers = nFormat.format(cataper);
					//System.out.println("百分比是"+catapers);
					cate.setType(type);
					cate.setPercent(catapers);
					catalist.add(cate);
					type = course.getCourseType();
					
					temp = finishpercent;
			
					//temp = 0;
				
					
					
				}else{
					temp = temp + finishpercent;
					
				}
				
				//System.out.println("中间变量temp的值为"+temp);
				finishpercent = 0;
				if(catalist.size()==5){
					break;
				}
				
			}
			Category cate = new Category();
			NumberFormat nFormat=NumberFormat.getNumberInstance(); 
		    nFormat.setMaximumFractionDigits(0);//设置小数点后面位数为
			float cataper = (float) ((double)temp/(double)allpercent)*100;
			String catapers = nFormat.format(cataper);
			//System.out.println("百分比是"+catapers);
//			cate.setType(type);
//			cate.setPercent(catapers);
			int flag = 0;
			for(int k =0 ;k<catalist.size();k++){
				String temptype = ((Category)catalist.get(k)).getType();
				if(type.equals(temptype)){
					int per =Integer.parseInt(((Category)catalist.get(k)).getPercent());
					int finallyper = Integer.parseInt(catapers)+ per;
					((Category)catalist.get(k)).setPercent(""+finallyper);
					flag = 1;
				}
			}
			if(flag == 0){
				cate.setType(type);
				cate.setPercent(catapers);
				catalist.add(cate);
			}
			
			int left = 0;
			int ready = 0;
			for(int k =0 ;k<catalist.size();k++){
				ready =Integer.parseInt(((Category)catalist.get(k)).getPercent())+ready;
				
			}
			left = 100 - ready;
			Category catef = new Category();
			catef.setType("其他");
			catef.setPercent(""+left);
			
			session.getTransaction().commit();
			return catalist;
			
			
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		
		return null;
	}

	
	public static void getNextTime(int userid){
		
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			
			RegressionLine finallineh = new RegressionLine();
			RegressionLine finallines = new RegressionLine();
			int i = 1;
			for(i=0;i<5;i++){
				Date dNow = new Date();   //当前时间
				Date dBefore = new Date();
	
				Calendar calendar = Calendar.getInstance(); //得到日历
				calendar.setTime(dNow);//把当前时间赋给日历
				calendar.add(Calendar.DAY_OF_MONTH, -i);  //设置为前一天
				dBefore = calendar.getTime();   //得到前一天的时间
	
	
				SimpleDateFormat sdf=new SimpleDateFormat("MM-dd"); //设置时间格式
				String defaultStartDate = sdf.format(dBefore); //格式化前一天
				//String defaultEndDate = sdf.format(dNow); //格式化当前时间
				
				
				String hql = "select lt.lastlearntime from LearningTracks lt where lt.lastlearntime like ? and lt.userid = ?";
				Query q = session.createQuery(hql);
				q.setString(0, "%"+defaultStartDate+"%");
				q.setInteger(1, userid);
				
				List list  = new ArrayList();
				list = q.list();
				int htime;
				int stime;
				RegressionLine line = new RegressionLine();
				RegressionLine line2 = new RegressionLine();
				
				
				if(list.size()!=0){
					
					
					int j = 0;
					for(j = 0;j<list.size();j++){
						//LearningTracks lt = (LearningTracks) list.get(j);
						//int videoid = lt.getVideoid();
						//int percent = Integer.parseInt(lt.getFinishpercent());
						//Video video = (Video) session.get(Video.class, videoid);
						htime = Integer.parseInt(((String)list.get(j)).substring(11, 13));
						stime = Integer.parseInt(((String)list.get(j)).substring(14, 16));
				
						
						line.addDataPoint(new DataPoint((j), htime));
						line2.addDataPoint(new DataPoint((j),stime));
						
					    
						//float p = (float) ((float) ((double)percent*(double)video.getVideotime())/(double)100);
						//System.out.println(nFormat.format(p));
						//time = time + p;
					}
					int hdata = (int) (line.getA1()*(j)+line.getA0());
					int sdata = (int) (line2.getA1()*(j)+line2.getA0());
					System.out.println(defaultStartDate+hdata+":"+sdata);
					
					finallineh.addDataPoint(new DataPoint(i, hdata));
					finallines.addDataPoint(new DataPoint(i, sdata));
					
					
					
				}else{
					finallineh.addDataPoint(new DataPoint(i, 0));
					finallines.addDataPoint(new DataPoint(i, 0));
				}
				
				
				
			
			}
			
			
			int finalhour = (int) (finallineh.getA1()*(i-1)+finallineh.getA0());
			int finalseco = (int) (finallines.getA1()*(i-1)+finallines.getA0());
			//System.out.println("系数是"+finalhour);
			System.out.println("推测的hour是"+finalhour);
			System.out.println("推测的分钟是"+finalseco);
			
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
	}
	
	
	
}
