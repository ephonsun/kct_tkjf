package com.framework.web.testwebservice.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.wsn.services.Service;

import com.framework.web.testwebservice.service.testService;

public class TestClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
//
//		Client client = clientFactory.createClient("http://localhost:8080/internet_money/webservice/HelloWorld?wsdl");
//
//		Object[] result = client.invoke("sayHello", "KEVIN");
//
//		System.out.println(result[0]);
		
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(testService.class);

		factory.setAddress("http://localhost:8080/internet_money/webservice/HelloWorld?");

		testService service = (testService) factory.create();

		System.out.println("[result]" + service.sayHello("flyfox"));

	}

}
