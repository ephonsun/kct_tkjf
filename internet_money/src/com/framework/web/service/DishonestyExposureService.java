package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface DishonestyExposureService extends BaseService {
	
public int getdishonestpersonNum(Map<String, Object> args) throws Exception;
	
	public List<?> getdishonestperson(Map<String, Object> args) throws Exception;
	
	public List<?> getdishonestcorporation(Map<String, Object> args) throws Exception;
	
    public int getdishonestcorporationNum(Map<String, Object> args) throws Exception;
	
	
	
	
	

}
