package com.framework.web.batchSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class MutexChangeFlowBatchInsertStatementSetter implements BatchPreparedStatementSetter {

	final List temList;   
    public MutexChangeFlowBatchInsertStatementSetter(List list){   
        temList = list;   
    } 
	
	@Override
	public int getBatchSize() {
		return temList.size();
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		Map<String,String> info = (Map)temList.get(i);   
        ps.setString(1, info.get("text"));
	}

}
