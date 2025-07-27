package com.hjc.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.usercenter.model.domain.User;
import com.hjc.usercenter.service.UserService;
import com.hjc.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 17763
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-07-27 04:14:57
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




