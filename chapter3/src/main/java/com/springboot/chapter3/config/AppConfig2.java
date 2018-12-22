package com.springboot.chapter3.config;

import com.springboot.chapter3.Conditional.DatabaseConditional;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/21
 **/
//@Configuration代表这是一个Java配置文件，Spring的容器会根据它来生成IoC容器取装配Bean
@Configuration
@ComponentScan(basePackages = "com.springboot.chapter3.*", excludeFilters = {@ComponentScan.Filter(classes={Service.class})}, lazyInit = true)//标明采用何种策略去扫描装配Bean
public class AppConfig2 {
    private static Logger log = Logger.getLogger(AppConfig2.class);

    @Bean(name = "dataSource")
    @Conditional(DatabaseConditional.class)//条件装配Bean
    public DataSource getDataSource(
            @Value("$database.driverName") String diverName,
            @Value("${database.url}") String url,
            @Value("${database.username}") String username ,
            @Value("${database.password}") String password){

        Properties properties = new Properties();
        properties.setProperty("driver",diverName);
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        DataSource dataSource = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
            log.debug("\nconnection=" + connection);
            statement = connection.prepareStatement("select 1 ");
            boolean b = statement.execute();
            log.debug("\nb=" + b);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
}
