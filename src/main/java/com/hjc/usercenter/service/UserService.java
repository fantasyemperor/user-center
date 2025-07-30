package com.hjc.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 17763
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-07-27 04:14:57
*/
public interface UserService extends IService<User> {


    /**
     * 注册用户
     * @param userAccount 用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return
     */
     long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode);


    /**
     * 登录
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
     User doLogin(String username,String password, HttpServletRequest request);



    /**\
     * 查询用户
     * @param username
     * @return
     */
    List<User> findUserByName(String username);

    /**
     * 用户注销
     * @param request
     * @return
     */
    int userLogOut(HttpServletRequest request);


}
