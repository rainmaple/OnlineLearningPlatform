package com.user.action;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.UserBean;
import com.utils.*;

import net.sf.json.JSONObject;

public class RegisteredAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_id;// 员工编号id
	private String user_name;// 员工姓名
	private String user_department;// 员工部门
	private String user_position;// 员工职位
	private String user_username;// 员工账户用户名
	private String user_password;// 员工账户密码
	private String user_nickname;// 员工账户昵称
	// private String user_interested;//员工感兴趣的课程
	private String user_mailbox;// 员工邮箱
	private String result;// 返回结果

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_department() {
		return user_department;
	}

	public void setUser_department(String user_department) {
		this.user_department = user_department;
	}

	public String getUser_position() {
		return user_position;
	}

	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	/*
	 * public String getUser_interested() { return user_interested; } public
	 * void setUser_interested(String user_interested) { this.user_interested =
	 * user_interested; }
	 */
	public String getUser_mailbox() {
		return user_mailbox;
	}

	public void setUser_mailbox(String user_mailbox) {
		this.user_mailbox = user_mailbox;
	}

	public String save() {

		// 该函数用来保存员工注册信息
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 写具体逻辑crud操作
		// 添加功能
		try {
			UserBean user = new UserBean();

			user.setUser_id(user_id);// 员工编号
			user.setUser_name(user_name);// 员工姓名
			user.setUser_department(user_department);// 部门
			user.setUser_position(user_position);// 职位
			user.setUser_username(user_username);// 用户名
			user.setUser_password(user_password);// 密码
			user.setUser_nickname(user_nickname);// 昵称
			// user.setUser_interested(user_interested);//兴趣
			user.setUser_mailbox(user_mailbox);// 邮箱
			// 使用session的方法实现添加
			session.save(user);

			// 提交事务
			tx.commit();

			Map<String, Object> map = new HashMap<String, Object>();
			// 跟传统的action传值一样，这里的name,gender,position由struts接收前端传参后初始化，在action中直接使用就可以了。
			map.put("isSave", true);
			// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
			JSONObject json = JSONObject.fromObject(map);
			// 注意，这里的result为String类型，内容为：
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			// 关闭资源
			session.close();
			// sessionFactory.close();
			// return SUCCESS;
		}
	}
}
