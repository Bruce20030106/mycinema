package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.common.UUIDUtil;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.domain.po.User;
import com.example.mycinema.mapper.UserMapper;
import com.example.mycinema.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    @Override
    public void userRegister(RegisterInfo registerInfo) {
        registerInfo.setUserId(UUIDUtil.generateUUIDAsLong());
        User user = BeanUtil.copyProperties(registerInfo, User.class);
        userMapper.insert(user);
    }


}

