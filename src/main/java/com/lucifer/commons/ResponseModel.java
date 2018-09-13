package com.lucifer.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Created by jonynian on 2018/9/13.
 */
public class ResponseModel <T extends Serializable> implements Serializable   {
    public static final String RESULT_SUCCESS = "200";
    public static final String RESULT_ERROR = "000";
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private T data;

    public ResponseModel() {
    }

    public ResponseModel(String c, String m) {
        this.code = c;
        this.msg = m;
    }

    @JsonProperty("code")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("msg")
    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonProperty("data")
    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
