package com.punchthebag.mjtgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;

import de.dentrassi.crypto.pem.PemKeyStoreProvider;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MjtgbotApplication {

    public static void main(String[] args) {
        Security.addProvider(new PemKeyStoreProvider());
        SpringApplication.run(MjtgbotApplication.class, args);
    }
}
