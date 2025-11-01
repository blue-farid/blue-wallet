package com.snapp.pay.insurance.bluewallet.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "blue-wallet.redisson")
public class RedissonProperties {
    private Lock lock;
    private Bucket rateLimiter;

    @Data
    public static class Lock {
        private LockConfig wallet;
    }

    @Data
    public static class LockConfig {
        private String key;
        private Integer waitTime;
        private Integer leaseTime;
    }

    @Data
    public static class Bucket {
        private String key;
    }
}
