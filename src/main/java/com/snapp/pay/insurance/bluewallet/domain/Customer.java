package com.snapp.pay.insurance.bluewallet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity
public class Customer {
    @Id
    private Long id;
    private String username;
    private String password;
}
