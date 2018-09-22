package com.lucifer.controller;


import com.lucifer.po.UserWx;
import com.lucifer.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController  extends  BaseController{
    private static final Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private TestService testService ;

    @RequestMapping("test")
    public String test(){
        HashMap map = new HashMap() ;
        map.put("dd","13214");
        buildSuccessResponse(map);
        UserWx user = testService.selectUserById(66);
        return buildSuccessResponse(user);
    }
}
