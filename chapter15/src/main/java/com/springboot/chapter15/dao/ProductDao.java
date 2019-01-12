package com.springboot.chapter15.dao;

import com.springboot.chapter15.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/12
 **/
@Mapper
public interface ProductDao {
    // 获取产品
    public ProductPo getProduct(Long id);

    //减库存，而@Param标明MyBatis参数传递给后台
    public int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity);

    public int decreaseProductByVersion(@Param("id") Long id,
    	    @Param("quantity") int quantity, @Param("version") int version);
}
