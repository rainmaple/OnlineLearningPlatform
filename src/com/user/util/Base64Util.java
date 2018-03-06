package com.user.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
 
 
    /**
     * �����������ݱ���ΪBASE64�ַ���
     * @param binaryData
     * @return
     */
    public static String encode(byte[] binaryData) {
        try {
            return new String(Base64.encodeBase64(binaryData), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
     
    /**
     * ��BASE64�ַ����ָ�Ϊ����������
     * @param base64String
     * @return
     */
    public static byte[] decode(String base64String) {
        try {
            return Base64.decodeBase64(base64String.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
   
 
}