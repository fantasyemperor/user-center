package com.hjc.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.usercenter.mapper.UserMapper;
import com.hjc.usercenter.model.domain.User;
import com.hjc.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.hjc.usercenter.userConstants.UserConstants.USER_LOGIN_STATUS;

/**
* @author 17763
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-07-27 04:14:57
*/





@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;


    /**
     * 返回用户脱敏
     * @param user
     * @return
     */
    public User userSafety(User user){
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setUserAccount(user.getUserAccount());
        user1.setAvatarUrl(user.getAvatarUrl());
        user1.setGender(user.getGender());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setUserStatus(user.getUserStatus());
        user1.setCreateTime(user.getCreateTime());
        user1.setUserRole(user.getUserRole());
        user1.setPlanetCode(user.getPlanetCode());
        return user1;
    }




    //注册
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode) {

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

        //星球编号不能重复
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.eq("planetCode",planetCode);
        long count2 = userMapper.selectCount(queryWrapper);
        if(count2>0){
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
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        
        //非null判断
        boolean saveResult = this.save(user);
        if(!saveResult){
            return -1;
        }

        return user.getId();
    }

    @Override
    public User doLogin(String userAccount, String userPassword, HttpServletRequest request) {

       //1.检验
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;

        }

        if(userAccount.length()<4){
            return null;
        };

        if(userPassword.length()<8){
            return null;
        };

        boolean isValid =  Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$").matcher(userAccount).matches();
        if (!isValid){
            return null;
        };

        //2.校验密码

//        String hashed = BCrypt.hashpw(userPassword, BCrypt.gensalt());

//        String encryptPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
//        queryWrapper.eq("userPassword", encryptPassword);


        User user = userMapper.selectOne(queryWrapper);

        // Then verify the password
        if (user == null || !BCrypt.checkpw(userPassword, user.getUserPassword())) {
            log.info("user login failed,userAccount cannot match password");
            return null;
        }
//            // Authentication successful
//        } else {
//            // Authentication failed
//        }
//        if(user == null){
//            log.info("user login failed,userAccount cannot match password");
//            return null;
//        }

        //3.返回前端信息脱敏
        User user1 = userSafety(user);
        //4.记录用户的登录态，返回脱敏的user
        request.getSession().setAttribute(USER_LOGIN_STATUS,user1);




        return user1;
    }



    @Override
    public List<User> findUserByName(String username) {




        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        List<User> userList = userMapper.selectList(queryWrapper);


        List<User> safeUserList = userList.stream()
                .map(this::userSafety)
                .collect(Collectors.toList());

        return safeUserList;

    }

    @Override
    public int userLogOut(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATUS);

        return 1;
    }


}




