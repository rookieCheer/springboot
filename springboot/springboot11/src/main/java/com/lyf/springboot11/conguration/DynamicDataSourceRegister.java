package com.lyf.springboot11.conguration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 我们自定义的配置，springboot是无法给我们自动配置，所以需要我们自己注册数据源.
 * 那么就要实现 EnvironmentAware用于读取上下文环境变量用于构建数据源，同时也需要实现 ImportBeanDefinitionRegistrar接口注册我们构建的数据源。
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    /**
     * 指定默认数据源(springboot2.0默认数据源是hikari如何想使用其他数据源可以自己配置)
     */
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";
    /**
     * 默认数据源
     */
    private DataSource defaultDataSource;
    /**
     * 用户自定义数据源
     */
    private Map<String, DataSource> slaveDataSources = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initslaveDataSources(environment);
    }

    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("driver", env.getProperty("spring.datasource.driver"));
        dsMap.put("url", env.getProperty("spring.datasource.url"));
        dsMap.put("username", env.getProperty("spring.datasource.username"));
        dsMap.put("password", env.getProperty("spring.datasource.password"));
        defaultDataSource = buildDataSource(dsMap);
    }


    private void initslaveDataSources(Environment env) {
        // 读取配置文件获取更多数据源
        String dsPrefixs = env.getProperty("slave.datasource.names");
        for (String dsPrefix : dsPrefixs.split(",")) {
            // 多个数据源
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("driver", env.getProperty("slave.datasource." + dsPrefix + ".driver"));
            dsMap.put("url", env.getProperty("slave.datasource." + dsPrefix + ".url"));
            dsMap.put("username", env.getProperty("slave.datasource." + dsPrefix + ".username"));
            dsMap.put("password", env.getProperty("slave.datasource." + dsPrefix + ".password"));
            DataSource ds = buildDataSource(dsMap);
            slaveDataSources.put(dsPrefix, ds);
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //添加默认数据源
        targetDataSources.put("dataSource", this.defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        //添加其他数据源
        targetDataSources.putAll(slaveDataSources);
        for (String key : slaveDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        //创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        //注册 - BeanDefinitionRegistry
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");
    }

    public DataSource buildDataSource(Map<String, Object> dataSourceMap) {
        try {
            Object type = dataSourceMap.get("type");
            if (type == null) {
                // 默认DataSource
                type = DATASOURCE_TYPE_DEFAULT;
            }
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dataSourceMap.get("driver").toString();
            String url = dataSourceMap.get("url").toString();
            String username = dataSourceMap.get("username").toString();
            String password = dataSourceMap.get("password").toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
