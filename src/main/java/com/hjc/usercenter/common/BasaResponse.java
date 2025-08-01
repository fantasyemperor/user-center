package com.hjc.usercenter.common;

import java.io.Serializable;

public class BasaResponse<T> implements Serializable {
    private  int code;
    private T data;
    private String message;
    private String description;


    public BasaResponse(int code, T data,String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;

    }

    public BasaResponse(int code, T data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;


    }

    public BasaResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.message="";
        this.description="";
    }

    public BasaResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
        this.description = errorCode.getDescription();

    }

    public BasaResponse(ErrorCode errorCode,String message,String description) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = message;
        this.description = description;
    }

    public BasaResponse(ErrorCode errorCode,String description) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
        this.description = description;
    }
}
