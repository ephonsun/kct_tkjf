package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;


public interface WaiterCompanyService extends BaseService {
	
	public List<?> getwaiterlist(Map<String, Object> args) throws Exception;
	
	public int pubfactory(Map<String, Object> args) throws Exception;
	
	public int pubequipment(Map<String, Object> args) throws Exception;
	
	public int publogistics(Map<String, Object> args) throws Exception;
	
	public int pubjob(Map<String, Object> args) throws Exception;
	
	public int pubactivity(Map<String, Object> args) throws Exception;
	
	public int pubcooperation(Map<String, Object> args) throws Exception;
	
	public int pubtraining(Map<String, Object> args) throws Exception;
	
	public int insertApply(Map<String, Object> args) throws Exception;
	
	public int pubaccounting(Map<String, Object> args) throws Exception;
	
	public int pubregistration(Map<String, Object> args) throws Exception;
	
	public int pubstockrights(Map<String, Object> args) throws Exception;
	
	public int pubcapital(Map<String, Object> args) throws Exception;
	
	public int pubbill(Map<String, Object> args) throws Exception;
	
	public int pubnameregistration(Map<String, Object> args) throws Exception;
	
	public int applyregistration(Map<String, Object> args) throws Exception;
	
	public int applymanagement(Map<String, Object> args) throws Exception;
	
	public int applylegalaffairs(Map<String, Object> args) throws Exception;
	
	public int applyfinancialmanage(Map<String, Object> args) throws Exception;
	
	public int applyequityassessment(Map<String, Object> args) throws Exception;  
	
	public int applyhr(Map<String, Object> args) throws Exception;
	
	public int applypatent(Map<String, Object> args) throws Exception;
	
	public List<?> pubfactorydetail(Map<String, Object> args) throws Exception;
	
	public List<?> getmanagementcompanydetail(Map<String, Object> args) throws Exception;
	
	public List<?> viewmobile(Map<String, Object> args) throws Exception;
	
	public int updateviewmobileLog(Map<String, Object> args) throws Exception;
	
	public List<?> getduangongselftags(Map<String, Object> args) throws Exception;
	
	public List<?> getduangongskilltags(Map<String, Object> args) throws Exception;
	
	public List<?> getduangongapplicationlist(Map<String, Object> args) throws Exception;
	
	public List<?> selectselfBiaoQian(String selftags) throws Exception;
	
	public List<?> selectSkillBiaoQian(String skillTags) throws Exception;
	
	public int insertSelfTag(String selftags) throws Exception;
	
	public int insertSkillTag(String skillTags) throws Exception;
	
	public int updateJianLiCount(Map<String, Object> args) throws Exception;
	
	public int insertSelfTag_post(String id,String tagid) throws Exception;
	
	public int insertSkillTag_post(String id,String tagid) throws Exception;
	
	public Map<String,?> selectJianLiId(Map<String, Object> args) throws Exception;
	
	public Map<String,?> getduangongapplicationdetail(Map<String, Object> args) throws Exception;
	
	public Map<String,?> selectSelfTagId(String selftags) throws Exception;
	
	public Map<String,?> selectSkillTagId(String skillTags) throws Exception;
	
	public int updateCount(Map<String, Object> args) throws Exception;
	
	public int pubduangongapplication(Map<String, Object> args) throws Exception;

	public int getwaiterlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getselfTags(Map<String, Object> args) throws Exception;
	
	public List<?> getskillTags(Map<String, Object> args) throws Exception;
	
	public int updateApply(Map<String, Object> args) throws Exception;
	
	public List<?> getduangongtags(Map<String, Object> args) throws Exception;
	
	public List<?> getduangonglist(Map<String, Object> args) throws Exception;
	
	public List<?> selectjianli(Map<String, Object> args) throws Exception;
	
	public int getduangonglistNum(Map<String, Object> args) throws Exception;
	
	public int updateduangongCount(Map<String, Object> args) throws Exception;
	
	public Map<String,?> getduangongdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getduangongNum(Map<String, Object> args) throws Exception;
	
	public List<?> selectPub(Map<String, Object> args) throws Exception;
	
	public int applicationduangong(Map<String, Object> args) throws Exception;
	
	public List<?> getduangongdetail1(Map<String, Object> args) throws Exception;
	
	public int pubduangong(Map<String, Object> args) throws Exception;
	
	public Map<String,?> selectTieZiId(Map<String, Object> args) throws Exception;
	
	public List<?> selectBiaoQian(String tags) throws Exception;
	
	public int insertTag(String tags) throws Exception;
	
	public Map<String,?> selectTagId(String tags) throws Exception;
	
	public int insertTag_post(String id,String tagid) throws Exception;
	
	public int companyconsult(Map<String, Object> args) throws Exception;
	
	public List<?> getmanagementcompanylist(Map<String, Object> args) throws Exception;
	
	public int getmanagementcompanylistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getlegalaffairslist(Map<String, Object> args) throws Exception;
	
	public int getlegalaffairslistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getlegalaffairsdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getfinancialmanagelist(Map<String, Object> args) throws Exception;
	
	public int getfinancialmanagelistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getfinancialmanagedetail(Map<String, Object> args) throws Exception;
	
	public List<?> getequityassessmentlist(Map<String, Object> args) throws Exception;
	
	public int getequityassessmentlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getequityassessmentdetail(Map<String, Object> args) throws Exception;
	
	public List<?> getpatentlist(Map<String, Object> args) throws Exception;
	
	public int getpatentlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> getpatentdetail(Map<String, Object> args) throws Exception;
	
	public List<?> gethrlist(Map<String, Object> args) throws Exception;
	
	public int gethrlistNum(Map<String, Object> args) throws Exception;
	
	public List<?> gethrdetail(Map<String, Object> args) throws Exception;
	
	

}
