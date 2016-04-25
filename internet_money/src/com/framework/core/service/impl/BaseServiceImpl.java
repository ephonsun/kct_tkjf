package com.framework.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Service;

import com.framework.core.dao.BaseDao;
import com.framework.core.entity.LogInfo;
import com.framework.core.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	@Resource
    private BaseDao baseDao;
	
	public Object doSaveAsExecute(String sql,CallableStatementCallback<?> cs) throws Exception{
		return baseDao.doSaveAsExecute(sql, cs);
	}

	public Object doSaveAsExecute(String sql,String clobStr) throws Exception{
		return baseDao.doSaveAsExecute(sql, clobStr);
	}
	
	public int doSaveAsUpdate(String sql) throws Exception {
		return baseDao.doSaveAsUpdate(sql);
	}

	public int doSaveAsUpdate(String sql, Map<String, ?> args) throws Exception {
		return baseDao.doSaveAsUpdate(sql, args);
	}

	public List<?> getQueryList(String sql) throws Exception {
		return baseDao.getQueryList(sql);
	}
	
	public int getQueryForInt(String sql) throws Exception {
		return baseDao.getQueryForInt(sql);
	}

	public List<?> getQueryList(String sql, Map<String, ?> args) throws Exception {
		return baseDao.getQueryList(sql, args);
	}

	public Map<String, ?> getQueryMap(String sql) throws Exception {
		return baseDao.getQueryMap(sql);
	}
	
	public <T> T getInfo(String sql,Class<T> entityClass) throws Exception {
		return baseDao.getInfo(sql,entityClass);
	}

	public <T> T getInfo(String sql, Map<String, ?> args,Class<T> entityClass) throws Exception {
		return baseDao.getInfo(sql, args,entityClass);
	}
	
	public <T> List<T> getQueryList(String sql, Class<T> entityClass) throws Exception {
		return this.baseDao.getQueryList(sql, entityClass); 
	}

	public <T> List<T> getQueryList(String sql, Map<String, ?> args, Class<T> entityClass) throws Exception {
		return this.baseDao.getQueryList(sql,args,entityClass); 
	}

	public Map<String, ?> getQueryMap(String sql, Map<String, ?> args) throws Exception {
		try {
			return baseDao.getQueryMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
		}
		return new HashMap<String, Object>();
	}

	public String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}

	public int[] doSaveAsBatchUpdate(String sql,BatchPreparedStatementSetter batch) throws Exception{
		return baseDao.doSaveAsBatchUpdate(sql, batch);
	}
	
	/**
	 * 保存日志
	 */
	@Override
	public int doSaveAsLogInfo(LogInfo info) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();  
		return this.doSaveAsUpdate(sql.toString(),paramMap); 
	}

	
}
