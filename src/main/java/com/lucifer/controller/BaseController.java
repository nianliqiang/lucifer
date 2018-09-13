package com.lucifer.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucifer.commons.ResponseModel;
import java.io.Serializable;
import jodd.util.StringUtil;

/**
 * Created by jonynian on 2018/9/13.
 */
public class BaseController {
    public static final ObjectMapper JSON_MAPPER_NOTNULL = new ObjectMapper();
    public static final String RESULT_SUCCESS = "200";
    public static final String RESULT_ERROR = "000";
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public <T extends Serializable> String buildSuccessResponse(T obj){
        ResponseModel<T> model = new ResponseModel<T>();
        model.setCode(ResponseModel.RESULT_SUCCESS);
        model.setData(obj);
        try {
            return JSON.toJSONString(model);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public <T extends Serializable> String buildErrResponse(String msg){
        ResponseModel<T> model = new ResponseModel<T>();
        model.setCode(ResponseModel.RESULT_ERROR);
        model.setMsg(msg);

        try {
            return JSON.toJSONString(model);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }




}
