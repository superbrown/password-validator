package com.superbrown.service.passwordValidator.bo.rule;

import org.springframework.stereotype.Component;

/**
 * Created by Mike on 2/22/2017.
 */
@Component
public class NoRepeatingAdjacentCharacterSequencesRule implements PasswordValidationRule {

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
