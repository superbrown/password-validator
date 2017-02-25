package com.superbrown.service.passwordValidator.bo.rule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Mike on 2/22/2017.
 */
@Component
public class MaximumLengthRule implements PasswordValidationRule {

	@Value("${passwordValidator.rule.maximumLength}")
	protected Integer maximumLengthAllowed;

	public boolean isAValidPassword(String username, String password) {

		return password.length() <= maximumLengthAllowed;
	}
}
