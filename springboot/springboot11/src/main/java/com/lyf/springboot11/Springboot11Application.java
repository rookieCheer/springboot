package com.lyf.springboot11;

import com.lyf.springboot11.conguration.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
/**
 * 我们需要通过@import注解把我们的数据源注册器导入到spring中 在启动类加上如下注解@Import(DynamicDataSourceRegister.class)。
 */
@Import(DynamicDataSourceRegister.class)
@ComponentScan("com.lyf")
@MapperScan("com.lyf.springboot11.dao*")
public class Springboot11Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot11Application.class, args);
    }

}

