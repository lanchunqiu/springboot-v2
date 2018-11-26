package com.springboot.springmvc.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Author lancq
 * @Description
 * @Date 2018/11/25
 **/
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //Spring IoC容器配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //可以返回Spring的java配置文件数组
        return new Class[0];
    }

    //DispatcherServlet的URI映射关系配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //可以返回Spring的java配置文件数组
        return new Class[]{WebConfig.class};
    }

    //DispatcherServlet拦截请求匹配
    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.do"};
    }
}
