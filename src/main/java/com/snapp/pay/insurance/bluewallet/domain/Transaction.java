package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

//TODO is data dangerous?
@Data
@Table
@Entity
@Accessors(chain = true)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long walletId;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    @CreationTimestamp
    private Date createdAt;
}
