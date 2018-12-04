package com.lyf.springboot03web.aspect;


import java.lang.annotation.*;

/**
 * Created by huguoju on 2016/12/30.
 * 在类或方法上添加@Auth就验证登录
 *
 * @Target注解是标注这个类它可以标注的位置: TYPE, TYPE类型可以声明在类上或枚举上或者是注解上
 *      FIELD声明在字段上
 *      Method  声明在方法上
 *      CONSTRUCTOR声明在构造方法上
 *      LOCAL_VARIABLE声明在局部变量上
 * @Retention注解表示的是本注解(标注这个注解的注解保留时期) SOURCE, 源代码时期
 *      CLASS 字节码时期, 编译之后
 *      RUNTIME运行时期, 也就是一直保留, 通常也都用这个
 * @Documented是否生成文档的标注, 也就是生成接口文档是, 是否生成注解文档
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    String[] value() default {};

    String[] authorities() default {};

    String[] roles() default {};
}