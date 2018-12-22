package com.springboot.chapter3.pojo;

import com.springboot.chapter3.pojo.definition.Animal;
import com.springboot.chapter3.pojo.definition.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/22
 **/
@Component
public class BusinessPerson implements Person,
        BeanNameAware,
        BeanFactoryAware,
        ApplicationContextAware,
        InitializingBean,
        DisposableBean
{
    private static Logger log = Logger.getLogger(BusinessPerson.class);

    //@Autowired根据属性的类型（by type）找到对应的bean进行注入
    //@Autowired
    //@Qualifier("cat")
    private Animal animal = null;

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    @Autowired
    @Qualifier("cat")
    public void setAnimal(Animal animal) {
        log.debug("延迟依赖注入");
        this.animal = animal;
    }

    @Override
    public void setBeanName(String name) {
        log.debug("\n【" + this.getClass().getSimpleName() + "】调用BeanNameAware的setBeanName方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.debug("\n【" + this.getClass().getSimpleName() + "】调用BeanFactoryAware的setBeanFactory方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("\n【" + this.getClass().getSimpleName() + "】调用ApplicationContextAware的setApplicationContext方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("\n【" + this.getClass().getSimpleName() + "】调用InitializingBean的afterPropertiesSet方法");
    }

    @Override
    public void destroy() throws Exception {
        log.debug("\n【" + this.getClass().getSimpleName() + "】调用DisposableBean的destroy方法");
    }

    @PostConstruct
    public void init(){
        log.debug("\n【" + this.getClass().getSimpleName() + "】注解@PostConstruct的自定义初始化方法");
    }

    @PreDestroy
    public void myDestroy(){
        log.debug("\n【" + this.getClass().getSimpleName() + "】注解@PreDestroy的自定义销毁方法");
    }
}
