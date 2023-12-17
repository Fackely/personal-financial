package com.application.personal_financial.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.application.personal_financial.modules",
                "com.application.personal_financial.crosscutting",
                "com.application.personal_financial.infrastructure"
        }
)
@EnableJpaRepositories(basePackages = {
        "com.application.personal_financial.crosscutting.persistence.repository"})
@EntityScan(basePackages = {
        "com.application.personal_financial.crosscutting.domain"})
public class PersonalFinancialApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalFinancialApplication.class, args);
    }

}
