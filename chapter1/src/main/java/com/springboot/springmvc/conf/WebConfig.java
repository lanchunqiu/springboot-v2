package com.springboot.springmvc.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/11/25
 **/
@Configuration
//定义Spring MVC扫描的包
@ComponentScan(value = "com.*", includeFilters = {@Filter(type = FilterType.ANNOTATION,value=Controller.class)})
//启动Spring MVC配置
@EnableWebMvc
public class WebConfig {

    /**
     * 通过注解@Bean 初始化视图解析器
     *
     * @return ViewResolver 视图解析器
     */
    @Bean(name = "internalResourceViewRViewResolver")
    public ViewResolver initViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * 初始化RequestMappingHandlerAdapter，并加装HTTP的JSON转换器
     * @return
     */
    @Bean(name = "requestMappingHandlerAdapter")
    public HandlerAdapter initRequestMappingHandlerAdapter(){
        //创建RequestMappingHandlerAdapter适配器
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();

        //HTTP JSON转换器
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(mediaType);

        //加入转换器的支持类型
        jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);

        //给适配器加入JSON转换器
        requestMappingHandlerAdapter.getMessageConverters().add(jackson2HttpMessageConverter);
        return requestMappingHandlerAdapter;
    }


}
