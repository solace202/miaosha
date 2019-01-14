package com.miaoshaproject.service.model;

import java.math.BigDecimal;

public class OrderModel {

   private String id;

   private Integer userId;

   private Integer itemId;

   // promoId若非空，则表示商品属于秒杀商品
   private Integer promoId;

   // promoId若非空，则表示使用秒杀单价
   private BigDecimal itemPrice;

   private Integer amount;

   // promoId若非空，则表示使用秒杀价格
   private BigDecimal orderPrice;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
   }

   public Integer getItemId() {
      return itemId;
   }

   public void setItemId(Integer itemId) {
      this.itemId = itemId;
   }

   public BigDecimal getItemPrice() {
      return itemPrice;
   }

   public void setItemPrice(BigDecimal itemPrice) {
      this.itemPrice = itemPrice;
   }

   public Integer getAmount() {
      return amount;
   }

   public void setAmount(Integer amount) {
      this.amount = amount;
   }

   public BigDecimal getOrderPrice() {
      return orderPrice;
   }

   public void setOrderPrice(BigDecimal orderPrice) {
      this.orderPrice = orderPrice;
   }

   public Integer getPromoId() {
      return promoId;
   }

   public void setPromoId(Integer promoId) {
      this.promoId = promoId;
   }
}
