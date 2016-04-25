package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface MoneyApplyService extends BaseService {
	
	public int moneyApply_insert(Map<String, Object> args) throws Exception;
	
	public int applyinsurance(Map<String, Object> args) throws Exception;
	
	public int loanbid_insert(Map<String, Object> args) throws Exception;
	
	public int applyaccounting(Map<String, Object> args) throws Exception;
	
	public int applylicai(Map<String, Object> args) throws Exception;
	
	public List<?> selectUser(Map<String, Object> args) throws Exception;
	
	public List<?> selectMoneyApply(Map<String, Object> args) throws Exception;
	
	public List<?> selectMoneyApplyClass(Map<String, Object> args) throws Exception;

}
