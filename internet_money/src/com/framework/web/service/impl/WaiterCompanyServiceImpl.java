package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.WaiterCompanyService;

@Service("waiterCompanyService")
public class WaiterCompanyServiceImpl extends BaseServiceImpl implements WaiterCompanyService {

	@Override
	public List<?> getwaiterlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		String category = (String) args.get("category");
		if("1".equals(category)){
			sql.append(" SELECT t.id,t.title,t.area,t.count,t.amount,t.district,e.avatar,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.company FROM "
					+ args.get("table_name") + " t , e_user e  ");
			sql.append(" WHERE t.authority = 0 ");
			sql.append(" AND t.side = "+args.get("side")+" ");
			sql.append(" AND t.district like '%"+args.get("district")+"%' ");
			sql.append(" AND e.id = t.user_id ");
		}else{
			sql.append(" SELECT t.id,t.title,t.count,e.avatar,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.company FROM "
					+ args.get("table_name") + " t , e_user e  ");
			sql.append(" WHERE t.authority = 0 ");
			sql.append(" AND t.side = "+args.get("side")+" ");
			sql.append(" AND t.district = "+args.get("district")+" ");
			sql.append(" AND e.id = t.user_id ");
		}
		sql.append(" ORDER BY t.id DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		return this.getQueryList(sql.toString());
		
	}

	@Override
	public int getwaiterlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) totalCount FROM " + args.get("table_name") + " t  WHERE t.authority = 0 ");
		sql.append(" AND t.side = "+args.get("side")+" ");
		sql.append(" AND t.district = "+args.get("district")+" ");
		// sql.append(" ORDER BY t.id ASC LIMIT
		// "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int pubfactory(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_pub_factory (company,mobile,name,district,area,amount,type,pics,memo,authority,side,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:area,:amount,:type,:pics,:memo,:authority,:side,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubequipment(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_pub_equipment (company,mobile,name,district,equipment,model,vender,productionDate,quantity,amount,pics,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:equipment,:model,:vender,:productionDate,:quantity,:amount,:pics,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int publogistics(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_logistics (company,mobile,name,district,memo,authority,side,count,time,title,user_id) ");
		sql.append(" values (:company,:mobile,:name,:district,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubjob(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_job (company,mobile,name,district,quantity,salaryMin,salaryMax,education,year,model,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:quantity,:salaryMin,:salaryMax,:education,:year,:model,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubactivity(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_tech (company,mobile,name,district,industry,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:industry,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubcooperation(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_cooperation (company,mobile,name,district,industry,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:industry,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubtraining(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_training (company,mobile,name,district,industry,quantity,days,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:industry,:quantity,:days,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubaccounting(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_accounting (company,mobile,name,district,industry,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:industry,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubstockrights(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_stockright (company,mobile,name,district,industry,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:industry,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubcapital(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_capital (company,mobile,name,district,industry,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:industry,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int pubbill(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_bill (company,mobile,name,district,bank,amount,repaymentDate,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:bank,:amount,:repaymentDate,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> pubfactorydetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select ");
		sql.append(" t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.count, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.company, ");
		if("myty".equals(args.get("type"))){
			sql.append(" t.mobile, ");
		}else if ("xiaoer".equals(args.get("type"))) {
			sql.append(" CONCAT(substr(t.mobile,1,3),'****',substr(t.mobile,8,4)) mobile, ");
		}
		sql.append(" t.`name`, ");
		if("e_pub_factory".equals(args.get("table_name"))){
			sql.append(" t.district, ");
			sql.append(" t.area, ");
			sql.append(" t.amount, ");
			sql.append(" t.pics, ");
			sql.append(" t.memo, ");
			sql.append(" t.type ");
			sql.append(" from "+args.get("table_name")+" t ");
		}else if("e_pub_equipment".equals(args.get("table_name"))){
			sql.append(" t.district, ");
			sql.append(" t.equipment, ");
			sql.append(" t.model, ");
			sql.append(" t.vender, ");
			sql.append(" t.productionDate, ");
			sql.append(" t.quantity, ");
			sql.append(" t.amount, ");
			sql.append(" t.pics, ");
			sql.append(" t.memo ");
			sql.append(" from "+args.get("table_name")+" t ");
		}else if("e_logistics".equals(args.get("table_name"))){
			sql.append(" t.district, ");
			sql.append(" t.memo ");
			sql.append(" from "+args.get("table_name")+" t ");
		}else if("e_job".equals(args.get("table_name"))){
			sql.append(" t.district, ");
			sql.append(" t.quantity, ");
			sql.append(" t.salaryMin, ");
			sql.append(" t.salaryMax, ");
			sql.append(" t.education, ");
			sql.append(" t.`year`, ");
			sql.append(" t.model, ");
			sql.append(" t.memo ");
			sql.append(" from "+args.get("table_name")+" t ");
		}else if("e_training".equals(args.get("table_name"))){
			sql.append(" t.district, ");
			sql.append(" t.industry, ");
			sql.append(" t.quantity, ");
			sql.append(" t.days, ");
			sql.append(" t.memo ");
			sql.append(" from "+args.get("table_name")+" t ");
		}else if("e_bill".equals(args.get("table_name"))){
			sql.append(" t.district, ");
			sql.append(" t.bank, ");
			sql.append(" t.amount, ");
			sql.append(" t.repaymentDate, ");
			sql.append(" t.memo ");
			sql.append(" from "+args.get("table_name")+" t ");
		}else if("e_management".equals(args.get("table_name")) || "e_legalaffairs".equals(args.get("table_name")) || "e_financialmanage".equals(args.get("table_name")) || "e_copywriter".equals(args.get("table_name")) || "e_equityassessment".equals(args.get("table_name")) || "e_studyreport".equals(args.get("table_name")) ){
			sql.append(" t.name, ");
			sql.append(" t.content, ");
			sql.append(" t.authority ");
			sql.append(" from "+args.get("table_name")+" t ");
		}else {
			sql.append(" t.district, ");
			sql.append(" t.industry, ");
			sql.append(" t.memo ");
			sql.append(" from "+args.get("table_name")+" t ");
		}
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
	public List<?> getduangongtags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ( select a.tag_id id,a.`name` tag from ");
		sql.append(" ( ");
		sql.append(" SELECT count(*) count,t.tag_id,e.`name` from r_duangong_pub_tag_id t,e_duangong_pub_tag e ");
		sql.append(" WHERE t.tag_id = e.id ");
		sql.append(" GROUP BY t.tag_id ");
		sql.append(" ) a,e_duangong_pub_tag b ");
		sql.append(" WHERE a.tag_id = b.id ");
		sql.append(" ORDER BY a.count desc limit 0,20) c ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getduangonglist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (!"".equals(args.get("tagId")) && args.get("tagId") != null) {
			sql.append(" select * from ( ");
			sql.append(
					" select e.id,e.title,e.count,FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time,e.pay,e.payUnit,e.apply,e.number,e.user_id userId,u.`name` user from r_duangong_pub_tag_id p,e_duangong_pub e,e_user u ");
			sql.append(" WHERE p.duangong_pub_id = e.id  ");
			sql.append("   AND p.tag_id = " + args.get("tagId") + " ");
			sql.append("   AND e.category = " + args.get("category") + " ");
			sql.append("   AND e.user_id = u.id ");
			sql.append(" ORDER BY e.time DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		} else {
			sql.append(" select * from ( ");
			sql.append(
					" select e.id,e.title,e.count,FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time,e.pay,e.payUnit,e.apply,e.number,e.user_id userId,u.`name` user from e_duangong_pub e,e_user u  ");
			sql.append(" WHERE e.user_id = u.id ");
			sql.append("   AND e.category = " + args.get("category") + " ");
			sql.append(" ORDER BY e.time DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getduangonglistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (!"".equals(args.get("tagId")) && args.get("tagId") != null) {
			sql.append(" select count(*) from r_duangong_pub_tag_id p,e_duangong_pub e,e_user u ");
			sql.append(" WHERE p.duangong_pub_id = e.id  ");
			sql.append("   AND p.tag_id = " + args.get("tagId") + " ");
			sql.append("   AND e.user_id = u.id ");
			sql.append("   AND e.category = " + args.get("category") + " ");
		} else {
			sql.append(" select count(*) from e_duangong_pub e,e_user u  ");
			sql.append(" WHERE e.user_id = u.id ");
			sql.append("   AND e.category = " + args.get("category") + " ");
		}
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public Map<String, ?> getduangongdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM (    ");
		sql.append(" SELECT t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.count, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.company, ");
		sql.append(" t.mobile, ");
		sql.append(" t.`name`, ");
		sql.append(" t.district, ");
		sql.append(" t.position, ");
		sql.append(" t.number, ");
		sql.append(" t.pay, ");
		sql.append(" t.payUnit, ");
		sql.append(" t.payType, ");
		sql.append(" t.gender, ");
		sql.append(" FROM_UNIXTIME(t.beginDay/1000,'%Y-%m-%d') beginDay, ");
		sql.append(" FROM_UNIXTIME(t.endDay/1000,'%Y-%m-%d') endDay, ");
		sql.append(" t.location, ");
		sql.append(" t.detail, ");
		sql.append(" t.apply, ");
		sql.append(" t.user_id userId, ");
		sql.append(" e.userName, ");
		sql.append(" e.`name` user    ");
		sql.append(" FROM e_duangong_pub t,e_user e   ");
		sql.append(" WHERE t.id = " + args.get("id") + "   ");
		sql.append(" and t.user_id = e.id  ");
		sql.append(" ) a ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public int pubduangong(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_duangong_pub (category,title,company,mobile,position,name,district,number,pay,payUnit,payType,gender,beginDay,endDay,location,detail,time,user_id) ");
		sql.append(
				" values (:category,:title,:company,:mobile,:position,:name,:district,:number,:pay,:payUnit,:payType,:gender,:beginDay,:endDay,:location,:detail,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public Map<String, ?> selectTieZiId(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_duangong_pub t ");
		sql.append(" WHERE t.time = " + args.get("time") + " ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public List<?> selectBiaoQian(String tags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_duangong_pub_tag t where t.name = '" + tags + "' ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int insertTag(String tags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_duangong_pub_tag (name)  values ('" + tags + "') ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public Map<String, ?> selectTagId(String tags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_duangong_pub_tag t ");
		sql.append(" WHERE t.name = '" + tags + "' ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public int insertTag_post(String id, String tagid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into r_duangong_pub_tag_id (tag_id,duangong_pub_id)  values (" + tagid + "," + id + ") ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> getduangongdetail1(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT e.id,e.`name` tag ");
		sql.append(" from r_duangong_pub_tag_id t,e_duangong_pub_tag e ");
		sql.append(" where t.duangong_pub_id = " + args.get("id") + " and t.tag_id = e.id ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateduangongCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_duangong_pub SET count=count+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int insertApply(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into r_duangong_apply (user_id,duangong_pub_id)  values (" + args.get("user_id") + ","
				+ args.get("id") + ") ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int updateApply(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_duangong_pub SET apply=apply+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> selectjianli(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_duangong_application t where t.user_id = " + args.get("user_id") + " ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getduangongNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_duangong_pub t where t.user_id = " + args.get("id") + " and t.apply < t.number ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getduangongselftags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ( select a.tag_id id,a.`name` tag from ");
		sql.append(" ( ");
		sql.append(
				" SELECT count(*) count,t.tag_id,e.`name` from r_duangong_application_self_tag_id t,e_duangong_application_self_tag e ");
		sql.append(" WHERE t.tag_id = e.id ");
		sql.append(" GROUP BY t.tag_id ");
		sql.append(" ) a,e_duangong_application_self_tag b ");
		sql.append(" WHERE a.tag_id = b.id ");
		sql.append(" ORDER BY a.count desc limit 0,10) c ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getduangongskilltags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ( select a.tag_id id,a.`name` tag from ");
		sql.append(" ( ");
		sql.append(
				" SELECT count(*) count,t.tag_id,e.`name` from r_duangong_application_skill_tag_id t,e_duangong_application_skill_tag e ");
		sql.append(" WHERE t.tag_id = e.id ");
		sql.append(" GROUP BY t.tag_id ");
		sql.append(" ) a,e_duangong_application_skill_tag b ");
		sql.append(" WHERE a.tag_id = b.id ");
		sql.append(" ORDER BY a.count desc limit 0,10) c ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getduangongapplicationlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (!"".equals(args.get("selfTagId")) && !"".equals(args.get("skillTagId"))) {// 都不为空的情况
			sql.append(" select * from ( ");
			sql.append(" select e.id,  e.title,  e.count,  FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
			sql.append(" e.userName, ");
			sql.append(" e.gender,e.age,e.declaration,e.user_id userId,u.`name` user ");
			sql.append(
					" from e_duangong_application e left join r_duangong_application_self_tag_id p on e.id = p.duangong_application_id,e_user u ");
			sql.append(" WHERE e.category = " + args.get("category") + " ");
			sql.append(" AND p.tag_id = " + args.get("selfTagId") + " ");
			sql.append(" AND e.user_id = u.id ");
			sql.append(" UNION  ");
			sql.append(" select e.id,  e.title,  e.count,  FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
			sql.append(" e.userName, ");
			sql.append(" e.gender,e.age,e.declaration,e.user_id userId,u.`name` user ");
			sql.append(
					" from e_duangong_application e left join r_duangong_application_skill_tag_id p on e.id = p.duangong_application_id,e_user u ");
			sql.append(" WHERE e.category = " + args.get("category") + " ");
			sql.append(" AND p.tag_id = " + args.get("skillTagId") + " ");
			sql.append(" AND e.user_id = u.id ) a ");
			sql.append(" ORDER BY a.time DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		}
		if (!"".equals(args.get("selfTagId")) && "".equals(args.get("skillTagId"))) {
			sql.append(" select * from ( ");
			sql.append(" select e.id,  e.title,  e.count,  FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
			sql.append(" e.userName, ");
			sql.append(" e.gender,e.age,e.declaration,e.user_id userId,u.`name` user ");
			sql.append(
					" from e_duangong_application e left join r_duangong_application_self_tag_id p on e.id = p.duangong_application_id,e_user u ");
			sql.append(" WHERE e.category = " + args.get("category") + " ");
			sql.append(" AND p.tag_id = " + args.get("selfTagId") + " ");
			sql.append(" AND e.user_id = u.id ) a ");
			sql.append(" ORDER BY a.time DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		}
		if (!"".equals(args.get("skillTagId")) && "".equals(args.get("selfTagId"))) {
			sql.append(" select * from ( ");
			sql.append(" select e.id,  e.title,  e.count,  FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
			sql.append(" e.userName, ");
			sql.append(" e.gender,e.age,e.declaration,e.user_id userId,u.`name` user ");
			sql.append(
					" from e_duangong_application e left join r_duangong_application_skill_tag_id p on e.id = p.duangong_application_id,e_user u ");
			sql.append(" WHERE e.category = " + args.get("category") + " ");
			sql.append(" AND p.tag_id = " + args.get("skillTagId") + " ");
			sql.append(" AND e.user_id = u.id ) a ");
			sql.append(" ORDER BY a.time DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		}if ("".equals(args.get("skillTagId")) && "".equals(args.get("selfTagId"))){
			sql.append(" select * from ( ");
			sql.append(" select e.id, ");
			sql.append(" e.title, ");
			sql.append(" e.count, ");
			sql.append(" FROM_UNIXTIME(e.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
			sql.append(" e.userName, ");
			sql.append(" e.gender, ");
			sql.append(" e.age, ");
			sql.append(" e.declaration, ");
			sql.append(" e.user_id userId, ");
			sql.append(" u.`name` user ");
			sql.append(
					" from e_duangong_application e,e_user u ");
			sql.append(" WHERE e.category = " + args.get("category") + "  ");
			sql.append("   AND e.user_id = u.id ) a ");
			sql.append(" ORDER BY a.time DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int pubduangongapplication(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_duangong_application (category,district,title,time,userName,name,picUrl,gender,age,height,mobile,department,declaration,freeTime,experience,user_id) ");
		sql.append(
				" values (:category,:district,:title,:time,:userName,:name,:picUrl,:gender,:age,:height,:mobile,:department,:declaration,:freeTime,:experience,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> selectselfBiaoQian(String selftags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_duangong_application_self_tag t where t.name = '" + selftags + "' ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> selectSkillBiaoQian(String skillTags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_duangong_application_skill_tag t where t.name = '" + skillTags + "' ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int insertSelfTag(String selftags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_duangong_application_self_tag (name)  values ('" + selftags + "') ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int insertSkillTag(String skillTags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_duangong_application_skill_tag (name)  values ('" + skillTags + "') ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int insertSelfTag_post(String id, String tagid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into r_duangong_application_self_tag_id (tag_id,duangong_application_id)  values (" + tagid
				+ "," + id + ") ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int insertSkillTag_post(String id, String tagid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into r_duangong_application_skill_tag_id (tag_id,duangong_application_id)  values (" + tagid
				+ "," + id + ") ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public Map<String, ?> selectJianLiId(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_duangong_application t ");
		sql.append(" WHERE t.time = " + args.get("time") + " ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public Map<String, ?> selectSelfTagId(String selftags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_duangong_application_self_tag t ");
		sql.append(" WHERE t.name = '" + selftags + "' ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public Map<String, ?> selectSkillTagId(String skillTags) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_duangong_application_skill_tag t ");
		sql.append(" WHERE t.name = '" + skillTags + "' ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public Map<String, ?> getduangongapplicationdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id, ");
		sql.append(" t.title, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.count, ");
		sql.append(" t.userName, ");
		sql.append(" CONCAT(SUBSTR(t.`name`,1,1),'*') name, ");
		sql.append(" t.picUrl, ");
		sql.append(" t.gender, ");
		sql.append(" t.age, ");
		sql.append(" t.height, ");
		sql.append(" t.mobile, ");
		sql.append(" t.department, ");
		sql.append(" t.declaration, ");
		sql.append(" t.freeTime, ");
		sql.append(" t.experience, ");
		sql.append(" t.user_id userId, ");
		sql.append(" e.`name` user");
		sql.append(" from e_duangong_application t,e_user e ");
		sql.append(" where t.id = " + args.get("id") + " ");
		sql.append("   and t.user_id = e.id ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public int updateJianLiCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_duangong_application SET count=count+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> selectPub(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_duangong_pub t ");
		sql.append(" WHERE t.user_id = '" + args.get("user_id") + "' ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int applicationduangong(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into r_duangong_invite (user_id,duangong_application_id)  values (" + args.get("user_id")
				+ "," + args.get("id") + ") ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> getselfTags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT e.id,e.`name` tag ");
		sql.append(" from r_duangong_application_self_tag_id t,e_duangong_application_self_tag e ");
		sql.append(" where t.duangong_application_id = " + args.get("id") + " and t.tag_id = e.id ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getskillTags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT e.id,e.`name` tag ");
		sql.append(" from r_duangong_application_skill_tag_id t,e_duangong_application_skill_tag e ");
		sql.append(" where t.duangong_application_id = " + args.get("id") + " and t.tag_id = e.id ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int pubregistration(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_registration (company,mobile,name,district,industry,memo,authority,side,count,time,title,user_id) ");
		sql.append(
				" values (:company,:mobile,:name,:district,:industry,:memo,:authority,:side,:count,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override 
	public int pubnameregistration(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_nameregistration (company,management,legalPerson,birthTime,birthPlace,name,mobile,qq,memo,time,user_id) ");
		sql.append(
				" values (:company,:management,:legalPerson,:birthTime,:birthPlace,:name,:mobile,:qq,:memo,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> viewmobile(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT mobile FROM " + args.get("table_name") + " t ");
		sql.append(" WHERE t.id = "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateviewmobileLog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_paotui_mobile_log (time,category,pub_id,user_id) ");
		sql.append(
				" values (:time,:category,:id,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int companyconsult(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into "+ args.get("table_name") +" (company,industry,name,mobile,content,authority,time,title,user_id) ");
		sql.append(" values (:company,:industry,:name,:mobile,:content,:authority,:time,:title,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applyregistration(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_registration_application (name,mobile,type,price,district,user_id,time,memo) ");
		sql.append(" values (:name,:mobile,:type,:price,:district,:user_id,:time,:memo) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applymanagement(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_management_application (name,mobile,company_id,user_id,time) ");
		sql.append(" values (:name,:mobile,:company_id,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applylegalaffairs(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_legalaffairs_application (name,mobile,company_id,user_id,time) ");
		sql.append(" values (:name,:mobile,:company_id,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applyfinancialmanage(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_financialmanage_application (name,mobile,company_id,user_id,time) ");
		sql.append(" values (:name,:mobile,:company_id,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applyequityassessment(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_equityassessment_application (name,mobile,company_id,user_id,time) ");
		sql.append(" values (:name,:mobile,:company_id,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applyhr(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_hr_application (name,mobile,company_id,user_id,time) ");
		sql.append(" values (:name,:mobile,:company_id,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int applypatent(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_patent_application (name,mobile,company_id,user_id,time) ");
		sql.append(" values (:name,:mobile,:company_id,:user_id,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> getmanagementcompanylist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id,c.name,c.honor,c.mainbusinesslist,c.district,c.type from e_management_company c ");//
		sql.append(" where c.type = "+args.get("type")+" ");
		sql.append("   and c.district = "+args.get("district")+" ");
		sql.append(" ORDER BY c.id DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getmanagementcompanylistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_management_company c ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getmanagementcompanydetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id, ");
		sql.append(" c.name, ");
		sql.append(" c.honor, ");
		sql.append(" c.serviceobject, ");
		sql.append(" c.mainbusiness, ");
		sql.append(" c.servicearea, ");
		sql.append(" c.otherbusiness, ");
		sql.append(" c.leading, ");
		sql.append(" c.district, ");
		sql.append(" c.type, ");
		sql.append(" c.staff, ");
		sql.append(" c.mobile, ");
		sql.append(" c.address ");
		sql.append("  from e_management_company c  ");
		sql.append(" where c.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getlegalaffairslist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id,c.name,c.honor,c.mainbusinesslist,c.district,c.type from e_legalaffairs_company c ");
		sql.append(" where c.type = "+args.get("type")+" ");
		sql.append("   and c.district = "+args.get("district")+" ");
		sql.append(" ORDER BY c.id DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getlegalaffairslistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_legalaffairs_company c ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getlegalaffairsdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id, ");
		sql.append(" c.name, ");
		sql.append(" c.honor, ");
		sql.append(" c.serviceobject, ");
		sql.append(" c.mainbusiness, ");
		sql.append(" c.servicearea, ");
		sql.append(" c.otherbusiness, ");
		sql.append(" c.leading, ");
		sql.append(" c.district, ");
		sql.append(" c.type, ");
		sql.append(" c.staff, ");
		sql.append(" c.mobile, ");
		sql.append(" c.address ");
		sql.append("  from e_legalaffairs_company c  ");
		sql.append(" where c.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getfinancialmanagelist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id,c.name,c.honor,c.mainbusinesslist,c.district,c.type from e_financialmanage_company c ");
		sql.append(" where c.type = "+args.get("type")+" ");
		sql.append("   and c.district = "+args.get("district")+" ");
		sql.append(" ORDER BY c.id DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getfinancialmanagelistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_financialmanage_company c ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getfinancialmanagedetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id, ");
		sql.append(" c.name, ");
		sql.append(" c.honor, ");
		sql.append(" c.serviceobject, ");
		sql.append(" c.mainbusiness, ");
		sql.append(" c.servicearea, ");
		sql.append(" c.otherbusiness, ");
		sql.append(" c.leading, ");
		sql.append(" c.district, ");
		sql.append(" c.type, ");
		sql.append(" c.staff, ");
		sql.append(" c.mobile, ");
		sql.append(" c.address ");
		sql.append("  from e_legalaffairs_company c  ");
		sql.append(" where c.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getequityassessmentlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id,c.name,c.honor,c.mainbusinesslist,c.district,c.type from e_equityassessment_company c ");
		sql.append(" where c.type = "+args.get("type")+" ");
		sql.append("   and c.district = "+args.get("district")+" ");
		sql.append(" ORDER BY c.id DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getequityassessmentlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_equityassessment_company c ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getequityassessmentdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id, ");
		sql.append(" c.name, ");
		sql.append(" c.honor, ");
		sql.append(" c.serviceobject, ");
		sql.append(" c.mainbusiness, ");
		sql.append(" c.servicearea, ");
		sql.append(" c.otherbusiness, ");
		sql.append(" c.leading, ");
		sql.append(" c.district, ");
		sql.append(" c.type, ");
		sql.append(" c.staff, ");
		sql.append(" c.mobile, ");
		sql.append(" c.address ");
		sql.append("  from e_legalaffairs_company c  ");
		sql.append(" where c.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getpatentlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id,c.name,c.honor,c.mainbusinesslist,c.district,c.type from e_patent_company c ");
		sql.append(" where c.type = "+args.get("type")+" ");
		sql.append("   and c.district = "+args.get("district")+" ");
		sql.append(" ORDER BY c.id DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getpatentlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_patent_company c ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getpatentdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id, ");
		sql.append(" c.name, ");
		sql.append(" c.honor, ");
		sql.append(" c.serviceobject, ");
		sql.append(" c.mainbusiness, ");
		sql.append(" c.servicearea, ");
		sql.append(" c.otherbusiness, ");
		sql.append(" c.leading, ");
		sql.append(" c.district, ");
		sql.append(" c.type, ");
		sql.append(" c.staff, ");
		sql.append(" c.mobile, ");
		sql.append(" c.address ");
		sql.append("  from e_legalaffairs_company c  ");
		sql.append(" where c.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> gethrlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id,c.name,c.honor,c.mainbusinesslist,c.district,c.type from e_hr_company c ");
		sql.append(" where c.type = "+args.get("type")+" ");
		sql.append("   and c.district = "+args.get("district")+" ");
		sql.append(" ORDER BY c.id DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int gethrlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_hr_company c ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> gethrdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select c.id, ");
		sql.append(" c.name, ");
		sql.append(" c.honor, ");
		sql.append(" c.serviceobject, ");
		sql.append(" c.mainbusiness, ");
		sql.append(" c.servicearea, ");
		sql.append(" c.otherbusiness, ");
		sql.append(" c.leading, ");
		sql.append(" c.district, ");
		sql.append(" c.type, ");
		sql.append(" c.staff, ");
		sql.append(" c.mobile, ");
		sql.append(" c.address ");
		sql.append("  from e_legalaffairs_company c  ");
		sql.append(" where c.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

}
