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
	// 只要获取发送的QQ邮箱地址
	private String to;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	// 用来存储返回给前端页面的值，需要与struts.xml中配置的名字一致，并且必须有get和set方法。
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String write() {

		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("465");
		mailInfo.setValidate(true);
		mailInfo.setUserName("769790863@qq.com"); // 自己的邮箱
		mailInfo.setPassword("zxgixwszzsljbebj");// 自己的邮箱密码，用于验证 （授权码）

		mailInfo.setFromAddress("769790863@qq.com"); /// 自己的邮箱
		mailInfo.setToAddress(to); // 对方的邮箱 吴宇翔451571631@qq.com
									// 我小号3373540133@qq.com
		System.out.println(to);
		mailInfo.setSubject("来自在线学习平台的验证数字");

		// 构造一个随机数
		Random random = new Random();// 默认构造方法
		int number = random.nextInt(10000);

		ServletActionContext.getRequest().getSession().setAttribute("check_number", number);// 将检验邮箱的验证码存储到会话中

		String s = String.valueOf(number);
		// System.out.println(s);
		mailInfo.setContent(s);

		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean isSend = sms.sendTextMail(mailInfo); // 发送文体格式

		Map<String, Object> map = new HashMap<String, Object>();
		// 跟传统的action传值一样，这里的name,gender,position由struts接收前端传参后初始化，在action中直接使用就可以了。
		map.put("isSend", isSend);
		// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
		JSONObject json = JSONObject.fromObject(map);
		// 注意，这里的result为String类型，内容为：
		// "{name:"Chris",age:5,position:"tt"}"
		result = json.toString();

		if (isSend) {
			System.out.println("发送成功");
		} else {
			System.out.println("出错了");
		}

		return SUCCESS;

		// HttpServletResponse response=ServletActionContext.getResponse();
		/*
		 * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		 * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		 * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		 */
		// response.setContentType("text/html;charset=utf-8");
		// response.setCharacterEncoding("UTF-8");
		// PrintWriter out = response.getWriter();
		// JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
		// String jsonString="{\"user\":{\"back\":\"" +isSend+ "\" }";
		// out.println(jsonString);
		// out.flush();
		// out.close();

		// "{\"user\":{\"back\":\"123\",\"name\":\"张三\",\"say\":\"Hello , i am a
		// action to print a json!\",\"password\":\"JSON\"},\"success\":true}"
	}

}
