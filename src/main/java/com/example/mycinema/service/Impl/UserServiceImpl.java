package com.example.mycinema.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mycinema.common.UUIDUtil;
import com.example.mycinema.domain.dto.LoginInfo;
import com.example.mycinema.domain.dto.RegisterInfo;
import com.example.mycinema.domain.po.User;
import com.example.mycinema.domain.vo.UserVO;
import com.example.mycinema.mapper.UserMapper;
import com.example.mycinema.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    private RedisTemplate<String, Object> redisTemplate;

    private final static String USER_KEY = "user:";

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

    @Override
    public UserVO getUserInfo(Long userId) {
        String cachekey = USER_KEY + userId;

        User user = (User) redisTemplate.opsForValue().get(cachekey);

        if(user == null){
            user = userMapper.selectById(userId);
            if(user != null){
                redisTemplate.opsForValue().set(cachekey,user,1, TimeUnit.DAYS);
            }
        }

        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);

        return userVO;
    }


}

