package com.superbrown.service.passwordValidator.bo.rule;

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration;
import com.superbrown.service.passwordValidator.dao.PasswordDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mike on 2/22/2017.
 */
public class NotARecentPasswordRule extends ValidationRule {

	public static final int NUMBER_OF_PASSWORDS_IN_HISOTRY_WE_CARE_ABOUT = 3;

	@Autowired
	protected PasswordDAO passwordDAO = new PasswordDAO();

	public NotARecentPasswordRule(PasswordValidatorConfiguration configuration) {

		super(configuration);
		passwordDAO = configuration.createPasswordDAO();
	}

	public boolean isAValidPassword(String username, String password) {

		List<String> recentlyUsedPasswords =
				passwordDAO.getRecentlyUsedPasswords(
						username,
						NUMBER_OF_PASSWORDS_IN_HISOTRY_WE_CARE_ABOUT);

		return recentlyUsedPasswords.contains(password) == false;
	}
}
