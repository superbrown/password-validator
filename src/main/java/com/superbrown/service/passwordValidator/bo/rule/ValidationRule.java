package com.superbrown.service.passwordValidator.bo.rule;

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration;

/**
 * Created by Mike on 2/22/2017.
 */
public abstract class ValidationRule {

	protected PasswordValidatorConfiguration configuration;

	public ValidationRule(PasswordValidatorConfiguration configuration) {
		this.configuration = configuration;
	}

	abstract public boolean isAValidPassword(String username, String string);

}
