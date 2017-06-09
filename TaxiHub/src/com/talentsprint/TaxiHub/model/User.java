package com.talentsprint.TaxiHub.model;

public class User {
	String user_name;
	String phone_number;
	String password;
	
	public User(String user_name, String phone_number, String password) {
		super();
		this.user_name = user_name;
		this.phone_number = phone_number;
		this.password = password;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
