package com.snapp.pay.insurance.bluewallet.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisUtil {
    private final StringRedisTemplate redisTemplate;

    //TODO expiry time
    public void insert(String key, String value, Integer minutes) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes());
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }
}
