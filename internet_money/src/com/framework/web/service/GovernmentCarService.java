package com.framework.web.service;

import java.util.List;
import java.util.Map;
import com.framework.core.service.BaseService;



public interface GovernmentCarService extends BaseService {
	
	
	public int doSave(Map<String, Object> args) throws Exception;
	
	public int doDelete(Map<String, Object> args) throws Exception;
	
	public int doUpdate(Map<String, Object> args) throws Exception;
	
	public List<?> doSelect() throws Exception;
	
	public int[] doBaechInsert(List list) throws Exception;
	
	public int insertHongBaoLog(Map<String, Object> args) throws Exception;
	
	public int registerCompany(Map<String, Object> args) throws Exception;
	
	public List<?> doSelect_user(String mobile) throws Exception;
	
	public List<?> doSelect_usersms(Map<String, Object> args) throws Exception;
	
	public Map<String, ?> doSelect_userid(String mobiletj) throws Exception;
	
	public Map<String, ?> doSelect_useridtwo(String mobiletj) throws Exception;
	
	public Map<String, ?> selectUserId(Map<String, Object> args) throws Exception;
	
	public List<?> getsms(String mobile) throws Exception;
	
	public int register(Map<String, Object> args) throws Exception;
	
	public List<?> getgovnews(Map<String, Object> args) throws Exception;
	
	public List<?> getgovnewsdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getgovagent(Map<String, Object> args) throws Exception;
	
	public List<?> getgovagentdetail(Map<String, Object> args) throws Exception;
	
	public int applygovagent(Map<String, Object> args) throws Exception;
	
	public int updatercfwnewsCount(Map<String, Object> args) throws Exception;
	
	public int getgovagentNum(Map<String, Object> args) throws Exception;
	
	public int getgovnewsNum(Map<String, Object> args) throws Exception;
	
	public List<?> getparknews(Map<String, Object> args) throws Exception;
	
	public int getparknewsNum(Map<String, Object> args) throws Exception;
	
	public List<?> getrcfwnews(Map<String, Object> args) throws Exception;
	
	public int getrcfwnewsNum(Map<String, Object> args) throws Exception;
	
	public List<?> getrcfwpolicy(Map<String, Object> args) throws Exception;
	
	public int getrcfwpolicyNum(Map<String, Object> args) throws Exception;
	
	public List<?> getrcfwnewsdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getparknewsdetail(Map<String, Object> args) throws Exception;
	
	public int insertHongBaoLogTj(Map<String, Object> args) throws Exception;
	
	
	
	
	

}
