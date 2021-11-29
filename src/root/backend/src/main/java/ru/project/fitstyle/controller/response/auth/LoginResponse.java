package ru.project.fitstyle.controller.response.auth;

import java.util.List;

public class LoginResponse {
	private final Long id;
	private final String email;
	private final String tokenType = "Bearer";
	private final String token;
	private final List<String> roles;

	public LoginResponse(Long id, String email, String token, List<String> roles) {
		this.id = id;
		this.email = email;
		this.token = token;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

	public List<String> getRoles() {
		return roles;
	}
}