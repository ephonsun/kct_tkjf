package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface VentureHouseService extends BaseService {
	
	public List<?> getmentortags(Map<String, Object> args) throws Exception;
	
	public List<?> getmentorlist(Map<String, Object> args) throws Exception;
	
	public int getmentorlistNum(Map<String, Object> args) throws Exception;
	
	public Map<String, ?> getmentordetail(Map<String, Object> args) throws Exception;
	
	public List<?> selectMentorTags(Map<String, Object> args) throws Exception;
	
	public int updateTeacherCount(Map<String, Object> args) throws Exception;
	
	public int startupconsult(Map<String, Object> args) throws Exception;
	
	public List<?> getstartupconsultlist(Map<String, Object> args) throws Exception;
	
	public int getstartupconsultlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getstartupconsultdetail(Map<String, Object> args) throws Exception;
	
	public int updateStartupConsultCount(Map<String, Object> args) throws Exception;
	
	public List<?> getstartupconsultlog(Map<String, Object> args) throws Exception;
	
	public int getstartupconsultlogNum(Map<String, Object> args) throws Exception;
	
	public int choosementor(Map<String, Object> args) throws Exception;
	
	public List<?> selectchoosementor(Map<String, Object> args) throws Exception;
	
	public int updateChooseTeacherNum(Map<String, Object> args) throws Exception;
	
	public int evaluatementor(Map<String, Object> args) throws Exception;
	
	public List<?> getmentorcomment(Map<String, Object> args) throws Exception;
	
	public int getmentorcommentNum(Map<String, Object> args) throws Exception;
	
	public List<?> getIndustrialbaselist(Map<String, Object> args) throws Exception;
	
	public int getIndustrialbaselistNum(Map<String, Object> args) throws Exception;
	
	public int updateStartUpConsultLog(Map<String, Object> args) throws Exception;
	
	public Map<String,?> getIndustrialbasedetail(Map<String, Object> args) throws Exception;
	
	public Map<String,?> getIndustrialbasedetail1(Map<String, Object> args) throws Exception;
	
	public int updateIndustrialbaseCount(Map<String, Object> args) throws Exception;
	

}
