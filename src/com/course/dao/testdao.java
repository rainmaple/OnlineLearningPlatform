package com.course.dao;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import com.hankcs.hanlp.HanLP;
import com.learning.entity.Category;
import com.learning.entity.Comment;
import com.learning.entity.Course;
import com.learning.entity.CourseComment;
import com.learning.entity.CourseTeacher;
import com.learning.entity.LearningTracks;
import com.learning.entity.Page;
import com.learning.entity.Problems;
import com.learning.entity.Video;
import com.learning.entity.VideoComment;
import com.user.util.Base64Util;
import com.user.util.GetUTCTimeUtil;
import com.user.util.HMACSHA1;
import com.user.util.MapKeyComparator;

public class testdao {
    
	@Test
	public void tests(){
		List list=new ArrayList<Course>();
		java.util.Scanner input = new java.util.Scanner(System.in);
//		String name = input.next();
//		System.out.println("输出你刚才输入的名字："+name);
//	    list = CourseDao.getCourseByname(name);
	    
	    
//		list = CourseDao.page(1,3);
//		Course course = CourseDao.getCourse(2);
//		System.out.println(course.getCoursename());
//		list = CourseDao.GetCourseList();
		System.out.println(list.size());
	    for(int i=0;i<list.size();i++){
	    	Course course = (Course)list.get(i);
	    	System.out.println(course.getCoursename());
	    } 
	}
	
	
	@Test
	public void testsave(){
		Course course = new Course();
		course.setCourseType("it");
		course.setCoursename("scrapy");
		course.setLearnnum(100);
		course.setTimelength("20h");
		course.setCoursedesc("你值得拥有");;
		CourseDao.saveCourse(course);
		
	} 
	
	@Test
	public void testtype(){
		List list=new ArrayList<Course>();
		list = CourseDao.getCorseByType("edu");
		for(int i=0;i<list.size();i++){
	    	Course course = (Course)list.get(i);
	    	System.out.println(course.getCoursename());
	    }
		System.out.println("----------------");
		List list2=new ArrayList<Course>();
////		list2 = CourseDao.pageDivBytype(list, 1, 3);
//		for(int i=0;i<list2.size();i++){
//	    	Course course = (Course)list2.get(i);
//	    	System.out.println(course.getCoursename());
//	    }
//		List list3=new ArrayList<Course>();
//		list3 = CourseDao.pageDivBytype(list, 2, 3);
//		for(int i=0;i<list3.size();i++){
//	    	Course course = (Course)list3.get(i);
//	    	System.out.println(course.getCoursename());
//	    }
		
	}
	
	@Test
	public void testpagedao(){
		Page page = new Page();
		page.setPagenow(1);
		page.setPagesize(5);
		System.out.println(page.getPagesize());
		
		page = PageDao.getpageinfo(page);
		System.out.println(page.toString());
//		List list=new ArrayList<Course>();
//		list = CourseDao.page(page);
		for(int i=1;i<=page.getPagecount();i++){
			page.setPagenow(i);
			List list=new ArrayList<Course>();
			list = CourseDao.page(page);
			for(int j=0;j<list.size();j++){
				Course course = (Course)list.get(j);
				System.out.println(course.getCoursename());
			}
	    }
	}
	@Test
	public void testcommentbyid(){
		List list = new ArrayList<Comment>();
		list = CommentDao.getCommentByProblemid(2);
		for(int i = 0 ; i < list.size() ; i++){
			Comment comment = (Comment)list.get(i);
			System.out.println(comment.toString());
		}
	}
	@Test
	public void testaddcomment(){
		Comment comment = new Comment();
		comment.setCommentmain("interesting");
		comment.setProblemid(123);
		comment.setResponsetime("2017/7/12/15:27");
		comment.setUserid(111);
		comment.setUsername("金小布");
		CommentDao.attachcommenttoProblem(comment);
	}
	
//	@Test
//	public void testproblembyid(){
//		List list = new ArrayList<Problems>();
//		list = ProblemDao.showallProblembycourseid(111);
//		for(int i = 0 ; i < list.size() ; i++){
//			Problems pro = (Problems)list.get(i);
//			System.out.println(pro.toString());
//		}
//	}
	
	@Test
	public void testvideobyid(){
		List list = new ArrayList<Video>();
		list = VideoDao.showallVideo(111);
		for(int i = 0 ; i < list.size() ; i++){
			Video pro = (Video)list.get(i);
			System.out.println(pro.toString());
		}
	}
	
	@Test
	public void testinsertcomment(){
		Comment com = new Comment();
//		com.setId(1);
		com.setResponsetime("123");
	    com.setCommentmain("你好啊");
	    com.setProblemid(123);
	    com.setUserid(111);
	    com.setUsername("金哥");
	    
	    CommentDao.attachcommenttoProblem(com);
	}
	
	@Test
	public void testtracks(){
		List list = new ArrayList<LearningTracks>();
		list = LearningTracksDao.gethistoryByuserid(123);
		for(int i = 0 ; i < list.size() ; i++){
			LearningTracks tracks = (LearningTracks)list.get(i);
			System.out.println(tracks.toString());
		}
	}
	
	@Test
	public void testproblemsearch(){
		List list = new ArrayList<Problems>();
		list = ProblemDao.getProblemsByname("结束");
		for(int i = 0 ; i < list.size() ; i++){
			Problems tracks = (Problems)list.get(i);
			System.out.println(tracks.toString());
		}
	}
	
	@Test
	public void testproblh(){
//		VideoDao.getvideotime();
//		VideoDao.getvideotime("H:\\视频测试文件\\配音版4.mp4");
		
//		CourseDao.createDir("H:\\测试文件哈哈\\");
	}
	
	@Test
	public void testvideocomment(){
//		List list = new ArrayList<VideoComment>();
//		list = VideoCommentDao.getCommentByVideoid(110);
//		for(int i = 0 ; i < list.size() ; i++){
//			VideoComment tracks = (VideoComment)list.get(i);
//			System.out.println(tracks.toString());
//		}
		
		VideoComment vcomment = new VideoComment();
		vcomment.setUserid(124);
		vcomment.setUsername("小红");
		vcomment.setVideoid(110);
		vcomment.setSubmittime("2017/7/15/16:04");
		vcomment.setSupport(0);
		VideoCommentDao.attachcommenttoVideo(vcomment);
		
	}
	
	@Test
	public void testsupport(){
//		SupportDao.updatesupport(2, 123);
//		SupportDao.updatesupport(1, 123);
		SupportDao.updatesupport(3, 123);
	}
	
	
	@Test
	public void testvcpage(){
		Page page = new Page();
		page.setPagenow(2);
		page.setPagesize(2);
		List list = new ArrayList<VideoComment>();
		
		list = VideoCommentDao.page(page, 110);
		for(int i = 0 ; i < list.size() ; i++){
			VideoComment tracks = (VideoComment)list.get(i);
			System.out.println(tracks.toString());
		}
			
	}
	
	@Test
	public void testpropage(){
		Page page = new Page();
		page.setPagenow(2);
		page.setPagesize(2);
		List list = new ArrayList<Problems>();
		list = ProblemDao.showallProblembycourseid(page, 111);
		for(int i = 0 ; i < list.size() ; i++){
			Problems tracks = (Problems)list.get(i);
			System.out.println(tracks.toString());
		}
	}
	
	
	@Test
	public void testrel(){
		List list = new ArrayList<Course>();
		list = CourseDao.getSomerelevantCourse("HTML/CSS");
		System.out.println("the list.size() is"+list.size());
		for(int i = 0 ; i < list.size() ; i++){
			Course tracks = (Course)list.get(i);
			System.out.println(tracks.toString());
		}
	}

	@Test
	public void testgroup(){
		List list = new ArrayList();
		list = VideoDao.showallVideobyChapters(61);
		System.out.println("the list.size is"+list.size());
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println(list.get(i));
//			List lista = new ArrayList<Video>();
//			lista = (List) list.get(i);
//			for(int j = 0;j<lista.size();i++){
//			Video tracks = (Video)list.get(i);
//			System.out.println(tracks.toString());
//		}
		}
	}
	
	@Test
	public void testcc(){
		List list = new ArrayList<CourseComment>();
		list = CommentDao.getCourseCommentBycourseid(61);
		for(int i = 0 ; i < list.size() ; i++){
			CourseComment tracks = (CourseComment)list.get(i);
			System.out.println(tracks.toString());
		}
	}
	
	@Test
	public void testccpage(){
		Page page = new Page();
		page.setPagenow(1);
		page.setPagesize(2);
		page.setPagecount(2);
		List list = new ArrayList<CourseComment>();
		list = CommentDao.getCommentBypage(page, 61);
		for(int i = 0 ; i < list.size() ; i++){
			CourseComment tracks = (CourseComment)list.get(i);
			System.out.println(tracks.toString());
		}
		
	}
	
	
	@Test
	public void testdate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		
	}
	
	
	@Test
	public void testlearningt(){
		
		HashMap hm = new HashMap();
		hm = (HashMap) CourseDao.returnLearnFlag(61,123);
		Iterator iter = hm.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			System.out.println(key);
			System.out.println(val);
		}
		
	}
	
	@Test 
	public void testview(){
		//ProblemDao.addviewnum(1);
		System.out.println(ProblemDao.getCommcount(1));
	}
	
	
	@Test
	public void testcsuport(){
		
		SupportDao.updatecsupport(3, 125);
		
	}
	
	@Test
	public void testcoll(){
		CollectionDao.updatecollect(61, 213);
		
	}
	
	@Test
	public void testct(){
		CourseTeacher ct = CourseTeacherDao.getcourseteacherbyid(61);
		System.out.println(ct.toString());
	}
	
	
	@Test
	public void testrel1(){
		
	}
	
	@Test
	public void testsearchpage(){
		
		Page page = new Page();
		page.setPagesize(2);
		page = PageDao.getsearchpage(page, "JAVA");
		System.out.println(page.toString());
		
	}
	
	
	@Test
	public void testaddlearnnum(){
		//CourseDao.addlearnum(61,258);
	}
	
	@Test 
	public void testgetcoll(){
		List list = new ArrayList<Course>();
		list = CourseDao.getCollection(213);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
	}
	
	@Test
	public void testaddlt(){
		
		LearningTracksDao.addlt(5, 321);
		
	}
	
	@Test
	public void testkey(){
		String content = "HTML+CSS基础教程8小时带领大家步步深入学习标签用法和意义,让你精通CSS中三大定位机制，彻底掌握网页布局的相关知识,用最简洁的案例教你布局的那些知识，这是前端工程师基本技能";
		List<String> keywordList = HanLP.extractKeyword(content,4);
		System.out.println(keywordList);
	}
	
	@Test
	public void testkey2(){
		Set rec = CourseDao.recommend(666);
		Iterator<Course> it = rec.iterator();
		while (it.hasNext()) {
		  Course str = it.next();
		  System.out.println(str.toString());
		}
	}
	
	@Test
	public void testfla(){
		NumberFormat nFormat=NumberFormat.getNumberInstance(); 
	    nFormat.setMaximumFractionDigits(0);//设置小数点后面位数为
	    System.out.println(nFormat.format(3.1415));
		float p = (float) ((double)3/(double)11)*100;
		System.out.println(nFormat.format(p));
	}
	
	@Test
	public void testreandom(){
		int max=20-5;
        int min=0;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);
	}
	
	@Test
	public void testltcour(){
		List list = new ArrayList();
	    list = LearningTracksDao.showUserLt(666);
	    for(int i=0;i<list.size();i++){
	    	System.out.println(list.get(i).toString());
	    }
	    
	}
	
	@Test
	public void testana(){
		List list =LearningTracksDao.showUserLt(666);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
	}
	
	@Test
	public void testdateadd(){
//		 Date date=new Date();//取时间 
//		 //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		
//	     Calendar calendar= new GregorianCalendar(); 
//	     calendar.setTime(date); 
//	     calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
//	     date=calendar.getTime();
//	     System.out.println(date);
		
//		Date dNow = new Date();   //当前时间
//		Date dBefore = new Date();
//
//		Calendar calendar = Calendar.getInstance(); //得到日历
//		calendar.setTime(dNow);//把当前时间赋给日历
//		calendar.add(Calendar.DAY_OF_MONTH, -5);  //设置为前一天
//		dBefore = calendar.getTime();   //得到前一天的时间
//
//
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
//		String defaultStartDate = sdf.format(dBefore); //格式化前一天
//		String defaultEndDate = sdf.format(dNow); //格式化当前时间


		

		
		for(int i=0;i<5;i++ ){
			Date dNow = new Date();   //当前时间
			Date dBefore = new Date();

			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -i);  //设置为前一天
			dBefore = calendar.getTime();   //得到前一天的时间


			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
			String defaultStartDate = sdf.format(dBefore); //格式化前一天
			String defaultEndDate = sdf.format(dNow); //格式化当前时间
			
			
			System.out.println("前一天的时间是：" + defaultStartDate);

			//System.out.println("生成的时间是：" + defaultEndDate);  
			
			
		}
		
		
	}
	
	@Test
	public void testtime(){
		NumberFormat nFormat=NumberFormat.getNumberInstance(); 
	    nFormat.setMaximumFractionDigits(0);//设置小数点后面位数为
	    
		float p = (float) ((float) ((double)27*(double)150)/(double)100);
		System.out.println(nFormat.format(p));
	}
	
	
	@Test
	public void testmin(){
		float min = (float) ((double)16 / (double)60);
		System.out.println(min);
	}
	
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
	
	@Test
	public void testmapa(){
		Map map = LearningTracksDao.getTimeAna(666);
		Map<String, String> resultMap = sortMapByKey(map);
		//List list = mapTransitionList(map);
		System.out.println(resultMap.toString());
	}
	
	@Test
	public void testcata(){
//		List list = LearningTracksDao.getcate(666);
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).toString());
//		}
		List cateperlist = LearningTracksDao.getcate(666);
		String jsondata="";
        String jsondata2 ="";
        for(int i=1;i<cateperlist.size();i++){
        	jsondata = jsondata+ "{value:" + ((Category)cateperlist.get(i)).getPercent() + ",name:' "+((Category)cateperlist.get(i)).getType()+ "'}," ;
            jsondata2 = jsondata2 +  "'"+((Category)cateperlist.get(i)).getType()+"',";
        }
        System.out.println(jsondata);
        System.out.println(jsondata2);
		
	}
	
	@Test
	public void testhmac(){
		try {
			String s = HMACSHA1.getSignature("123", "tdtAyDvVdusjPkI63IbQKDJFTOt01Y&");
			System.out.println(s);
			
			System.out.println("//"+Base64Util.encode(s.getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test 
	public void testsign() throws Exception{
		String str = "http://live.aliyuncs.com/?Format=XML&SignatureMethod=HMAC-SHA1&Action=DescribeLiveSnapshotConfig&AccessKeyId=testid&RegionId=cn-shanghai&ServiceCode=live&DomainName=test.com&AppName=test&SignatureNonce=c2fe8fbb-2977-4414-8d39-348d02419c1c&Version=2016-11-01&SignatureVersion=1.0&Timestamp=2017-06-14T09:51:14Z";
		try {
			String str1 = URLEncoder.encode(str, "utf-8");
			System.out.println("str1 is "+str1);
			String str2 = "GET&%2F&"+ str1 ;
			System.out.println("str2 is" + str2);
			String s = HMACSHA1.getSignature(str2, "testsecret&");
			String s2 = HMACSHA1.getSignature("GET&%2F&AccessKeyId%3Dtestid%26Action%3DDescribeLiveSnapshotConfig%26AppName%3Dtest%26DomainName%3Dtest.com%26Format%3DXML%26RegionId%3Dcn-shanghai%26ServiceCode%3Dlive%26SignatureMethod%3DHMAC-SHA1%26SignatureNonce%3Dc2fe8fbb-2977-4414-8d39-348d02419c1c%26SignatureVersion%3D1.0%26Timestamp%3D2017-06-14T09%253A51%253A14Z%26Version%3D2016-11-01", "testsecret&");
			System.out.println("the s2 is "+Base64Util.encode(s2.getBytes()));
			System.out.println("HAMC is "+ s);
			String sign = Base64Util.encode(s.getBytes());
			System.out.println("the sign is"+ sign);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getUtc() throws UnsupportedEncodingException{
		 	String UTCTimeStr = GetUTCTimeUtil.getUTCTimeStr() ;
		 	UTCTimeStr = URLEncoder.encode(UTCTimeStr,"utf-8");
		 	String sign = Base64Util.encode(UTCTimeStr.getBytes());
		 	//String s2 = HMACSHA1.getSignature(UTCTimeStr);
	        System.out.println(UTCTimeStr);   
	        //System.out.println(GetUTCTimeUtil.getLocalTimeFromUTC(UTCTimeStr));  
	}
	
	@Test 
	public void getapiurl() throws Exception{
		
		
		String s = "https://live.aliyuncs.com?Action=DescribeLiveStreamsOnlineList&DomainName=live.iotesta.cn&StartTime=2015-06-25T03:30:50Z&EndTime=2015-12-26T03:30:50Z";
		String format = "Format=XML";
		String signaturemethod = "SignatureMethod=HMAC-SHA1";
		String Version="Version=2016-11-01";
		String AccessKeyId = "LTAINbH9JXc8UW4y";
		String Timestamp = GetUTCTimeUtil.getUTCTimeStr();
		String SignatureVersion = "SignatureVersion=1.0";
		String SignatureNonce = "93452c33-2158-4be6-af42-f5f283cb00a5";
		
		//String percentEncode = format+"&"+Version+"&"+"";
		String Canonicalized = "2016-11-01&LTAINbH9JXc8UW4y&HMAC-SHA1&"+Timestamp+"1.0&93452c33-2158-4be6-af42-f5f283cb00a5&DescribeLiveStreamsPublishList&live.iotesta.cn&2017-07-25T19:00:00Z&2017-07-30T19:00:00Z";
		String stringtosign  = "GET&%2F&"+Canonicalized;
		String signature0 = HMACSHA1.getSignature(stringtosign, "tdtAyDvVdusjPkI63IbQKDJFTOt01Y&");
		String signature = Base64Util.encode(signature0.getBytes());
		String puglicdata = "Format=XML&SignatureMethod=HMAC-SHA1&Signature="+signature+"&Timestamp=2017-03-08T06%3A44%3A15Z&SignatureNonce=93452c33-2158-4be6-af42-f5f283cb00a5&Version=2016-11-01&SignatureVersion=1.0";

		
		
		
	}
	
	
	@Test
	public void teststringre(){
		String s = "2017-07-25 21:20";
		String s1 = s.substring(14, 16);
		String s2 = s.substring(11, 13);
		System.out.println(s1);
		System.out.println(s2);
		int s3 = Integer.parseInt("09");
		System.out.println(s3);
	}
	
	@Test
	public void testtimehuigui(){
		LearningTracksDao.getNextTime(666);
	}
	
	
	@Test
	public void testgettype(){
		Page page = new Page();
		page.setPagenow(1);
		page.setPagesize(12);
		page = PageDao.gettypepage(page, "2-4min", "Java", "基础");
		List list = CourseDao.gettypebylist(page,"2-4min", "Java", "基础");
		System.out.println(list.toString());
		
		
		System.out.println(page.toString());
	}
	
	
	@Test
	public void testhalfs(){
		String s ="困难a困难a";
		s = s.substring(0, s.length()/2);
		System.out.println(s);
		
		System.out.println("2-4min".length());
	}
	
	@Test
	public void testfile(){
		File file = new File("C:/Users/jinge no.1/Desktop/OnlineLearning/WebRoot/img");
		File[] files = file.listFiles();
		if(files!=null){
			for(File f:files){
				System.out.println(f.getName());
				System.out.println(f.getPath());
			}
		}
	}
	
	@Test
	public void testfilelist(){
		String file = CourseDao.getcoursefile(61);
		String filepath = "D:/java/.metadata/.me_tcat85/webapps/OnlineLearning/coursefile/"+file;
		List list = CourseDao.getfilelist(filepath);
		System.out.println(list.toString());
	}
	
}
