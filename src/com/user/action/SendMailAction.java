package com.user.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.user.utils.*;

import net.sf.json.JSONObject;

public class SendMailAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	// ֻҪ��ȡ���͵�QQ�����ַ
	private String to;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	// �����洢���ظ�ǰ��ҳ���ֵ����Ҫ��struts.xml�����õ�����һ�£����ұ�����get��set������
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String write() {

		// �������Ҫ�������ʼ�
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("465");
		mailInfo.setValidate(true);
		mailInfo.setUserName("769790863@qq.com"); // �Լ�������
		mailInfo.setPassword("zxgixwszzsljbebj");// �Լ����������룬������֤ ����Ȩ�룩

		mailInfo.setFromAddress("769790863@qq.com"); /// �Լ�������
		mailInfo.setToAddress(to); // �Է������� ������451571631@qq.com
									// ��С��3373540133@qq.com
		System.out.println(to);
		mailInfo.setSubject("��������ѧϰƽ̨����֤����");

		// ����һ�������
		Random random = new Random();// Ĭ�Ϲ��췽��
		int number = random.nextInt(10000);

		ServletActionContext.getRequest().getSession().setAttribute("check_number", number);// �������������֤��洢���Ự��

		String s = String.valueOf(number);
		// System.out.println(s);
		mailInfo.setContent(s);

		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		boolean isSend = sms.sendTextMail(mailInfo); // ���������ʽ

		Map<String, Object> map = new HashMap<String, Object>();
		// ����ͳ��action��ֵһ���������name,gender,position��struts����ǰ�˴��κ��ʼ������action��ֱ��ʹ�þͿ����ˡ�
		map.put("isSend", isSend);
		// ��һ��JAVA����������map���󣩣�ת��Ϊһ��JSON������Ҫimport net.sf.json.JSONArray��
		JSONObject json = JSONObject.fromObject(map);
		// ע�⣬�����resultΪString���ͣ�����Ϊ��
		// "{name:"Chris",age:5,position:"tt"}"
		result = json.toString();

		if (isSend) {
			System.out.println("���ͳɹ�");
		} else {
			System.out.println("������");
		}

		return SUCCESS;

		// HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * �ڵ���getWriter֮ǰδ���ñ���(�ȵ���setContentType����setCharacterEncoding�������ñ���),
		 * HttpServletResponse��᷵��һ����Ĭ�ϵı���(��ISO-8859-1)�����PrintWriterʵ���������ͻ�
		 * ����������롣�������ñ���ʱ�����ڵ���getWriter֮ǰ����,��Ȼ����Ч�ġ�
		 */
		// response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		// PrintWriter out = response.getWriter();
		// JSON�ڴ��ݹ���������ͨ�ַ�����ʽ���ݵģ������ƴ��һ��������
		// String jsonString="{\"user\":{\"back\":\"" +isSend+ "\" }";
		// out.println(jsonString);
		// out.flush();
		// out.close();

		// "{\"user\":{\"back\":\"123\",\"name\":\"����\",\"say\":\"Hello , i am a
		// action to print a json!\",\"password\":\"JSON\"},\"success\":true}"
	}

}
