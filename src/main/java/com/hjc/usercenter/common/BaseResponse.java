package com.hjc.usercenter.common;

import lombok.Data;

import java.io.Serializable;


@Data
public class BaseResponse<T> implements Serializable {
    private  int code;
    private T data;
    private String message;
    private String description;


    public BaseResponse(int code, T data,String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;

    }

    public BaseResponse(int code, T data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;


    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.message="";
        this.description="";
    }

    public BaseResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
        this.description = errorCode.getDescription();

    }

    public BaseResponse(ErrorCode errorCode,String message,String description) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(ErrorCode errorCode,String description) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
        this.description = description;
    }
}
