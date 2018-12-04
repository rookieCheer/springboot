package com.lyf.springboot01web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lyf")
public class Springboot01WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01WebApplication.class, args);
    }
}
