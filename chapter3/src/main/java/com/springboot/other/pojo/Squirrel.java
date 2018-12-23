package com.springboot.other.pojo;

import com.springboot.chapter3.pojo.Dog;
import com.springboot.chapter3.pojo.definition.Animal;
import org.apache.log4j.Logger;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/23
 **/
public class Squirrel implements Animal {
    private static Logger log = Logger.getLogger(Squirrel.class);
    @Override
    public void use() {
        log.debug("松鼠采集松果。");
    }
}
