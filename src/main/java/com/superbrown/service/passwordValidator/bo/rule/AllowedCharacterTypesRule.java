package com.superbrown.service.passwordValidator.bo.rule;

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration;

/**
 * Created by Mike on 2/22/2017.
 */
public class AllowedCharacterTypesRule extends PasswordValidationRule {

	public AllowedCharacterTypesRule(PasswordValidatorConfiguration configuration) {

		super(configuration);
	}

	public boolean isAValidPassword(String username, String password) {

		// Design note: This could likely be done with a regex, but I'm not well versed in regular
		// expressions.  =o(

		// Must consist of a mixture of lowercase letters and numerical digits only, with at least
		// one of each.

		boolean letterFound = false;
		boolean numberFound = false;

		for (int i = 0; i < password.length(); i++) {

			Character c = password.charAt(i);

			if (Character.isLowerCase(c)) {
				letterFound = true;
			}
			else if (Character.isDigit(c)) {
				numberFound = true;
			}
			else {
				// if any character is neither a lowercase letter or a number, the string isn't
				// password legal password
				return false;
			}
		}

		return letterFound && numberFound;
	}
}
