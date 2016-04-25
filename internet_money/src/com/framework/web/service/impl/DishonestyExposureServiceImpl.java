package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.DishonestyExposureService;

@Service("dishonestyExposureService")
public class DishonestyExposureServiceImpl extends BaseServiceImpl implements DishonestyExposureService {
	
	@Override
	public List<?> getdishonestperson(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if("0".equals(args.get("nameNum")) && "1".equals(args.get("idCardNumberNum"))){//0 表示有参数  1表示这个参数没有传
			sql.append(" SELECT t.id, ");
			sql.append(" t.`name`, ");
			sql.append(" t.gender, ");
			sql.append(" t.age, ");
			sql.append(" CASE LENGTH(t.idCardNumber) WHEN 18 THEN CONCAT(SUBSTR(t.idCardNumber,1,10),'****',SUBSTR(t.idCardNumber,15,4)) WHEN 15 THEN CONCAT(SUBSTR(t.idCardNumber,1,7),'****',SUBSTR(t.idCardNumber,12,4)) ELSE 0 end AS idCardNumber, ");
			sql.append(" t.amount , ");
			sql.append(" t.department , ");
			sql.append(" t.area, ");
			sql.append(" t.publisher ");
			sql.append(" FROM e_dishonest_person t  ");
			sql.append(" WHERE t.name like '%"+args.get("name")+"%' ");
		}else if("1".equals(args.get("nameNum")) && "0".equals(args.get("idCardNumberNum"))){
			sql.append(" SELECT t.id, ");
			sql.append(" t.`name`, ");
			sql.append(" t.gender, ");
			sql.append(" t.age, ");
			sql.append(" CASE LENGTH(t.idCardNumber) WHEN 18 THEN CONCAT(SUBSTR(t.idCardNumber,1,10),'****',SUBSTR(t.idCardNumber,15,4)) WHEN 15 THEN CONCAT(SUBSTR(t.idCardNumber,1,7),'****',SUBSTR(t.idCardNumber,12,4)) ELSE 0 end AS idCardNumber, ");
			sql.append(" t.amount , ");
			sql.append(" t.department , ");
			sql.append(" t.area, ");
			sql.append(" t.publisher ");
			sql.append(" FROM e_dishonest_person t  ");
			sql.append(" WHERE t.idCardNumber like '%"+args.get("idCardNumber")+"%' ");
		}else if("0".equals(args.get("nameNum")) && "0".equals(args.get("idCardNumberNum"))){
			sql.append(" SELECT t.id, ");
			sql.append(" t.`name`, ");
			sql.append(" t.gender, ");
			sql.append(" t.age, ");
			sql.append(" CASE LENGTH(t.idCardNumber) WHEN 18 THEN CONCAT(SUBSTR(t.idCardNumber,1,10),'****',SUBSTR(t.idCardNumber,15,4)) WHEN 15 THEN CONCAT(SUBSTR(t.idCardNumber,1,7),'****',SUBSTR(t.idCardNumber,12,4)) ELSE 0 end AS idCardNumber, ");
			sql.append(" t.amount , ");
			sql.append(" t.department , ");
			sql.append(" t.area, ");
			sql.append(" t.publisher ");
			sql.append(" FROM e_dishonest_person t  ");
			sql.append(" WHERE t.idCardNumber like '%"+args.get("idCardNumber")+"%' ");
			sql.append("   AND t.name like '%"+args.get("name")+"%' ");
		}else{
			sql.append(" SELECT t.id, ");
			sql.append(" t.`name`, ");
			sql.append(" t.gender, ");
			sql.append(" t.age, ");
			sql.append(" CASE LENGTH(t.idCardNumber) WHEN 18 THEN CONCAT(SUBSTR(t.idCardNumber,1,10),'****',SUBSTR(t.idCardNumber,15,4)) WHEN 15 THEN CONCAT(SUBSTR(t.idCardNumber,1,7),'****',SUBSTR(t.idCardNumber,12,4)) ELSE 0 end AS idCardNumber, ");
			sql.append(" t.amount , ");
			sql.append(" t.department , ");
			sql.append(" t.area, ");
			sql.append(" t.publisher ");
			sql.append(" FROM e_dishonest_person t  ");
		}
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+"  ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getdishonestpersonNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if("0".equals(args.get("name")) && "1".equals(args.get("idCardNumber"))){
			sql.append(" SELECT count(*) FROM e_dishonest_person t  ");
			sql.append(" WHERE t.name like '%"+args.get("name")+"%' ");
		}else if("1".equals(args.get("name")) && "0".equals(args.get("idCardNumber"))){
			sql.append(" SELECT count(*) FROM e_dishonest_person t  ");
			sql.append(" WHERE t.idCardNumber like '%"+args.get("idCardNumber")+"%' ");
		}else if("0".equals(args.get("name")) && "0".equals(args.get("idCardNumber"))){
			sql.append(" SELECT count(*) FROM e_dishonest_person t  ");
			sql.append(" WHERE t.idCardNumber like '%"+args.get("idCardNumber")+"%' ");
			sql.append("   AND t.name like '%"+args.get("name")+"%' ");
		}else{
			sql.append(" SELECT count(*) FROM e_dishonest_person t  ");
		}
		return this.getQueryForInt(sql.toString());
	}

	@Override
	public List<?> getdishonestcorporation(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if("0".equals(args.get("codeNum")) && "1".equals(args.get("corporationNum"))){
			sql.append(" SELECT * FROM e_dishonest_corporation t ");
			sql.append(" WHERE t.code like '%"+args.get("code")+"%' ");
		}else if("1".equals(args.get("codeNum")) && "0".equals(args.get("corporationNum"))){
			sql.append(" SELECT * FROM e_dishonest_corporation t ");
			sql.append(" WHERE t.corporation like '%"+args.get("corporation")+"%' ");
		}else if("0".equals(args.get("codeNum")) && "0".equals(args.get("corporationNum"))){
			sql.append(" SELECT * FROM e_dishonest_corporation t ");
			sql.append(" WHERE t.corporation like '%"+args.get("corporation")+"%' ");
			sql.append("   AND t.code like '%"+args.get("code")+"%' ");
		}else{
			sql.append(" SELECT * FROM e_dishonest_corporation t ");
		}
		sql.append(" ORDER BY t.id ASC LIMIT "+args.get("startNum")+","+args.get("num")+"  ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int getdishonestcorporationNum(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if("0".equals(args.get("code")) && "1".equals(args.get("corporation"))){
			sql.append(" SELECT count(*) FROM e_dishonest_corporation t ");
			sql.append(" WHERE t.code like '%"+args.get("code")+"%' ");
		}else if("1".equals(args.get("code")) && "0".equals(args.get("corporation"))){
			sql.append(" SELECT count(*) FROM e_dishonest_corporation t ");
			sql.append(" WHERE t.corporation like '%"+args.get("corporation")+"%' ");
		}else if("0".equals(args.get("code")) && "0".equals(args.get("corporation"))){
			sql.append(" SELECT count(*) FROM e_dishonest_corporation t ");
			sql.append(" WHERE t.corporation like '%"+args.get("corporation")+"%' ");
			sql.append("   AND t.code like '%"+args.get("code")+"%' ");
		}else{
			sql.append(" SELECT count(*) FROM e_dishonest_corporation t ");
		}
		return this.getQueryForInt(sql.toString());
	}
	
	
	

}
