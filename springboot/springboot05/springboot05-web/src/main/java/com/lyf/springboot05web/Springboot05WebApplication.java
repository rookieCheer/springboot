package com.lyf.springboot05web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lyf")
public class Springboot05WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05WebApplication.class, args);
    }
}
