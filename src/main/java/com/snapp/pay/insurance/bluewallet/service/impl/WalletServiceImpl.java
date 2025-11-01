package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.request.admin.CreateWalletRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.admin.CreateWalletResponse;
import com.snapp.pay.insurance.bluewallet.exception.customer.CustomerNotFoundException;
import com.snapp.pay.insurance.bluewallet.exception.wallet.MultipleWalletException;
import com.snapp.pay.insurance.bluewallet.exception.wallet.WalletNotFoundException;
import com.snapp.pay.insurance.bluewallet.mapper.WalletMapper;
import com.snapp.pay.insurance.bluewallet.repository.CustomerRepository;
import com.snapp.pay.insurance.bluewallet.repository.WalletRepository;
import com.snapp.pay.insurance.bluewallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final CustomerRepository customerRepository;
    private final WalletMapper mapper;

    @Override
    public GetWalletResponse getWallet(Long customerId) {
        return new GetWalletResponse()
                .setWallet(mapper.toDto(walletRepository.findByCustomerId(customerId)
                        .orElseThrow(WalletNotFoundException::new)));
    }

    @Override
    public CreateWalletResponse createWallet(CreateWalletRequest request) {
        if (customerRepository.findById(request.getWallet().getCustomerId()).isEmpty()) {
            throw new CustomerNotFoundException();
        }
        if (walletRepository.findByCustomerId(request.getWallet().getCustomerId()).isPresent()) {
            throw new MultipleWalletException();
        }
        return new CreateWalletResponse()
                .setWallet(mapper.toDto(walletRepository
                        .save(mapper.toEntity(request.getWallet()))));
    }
}
