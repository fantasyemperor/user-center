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
        user.setUsername("hjc");
        user.setUserAccount("111");
        user.setAvatarUrl("https://pica.zhimg.com/v2-420d7455f5fa1b5fbca17790c8b5bc90_r.jpg");
        user.setGender(0);
        user.setUserPasswword("111");
        user.setPhone("111");
        user.setEmail("111");

        boolean result = userService.save(user);

        System.out.println(result);

        Assertions.assertTrue(result);


    }

}