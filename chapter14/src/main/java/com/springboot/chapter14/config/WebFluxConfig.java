package com.springboot.chapter14.config;

import com.springboot.chapter14.enumeration.SexEnum;
import com.springboot.chapter14.pojo.User;
import com.springboot.chapter14.validator.UserValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * @Author lancq
 * @Description 实现接口WebFluxConfigurer，该接口都是default方法(Java 8)
 * @Date 2019/1/12
 **/
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
    /**
     * 注册Converter
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUserConverter());
    }

    /**
     * String -> User 类型转换器
     * String格式{id}-{userName}-{sex}-{note}
     * @return
     */
    public Converter<String,User> stringToUserConverter(){
        Converter<String,User> converter = new Converter<String, User>() {
            @Override
            public User convert(String s) {
                String[] strArr = s.split("-");
                User user = new User();
                Long id = Long.valueOf(strArr[0]);
                user.setId(id);
                user.setUserName(strArr[1]);
                int sexCode = Integer.valueOf(strArr[2]);
                SexEnum sex = SexEnum.getSexEnum(sexCode);
                user.setSex(sex);
                user.setNote(strArr[3]);
                return user;
            }
        };
        return converter;
    }

    /**
     * 设置全局性验证器
     * @return
     */
    @Override
    public Validator getValidator() {
        return new UserValidator();
    }

    /**
     * 设置静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                //注册资源通过URI访问
                .addResourceHandler("/resources/static/**")
                //注册spring资源，可以在spring机制中访问
                .addResourceLocations("/public", "classpath:/static/")
                //缓存一年
                .setCacheControl(CacheControl.maxAge(365,TimeUnit.DAYS));

    }
}
