package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.AppService;

@Service("appService")
public class AppServiceImpl extends BaseServiceImpl implements AppService {

	@Override
	public int moneyApply_insert(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_loan_application (mobile,name,amount,mode,days,memo,user_id,time) ");
		sql.append(" values (:mobile,:name,:amount,:mode,:days,:memo,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> selectUser(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_licai_log t where t.user_id = "+args.get("user_id")+" and t.licai_id = "+args.get("licai_id")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int applylicai(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_licai_log (licai_id,mobile,name,amount,user_id,time,memo,referrer) ");
		sql.append(" values (:licai_id,:mobile,:name,:amount,:user_id,:time,:memo,:referrer) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int pubdemand(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into "+ args.get("table_name") +" (company,mobile,name,memo,side,time,title,user_id) ");
		sql.append(" values (:company,:mobile,:name,:memo,:side,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

}
