package com.framework.web.testwebservice.service;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAnyElement;

@WebService
public interface testService {
	
	@XmlAnyElement
	public String sayHello(String name);

}
