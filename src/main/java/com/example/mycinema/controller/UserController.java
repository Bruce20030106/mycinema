package com.example.mycinema.controller;

import com.example.mycinema.common.JwtUtils;
import com.example.mycinema.common.R;
import com.example.mycinema.common.UserContextHolder;
import com.example.mycinema.domain.dto.LoginInfo;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.domain.po.User;
import com.example.mycinema.domain.vo.UserVO;
import com.example.mycinema.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户管理接口")
@RestController
@AllArgsConstructor
public class UserController {


    private final IUserService userService;

    @PostMapping("/doRegister")
    public R<String> UserRegister(@RequestBody RegisterInfo registerInfo){

        boolean not_exist = userService.userRegister(registerInfo);

        if(!not_exist){
            return R.error("已经存在该用户名！");
        }else return R.success("用户注册成功！");


    }

    @ApiOperation("查询当前登录用户的详细信息")
    @PostMapping("/user/getUserInfo")
    public R<UserVO> getUserInfo(){

        UserVO userInfo = userService.getUserInfo(UserContextHolder.getUser().getUserName());

        return R.success(userInfo);
    }

    @PostMapping("doLogin")
    public R<String> userLogin(@RequestBody LoginInfo loginInfo){

        User user = userService.login(loginInfo);

        if(user!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId",user.getUserId());
            claims.put("userName",user.getUserName());

            String jwt = JwtUtils.generateJwt(claims);
            return R.success(jwt);
        }

        return R.error("用户名或密码错误");
    }


}
