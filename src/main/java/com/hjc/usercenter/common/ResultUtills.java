package com.hjc.usercenter.common;

public class ResultUtills {

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> ok(T data){

        int code = 1;
        String message = "ok";
        String description = "";
        BaseResponse result  = new BaseResponse(1,data,message,description);
        return result;

    }

    /**
     * 失败
     * @param errorCode
     * @return
     * @param
     */

    public static BaseResponse error(ErrorCode errorCode){
        BaseResponse result  = new BaseResponse(errorCode);


        return result;

    }

    public static BaseResponse error(int errorCode,String message,String description){
         return new BaseResponse(errorCode,null,message,description);
//        return 2333;


    }

    public static BaseResponse error(ErrorCode errorCode,String message,String description){
        BaseResponse result  = new BaseResponse(errorCode.getCode(),message,description);
        return result;

    }

    public static BaseResponse error(ErrorCode errorCode,String description){
        BaseResponse result  = new BaseResponse(errorCode.getCode(),description);

        return result;

    }



}
