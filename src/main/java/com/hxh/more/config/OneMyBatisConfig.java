package com.hxh.more.config;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.hxh.more.dao.one", sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class OneMyBatisConfig {

    /**
     * one 数据源的mybatis Bean 工厂
     *
     * @param oneDataSource 数据源1的配置
     * @return sqlSession工厂 springboot自动根据这个sqlSession 工作
     * @throws Exception IOException 或者 找不到bean
     */
    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource oneDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(oneDataSource);
        // 自定义mybatis配置
        // new 方式 在spring中显得格外傻逼
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        config.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(config);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/one/*.xml"));
        return bean.getObject();
    }


//    @Bean(name = "oneTransactionManager")
//    @Primary
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("oneDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Bean(name = "oneSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory oneSqlSessionFactory) {
        return new SqlSessionTemplate(oneSqlSessionFactory);
    }


}
