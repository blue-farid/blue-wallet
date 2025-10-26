package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;
import com.snapp.pay.insurance.bluewallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    //TODO get customer id from token
    @GetMapping
    public ResponseEntity<GetWalletResponse> getWallet() {
        return ResponseEntity.ok(walletService.getWallet(Long.parseLong("1")));
    }
}
