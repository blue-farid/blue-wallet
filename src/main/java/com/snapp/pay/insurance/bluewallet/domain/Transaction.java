package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromWalletId;
    private Long toWalletId;
    private BigDecimal amount;
    @CreationTimestamp
    private Date createdAt;
}
