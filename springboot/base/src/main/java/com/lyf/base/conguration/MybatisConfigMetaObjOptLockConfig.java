package com.lyf.base.conguration;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Reason:	 MyBatis 数据库配置.
 *
 * @author xxx
 * @version $Id: MyBatisConfiguration, v 0.1 2015/7/12 12:07
 * @since JDK 1.8
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.lyf.base.dao*")
public class MybatisConfigMetaObjOptLockConfig {

	@Bean
	public DataSource druidDataSource() throws SQLException {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl("jdbc:mysql://127.0.0.1:3306/springboot?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Shanghai");
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUsername("root");
		datasource.setPassword("1234");
		datasource.setInitialSize(0);
		datasource.setMinIdle(0);
		datasource.setMaxWait(60000);
		datasource.setMaxActive(10);
		datasource.setMinEvictableIdleTimeMillis(1800000);
		return datasource;
	}


	@Bean("mybatisSqlSession")
	public SqlSessionFactory sqlSessionFactory(ResourceLoader resourceLoader, GlobalConfiguration globalConfiguration)
			throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(druidDataSource());
		sqlSessionFactory.setTypeAliasesPackage("com.lyf.base.entity");
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*MapperExt.xml"));
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactory.setConfiguration(configuration);

		sqlSessionFactory.setPlugins(new Interceptor[]{new PaginationInterceptor(), new PerformanceInterceptor(),
				new OptimisticLockerInterceptor()});
		sqlSessionFactory.setGlobalConfig(globalConfiguration);
		return sqlSessionFactory.getObject();
	}

	@Bean
	public GlobalConfiguration globalConfiguration() {
		GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
		conf.setLogicDeleteValue("-1");
		conf.setLogicNotDeleteValue("1");
		conf.setIdType(0);
		return conf;
	}
}
