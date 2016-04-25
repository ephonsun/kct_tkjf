package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.EnterpriseCooperationService;

@Service("enterpriseCService")
public class EnterpriseCooperationServiceImpl extends BaseServiceImpl implements EnterpriseCooperationService {
	
	@Override
	public List<?> search(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (SELECT 2 type,id,title from e_tech t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ");
		sql.append(" UNION ");
		sql.append(" SELECT 3 type,id,title from e_material t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ");
		sql.append(" UNION ");
		sql.append(" SELECT 1 type,id,title from e_cooperation t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ");
		sql.append(" UNION ");
		sql.append(" SELECT 4 type,id,title from e_finance t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ) a ");
		sql.append(" ORDER BY a.id desc limit "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int searchNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from (SELECT 2 type,id,title from e_tech t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ");
		sql.append(" UNION ");
		sql.append(" SELECT 3 type,id,title from e_material t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ");
		sql.append(" UNION ");
		sql.append(" SELECT 1 type,id,title from e_cooperation t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ");
		sql.append(" UNION ");
		sql.append(" SELECT 4 type,id,title from e_finance t where t.title like '%"+args.get("word")+"%' OR t.memo like '%"+args.get("word")+"%' ) a ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> gettechlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select id,title,count,FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time,memo ");
		sql.append(" from e_tech e ");
		sql.append(" where e.type = "+args.get("type")+" ");
		sql.append("   and e.side = "+args.get("side")+" ");
		if("0".equals(args.get("order"))){
			sql.append(" ORDER BY e.time desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		} else {
			sql.append(" ORDER BY e.count desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int gettechlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) ");
		sql.append(" from e_tech e ");
		sql.append(" where e.type = "+args.get("type")+" ");
		sql.append("   and e.side = "+args.get("side")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> pubtechdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select ");
		sql.append(" t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.type, ");
		sql.append(" t.count, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.company, ");
		sql.append(" t.mobile, ");
		sql.append(" t.name, ");
		sql.append(" t.district, ");
		sql.append(" t.industry, ");
		sql.append(" t.memo ");
		sql.append(" from e_tech t ");
		sql.append(" WHERE t.id = " + args.get("id") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE " + args.get("table_name") + " SET count=count+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int pubtech(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_tech (type,title,company,mobile,name,district,industry,memo,side,time,user_id) ");
		sql.append(" values (:type,:title,:company,:mobile,:name,:district,:industry,:memo,:side,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> getmateriallist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select id,title,count,FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time,memo ");
		sql.append(" from e_material e ");
		sql.append(" where e.side = "+args.get("side")+" ");
		if("0".equals(args.get("order"))){
			sql.append(" ORDER BY e.time desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		} else {
			sql.append(" ORDER BY e.count desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getmateriallistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_material e where e.side = "+args.get("side")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int pubmaterial(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_material (title,company,mobile,name,district,industry,memo,side,time,user_id) ");
		sql.append(" values (:title,:company,:mobile,:name,:district,:industry,:memo,:side,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> pubmaterialdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select ");
		sql.append(" t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.count, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.company, ");
		sql.append(" t.mobile, ");
		sql.append(" t.name, ");
		sql.append(" t.district, ");
		sql.append(" t.industry, ");
		sql.append(" t.memo ");
		sql.append(" from e_material t ");
		sql.append(" WHERE t.id = " + args.get("id") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getcooperationlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select id,title,count,FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time,memo ");
		sql.append(" from e_cooperation e ");
		sql.append(" where e.side = "+args.get("side")+" ");
		if("0".equals(args.get("order"))){
			sql.append(" ORDER BY e.time desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		} else {
			sql.append(" ORDER BY e.count desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getcooperationlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_cooperation e where e.side = "+args.get("side")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int pubcooperation(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_cooperation (title,company,mobile,name,district,industry,memo,side,time,user_id) ");
		sql.append(" values (:title,:company,:mobile,:name,:district,:industry,:memo,:side,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> pubcooperationdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select ");
		sql.append(" t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.count, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.company, ");
		sql.append(" t.mobile, ");
		sql.append(" t.name, ");
		sql.append(" t.district, ");
		sql.append(" t.industry, ");
		sql.append(" t.memo ");
		sql.append(" from e_cooperation t ");
		sql.append(" WHERE t.id = " + args.get("id") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getfinancelist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select id,title,count,FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time,memo ");
		sql.append(" from e_finance e ");
		sql.append(" where e.side = "+args.get("side")+" ");
		if("0".equals(args.get("order"))){
			sql.append(" ORDER BY e.time desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		} else {
			sql.append(" ORDER BY e.count desc limit "+args.get("startNum")+","+args.get("num")+" ) a ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getfinancelistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_finance e where e.side = "+args.get("side")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int pubfinance(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_finance (title,company,mobile,name,district,industry,memo,side,time,user_id) ");
		sql.append(" values (:title,:company,:mobile,:name,:district,:industry,:memo,:side,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> pubfinancedetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select ");
		sql.append(" t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.count, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.company, ");
		sql.append(" t.mobile, ");
		sql.append(" t.name, ");
		sql.append(" t.district, ");
		sql.append(" t.industry, ");
		sql.append(" t.memo ");
		sql.append(" from e_finance t ");
		sql.append(" WHERE t.id = " + args.get("id") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	


	
	
	
	
	

}
