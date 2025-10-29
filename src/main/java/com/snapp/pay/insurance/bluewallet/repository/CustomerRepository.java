package com.snapp.pay.insurance.bluewallet.repository;

import com.snapp.pay.insurance.bluewallet.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//TODO cache
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMail(String mail);
}
