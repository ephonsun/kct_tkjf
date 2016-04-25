package com.framework.core.service;

import com.framework.core.entity.LogInfo;

public interface BaseService {

	/**
	 * 保存日志
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public int doSaveAsLogInfo(LogInfo info) throws Exception;
	
	
}
