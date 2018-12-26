package com.springboot.chapter4.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/26
 **/
@Aspect
@Order(1)
public class MyAspect1 implements Ordered {
    @Override
    public int getOrder() {
        return 1;
    }

    @Pointcut("execution(* com.springboot.chapter4.aspect.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects() {
    }

    @Before("manyAspects()")
    public void before() {
        System.out.println("MyAspect1 before ......");
    }

    @After("manyAspects()")
    public void after() {
        System.out.println("MyAspect1 after ......");
    }

    @After("manyAspects()")
    public void afterReturning() {
        System.out.println("MyAspect1 afterReturning ......");
    }
}
