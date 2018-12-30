package com.springboot.chapter5.main;

import com.springboot.chapter5.dao.MyBatisUserDao;
import com.springboot.chapter5.enumeration.SexEnum;
import com.springboot.chapter5.plugin.MyPlugin;
import org.apache.catalina.startup.ClassLoaderFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/28
 **/
//定义Spring Boot扫描包路径
@SpringBootApplication(scanBasePackages = "com.springboot.chapter5")
//定义JPA接口扫描包路径
@EnableJpaRepositories(basePackages = "com.springboot.chapter5.dao")
//定义实体Bean扫描包路径
@EntityScan(basePackages = "com.springboot.chapter5.pojo")
//定义MyBatis扫描
@MapperScan(
        //指定扫描包
        basePackages="com.springboot.chapter5",
        //指定SqlSessionFactory
        sqlSessionFactoryRef = "sqlSessionFactory",
        //
        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class)
public class Chapter5Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory = null;

//    // 定义一个MyBatis的Mapper接口
//	@Bean
//	public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao() {
//	     MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
//	     bean.setMapperInterface(MyBatisUserDao.class);
//	     bean.setSqlSessionFactory(sqlSessionFactory);
//	     TypeHandler<SexEnum> sexTypeHandler = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().getTypeHandler(SexEnum.class);
//	     return bean;
//	}

//    /**
//     * 配置Mybatis接口扫描
//     * @return
//     */
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer(){
//	    //定义扫描器实例
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//
//        //加载SqlSessionFactory,SpringBoot会自动生成SqlSession实例
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        //定义扫描的包
//        mapperScannerConfigurer.setBasePackage("com.springboot.chapter5.*");
//        //限定被标注@Repository的接口才扫描
//        mapperScannerConfigurer.setAnnotationClass(Repository.class);
//        //通过继承某个接口限制扫描，一般使用的不多
//        //mapperScannerConfigurer.setMarkerInterface(.....);
//        return mapperScannerConfigurer;
//    }

    //启用Spring Bean 生命周期执行方法，加入插件
    @PostConstruct
    public void initMyBatis(){
        //插件实例
        Interceptor plugin = new MyPlugin();
        //设置插件属性
        Properties properties = new Properties();
        properties.setProperty("key1","value1");
        properties.setProperty("key2","value2");
        properties.setProperty("key3","value4");
        plugin.setProperties(properties);
        //在 sqlSessionFactory中添加插件
        sqlSessionFactory.getConfiguration().addInterceptor(plugin);
    }
}
