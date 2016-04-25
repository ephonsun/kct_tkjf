package com.framework.core.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LogInfo implements Serializable{
	
	private String operId = "";//����Ա
	private String logRemark = "";//��ע
	private String contentId = "";//���ܱ���
	private String remoteAddr = "";//Զ�̷��ʵ�ַ
	private String remoteHost = "";//Զ�̷��ʶ˿�
	private String serviceAddr = "";//������ַ
	private int serviceHost = 0;//�����˿�
	private String browser = "";//�������Ϣ
	private String logType = "";//�������� //0:����1����ѯ 2������ 3������ 4:ɾ�� 5�����6���޸�9���ǳ� 
	
	
	public String getServiceAddr() {
		return serviceAddr;
	}
	public void setServiceAddr(String serviceAddr) {
		this.serviceAddr = serviceAddr;
	}
	public int getServiceHost() {
		return serviceHost;
	}
	public void setServiceHost(int serviceHost) {
		this.serviceHost = serviceHost;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getLogRemark() {
		return logRemark;
	}
	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	
	  
	
}
