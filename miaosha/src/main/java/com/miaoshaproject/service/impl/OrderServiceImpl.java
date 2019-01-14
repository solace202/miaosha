package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderDoMapper;
import com.miaoshaproject.dao.SequenceDoMapper;
import com.miaoshaproject.dataobject.OrderDo;
import com.miaoshaproject.dataobject.SequenceDo;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

   @Autowired
   private ItemService itemService;

   @Autowired
   private UserService userService;

   @Autowired
   private OrderDoMapper orderDoMapper;

   @Autowired
   private SequenceDoMapper sequenceDoMapper;

   @Override
   @Transactional
   public OrderModel createOrder(Integer itemId, Integer userId, Integer promoId, Integer amount) throws BusinessException {

      // 1. 校验下单状态，下单的商品是否存在，用户是否合法，购买数量是否正确
      ItemModel itemModel = itemService.getItemById(itemId);
      if(itemModel == null) {
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
      }

      UserModel userModel = userService.getUserById(userId);
      // 用户是否登录的校验
      if(userModel == null) {
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
      }

      // 数量校验
      if(amount < 0 || amount > 99) {
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品数量信息不合法");
      }

      // 是否属于秒杀商品的校验
      if(promoId != null) {
         // 此秒杀活动id是否和ItemModel中的聚合类型PromoModel中的id相等
         if(promoId != itemModel.getPromoModel().getId()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "秒杀商品不存在");
         }
         // 商品是否还处于秒杀中
         else if(itemModel.getPromoModel().getStatus() != 2) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "秒杀活动已结束");
         }
      }

      // 2. 落单减库存
      boolean result = itemService.decreaseItem(itemId, amount);

      if(!result) {
          throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
      }

      itemService.increaseSales(itemId, amount);

      // 3. 订单入库
      OrderModel orderModel = new OrderModel();
      orderModel.setItemId(itemId);
      orderModel.setUserId(userId);
      orderModel.setAmount(amount);
      orderModel.setPromoId(promoId);

      // 商品属于秒杀商品时，订单的单价设置为秒杀的价格
      if(promoId != null) {
         orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
      }
      else {
         orderModel.setItemPrice(itemModel.getPrice());
      }

      orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));

      // 设置订单号
      orderModel.setId(generateOrderNo());

      OrderDo orderDo = convertFromOrderModel(orderModel);
      orderDoMapper.insertSelective(orderDo);

      // 4. 返回前端
      return orderModel;
   }

   /*
   * 生成订单号
   * */
   // 为了确保订单号的唯一性，必须将此处订单号在数据库的修改与相关连的事务操作隔离开
   // 必须单独加个事务：propagation = Propagation.REQUIRES_NEW
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public String generateOrderNo() {
      // 订单号为16位
      StringBuilder stringBuilder = new StringBuilder();

      // 前八位为日期信息，年月日
      LocalDateTime now = LocalDateTime.now();
      String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
      stringBuilder.append(nowDate);

      // 中间6位为自增序列
      int sequence = 0;
      SequenceDo sequenceDo = sequenceDoMapper.selectSequenceByName("order_info");
      sequence = sequenceDo.getCurrValue();
      sequenceDo.setCurrValue(sequenceDo.getCurrValue() + sequenceDo.getStep());
      sequenceDoMapper.updateByPrimaryKeySelective(sequenceDo);
      String sequenceStr = String.valueOf(sequence);

      for(int i = 0; i < sequenceStr.length(); i++) {
         stringBuilder.append("0");
      }

      stringBuilder.append(sequenceStr);

      // 最后2位为分库分表
      String end = "00";
      stringBuilder.append(end);

      return stringBuilder.toString();
   }

   private OrderDo convertFromOrderModel(OrderModel orderModel) {
      if(orderModel == null) {
         return null;
      }

      OrderDo orderDo = new OrderDo();
      BeanUtils.copyProperties(orderModel, orderDo);

      return orderDo;
   }
}
