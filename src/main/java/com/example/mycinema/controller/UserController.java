package com.example.mycinema.controller;

import com.example.mycinema.common.R;
import com.example.mycinema.domain.dto.LoginInfo;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.domain.vo.UserVO;
import com.example.mycinema.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理接口")
@RestController
@AllArgsConstructor
public class UserController {


    private final IUserService userService;

    @PostMapping("/doRegister")
    public R<String> UserRegister(@RequestBody RegisterInfo registerInfo){

        userService.userRegister(registerInfo);

        return R.success("用户注册成功！");
    }

    @ApiOperation("查询当前登录用户的详细信息")
    @PostMapping("/user/getUserInfo")
    public R<UserVO> getUserInfo(){
        return null;
    }

    @PostMapping("doLogin")
    public R<String> userLogin(@RequestBody LoginInfo loginInfo){
        return null;
    }


}
