package com.framework.core.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class testbean {
	
	@NotEmpty(message="{testbean.name.not.empty}")
	private String name;
	
	@Length(max=11,min=5,message="{testbean.mobile.length.error}")
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

}
