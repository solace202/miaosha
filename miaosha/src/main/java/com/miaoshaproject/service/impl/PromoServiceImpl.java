package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.PromoDoMapper;
import com.miaoshaproject.dataobject.PromoDo;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.PromoModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PromoServiceImpl implements PromoService {

   @Autowired
   private PromoDoMapper promoDoMapper;

   @Override
   public PromoModel getPrompByItemId(Integer itemId) {
      PromoDo promoDo = promoDoMapper.selectByItemId(itemId);
      PromoModel promoModel = convertFromPromoDo(promoDo);

      Long now = new Date().getTime();

      // 还未开始
      if(promoModel.getStartTime().getTime() > now) {
         promoModel.setStatus(1);
      }
      // 已经结束
      else if(promoModel.getEndTime().getTime() < now) {
         promoModel.setStatus(3);
      }
      else {
         promoModel.setStatus(2);
      }

      return promoModel;
   }

   private PromoModel convertFromPromoDo(PromoDo promoDo) {
      if(promoDo == null) {
         return null;
      }

      PromoModel promoModel = new PromoModel();
      BeanUtils.copyProperties(promoDo, promoModel);
      promoModel.setPromoItemPrice(promoDo.getPromoItemPrice());
      promoModel.setStartTime(promoDo.getStartDate());
      promoModel.setEndTime(promoDo.getEndDate());

      return promoModel;
   }
}
