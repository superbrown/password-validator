package com.superbrown.service.passwordValidator.bo.rule;

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration;

/**
 * Created by Mike on 2/22/2017.
 */
public class NoRepeatingAdjacentCharacterSequencesRule extends ValidationRule {

	public NoRepeatingAdjacentCharacterSequencesRule(PasswordValidatorConfiguration configuration) {

		super(configuration);
	}

	public boolean isAValidPassword(String username, String password) {

		for (int sequenceLength = 2; sequenceLength <= password.length() / 2; sequenceLength++) {

			for (int startIndex = 0;
				 startIndex <= (password.length() - (sequenceLength * 2));
				 startIndex++) {

				int endIndex = startIndex + sequenceLength;
				String sequence = password.substring(startIndex, endIndex);
				String adjacentSequence = password.substring(endIndex, endIndex + sequenceLength);

				if (sequence.equals(adjacentSequence)) {
					return false;
				}
			}
		}

		return true;
	}
}
