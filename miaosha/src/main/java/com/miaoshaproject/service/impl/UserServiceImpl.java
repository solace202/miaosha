package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.UserDoMapper;
import com.miaoshaproject.dao.UserPasswordDoMapper;
import com.miaoshaproject.dataobject.UserDo;
import com.miaoshaproject.dataobject.UserPasswordDo;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserDoMapper userDoMapper;

   @Autowired
   private UserPasswordDoMapper userPasswordDoMapper;

   @Autowired
   private ValidatorImpl validator;

   @Override
   public UserModel getUserById(Integer id) {
      UserDo userDo = userDoMapper.selectByPrimaryKey(id);
      // 通过id获取用户的加密的密码信息
      UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByUserId(id);

      return convertFromUserDataObject(userDo, userPasswordDo);
   }

   @Override
   @Transactional
   public void register(UserModel userModel) throws BusinessException {
      if(userModel == null) {
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
      }

      // 旧的对于用户各种信息的校验，太low
//      if(StringUtils.isBlank(userModel.getName()) ||
//              userModel.getAge() == null ||
//              userModel.getGender() == null ||
//              StringUtils.isBlank(userModel.getTelphone())) {
//         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//      }

      // 使用我们自己对validator增强后的类来进行校验
      ValidationResult result = validator.validate(userModel);

      if(result.isHasErrors()) {
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.errErrMsg());
      }

      UserDo userDo = convertFromUserModel(userModel);
      userDoMapper.insertSelective(userDo);
      userModel.setId(userDo.getId());

      UserPasswordDo userPasswordDo = convertPasswordFromUserModel(userModel);
      userPasswordDoMapper.insertSelective(userPasswordDo);

      return;
   }

   @Override
   public UserModel validateLogin(String telphone, String encrptpassword) throws BusinessException {
      UserDo userDo = userDoMapper.selectByTelphone(telphone);

      if(userDo == null) {
         throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
      }

      UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByUserId(userDo.getId());
      UserModel userModel = convertFromUserDataObject(userDo, userPasswordDo);

      if(!StringUtils.equals(encrptpassword, userModel.getEncrptPassword())) {
         throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
      }

      return userModel;
   }


   private UserDo convertFromUserModel(UserModel userModel) {
      if(userModel == null) {
         return null;
      }

      UserDo userDo = new UserDo();
      BeanUtils.copyProperties(userModel, userDo);

      return userDo;
   }

   public UserPasswordDo convertPasswordFromUserModel(UserModel userModel) {
      if(userModel == null) {
         return null;
      }

      UserPasswordDo userPasswordDo = new UserPasswordDo();
      userPasswordDo.setEncrptPassword(userModel.getEncrptPassword());
      userPasswordDo.setUserId(userModel.getId());

      return userPasswordDo;
   }

   private UserModel convertFromUserDataObject(UserDo userDo, UserPasswordDo userPasswordDo) {
      if(userDo == null) {
         return null;
      }

      UserModel userModel = new UserModel();
      BeanUtils.copyProperties(userDo, userModel);
      userModel.setEncrptPassword(userPasswordDo.getEncrptPassword());

      return userModel;
   }
}
