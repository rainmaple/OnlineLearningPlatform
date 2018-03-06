package com.user.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class CheckMailAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ������֤��
	private int check_mail;

	public int getCheck_mail() {
		return check_mail;
	}

	public void setCheck_mail(int check_mail) {
		this.check_mail = check_mail;
	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String check() {
		// ��ȡsession�е�ֵ
		// ActionContext ctx = invocation.getInvocationContext();
		// Map<String,Object> session = ctx.getSession();
		// String check_number =
		// (String)session.get("check_mail");//��ȡ�û���¼����Ϣ����session�л�ȡ

		// ��ȡsession��ֵ
		int check_number = (Integer) ServletActionContext.getRequest().getSession().getAttribute("check_number");// ȡ��session�е���֤��
		// System.out.println(check_number);
		// ����������ֽ��бȽ� �鿴�Ƿ�ɹ�
		boolean eq = (check_number == check_mail);
		Map<String, Object> map = new HashMap<String, Object>();
		// ����ͳ��action��ֵһ���������name,gender,position��struts����ǰ�˴��κ��ʼ������action��ֱ��ʹ�þͿ����ˡ�
		map.put("result", eq);
		// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
		JSONObject json = JSONObject.fromObject(map);
		// ע�⣬�����resultΪString���ͣ�����Ϊ��
		// "{name:"Chris",age:5,position:"tt"}"
		result = json.toString();

		if (eq) {
			System.out.println("��֤�ɹ�");
		} else {
			System.out.println("��֤ʧ��");
		}
		return SUCCESS;
	}

}
