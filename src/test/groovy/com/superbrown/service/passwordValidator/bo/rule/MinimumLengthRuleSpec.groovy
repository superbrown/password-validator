package com.superbrown.service.passwordValidator.bo.rule

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration
import spock.lang.Specification

/**
 * Created by Mike on 2/22/2017.
 */
class MinimumLengthRuleSpec extends Specification{

    def "when length is set to 3"() {

        given:
        PasswordValidatorConfiguration configuration = new PasswordValidatorConfiguration()
        configuration.minimumLengthAllowed = 3

        MinimumLengthRule rule = new MinimumLengthRule(configuration)

        when:
        boolean result_0 = rule.isAValidPassword("username", "")
        boolean result_1 = rule.isAValidPassword("username", ".")
        boolean result_2 = rule.isAValidPassword("username", "..")
        boolean result_3 = rule.isAValidPassword("username", "...")
        boolean result_4 = rule.isAValidPassword("username", "....")

        then:
        result_0 == false
        result_1 == false
        result_2 == false
        result_3 == true
        result_4 == true
    }

    def "when length is set to 1"() {

        given:
        PasswordValidatorConfiguration configuration = new PasswordValidatorConfiguration()
        configuration.minimumLengthAllowed = 1

        MinimumLengthRule rule = new MinimumLengthRule(configuration)

        when:
        boolean result_0 = rule.isAValidPassword("username", "")
        boolean result_1 = rule.isAValidPassword("username", ".")
        boolean result_2 = rule.isAValidPassword("username", "..")
        boolean result_3 = rule.isAValidPassword("username", "...")
        boolean result_4 = rule.isAValidPassword("username", "....")

        then:
        result_0 == false
        result_1 == true
        result_2 == true
        result_3 == true
        result_4 == true
    }

    def "when length is set to 0"() {

        given:
        PasswordValidatorConfiguration configuration = new PasswordValidatorConfiguration()
        configuration.minimumLengthAllowed = 0

        MinimumLengthRule rule = new MinimumLengthRule(configuration)

        when:
        boolean result_0 = rule.isAValidPassword("username", "")
        boolean result_1 = rule.isAValidPassword("username", ".")
        boolean result_2 = rule.isAValidPassword("username", "..")
        boolean result_3 = rule.isAValidPassword("username", "...")
        boolean result_4 = rule.isAValidPassword("username", "....")

        then:
        result_0 == true
        result_1 == true
        result_2 == true
        result_3 == true
        result_4 == true
    }
}
