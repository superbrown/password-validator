package com.superbrown.service.passwordValidator.bo.rule

import spock.lang.Specification

/**
 * Created by Spock on 2/22/2017.
 */
class NoRepeatingAdjacentCharacterSequencesRuleSpec extends Specification{

    def "it passes for a number of combinations"() {

        given:

        when:
        NoRepeatingAdjacentCharacterSequencesRule rule = new NoRepeatingAdjacentCharacterSequencesRule()

        then:
        rule.isAValidPassword("username", "_._SpockSpock") == false
        rule.isAValidPassword("username", "SpockSpock_._") == false
        rule.isAValidPassword("username", "SpockSpockSpock") == false
        rule.isAValidPassword("username", "SpockSpock") == false
        rule.isAValidPassword("username", "_Spock_Spock") == false
        rule.isAValidPassword("username", "") == true
        rule.isAValidPassword("username", "abcdef") == true
        rule.isAValidPassword("username", "abcSPSPdef") == false
        rule.isAValidPassword("username", "aaa") == true
        rule.isAValidPassword("username", "aaaa") == false
    }
}
