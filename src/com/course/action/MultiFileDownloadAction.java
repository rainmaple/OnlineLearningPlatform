package com.course.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.data.util.ZipFileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class MultiFileDownloadAction extends ActionSupport {
	 
    private static final long serialVersionUID = 2743077909387361587L;
     
    //����JSPҳ�洫�ݹ����ĸ�����·��
    private String attachmentPath;
     
    //����ѹ�����zip�ļ���·�������ݸ�ͨ�õ�����Action
    private String fileName;
 
     
    /**
     * ���ض������
     * ʵ�֣����������ѹ����zip��,Ȼ��������zip��
     */
    public String downloadMultiFile() {
        //ʹ�õ�ǰʱ�������ļ�����
        String formatDate =new  SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        //ѹ�����zip�ļ����·��
        fileName = "D:/test/" + formatDate + ".zip";
         
        if(attachmentPath != null && !"".equals(attachmentPath)) {
            //�����������·��ȡ��
            String[] attachmentPathArray = attachmentPath.split(",");
            if(attachmentPathArray != null && attachmentPathArray.length >0) {
                File[] files = new File[attachmentPathArray.length];
                for(int i=0;i<attachmentPathArray.length;i++) {
                    if(attachmentPathArray[i] != null) {
                        File file = new File(attachmentPathArray[i].trim());
                        if(file.exists()) {
                            files[i] = file;
                        }
                    }
                }
                //���������ѹ����zip
                ZipFileUtil.compressFiles2Zip(files,fileName);
            }
             
        }
        return SUCCESS;
    }
     
     
    public String getAttachmentPath() {
        return attachmentPath;
    }
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
     
     
 
}