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
	// 邮箱验证码
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
		// 获取session中的值
		// ActionContext ctx = invocation.getInvocationContext();
		// Map<String,Object> session = ctx.getSession();
		// String check_number =
		// (String)session.get("check_mail");//获取用户登录的信息，从session中获取

		// 获取session的值
		int check_number = (Integer) ServletActionContext.getRequest().getSession().getAttribute("check_number");// 取出session中的验证码
		// System.out.println(check_number);
		// 与输入的数字进行比较 查看是否成功
		boolean eq = (check_number == check_mail);
		Map<String, Object> map = new HashMap<String, Object>();
		// 跟传统的action传值一样，这里的name,gender,position由struts接收前端传参后初始化，在action中直接使用就可以了。
		map.put("result", eq);
		// 将一个JAVA对象（这里是map对象），转化为一个JSON对象。需要import net.sf.json.JSONArray。
		JSONObject json = JSONObject.fromObject(map);
		// 注意，这里的result为String类型，内容为：
		// "{name:"Chris",age:5,position:"tt"}"
		result = json.toString();

		if (eq) {
			System.out.println("验证成功");
		} else {
			System.out.println("验证失败");
		}
		return SUCCESS;
	}

}
