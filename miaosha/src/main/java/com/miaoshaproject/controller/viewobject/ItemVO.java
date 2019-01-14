package com.miaoshaproject.controller.viewobject;

import java.math.BigDecimal;

public class ItemVO {

   private Integer id;

   // 商品名称
   private String title;

   // 商品秒杀活动id,如果为空则无秒杀活动
   private Integer promoId;

   // 价格，如果promoId不为空，则使用秒杀单价
   private BigDecimal price;

   // 库存
   private Integer stock;

   // 描述
   private String description;

   // 销量
   private Integer sales;

   // 商品描述图片
   private String imgUrl;

   // 秒杀状态
   private Integer status;

   // 秒杀价格
   private BigDecimal promoPrice;

   // 秒杀开始时间
   private String startDate;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public Integer getStock() {
      return stock;
   }

   public void setStock(Integer stock) {
      this.stock = stock;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getSales() {
      return sales;
   }

   public void setSales(Integer sales) {
      this.sales = sales;
   }

   public String getImgUrl() {
      return imgUrl;
   }

   public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
   }

   public Integer getPromoId() {
      return promoId;
   }

   public void setPromoId(Integer promoId) {
      this.promoId = promoId;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public BigDecimal getPromoPrice() {
      return promoPrice;
   }

   public void setPromoPrice(BigDecimal promoPrice) {
      this.promoPrice = promoPrice;
   }

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }
}
