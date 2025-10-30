package com.snapp.pay.insurance.bluewallet.api.v1.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotNull(message = "validation.wallet.id.null")
    private Long toWalletId;
    @NotNull(message = "validation.transaction.amount.id.null")
    private BigDecimal amount;
}
