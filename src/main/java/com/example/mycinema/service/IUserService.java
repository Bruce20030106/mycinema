package com.example.mycinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.domain.po.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends IService<User> {

    void userRegister(RegisterInfo registerInfo);
}
