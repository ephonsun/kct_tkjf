package com.framework.web.beanTask;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.framework.web.service.LoginService;

@Component
public class JobTask {
	
	private static int id = 0;
	
	@Resource
	private LoginService loginService;
	
	
	
	
	
	/**  
     * 定时计算。每天凌晨 01:00 执行一次  
	 * @throws Exception 
     */    
//    @Scheduled(cron = "0/30 * * * * ?")   
//    public void show() throws Exception{
//    	id += 1;
//        this.loginService.printtxt(id);
//    }

}
