package com.superbrown.service.passwordValidator.controller;

import com.superbrown.service.passwordValidator.bo.PasswordValidatorBO;
import com.superbrown.service.passwordValidator.model.ValidationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mike on 2/22/2017.
 */

@RestController
public class PasswordValidatorController {

	@Autowired
	protected PasswordValidatorBO passwordValidatorBO;

	@RequestMapping(value="/validatePassword", method = RequestMethod.GET)
	public ValidationState validatePassword(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password) {

		boolean isAValidPassword = passwordValidatorBO.isAValidPassword(username, password);

		return new ValidationState(username, password, isAValidPassword);
	}
}
