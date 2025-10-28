package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

//TODO add transactions here? search
@Data
@Table
@Entity
@Accessors(chain = true)
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private Long customerId;
    @NotNull
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;

    public void decreaseBalance(BigDecimal balance) {
        this.balance = this.balance.subtract(balance);
    }

    public void increaseBalance(BigDecimal balance) {
        this.balance = this.balance.add(balance);
    }
}
