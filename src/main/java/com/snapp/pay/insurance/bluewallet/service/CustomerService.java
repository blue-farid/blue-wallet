package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.model.CustomerDto;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto dto);
}
