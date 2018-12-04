package com.lyf.springboot04web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lyf")
public class Springboot04WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot04WebApplication.class, args);
    }
}
