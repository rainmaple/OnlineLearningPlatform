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
	private String user_username; // �û���
	private String user_password;// ����
	private String result;// ���ؽ��

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

		// �ú�����������Ա��ע����Ϣ
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// ��������
		Transaction tx = session.beginTransaction();
		// д�����߼�crud����
		// ��ӹ���
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			// UserBean user = new UserBean();//����һ��userBen�Ķ���
			// ����sql�Ĳ�ѯ���
			String sql = "from UserBean where user_username=" + "\'" + user_username + "\'";// ��Ϊ��Ӣ��ʱ����Ҫ������
																							// ��
																							// user_name='wang'
																							// ��Ȼ����ʾ�Ҳ����к�
			Query query = session.createQuery(sql); // �˴�user�����������������ݿ�ı���,select
													// * ��д
			List<UserBean> users = query.list();
			// ����Ƿ�������û���
			if (users != null && users.size() > 0) {
				UserBean user = users.get(0);
				//System.out.println(user.getUser_password());
				// �������
				if (user_password.equals(user.getUser_password())) {
					
					// ����ͳ��action��ֵһ���������name,gender,position��struts����ǰ�˴��κ��ʼ������action��ֱ��ʹ�þͿ����ˡ�
					map.put("result", "�ɹ�");

					// ����session�����ֵ�¼״̬
					ServletActionContext.getRequest().getSession().setAttribute("user_username", user_username);// �����û���
					ServletActionContext.getRequest().getSession().setAttribute("user_nickname",
							user.getUser_nickname());// �����ǳ�
					ServletActionContext.getRequest().getSession().setAttribute("user_id", user.getUser_id());// ����Ա�����
				} else {
					map.put("result", "�����������");
				}
			} else {
				map.put("result", "�˺Ŵ���򲻴���");
			}

			/*
			 * if(users != null && users.size() > 0) { for (int i = 0; i <
			 * users.size(); i++) { UserBean user = users.get(i);
			 * //System.out.println(me.getDoYouLoveMe()); } }
			 */

			// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
			JSONObject json = JSONObject.fromObject(map);
			// ע�⣬�����resultΪString���ͣ�����Ϊ��
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", "��¼����");
			return SUCCESS;
		} finally {
			// �ر���Դ
			session.close();
			// sessionFactory.close();
			// ���ùر�sessionFactory��session���̲߳���ȫ�ģ������ڲ�ͬ���߳���ʹ��ͬһ��session
		}
	}

}
