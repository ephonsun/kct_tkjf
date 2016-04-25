package com.framework.core.entity;

public class WebResult implements java.io.Serializable {
	
	/**
     * 返回值：0成功；1失败
     */
    public int resultCode;
    /**
     * 返回消息
     */
    public String resultMsg;
    /**
     * 返回数据
     */
    public Object resultData;
    
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Object getResultData() {
		return resultData;
	}
	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
    


}
