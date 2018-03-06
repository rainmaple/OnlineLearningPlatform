package com.course.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
 
import javax.servlet.http.HttpServletResponse;
 
import org.apache.struts2.ServletActionContext;
 
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	 
	 
    private static final long serialVersionUID = -3036349171314867490L;
     
    //�ļ���
    private String fileName;
     
    public String getFileName() {
        return fileName;
    }
 
    public void setFileName(String fileName) throws UnsupportedEncodingException {
        //��UTF-8���±����ļ���,�����������
        this.fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
    }
     
    public InputStream getInputStream() throws UnsupportedEncodingException, FileNotFoundException{
        HttpServletResponse response = ServletActionContext.getResponse();
        //attachment,�Ը����ķ�ʽ�����ļ�,��򿪱����ļ��Ի���;inline,�������ķ�ʽ����,�������ֱ�Ӵ��ļ�
        //response.setHeader("Content-Disposition", "attachment;fileName="
        //         + java.net.URLEncoder.encode(fileName,"UTF-8"));//java.net.URLEncoder.encode(fileName,"UTF-8")  ����ת�����������
          
        //���fileName�����·��
        //return ServletActionContext.getServletContext().getResourceAsStream(fileName);
        //���fileName�Ǿ���·��
        return new BufferedInputStream(new FileInputStream(fileName));
    }
     
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
     
     
 
}