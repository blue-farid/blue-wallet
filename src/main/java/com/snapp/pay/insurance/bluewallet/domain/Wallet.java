package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Table
@Entity
@Accessors(chain = true)
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long customerId;
    @NotNull
    private BigDecimal balance = BigDecimal.valueOf(0);
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public void decreaseBalance(BigDecimal balance) {
        this.balance = this.balance.subtract(balance);
    }

    public void increaseBalance(BigDecimal balance) {
        this.balance = this.balance.add(balance);
    }
}
