package com.user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.UserBean;
import com.utils.HibernateUtils;

import net.sf.json.JSONObject;

public class UserInfoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	// 用户的员工编号
	private String user_id;
	private String result;

	// 返回的结果
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String info() {

		// 该函数用来查询显示用户个人信息
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 写具体逻辑crud操作
		// 添加功能
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			// UserBean user = new UserBean();//创建一个userBen的对象
			// 创建sql的查询语句
			String sql = "from UserBean where user_id=" + user_id;
			Query query = session.createQuery(sql); // 此处user是类名，而不是数据库的表名,select
													// * 不写
			List<UserBean> users = query.list();
			// 检测是否有这个用户名
			if (users != null && users.size() > 0) {
				// 查询返回user信息，只获取第一个，因为员工编号是主键唯一的
				UserBean user = users.get(0);
				map.put("user_id", user.getUser_id());// 员工编号
				map.put("user_name", user.getUser_name());// 员工姓名
				map.put("user_department", user.getUser_department());// 部门
				map.put("user_position", user.getUser_position());// 职位
				map.put("user_nickname", user.getUser_nickname());// 昵称
				map.put("user_mailbox", user.getUser_mailbox());// 邮箱
			}

			// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
			JSONObject json = JSONObject.fromObject(map);
			// 注意，这里的result为String类型，内容为：
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();
			return SUCCESS;
		} finally {
			// 关闭资源
			session.close();
			// sessionFactory.close();
			// 不用关闭sessionFactory，session是线程不安全的，不能在不同的线程中使用同一个session
		}
	}

}
