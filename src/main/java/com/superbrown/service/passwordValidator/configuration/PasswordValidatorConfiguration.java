package com.superbrown.service.passwordValidator.configuration;

import com.superbrown.service.passwordValidator.bo.PasswordValidatorBO;
import com.superbrown.service.passwordValidator.bo.rule.ValidationRule;
import com.superbrown.service.passwordValidator.dao.PasswordDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 2/22/2017.
 */

@Configuration
@PropertySource(value = {
		"classpath:PasswordValidator.properties",
		"classpath:application.properties"})
public class PasswordValidatorConfiguration {

	@Value("${passwordValidator.validationRuleClassNames}")
	private List<String> validationRuleClassNames;

	@Value("${passwordValidator.rule.maximumLength}")
	private int maximumLengthAllowed;

	@Value("${passwordValidator.rule.minimumLength}")
	private int minimumLengthAllowed;

	@Bean(name="passwordValidatorBO")
	@Scope("prototype")
	public PasswordValidatorBO createPasswordValidatorBO() {
		return new PasswordValidatorBO();
	}

	@Bean(name="passwordDAO")
	@Scope("prototype")
	public PasswordDAO createPasswordDAO() {
		return new PasswordDAO();
	}

	@Bean(name="validationRules")
	@Scope("prototype")
	public List<ValidationRule> createValidationRules() {

		List<ValidationRule> validationRules = new ArrayList();

		for (String validationRuleClassName : validationRuleClassNames) {

			Class<?> clazz = null;
			try {
				clazz = Class.forName(validationRuleClassName);
				Constructor<?> ctor = clazz.getDeclaredConstructor(PasswordValidatorConfiguration.class);
				ValidationRule validationRule = (ValidationRule)ctor.newInstance(this);
				validationRules.add(validationRule);

			} catch (Exception e) {
				throw new RuntimeException("Failed to intialize validation rule.", e);
			}
		}

		return validationRules;
	}

	public int getMaximumLengthAllowed() {
		return maximumLengthAllowed;
	}

	public int getMinimumLengthAllowed() {
		return minimumLengthAllowed;
	}

	// necessary to have Spring parse comma separated params as lists
	@Bean
	public static ConversionService conversionService() {
		return new DefaultConversionService();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
