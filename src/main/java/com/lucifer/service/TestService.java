package com.lucifer.service;

import com.lucifer.po.UserWx;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface TestService {
    UserWx selectUserById(int i);
}
