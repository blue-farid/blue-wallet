package com.snapp.pay.insurance.bluewallet.mapper;

import com.snapp.pay.insurance.bluewallet.api.v1.model.TransactionDto;
import com.snapp.pay.insurance.bluewallet.domain.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDto(Transaction transaction);
    Transaction toEntity(TransactionDto dto);
}
