package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.OtpRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;
import com.snapp.pay.insurance.bluewallet.constant.ApiStatus;
import com.snapp.pay.insurance.bluewallet.exception.auth.RateLimitException;
import com.snapp.pay.insurance.bluewallet.service.AuthenticationService;
import com.snapp.pay.insurance.bluewallet.util.RateLimitUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO we could add a flow for login with password too
//TODO circuit breaker
//TODO bulkhead

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;
    private final RateLimitUtil rateLimitUtil;

    @GetMapping("/otp")
    public ResponseEntity<ApiResponse<Void>> getOtp(@Valid OtpRequest request) {
        if (!rateLimitUtil.isOtpRequestAllowed(request.getMail())) {
            throw new RateLimitException();
        }

        authService.sendOtp(request);
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LoginOrSignupResponse>> login(@RequestBody @Valid LoginOrSignupRequest request) {
        if (!rateLimitUtil.isLoginRequestAllowed(request.getMail())) {
            throw new RateLimitException();
        }
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name(), authService.login(request)));
    }
}
