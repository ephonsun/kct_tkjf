package com.framework.web.batchSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class MutexBatchInsertStatementSetter implements BatchPreparedStatementSetter {

	final List temList;
	
	public MutexBatchInsertStatementSetter(List temList) {
		this.temList = temList;
	}

	@Override
	public int getBatchSize() {
		return temList.size();
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		Map<String,String> info = (Map)temList.get(i);   
        ps.setString(1, info.get("time"));
        ps.setString(2, info.get("text"));
	}

}
