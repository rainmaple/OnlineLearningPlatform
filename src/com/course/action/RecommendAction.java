package com.course.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.course.dao.CourseDao;
import com.course.dao.LearningTracksDao;
import com.course.dao.PageDao;
import com.learning.entity.Category;
import com.learning.entity.Course;
import com.learning.entity.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.user.util.MapKeyComparator;

public class RecommendAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();

		
		Set set = new HashSet<Course>();
		//list = CourseDao.GetCourseList();
		set = CourseDao.recommend(666);
		List list = new ArrayList<Course>();
		list.addAll(set);
		//List list = (List) CourseDao.recommend(666);
		request.setAttribute("recommend", list);
		//System.out.println(list);
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
		Map map = LearningTracksDao.getTimeAna(userid);
		
		Map<String, String> resultMap = sortMapByKey(map);
//		Iterator it2 = resultMap.keySet().iterator();
//		while (it2.hasNext()) {
//            String key = it2.next().toString();
//           System.out.println("the key is" + key);
//        }
		
		
		List listKey = new ArrayList();
        List listValue = new ArrayList();
        Iterator it = resultMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            listKey.add(key);
            listValue.add(resultMap.get(key));
            //System.out.println(resultMap.get(key));
            //System.out.println(key);
        }
		
        request.setAttribute("day1", listKey.get(0));
        request.setAttribute("day1data", listValue.get(0));
        
        
        request.setAttribute("day2", listKey.get(1));
        request.setAttribute("day2data", listValue.get(1));
        
        request.setAttribute("day3", listKey.get(2));
        request.setAttribute("day3data", listValue.get(2));
        
        request.setAttribute("day4", listKey.get(3));
        request.setAttribute("day4data", listValue.get(3));
        
        request.setAttribute("day5", listKey.get(4));
        request.setAttribute("day5data", listValue.get(4));
        
        
        List cateperlist = LearningTracksDao.getcate(666);
        String jsondata="";
        String jsondata2 ="";
        for(int i=1;i<cateperlist.size();i++){
        	jsondata = jsondata+ "{value:" + ((Category)cateperlist.get(i)).getPercent() + ",name:'"+((Category)cateperlist.get(i)).getType()+ "'}," ;
            jsondata2 = jsondata2 +  "'"+((Category)cateperlist.get(i)).getType()+"',";
        }
        
        request.setAttribute("jsondata", jsondata);
        request.setAttribute("jsondata2", jsondata2);
        
        
        
        
		context.put("recommend", list);
		
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).toString());
//		}
		
		
		//context.put("courselist", list);
		
	
		
				
		return SUCCESS;
	}
	
	
	
	
	private List changelist;
	
	public List getChangelist() {
		return changelist;
	}

	public void setChangelist(List changelist) {
		this.changelist = changelist;
	}

	public String changerecommend(){
		
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
		Map map = LearningTracksDao.getTimeAna(userid);
		
		Set set = new HashSet<Course>();
		//list = CourseDao.GetCourseList();
		set = CourseDao.recommend(userid);
		List list = new ArrayList<Course>();
		list.addAll(set);
		//List list = (List) CourseDao.recommend(666);
		request.setAttribute("recommend", list);
		//System.out.println(list);
		
		int max=list.size()-5;
        int min=0;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        
        List newlist = new ArrayList();
        for(int i=s;i<s+5;i++){
        	newlist.add(list.get(i));
        }
        //System.out.println("草泥马");
        for(int i=0;i<newlist.size();i++){
			System.out.println(newlist.get(i).toString());
		}
        
        
        //System.out.println(s);
		
		this.changelist = newlist;
		
		//context.put("recommend", list);
		
		
//		System.out.println("我到这里了");
//		List list = (List) request.getAttribute("recommend");
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).toString());
//		}
//		System.out.println("我到这里了2");
		
		return "changeok";
	}
	
	
	
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
	
	
	private String day01;
	
	public String getDay01() {
		return day01;
	}

	public void setDay01(String day01) {
		this.day01 = day01;
	}
	private int day01data;
	

	public int getDay01data() {
		return day01data;
	}

	public void setDay01data(int day01data) {
		this.day01data = day01data;
	}
	
	
	private String day02;
	
	public String getDay02() {
		return day02;
	}

	public void setDay02(String day02) {
		this.day02 = day02;
	}
	private int day02data;
	

	public int getDay02data() {
		return day02data;
	}

	public void setDay02data(int day02data) {
		this.day02data = day02data;
	}
	
	
	private String day03;
	
	public String getDay03() {
		return day03;
	}

	public void setDay03(String day03) {
		this.day03 = day03;
	}
	private int day03data;
	

	public int getDay03data() {
		return day03data;
	}

	public void setDay03data(int day03data) {
		this.day03data = day03data;
	}
	
	
	private String day04;
	
	public String getDay04() {
		return day04;
	}

	public void setDay04(String day04) {
		this.day04 = day04;
	}
	private int day04data;
	

	public int getDay04data() {
		return day04data;
	}

	public void setDay04data(int day04data) {
		this.day04data = day04data;
	}
	
	
	private String day05;
	
	public String getDay05() {
		return day05;
	}

	public void setDay05(String day05) {
		this.day05 = day05;
	}
	private int day05data;
	

	public int getDay05data() {
		return day05data;
	}

	public void setDay05data(int day05data) {
		this.day05data = day05data;
	}
	
	
	private String jsondata;
	public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public String getJsondata2() {
		return jsondata2;
	}

	public void setJsondata2(String jsondata2) {
		this.jsondata2 = jsondata2;
	}




	private String jsondata2;

	public String getempstatics(){
		ActionContext context=ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//int userid = Integer.parseInt((String)request.getParameter("userid"));
		Map<String,Object> session = null;
		session = context.getSession();
		int userid = (Integer) session.get("user_id");
		
		Map map = LearningTracksDao.getTimeAna(userid);
		
		Map<String, String> resultMap = sortMapByKey(map);
		
		List listKey = new ArrayList();
        List listValue = new ArrayList();
        Iterator it = resultMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            listKey.add(key);
            listValue.add(resultMap.get(key));
            //System.out.println(resultMap.get(key));
            //System.out.println(key);
        }
		day01 = (String) listKey.get(0);
		this.day01data = Integer.parseInt((String) listValue.get(0));
//        request.setAttribute("day1", listKey.get(0));
//        request.setAttribute("day1data", listValue.get(0));
//        System.out.println("this.day01 is"+this.day01);
//        System.out.println(day01data);
		this.day02 = (String) listKey.get(1);
		this.day02data = Integer.parseInt((String) listValue.get(1));
//        request.setAttribute("day2", listKey.get(1));
//        request.setAttribute("day2data", listValue.get(1));
        
		this.day03 = (String) listKey.get(2);
		this.day03data = Integer.parseInt((String) listValue.get(2));
//        request.setAttribute("day3", listKey.get(2));
//        request.setAttribute("day3data", listValue.get(2));
      
		this.day04 = (String) listKey.get(3);
		this.day04data = Integer.parseInt((String) listValue.get(3));
//        request.setAttribute("day4", listKey.get(3));
//        request.setAttribute("day4data", listValue.get(3));
        
		this.day05 = (String) listKey.get(4);
		this.day05data =Integer.parseInt((String) listValue.get(4));
//        request.setAttribute("day5", listKey.get(4));
//        request.setAttribute("day5data", listValue.get(4));
		
		
		
        List cateperlist = LearningTracksDao.getcate(userid);
        String jsondata="";
        String jsondata2 ="";
        for(int i=1;i<cateperlist.size();i++){
        	if(i==cateperlist.size()-1){
        		jsondata = jsondata+ "{value: " + ((Category)cateperlist.get(i)).getPercent() + ",name: '"+((Category)cateperlist.get(i)).getType()+ "'}" ;
                jsondata2 = jsondata2 +  "'"+((Category)cateperlist.get(i)).getType()+"'";
        	}else{
	        	jsondata = jsondata+ "{value: " + ((Category)cateperlist.get(i)).getPercent() + ",name: '"+((Category)cateperlist.get(i)).getType()+ "'}," ;
	            jsondata2 = jsondata2 +  "'"+((Category)cateperlist.get(i)).getType()+"',";
            }
        }
        
        this.jsondata = jsondata;
        this.jsondata2 = jsondata2;
        request.setAttribute("jsondata", jsondata);
        request.setAttribute("jsondata2", jsondata2);
//        System.out.println(jsondata);
//        System.out.println(jsondata2);
//        
        return "getok";
		
	}
	
}
