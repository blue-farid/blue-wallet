package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.request.GetTransactionsRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.TransferRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.admin.IncreaseBalanceRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetTransactionsResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransferResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.admin.IncreaseBalanceResponse;
import com.snapp.pay.insurance.bluewallet.constant.ApiStatus;
import com.snapp.pay.insurance.bluewallet.service.TransactionService;
import com.snapp.pay.insurance.bluewallet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.snapp.pay.insurance.bluewallet.constant.AuthoritiesConstant.ADMIN;
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
    public ResponseEntity<ApiResponse<GetTransactionsResponse>> getTransactions(GetTransactionsRequest request, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name(),
                transactionService.getTransactions(request, securityUtil.getCurrentUserId(), pageable)));
    }

    @Secured(CUSTOMER)
    @PostMapping("/transfers")
    public ResponseEntity<ApiResponse<TransferResponse>> transaction(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name(),
                transactionService.transfer(request, securityUtil.getCurrentUserId())));
    }

    @Secured(ADMIN)
    @PostMapping
    public ResponseEntity<ApiResponse<IncreaseBalanceResponse>> increaseBalance(@RequestBody IncreaseBalanceRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name(),
                transactionService.increaseBalance(request)));
    }
}
