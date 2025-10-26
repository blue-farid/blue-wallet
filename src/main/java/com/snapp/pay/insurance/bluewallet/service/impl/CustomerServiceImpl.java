package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.model.CustomerDto;
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
    public CustomerDto createCustomer(CustomerDto dto) {
       return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
}
