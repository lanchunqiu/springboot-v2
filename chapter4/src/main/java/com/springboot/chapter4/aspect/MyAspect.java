package com.springboot.chapter4.aspect;

import org.aspectj.lang.annotation.*;

/**
 * @Author lancq
 * @Description 切面
 * @Date 2018/12/25
 **/
@Aspect
public class MyAspect {

    //切点
    @Pointcut("execution(* com.springboot.chapter4.aspect.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(){
        System.out.println("before ......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after ......");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing ......");
    }
}
