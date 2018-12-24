package com.springboot.chapter4.intercept;

import com.springboot.chapter4.invoke.Invocation;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/24
 **/
public class MyInterceptor implements Interceptor {
    private static Logger log = Logger.getLogger(MyInterceptor.class);

    @Override
    public void before() {
        log.debug("before ......");
    }

    @Override
    public void after() {
        log.debug("after ......");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        log.debug("around before ......");
        Object object = invocation.proceed();
        log.debug("around after ......");

        return object;
    }

    @Override
    public void afterReturning() {
        log.debug("afterReturning ......");
    }

    @Override
    public void afterThrowing() {
        log.debug("afterThrowing ......");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
