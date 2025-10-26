package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.request.TransactionRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransactionResponse;
import com.snapp.pay.insurance.bluewallet.domain.Transaction;
import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import com.snapp.pay.insurance.bluewallet.mapper.TransactionMapper;
import com.snapp.pay.insurance.bluewallet.mapper.WalletMapper;
import com.snapp.pay.insurance.bluewallet.repository.TransactionRepository;
import com.snapp.pay.insurance.bluewallet.repository.WalletRepository;
import com.snapp.pay.insurance.bluewallet.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final TransactionMapper transactionMapper;
    private final WalletMapper walletMapper;

    @Override
    @Transactional
    public TransactionResponse transaction(TransactionRequest request) {
        Wallet sourceWallet = walletRepository.findById(request.getTransaction().getFromWalletId()).orElseThrow();
        Wallet destinationWallet = walletRepository.findById(request.getTransaction().getToWalletId()).orElseThrow();
        BigDecimal amount = request.getTransaction().getAmount();
        sourceWallet.decreaseBalance(amount);
        destinationWallet.increaseBalance(amount);
        Transaction result = transactionRepository.save(transactionMapper.toEntity(request.getTransaction()));
        sourceWallet = walletRepository.save(sourceWallet);
        walletRepository.save(destinationWallet);
        return new TransactionResponse()
                .setTransaction(transactionMapper.toDto(result))
                .setWallet(walletMapper.toDto(sourceWallet));
    }
}
