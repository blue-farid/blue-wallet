package com.snapp.pay.insurance.bluewallet.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "blue-wallet.redisson")
public class RedissonProperties {
    private Lock lock;

    @Data
    public static class Lock {
        private Wallet wallet;
    }

    @Data
    public static class Wallet {
        private String key;
        private Integer waitTime;
        private Integer leaseTime;
    }
}
