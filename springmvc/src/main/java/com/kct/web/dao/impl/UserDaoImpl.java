package com.kct.web.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.kct.web.dao.UserDao;
import com.kct.web.pojo.User;

public class UserDaoImpl implements UserDao {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return this.sqlSessionTemplate.delete("deleteByPrimaryKey", id);
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
