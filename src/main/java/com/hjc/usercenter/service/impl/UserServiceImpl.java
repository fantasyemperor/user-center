package com.hjc.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.usercenter.mapper.UserMapper;
import com.hjc.usercenter.model.domain.User;
import com.hjc.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
* @author 17763
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-07-27 04:14:57
*/



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegiser(String userAccount, String userPassword, String checkPassword) {

       //1.校验
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;

        }
        if(userAccount.length()<4){
            return -1;
        }
        if(userPassword.length()<8){
            return -1;
        }
        if(!userPassword.equals(checkPassword)){
            return -1;
        }

        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if(count>0){
            return -1;
        }

        //判断是否含有特殊字符
        boolean isValid =  Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$").matcher(userAccount).matches();
        if (!isValid){
            return -1;
        }

        //2.加密密码
        String encryptPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());

        //3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPasswword(encryptPassword);
        //非null判断
        boolean saveResult = this.save(user);
        if(saveResult){
            return -1;
        }

        return user.getId();
    }
}




