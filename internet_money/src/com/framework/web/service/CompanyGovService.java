package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface CompanyGovService extends BaseService {
	
	public int companyconsult(Map<String, Object> args) throws Exception;
	
	public List<?> getconsultlist(Map<String, Object> args) throws Exception;
	
	public int getconsultlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> consultdetail(Map<String, Object> args) throws Exception;
	
	public int updateconsultCount(Map<String, Object> args) throws Exception;
	
	public int pubcompanyconsultlog(Map<String, Object> args) throws Exception;
	
	public List<?> getcompanyconsultlog(Map<String, Object> args) throws Exception;

}
