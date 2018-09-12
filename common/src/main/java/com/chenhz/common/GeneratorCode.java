package com.chenhz.common;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.chenhz.common.enums.Application;

/**
 * @author CHZ
 * @create 2018/8/7
 */
public abstract class GeneratorCode {


    public abstract Application getApp();

    public void generateCode(){
        String packageName = getApp().getPath()[0];
        boolean serviceNameStartWithI = false;
        generateByTables(serviceNameStartWithI,Application.COMMON);
    }


    public void generateByTables(boolean serviceNameWithI,Application app,String ... tableNames){

        String dbUrl = "jdbc:mysql://localhost:3306/sample?serverTimezone=UTC";
        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("tb_sys_","sys_","tb_")
                .setInclude(tableNames);

        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("chz")
                .setOutputDir(System.getProperty("user.dir")+"/src/main/java")
                .setFileOverride(true)
                .setMapperName("%sDao");

        if (!serviceNameWithI){
            config.setServiceName("%sService");
        }



        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(app.getPath()[0])
                .setEntity("entity")
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("dao.mapper")
                .setMapper("dao");



        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();

    }

    public void generateByTables(Application app, String... tableNames) {
        generateByTables(true, app, tableNames);
    }

    public void generateByTables(String... tableNames){
        generateByTables(true, getApp(), tableNames);
    }

}
