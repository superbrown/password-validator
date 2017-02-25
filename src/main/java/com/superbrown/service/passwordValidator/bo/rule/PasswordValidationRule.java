package com.superbrown.service.passwordValidator.bo.rule;

/**
 * Created by Mike on 2/22/2017.
 */
public interface PasswordValidationRule {

	boolean isAValidPassword(String username, String string);
}
