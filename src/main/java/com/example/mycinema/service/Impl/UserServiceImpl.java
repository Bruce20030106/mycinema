package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.common.UUIDUtil;
import com.example.mycinema.domain.dto.LoginInfo;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.domain.po.User;
import com.example.mycinema.mapper.UserMapper;
import com.example.mycinema.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    @Override
    public boolean userRegister(RegisterInfo registerInfo) {
        registerInfo.setUserId(UUIDUtil.generateUUIDAsLong());
        User user = BeanUtil.copyProperties(registerInfo, User.class);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,user.getUserName());
        Long count = userMapper.selectCount(lambdaQueryWrapper);

        if(count>0){
            return false;
        }else{
            userMapper.insert(user);
            return true;
        }

    }

    @Override
    public User login(LoginInfo loginInfo) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, loginInfo.getUserName()));

        if (Objects.isNull(user) || !Objects.equals(user.getPassword(), loginInfo.getPassword())){
            return null;
        } else return user;

    }
}

