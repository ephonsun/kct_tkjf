package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface MyTianYService extends BaseService {
	
	public int updateUser(Map<String, Object> args) throws Exception;
	
	public int updateusercompany(Map<String, Object> args) throws Exception;
	
	public List<?> getagentapplicationlist(Map<String, Object> args) throws Exception;
	
	public List<?> getagentapplicationdetail(Map<String, Object> args) throws Exception;
	
	public List<?> selectPerson(Map<String, Object> args) throws Exception;
	
	public List<?> getusercompany(Map<String, Object> args) throws Exception;
	
	public List<?> getusercompanylist(Map<String, Object> args) throws Exception;
	
	public List<?> doSelect_usercompany(Map<String, Object> args) throws Exception;
	
	public List<?> getloanapplicationlist(Map<String, Object> args) throws Exception;
	
	public List<?> getloanapplicationdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getpublist(Map<String, Object> args) throws Exception;
	
	public List<?> getpubdetail(Map<String, Object> args) throws Exception;
	
	public int updateMyApplication(Map<String, Object> args) throws Exception;
	
	public int deleteapplication(Map<String, Object> args) throws Exception;
	
	public List<?> getconsultlist(Map<String, Object> args) throws Exception;
	
	public List<?> getmymentorlist(Map<String, Object> args) throws Exception;
	
	public int deleteSelfTag_post(String id) throws Exception;
	
	public int deleteSkillTag_post(String id) throws Exception;
	
	public int getmymentorlistNum(Map<String, Object> args) throws Exception;
	
	public int updateWaiterDetail(Map<String, Object> args) throws Exception;
	
	public List<?> getmystartupconsultlist(Map<String, Object> args) throws Exception;
	
	public int getmystartupconsultlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getmystartupconsultdetail(Map<String, Object> args) throws Exception;
	
	public int updatemyStartupConsultCount(Map<String, Object> args) throws Exception;
	
	public List<?> selectusername(Map<String, Object> args) throws Exception;
	
	public List<?> gethongbao(Map<String, Object> args) throws Exception;
	
	public List<?> gethongbaolog(Map<String, Object> args) throws Exception;
	
	public List<?> getconsultdetail(Map<String, Object> args) throws Exception;
	
	public int getagentapplicationlistNum(Map<String, Object> args) throws Exception;
	
	public int getloanapplicationlistNum(Map<String, Object> args) throws Exception;
	
	public int getpublistNum(Map<String, Object> args) throws Exception;
	
	public int getconsultlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getinsurancelist(Map<String, Object> args) throws Exception;
	
	public int getinsurancelistNum(Map<String, Object> args) throws Exception;
	
	public int gethongbaologNum(Map<String, Object> args) throws Exception;
	
	public Map<String,?> getapplication(Map<String, Object> args) throws Exception;
	
	public Map<String, ?> selectUserMap(Map<String, Object> args) throws Exception;
	
	public void jdbcTest() throws Exception;
	
	public int addusercompany(Map<String, Object> args) throws Exception;
	
	public List<?> doSelect_addusercompany(Map<String, Object> args) throws Exception;

}
