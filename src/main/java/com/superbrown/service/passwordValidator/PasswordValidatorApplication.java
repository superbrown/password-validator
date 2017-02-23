package com.superbrown.service.passwordValidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Mike on 2/22/2017.
 */

@SpringBootApplication
@EnableSwagger2
@ComponentScan

public class PasswordValidatorApplication {

	public static void main(String[] args) {

		SpringApplication.run(PasswordValidatorApplication.class, args);
	}
}
