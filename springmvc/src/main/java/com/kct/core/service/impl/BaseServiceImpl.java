package com.kct.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kct.core.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<?> selectList(String statement){
		return sqlSessionTemplate.selectList(statement);
	}
	
	public List<?> selectList(String statement,Object parameter){
		return sqlSessionTemplate.selectList(statement, parameter);
	}
	
	
	

}
