package com.lyf.springboot08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Springboot08Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08Application.class, args);
    }

}

