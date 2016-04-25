package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface MngMentorService extends BaseService {
	
	
	public List<?> selectmentorlist(Map<String, Object> args) throws Exception;
	
	public int pubmentor(Map<String, Object> args) throws Exception;
	
	public int selectmentorlistNum(Map<String, Object> args) throws Exception;
	
	public int mentor_update(Map<String, Object> args) throws Exception;
	
	public int mentor_updateZt(Map<String, Object> args) throws Exception;

}
