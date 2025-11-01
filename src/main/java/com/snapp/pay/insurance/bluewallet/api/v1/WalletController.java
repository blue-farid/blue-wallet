package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.request.admin.CreateWalletRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.GetWalletResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.response.admin.CreateWalletResponse;
import com.snapp.pay.insurance.bluewallet.constant.ApiStatus;
import com.snapp.pay.insurance.bluewallet.service.WalletService;
import com.snapp.pay.insurance.bluewallet.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.snapp.pay.insurance.bluewallet.constant.AuthoritiesConstant.ADMIN;
import static com.snapp.pay.insurance.bluewallet.constant.AuthoritiesConstant.CUSTOMER;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final SecurityUtil securityUtil;

    @Secured(CUSTOMER)
    @GetMapping
    public ResponseEntity<ApiResponse<GetWalletResponse>> getWallet() {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name(),
                walletService.getWallet(securityUtil.getCurrentUserId())));
    }

    @Secured(ADMIN)
    @PostMapping
    public ResponseEntity<ApiResponse<CreateWalletResponse>> createWallet(@RequestBody @Valid CreateWalletRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name(),
                walletService.createWallet(request)));
    }
}
