package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.SystemService;

@Service("systemService")
public class SystemServiceImpl extends BaseServiceImpl implements SystemService {

	@Override
	public List<?> getmoduleconfig(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_module_config t ");
		sql.append(" WHERE t.father_id = "+args.get("father_id")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int insertmoduleconfig(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_module_config (father_id,name) ");
		sql.append(" values (:father_id,:name) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	@Override
	public int updatemoduleconfig(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_module_config SET name=:name where id="+args.get("id")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> gethongbaoconfig(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(" t.id,  ");
		sql.append(" t.type, ");
		sql.append(" t.`name`, ");
		sql.append(" t.beginTime, ");
		sql.append(" FROM_UNIXTIME(t.endTime/1000,'%Y-%m-%d %H:%i:%S') endTime, ");
		sql.append(" FROM_UNIXTIME(t.amount/1000,'%Y-%m-%d %H:%i:%S') amount, ");
		sql.append(" t.amount, ");
		sql.append(" t.memo ");
		sql.append(" from e_hongbao_rule t ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int sethongbaoconfig(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_hongbao_rule (name,beginTime,endTime,amount,memo) ");
		sql.append(" values (:name,:beginTime,:endTime,:amount,:memo) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int sethongbao(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_hongbao_log (type,rule_id,user_id,time,amount,memo,operator_id) ");
		sql.append(" values (:type,:rule_id,:user_id,:time,:amount,:memo,:operator_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	

	

	@Override
	public int updateUserHbao(Map<String, Object> args1) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET amount=amount+ "+args1.get("amount1")+" where id= "+args1.get("operator_id")+" ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public Map<String, ?> selectamount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select amount from e_user where id = "+args.get("operator_id")+" ");
		return this.getQueryMap(sql.toString());
	}

	

	

}
