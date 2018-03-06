package com.user.utils;
/**  
* �����ʼ���Ҫʹ�õĻ�����Ϣ  
*/   
import java.security.Security;
import java.util.Properties;   
import javax.net.ssl.*;

public class MailSenderInfo {
	// �����ʼ��ķ�������IP�Ͷ˿�   
    private String mailServerHost;   
    private String mailServerPort = "465";   
    // �ʼ������ߵĵ�ַ   
    private String fromAddress;   
    // �ʼ������ߵĵ�ַ   
    private String toAddress;   
    // ��½�ʼ����ͷ��������û���������   
    private String userName;   
    private String password;   
    // �Ƿ���Ҫ�����֤   
    private boolean validate = false;   
    // �ʼ�����   
    private String subject;   
    // �ʼ����ı�����   
    private String content;   
    // �ʼ��������ļ���   
    private String[] attachFileNames;     
    /**  
      * ����ʼ��Ự����  
      */   
    public Properties getProperties(){
    	
    	//Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    	 Properties p = new Properties();   
    	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    	p.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    	p.setProperty("mail.smtp.socketFactory.fallback", "false");
    	 p.setProperty("mail.smtp.socketFactory.port", "465"); 
//    	 p.setProperty("mail.smtp.socketFactory.port", "25"); 
      p.put("mail.smtp.host", this.mailServerHost);   
      p.put("mail.smtp.port", this.mailServerPort);   
//      p.put("mail.smtp.auth", validate ? "true" : "false");  
      p.put("mail.smtp.auth",  "true" );   
      p.put("mail.smtp.ssl.enable", "true");
     
      return p;   
    }   
    public String getMailServerHost() {   
      return mailServerHost;   
    }   
    public void setMailServerHost(String mailServerHost) {   
      this.mailServerHost = mailServerHost;   
    }  
    public String getMailServerPort() {   
      return mailServerPort;   
    }  
    public void setMailServerPort(String mailServerPort) {   
      this.mailServerPort = mailServerPort;   
    }  
    public boolean isValidate() {   
      return validate;   
    }  
    public void setValidate(boolean validate) {   
      this.validate = validate;   
    }  
    public String[] getAttachFileNames() {   
      return attachFileNames;   
    }  
    public void setAttachFileNames(String[] fileNames) {   
      this.attachFileNames = fileNames;   
    }  
    public String getFromAddress() {   
      return fromAddress;   
    }   
    public void setFromAddress(String fromAddress) {   
      this.fromAddress = fromAddress;   
    }  
    public String getPassword() {   
      return password;   
    }  
    public void setPassword(String password) {   
      this.password = password;   
    }  
    public String getToAddress() {   
      return toAddress;   
    }   
    public void setToAddress(String toAddress) {   
      this.toAddress = toAddress;   
    }   
    public String getUserName() {   
      return userName;   
    }  
    public void setUserName(String userName) {   
      this.userName = userName;   
    }  
    public String getSubject() {   
      return subject;   
    }  
    public void setSubject(String subject) {   
      this.subject = subject;   
    }  
    public String getContent() {   
      return content;   
    }  
    public void setContent(String textContent) {   
      this.content = textContent;   
    }   

}