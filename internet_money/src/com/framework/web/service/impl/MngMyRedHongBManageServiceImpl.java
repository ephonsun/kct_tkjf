package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.MngMyRedHongBManageService;

@Service("MyRedHongBManageService")
public class MngMyRedHongBManageServiceImpl extends BaseServiceImpl implements MngMyRedHongBManageService  {

	@Override
	public List<?> selectuser(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_user t  ");
		sql.append(" WHERE t.id = "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public List<?> gethongbao(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT t.hongbao amount FROM e_user t  ");
		sql.append(" WHERE t.mobile = "+args.get("mobile")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int hongbao_xf(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET hongbao=hongbao - :hongbao WHERE id =:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> hongbao_yue(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.username,t.name,t.mobile,t.hongbao FROM e_user t  ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int hongbao_yueNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_user t  ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int hongbao_ff(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET hongbao=hongbao + :hongbao WHERE id =:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> searchMobile(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.username,t.name,t.mobile,t.hongbao FROM e_user t  ");
		sql.append(" WHERE t.mobile = "+args.get("mobile")+"  ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int searchMobileNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_user t  ");
		sql.append(" WHERE t.mobile = "+args.get("mobile")+"  ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> searchYe(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.username,t.name,t.mobile,t.hongbao FROM e_user t ");
		sql.append(" WHERE t.hongbao between "+args.get("min")+" and "+args.get("max")+" ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int searchYeNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_user t  ");
		sql.append(" WHERE t.hongbao between "+args.get("min")+" and "+args.get("max")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public Map<String, ?> gethongbaoMng(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.hongbao FROM e_user t where t.id= "+args.get("id")+" ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public int updatehongbao(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET hongbao=:sxnum WHERE mobile =:mobile ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int updatehongbaoZero(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET hongbao = 0 WHERE mobile =:mobile ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public Map<String, ?> gethongbaoMap(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT t.hongbao amount FROM e_user t  ");
		sql.append(" WHERE t.mobile = "+args.get("mobile")+" ) a ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public int insertXkkfHongBaoLogTj(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_hongbao_log (type,rule_id,user_id,time,amount,memo,operator_id) ");
		sql.append(" values (:type,:rule_id,:user_id,:createTime,:amount,:memo,:operator_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	
	
	
	
	
	

}
