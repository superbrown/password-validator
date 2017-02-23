package com.superbrown.service.passwordValidator.bo;

import com.superbrown.service.passwordValidator.bo.rule.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mike on 2/22/2017.
 */
public class PasswordValidatorBO {

	@Autowired
	protected List<ValidationRule> validationRules;

	public boolean isALegalPassword(String username, String password) {

		for (ValidationRule validationRule : validationRules) {

			if (validationRule.isAValidPassword(username, password) == false) {
				return false;
			}
		}

		return true;
	}
}
