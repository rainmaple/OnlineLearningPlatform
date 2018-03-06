package com.user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.UserBean;
import com.utils.HibernateUtils;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_username; // 用户名
	private String user_password;// 密码
	private String result;// 返回结果

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String login() {
		/*
		 * System.out.println("1111111111111111111111111");
		 * System.out.println(user_username); System.out.println(user_password);
		 * System.out.println("22222222222222222222222222");
		 */

		// 该函数用来保存员工注册信息
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
			String sql = "from UserBean where user_username=" + "\'" + user_username + "\'";// 因为在英文时，需要加引号
																							// 如
																							// user_name='wang'
																							// 不然会显示找不到列号
			Query query = session.createQuery(sql); // 此处user是类名，而不是数据库的表名,select
													// * 不写
			List<UserBean> users = query.list();
			// 检测是否有这个用户名
			if (users != null && users.size() > 0) {
				UserBean user = users.get(0);
				//System.out.println(user.getUser_password());
				// 检测密码
				if (user_password.equals(user.getUser_password())) {
					
					// 跟传统的action传值一样，这里的name,gender,position由struts接收前端传参后初始化，在action中直接使用就可以了。
					map.put("result", "成功");

					// 输入session，保持登录状态
					ServletActionContext.getRequest().getSession().setAttribute("user_username", user_username);// 保存用户名
					ServletActionContext.getRequest().getSession().setAttribute("user_nickname",
							user.getUser_nickname());// 保存昵称
					ServletActionContext.getRequest().getSession().setAttribute("user_id", user.getUser_id());// 保存员工编号
				} else {
					map.put("result", "密码输入错误");
				}
			} else {
				map.put("result", "账号错误或不存在");
			}

			/*
			 * if(users != null && users.size() > 0) { for (int i = 0; i <
			 * users.size(); i++) { UserBean user = users.get(i);
			 * //System.out.println(me.getDoYouLoveMe()); } }
			 */

			// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
			JSONObject json = JSONObject.fromObject(map);
			// 注意，这里的result为String类型，内容为：
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", "登录错误");
			return SUCCESS;
		} finally {
			// 关闭资源
			session.close();
			// sessionFactory.close();
			// 不用关闭sessionFactory，session是线程不安全的，不能在不同的线程中使用同一个session
		}
	}

}
