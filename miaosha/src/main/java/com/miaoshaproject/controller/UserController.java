package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController{

   @Autowired
   UserService userService;

   @Autowired
   HttpServletRequest httpServletRequest;


   @RequestMapping(value = "/get", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORME})
   @ResponseBody
   public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {
      UserModel user = userService.getUserById(id);

      // 若获取的用户信息不存在
      if(user == null) {
         user.setEncrptPassword("999");
//         throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
      }

      UserVO userVO = converFromUserModel(user);

      return CommonReturnType.create(userVO);
   }

   @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORME})
   @ResponseBody
   public CommonReturnType login(@RequestParam(name="telphone") String telphone,
                                 @RequestParam(name="password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
      UserModel userModel = userService.validateLogin(telphone, this.EncodeByMD5(password));

      // 将用户登录信息保存到session中
      this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
      this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

      return CommonReturnType.create(null);
   }

   @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORME})
   @ResponseBody
   public CommonReturnType register(@RequestParam(name="telphone")String telphone,
                                    @RequestParam(name="otpCode")String otpCode,
                                    @RequestParam(name="name")String name,
                                    @RequestParam(name="age")Integer age,
                                    @RequestParam(name="gender")Integer gender,
                                    @RequestParam(name="password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

      // 首先验证otp码是否正确
      String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);

      if(!com.alibaba.druid.util.StringUtils.equals(inSessionOtpCode, otpCode)) {
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不正确");
      }

      UserModel userModel = new UserModel();
      userModel.setTelphone(telphone);
      userModel.setAge(age);
      userModel.setGender(gender);
      userModel.setName(name);
      userModel.setRegisterMode("byphone");
      userModel.setEncrptPassword(this.EncodeByMD5(password));

      userService.register(userModel);

      return CommonReturnType.create(null);
   }

   @RequestMapping("/getotp")
   @ResponseBody
   public CommonReturnType getOtp(@RequestParam(name="telphone") String telphone) {
      //生成otp验证码(OTP: One-time Password也叫动态口令)
      Random random = new Random();
      int otpCode = random.nextInt(999999);
      otpCode += 100000;
      String otpString = String.valueOf(otpCode);

      // 将otp验证码和注册手机号相关联(企业开发时使用的是redis，此处使用HttpSession暂做模拟)
      httpServletRequest.getSession().setAttribute(telphone, otpString);

      // 将otp验证码通过短信发送给用户(这里使用打印到控制台来模拟)
      System.out.println("telphone= " + telphone + "& otpCode=" + otpString);

      return CommonReturnType.create(otpString);
   }

   // password加密
   public String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      // 确定计算方法
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      BASE64Encoder base64Encoder = new BASE64Encoder();
      // 加密字符串
      String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));

      return newstr;
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
