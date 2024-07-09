package com.example.mycinema.controller;

import com.example.mycinema.common.R;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理接口")
@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {


    private final IUserService userService;

    @PostMapping("/register")
    public R<String> UserRegister(@RequestBody RegisterInfo registerInfo){
        userService.userRegister(registerInfo);

        return R.success("用户注册成功！");
    }

}
