package com.user.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public final class GetUTCTimeUtil {  
    
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;  
      
    /** 
     * �õ�UTCʱ�䣬����Ϊ�ַ�������ʽΪ"yyyy-MM-dd HH:mm"<br /> 
     * �����ȡʧ�ܣ�����null 
     * @return 
     */  
    public static String getUTCTimeStr() {  
        StringBuffer UTCTimeBuffer = new StringBuffer();  
        // 1��ȡ�ñ���ʱ�䣺  
        Calendar cal = Calendar.getInstance() ;  
        // 2��ȡ��ʱ��ƫ������  
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
        // 3��ȡ������ʱ�  
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
        // 4���ӱ���ʱ����۳���Щ������������ȡ��UTCʱ�䣺  
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));  
        int year = cal.get(Calendar.YEAR);  
        int month = cal.get(Calendar.MONTH)+1;  
        int day = cal.get(Calendar.DAY_OF_MONTH);  
        int hour = cal.get(Calendar.HOUR_OF_DAY);  
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day).append("T") ;  
        UTCTimeBuffer.append("").append(hour).append(":").append(minute).append(":").append(second).append("Z") ;  
        try{  
            //format.parse(UTCTimeBuffer.toString()) ;  
            return UTCTimeBuffer.toString() ;  
        }catch(Exception e)  
        {  
            e.printStackTrace() ;  
        }  
        return null ;  
    }  
      
    /** 
     * ��UTCʱ��ת��Ϊ������ʱ�� 
     * @param UTCTime 
     * @return 
     */  
    public static String getLocalTimeFromUTC(String UTCTime){  
        java.util.Date UTCDate = null ;  
        String localTimeStr = null ;  
        try {  
            UTCDate = format.parse(UTCTime);  
            format.setTimeZone(TimeZone.getTimeZone("GMT-8")) ;  
            localTimeStr = format.format(UTCDate) ;  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
           
        return localTimeStr ;  
    }  
      
    /*public static void main(String[] args) {   
        String UTCTimeStr = getUTCTimeStr() ;  
        System.out.println(UTCTimeStr);   
        System.out.println(getLocalTimeFromUTC(UTCTimeStr));  
    } */ 
  
}  
