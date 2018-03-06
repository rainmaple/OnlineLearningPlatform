package com.learning.dao;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Iterator;  
import java.util.List;  
  
import javax.servlet.http.HttpServletRequest;  
  
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.log4j.Logger;


      
      
      
      
    public class UploadService {  
        private static final Logger logger = Logger.getLogger(UploadService.class);  
        private static String uploadPath = null;// �ϴ��ļ���Ŀ¼  
        private static String tempPath = null; // ��ʱ�ļ�Ŀ¼  
        private static File uploadFile = null;  
        private static File tempPathFile = null;  
        private static int sizeThreshold = 1024*2000;  
        private static int sizeMax = 50*1024*1024;  
        //��ʼ��  
        static{  
              tempPath = "/home/emacle/tmp";  
              tempPathFile = new File(tempPath);  
              if (!tempPathFile.exists()) {  
                  tempPathFile.mkdirs();  
              }  
    }  
        public static boolean upload(HttpServletRequest request){  
            logger.info("uploading a file");  
            boolean flag = true;  
            //������������Ƿ�Ϊmultipart�����ݡ�  
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
            //�����ǵĻ�  
            if(isMultipart){  
             /**Ϊ�����󴴽�һ��DiskFileItemFactory����ͨ��������������ִ�н��������еı���Ŀ��������һ��List�С�**/  
             try {  
              DiskFileItemFactory factory = new DiskFileItemFactory();  
              factory.setSizeThreshold(sizeThreshold); // ���û�������С��������4kb  
              factory.setRepository(tempPathFile);// ���û�����Ŀ¼  
              ServletFileUpload upload = new ServletFileUpload(factory);  
              upload.setHeaderEncoding("UTF-8");//����ļ���������  
              upload.setSizeMax(sizeMax);// ��������ļ��ߴ�  
              List<FileItem> items = upload.parseRequest(request);  
              Iterator<FileItem> itr = items.iterator();//���еı���  
              //�����ļ�  
              while (itr.hasNext()){  
                FileItem item = (FileItem) itr.next();//ѭ�����ÿ������  
                if (!item.isFormField()){//������ļ�����  
                  String name = item.getName();//����ļ��� ����·����  
                  if(name!=null){  
                      File fullFile=new File(item.getName());  
                      String nameA=item.getFieldName();  
                      uploadPath = "/home/services/webstatic/client/"+nameA;  
                      uploadFile = new File(uploadPath);  
                      if (!uploadFile.exists()) {  
                          uploadFile.mkdirs();  
                      }  
                      if(!fullFile.getName().equals("")){  
                          File savedFile=new File(uploadPath,fullFile.getName());  
                          item.write(savedFile);  
                          logger.info("upload a file for "+nameA+" successfully");  
                      }else{  
                          logger.info("no file upload for "+nameA);  
                      }  
                       
                     }  
                   }  
                 }  
                }catch(FileNotFoundException e){  
                      
                    logger.info("no file upload for ");  
                }  
                catch (FileUploadException e) {  
                 flag = false;  
                 logger.info("error occured when uploading a file ");  
                 e.printStackTrace();  
                }catch (Exception e) {  
                 flag = false;  
                 logger.info("error occured!! when uploading a file ");  
                 e.printStackTrace();  
                }  
               }else{  
                flag = false;  
                logger.info("the enctype must be multipart/form-data");  
                System.out.println("the enctype must be multipart/form-data");  
               }  
              
            return flag;  
            }  
      
         }  
          