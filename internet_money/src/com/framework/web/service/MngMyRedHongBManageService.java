package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface MngMyRedHongBManageService extends BaseService {
	
	public List<?> selectuser(Map<String, Object> args) throws Exception;
	
	public List<?> gethongbao(Map<String, Object> args) throws Exception;
	
	public int hongbao_xf(Map<String, Object> args) throws Exception;
	
	public List<?> hongbao_yue(Map<String, Object> args) throws Exception;
	
	public int hongbao_yueNum(Map<String, Object> args) throws Exception;
	
	public int hongbao_ff(Map<String, Object> args) throws Exception;
	
	public List<?> searchMobile(Map<String, Object> args) throws Exception;
	
	public int searchMobileNum(Map<String, Object> args) throws Exception;
	
	public List<?> searchYe(Map<String, Object> args) throws Exception;
	
	public int searchYeNum(Map<String, Object> args) throws Exception;
	
	public Map<String,?> gethongbaoMng(Map<String, Object> args) throws Exception;
	
	public Map<String,?> gethongbaoMap(Map<String, Object> args) throws Exception;
	
	public int updatehongbao(Map<String, Object> args) throws Exception;
	
	public int updatehongbaoZero(Map<String, Object> args) throws Exception;
	
	public int insertXkkfHongBaoLogTj(Map<String, Object> args) throws Exception;
	

}
