package com.superbrown.service.passwordValidator.bo.rule

import com.superbrown.service.passwordValidator.configuration.PasswordValidatorConfiguration
import com.superbrown.service.passwordValidator.dao.PasswordDAO
import spock.lang.Specification

/**
 * Created by Mike on 2/22/2017.
 */
class NotARecentPasswordRuleSpec extends Specification{

    def "scenarios where there are three passwords in history"() {

        given:
        PasswordDAO passwordDAO = Mock()
        List<String> recentlyUsedPasswords = new ArrayList<>()
        recentlyUsedPasswords.add("one")
        recentlyUsedPasswords.add("two")
        recentlyUsedPasswords.add("three")

        PasswordValidatorConfiguration configuration = Spy(PasswordValidatorConfiguration)
        configuration.createPasswordDAO() >> passwordDAO

        NotARecentPasswordRule rule = new NotARecentPasswordRule(configuration)

        boolean returnedValue_one
        boolean returnedValue_two
        boolean returnedValue_three
        boolean returnedValue_somethingElse

        when:
        returnedValue_one = rule.isAValidPassword("username", "one")
        returnedValue_two = rule.isAValidPassword("username", "two")
        returnedValue_three = rule.isAValidPassword("username", "three")
        returnedValue_somethingElse = rule.isAValidPassword("username", "somethingElse")

        then:
        4 * passwordDAO.getRecentlyUsedPasswords("username", 3) >> recentlyUsedPasswords

        returnedValue_one == false
        returnedValue_two == false
        returnedValue_three == false
        returnedValue_somethingElse == true
    }

    def "scenarios where there is only one password in history"() {

        given:
        PasswordDAO passwordDAO = Mock()
        List<String> recentlyUsedPasswords = new ArrayList<>()
        recentlyUsedPasswords.add("one")

        PasswordValidatorConfiguration configuration = Spy(PasswordValidatorConfiguration)
        configuration.createPasswordDAO() >> passwordDAO

        NotARecentPasswordRule rule = new NotARecentPasswordRule(configuration)

        when:
        boolean returnedValue_one = rule.isAValidPassword("username", "one")
        boolean returnedValue_two = rule.isAValidPassword("username", "two")
        boolean returnedValue_three = rule.isAValidPassword("username", "three")
        boolean returnedValue_somethingElse = rule.isAValidPassword("username", "somethingElse")

        then:
        4 * passwordDAO.getRecentlyUsedPasswords("username", 3) >> recentlyUsedPasswords

        returnedValue_one == false
        returnedValue_two == true
        returnedValue_three == true
        returnedValue_somethingElse == true
    }


    def "scenarios where there are no passwords in history"() {

        given:
        PasswordDAO passwordDAO = Mock()
        List<String> recentlyUsedPasswords = new ArrayList<>()

        PasswordValidatorConfiguration configuration = Spy(PasswordValidatorConfiguration)
        configuration.createPasswordDAO() >> passwordDAO

        NotARecentPasswordRule rule = new NotARecentPasswordRule(configuration)

        boolean returnedValue_one
        boolean returnedValue_two
        boolean returnedValue_three
        boolean returnedValue_somethingElse

        when:
        returnedValue_one = rule.isAValidPassword("username", "one")
        returnedValue_two = rule.isAValidPassword("username", "two")
        returnedValue_three = rule.isAValidPassword("username", "three")
        returnedValue_somethingElse = rule.isAValidPassword("username", "somethingElse")

        then:
        4 * passwordDAO.getRecentlyUsedPasswords("username", 3) >> recentlyUsedPasswords
        returnedValue_one == true
        returnedValue_two == true
        returnedValue_three == true
        returnedValue_somethingElse == true
    }
}
