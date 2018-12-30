package com.springboot.chapter5.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
//定义拦截签名
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class MyPlugin implements Interceptor {

    Properties properties = null;

    /**
     * 拦截方法逻辑
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("插件拦截方法.......");
        return invocation.proceed();
    }

    /**
     * 生成MyBatis拦截器代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置插件属性
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
