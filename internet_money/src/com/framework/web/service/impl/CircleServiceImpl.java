package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.CircleService;

@Service("circleService")
public class CircleServiceImpl extends BaseServiceImpl implements CircleService {

	@Override
	public List<?> gethottags(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ( select a.tag id,a.`name` tag from ");
		sql.append(" ( ");
		sql.append(" SELECT count(*) count,t.tag_id tag,e.`name` from r_group_tag_post t,e_group_tag e ");
		sql.append(" WHERE t.tag_id = e.id ");
		sql.append(" GROUP BY t.tag_id ");
		sql.append(" ) a,e_group_tag b ");
		sql.append(" WHERE a.tag = b.id ");
		sql.append(" ORDER BY a.count desc limit 0,20) c ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public List<?> getgrouppostlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if(!"".equals(args.get("tagId")) && args.get("tagId") != null){
			sql.append(" select * from ( select e.id,e.title,e.count,e.comment,FROM_UNIXTIME(e.pubTime/1000,'%Y-%m-%d %H:%i:%S') pubTime,FROM_UNIXTIME(e.updateTime/1000,'%Y-%m-%d %H:%i:%S') updateTime,e.user_id userId,u.`name` user,u.avatar from r_group_tag_post p,e_group_post e,e_user u ");
			sql.append(" WHERE p.post_id = e.id  ");
			sql.append("   AND p.tag_id = "+args.get("tagId")+" ");
			sql.append("   AND e.user_id = u.id ");
			sql.append(" ORDER BY e.updateTime DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		}else{
			sql.append(" select * from ( select e.id,e.title,e.count,e.comment,FROM_UNIXTIME(e.pubTime/1000,'%Y-%m-%d %H:%i:%S') pubTime,FROM_UNIXTIME(e.updateTime/1000,'%Y-%m-%d %H:%i:%S') updateTime,e.user_id userId,u.`name` user,u.avatar from e_group_post e,e_user u  ");
			sql.append(" WHERE e.user_id = u.id ");
			sql.append(" ORDER BY e.updateTime DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		}
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public int getgrouppostdetailNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if(!"".equals(args.get("tagId")) && args.get("tagId") != null){
			sql.append(" select count(*) from r_group_tag_post p,e_group_post e,e_user u ");
			sql.append(" WHERE p.post_id = e.id  ");
			sql.append("   AND p.tag_id = "+args.get("tagId")+" ");
			sql.append("   AND e.user_id = u.id ");
		}else{
			sql.append(" select count(*) from e_group_post e,e_user u  where e.user_id = u.id ");
		}
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public Map<String,?> getgrouppostdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT t.id,t.title,t.content,t.count,t.comment,FROM_UNIXTIME(t.pubTime/1000,'%Y-%m-%d %H:%i:%S') pubTime,FROM_UNIXTIME(t.updateTime/1000,'%Y-%m-%d %H:%i:%S') updateTime,t.user_id userId,e.`name` user,e.avatar FROM e_group_post t,e_user e ");
		sql.append(" WHERE t.id = "+args.get("id")+" ");
		sql.append(" and t.user_id = e.id ) a ");
		return this.getQueryMap(sql.toString());
	}
	
	@Override
	public Map<String, ?> selectTieZiId(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_group_post t ");
		sql.append(" WHERE t.pubTime = "+args.get("pubTime")+" ");
		return this.getQueryMap(sql.toString());
	}
	
	@Override
	public List<?> getgrouppostdetail1(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT e.id,e.`name` tag ");
		sql.append(" from r_group_tag_post t,e_group_tag e ");
		sql.append(" where t.post_id = "+args.get("id")+" and t.tag_id = e.id ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateCount(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_group_post SET count=count+1 where id="+args.get("id")+" ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> getgrouppostcomment(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM (  ");
		sql.append(" SELECT t.id,t.user_id userid,e.`name` user,e.avatar,t.message,FROM_UNIXTIME(t.time/1000,'%Y-%m-%d %H:%i:%S') time  ");
		sql.append(" FROM e_group_comment t,e_user e ");
		sql.append(" where t.user_id = e.id  ");
		sql.append("   and t.post_id = "+args.get("postId")+" ");
		sql.append(" ORDER BY t.id DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		sql.append(" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getgrouppostcommentNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		//sql.append(" SELECT * FROM (  ");
		sql.append(" SELECT count(*)  ");
		sql.append(" FROM e_group_comment t,e_user e ");
		sql.append(" where t.user_id = e.id  ");
		sql.append("   and t.post_id = "+args.get("postId")+" ");
		//sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+" ");
		//sql.append(" ) a ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int pubgrouppost(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_group_post (title,content,pubTime,updateTime,user_id) ");
		sql.append(" values (:title,:content,:pubTime,:updateTime,:userid) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int pubgroupcomment(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_group_comment (user_id,time,message,post_id) ");
		sql.append(" values (:user_id,:time,:message,:post_id) ");
	return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int updateTime(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_group_post SET updateTime="+args.get("time")+",comment=comment+1 where id="+args.get("post_id")+" ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int updateComment(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_group_post SET comment=comment+1 where id="+args.get("postId")+" ");
		return this.doSaveAsUpdate(sql.toString());
	}
	
	

	@Override
	public Map<String, ?> selectTagId(String biaoqian) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.id FROM e_group_tag t ");
		sql.append(" WHERE t.name = '"+biaoqian+"' ");
		return this.getQueryMap(sql.toString());
	}

	@Override
	public List<?> selectBiaoQian(String biaoqian) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_group_tag t where t.name = '"+biaoqian+"' ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int insertTag(String biaoqian) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_group_tag (name)  values ('"+biaoqian+"') ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public int insertTag_post(String id, String tagid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into r_group_tag_post (tag_id,post_id)  values ("+tagid+","+id+") ");
		return this.doSaveAsUpdate(sql.toString());
	}

	@Override
	public List<?> getMnggrouppostlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
			sql.append(" select * from ( select e.id,e.title,e.count,e.comment,FROM_UNIXTIME(e.pubTime/1000,'%Y-%m-%d %H:%i:%S') pubTime,FROM_UNIXTIME(e.updateTime/1000,'%Y-%m-%d %H:%i:%S') updateTime,e.user_id userId,u.`name` user,u.avatar from e_group_post e,e_user u  ");
			sql.append(" WHERE e.user_id = u.id ");
			sql.append(" ORDER BY e.updateTime DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getMnggrouppostlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
			sql.append(" select count(*) from e_group_post e,e_user u  where e.user_id = u.id ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> MngTitlelist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( select e.id,e.title,e.count,e.comment,FROM_UNIXTIME(e.pubTime/1000,'%Y-%m-%d %H:%i:%S') pubTime,FROM_UNIXTIME(e.updateTime/1000,'%Y-%m-%d %H:%i:%S') updateTime,e.user_id userId,u.`name` user,u.avatar from e_group_post e,e_user u  ");
		sql.append(" WHERE e.user_id = u.id ");
		sql.append("   AND e.title like '%"+args.get("title")+"%' ");
		sql.append(" ORDER BY e.updateTime DESC LIMIT "+args.get("startNum")+","+args.get("num")+" ) a ");
	return this.getQueryList(sql.toString());
	}

	@Override
	public int MngTitlelistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from e_group_post e,e_user u  where e.user_id = u.id AND e.title like '%"+args.get("title")+"%' ");
	return this.getQueryForInt(sql.toString());
	}

	

	

	

	
	
	

}
