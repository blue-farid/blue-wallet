package com.snapp.pay.insurance.bluewallet.repository;

import com.snapp.pay.insurance.bluewallet.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
