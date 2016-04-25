package com.framework.web.testwebservice.test;

import javax.jws.WebService;

import com.framework.web.testwebservice.service.testService;

@WebService(endpointInterface="com.framework.web.testwebservice.service.testService")
public class testWebService implements testService {

	public static void main(String[] args) {
		
	}

	@Override
	public String sayHello(String name) {
		
		return "Hello123123123:"+name;
	}
	
	
	

}
