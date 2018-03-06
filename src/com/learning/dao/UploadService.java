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
        private static String uploadPath = null;// 上传文件的目录  
        private static String tempPath = null; // 临时文件目录  
        private static File uploadFile = null;  
        private static File tempPathFile = null;  
        private static int sizeThreshold = 1024*2000;  
        private static int sizeMax = 50*1024*1024;  
        //初始化  
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
            //检查输入请求是否为multipart表单数据。  
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
            //若果是的话  
            if(isMultipart){  
             /**为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。**/  
             try {  
              DiskFileItemFactory factory = new DiskFileItemFactory();  
              factory.setSizeThreshold(sizeThreshold); // 设置缓冲区大小，这里是4kb  
              factory.setRepository(tempPathFile);// 设置缓冲区目录  
              ServletFileUpload upload = new ServletFileUpload(factory);  
              upload.setHeaderEncoding("UTF-8");//解决文件乱码问题  
              upload.setSizeMax(sizeMax);// 设置最大文件尺寸  
              List<FileItem> items = upload.parseRequest(request);  
              Iterator<FileItem> itr = items.iterator();//所有的表单项  
              //保存文件  
              while (itr.hasNext()){  
                FileItem item = (FileItem) itr.next();//循环获得每个表单项  
                if (!item.isFormField()){//如果是文件类型  
                  String name = item.getName();//获得文件名 包括路径啊  
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
          