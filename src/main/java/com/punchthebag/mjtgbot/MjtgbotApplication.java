package com.punchthebag.mjtgbot;

import com.punchthebag.mjtgbot.entity.Hand;
import com.punchthebag.mjtgbot.service.HandParser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;

import de.dentrassi.crypto.pem.PemKeyStoreProvider;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MjtgbotApplication {

    public static void main(String[] args) {
        Security.addProvider(new PemKeyStoreProvider());
        SpringApplication.run(MjtgbotApplication.class, args);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Hand hand(HandParser handParser) {
        return new Hand(handParser);
    }

}
