package com.hxh.more.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**
     * 创建 orders 数据源的配置对象
     */
    @Primary
    @Bean(name = "oneDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.one") // 读取 spring.datasource.one 配置到 DataSourceProperties 对象
    public DataSourceProperties oneDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 one 数据源
     */
    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one.hikari")
    // 读取 spring.datasource.one 配置到 HikariDataSource 对象
    public DataSource oneDataSource() {
        // <1.1> 获得 DataSourceProperties 对象
        DataSourceProperties properties = this.oneDataSourceProperties();
        // <1.2> 创建 HikariDataSource 对象
        return createHikariDataSource(properties);
    }

    /**
     * 创建 two 数据源的配置对象
     */
    @Bean(name = "twoDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.two") // 读取 spring.datasource.two 配置到 DataSourceProperties 对象
    public DataSourceProperties twoDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 two 数据源
     */
    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two.hikari")
    public DataSource twoDataSource() {
        // 获得 DataSourceProperties 对象
        DataSourceProperties properties = this.twoDataSourceProperties();
        // 创建 HikariDataSource 对象
        return createHikariDataSource(properties);
    }


    private static HikariDataSource createHikariDataSource(DataSourceProperties properties) {
        // 创建 HikariDataSource 对象
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        // 设置线程池名
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

}
