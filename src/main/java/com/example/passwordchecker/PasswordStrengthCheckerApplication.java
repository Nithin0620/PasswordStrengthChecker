package com.example.passwordchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Password Strength Checker Spring Boot application.
 * Run locally  : mvn spring-boot:run
 * Build JAR    : mvn clean install
 * Start JAR    : java -jar target/password-strength-checker-1.0.0.jar
 */
@SpringBootApplication
public class PasswordStrengthCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordStrengthCheckerApplication.class, args);
    }
}
