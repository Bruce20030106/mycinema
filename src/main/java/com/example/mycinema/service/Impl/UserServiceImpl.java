package com.example.mycinema.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.domain.po.User;
import com.example.mycinema.mapper.UserMapper;
import com.example.mycinema.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
