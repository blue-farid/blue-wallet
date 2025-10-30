package com.snapp.pay.insurance.bluewallet.api.v1.response.admin;

import com.snapp.pay.insurance.bluewallet.api.v1.model.WalletDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IncreaseBalanceResponse {
    private WalletDto wallet;
}
