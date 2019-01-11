package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
// 类似于我们在原有的校验规则上制定了我们自己的一套校验方法,最终我们校验使用的是我们自己的类
public class ValidatorImpl implements InitializingBean {

   private Validator validator;

   // 实现校验方法并返回校验结果，
   public ValidationResult validate(Object bean) {
      final ValidationResult result = new ValidationResult();
      Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);

      if(constraintViolationSet.size() > 0) {
         result.setHasErrors(true);

         constraintViolationSet.forEach(constraintViolation -> {
            String errMsg = constraintViolation.getMessage();
            String propertyName = constraintViolation.getPropertyPath().toString();

            result.getErrorMsgMap().put(propertyName, errMsg);
         });
      }

      return result;
   }

   @Override
   // 实现InitializingBean接口后，spring在加载bean时会调用afterPropertiesSet方法进行初始化（倘若给bean配置了init-method方法，该方法会被最后执行）
   public void afterPropertiesSet() throws Exception {
      // 将hiberate的validator（我们定义的全局变量）通过工厂的初始化方式使其实例化
      this.validator = Validation.buildDefaultValidatorFactory().getValidator();
   }
}




