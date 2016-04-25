package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface AppService extends BaseService {
	
	public int moneyApply_insert(Map<String, Object> args) throws Exception;
	
	public List<?> selectUser(Map<String, Object> args) throws Exception;
	
	public int applylicai(Map<String, Object> args) throws Exception;
	
	public int pubdemand(Map<String, Object> args) throws Exception;
	
//	public List<?> getgrouppostlist(Map<String, Object> args) throws Exception;
//	
//	public Map<String,?> getgrouppostdetail(Map<String, Object> args) throws Exception;
//	
//	public int updateCount(Map<String, Object> args) throws Exception;

}
