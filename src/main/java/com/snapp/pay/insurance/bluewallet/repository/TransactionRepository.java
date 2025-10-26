package com.snapp.pay.insurance.bluewallet.repository;

import com.snapp.pay.insurance.bluewallet.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
