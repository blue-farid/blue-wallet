package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.request.GetTransactionsRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.TransactionRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetTransactionsResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransactionResponse;
import com.snapp.pay.insurance.bluewallet.constant.AuthoritiesConstant;
import com.snapp.pay.insurance.bluewallet.service.TransactionService;
import com.snapp.pay.insurance.bluewallet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.snapp.pay.insurance.bluewallet.constant.AuthoritiesConstant.CUSTOMER;

@RequestMapping("/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final SecurityUtil securityUtil;

    //TODO add filters
    @Secured(CUSTOMER)
    @GetMapping
    public ResponseEntity<GetTransactionsResponse> getTransactions(GetTransactionsRequest request, @PageableDefault Pageable pageable) {
       return ResponseEntity.ok(transactionService.getTransactions(request, securityUtil.getCurrentUserId(), pageable));
    }

    @Secured(CUSTOMER)
    @PostMapping
    public ResponseEntity<TransactionResponse> transaction(TransactionRequest request) {
        return ResponseEntity.ok(transactionService.transaction(request));
    }
}
