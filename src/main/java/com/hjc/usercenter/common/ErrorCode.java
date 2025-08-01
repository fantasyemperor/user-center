package com.hjc.usercenter.common;

import lombok.Getter;

/**
 * 错误码
 */

@Getter
public enum ErrorCode {

    SUCCESS(0,"success",""),
    PARAMS_ERROR(4000,"请求参数错误",""),
    NULL_ERROR(4001,"请求参数为空",""),
    NO_LOGIN(40100,"","未登录"),
    NO_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常","");



    private  final  int code;
    private  final String message;
    private  final String description;

      ErrorCode(int code,String message ,String description){

          this.code = code;
          this.message = message;
          this.description = description;

    }




}
