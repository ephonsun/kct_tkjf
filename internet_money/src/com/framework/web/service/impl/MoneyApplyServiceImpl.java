package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.MoneyApplyService;
import com.framework.web.test.MoneyApply;

@Service("moneyApplyService")
public class MoneyApplyServiceImpl extends BaseServiceImpl implements MoneyApplyService {

	@Override
	public int moneyApply_insert(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_loan_application (mobile,name,company,industry,amount,mode,days,email,asset,sales,memo,user_id,time) ");
		sql.append(" values (:mobile,:name,:company,:industry,:amount,:mode,:days,:email,:asset,:sales,:memo,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applyinsurance(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_insurance_application (name,user_id,time,mobile,insurance_id) ");
		sql.append(" values (:name,:user_id,:time,:mobile,:category) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int loanbid_insert(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_loan_bid (company,industry,name,mobile,email,product,sales,loanAmount,loanBank,loanRate,loanCertificate,memo,time,user_id) ");
		sql.append(" values (:company,:industry,:name,:mobile,:email,:product,:sales,:loanAmount,:loanBank,:loanRate,:loanCertificate,:memo,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applyaccounting(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_accounting_application (name,mobile,category,month,price,user_id,time) ");
		sql.append(" values (:name,:mobile,:category,:month,:price,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applylicai(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_licai_log (licai_id,mobile,name,amount,user_id,time,memo,referrer) ");
		sql.append(" values (:licai_id,:mobile,:name,:amount,:user_id,:time,:memo,:referrer) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> selectUser(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_licai_log t where t.user_id = "+args.get("user_id")+" and t.licai_id = "+args.get("licai_id")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> selectMoneyApply(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select t.company,t.`name`,t.mobile,t.amount money,t.memo,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time "
				+ "from e_loan_application t order by t.time desc ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> selectMoneyApplyClass(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select t.company,t.`name`,t.mobile,t.amount money,t.memo,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time "
				+ "from e_loan_application t order by t.time desc ) a ");
		return this.getQueryList(sql.toString(),MoneyApply.class);
	}

	

}
