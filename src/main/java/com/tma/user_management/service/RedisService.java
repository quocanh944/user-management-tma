package com.tma.user_management.service;

public interface RedisService {
    public void addJWTRedis(String jwt);
    public String getJWTFromUsername(String username);
    public void removeJTWRedis(String jwt);
}
