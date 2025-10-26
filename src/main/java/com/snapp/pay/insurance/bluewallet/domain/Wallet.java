package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Table
@Entity
public class Wallet {
    @Id
    private Long id;
    private Long customerId;
    private BigDecimal balance;
}
