package com.snapp.pay.insurance.bluewallet.util;

import com.snapp.pay.insurance.bluewallet.config.properties.RedissonProperties;
import com.snapp.pay.insurance.bluewallet.config.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimitUtil {
    private final static long TOKENS = 1;
    private final RedissonClient redissonClient;
    private final SecurityProperties securityProperties;
    private final RedissonProperties redissonProperties;

    public boolean isOtpRequestAllowed(String key) {
        return validate(key, securityProperties.getRateLimit().getOtp().getInterval(), securityProperties.getRateLimit().getOtp().getLimit());
    }

    public boolean isLoginRequestAllowed(String key) {
        return validate(key, securityProperties.getRateLimit().getLogin().getInterval(), securityProperties.getRateLimit().getLogin().getLimit());
    }

    private boolean validate(String key, Integer interval, Integer limit) {
        String redisKey = redissonProperties.getRateLimiter().getKey() + key;
        RAtomicLong counter = redissonClient.getAtomicLong(redisKey);

        long current = counter.get();

        if (current >= limit) {
            return false;
        }

        long updated = counter.incrementAndGet();
        counter.expire(Duration.ofMinutes(interval));
        return updated <= limit;
    }
}
