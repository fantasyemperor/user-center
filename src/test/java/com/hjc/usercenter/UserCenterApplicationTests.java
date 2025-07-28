package com.hjc.usercenter;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void hash(){
        String hashed = BCrypt.hashpw("user123", BCrypt.gensalt());
        System.out.println(hashed);
    }

    @Test
    void contextLoads() {
    }

}
