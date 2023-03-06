package com.jaydeep.ems.security;

public class JwtAuthRequest {
	
	private String email;
	private String password;
	
	public JwtAuthRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public JwtAuthRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthRequest [email=" + email + ", password=" + password + "]";
	}
	
	
}
