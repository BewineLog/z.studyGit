package com.model2.mvc.service.domain;

import java.sql.Date;


public class User {
	
	private String userId;
	private String userName;
	private String password;
	private String role;
	private String ssn;
	private String phone;
	private String addr;
	private String email;
	private Date regDate; 
	private String regDateString;
	
	private String isKakao;
	private String isNaver;
	
	public User(){
		this.isKakao = "0";
		this.isNaver = "0";
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
//		this.regDate = Date.valueOf(regDate);
		if(regDate != null) {
			this.regDate = Date.valueOf(regDate);
			this.regDateString = regDate;
		}
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
		
		if(this.regDate != null) {
			this.regDateString = this.regDate.toString();
		}
	
	}

	public String getRegDateString() {
		return regDateString;
	}

	public void setRegDateString(String regDateString) {
		this.regDateString = regDateString;
	}

	public String getIsKakao() {
		return isKakao;
	}

	public String getIsNaver() {
		return isNaver;
	}

	public void setIsKakao(String isKakao) {
		this.isKakao = isKakao;
	}

	public void setIsNaver(String isNaver) {
		this.isNaver = isNaver;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", ssn=" + ssn + ", phone=" + phone + ", addr=" + addr + ", email=" + email + ", regDate=" + regDate
				+ "]";
	}
	
}
