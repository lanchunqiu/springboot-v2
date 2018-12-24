package com.springboot.chapter4.proxy;


import com.springboot.chapter4.intercept.Interceptor;
import com.springboot.chapter4.invoke.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/24
 * 动态代理
 **/
public class ProxyBean implements InvocationHandler {
    private Object target = null;

    private Interceptor interceptor = null;

    /**
     * 绑定代理对象
     * @param target 被代理对象
     * @param interceptor 拦截器
     * @return 代理对象
     */
    public Object getProxyBean(Object target, Interceptor interceptor){
        this.target = target;
        this.interceptor = interceptor;

        //生成代理对象
        Class clazz = target.getClass();
        Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        return proxy;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 当前方法
     * @param args 参数
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target, method, args);
        Object retObj = null;
        try{
            if(this.interceptor.useAround()){
                retObj = this.interceptor.around(invocation);
            } else {
                retObj = method.invoke(target, args);
            }
        } catch (Exception e){
            exceptionFlag = true;
        }

        if(exceptionFlag){
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;
    }
}
