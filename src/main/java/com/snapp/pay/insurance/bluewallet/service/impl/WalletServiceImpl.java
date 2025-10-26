package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;
import com.snapp.pay.insurance.bluewallet.mapper.WalletMapper;
import com.snapp.pay.insurance.bluewallet.repository.WalletRepository;
import com.snapp.pay.insurance.bluewallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository repository;
    private final WalletMapper mapper;

    //TODO customer exception
    @Override
    public GetWalletResponse getWallet(Long customerId) {
        return new GetWalletResponse()
                .setWallet(mapper.toDto(repository.findByCustomerId(customerId)
                        .orElseThrow(RuntimeException::new)));
    }
}
