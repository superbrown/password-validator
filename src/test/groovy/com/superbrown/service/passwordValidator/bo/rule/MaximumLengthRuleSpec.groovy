package com.superbrown.service.passwordValidator.bo.rule

import spock.lang.Specification
/**
 * Created by Mike on 2/22/2017.
 */
class MaximumLengthRuleSpec extends Specification{

    def "when length is set to 3"() {

        given:
        MaximumLengthRule rule = new MaximumLengthRule()
        rule.maximumLengthAllowed = 3

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
        result_4 == false
    }

    def "when length is set to 1"() {

        given:
        MaximumLengthRule rule = new MaximumLengthRule()
        rule.maximumLengthAllowed = 1

        when:
        boolean result_0 = rule.isAValidPassword("username", "")
        boolean result_1 = rule.isAValidPassword("username", ".")
        boolean result_2 = rule.isAValidPassword("username", "..")
        boolean result_3 = rule.isAValidPassword("username", "...")
        boolean result_4 = rule.isAValidPassword("username", "....")

        then:
        result_0 == true
        result_1 == true
        result_2 == false
        result_3 == false
        result_4 == false
    }

    def "when length is set to 0"() {

        given:
        MaximumLengthRule rule = new MaximumLengthRule()
        rule.maximumLengthAllowed = 0

        when:
        boolean result_0 = rule.isAValidPassword("username", "")
        boolean result_1 = rule.isAValidPassword("username", ".")
        boolean result_2 = rule.isAValidPassword("username", "..")
        boolean result_3 = rule.isAValidPassword("username", "...")
        boolean result_4 = rule.isAValidPassword("username", "....")

        then:
        result_0 == true
        result_1 == false
        result_2 == false
        result_3 == false
        result_4 == false
    }
}
