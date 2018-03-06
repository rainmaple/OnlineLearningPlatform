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

public class ChangeInfoAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;// Ա�����
	private String user_department;// ����
	private String user_position;// ְλ
	private String user_nickname;// �ǳ�
	private String user_mailbox;// ����

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_mailbox() {
		return user_mailbox;
	}

	public void setUser_mailbox(String user_mailbox) {
		this.user_mailbox = user_mailbox;
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
			String sql = "update UserBean user set user.user_department = " + "\'" + user_department + "\'" + ","
					+ "user.user_position = " + "\'" + user_position + "\'" + "," + "user.user_nickname = " + "\'"
					+ user_nickname + "\'" + "," + "user.user_mailbox = " + "\'" + user_mailbox + "\'"
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
