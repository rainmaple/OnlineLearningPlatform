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
	private int user_id;// Ա�����id
	private String user_name;// Ա������
	private String user_department;// Ա������
	private String user_position;// Ա��ְλ
	private String user_username;// Ա���˻��û���
	private String user_password;// Ա���˻�����
	private String user_nickname;// Ա���˻��ǳ�
	// private String user_interested;//Ա������Ȥ�Ŀγ�
	private String user_mailbox;// Ա������
	private String result;// ���ؽ��

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

		// �ú�����������Ա��ע����Ϣ
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// ��������
		Transaction tx = session.beginTransaction();
		// д�����߼�crud����
		// ��ӹ���
		try {
			UserBean user = new UserBean();

			user.setUser_id(user_id);// Ա�����
			user.setUser_name(user_name);// Ա������
			user.setUser_department(user_department);// ����
			user.setUser_position(user_position);// ְλ
			user.setUser_username(user_username);// �û���
			user.setUser_password(user_password);// ����
			user.setUser_nickname(user_nickname);// �ǳ�
			// user.setUser_interested(user_interested);//��Ȥ
			user.setUser_mailbox(user_mailbox);// ����
			// ʹ��session�ķ���ʵ�����
			session.save(user);

			// �ύ����
			tx.commit();

			Map<String, Object> map = new HashMap<String, Object>();
			// ����ͳ��action��ֵһ���������name,gender,position��struts����ǰ�˴��κ��ʼ������action��ֱ��ʹ�þͿ����ˡ�
			map.put("isSave", true);
			// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
			JSONObject json = JSONObject.fromObject(map);
			// ע�⣬�����resultΪString���ͣ�����Ϊ��
			// "{name:"Chris",age:5,position:"tt"}"
			result = json.toString();

			return SUCCESS;

		} catch (HibernateException e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			// �ر���Դ
			session.close();
			// sessionFactory.close();
			// return SUCCESS;
		}
	}
}
