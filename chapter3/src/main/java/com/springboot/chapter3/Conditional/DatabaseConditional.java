package com.springboot.chapter3.Conditional;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/22
 **/
public class DatabaseConditional implements Condition {
    private static Logger log = Logger.getLogger(DatabaseConditional.class);
    /**
     * 数据库装配条件
     * @param context
     * @param metadata
     * @return true 装配Bean，否则不装配
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //取出环境配置
        Environment env = context.getEnvironment();
        log.debug("env==================" + env);

        //判断数据库文件中是否存在对应的数据库配置
        return env.containsProperty("database.driverName")
                && env.containsProperty("database.url")
                && env.containsProperty("database.username")
                && env.containsProperty("database.password");

    }
}
