package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.MngMentorService;

@Service("mngMentorService")
public class MngMentorServiceImpl extends BaseServiceImpl implements MngMentorService {

	@Override
	public int pubmentor(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" insert into e_mentor (picUrl,name,gender,birth,address,education,school,department,title,detail) ");
		sql.append(
				" values (:picUrl,:name,:gender,:birth,:address,:education,:school,:department,:title,:detail) ");
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> selectmentorlist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT e.id,e.name,e.department,e.title,e.recommend,e.count FROM e_mentor e ");
		sql.append(" ORDER BY e.id LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int selectmentorlistNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) FROM e_mentor e ");
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public int mentor_update(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_mentor SET picUrl=:picUrl,name=:name,gender=:gender,birth=:birth,address=:address,education=:education,school=:school,department=:department,title=:title,detail=:detail,user_id=:user_id WHERE id =:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int mentor_updateZt(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_mentor SET state=:state where id=:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	
	
	
	
	
	
	
	

}
