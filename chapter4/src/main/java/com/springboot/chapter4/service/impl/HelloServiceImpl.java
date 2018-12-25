package com.springboot.chapter4.service.impl;

import com.springboot.chapter4.service.IHelloService;
import org.apache.log4j.Logger;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/24
 **/
public class HelloServiceImpl implements IHelloService {
    private static Logger log = Logger.getLogger(HelloServiceImpl.class);
    @Override
    public void sayHello(String name) {
        log.debug("hello " + name);
    }
}
