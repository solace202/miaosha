package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController {

   @Autowired
   private ItemService itemService;

   // 创建商品的controller
   @RequestMapping(value = "/createItem", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORME})
   @ResponseBody
   public CommonReturnType createItem(@RequestParam(name="title") String title,
                                      @RequestParam(name="price") BigDecimal price,
                                      @RequestParam(name="stock") Integer stock,
                                      @RequestParam(name="description") String description,
                                      @RequestParam(name="sales") Integer sales,
                                      @RequestParam(name="imgUrl") String imgUrl
                                      ) throws BusinessException {

      // 封装servie请求用来创建商品
      ItemModel itemModel = new ItemModel();
      itemModel.setTitle(title);
      itemModel.setPrice(price);
      itemModel.setStock(stock);
      itemModel.setDescription(description);
      itemModel.setSales(sales);
      itemModel.setImgUrl(imgUrl);

      ItemModel itemModelForReturn = itemService.createItem(itemModel);
      ItemVO itemVO = convertFromItemModel(itemModelForReturn);

      return CommonReturnType.create(itemVO);
   }

   // 获取商品列表的controller
   @RequestMapping(value = "/listitem", method = {RequestMethod.GET})
   @ResponseBody
   public CommonReturnType listItem() {
      List<ItemModel> itemModelList = itemService.listItem();

      List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
         ItemVO itemVO = convertFromItemModel(itemModel);

         return itemVO;
      }).collect(Collectors.toList());

      return CommonReturnType.create(itemVOList);
   }

   // 获取商品详情的controller
   @RequestMapping(value = "/get", method = {RequestMethod.GET})
   @ResponseBody
   public CommonReturnType getItem(@RequestParam(name="itemId") Integer itemId) {
      ItemModel itemModel = itemService.getItemById(itemId);
      ItemVO itemVO = convertFromItemModel(itemModel);

      return CommonReturnType.create(itemVO);
   }

   public ItemVO convertFromItemModel(ItemModel itemModel) {
      if(itemModel == null) {
         return null;
      }

      ItemVO itemVO = new ItemVO();
      BeanUtils.copyProperties(itemModel, itemVO);

      if(itemModel.getPromoModel() != null) {
         DateTime dateTime = new DateTime(itemModel.getPromoModel().getStartTime());

         itemVO.setPromoId(itemModel.getPromoModel().getId());
         itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
         itemVO.setStatus(itemModel.getPromoModel().getStatus());
         itemVO.setStartDate(dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
      }

      return itemVO;
   }
}
