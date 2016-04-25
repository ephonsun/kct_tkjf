package com.framework.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/**日期格式转换**/
	public static String dateTimeFormat(String dateTime,String pattern) {
		try {
			if("".equals(dateTime)){
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(sdf.parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateTimeFormatToNull(String dateTime) {
		if("".equals(dateTime)){
			return null;
		}
		return dateTime;
	}
	
    public static Date stringToDate(String str) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        date = java.sql.Date.valueOf(str);  
        return date;  
    }  
    
	/**
	 * 获取指定格式的当前日期 参数：pattern，日期格式，如:yyyyMMddhhmmss 格式描述符的含义参见JDK simpleDateFormat类
	 */
	public static String getCurrentDate(String pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("input string parameter is null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date now = new Date();
		return sdf.format(now);
	}
}
