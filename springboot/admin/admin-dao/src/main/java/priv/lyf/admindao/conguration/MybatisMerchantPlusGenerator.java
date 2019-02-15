package priv.lyf.admindao.conguration;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
* @Description:    自动生成配置
* @Author:         xxx
* @CreateDate:     2018/10/12 14:56
* @Version:        1.0
*/
@SuppressWarnings("all")
public class MybatisMerchantPlusGenerator {

    public static void main(String[] args) {

        String packageName = "priv.lyf.admindao";
        generateByTables(packageName, "sys_shiro_user", "sys_shiro_permission", "sys_shiro_role", "sys_shiro_role_permission", "sys_shiro_user_role");

    }

    private static void generateByTables(String packageName, String... tableNames) {
        String outputDir = "E:\\user\\xxx\\springboot\\admin\\admin-dao\\src\\main\\java";
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);//覆盖
        gc.setActiveRecord(false);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("XXX");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        String dbUrl = "jdbc:mysql://127.0.0.1:3306/admin?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Shanghai";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("1234")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
//                转换类型
//                if (fieldType.contains("tinyint")) {
//                    return DbColumnType.BASE_BOOLEAN;
//                }
                return super.processTypeConvert(fieldType);
            }
        });
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames)//修改替换成你需要的表名，多个表名传数组
                .setSuperEntityClass("priv.lyf.admindao.superclass.SuperEntity")//实体类基类
                .setSuperMapperClass("priv.lyf.admindao.superclass.SuperMapper");//持久层基类

        new AutoGenerator().setGlobalConfig(gc)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setEntity("entity")
                                .setMapper("dao")
                                .setXml("mapper").setService("service").setServiceImpl("service.impl")

                ).execute();
    }
}
