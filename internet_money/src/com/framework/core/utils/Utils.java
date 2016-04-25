package com.framework.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.security.MessageDigest;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.druid.filter.config.ConfigTools;

public class Utils {

	public static String BlobToStr(Blob blob) {
		String result = null;
		try {
			ByteArrayInputStream msgContent = (ByteArrayInputStream) blob
					.getBinaryStream();
			byte[] byte_data = new byte[msgContent.available()];
			msgContent.read(byte_data, 0, byte_data.length);
			result = new String(byte_data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String ClobToStr(Clob clob) {
		Reader is = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			if (clob != null) {
				is = clob.getCharacterStream();
				br = new BufferedReader(is);
				String s = br.readLine();
				while (s != null) {
					sb.append(s);
					s = br.readLine();
				}
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String Md5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			//System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * ���ܽ����㷨 ִ��һ�μ��ܣ����ν���
	 */
	public static String convertMD5(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}
	
	public static String nullToEmpty(String str){
		if(str==null||"null".equals(str)){
			return "";
		}
		return str;
	}
	
	public static String notNull(String str, String strDefault)
	{
		if (str == null)
		{
			if (strDefault != null)
			{
				return strDefault;
			}
			else
			{
				return "";
			}
		}
		return str.trim();
	}
	
	public static String formatTime(){
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String time = simple.format(date);
		return time;
	}


    public static void main(String args[]) {  
    	ConfigTools cft = new ConfigTools(); 
    	try {
			//System.out.println(cft.encrypt("emkt1234"));
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
