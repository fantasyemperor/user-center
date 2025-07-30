package com.hjc.usercenter.common;

public class ResultUtills {
    public static <T>BasaResponse ok(T data){

        int code = 1;
        String message = "ok";
        BasaResponse result  = new BasaResponse(1,data,message);
        return result;

    }

}
