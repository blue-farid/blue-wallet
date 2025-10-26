package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;
import com.snapp.pay.insurance.bluewallet.service.WalletService;
import com.snapp.pay.insurance.bluewallet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.snapp.pay.insurance.bluewallet.constant.AuthoritiesConstant.CUSTOMER;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final SecurityUtil securityUtil;

    @Secured(CUSTOMER)
    @GetMapping
    public ResponseEntity<GetWalletResponse> getWallet() {
        return ResponseEntity.ok(walletService.getWallet(securityUtil.getCurrentUserId()));
    }
}
