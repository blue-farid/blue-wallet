package com.snapp.pay.insurance.bluewallet.api.v1.request.admin;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncreaseBalanceRequest {
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Long walletId;
}
