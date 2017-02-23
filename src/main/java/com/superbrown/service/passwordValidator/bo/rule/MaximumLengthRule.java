package com.superbrown.service.passwordValidator.bo.rule;

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration;

/**
 * Created by Mike on 2/22/2017.
 */
public class MaximumLengthRule extends ValidationRule {

	protected Integer maximumLengthAllowed;

	public MaximumLengthRule(PasswordValidatorConfiguration configuration) {

		super(configuration);
		maximumLengthAllowed = configuration.getMaximumLengthAllowed();
	}

	public boolean isAValidPassword(String username, String password) {

		return password.length() <= maximumLengthAllowed;
	}
}
