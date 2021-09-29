package com.hxh.more;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MoreApplication.class, args);
    }

    private Logger logger = LoggerFactory.getLogger(MoreApplication.class);

    @Resource(name = "oneDataSource")
    private DataSource oneDataSource;

    @Resource(name = "twoDataSource")
    private DataSource twoDataSource;


    @Override
    public void run(String... args) throws Exception {
        // orders 数据源
        try (Connection conn = oneDataSource.getConnection()) {
            // 这里，可以做点什么
            logger.info("[run][ordersDataSource 获得连接：{}]", conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // users 数据源
        try (Connection conn = twoDataSource.getConnection()) {
            // 这里，可以做点什么
            logger.info("[run][usersDataSource 获得连接：{}]", conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
