package com.hjc.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 2L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;


}

