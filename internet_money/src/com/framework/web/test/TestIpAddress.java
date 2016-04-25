package com.framework.web.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import net.sf.json.JSONObject;

public class TestIpAddress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String ip = getIp(request);
		//System.out.println("ip="+ip);
		String httpUrl = "http://apis.baidu.com/apistore/iplookupservice/iplookup";
		String httpArg = "ip=218.94.31.130";
		String jsonResult = request(httpUrl, httpArg);

		JSONObject jsonObject = new JSONObject();
		JSONObject jObject = jsonObject.fromObject(jsonResult);
		System.out.println(jObject.getString("errNum"));
		System.out.println(jObject.getString("errMsg"));
		System.out.println(jObject.getJSONObject("retData"));
		//System.out.println(jsonResult);
		JSONObject retData = jObject.getJSONObject("retData");
		System.out.println(retData.getString("ip"));
		System.out.println(retData.getString("country"));
		System.out.println(retData.getString("province"));
		System.out.println(retData.getString("city"));// 徐州,连云港,宿迁,淮安,盐城,扬州,泰州,南通,南京,镇江,苏州,无锡,常州
		System.out.println(retData.getString("district"));
		System.out.println(retData.getString("carrier"));

	}
	
	public static String getIp(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		return ip;
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
			connection.setRequestProperty("apikey", "f184f0f8252d72bcb157a8ff8dbd35c3");
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

	public static String format_city(String str) {
		String s2 = null;
		try {
			byte[] converttoBytes = str.getBytes("UTF-8");
			s2 = new String(converttoBytes, "UTF-8");
			System.out.println(s2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s2;
	}

}
