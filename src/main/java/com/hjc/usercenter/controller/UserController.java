package com.hjc.usercenter.controller;


import com.hjc.usercenter.common.BasaResponse;
import com.hjc.usercenter.common.ErrorCode;
import com.hjc.usercenter.common.ResultUtills;
import com.hjc.usercenter.exception.BusinessException;
import com.hjc.usercenter.model.domain.User;
import com.hjc.usercenter.model.domain.request.UserLoginRequest;
import com.hjc.usercenter.model.domain.request.UserRegisterRequest;
import com.hjc.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.hjc.usercenter.userConstants.UserConstants.USER_LOGIN_STATUS;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;



    /**
     * 判断是否为管理员
     * @param request
     * @return
     */
    public boolean isAdmin(HttpServletRequest request){
        Object user  = request.getSession().getAttribute(USER_LOGIN_STATUS);
        User user1 = (User)user;
        if(user1.getUserRole()<=0){
            return false;
        };
        if(user1.getUserRole()>1){
            return false;
        }
        return true;
    }


    //注册
    @PostMapping("/register")
    public BasaResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        if(userRegisterRequest==null){
//            return ResultUtills.error(ErrorCode.NULL_ERROR)
          throw  new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();

        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw  new BusinessException(ErrorCode.PARAMS_ERROR);
        };

        return ResultUtills.ok(userService.userRegister(userAccount, userPassword, checkPassword,planetCode));
    }


    //登录
    @PostMapping("/userLogin")
    public BasaResponse userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {

        if(userLoginRequest==null){
            return null;
        }

        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        };


        return ResultUtills.ok(userService.doLogin(userAccount, userPassword, request));
    }

    //查询用户
    @GetMapping("/search")
    public BasaResponse findUserByName(String username, HttpServletRequest request) {

        if(!StringUtils.isNotBlank(username)){
//            return Collections.emptyList();
            return null;
        }

        if(!isAdmin(request)){
//            return Collections.emptyList();
            return null;
        }

        return ResultUtills.ok(userService.findUserByName(username));
    }

    //删除用户
    @PostMapping("/delete")
    public BasaResponse isDeleteUser(@RequestBody long userId, HttpServletRequest request) {
        if(userId<=0){
//            return false;
            return null;
        }

        if(!isAdmin(request)){
//            return false;
            return null;
        }

        return ResultUtills.ok(userService.removeById(userId));



    }

    @PostMapping("/logout")
    public BasaResponse logout(HttpServletRequest request) {
        return ResultUtills.ok(userService.userLogOut(request));
    }

}
