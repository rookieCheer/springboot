package com.lyf.springboot02web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lyf")
public class Springboot02WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02WebApplication.class, args);
    }
}
