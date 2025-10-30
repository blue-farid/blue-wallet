package com.snapp.pay.insurance.bluewallet.api.v1.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletDto {
    private Long id;
    @NotNull
    private Long customerId;
    @NotNull(message = "validation.wallet.balance.null")
    private BigDecimal balance;
}
