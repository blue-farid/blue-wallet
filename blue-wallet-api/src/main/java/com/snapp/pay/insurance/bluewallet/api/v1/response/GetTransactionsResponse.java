package com.snapp.pay.insurance.bluewallet.api.v1.response;

import com.snapp.pay.insurance.bluewallet.api.v1.model.TransactionDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

@Data
@Accessors(chain = true)
public class GetTransactionsResponse {
    private Page<TransactionDto> transactions;
}
