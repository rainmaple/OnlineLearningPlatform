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
	// �û���Ա�����
	private String user_id;
	private String result;

	// ���صĽ��
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

		// �ú���������ѯ��ʾ�û�������Ϣ
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
			String sql = "from UserBean where user_id=" + user_id;
			Query query = session.createQuery(sql); // �˴�user�����������������ݿ�ı���,select
													// * ��д
			List<UserBean> users = query.list();
			// ����Ƿ�������û���
			if (users != null && users.size() > 0) {
				// ��ѯ����user��Ϣ��ֻ��ȡ��һ������ΪԱ�����������Ψһ��
				UserBean user = users.get(0);
				map.put("user_id", user.getUser_id());// Ա�����
				map.put("user_name", user.getUser_name());// Ա������
				map.put("user_department", user.getUser_department());// ����
				map.put("user_position", user.getUser_position());// ְλ
				map.put("user_nickname", user.getUser_nickname());// �ǳ�
				map.put("user_mailbox", user.getUser_mailbox());// ����
			}

			// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
			JSONObject json = JSONObject.fromObject(map);
			// ע�⣬�����resultΪString���ͣ�����Ϊ��
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();
			return SUCCESS;
		} finally {
			// �ر���Դ
			session.close();
			// sessionFactory.close();
			// ���ùر�sessionFactory��session���̲߳���ȫ�ģ������ڲ�ͬ���߳���ʹ��ͬһ��session
		}
	}

}
