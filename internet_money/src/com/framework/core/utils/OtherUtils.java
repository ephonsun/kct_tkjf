package com.framework.core.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;


public class OtherUtils {
	
	private static Logger logger = LoggerFactory.getLogger(OtherUtils.class);
	
	
	
	/**
	 * 
	* @Title: JsonParseIp 
	* @Description: TODO(解析用户IP地址) 
	* @param @param IpAddress
	* @param @return  ip地址 
	* @return HashMap<String,Object>  返回类型 
	* @throws
	 */
	public static HashMap<String, Object> JsonParseIp(String IpAddress) {
		HashMap<String, Object> map = new HashMap<>();
		String jsonResult = request(Constant.httpUrl, IpAddress);
		JSONObject jsonObject = new JSONObject();
		JSONObject jObject = jsonObject.fromObject(jsonResult);//转换为 JSONObject 对象
		JSONObject retData = jObject.getJSONObject("retData");
		map.put("errNum", jObject.getString("errNum"));
		if("0".equals(jObject.getString("errNum"))){
			map.put("errMsg", jObject.getString("errMsg"));
			map.put("ip", retData.getString("ip"));
			map.put("country", retData.getString("country"));
			map.put("province", retData.getString("province"));
			map.put("city", retData.getString("city"));
			map.put("district", retData.getString("district"));
			map.put("carrier", retData.getString("carrier"));
		}
		return map;
	}
	
	
	
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */  
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>1){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }
            }  
            return "ip="+ipAddress;   
    }  
	
	
	
	
	
	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey", Constant.apikey);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	* @Title: format_city 
	* @Description: TODO(Unicode 转化为UTF-8 格式) 
	* @param @param str
	* @param @return  参数说明 
	* @return String  返回类型 
	* @throws
	 */
	public static String format_Unicode(String str) {
		String utf8 = null;
		try {
			byte[] converttoBytes = str.getBytes("UTF-8");
			utf8 = new String(converttoBytes, "UTF-8");
			//System.out.println(utf8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utf8;
	}
	
	
	
	
	/**
	 * 
	* @Title: generateShortUrl 
	* @Description: TODO(生成百度短地址) 
	* @param @param params
	* @param @return  参数说明    长地址
	* @return String  返回类型 
	* @throws
	 */
    public static String getShortUrl(String params) {  
    	String shorturl = null;
        try {  
        	URL url= new URL(Constant.baiduURL);
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
             //System.out.println(sb);
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
        return shorturl;
    }  
	
	
	
	
	
	public static String format_limit(String page,int num) {
		String a = (Integer.valueOf(page)-1)*num+"";
		return a;
	}
	
	public static int format_Number(String str) {
		int num = Integer.valueOf(str);
		return num;
	}
	
	public static long format_date(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(str+" 00:00:00");
		} catch (Exception e) {
			logger.error("日期转换错误 OtherUtils", e);
		}
		return date.getTime();
	}
	
	public static String format_null(String str) {
		if(str == null){
			return "";
		}else{
			return str;
		}
		
	}
	
	public static String format_void(String str) {
		if(str == ""){
			return null;
		}else{
			return str;
		}
		
	}
	
	public static Object EmptToNull(Object object) {
		if("".equals(object) || object == null){
			return null;
		}else{
			return object;
		}
	}
	
	

}
