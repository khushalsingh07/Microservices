package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountsContacInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContacInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice Rest API Documentation",
				description = "SBI Account microservice Rest API Documentation",
				version = "V1",
				contact = @Contact(
						name = "SBI",
						email = "sbi.supprt@gmail.com",
						url = "https://onlinesbi.sbi"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://onlinesbi.sbi"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SBI Account microservice Rest API Documentation",
				url = "https://onlinesbi.sbi"
		)

)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
