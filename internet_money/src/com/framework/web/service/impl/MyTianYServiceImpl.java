package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.MyTianYService;

@Service("myTianYService")
@Transactional
public class MyTianYServiceImpl extends BaseServiceImpl implements MyTianYService {

	
	@Override
	public int updateUser(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE e_user t ");
			sql.append(" SET t.name=:name, ");
			sql.append(" t.userName=:userName, ");
			sql.append(" t.gender=:gender, ");
			sql.append(" t.avatar=:avatar, ");
			sql.append(" t.email=:email, ");
//			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.identification=:identification, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.address=:address, ");
			sql.append(" t.lastTime=:lastTime,  ");
			sql.append(" t.lastip=:lastip  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	
	@Override
	public int updateusercompany(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_company t ");
		sql.append(" SET t.name='"+args.get("name")+"', ");
		sql.append(" t.district="+args.get("district")+", ");
		sql.append(" t.address="+args.get("address")+", ");
		sql.append(" t.industry="+args.get("industry")+", ");
		sql.append(" t.license="+args.get("license")+", ");
		sql.append(" t.organization="+args.get("organization")+", ");
		sql.append(" t.registeredCapital="+args.get("registeredCapital")+", ");
		sql.append(" t.introduction="+args.get("introduction")+" ");
		sql.append(" WHERE t.id ="+args.get("id")+" ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> getagentapplicationlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (SELECT t.id, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.agent_title description, ");
		sql.append(" t.state ");
		sql.append(" FROM e_gov_agent_application t  ");
		sql.append(" WHERE t.user_id ="+args.get("id")+" ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int getagentapplicationlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_gov_agent_application t WHERE t.user_id ="+args.get("id")+" ");
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getagentapplicationdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_gov_agent_application t where t.id = "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> selectPerson(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id, ");
		sql.append(" t.userName, ");
		sql.append(" t.`name`, ");
		sql.append(" t.gender, ");
		sql.append(" t.email, ");
		sql.append(" t.mobile, ");
		sql.append(" t.identification, ");
		sql.append(" t.company, ");
		sql.append(" t.address, ");
		sql.append(" t.avatar, ");
		sql.append(" t.state, ");
		sql.append(" t.type, ");
		sql.append(" t.privilege, ");
		sql.append(" FROM_UNIXTIME(t.createTime/1000,'%Y-%m-%d %H:%i:%S') createTime, ");
		sql.append(" FROM_UNIXTIME(t.lastTime/1000,'%Y-%m-%d %H:%i:%S') lastTime, ");
		sql.append(" t.lastIp ");
		sql.append(" from e_user t where t.id = "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}
	
	
	@Override
	public List<?> getusercompany(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id, ");
		sql.append(" t.name, ");
		sql.append(" t.district, ");
		sql.append(" t.address, ");
		sql.append(" t.industry, ");
		sql.append(" t.license, ");
		sql.append(" t.organization, ");
		sql.append(" t.registeredCapital, ");
		sql.append(" t.introduction ");
		sql.append(" from e_company t where t.id = "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}
	
	
	

	@Override
	public List<?> getloanapplicationlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.amount, ");
		sql.append(" t.state ");
		sql.append(" FROM e_loan_application t ");
		sql.append(" WHERE t.user_id ="+args.get("id")+" ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}
	
	
	@Override
	public List<?> getusercompanylist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id, ");
		sql.append(" t.name ");
		sql.append(" FROM e_company t ");
		sql.append(" WHERE t.user_id ="+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}
	
	
	
	@Override
	public int getloanapplicationlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_loan_application t WHERE t.user_id ="+args.get("id")+" ");
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getloanapplicationdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" SELECT t.id, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.company, ");
		sql.append(" t.mobile, ");
		sql.append(" t.`name`, ");
		sql.append(" t.industry, ");
		sql.append(" t.email, ");
		sql.append(" t.amount, ");
		sql.append(" t.mode, ");
		sql.append(" t.days, ");
		sql.append(" t.asset, ");
		sql.append(" t.sales, ");
		sql.append(" t.memo, ");
		sql.append(" e.name, ");
		sql.append(" t.state ");
		sql.append(" FROM e_loan_application t,e_loan_state e ");
		sql.append(" where t.id = "+args.get("id")+" ) a ");
		sql.append("   AND t.state = e.state ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getpublist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,1 category from e_pub_factory t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,2 category from e_pub_equipment t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,3 category from e_logistics t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,4 category from e_job t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,5 category from e_tech t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,6 category from e_cooperation t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,7 category from e_training t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,8 category from e_accounting t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,9 category from e_stockright t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,10 category from e_capital t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,11 category from e_bill t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,12 category from e_registration t where t.user_id = "+args.get("id")+" ) a ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,13 category from e_management t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,14 category from e_legalaffairs t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,15 category from e_financialmanage t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,16 category from e_copywriter t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,17 category from e_equityassessment t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.title description,t.count,18 category from e_studyreport t where t.user_id = "+args.get("id")+" ");
		sql.append(" ORDER BY a.time DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int getpublistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from (select t.id,t.time,t.district description,t.count from e_pub_factory t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_pub_equipment t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_logistics t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_job t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_tech t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_cooperation t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_training t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_accounting t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_stockright t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_capital t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_bill t where t.user_id = "+args.get("id")+" ");
		sql.append(" UNION  ");
		sql.append(" select t.id,t.time,t.district description,t.count from e_registration t where t.user_id = "+args.get("id")+" ) a ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,t.time,t.district description,t.count from e_management t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,t.time,t.district description,t.count from e_legalaffairs t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,t.time,t.district description,t.count from e_financialmanage t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,t.time,t.district description,t.count from e_copywriter t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,t.time,t.district description,t.count from e_equityassessment t where t.user_id = "+args.get("id")+" ");
//		sql.append(" UNION  ");
//		sql.append(" select t.id,t.time,t.district description,t.count from e_studyreport t where t.user_id = "+args.get("id")+"  ");
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getpubdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.memo detail,t.count FROM "+args.get("table_name")+" t ");
		sql.append(" WHERE t.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getconsultlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT "); 
		sql.append(" t.id, ");
		sql.append(" t.category, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.title content FROM e_company_consult t where t.user_id = "+args.get("user_id")+"  ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int getconsultlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_company_consult t where t.user_id = "+args.get("user_id")+"");
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getconsultdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.memo content FROM e_company_consult t ");
		sql.append(" WHERE t.id = "+args.get("id")+" ) a ");
		//sql.append("   AND t.category = "+args.get("category")+" ");
		return this.getQueryList(sql.toString());
	}

	

	

	

	@Override
	public List<?> getmymentorlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select t.id,t.mentor_id mentorId,e.`name` mentor,t.company,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time ");
		sql.append(" from e_mentor_employ t,e_mentor e "); 
		sql.append(" where t.user_id = "+args.get("user_id")+" ");
		sql.append("   and e.id = t.mentor_id ");
		sql.append(" ORDER BY t.time desc LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getmymentorlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) ");
		sql.append(" from e_mentor_employ t "); 
		sql.append(" where t.user_id = "+args.get("user_id")+" ");
		return this.getQueryForInt(sql.toString());
	}

	

	

	

	@Override
	public List<?> getinsurancelist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select  ");
		sql.append(" t.id, ");
		sql.append(" k.`name` insurance, ");
		sql.append(" t.mobile, ");
		sql.append(" t.name, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.state, ");
		sql.append(" from e_insurance_application t,e_insurance k ");
		sql.append(" where t.insurance_id = k.id  ");
		sql.append("   and t.user_id = "+args.get("user_id")+" ");
		sql.append(" ORDER BY t.time desc LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}
	
	
	@Override
	public int getinsurancelistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_insurance_application t,e_insurance k ");
		sql.append(" where t.insurance_id = k.id  ");
		sql.append("   and t.user_id = "+args.get("user_id")+" ");
		return this.getQueryForInt(sql.toString());
	}
	
	

	@Override
	public List<?> getmystartupconsultlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from (   ");
		sql.append(" SELECT t.id,   "); 
		sql.append(" t.mentor_id mentorId,   ");
		sql.append(" e.`name` mentor, ");
		sql.append(" t.content, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time ");
		sql.append(" FROM e_startup_consult t,e_mentor e  ,e_user u ");
		sql.append(" where t.mentor_id = e.id   "); 
		sql.append("   and t.user_id = u.id  ");
		sql.append(" and t.user_id = "+args.get("user_id")+"   ");
		sql.append(" ORDER BY t.time ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getmystartupconsultlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) ");
		sql.append(" FROM e_startup_consult t  ");
		sql.append(" where t.user_id = "+args.get("user_id")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getmystartupconsultdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		
		sql.append("  select * from (  ");
		sql.append(" SELECT t.id,   ");
		sql.append(" t.mentor_id mentorId,   ");
		sql.append(" e.`name` mentor,   ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,   ");
		sql.append(" t.content ");
		sql.append(" FROM e_startup_consult t,e_mentor e   ");
		sql.append(" where t.id = "+args.get("id")+"   ");
		sql.append(" and t.mentor_id = e.id  ");
		sql.append(" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updatemyStartupConsultCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_startup_consult SET count=count+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> gethongbao(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.hongbao amount FROM e_user t where t.id= "+args.get("user_id")+" ) a ");
		return this.getQueryList(sql.toString(),args);
	}
	
	@Override
	public List<?> gethongbaolog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id,t.type,t.rule_id,e.`name` rule,FROM_UNIXTIME(t.time/1000,'%m-%d %H:%i') time,t.amount,t.memo FROM e_hongbao_log t ,e_hongbao_rule e ");
		sql.append(" where t.user_id = "+args.get("user_id")+" ");
		sql.append(" and t.rule_id = e.id ");
		sql.append(" ORDER BY t.time DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString(),args);
	}

	@Override 
	public List<?> selectusername(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_user t where t.id <> "+args.get("id")+" ");
		sql.append(" and t.userName =:userName ");
		return this.getQueryList(sql.toString(),args);
	}

	@Override
	public int gethongbaologNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_hongbao_log t ,e_hongbao_rule e ");
		sql.append(" where t.user_id = "+args.get("user_id")+" ");
		sql.append(" and t.rule_id = e.id ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int updateWaiterDetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		String category = (String) args.get("category");
		if("1".equals(category)){
			sql.append(" UPDATE e_pub_factory t ");
			sql.append(" SET t.area=:area, ");
			sql.append(" t.amount=:amount, ");
			sql.append(" t.pics=:pics, ");
			sql.append(" t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("2".equals(category)) {
			sql.append(" UPDATE e_pub_equipment t ");
			sql.append(" SET t.equipment=:equipment, ");
			sql.append(" t.model=:model, ");
			sql.append(" t.vender=:vender, ");
			sql.append(" t.productionDate=:productionDate, ");
			sql.append(" t.quantity=:quantity, ");
			sql.append(" t.amount=:amount, ");
			sql.append(" t.pics=:pics, ");
			sql.append(" t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("3".equals(category)) {
			sql.append(" UPDATE e_logistics t ");
			sql.append(" SET t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.industry=:industry, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("4".equals(category)) {
			sql.append(" UPDATE e_job t ");
			sql.append(" SET t.quantity=:quantity, ");
			sql.append(" t.salaryMin=:salaryMin, ");
			sql.append(" t.salaryMax=:salaryMax, ");
			sql.append(" t.education=:education, ");
			sql.append(" t.year=:year, ");
			sql.append(" t.model=:model, ");
			sql.append(" t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("5".equals(category)) {
			sql.append(" UPDATE e_tech t ");
			sql.append(" SET t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.industry=:industry, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("6".equals(category)) {
			sql.append(" UPDATE e_cooperation t ");
			sql.append(" SET t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.industry=:industry, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("7".equals(category)) {
			sql.append(" UPDATE e_training t ");
			sql.append(" SET t.industry=:industry, ");
			sql.append(" t.quantity=:quantity, ");
			sql.append(" t.days=:days, ");
			sql.append(" t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("8".equals(category)) {
			sql.append(" UPDATE e_accounting t ");
			sql.append(" SET t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.industry=:industry, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("9".equals(category)) {
			sql.append(" UPDATE e_stockright t ");
			sql.append(" SET t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.industry=:industry, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("10".equals(category)) {
			
		}else if ("11".equals(category)) {
			sql.append(" UPDATE e_bill t ");
			sql.append(" SET t.bank=:bank, ");
			sql.append(" t.amount=:amount, ");
			sql.append(" t.repaymentDate=:repaymentDate, ");
			sql.append(" t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}else if ("12".equals(category)) {
			sql.append(" UPDATE e_registration t ");
			sql.append(" SET t.title=:title, ");
			sql.append(" t.time=:time, ");
			sql.append(" t.company=:company, ");
			sql.append(" t.name=:name, ");
			sql.append(" t.mobile=:mobile, ");
			sql.append(" t.district=:district, ");
			sql.append(" t.industry=:industry, ");
			sql.append(" t.memo=:memo  ");
			sql.append(" WHERE t.id ="+args.get("id")+" ");
		}
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public Map<String, ?> getapplication(Map<String, Object> args) throws Exception {
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
			sql.append(" t.category, ");
			sql.append(" t.height, ");
			sql.append(" t.mobile, ");
			sql.append(" t.department, ");
			sql.append(" t.declaration, ");
			sql.append(" t.freeTime, ");
			sql.append(" t.experience, ");
			sql.append(" t.user_id userId, ");
			sql.append(" e.`name` user");
			sql.append(" from e_duangong_application t,e_user e ");
			sql.append(" where t.user_id = " + args.get("user_id") + " ");
			sql.append("   and t.user_id = e.id ");
			return this.getQueryMap(sql.toString());
	}

	@Override
	public int deleteSelfTag_post(String id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from r_duangong_application_self_tag_id where duangong_application_id ="+id+" ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int deleteSkillTag_post(String id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from r_duangong_application_skill_tag_id where duangong_application_id ="+id+" ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int updateMyApplication(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_duangong_application t ");
		sql.append(" SET t.title=:title, ");
		sql.append(" t.picUrl=:picUrl, ");
		sql.append(" t.gender=:gender, ");
		sql.append(" t.age=:age, ");
		sql.append(" t.height=:height, ");
		sql.append(" t.mobile=:mobile, ");
		sql.append(" t.department=:department, ");
		sql.append(" t.district=:district, ");
		sql.append(" t.category=:category, ");
		sql.append(" t.declaration=:declaration, ");
		sql.append(" t.freeTime=:freeTime, ");
		sql.append(" t.experience=:experience, ");
		sql.append(" t.time=:time ");
		sql.append(" WHERE t.id ="+args.get("id")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	@Transactional(rollbackFor=Exception.class) 
	public void jdbcTest() throws Exception{
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		StringBuffer sql3 = new StringBuffer();
		sql.append(" delete from e_sms where mobile = 13611591327  ");
		this.doSaveAsUpdate(sql.toString());
		
		sql2.append(" delete from e_sms where    ");
		this.doSaveAsUpdate(sql2.toString());
		
		
	}

	@Override
	public int deleteapplication(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from e_duangong_application where user_id =:user_id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}


	@Override
	public List<?> doSelect_usercompany(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_company where name = '"+args.get("name")+"' and id <> "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public List<?> doSelect_addusercompany(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_company where name = '"+args.get("name")+"' ");
		return this.getQueryList(sql.toString());
	}


	@Override
	public Map<String, ?> selectUserMap(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id, ");
		sql.append(" t.userName, ");
		sql.append(" t.`name`, ");
		sql.append(" t.gender, ");
		sql.append(" t.email, ");
		sql.append(" t.avatar, ");
		sql.append(" t.mobile, ");
		sql.append(" t.identification, ");
		sql.append(" t.company, ");
		sql.append(" t.address, ");
		sql.append(" t.lastIp ");
		sql.append(" from e_user t where t.id = "+args.get("id")+" ");
		return this.getQueryMap(sql.toString());
	}


	@Override
	public int addusercompany(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_company (name,district,address,industry,license,organization,registeredCapital,introduction,user_id) ");
		sql.append(" values (:name,:district,:address,:industry,:license,:organization,:registeredCapital,:introduction,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	

	

	

	

	

	
	
	
}
