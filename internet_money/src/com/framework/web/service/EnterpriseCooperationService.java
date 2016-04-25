package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface EnterpriseCooperationService extends BaseService {
	
	public List<?> search(Map<String, Object> args) throws Exception;
	
	public int searchNum(Map<String, Object> args) throws Exception;
	
	public List<?> gettechlist(Map<String, Object> args) throws Exception;
	
	public int gettechlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> pubtechdetail(Map<String, Object> args) throws Exception;
	
	public int updateCount(Map<String, Object> args) throws Exception;
	
	public int pubtech(Map<String, Object> args) throws Exception;
	
	public List<?> getmateriallist(Map<String, Object> args) throws Exception;
	
	public int getmateriallistNum(Map<String, Object> args) throws Exception;
	
	public int pubmaterial(Map<String, Object> args) throws Exception;
	
	public List<?> pubmaterialdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getcooperationlist(Map<String, Object> args) throws Exception;
	
	public int getcooperationlistNum(Map<String, Object> args) throws Exception;
	
	public int pubcooperation(Map<String, Object> args) throws Exception;
	
	public List<?> pubcooperationdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getfinancelist(Map<String, Object> args) throws Exception;
	
	public int getfinancelistNum(Map<String, Object> args) throws Exception;
	
	public int pubfinance(Map<String, Object> args) throws Exception;
	
	public List<?> pubfinancedetail(Map<String, Object> args) throws Exception;
	
	
	
	
	
	
	
	
	
	

}
