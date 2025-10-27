package com.snapp.pay.insurance.bluewallet.mapper;

import com.snapp.pay.insurance.bluewallet.api.v1.model.WalletDto;
import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDto toDto(Wallet wallet);
    @Mapping(target = "balance", defaultValue = "0")
    Wallet toEntity(WalletDto wallet);
}
