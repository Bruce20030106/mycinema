package com.example.mycinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mycinema.domain.dto.LoginInfo;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.domain.po.User;
import com.example.mycinema.domain.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends IService<User> {

    boolean userRegister(RegisterInfo registerInfo);

    User login(LoginInfo loginInfo);

    UserVO getUserInfo(String userName);
}
