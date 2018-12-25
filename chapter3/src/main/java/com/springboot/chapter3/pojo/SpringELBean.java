package com.springboot.chapter3.pojo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/23
 **/
@Component
public class SpringELBean {
    private static Logger log = Logger.getLogger(SpringELBean.class);


    @Value("#{'使用Spring EL 赋值字符串'}")
    private String str = null;

    @Value("#{9.3E3}")
    private double d;

    @Value("#{3.14}")
    private float pi;

    @Override
    public String toString() {
        return "str=" + str
                + ",d=" + d
                + ",pi=" + pi;
    }
}
