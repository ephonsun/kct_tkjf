package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface CircleService extends BaseService {
	
	public List<?> gethottags(Map<String, Object> args) throws Exception;

	public List<?> getgrouppostlist(Map<String, Object> args) throws Exception;
	
	public Map<String,?> getgrouppostdetail(Map<String, Object> args) throws Exception;
	
	public Map<String,?> selectTagId(String biaoqian) throws Exception;
	
	public List<?> getgrouppostdetail1(Map<String, Object> args) throws Exception;
	
	public List<?> getgrouppostcomment(Map<String, Object> args) throws Exception;
	
	public int getgrouppostdetailNum(Map<String, Object> args) throws Exception;
	
	public int updateCount(Map<String, Object> args) throws Exception;
	
	public int getgrouppostcommentNum(Map<String, Object> args) throws Exception;
	
	public int pubgrouppost(Map<String, Object> args) throws Exception;
	
	public List<?> selectBiaoQian(String biaoqian) throws Exception;
	
	public int insertTag(String biaoqian) throws Exception;
	
	public int insertTag_post(String id,String tagid) throws Exception;
	
	public Map<String,?> selectTieZiId(Map<String, Object> args) throws Exception;
	
	public int pubgroupcomment(Map<String, Object> args) throws Exception;
	
	public int updateTime(Map<String, Object> args) throws Exception;
	
	public int updateComment(Map<String, Object> args) throws Exception;
	
	public List<?> getMnggrouppostlist(Map<String, Object> args) throws Exception;
	
	public int getMnggrouppostlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> MngTitlelist(Map<String, Object> args) throws Exception;
	
	public int MngTitlelistNum(Map<String, Object> args) throws Exception;
	
	

}
