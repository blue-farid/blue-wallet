package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;
import com.snapp.pay.insurance.bluewallet.domain.Customer;
import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import com.snapp.pay.insurance.bluewallet.mapper.CustomerMapper;
import com.snapp.pay.insurance.bluewallet.repository.CustomerRepository;
import com.snapp.pay.insurance.bluewallet.repository.WalletRepository;
import com.snapp.pay.insurance.bluewallet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final WalletRepository walletRepository;
    private final CustomerMapper mapper;

    @Override
    @Transactional
    public LoginOrSignupResponse login(LoginOrSignupRequest request) {
        //TODO validate the otp
        Customer customer = customerRepository.findByMail(request.getMail()).orElse(newCustomer(request));
        //TODO jwt implementation
        return new LoginOrSignupResponse()
                .setCustomer(mapper.entityToDto(customer))
                .setToken("jwt-token");
    }

    private Customer newCustomer(LoginOrSignupRequest request) {
        Customer customer = customerRepository.save(new Customer().setMail(request.getMail()));
        walletRepository.save(
                new Wallet().setCustomerId(customer.getId())
        );
        return customer;
    }
}
