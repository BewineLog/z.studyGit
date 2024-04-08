package com.model2.mvc.service.domain;

public class OAuthToken {

	private String access_token;
	private String token_type;
	private String refresh_token;
	private String scope;
	
	private int expires_in;
	private int refresh_token_expires_in;
	
	
	public OAuthToken() {
		// TODO Auto-generated constructor stub
	}


	public String getAccess_token() {
		return access_token;
	}


	public String getToken_type() {
		return token_type;
	}


	public String getRefresh_token() {
		return refresh_token;
	}


	public String getScope() {
		return scope;
	}


	public int getExpires_in() {
		return expires_in;
	}


	public int getRefresh_token_expires_in() {
		return refresh_token_expires_in;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}


	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}


	public void setScope(String scope) {
		this.scope = scope;
	}


	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}


	public void setRefresh_token_expires_in(int refresh_token_expires_in) {
		this.refresh_token_expires_in = refresh_token_expires_in;
	}


	@Override
	public String toString() {
		return "OAuthToken [access_token=" + access_token + ", token_type=" + token_type + ", refresh_token="
				+ refresh_token + ", scope=" + scope + ", expires_in=" + expires_in + ", refresh_token_expires_in="
				+ refresh_token_expires_in + "]";
	}

}
