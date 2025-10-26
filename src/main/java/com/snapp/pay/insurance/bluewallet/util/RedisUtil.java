package com.snapp.pay.insurance.bluewallet.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisUtil {
    //TODO why this version?
    private final StringRedisTemplate redisTemplate;

    //TODO expiry time
    public void insert(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }
}
