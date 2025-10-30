package com.snapp.pay.insurance.bluewallet.api.v1.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//TODO we should not show our database id
@Data
public class TransactionDto {
    private Long id;
    @NotNull(message = "validation.wallet.id.null")
    private Long walletId;
    private String type;
    @NotNull(message = "validation.transaction.amount.id.null")
    private BigDecimal amount;
    private Date date;
}
