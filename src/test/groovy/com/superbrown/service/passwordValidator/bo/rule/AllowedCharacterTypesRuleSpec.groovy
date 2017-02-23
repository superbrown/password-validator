package com.superbrown.service.passwordValidator.bo.rule

import spock.lang.Specification

/**
 * Created by Mike on 2/22/2017.
 */
class AllowedCharacterTypesRuleSpec extends Specification{

    def "valid characters"() {

        given:

        when:
        AllowedCharacterTypesRule rule = new AllowedCharacterTypesRule()

        then:
        rule.isAValidPassword("username", "password22") == true
        rule.isAValidPassword("username", "22password") == true
        rule.isAValidPassword("username", "password22") == true
        rule.isAValidPassword("username", "p2") == true
        rule.isAValidPassword("username", "2p") == true
    }

    def "contains only lower case characters"() {

        given:

        when:
        AllowedCharacterTypesRule rule = new AllowedCharacterTypesRule()

        then:
        rule.isAValidPassword("username", "aaaaa") == false
        rule.isAValidPassword("username", "a") == false
        rule.isAValidPassword("username", "abc") == false
    }

    def "contains only number characters"() {

        given:

        when:
        AllowedCharacterTypesRule rule = new AllowedCharacterTypesRule()

        then:
        rule.isAValidPassword("username", "12") == false
        rule.isAValidPassword("username", "1") == false
        rule.isAValidPassword("username", "123") == false
    }

    def "contains an uppercase letter"() {

        given:

        when:
        AllowedCharacterTypesRule rule = new AllowedCharacterTypesRule()

        then:
        rule.isAValidPassword("userName1", "Password1") == false
        rule.isAValidPassword("Username1", "passWord1") == false
        rule.isAValidPassword("usernamE1", "1passworD") == false
        rule.isAValidPassword("usernamE1", "1PASSWORD") == false
    }

    def "contains a special character"() {

        given:

        when:
        AllowedCharacterTypesRule rule = new AllowedCharacterTypesRule()

        then:
        rule.isAValidPassword("username1", "password1!") == false
        rule.isAValidPassword("username1", "!password1") == false
        rule.isAValidPassword("usernamE1", "pass!word1") == false
    }

}
