package com.course.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileuploadAction extends ActionSupport {

    /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private File[] uploadImage; //�õ��ϴ����ļ�
    public File[] getUploadImage() {
		return uploadImage;
	}


	public void setUploadImage(File[] uploadImage) {
		this.uploadImage = uploadImage;
	}


	public String[] getUploadImageContentType() {
		return uploadImageContentType;
	}


	public void setUploadImageContentType(String[] uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}


	public String[] getUploadImageFileName() {
		return uploadImageFileName;
	}


	public void setUploadImageFileName(String[] uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}


	private String[] uploadImageContentType; //�õ��ļ�������
    private String[] uploadImageFileName; //�õ��ļ�������
    
    
    public String execute(){
        System.out.println("fileName:"+this.getUploadImageFileName());
        System.out.println("contentType:"+this.getUploadImageContentType());
        System.out.println("File:"+this.getUploadImage());
        for (int i = 0; i < uploadImage.length; i++){
        //��ȡҪ�����ļ��е�����·��(����·��)
	        String realPath=ServletActionContext.getServletContext().getRealPath("/file");
	        System.out.println("the realpath is"+realPath);

	        File file = new File(realPath);
	        
	        //���Դ˳���·������ʾ���ļ���Ŀ¼�Ƿ���ڡ��������ڣ������˳���·����ָ����Ŀ¼���������б��赫�����ڵĸ�Ŀ¼��
	        if(!file.exists())file.mkdirs();
	        
	        try {
	            //�����ļ�
	            FileUtils.copyFile(uploadImage[i], new File(file,uploadImageFileName[i]));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        return SUCCESS;
    }

   
}