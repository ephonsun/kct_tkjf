package com.framework.web.service;

import java.util.List;
import java.util.Map;

public interface SystemService {
	
	public List<?> getmoduleconfig(Map<String, Object> args) throws Exception;
	
	public int insertmoduleconfig(Map<String, Object> args) throws Exception;
	
	public int updatemoduleconfig(Map<String, Object> args) throws Exception;
	
	public List<?> gethongbaoconfig(Map<String, Object> args) throws Exception;
	
	public int sethongbaoconfig(Map<String, Object> args) throws Exception;
	
	public int sethongbao(Map<String, Object> args) throws Exception;
	
	public int updateUserHbao(Map<String, Object> args) throws Exception;
	
	
	
	
	
	public Map<String, ?> selectamount(Map<String, Object> args) throws Exception;

}
