package com.snapp.pay.insurance.bluewallet.mapper;

import com.snapp.pay.insurance.bluewallet.api.v1.model.CustomerDto;
import com.snapp.pay.insurance.bluewallet.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
}
