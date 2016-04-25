package com.framework.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class GlobalConfig {
	
	
	public static final String account = "JSM40152";//
	//密码
	public static final String password = "gkssayc0";//
	//校验码
	public static final String veryCode = "mrk5kv535ij6";
	
	public static final String address = "http://112.74.76.186:8030/service/httpService/httpInterface.do?method=sendMsg";
	
	public static String DATE_PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 发送模版短信(短信模版的创建参考客户端操作手册)
	 * 
	 *   模版：@1@会员，动态验证码@2@(五分钟内有效)。请注意保密，勿将验证码告知他人。
	 *   参数值:@1@=member,@2@=4293
	 *   最终短信内容：【短信签名】member会员，动态验证码4293(五分钟内有效)。请注意保密，勿将验证码告知他人。
	 * 
	 * @param mobile 手机号码
	 * @param tempId 模版编号，对应客户端模版编号
	 * @param content 各参数值，以英文逗号隔开，如：@1@=member,@2@=4293
	 * @return
	 * @throws Exception
	 */
	public static String sendTemplateSms(String mobile,String tempId,String content) throws Exception{
		//String address = HTTP_URL + "/service/httpService/httpInterface.do?method=sendMsg";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		params.append("&mobile=").append(mobile);
		params.append("&content=").append(content);
		params.append("&msgtype=").append("2");
		params.append("&tempid=").append(tempId);
		params.append("&code=").append("utf-8");
		
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
		out.write(params.toString()); //post的关键所在！
		out.flush();
		out.close();
		String temp = "";
		String result = "";
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		while (( temp = br.readLine()) != null) {
			result += temp;
		}
		//System.out.println(result);
		
		return result;
	}
	
	
	
	/**
	 *   
	* @Title: createUserName 
	* @Description: TODO(随机用户名) 
	* @param @param numberFlag
	* @param @param length
	* @param @return  参数说明 
	* @return String  返回类型 
	* @throws
	 */
	 public static String createUserName(){  
		String time = String.valueOf(System.currentTimeMillis()).substring(5, 13);
		String random = createRandom(true, 4);
		String username = random + time;
	  return username;  
	 } 
	
	
	
	
	
	
	
	/** 
	  * 创建指定数量的随机字符串 
	  * @param numberFlag 是否是数字 
	  * @param length 
	  * @return 
	  */  
	 public static String createRandom(boolean numberFlag, int length){  
	  String retStr = "";  
	  String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";  
	  int len = strTable.length();  
	  boolean bDone = true;  
	  do {  
	   retStr = "";  
	   int count = 0;  
	   for (int i = 0; i < length; i++) {  
	    double dblR = Math.random() * len;  
	    int intR = (int) Math.floor(dblR);  
	   char c = strTable.charAt(intR);  
	    if (('0' <= c) && (c <= '9')) {  
	     count++;  
	    }  
	    retStr += strTable.charAt(intR);  
	   }  
	   if (count >= 2) {  
	    bDone = false;  
	   }  
	  } while (bDone);  
	  //System.out.println(retStr);
	  return retStr;  
	 } 
	 
	 
	 public static String parseXML(String xml)  
	            throws JDOMException, IOException {  
	        /** *用于存放节点的信息** */  
	        Map<String, Object> map = new HashMap<String, Object>();  
	        /** *创建一个新的字符串*** */  
	        StringReader xmlReader = new StringReader(xml);  
	        /** **创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入 */  
	        InputSource xmlSource = new InputSource(xmlReader);  
	        /** *创建一个SAXBuilder* */  
	        SAXBuilder builder = new SAXBuilder();  
	        /** *通过输入源SAX构造一个Document** */  
	        org.jdom.Document doc = builder.build(xmlSource);  
	        /** *获得根节点** */  
	        org.jdom.Element elt = doc.getRootElement();  
	        /** *获得BODY节点** */  
	        org.jdom.Element mt = elt.getChild("mt");
	        org.jdom.Element status = mt.getChild("status");
	        String statusStr = status.getText();
	        return statusStr;
	    }  

}
