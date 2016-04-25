package com.framework.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.framework.core.entity.SessionBean;
import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.core.utils.GlobalConfig;
import com.framework.core.utils.GlobalConst;
import com.framework.web.service.LoginService;

/**
 * 登录
 * @author fandc
 *
 */
@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

	/**
	 * 获取人员session信息
	 */
	public SessionBean getSessionBean(String mobile,String password) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * from e_user t ");
		buf.append(" WHERE t.mobile = "+mobile+" ");
		buf.append(" and t.password = '"+password+"' ");
		return this.getInfo(buf.toString(), SessionBean.class);
	}

	@Override
	public List<?> selectuser(String mobile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<?> doSelect_sms(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_sms where mobile =:mobile and smsCode=:smsCode ");
		return this.getQueryList(sql.toString(),args);
	}

	@Override
	public int updatePassword(Map<String, ?> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET password=:password WHERE mobile ="+args.get("mobile")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	@Override
	public Map<String, ?> doSelect_sms(String mobile) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_sms where mobile = "+mobile+" ");
		return this.getQueryMap(sql.toString());
	}
	
	@Override
	public int update_smstime(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_sms SET time=:time,smsCode=:sms WHERE mobile ="+args.get("mobile")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}
	
	@Override
	public int insert_sms(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into e_sms (mobile,smsCode,time) ");
		sql.append(" values (:mobile,:sms,:time) ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int updatehongbaotj(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET hongbao=hongbao+100 WHERE id ="+args.get("mobiletj")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public int updatehongbaoljtj(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET hongbao=hongbao+100 WHERE mobile ="+args.get("ljtj")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public List<?> doSelect_username(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_company where name = '"+args.get("name")+"' ");
		return this.getQueryList(sql.toString());
	}
	
	@Override
	public List<?> doSelect_company(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_company where name = '"+args.get("name")+"' ");
		return this.getQueryList(sql.toString());
	}

	@Override
	public int updateLastTime(Map<String, Object> args) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE e_user SET lastTime=:lastTime WHERE id ="+args.get("id")+" ");
		return this.doSaveAsUpdate(sql.toString(),args);
	}

	@Override
	public void printtxt(int id) throws Exception {
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		Map<String, Object> map = new HashMap<>();
		String username = "用户"+GlobalConfig.createUserName();
		sql.append(" select username from e_user where id = "+id+" ");
		map = (Map<String, Object>) this.getQueryMap(sql.toString());
		if("".equals(map.get("username")) || map.get("username") == null){
			sql2.append(" UPDATE e_user SET userName= '"+username+"' WHERE id = "+id+" ");
			this.doSaveAsUpdate(sql2.toString());
		}
		

	}

	@Override
	public Map<String, ?> selectCityCode(String city) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from e_area where city = '"+city+"' ");
		return this.getQueryMap(sql.toString());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
