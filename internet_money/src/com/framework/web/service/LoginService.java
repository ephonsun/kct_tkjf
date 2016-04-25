package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.entity.SessionBean;
import com.framework.core.service.BaseService;

public interface LoginService extends BaseService {
	
	public SessionBean getSessionBean(String userId,String passwd) throws Exception;
	
	public List<?> selectuser(String mobile) throws Exception;
	
	public List<?> doSelect_sms(Map<String, Object> args) throws Exception;
	
	
	public Map<String, ?> selectCityCode(String city) throws Exception;
	
	public int updatePassword(Map<String, ?> args) throws Exception;
	
	public Map<String, ?> doSelect_sms(String mobile) throws Exception;
	
	public int update_smstime(Map<String, Object> args) throws Exception;
	
	public int insert_sms(Map<String, Object> args) throws Exception;
	
	public int updatehongbaotj(Map<String, Object> args) throws Exception;
	
	public int updatehongbaoljtj(Map<String, Object> args) throws Exception;
	
	public List<?> doSelect_username(Map<String, Object> args) throws Exception;
	
	public void printtxt(int id) throws Exception;
	
	public List<?> doSelect_company(Map<String, Object> args) throws Exception;
	
	public int updateLastTime(Map<String, Object> args) throws Exception;
	
	
	
	
	
	
}
