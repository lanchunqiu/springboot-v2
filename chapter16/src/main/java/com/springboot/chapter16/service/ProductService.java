package com.springboot.chapter16.service;

import com.springboot.chapter16.pojo.Product;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
public interface ProductService {
    Product getProduct(Long id);
}
