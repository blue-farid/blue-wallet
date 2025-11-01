package com.snapp.pay.insurance.bluewallet.util;

import com.snapp.pay.insurance.bluewallet.config.properties.RedissonProperties;
import com.snapp.pay.insurance.bluewallet.config.properties.SecurityProperties;
import io.github.bucket4j.*;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
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

    private boolean validate(String key, Integer interval, Integer refillTokensPerMinute) {
        RBucket<Bucket> bucket = redissonClient.getBucket(redissonProperties.getRateLimiter().getKey() + key);
        Bucket bucketObj = bucket.get();

        if (bucketObj == null) {
            Bandwidth bandwidth = Bandwidth.builder()
                    .capacity(refillTokensPerMinute)
                    .refillIntervally(refillTokensPerMinute, Duration.ofMinutes(interval))
                    .build();

            bucketObj = Bucket.builder().addLimit(bandwidth).build();
            bucket.set(bucketObj);
        }

        return bucketObj.tryConsume(TOKENS);
    }
}
