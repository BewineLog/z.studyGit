package com.model2.mvc.service.domain;

import java.util.Map;

public class KakaoUser {
//	{"id":3424650973,"connected_at":"2024-04-08T03:04:44Z",
//		"properties":{"nickname":"이정한"},
//		"kakao_account":{"profile_nickname_needs_agreement":false,"profile":{"nickname":"이정한","is_default_nickname":false}}}
	
	private String id;
	private String connected_at;
	
	private Map<String,String> properties;
	private Map<String, Object> kakao_account;
	
	
	
	public KakaoUser() {
		// TODO Auto-generated constructor stub
	}



	public String getId() {
		return id;
	}



	public String getConnected_at() {
		return connected_at;
	}



	public Map<String, String> getProperties() {
		return properties;
	}



	public Map<String, Object> getKakao_account() {
		return kakao_account;
	}



	public void setId(String id) {
		this.id = id;
	}



	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}



	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}



	public void setKakao_account(Map<String, Object> kakao_account) {
		this.kakao_account = kakao_account;
	}



	@Override
	public String toString() {
		return "KakaoUser [id=" + id + ", connected_at=" + connected_at + ", properties=" + properties
				+ ", kakao_account=" + kakao_account + "]";
	}
	
	
	

}
