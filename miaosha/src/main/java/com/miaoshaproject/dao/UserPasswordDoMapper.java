package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.UserPasswordDo;

public interface UserPasswordDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    int insert(UserPasswordDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    int insertSelective(UserPasswordDo record);

    UserPasswordDo selectByPrimaryKey(Integer userId);

    UserPasswordDo selectByUserId(Integer userId);
}