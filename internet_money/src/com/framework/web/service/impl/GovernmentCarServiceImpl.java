package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.batchSetter.MutexBatchInsertStatementSetter;
import com.framework.web.service.GovernmentCarService;

@Service("governmentCarService")
public class GovernmentCarServiceImpl extends BaseServiceImpl implements GovernmentCarService {

	@Override
	public int doSave(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into test (text) ");
		sql.append(" values (:text) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int doDelete(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from test where text =:text ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int doUpdate(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE test SET text=:text WHERE id = 5 ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	@Override
	public List<?> doSelect() throws Exception {
		return this.getQueryList(" select text from test ");
	}

	
	@Override
	public int[] doBaechInsert(List list) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into test (time,text) ");
		sql.append(" values (?,?) ");
		return this.doSaveAsBatchUpdate(sql.toString(), new MutexBatchInsertStatementSetter(list));
	}

	@Override
	public int register(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_user (mobile,password,createTime,user_tuijian_id,lastip,type,userName) ");
		sql.append(" values (:mobile,:password,:createTime,:mobiletj,:lastip,:typelx,:userName) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> doSelect_user(String mobile) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_user where mobile = "+mobile+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getgovnews(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id,t.title,t.type,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.shortContent FROM e_gov_news t  ");
		if(!"".equals(args.get("type")) && args.get("type") != null ){
			sql.append(" WHERE t.type = "+args.get("type")+" ");
		}
		sql.append(" ORDER BY t.time DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int getgovnewsNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_gov_news t ");
		sql.append(" WHERE t.type not IN (8,9) ");
		if(!"".equals(args.get("type")) && args.get("type") != null ){
			sql.append(" AND t.type = "+args.get("type")+" ");
		}
		
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getgovnewsdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( SELECT t.id id,t.title,t.content,t.url,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time ,t.type FROM e_gov_news t where t.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getgovagent(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.title,t.shortContent FROM e_gov_agent t ");
		if(!"".equals(args.get("type")) && args.get("type") != null ){
			sql.append(" where t.tags = "+args.get("type")+" ");
		}
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int getgovagentNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_gov_agent t ");
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getgovagentdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id,t.title,t.content,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.tags type FROM e_gov_agent t where t.id = "+args.get("id")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int applygovagent(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_gov_agent_application (agent_id,mobile,name,time,user_id) ");
		sql.append(" values (:agent_id,:mobile,:name,:time,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> getsms(String mobile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ?> doSelect_userid(String mobiletj) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id from e_sms where mobile = "+mobiletj+" ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public List<?> doSelect_usersms(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_sms where mobile =:mobile and smsCode=:sms  ");
		return this.getQueryList(sql.toString(),args);
	}

	@Override
	public int insertHongBaoLog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_hongbao_log (type,rule_id,user_id,time,amount,memo,operator_id) ");
		sql.append(" values (:type,:rule_id,:user_id,:createTime,:amount,:memo,:operator_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public Map<String, ?> selectUserId(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_user t ");
		sql.append(" WHERE t.createTime = " + args.get("createTime") + " ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public List<?> getparknews(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.title,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.shortContent FROM e_park_news t ");
		sql.append(" WHERE t.type = "+args.get("type")+" ");
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getparknewsNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_park_news t ");
		sql.append(" WHERE t.type = "+args.get("type")+" ");
		return this.getQueryForInt(sql.toString());
	}
	
	
	@Override
	public List<?> getrcfwnews(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.title,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.count,t.pic,t.summary FROM e_rcfw_news t ");
		sql.append(" WHERE t.type = "+args.get("type")+" ");
		sql.append(" ORDER BY t.time DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getrcfwnewsNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_rcfw_news t ");
		sql.append(" WHERE t.type = "+args.get("type")+" ");
		return this.getQueryForInt(sql.toString());
	}
	
	
	@Override
	public List<?> getrcfwpolicy(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id,t.title,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.count,t.pic,t.summary,t.link FROM e_rcfw_policy t ");
		sql.append(" WHERE t.type = "+args.get("type")+" ");
		sql.append(" ORDER BY t.time DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getrcfwpolicyNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_rcfw_policy t ");
		sql.append(" WHERE t.type = "+args.get("type")+" ");
		return this.getQueryForInt(sql.toString());
	}
	
	
	@Override
	public List<?> getrcfwnewsdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT  ");
		sql.append(" t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.count, ");
		sql.append(" t.pic, ");
		sql.append(" t.summary, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,  ");
		sql.append(" t.content ");
		sql.append(" from e_rcfw_news t  ");
		sql.append(" WHERE t.id =:id ) a ");
		return this.getQueryList(sql.toString(),args);
	}
	

	@Override
	public List<?> getparknewsdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT  ");
		sql.append(" t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.content, ");
		sql.append(" t.url, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time  ");
		sql.append(" from e_park_news t  ");
		sql.append(" WHERE t.id =:id ) a ");
		return this.getQueryList(sql.toString(),args);
	}

	@Override
	public int insertHongBaoLogTj(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_hongbao_log (type,rule_id,user_id,time,amount,memo,operator_id) ");
		sql.append(" values (:type,:rule_tjid,:mobiletj,:createTime,:amount,:memotj,:operator_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public Map<String, ?> doSelect_useridtwo(String mobiletj) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id from e_user where mobile = "+mobiletj+" ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public int registerCompany(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_company (name,user_id) ");
		sql.append(" values (:company,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int updatercfwnewsCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_rcfw_news SET count=count+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	

	

	

	

	


}
