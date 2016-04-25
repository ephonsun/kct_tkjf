package com.framework.core.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.framework.core.entity.LogInfo;
import com.framework.core.entity.SessionBean;
import com.framework.core.utils.GlobalConst;

public abstract class BaseController {
	
	/**获取session信息**/
	public SessionBean getSessionBean() {
		return (SessionBean) getRequest().getSession().getAttribute(GlobalConst.SESSION_KEY);
    }
	/**获取当前操作人**/
	public String getUserId() {
		if(getSessionBean()!=null){
			return getSessionBean().getId();
		}
		return null;
	}
	/**获取当前操作人用户名**/
	public String getUserName() {
		if(getSessionBean()!=null){
			return getSessionBean().getUserName();
		}
		return null;
	}
	
	/**获取当前操作人姓名**/
	public String getName() {
		if(getSessionBean()!=null){
			return getSessionBean().getName();
		}
		return null;
	}
	
	/**获取当前操作人性别**/
	public String getGender() {
		if(getSessionBean()!=null){
			return getSessionBean().getGender();
		}
		return null;
	}
	
	
	/**获取当前操作人邮箱**/
	public String getEmail() {
		if(getSessionBean()!=null){
			return getSessionBean().getEmail();
		}
		return null;
	}
	
	/**获取当前操作人电话**/
	public String getMobile() {
		if(getSessionBean()!=null){
			return getSessionBean().getMobile();
		}
		return null;
	}
	
	/**获取当前操作人身份证**/
	public String getIdentification() {
		if(getSessionBean()!=null){
			return getSessionBean().getIdentification();
		}
		return null;
	}
	
	/**获取当前操作人公司**/
	public String getCompany() {
		if(getSessionBean()!=null){
			return getSessionBean().getCompany();
		}
		return null;
	}
	
	/**获取当前操作人住址**/
	public String getAddress() {
		if(getSessionBean()!=null){
			return getSessionBean().getAddress();
		}
		return null;
	}
	
	/**获取当前操作人状态**/
	public String getState() {
		if(getSessionBean()!=null){
			return getSessionBean().getState();
		}
		return null;
	}
	
	/**获取当前操作人类型**/
	public String getType() {
		if(getSessionBean()!=null){
			return getSessionBean().getType();
		}
		return null;
	}
	
	/**获取当前操作人权限**/
	public String getPrivilege() {
		if(getSessionBean()!=null){
			return getSessionBean().getPrivilege();
		}
		return null;
	}
	
	/**获取当前操作人创建时间**/
	public String getCreateTime() {
		if(getSessionBean()!=null){
			return getSessionBean().getCreateTime();
		}
		return null;
	}
	
	/**获取当前操作人上次登录时间**/
	public String getLastTime() {
		if(getSessionBean()!=null){
			return getSessionBean().getLastTime();
		}
		return null;
	}
	
	/**获取当前操作人上次登录IP**/
	public String getLastIp() {
		if(getSessionBean()!=null){
			return getSessionBean().getLastIp();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**日志**/
	public LogInfo getLogInfo() throws Exception {
        try {
        	LogInfo info = new LogInfo();
            info.setOperId(this.getUserId());
            info.setContentId(this.getRequest().getParameter("contentId"));
            return info;
        } catch (Exception e) {
            throw e;
        }
    }
	/**获取request**/
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**生成ID**/
	public String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
