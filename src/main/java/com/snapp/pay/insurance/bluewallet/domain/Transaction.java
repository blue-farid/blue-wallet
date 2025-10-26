package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

//TODO is data dangerous?
@Data
@Table
@Entity
public class Transaction {
    @Id
    private Long id;
    private Long fromWalletId;
    private Long toWalletId;
    private BigDecimal amount;
    @CreationTimestamp
    private Date createdAt;
}
