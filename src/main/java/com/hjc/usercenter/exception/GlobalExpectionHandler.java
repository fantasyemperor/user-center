package com.hjc.usercenter.exception;


import com.hjc.usercenter.common.BasaResponse;
import com.hjc.usercenter.common.ErrorCode;
import com.hjc.usercenter.common.ResultUtills;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExpectionHandler {

    @ExceptionHandler(BusinessException.class)
    public BasaResponse<?> businessExceptionHandler(BusinessException e){
        log.error("businessException"+e.getMessage(),e);
        return ResultUtills.error(e.getCode(),e.getMessage(),e.getDescription());

    }

    @ExceptionHandler(RuntimeException.class)
    public BasaResponse<?> runtimeExceptionHandler(RuntimeException e){

        log.error("runtimeException",e);
        return ResultUtills.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");

    }
}
