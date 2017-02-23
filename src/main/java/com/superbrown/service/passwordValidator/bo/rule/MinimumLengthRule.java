package com.superbrown.service.passwordValidator.bo.rule;

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration;

/**
 * Created by Mike on 2/22/2017.
 */
public class MinimumLengthRule extends PasswordValidationRule {

	protected Integer minimumLengthAllowed;

	public MinimumLengthRule(PasswordValidatorConfiguration configuration) {

		super(configuration);
		minimumLengthAllowed = configuration.getMinimumLengthAllowed();
	}

	public boolean isAValidPassword(String username, String password) {

		return password.length() >= minimumLengthAllowed;
	}
}
