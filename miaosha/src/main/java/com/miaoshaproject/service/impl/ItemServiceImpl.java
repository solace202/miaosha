package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemDoMapper;
import com.miaoshaproject.dao.ItemStockDoMapper;
import com.miaoshaproject.dataobject.ItemDo;
import com.miaoshaproject.dataobject.ItemStockDo;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
   @Autowired
   private ValidatorImpl validator;

   @Autowired
   private ItemDoMapper itemDoMapper;

   @Autowired
   private ItemStockDoMapper itemStockDoMapper;

   @Override
   @Transactional
   public ItemModel createItem(ItemModel itemModel) throws BusinessException {
      ValidationResult result = validator.validate(itemModel);

      if(result.isHasErrors()) {
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.errErrMsg());
      }

      ItemDo itemDo = convertFromItemModel(itemModel);
      itemDoMapper.insertSelective(itemDo);

      ItemStockDo itemStockDo = convertFromItemStockModel(itemModel);
      itemStockDoMapper.insertSelective(itemStockDo);

      return this.getItemById(itemModel.getId());
   }

   @Override
   public List<ItemModel> listItem() {



      return null;
   }

   @Override
   public ItemModel getItemById(Integer id) {
      ItemDo itemDo = itemDoMapper.selectByPrimaryKey(id);
      ItemStockDo itemStockDo = itemStockDoMapper.selectByItemId(id);

      return convertFromItemDo(itemDo, itemStockDo);
   }

   public ItemDo convertFromItemModel(ItemModel itemModel) {
      if(itemModel == null) {
         return null;
      }

      ItemDo itemDo = new ItemDo();
      BeanUtils.copyProperties(itemModel, itemDo);

      return itemDo;
   }

   public ItemModel convertFromItemDo(ItemDo itemDo, ItemStockDo itemStockDo) {

      if(itemDo == null || itemStockDo == null) {
         return null;
      }

      ItemModel itemModel = new ItemModel();
      BeanUtils.copyProperties(itemDo, itemModel);
      itemModel.setPrice(itemModel.getPrice());
      itemModel.setStock(itemStockDo.getStock());

      return itemModel;
   }

   public ItemStockDo convertFromItemStockModel(ItemModel itemModel) {
      if(itemModel == null) {
         return null;
      }

      ItemStockDo itemStockDo = new ItemStockDo();
      itemStockDo.setItemId(itemModel.getId());
      itemStockDo.setStock(itemModel.getStock());

      return itemStockDo;
   }
}
