package com.springboot.chapter4.aspect;

import com.springboot.chapter4.aspect.validator.UserValidator;
import com.springboot.chapter4.aspect.validator.impl.UserValidatorImpl;
import com.springboot.chapter4.pojo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author lancq
 * @Description 切面
 * @Date 2018/12/25
 **/
@Aspect
public class MyAspect {

    //切点
    @Pointcut("execution(* com.springboot.chapter4.*.*.*.*.print(..)) && bean('userServiceImpl')")
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

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("around before ......");
        jp.proceed();
        System.out.println("around after ......");
    }

    //注解@DeclareParents，作用是引入新的类来增强服务
    @DeclareParents(value = "com.springboot.chapter4.aspect.service.impl.UserServiceImpl+",
    defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    @Before("pointCut() && args(user)")
    public void beforeParam( User user) {
        System.out.println("before ......");
    }
}
