package com.springboot.chapter15.dao;

import com.springboot.chapter15.pojo.PurchaseRecordPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/12
 **/
@Mapper
public interface PurchaseRecordDao {
    public int insertPurchaseRecord(PurchaseRecordPo pr);
}
