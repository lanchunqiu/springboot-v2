package com.springboot.chapter16.service.impl;

import com.springboot.chapter16.pojo.Product;
import com.springboot.chapter16.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProduct(Long id) {
        throw new RuntimeException("未能支持该方法");
    }
}
