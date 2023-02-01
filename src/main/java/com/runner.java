package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class runner {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(runner.class, args);
    }
}
