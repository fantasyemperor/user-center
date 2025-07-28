package com.hjc.usercenter.service;

import com.hjc.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void TestAddUser() {
        User user = new User();
        user.setUsername("hjc22");
        user.setUserAccount("11122");
        user.setAvatarUrl("1123");
        user.setGender(0);
        user.setUserPassword("1112");
        user.setPhone("1112");
        user.setEmail("1112");

        boolean result = userService.save(user);

        System.out.println(result);

        Assertions.assertTrue(result);


    }



    @Test
    void userRegiser() {

        String userAccount = "111333444";
        String userPassword = "1776397883";
        String checkPassword = "1776397883";

        long result = userService.userRegister(userAccount,userPassword,checkPassword);
        System.out.println(result);
        Assertions.assertTrue(result == -1);

        userAccount = "11";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertTrue(result == -1);

        userAccount = "222222";
        userPassword = "1776";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertTrue(result == -1);

        userAccount = "122222346";
        userPassword = "1776397883";
        checkPassword = "1776397883";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertTrue(result == -1);

        userAccount = "3333333333";
        userPassword = "1776397883";
        checkPassword = "1776397882";
        result = userService.userRegister(userAccount,userPassword,checkPassword);
        Assertions.assertTrue(result == -1);


















    }
}