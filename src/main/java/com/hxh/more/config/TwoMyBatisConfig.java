package com.hxh.more.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration()
@MapperScan(basePackages = "com.hxh.more.dao.two",sqlSessionTemplateRef = "twoSqlSessionTemplate")
public class TwoMyBatisConfig {


    /**
     * two 数据源的mybatis Bean 工厂
     *
     * @param twoDataSource 数据源1的配置
     * @return sqlSession工厂 springboot自动根据这个sqlSession 工作
     * @throws Exception IOException 或者 找不到bean
     */
    @Bean(name = "twoSqlSessionFactory")
    @Primary
    public SqlSessionFactory twoSqlSessionFactory(@Qualifier("twoDataSource") DataSource twoDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(twoDataSource);
        // 自定义mybatis配置
//        bean.setConfiguration();
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/one/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "twoSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate twoSqlSessionTemplate(@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
