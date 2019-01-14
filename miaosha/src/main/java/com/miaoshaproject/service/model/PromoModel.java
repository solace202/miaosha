package com.miaoshaproject.service.model;


import java.math.BigDecimal;
import java.util.Date;

public class PromoModel {
   private Integer id;

   // 秒杀活动名称
   private String promoName;

   // 秒杀状态
   // 1：秒杀未开始；2：秒杀正在进行；3：秒杀已结束
   private Integer status;

   // 秒杀活动开始时间
   private Date startTime;

   // 秒杀结束时间
   private Date endTime;

   // 秒杀活动适用商品
   private Integer itemId;

   // 秒杀商品的活动价格
   private BigDecimal promoItemPrice;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getPromoName() {
      return promoName;
   }

   public void setPromoName(String promoName) {
      this.promoName = promoName;
   }

   public Date getStartTime() {
      return startTime;
   }

   public void setStartTime(Date startTime) {
      this.startTime = startTime;
   }

   public Date getEndTime() {
      return endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public Integer getItemId() {
      return itemId;
   }

   public void setItemId(Integer itemId) {
      this.itemId = itemId;
   }

   public BigDecimal getPromoItemPrice() {
      return promoItemPrice;
   }

   public void setPromoItemPrice(BigDecimal promoItemPrice) {
      this.promoItemPrice = promoItemPrice;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }
}
