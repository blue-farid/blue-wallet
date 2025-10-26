package com.snapp.pay.insurance.bluewallet.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "blue-wallet.security")
public class SecurityProperties {
    private Jwt jwt;

    @Data
    public static class Jwt {
        private String secret;
        private Integer expiration;
    }
}
