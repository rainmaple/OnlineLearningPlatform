package com.data.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class ZipFileUtil {
    
    /**
     * ���ļ�ѹ����zip��ʽ
     * @param files         ��Ҫѹ�����ļ�
     * @param zipFilePath ѹ�����zip�ļ�·��   ,��"D:/test/aa.zip";
     */
    public static void compressFiles2Zip(File[] files,String zipFilePath) {
        if(files != null && files.length >0) {
            if(isEndsWithZip(zipFilePath)) {
                ZipArchiveOutputStream zaos = null;
                try {
                    File zipFile = new File(zipFilePath);
                    zaos = new ZipArchiveOutputStream(zipFile);
                    //Use Zip64 extensions for all entries where they are required
                    zaos.setUseZip64(Zip64Mode.AsNeeded);
                     
                    //��ÿ���ļ���ZipArchiveEntry��װ
                    //����ZipArchiveOutputStreamд��ѹ���ļ���
                    for(File file : files) {
                        if(file != null) {
                            ZipArchiveEntry zipArchiveEntry  = new ZipArchiveEntry(file,file.getName());
                            zaos.putArchiveEntry(zipArchiveEntry);
                            InputStream is = null;
                            try {
                                is = new BufferedInputStream(new FileInputStream(file));
                                byte[] buffer = new byte[1024 * 5]; 
                                int len = -1;
                                while((len = is.read(buffer)) != -1) {
                                    //�ѻ��������ֽ�д�뵽ZipArchiveEntry
                                    zaos.write(buffer, 0, len);
                                }
                                //Writes all necessary data for this entry.
                                zaos.closeArchiveEntry(); 
                            }catch(Exception e) {
                                throw new RuntimeException(e);
                            }finally {
                                if(is != null)
                                    is.close();
                            }
                             
                        }
                    }
                    zaos.finish();
                }catch(Exception e){
                    throw new RuntimeException(e);
                }finally {
                        try {
                            if(zaos != null) {
                                zaos.close();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                }
                 
            }
             
        }
         
    }
     
    /**
     * ��zip�ļ���ѹ��ָ�����ļ���
     * @param zipFilePath   zip�ļ�·��, �� "D:/test/aa.zip"
     * @param saveFileDir   ��ѹ����ļ����·��, ��"D:/test/"
     */
    public static void decompressZip(String zipFilePath,String saveFileDir) {
        if(isEndsWithZip(zipFilePath)) {
            File file = new File(zipFilePath);
            if(file.exists()) {
                InputStream is = null;
                //can read Zip archives
                ZipArchiveInputStream zais = null;
                try {
                    is = new FileInputStream(file);
                    zais = new ZipArchiveInputStream(is);
                    ArchiveEntry  archiveEntry = null;
                    //��zip���е�ÿ���ļ���ȡ����
                    //Ȼ����ļ�д��ָ�����ļ���
                    while((archiveEntry = zais.getNextEntry()) != null) {
                        //��ȡ�ļ���
                        String entryFileName = archiveEntry.getName();
                        //�����ѹ�������ļ����·��
                        String entryFilePath = saveFileDir + entryFileName;
                        byte[] content = new byte[(int) archiveEntry.getSize()];
                        zais.read(content);
                        OutputStream os = null;
                        try {
                            //�ѽ�ѹ�������ļ�д��ָ��·��
                            File entryFile = new File(entryFilePath);
                            os = new BufferedOutputStream(new FileOutputStream(entryFile));
                            os.write(content);
                        }catch(IOException e) {
                            throw new IOException(e);
                        }finally {
                            if(os != null) {
                                os.flush();
                                os.close();
                            }
                        }
                         
                    }
                }catch(Exception e) {
                    throw new RuntimeException(e);
                }finally {
                        try {
                            if(zais != null) {
                                zais.close();
                            }
                            if(is != null) {
                                is.close();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                }
            }
        }
    }
     
    /**
     * �ж��ļ����Ƿ���.zipΪ��׺
     * @param fileName        ��Ҫ�жϵ��ļ���
     * @return ��zip�ļ�����true,���򷵻�false
     */
    public static boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if(fileName != null && !"".equals(fileName.trim())) {
            if(fileName.endsWith(".ZIP")||fileName.endsWith(".zip")){
                flag = true;
            }
        }
        return flag;
    }
     
}