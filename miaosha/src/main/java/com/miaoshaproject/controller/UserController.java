package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {

   @Autowired
   UserService userService;

   @RequestMapping("/get")
   @ResponseBody
   public CommonReturnType getUser(@RequestParam(name="id") Integer id) {
      UserModel user = userService.getUserById(id);

      UserVO userVO =  converFromUserModel(user);

      return CommonReturnType.create(userVO);
   }

   private UserVO converFromUserModel(UserModel userModel) {
      if(userModel == null) {
         return null;
      }

      UserVO userVo = new UserVO();
      BeanUtils.copyProperties(userModel, userVo);
      return userVo;
   }
}
