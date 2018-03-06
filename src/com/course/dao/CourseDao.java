package com.course.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hankcs.hanlp.HanLP;
import com.learning.dao.HibernateSessionFactory;
import com.learning.entity.Course;
import com.learning.entity.DownloadFile;
import com.learning.entity.LearningTracks;
import com.learning.entity.Page;
import com.learning.entity.Video;
import com.opensymphony.xwork2.ActionContext;
import com.user.entity.UserBean;

public class CourseDao{
	
	public static List<Course> GetCourseList() {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		Map<String,Object> session2 = null;
		session2 = context.getSession();
		int userid = Integer.parseInt((String)session2.get("user_id"));
		
		
		
		Session session = HibernateSessionFactory.getSession();
		List<Course> list ;
		try {
			session.beginTransaction();
			
			UserBean ub = (UserBean) session.get(UserBean.class, userid);
			String department = ub.getUser_department();
			String position = ub.getUser_position();
			
			String hql ="from Course c where c.department like ? or c.department = null and c.position like ? or c.position = null";
			Query q = session.createQuery(hql);
			q.setString(0, "%"+department+"%");
			q.setString(1, "%"+position+"%");
			
		
//			Criteria criteria = session.createCriteria(Course.class);
//			criteria.addOrder(Order.asc("id"));
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
	
	
	public static Course getCourse(int id) {
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getSession();
		  
		try {
			session.beginTransaction();
//			Query q = session.createQuery("from Course where id=?");  
//	        q.setInteger(0, id);  
	      
			Course Course=(Course)session.get(Course.class, id);
			session.getTransaction().commit();
			return Course;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			//session.close();
		}
		return null;
	}
	
	public static List getCourseByname(Page page,String coursename){
		
		ActionContext context=ActionContext.getContext();
		Map<String,Object> session2 = null;
		session2 = context.getSession();
		int userid = (Integer) session2.get("user_id");
		
		
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			
			UserBean ub = (UserBean) session.get(UserBean.class, userid);
			String department = ub.getUser_department();
			String position = ub.getUser_position();
			
			List list=new ArrayList<Course>();
			String hql = "from Course as course where (course.coursename like :likename or course.coursedesc like :likedesc) and (course.department like :likedepartment or course.department = null) and (course.position like :likeposition or course.position = null)";

			Query query=session.createQuery(hql); 
			query.setString("likename","%"+coursename+"%");
			query.setString("likedesc","%"+coursename+"%");
			query.setString("likedepartment", "%"+department+"%");
			query.setString("likeposition", "%"+position+"%");
			
			int pagenow = page.getPagenow();
			int pagesize = page.getPagesize();
			int firstIndex = (pagenow-1)*pagesize;

			list = query.setFirstResult(firstIndex).setMaxResults(pagesize).list(); 
			
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
	
	public static Course  deleteCourse(int id) {
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getSession();
		
		try {
			session.beginTransaction();
			Course Course=(Course)session.get(Course.class, id);
			
			session.delete(Course);
			session.getTransaction().commit();
			return Course;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return null;
	}
	
	
	//根据所有课程分页
	public static List page(Page page) { 
		ActionContext context=ActionContext.getContext();
		Map<String,Object> session2 = null;
		session2 = context.getSession();
		int userid = (Integer) session2.get("user_id");
		
		
		
		Session session = HibernateSessionFactory.getSession();
		try 
		{ 
			List list=new ArrayList<Course>();
			session.beginTransaction();
//			String hql = "select count(*) from Course as user";
//			Query q0 = session.createQuery(hql);
//			Long allcount = ((Long)q0.uniqueResult()).longValue();
//			page.setAllcount(allcount);
			
			int pagenow = page.getPagenow();
			int pagesize = page.getPagesize();
//			System.out.println("总记录数是"+allcount);
			//String hql2 = " from Course"; //Course是类名 
			
			
			UserBean ub = (UserBean) session.get(UserBean.class, userid);
			String department = ub.getUser_department();
			String position = ub.getUser_position();
			
			String hql ="from Course c where c.department like ? or c.department = null and c.position like ? or c.position = null";
			Query q = session.createQuery(hql);
			q.setString(0, "%"+department+"%");
			q.setString(1, "%"+position+"%");
			
			
			//Query q = session.createQuery(hql2);
			int firstIndex = (pagenow-1)*pagesize;
//			int lastIndex = pagenow*pagesize-1;
//			System.out.println(firstIndex);
//			System.out.println(lastIndex);
			list = q.setFirstResult(firstIndex).setMaxResults(pagesize).list(); 
//			list = q.list();
			
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
	
	public static List getCorseByType(String type){
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			List list=new ArrayList<Course>();
			String hql="from Course as course where course.courseType =?";
			Query query=session.createQuery(hql); 
			query.setString(0, type);
			list = query.list();
			session.getTransaction().commit();
			return list;
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		return null;
	}
	
	//根据某一类型课程分页
	public static List pageDivBytype(List courselist,Page page){
		Session session = HibernateSessionFactory.getSession();
		try 
		{ 
			List list=new ArrayList<Course>();
			int pagenow = page.getPagenow();
			int pagesize = page.getPagesize();
			int firstIndex = (pagenow-1)*pagesize;
			int lastIndex = (pagenow*pagesize)-1;
			for(int i=firstIndex;i<=lastIndex;i++){
				list.add(courselist.get(i));
			}
			
		    return list;
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally{
			session.close();
		} 
		return null;
	}
	
	public static void  updateCourse(Course Course) {
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getSession();
		
		try {
			session.beginTransaction();
			session.update(Course);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		
	}
	
	
	public static void  saveCourse(Course Course) {
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getSession();
		
		try {
			session.beginTransaction();
			session.save(Course);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		
		} finally {
			session.close();
		}
		
		String coursename = Course.getCoursename();
		boolean bool = createDir(coursename);
		//在指定路径下创建该课程的文件夹
		
	}
	
	//每个课程创建一个文件夹用于存放视频
	public static boolean createDir(String destDirName) {
		File dir = new File("D:\\java\\.metadata\\.me_tcat85\\webapps\\OnlineLearning\\file\\"+destDirName);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("创建目录失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("创建目录成功！" + destDirName);
			return true;
		} else {
			System.out.println("创建目录失败！");
			return false;
		}
	}
	
	
	public static List getSomerelevantCourse(String coursetype){
		Session session = HibernateSessionFactory.getSession();
		try{
			List list = new ArrayList<Course>();
			session.beginTransaction();
			String hql = "from Course c where c.courseType like ? or c.coursedesc like ?";
			Query q = session.createQuery(hql);
			q.setString(0, "%"+coursetype+"%");
			q.setString(1, "%"+coursetype+"%");
			Random rand = new Random();
			int randomnum = rand.nextInt(5);
			q.setFirstResult(randomnum);
			q.setMaxResults(3);
			list = q.list();
			
			session.getTransaction().commit();
			return list;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	public static Map returnLearnFlag(int courseid,int userid){
		
		HashMap hm = new HashMap();
		Session session = HibernateSessionFactory.getSession();
		try{
			List list = new ArrayList<LearningTracks>();
			session.beginTransaction();
			String hql ="from LearningTracks lt where lt.userid =? and lt.courseid = ? order by lt.lastlearntime desc"; 
			Query q = session.createQuery(hql);
			q.setInteger(0,userid);
			q.setInteger(1, courseid);
			list = q.list();
			session.getTransaction().commit();
			
			if(list.size()==0){
				session.beginTransaction();
				hql = "select v.id from Video v where v.chapter = 1 and v.chapterde = 1 and v.belongto = ?";
				q = session.createQuery(hql);
				q.setInteger(0, courseid);
				int videoid = (Integer) q.uniqueResult();
				session.getTransaction().commit();
				
				hm.put("开始学习",videoid);
			}else{
				session.beginTransaction();
				
				LearningTracks lt = (LearningTracks) list.get(0);
				int chapter = lt.getLastchapter();
				int chapterde = lt.getLastchapterde();
				hql = "select v.id from Video v where v.chapter = ? and v.chapterde = ? and courseid = ?";
				q = session.createQuery(hql);
				q.setInteger(0, chapter);
				q.setInteger(1, chapterde);
				q.setInteger(2, courseid);
				int videoid =  (Integer) q.uniqueResult();
				session.getTransaction().commit();
				
				
				hm.put("继续学习", videoid);
				
			}
			return hm;
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return null;
		
		
	}
	
	
	public static List getrandomCourse(){
		
		Session session = HibernateSessionFactory.getSession();
		try{
			List list = new ArrayList<Course>();
			session.beginTransaction();
			String hql = "from Course c ";
			Query q = session.createQuery(hql);
		
			Random rand = new Random();
			int randomnum = rand.nextInt(20);
			q.setFirstResult(randomnum);
			q.setMaxResults(3);
			list = q.list();
			
			session.getTransaction().commit();
			return list;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
		
	}
	
	
	
	public static void addlearnum(int courseid,int userid,int videoid){
		ActionContext context=ActionContext.getContext();
		Map<String,Object> session2 = null;
		session2 = context.getSession();
		//int userid = (Integer) session2.get("user_id");
		
		Session session = HibernateSessionFactory.getSession();
		try{
			
			session.beginTransaction();
			String hql = "update Course course set course.learnnum = course.learnnum+1 where course.id = ?";
			Query q = session.createQuery(hql);
			q.setInteger(0, courseid);
			q.executeUpdate();
			
			
			session.getTransaction().commit();
			
			
			LearningTracks lt = new LearningTracks();
			Course course = CourseDao.getCourse(courseid);
			
			
			hql = "from Video v where v.id=?";
			q = session.createQuery(hql);
			q.setInteger(0, videoid);
			Video  video = (Video) q.uniqueResult();
			int chapter = video.getChapter();
			int chapterde = video.getChapterde();
			
			
			hql = "from LearningTracks lt where lt.userid = ? and lt.videoid = ?";
			q = session.createQuery(hql);
			q.setInteger(0, userid);
			q.setInteger(1, videoid);
		
			
			List list = q.list();
			//获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
			String date = df.format(new Date());
			if(list.size()==0){
			
				
				lt.setCourseid(courseid);
				lt.setCoursename(course.getCoursename());
				lt.setFinishpercent("0%");
				lt.setLastchapter(chapter);
				lt.setLastchapterde(chapterde);
				
				lt.setVideoid(videoid);
				
				lt.setLastlearntime(date);
				lt.setUserid(userid);
				lt.setUsername((String)session2.get("user_nickname"));
				
				LearningTracksDao.saveLt(lt);
			}else{
				
				hql = "update LearningTracks lt set lt.lastlearntime = ? where lt.userid = ? and lt.videoid = ?";
				q = session.createQuery(hql);
				q.setString(0, date);
				q.setInteger(1, userid);
				q.setInteger(2, videoid);
			
				
				q.executeUpdate();
				
			}
			
			
	
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
	}
	
	
	//根据用户id获取所有收藏的课程
	public static List getCollection(int userid){
		
		Session session = HibernateSessionFactory.getSession();
		try{
			List list = new ArrayList();
			session.beginTransaction();
			String hql = "select coll.courseid from Collection coll where coll.userid = ? and coll.flag=1";
			Query q = session.createQuery(hql);
			q.setInteger(0, userid);
			list = q.list();
			session.getTransaction().commit();
			List list2 = new ArrayList<Course>();
			for(int i=0;i<list.size();i++){
				Course  course = CourseDao.getCourse((Integer)list.get(i));
				list2.add(course);
			}
			
			
			return list2;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			//session.close();
		}
		return null;
		
	}
	
	
	//根据用户推荐适合用户的课程
	public static Set recommend(int userid){
		Session session1 = HibernateSessionFactory.getSession();
		try{
			List list = new ArrayList();
			session1.beginTransaction();
			
			
			String hql = "select coll.courseid from Collection coll where coll.userid = ? and coll.flag=1";
			Query q1 = session1.createQuery(hql);
			q1.setInteger(0, userid);
			list = q1.list();
			
			String text= "";
			
			for(int i=0;i<list.size();i++){
				Course  course = (Course)session1.get(Course.class, (Integer)list.get(i));
				text = text+course.getCoursename()+course.getCoursedesc();
			}
			//session1.getTransaction().commit();
			hql = "select cc.content from CourseComment cc where cc.userid = ?";
			q1 = session1.createQuery(hql);
			q1.setInteger(0, userid);
			List list2 = q1.list();
			for(int i=0;i<list2.size();i++){
				
				text = text+(String)list2.get(i);
			}
			//session1.getTransaction().commit();
			
			hql = "select c.commentmain from Comment c where c.userid = ?";
			q1 = session1.createQuery(hql);
			q1.setInteger(0, userid);
			List list3 = q1.list();
			for(int i=0;i<list3.size();i++){
				
				text = text+(String)list3.get(i);
			}
			session1.getTransaction().commit();
			
			
			
			System.out.println("keytext is"+text);
			List<String> keywordslist = HanLP.extractKeyword(text, 4);
			System.out.println(keywordslist);
			//List eachkeylist = new ArrayList<Course>();
			List recommend = new ArrayList<Course>();
			for(int j=0;j<keywordslist.size();j++){
				hql = "from Course c where c.coursename like ? and c.coursedesc like ?";
				q1 = session1.createQuery(hql);
				q1.setString(0, "%"+keywordslist.get(j)+"%");
				q1.setString(1, "%"+keywordslist.get(j)+"%");
				//eachkeylist = q.list();
				recommend.addAll(q1.list());
				//session1.getTransaction().commit();
			}
			Set<Course> realrecommend = new HashSet<Course>(); 
			realrecommend.addAll(recommend);
			
			//session1.getTransaction().commit();
			return realrecommend;
		}catch(Exception e){
			session1.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session1.close();
		}
		return null;
	}
	
	//
	public static List gettypebylist(Page page,String timelength,String type,String dificulty){
		
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
		
		Session session = HibernateSessionFactory.getSession();
		try 
		{ 
			List list=new ArrayList<Course>();
			session.beginTransaction();
			
			
			String hql = "from Course as course where course.timelength > ? and course.timelength < ? and course.dificulty = ? and course.courseType = ?";
			Query q = session.createQuery(hql);
			q.setString(0, min);
			q.setString(1, max);
			q.setString(2,dificulty);
			q.setString(3, type);
			
			
			
			
			
			q.setFirstResult((page.getPagenow()-1)*page.getPagesize());
			q.setMaxResults(page.getPagesize());
			list = q.list();
			
			
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
	
	
	
	public static String getcoursefile(int courseid){
		Session session = HibernateSessionFactory.getSession();
		try{
			session.getTransaction().begin();
			
			String hql = "select c.coursefile from Course c where c.id = ?";
			Query q = session.createQuery(hql);
			q.setInteger(0, courseid);
			
			String coursefile = (String)q.uniqueResult();
			
			session.getTransaction().commit();
			return coursefile;
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return "";
	}
	
	
	public static List getfilelist(String filepath){
		
		List filelist = new ArrayList<DownloadFile>();
		
		
		File file = new File(filepath);
		File[] files = file.listFiles();
		if(files!=null){
			for(File f:files){
				DownloadFile df = new DownloadFile();
				df.setFilename(f.getName());
				df.setFilepath(f.getPath().replace("\\", "/"));
				filelist.add(df);
			}
		}
		
		
		return filelist;
	}
	
	

}

