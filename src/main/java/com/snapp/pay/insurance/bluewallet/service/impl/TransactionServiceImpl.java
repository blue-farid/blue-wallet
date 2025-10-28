package com.snapp.pay.insurance.bluewallet.service.impl;

import com.snapp.pay.insurance.bluewallet.api.v1.model.TransactionDto;
import com.snapp.pay.insurance.bluewallet.api.v1.request.GetTransactionsRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.TransferRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.admin.IncreaseBalanceRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetTransactionsResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransferResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.admin.IncreaseBalanceResponse;
import com.snapp.pay.insurance.bluewallet.domain.Transaction;
import com.snapp.pay.insurance.bluewallet.domain.TransactionType;
import com.snapp.pay.insurance.bluewallet.domain.Wallet;
import com.snapp.pay.insurance.bluewallet.exception.wallet.WalletNotFoundException;
import com.snapp.pay.insurance.bluewallet.mapper.TransactionMapper;
import com.snapp.pay.insurance.bluewallet.mapper.WalletMapper;
import com.snapp.pay.insurance.bluewallet.repository.TransactionRepository;
import com.snapp.pay.insurance.bluewallet.repository.WalletRepository;
import com.snapp.pay.insurance.bluewallet.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final TransactionMapper transactionMapper;
    private final WalletMapper walletMapper;

    @Override
    @Transactional
    public TransferResponse transfer(TransferRequest request, Long customerId) {
        Wallet sourceWallet = walletRepository.findByCustomerId(customerId).orElseThrow(WalletNotFoundException::new);
        Wallet destinationWallet = walletRepository.findById(request.getToWalletId()).orElseThrow(WalletNotFoundException::new);
        BigDecimal amount = request.getAmount();
        sourceWallet.decreaseBalance(amount);
        destinationWallet.increaseBalance(amount);
        Transaction t1 = new Transaction()
                .setAmount(amount)
                .setType(TransactionType.CREDIT)
                .setWalletId(destinationWallet.getId());
        Transaction t2 = new Transaction()
                .setAmount(amount)
                .setType(TransactionType.DEBIT)
                .setWalletId(sourceWallet.getId());
        transactionRepository.saveAll(List.of(t1, t2));
        sourceWallet = walletRepository.save(sourceWallet);
        walletRepository.save(destinationWallet);
        return new TransferResponse()
                .setWallet(walletMapper.toDto(sourceWallet));
    }

    @Override
    public GetTransactionsResponse getTransactions(GetTransactionsRequest request, Long customerId, Pageable pageable) {
        Wallet wallet = walletRepository.findByCustomerId(customerId).orElseThrow(WalletNotFoundException::new);
        Page<TransactionDto> transactions = transactionRepository
                .findByWalletId(wallet.getId(), pageable)
                .map(transactionMapper::toDto);
        return new GetTransactionsResponse()
                .setTransactions(transactions);
    }

    @Override
    @Transactional
    public IncreaseBalanceResponse increaseBalance(IncreaseBalanceRequest request) {
        Wallet wallet = walletRepository.findByCustomerId(request.getWalletId()).orElseThrow(WalletNotFoundException::new);
        Transaction transaction = new Transaction()
                .setAmount(request.getAmount())
                .setType(TransactionType.CREDIT)
                .setWalletId(wallet.getId());

        wallet.increaseBalance(request.getAmount());

        transactionRepository.save(transaction);
        walletRepository.save(wallet);

        return new IncreaseBalanceResponse()
                .setWallet(walletMapper.toDto(wallet));
    }
}
