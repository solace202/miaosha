package com.miaoshaproject.service;

import com.miaoshaproject.service.model.UserModel;

public interface UserService {
   // 通过id查找用户
   UserModel getUserById(Integer id);

}
