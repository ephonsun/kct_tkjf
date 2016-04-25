package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.MyRedEnvelopeService;

@Service("myRedEnvelopeService")
public class MyRedEnvelopeServiceImpl extends BaseServiceImpl implements MyRedEnvelopeService  {
	
	@Override
	public List<?> gethongbao(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.hongbao amount FROM e_user t where t.id= "+args.get("user_id")+" ) a ");
		return this.getQueryList(sql.toString(),args);
	}
	
	@Override
	public List<?> gethongbaolog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id,t.type,t.rule_id,e.`name` rule,t.time,t.amount,t.memo FROM e_hongbao_log t ,e_hongbao_rule e ");
		sql.append(" where t.user_id = "+args.get("user_id")+" ");
		sql.append(" and t.rule_id = e.id ) a ");
		return this.getQueryList(sql.toString(),args);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
