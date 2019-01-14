package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

public interface ItemService {

   // 创建商品
   ItemModel createItem(ItemModel itemModel) throws BusinessException;


   // 获取商品列表
   List<ItemModel> listItem();


   // 获取商品详情
   ItemModel getItemById(Integer id);

   boolean decreaseItem(Integer itemId, Integer amount);

   void increaseSales(Integer itemId, Integer amount);
}
