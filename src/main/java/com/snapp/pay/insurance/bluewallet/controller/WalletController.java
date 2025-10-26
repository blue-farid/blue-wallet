package com.snapp.pay.insurance.bluewallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {
    @GetMapping("/balance")
    public ResponseEntity<Void> getBalance() {
        return ResponseEntity.ok().build();
    }
    @PostMapping("/transactions")
    public ResponseEntity<Void> getTransactions() {
        return ResponseEntity.ok().build();
    }
}
