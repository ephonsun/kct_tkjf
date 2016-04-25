package com.framework.web.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;



public class LongUrlToshortUrl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String a = null;
//		a = "qwre";
//		System.out.println(a);
//		a = "0";
//		System.out.println(a);
		//generateShortUrl("http://dwz.cn/create.php","http://www.tkjfw.net/internet_money/LoginController/redirect.do?param1=623ca911c63d40e99053f6&param2=3b70eb54cd2b5fe89155e9c5930074a099cc89bb39f1c9d90f714306d8e298bf9a43285ffbd87807");  
	}
	
	
	/** 
     * 生成端连接信息 
     *  
     * @author: Jerri  
     * @date: 2014年3月22日下午5:31:15 
     */  
    public static void  generateShortUrl(String str,String params) {  
    	String shorturl = null;
        try {  
        	URL url= new URL(str);
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        	 connection.setDoOutput(true);  
             connection.setDoInput(true);  
             connection.setUseCaches(false);  
             connection.setInstanceFollowRedirects(true);  
             connection.setRequestMethod("POST"); // 设置请求方式  
             connection.setRequestProperty("Accept", "application/json");//设置接收数据的格式  
             connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置发送数据的格式
             connection.connect();  
             DataOutputStream out = new DataOutputStream(
                     connection.getOutputStream()); // utf-8编码  
             String content = "url=" + URLEncoder.encode(params, "UTF-8");
             //out.append(params);  
             //JSONObject obj = new JSONObject();
             //obj.element("url", params);
             out.writeBytes(content);
             out.flush();  
             out.close();  
           //读取响应
             BufferedReader reader = new BufferedReader(new InputStreamReader(
                     connection.getInputStream()));
             String lines;
             StringBuffer sb = new StringBuffer("");
             while ((lines = reader.readLine()) != null) {
                 lines = new String(lines.getBytes(), "utf-8");
                 sb.append(lines);
             }
             System.out.println(sb);
             reader.close();
             // 断开连接
             connection.disconnect();
             JSONObject jsonObject = new JSONObject();
             JSONObject sbObject = jsonObject.fromObject(sb.toString());
             shorturl = sbObject.get("tinyurl").toString();
        } catch (Exception e) {  
            e.printStackTrace();  
            shorturl = "0";
        }
        
        System.out.println(shorturl);
          
    }  
	

}
