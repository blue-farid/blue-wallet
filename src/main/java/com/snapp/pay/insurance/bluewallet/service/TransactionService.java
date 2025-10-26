package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.request.GetTransactionsRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.TransactionRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetTransactionsResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransactionResponse;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    TransactionResponse transaction(TransactionRequest request);
    GetTransactionsResponse getTransactions(GetTransactionsRequest request, Long customerId, Pageable pageable);
}
