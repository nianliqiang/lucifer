package com.lucifer.util.dataSource;


import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 多数据源配置类
 */
@Configuration
public class DataSourceConfig {

    public  static  final  String READ_ONLY="readOnly";
    public  static  final  String MASTER="master";

    //数据源1
    @Bean(name = READ_ONLY)
    @ConfigurationProperties(prefix = "spring.datasource.readOnly")
    public DataSource loanadmin() {
        return DataSourceBuilder.create().build();
    }

    //数据源2
    @Bean(name = MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(loanadmin());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put(READ_ONLY, loanadmin());
        dsMap.put(MASTER, master());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }


}
