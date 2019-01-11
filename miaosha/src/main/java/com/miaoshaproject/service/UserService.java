package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

public interface UserService {
   // 通过id查找用户
   UserModel getUserById(Integer id);

   //注册用户
   void register(UserModel userModel) throws BusinessException;

   /*
   * telphone: 用户手机号
   * encrptpassword: 加密后的密码
   * */
   UserModel validateLogin(String telphone, String encrptpassword) throws BusinessException;

}
