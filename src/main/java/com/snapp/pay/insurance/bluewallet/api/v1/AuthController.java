package com.snapp.pay.insurance.bluewallet.api.v1;

import com.snapp.pay.insurance.bluewallet.api.v1.model.ApiResponse;
import com.snapp.pay.insurance.bluewallet.api.v1.request.LoginOrSignupRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.request.OtpRequest;
import com.snapp.pay.insurance.bluewallet.api.v1.response.LoginOrSignupResponse;
import com.snapp.pay.insurance.bluewallet.constant.ApiStatus;
import com.snapp.pay.insurance.bluewallet.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//TODO we could add a flow for login with password too

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @GetMapping("/otp")
    public ResponseEntity<ApiResponse<Void>> getOtp(@Validated OtpRequest request) {
        authService.sendOtp(request);
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LoginOrSignupResponse>> login(@RequestBody LoginOrSignupRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(ApiStatus.SUCCESS.name(), authService.login(request)));
    }
}
