package com.user.action;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.utils.HibernateUtils;

import net.sf.json.JSONObject;

public class ChangePwdAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;// 员工编号
	private String user_password;//密码

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	// 返回的结果
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	// 修改员工信息
	public String change() {

		// 该函数用来保存员工注册信息
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 写具体逻辑crud操作
		// 添加功能
		try {
			//UserBean user = new UserBean();

			// 直接使用hibernate的session.update()会使整字段更新
			// 构造update的sql语句
			String sql = "update UserBean user set user.user_password = " + "\'" + user_password + "\'" 
					+ "where user.user_id = " + "\'" + user_id + "\'";

			// where id = 3";// +"\'"+user_username+"\'"
			Query query = session.createQuery(sql);
			query.executeUpdate();

			/*
			 * user.setUser_id(user_id);//id
			 * user.setUser_department(user_department);//部门
			 * user.setUser_position(user_position);//职位
			 * user.setUser_nickname(user_nickname);//昵称
			 * //user.setUser_interested(user_interested);//兴趣
			 * user.setUser_mailbox(user_mailbox);//邮箱 //使用session的方法实现修改
			 * session.update(user);
			 */

			// 提交事务
			tx.commit();

			Map<String, Object> map = new HashMap<String, Object>();
			// 跟传统的action传值一样，这里的name,gender,position由struts接收前端传参后初始化，在action中直接使用就可以了。
			map.put("result", "修改成功");
			// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
			JSONObject json = JSONObject.fromObject(map);
			// 注意，这里的result为String类型，内容为：
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();

			Map<String, Object> map = new HashMap<String, Object>();
			// 跟传统的action传值一样，这里的name,gender,position由struts接收前端传参后初始化，在action中直接使用就可以了。
			map.put("result", "修改失败，请重试");
			// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
			JSONObject json = JSONObject.fromObject(map);
			// 注意，这里的result为String类型，内容为：
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;
		} finally {
			// 关闭资源
			session.close();
			// sessionFactory.close();
			// return SUCCESS;
		}
	}

}
