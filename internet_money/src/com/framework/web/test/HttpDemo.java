package com.framework.web.test;

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

/**
 * Http Demo for Java
 * 参考文档《短信综合信息管理系统_Http接口文档.doc》
 *
 */
public class HttpDemo {
	
/*	//帐号
	static String account = "JSM40001";//JSM40013
	//密码
	static String password = "123456";//40013
	//校验码
	static String veryCode = "111222";*/
	
	static String account = "JSM40152";//
	//密码
	static String password = "gkssayc0";//
	//校验码
	static String veryCode = "mrk5kv535ij6";
	
	final static String HTTP_URL = "http://112.74.76.186:8030";
	
//	static String SERVERIP = "http://202.102.89.226:8030";
	
	
	/**
	 * 发送普通短信
	 * @param mobile 手机号码, 多个号码以英文逗号隔开,最多支持100个号码
	 * @param content 短信内容 
	 * @return 
	 * @throws Exception
	 */
	public static String sendTextSms(String mobile,String content) throws Exception{
		
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=sendMsg";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		params.append("&mobile=").append(mobile);
		params.append("&content=").append(content);
		params.append("&msgtype=").append("1");
		params.append("&code=").append("utf-8");
//		params.append("&sendtime=").append("20151001113000"); //发送定时短信
		
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
		return result;
	}
	
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
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=sendMsg";
		
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
		System.out.println(result);
		
		return result;
	}

	/**
	 * 获取短信状态报告
	 * @return 返回数据格式：
	 * <?xml version="1.0" encoding="UTF-8"?>
		<sms>
			<rpt>
				<mobile>15951977097</mobile> 
				<msgid>7799292837969854465</msgid>
				<status>MA:0006</status>
				<time>2015-06-12 15:10:53</time>
				<extno/>
			</rpt>
			<rpt>
				<mobile>15951977097</mobile>
				<msgid>7799388495481470979</msgid>
				<status>MA:0006</status>
				<time>2015-06-12 15:16:17</time>
				<extno/>
			</rpt>
		</sms>
	 * @throws Exception
	 */
	public static String queryReport() throws Exception{
		
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=queryReport";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		
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
		return result;
	}
	
	
	public static String queryMo() throws Exception{
		
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=queryMo";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		
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
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String result = "";
		//发送普通短信
//		result = sendTextSms("15951977097", "您的验证码为：3123，请勿向任何人提供短信验证码");
		
		//发送模版短信
		result = sendTemplateSms("10000000000", "JSM40152-0001", "@1@=321456,@2@=尊敬的天元财富网您好");
		
		//获取短信状态报告
		//result = queryReport();
		
		//获取上行短信
		//result = queryMo();
		
		System.out.println(result);
		String status = parseXML(result);
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
