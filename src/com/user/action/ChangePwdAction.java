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
	private String user_id;// Ա�����
	private String user_password;//����

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

	// ���صĽ��
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	// �޸�Ա����Ϣ
	public String change() {

		// �ú�����������Ա��ע����Ϣ
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// ��������
		Transaction tx = session.beginTransaction();
		// д�����߼�crud����
		// ��ӹ���
		try {
			//UserBean user = new UserBean();

			// ֱ��ʹ��hibernate��session.update()��ʹ���ֶθ���
			// ����update��sql���
			String sql = "update UserBean user set user.user_password = " + "\'" + user_password + "\'" 
					+ "where user.user_id = " + "\'" + user_id + "\'";

			// where id = 3";// +"\'"+user_username+"\'"
			Query query = session.createQuery(sql);
			query.executeUpdate();

			/*
			 * user.setUser_id(user_id);//id
			 * user.setUser_department(user_department);//����
			 * user.setUser_position(user_position);//ְλ
			 * user.setUser_nickname(user_nickname);//�ǳ�
			 * //user.setUser_interested(user_interested);//��Ȥ
			 * user.setUser_mailbox(user_mailbox);//���� //ʹ��session�ķ���ʵ���޸�
			 * session.update(user);
			 */

			// �ύ����
			tx.commit();

			Map<String, Object> map = new HashMap<String, Object>();
			// ����ͳ��action��ֵһ���������name,gender,position��struts����ǰ�˴��κ��ʼ������action��ֱ��ʹ�þͿ����ˡ�
			map.put("result", "�޸ĳɹ�");
			// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
			JSONObject json = JSONObject.fromObject(map);
			// ע�⣬�����resultΪString���ͣ�����Ϊ��
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();

			Map<String, Object> map = new HashMap<String, Object>();
			// ����ͳ��action��ֵһ���������name,gender,position��struts����ǰ�˴��κ��ʼ������action��ֱ��ʹ�þͿ����ˡ�
			map.put("result", "�޸�ʧ�ܣ�������");
			// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
			JSONObject json = JSONObject.fromObject(map);
			// ע�⣬�����resultΪString���ͣ�����Ϊ��
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;
		} finally {
			// �ر���Դ
			session.close();
			// sessionFactory.close();
			// return SUCCESS;
		}
	}

}
