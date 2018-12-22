package com.springboot.chapter3.life;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/22
 **/
@Component
public class BeanPostProcessorExample implements BeanPostProcessor {
    private static Logger log = Logger.getLogger(BeanPostProcessorExample.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("\nBeanPostProcessor调用postProcessBeforeInitialization方法, 参数【" + bean.getClass().getSimpleName() +","
                + beanName + "】");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("\nBeanPostProcessor调用postProcessAfterInitialization方法, 参数【" + bean.getClass().getSimpleName() +","
                + beanName + "】");
        return bean;
    }
}
