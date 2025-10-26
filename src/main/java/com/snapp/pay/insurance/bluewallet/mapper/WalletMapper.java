package com.snapp.pay.insurance.bluewallet.mapper;

import com.snapp.pay.insurance.bluewallet.api.v1.model.WalletDto;
import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDto toDto(Wallet wallet);
}
