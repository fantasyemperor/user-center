package com.hjc.usercenter.common;

public class ResultUtills {

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BasaResponse<T> ok(T data){

        int code = 1;
        String message = "ok";
        String description = "";
        BasaResponse result  = new BasaResponse(1,data,message,description);
        return result;

    }

    /**
     * 失败
     * @param errorCode
     * @return
     * @param
     */

    public static BasaResponse error(ErrorCode errorCode){
        BasaResponse result  = new BasaResponse(errorCode);


        return result;

    }

    public static BasaResponse error(int errorCode,String message,String description){
         return new BasaResponse(errorCode,null,message,description);


    }

    public static BasaResponse error(ErrorCode errorCode,String message,String description){
        BasaResponse result  = new BasaResponse(errorCode.getCode(),message,description);
        return result;

    }

    public static BasaResponse error(ErrorCode errorCode,String description){
        BasaResponse result  = new BasaResponse(errorCode.getCode(),description);

        return result;

    }



}
