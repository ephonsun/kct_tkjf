package com.framework.web.service;

import java.util.List;
import java.util.Map;

import com.framework.core.service.BaseService;

public interface MngDishonestyEService extends BaseService {
	

    public int pubdishonesty(Map<String, Object> args) throws Exception;
	
    public List<?> selectdislist(Map<String, Object> args) throws Exception;
    
    public int selectdislistNum(Map<String, Object> args) throws Exception;
    
    public List<?> getdisdetail(Map<String, Object> args) throws Exception;
    
    public int dis_update(Map<String, Object> args) throws Exception;
    
    public int dis_updateZt(Map<String, Object> args) throws Exception;

}
