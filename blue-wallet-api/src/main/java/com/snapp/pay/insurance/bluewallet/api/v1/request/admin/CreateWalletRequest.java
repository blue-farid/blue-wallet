package com.snapp.pay.insurance.bluewallet.api.v1.request.admin;

import com.snapp.pay.insurance.bluewallet.api.v1.model.WalletDto;
import lombok.Data;

@Data
public class CreateWalletRequest {
    private WalletDto wallet;
}
