package com.tma.user_management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
//    @Value("${redis.host}")
//    private String redisHost;
//
//    @Value("${redis.port}")
//    private Integer redisPort;
//
//    @Value("${redis.pass}")
//    private String redisPass;
//
//    @Value("${redis.username}")
//    private String username;

//    @Bean
//    @Primary
//    public JedisConnectionFactory jedisConnectionFactory() throws Exception {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
//
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPass));
//        redisStandaloneConfiguration.setUsername(username);
//
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration);
//        factory.setTimeout(0);
//
//        return factory;
//    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) throws Exception {
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        return redisTemplate;
    }
}
