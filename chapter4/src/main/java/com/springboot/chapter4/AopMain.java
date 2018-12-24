package com.springboot.chapter4;

import com.springboot.chapter4.intercept.MyInterceptor;
import com.springboot.chapter4.proxy.ProxyBean;
import com.springboot.chapter4.service.IHelloService;
import com.springboot.chapter4.service.impl.HelloServiceImpl;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/24
 **/
public class AopMain {

    public static void main(String[] args) {
        testProxy();
    }

    private static void testProxy() {
        IHelloService helloService = new HelloServiceImpl();
        IHelloService proxy = (IHelloService) new ProxyBean().getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("zhangsan");

        System.out.println("\n###############name is null!!#############\n");
        proxy.sayHello(null);
    }
}
