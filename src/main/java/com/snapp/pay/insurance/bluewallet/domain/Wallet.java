package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Table
@Entity
public class Wallet {
    @Id
    private Long id;
    private Long customerId;
    private BigDecimal balance;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
}
