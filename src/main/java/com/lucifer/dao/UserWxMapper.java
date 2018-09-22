package com.lucifer.dao;

import com.lucifer.po.UserWx;

public interface UserWxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWx record);

    int insertSelective(UserWx record);

    UserWx selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWx record);

    int updateByPrimaryKey(UserWx record);
}