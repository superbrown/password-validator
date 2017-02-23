package com.superbrown.service.passwordValidator.model;

/**
 * Created by Mike on 2/22/2017.
 */
public class ValidationState {

	protected final String username;
	protected final String password;
	protected final boolean legalPassword;

	public ValidationState(String username, String password, boolean legalPassword) {
		this.username = username;
		this.password = password;
		this.legalPassword = legalPassword;
	}

	public boolean isLegalPassword() {
		return legalPassword;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
