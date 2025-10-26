package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.request.TransactionRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse transaction(TransactionRequest request);
}
