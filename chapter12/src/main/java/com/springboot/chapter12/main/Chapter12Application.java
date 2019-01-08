package com.springboot.chapter12.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/9
 **/
@SpringBootApplication
public class Chapter12Application extends WebSecurityConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Chapter12Application.class, args);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编码器
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //使用内存存储
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)//设置密码编码器
                .withUser("admin")//注册用户admin
                .password(encoder.encode("abc"))//设置密码
                .roles("USER", "ADMIN")
                .and()
                .withUser("myuser")//注册用户myuser
                .password(encoder.encode("123456"))
                .roles("USER");

    }
}
