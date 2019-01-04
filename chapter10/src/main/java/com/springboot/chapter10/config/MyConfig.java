package com.springboot.chapter10.config;

import com.springboot.chapter10.interceptor.Interceptor1;
import com.springboot.chapter10.interceptor.MultiInterceptor1;
import com.springboot.chapter10.interceptor.MultiInterceptor2;
import com.springboot.chapter10.interceptor.MultiInterceptor3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/4
 **/
@Configuration
public class MyConfig implements WebMvcConfigurer {
    //国际化拦截器
    private LocaleChangeInterceptor localeInterceptor = null;

    //国际化解析器。注意，这个BeanName要为localeResolver
    @Bean(name="localeResolver")
    public LocaleResolver initLocaleResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        //默认国际化区域
        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return resolver;
    }

    //创建国际化拦截器
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        if (localeInterceptor != null){
            return localeInterceptor;
        }
        localeInterceptor = new LocaleChangeInterceptor();
        //设置参数名
        localeInterceptor.setParamName("language");
        return localeInterceptor;
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器到SpringMVC机制，然后会返回一个拦截器注册
        InterceptorRegistration registration = registry.addInterceptor(new Interceptor1());
        //指定拦截匹配模式，限制拦截请求
        registration.addPathPatterns("/interceptor/*");

        InterceptorRegistration registration1 = registry.addInterceptor(new MultiInterceptor1());
        registration1.addPathPatterns("/interceptor/*");
        InterceptorRegistration registration2 = registry.addInterceptor(new MultiInterceptor2());
        registration2.addPathPatterns("/interceptor/*");
        InterceptorRegistration registration3 = registry.addInterceptor(new MultiInterceptor3());
        registration3.addPathPatterns("/interceptor/*");

        //增加国际化拦截器
        InterceptorRegistration r = registry.addInterceptor(localeChangeInterceptor());
    }

}
