package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.request.TransactionRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {
    @GetMapping
    public ResponseEntity<Void> getTransactions() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> transaction(TransactionRequest request) {
        return ResponseEntity.ok().build();
    }
}
