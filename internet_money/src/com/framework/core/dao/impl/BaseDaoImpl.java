package com.framework.core.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.framework.core.dao.BaseDao;
import com.framework.core.utils.DateUtils;
import com.framework.core.utils.GlobalConst;
import com.framework.core.utils.Utils;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

	private static Logger log = Logger.getLogger(BaseDaoImpl.class);
	@Resource
	private LobHandler lobHandler;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	
	@Override
	public <T> T getInfo(String sql, Map<String, ?> args,Class<T> entityClass) throws Exception {
		try{
			return this.namedParameterJdbcTemplate.queryForObject(sql, args, ParameterizedBeanPropertyRowMapper.newInstance(entityClass));
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	@Override
	public <T> T getInfo(String sql,Class<T> entityClass) throws Exception {
		try{
			return this.namedParameterJdbcTemplate.queryForObject(sql, new HashMap<String, Object>(), ParameterizedBeanPropertyRowMapper.newInstance(entityClass));
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	@Override
	public List<?> getQueryList(String sql) throws Exception {
		return this.jdbcTemplate.query(sql, getRowMapper());
	}
	
	@Override
	public int getQueryForInt(String sql) throws Exception {
		return this.jdbcTemplate.queryForInt(sql);
	}
	
	@Override
	public int doSaveAsUpdate(String sql,Map<String,?> args) throws Exception {
		return this.namedParameterJdbcTemplate.update(sql,args);
	}
	
	@Override
	public Object doSaveAsExecute(String sql,CallableStatementCallback<?> cs) throws Exception {
		return this.jdbcTemplate.execute(sql, cs);
	}
	
	@Override
	public int doSaveAsUpdate(String sql) throws Exception {
		return this.namedParameterJdbcTemplate.update(sql, new HashMap<String, Object>());
	}

	@Override
	public List<?> getQueryList(String sql, Map<String, ?> args) throws Exception {
		return this.namedParameterJdbcTemplate.query(sql,args, getRowMapper());
	}

	@Override
	public Map<String, Object> getQueryMap(String sql) throws Exception {
		try{
			return this.namedParameterJdbcTemplate.queryForMap(sql, new HashMap<String, Object>());
		}catch(EmptyResultDataAccessException e){
			return null;
		}catch(IncorrectResultSizeDataAccessException ee){
			return null;
		}
	}

	@Override
	public Map<String, Object> getQueryMap(String sql, Map<String, ?> args) throws Exception {
		return this.namedParameterJdbcTemplate.queryForMap(sql, args);
	}
	

	private RowMapper<?> getRowMapper() {
		return new RowMapper<Object>(){
			private ResultSetMetaData rsmd;
			private int columns;
			int type = 0;
			String name = null;
			Object value = null;
			Map<String, Object> values = null;
			public Object mapRow(ResultSet rs, int arg1) throws SQLException { 
				if (rs.isFirst()){
					this.rsmd = rs.getMetaData();
					this.columns = this.rsmd.getColumnCount();
				}
				values = new HashMap<String, Object>();
				for (int i = 1; i <= this.columns; ++i){
					type = this.rsmd.getColumnType(i);
					name = this.rsmd.getColumnName(i).toLowerCase();
					value = rs.getObject(i);
					if("ORACLE".equals(GlobalConst.DIALECT)){
						if(Types.CLOB==type){
							values.put(name, Utils.ClobToStr(rs.getClob(i)));
						}else if(Types.BLOB==type){
							values.put(name, Utils.BlobToStr(rs.getBlob(i)));
						}else if(Types.TIMESTAMP==type){
							if(value==null){
								values.put(name, value);
							}else{
								values.put(name, DateUtils.dateTimeFormat(value.toString(), GlobalConst.DATE_PATTERN_YMDHMS));
							}
						}else{
							values.put(name, value);
						}
					}
				}
				return values;
			}};
	}

	public int[] doSaveAsBatchUpdate(String sql,BatchPreparedStatementSetter batch){
		return this.jdbcTemplate.batchUpdate(sql, batch);
	}
	
	@Override
	public <T> List<T> getQueryList(String sql, Class<T> entityClass) throws Exception {
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(entityClass)); 
	}

	@Override
	public <T> List<T> getQueryList(String sql, Map<String, ?> args, Class<T> entityClass) throws Exception {
		return this.namedParameterJdbcTemplate.query(sql,args,new BeanPropertyRowMapper<T>(entityClass)); 
	}

	@Override
	public Object doSaveAsExecute(String sql, final String clobStr) throws Exception {
		int flag = 1;
		try {
			this.jdbcTemplate.execute(sql,
				new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
					protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
						lobCreator.setClobAsString(ps, 1, clobStr);
					}
				});
		} catch (RuntimeException re) {
			flag = -1;
			throw re;
		} 
		return flag;
	}
}
