package com.framework.web.test;


public class MoneyApply {
	private String company;
	private String name;
	private String mobile;
	private String money;
	private String memo;
	private String time;

	public MoneyApply() {
	}

	public MoneyApply(String company, String name, String mobile, String money, String memo,String time) {
		this.company = company;
		this.name = name;
		this.mobile = mobile;
		this.money = money;
		this.memo = memo;
		this.time = time;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

	
}