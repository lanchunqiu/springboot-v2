package com.springboot.chapter15.service;

import com.springboot.chapter15.pojo.PurchaseRecordPo;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/12
 **/
public interface PurchaseService {
    /**
     * 处理购买业务
     * @param userId 用户编号
     * @param productId 产品编号
     * @param quantity 购买数量
     * @return 成功or失败
     */
    public boolean purchase(Long userId, Long productId, int quantity);

    boolean purchaseRedis(Long userId, Long productId, int quantity);

    boolean dealRedisPurchase(List<PurchaseRecordPo> prpList);
}
