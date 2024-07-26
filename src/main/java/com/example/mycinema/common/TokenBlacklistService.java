package com.example.mycinema.common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenBlacklistService {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public TokenBlacklistService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addToBlacklist(String token) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(token, "blacklisted", 7, TimeUnit.DAYS); // 设置token过期时间为7天，视需要调整
    }

    public boolean isBlacklisted(String token) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return "blacklisted".equals(ops.get(token));
    }
}
