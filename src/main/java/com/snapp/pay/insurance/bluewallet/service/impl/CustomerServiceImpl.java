package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;
import com.snapp.pay.insurance.bluewallet.domain.Customer;
import com.snapp.pay.insurance.bluewallet.mapper.CustomerMapper;
import com.snapp.pay.insurance.bluewallet.repository.CustomerRepository;
import com.snapp.pay.insurance.bluewallet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public LoginOrSignupResponse login(LoginOrSignupRequest dto) {
        //TODO validate the otp
        Customer customer = repository.findByMail(dto.getMail()).orElse(newCustomer(dto));
        //TODO jwt implementation
        return new LoginOrSignupResponse()
                .setCustomer(mapper.toDto(customer))
                .setToken("jwt-token");
    }

    private Customer newCustomer(LoginOrSignupRequest dto) {
        return repository.save(new Customer().setMail(dto.getMail()));
    }
}
