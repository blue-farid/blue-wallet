package com.snapp.pay.insurance.bluewallet.repository;

import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCustomerId(Long customerId);
}
