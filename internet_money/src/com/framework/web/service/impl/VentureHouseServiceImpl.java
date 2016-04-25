package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.VentureHouseService;

@Service("ventureHouseService")
public class VentureHouseServiceImpl extends BaseServiceImpl implements VentureHouseService {

	@Override
	public List<?> getmentortags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ( select id,name tag from e_mentor_tag ) c ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getmentorlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (!"".equals(args.get("tagId")) && args.get("tagId") != null) {
			sql.append(
					" SELECT e.id,e.name,e.department,e.title,e.recommend,e.count,e.picUrl FROM r_mentor_tag_id p,e_mentor e ");
			sql.append(" WHERE p.mentor_id = e.id  ");
			sql.append("   AND p.tag_id = " + args.get("tagId") + " ");
			sql.append(" ORDER BY convert(e.name using gbk) ASC LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		} else {
			sql.append(" SELECT e.id,e.name,e.department,e.title,e.recommend,e.count,e.picUrl FROM e_mentor e ");
			sql.append(" ORDER BY convert(e.name using gbk) ASC LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getmentorlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		// sql.append(" ORDER BY t.id ASC LIMIT
		// "+args.get("startNum")+","+args.get("num")+" ");
		if (!"".equals(args.get("tagId")) && args.get("tagId") != null) {
			sql.append(" SELECT count(*) FROM r_mentor_tag_id p,e_mentor e ");
			sql.append(" WHERE p.mentor_id = e.id  ");
			sql.append("   AND p.tag_id = " + args.get("tagId") + " ");
		} else {
			sql.append(" SELECT count(*) FROM e_mentor e ");
		}
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public Map<String, ?> getmentordetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" SELECT t.id,t.picUrl,t.name,t.gender,t.birth,t.address,t.education,t.school,t.department,t.title,t.detail,t.requirement,t.reward,t.wishes,t.contact,t.recommend,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.count,t.number FROM e_mentor t where t.id = "
						+ args.get("id") + " ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public List<?> selectMentorTags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select e.id,e.`name` tag from e_mentor_tag e,r_mentor_tag_id r ");
		sql.append(" where r.mentor_id = " + args.get("id") + " ");
		sql.append("   and r.tag_id = e.id ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateTeacherCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_mentor SET count=count+1 where id=:id ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int startupconsult(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_startup_consult (content,authority,time,title,mentor_id,user_id) ");
		sql.append(" values (:content,:authority,:time,:title,:mentor_id,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> getstartupconsultlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from ( ");
		sql.append(" SELECT t.id, ");
		sql.append("  t.count, ");
		// sql.append(" t.mentor_id mentorId, ");
		// sql.append(" e.`name` mentor, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%m-%d %H:%i') time, ");
		sql.append(" t.title ");
		sql.append(" FROM e_startup_consult t,e_mentor e ");
		sql.append(" where t.mentor_id = e.id ");
		sql.append("   and t.authority = 0 ");
		sql.append("   and t.mentor_id = " + args.get("mentorId") + " ");
		// sql.append(" and t.user_id = "+args.get("user_id")+" ");
		sql.append(" ORDER BY t.time ASC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getstartupconsultlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) ");
		sql.append(" FROM e_startup_consult t  ");
		sql.append(" where t.authority = 0 ");
		sql.append("   and t.mentor_id = " + args.get("mentorId") + " ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getstartupconsultdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.content, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.count ");
		sql.append(" FROM e_startup_consult t,e_mentor e ");
		sql.append(" where t.id = " + args.get("id") + " ");
		sql.append(" and t.mentor_id = e.id ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateStartupConsultCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_startup_consult SET count=count+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> getstartupconsultlog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (  ");
		sql.append(" select t.id, ");
		sql.append(" t.content, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.user_id userId, ");
		sql.append(" '导师' user ");
		sql.append(" from e_startup_consult_log t,e_mentor e ");
		sql.append(" where t.consult_id = " + args.get("consult_id") + " ");
		sql.append("   and t.user_id = e.user_id ");
		sql.append(" UNION ALL ");
		sql.append(" select t.id, ");
		sql.append(" t.content, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time, ");
		sql.append(" t.user_id userId, ");
		sql.append(" e.`name` user  ");
		sql.append(" from e_startup_consult_log t,e_user e ");
		sql.append(" where t.consult_id = " + args.get("consult_id") + " ");
		sql.append(" and t.user_id = e.id ");
		sql.append(
				" and t.user_id NOT IN ( select t.user_id from e_startup_consult_log t,e_mentor e where t.consult_id = "
						+ args.get("consult_id") + " and t.user_id = e.user_id) ");
		sql.append(" ) a ");
		sql.append(" ORDER BY a.time desc LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getstartupconsultlogNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) ");
		sql.append(" from e_startup_consult_log t ");
		sql.append(" where t.consult_id = " + args.get("consult_id") + " ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int choosementor(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_mentor_employ (time,mentor_id,user_id,company) ");;
		sql.append(" values (:time,:mentor_id,:user_id,:company) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> selectchoosementor(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_mentor_employ t where t.user_id = " + args.get("user_id") + " and t.mentor_id = "
				+ args.get("mentor_id") + "  ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateChooseTeacherNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_mentor SET number=number+1 where id=:mentor_id ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public int evaluatementor(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_mentor_comment (content,time,mentor_id,user_id) ");
		sql.append(" values (:content,:time,:mentor_id,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> getmentorcomment(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" select * from ( select t.id,t.content,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time,t.user_id userId,e.`name` user from e_mentor_comment t,e_user e ");
		sql.append(" where t.mentor_id = " + args.get("id") + "  ");
		sql.append("   and t.user_id = e.id  ");
		sql.append(" ORDER BY t.time desc LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getmentorcommentNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_mentor_comment t ");
		sql.append(" where t.mentor_id = " + args.get("id") + "  ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int updateStartUpConsultLog(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_startup_consult_log (consult_id,time,content,user_id) ");
		sql.append(" values (:consult_id,:time,:message,:user_id) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> getIndustrialbaselist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from ( ");
		sql.append(" SELECT t.id, ");
		sql.append("  t.title, ");
		sql.append("  t.introduction, ");
		sql.append("  t.district, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%m-%d %H:%i') time, ");
		sql.append(" t.introductimg ");
		sql.append(" FROM e_enterprise t");
		if("".equals(args.get("city")) || args.get("city") == null){
			sql.append(" where SUBSTR(t.district,1,4) like '%%' ");
		}else {
			sql.append(" where SUBSTR(t.district,1,4) = " + args.get("city") + " ");
		}
		if("".equals(args.get("industry")) || args.get("industry") == null){
			sql.append("   and t.industry like '%%' ");
		}else {
			sql.append("   and t.industry = " + args.get("industry") + " ");
		}
		sql.append(" ORDER BY t.time DESC LIMIT " + args.get("startNum") + "," + args.get("num") + " ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getIndustrialbaselistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) ");
		sql.append(" FROM e_enterprise t");
		if("".equals(args.get("city")) || args.get("city") == null){
			sql.append(" where SUBSTR(t.district,1,4) like '%%' ");
		}else {
			sql.append(" where SUBSTR(t.district,1,4) = " + args.get("city") + " ");
		}
		if("".equals(args.get("industry")) || args.get("industry") == null){
			sql.append("   and t.industry like '%%' ");
		}else {
			sql.append("   and t.industry = " + args.get("industry") + " ");
		}
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public Map<String,?> getIndustrialbasedetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id, ");
		sql.append(" t.title, ");
		sql.append(" t.introduction, ");
		sql.append(" t.introductimg, ");
		sql.append(" t.city, ");
		sql.append(" t.district, ");
		sql.append(" t.location, ");
//		sql.append(" t.slideimg, ");
//		sql.append(" t.productionimg, ");
		sql.append(" t.boardroom, ");
		sql.append(" t.restingarea, ");
		sql.append(" t.freecoffee, ");
		sql.append(" t.detail, ");
		sql.append(" t.printing, ");
		sql.append(" t.wifi, ");
		sql.append(" FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time ");
		sql.append(" FROM e_enterprise t ");
		sql.append(" where t.id = " + args.get("id") + " ) a ");
		return this.getQueryMap(sql.toString());
	}
	
	@Override
	public Map<String,?> getIndustrialbasedetail1(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( SELECT t.id, ");
		sql.append(" t.slideimg, ");
		sql.append(" t.productionimg ");
		sql.append(" FROM e_enterprise t ");
		sql.append(" where t.id = " + args.get("id") + " ) a ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public int updateIndustrialbaseCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_enterprise SET count=count+1 where id=" + args.get("id") + " ");
		return this.doSaveAsUpdate(sql.toString());
	}

	

}
