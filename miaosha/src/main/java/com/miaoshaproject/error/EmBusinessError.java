package com.miaoshaproject.error;

public enum EmBusinessError implements CommonError {

   PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
   UNKNOW_ERROR(10002, "未知错误"),

   // 用户类错误信息
   USER_NOT_EXIST(20001, "用户信息不存在"),
   USER_LOGIN_FAIL(20002, "用户手机号或密码不正确"),
   USER_NOT_LOGIN(20003, "用户还未登录"),


   //订单类错误信息
   STOCK_NOT_ENOUGH(30001, "商品库存不足");
   ;

   private int errCode;
   private String errMsg;

   private EmBusinessError(int errCode, String errMsg) {
      this.errCode = errCode;
      this.errMsg = errMsg;
   }

   @Override
   public int getErrCode() {
      return this.errCode;
   }

   @Override
   public String getErrMsg() {
      return this.errMsg;
   }

   @Override
   public CommonError setErrMsg(String errMsg) {
      this.errMsg = errMsg;
      return this;
   }
}
