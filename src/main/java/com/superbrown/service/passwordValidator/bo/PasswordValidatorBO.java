package com.superbrown.service.passwordValidator.bo;

import com.superbrown.service.passwordValidator.bo.rule.PasswordValidationRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mike on 2/22/2017.
 */
public class PasswordValidatorBO {

	@Autowired
	protected List<PasswordValidationRule> passwordValidationRules;

	public boolean isAValidPassword(String username, String password) {

		for (PasswordValidationRule passwordValidationRule : passwordValidationRules) {

			if (passwordValidationRule.isAValidPassword(username, password) == false) {
				return false;
			}
		}

		return true;
	}
}
