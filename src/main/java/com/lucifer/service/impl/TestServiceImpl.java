package com.lucifer.service.impl;

import com.lucifer.dao.UserWxMapper;
import com.lucifer.po.UserWx;
import com.lucifer.service.TestService;
import com.lucifer.util.dataSource.DataSource;
import com.lucifer.util.dataSource.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private  UserWxMapper   userWxMapper;


    @DataSource(DataSourceConfig.MASTER)
    @Override
    public UserWx selectUserById(int i) {
        return userWxMapper.selectByPrimaryKey(i);
    }
}
