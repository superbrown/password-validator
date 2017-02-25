package com.superbrown.service.passwordValidator.bo.rule;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Mike on 2/22/2017.
 */
public class MinimumLengthRule implements PasswordValidationRule {

	@Value("${passwordValidator.rule.minimumLength}")
	protected Integer minimumLengthAllowed;

	public boolean isAValidPassword(String username, String password) {

		return password.length() >= minimumLengthAllowed;
	}
}
