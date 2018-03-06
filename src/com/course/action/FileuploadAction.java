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
	private File[] uploadImage; //得到上传的文件
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


	private String[] uploadImageContentType; //得到文件的类型
    private String[] uploadImageFileName; //得到文件的名称
    
    
    public String execute(){
        System.out.println("fileName:"+this.getUploadImageFileName());
        System.out.println("contentType:"+this.getUploadImageContentType());
        System.out.println("File:"+this.getUploadImage());
        for (int i = 0; i < uploadImage.length; i++){
        //获取要保存文件夹的物理路径(绝对路径)
	        String realPath=ServletActionContext.getServletContext().getRealPath("/file");
	        System.out.println("the realpath is"+realPath);

	        File file = new File(realPath);
	        
	        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
	        if(!file.exists())file.mkdirs();
	        
	        try {
	            //保存文件
	            FileUtils.copyFile(uploadImage[i], new File(file,uploadImageFileName[i]));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        return SUCCESS;
    }

   
}