package com.snapp.pay.insurance.bluewallet.api.v1.request.admin;

import com.snapp.pay.insurance.bluewallet.api.v1.model.WalletDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateWalletRequest {
    private WalletDto wallet;
}
