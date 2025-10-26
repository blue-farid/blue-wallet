package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;

public interface WalletService {
    GetWalletResponse getWallet(Long customerId);
}
