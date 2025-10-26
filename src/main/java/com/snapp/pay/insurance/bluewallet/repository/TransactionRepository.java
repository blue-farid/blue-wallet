package com.snapp.pay.insurance.bluewallet.repository;

import com.snapp.pay.insurance.bluewallet.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByFromWalletId(Long fromWalletId, Pageable pageable);
}
