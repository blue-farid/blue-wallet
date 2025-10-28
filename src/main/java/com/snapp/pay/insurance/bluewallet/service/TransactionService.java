package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.request.GetTransactionsRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.TransferRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.admin.IncreaseBalanceRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetTransactionsResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransferResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.admin.IncreaseBalanceResponse;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    TransferResponse transfer(TransferRequest request, Long customerId);

    GetTransactionsResponse getTransactions(GetTransactionsRequest request, Long customerId, Pageable pageable);

    IncreaseBalanceResponse increaseBalance(IncreaseBalanceRequest request);
}
