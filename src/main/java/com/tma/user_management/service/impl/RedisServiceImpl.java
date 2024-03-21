package com.tma.user_management.service.impl;

import com.tma.user_management.security.JWTTokenProvider;
import com.tma.user_management.service.RedisService;
import com.tma.user_management.util.BadInputException;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {
    private RedisTemplate<String, String> redisTemplate;
    private JWTTokenProvider jwtTokenProvider;

    @Override
    public void addJWTRedis(String jwt) {
        redisTemplate.opsForValue().set(jwtTokenProvider.getUsernameFromToken(jwt), jwt);
    }

    @Override
    public String getJWTFromUsername(String username) {
        return redisTemplate.opsForValue().get(username);
    }

    @Override
    public void removeJTWRedis(String username) {
        redisTemplate.delete(username);
    }
}
