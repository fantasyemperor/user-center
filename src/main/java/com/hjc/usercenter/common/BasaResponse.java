package com.hjc.usercenter.common;

import java.io.Serializable;

public class BasaResponse<T> implements Serializable {
    private static int code;
    private T data;
    private String message;


    public BasaResponse(int code, T data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;

    }

    public BasaResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.message = message;

    }

}
