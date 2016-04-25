package com.framework.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.framework.core.service.impl.BaseServiceImpl;
import com.framework.web.service.OtherService;

@Service("otherService")
public class OtherServiceImpl extends BaseServiceImpl implements OtherService {

	@Override
	public List<?> getindustrylist() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM t_industry t ");
		return this.getQueryList(sql.toString());
	}

}
