package com.springboot.chapter3.pojo;

import com.springboot.chapter3.pojo.definition.Animal;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/22
 **/
@Component
@Primary
public class Dog implements Animal {
    private static Logger log = Logger.getLogger(Dog.class);
    @Override
    public void use() {
        log.debug("狗【" + Dog.class.getSimpleName() + "】是人类的好朋友。");
    }
}
