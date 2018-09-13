package com.lucifer.exception;

/**
 * Created by jonynian on 2018/9/3.
 */
public class LuciferException extends  RuntimeException{

    private String code;
    private String error;

    public LuciferException(String message) {
        super(message);
    }
    public LuciferException(String code, String message) {
        super(message);
        this.code = code;
    }

}
