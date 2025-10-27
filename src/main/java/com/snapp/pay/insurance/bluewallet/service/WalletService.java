package com.snapp.pay.insurance.bluewallet.service;

import com.snapp.pay.insurance.bluewallet.api.v1.request.admin.CreateWalletRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.admin.CreateWalletResponse;

public interface WalletService {
    GetWalletResponse getWallet(Long customerId);
    CreateWalletResponse createWallet(CreateWalletRequest request);
}
