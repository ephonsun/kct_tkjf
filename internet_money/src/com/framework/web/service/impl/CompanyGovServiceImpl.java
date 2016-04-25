package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.CompanyGovService;

@Service("companyGovService")
public class CompanyGovServiceImpl extends BaseServiceImpl implements CompanyGovService {

	@Override
	public int companyconsult(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_company_consult (category,company,industry,name,mobile,content,authority,time,title,user_id) ");
		sql.append(" values (:category,:company,:industry,:name,:mobile,:content,:authority,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	@Override
	public List<?> getconsultlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.category,t.title,t.count,e.avatar,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time FROM e_company_consult t,e_user e ");
		sql.append(" WHERE t.category = "+args.get("category")+" ");
		sql.append("   AND t.user_id = e.id ");
		sql.append(" ORDER BY t.time DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getconsultlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_company_consult t ");
		sql.append(" WHERE t.category = "+args.get("category")+" ");
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}
	
	@Override
	public List<?> consultdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id,t.count,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.category,t.company,t.industry,t.name,t.content memo,t.title,CONCAT(SUBSTR(t.mobile,1,3),'****',SUBSTR(t.mobile,8,4)) mobile FROM e_company_consult t where t.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int updateconsultCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_company_consult SET count=count+1 where id=:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int pubcompanyconsultlog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_company_consult_log (content,time,consult_id,user_id) ");
		sql.append(" values (:content,:time,:consult_id,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> getcompanyconsultlog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select  ");
		sql.append(" t.id, ");
		sql.append(" t.content, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.user_id userId, ");
		sql.append(" e.`name` user ");
		sql.append(" from e_company_consult_log t ,e_user e ");
		sql.append(" where t.consult_id =:consult_id ");
		sql.append("   and t.user_id = e.id ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString(),args);
	}
	
	

}
