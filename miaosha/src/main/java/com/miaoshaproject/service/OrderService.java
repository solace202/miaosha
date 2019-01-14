package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

public interface OrderService {

   // 对于商品是否属于秒杀进行校验
   // （推荐）1. 通过前端url传递过来秒杀活动的id，在下单前校验是否存在此秒杀活动且是否正在进行
   // 2. 直接在下单接口中判断商品是否属于秒杀商品，若存在活动则以秒杀价格下单
   OrderModel createOrder(Integer itemId, Integer userId, Integer promoId, Integer amount) throws BusinessException;
}
