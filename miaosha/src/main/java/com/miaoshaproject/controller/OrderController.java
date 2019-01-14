package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class OrderController extends BaseController{

   @Autowired
   private OrderService orderService;

   @Autowired
   private HttpServletRequest httpServletRequest;

   @RequestMapping(value = "/createorder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORME})
   @ResponseBody
   public CommonReturnType createOrder(@RequestParam(name="itemId") Integer itemId,
                                       @RequestParam(name="amount") Integer amount,
                                       @RequestParam(name="promoId", required = false) Integer promoId) throws BusinessException {

      // 获取用户的登录信息，用来判定用户是否已经登录
      Boolean isLogin = (Boolean) this.httpServletRequest.getSession().getAttribute("IS_LOGIN");

      if(isLogin == null || !isLogin.booleanValue()) {
         throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
      }

      // 在session中拿到当前请求的用户
      UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
      OrderModel orderModel = orderService.createOrder(itemId, userModel.getId(), promoId, amount);

      return CommonReturnType.create(null);
   }
}
