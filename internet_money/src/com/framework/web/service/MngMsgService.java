package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface MngMsgService extends BaseService {
	
	public List<?> selectnews(Map<String, Object> args) throws Exception;
	
	public List<?> selectnewslist(Map<String, Object> args) throws Exception;
	
	public int updatenewstag(Map<String, Object> args) throws Exception;
	
	public List<?> waitermsglist(Map<String, Object> args) throws Exception;
	
	public int waitermsgzt(Map<String, Object> args) throws Exception;
	
	public int selectnewslistNum(Map<String, Object> args) throws Exception;
	
	public int delete_news(Map<String, Object> args) throws Exception;
	
	public int pubnews(Map<String, Object> args) throws Exception;
	
	public int news_updateZt(Map<String, Object> args) throws Exception;
	
	public int news_edit(Map<String, Object> args) throws Exception;
	
	public int news_editTj(Map<String, Object> args) throws Exception;

}
