package com.framework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.MngDishonestyEService;

@Service("mngDishonestyEService")
public class MngDishonestyEServiceImpl extends BaseServiceImpl implements MngDishonestyEService {

	@Override
	public int pubdishonesty(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if("0".equals(args.get("lx"))){
			sql.append(" insert into e_dishonest_person (name,gender,age,idCardNumber,area,amount,publisher,department) ");
			sql.append(" values (:name,:gender,:age,:idCardNumber,:area,:amount,:publisher,:department) ");
		}else{
			sql.append(" insert into e_dishonest_corporation (corporation,type,code,name,amount,publisher) ");
			sql.append(" values (:corporation,:type,:code,:name,:amount,:publisher) ");
		}
		
		return this.doSaveAsUpdate(sql.toString(), args);
	}

	@Override
	public List<?> selectdislist(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		if("0".equals(args.get("lx"))){
			sql.append(" SELECT * FROM e_dishonest_person e ");
			sql.append(" ORDER BY e.id LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		}else{
			sql.append(" SELECT * FROM e_dishonest_corporation e ");
			sql.append(" ORDER BY e.id LIMIT " + args.get("startNum") + "," + args.get("num") + " ");
		}
		return this.getQueryList(sql.toString());
	}

	@Override
	public int selectdislistNum(Map<String, Object> args) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> getdisdetail(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM e_dishonest_person e where e.id = "+args.get("id")+" ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int dis_update(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_dishonest_person SET name=:name,gender=:gender,age=:age,idCardNumber=:idCardNumber,area=:area,amount=:amount,publisher=:publisher,department=:department WHERE id =:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int dis_updateZt(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_dishonest_person SET state=:state where id=:id ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

}
