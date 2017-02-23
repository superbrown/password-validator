package com.superbrown.service.passwordValidator.bo

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration
import com.superbrown.service.passwordValidator.dao.PasswordDAO
import com.superbrown.service.passwordValidator.bo.rule.*
import spock.lang.Specification

/**
 * Created by Mike on 2/22/2017.
 */
class PasswordValidatorBOSpec extends Specification{

    def "one validation fails"() {

        given:
        PasswordValidationRule validationRule_1 = Mock()
        PasswordValidationRule validationRule_2 = Mock()
        PasswordValidationRule validationRule_3 = Mock()

        List<PasswordValidationRule> validationRules = new ArrayList<>()
        validationRules.add(validationRule_1)
        validationRules.add(validationRule_2)
        validationRules.add(validationRule_3)

        PasswordValidatorBO bo = new PasswordValidatorBO()
        bo.passwordValidationRules = validationRules

        when:
        boolean returnValue = bo.isAValidPassword("username", "password")

        then:
        validationRule_1.isAValidPassword("username", "password") >> true
        validationRule_2.isAValidPassword("username", "password") >> true
        validationRule_3.isAValidPassword("username", "password") >> false

        returnValue == false
    }

    def "two validations fail"() {

        given:
        PasswordValidationRule validationRule_1 = Mock()
        PasswordValidationRule validationRule_2 = Mock()
        PasswordValidationRule validationRule_3 = Mock()

        List<PasswordValidationRule> validationRules = new ArrayList<>()
        validationRules.add(validationRule_1)
        validationRules.add(validationRule_2)
        validationRules.add(validationRule_3)

        PasswordValidatorBO bo = new PasswordValidatorBO()
        bo.passwordValidationRules = validationRules

        when:
        boolean returnValue = bo.isAValidPassword("username", "password")

        then:
        validationRule_1.isAValidPassword("username", "password") >> false
        validationRule_2.isAValidPassword("username", "password") >> true
        validationRule_3.isAValidPassword("username", "password") >> false

        returnValue == false
    }

    def "three validations fail"() {

        given:
        PasswordValidationRule validationRule_1 = Mock()
        PasswordValidationRule validationRule_2 = Mock()
        PasswordValidationRule validationRule_3 = Mock()

        List<PasswordValidationRule> validationRules = new ArrayList<>()
        validationRules.add(validationRule_1)
        validationRules.add(validationRule_2)
        validationRules.add(validationRule_3)

        PasswordValidatorBO bo = new PasswordValidatorBO()
        bo.passwordValidationRules = validationRules

        when:
        boolean returnValue = bo.isAValidPassword("username", "password")

        then:
        validationRule_1.isAValidPassword("username", "password") >> false
        validationRule_2.isAValidPassword("username", "password") >> false
        validationRule_3.isAValidPassword("username", "password") >> false

        returnValue == false
    }

    def "various real scenarios"() {

        given:
        PasswordDAO passwordDAO = new PasswordDAO()
        PasswordValidatorConfiguration configuration = Spy(PasswordValidatorConfiguration)
        configuration.getMinimumLengthAllowed() >> 5
        configuration.getMaximumLengthAllowed() >> 12
        configuration.createPasswordValidatorBO() >> passwordDAO

        AllowedCharacterTypesRule allowedCharacterTypesRule = new AllowedCharacterTypesRule(configuration)

        MinimumLengthRule minimumLengthRule = new MinimumLengthRule(configuration)

        MaximumLengthRule maximumLengthRule = new MaximumLengthRule(configuration)

        NoRepeatingAdjacentCharacterSequencesRule noRepeatingAdjacentCharacterSequencesRule =
                new NoRepeatingAdjacentCharacterSequencesRule(configuration)

        NotARecentPasswordRule notARecentPasswordRule = new NotARecentPasswordRule(configuration)
        notARecentPasswordRule.passwordDAO = passwordDAO

        List<PasswordValidationRule> validationRules = new ArrayList<>()
        validationRules.add(allowedCharacterTypesRule)
        validationRules.add(minimumLengthRule)
        validationRules.add(maximumLengthRule)
        validationRules.add(noRepeatingAdjacentCharacterSequencesRule)
        validationRules.add(notARecentPasswordRule)

        when:
        PasswordValidatorBO bo = new PasswordValidatorBO()
        bo.passwordValidationRules = validationRules

        then:

        // Must consist of a mixture of lowercase letters and numerical digits only, with at least
        // one of each.
        bo.isAValidPassword("username", "password22") == true
        bo.isAValidPassword("username", "Password22") == false
        bo.isAValidPassword("username", "password22!") == false
        bo.isAValidPassword("username", "password 22") == false

        // Must be between 5 and 12 characters in length.
        bo.isAValidPassword("username", "a1") == false
        bo.isAValidPassword("username", "a123") == false
        bo.isAValidPassword("username", "a1234") == true
        bo.isAValidPassword("username", "a12345") == true
        bo.isAValidPassword("username", "a123456") == true
        bo.isAValidPassword("username", "a1234567") == true
        bo.isAValidPassword("username", "a12345678") == true
        bo.isAValidPassword("username", "a123456789") == true
        bo.isAValidPassword("username", "a1234567890") == true
        bo.isAValidPassword("username", "a12345678901") == true
        bo.isAValidPassword("username", "a123456789012") == false

        // Must not contain any sequence of characters immediately followed by the same sequence.
        bo.isAValidPassword("username", "a1231238") == false

        // The password may not be the same as any of the users three most recent passwords.
        // (We can't yet test this because it hasn't been fully implemented. The current
        // implementation always passes.)
    }
}
