package com.springboot.chapter3;


import com.springboot.chapter3.config.AppConfig;
import com.springboot.chapter3.config.AppConfig2;
import com.springboot.chapter3.pojo.BusinessPerson;
import com.springboot.chapter3.pojo.User2;
import com.springboot.chapter3.pojo.User;
import com.springboot.chapter3.pojo.definition.Person;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/21
 **/
public class IoCTest {
    private static Logger log = Logger.getLogger(IoCTest.class);

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        log.info(user.getId());

        ctx = new AnnotationConfigApplicationContext(AppConfig2.class);
        User2 user2 = ctx.getBean(User2.class);
        log.info(user2.getId());

        Person person = ctx.getBean(BusinessPerson.class);
        person.service();

        //关闭IoC容器
        ((AnnotationConfigApplicationContext) ctx).close();
    }
}
