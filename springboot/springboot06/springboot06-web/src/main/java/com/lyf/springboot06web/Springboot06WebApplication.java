package com.lyf.springboot06web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lyf")
public class Springboot06WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot06WebApplication.class, args);
    }
}
